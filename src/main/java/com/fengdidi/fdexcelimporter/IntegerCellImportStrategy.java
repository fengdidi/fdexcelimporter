package com.fengdidi.fdexcelimporter;

import org.apache.poi.ss.usermodel.Cell;

public class IntegerCellImportStrategy extends CellImportStrategy {
	public IntegerCellImportStrategy(){
		super();
		this.type = "integer";
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
			double number = cell.getNumericCellValue();
			if(Math.floor(number)==number){
				return true;
			}else{
				this.errorType = DataError.NUMBER_FORMAT_MISMATCH;
				return false;
			}
		}else{
			this.errorType = DataError.CELL_TYPE_MISMATCH;
			return false;
		}
	}
	
	public Integer getValue(Cell cell){
		if(cell != null){
			return (int)cell.getNumericCellValue();
		}else{
			return 0;
		}
	}
}
