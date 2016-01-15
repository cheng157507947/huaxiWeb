package com.zncxi.huaxi.util.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import org.wangzz.core.web.ServletUtils;

import com.zncxi.huaxi.domain.CollHisData;

/**
 * 田块历史数据execl视图
 * @author xiaoCheng
 *
 */
public class PumpHisDataViewExcel extends AbstractExcelView{

	
	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sceneName =  (String)model.get("sceneName");
		
		response.setContentType(ServletUtils.EXCEL_TYPE);
		ServletUtils.setFileDownloadHeader(response, sceneName+"数据.xls");
		
		String[] title1 = { "采集时间","水源地水位（米）","水泵出口压力（MPa）"};

		Sheet s = workbook.createSheet(sceneName+"数据导出");
		
		for (int i = 0; i <title1.length-1; i++) {
			s.setColumnWidth(i, 10 * 256);// 设置列的宽度
		}

		Row r = null;
		Cell c = null;
		CellStyle cTitleCellStyle = getTitleCellStyle(workbook);
		CellStyle cMenuCellStyle = getMenuCellStyle(workbook);
		CellStyle cOtherCellStyle = getOtherCellStyle(workbook);
		CellStyle blueCellStyle = getBlueCellStyle(workbook);
	    CellStyle numCellBlodStyle = getNum2Style(workbook);
	    CellStyle numCellStyle = getNum2Style2(workbook);
		CellStyle dateCellStyle=getDateStyle(workbook);

		s.addMergedRegion(new CellRangeAddress(0, 0, 0, 11));
		r = s.createRow(0);
		c = r.createCell(0);
		c.setCellValue(sceneName+"数据统计表");
		c.setCellStyle(cTitleCellStyle);
		for (int i = 1; i <= title1.length-1; i++) {
			r.createCell(i).setCellStyle(cOtherCellStyle);
		}
		
		s.addMergedRegion(new CellRangeAddress(1, 1, 0, 11));
		r = s.createRow(1);
		c = r.createCell(0);
		//c.setCellValue(queryStr);
		c.setCellValue("");
		c.setCellStyle(cOtherCellStyle);
		for (int j = 1; j <= title1.length-1; j++) {
			r.createCell(j).setCellStyle(cOtherCellStyle);
		}
		
		r = s.createRow(2);
		for (int m = 0; m <title1.length; m++) {
			c = r.createCell(m);
			c.setCellValue(title1[m].toString());
			c.setCellStyle(cMenuCellStyle);
		}

