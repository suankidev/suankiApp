# suankiApp

It contains three module:
a. common : common utility method to used in other module
b. scalaDev  : 1. it contains the scala basic and advance learning material
               2. design pattern
c. sparkWithScala: contains spark code for dev


to load the project:

1. open project in ide
2. run in cmd: 
   it might take time for the first time
   sbt clean compile

3. ~sparkWithScala/runMain com.suanki.Init


## Plugins
- sbt-scalafmt
- sbt-assembly


## Test
Run the tests with enabled coverage:
```shell script
sbt clean test
```

To generate the coverage reports run
```shell script
sbt coverageReport
```

By default, scoverage will generate reports for each project separately. You can merge them into an aggregated report by using the following:
```shell script
sbt coverageAggregate