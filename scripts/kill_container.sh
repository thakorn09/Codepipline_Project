#!/bin/bash
echo 'Stopping the running container'
docker rm -f $(docker ps -a -q)