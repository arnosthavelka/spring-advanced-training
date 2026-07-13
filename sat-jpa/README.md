# sat-jpa

#Goal
* demonstrate Querydsl usage - see https://openfeign.github.io/querydsl/tutorials/jpa/
* demonstrate GraalVM usage - see https://www.graalvm.org/latest/getting-started/

#Build
##Standard

```
mvn clean package
```

##GraalVM


### Compile with a native Executable

```
 -Pnative native:compile
```

This will generate a standalone binary inside your `/target` directory that can be executed directly (e.g., ./target/sat-jpa).

### Containerize via Buildpacks
```
mvn spring-boot:build-image
```

This will generate `sat-jpa:<VERSION>` docker image. A new container can be started e.g. like this:
```
docker run --name sat-jpa-app -p 8080:8080 sat-jpa:0.6.6-SNAPSHOT
```