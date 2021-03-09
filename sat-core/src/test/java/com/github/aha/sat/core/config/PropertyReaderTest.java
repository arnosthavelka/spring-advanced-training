package com.github.aha.sat.core.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest(classes = { PropertyReader.class })
class PropertyReaderTest {

	@Autowired
	ApplicationContext context;

	@Test
	void definedValues() {
		PropertyReader propertyReader = context.getBean(PropertyReader.class);

		assertThat(propertyReader.getValue()).isNotEmpty();
		assertThat(propertyReader.getNumber()).isNotNull();
		assertThat(propertyReader.getLongnumber()).isNotNull();
		assertThat(propertyReader.getInt10()).isBetween(0, 10);
		assertThat(propertyReader.getIntRange()).isBetween(1024, 65536);
	}

}
