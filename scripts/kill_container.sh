#!/bin/bash
echo 'Stopping the running container on ec2'
docker-compose stop
docker-compose rm -f