//package com.baswenneker;
package MapReduceChain;

import java.io.IOException;
import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
public class MapReduceChainJob extends Configured implements Tool {

	public static final Log LOG = LogFactory.getLog(MapReduceChainJob.class);
	
    @Override
	public int run(String[] args) throws Exception {
		Job job = getOrderIntakeJob(args[0], args[1]);
        LOG.info("######## RUN");
		return job.waitForCompletion(true) ? 0 : 1;
	}


	public Job getOrderIntakeJob(String inputPath, String outputPath) throws IOException{
		
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
		job.setJarByClass(OrderIntakeReducer.class);

		job.setOutputKeyClass(LongWritable.class);
		job.setOutputValueClass(List.class);

		job.setMapperClass(OrderIntakeMapper.class);
		job.setReducerClass(OrderIntakeReducer.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		job.setMapOutputKeyClass(LongWritable.class);
		job.setMapOutputValueClass(Text.class);

		FileInputFormat.addInputPath(job, new Path(inputPath));
		FileOutputFormat.setOutputPath(job, new Path(outputPath));
		
		return job;
	}


	public Job getMakeEvenJob(String inputPath, String outputPath) throws IOException{
		
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
		job.setJarByClass(MakeEvenReducer.class);

		job.setOutputKeyClass(LongWritable.class);
		job.setOutputValueClass(List.class);

		job.setMapperClass(MakeEvenMapper.class);
		job.setReducerClass(MakeEvenReducer.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		job.setMapOutputKeyClass(LongWritable.class);
		job.setMapOutputValueClass(IntWritable.class);

		FileInputFormat.addInputPath(job, new Path(inputPath));
		FileOutputFormat.setOutputPath(job, new Path(outputPath));
		
		return job;
	}
	
	public Job getSortJob(String inputPath, String outputPath) throws IOException{
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
		job.setJarByClass(SortReducer.class);

		job.setOutputKeyClass(LongWritable.class);
		job.setOutputValueClass(List.class);

		job.setMapperClass(SortMapper.class);
		job.setReducerClass(SortReducer.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		job.setMapOutputKeyClass(LongWritable.class);
		job.setMapOutputValueClass(IntWritable.class);

		FileInputFormat.addInputPath(job, new Path(inputPath));
		FileOutputFormat.setOutputPath(job, new Path(outputPath));
		
		return job;
	}
	
	/**
	 * Main method for starting the SameTaste MapReduce job.
	 * 
	 * @author Bas Wenneker
	 * @param String
	 *            [] args Hadoop arguments
	 **/
	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new Configuration(),
				new MapReduceChainJob(), args);
		System.exit(exitCode);
	}
}
