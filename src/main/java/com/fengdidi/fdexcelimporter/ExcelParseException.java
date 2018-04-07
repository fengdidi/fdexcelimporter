package com.fengdidi.fdexcelimporter;

public class ExcelParseException extends Exception {

	
	private static final long serialVersionUID = -3927385615849407576L;

	private String errorMsg;
	
	public ExcelParseException(){
		super();
		this.errorMsg = "The format of the XML config file is not acceptable!";
	}
	
	public ExcelParseException(String errorMsg){
		super();
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
