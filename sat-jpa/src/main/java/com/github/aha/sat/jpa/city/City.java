package com.github.aha.sat.jpa.city;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import com.github.aha.sat.jpa.country.Country;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NamedQuery(name = "City.findByName", query = "select c from City c where c.name = ?1")
@NamedQuery(name = "City.findByNameAndCountry", query = "select c from City c where c.name like ?1 and c.country = ?2")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class City implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = true)
	private String state;

	@ManyToOne
	@JoinColumn(name = "COUNTRY_ID")
	@NonNull
	private Country country;

}
