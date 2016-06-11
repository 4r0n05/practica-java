#!/bin/bash

rm -R hito2 >& /dev/null;

javac -d "." java/*.java;

jar -vcfm hito2.jar manifest.mf hito2/*.class;

#java -jar hito2.jar
