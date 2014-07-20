#!/usr/bin/env bash

for i in "$@"
do
case $i in
    -b|--build)
	echo "# Compile scripts"
	sudo javac -classpath `yarn classpath` -d classes/ *.java
	echo "# Create JAR"
	sudo jar cvf sametaste.jar -C classes/ com 
	shift
	;;
    -c|--cleanup)
	echo "# Cleanup HDFS result directory"
	hdfs dfs -rm -r /tmp/testing/sametaste_out
	shift
	;;
    -i|--input)
	echo "# Create input directory and copy input file"
	hdfs dfs -mkdir -p /tmp/testing/sametaste_in
	hdfs dfs -copyFromLocal ./tastes.txt /tmp/testing/sametaste_in
	shift
	;;
    -r|--run)
	echo "# Run hadoop job"
	hadoop jar sametaste.jar com.baswenneker.SameTasteJob /tmp/testing/sametaste_in /tmp/testing/sametaste_out
	shift
	;;
    -s|--show)
	echo "# Show results"
	hdfs dfs -cat /tmp/testing/sametaste_out/part-r-00000
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

echo "# Finished build"
