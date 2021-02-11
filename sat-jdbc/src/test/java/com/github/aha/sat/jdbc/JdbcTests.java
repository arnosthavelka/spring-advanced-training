package com.github.aha.sat.jdbc;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest(classes = JdbcApplication.class)
public class JdbcTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void testCount() {
        Integer count = jdbcTemplate.queryForObject("select count(*) from CATCIS", Integer.class);
		assertThat(count).isEqualTo(7);
    }

}
