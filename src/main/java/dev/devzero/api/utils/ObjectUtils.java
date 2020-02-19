/***********************************************************************
 * Module:  ObjectUtils.java
 * Author:  Mauricio Saca
 * Purpose: Defines the Class ObjectUtils
 ***********************************************************************/
package dev.devzero.api.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectUtils {

	private static final String DATE_PATTERN = "dd/MM/yyyy";

	public static String getFormattedDate(Date date) {
		return getFormattedDate(date, DATE_PATTERN);
	}

	public static String getFormattedDate(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

}
