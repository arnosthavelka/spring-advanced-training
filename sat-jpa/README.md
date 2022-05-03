# sat-jpa
The examples of JPA usage via Spring Data JPA & QueryDSL.

| Item                                  | Country domain | City domain 
| ------------------------------------- | -------------- | -----------
| Technology                            | QueryDsl       | JPA Criteria
| Examples                              | queries, projection, tuple, count | same + named queries

## Country domain
Simple feature to demonstrate CRUD operations with ElasticSearch. The root API can be accessed here http://localhost:8080/api/cities/.

| Item                                  | Description
| ------------------------------------- | -----------
| Technology                            | QueryDsl

### Persistence


### REST API

| Action                                    | HTTP Method | Context path
| ----------------------------------------- | ----------- | -------------
| Get item by ID                            | GET         | http://localhost:8080/api/cities/{id}

### Item output

```
{
  "id": "HwB5aHgBiVYee_AkNeA6",
  "name": "Prague",
  "country": "Czech Republic",
  "subcountry": "Praha",
  "geonameid": 3067696
}
```