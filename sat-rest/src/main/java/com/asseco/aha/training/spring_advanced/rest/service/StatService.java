package com.asseco.aha.training.spring_advanced.rest.service;

import org.hibernate.stat.EntityStatistics;

public interface StatService {

    String[] getEntityNames();

    String[] getQueries();

    EntityStatistics getEntityStatistics(String entityName);

}