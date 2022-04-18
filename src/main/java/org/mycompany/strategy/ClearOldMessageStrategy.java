package org.mycompany.strategy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.apache.camel.Exchange;
import org.apache.camel.component.file.remote.RemoteFile;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.apache.commons.net.ftp.FTPClient;
import org.mycompany.entity.StorageDataParam;

public class ClearOldMessageStrategy implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		// TODO Auto-generated method stub

		if (newExchange == null) {
            return oldExchange;
        } 
		newExchange.getIn().setHeader("account", oldExchange.getIn().getHeader("account"));
        return newExchange;
	}

//	RemoteFile<?> files = newExchange.getMessage().getBody(RemoteFile.class);
//	newExchange.getOut().setHeader("finished", false);
//	if(files == null) {
//		newExchange.getOut().setHeader("finished", true);
//	} else if(!files.isDirectory()) {
//		String fileName = files.getFileName();
//		System.out.println("START Analysis: " + fileName);
//		ArrayList<StorageDataParam> storageDataList = oldExchange.getMessage().getBody(ArrayList.class);
//		if(storageDataList == null || !(storageDataList.get(0) instanceof StorageDataParam)) {
//			storageDataList = new ArrayList<>();
//		}
//		String [] fileNameArgs = fileName.substring(0, fileName.indexOf('.')-1).split("_");
//		if(fileNameArgs.length < 4) {
//			throw new RuntimeException("FILE name format Exception");
//		}
//		StorageDataParam data = new StorageDataParam();
//		data.setConstructionName(fileNameArgs[1]);
//		SimpleDateFormat simpleDate = new SimpleDateFormat();
//		simpleDate.applyPattern("yyyyMMddHHmmss");
//		try {
//			data.setTime(simpleDate.parse(fileNameArgs[3]));
//			storageDataList.add(data);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
//	    ObjectOutputStream oos;
//		try {
//			oos = new ObjectOutputStream(baos);
//			oos.writeObject(files.getFile());
//
//		    oos.flush();
//		    oos.close();
//
//		    InputStream is = new ByteArrayInputStream(baos.toByteArray());
//			newExchange.getMessage().setBody(is);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//
//	    
//		
//		newExchange.getMessage().setHeader("folderName", data.getConstructionName());
//		
//		newExchange.getMessage().setHeader("data", storageDataList);
}
