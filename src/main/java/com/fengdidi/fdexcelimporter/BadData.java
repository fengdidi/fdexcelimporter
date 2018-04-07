package com.fengdidi.fdexcelimporter;

public class BadData {
	private int col;
	private int row;
	private String value;
	private int errorType;
	private String errorDes;
	
	public BadData(){
		
	}

	
	
	public BadData(int row, int col, String value, int errorType) {
		super();
		this.col = col;
		this.row = row;
		this.value = value;
		this.errorType = errorType;
	}



	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getErrorType() {
		return errorType;
	}

	public void setErrorType(int errorType) {
		this.errorType = errorType;
	}



	public String getErrorDes() {
		if(this.errorType == DataError.CELL_TYPE_MISMATCH){
			return "������������������������";
		}
		if(this.errorType == DataError.DATE_FORMAT_MISMATCH){
			return "���������������������";
		}
		if(this.errorType == DataError.NULL_BUT_NOT_NULLABLE){
			return "���������������";
		}
		if(this.errorType == DataError.NUMBER_FORMAT_MISMATCH){
			return "���������������������������";
		}
		if(this.errorType == DataError.STRING_LENGTH_MISMATCH){
			return "���������������������������������������";
		}
		
		
		return "Unkown Error";
	}



	public void setErrorDes(String errorDes) {
		this.errorDes = errorDes;
	}
	
	
}
