package com.fengdidi.fdexcelimporter;

import org.apache.poi.ss.usermodel.Cell;

public class PlaceholderCellImportStrategy extends CellImportStrategy {
	public PlaceholderCellImportStrategy(){
		super();
		this.type = "placeholder";
	}
	
	public boolean valid(Cell cell){
		return true;
	}
}
