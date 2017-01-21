@echo off
set PROPPATH=".\src\main\resources"
set CLASSPATH=.;./target/scheduler-0.0.1-SNAPSHOT.jar;%PROPPATH%
set JAVA_HOME="C:\Program Files\Java\jdk1.8.0_121"
%JAVA_HOME%\bin\java -Xms128m -Xmx384m -Xnoclassgc -classpath %CLASSPATH% com.rajendarreddyj.scheduler.ReadFile