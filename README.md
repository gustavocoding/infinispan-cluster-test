### Infinispan Cluster Test

Simple unit test for startup of Infinispan server nodes.

#### Running

```
mvn -Dserver.location=<SERVER_HOME> test -s maven-settings.xml
```

where ```SERVER_HOME``` is the directory where the server is located.

The logs will be at ```SERVER_HOME/logs/server0```, ```SERVER_HOME/logs/server1```, ```SERVER_HOME/logs/server2```

#### Enabling trace

```
mvn -DenableTrace=true -Dserver.location=<SERVER_HOME> test -s maven-settings.xml
```