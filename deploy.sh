mvn clean package -Dmaven.test.skip=true
#scp target/cdhTest-1.0.jar.original zhaogj@172.0.0.101:/home/zhaogj/cdhTest/lib/cdhTest-1.0.jar
scp target/cdhTest-1.0.jar zhaogj@172.0.0.21:/home/zhaogj/cdhTest/tmp/cdhTest-1.0.jar