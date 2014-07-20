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
        
 /*       
public class AddMapper extends Mapper<LongWritable, Text, Text, Text> {
    private Text personName = new Text();
    private Text personTaste = new Text();
    
    public static final Log LOG = LogFactory.getLog(AddMapper.class);
        
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        StringTokenizer tokenizer = new StringTokenizer(line, " ");

        List<String> list = new ArrayList<String>();
        while(tokenizer.hasMoreTokens()){
            list.add(tokenizer.nextToken());
        }

        Collections.sort(list);
        context.write(key, list);
    }
 } 
         */