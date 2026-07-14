package com.github.aha.sat.jpa.country;

import static com.github.aha.sat.jpa.city.QCity.city;
import static com.github.aha.sat.jpa.country.QCountry.country;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import com.github.aha.sat.jpa.city.CityProjection;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.H2Templates;
import com.querydsl.sql.SQLExpressions;
import com.querydsl.sql.SQLTemplates;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@DataJpaTest
class CountryRepositoryCteTests {

	private static final String COUNTRY_ID = "country_id";
	private static final String USA_COUNTRY = "usa_country";
	static final String USA = "USA";
	static final String AUSTRALIA = "Australia";

	@PersistenceContext
	private EntityManager em;

	@Test
	void findAllProjectionWithCte() {
		assertThat(findAllProjectionWithSql())
		    .first()
		    .satisfies(c -> {
			    assertThat(c.getName()).isEqualTo("Atlanta");
			    assertThat(c.getCountryName()).isEqualTo(USA);
		    });
	}

	private List<CityProjection> findAllProjectionWithSql() {
		SQLTemplates templates = H2Templates.builder().build();

		PathBuilder<Object> usaCountriesCtePath = new PathBuilder<>(Object.class, USA_COUNTRY);
		NumberPath<Long> cteCountryId = usaCountriesCtePath.getNumber(COUNTRY_ID, Long.class);
		StringPath cteCountryName = usaCountriesCtePath.getString("country_name");

		var usaCountriesSubquery = SQLExpressions.select(
			    country.id.as(COUNTRY_ID),
			    country.name.as("country_name"))
		    .from(country)
		    .where(country.name.eq(USA));

		var query = new JPASQLQuery<CityProjection>(em, templates)
		    .with(usaCountriesCtePath, usaCountriesSubquery)
		    .select(Projections.bean(
		        CityProjection.class,
		        city.id,
		        city.name,
		        city.state,
		        cteCountryName.as("countryName")))
		    .from(city)
		    .innerJoin(usaCountriesCtePath)
		    .on(Expressions.numberPath(Long.class, city, COUNTRY_ID).eq(cteCountryId));
		return query.fetch();
	}

}
