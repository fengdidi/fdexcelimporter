package com.fengdidi.fdexcelimporter;

import java.util.*;


public class ExcelResultSet {
	HashMap<String,Object> data;
	
	public ExcelResultSet(){
		this.data = new HashMap<String,Object>();
	}
	
	public void addAttribute(String key,Object value){
		this.data.put(key, value);
	}
	
	public String getString(String key){
		return (String)this.data.get(key);
	}
	
	public Double getNumber(String key){
		return (Double)this.data.get(key);
	}
	
	public Integer getInteger(String key){
		return (Integer)this.data.get(key);
	}
	
	public Date getDate(String key){
		return (Date)this.data.get(key);
	}
	
	public int getPrimitiveInt(String key){
		return (int)this.getInteger(key);
	}
	
	public double getPrimitiveDouble(String key){
		return (double)this.getNumber(key);
	}
}
