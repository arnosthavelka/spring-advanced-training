package com.github.aha.sat.rest.jmx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import com.github.aha.sat.rest.domain.City;
import com.github.aha.sat.rest.service.CityService;

@Component
@ManagedResource(description = "MBean about entity City")
// objectName = "sat-rest:jmx=CityMBean",
// jolokia: http://localhost:8080/jolokia/read/com.asseco.aha.training.sat-rest:name=cityMBean,type=CityMBean
public class CityMBean {

    @Autowired
    private CityService cityService;

    @Value("${sat-rest.cfg.author}")
    private String author;

    @ManagedAttribute
    public String getAuthor() {
        return author;
    }

    @ManagedAttribute
    public void setAuthor(String author) {
        this.author = author;
    }

    @ManagedOperation(description = "Get City name by ID")
    public String getCityName(int id) {
        City city = cityService.item(id);
        return city.getName();
    }

    @ManagedOperation(description = "Get City by ID")
    public City getCity(int id) {
        return cityService.item(id);
    }
}
