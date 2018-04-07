package com.fengdidi.fdexcelimporter;

import org.apache.poi.ss.usermodel.Cell;

public class CellImportStrategy {
	protected boolean nullable;
	protected String type;
	protected String colName;
	
	protected int errorType = -1;
	
	public boolean valid(Cell cell){
		return false;
	}
	
	public Object getValue(Cell cell){
		return null;
	}
	
	public boolean isNullable() {
		return nullable;
	}
	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getColName() {
		return colName;
	}
	public void setColName(String colName) {
		this.colName = colName;
	}

	public int getErrorType() {
		return errorType;
	}

	public void setErrorType(int errorType) {
		this.errorType = errorType;
	}
	
}
