/**
 * 
 */
package org.mycompany.process;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Iterator;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.mycompany.entity.Data;
import org.mycompany.entity.StationData;
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
			String path[] = file.getParent().split("[\\\\/]",-1);
			boolean isStorage = false;
			for(String directoryName : path) {
				if(directoryName.equals("TNN")) {
					isStorage = true;
					exchange.getIn().setHeader("isStorage", isStorage);
				}
			}
			
			String fileName = file.getName();
			String fileNameArgs [] = fileName.split("_");
			ArrayList<Data> data = new ArrayList<>();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
			simpleDateFormat.applyLocalizedPattern("yyyyMMddHHmmss");
			exchange.getIn().setHeader("constructionName", "");
			boolean isException = false;
			Exception ex = null;
			if(fileName.endsWith(".txt")) {
				BufferedReader myReader = new BufferedReader(new FileReader(file));
				String line = myReader.readLine();
				
				try {
					while (line != null) {
						Data dataParam;
						if(isStorage) {
							dataParam = new StorageDataParam();
							dataParam.setConstructionName(fileNameArgs[1]);
							exchange.getIn().setHeader("constructionName", dataParam.getConstructionName());
						} else {
							dataParam = new StationData();
						}
					   
					    if(fileNameArgs[2].equals("predata")) {
					    	List<String> lineArgs =  Arrays.asList(line.split("\\s")).stream()
					    			.filter(e-> !e.equals("")).collect(Collectors.toList());
					    	dataParam.setParameterName(lineArgs.get(0));
					    	dataParam.setTime(simpleDateFormat.parse(lineArgs.get(1)));
					    	dataParam.setValue(Float.valueOf(lineArgs.get(2)));
					    	dataParam.setUnit(lineArgs.get(3));
					    	dataParam.setStatus(Boolean.TRUE);
					    	data.add(dataParam);
					    } else if(fileNameArgs[2].equals("obsdata")) {
					    	List<String> lineArgs =  Arrays.asList(line.split("\\s")).stream()
					    			.filter(e-> !e.equals("")).collect(Collectors.toList());
					    	dataParam.setParameterName(lineArgs.get(0));
					    	dataParam.setTime(simpleDateFormat.parse(lineArgs.get(3)));
					    	dataParam.setValue(Float.valueOf(lineArgs.get(5)));
					    	dataParam.setUnit(lineArgs.get(6));
					    	dataParam.setStatus(Boolean.TRUE);
					    	data.add(dataParam);
					    } else if(fileNameArgs.length == 3) {
					    	List<String> lineArgs =  Arrays.asList(line.split("\\s")).stream()
					    			.filter(e-> !e.equals("")).collect(Collectors.toList());
					    	dataParam.setStationCode(lineArgs.get(0));
					    	dataParam.setParameterName(lineArgs.get(1));
					    	dataParam.setTime(simpleDateFormat.parse(lineArgs.get(4)));
					    	dataParam.setValue(Float.valueOf(lineArgs.get(2)));
					    	dataParam.setUnit(lineArgs.get(3));
					    	dataParam.setDeviceStatus(lineArgs.get(5));
					    	dataParam.setStatus(Boolean.TRUE);
					    	data.add(dataParam);
					    } else if(fileNameArgs.length == 4) {
					    	List<String> lineArgs =  Arrays.asList(line.split("\\s")).stream()
					    			.filter(e-> !e.equals("")).collect(Collectors.toList());
					    	dataParam.setStationCode(fileNameArgs[2]);
					    	dataParam.setParameterName(lineArgs.get(0));
					    	dataParam.setTime(simpleDateFormat.parse(lineArgs.get(3)));
					    	dataParam.setValue(Float.valueOf(lineArgs.get(1)));
					    	dataParam.setUnit(lineArgs.get(2));
					    	dataParam.setDeviceStatus(lineArgs.get(4));
					    	dataParam.setStatus(Boolean.TRUE);
					    	data.add(dataParam);
					    } else if(fileNameArgs.length == 5) {
					    	List<String> lineArgs =  Arrays.asList(line.split("\\s")).stream()
					    			.filter(e-> !e.equals("")).collect(Collectors.toList());
					    	dataParam.setStationCode(fileNameArgs[2]);
					    	dataParam.setParameterName(fileNameArgs[3]);
					    	dataParam.setTime(simpleDateFormat.parse(lineArgs.get(0)));
					    	dataParam.setValue(Float.valueOf(lineArgs.get(1)));
					    	dataParam.setUnit(lineArgs.get(2));
					    	dataParam.setDeviceStatus(lineArgs.get(3));
					    	dataParam.setStatus(Boolean.TRUE);
					    	data.add(dataParam);
					    } 
					      line = myReader.readLine();
					}
				} catch (Exception e) {
					isException = true;
					ex = e;
				} finally {
					myReader.close();
				}
				
				

				
			} else if(fileName.endsWith(".xls") || fileName.endsWith(".xlsx")) {
				 // ?????c m???t file XSL.
			       FileInputStream inputStream = new FileInputStream(file);
			       try {
			    	   // ?????i t?????ng workbook cho file XSL.
				       HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

				 
				       // L???y ra sheet ?????u ti??n t??? workbook
				       HSSFSheet sheet = workbook.getSheetAt(0);

				 
				       // L???y ra Iterator cho t???t c??? c??c d??ng c???a sheet hi???n t???i.
				       Iterator<Row> rowIterator = sheet.iterator();
				       rowIterator.next();
				       while (rowIterator.hasNext()) {
				           Row row = rowIterator.next();
				           StorageDataParam dataParam = new StorageDataParam();
							dataParam.setConstructionName(fileNameArgs[1]);
							exchange.getIn().setHeader("constructionName", dataParam.getConstructionName());
				           // L???y Iterator cho t???t c??? c??c cell c???a d??ng hi???n t???i.
				           Iterator<Cell> cellIterator = row.cellIterator();
				           if(fileNameArgs[2].equals("predata")) {
				        	   Cell cell = cellIterator.next();
				        	   dataParam.setParameterName(cell.getStringCellValue());
				        	   cell = cellIterator.next();
						    	dataParam.setTime(simpleDateFormat.parse(cell.getStringCellValue()));
						    	cell = cellIterator.next();
						    	dataParam.setValue(Float.valueOf(String.valueOf(cell.getNumericCellValue())));
						    	cell = cellIterator.next();
						    	dataParam.setUnit(cell.getStringCellValue());
						    	dataParam.setStatus(Boolean.TRUE);
						    	data.add(dataParam);
				           } else {
					        	   Cell cell = cellIterator.next();
					        	   dataParam.setParameterName(cell.getStringCellValue());
					        	   cell = cellIterator.next();
					        	   cell = cellIterator.next();
					        	   cell = cellIterator.next();
					        	   dataParam.setTime(simpleDateFormat.parse(cell.getStringCellValue()));
					        	   cell = cellIterator.next();
					        	   cell = cellIterator.next();
							    	dataParam.setValue(Float.valueOf(String.valueOf(cell.getNumericCellValue())));
							    	cell = cellIterator.next();
							    	dataParam.setUnit(cell.getStringCellValue());
							    	dataParam.setStatus(Boolean.TRUE);
							    	data.add(dataParam);
				           }
				     
				          
				       }
			       } catch( Exception e) {
			    	   ex = e;
			    	   isException = true;
			       }
			      
			}
			exchange.getIn().setHeader("isException", isException);
			exchange.getMessage().setHeader("dataSave", data);
			if(isException) throw ex;
			

		
	}

}
