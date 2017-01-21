#!/bin/bash
JAVA_HOME=/apps/java/jdk8
PROP_PATH=./src/main/resources/
CLASSPATH=.:./target/scheduler-0.0.1-SNAPSHOT.jar:$PROP_PATH:
$JAVA_HOME/bin/java -Xms128m -Xmx384m -Xnoclassgc -cp $CLASSPATH com.rajendarreddyj.scheduler.ReadFile
exit 0
