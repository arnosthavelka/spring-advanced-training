package com.github.aha.sat.rest.config;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class InfoService implements InfoContributor {

	@Override
	public void contribute(Info.Builder builder) {
		builder.withDetail("author", "Arny");
	}

}
