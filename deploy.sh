mvn clean package -Dmaven.test.skip=true
scp -P 10022 target/dataServiceIDC-1.0.jar.original nti_java@10.200.1.86:/home/nti_java/dataServiceIDC/lib/dataServiceIDC-1.0.jar