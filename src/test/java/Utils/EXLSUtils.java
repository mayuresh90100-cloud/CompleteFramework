package Utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EXLSUtils{
	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	public static List<List<String>> getAllData(String xlfile, String xlsheet) throws IOException {
	    List<List<String>> sheetData = new ArrayList<>();

	    try (FileInputStream fi = new FileInputStream(xlfile);
	         XSSFWorkbook wb = new XSSFWorkbook(fi)) {

	        XSSFSheet ws = wb.getSheet(xlsheet);
	        DataFormatter formatter = new DataFormatter();

	        for (Row row : ws) {
	            List<String> rowData = new ArrayList<>();
	            for (Cell cell : row) {
	                String cellValue = formatter.formatCellValue(cell);
	                rowData.add(cellValue);
	            }
	            sheetData.add(rowData);
	        }
	    }

	    return sheetData;
	}
	
	public static String getcellData(String xlfile, String xlsheet, int rownum, int colnum) throws IOException
	{
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.getCell(colnum);
		
		String data;
		try
		{
			DataFormatter formatter=new DataFormatter();
			data=formatter.formatCellValue(cell);
		}
		catch (Exception e)
		{
			data="";
		}
		
		wb.close();
		fi.close();
		return data;
		
	}
	
	public static void setCellData(String xlfile, String xlsheet, int rownum, int colnum, String data) throws IOException
	{
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(xlfile);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	
}