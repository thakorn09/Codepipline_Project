#!/bin/bash
echo 'start_container'
cd server
docker-compose pull
docker-compose up -d