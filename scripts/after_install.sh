#!/bin/bash
echo 'Login to ECR'
sudo snap install docker
$sudo $(aws ecr get-login --no-include-email --region ap-southeast-1)
