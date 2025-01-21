package com.stifel.LEXCommons;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.stifel.corefunctions.Common_Functions;
import com.stifel.corefunctions.ReportGenerator;
import com.stifel.corefunctions.Browser_setup;
import com.stifel.corefunctions.Utilities;

public class SF_StandardLEX_ActionOnWebElements extends Browser_setup {

	static String sheetName = null;
	static int sheetVal = 0;
	String expectedResult1 = "Request";
	String expectedResult2 = "In Negotiation";
	String expectedResult3 = "In Signature";

	SF_StandardLEX_Common_Navigations lcn = new SF_StandardLEX_Common_Navigations();

	public static void SetSheetDetails(String sheetName, int sheetVal) {

		SF_StandardLEX_ActionOnWebElements.sheetName = sheetName;
		SF_StandardLEX_ActionOnWebElements.sheetVal = sheetVal;

	}


	public void enterTextAreawithSectionName(WebDriver driver, String SectioName, String labelName) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);

		if (excelValue != null && !excelValue.isEmpty()) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getTextAreainSection(labelName, SectioName)))).clear();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getTextAreainSection(labelName, SectioName)))).sendKeys(excelValue);
		}

	}

	public void enterTextBox(WebDriver driver, String labelName) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);

		if (excelValue != null && !excelValue.isEmpty()) {
			lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.getTextBox(labelName), driver);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getTextBox(labelName)))).clear();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getTextBox(labelName)))).sendKeys(excelValue);
		}

	}

	public void enterTextBoxforSubfunc(WebDriver driver, String labelName) {

		WebDriverWait wait = new WebDriverWait(driver, 50);
		String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);

		if (excelValue != null && !excelValue.isEmpty()) {
			lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.getTextBoxforSubfunc(labelName), driver);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getTextBoxforSubfunc(labelName)))).clear();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getTextBoxforSubfunc(labelName)))).sendKeys(excelValue);
		}

	}


	public void enterTextArea(WebDriver driver, String labelName) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);

		if (excelValue != null && !excelValue.isEmpty()) {
			lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.getTextArea(labelName), driver);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getTextArea(labelName)))).clear();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getTextArea(labelName)))).sendKeys(excelValue);
		}

	}


	public void enterTextAreaforSubfunc(WebDriver driver, String labelName) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);

		if (excelValue != null && !excelValue.isEmpty()) {
			lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.getTextAreaforSubfunc(labelName), driver);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getTextAreaforSubfunc(labelName)))).clear();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getTextAreaforSubfunc(labelName)))).sendKeys(excelValue);
		}

	}


	public String enterTextBoxWithRandomData(WebDriver driver, String labelName, int randomInteger) throws ParseException, InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);
		String NewLegalName = null;
		if (excelValue != null && !excelValue.isEmpty()) {
			lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.getTextBox(labelName), driver);

			// long randomInteger = Generic_Functions.randomFunctionGenerator();
			NewLegalName = excelValue + randomInteger;
			Thread.sleep(8000);
			List<WebElement> lists = driver.findElements(By.xpath(SF_StandardLEX_WebElementLocators.getTextBox(labelName)));

			if (lists.size() > 1) {
				wait.until(ExpectedConditions.visibilityOf(lists.get(lists.size() - 1))).clear();
				wait.until(ExpectedConditions.visibilityOf(lists.get(lists.size() - 1))).sendKeys(NewLegalName);
			} else if (lists.size() == 1) {
				wait.until(ExpectedConditions.visibilityOf(lists.get(0))).clear();
				wait.until(ExpectedConditions.visibilityOf(lists.get(0))).sendKeys(NewLegalName);
			}
		}
		return NewLegalName;
	}


	public String enterTextBoxWithRandomData2(WebDriver driver, String labelName, int randomInteger) throws ParseException, InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);
		String NewLegalName = null;
		if (excelValue != null && !excelValue.isEmpty()) {
			lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.getTextBox3(labelName), driver);

			// long randomInteger = Generic_Functions.randomFunctionGenerator();
			NewLegalName = excelValue + randomInteger;
			Thread.sleep(8000);
			List<WebElement> lists = driver.findElements(By.xpath(SF_StandardLEX_WebElementLocators.getTextBox3(labelName)));

			if (lists.size() > 1) {
				wait.until(ExpectedConditions.visibilityOf(lists.get(lists.size() - 1))).clear();
				wait.until(ExpectedConditions.visibilityOf(lists.get(lists.size() - 1))).sendKeys(NewLegalName);
			} else if (lists.size() == 1) {
				wait.until(ExpectedConditions.visibilityOf(lists.get(0))).clear();
				wait.until(ExpectedConditions.visibilityOf(lists.get(0))).sendKeys(NewLegalName);
			}
		}
		return NewLegalName;
	}


	public String enterTextBoxWithRandomDataforSubFunc(WebDriver driver, String labelName, int randomInteger) throws ParseException, InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);
		String NewLegalName = null;
		if (excelValue != null && !excelValue.isEmpty()) {
			lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.getTextBoxforSubfunc(labelName), driver);

			// long randomInteger = Generic_Functions.randomFunctionGenerator();
			NewLegalName = excelValue + randomInteger;
			Thread.sleep(8000);
			List<WebElement> lists = driver.findElements(By.xpath(SF_StandardLEX_WebElementLocators.getTextBoxforSubfunc(labelName)));

			if (lists.size() > 1) {
				wait.until(ExpectedConditions.visibilityOf(lists.get(lists.size() - 1))).clear();
				wait.until(ExpectedConditions.visibilityOf(lists.get(lists.size() - 1))).sendKeys(NewLegalName);
			} else if (lists.size() == 1) {
				wait.until(ExpectedConditions.visibilityOf(lists.get(0))).clear();
				wait.until(ExpectedConditions.visibilityOf(lists.get(0))).sendKeys(NewLegalName);
			}
		}
		return NewLegalName;
	}

	public String enterTextBoxWithRandomAlphabeticalData(WebDriver driver, String labelName, String randomAlphabet) throws ParseException, InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);
		String NewLegalName = null;
		if (excelValue != null && !excelValue.isEmpty()) {
			lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.getTextBox(labelName), driver);
			// long randomInteger = Generic_Functions.randomFunctionGenerator();
			NewLegalName = excelValue + randomAlphabet;
			Thread.sleep(5000);
			List<WebElement> lists = driver.findElements(By.xpath(SF_StandardLEX_WebElementLocators.getTextBox(labelName)));
			Thread.sleep(5000);
			wait.until(ExpectedConditions.visibilityOf(lists.get(lists.size() - 1))).clear();
			wait.until(ExpectedConditions.visibilityOf(lists.get(lists.size() - 1))).sendKeys(NewLegalName);
