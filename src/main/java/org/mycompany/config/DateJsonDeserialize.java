package org.mycompany.config;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;

public class DateJsonDeserialize  extends StdScalarDeserializer<Date> {
    /**
	 * 
	 */
	private static final String FORMAT_DATE_1 = "yyyyMMddhhmmss";
	private static final String FORMAT_DATE_2 = "yyyy-MM-dd hh:mm:ss";
	private static final long serialVersionUID = 1L;
	private static final SimpleDateFormat formatter1 = new SimpleDateFormat(FORMAT_DATE_1);
	private static final SimpleDateFormat formatter2 = new SimpleDateFormat(FORMAT_DATE_2);
    public DateJsonDeserialize() { super(Date.class); }

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws JsonMappingException {
        try {
    	String dateString = p.getText();
    	if(dateString.length() == FORMAT_DATE_1.length()) {
    		
				return formatter1.parse(dateString);
			
    	}
		return formatter2.parse(dateString); 
        } catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        throw new JsonMappingException( "time is not right pattern");
    }
}

