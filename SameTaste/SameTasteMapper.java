package SameTaste;

import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SameTasteMapper extends Mapper<LongWritable, Text, Text, Text> {
	private Text personName = new Text();
	private Text personTaste = new Text();
	public static final Log LOG = LogFactory.getLog(SameTasteMapper.class);

	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
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
