#!/bin/bash

PARAM=$1

if [ "$PARAM" == "rabbitmq" ]; then
    echo "[Docker] Stopping containers rabbitmq"
    docker stop $(docker ps -f name=rabbitmq -q) > /tmp/comunicacao-indireta-discard.txt
    echo "[Docker] Removing containers rabbitmq"
    docker rm $(docker ps -a -f name=rabbitmq -q) > /tmp/comunicacao-indireta-discard.txt
fi