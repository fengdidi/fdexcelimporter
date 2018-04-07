package com.fengdidi.fdexcelimporter;

import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;

public class DateCellImportStrategy extends CellImportStrategy {
	
	private String dateFormat;
	
	public DateCellImportStrategy(){
		super();
		this.type = "date";
	}
	
	public boolean valid(Cell cell){
		
		if(cell == null){
			if(this.nullable){
				return true;
			}else{
				this.errorType = DataError.NULL_BUT_NOT_NULLABLE;
				return false;
			}
		}
		
		if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
			if(HSSFDateUtil.isCellDateFormatted(cell)){
				if(HSSFDateUtil.isValidExcelDate(cell.getNumericCellValue())){
					return true;
				}else{
					this.errorType = DataError.DATE_FORMAT_MISMATCH;
					return false;
				}
			}else{
				this.errorType = DataError.CELL_TYPE_MISMATCH;
				return false;
			}
			
		}else{
			this.errorType = DataError.CELL_TYPE_MISMATCH;
			return false;
		}
	
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	
	public Date getValue(Cell cell){
		if(cell != null){
			return cell.getDateCellValue();
		}else{
			return new Date();
		}
	}
	
	
}
