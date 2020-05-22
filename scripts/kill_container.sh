#!/bin/bash
echo 'Stopping the running container'
docker rm $(docker ps -aq)
