package com.github.aha.sat.jdbc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.aha.sat.jdbc.JdbcApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JdbcApplication.class)
public class JdbcTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void testCount() {
        Integer count = jdbcTemplate.queryForObject("select count(*) from CATCIS", Integer.class);
        assertThat(count, equalTo(7));
    }

}
