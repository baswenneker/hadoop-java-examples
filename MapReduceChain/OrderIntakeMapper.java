package MapReduceChain;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class OrderIntakeMapper extends
		Mapper<LongWritable, Text, LongWritable, Text> {

	public static final Log LOG = LogFactory.getLog(OrderIntakeMapper.class);

	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		LOG.info("TEST ORDERINTAKEMAPPER");
        
        String[] order = value.toString().split(" ");
        
        LongWritable tableNumber = getTableNumber(context);
        Integer numberOfOrders = Integer.parseInt(order[2]);
        String foodOrBeverage = order[3];
        
        for(int i = 0; i < numberOfOrders; i++){
            context.write(tableNumber, new Text(foodOrBeverage));
        } 
    }

    public LongWritable getTableNumber(Context context){
        String fileName = ((FileSplit) context.getInputSplit()).getPath().getName();
        String tableNumber = fileName.substring(5, fileName.length());
        return new LongWritable(Integer.parseInt(tableNumber));
    }

}
