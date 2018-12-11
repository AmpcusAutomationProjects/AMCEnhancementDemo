package com.environmentalVariables;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead {
	
	
	
	XSSFWorkbook wb;
	XSSFSheet sheet1;
	
	public ExcelRead(String excelPath)
	
	{
		try {
			File f = new File(excelPath);
			
			FileInputStream fis = new FileInputStream(f);
			
			wb = new XSSFWorkbook(fis);
			
			
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			 
		}
	}
	
	public String getData(int sheetNumber,int row, int column)
	{
		sheet1 = wb.getSheetAt(sheetNumber);
		String data = sheet1.getRow(row).getCell(column).getStringCellValue();
		return data;
	}
	
	public int getRollCount(int sheetIndex)
	{
		int row = wb.getSheetAt(sheetIndex).getLastRowNum();
		
		row=row+1;
		
		return row;
	}

}


