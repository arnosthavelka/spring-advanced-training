package com.github.aha.sat.elk.city;

import static java.util.Objects.nonNull;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CityService {

	private static final int BULK_SIZE = 500;

	@NonNull
	final CityRepository repository;

	@NonNull
	final ElasticsearchOperations esTemplate;

	final CsvMapper csvMapper = new CsvMapper();

	final CsvSchema schema = csvMapper
			.typedSchemaFor(City.class)
			.withHeader()
			.withColumnReordering(true);

	public void uploadFile(String csvFileName) {
		log.info("loading file {} ...", csvFileName);
		List<City> csvData = parseFile(csvFileName);
		log.info("{} entries loaded from CSV file", csvData.size());
		storeData(csvData);
		log.info("data loading finish");
	}

	private List<City> parseFile(String csvFileName) {
		try {
			return csvMapper
					.readerFor(City.class)
					.with(schema)
					.<City>readValues(new File(csvFileName))
					.readAll();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void storeData(List<City> instData) {
		final AtomicInteger counter = new AtomicInteger();

		final Collection<List<City>> chunks = instData.stream()
				.collect(Collectors.groupingBy(it -> counter.getAndIncrement() / BULK_SIZE))
				.values();
		counter.set(0);
		chunks.forEach(ch -> {
			repository.saveAll(ch);
			log.info("inst bulk stored [{}/{}] ...", counter.getAndIncrement(), chunks.size());
		});
	}

	public Optional<City> getOne(String cityId) {
		return repository.findById(cityId);
	}

	public Page<City> search(City city, Pageable pageable) {
		IndexCoordinates index = IndexCoordinates.of(City.INDEX);

		CriteriaQuery query = buildSearchQuery(city);
		query.setPageable(pageable);
		return esTemplate.queryForPage(query, City.class, index);
	}

	private CriteriaQuery buildSearchQuery(City city) {
		Criteria criteria = new Criteria();
		if (nonNull(city.getName())) {
			criteria.and(new Criteria("name").is(city.getName()));
		}
		if (nonNull(city.getCountry())) {
			criteria.and(new Criteria("country").contains(city.getCountry()));
		}
		if (nonNull(city.getSubcountry())) {
			criteria.and(new Criteria("subcountry").is(city.getSubcountry()));
		}
		return new CriteriaQuery(criteria);
	}

}
