# example-spring-boot

## Building

```
mvn package
```

## Running

Please refer to [README](https://github.com/CRaC/docs#users-flow) for details.

### Preparing the image
1. Run the [JDK](README.md#JDK) in the checkpoint mode
```
$JAVA_HOME/bin/java -XX:CRaCCheckpointTo=cr -jar target/spring-boot-0.0.1-SNAPSHOT.jar
```
2. Warm-up the instance
```
siege -c 1 -r 100000 -b -H "Content-type: text/plain" "http://localhost:8080/transform POST < example.xml"
```
3. Request checkpoint
```
jcmd target/spring-boot-0.0.1-SNAPSHOT.jar JDK.checkpoint
```

### Restoring

```
$JAVA_HOME/bin/java -XX:CRaCRestoreFrom=cr
```
