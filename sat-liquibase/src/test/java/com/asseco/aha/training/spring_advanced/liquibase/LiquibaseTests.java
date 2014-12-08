package com.asseco.aha.training.spring_advanced.liquibase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asseco.aha.training.spring_advanced.liquibase.LiquibaseApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LiquibaseApplication.class)
public class LiquibaseTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void testCount() {
        Integer count = jdbcTemplate.queryForObject("select count(*) from person", Integer.class);
        assertThat(1, equalTo(count));
    }

}
