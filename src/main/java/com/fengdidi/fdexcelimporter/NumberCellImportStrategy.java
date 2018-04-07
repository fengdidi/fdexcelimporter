package com.fengdidi.fdexcelimporter;

import org.apache.poi.ss.usermodel.Cell;

public class NumberCellImportStrategy extends CellImportStrategy {
	public NumberCellImportStrategy(){
		super();
		this.type="number";
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
			return true;
		}else{
			this.errorType = DataError.CELL_TYPE_MISMATCH;
			return false;
		}
	}
	
	public Double getValue(Cell cell){
		if(cell != null){
			return cell.getNumericCellValue();
		}else{
			return 0.0;
		}
	}
}
