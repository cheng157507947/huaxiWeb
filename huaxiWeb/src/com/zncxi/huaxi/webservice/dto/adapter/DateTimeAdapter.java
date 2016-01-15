package com.zncxi.huaxi.webservice.dto.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateTimeAdapter extends XmlAdapter<String, Date>{

	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public Date unmarshal(String v) throws Exception {
		return format.parse(v);
	}

	@Override
	public String marshal(Date v) throws Exception {
		return format.format(v);
	}

}
