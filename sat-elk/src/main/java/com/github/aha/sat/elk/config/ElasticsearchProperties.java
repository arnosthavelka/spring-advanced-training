package com.github.aha.sat.elk.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties( prefix = "elk" )
@Getter
@Setter
public class ElasticsearchProperties {

    private boolean securityEnabled;
    private String host;
    private String username;
    private String password;

}
