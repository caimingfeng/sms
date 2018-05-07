package com.sms.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


/**
 * 从EXCEL导入到数据库
 * 创建人：LRJ 创建时间：2014年12月23日
 * @version
 */
public class ObjectExcelRead {

	/**
	 * @param filepath //文件路径
	 * @param filename //文件名
	 * @param startrow //开始行号
	 * @param startcol //开始列号
	 * @param sheetnum //sheet
	 * @return list
	 */
	public static List<Object> readExcel(String filepath, String filename, int startrow, int startcol, int sheetnum) {
		List<Object> varList = new ArrayList<Object>();
		
		System.out.println("开始读取Excel文件数据.......");
		try {
			File target = new File(filepath, filename);
			FileInputStream fi = new FileInputStream(target);
			@SuppressWarnings("resource")
			HSSFWorkbook wb = new HSSFWorkbook(fi);
			HSSFSheet sheet = wb.getSheetAt(sheetnum); 					//sheet 从0开始
			int rowNum = sheet.getLastRowNum() + 1; 					//取得最后一行的行号

			for (int i = startrow; i < rowNum; i++) {					//行循环开始
				
				PageData varpd = new PageData();
				HSSFRow row = sheet.getRow(i); 							//行
				int cellNum = row.getLastCellNum(); 					//每行的最后一个单元格位置

				for (int j = startcol; j < cellNum; j++) {				//列循环开始
					
					@SuppressWarnings("deprecation")
					HSSFCell cell = row.getCell(Short.parseShort(j + ""));
					String cellValue = null;
					if (null != cell) {
						switch (cell.getCellType()) { 					// 判断excel单元格内容的格式，并对其进行转换，以便插入数据库
						case HSSFCell.CELL_TYPE_NUMERIC://读取的Excel单元格的格式是数字,并转换为字符串
							cellValue = String.valueOf((int) cell.getNumericCellValue());
							break;
						case HSSFCell.CELL_TYPE_STRING://文本
							cellValue = cell.getStringCellValue();
							break;
						case HSSFCell.CELL_TYPE_FORMULA://公式型
							cellValue = cell.getNumericCellValue() + "";
							// cellValue = String.valueOf(cell.getDateCellValue());
							break;
						case HSSFCell.CELL_TYPE_BLANK://空值
							cellValue = "";
							break;
						case HSSFCell.CELL_TYPE_BOOLEAN://布尔型
							cellValue = String.valueOf(cell.getBooleanCellValue());
							break;
						case HSSFCell.CELL_TYPE_ERROR://错误
							cellValue = String.valueOf(cell.getErrorCellValue());
							break;
						}
					} else {
						cellValue = "";
					}
					
					varpd.put("var"+j, cellValue);
				}
				varList.add(varpd);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		
		return varList;
	}
}
