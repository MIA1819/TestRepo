
package com.stifel.corefunctions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.apache.commons.lang.StringUtils;

public class ReportGenerator {
	private static final Object LOCK = new Object();
	public int deffectCount;
	public int passCount;
	public int stepno;
	public int step_no;
	public String FILENAME;
	public static final String pathCommon = new File("").getAbsolutePath();

	public static final String folderName = "Result_"
			+ new SimpleDateFormat("dd-MM-yyyy_h-mm-a").format(Calendar
					.getInstance().getTime());
	final String executionTime;
	final String executionDate;
	public static final String FOLDER_PATH = pathCommon + "//result//";
	public String DESTINATION;
	public int val;
	public String SOURCE_FOLDER;
	public long test_step_unique_id;
	public long screenshot_name;
	public long screenshot_html_name;
	public String step_desc;
	public String ExpectedRes;
	public String Actual;
	public String txtDocLogs;
	public int countLogs;
	private String folderPathForReport;
	private int passStp;
	private int failStp;
	private Map<String, String> data;
	private String folderNameForReport;

	public ReportGenerator() {
		this.deffectCount = 0;
		this.passCount = 0;

		this.FILENAME = "result.html";

		this.executionTime = new SimpleDateFormat("h:mm a").format(Calendar
				.getInstance().getTime());
		this.executionDate = new SimpleDateFormat("dd-MMM-yyyy")
				.format(Calendar.getInstance().getTime());

		this.DESTINATION = pathCommon + "//result//";
		this.val = 1;
		this.SOURCE_FOLDER = FOLDER_PATH + folderName;

		this.screenshot_name = 0L;
		this.screenshot_html_name = 0L;

		this.txtDocLogs = "";
		this.countLogs = 0;

		this.passStp = 0;
		this.failStp = 0;

		this.data = new HashMap();
	}

	public void setValue(String key, String value) {
		this.data.put(key, value);
	}

	public String getValue(String key) {
		return ((String) this.data.get(key));
	}

	public int getFailStp() {
		return this.failStp;
	}

	public void setFailStp(int failStp) {
		this.failStp = failStp;
	}

	public int getPassStp() {
		return this.passStp;
	}

	public void setPassStp(int passStp) {
		this.passStp = passStp;
	}

	public String getFolderPathForReport() {
		return this.folderPathForReport;
	}

	public void setFolderPathForReport(String folderPathForReport) {
		this.folderPathForReport = folderPathForReport;
	}

	public String getFolderNameForReport() {
		return this.folderNameForReport;
	}

	public void setFolderNameForReport(String folderNameForReport) {
		this.folderNameForReport = folderNameForReport;
	}

