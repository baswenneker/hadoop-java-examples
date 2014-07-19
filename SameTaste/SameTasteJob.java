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
    

/**
 *
 *
 * @author Bas Wenneker
 * 
 **/
public class SameTasteJob extends Configured implements Tool{
        
	@Override
	public int run(String[] args) throws Exception{
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

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		    
	    return job.waitForCompletion(true) ? 0 : 1;
	}

    /**
     *  Main method for starting the SameTaste MapReduce job.
     *  @author Bas Wenneker
     *  @param String[] args    Hadoop arguments
     **/
    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new Configuration(), new SameTasteJob(), args);
        System.exit(exitCode);
    }        
}
