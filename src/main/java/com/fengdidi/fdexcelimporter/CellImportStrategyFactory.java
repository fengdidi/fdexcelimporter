package com.fengdidi.fdexcelimporter;

public class CellImportStrategyFactory {
	public static CellImportStrategy getCellImportStrategy(String type,
			String colName,boolean nullable){
		if(type.equals("date")){
			DateCellImportStrategy d = new DateCellImportStrategy();
			d.setNullable(nullable);
			d.setColName(colName);
			d.setType(type);
			return d;
		}else if(type.equals("string")){
			StringCellImportStrategy s = new StringCellImportStrategy();
			s.setColName(colName);
			s.setType(type);
			s.setNullable(nullable);
			return s;
		}else if(type.equals("number")){
			NumberCellImportStrategy n = new NumberCellImportStrategy();
			n.setColName(colName);
			n.setType(type);
			n.setNullable(nullable);
			return n;
		}else if(type.equals("integer")){
			IntegerCellImportStrategy i = new IntegerCellImportStrategy();
			i.setColName(colName);
			i.setType(type);
			i.setNullable(nullable);
			return i;
		}else {
			PlaceholderCellImportStrategy p = new PlaceholderCellImportStrategy();
			p.setType("placeholder");
			return p;
		}
	}
}
