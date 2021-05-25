package com.github.aha.sat.rest.city;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.aha.sat.rest.city.resource.CityProjections.Basic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
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

}
