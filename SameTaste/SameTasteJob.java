package SameTaste;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 *
 *
 * @author Bas Wenneker
 * 
 **/
public class SameTasteJob extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {
		Configuration conf = new Configuration();
		//Job job = new Job(conf, "sametaste");
		Job job = Job.getInstance(conf);
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
	 * Main method for starting the SameTaste MapReduce job.
	 * 
	 * @author Bas Wenneker
	 * @param String
	 *            [] args Hadoop arguments
	 **/
	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new Configuration(), new SameTasteJob(),
				args);
		System.exit(exitCode);
	}
}
