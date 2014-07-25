package SameTaste;

import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SameTasteReducer extends Reducer<Text, Text, Text, Text> {
	public static final Log LOG = LogFactory.getLog(SameTasteReducer.class);

	// http://stackoverflow.com/questions/10956254/hadoop-reduce-function-is-not-excuted
	@Override
	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {

		// System.exit(0);
		// throw new IOException("TERROR");
		// return;
		LOG.info(" LOG REDUCER CALLED");

		String output = "";
		while (values.iterator().hasNext()) {
			output += values.iterator().next() + " ";
			// if(values.hasNext()){
			// output += ", ";
			// }
		}
		context.write(key, new Text(output));
	}
}
