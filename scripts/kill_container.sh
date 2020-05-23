#!/bin/bash
echo 'Stopping the running container on ec2'
docker rm -f $(docker ps -a -q)