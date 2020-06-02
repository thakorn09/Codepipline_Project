#!/bin/bash
echo 'change dir'
cd ~
echo 'Stopping the running container on Ec2'
docker-compose stop
docker-compose rm -f

