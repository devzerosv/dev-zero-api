/***********************************************************************
 * Module:  JsonDateSerializer.java
 * Author:  Mauricio Saca
 * Purpose: Defines the Class JsonDateSerializer
 ***********************************************************************/
package dev.devzero.api.utils;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonDateSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
			throws IOException, JsonProcessingException {

		gen.writeString(ObjectUtils.getFormattedDate(date));

	}

}