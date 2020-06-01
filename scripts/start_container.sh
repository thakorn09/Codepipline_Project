#!/bin/bash
echo 'start_container'
docker pull 862605034458.dkr.ecr.ap-southeast-1.amazonaws.com/codepipeline:$IMAGE_TAG
docker run -d -p 9000:9000 862605034458.dkr.ecr.ap-southeast-1.amazonaws.com/codepipeline:$IMAGE_TAG