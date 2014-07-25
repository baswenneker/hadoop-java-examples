package MapReduceChain;

import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class OrderIntakeReducer extends
		Reducer<LongWritable, IntWritable, LongWritable, Text> {

	public static final Log LOG = LogFactory.getLog(OrderIntakeReducer.class);

	public void reduce(LongWritable tableNumber, Iterable<Text> values,
			Context context) throws IOException, InterruptedException {

		while (values.iterator().hasNext()) {
            context.write(tableNumber, new Text(values.iterator().next()));
		}
	}
}
