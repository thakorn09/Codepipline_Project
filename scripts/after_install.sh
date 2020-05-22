#!/bin/bash
echo 'Login To ECR'
$sudo $(aws ecr get-login --no-include-email --region ap-southeast-1)
