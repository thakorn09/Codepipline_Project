#!/bin/bash
echo "Stopping the running container"
cd ~
docker rm -f $(docker ps -a -q)
docker ps
