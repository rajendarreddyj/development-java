development-java
=================
This Directory will have example projects related to spring framework, hibernate, Core Java and Java EE.

[![license](https://img.shields.io/github/license/mashape/apistatus.svg)](https://raw.githubusercontent.com/rajendarreddyj/development-java/master/LICENSE)
[![Build Status](https://travis-ci.org/rajendarreddyj/development-java.svg?branch=master)](https://travis-ci.org/rajendarreddyj/development-java)
[![Coverage Status](https://coveralls.io/repos/github/rajendarreddyj/development-java/badge.svg?branch=master)](https://coveralls.io/github/rajendarreddyj/development-java?branch=master)
[![Coverity Scan](https://scan.coverity.com/projects/11891/badge.svg)](https://scan.coverity.com/projects/rajendarreddyj-development-java)
[![Quality Gate](https://sonarqube.com/api/badges/gate?key=com.rajendarreddyj%3Adevelopment-java)](https://sonarqube.com/dashboard/index/com.rajendarreddyj%3Adevelopment-java) 
[![Technical debt ratio](https://sonarqube.com/api/badges/measure?key=com.rajendarreddyj%3Adevelopment-java&metric=sqale_debt_ratio)](https://sonarqube.com/dashboard/index/com.rajendarreddyj%3Adevelopment-java)

This repository will have example projects related to spring framework, hibernate, Core Java and Java EE.

This mojo performs byte code analysis to determine missing or unused dependencies. This goal is meant to be launched from the command line. It will fork the build and execute test-compile so there are class files to analyze. If you want to bind analyze in your pom, use the dependency:analyze-only mojo instead.

`mvn dependency:analyze`

Below for just detecting projects that override the dependencyManagement directly. Set ignoreDirect to false to detect these otherwise normal conditions.

`mvn dependency:analyze-dep-mgt`

This mojo is used to view the dependency hierarchy of the project currently being built. It will output the resolved tree of dependencies that the Maven build process actually uses.

`mvn dependency:tree`

This mojo displays all dependencies that have newer versions available.
 
`mvn versions:display-dependency-updates`

This mojo displays all plugins that have newer versions available.

`mvn versions:display-plugin-updates`

This mojo displays properties that are linked to artifact versions and have updates available.

`mvn versions:display-property-updates`

To create PMD report

`mvn -Dmaven.test.failure.ignore=true pmd:pmd`

To open findbugs gui

`mvn -Dmaven.test.failure.ignore=true findbugs:gui`

To create findbugs report

`mvn -Dmaven.test.failure.ignore=true findbugs:findbugs` 

To run sonarqube analysis

`mvn -Dmaven.test.failure.ignore=true clean verify sonar:sonar`

To generate checkstyle report as standalone

`mvn -Dmaven.test.failure.ignore=true checkstyle:checkstyle`

To run cobertura code coverage analysis to report to coveralls -- requires coveralls token

`mvn -Dmaven.test.failure.ignore=true clean test cobertura:cobertura coveralls:report`
