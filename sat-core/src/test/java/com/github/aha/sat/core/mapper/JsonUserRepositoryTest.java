package com.github.aha.sat.core.mapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = { MapperApplication.class })
class JsonUserRepositoryTest {

    @Autowired
    private UserRepository repo;
    
	@Test
	void testUsersCount() {
		assertThat(repo.fetchAllUsers().size(), is(equalTo(3)));
	}

	@Test
	void testFirstUser() {
		assertThat(repo.firstUser().getUserName(), is(equalTo("aha")));
	}

	@Test
	void testRisator() {
		UserDTO user = repo.userByFirstNameAndLastName("Richard", "Strauss");
		assertThat(user.getUserName(), is(equalTo("risator")));
	}

}
