package com.fengdidi.fdexcelimporter;

import org.apache.poi.ss.usermodel.Cell;

public class StringCellImportStrategy extends CellImportStrategy {
	
	private String length;
	private String compareType;
	
	public StringCellImportStrategy(){
		super();
		this.type="string";
	}
	
	public boolean valid(Cell cell){
		
		if(cell == null){
			if(this.nullable){
				return true;
			}else{
				this.errorType = DataError.NULL_BUT_NOT_NULLABLE;
				return false;
			}
		}
		
		if(cell.getCellType() == Cell.CELL_TYPE_STRING){
			if(this.nullable){
				return true;
			}else{
				String content = cell.getStringCellValue().trim();
				if(content!=null && !content.equals("")){
					if(this.length!=null){
						if(this.compareType!=null){
							if(this.compareType.equals("large")){
								if(Integer.valueOf(this.length) < content.length()){
									return true;
								}else{
									this.errorType = DataError.STRING_LENGTH_MISMATCH;
									return false;
								}
							}else if(this.compareType.equals("large-or-equal")){
								if(Integer.valueOf(this.length) <= content.length()){
									return true;
								}else{
									this.errorType = DataError.STRING_LENGTH_MISMATCH;
									return false;
								}
							}else if(this.compareType.equals("small")){
								if(Integer.valueOf(this.length) > content.length()){
									return true;
								}else{
									this.errorType = DataError.STRING_LENGTH_MISMATCH;
									return false;
								}
							}else if(this.compareType.equals("small-equal")){
								if(Integer.valueOf(this.length) >= content.length()){
									return true;
								}else{
									this.errorType = DataError.STRING_LENGTH_MISMATCH;
									return false;
								}
							}else if(this.compareType.equals("equal")){
								if(Integer.valueOf(this.length) == content.length()){
									return true;
								}else{
									this.errorType = DataError.STRING_LENGTH_MISMATCH;
									return false;
								}
							}else if(this.compareType.equals("not-equal")){
								if(Integer.valueOf(this.length) != content.length()){
									return true;
								}else{
									this.errorType = DataError.STRING_LENGTH_MISMATCH;
									return false;
								}
							}else{
								if(Integer.valueOf(this.length) == content.length()){
									return true;
								}else{
									this.errorType = DataError.STRING_LENGTH_MISMATCH;
									return false;
								}
							}
						}else{
							if( content.length() == Integer.valueOf(this.length) ){
								return true;
							}else{
								this.errorType = DataError.STRING_LENGTH_MISMATCH;
								return false;
							}
						}
					}else{
						return true;
					}
				}else{
					this.errorType = DataError.NULL_BUT_NOT_NULLABLE;
					return false;
				}
			}
		}else{
			this.errorType = DataError.CELL_TYPE_MISMATCH;
			return false;
		}
	}
	
	public String getValue(Cell cell){
		if(cell != null){
			return cell.getStringCellValue().trim();
		}else{
			return "";
		}
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getCompareType() {
		return compareType;
	}

	public void setCompareType(String compareType) {
		this.compareType = compareType;
	}
	
	
}
