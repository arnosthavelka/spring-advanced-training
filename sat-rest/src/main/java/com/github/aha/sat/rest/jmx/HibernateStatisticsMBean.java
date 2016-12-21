package com.github.aha.sat.rest.jmx;

import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import com.github.aha.sat.rest.service.StatService;

@Component
@ManagedResource(description = "MBean about Hibernate statistics")
public class HibernateStatisticsMBean {

    // @Autowired
    // @Qualifier("mbeanExporter")
    // private MBeanExporter exporter;

    @Autowired
    private StatService statService;

    //
    // @Autowired
    // private HibernateStatisticsFactoryBean hsfb;
    //
    // @PostConstruct
    // public void init() throws MalformedObjectNameException {
    // ObjectName name = new ObjectName("hibernate:name=statistics");
    // exporter.registerManagedResource(statService.getStatistics(), name); // ,
    // }

    @ManagedOperation
    public Statistics getStatistics() {
        return statService.getStatistics();
    }
}
