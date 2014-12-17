package com.asseco.aha.training.spring_advanced.rest.service;

import javax.persistence.EntityManager;

import org.hibernate.Session;
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

    private Statistics getStatistics() {
        Session session = (Session) this.em.getDelegate();
        // session.getSessionFactory().getStatistics().setStatisticsEnabled(true);
        return session.getSessionFactory().getStatistics();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.asseco.aha.training.spring_advanced.rest.service.StatService#getEntityNames()
     */
    @Override
    public String[] getEntityNames() {
        return getStatistics().getEntityNames();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.asseco.aha.training.spring_advanced.rest.service.StatService#getQueries()
     */
    @Override
    public String[] getQueries() {
        return getStatistics().getQueries();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.asseco.aha.training.spring_advanced.rest.service.StatService#getEntityStatistics(java.lang.String)
     */
    @Override
    public EntityStatistics getEntityStatistics(String entityName) {
        return getStatistics().getEntityStatistics("com.asseco.aha.training.spring_advanced.rest.domain." + entityName);
    }
}
