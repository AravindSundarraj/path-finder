# Project Title : 

        path-finder

# Project Description :
````
 path-finder app is used finds the path between two cities exist or not.
 if the path is found response will be 'yes' otherwise 'no'
````
````
For Example:
city.txt content is:
Boston, New York
Philadelphia, Newark
Newark, Boston
Trenton, Albany

http://localhost:9091/path-finder/v1/route?destination=Boston&origin=Newark

Should return yes

http://localhost:9091/path-finder/v1/route?destination=Boston&origin=Florida

Should return yes

http://localhost:9091/path-finder/v1/route?destination=Albany&origin=Philadelphia

Should return no
````

 
# Build :
````
mvn clean build package
````

# Execute the path-finder:

Using maven Spring Boot plugin 
``` 
    mvn spring-boot:run 
```
Using Java command line 
```
    java -jar target/path-finder-0.0.1-SNAPSHOT.jar
```

# path-finder - documentation
# swagger
````
  http://localhost:9091/path-finder/swagger-ui.html
````  

# Tech/Framework used

````
Graph data structure is used to implement the path between cities.

BFS (Breath First Search) is used to identify the connection exist or not

Spring-boot
Java8
Swagger
maven

````
# Code coverage
 ````
Jacoco plugin is added to generate code coverage report

````

