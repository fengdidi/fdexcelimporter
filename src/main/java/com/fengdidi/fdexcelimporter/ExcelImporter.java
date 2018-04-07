package com.fengdidi.fdexcelimporter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ExcelImporter {
	List<CellImportStrategy> css;
	String sheetName;
	int dataIndex;
	int sheetIndex;
	int rowValidColIndex;
	
	public ExcelImporter(File configFile) throws ConfigParseException{
		this.readXMLFile(configFile);
	}
	
	
	public void importExcel(InputStream is,
							RowAccepted rowAccepted,
							CellNotAcceptable cellNotAcceptable) throws ExcelParseException{
		
		HSSFWorkbook workbook;
		try {
			workbook = new HSSFWorkbook(is);
			HSSFSheet sheet;
			if(sheetName != null){
				sheet = workbook.getSheet(sheetName);
			}else{
				sheet = workbook.getSheetAt(this.sheetIndex);
			}
			
			if(sheet==null){
				throw new ExcelParseException("Cannot find the sheet named:"+this.sheetName);
			}
			
			if(sheet.getPhysicalNumberOfRows()<=this.dataIndex){
				throw new ExcelParseException("There is no data in this shit!");
			}
			
			Iterator<Row> rowIt = sheet.rowIterator();
			for(int i=0;i<this.dataIndex;i++){
				rowIt.next();
			}
			int rowIndex = dataIndex-1;
			while(rowIt.hasNext()){
				
				Row r = rowIt.next();
				rowIndex++;
				
				Cell validCell = r.getCell(rowValidColIndex);
				
				if(validCell == null || validCell.getCellType() == Cell.CELL_TYPE_BLANK)
					break;
			
				ExcelResultSet ers = new ExcelResultSet();
				for(int i=0;i<css.size();i++){
					CellImportStrategy cs = css.get(i);
					Cell cell = r.getCell(i);
					if(cs.valid(cell)){
						if(!cs.getType().equals("placeholder")){
							ers.addAttribute(cs.getColName(), cs.getValue(cell));
						}
						
						if(i==css.size()-1){
							rowAccepted.locationAndValue(rowIndex, ers);
						}
					}else{
						cellNotAcceptable.locationAndReason(rowIndex, i, cell, cs.getErrorType());
						break;
					}
				}
				
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new ExcelParseException("Cannot read the excel!");
		}
		
	}
	
	private void readXMLFile(File configFile) throws ConfigParseException{
		SAXReader reader = new SAXReader();
		css = new ArrayList<CellImportStrategy>();
		try {
			
			Document doc = reader.read(configFile);
			Node sheet = doc.selectSingleNode("//config/sheet");
			if(sheet == null){
				throw new ConfigParseException();
			}
			
			Attribute sheetNameAttr =  ((Element)sheet).attribute("name");
			Attribute sheetIndexAttr =  ((Element)sheet).attribute("sheet-index");
			if(sheetNameAttr == null && sheetIndexAttr == null){
				throw new ConfigParseException("You need to give a sheet name or sheet index");
			}
			
			if(sheetNameAttr!=null)
				sheetName = sheetNameAttr.getValue();
			
			if(sheetIndexAttr != null){
				this.sheetIndex = Integer.valueOf(sheetIndexAttr.getValue());
			}
			
			Attribute dataIndexAttr =  ((Element)sheet).attribute("data-index");
			if(dataIndexAttr == null){
				throw new ConfigParseException("You should give a data index");
			}
			
			dataIndex = Integer.valueOf(dataIndexAttr.getValue());
			
			Attribute rowValidColIndexAttr =  ((Element)sheet).attribute("row-valid-col-index");
			if(rowValidColIndexAttr == null){
				throw new ConfigParseException("You should give a row valid column index");
			}
			
			rowValidColIndex = Integer.valueOf(rowValidColIndexAttr.getValue());
			
			@SuppressWarnings("unchecked")
			List<Node> colNodes = doc.selectNodes("//config/sheet/column");
			for(Node colNode : colNodes){
				
				Attribute attr;
				
				attr =  ((Element)colNode).attribute("type");
				if(attr == null){
					throw new ConfigParseException();
				}
				String typeStr = attr.getValue();

				if(typeStr.equals("placeholder")){
					CellImportStrategy cs1 = getCellImportStrategy(null,null,typeStr,null);
					this.css.add(cs1);
				}else{
					
					attr=null;
					attr =  ((Element)colNode).attribute("name");
					if(attr == null){
						throw new ConfigParseException();
					}
					String nameStr = attr.getValue();
					attr=null;
					attr =  ((Element)colNode).attribute("nullable");
					if(attr == null){
						throw new ConfigParseException();
					}
					String nullStr = attr.getValue();
					attr=null;
					
					String dateFormatStr = null;
					
					if(typeStr.equals("date")){
						attr =  ((Element)colNode).attribute("date-format");
						if(attr==null){
							dateFormatStr = "yyyy-MM-dd";
						}else{
							dateFormatStr = attr.getValue();
						}
					}
					
					CellImportStrategy cs2 = getCellImportStrategy(nameStr,nullStr,typeStr,dateFormatStr);
					
					if(typeStr.equals("string")){
						StringCellImportStrategy scs2 = (StringCellImportStrategy)cs2;
						attr =  ((Element)colNode).attribute("length");
						if(attr!=null){
							scs2.setLength(attr.getValue());
						}
						attr=null;
						attr =  ((Element)colNode).attribute("compare-type");
						if(attr!=null){
							scs2.setCompareType(attr.getValue());
						}
					}
					
					this.css.add(cs2);
				}
				
			}
			
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new ConfigParseException();
		}
	}
	
	private CellImportStrategy getCellImportStrategy(
			String nameStr,
			String nullStr,
			String typeStr,
			String dateFormatStr){
		boolean nullable = Boolean.valueOf(nullStr);
		
		CellImportStrategy cs = CellImportStrategyFactory.getCellImportStrategy(typeStr, nameStr, nullable);
		if(cs.type.equals("date")){
			((DateCellImportStrategy)cs).setDateFormat(dateFormatStr);
		}
		
		return cs;
	}


	public int getDataIndex() {
		return dataIndex;
	}


	public void setDataIndex(int dataIndex) {
		this.dataIndex = dataIndex;
	}
	
	
}
