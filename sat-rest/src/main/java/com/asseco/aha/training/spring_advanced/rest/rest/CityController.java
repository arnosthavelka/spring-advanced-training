package com.asseco.aha.training.spring_advanced.rest.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asseco.aha.training.spring_advanced.rest.domain.City;
import com.asseco.aha.training.spring_advanced.rest.repository.CityRepository;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @RequestMapping("/")
    public List<City> list() {
        return cityRepository.findAll(new Sort(Sort.Direction.ASC, "country", "name"));
    }
}
