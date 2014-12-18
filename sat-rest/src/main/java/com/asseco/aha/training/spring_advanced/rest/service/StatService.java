package com.asseco.aha.training.spring_advanced.rest.service;

import org.hibernate.SessionFactory;
import org.hibernate.stat.EntityStatistics;
import org.hibernate.stat.Statistics;

public interface StatService {

    Statistics getStatistics();

    SessionFactory getSessionFactory();

    String[] getEntityNames();

    String[] getQueries();

    EntityStatistics getEntityStatistics(String entityName);

}