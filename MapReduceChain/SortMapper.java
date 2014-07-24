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
import org.apache.hadoop.io.IntWritable;
        
public class SortMapper extends Mapper<LongWritable, Text, LongWritable, IntWritable> {

    public static final Log LOG = LogFactory.getLog(SortMapper.class);
        
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        StringTokenizer tokenizer = new StringTokenizer(line, " ");
        Integer i = 0;
        while(tokenizer.hasMoreTokens()){
            i = Integer.parseInt(tokenizer.nextToken());
            i += i%2;
            LOG.info("LOOP "+i);
            context.write(key, new IntWritable(i));
        }
    }
 }       
