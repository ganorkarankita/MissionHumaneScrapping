package automatation.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	static String path;

	public ExcelUtils(String path) {
		this.path = path;
	}

	public static int getRowCount(String sheetName) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);

		int rowCount = ws.getLastRowNum();
		System.out.println("No of Rows:" + rowCount);
		wb.close();
		fi.close();
		return rowCount;
	}

	public static int getCellCount(String sheetName, int rowNum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rowNum);

		int cellCount = row.getLastCellNum();
		System.out.println("No of column:" + cellCount);
		wb.close();
		fi.close();
		return cellCount;
	}

	public static String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rowNum);
		cell = row.getCell(colNum);
		String data;
		try {
			DataFormatter formatter = new DataFormatter();

			String celldata = formatter.formatCellValue(cell);

			return celldata;
		} catch (Exception e) {
			data = "";
		}
		wb.close();
		fi.close();
		return data;

	}

	public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
		File ExcelFile = new File(path);
		if (!ExcelFile.exists()) { // if excel file not exists then create new
			wb = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			wb.write(fo);
		}

		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);

		if (wb.getSheetIndex(sheetName) == -1) // if sheet not exists then create new sheet
			wb.createSheet(sheetName);
		ws = wb.getSheet(sheetName);

		if (ws.getRow(rowNum) == null)// if row not exists then create new row
			ws.createRow(rowNum);
		row = ws.getRow(rowNum);
		row = ws.getRow(rowNum);

		cell = row.createCell(colNum);
		cell.setCellValue(data);
		fo = new FileOutputStream(path);

		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}

}
