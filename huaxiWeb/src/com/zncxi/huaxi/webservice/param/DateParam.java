package com.zncxi.huaxi.webservice.param;

import java.text.ParseException;
import java.util.Date;

import javax.ws.rs.WebApplicationException;

import org.apache.commons.lang3.time.DateUtils;

public class DateParam extends AbstractParam<Date> {

private static final String[] formatStrings = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "yyyy-MM-dd HH", "yyyy-MM-dd HH:mm", "yyyyMMdd", "yyyyMMdd HH", "yyyyMMdd HH:mm", "yyyyMMdd HH:mm:ss", "yyyyMMddHH", "yyyyMMddHHmm", "yyyyMMddHHmmss","yyyyMMdd HHmmss"};
	
	public DateParam(String param) throws WebApplicationException {
		super(param);
	}

	@Override
	protected Date parse(String param) throws Throwable {
		Date d = new Date();
		try {
			d = DateUtils.parseDateStrictly(param, formatStrings);
		} catch (ParseException e) {
           //over
        }
		return d;
	}

}
