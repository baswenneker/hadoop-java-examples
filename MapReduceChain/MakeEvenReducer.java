package MapReduceChain;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class MakeEvenReducer extends
		Reducer<LongWritable, IntWritable, LongWritable, Text> {
	public static final Log LOG = LogFactory.getLog(MakeEvenReducer.class);

	@Override
	public void reduce(LongWritable key, Iterable<IntWritable> values,
			Context context) throws IOException, InterruptedException {

		LOG.info("LOG REDUCER CALLED " + key);
		
        String output = "";
		while (values.iterator().hasNext()) {
			output += values.iterator().next() + " ";
		}

		// Write the key:value pair to the context. Doing so will write the following to the output file: key<TAB>value
        // Note that when the key is null, the output file will not contain the key and <TAB>.
        context.write(null, new Text(output));
	}
}
