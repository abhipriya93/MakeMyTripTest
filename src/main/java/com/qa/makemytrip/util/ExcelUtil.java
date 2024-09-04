package com.qa.makemytrip.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

public class ExcelUtil {

	public static final String TEST_DATA_SHEET_NAME="C:\\Users\\abhij\\eclipse-workspace\\Flight\\src\\test\\resource\\testdata\\user_data.xlsx";
	private static Workbook book;
	private static Sheet sheet;
		
	public static Object[][] getTestData(String sheetName)
	{
		Object data[][]=null;
		
		try {
			FileInputStream ip=new FileInputStream(TEST_DATA_SHEET_NAME);
			book=WorkbookFactory.create(ip);
			sheet=book.getSheet(sheetName);
			data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			for(int i=0;i<sheet.getLastRowNum();i++)
			{
				for(int j=0;j<sheet.getRow(0).getLastCellNum();j++)
				{
					data[i][j]=sheet.getRow(i+1).getCell(j).toString();
				}
			}
			
		
		}
		catch(FileNotFoundException e)
		{
			
		}
		catch(InvalidFormatException e)
		{
			
		}
		catch(IOException e)
		{
			
		}
		
		return data;
	}
	
}
