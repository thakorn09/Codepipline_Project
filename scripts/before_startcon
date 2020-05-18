#!/bin/bash
echo "Login to ECR"
$sudo $(aws ecr get-login --no-include-email --region ap-southeast-1)

COMMIT_HASH=$(echo $CODEBUILD_RESOLVED_SOURCE_VERSION | cut -c 1-7)
IMAGE_TAG=${COMMIT_HASH}

echo $IMAGE_TAG





