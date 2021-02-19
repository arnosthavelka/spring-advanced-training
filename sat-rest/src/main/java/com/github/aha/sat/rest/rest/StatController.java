package com.github.aha.sat.rest.rest;

import org.hibernate.stat.EntityStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.aha.sat.rest.service.StatService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/stat")
@Api(value = "stat", description = "Endpoint for access to hibernate statistics")
public class StatController {

    @Autowired
    private StatService statService;

    /*
     * http://localhost:8080/stat/entity
     */
	@GetMapping(value = "/entity", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ApiOperation(value = "Returns hibernate entities", notes = "Returns list of available entities in Hibernate", response = String[].class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successful retrieval of Hibernate entities") })
    public String[] listEntities() {
        return statService.getEntityNames();
    }

    /*
     * http://localhost:8080/stat/entity/City
     */
	@GetMapping(value = "/entity/{entity}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ApiOperation(value = "Returns statistics of the entity", response = EntityStatistics.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successful retrieval of entity statistics") })
    public EntityStatistics entityStat(@PathVariable String entity) {
        return statService.getEntityStatistics(entity);
    }

    /*
     * http://localhost:8080/stat/query
     * 
     * Currently empty (criteria queries are not considered): http://stackoverflow.com/questions/2629754/hibernate-criteria-and-statistics
     */
	@GetMapping(value = "/query", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ApiOperation(value = "Returns queries", notes = "Returns list of used queries", response = String[].class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successful retrieval of queries") })
    public String[] listQueries() {
        return statService.getQueries();
    }

}