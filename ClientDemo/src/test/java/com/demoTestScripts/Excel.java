package com.demoTestScripts;

import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Font;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.environmentalVariables.ExcelRead;

public class Excel {

	public static void main(String[] args) {

		Workbook wb = new HSSFWorkbook();
		
		ExcelRead eRead = new ExcelRead("C:\\Users\\joseph.quansah\\Documents\\Project_Report.xls");
		
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
		
//		CellStyle style = wb.createCellStyle();
//		//style.setFillBackgroundColor(IndexedColors.BLUE.getIndex());
//		//style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//		
//		Font font = wb.createFont();
//		font.setColor(IndexedColors.BRIGHT_GREEN.getIndex());
//		font.setBold(font.getBold());
//		font.setItalic(true);
//		font.setFontHeightInPoints((short)16);
//		font.setUnderline(font.getUnderline());
//		
//		style.setFont(font);
//		
//		 
//		cell.setCellStyle(style);
//		cell.setCellValue("Bacon, Egg and Cheese");
		
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
		
		
//		cell2.setCellValue("+");
//		cell3.setCellValue(199);
//		cell4.setCellValue("=");
//		cell5.setCellFormula("B2+D2");
		
		try {
			FileOutputStream output = new FileOutputStream("Test1.xls");
			wb.write(output);
			output.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
