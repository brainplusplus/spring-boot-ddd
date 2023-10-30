package com.app.object;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;


public class StringToDateConverter implements org.apache.commons.beanutils.Converter{

	public final static String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
	public final static String DEFAULT_TIME_PATTERN = "HH:mm:ss";
	

	private DateFormat dateFormat;

	public StringToDateConverter() {
		this.dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
		this.dateFormat.setLenient(false);

	}

	public StringToDateConverter(String pattern) {
		this.dateFormat = new SimpleDateFormat(pattern);
		this.dateFormat.setLenient(false);
	}

	public StringToDateConverter(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	public Date convert(String source) {
		try {
			return dateFormat.parse(source);
		}
		catch (ParseException e) {
			String pattern;
			if (dateFormat instanceof SimpleDateFormat) {
				pattern = ((SimpleDateFormat) dateFormat).toPattern();
			}
			else {
				pattern = dateFormat.toString();
			}
			throw new IllegalArgumentException(e.getMessage() + ", format: [" + pattern + "]");
		}
	}

	@Override
	public <T> T convert(Class<T> type, Object value) {
		try {
			return (T) dateFormat.parse(value.toString());
		}
		catch (ParseException e) {
			String pattern;
			if (dateFormat instanceof SimpleDateFormat) {
				pattern = ((SimpleDateFormat) dateFormat).toPattern();
			}
			else {
				pattern = dateFormat.toString();
			}
			throw new IllegalArgumentException(e.getMessage() + ", format: [" + pattern + "]");
		}
	}

}