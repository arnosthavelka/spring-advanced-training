package com.github.aha.sat.rest.service;

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