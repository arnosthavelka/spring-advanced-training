package com.github.aha.sat.rest.stat;

import org.hibernate.stat.EntityStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Usage:
 * list entities 					- GET http://localhost:8080/stat/entity
 * list queries		 				- GET http://localhost:8080/stat/query
 * get statistics for one entity 	- GET http://localhost:8080/stat/entity/City
 */
@RestController
@RequestMapping("/stat")
@Tag(name = "Hibernate statistics")
public class StatController {

    @Autowired
    private StatService statService;

	@GetMapping(value = "/entity", produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Returns hibernate entities", description = "Returns list of available entities in Hibernate")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful retrieval of Hibernate entities") })
    public String[] getALl() {
        return statService.getEntityNames();
    }

	@GetMapping(value = "/entity/{entity}", produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Returns statistics of the entity")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful retrieval of entity statistics") })
    public EntityStatistics getOne(@PathVariable String entity) {
        return statService.getEntityStatistics(entity);
    }

	@GetMapping(value = "/query", produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Returns queries", description = "Returns list of used queries")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful retrieval of queries") })
    public String[] listQueries() {
		// Currently empty (criteria queries are not considered): http://stackoverflow.com/questions/2629754/hibernate-criteria-and-statistics
        return statService.getQueries();
    }

}