package com.stifel.PageObjects;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.stifel.LEXCommons.SF_StandardLEX_ActionOnWebElements;
import com.stifel.LEXCommons.SF_StandardLEX_Common_Navigations;
import com.stifel.LEXCommons.SF_StandardLEX_WebElementLocators;
import com.stifel.corefunctions.Browser_setup;
import com.stifel.corefunctions.Common_Functions;
import com.stifel.corefunctions.ReportGenerator;
import com.stifel.corefunctions.Utilities;

public class SecurityMaster_Interest extends Browser_setup{
	
	SF_StandardLEX_Common_Navigations nc = new SF_StandardLEX_Common_Navigations();
	SF_StandardLEX_ActionOnWebElements lwe = new SF_StandardLEX_ActionOnWebElements();
	static String SecurityMasterNameID = null;
	public static String SecurityMasterURL = null;

	public static  String SecurityMasterName;
	
	public void ClickSecurityMasterTab(WebDriver driver,ReportGenerator suit, String folder) throws Exception {
		driver.navigate().refresh();
//		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
//		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
		Thread.sleep(10000);
		nc.SearchandClickObjectTab(driver,suit, folder, "Security Master/Interest");
		Thread.sleep(10000);
	}
	
	public void ClickSecurityMasterNewbutton(WebDriver driver) throws Exception {

		 WebDriverWait wait = new WebDriverWait(driver,30);
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a/div[text()='New']"))).click();
		 Thread.sleep(5000);

	}
	
	public String CreateSecurityMasterInterest(WebDriver driver, ReportGenerator suit, String folder, String sheetName, int sheetVal) throws Exception{
		
	// Random digit generator
		Random ran = new Random();
		int Ran4digit = ran.nextInt(9999);
		int Ran6digit = ran.nextInt(999999);

		SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);

		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);

		WebDriverWait wait = new WebDriverWait(driver, 50);

		String AccRecordType = Utilities.getParameterFromInputSheet(sheetName, "Record type", sheetVal);
		//Thread.sleep(10000);
		
		boolean accTypePresent = driver.findElements(By.xpath("//span[@class=' label bBody' and text()='Next']")).size()!=0;
		
		if (accTypePresent) {
			if (AccRecordType != null && !AccRecordType.isEmpty()) {

				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getRadioButton(AccRecordType)))).click();

				wait.until(ExpectedConditions.visibilityOf(LightningWE.NextBtn_xpath)).click();

				//Generic_Functions.waitForLoad(driver, 80);
			}
		}
//		driver.navigate().refresh();
		Thread.sleep(5000);
		
		SecurityMasterName = lwe.enterTextBoxWithRandomData(driver, "Security Master/Interest Name", Ran6digit);
		System.out.println("SecurityMaster Name =:"+ SecurityMasterName);
		//Utilities.SetParameterFromInputSheet(sheetName, "Company Name Inserted", sheetVal, CompanyName);
		lwe.selectLookupValue(driver, "Parent Sector");
		lwe.selectMandatoryPicklist(driver, "Region");
		lwe.enterTextBox(driver, "Ticker");
		lwe.enterTextBox(driver, "Country");
		lwe.enterTextBox(driver, "Market Cap");
		lwe.selectCheckbox(driver, "Active");
		lwe.selectLookupValue(driver, "Research Analyst");
		lwe.selectLookupValue(driver, "Companyr");
		lwe.selectLookupValue(driver, "Sector Company Sector");
		lwe.selectCheckbox(driver, "Stifel");
		lwe.selectCheckbox(driver, "KBW");
		
		wait.until(ExpectedConditions.visibilityOf(LightningWE.SaveBtn_xpath)).click();
		Thread.sleep(10000);
		boolean SecMastername = driver.findElements(By.xpath("//lightning-formatted-text[text()='"+SecurityMasterName+"']")).size()>0;
		if(SecMastername)
		{
			Common_Functions.ResultPass(driver, suit, folder, "Create Security Master record", "Security Master record is created", "Security Master record is created");
			System.out.println("Account Created succcessfully");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Create Security Master record", "Security Master record is created", "Security Master record is not created");
			System.out.println("Account creation failed");
		}
		
		SecurityMasterURL = driver.getCurrentUrl();
		System.out.println(SecurityMasterURL);
		return SecurityMasterURL;
	}

	
}
