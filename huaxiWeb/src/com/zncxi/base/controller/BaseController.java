package com.zncxi.base.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class BaseController {

	@InitBinder
	public void initBinder(WebDataBinder binder) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CustomDateEditor dateEditor = new CustomDateEditor(df, true);

		DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor dateEditor2 = new CustomDateEditor(df2, true);

		binder.registerCustomEditor(Date.class, dateEditor);
		binder.registerCustomEditor(Date.class, dateEditor2);
	}
}
