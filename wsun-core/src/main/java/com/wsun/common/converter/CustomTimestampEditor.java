/*
 * File : CustomTimestampEditor.java
 * date : 2013年9月5日 下午8:23:41
 */
package com.wsun.common.converter;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.util.StringUtils;

/**
* Property editor for <code>java.sql.Timestamp</code>,<br>
* supporting a custom <code>java.text.DateFormat</code>.
* @author: dbyangguang
* @date: 2013年9月5日 下午8:23:41
* @version: 1.0
*/
public class CustomTimestampEditor extends PropertyEditorSupport {

	private final SimpleDateFormat dateFormat;

	private final boolean allowEmpty;

	private final int exactDateLength;

	public CustomTimestampEditor(SimpleDateFormat dateFormat, boolean allowEmpty) {
		this.dateFormat = dateFormat;
		this.allowEmpty = allowEmpty;
		this.exactDateLength = -1;
	}

	public CustomTimestampEditor(SimpleDateFormat dateFormat, boolean allowEmpty, int exactDateLength) {
		this.dateFormat = dateFormat;
		this.allowEmpty = allowEmpty;
		this.exactDateLength = exactDateLength;
	}

	public void setAsText(String text) throws IllegalArgumentException {
		if ((this.allowEmpty) && (!(StringUtils.hasText(text)))) {
			setValue(null);
		} else {
			if ((text != null) && (this.exactDateLength >= 0) && (text.length() != this.exactDateLength)) {
				throw new IllegalArgumentException("Could not parse date: it is not exactly" + this.exactDateLength
						+ "characters long");
			}
			try {
				setValue(new Timestamp(this.dateFormat.parse(text).getTime()));
			} catch (ParseException ex) {
				throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
			}
		}
	}

	public String getAsText() {
		Timestamp value = (Timestamp) getValue();
		return ((value != null) ? this.dateFormat.format(value) : "");
	}
}
