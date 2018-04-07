package com.fengdidi.fdexcelimporter;

public class ConfigParseException extends Exception {

	private static final long serialVersionUID = 1L;
	private String errorMsg;
	
	public ConfigParseException(){
		super();
		this.errorMsg = "The format of the XML config file is not acceptable!";
	}
	
	public ConfigParseException(String errorMsg){
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
