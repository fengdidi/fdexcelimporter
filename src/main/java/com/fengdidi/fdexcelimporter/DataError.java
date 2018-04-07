package com.fengdidi.fdexcelimporter;

public class DataError {
	private int colIndex;
	private int rowIndex;
	private int errorType;
	
	
	public static int NULL_BUT_NOT_NULLABLE = 0;
	public static int CELL_TYPE_MISMATCH = 1;
	public static int NUMBER_FORMAT_MISMATCH = 2;
	public static int DATE_FORMAT_MISMATCH = 3;
	public static int STRING_LENGTH_MISMATCH = 4;
	
	public DataError(int colIndex,int rowIndex,int errorType){
		this.colIndex = colIndex;
		this.rowIndex = rowIndex;
		this.errorType = errorType;
	}
	
	public int getColIndex() {
		return colIndex;
	}
	public void setColIndex(int colIndex) {
		this.colIndex = colIndex;
	}
	public int getRowIndex() {
		return rowIndex;
	}
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}
	public int getErrorType() {
		return errorType;
	}
	public void setErrorType(int errorType) {
		this.errorType = errorType;
	}
	
	
}	
