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
import org.apache.commons.collections.IteratorUtils;
         
 public class SortReducer extends Reducer<LongWritable, IntWritable, LongWritable, Text> {
    public static final Log LOG = LogFactory.getLog(SortReducer.class);

    @Override
    public void reduce(LongWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        
        LOG.info(" LOG REDUCER CALLED "+key);

        //List<IntWritable> list = IteratorUtils.toList(values.iterator()); 
        String output ="";

        while (values.iterator().hasNext()) {
            output += values.iterator().next() + " ";
            LOG.info("Loop"+output);
        }

        LOG.info(output);
        /*for(IntWritable i: list){
            LOG.info("NOT SORTED "+output);
            output += i + " ";
        }

        Collections.sort(list);
        output ="";
        for(IntWritable i: list){
        LOG.info("SORTED "+output);
            output += i + " ";
        }*/
        //for (Iterator it = list.iterator(); it.hasNext(); ) {
        //    LOG.info(output);
        //    output += it.next() + " ";
        //}

        context.write(key, new Text(output));
    }
 }
        
