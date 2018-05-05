g## Introduction

The goal of this proof of concept is to demonstrate a solution on how regular JSF2 web applications can be enabled for (Auto-) Scalability, High Availability (HA), and Disaster Recovery (DR). 
This requires to load balance requests over multiple application servers in multiple data centers (or Availability Zones). A common problem is handling session state accross multiple http requests. Session state is very common in modern web applications. Just think of the information: Is the user logged in or not.  
This can be addressed by implementing session affinity, aka session stickiness, which ensures that the requests of one user are always sent to the same server. This strategy however has a painful drawback: When that server becomes unavailable (e.g. due to auto scaling event, maintenance, technical problems, etc ..), all users on that server will loose their session state, which may be acceptable in a DR event, but not for HA setups and frequent Auto-Scaling events.
The alternative is to share this state accross multiple servers.

## ALB, ECS, ElastiCache Demo

This simple web application is intended to demonstrate how session state can be shared using managed services offered on the AWS platform.
