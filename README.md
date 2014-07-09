hadoop-mapreduce-sametaste
==========================


sudo javac -classpath `yarn classpath` -d sametaste_classes2/ SameTasteJob.java SameTasteMapper.java SameTasteReducer.java 

sudo jar cvf sametaste.jar -C sametaste_classes2/ .

```bash
hadoop jar /vagrant/hadoop-mapreduce-sametaste/sametaste.jar com.baswenneker.SameTaste /tmp/testing/sametaste_in /tmp/testing/sametaste_out
```

hdfs dfs -rm -r /tmp/testing/sametaste_out

http://192.168.33.10:50070/logs/userlogs/

hdfs dfs -cat /tmp/testing/sametaste_out/part-r-00000