package com.github.aha.sat.core.mapper;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// https://dzone.com/articles/spring-boot-and-cache-abstraction?edition=155254&utm_source=Weekly%20Digest&utm_source=Weekly%20Digest&utm_medium=email&utm_medium=email&utm_campaign=wd%202017-01-18&utm_campaign=wd%202017-01-18
@Service
public class JsonUserRepository implements UserRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(JsonUserRepository.class);

	@Autowired
	private List<UserDTO> users;

	@Override
	public List<UserDTO> fetchAllUsers() {
		LOGGER.info("Fetching all users");
		return users;
	}

	@Override
	public UserDTO firstUser() {
		LOGGER.info("fetching firstUser");

		return users.get(0);
	}

	@Override
	public UserDTO userByFirstNameAndLastName(String firstName, String lastName) {
		LOGGER.info("fetching user by firstname and lastname");

		Optional<UserDTO> user = users.stream()
			.filter(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))
			.findFirst();

		return user.isPresent() ? user.get() : null;
	}

}
