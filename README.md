# development-java    
[![license](https://img.shields.io/github/license/mashape/apistatus.svg)](https://raw.githubusercontent.com/rajendarreddyj/development-java/master/LICENSE)
[![Build Status](https://travis-ci.org/rajendarreddyj/development-java.svg?branch=master)](https://travis-ci.org/rajendarreddyj/development-java)
[![Coverage Status](https://coveralls.io/repos/github/rajendarreddyj/development-java/badge.svg?branch=master)](https://coveralls.io/github/rajendarreddyj/development-java?branch=master)
[![Coverity Scan](https://scan.coverity.com/projects/11891/badge.svg)](https://scan.coverity.com/projects/rajendarreddyj-development-java)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=com.rajendarreddyj%3Adevelopment-java&metric=alert_status)](https://sonarcloud.io/dashboard/index/com.rajendarreddyj%3Adevelopment-java) 
[![Technical debt ratio](https://sonarcloud.io/api/project_badges/measure?project=com.rajendarreddyj%3Adevelopment-java&metric=sqale_index)](https://sonarcloud.io/dashboard/index/com.rajendarreddyj%3Adevelopment-java)
[![](https://img.shields.io/github/issues-raw/rajendarreddyj/development-java.svg)](https://github.com/rajendarreddyj/development-java/issues)
[![](https://tokei.rs/b1/github/rajendarreddyj/development-java?category=code)](https://github.com/rajendarreddyj/development-java)

This repository will have example projects related to spring framework, hibernate, Core Java and Java EE.

This mojo performs byte code analysis to determine missing or unused dependencies. This goal is meant to be launched from the command line. It will fork the build and execute test-compile so there are class files to analyze. If you want to bind analyze in your pom, use the dependency:analyze-only mojo instead.

`mvn dependency:analyze`

Below for just detecting projects that override the dependencyManagement directly. Set ignoreDirect to false to detect these otherwise normal conditions.

`mvn dependency:analyze-dep-mgt`

This mojo is used to view the dependency hierarchy of the project currently being built. It will output the resolved tree of dependencies that the Maven build process actually uses.

`mvn dependency:tree`

This mojo will set New version for parent pom.

`mvn versions:set -DnewVersion=1.0`

This mojo will update version in child modules.

`mvn versions:update-child-modules`

This mojo displays all dependencies that have newer versions available.
 
`mvn versions:display-dependency-updates`

This mojo displays all plugins that have newer versions available.

`mvn versions:display-plugin-updates`

This mojo displays properties that are linked to artifact versions and have updates available.

`mvn versions:display-property-updates`

This mojo will update your dependencies to the latest available versions

`mvn versions:use-latest-versions`

This mojo will update your dependecies specified in property values

`mvn versions:update-properties`

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

To create a maven wrapper go in the main folder of the project and run this command:

`mvn -N io.takari:maven:wrapper`

To create a maven wrapper with specific the version of Maven:

`mvn -N io.takari:maven:wrapper -Dmaven=3.5.2`
