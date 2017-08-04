# 概述
通过Java API操作 CDH的Hadoop相关代码

# JDK
version 1.7

```
export JAVA_HOME=/home/zhaogj/jdk
export CLASSPATH=$CLASSPATH:$JAVA_HOME/lib:$JAVA_HOME/jre/lib
export PATH=$JAVA_HOME/bin:$JAVA_HOME/jre/bin:$PATH:$HOME/bin
```

# RUN
```bash
cd /home/zhaogj
mkdir cdhTest
cd cdhTest
mkdir bin
mkdir lib
mkdir logs

```

# Kafka
```bash
# start kafka
bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties

```