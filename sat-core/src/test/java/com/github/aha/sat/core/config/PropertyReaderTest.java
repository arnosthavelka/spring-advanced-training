package com.github.aha.sat.core.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnableConfigurationProperties(PropertyReader.class)
class PropertyReaderTest {

    @Autowired
    PropertyReader propertyReader;

    @Test
    void definedValues() {
        assertThat(propertyReader.getText()).isNotEmpty();
        assertThat(propertyReader.getSmallNumber()).isNotNull();
        assertThat(propertyReader.getLongNumber()).isNotNull();
        assertThat(propertyReader.getNumberLessThanTen()).isLessThanOrEqualTo(10);
        assertThat(propertyReader.getNumberInRange()).isBetween(1024, 65536);
    }

}
