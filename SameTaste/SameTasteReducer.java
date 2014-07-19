// http://stevekrenzel.com/finding-friends-with-mapreduce
// https://sites.google.com/site/tacchadoop/home/word-count-example-part-i---create-your-own-jar
// http://hadoop.apache.org/docs/r0.18.3/mapred_tutorial.html
// http://www.javacodegeeks.com/2013/08/writing-a-hadoop-mapreduce-task-in-java.html
// http://code.google.com/p/hadoop-map-reduce-examples/source/browse/trunk/hadoop-examples/src/com/hadoop/examples/anagrams/
// https://www.dbtsai.com/blog/hadoop-mr-to-implement-people-you-might-know-friendship-recommendation/


package com.baswenneker;

import java.io.IOException;
import java.util.*;
import org.apache.commons.logging.*;
        
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
         
 public class SameTasteReducer extends Reducer<Text, Text, Text, Text> {
    public static final Log LOG = LogFactory.getLog(SameTasteReducer.class);

    //http://stackoverflow.com/questions/10956254/hadoop-reduce-function-is-not-excuted
    @Override
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        
        //System.exit(0);
        //throw new IOException("TERROR");
        //return;
        LOG.info(" LOG REDUCER CALLED");

        String output ="";
        while (values.iterator().hasNext()) {
            output += values.iterator().next() + " ";
            //if(values.hasNext()){
            //	output += ", ";
            //}
        }
        context.write(key, new Text(output));
    }
 }
        
