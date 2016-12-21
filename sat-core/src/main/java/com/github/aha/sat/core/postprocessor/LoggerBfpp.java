package com.github.aha.sat.core.postprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class LoggerBfpp implements BeanFactoryPostProcessor {

    private Logger LOG = LoggerFactory.getLogger(LoggerBfpp.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] beanNames = beanFactory.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            LOG.info("Bean '{}': scope={}, singleton={}", beanName, beanDefinition.getScope(), beanDefinition.isSingleton());
        }
    }
}
