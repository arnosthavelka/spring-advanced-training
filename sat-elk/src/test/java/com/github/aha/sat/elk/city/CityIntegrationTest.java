package com.github.aha.sat.elk.city;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.junit.jupiter.api.Test;
import org.testcontainers.elasticsearch.ElasticsearchContainer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class CityIntegrationTest {

	private static final String ELASTICSEARCH_DEFAULT_IMAGE = "docker.elastic.co/elasticsearch/elasticsearch";
	private static final String ELASTICSEARCH_USERNAME = "admin";
	private static final String ELASTICSEARCH_PASSWORD = "admin";

	@Test
	void test() throws IOException {
		// Create the elasticsearch container.
		try (ElasticsearchContainer container = new ElasticsearchContainer(ELASTICSEARCH_DEFAULT_IMAGE)) {
		    // Start the container. This step might take some time...
		    container.start();

		    // Do whatever you want with the rest client ...
		    final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		    credentialsProvider.setCredentials(AuthScope.ANY,
		        new UsernamePasswordCredentials(ELASTICSEARCH_USERNAME, ELASTICSEARCH_PASSWORD));

			RestClient client = RestClient.builder(HttpHost.create(container.getHttpHostAddress()))
		        .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider))
		        .build();

		    Response response = client.performRequest(new Request("GET", "/_cluster/health"));

			log.info("Response: {}n", response);
		}
	}

}
