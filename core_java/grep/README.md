# Introduction

This application will  mimics Linux grep command which allows users to search matching strings from files.
The app has 2 Classes `JavaGrepImp` which use `for` loop for iterating through the file to find a match pattern. `JavaGrepLambdaImp`
will use lambda and stream API for better and convenient `grep` implementation. The app is dockerized to Dockerhub for usage purpose.

# Quick Start

```
    # Using CLI
    
    cd core_java/grep
    mvn clean compile package
    java -cp target/grep-1.0-SNAPSHOT.jar ca.jrvs.apps.grep.JavaGrepImp \
    .*Romeo.*Juliet.* ./data ./out/grep.txt
    
    # Using Docker
    
    cd core_java/grep
    mvn clean compile package
    docker build -t grep_app .
    docker run --rm -v `pwd`/data:/data -v `pwd`/log:/log grep_app .*Romeo.*Juliet.* /data /log/grep.out

```
# Implemenation

## Pseudocode

```
    matchedLines = []
    for file in listFiles(rootDir)
        for line in readLines(file)
            if containsPattern(line)
                matchedLines.add(line)
    writeToFile(matchedLines)
    
```
## Performance Issue
Using `List`  may take up a lot of memory when processing large data sets.

# Deployment
The Grep app is pushed to Github and also Dockerhub. User can download the images from
Dockerhub to use the app.

#Improvements
- Improving and handle all possible error
- Improving memory usage by replacing `List` with `Stream`




