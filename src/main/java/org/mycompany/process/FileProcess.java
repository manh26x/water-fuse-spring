/**
 * 
 */
package org.mycompany.process;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.mycompany.entity.StorageDataParam;


/**
 * @author USER
 *
 */
public class FileProcess  implements Processor  {

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		File file = exchange.getMessage().getBody(File.class);
		String fileName = file.getName();
		String fileNameArgs [] = fileName.split("_");
		ArrayList<StorageDataParam> data = new ArrayList<>();
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		
		Scanner myReader = new Scanner(file);
		while (myReader.hasNextLine()) {
			StorageDataParam dataParam = new StorageDataParam();
			dataParam.setConstructionName(fileNameArgs[1]);
		    String line = myReader.nextLine();
		    if(fileNameArgs[2].equals("predata")) {
		    	simpleDateFormat.applyLocalizedPattern("yyyyMMddHHmmss");
		    	
		    	List<String> lineArgs =  Arrays.asList(line.split("\\s")).stream()
		    			.filter(e-> !e.equals("")).collect(Collectors.toList());
		    	dataParam.setParameterName(lineArgs.get(0));
		    	dataParam.setTime(simpleDateFormat.parse(lineArgs.get(1)));
		    	dataParam.setValue(Float.valueOf(lineArgs.get(2)));
		    	dataParam.setUnit(lineArgs.get(3));
		    	dataParam.setStatus(Boolean.TRUE);
		    	data.add(dataParam);
		    }
		    
		}
		myReader.close();

		exchange.getMessage().setHeader("dataSave", data);
	}

}
