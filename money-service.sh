#!/bin/bash

help() {
    printf "Usage:
    ./money-service.sh <help|build|start|fulfill|stop>
    help - shows this help
    build - builds money service jar with dependencies
    start - starts money service
    fulfill - fills service with test accounts data
    stop - stops money service\n"
}

build() {
    gradle clean build shadowJar
}


start() {
    java -jar build/libs/money-service-1.0-SNAPSHOT.jar &

}

fulfill() {
    curl "localhost:8080/account/create?id=1&amount=100000"
    curl "localhost:8080/account/create?id=2&amount=500"
    curl "localhost:8080/account/create?id=3&amount=100.34"
    curl "localhost:8080/account/create?id=4&amount=3245.90"
}

stop() {
    curl "localhost:8080/system/kill" &
}

cmd=$1

if [ "$cmd" = "help" ]; then
    help
elif [ "$cmd" = "build" ]; then
    build
elif [ "$cmd" = "start" ]; then
    start
elif [ "$cmd" = "fulfill" ]; then
    fulfill
elif [ "$cmd" = "stop" ]; then
    stop
else
    help
fi;