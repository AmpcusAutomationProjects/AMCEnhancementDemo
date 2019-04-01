package com.demoTestScripts;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelRW {
	private static Workbook wb;
	private static Sheet sh;
	private static FileInputStream fis;
	private static FileOutputStream fos;
	//private static Row row;
	//private static Cell cell;
	

	public static void main(String[] args) throws Exception {
		fis = new FileInputStream("C:\\Users\\joseph.quansah\\Documents\\Project_Report.xls");
		wb = WorkbookFactory.create(fis);
		sh = wb.getSheet("Sheet1");
		
		int rows = sh.getLastRowNum();

		 

		for (int i = 0; i < rows; i++) {
			System.out.println(sh.getRow(i).getCell(0));
			System.out.println(sh.getRow(i).getCell(1));
		}
		
		Sheet sheet = wb.createSheet("Final Results");
		
		Cell cell1 = sheet.createRow(2).createCell(3);
		Cell cell2 = sheet.createRow(2).createCell(4);
		Cell cell3 = sheet.createRow(2).createCell(5);
		Cell cell4 = sheet.createRow(2).createCell(6);
		Cell cell5 = sheet.createRow(2).createCell(7);
		Cell cell6 = sheet.createRow(2).createCell(7);
		
		Cell cell7 = sheet.createRow(3).createCell(2);
		Cell cell8 = sheet.createRow(3).createCell(3);
		Cell cell9 = sheet.createRow(3).createCell(4);
		Cell cell10 = sheet.createRow(3).createCell(5);
		Cell cell11 = sheet.createRow(3).createCell(6);
		Cell cell12 = sheet.createRow(3).createCell(7);
		Cell cell13 = sheet.createRow(3).createCell(8);
		
		Cell cell14 = sheet.createRow(4).createCell(3);
		Cell cell15 = sheet.createRow(4).createCell(4);
		
		
		
		cell1.setCellValue("TOTAL");
		cell2.setCellValue("PASS");
		cell3.setCellValue("FAIL");
		cell4.setCellValue("BLOCKED");
		cell5.setCellValue("IN PROGRESS");
		cell6.setCellValue("NO RUN");
		
		cell7.setCellValue("Smoke");
		cell8.setCellFormula("COUNTIFS(Execution!A2:A527,\"EXP\")");
		cell9.setCellFormula("COUNTIFS(Execution!A2:A527,\"EXP\", Execution!J2:J527,\"Completed\")");
		cell10.setCellFormula("COUNTIFS(Execution!A2:A527,\"EXP\", Execution!J2:J527,\"Failed\")");
		cell11.setCellFormula("COUNTIFS(Execution!A2:A527,\"EXP\", Execution!J2:J527,\"Blocked\")");
		cell12.setCellFormula("COUNTIFS(Execution!A2:A527,\"EXP\", Execution!J2:J527,\"In Progress\")");
		cell13.setCellFormula("COUNTIFS(Execution!A2:A527,\"EXP\", Execution!J2:J527,\"Not Executed\")");
		
		cell14.setCellValue("Percentage");
		cell15.setCellFormula("E4/D4");
		
		
		fos = new FileOutputStream("C:\\Users\\joseph.quansah\\Documents\\Project_Report.xls");
		wb.write(fos);
		fos.flush();
		fos.close();
		

	}

}
