package com.github.aha.sat.rest.stat;

import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(description = "MBean about Hibernate statistics")
public class HibernateStatisticsMBean {

    @Autowired
    private StatService statService;

    @ManagedOperation
    public Statistics getStatistics() {
        return statService.getStatistics();
    }

}
