package com.asseco.aha.training.spring_advanced.rest.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriTemplate;

import com.asseco.aha.training.spring_advanced.rest.domain.City;
import com.asseco.aha.training.spring_advanced.rest.rest.json.View;
import com.asseco.aha.training.spring_advanced.rest.service.CityService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    /*
     * http://localhost:8080/city/, http://localhost:8080/city/?country=Spain, http://localhost:8080/city/?sorting=id
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @JsonView(View.Summary.class)
    public List<City> list(@PathParam("country") String country, @PathParam("sorting") String sorting) {
        return cityService.list(country, sorting);
    }

    /*
     * http://localhost:8080/city/105
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE })
    public City item(@PathVariable("id") long id) {
        return cityService.item(id);
    }

    /*
     * http://localhost:8080/city/ + JSON input
     */
    @RequestMapping(value = "/", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(value = HttpStatus.CREATED)
    public void insert(@RequestBody City city, HttpServletRequest request, HttpServletResponse response) {
        Long id = cityService.insert(city);
        response.addHeader("Location", getNewLocation(request, id));
    }

    private String getNewLocation(HttpServletRequest request, Long id) {
        StringBuffer url = request.getRequestURL();
        UriTemplate template = new UriTemplate(url.append("/{childId}").toString());
        return template.expand(id).toASCIIString();

    }
}