//			if(lists.size()==1){
//				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LightningWebElementLocators.getTextBox(labelName)))).clear();
//				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LightningWebElementLocators.getTextBox(labelName)))).sendKeys(NewLegalName);
//			}
//			else if(lists.size()>1)
//			{
//				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("("+LightningWebElementLocators.getTextBox(labelName)+")["+lists.size()+"]"))).clear();
//				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("("+LightningWebElementLocators.getTextBox(labelName)+")["+lists.size()+"]"))).sendKeys(NewLegalName);
//			}

		}
		return NewLegalName;
	}

	public String selectPicklist(WebDriver driver, String labelName) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);
		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		if (excelValue != null && !excelValue.isEmpty()) {

			lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.getPicklist(labelName), driver);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getPicklist(labelName)))).click();
			Thread.sleep(4000);

			//	System.out.println(labelName+":-:-");
			//List<WebElement> ele = driver.findElements(By.xpath("//div[contains(@class,'select-options') and contains(@class, 'popupTargetContainer') and contains(@class, 'visible')]"));
			List<WebElement> ele;
// System.out.println(labelName+":-:-");
			try {
				ele = driver.findElements(By.xpath("//div[contains(@class,'select-options') and contains(@class,'popupTargetContainer') and contains(@class,'visible')]"));
			} catch (Exception e) {
				try {
					ele = driver.findElements(By.xpath("//div[contains(@class,'select-options') and contains(@class,'popupTargetContainer')]//preceding-sibling::div[@class='uiPopupTrigger']"));
				} finally {
					JavascriptExecutor jse = (JavascriptExecutor) driver;
					jse.executeScript("arguments[0].click()", driver.findElement(By.xpath("//div[contains(@class,'select-options') and contains(@class,'popupTargetContainer')]//preceding-sibling::div[@class='uiPopupTrigger']")));
				}
			}
			for (WebElement x : ele) {
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click()", x.findElement(By.xpath(".//ul/li/a[text()='" + excelValue + "']")));


			}

		}
		return excelValue;
	}


	public String selectOtherPicklist(WebDriver driver, String labelName) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);
		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		if (excelValue != null && !excelValue.isEmpty()) {

			lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.getOtherPicklist(labelName), driver);

			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getOtherPicklist(labelName)))).click();
			Thread.sleep(4000);

			//	System.out.println(labelName+":-:-");
			//List<WebElement> ele = driver.findElements(By.xpath("//Select[@name='Role']//option"));
			WebElement ele = driver.findElement(By.xpath("//Select[@name='"+labelName+"']"));
			Thread.sleep(4000);
			Select sle=new Select(ele);
			sle.selectByValue(excelValue);


			/*for (WebElement x : list) {
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click()", x.findElement(By.xpath("//Select[@name='Role']//option[text()='"+excelValue+"']")));



			}*/

		}
		return excelValue;
	}


	public String selectPicklistforSubfunc(WebDriver driver, String labelName) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 30);


		String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);
		if (excelValue != null && !excelValue.isEmpty()) {
			lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.getPicklistforSubfunc(labelName), driver);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getPicklistforSubfunc(labelName)))).click();
			Thread.sleep(3000);

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click()", wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='" + labelName + "']/..//div[@role='listbox']//span[text()='" + excelValue + "']"))));

		}
		return excelValue;
	}


	public String selectPicklistforSubfunc2(WebDriver driver, String labelName) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 30);


		String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);
		if (excelValue != null && !excelValue.isEmpty()) {
			lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.getPicklistforSubfunc2(labelName), driver);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click()", wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getPicklistforSubfunc2(labelName)))));

			Thread.sleep(3000);
			jse.executeScript("arguments[0].click()", wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='" + labelName + "']/..//div[@role='listbox']//span[text()='" + excelValue + "']"))));

		}
		return excelValue;
	}


	public void enterTextBox2(WebDriver driver, String labelName) {

		WebDriverWait wait = new WebDriverWait(driver, 50);
		String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);

		if (excelValue != null && !excelValue.isEmpty()) {
			lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.getTextBox2(labelName), driver);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getTextBox2(labelName)))).clear();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getTextBox2(labelName)))).sendKeys(excelValue);
		}

	}


	public String selectPicklist2(WebDriver driver, String labelName) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);
		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		if (excelValue != null && !excelValue.isEmpty()) {
			lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.getPicklist2(labelName), driver);
			if (labelName != "What's At Risk?") {

				Actions action = new Actions(driver);
				WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getPicklist2(labelName))));
				js.executeScript("arguments[0].click();", e);

				Thread.sleep(3000);
				List<WebElement> ele = driver.findElements(By.xpath("//div[contains(@class,'select-options') and contains(@class, 'popupTargetContainer') and contains(@class, 'visible')]"));
				for (WebElement x : ele) {
					WebElement e1 = x.findElement(By.xpath(".//ul/li/a[text()='" + excelValue + "']"));
					js.executeScript("arguments[0].click();", e1);

				}
			} else {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='At Risk Information']/parent::*/parent::*/div/div/div[2]//a"))).click();
				Thread.sleep(2000);

				List<WebElement> ele = driver.findElements(By.xpath("//div[contains(@class,'select-options') and contains(@class, 'popupTargetContainer') and contains(@class, 'visible')]"));

				for (WebElement x : ele) {

					x.findElement(By.xpath(".//ul/li/a[text()='" + excelValue + "']")).click();

				}

			}

		}
		return excelValue;
	}


	public void selectMandatoryPicklist(WebDriver driver, String labelName) throws InterruptedException {
		String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);
		if (excelValue != null && !excelValue.isEmpty()) {
			lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.getPicklist(labelName), driver);

			Thread.sleep(6000);
			lcn.WaitUntilElementIsVisibleAndClickUsingXpath(SF_StandardLEX_WebElementLocators.getPicklist(labelName), driver);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LightningWebElementLocators.getPicklist(labelName)))).click();
			Thread.sleep(4000);

			List<WebElement> ele = driver.findElements(By.xpath("//div[contains(@class,'select-options') and contains(@class, 'popupTargetContainer') and contains(@class, 'visible')]"));
			for (WebElement x : ele) {
				x.findElement(By.xpath(".//ul/li/a[text()='" + excelValue + "']")).click();
			}
		}

	}


	public void selectTodaysDate2(WebDriver driver, String labelName) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);

		if (excelValue != null && !excelValue.isEmpty()) {
			lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.getDateIcon2(labelName), driver);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getDateIcon2(labelName)))).click();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LightningWebElementLocators.getDateSelection()))).click();
			List<WebElement> dateLinks = driver.findElements(By.xpath(SF_StandardLEX_WebElementLocators.getDateSelection()));

			js.executeScript("arguments[0].click();", dateLinks.get(dateLinks.size() - 1));

			//dateLinks.get(dateLinks.size()-1).click();
		}
	}

	public void selectDropdown(WebDriver driver, String label, String str) throws InterruptedException {
		WebElement wb = driver.findElement(By.xpath("//select[@name='" + label + "']"));
		Select sel1 = new Select(wb);
		sel1.selectByVisibleText(str);
		Thread.sleep(5000);
	}

	public void selectMultiPicklist(WebDriver driver, String labelName) throws InterruptedException {

		String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);

		if (excelValue != null && !excelValue.isEmpty()) {
			lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.getMultiPicklist(labelName), driver);
			SF_StandardLEX_WebElementLocators.SelectScrollableValueinMultiPicklist(driver, labelName, excelValue);
