#!/bin/bash
echo 'Stopping the running container'
sudo snap install docker
docker rm -f $(docker ps -a -q)