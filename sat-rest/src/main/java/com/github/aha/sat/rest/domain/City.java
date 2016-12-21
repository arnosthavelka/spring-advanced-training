package com.github.aha.sat.rest.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.hateoas.Identifiable;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.aha.sat.rest.rest.json.View;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel
public class City implements Serializable, Identifiable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @JsonView(View.Summary.class)
    @ApiModelProperty(position = 1, required = true, value = "ID of the city")
    private Long id;

    @Column(nullable = false)
    @JsonView(View.Summary.class)
    @ApiModelProperty(position = 2, required = true, value = "Name of the city")
    private String name;

    @Column(nullable = true)
    @ApiModelProperty(position = 4, required = false, value = "State where the city belongs")
    private String state;

    @Column(nullable = false)
    @ApiModelProperty(position = 3, required = true, value = "Country where the city belongs")
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
