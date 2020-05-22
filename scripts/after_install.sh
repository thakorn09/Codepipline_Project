#!/bin/bash
echo 'Login to ECR'
$sudo $(aws ecr get-login --no-include-email --region ap-southeast-1)
