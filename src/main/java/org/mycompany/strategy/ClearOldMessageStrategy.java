package org.mycompany.strategy;

import org.apache.camel.Exchange;
import org.apache.camel.component.file.remote.RemoteFile;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class ClearOldMessageStrategy implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		// TODO Auto-generated method stub
		if (newExchange == null) {
            return oldExchange;
        } else {
        	RemoteFile files = newExchange.getMessage().getBody(RemoteFile.class);
        	if(files != null && !files.isDirectory()) {
    			System.out.println(files.getFileName());
    			newExchange.getMessage().setHeader("isFile", true);
    		}
        	
        }
        return newExchange;
	}
	


}
