package com.github.aha.sat.elk.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.github.aha.sat.elk.city.CityRepository;

@SpringBootTest
@TestPropertySource(properties = "elk.security-enabled=false")
class ElasticsearchUnsecuredConfigTest {

	@MockitoBean
	CityRepository cityRepository;

	@Autowired
	ElasticsearchUnsecuredConfig config;

	@Test
	void clientConfiguration() {
		assertThat(config.clientConfiguration().getSslContext()).isEmpty();
		assertThat(config.getElkProperties().getHost()).contains("oxygen-hh310");
	}

}
