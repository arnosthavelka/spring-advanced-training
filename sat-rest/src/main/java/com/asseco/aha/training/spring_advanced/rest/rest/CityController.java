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
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/city")
@Api(value = "city", description = "Endpoint for city management")
public class CityController {

    @Autowired
    private CityService cityService;

    /*
     * http://localhost:8080/city/, http://localhost:8080/city/?country=Spain, http://localhost:8080/city/?sorting=id
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @JsonView(View.Summary.class)
    @ApiOperation(value = "Returns list of cities", notes = "Returns a list of found city details.", response = City.class, responseContainer = "List")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successful retrieval of the list of cities (with simple detail)") })
    public List<City> list(@ApiParam(name = "country", required = false) @PathParam("country") String country,
            @ApiParam(name = "sorting", required = false) @PathParam("sorting") String sorting) {
        return cityService.list(country, sorting);
    }

    /*
     * http://localhost:8080/city/ + content
     */
    @RequestMapping(value = "/", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create the city", notes = "Create the city with defined attributes (ID is erased if defined)", response = Void.class)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Successful creation of the city") })
    public void insert(@RequestBody City city, HttpServletRequest request, HttpServletResponse response) {
        Long id = cityService.save(city);
        response.addHeader("Location", getNewLocation(request, id));
    }

    private String getNewLocation(HttpServletRequest request, Long id) {
        StringBuffer url = request.getRequestURL();
        UriTemplate template = new UriTemplate(url.append("/{childId}").toString());
        return template.expand(id).toASCIIString();

    }

    /*
     * http://localhost:8080/city/105
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE })
    @ApiOperation(value = "Returns city", notes = "Returns the city specified by ID .", response = City.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successful retrieval of city detail (full detail)"),
            @ApiResponse(code = 404, message = "City with given ID does not exist") })
    public City item(@PathVariable("id") long id) {
        City city = cityService.item(id);
        if (city == null) {
            throw new CityNotFoundException(String.format("City [id=%d] was not found!", id));
        }

        return city;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    class CityNotFoundException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public CityNotFoundException(String message) {
            super(message);
        }
    }

    /*
     * http://localhost:8080/city/100 + content
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update the city", notes = "Update the city with defined attributes (for defined ID)", response = Void.class)
    @ApiResponses(value = { @ApiResponse(code = 204, message = "Successful update of the city"),
            @ApiResponse(code = 409, message = "When ID in path is not equal to ID in the content (body)") })
    public void update(@RequestBody City city, @PathVariable long id) {
        if (id != city.getId()) {
            throw new CityIntegrityException(String.format("Integrity error [pathId=%d, cityId=%d]!", id, city.getId()));
        }

        cityService.save(city);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    class CityIntegrityException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public CityIntegrityException(String message) {
            super(message);
        }
    }

    /*
     * http://localhost:8080/city/100
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete the city", notes = "Delete the city defined by ID)", response = Void.class)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Successful deletion of the city") })
    public void delete(@PathVariable long id) {
        cityService.delete(id);
    }

}