package com.fengdidi.fdexcelimporter;

import org.apache.poi.ss.usermodel.Cell;

public interface CellNotAcceptable {
	public void locationAndReason(int rowIndex,int colIndex,Cell cell,int errorType);
}
