package com.asseco.aha.training.spring_advanced.sb.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class User {

	@Digits(integer = 5, fraction = 0)
	@NotNull
	private Integer id;

	@Length(min = 3, max = 10)
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
