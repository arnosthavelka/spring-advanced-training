package com.github.aha.sat.elk.city;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Document(indexName = City.INDEX)
@Data
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

	public final static String INDEX = "city";

    @Id
    private Long id;

	@JsonProperty("name")
    private String name;

	@JsonProperty("country")
	private String country;

	@JsonProperty("subcountry")
	private String subcountry;

}
