package com.asseco.aha.training.spring_advanced.core;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProxyModeApplication.class)
public class ProxyModeTest {

	@Autowired
	@Qualifier("beanSingleton")
	private TokenBean bean;

	@Test
	public void contextLoads() {
		Assert.assertNotEquals(bean.getToken(), bean.getToken());
		// Assert.assertNotEquals(bean.toString(), bean.toString());
	}
}
