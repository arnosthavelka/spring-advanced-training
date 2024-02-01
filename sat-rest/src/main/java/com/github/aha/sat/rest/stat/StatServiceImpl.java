package com.github.aha.sat.rest.stat;

import jakarta.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.stat.EntityStatistics;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StatServiceImpl implements StatService {

    @Autowired
    private EntityManager em;

    public Statistics getStatistics() {
        var session = (Session) this.em.getDelegate();
        return session.getSessionFactory().getStatistics();
    }

    public SessionFactory getSessionFactory() {
        var session = (Session) this.em.getDelegate();
        return session.getSessionFactory();
    }

    @Override
    public String[] getEntityNames() {
        return getStatistics().getEntityNames();
    }

    @Override
    public String[] getQueries() {
        return getStatistics().getQueries();
    }

    @Override
    public EntityStatistics getEntityStatistics(String entityName) {
        return getStatistics().getEntityStatistics("com.github.aha.sat.rest.domain." + entityName);
    }

}
