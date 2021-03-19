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

@RestController
@RequestMapping("/stat")
@Tag(name = "Hibernate statistics")
public class StatController {

    @Autowired
    private StatService statService;

    /*
     * http://localhost:8080/stat/entity
     */
	@GetMapping(value = "/entity", produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Returns hibernate entities", description = "Returns list of available entities in Hibernate")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful retrieval of Hibernate entities") })
    public String[] listEntities() {
        return statService.getEntityNames();
    }

    /*
     * http://localhost:8080/stat/entity/City
     */
	@GetMapping(value = "/entity/{entity}", produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Returns statistics of the entity")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful retrieval of entity statistics") })
    public EntityStatistics entityStat(@PathVariable String entity) {
        return statService.getEntityStatistics(entity);
    }

    /*
     * http://localhost:8080/stat/query
     * 
     * Currently empty (criteria queries are not considered): http://stackoverflow.com/questions/2629754/hibernate-criteria-and-statistics
     */
	@GetMapping(value = "/query", produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Returns queries", description = "Returns list of used queries")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful retrieval of queries") })
    public String[] listQueries() {
        return statService.getQueries();
    }

}