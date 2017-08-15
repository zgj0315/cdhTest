#!/bin/bash
# Created by zhaogj on 20170815

# 获取命令字符串
CMD="$0"

# 获取命令绝对路径
SHELL_PATH=`cd $(dirname "$CMD"); pwd`

# 判断脚本是否在publish目录下
if test ${SHELL_PATH##*/} != "publish"; then
  echo "err shell path"
  exit
fi

PROJECT_PATH=${SHELL_PATH%/*}

# maven打包工程
cd ${PROJECT_PATH}; mvn clean package -Dmaven.test.skip=true; cd ${SHELL_PATH}

JAR=$(find ${PROJECT_PATH}/target -name "*.jar")
JAR_ORIGINAL=$(find ${PROJECT_PATH}/target -name "*.jar.original")

unzip -o ${JAR} -d ${PROJECT_PATH}/target/

PROJECT_NAME=${JAR##*/}
PROJECT_NAME=${PROJECT_NAME%.jar}

PACKAGE_PATH=${SHELL_PATH}/${PROJECT_NAME}

if test -d ${PACKAGE_PATH}/bin
  then
    rm -f ${PACKAGE_PATH}/bin/*
  else
    mkdir -p ${PACKAGE_PATH}/bin
fi

if test -d ${PACKAGE_PATH}/lib
  then
    rm -f ${PACKAGE_PATH}/lib/*
  else
    mkdir -p ${PACKAGE_PATH}/lib
fi

if test -d ${PACKAGE_PATH}/log
  then
    rm -f ${PACKAGE_PATH}/log/*
  else
    mkdir -p ${PACKAGE_PATH}/log
fi

cp ${PROJECT_PATH}/target/BOOT-INF/lib/*.jar ${PACKAGE_PATH}/lib/
cp ${JAR_ORIGINAL} ${PACKAGE_PATH}/lib/${PROJECT_NAME}.jar

echo -e "#!/bin/bash
PRG=\"\$0\"
BIN=\`cd \$(dirname \"\$PRG\"); pwd\`
HOME=\`dirname \"\$BIN\"\`
LIB=\`find \${HOME}/lib/ -name \"*.jar\"\`
LOG=\${HOME}/logs/
classpath=\".\"
for item in \${LIB}
do
  classpath=\${classpath}:\${item}
done
JVM_OPTS=\"-server -Xms4G -Xmx4G -Xloggc:\${LOG}/gc.log -XX:-PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCApplicationStoppedTime -XX:+UseConcMarkSweepGC -XX:CMSInitiatingOccupancyFraction=70 -XX:+CMSParallelRemarkEnabled -XX:+HeapDumpOnOutOfMemoryError\"
for i in \`ps aux | grep $PROJECT_NAME | grep -server | awk '{print\$2}'\`
do
  echo \"kill \"\${i}
  kill \${i}
done
echo \"sleep 3s\"
sleep 3
java \${JVM_OPTS} -cp \${classpath} org.after90.Application > /dev/null 2>&1 &
echo \"dataService start success.\"" > ${PACKAGE_PATH}/bin/restart.sh
chmod +x ${PACKAGE_PATH}/bin/restart.sh