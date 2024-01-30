package com.github.aha.sat.core.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerBfpp implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		String[] beanNames = beanFactory.getBeanDefinitionNames();
		for (String beanName : beanNames) {
			var beanDefinition = beanFactory.getBeanDefinition(beanName);
			log.info("Bean '{}': scope={}, singleton={}", beanName, beanDefinition.getScope(),
					beanDefinition.isSingleton());
		}
	}

}
