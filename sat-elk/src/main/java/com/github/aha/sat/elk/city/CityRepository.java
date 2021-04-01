package com.github.aha.sat.elk.city;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CityRepository extends ElasticsearchRepository<City, String> {

	Page<City> findByCountry(String name, Pageable pageable);

}
