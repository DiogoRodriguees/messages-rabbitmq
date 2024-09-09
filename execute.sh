#!/bin/bash

COMMAND=$1

echo "[LOGGER] Executing $COMMAND ..."

if [ "$COMMAND" == "rabbitmq" ]; then
    echo "[Docker] Starting containers rabbitmq"
    docker run -d --hostname rabbitmq --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
fi

if [ "$COMMAND" == "coletor" ]; then
    cd ./coletor/src
    go run main.go
fi

if [ "$COMMAND" == "classificador" ]; then
    echo "[LOGGER] Executing classificador ..."
fi

if [ "$COMMAND" == "assinante" ]; then
    echo "[LOGGER] Executing assinantes ..."
fi