	public String writeReportHeader(String project_name,
			String application_name, String release_name, String folder,
			String resultHead) throws Exception {
		synchronized (LOCK) {
			this.stepno = 0;
			this.SOURCE_FOLDER = this.SOURCE_FOLDER + "//" + resultHead + "/";
			this.val += 1;
			String folder1 = folder;
			setFolderNameForReport(folder);
			setFolderPathForReport(this.SOURCE_FOLDER + folder);
			folder = folder + "_";
			this.txtDocLogs = resultHead;
			try {
				File stockdir = new File(this.SOURCE_FOLDER);
				File stockFile = new File(this.SOURCE_FOLDER + folder
						+ this.FILENAME);
				if (!(stockdir.exists())) {
					stockdir.mkdirs();
					stockFile.createNewFile();
				} else {
					String copyFolder = this.SOURCE_FOLDER + folder;
					File Sourcefolder1 = new File(copyFolder);
					File Destnationfolder1 = new File(this.DESTINATION);
					copyFolder(Sourcefolder1, Destnationfolder1);
				}

				FileWriter fw = new FileWriter(stockFile.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);

				bw.write("<html>");

				bw.write("<head>");

				bw.write("<meta http-equiv=Content-Languagecontent=en-us>");

				bw.write("<meta http-equiv=Content-Typecontent=text/html; charset=windows-1252>");

				bw.write("<title>" + project_name + "_" + application_name
						+ "</title>");

				bw.write("<style type=text/css>");
				bw.write("body {background-color:##D7DF01}");
				bw.write("td {colspan=2;valign=top;border: 1pt solid rgb(120, 192, 212);border-style: none solid solid; border-color: -moz-use-text-color rgb(120, 192, 212) rgb(120, 192, 212); border-width: medium 1pt 1pt; padding: 0in 5.4pt; background: rgb(165, 213, 226) none repeat scroll 0% 0%;  -moz-background-clip: border; -moz-background-origin: padding; -moz-background-inline-policy: continuous;font-size:9.0pt;color:#17365D}");

				bw.write("hr{color:#17365D}");
				bw.write("th{border: 1pt solid rgb(120, 192, 212); padding: 0in 5.4pt; background: rgb(210, 234, 241) none repeat scroll 0% 0%;  -moz-background-clip: border; -moz-background-origin: padding; -moz-background-inline-policy: continuous;font-size:9.0pt;font-weight:bold}");

				bw.write("table{border=1;cellspacing=0;cellpadding=0;border-collapse:collapse;border:none;mso-border-alt:solid #78C0D4 1.0pt;mso-border-themecolor:accent5;mso-border-themetint:191;mso-yfti-tbllook:1184; mso-padding-alt:0in 5.4pt 0in 5.4pt}");

				bw.write("</style>");

				bw.write("</head>");

				bw.write("<body>");

				bw.write("<table width=100% border=1px><tr border=1><th>");

				bw.write("<h2><em  align=centre style='color:red;font-family:'Times new roman','serif';font-size:26pt;letter-spacing:0.25pt;'>Project Name :"
						+ project_name + "</em></h2></th></tr></table>");

				bw.write("<hr / style='color:white;'><table width=100% border=1px> <tr height=50 border=1 style='color:#17365D;font-family:'Times new roman','serif';font-size:30pt;letter-spacing:0.25pt;'>");

				bw.write("<th border=1><b align=right  style='color:#17365D;font-family:'Times new roman','serif';font-size:15pt;letter-spacing:0.25pt;'>Application Name :"
						+ application_name + "<br><b></th>");

				bw.write("<th border=1><b  align=right  style='color:#17365D;font-family:'Times new roman','serif';font-size:15pt;letter-spacing:0.25pt;'>Release Name :"
						+ release_name + "<br></b></th>");

				bw.write("<th border=1><b style='color:#17365D;font-family:'Times new roman','serif';font-size:13pt;letter-spacing:0.25pt;align:right'>Test Run Date :"
						+ this.executionDate + "<br></b></th>");

				bw.write("<th border=1><b style='color:#17365D;font-family:'Times new roman','serif';font-size:13pt;letter-spacing:0.25pt;align:right'>Test Run Time :"
						+ this.executionTime + "<br></b></th></tr></table>");

				bw.write("<hr / style='color:white;'> ");
				bw.newLine();
				bw.close();
				writeHtmlLinebreak(folder);
				writeTestStepHeader(folder);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return folder1;
		}
	}

	public synchronized String getFolderName() {
		return folderName.split("/")[0];
	}

	public void writeHtmlLinebreak(String folder) throws Exception {
		synchronized (LOCK) {
			String folderNameForHTMLFile = getFolderPathForReport();
			folderNameForHTMLFile = folderNameForHTMLFile.replaceAll("//", "/");
			OutputStreamWriter writer = new OutputStreamWriter(
					new FileOutputStream(folderNameForHTMLFile + "_"
							+ this.FILENAME, true), "UTF-8");

			BufferedWriter bw = new BufferedWriter(writer);
			bw.write("<br>");
			bw.write("<br>");
			bw.newLine();
			bw.close();
		}
	}

	public void writeTestStepHeader(String folder) throws Exception {
		synchronized (LOCK) {
			String folderNameForHTMLFile = getFolderPathForReport();
			folderNameForHTMLFile = folderNameForHTMLFile.replaceAll("//", "/");
			OutputStreamWriter writer = new OutputStreamWriter(
					new FileOutputStream(folderNameForHTMLFile + "_"
							+ this.FILENAME, true), "UTF-8");

			BufferedWriter bw = new BufferedWriter(writer);
			bw.write("<table width=100% border=1px>");
			bw.write("<tr>");
			bw.write("<th width=5%>Step No</th>");
			bw.write("<th width=20%>Step Description</th>");
			bw.write("<th width=20%>Expected Result</th>");
			bw.write("<th width=20%>Actual Result</th>");
			bw.write("<th width=5%>Result</th>");
			bw.write("<th width=5%>Screenshot</th>");
			bw.write("</tr>");
			bw.write("</table>");
			bw.newLine();
			bw.close();
		}
	}

	public synchronized void writeReportPass(String stepdesc, String Expected,
			String strActRslt, long test_step_unique_id, String folder)
			throws Exception {
		this.passCount += 1;
		this.stepno += 1;
		this.screenshot_name = test_step_unique_id;
		this.step_no = this.stepno;
		this.step_desc = stepdesc;
		this.ExpectedRes = Expected;
		this.Actual = strActRslt;
		this.screenshot_html_name = test_step_unique_id;
		setPassStp(getPassStp() + 1);
		writeTestStepRowPass(this.step_no, this.step_desc, this.ExpectedRes,
				this.Actual, this.screenshot_html_name, folder);
	}

	public synchronized void ReportPass(WebDriver driver, String stepdesc,
			String Expected, String strActRslt, String folder) throws Exception {
		long tetStepUniqueID = 0L;
		this.passCount += 1;
		this.stepno += 1;
		String ScreenShotStatus = "No";
		GetDataFromRunsheet getData = new GetDataFromRunsheet();
	
		if (StringUtils.equalsIgnoreCase(getData.getDetails("TakeScreenShotForPassedScripts"), "Yes")) {
			tetStepUniqueID = takeScreenshot(driver, folder);
			ScreenShotStatus = "Yes";
		} else {
			ScreenShotStatus = "No";
		}
		this.screenshot_name = tetStepUniqueID;
		this.step_no = this.stepno;
		this.step_desc = stepdesc;
		this.ExpectedRes = Expected;
		this.Actual = strActRslt;
		this.screenshot_html_name = tetStepUniqueID;
		setPassStp(getPassStp() + 1);
		writeTestStepRowPass(this.step_no, this.step_desc, this.ExpectedRes,
				this.Actual, this.screenshot_html_name, ScreenShotStatus);
	}

	public void writeTestStepRowPass(int step_no, String step_desc,
			String ExpectedRes, String Actual, long screenshot_html_name,
			String ScreenShotStatus) throws Exception {
		synchronized (LOCK) {
			String folderNameforScreenshot = getFolderNameForReport();
			String folderNameForHTMLFile = getFolderPathForReport();
			folderNameForHTMLFile = folderNameForHTMLFile.replaceAll("//", "/");
			OutputStreamWriter writer = new OutputStreamWriter(
					new FileOutputStream(folderNameForHTMLFile + "_"
							+ this.FILENAME, true), "UTF-8");

			BufferedWriter bw = new BufferedWriter(writer);
			bw.write("<table width=100% border=1px>");
			bw.write("<tr>");
			bw.write("<td width=5%>Step" + step_no + "</td>");
			bw.write("<td width=20%>" + step_desc + "</td>");
			bw.write("<td width=20%>" + ExpectedRes + "</td>");
			bw.write("<td width=20%>" + Actual + "</td>");
			bw.write("<td width=5% height=100 style=color:green;font-weight:bold>Pass</td>");
			if (StringUtils.equalsIgnoreCase(ScreenShotStatus, "Yes")) {
				bw.write("<td width=5%><a href='" + folderNameforScreenshot
						+ "/" + screenshot_html_name + ".png" + "'"
						+ "target=_self>Screenshot</a></td>");
			} else {
				bw.write("<td width=5%><a>Screenshot</a></td>");
			}
			bw.write("</tr>");
			bw.write("</table>");
			bw.close();
		}
	}

	public String writeSubHeader(String heading, String folder)
			throws Exception {
		synchronized (LOCK) {
			String folderNameForHTMLFile = getFolderPathForReport();
			folderNameForHTMLFile = folderNameForHTMLFile.replaceAll("//", "/");
			OutputStreamWriter writer = new OutputStreamWriter(
					new FileOutputStream(folderNameForHTMLFile + "_"
							+ this.FILENAME, true), "UTF-8");

			BufferedWriter bw = new BufferedWriter(writer);
			bw.write("<br></br>");
			bw.write("<table width=100% border=1px align=center>");
			bw.write("<tr>");
			bw.write("<td width=20% align=center><font size=3px color=blue>"
					+ heading + "</font></td>");
			bw.write("</tr>");
			bw.write("</table>");
			bw.close();
			return this.SOURCE_FOLDER + folder;
		}
	}

	public String writeSubHeaderForScripts(String heading, String folder)
			throws Exception {
		synchronized (LOCK) {
			String folderNameForHTMLFile = getFolderPathForReport();
			folderNameForHTMLFile = folderNameForHTMLFile.replaceAll("//", "/");
			OutputStreamWriter writer = new OutputStreamWriter(
					new FileOutputStream(folderNameForHTMLFile + "_"
							+ this.FILENAME, true), "UTF-8");

			BufferedWriter bw = new BufferedWriter(writer);
			bw.write("<br></br>");
			bw.write("<table width=100% border=1px align=center>");
			bw.write("<tr id='Test_Case'>");
			bw.write("<td width=20% align=left><font size=3px color=blue>Test Case Name</font></td>");
			bw.write("<td width=20% align=center><font size=3px color=blue>"
					+ heading + "</font></td>");
			bw.write("</tr>");
			bw.write("</table>");
			bw.close();
			return this.SOURCE_FOLDER + folder;
		}
	}

	public synchronized void writeReportFail(String stepdesc, String Expected,
			String strActRslt, long test_step_unique_id, String folder)
			throws Exception {
		this.deffectCount += 1;
		this.stepno += 1;
		this.screenshot_name = test_step_unique_id;
		this.step_no = this.stepno;
		this.step_desc = stepdesc;
		this.ExpectedRes = Expected;
		this.Actual = strActRslt;
		this.screenshot_html_name = test_step_unique_id;
		setFailStp(getFailStp() + 1);
		writeTestStepRowFail(this.step_no, this.step_desc, this.ExpectedRes,
				this.Actual, this.screenshot_html_name, folder);
	}

	public synchronized void ReportFail(WebDriver driver, String stepdesc,
			String Expected, String strActRslt, String folder) throws Exception {
		long tetStepUniqueID = 0L;
		this.deffectCount += 1;
		this.stepno += 1;
		String ScreenShotStatus = "No";
		GetDataFromRunsheet getData = new GetDataFromRunsheet();
		if (StringUtils.equalsIgnoreCase(
				getData.getDetails("TakeScreenShotForFailedScripts"), "Yes")) {
			tetStepUniqueID = takeScreenshot(driver, folder);
			ScreenShotStatus = "Yes";
		} else {
			ScreenShotStatus = "No";
		}
		this.screenshot_name = tetStepUniqueID;
		this.step_no = this.stepno;
		this.step_desc = stepdesc;
		this.ExpectedRes = Expected;
		this.Actual = strActRslt;
		this.screenshot_html_name = tetStepUniqueID;
		setFailStp(getFailStp() + 1);
		writeTestStepRowFail(this.step_no, this.step_desc, this.ExpectedRes,
				this.Actual, this.screenshot_html_name, ScreenShotStatus);
	}

	public void writeTestStepRowFail(int step_no, String step_desc,
			String ExpectedRes, String Actual, long screenshot_html_name,
			String ScreenShotStatus) throws Exception {
		synchronized (LOCK) {
			String folderNameforScreenshot = getFolderNameForReport();
			String folderNameForHTMLFile = getFolderPathForReport();
			folderNameForHTMLFile = folderNameForHTMLFile.replaceAll("//", "/");
			OutputStreamWriter writer = new OutputStreamWriter(
					new FileOutputStream(folderNameForHTMLFile + "_"
							+ this.FILENAME, true), "UTF-8");

			BufferedWriter bw = new BufferedWriter(writer);
			bw.write("<table width=100% border=1px>");
			bw.write("<tr>");
			bw.write("<td width=5%>Step" + step_no + "</td>");
			bw.write("<td width=20%>" + step_desc + "</td>");
			bw.write("<td width=20%>" + ExpectedRes + "</td>");
			bw.write("<td width=20%>" + Actual + "</td>");
			bw.write("<td width=5% height=100 style=color:red;font-weight:bold>Fail</td>");
			if (StringUtils.equalsIgnoreCase(ScreenShotStatus, "Yes")) {
				bw.write("<td width=5%><a href='" + folderNameforScreenshot
						+ "/" + screenshot_html_name + ".png" + "'"
						+ "target=_self>Screenshot</a></td>");
			} else {
				bw.write("<td width=5%><a>Screenshot</a></td>");
			}
			bw.write("</tr>");
			bw.write("</table>");
			bw.close();
		}
	}

	private synchronized void copyFolder(File Sourcefolder1,
			File destnationfolder2) {
		String[] files = Sourcefolder1.list();
		for (String file : files)
			copyFolder(Sourcefolder1, destnationfolder2);
	}

	public synchronized long takeScreenshot(WebDriver driver, String folder)
			throws Exception {
		File screenshot;
		try {
			screenshot = (File) ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
		} catch (Exception e) {
			driver = new Augmenter().augment(driver);
			screenshot = (File) ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
		}
		try {
			File f = null;
			boolean a = screenshot.exists();
			if (a) {
				this.test_step_unique_id = Calendar.getInstance()
						.getTimeInMillis();
				FileUtils.copyFile(screenshot, new File(
						getFolderPathForReport() + "/"
								+ this.test_step_unique_id + ".png"));
			}
			screenshot.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.test_step_unique_id;
	}

	public void appendToFile(Exception e) {
		synchronized (LOCK) {
			try {
				if (this.countLogs == 0) {
					FileWriter fstream = new FileWriter(this.SOURCE_FOLDER
							+ "/" + "LogsReport.txt", true);
					BufferedWriter out = new BufferedWriter(fstream);
					out.write("<Failure" + (this.countLogs + 1) + ">");
					PrintWriter pWriter = new PrintWriter(out, true);
					e.printStackTrace(pWriter);
					this.countLogs += 1;
				} else {
					FileWriter fstream = new FileWriter(this.SOURCE_FOLDER
							+ "/" + "LogsReport.txt", true);
					BufferedWriter out = new BufferedWriter(fstream);
					out.write("</Failure" + this.countLogs + ">");
					out.write("<Failure" + (this.countLogs + 1) + ">");
					PrintWriter pWriter = new PrintWriter(out, true);
					e.printStackTrace(pWriter);
					this.countLogs += 1;
				}
			} catch (Exception ie) {
				throw new RuntimeException("Error on LogReport.xml generation",
						ie);
			}
		}
	}
}