		int i = 3;
		int g = 3;
		Object ListObject = model.get("datas");
		if(ListObject!=null){
			List<CollHisData> datas =(List<CollHisData>) model.get("datas");
			for(CollHisData data: datas){
				r = s.createRow(i);
				i++;
				
				c = r.createCell(0);
				c.setCellStyle(dateCellStyle);
				c.getCellStyle().setVerticalAlignment(CellStyle.VERTICAL_CENTER);
				if(data.getCollTime()!=null){
					c.setCellValue(data.getCollTime());
				}
				
				c = r.createCell(1);
				c.setCellStyle(numCellStyle);
				c.getCellStyle().setVerticalAlignment(CellStyle.VERTICAL_CENTER);
				if(data.getData1()!=null){
					c.setCellValue(data.getData1());
				}
				
				c = r.createCell(2);
				c.setCellStyle(numCellStyle);
				c.getCellStyle().setVerticalAlignment(CellStyle.VERTICAL_CENTER);
				if(data.getData2()!=null){
					c.setCellValue(data.getData2());
				}
			}
		}
	}
	
	@SuppressWarnings("unused")
	private static CellStyle getCellStyle(Workbook workbook) {
		CellStyle cs = workbook.createCellStyle();
		Font f = workbook.createFont();
		f.setBoldweight(Font.BOLDWEIGHT_BOLD);
		cs.setFont(f);
		cs.setAlignment(CellStyle.ALIGN_CENTER);
		cs.setBorderTop(CellStyle.BORDER_THIN);
		cs.setBorderLeft(CellStyle.BORDER_THIN);
		cs.setBorderRight(CellStyle.BORDER_THIN);
		cs.setBorderBottom(CellStyle.BORDER_THIN);
		return cs;
	}

	private static CellStyle getTitleCellStyle(Workbook workbook) {
		CellStyle cTitleCellStyle = workbook.createCellStyle();
		Font fTitleFont = workbook.createFont();
		fTitleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		fTitleFont.setFontHeightInPoints((short) 18);
		fTitleFont.setFontName("宋体");
		cTitleCellStyle.setFont(fTitleFont);
		cTitleCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cTitleCellStyle.setBorderTop(CellStyle.BORDER_THIN);
		cTitleCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		cTitleCellStyle.setBorderRight(CellStyle.BORDER_THIN);
		cTitleCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		return cTitleCellStyle;
	}

	private static CellStyle getMenuCellStyle(Workbook workbook) {
		CellStyle cMenuCellStyle = workbook.createCellStyle();
		Font infoFont = workbook.createFont();
		infoFont.setFontName("宋体");
		cMenuCellStyle.setFont(infoFont);
		cMenuCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cMenuCellStyle.setVerticalAlignment(CellStyle.VERTICAL_TOP);
		cMenuCellStyle.setBorderTop(CellStyle.BORDER_THIN);
		cMenuCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		cMenuCellStyle.setBorderRight(CellStyle.BORDER_THIN);
		cMenuCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		cMenuCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		cMenuCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cMenuCellStyle.setWrapText(true);
		return cMenuCellStyle;
	}

	private static CellStyle getOtherCellStyle(Workbook workbook) {
		CellStyle cOtherCellStyle = workbook.createCellStyle();
		cOtherCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cOtherCellStyle.setBorderTop(CellStyle.BORDER_THIN);
		cOtherCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		cOtherCellStyle.setBorderRight(CellStyle.BORDER_THIN);
		cOtherCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		return cOtherCellStyle;
	}

	private static CellStyle getBlueCellStyle(Workbook workbook) {
		CellStyle blueCellStyle = workbook.createCellStyle();
		Font blueFont = workbook.createFont();
		blueFont.setFontName("宋体");
		blueFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		blueFont.setColor(IndexedColors.BLUE.getIndex());
		blueCellStyle.setAlignment(CellStyle.ALIGN_LEFT);
		blueCellStyle.setBorderTop(CellStyle.BORDER_THIN);
		blueCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		blueCellStyle.setBorderRight(CellStyle.BORDER_THIN);
		blueCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		blueCellStyle.setFont(blueFont);
		return blueCellStyle;
	}

	private static CellStyle getNum2Style(Workbook workbook) {
		CellStyle cs = workbook.createCellStyle();
		Font f = workbook.createFont();
		f.setBoldweight(Font.BOLDWEIGHT_BOLD);
		cs.setFont(f);
		cs.setAlignment(CellStyle.ALIGN_CENTER);
		cs.setBorderTop(CellStyle.BORDER_THIN);
		cs.setBorderLeft(CellStyle.BORDER_THIN);
		cs.setBorderRight(CellStyle.BORDER_THIN);
		cs.setBorderBottom(CellStyle.BORDER_THIN);
		DataFormat format = workbook.createDataFormat();
		cs.setDataFormat(format.getFormat("0.00")); // 两位小数
		return cs;
	}

	private static CellStyle getNum2Style2(Workbook workbook) {
		CellStyle cs = workbook.createCellStyle();
		Font f = workbook.createFont();
		cs.setFont(f);
		cs.setAlignment(CellStyle.ALIGN_CENTER);
		cs.setBorderTop(CellStyle.BORDER_THIN);
		cs.setBorderLeft(CellStyle.BORDER_THIN);
		cs.setBorderRight(CellStyle.BORDER_THIN);
		cs.setBorderBottom(CellStyle.BORDER_THIN);
		DataFormat format = workbook.createDataFormat();
		cs.setDataFormat(format.getFormat("0.00")); // 两位小数
		return cs;
	}
	
	private static CellStyle getDateStyle(Workbook workbook){
		CellStyle cs = workbook.createCellStyle();
		CreationHelper createHelper = workbook.getCreationHelper();
		cs.setAlignment(CellStyle.ALIGN_CENTER);
		cs.setBorderTop(CellStyle.BORDER_THIN);
		cs.setBorderLeft(CellStyle.BORDER_THIN);
		cs.setBorderRight(CellStyle.BORDER_THIN);
		cs.setBorderBottom(CellStyle.BORDER_THIN);
		cs.setDataFormat(
			        createHelper.createDataFormat().getFormat("m/d/yy"));
		return cs;
		
	}
}
