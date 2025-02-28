package com.github.aha.sat.elk;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.github.aha.sat.elk.city.CityRepository;
import com.github.aha.sat.elk.config.ElasticsearchSecuredConfig;

@SpringBootTest
class ElasticsearchClientConfigTest {

	@MockBean
	CityRepository cityRepository;

	@Autowired
	ElasticsearchSecuredConfig elasticsearchClientConfig;

	@Test
	void clientConfiguration() {
		assertThat(elasticsearchClientConfig.getElkProperties().getHost()).contains("oxygen-hh310");
	}

}
