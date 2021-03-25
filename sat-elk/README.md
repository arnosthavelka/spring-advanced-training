# sat-elk
POC for working with Elasticsearch

See also https://www.baeldung.com/spring-data-elasticsearch-tutorial and https://www.baeldung.com/spring-data-elasticsearch-queries

TODO

---
http://localhost:8080/api/cities/upload?filename=Z:/world-cities.csv

2021-03-25 09:21:14.374  INFO 6936 --- [nio-8080-exec-1] com.github.aha.sat.elk.city.CityService  : loading file Z:/world-cities.csv ...
2021-03-25 09:21:14.439  INFO 6936 --- [nio-8080-exec-1] com.github.aha.sat.elk.city.CityService  : 23018 entries loaded from CSV file
2021-03-25 09:21:15.593  INFO 6936 --- [nio-8080-exec-1] com.github.aha.sat.elk.city.CityService  : inst bulk stored [0/47] ...
...
2021-03-25 09:21:21.564  INFO 6936 --- [nio-8080-exec-1] com.github.aha.sat.elk.city.CityService  : inst bulk stored [46/47] ...
2021-03-25 09:21:21.564  INFO 6936 --- [nio-8080-exec-1] com.github.aha.sat.elk.city.CityService  : data loading finish

---
http://localhost:8080/api/cities/HwB5aHgBiVYee_AkNeA6

{
  "id": "HwB5aHgBiVYee_AkNeA6",
  "name": "Prague",
  "country": "Czech Republic",
  "subcountry": "Praha",
  "geonameid": 3067696
}

---
http://localhost:8080/api/cities/?country=Republic&subcountry=praha

{
  "content": [
    ...
    {
      "id": "HwB5aHgBiVYee_AkNeA6",
      "name": "Prague",
      "country": "Czech Republic",
      "subcountry": "Praha",
      "geonameid": 3067696
    },
    ...
  ],
  "pageable": {
    "sort": {
      "sorted": false,
      "unsorted": true,
      "empty": true
    },
    "offset": 0,
    "pageNumber": 0,
    "pageSize": 20,
    "unpaged": false,
    "paged": true
  },
  "aggregations": null,
  "scrollId": null,
  "maxScore": 10.203253,
  "totalPages": 1,
  "totalElements": 7,
  "size": 20,
  "number": 0,
  "sort": {
    "sorted": false,
    "unsorted": true,
    "empty": true
  },
  "first": true,
  "last": true,
  "numberOfElements": 7,
  "empty": false
}

## Running Elasticsearch by Docker in Linux AWS
See https://hub.docker.com/_/elasticsearch.

#### Add docker network
Create user defined network (useful for connecting to other services attached to the same network (e.g. Kibana)):

`docker network create sat-elk-net`

_Note: the available networks can be listed by. 

`docker network ls`

#### Add Elasticsearch cluster
Run Elasticsearch:

`docker run -d --name sat-elasticsearch --net sat-elk-net -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" elasticsearch:7.9.3` (as supported by Spring Data - docker has already newer version)

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

