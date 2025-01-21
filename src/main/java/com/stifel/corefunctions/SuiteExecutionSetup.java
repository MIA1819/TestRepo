
package com.stifel.corefunctions;

import java.io.PrintStream;
import org.apache.commons.lang.StringUtils;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class SuiteExecutionSetup {
	private static FrameworkParameters frameworkParameters = FrameworkParameters
			.getInstance();

	@BeforeSuite
	public void setParametersForExecution() {
		frameworkParameters.setOverallPass(0);
		GetDataFromRunsheet getData = new GetDataFromRunsheet();
		String sheetName = System.getProperty("sheetName");
		String threadCount = System.getProperty("threadCount");
		if (StringUtils.isEmpty(threadCount)) {
			threadCount = getData.getDetails("Thread");
		}

		if (StringUtils.isEmpty(sheetName)) {
			sheetName = getData.getDetails("SheetName");
		}
		System.out.println("Executing \"" + sheetName
				+ "\" Test Suite with Thread : " + threadCount);
		System.out.println("Take Pass Screen Shot     - "
				+ getData.getDetails("TakeScreenShotForPassedScripts"));
		System.out.println("Take Fail Screen Shot     - "
				+ getData.getDetails("TakeScreenShotForFailedScripts"));
		System.out.println("Record Video on Sauce     - "
				+ getData.getDetails("RecordVideoOfExecution"));
		System.out.println("Record screenshoton sauce - "
				+ getData.getDetails("TakeScreenShot"));
	}

	@AfterSuite
	public void printTestParameters() {
		int failCount = frameworkParameters.getOverallFail();
		int noRunCount = frameworkParameters.getOverallNoRun();
		int passCount = frameworkParameters.getOverallPass();
		System.out.println("Total Test Cases Executed : "
				+ (passCount + noRunCount + failCount));
		System.out.println("Total Test Cases Passed\t  : " + passCount);
		System.out.println("Total Test Cases Failed   : " + failCount);
		System.out.println("Total Test Cases No Run   : " + noRunCount);
	}
}
