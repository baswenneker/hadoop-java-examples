package MapReduceChain;

import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MakeEvenMapper extends
		Mapper<LongWritable, Text, LongWritable, IntWritable> {

	public static final Log LOG = LogFactory.getLog(MakeEvenMapper.class);

	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		StringTokenizer tokenizer = new StringTokenizer(line, " ");
		Integer i = 0;
		while (tokenizer.hasMoreTokens()) {
			i = Integer.parseInt(tokenizer.nextToken());
			i += i % 2;
			LOG.info("LOOP " + i);
			context.write(key, new IntWritable(i));
		}
	}
}
