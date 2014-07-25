//http://courses.coreservlets.com/Course-Materials/pdf/hadoop/04-MapRed-8-Workflows.pdf
//http://hadoop-course.googlecode.com/svn/trunk/Solutions/src/main/java/mapRed/workflows/JobControlWorkflow.java


package MapReduceChain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SortMapper extends
		Mapper<LongWritable, Text, LongWritable, IntWritable> {

	public static final Log LOG = LogFactory.getLog(SortMapper.class);

	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		String[] tokens = line.split(" ");
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        for(String token: tokens){
            list.add(Integer.parseInt(token));
        }
        Collections.sort(list);

        for(int i = 0; i < list.size(); i++){
            context.write(key, new IntWritable(list.get(i)));    
        }
	}
}
