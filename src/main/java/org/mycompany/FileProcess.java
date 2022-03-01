/**
 * 
 */
package org.mycompany;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.remote.RemoteFile;
import org.apache.camel.impl.ProcessorEndpoint;

/**
 * @author USER
 *
 */
public class FileProcess  implements Processor  {

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		RemoteFile files = exchange.getMessage().getBody(RemoteFile.class);
		exchange.getMessage().setHeader("isFile", false);
		if(!files.isDirectory()) {
			System.out.println(files.getFileName());
			exchange.getMessage().setHeader("isFile", true);
		}
	}

}
