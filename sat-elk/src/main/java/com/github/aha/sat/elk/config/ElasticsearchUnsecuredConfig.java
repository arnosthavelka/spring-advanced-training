package com.github.aha.sat.elk.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Configuration
@ConditionalOnProperty(name = "elk.security-enabled", havingValue = "false", matchIfMissing = true)
@RequiredArgsConstructor
public class ElasticsearchUnsecuredConfig extends ElasticsearchConfiguration {

	@Getter
	private final ElasticsearchProperties elkProperties;

	@Override
	public ClientConfiguration clientConfiguration() {
		return ClientConfiguration.builder().connectedTo(elkProperties.getHost()).build();
	}

}
