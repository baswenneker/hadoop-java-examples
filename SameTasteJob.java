// http://stevekrenzel.com/finding-friends-with-mapreduce
// https://sites.google.com/site/tacchadoop/home/word-count-example-part-i---create-your-own-jar
// http://hadoop.apache.org/docs/r0.18.3/mapred_tutorial.html
// http://www.javacodegeeks.com/2013/08/writing-a-hadoop-mapreduce-task-in-java.html
// http://code.google.com/p/hadoop-map-reduce-examples/source/browse/trunk/hadoop-examples/src/com/hadoop/examples/anagrams/
// https://www.dbtsai.com/blog/hadoop-mr-to-implement-people-you-might-know-friendship-recommendation/
// http://stackoverflow.com/questions/23122744/cannot-run-the-job-on-hadoop-cluster-only-runs-using-localjobrunner


package com.baswenneker;

import java.io.IOException;
import java.util.*;
        
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.util.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
    

public class SameTasteJob extends Configured implements Tool
{
        
@Override
    public int run(String[] args) throws Exception
    {
        Configuration conf = new Configuration();
        Job job = new Job(conf, "sametaste");
        job.setJarByClass(SameTasteReducer.class);
        
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
            
        job.setMapperClass(SameTasteMapper.class);
        job.setReducerClass(SameTasteReducer.class);
            
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        //job.setOutputKeyClass(Text.class);
        //job.setOutputValueClass(Text.class);


        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
            
        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new Configuration(), new SameTasteJob(), args);
        System.exit(exitCode);
    }
        
}

/*
package com.baswenneker;

import java.io.IOException;
import java.util.*;
        
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
        
public class SameTasteJob {
        
 public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = new Job(conf, "sametaste");
    job.setJarByClass(SameTasteJob.class);
    
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);
        
    job.setMapperClass(SameTasteMapper.class);
    job.setReducerClass(SameTasteReducer.class);
        
    job.setInputFormatClass(TextInputFormat.class);
    job.setOutputFormatClass(TextOutputFormat.class);
        
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
        
    job.waitForCompletion(true);
 }
        
}*/