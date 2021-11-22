# Development

Using:

- Maven
- Spring Boot 2.5.6
- Spring Web 
- Spring Data JPA
- H2 Database - Embedded
- JUnit
- Swagger
- Docker
 
----------------

## Run app
###To create this project docker image 
In the project folder run:
> mvn clean install
- change directory
> cd docker
- build the image
> docker build -t shopping-cart .
- list the image
> docker images

###To run this project docker image
> docker run -d -p 8080:8080 --name shopping-cart-server shopping-cart

#### Links localhost:
Context Path: api/shopping/v1/
- http://127.0.0.1:8080/api/shopping/v1/api-docs
- http://127.0.0.1:8080/api/shopping/v1/swagger-ui.html
