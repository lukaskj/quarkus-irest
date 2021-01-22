```bash
$ ./mvnw clean package -DskipTests
$ ./mvnw quarkus:add-extension -Dextensions="jdbc-postgres,hibernate-orm-panache,resteasy-jsonb,smallrye-openapi,hibernate-validator"

$ ./mvnw quarkus:add-extension -Dextensions="jdbc-postgres,hibernate-validator"

$ docker run --ulimit memlock=-1:-1 -it --rm --memory-swappiness=0 --name quarkus_test -e POSTGRES_USER=quarkus_test -e POSTGRES_PASSWORD=quarkus_test -e POSTGRES_DB=quarkus_test -p 5432:5432 postgres:13-alpine

$ docker run --name postgres -e POSTGRES_USER=root -e POSTGRES_PASSWORD=root -p 5432:5432 postgres:13-alpine
```