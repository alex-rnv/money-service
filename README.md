# MONEY SERVICE #

Demo REST API implementation for money transfers.

### Design Requirements ###
* Be simple
* No Spring
* In-memory store
* Run as Java standalone app
* Tests


### Installation Requirements ###
* Java 8 (No new features used, but it was tested with this version only)
* Gradle
* Git

### Usage ###

There is helper script money-service.sh provided for *nix environments to simplify testing. Just use its functions directly on other OS. 

```
#!shell
git clone https://alex-rnv@bitbucket.org/alex-rnv/money-service.git
cd money-service
./money-service.sh build
./money-service.sh start
./money-service.sh fulfill # adds some test accounts to in-memory store
```

By default, 8080 port is used. Transfer example:
```
curl localhost:8080/transfer?from=<id>&to=<id>&amount=<num>
```

To check account information before and after transfer, use
```
curl localhost:8080/account/find?id=<id>
```

### Demo application limitations ###
* Simplified data model.
* HTTP GET is used with no auth (just easier to check in browser).
* No code comments, as it is standard layered classes hierarchy.
* No unit tests, as business logic is simple and covered by integration test.