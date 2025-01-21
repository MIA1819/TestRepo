
package com.stifel.corefunctions.copy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;


import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GetDataFromRunsheet {
	public synchronized String getDetails(String key) {
		String propertyValue = null;
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream("details.properties");
			prop.load(input);
			propertyValue = prop.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return propertyValue;
	}

	public List<RowRecord> getRowRecord(List<RowRecord> rowRecords,
			String field, String fieldValue) {
		List rowRecordList = new ArrayList();

		for (RowRecord rowRecord : rowRecords) {
			if ((rowRecord.getMyMap(field) != null)
					&& (rowRecord.getMyMap(field).equalsIgnoreCase(fieldValue))) {
				rowRecordList.add(rowRecord);
			}
		}
		return rowRecordList;
	}

	public List<String> getColumnRecord(List<RowRecord> rowRecords, String field) {
		List rowRecordList = new ArrayList();

		for (RowRecord rowRecord : rowRecords) {
			if (rowRecord.getMyMap(field) != null) {
				rowRecordList.add(rowRecord.getMyMap(field));
			}
		}
		return rowRecordList;
	}

	public List<RowRecord> readExcel(String fileName, String tabName) {
		List columnNames = new ArrayList();
		List rowRecords = new ArrayList();
		try {
			FileInputStream file = new FileInputStream(new File(fileName));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet(tabName);

			Iterator rowIterator = sheet.iterator();
			int rowCount = 1;
			while (rowIterator.hasNext()) {
				Row row = (Row) rowIterator.next();
				Iterator cellIterator = row.cellIterator();
				if (rowCount == 1) {
					while (cellIterator.hasNext()) {
						Cell cell = (Cell) cellIterator.next();
						columnNames.add(cell.getStringCellValue());
					}
					++rowCount;
				}

				RowRecord rowRecord = new RowRecord();
				while (cellIterator.hasNext()) {
					Cell cell = (Cell) cellIterator.next();
					int index = cell.getColumnIndex();

					switch (cell.getCellType()) {
						case 3 :
							rowRecord.addToMap((String) columnNames.get(index),
									String.valueOf(cell.getStringCellValue()));
							break;
						case 4 :
							rowRecord.addToMap((String) columnNames.get(index),
									String.valueOf(cell.getBooleanCellValue()));
							break;
						case 0 :
							String val = String.valueOf(cell
									.getNumericCellValue());
							double d = Double.parseDouble(val);
							int i = (int) d;
							rowRecord.addToMap((String) columnNames.get(index),
									String.valueOf(i));
							break;
						case 1 :
							rowRecord.addToMap((String) columnNames.get(index),
									String.valueOf(cell.getStringCellValue()));
						case 2 :
							
					}
				}
				rowRecords.add(rowRecord);
			}
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowRecords;
	}
}