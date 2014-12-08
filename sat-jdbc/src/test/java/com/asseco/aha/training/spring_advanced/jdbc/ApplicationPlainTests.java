package com.asseco.aha.training.spring_advanced.jdbc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JdbcPlainApplication.class)
public class ApplicationPlainTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void testCount() {
        Integer count = jdbcTemplate.queryForObject("select count(*) from CATCIS", Integer.class);
        assertThat(7, equalTo(count));
    }

}
