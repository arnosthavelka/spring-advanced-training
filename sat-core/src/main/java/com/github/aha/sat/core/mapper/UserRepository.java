package com.github.aha.sat.core.mapper;

import java.util.List;

public interface UserRepository {

	List<UserDTO> fetchAllUsers();

	UserDTO firstUser();

	UserDTO userByFirstNameAndLastName(String firstName, String lastName);

}
