#!/usr/bin/env bash

for i in "$@"
do
case $i in
    -b|--build)
	echo "# Compile scripts"
    sudo rm classes/MapReduceChain/*.class
	sudo javac -classpath `yarn classpath` -d classes/ *.java
	echo "# Create JAR"
	sudo jar cvf mapreducechain.jar -C classes/ MapReduceChain 
	shift
	;;
    -c|--cleanup)
	echo "# Cleanup HDFS result directory"
	hdfs dfs -rm -r /tmp/testing/mapreducechain_out
	shift
	;;
    -i|--input)
	echo "# Create input directory and copy input file"
	hdfs dfs -mkdir -p /tmp/testing/mapreducechain_in
    hdfs dfs -rm -r /tmp/testing/mapreducechain_in/*
	hdfs dfs -copyFromLocal ./table7 /tmp/testing/mapreducechain_in
	hdfs dfs -copyFromLocal ./table15 /tmp/testing/mapreducechain_in
	shift
	;;
    -r|--run)
	echo "# Run hadoop job"
	hadoop jar mapreducechain.jar MapReduceChain.MapReduceChainJob /tmp/testing/mapreducechain_in /tmp/testing/mapreducechain_out
	shift
	;;
    -s|--show)
	echo "# Show results"
	hdfs dfs -cat /tmp/testing/mapreducechain_out/part-r-00000
	shift
	;;
    -h|--help)
    cat help.txt	
    shift
	;;
    *)
	echo "# Unknown option $key"
	# exit 1    
        # unknown option
    ;;
esac
done

echo "# Finished"
