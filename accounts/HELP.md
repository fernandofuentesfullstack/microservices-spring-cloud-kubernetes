# Getting Started

### Reference Documentation
```
mvn clean package
```
```
docker build -t fernandofuentesfullstack/accounts:0.0.1 .
```
```
docker run -d -p 8099:8080 -t fernandofuentesfullstack/accounts:0.0.1
```

OR with Paketo Buildpacks

```
mvn spring-boot:build-image
```
```
docker run -d -p 8099:8080 -t fernandofuentesfullstack/accounts:0.0.1-SNAPSHOT
```
