package com.github.aha.sat.jpa.city;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries(value = { @NamedQuery(name = "City.findByName", query = "select c from City c where c.name = ?1"),
        @NamedQuery(name = "City.findByNameAndCountry", query = "select c from City c where c.name like ?1 and c.country = ?2") })
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
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
        return this.name;
    }

    public String getState() {
        return this.state;
    }

    public String getCountry() {
        return this.country;
    }

    @Override
    public String toString() {
        return getName() + "," + getState() + "," + getCountry();
    }
}
