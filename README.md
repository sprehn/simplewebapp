## Introduction

The goal of this proof of concept is to demonstrate a solution on how regular JSF2 web applications can be enabled for (Auto-) Scalability, High Availability (HA), and Disaster Recovery (DR). 
This requires to load balance requests over multiple application servers in multiple data centers (or Availability Zones). A common problem is handling session state accross multiple http requests. Session state is very common in modern web applications. Just think of the information: Is the user logged in or not.  
This can be addressed by implementing session affinity, aka session stickiness, which ensures that the requests of one user are always sent to the same server. This strategy however has a painful drawback: When that server becomes unavailable (e.g. due to auto scaling event, maintenance, technical problems, etc ..), all users on that server will loose their session state, which may be acceptable in a DR event, but not for HA setups and frequent Auto-Scaling events.
The alternative is to share this state accross multiple servers.

## ALB, ECS, ElastiCache Demo

This simple web application is intended to demonstrate how session state can be shared using managed services offered on the AWS platform.

## Credits 
This project is derived from this article [Scaling JSF applications with Spring Session](https://auth0.com/blog/horizontal-scaling-jsf-applications-with-spring-session/) and this github [project](https://github.com/auth0-blog/spring-boot-session).

## Application commands

```bash
# run app locally
mvn spring-boot:run

# prepare to dockerize
mvn clean package

# create dockerized app image
docker build -t simplewebapp .

# run dockerized app
docker run -d -p 8081:8080 --name webapp-1 -e JAVA_OPTS="-Dspring.redis.host=YOUR_REDIS_HOST" simplewebapp 
docker run -d -p 8082:8080 --name webapp-2 -e JAVA_OPTS="-Dspring.redis.host=YOUR_REDIS_HOST" simplewebapp 

# stop and remove docker instance
docker rm -f webapp-1
docker rm -f webapp-2

# getting application logs
docker logs webapp-1
```

## NGINX commands

```bash
# create dockerized app image
docker build -t simpleloadbalancer nginx

# run dockerized app
docker run -p 80:80 -d --name lb-1 simpleloadbalancer

It is important that internal and external port match. Otherwise jsf actions will get confused.

# stop and remove docker instance
docker rm -f lb-1

# getting application logs
docker logs lb-1
```

## Some redis commands
start redis server
```bash
redis-server
```

log into cli
````bash
redis-cli
```

useful commnads
```bash
# to set a password on a running redis instance
127.0.0.1:6379> CONFIG set requirepass secret

# authenticate with a password in redis-cli
127.0.0.1:6379> AUTH secret

# allow access from non-localhost 
127.0.0.1:6379> CONFIG SET protected-mode no
```
