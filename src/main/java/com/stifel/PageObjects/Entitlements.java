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

public class Entitlements extends Browser_setup{
	
	SF_StandardLEX_Common_Navigations nc = new SF_StandardLEX_Common_Navigations();
	SF_StandardLEX_ActionOnWebElements lwe = new SF_StandardLEX_ActionOnWebElements();
	static String EntitlementNameID = null;
	public static String EntitlementURL = null;

	public static  String EntitlementName;
	

	
	public String CreateEntitlement(WebDriver driver, ReportGenerator suit, String folder, String sheetName, int sheetVal) throws Exception{
		
	// Random digit generator
		Random ran = new Random();
		int Ran4digit = ran.nextInt(9999);
		int Ran6digit = ran.nextInt(999999);

		SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);

		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);

		WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Submit Entitlement Request']"))).click();
        

//		driver.navigate().refresh();
		Thread.sleep(5000);
		
		EntitlementName = lwe.enterTextBoxWithRandomData(driver, "Subject", Ran6digit);
		System.out.println("Entitlement Name =:"+ EntitlementName);
		//Utilities.SetParameterFromInputSheet(sheetName, "Company Name Inserted", sheetVal, CompanyName);
		lwe.enterTextArea(driver, "Description");
		lwe.selectMultiPicklist(driver, "Aggregators Request");
		lwe.selectCheckbox(driver, "Bluematrix Library Access Request");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button/span[text()='Save'])[2]"))).click();
		
		Thread.sleep(10000);
		nc.SwitchTabsinLightning(driver, suit, folder, "Entitlements");
		Thread.sleep(5000);
		boolean Entitlement = driver.findElements(By.xpath("//div[@class='Subject textOverflow']//div[text()='"+EntitlementName+"']")).size()>0;
		if(Entitlement)
		{
			Common_Functions.ResultPass(driver, suit, folder, "Create Entitlement record", "Entitlement is created", "Entitlement record is created");
			
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Create Entitlement record", "Entitlement record is created", "Entitlement record is not created");
		
		}
		
		EntitlementURL = driver.getCurrentUrl();
		System.out.println(EntitlementURL);
		return EntitlementURL;
	}

	
}
