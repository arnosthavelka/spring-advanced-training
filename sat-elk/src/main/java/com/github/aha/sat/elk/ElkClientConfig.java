package com.github.aha.sat.elk;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

@Configuration
public class ElkClientConfig extends ElasticsearchConfiguration {

	@Override
	public ClientConfiguration clientConfiguration() {
		return ClientConfiguration.builder()
				.connectedTo("oxygen-arnost.ifs.dev.fra.aws.dbgcloud.io:9200")
				.build();
	}

}
