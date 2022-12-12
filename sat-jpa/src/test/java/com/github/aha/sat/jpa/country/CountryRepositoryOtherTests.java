package com.github.aha.sat.jpa.country;

import static com.github.aha.sat.jpa.city.QCity.city;
import static com.github.aha.sat.jpa.country.QCountry.country;
import static com.github.aha.sat.jpa.country.QuerydslUtils.fetchProjectedPage;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.aha.sat.jpa.city.City;
import com.github.aha.sat.jpa.city.CityProjection;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;

@DataJpaTest
class CountryRepositoryOtherTests {

	static final String USA = "USA";
	static final String AUSTRALIA = "Australia";

	@PersistenceContext
	private EntityManager em;

	@Nested
	class CollectionTests {

		/**
		 * select country0_.id as id1_1_, country0_.name as name2_1_
		 * from country country0_
		 * where (select count(cities1_.country_id) from city cities1_ where country0_.id = cities1_.country_id)='Canada'
		 * order by country0_.name asc
		 */
		@Test
		void size() {
			var result = new JPAQuery<>(em)
					.select(country)
					.from(country)
					.where(country.cities.size().eq(1))
					.orderBy(country.name.asc())
					.fetch();

			assertThat(result)
					.hasSize(7)
					.first()
					.satisfies(c -> {
						assertThat(c.getName()).isEqualTo("Canada");
						assertThat(c.getCities()).hasSize(1);
					});
		}

		/**
		 * select country0_.id as id1_1_, country0_.name as name2_1_
		 * from country country0_
		 * where  not (exists (select cities1_.id from city cities1_ where country0_.id=cities1_.country_id))
		 * order by country0_.name asc
		 */
		@Test
		void isEmpty() {
			var result = new JPAQuery<>(em)
					.select(country)
					.from(country)
					.where(country.cities.isEmpty())
					.orderBy(country.name.asc())
					.fetch();

			assertThat(result)
					.hasSize(1)
					.first()
					.satisfies(c -> {
						assertThat(c.getName()).isEqualTo("Belgium");
						assertThat(c.getCities()).isEmpty();
					});
		}

		/**
		 * select country0_.id as id1_1_, country0_.name as name2_1_ 
		 * from country country0_
		 * where exists (select 1 from city cities1_ where country0_.id=cities1_.country_id and cities1_.name='Barcelona')
		 */
		@Test
		void any() {
			var result = new JPAQuery<>(em)
					.select(country)
					.from(country)
					.where(country.cities.any().name.eq("Barcelona"))
					.fetchOne();

			assertThat(result).satisfies(c -> {
				assertThat(c.getName()).isEqualTo("Spain");
				assertThat(c.getCities().stream().map(City::getName)).contains("Barcelona");
			});
		}
		
		/**
		 * select country0_.id as id1_1_, country0_.name as name2_1_ 
		 * from country country0_ cross join city city1_
		 * where (city1_.id in (select cities2_.id from city cities2_ where country0_.id=cities2_.country_id)) and city1_.name='Barcelona' 
		 */
		@Test
		void contains() {
			var result = new JPAQuery<>(em)
					.select(country)
					.from(country, city)
					.where(country.cities.contains(city)
							.and(city.name.eq("Barcelona")))
					.fetchOne();
	
			assertThat(result).satisfies(c -> {
				assertThat(c.getName()).isEqualTo("Spain");
				assertThat(c.getCities().stream().map(City::getName)).contains("Barcelona");
			});
		}
	
		/**
		 * select country0_.id as id1_1_, country0_.name as name2_1_
		 * from country country0_
		 * where (select city1_.id from city city1_ where city1_.name='Barcelona') in (select cities2_.id from city cities2_ where country0_.id=cities2_.country_id
		 */
		@Test
		void containsWithExpression() {
			var result = new JPAQuery<>(em)
					.select(country)
					.from(country)
					.where(country.cities.contains(JPAExpressions.select(city).from(city).where(city.name.eq("Barcelona"))))
					.fetchOne();
			
			assertThat(result).satisfies(c -> {
				assertThat(c.getName()).isEqualTo("Spain");
				assertThat(c.getCities().stream().map(City::getName)).contains("Barcelona");
			});
		}
	
	}
	
	@Nested
	class PagingAndSortingTest {

		private Page<CityProjection> findAllProjectionSortedBy(Pageable pageable, Class<?> pathType) {
			var query = new JPAQuery<CityProjection>(em)
					.select(Projections.constructor(CityProjection.class, city.id, city.name, city.state, country.name))
					.from(city)
//					.innerJoin(country.cities, city)
					.innerJoin(country)
					.on(country.id.eq(city.country.id))
					.where(country.name.eq(USA));
			return fetchProjectedPage(em, query, pageable, pathType);
		}

		@Test
		void pagedResultSortedByCityId() {
			var result = findAllProjectionSortedBy(of(0, 3, ASC, "id"), City.class);

			assertThat(result)
					.first()
					.satisfies(c -> {
						assertThat(c.getName()).isEqualTo("Atlanta");
						assertThat(c.getCountryName()).isEqualTo(USA);
					});
		}

		@Test
		void pagedResultSortedByCityNameDescended() {
			var result = findAllProjectionSortedBy(of(0, 3, DESC, "name"), City.class);

			assertThat(result)
					.first()
					.satisfies(c -> {
						assertThat(c.getName()).isEqualTo("San Francisco");
						assertThat(c.getCountryName()).isEqualTo(USA);
					});
		}

		@Test
		void pagedResultSortedByMultipleAtrributes() {
			var result = findAllProjectionSortedBy(of(0, 3, ASC, "state", "name"), City.class);

			assertThat(result)
					.first()
					.satisfies(c -> {
						assertThat(c.getName()).isEqualTo("New York");
						assertThat(c.getCountryName()).isEqualTo(USA);
					});
		}

		@Test
		void pagedResultSortedByCountryName() {
			var result = findAllProjectionSortedBy(of(0, 3, ASC, "countryName"), City.class);

			assertThat(result)
					.first()
					.satisfies(c -> {
						assertThat(c.getName()).isEqualTo("San Francisco");
						assertThat(c.getCountryName()).isEqualTo(USA);
					});
		}

	}

}
