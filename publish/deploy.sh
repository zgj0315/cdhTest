#!/bin/bash

CMD="$0"
SHELL_PATH=`cd $(dirname "$CMD"); pwd`
PROJECT_PATH=${SHELL_PATH%/*}

cd ${PROJECT_PATH}; mvn clean package -Dmaven.test.skip=true; cd ${SHELL_PATH}

JAR=$(find ${PROJECT_PATH}/target -name "*.jar")
JAR_ORIGINAL=$(find ${PROJECT_PATH}/target -name "*.jar.original")
PROJECT_NAME=${JAR##*/}
PROJECT_NAME=${PROJECT_NAME%.jar}

scp ${JAR_ORIGINAL} zhaogj@172.0.0.21:~/cdhTest-1.0/lib/${PROJECT_NAME}.jar