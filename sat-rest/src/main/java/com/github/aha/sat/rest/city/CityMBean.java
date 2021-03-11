package com.github.aha.sat.rest.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

/**
 * Usage (objectName = "sat-rest:jmx=CityMBean"): 
 * list MBeans 		- GET http://localhost:8080/actuator/jolokia/list
 * get CityMBean	- GET http://localhost:8080/actuator/jolokia/read/com.github.aha.sat.rest:name=cityMBean,type=CityMBean
 * get attribute	- GET http://localhost:8080/actuator/jolokia/read/com.github.aha.sat.rest:name=cityMBean,type=CityMBean/Author
 * call method		- GET http://localhost:8080/actuator/jolokia/exec/com.github.aha.sat.rest:name=cityMBean,type=CityMBean/getCityName/100
 */
@Component
@ManagedResource(description = "MBean for entity City")
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
		return cityService.getOne(id).getName();
    }

    @ManagedOperation(description = "Get City by ID")
	public City getCity(Long id) {
        return cityService.getOne(id);
    }
}
