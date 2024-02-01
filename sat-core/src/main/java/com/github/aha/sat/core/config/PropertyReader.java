package com.github.aha.sat.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "custom.value")
@Getter
@Setter
public class PropertyReader {

    private String text;

    private Long longNumber;

    private Integer smallNumber;

    private Integer numberLessThanTen;

    private Integer numberInRange;

}
