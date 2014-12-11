package com.asseco.aha.training.spring_advanced.rest.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.asseco.aha.training.spring_advanced.rest.rest.json.View;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
// @XmlRootElement - no needed when library jackson-dataformat-xml is used
// @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" }) -- for repository getOne
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @JsonView(View.Summary.class)
    private Long id;

    @Column(nullable = false)
    @JsonView(View.Summary.class)
    private String name;

    @Column(nullable = false)
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

    // @Override
    // public String toString() {
    // return String.format("%s [%d]", getName(), getCountry());
    // }
}
