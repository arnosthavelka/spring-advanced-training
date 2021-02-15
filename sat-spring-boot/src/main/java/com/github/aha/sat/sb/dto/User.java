package com.github.aha.sat.sb.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {

	@Digits(integer = 5, fraction = 0)
	@NotNull
	private Integer id;

	@Size(min = 3, max = 10)
	@NotNull
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
