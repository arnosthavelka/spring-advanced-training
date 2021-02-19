package com.github.aha.sat.rest.stat;

import javax.persistence.EntityManager;

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
        Session session = (Session) this.em.getDelegate();
        // session.getSessionFactory().getStatistics().setStatisticsEnabled(true);
        return session.getSessionFactory().getStatistics();
    }

    public SessionFactory getSessionFactory() {
        Session session = (Session) this.em.getDelegate();
        return session.getSessionFactory();
    }

    /*
     * (non-Javadoc)
     * @see com.github.aha.sat.rest.service.StatService#getEntityNames()
     */
    @Override
    public String[] getEntityNames() {
        return getStatistics().getEntityNames();
    }

    /*
     * (non-Javadoc)
     * @see com.github.aha.sat.rest.service.StatService#getQueries()
     */
    @Override
    public String[] getQueries() {
        return getStatistics().getQueries();
    }

    /*
     * (non-Javadoc)
     * @see com.github.aha.sat.rest.service.StatService#getEntityStatistics(java.lang.String)
     */
    @Override
    public EntityStatistics getEntityStatistics(String entityName) {
        return getStatistics().getEntityStatistics("com.github.aha.sat.rest.domain." + entityName);
    }
}
