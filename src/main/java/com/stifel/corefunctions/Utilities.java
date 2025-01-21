package com.stifel.corefunctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.WebDriver;

/**
 * Desc:Utilities functions for External input Data Parameterisation This class
 * makes the Test Matrix External Data driven Created By :Core Automation team
 *
 */
public class Utilities {
	private final static Object LOCK = new Object();
	public static String pathInsideProject, path;
	public static WebDriver driver;

	/**
	 * 
	 * @param driver
	 */
	public void setDriver(WebDriver driver) {
		Utilities.driver = driver;
	}

	/**
	 * 
	 * @return
	 * @throws URISyntaxException
	 */
	public static String getPathCommon() throws URISyntaxException {
		pathInsideProject = new File("").getAbsolutePath();
		return pathInsideProject;
	}

	/**
	 * Desc : Drive the Excel Template for fetching the Test Data for Test
	 * Execution
	 * 
	 * @param sheetName
	 * @param parameter
	 *            ()
	 * @param rowNum
	 * @return
	 * @throws URISyntaxException
	 */
	public synchronized static String getParameterFromInputSheet(String sheetName, String parameter, int rowNum) {
		String value = null;
		FileInputStream file;

		String FileName = Browser_setup.getThreadDataSheetName();
		try {
			String path = getPathCommon();
			
			file = new FileInputStream(new File(path + "//Test Data//" + FileName));

			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheet(sheetName);
			int paramCol = -1;
			Iterator<Cell> cellIterator = sheet.getRow(0).cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				try {
					if (cell.getStringCellValue().equals(parameter)) {
						paramCol = cell.getColumnIndex();
					}
				} catch (Exception e) {
				}
			}
			try {
				value = sheet.getRow(rowNum).getCell(paramCol).getStringCellValue();
			} catch (Exception e) {
			}
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Please verify the Data sheet, and the path where it is saved are correct");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * Desc :Drive the Excel Template for fetching the Test Data for Test
	 * Execution
	 * 
	 * @param sheetName
	 * @param parameter
	 * @param rowNum
	 * @return {@code String} for each row in the Excel Data
	 */
//	public static String RetrieveHTMLPropertyFromSpread(String sheetName, String parameter, int rowNum) {
//
//		String value = null;
//		try {
//			String path = getPathCommon();
//			FileInputStream file = new FileInputStream(new File(path + "//Locater_Properties//Object Data.xls"));
//			HSSFWorkbook workbook = new HSSFWorkbook(file);
//			HSSFSheet sheet = workbook.getSheet(sheetName);
//			@SuppressWarnings("unused")
//			int rowCount = sheet.getLastRowNum();
//
//			int paramCol = -1;
//
//			Iterator<Cell> cellIterator = sheet.getRow(0).cellIterator();
//			while (cellIterator.hasNext()) {
//				Cell cell = cellIterator.next();
//				try {
//					if (cell.getStringCellValue().equals(parameter)) {
//						paramCol = cell.getColumnIndex();
//					}
//				} catch (Exception e) {
//					System.out.println("cellIterator Error:Getparameterfromdatasheet Exception ");
//				}
//			}
//			try {
//				value = sheet.getRow(rowNum).getCell(paramCol).getStringCellValue();
//			} catch (Exception e) {
//				try {
//					Double value1 = sheet.getRow(rowNum).getCell(paramCol).getNumericCellValue();
//					int value2 = value1.intValue();
//					value = String.valueOf(value2);
//				} catch (Exception e2) {
//					value = "Null";
//				}
//			}
//			file.close();
//			FileOutputStream out = new FileOutputStream(new File(path + "//Locater_Properties//Object Data.xls"));
//			workbook.write(out);
//			out.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (URISyntaxException e1) {
//			e1.printStackTrace();
//		}
//		return value;
//	}

	public static void SetParameterFromInputSheet(String sheetName, String parameter, int rowNum, String Value) {
		synchronized (LOCK) {
			try {
				String FileName = Browser_setup.getThreadDataSheetName();

				String path = Utilities.getPathCommon();
				FileInputStream file = new FileInputStream(new File(path + "//Test Data//" + FileName));

				HSSFWorkbook workbook = new HSSFWorkbook(file);
				HSSFSheet sheet = workbook.getSheet(sheetName);

				int paramCol = -1;
				Iterator<Cell> cellIterator = sheet.getRow(0).cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = (Cell) cellIterator.next();
					try {
						if (cell.getStringCellValue().equals(parameter)) {
							paramCol = cell.getColumnIndex();
						}
					} catch (Exception e) {
					}
				}
				try {
					HSSFRow row1 = sheet.getRow(rowNum);
					HSSFCell cellA1 = row1.createCell(paramCol);
					cellA1.setCellValue(Value);
				} catch (Exception e) {
				}

				FileOutputStream out = new FileOutputStream(new File(path + "//Test Data//" + FileName));

				workbook.write(out);
				out.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("Please verify the Data sheet, and the path where it is saved are correct");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
		}
	}
}
