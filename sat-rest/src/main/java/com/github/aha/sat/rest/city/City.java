package com.github.aha.sat.rest.city;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.aha.sat.rest.city.resource.CityProjections.Basic;

@Entity
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
	@JsonView(Basic.class)
    private Long id;

    @Column(nullable = false)
	@JsonView(Basic.class)
    private String name;

    @Column(nullable = true)
    private String state;

    @Column(nullable = false)
    private String country;

    protected City() {
    }

    public City(String name, String country, String state) {
        super();
        this.name = name;
        this.country = country;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("%s/%s [id=%d]", getCountry(), getName(), getId());
    }
}
