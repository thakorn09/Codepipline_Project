#!/bin/bash
echo 'start_container'
cd ~
docker-compose pull
docker-compose up -d