//			LightningWebElementLocators.SelectValueinMultiPicklist(driver, labelName, excelValue);																							 

		}

	}

	public void selectMultiPicklistinSection(WebDriver driver, String labelName, String sectioname) throws Exception {

		String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);

		if (excelValue != null && !excelValue.isEmpty()) {
			//LightningWebElementLocators.SelectScrollableValueinMultiPicklist(driver, labelName, excelValue);
			SF_StandardLEX_WebElementLocators.SelectValueinMultiPicklistinSection(driver, labelName, excelValue, sectioname);
		}

	}


	public void selectTodaysDate(WebDriver driver, String labelName) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);

		if (excelValue != null && !excelValue.isEmpty()) {
			lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.getDateIcon(labelName), driver);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getDateIcon(labelName)))).click();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LightningWebElementLocators.getDateSelection()))).click();
			List<WebElement> dateLinks = driver.findElements(By.xpath(SF_StandardLEX_WebElementLocators.getDateSelection()));
			dateLinks.get(dateLinks.size() - 1).click();
		}
	}


	public void enterDate(WebDriver driver, String labelName) {

		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);

		if (excelValue != null && !excelValue.isEmpty()) {
			lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.getDateBox(labelName), driver);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getDateBox(labelName)))).clear();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getDateBox(labelName)))).sendKeys(formatter.format(date));
		}

	}


	public void enterCustomDate(WebDriver driver, String labelName) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);


		if (excelValue != null && !excelValue.isEmpty()) {
			lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.getDateBox(labelName), driver);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getDateBox(labelName)))).clear();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getDateBox(labelName)))).sendKeys(excelValue);
		}
	}


	public void enterDateforSubfunc(WebDriver driver, String labelName) {

		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);

		if (excelValue != null && !excelValue.isEmpty()) {
			lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.getDateBoxforSubfunc(labelName), driver);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getDateBoxforSubfunc(labelName)))).clear();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getDateBoxforSubfunc(labelName)))).sendKeys(formatter.format(date));
		}

	}


	public void enterCustomDateforSubfunc(WebDriver driver, String labelName) throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);

		if (excelValue != null && !excelValue.isEmpty()) {
			lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.getDateBoxforSubfunc(labelName), driver);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getDateBoxforSubfunc(labelName)))).clear();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getDateBoxforSubfunc(labelName)))).sendKeys(excelValue);
			Thread.sleep(5000);
		}
	}


	public void selectLookupValue(WebDriver driver, String labelName) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 50);
		String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);
		//JavascriptExecutor js= (JavascriptExecutor) driver;
		if (excelValue != null && !excelValue.isEmpty()) {
			lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.getLookupInputBox(labelName), driver);
			Thread.sleep(3000);
			List<WebElement> ValuePresent = driver.findElements(By.xpath(SF_StandardLEX_WebElementLocators.getLookupvalueDeleteicon(labelName)));
			if (ValuePresent.size() > 0) {
				for (WebElement E : ValuePresent) {
					Thread.sleep(3000);
					E.click();
					Thread.sleep(3000);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getLookupInputBox(labelName)))).clear();
					Thread.sleep(2000);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getLookupInputBox(labelName)))).sendKeys(excelValue);
					Thread.sleep(2000);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'\"" + excelValue + "\" in')]//ancestor::div[contains(@class,'searchButton')]"))).click();
					Thread.sleep(2000);
					WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.lookupValueOnDetailScreen(excelValue))));
					e.click();


					break;
				}
			} else {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getLookupInputBox(labelName)))).clear();
				Thread.sleep(1000);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getLookupInputBox(labelName)))).sendKeys(excelValue);
				Thread.sleep(3000);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'\"" + excelValue + "\" in')]//ancestor::div[contains(@class,'searchButton')]"))).click();
				Thread.sleep(1000);
				lcn.WaitUntilElementIsVisibleAndClickUsingXpath(SF_StandardLEX_WebElementLocators.lookupValueOnDetailScreen(excelValue), driver);
                   /* WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LightningWebElementLocators.lookupValueOnDetailScreen(excelValue))));
                    js.executeScript("arguments[0].click();", e); */

			}

		}
	}


	public void selectOtherLookupValue(WebDriver driver, String labelName) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 50);
		String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);
		//JavascriptExecutor js= (JavascriptExecutor) driver;
		if (excelValue != null && !excelValue.isEmpty()) {
			lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.getOtherLookupInputBox(labelName), driver);
			Thread.sleep(3000);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getOtherLookupInputBox(labelName)))).click();
			//Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getOtherLookupInputBox(labelName)))).sendKeys(excelValue);
			Thread.sleep(5000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getOtherLookupInputBox(labelName)))).clear();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getOtherLookupInputBox(labelName)))).click();
			//Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getOtherLookupInputBox(labelName)))).sendKeys(excelValue);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@role='option']//span[@class='slds-media__body'])[1]"))).click();
			//Thread.sleep(3000);
			WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.lookupValueOnDetailScreen3(excelValue))));
			Thread.sleep(5000);
			try {
				/*JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click();", e);*/
				//Thread.sleep(2000);
				if(e.isDisplayed()) {
					e.click();
				}
				else{
					JavascriptExecutor jse = (JavascriptExecutor) driver;
					jse.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@role='option']//span//following-sibling::span[contains(@class,'option')]")));
					Thread.sleep(2000);
				}
			}
			catch(Exception exc) {
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@role='option']//span//following-sibling::span[contains(@class,'option')]")));
				Thread.sleep(2000);
			}
			Thread.sleep(2000);

	}

			else

	{
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getLookupInputBox(labelName)))).clear();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getLookupInputBox(labelName)))).sendKeys(excelValue);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'\"" + excelValue + "\" in')]//ancestor::div[contains(@class,'searchButton')]"))).click();
		Thread.sleep(1000);
		lcn.WaitUntilElementIsVisibleAndClickUsingXpath(SF_StandardLEX_WebElementLocators.lookupValueOnDetailScreen(excelValue), driver);
                   /* WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LightningWebElementLocators.lookupValueOnDetailScreen(excelValue))));
                    js.executeScript("arguments[0].click();", e); */

	}

}


	public void selectSuggestionValue(WebDriver driver, String labelName) throws InterruptedException{
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);
		
		if (excelValue != null && !excelValue.isEmpty()) {
		driver.findElement(By.xpath("//div[text()='"+labelName+"']/following-sibling::div//input")).sendKeys(excelValue);
		Thread.sleep(10000);
		
		WebElement element = driver.findElement(By.xpath("//span[contains(text(),'"+excelValue+"')]"));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", element);
		}
	}

	
	public void selectLookupValueforSubfunc(WebDriver driver, String labelName) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 50);
		//String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);
		String excelValue=labelName;
		//JavascriptExecutor js= (JavascriptExecutor) driver;
		if (excelValue != null && !excelValue.isEmpty()) {
			lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.getLookupInputBoxforSubfunc(labelName), driver);
			Thread.sleep(3000);
			List<WebElement> ValuePresent = driver.findElements(By.xpath(SF_StandardLEX_WebElementLocators.getLookupvalueDeleteiconforSubfunc(labelName)));
			if (ValuePresent.size() > 0) {
				for (WebElement E : ValuePresent) {
					Thread.sleep(3000);
					E.click();
					Thread.sleep(3000);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getLookupInputBoxforSubfunc(labelName)))).clear();
					Thread.sleep(2000);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getLookupInputBoxforSubfunc(labelName)))).sendKeys(excelValue, Keys.TAB, Keys.ENTER);
					Thread.sleep(2000);
	                    WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.lookupValueOnDetailScreen(excelValue))));
	                    e.click(); 
	 

					break;
				}
			}

				else {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getLookupInputBoxforSubfunc(labelName)))).clear();
				Thread.sleep(2000);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getLookupInputBoxforSubfunc(labelName)))).sendKeys(excelValue);
				Thread.sleep(4000);
				
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("arguments[0].click()",wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='"+labelName+"']/following-sibling::div//*[@data-value='actionAdvancedSearch']"))));
				//jse.executeScript("arguments[0].click()",wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Show All Results for')]/ancestor::*[@data-value='actionAdvancedSearch']"))));
				//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Show All Results for')]/ancestor::*[@data-value='actionAdvancedSearch']"))).sendKeys(Keys.ENTER);
				
				Thread.sleep(4000);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.lookupValueOnDetailScreen(excelValue)))).click();
                   /* WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LightningWebElementLocators.lookupValueOnDetailScreen(excelValue))));
                    js.executeScript("arguments[0].click();", e); */
 
				}

		}
	}
	
	
	
	
	public void selectSuggestionValue2(WebDriver driver, String labelName) throws InterruptedException{
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);
		
		if (excelValue != null && !excelValue.isEmpty()) {
			lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.getLookupInputBoxforSubfunc(labelName), driver);
			Thread.sleep(3000);
			
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getLookupInputBoxforSubfunc(labelName)))).sendKeys(excelValue);
			Thread.sleep(4000);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getLookupInputBoxforSubfunc(labelName)))).sendKeys(Keys.RETURN);
			
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Show All Results for')]/ancestor::*[@data-value='actionAdvancedSearch']"))).sendKeys(Keys.ENTER);
			
			Thread.sleep(4000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.lookupValueOnDetailScreen2(excelValue)))).click();

		}
		
	}

	


	public void selectCheckbox(WebDriver driver, String labelName) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.getCheckbox(labelName), driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getCheckbox(labelName)))).click();

	}
	
	
	public void selectCheckboxforSubfunc(WebDriver driver, String labelName) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.getCheckboxforSubfunc(labelName), driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getCheckboxforSubfunc(labelName)))).click();

	}
	
	
	public void selectCheckboxforSubfunc2(WebDriver driver, String labelName) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.getCheckboxforSubfunc2(labelName), driver);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("arguments[0].click();",wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getCheckboxforSubfunc2(labelName)))));

	}

	

	public void clickElement(WebDriver driver, String xpathOfElement) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathOfElement))).click();

	}

	// Azman - new
	public static void pressTab(WebDriver driver) throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
	}

	// Pawan - Click Tab on screem
	public void clickMainTabonScreen(WebDriver driver, String TabName) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.GetMAinTabs(TabName)))).click();
	}

	public String GetViewScreentext(WebDriver driver, String LabelName) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.gettextElementOnViewScreen(LabelName), driver);
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.gettextElementOnViewScreen(LabelName)))).getText();
		return text;
	}

	public String GetViewScreenlink(WebDriver driver, String LabelName) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.gettextElementOnViewScreen(LabelName), driver);
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getlinkElementOnViewScreen(LabelName)))).getText();
		return text;
	}
	
	public void scrollToElement(WebDriver driver, WebElement element) {
		WebElement targetElement = null;
		int count = 1, sign = 1;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		while (true) {
			js.executeScript("window.scrollBy(0," + (200 * sign) + ")");
			try {
				targetElement = wait.until(ExpectedConditions.visibilityOf(element));// driver.findElement(By.xpath(elementXPath));
			} catch (Exception ex) {
				continue;
			}
			if (targetElement != null) {
				System.out.println("Element Found");
				break;
			}
			if (count > 6) {
				System.out.println("Could not find the element");
				break;
			}

			count++;
			if (count >= 3)
				sign = -1;
		}

	}

	public void scrollByPixels(WebDriver driver, int pixelValue) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + pixelValue + ")");
		Thread.sleep(5000);
	}
	

		public String enterInputTextBoxWithRandomData(WebDriver driver, String labelName, int randomInteger) throws Exception {

			WebDriverWait wait = new WebDriverWait(driver, 30);
			String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);
			
			if (excelValue != null && !excelValue.isEmpty()) {
				excelValue = excelValue + randomInteger;
				Thread.sleep(3000);
				lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.getInputTextBox(labelName), driver);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getInputTextBox(labelName)))).clear();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getInputTextBox(labelName)))).sendKeys(excelValue);
				Thread.sleep(3000);		
			}
			return excelValue;
		}
		
		
		public void enterSpecifiedDate(WebDriver driver, String labelName) throws Exception {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);
			Thread.sleep(4000);
			
			if (excelValue != null && !excelValue.isEmpty()) {
				lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.getDateBox(labelName), driver);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getDateBox(labelName)))).clear();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getDateBox(labelName)))).sendKeys(excelValue);
				Thread.sleep(4000);
			}

		}
		
		
		public void enterInputTextBox(WebDriver driver, String labelName) throws Exception {

			WebDriverWait wait = new WebDriverWait(driver, 30);
			String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);
			
			if (excelValue != null && !excelValue.isEmpty()) {
				lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.getInputTextBox(labelName), driver);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getInputTextBox(labelName)))).clear();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getInputTextBox(labelName)))).sendKeys(excelValue);
				Thread.sleep(3000);		
			}
		}
		
		
		public void ClickFieldEditButton(WebDriver driver, String labelName) {
			
			WebDriverWait wait = new WebDriverWait(driver, 30);
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			
	    	js.executeScript("arguments[0].click();", wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='"+ labelName +"']/parent::div/following-sibling::div/button"))));
		}
		
		
		public String getAlphaNumericString()
        {
     
            // chose a Character random from this String
            String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                        + "0123456789"
                                        + "abcdefghijklmnopqrstuvxyz";
     
            // create StringBuffer size of AlphaNumericString
            StringBuilder sb = new StringBuilder(10);
     
            for (int i = 0; i < 10; i++) {
     
                // generate a random number between
                // 0 to AlphaNumericString variable length
                int index
                    = (int)(AlphaNumericString.length()
                            * Math.random());
     
                // add Character one by one in end of sb
                sb.append(AlphaNumericString
                              .charAt(index));
            }
     
            return sb.toString();
        }


		public void selectLookupValue1(WebDriver driver, String sheetName,String labelName, int sheetVal) throws InterruptedException {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);
            //JavascriptExecutor js= (JavascriptExecutor) driver;
            if (excelValue != null && !excelValue.isEmpty()) {
                lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.getLookupInputBox(labelName), driver);
                Thread.sleep(3000);
                List<WebElement> ValuePresent = driver.findElements(By.xpath(SF_StandardLEX_WebElementLocators.getLookupvalueDeleteicon(labelName)));
                if (ValuePresent.size() > 0) {
                    for (WebElement E : ValuePresent) {
                        Thread.sleep(3000);
                        E.click();
                        Thread.sleep(3000);
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getLookupInputBox(labelName)))).clear();
                        Thread.sleep(2000);
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getLookupInputBox(labelName)))).sendKeys(excelValue);
                        Thread.sleep(2000);
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[contains(text(),'\""+excelValue+"\" in')]//ancestor::div[contains(@class,'searchButton')])[1]"))).click();
                            Thread.sleep(2000);
                            WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.lookupValueOnDetailScreen(excelValue))));
                            e.click(); 
                           Thread.sleep(5000);

 

                        break;
                    }
                }

 

                    else {
                    Thread.sleep(2000);
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getLookupInputBox(labelName)))).clear();
                    Thread.sleep(1000);
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getLookupInputBox(labelName)))).sendKeys(excelValue);
                    Thread.sleep(3000);
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[contains(text(),'\""+excelValue+"\" in')]//ancestor::div[contains(@class,'searchButton')])[1]"))).click();
                        Thread.sleep(1000);
                        lcn.WaitUntilElementIsVisibleAndClickUsingXpath(SF_StandardLEX_WebElementLocators.lookupValueOnDetailScreen(excelValue), driver);
                       /* WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LightningWebElementLocators.lookupValueOnDetailScreen(excelValue))));
                        js.executeScript("arguments[0].click();", e); */
     
                    }
            }
        }
		
		
		
		public String selectPicklist1(WebDriver driver,String labelName,String excelValue) throws Exception {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            //String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);
            SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
            if (excelValue != null && !excelValue.isEmpty()) {
                
                lcn.WaitUntilElementIsVisible(SF_StandardLEX_WebElementLocators.getPicklist(labelName), driver);    
                
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getPicklist(labelName)))).click();
                    Thread.sleep(4000);
                    
                //    System.out.println(labelName+":-:-");
                    List<WebElement> ele = driver.findElements(By.xpath("//div[contains(@class,'select-options') and contains(@class, 'popupTargetContainer') and contains(@class, 'visible')]"));
                for(WebElement x: ele)
                {
                    JavascriptExecutor jse = (JavascriptExecutor)driver;
                    jse.executeScript("arguments[0].click()", x.findElement(By.xpath(".//ul/li/a[text()='"+excelValue+"']")));

                }

            }
            return excelValue;
        }


}