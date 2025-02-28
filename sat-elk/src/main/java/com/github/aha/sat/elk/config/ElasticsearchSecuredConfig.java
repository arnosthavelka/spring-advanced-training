package com.github.aha.sat.elk.config;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@ConditionalOnProperty(name = "elk.security-enabled", havingValue = "true")
@RequiredArgsConstructor
@Slf4j
public class ElasticsearchSecuredConfig extends ElasticsearchConfiguration {

	@Getter
	private final ElasticsearchProperties elkProperties;

	@Override
	public ClientConfiguration clientConfiguration() {
		return ClientConfiguration.builder()
				.connectedTo(elkProperties.getHost())
				.usingSsl(createSSLContext(), new NoopHostnameVerifier() )
				.withBasicAuth(elkProperties.getUsername(), elkProperties.getPassword())
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
