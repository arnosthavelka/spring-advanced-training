package com.github.aha.sat.elk.city;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(indexName = City.INDEX)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String INDEX = "city";

    @Id
    private String id;

    @JsonProperty("name")
    @Field(type = FieldType.Text, fielddata = true)
    private String name;

    @JsonProperty("country")
    private String country;

    @JsonProperty("subcountry")
    private String subcountry;

    @JsonProperty("geonameid")
    private Long geonameid;

}
