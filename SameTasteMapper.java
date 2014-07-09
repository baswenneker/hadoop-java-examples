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
        
        
public class SameTasteMapper extends Mapper<LongWritable, Text, Text, Text> {
    private Text personName = new Text();
    private Text personTaste = new Text();
public static final Log LOG = LogFactory.getLog(SameTasteMapper.class);
        
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        StringTokenizer tokenizer = new StringTokenizer(line, " ");
System.out.println(" MAPPER CALLED");

        LOG.info(" LOG MAPPER CALLED");
        personName.set(tokenizer.nextToken());
        tokenizer.nextToken(); // Skip 'likes'
        personTaste.set(tokenizer.nextToken());
        context.write(personTaste, personName);
    }
 } 
         