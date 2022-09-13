package org.mycompany.process;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class FirstFilePathProcess implements Processor   {

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		String filePath = (String) exchange.getMessage().getHeader("CamelFileParent");
		int index = filePath.indexOf('/', 1);
		if(index > 0) {
			exchange.getMessage().setHeader("folder", filePath.substring(0, index));
		} else {
			exchange.getMessage().setHeader("folder", filePath.substring(0));
		}
		
	}

}
