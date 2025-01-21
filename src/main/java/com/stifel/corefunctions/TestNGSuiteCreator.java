/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package com.stifel.corefunctions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.stifel.corefunctions.*;


public class TestNGSuiteCreator {
	public void createSuiteXMLFile() throws URISyntaxException, IOException {
		try {
			GetDataFromRunsheet getData = new GetDataFromRunsheet();

			XmlSuite suite1 = new XmlSuite();
			suite1.setName(getData.getDetails("ProjectName"));
			String threadCount = getData.getDetails("Thread");
			suite1.setThreadCount(Integer.parseInt(threadCount));
			//suite1.setParallel("tests");
			String sheetName = getData.getDetails("SheetName");

			String testNGExcelPath = Utilities.getPathCommon()
					+ "\\TestMatrix.xlsx";
			testNGExcelPath = testNGExcelPath.replace("//", "/");
			List<RowRecord> rowRecords = getData.readExcel(testNGExcelPath, sheetName);
            
			List<XmlTest> Tests = new ArrayList<XmlTest>();

			for(int j =1; j<rowRecords.size();j++) {
				RowRecord record = rowRecords.get(j);
				if (record.getMyMap("RunStatus").equalsIgnoreCase("Yes")) {
					XmlTest test = new XmlTest(suite1);
					for (String colName : record.getData().keySet()) {
						if (colName.equalsIgnoreCase("ClassName")) {
							String classNames = record.getMyMap(colName);
							List xmlClass = new ArrayList();
							xmlClass.add(new XmlClass(classNames));
							test.setXmlClasses(xmlClass);
						} else if (colName.equalsIgnoreCase("Name")) {
							test.setName(record.getMyMap(colName));
						} else if (colName.equalsIgnoreCase("groupName")) {
							String groupName = record.getMyMap(colName);
							if ((!(groupName.isEmpty())) && (groupName != null)) {
								String[] groupNameList = groupName.split(",");
								List groupNameList1 = new ArrayList();
								for (int i = 0; i < groupNameList.length; ++i) {
									groupNameList1.add(groupNameList[i]);
								}
								test.setIncludedGroups(groupNameList1);
							}
						} else {
							if (colName.equalsIgnoreCase("RunStatus"))
								continue;
//							if (colName.equals("rallyTestCaseID")) {
//								if (StringUtils.isNotEmpty(record
//										.getMyMap(colName)))
//									test.addParameter("testCaseID",
//											record.getMyMap(colName));
//								else {
//									test.addParameter("testCaseID",
//											"Add Rally Test Case ID");
//								}
//							} 
							 if (StringUtils.isNotEmpty(record
									.getMyMap(colName))) {
								test.addParameter(colName,
										record.getMyMap(colName));
							}
						}
					}
					Tests.add(test);
				}
			}
			suite1.setTests(Tests);
			String path = Utilities.getPathCommon();
			File dirName = new File(path + "\\TestNGXML");
			String fileName = path + "\\TestNGXML\\" + sheetName + ".xml";
			if (!(dirName.exists())) {
				dirName.mkdirs();
			}
			File file = new File(fileName);
			FileWriter writer = new FileWriter(file);
			writer.write(suite1.toXml());
			writer.close();
			System.out.println("TestNG.xml file is generated in : \n" + path
					+ "\\TestNGXML\\" + sheetName + ".xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void runTestNGTest() {
		try {
			GetDataFromRunsheet getData = new GetDataFromRunsheet();

			TestListenerAdapter tla = new TestListenerAdapter();
			TestNG tng = new TestNG();

			String sheetName = System.getProperty("sheetName");
			String threadCount = System.getProperty("threadCount");
			XmlSuite suite = new XmlSuite();

			suite.setName(getData.getDetails("ProjectName"));
			if (StringUtils.isEmpty(threadCount)) {
				threadCount = getData.getDetails("Thread");
			}

			if (StringUtils.isEmpty(sheetName)) {
				sheetName = getData.getDetails("SheetName");
			}
			suite.setThreadCount(Integer.parseInt(threadCount));
			//suite.setParallel("tests");

			String testNGExcelPath = Utilities.getPathCommon()
					+ "/TestMatrix_TestNg.xls";
			testNGExcelPath = testNGExcelPath.replace("//", "/");
			List<RowRecord> rowRecords = getData.readExcel(testNGExcelPath, sheetName);

			List Tests = new ArrayList();

			for (RowRecord record : rowRecords) {
				if (record.getMyMap("RunStatus").equalsIgnoreCase("Yes")) {
					XmlTest test = new XmlTest(suite);
					for (String colName : record.getData().keySet()) {
						if (colName.equalsIgnoreCase("ClassName")) {
							String classNames = record.getMyMap(colName);
							List xmlClass = new ArrayList();
							xmlClass.add(new XmlClass(classNames));
							test.setXmlClasses(xmlClass);
						} else if (colName.equalsIgnoreCase("name")) {
							test.setName(record.getMyMap(colName));
						} else if (colName.equalsIgnoreCase("gropuName")) {
							String groupName = record.getMyMap(colName);
							if ((!(groupName.isEmpty())) && (groupName != null)) {
								String[] groupNameList = groupName.split(",");
								List groupNameList1 = new ArrayList();
								for (int i = 0; i < groupNameList.length; ++i) {
									groupNameList1.add(groupNameList[i]);
								}
								test.setIncludedGroups(groupNameList1);
							}
						} else {
							if (colName.equalsIgnoreCase("RunStatus"))
								continue;
							if (colName.equals("rallyTestCaseID")) {
								if (StringUtils.isNotEmpty(record
										.getMyMap(colName)))
									test.addParameter("testCaseID",
											record.getMyMap(colName));
								else {
									test.addParameter("testCaseID",
											"Add Rally Test Case ID");
								}
							} else if (StringUtils.isNotEmpty(record
									.getMyMap(colName))) {
								test.addParameter(colName,
										record.getMyMap(colName));
							}
						}
					}
					Tests.add(test);
				}
			}
			tng.setXmlSuites(Arrays.asList(new XmlSuite[]{suite}));
			suite.setTests(Tests);
			List mySuites = new ArrayList();
			mySuites.add(suite);
			tng.setXmlSuites(mySuites);
			String path = Utilities.getPathCommon();
			String file = path + "/TestNGXML/" + sheetName + ".xml";
			file = file.replace("//", "/");
			FileWriter writer = new FileWriter(file);
			writer.write(suite.toXml());
			writer.close();
			System.out.println("TestNG.xml file is generated in : \n" + path
					+ "\\TestNGXML\\" + sheetName + ".xml");
			tng.addListener(tla);
			tng.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}