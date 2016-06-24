package com.ourpalm.editor.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class DateTag extends TagSupport {

	private static final long serialVersionUID = 6464168398214506236L;

	private String value;

	@Override
	public int doStartTag() throws JspException {
		String s = "";
		try {
			long time = Long.valueOf(value);
			Date d = new Date(time);
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			s = dateformat.format(d);
		} catch (NumberFormatException e) {
		}
		try {
			pageContext.getOut().write(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}

	public void setValue(String value) {
		this.value = value;
	}

}