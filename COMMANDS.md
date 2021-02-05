```bash
$ mvn io.quarkus:quarkus-maven-plugin:1.11.0.Final:create "-DprojectGroupId=com.lukaskj.irest" "-DprojectArtifactId=marketplace" "-DclassName=com.lukaskj.irest.marketplace.HelloResource" "-Dpath=/marketplace"

$ ./mvnw clean package -DskipTests

# Register
$ ./mvnw quarkus:add-extension -Dextensions="jdbc-postgres,hibernate-orm-panache,resteasy-jsonb,smallrye-openapi,hibernate-validator,quarkus-smallrye-jwt,quarkus-smallrye-opentracing,quarkus-smallrye-metrics,smallrye-reactive-messaging-amqp"

$ ./mvnw quarkus:add-extension -Dextensions="jdbc-postgres,hibernate-validator"

# Marketplace
$ ./mvnw quarkus:add-extension -Dextensions="resteasy-mutiny,jdbc-postgres, flyway, pg-client, smallrye-reactive-messaging-amqp"

$ docker run --ulimit memlock=-1:-1 -it --rm --memory-swappiness=0 --name quarkus_test -e POSTGRES_USER=quarkus_test -e POSTGRES_PASSWORD=quarkus_test -e POSTGRES_DB=quarkus_test -p 5432:5432 postgres:13-alpine

$ docker run --name postgres -e POSTGRES_USER=root -e POSTGRES_PASSWORD=root -p 5432:5432 postgres:13-alpine

$ ./mvnw quarkus:dev
```