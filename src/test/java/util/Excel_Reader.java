package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excel_Reader {
	private static FileInputStream file = null;
	private static Workbook book;
	private static Sheet sheet;
	
	
	private static Workbook loadworkbook(String path) {
		try {
			file = new FileInputStream(path);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException a) {
			a.printStackTrace();
		}
		try {
		book= WorkbookFactory.create(file);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return book;
	}
	
	public static Object[][] readwholedata(String Sheetname){
		String filepath="C:\\\\Users\\\\rajwi\\\\eclipse-workspace\\\\Walmart\\\\src\\\\test\\\\java\\\\test_data\\\\data1.xlsx";
		Workbook book = loadworkbook(filepath);
		sheet =book.getSheet(Sheetname);
		 Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		 
		 for (int i=0;i<sheet.getLastRowNum();i++) {
			 for(int j=0;j<sheet.getRow(0).getLastCellNum();j++)
			 {
					 data[i][j]= sheet.getRow(i+1).getCell(j).toString();
				 }
		 }

		return data;
		}
	
	public static Object[] Row_Values(String sheetname, int columnnum) {
		String Filepath ="C:\\Users\\rajwi\\eclipse-workspace\\Walmart\\src\\test\\java\\test_data\\data.xlsx";
		Workbook book = loadworkbook(Filepath);
		sheet= book.getSheet(sheetname);
		
		Object[] obj = new Object[sheet.getLastRowNum()];
		
		for(int i=0;i<sheet.getLastRowNum();i++) {
			obj[i]=sheet.getRow(i+1).getCell(columnnum).toString();
		}
		return obj;
		
	}
}
