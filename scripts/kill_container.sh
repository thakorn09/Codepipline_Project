#!/bin/bash
echo 'Stopping the running container on ec2'
cd server
docker-compose stop
docker-compose rm -f