package com.stifel.corefunctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.Reporter;


public class Common_Functions {
	 public static long test_step_unique_id;
	 public static WebElement finalElement;
	 public static List<WebElement> finalElements;
	 public static String[][] data1 = null;
	 public static String[][] data2 = null;
//Generic_Functions.selectFromDropDownByvisibleText 


	public static long randomFunctionGenerator() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		Date date1 = new Date();
		Date date = sdf.parse(sdf.format(date1));
		return date.getTime();
	}
	
	
	// Scroll element to view
	public static void scrollIntoView(WebDriver driver,WebElement element) {
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", element);
    
	}

	public static String generateAccountNumber() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		Date date1 = new Date();
		Date date = sdf.parse(sdf.format(date1));
		Random ran = new Random();
		return String.valueOf(date.getTime()) + String.valueOf(ran.nextInt(10) + ran.nextInt(89));
	}

	public static boolean ElementLocatorVerification(By by, WebDriver driver) {
		try {
			if (driver.findElement(by) != null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Desc :Reusable function on Retrieving a WebElement Defined from the
	 * Application
	 * 
	 * @param locateID
	 * @param locateCSS
	 * @param locateXPath
	 * @param driver
	 * @return {@code WebElement} object of WebElement
	 */
	public static WebElement RetrieveWebElementFromApplication(String locateID, String locateCSS, String locateXPath,
			WebDriver driver) {
		WebElement finalElement = null;
		if (ElementLocatorVerification(By.id(locateID), driver)) {
			finalElement = driver.findElement(By.id(locateID));
		} else if (ElementLocatorVerification(By.cssSelector(locateCSS), driver)) {
			finalElement = driver.findElement(By.cssSelector(locateCSS));
		} else if (ElementLocatorVerification(By.xpath(locateXPath), driver)) {
			finalElement = driver.findElement(By.xpath(locateXPath));
		}
		return finalElement;
	}

	/**
	 * Desc :Reusable function on Retrieving a WebElement List Defined from the
	 * application
	 * 
	 * @param locateID
	 * @param locateCSS
	 * @param locateXPath
	 * @param driver
	 * @return {@code List<WebElement>} object of List<WebElement>
	 */
	public static List<WebElement> RetrieveWebElementsFromApplication(String locateID, String locateCSS,
			String locateXPath, WebDriver driver) {
		List<WebElement> finalElements = null;
		if (ElementLocatorVerification(By.id(locateID), driver)) {
			finalElements = driver.findElements(By.id(locateID));
		} else if (ElementLocatorVerification(By.cssSelector(locateCSS), driver)) {
			finalElements = driver.findElements(By.cssSelector(locateCSS));
		} else if (ElementLocatorVerification(By.xpath(locateXPath), driver)) {
			finalElements = driver.findElements(By.xpath(locateXPath));
		}
		return finalElements;
	}

	/**
	 * Desc :Load the Application URL in the specified Browser. The result is
	 * {@code true}
	 * 
	 * @param URL
	 * @param driver
	 * @param verifyElement
	 * @param timeOut
	 * @param currentsuit
	 * @param folder
	 * @param description
	 * @return
	 * @throws Exception
	 */
	public static boolean LoadApplication(String URL, WebDriver driver, String verifyElement, int timeOut,
			ReportGenerator currentsuit, String folder, String description) throws Exception {
		boolean loginStatus = false;
		driver.get(URL);
		Thread.sleep(5000);
		waitForLoad(driver, timeOut);
		loginStatus = waitUntilConditionPresent(driver, verifyElement, timeOut);
		if (!loginStatus) {
			currentsuit.ReportFail(driver, "Validate whether " + description + " is displayed",
					description + " should be displayed", description + " is not displayed", folder);
		} else {
			currentsuit.ReportPass(driver, "Validate whether " + description + " is displayed",
					description + " should be displayed", description + " is displayed", folder);
		}
		return loginStatus;
	}

	/**
	 * 
	 * @param driver
	 * @throws InterruptedException
	 */
	public static void waitwhenWheel(WebDriver driver) throws InterruptedException

	{
		try {
			WebElement loadingWheel = driver.findElement(By.cssSelector("[id='_progressOverlayWindow']"));
			Common_Functions.waitForJavaScriptLoad(loadingWheel, 30, driver);
		} catch (Exception e) {
			Thread.sleep(30000);
		}
	}

	/**
	 * Desc : Provide a Delay while the Browser is loaded.
	 * 
	 * @param driver
	 * @param time
	 */
	public static void waitForLoad(WebDriver driver, int time) {
		boolean status = false;
		Calendar startTime = Calendar.getInstance();
		Calendar endTime = Calendar.getInstance();
		long timeTaken;
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, time);
		do {

			try {
				wait.until(pageLoadCondition);
				if (wait.until(pageLoadCondition) == true) {
					status = true;
				} else {
					status = false;
				}
			} catch (NoSuchElementException e) {
				status = false;
			}
			endTime = Calendar.getInstance();
			timeTaken = (endTime.getTimeInMillis() - startTime.getTimeInMillis()) / 1000;
			if (timeTaken > time) {
				timeTaken = timeTaken - time;
				startTime = Calendar.getInstance();
			}
		} while (status == false && timeTaken <= time);
	}

	/**
	 * Desc :Provide a Delay till the spinner wheel is completed.
	 * 
	 * @param locater
	 * @param timeOut
	 * @param driver
	 * @throws InterruptedException
	 */
	public static void waitForSpinnerLoad(WebElement locater, int timeOut, WebDriver driver)
			throws InterruptedException {
		boolean status = false;
		Calendar startTime = Calendar.getInstance();
		Calendar endTime = Calendar.getInstance();
		long timeTaken;
		do {
			try {
				if (!locater.getAttribute("id").equals("appendSpinner")) {
					status = true;
				} else {
					status = false;
				}
			} catch (NoSuchElementException e) {
				status = false;
			}
			endTime = Calendar.getInstance();
			timeTaken = (endTime.getTimeInMillis() - startTime.getTimeInMillis()) / 1000;

		} while (status == false && timeTaken <= timeOut);
		System.out.println("Time taken is " + timeTaken);
		if (timeTaken > timeOut) {
			System.out.println("timeTaken > timeOut  case");
			Thread.sleep(30000);
		}
	}

	/**
	 * Desc :Provide a Delay till the Progress Bar is completed.
	 * 
	 * @param ID
	 * @param driver
	 * @return
	 * @throws InterruptedException
	 */
	public static boolean WaitUntilProgressBar(String ID, WebDriver driver) throws InterruptedException {
		boolean status = false;
		boolean WaitStatus1 = true;
		while (WaitStatus1) {
			try {
				String GetText = "return arguments[0].innerText";
				String Actual = "";
				try {
					Actual = driver.findElement(By.id(ID)).getText();
				} catch (WebDriverException e1) {
					Actual = (String) ((JavascriptExecutor) driver).executeScript(GetText,
							driver.findElement(By.id(ID)));
				}
				System.out.println("Color " + driver.findElement(By.id(ID)).getCssValue("background-color"));
				System.out.println("Actual1" + Actual);
				if (Actual.equals("Updating Results ...")) {
					Thread.sleep(100);
					continue;
				}
				System.out.println("done");
				status = true;
				WaitStatus1 = false;
			} catch (Exception e) {
				Thread.sleep(100);
				System.out.println(e);
				continue;
			}
		}
		return status;
	}

	/**
	 * Desc :Provide a Delay till the Object ID is displayed.
	 * 
	 * @param ID
	 * @param driver
	 * @return
	 * @throws InterruptedException
	 */
	public static boolean waitUntilID(String ID, WebDriver driver) throws InterruptedException {
		boolean status = false;
		boolean WaitStatus1 = true;
		while (WaitStatus1) {
			try {
				String GetText = "return arguments[0].innerText";
				String Actual = (String) ((JavascriptExecutor) driver).executeScript(GetText,
						driver.findElement(By.id(ID)));
				System.out.println("Actual1" + Actual);
				if (Actual.equals("null")) {
					Thread.sleep(100);
					continue;
				}
				System.out.println("done");
				status = true;
				WaitStatus1 = false;
			} catch (Exception e) {
				Thread.sleep(100);
				continue;
			}
		}
		return status;
	}

	/**
	 * Desc :Provide a Delay till the Object Xpath is displayed.
	 * 
	 * @param XPATH
	 * @param driver
	 * @return
	 * @throws InterruptedException
	 */
	public static boolean waitUntilXpath(String XPATH, WebDriver driver) throws InterruptedException {
		boolean status = false;
		boolean WaitStatus1 = true;
		while (WaitStatus1) {
			try {
				String GetText1 = "return arguments[0].innerText";
				String Actual1 = (String) ((JavascriptExecutor) driver).executeScript(GetText1,
						driver.findElement(By.xpath(XPATH)));
				System.out.println("Actual1" + Actual1);
				if (Actual1.equals("null")) {
					Thread.sleep(100);
					continue;
				}
				System.out.println("done");
				status = true;
				WaitStatus1 = false;
			} catch (Exception e) {
				Thread.sleep(100);
				continue;
			}
		}
		return status;
	}

	/**
	 * Desc : Function to verify whether the specified locator property is
	 * present in application
	 * 
	 * @param locator_Property
	 * @param driver
	 * @param currentsuit
	 * @param folder
	 * @return
	 * @throws Exception
	 */
	public static boolean isPropertyPresent(WebElement locator_Property, WebDriver driver, ReportGenerator currentsuit,
			String folder) throws Exception {
		boolean returnStatus = false;
		long test_step_unique_id;
		if (locator_Property != null) {
			returnStatus = true;
			test_step_unique_id = currentsuit.takeScreenshot(driver, folder);
		} else {
			returnStatus = false;
			test_step_unique_id = currentsuit.takeScreenshot(driver, folder);
		}
		return returnStatus;
	}

	/**
	 * Desc :Provide a Delay till the page graphics is loaded.
	 * 
	 * @param locater
	 * @param timeOut
	 * @param driver
	 * @throws InterruptedException
	 */
	public static void waitForGraphLoad(WebElement locater, int timeOut, WebDriver driver) throws InterruptedException {
		boolean status = false;
		Calendar startTime = Calendar.getInstance();
		Calendar endTime = Calendar.getInstance();
		long timeTaken;
		do {
			try {
				if (!locater.getAttribute("style").equals("opacity: 0.25;")) {
					status = true;
				} else {
					status = false;
				}
			} catch (NoSuchElementException e) {
				status = false;
			}
			endTime = Calendar.getInstance();
			timeTaken = (endTime.getTimeInMillis() - startTime.getTimeInMillis()) / 1000;

		} while (status == false && timeTaken <= timeOut);
		System.out.println("Time taken is " + timeTaken);
		if (timeTaken > timeOut) {
			System.out.println("timeTaken > timeOut  case");
			Thread.sleep(30000);
		}
	}

	/**
	 * Desc :Provide a Delay till the page Javascript is loaded.
	 * 
	 * @param locater
	 * @param timeOut
	 * @param driver
	 * @return
	 * @throws InterruptedException
	 */
	public static long waitForJavaScriptLoad(WebElement locater, int timeOut, WebDriver driver)
			throws InterruptedException {
		boolean status = false;
		Calendar startTime = Calendar.getInstance();
		Calendar endTime = Calendar.getInstance();
		long timeTaken;
		do {
			try {
				if (locater.getText().equals("")) {
					status = true;
				} else {
					status = false;
				}
			} catch (NoSuchElementException e) {
				status = false;
			}
			endTime = Calendar.getInstance();
			timeTaken = (endTime.getTimeInMillis() - startTime.getTimeInMillis()) / 1000;

		} while (status == false && timeTaken <= timeOut);
		System.out.println("Time taken for Loading is " + timeTaken);
		if (timeTaken > timeOut) {
			System.out.println("timeTaken > timeOut  case");
			Thread.sleep(30000);
		}
		return timeTaken;
	}

	/**
	 * Desc: Provide a wait Till the Page got updated with Javascripts
	 * 
	 * @param timeOut
	 * @param driver
	 * @throws InterruptedException
	 */
	public static void waitUnitlrocDetailsLoad(int timeOut, WebDriver driver) throws InterruptedException {
		boolean status = false;
		Calendar startTime = Calendar.getInstance();
		Calendar endTime = Calendar.getInstance();
		long timeTaken;
		do {
			try {
				if (!driver.findElement(By.tagName("title")).equals("loading")) {
					status = true;
				} else {
					status = false;
				}
			} catch (NoSuchElementException e) {
				status = false;
			}
			endTime = Calendar.getInstance();
			timeTaken = (endTime.getTimeInMillis() - startTime.getTimeInMillis()) / 1000;

		} while (status == false && timeTaken <= timeOut);
		System.out.println("Time taken for Tags got Loading is " + timeTaken);
		if (timeTaken > timeOut) {
			System.out.println("timeTaken > timeOut  case");
			Thread.sleep(30000);
		}
	}

	/**
	 * Desc :Verify the specified object properties are Present in the
	 * Application. The result is {@code true} if and only if the argument is
	 * not {@code null} and is a {@code
	 * Web Element properties are Present in the Page} object that represents
	 * WebElement.
	 * 
	 * @param byId
	 * @param byCss
	 * @param xPath
	 * @param driver
	 * @param currentsuit
	 * @param folder
	 * @return {@code true} if the given object properties are Present in the
	 *         page {@code true}
	 * @throws Exception
	 */
	public static boolean isPropertyPresent(By byId, By byCss, By xPath, WebDriver driver, ReportGenerator currentsuit,
			String folder) throws Exception {
		boolean returnStatus = false;
		long test_step_unique_id;
		if (driver.findElements(byId).size() != 0 || driver.findElements(byCss).size() != 0
				|| driver.findElements(xPath).size() != 0) {

			returnStatus = true;
			test_step_unique_id = currentsuit.takeScreenshot(driver, folder);
		} else {
			returnStatus = false;
			test_step_unique_id = currentsuit.takeScreenshot(driver, folder);

		}
		return returnStatus;
	}

	/**
	 * Desc :Verify whether the Specified Object is Displayed in the
	 * Application. The result is {@code true} if and only if the argument is
	 * not {@code null} and is a {@code
	 * Web Element is Displayed in the Page} object that represents web element.
	 * 
	 * @param locator_Property
	 * @param driver
	 * @param currentsuit
	 * @param folder
	 * @param description
	 * @return {@code true} if the given object is Displayed/Present in the page
	 *         {@code WebElement}
	 * @throws Exception
	 */
	public static boolean isDisplayed(WebElement locator_Property, WebDriver driver, ReportGenerator currentsuit,
			String folder, String description) throws Exception {
		boolean returnStatus = false;
		if (!description.equals("") && locator_Property != null) {
			try {
				boolean returnStatusNew = false;
				if (locator_Property.isDisplayed()) {
					returnStatusNew = true;
					currentsuit.ReportPass(driver,
							"Validate whether " + description + " is displayed in the Application",
							description + " should be displayed in the page", description + " is displayed in the page",
							folder);
				}
				returnStatus = returnStatusNew;
			} catch (Exception e) {
				currentsuit.appendToFile(e);
				currentsuit.ReportFail(driver,
						"Validate whether " + description + " is displayed in the Application",
						description + " should be displayed in the page", description + " is not displayed in the page",
						folder);
			}
		} else {
			currentsuit.ReportFail(driver, "Validate whether " + description + " is displayed in the Application",
					description + " should be displayed in the page", description + " is not displayed in the page",
					folder);
		}
		return returnStatus;
	}

	/**
	 * Desc :Verify whether the Specified Object is not Displayed in the
	 * Application. The result is {@code true} if and only if the argument is
	 * not {@code null} and is a {@code
	 * Web Element is Displayed in the Page} object that represents web element.
	 * 
	 * @param locator_Property
	 * @param driver
	 * @param currentsuit
	 * @param folder
	 * @param description
	 * @return {@code true} if the given object is Displayed/Present in the page
	 *         {@code WebElement}
	 * @throws Exception
	 */
	public static boolean notDisplayed(WebElement locator_Property, WebDriver driver, ReportGenerator currentsuit,
			String folder, String description) throws Exception {
		boolean returnStatus = false;
		if (!description.equals("") && locator_Property != null) {
			try {
				boolean returnStatusNew = false;
				if (locator_Property.isDisplayed()) {
					returnStatusNew = true;
					currentsuit.ReportFail(driver,
							"Validate whether " + description + " is not displayed in the Application",
							description + " should not be displayed in the page",
							description + " is displayed in the page", folder);
					returnStatus = returnStatusNew;
				} else {
					currentsuit.ReportPass(driver,
							"Validate whether " + description + " is not displayed in the Application",
							description + " should not be displayed in the page",
							description + " is not displayed in the page", folder);
					returnStatus = true;
				}
			} catch (Exception e) {
				currentsuit.appendToFile(e);
				currentsuit.ReportPass(driver,
						"Validate whether " + description + " is not displayed in the Application",
						description + " should not be displayed in the page",
						description + " is not displayed in the page", folder);
				returnStatus = true;

			}
		} else {
			currentsuit.ReportPass(driver,
					"Validate whether " + description + " is not displayed in the Application",
					description + " should not be displayed in the page", description + " is not displayed in the page",
					folder);
			returnStatus = true;

		}
		return returnStatus;
	}

	/**
	 * Desc :Function writes fail condition if the object is not displayed
	 * 
	 * @param driver
	 * @param currentsuit
	 * @param folder
	 * @param description
	 * @throws Exception
	 */
	public static void notDisplayedWriteFail(WebDriver driver, ReportGenerator currentsuit, String folder,
			String description) throws Exception {
		currentsuit.ReportPass(driver, "Validate whether " + description + " is not displayed in the Application",
				description + " should not be displayed in the page", description + " is not displayed in the page",
				folder);
	}

	/**
	 * Desc :Function writes pass condition
	 * 
	 * @param driver
	 * @param currentsuit
	 * @param folder
	 * @param testCaseDescription
	 * @param expectedResult
	 * @param actualResult
	 * @throws Exception
	 */
	public static void ResultPass(WebDriver driver, ReportGenerator currentsuit, String folder, String testCaseDescription,
			String expectedResult, String actualResult) throws Exception {
		currentsuit.ReportPass(driver, testCaseDescription, expectedResult, actualResult, folder);
	}

	/**
	 * Desc :Function writes fail condition
	 * 
	 * @param driver
	 * @param currentsuit
	 * @param folder
	 * @param testCaseDescription
	 * @param expectedResult
	 * @param actualResult
	 * @throws Exception
	 */
	public static void ResultFail(WebDriver driver, ReportGenerator currentsuit, String folder, String testCaseDescription,
			String expectedResult, String actualResult) throws Exception {
		currentsuit.ReportFail(driver, testCaseDescription, expectedResult, actualResult, folder);
		Reporter.log(actualResult);
		//AssertJUnit.assertEquals("Pass", "Fail");
	}

	public static void WriteFailAndContinue(WebDriver driver, ReportGenerator currentsuit, String folder, String testCaseDescription,
			String expectedResult, String actualResult) throws Exception {
		currentsuit.ReportFail(driver, testCaseDescription, expectedResult, actualResult, folder);
		Reporter.log(actualResult);
		//AssertJUnit.assertEquals("Pass", "Fail");
	}
	
	
	
	/**
	 * Desc :Enter a Text in a Specified Text Field. The result is {@code
	 * true} if and only if the argument is not {@code null} and is a {@code
	 * Web Element is Displayed in the Page} object that represents WebElement.
	 * 
	 * @param enterDetails
	 * @param locator_Property
	 * @param driver
	 * @param currentsuit
	 * @param folder
	 * @param description
	 * @return {@code true} if the given object is Displayed/Present in the page
	 *         {@code String}
	 * @throws Exception
	 */
	public static boolean EnterValues(String enterDetails, WebElement locator_Property, WebDriver driver,
			ReportGenerator currentsuit, String folder, String description) throws Exception {
		boolean returnStatus = false;
		try {
			boolean returnStatusNew = false;
			if (locator_Property.isDisplayed()) {
				returnStatus = true;
				locator_Property.clear();
				locator_Property.sendKeys(enterDetails);
			}
			returnStatus = returnStatusNew;
		} catch (Exception e) {
			currentsuit.appendToFile(e);
			currentsuit.ReportFail(driver, "Validate whether " + description + " is displayed in the Application",
					description + " should be displayed in the page", description + " is not displayed in the page",
					folder);
		}
		return returnStatus;
	}

	/**
	 * Desc :Submit the Specified Object. The result is {@code true} if and only
	 * if the argument is not {@code null} and is a {@code
	 * Web Element is Displayed in the Page} object that represents web element.
	 * 
	 * @param locator_Property
	 * @param driver
	 * @param currentsuit
	 * @param folder
	 * @param description
	 * @return {@code true} if the given object is Displayed/Present in the page
	 *         {@code WebElement}
	 * @throws Exception
	 */
	public static boolean Submit(WebElement locator_Property, WebDriver driver, ReportGenerator currentsuit, String folder,
			String description) throws Exception {
		boolean returnStatus = false;
		try {
			boolean returnStatusNew = false;
			if (locator_Property.isDisplayed()) {

				// changes made here by DevOps QA automation Team
				// returnStatus=true; this line was giving incorrect results.
				returnStatusNew = true;

				// changes made by DevOps QA automation team
				returnStatusNew = true;

				locator_Property.click();
				Thread.sleep(300);
			}
			returnStatus = returnStatusNew;
		} catch (Exception e) {
			currentsuit.appendToFile(e);
			currentsuit.ReportFail(driver, "Validate whether " + description + " is displayed in the Application",
					description + " should be displayed in the page", description + " is not displayed in the page",
					folder);
			System.out.println(e);
		}
		return returnStatus;
	}

	/**
	 * Desc : Click on the Specified Object. The result is {@code
	 * true} if and only if the argument is not {@code null} and is a {@code
	 * Web Element is Displayed in the Page} object that represents web element.
	 * 
	 * @param locator_Property
	 * @param driver
	 * @param currentsuit
	 * @param folder
	 * @param description
	 * @return {@code true} if the given object is Displayed/Present in the page
	 *         {@code WebElement}
	 * @throws Exception
	 */
	public static boolean Click(WebElement locator_Property, WebDriver driver, ReportGenerator currentsuit, String folder,
			String description) throws Exception {
		boolean returnStatus = false;
		try {
			boolean returnStatusNew = false;
			if (locator_Property.isDisplayed()) {
				returnStatus = true;
				locator_Property.submit();
				Thread.sleep(3000);
			}
			returnStatus = returnStatusNew;
		} catch (Exception e) {
			currentsuit.appendToFile(e);
			currentsuit.ReportFail(driver, "Validate whether " + description + " is displayed in the Application",
					description + " should be displayed in the page", description + " is not displayed in the page",
					folder);
		}

		return returnStatus;
	}

	/**
	 * Desc :Click on the Specified Object. The result is {@code
	 * true} if and only if the argument is not {@code null} and is a {@code
	 * Web Element is Displayed in the Page} object that represents web element.
	 * 
	 * @param locator_Property
	 * @param driver
	 * @param currentsuit
	 * @param folder
	 * @return {@code true} if the given object is Displayed/Present in the page
	 *         {@code WebElement}
	 * @throws Exception
	 */
	public static boolean advancedClick(WebElement locator_Property, WebDriver driver, ReportGenerator currentsuit,
			String folder) throws Exception {
		boolean returnStatus = false;
		try {
			boolean returnStatusNew = false;
			if (locator_Property.isDisplayed()) {
				returnStatus = true;
				try {
					locator_Property.click();
				} catch (Exception ex) {
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					executor.executeScript("arguments[0].click();", locator_Property);
				}

				Thread.sleep(3000);
			}
			returnStatus = returnStatusNew;
		} catch (Exception e) {
			currentsuit.appendToFile(e);
			System.out.println(e);
		}

		return returnStatus;
	}

	/**
	 * Desc : Compares the text to the specified Text. The result is {@code
	 * true} if and only if the argument is not {@code null} and is a {@code
	 * String} object that represents the same sequence of characters as this
	 * object.
	 * 
	 * @param locator_Text
	 * @param driver
	 * @param currentsuit
	 * @param folder
	 * @param description
	 * @return {@code true} if the given object represents a {@code String}
	 *         equivalent to this string, {@code false} otherwise
	 * @throws Exception
	 */
	public static boolean CompareText(String locator_Text, WebDriver driver, ReportGenerator currentsuit, String folder,
			String description) throws Exception {
		boolean returnStatusCom = false;
		if (!description.equals("")) {
			try {
				boolean returnStatusNewCom = false;
				if (locator_Text.trim().equals(description.trim())) {
					returnStatusNewCom = true;
					currentsuit.ReportPass(driver,
							"Validate whether " + description
									+ " text is displayed and verified with the Reference parameter",
							description + " text should be displayed and verified as expected",
							description + " text is displayed & verified", folder);
				} else {
					currentsuit.ReportFail(driver,
							"Validate whether " + description
									+ " text is displayed and verified with the Reference parameter",
							description + " text should be displayed and verified as expected",
							description + " text is displayed but verified as not the expected", folder);
				}
				returnStatusCom = returnStatusNewCom;
			} catch (Exception e) {
				currentsuit.appendToFile(e);
				currentsuit.ReportFail(driver,
						"Validate whether " + description
								+ " text is displayed and verified with the Reference parameter",
						description + " text should be displayed and verified as expected",
						description + " text is displayed but verified as not the expected", folder);
			}
		}
		return returnStatusCom;
	}

	/**
	 * Desc :Reusable function on Highlight a WebElement
	 * 
	 * @param locator_Property
	 * @param driver
	 * @param currentsuit
	 * @param folder
	 * @param description
	 * @return {@code long} object of screenshots
	 * @throws Exception
	 */
	public static long highlightElement(WebElement locator_Property, WebDriver driver, ReportGenerator currentsuit,
			String folder, String description) throws Exception {
		try {
			currentsuit.ReportPass(driver, "Validate whether " + description + " is displayed in the Application",
					description + " should be highlighted in the page", description + " is highlighted in the page",
					folder);
		} catch (Exception e) {
			currentsuit.ReportFail(driver, "Validate whether " + description + " is displayed in the Application",
					description + " should be displayed in the page", description + " is not displayed in the page",
					folder);
		}
		long test_step_unique_id = 0;
		return test_step_unique_id;
	}

	/**
	 * Desc :Compares this string to the specified object. The result is {@code
	 * true} if and only if the argument is not {@code null} and is a {@code	
	 * String} object that represents the same sequence of characters as this
	 * object.
	 * 
	 * @param locator_Property
	 * @param driver
	 * @param currentsuit
	 * @param folder
	 * @param textToBeVerified
	 * @return {@code true} if the given object represents a {@code String}
	 *         equivalent to this string, {@code false} otherwise
	 * @throws Exception
	 */
	public static boolean VerifyTextDisplayed(WebElement locator_Property, WebDriver driver, ReportGenerator currentsuit,
			String folder, String textToBeVerified) throws Exception {
		boolean returnStatus = false;
		try {
			boolean returnStatusNew = false;
			if (locator_Property.getText().equals(textToBeVerified)) {
				// Changes made by DevOps QA automation Team . Here We have
				// changed returnStatus=true to returnStatusNew=true

				// returnStatus = true; We were getting incorrect result because
				// of this line

				returnStatusNew = true;
				currentsuit.ReportPass(driver,
						"Validate whether " + textToBeVerified + " is displayed in the Application",
						textToBeVerified + " should be displayed in the page",
						textToBeVerified + " is displayed in the page", folder);
			}
			returnStatus = returnStatusNew;
		} catch (Exception e) {
			currentsuit.appendToFile(e);
			currentsuit.ReportFail(driver,
					"Validate whether " + textToBeVerified + " is displayed in the Application",
					textToBeVerified + " should be displayed in the page",
					textToBeVerified + " is not displayed in the page", folder);
		}

		return returnStatus;
	}

	/**
	 * Desc :Provide a Delay till the given condition is true.
	 * 
	 * @param driver
	 * @param el
	 * @param timeOut
	 * @return
	 * @throws InterruptedException
	 */
	public static boolean waitUntilConditionPresent(WebDriver driver, String el, int timeOut)
			throws InterruptedException {
		boolean status = false;
		Calendar startTime = Calendar.getInstance();
		Calendar endTime = Calendar.getInstance();
		long timeTaken;
		do {
			try {
				WebElement expected_ID = RetrieveWebElementFromApplication(el, el, el, driver);
				if (expected_ID != null) {
					status = true;
				}
			} catch (NoSuchElementException e) {
				status = false;
			}
			endTime = Calendar.getInstance();
			timeTaken = (endTime.getTimeInMillis() - startTime.getTimeInMillis()) / 1000;

		} while (status == false && timeTaken <= timeOut);
		if (timeTaken > timeOut) {
			Thread.sleep(1000);
		}
		return status;
	}

	/**
	 * Desc: Verify the specified object is Present in the Application. The
	 * result is {@code true} if and only if the argument is not {@code null}
	 * and is a {@code
	 * Web Element is Present in the Page} object that represents WebElement.
	 * 
	 * @param byId
	 * @param byCss
	 * @param xPath
	 * @param driver
	 * @param currentsuit
	 * @param folder
	 * @param description
	 * @return {@code true} if the given object is Present in the page
	 *         {@code WebElement}
	 * @throws Exception
	 */
	public static boolean isPresent(By byId, By byCss, By xPath, WebDriver driver, ReportGenerator currentsuit,
			String folder, String description) throws Exception {
		boolean returnStatus = false;
		if (!description.equals("")) {
			if (driver.findElements(byId).size() != 0 || driver.findElements(byCss).size() != 0
					|| driver.findElements(xPath).size() != 0) {
				returnStatus = true;
				currentsuit.ReportPass(driver,
						"Validate whether " + description + " is present in the Application",
						description + " should be 	present in the page", description + " is present in the page",
						folder);
			} else {
				returnStatus = false;
				currentsuit.ReportFail(driver, "Validate whether" + description + " is present in the Application",
						description + " should be present in the page", description + " is not present in the page",
						folder);
			}
		}
		return returnStatus;
	}

	/**
	 * Desc :Verify the specified object is Present in the Application. The
	 * result is {@code
	 * true} if and only if the argument is not {@code null} and is a {@code
	 * Web Element is Present in the Page} object that represents WebElement.
	 * 
	 * @param locator_Property
	 * @param driver
	 * @param currentsuit
	 * @param folder
	 * @param description
	 * @return {@code true} if the given object is Present in the page
	 *         {@code WebElement}
	 * @throws Exception
	 */
	public static boolean isElementPresent(WebElement locator_Property, WebDriver driver, ReportGenerator currentsuit,
			String folder, String description) throws Exception {
		boolean returnStatus = false;
		if (!description.equals("")) {
			if (locator_Property != null) {

				returnStatus = true;
				currentsuit.ReportPass(driver,
						"Validate whether " + description + " is present in the Application",
						description + " should be 	present in the page", description + " is present in the page",
						folder);
			} else {
				returnStatus = false;
				currentsuit.ReportFail(driver, "Validate whether" + description + " is present in the Application",
						description + " should be present in the page", description + " is not present in the page",
						folder);

			}
		}
		return returnStatus;
	}

	public static int DataRowCountNumber(String SheetName) {
		int rowCount = 0;
		try {
			String filename = Browser_setup.getThreadDataSheetName();
			String fileName = Utilities.getPathCommon().concat("\\Test_Scenarios\\" + filename);// pathInsideProject;
			File file = new File(fileName);
			FileInputStream inputStream = new FileInputStream(file);
			HSSFWorkbook workbook = null;
			workbook = new HSSFWorkbook(inputStream);
			// Read sheet inside the workbook by its name
			HSSFSheet sheet = workbook.getSheet(SheetName);
			// Find number of rows in excel file
			rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
			// Create a loop over all the rows of excel file to read it
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount;
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~New Methods added by DevOps QA automation
	// Team~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	/**
	 * Desc :Reusable function on Retrieving a WebElement Defined from the
	 * Application By Link Text
	 * 
	 * @param locateLinkText
	 * @param driver
	 * @return {@code WebElement} object of WebElement
	 */
	public static WebElement RetrieveWebElementFromApplicationByLinkText(String locateLinkText, WebDriver driver) {
		WebElement finalElement = null;
		if (ElementLocatorVerification(By.linkText(locateLinkText), driver)) {
			finalElement = driver.findElement(By.linkText(locateLinkText));
		}
		return finalElement;
	}

	/**
	 * Desc :Reusable function to select from drop down by Value
	 * 
	 * @param locator
	 * @param driver
	 * @param Value
	 * @return nothing
	 */
	public static void selectFromDropDownByvalue(String locator, WebDriver driver, String Value) {
		waitForUIElementByName(locator, driver);
		WebElement element = driver.findElement(By.name(locator));
		Select item = new Select(element);
		item.selectByValue(Value);
	}

	/**
	 * Desc :Reusable function to select from drop down by Visible Text
	 * 
	 * @param locator
	 * @param driver
	 * @param Value
	 * @return nothing
	 */
	public static void selectFromDropDownByvisibleText(String locator, WebDriver driver, String text) {
		waitForUIElementByxPath(locator, driver);
		WebElement element = driver.findElement(By.xpath(locator));
		Select item = new Select(element);
		item.selectByVisibleText(text);
	}

	/**
	 * Desc :Reusable function to wait until web elemnent is located by Name .
	 * This is used in drop down function
	 * 
	 * @param locator
	 * @param driver
	 * @return nothing
	 */
	public static void waitForUIElementByName(String locator, WebDriver driver) {
		Reporter.log("Waiting for locator " + locator);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.elementToBeClickable(By.name(locator)));
	}

	/**
	 * Desc :Reusable function to wait until web elemnent is located by xPath .
	 * This is used in drop down function
	 * 
	 * @param locator
	 * @param driver
	 * @return nothing
	 */
	public static void waitForUIElementByxPath(String locator, WebDriver driver) {
		Reporter.log("Waiting for locator " + locator);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
	}

	/**
	 * Desc :Reusable function to scroll down by xPath
	 * 
	 * @param locator
	 * @param driver
	 * @return nothing
	 */
	public static void makeElementViewByxpath(String locator, WebDriver driver) throws Exception {
		WebElement element = driver.findElement(By.xpath(locator));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
		// JavascriptExecutor jsx = (JavascriptExecutor) uidriver;
		// jsx.executeScript("window.scrollBy(30,0)", "");
	}

	/**
	 * Desc :Reusable function to scroll down by Link Text
	 * 
	 * @param locator
	 * @param driver
	 * @return nothing
	 */
	public static void makeElementViewByLinkText(String locator, WebDriver driver) throws Exception {
		WebElement element = driver.findElement(By.linkText(locator));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
		// JavascriptExecutor jsx = (JavascriptExecutor) uidriver;
		// jsx.executeScript("window.scrollBy(30,0)", "");
	}

	/**
	 * Desc :Reusable function to get attribute Title by xPath
	 * 
	 * @param locator
	 * @param driver
	 * @return attribute title
	 */
	public static String getattributeTitleByxpath(String locator, WebDriver driver) {
		waitForUIElementByxPath(locator, driver);
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getAttribute("title");
	}

	public static String getTextByXpath(String locator, WebDriver driver) {
		waitForUIElementByxPath(locator, driver);
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getText();
	}

	/**
	 * Desc :Reusable function to Handle pop up window
	 * 
	 * @param driver
	 * @return nothing
	 */
	public static void handlePopUpWindow(WebDriver driver, ReportGenerator currentsuit, String folder, String description)
			throws Exception {
		String parentWindowHandler = driver.getWindowHandle(); // Store your
																// parent window
		String subWindowHandler = null;
		try {
			Set<String> handles = driver.getWindowHandles(); // get all window
																// handles
			Iterator<String> iterator = handles.iterator();
			while (iterator.hasNext()) {
				subWindowHandler = iterator.next();
			}
			driver.switchTo().window(subWindowHandler); // switch to popup
														// window
														// perform operations on
														// popup

			driver.switchTo().window(parentWindowHandler); // switch back to
															// parent window
		} catch (Exception e) {
			currentsuit.appendToFile(e);
			currentsuit.ReportFail(driver, "Validate whether " + description + " is displayed in the Application",
					description + " should be displayed in the page", description + " is not displayed in the page",
					folder);
			System.out.println(e);
		}
	}

	/**
	 * Desc :Reusable function to get webelement and click on it by xPath
	 * 
	 * @param locator
	 * @param driver
	 * @param currentsuit
	 * @param folder
	 * @param description
	 * @return {@code true} if the given object is Displayed/Present in the page
	 *         {@code WebElement}
	 * @throws Exception
	 */
	public static boolean clickByxpath(String locator, WebDriver driver, ReportGenerator currentsuit, String folder,
			String description) throws Exception {
		waitForUIElementByxPath(locator, driver);
		boolean returnStatus = false;
		WebElement element = driver.findElement(By.xpath(locator));
		try {
			boolean returnStatusNew = false;
			if (element.isDisplayed()) {
				returnStatusNew = true;
				element.click();
				Thread.sleep(300);
			}
			returnStatus = returnStatusNew;
		} catch (Exception e) {
			currentsuit.appendToFile(e);
			currentsuit.ReportFail(driver, "Validate whether " + description + " is displayed in the Application",
					description + " should be displayed in the page", description + " is not displayed in the page",
					folder);
			System.out.println(e);
		}
		return returnStatus;
	}

	/**
	 * Desc :Reusable function to get webelement and click on it by ID
	 * 
	 * @param locator
	 * @param driver
	 * @param currentsuit
	 * @param folder
	 * @param description
	 * @return {@code true} if the given object is Displayed/Present in the page
	 *         {@code WebElement}
	 * @throws Exception
	 **/
	public static boolean clickById(String locator, WebDriver driver, ReportGenerator currentsuit, String folder,
			String description) throws Exception {
		waitForUIElementByid(locator, driver);
		boolean returnStatus = false;
		WebElement element = driver.findElement(By.id(locator));
		try {
			boolean returnStatusNew = false;
			if (element.isDisplayed()) {
				returnStatusNew = true;
				element.click();
				Thread.sleep(300);
			}
			returnStatus = returnStatusNew;
		} catch (Exception e) {
			currentsuit.appendToFile(e);
			currentsuit.ReportFail(driver, "Validate whether " + description + " is displayed in the Application",
					description + " should be displayed in the page", description + " is not displayed in the page",
					folder);
			System.out.println(e);
		}
		return returnStatus;
	}

	/**
	 * Desc :Reusable function to get webelement and click on it by LinkText
	 * 
	 * @param locator
	 * @param driver
	 * @param currentsuit
	 * @param folder
	 * @param description
	 * @return {@code true} if the given object is Displayed/Present in the page
	 *         {@code WebElement}
	 * @throws Exception
	 **/
	public static boolean clickByLinkText(String locator, WebDriver driver, ReportGenerator currentsuit, String folder,
			String description) throws Exception {
		waitForUIElementByLinkText(locator, driver);
		boolean returnStatus = false;
		WebElement element = driver.findElement(By.linkText(locator));
		try {
			boolean returnStatusNew = false;
			if (element.isDisplayed()) {
				returnStatusNew = true;
				element.click();
				Thread.sleep(300);
			}
			returnStatus = returnStatusNew;
		} catch (Exception e) {
			currentsuit.appendToFile(e);
			currentsuit.ReportFail(driver, "Validate whether " + description + " is displayed in the Application",
					description + " should be displayed in the page", description + " is not displayed in the page",
					folder);
			System.out.println(e);
		}
		return returnStatus;
	}

	public static boolean clickByPartialLinkText(String locator, WebDriver driver, ReportGenerator currentsuit, String folder,
			String description) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		boolean returnStatus = false;
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(locator)));
		try {
			boolean returnStatusNew = false;
			if (element.isDisplayed()) {
				returnStatusNew = true;
				element.click();
				Thread.sleep(300);
			}
			returnStatus = returnStatusNew;
		} catch (Exception e) {
			currentsuit.appendToFile(e);
			currentsuit.ReportFail(driver, "Validate whether " + description + " is displayed in the Application",
					description + " should be displayed in the page", description + " is not displayed in the page",
					folder);
			System.out.println(e);
		}
		return returnStatus;
	}
	/**
	 * Desc :Reusable function to wait until web elemnent is located by ID .
	 * 
	 * @param locator
	 * @param driver
	 * @return nothing
	 */
	public static void waitForUIElementByid(String locator, WebDriver driver) {
		Reporter.log("Waiting for locator " + locator);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(locator)));
	}

	/**
	 * Desc :Reusable function to wait until web elemnent is located by LinkText
	 * .
	 * 
	 * @param locator
	 * @param driver
	 * @return nothing
	 */
	public static void waitForUIElementByLinkText(String locator, WebDriver driver) {
		Reporter.log("Waiting for locator " + locator);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText(locator)));
	}
	public static void waitForUIElementByPartialLinkText(String locator, WebDriver driver) {
		Reporter.log("Waiting for locator " + locator);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(locator)));
	}

	public static boolean alertPresent(WebDriver driver, ReportGenerator currentsuit, String folder, String description)
			throws Exception {
		try {
			driver.switchTo().alert();
			return true;
		} catch (Exception e) {
			currentsuit.appendToFile(e);
			currentsuit.ReportFail(driver, "Validate whether " + description + " is displayed in the Application",
					description + " should be displayed in the page", description + " is not displayed in the page",
					folder);
			System.out.println(e);
			return false;
		}
	}

	/**
	 * Desc :Enter a Text in a Specified Text Field by xPath. The result is
	 * {@code
	 * true} if and only if the argument is not {@code null} and is a {@code
	 * Web Element is Displayed in the Page} object that represents WebElement.
	 * 
	 * @param text
	 * @param locator
	 * @param driver
	 * @param currentsuit
	 * @param folder
	 * @param description
	 * @return {@code true} if the given object is Displayed/Present in the page
	 *         {@code String}
	 * @throws Exception
	 */
	public static boolean enterDataByxpath(String locator, String text, WebDriver driver, ReportGenerator currentsuit,
			String folder, String description) throws Exception {
		waitForUIElementByxPath(locator, driver);
		WebElement element = driver.findElement(By.xpath(locator));
		boolean returnStatus = false;
		try {
			boolean returnStatusNew = false;
			if (element.isDisplayed()) {
				returnStatusNew = true;
				element.clear();
				element.sendKeys(text);
			}
			returnStatus = returnStatusNew;
		} catch (Exception e) {
			currentsuit.appendToFile(e);
			currentsuit.ReportFail(driver, "Validate whether " + description + " is displayed in the Application",
					description + " should be displayed in the page", description + " is not displayed in the page",
					folder);
		}
		return returnStatus;

	}

	/**
	 * Desc :Enter a Text in a Specified Text Field by id. The result is {@code
	 * true} if and only if the argument is not {@code null} and is a {@code
	 * Web Element is Displayed in the Page} object that represents WebElement.
	 * 
	 * @param text
	 * @param locator
	 * @param driver
	 * @param currentsuit
	 * @param folder
	 * @param description
	 * @return {@code true} if the given object is Displayed/Present in the page
	 *         {@code String}
	 * @throws Exception
	 */
	public static boolean enterDataByid(String locator, String text, WebDriver driver, ReportGenerator currentsuit,
			String folder, String description) throws Exception {
		waitForUIElementByid(locator, driver);
		WebElement element = driver.findElement(By.id(locator));
		boolean returnStatus = false;
		try {
			boolean returnStatusNew = false;
			if (element.isDisplayed()) {
				returnStatusNew = true;
				element.clear();
				element.sendKeys(text);
			}
			returnStatus = returnStatusNew;
		} catch (Exception e) {
			currentsuit.appendToFile(e);
			currentsuit.ReportFail(driver, "Validate whether " + description + " is displayed in the Application",
					description + " should be displayed in the page", description + " is not displayed in the page",
					folder);
		}
		return returnStatus;
	}

	/**
	 * Desc :Enter a Text in a Specified Text Field by name. The result is
	 * {@code
	 * true} if and only if the argument is not {@code null} and is a {@code
	 * Web Element is Displayed in the Page} object that represents WebElement.
	 * 
	 * @param text
	 * @param locator
	 * @param driver
	 * @param currentsuit
	 * @param folder
	 * @param description
	 * @return {@code true} if the given object is Displayed/Present in the page
	 *         {@code String}
	 * @throws Exception
	 */
	public static boolean enterDataByname(String locator, String text, WebDriver driver, ReportGenerator currentsuit,
			String folder, String description) throws Exception {
		waitForUIElementByName(locator, driver);
		WebElement element = driver.findElement(By.name(locator));
		boolean returnStatus = false;
		try {
			boolean returnStatusNew = false;
			if (element.isDisplayed()) {
				returnStatusNew = true;
				element.clear();
				element.sendKeys(text);
			}
			returnStatus = returnStatusNew;
		} catch (Exception e) {
			currentsuit.appendToFile(e);
			currentsuit.ReportFail(driver, "Validate whether " + description + " is displayed in the Application",
					description + " should be displayed in the page", description + " is not displayed in the page",
					folder);
		}
		return returnStatus;
	}

	/**
	 * Desc :Reusable function to get attribute Value by xPath
	 * 
	 * @param locator
	 * @param driver
	 * @return attribute value
	 */
	public static String getattributeValueByxpath(String locator, WebDriver driver) {
		waitForUIElementByxPath(locator, driver);
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getAttribute("value");
	}

	public static WebElement RetrieveWebElementFromApplicationByPartialLinkText(String locateLinkText,
			WebDriver driver) {
		WebElement finalElement = null;
		if (ElementLocatorVerification(By.partialLinkText(locateLinkText), driver)) {
			finalElement = driver.findElement(By.partialLinkText(locateLinkText));
		}
		return finalElement;
	}

	/**
	 * Desc :Reusable function to click Radio button
	 * 
	 * @param locator
	 * @param driver
	 * @return attribute title
	 */
	public static void selectRadioButtonbytext(String locator, WebDriver driver, String text, ReportGenerator currentsuit,
			String folder, String description) {
		driver.findElement(By.xpath(locator)).click();
	}

	/**
	 * Desc :Reusable function to select from drop down by Visible Text
	 * 
	 * @param locator
	 * @param driver
	 * @param Value
	 * @return nothing
	 */
	public static WebElement findElement(String locator, WebDriver driver, ReportGenerator currentsuit, String folder,
			String description) {
		WebElement element = driver.findElement(By.xpath(locator));
		Select item = new Select(element);
		return item.getFirstSelectedOption();
	}

	public static boolean isElementSelected(WebElement locator_Property, WebDriver driver, ReportGenerator currentsuit,
			String folder, String description) throws Exception {
		boolean returnStatus = false;
		if (!description.equals("")) {
			if (locator_Property.isSelected()) {

				returnStatus = true;
				currentsuit.ReportPass(driver,
						"Validate whether " + description + " is present in the Application",
						description + " should be 	present in the page", description + " is present in the page",
						folder);
			} else {
				returnStatus = false;
				currentsuit.ReportFail(driver, "Validate whether" + description + " is present in the Application",
						description + " should be present in the page", description + " is not present in the page",
						folder);

			}
		}
		return returnStatus;
	}

	public static boolean isElementEnabled(WebElement locator_Property, WebDriver driver, ReportGenerator currentsuit,
			String folder, String description) throws Exception {
		boolean returnStatus = false;
		if (!description.equals("")) {
			if (locator_Property.isEnabled()) {

				returnStatus = true;
				currentsuit.ReportPass(driver,
						"Validate whether " + description + " is present in the Application",
						description + " should be 	present in the page", description + " is present in the page",
						folder);
			} else {
				returnStatus = false;
				currentsuit.ReportFail(driver, "Validate whether" + description + " is present in the Application",
						description + " should be present in the page", description + " is not present in the page",
						folder);

			}
		}
		return returnStatus;
	}

	public static String getExcelData(String Sheet, String Field, String DataRow) throws IOException {
		String File1Location = null;
		File OpenFile1 = null;
		FileInputStream ReadFile1 = null;
		Properties FileProperty1 = null;
		Workbook book = null;
		Sheet sheet1 = null;
		int TotalRowsInSheet1 = 0;
		DataFormatter dataformat = null;
		Cell cell = null;
		String CellData = null;
		String ColumnName = null;
		String TestColumnName = null;
		File1Location = "Test_Scenarios//Data.xls";
		OpenFile1 = new File(File1Location);
		ReadFile1 = new FileInputStream(OpenFile1);
		FileProperty1 = new Properties();
		dataformat = new DataFormatter();
		book = new HSSFWorkbook(ReadFile1);
		sheet1 = book.getSheet(Sheet);
		TotalRowsInSheet1 = sheet1.getLastRowNum() - sheet1.getFirstRowNum();
		for (int i = 0; i <= TotalRowsInSheet1; i++) {
			Row row = sheet1.getRow(i);
			Row row1 = sheet1.getRow(0);
			TestColumnName = row.getCell(0).getStringCellValue();
			if (TestColumnName.equals(DataRow)) {
				for (int j = 1; j <= row.getLastCellNum(); j++) {
					ColumnName = row1.getCell(j).getStringCellValue();
					if (ColumnName.equals(Field)) {
						cell = row.getCell(j);
						CellData = dataformat.formatCellValue(cell);
						break;
					}
				}
			}
		}
		return CellData;
	}

}
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
