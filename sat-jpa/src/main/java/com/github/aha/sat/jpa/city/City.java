package com.github.aha.sat.jpa.city;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import com.github.aha.sat.jpa.country.Country;

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
