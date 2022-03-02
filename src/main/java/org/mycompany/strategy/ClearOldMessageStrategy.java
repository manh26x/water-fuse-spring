package org.mycompany.strategy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.component.file.remote.RemoteFile;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.mycompany.entity.StorageData;

public class ClearOldMessageStrategy implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		// TODO Auto-generated method stub

		if (newExchange == null) {
            return oldExchange;
        } else {
        	RemoteFile<?> files = newExchange.getMessage().getBody(RemoteFile.class);
        	newExchange.getOut().setHeader("finished", false);
        	if(files == null) {
        		newExchange.getOut().setHeader("finished", true);
        	} else if(!files.isDirectory()) {
        		String fileName = files.getFileName();
    			System.out.println("START Analysis: " + fileName);
    			ArrayList<StorageData> storageDataList = oldExchange.getMessage().getBody(ArrayList.class);
    			if(storageDataList == null || !(storageDataList.get(0) instanceof StorageData)) {
    				storageDataList = new ArrayList<>();
    			}
    			String [] fileNameArgs = fileName.substring(0, fileName.indexOf('.')-1).split("_");
    			if(fileNameArgs.length < 4) {
    				throw new RuntimeException("FILE name format Exception");
    			}
    			StorageData data = new StorageData();
    			data.setConstructionName(fileNameArgs[1]);
    			SimpleDateFormat simpleDate = new SimpleDateFormat();
    			simpleDate.applyPattern("yyyyMMddHHmmss");
    			try {
					data.setTime(simpleDate.parse(fileNameArgs[3]));
					storageDataList.add(data);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			newExchange.getMessage().setHeader("data", storageDataList);
    			newExchange.getMessage().setBody(oldExchange.getMessage().getBody());
    		}
        	
        }
        return newExchange;
	}
	


}
