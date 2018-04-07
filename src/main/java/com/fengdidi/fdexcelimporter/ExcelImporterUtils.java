package com.fengdidi.fdexcelimporter;

import org.apache.poi.ss.usermodel.Cell;

public class ExcelImporterUtils {
	public static String getCellString(Cell cell){
		if(cell == null){
			return "";
		}
		if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
			return String.valueOf(cell.getNumericCellValue());
		}
		if(cell.getCellType() == Cell.CELL_TYPE_STRING){
			return cell.getStringCellValue();
		}
		if(cell.getCellType() == Cell.CELL_TYPE_BLANK){
			return "";
		}
		if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){
			return String.valueOf(cell.getBooleanCellValue());
		}
		if(cell.getCellType() == Cell.CELL_TYPE_ERROR){
			return "未知";
		}
		if(cell.getCellType() == Cell.CELL_TYPE_FORMULA){
			return cell.getCellFormula();
		}
		return "未知";
	}
}
