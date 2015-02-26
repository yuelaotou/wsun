/*
 * File : CustomDateSerializer.java
 * date : 2013年9月5日 下午9:29:00
 */
package com.wsun.common.converter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
* Spring mvc 返回JSON时，自动把日期转换成TimeStamp类型
* @author: dbyangguang
* @date: 2013年9月5日 下午9:29:00
* @version: 1.0
*/
public class CustomDateTimeSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
			JsonProcessingException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = formatter.format(value);
		jgen.writeString(formattedDate);
	}

}
