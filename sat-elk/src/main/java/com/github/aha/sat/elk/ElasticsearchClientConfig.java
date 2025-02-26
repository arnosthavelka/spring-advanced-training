package com.github.aha.sat.elk;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class ElasticsearchClientConfig extends ElasticsearchConfiguration {

	@Value("${spring.elasticsearch.rest.uris}")
	String connectionUrl;

	@Override
	public ClientConfiguration clientConfiguration() {
		return ClientConfiguration.builder()
				.connectedTo(connectionUrl)
				.usingSsl(createSSLContext(), new NoopHostnameVerifier() )
				.withBasicAuth("elastic", "elastic")
				.build();
	}
	
    private SSLContext createSSLContext() {
        try {
			return org.apache.http.ssl.SSLContexts.custom()
					.loadTrustMaterial(new TrustAllStrategy())
					.build();
        } catch (Exception e) {
            log.error("cannot create SSLContext", e);
        }
        return null;
    }
    
}
