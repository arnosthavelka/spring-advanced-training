package com.github.aha.sat.jpa.country;

import static com.github.aha.sat.jpa.city.QCity.city;
import static com.querydsl.core.types.dsl.Expressions.numberPath;
import static com.querydsl.sql.SQLExpressions.select;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.H2Templates;
import com.querydsl.sql.SQLTemplates;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@Slf4j
class CountryRepositoryCteTests {

	private static final String COUNTRY_ID = "country_id";
	private static final String COUNTRY_SIZE = "countryName";

	@PersistenceContext
	private EntityManager em;

	@Test
	void findAllProjectionWithCte() {
		List<CteProjection> data = findCitiesByCountrySizeGreaterThan(2);
		log.info("Cities:\n{}", data);
		assertThat(data)
		    .first()
		    .satisfies(c -> {
			    assertThat(c.getName()).isEqualTo("Atlanta");
			    assertThat(c.getCountrySize()).isEqualTo(5);
		    });
	}

	private List<CteProjection> findCitiesByCountrySizeGreaterThan(int citySize) {
		SQLTemplates templates = H2Templates.builder().build();

		PathBuilder<Object> countryCtePath = new PathBuilder<>(Object.class, "countries_with_size");
		NumberPath<Long> cteCountryId = countryCtePath.getNumber(COUNTRY_ID, Long.class);
		NumberPath<Long> cteCountrySize = countryCtePath.getNumber(COUNTRY_SIZE, Long.class);

		var countrySubquery = 
			select(
		        numberPath(Long.class, city, COUNTRY_ID),
		        city.id.count().as(COUNTRY_SIZE))
		        .from(city)
		        .groupBy(numberPath(Long.class, city, COUNTRY_ID))
		        .having(city.id.count().goe(citySize))
		        .limit(2)
		        .offset(1);

		var query = new JPASQLQuery<CteProjection>(em, templates)
		    .with(countryCtePath, countrySubquery)
		    .select(Projections.constructor(
		        CteProjection.class,
		        city.id,
		        city.name,
		        city.state,
		        cteCountrySize))
		    .from(city)
		    .innerJoin(countryCtePath)
		    .on(numberPath(Long.class, city, COUNTRY_ID).eq(cteCountryId))
		    .orderBy(city.name.asc());
		return query.fetch();
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class CteProjection {

		private Long id;
		private String name;
		private String state;
		private Long countrySize;

	}

}
