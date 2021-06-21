# sat-elk
POC for accessing Elasticsearch via Spring Data.

See also https://www.baeldung.com/spring-data-elasticsearch-tutorial and https://www.baeldung.com/spring-data-elasticsearch-queries

## City domain
Simple feature to demonstrate CRUD operations with ElasticSearch. The root API can be accessed here http://localhost:8080/api/cities/.

### REST API

| Action                                    | HTTP Method | Context path
| ----------------------------------------- | ----------- | -------------
| Get item by ID                            | GET         | http://localhost:8080/api/cities/{id}
| Static search (just by country)           | GET         | http://localhost:8080/api/cities/country/united kingdom?sort=name,desc
| Dynamic search (by defined attributes)    | GET         | http://localhost:8080/api/cities/?name=be&country=Czech&subcountry=bohemia&size=5&sort=name,asc
| Upload CSV					        	| POST        | http://localhost:8080/api/cities/upload?filename=Z:/world-cities.csv

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

### Static Search Output

```
{
  "content": [
    {
      "id": "7wXjgXgBeV-uft03tpY1",
      "name": "York",
      "country": "United Kingdom",
      "subcountry": "England",
      "geonameid": 2633352
    },
    ...
  ],
  "pageable": {
    "sort": {
      "sorted": true,
      "unsorted": false,
      "empty": false
    },
    "offset": 0,
    "pageSize": 20,
    "pageNumber": 0,
    "unpaged": false,
    "paged": true
  },
  "aggregations": null,
  "scrollId": null,
  "maxScore": "NaN",
  "totalPages": 26,
  "totalElements": 513,
  "size": 20,
  "number": 0,
  "sort": {
    "sorted": true,
    "unsorted": false,
    "empty": false
  },
  "first": true,
  "last": false,
  "numberOfElements": 20,
  "empty": false
}
```

### Dynamic Search Output

```
{
  "totalHits": 3,
  "totalHitsRelation": "EQUAL_TO",
  "maxScore": "NaN",
  "scrollId": null,
  "searchHits": [
    ...,
    {
      "index": "city",
      "id": "ckmz1XkB8HBQc_fknzUo",
      "score": "NaN",
      "sortValues": [
        "beroun"
      ],
      "content": {
        "id": "ckmz1XkB8HBQc_fknzUo",
        "name": "Beroun",
        "country": "Czech Republic",
        "subcountry": "Central Bohemia",
        "geonameid": 3079467
      },
      "highlightFields": {},
      "innerHits": {},
      "nestedMetaData": null,
      "routing": null,
      "explanation": null,
      "matchedQueries": []
    },
    ...
  ],
  "aggregations": null,
  "empty": false
}
```

### Upload Output

```
2021-03-25 09:21:14.374  INFO 6936 --- [nio-8080-exec-1] com.github.aha.sat.elk.city.CityService  : loading file Z:/world-cities.csv ...
2021-03-25 09:21:14.439  INFO 6936 --- [nio-8080-exec-1] com.github.aha.sat.elk.city.CityService  : 23018 entries loaded from CSV file
2021-03-25 09:21:15.593  INFO 6936 --- [nio-8080-exec-1] com.github.aha.sat.elk.city.CityService  : inst bulk stored [0/47] ...
...
2021-03-25 09:21:21.564  INFO 6936 --- [nio-8080-exec-1] com.github.aha.sat.elk.city.CityService  : inst bulk stored [46/47] ...
2021-03-25 09:21:21.564  INFO 6936 --- [nio-8080-exec-1] com.github.aha.sat.elk.city.CityService  : data loading finish
```

### Index mapping
http://oxygen-arnost.ifs.dev.dbgcloud.io:9200/city/_mapping


## Running Elasticsearch by Docker in Linux AWS
See https://hub.docker.com/_/elasticsearch.

#### Add docker network
Create user defined network (useful for connecting to other services attached to the same network (e.g. Kibana)):

`docker network create sat-elk-net`

_Note: the available networks can be listed by. 

`docker network ls`

#### Add Elasticsearch cluster
Run Elasticsearch:

`docker run -d --name sat-elasticsearch --net sat-elk-net -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" elasticsearch:7.10.1` (as supported by Spring Data - docker has already newer version)

The Elasticsearch instance can be verified by REST call
`GET http://<ELK_HOST>:9200`

_Note: the port mapping is important otherwise we cannot connect there (the port is not available). 
The availability can be checked from PowerShell console as:_

`Test-NetConnection -ComputerName <ELK_HOST> -Port 9200`

_The used ports can be listed in AWS by:_

`netstat -tulpn`

#### Add ElasticHQ GUI
`docker run -d --name sat-elastichq --net sat-elk-net -p 5000:5000 elastichq/elasticsearch-hq`

The GUI is accessed on http://<ELK_HOST>:5000 (we need to pass http://<ELK_HOST>:9200).

#### Start/Stop container
`docker start sat-elasticsearch` | `docker stop sat-elasticsearch`

#### List running containers
`docker ps`

#### List available images
`docker image ls`

