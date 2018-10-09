package Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import au.com.bytecode.opencsv.CSVReader;
/**
 * 
 * @author likaixun
 *
 */
public class ReadExcelData {
	public static XSSFSheet sheet;
	public static Map<String,String> testInputData=getAllTestData();
	
	
	public static void setPath(String firepath, String sheetname) {

		XSSFWorkbook workbook;
		try {
			FileInputStream fs = new FileInputStream(firepath);
			workbook = new XSSFWorkbook(fs);
			sheet = workbook.getSheet(sheetname);
		} catch (IOException e) {
			Log.error("Package utilly||Class ExcelData||Method setPath "
					+ e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * @param iTestCaseRow行
	 * @param column列
	 * @return 
	 */
	public static String getCellData(int iTestCaseRow, int column) {

		XSSFCell cell = sheet.getRow(iTestCaseRow).getCell(column);//根据行列找到数据（单元格）
		String cellvalue = null;
		switch (cell.getCellType()) {
		case XSSFCell.CELL_TYPE_NUMERIC:
			cellvalue = cell.getRawValue();
			break;
		case XSSFCell.CELL_TYPE_STRING:
			cellvalue = cell.getStringCellValue();
			break;
		case XSSFCell.CELL_TYPE_BLANK:
			cellvalue ="";
			break;
		default:
			Log.warn("Excel data type does not exist. Cell type is:"+cell.getCellType());
			break;
		}
		
	
      return cellvalue;
	}

	
	/**
	 * @param key
	 * @param column
	 * 获得行号
	 * @return
	 */
	public static int getRowContains(String key, int column) {
                  int i;
                  int rowCount=sheet.getLastRowNum();
                  for ( i = 1; i <rowCount; i++) {
					if(ReadExcelData.getCellData(i, column).equalsIgnoreCase(key)){
						break;
					}
					if(i>=rowCount){
						Log.error("[getRowContains]:Can not is find"+key);
						
					}
				}
		
      return i;
	}
	
	//可以读多列
	public static String getTestData(String key) {
		
       ReadExcelData.setPath(Contants.path+Contants.filename,Contants.sheetname);
      int rowNum=  ReadExcelData.getRowContains(key, Contants.keycolumn);//获得行号
      String cellValue=ReadExcelData.getCellData(rowNum, Contants.column);
      return cellValue;
}
	//只可以处理2列
	public static Map<String,String> getAllTestData() {
		Map<String,String> mapData=new HashMap<String,String>();
	       setPath(Contants.path+Contants.filename,Contants.sheetname);
	       int rowNumber=sheet.getPhysicalNumberOfRows();
	       XSSFCell cell2 = null;
	       XSSFCell cell1 = null;
	       for (int i = 1; i < rowNumber; i++) {
	    	   cell1=sheet.getRow(i).getCell(Contants.keycolumn);
	    	   if(cell1.getCellType()!=XSSFCell.CELL_TYPE_BLANK){
	    		   String keyValue=cell1.getStringCellValue();
	    		   String value;
	    		   cell2=sheet.getRow(i).getCell(Contants.column);
	    		   switch (cell2.getCellType()) {
	    			case XSSFCell.CELL_TYPE_NUMERIC:
	    				value = cell2.getRawValue().toString().trim().toLowerCase();
	    				mapData.put(keyValue, value);
	    				break;
	    			case XSSFCell.CELL_TYPE_STRING:
	    				value = cell2.getStringCellValue().toString().trim().toLowerCase();
	    				mapData.put(keyValue, value);
	    				break;
	    			case XSSFCell.CELL_TYPE_BLANK:
	    				value ="";
	    				mapData.put(keyValue, value);
	    				break;
	    			default:
	    				Log.warn("Excel data type does not exist. Cell type is:"+cell2.getCellType());
	    				break;
	    			}
	    	   }
			
		}
		return mapData;
	   
	}
	
	public static String getMapData(String skey) {
		
		
		return  testInputData.get(skey).toLowerCase();
		
	}
	//可以读多列
	public static List<String[]> getLocatorsFromObjectsFile(){
		CSVReader csvReader;
		List<String[]> lists =new ArrayList<>();
				
		try {
				csvReader = new CSVReader(new FileReader(Contants.path + Contants.csvFileName));
				lists = csvReader.readAll();
				csvReader.close();
			} catch (IOException e) {
				Log.error("Fail to get the web locators from ObjectRepository file. ");
				e.printStackTrace();
			} 
				
		return lists;
	}
	
	
	public static List<String[]> getLocatorsFromObjects(){
		CSVReader csvReader;
		List<String[]> lists =new ArrayList<>();
				
		try {
				csvReader = new CSVReader(new FileReader(Contants.path + Contants.ObjectFile));
				lists = csvReader.readAll();
				csvReader.close();
			} catch (IOException e) {
				Log.error("Fail to get the web locators from ObjectRepository file. ");
				e.printStackTrace();
			} 
				
		return lists;
	}
}
