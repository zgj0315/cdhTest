#!/bin/bash
# Created by zhaogj on 05/11/2016.

# 脚本名称
PRG="$0"
# echo "PRG=$PRG"

# 脚本所在目录
BIN=`cd $(dirname "$PRG"); pwd`
#echo "BIN=$BIN"

HOME=`dirname "$BIN"`
#echo "HOME=$HOME"

# 找到所有lib下的依赖包
LIB=`find ${HOME}/lib/ -name "*.jar"`
#echo "LIB=$LIB"

# 日志目录
LOG=${HOME}/logs/
#echo "LOG=$LOG"

classpath="."
for item in $LIB
do
  classpath=$classpath:$item
done
#echo "classpath=$classpath"

JVM_OPTS="-server -Xms4G -Xmx4G -Xloggc:$LOG/gc.log -XX:-PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCApplicationStoppedTime -XX:+UseConcMarkSweepGC -XX:CMSInitiatingOccupancyFraction=70 -XX:+CMSParallelRemarkEnabled -XX:+HeapDumpOnOutOfMemoryError"

for i in `ps aux | grep cdhTest | grep -server | awk '{print$2}'`
do
  echo "kill "$i
  kill $i
done
echo "sleep 3s"
sleep 3

java $JVM_OPTS -cp $classpath org.after90.Application > /dev/null 2>&1 &
echo "dataService start success."
