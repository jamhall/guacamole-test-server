# guacamole-test-server
A server for testing remote desktop connections to guacd

## Running

Package:

```
mvn package
```

Build:

```
java -jar target/gts.jar --guacd-host 1.2.3.4 --guacd-port 4822 --port 8080
```
