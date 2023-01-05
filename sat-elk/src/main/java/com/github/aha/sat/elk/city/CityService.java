package com.github.aha.sat.elk.city;

import static com.fasterxml.jackson.dataformat.csv.CsvParser.Feature.FAIL_ON_MISSING_HEADER_COLUMNS;
import static java.util.Objects.nonNull;
import static org.springframework.data.elasticsearch.core.SearchHitSupport.searchPageFor;
import static org.springframework.data.elasticsearch.core.SearchHitSupport.unwrapSearchHits;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.github.aha.sat.elk.ElkException;

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

	List<City> parseFile(String csvFileName) {
		try {
			var csvFile = Path.of(csvFileName);
			return csvMapper
					.disable(FAIL_ON_MISSING_HEADER_COLUMNS)
					.readerFor(City.class)
					.with(schema)
					.<City>readValues(csvFile.toFile())
					.readAll();
		} catch (IOException e) {
			throw new ElkException(e);
		}
	}

	private void storeData(List<City> cities) {
		final var counter = new AtomicInteger();

		final Collection<List<City>> chunks = cities.stream()
				.collect(Collectors.groupingBy(it -> counter.getAndIncrement() / BULK_SIZE))
				.values();
		counter.set(0);
		chunks.forEach(ch -> {
			repository.saveAll(ch);
			log.info("bulk of cities stored [{}/{}] ...", counter.getAndIncrement(), chunks.size());
		});
	}

	public City findById(String cityId) {
		return repository.findById(cityId).orElseThrow(() -> new ElkException("City with ID=" + cityId + " was not found!"));
	}

	public Page<City> searchByCountry(String country, Pageable pageable) {
		return repository.findByCountry(country, pageable);
	}

	@SuppressWarnings("unchecked")
	public Page<City> search(String name, String country, String subcountry, Pageable pageable) {
		return (Page<City>) unwrapSearchHits(searchPageFor(searchHits(name, country, subcountry, pageable), pageable));
	}

	SearchHits<City> searchHits(String name, String country, String subcountry, Pageable pageable) {
		CriteriaQuery query = buildSearchQuery(name, country, subcountry);
		query.setPageable(pageable);

		return esTemplate.search(query, City.class);
	}

	private CriteriaQuery buildSearchQuery(String name, String country, String subcountry) {
		var criteria = new Criteria();
		if (nonNull(name)) {
			criteria.and(new Criteria("name").contains(name));
		}
		if (nonNull(country)) {
			criteria.and(new Criteria("country").expression(country));
		}
		if (nonNull(subcountry)) {
			criteria.and(new Criteria("subcountry").is(subcountry));
		}
		return new CriteriaQuery(criteria);
	}

}
