package com.github.aha.sat.rest.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(description = "MBean about entity City")
// objectName = "sat-rest:jmx=CityMBean",
// jolokia: http://localhost:8080/jolokia/read/com.github.aha.sat.sat-rest:name=cityMBean,type=CityMBean
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
	public String getCityName(Long id) {
        City city = cityService.getOne(id);
        return city.getName();
    }

    @ManagedOperation(description = "Get City by ID")
	public City getCity(Long id) {
        return cityService.getOne(id);
    }
}
