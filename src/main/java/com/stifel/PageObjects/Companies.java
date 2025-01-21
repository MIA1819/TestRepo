package com.stifel.PageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

public class Companies extends Browser_setup {

	SF_StandardLEX_Common_Navigations nc = new SF_StandardLEX_Common_Navigations();
	SF_StandardLEX_ActionOnWebElements lwe = new SF_StandardLEX_ActionOnWebElements();
	static String companyID = null;
	public static String companyURL = null;

	public String CompanyName;

	public void ClickCompaniesTab(WebDriver driver, ReportGenerator suit, String folder) throws Exception {
		driver.navigate().refresh();
		Thread.sleep(10000);
		nc.SearchandClickObjectTab(driver, suit, folder, "Companies");
	}

	public void ClickCompaniesNewbutton(WebDriver driver) throws Exception {
		Actions a = new Actions(driver);

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);

		List<WebElement> ele = driver.findElements(By.xpath("//a/div[text()='New']"));
		WebElement ele2 = ele.get(0);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", ele2);
		Thread.sleep(10000);
	}

	public void ClickCompaniesEditbutton(WebDriver driver) throws Exception {
		// driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//lightning-button/button[@name='Edit']"))).click();
		Thread.sleep(10000);
	}

	public String CreateCompanies(WebDriver driver, ReportGenerator suit, String folder, String sheetName, int sheetVal)
			throws Exception {

		// Random digit generator
		Random ran = new Random();
		int Ran3digit = ran.nextInt(999);
		int Ran6digit = ran.nextInt(999999);

		SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);

		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);

		WebDriverWait wait = new WebDriverWait(driver, 50);

		String AccRecordType = Utilities.getParameterFromInputSheet(sheetName, "Record type", sheetVal);
		// Thread.sleep(10000);

		boolean accTypePresent = driver.findElements(By.xpath("//span[@class=' label bBody' and text()='Next']"))
				.size() != 0;

		if (accTypePresent) {
			if (AccRecordType != null && !AccRecordType.isEmpty()) {

				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath(SF_StandardLEX_WebElementLocators.getRadioButton(AccRecordType)))).click();
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click()",
						wait.until(ExpectedConditions.visibilityOf(LightningWE.NextBtn_xpath)));

			}
		}
		driver.navigate().refresh();
		Thread.sleep(3000);

		CompanyName = lwe.enterTextBoxWithRandomData(driver, "Company Name", Ran6digit);
		System.out.println("Company Name =:" + CompanyName);
		// Utilities.SetParameterFromInputSheet(sheetName, "Company Name Inserted",
		// sheetVal, CompanyName);
		lwe.selectMandatoryPicklist(driver, "Primary Industry Group");
		lwe.selectPicklist(driver, "Primary Sub-Sector");
		lwe.selectPicklist(driver, "Company Type");
		lwe.selectPicklist(driver, "Sub Type");
		lwe.selectPicklist(driver, "Tier");
		lwe.selectPicklist(driver, "IPREO Type");
		lwe.enterTextArea(driver, "Lead Industry Banker(s)");

		lwe.selectPicklist(driver, "Client Status (KBWI)");
		lwe.selectPicklist(driver, "Client Status (SNC)");
		lwe.selectPicklist(driver, "Client Status (SEBA/SNEL/KBWL)");

		lwe.selectLookupValue(driver, "Parent Company");
		lwe.selectPicklist(driver, "Billing Country");
		lwe.enterTextArea(driver, "Billing Street");
		lwe.enterTextBox(driver, "Billing City");
		lwe.selectPicklist(driver, "Billing State/Province");
		lwe.enterTextBox(driver, "Billing Zip/Postal Code");
		//lwe.selectPicklist(driver, "Record Status");
		lwe.selectPicklist(driver, "Coverage Status");
		lwe.selectPicklist(driver, "Primary Country");
		lwe.enterTextArea(driver, "Primary Street");
		lwe.enterTextBox(driver, "Primary City");
		lwe.selectPicklist(driver, "Primary State/Province");
		lwe.enterTextBoxWithRandomData(driver, "Primary Zip/Postal Code", Ran6digit);
		lwe.enterTextBox(driver, "Primary Company Type");
		lwe.enterTextBox(driver, "LEI");
		lwe.selectPicklist(driver, "Ipreo Primary Investment Style");
		lwe.enterTextBox(driver, "Portfolio Turnover");
		lwe.enterTextBoxWithRandomData(driver, "Tax Id", Ran6digit);
		lwe.enterTextArea(driver, "Description");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@title='Save']")));
		Thread.sleep(15000);
		boolean Company = driver.findElements(By.xpath("//span[text()='" + CompanyName + "']")).size()>=0;
		System.out.println("//span[text()='" + CompanyName + "']");
		if (Company) {
			Common_Functions.ResultPass(driver, suit, folder, "Create Account", "Account is created",
					"Account is created");
			System.out.println("Account Created succcessfully");
		} else {
			Common_Functions.ResultFail(driver, suit, folder, "Create Account", "Account is created",
					"Account is not created");
			System.out.println("Account creation failed");
		}

		companyURL = driver.getCurrentUrl();
		System.out.println(companyURL);
		return CompanyName;
	}

	public String EditCompanies(WebDriver driver, ReportGenerator suit, String folder, String sheetName, int sheetVal)
			throws Exception {

		// Random digit generator
		Random ran = new Random();
		int Ran6digit = ran.nextInt(999999);

		SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);

		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);

		WebDriverWait wait = new WebDriverWait(driver, 50);

//			driver.navigate().refresh();
//			Thread.sleep(10000);
		CompanyName = lwe.enterTextBoxWithRandomData(driver, "Company Name", Ran6digit);
		// System.out.println("Company Name =:"+ CompanyName);
		// Utilities.SetParameterFromInputSheet(sheetName, "Company Name Inserted",
		// sheetVal, CompanyName);
		lwe.selectMandatoryPicklist(driver, "Primary Industry Group");
		lwe.selectPicklist(driver, "Sub-Sector");
		lwe.selectPicklist(driver, "Company Type");
		lwe.selectPicklist(driver, "Sub Type");
		lwe.selectPicklist(driver, "Tier");
		lwe.selectPicklist(driver, "IPREO Type");
		lwe.enterTextArea(driver, "Lead Industry Banker(s)");

		lwe.selectPicklist(driver, "Client Status (KBWI)");
		lwe.selectPicklist(driver, "Client Status (SNC)");
		lwe.selectPicklist(driver, "Client Status (SEBA/SNEL/KBWL)");

		lwe.selectLookupValue(driver, "Parent Company");
		lwe.selectPicklist(driver, "Billing Country");
		lwe.enterTextBox(driver, "Billing Street");
		lwe.enterTextBox(driver, "Billing City");
		lwe.selectPicklist(driver, "Billing State/Province");
		lwe.enterTextBox(driver, "Billing Zip/Postal Code");
		lwe.selectPicklist(driver, "Record Status");
		lwe.selectPicklist(driver, "Coverage Status");
		lwe.selectPicklist(driver, "Primary Country");
		lwe.enterTextArea(driver, "Primary Street");
		lwe.enterTextBox(driver, "Primary City");
		lwe.selectPicklist(driver, "Primary State/Province");
		lwe.enterTextBoxWithRandomData(driver, "Primary Zip/Postal Code", Ran6digit);
		lwe.enterTextBox(driver, "Primary Company Type");
		lwe.enterTextBox(driver, "LEI");
		lwe.selectPicklist(driver, "Ipreo Primary Investment Style");
		lwe.enterTextBox(driver, "Portfolio Turnover");
		lwe.enterTextBoxWithRandomData(driver, "Tax Id", Ran6digit);
		lwe.enterTextArea(driver, "Description");
		lwe.enterTextArea(driver, "Rate Card Details (SN)");
		lwe.enterTextArea(driver, "Rate Card Details (KBW)");

		wait.until(ExpectedConditions.visibilityOf(LightningWE.SaveBtn_xpath)).click();
		Thread.sleep(15000);
		boolean Company = driver.findElements(By.xpath("//h1/div[text()='Company']")).size() > 0;

		if (Company) {
			Common_Functions.ResultPass(driver, suit, folder, "Update Account", "Account is Updated",
					"Account is Updated");
			// System.out.println("Account updated succcessfully");
		} else {
			Common_Functions.ResultFail(driver, suit, folder, "Update Account", "Account is Updated",
					"Account is not Updated");
			// System.out.println("Account updated failed");
		}

		companyURL = driver.getCurrentUrl();
		System.out.println(companyURL);
		return CompanyName;
	}

	public String Create_Sponsor_Portfolio_Relationships(WebDriver driver, ReportGenerator suit, String folder,
			String sheetName, int sheetVal) throws Exception {

		// Random digit generator
		Random ran = new Random();
		int Ran6digit = ran.nextInt(999999);

		SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);

		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);

		WebDriverWait wait = new WebDriverWait(driver, 50);
		nc.SwitchTabsinLightning(driver, suit, folder, "Contacts & Relationships");
		// Common_Functions.scrollIntoView(driver,
		// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Affiliated
		// Contacts']"))));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click()", wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//h2[text()='Sponsor Portfolio Relationships']/ancestor::div[3]//button[text()='New']"))));
		Thread.sleep(3000);

		lwe.selectLookupValueforSubfunc(driver, "Related Company");
		lwe.selectPicklistforSubfunc2(driver, "Company Type");
		lwe.selectPicklistforSubfunc2(driver, "Related Company Type");
		lwe.selectPicklistforSubfunc2(driver, "Status");
		lwe.enterDateforSubfunc(driver, "Date of Initial Investment");
		lwe.enterDateforSubfunc(driver, "End Date");
		//lwe.selectCheckboxforSubfunc(driver, "Controlling Party");
		lwe.enterTextAreaforSubfunc(driver, "Notes");
		lwe.enterTextAreaforSubfunc(driver, "Transactions/Acquisitions");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[text()='Save']")));
		Thread.sleep(15000);
		boolean CompanyAffiliation = driver.findElements(By.xpath("//h2[text()='Sponsor Portfolio Relationships']/parent::div/parent::div/parent::div/following-sibling::div[2]/table/tbody/tr[1]")).size() > 0;

		if (CompanyAffiliation) {
			Common_Functions.ResultPass(driver, suit, folder, "Update Account", "Account is Updated",
					"Account is Updated");
			// System.out.println("Account updated succcessfully");
		} else {
			Common_Functions.ResultFail(driver, suit, folder, "Update Account", "Account is Updated",
					"Account is not Updated");
			// System.out.println("Account updated failed");
		}

		companyURL = driver.getCurrentUrl();
		System.out.println(companyURL);
		return companyURL;
	}

	public void validateInteraction(WebDriver driver, ReportGenerator suit, String folder, String eventName) throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Marketing Event')]")));
		
		List<WebElement> ele = driver.findElements(By.xpath("//a[contains(text(),'Marketing Event')]"));
		if (ele.size() > 0) {
			Common_Functions.ResultPass(driver, suit, folder, "The Event should be present in the Interactions tab", "The event is present in the interactions tab",
					"The event is present in the interactions tab");
		} else {
			Common_Functions.ResultFail(driver, suit, folder, "The Event should be present in the Interactions tab", "The event is present in the interactions tab",
					"The event is not present in the interactions tab");
		}

	}
	
	public void validateInteraction1(WebDriver driver, ReportGenerator suit, String folder, String eventName) throws Exception {

		 

        WebDriverWait wait = new WebDriverWait(driver, 30);
        
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[text()='Approved Attendees']//parent::div//following-sibling::div//table//tbody//tr//td[3]//a[contains(@href,'/lightning')]"))));
        Thread.sleep(15000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Interactions']"))).click();
        Thread.sleep(5000);
        List<WebElement> ele = driver.findElements(By.xpath("//a[contains(text(),'Marketing Event')]"));
        if (ele.size() > 0) {
            Common_Functions.ResultPass(driver, suit, folder, "The Event should be present in the Interactions tab", "The event is present in the interactions tab",
                    "The event is present in the interactions tab");
        } else {
            Common_Functions.ResultFail(driver, suit, folder, "The Event should be present in the Interactions tab", "The event is present in the interactions tab",
                    "The event is not present in the interactions tab");
            }

 

    }
	
	public void ValidateTradingCommissionUSD(WebDriver driver, ReportGenerator suit, String folder) throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		String[] ExpectedHeaders = {"Entity","Category","Previous Day ($)","MTD ($)","YTD ($)","Rolling 12 Months ($)","Previous Year ($)"};		
		
		int i = 2;
		
		String Title;
		
		for(String s : ExpectedHeaders) {
			
			Title = driver.findElement(By.xpath("//strong[text()='Commissions (USD)']/parent::div/following-sibling::div[@class='infiniteScrollDiv']//table/thead/tr/th["+i+"]")).getAttribute("title").toString();
			
			if(s.equalsIgnoreCase(Title)) {
				Common_Functions.ResultPass(driver, suit, folder, "Validation of the Header", "Expected: "+s,
						"Actual: "+Title);
			}
			else {
				Common_Functions.ResultFail(driver, suit, folder, "Validation of the Header", "Expected: "+s,
						"Actual: "+Title);
			}
			
			i++;
			
		}
	}

		
	public void ValidateTradingCommissionGBP(WebDriver driver, ReportGenerator suit, String folder) throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 30);
			
		String[] ExpectedHeaders = {"Entity","Category","Previous Day (£)","MTD (£)","YTD (£)","Rolling 12 Months (£)","Previous Year (£)"};		
			
		int i = 2;
			
		String Title;
			
		for(String s : ExpectedHeaders) {
				
			Title = driver.findElement(By.xpath("//strong[text()='Commissions (GBP)']/parent::div/following-sibling::div[@class='infiniteScrollDiv']//table/thead/tr/th["+i+"]")).getAttribute("title").toString();
				
			if(s.equalsIgnoreCase(Title)) {
				Common_Functions.ResultPass(driver, suit, folder, "Validation of the Header", "Expected: "+s,
							"Actual: "+Title);
			}
			else {
				Common_Functions.ResultFail(driver, suit, folder, "Validation of the Header", "Expected: "+s,
							"Actual: "+Title);
			}
				
			i++;
		}
	}

		
		
	public void ValidateTradingCommissionEUR(WebDriver driver, ReportGenerator suit, String folder) throws Exception {

			WebDriverWait wait = new WebDriverWait(driver, 30);
				
			String[] ExpectedHeaders = {"Entity","Category","Previous Day (€)","MTD (€)","YTD (€)","Rolling 12 Months (€)","Previous Year (€)"};		
				
			int i = 2;
				
			String Title;
				
			for(String s : ExpectedHeaders) {
					
				Title = driver.findElement(By.xpath("//strong[text()='Commissions (EUR)']/parent::div/following-sibling::div[@class='infiniteScrollDiv']//table/thead/tr/th["+i+"]")).getAttribute("title").toString();
					
				if(s.equalsIgnoreCase(Title)) {
					Common_Functions.ResultPass(driver, suit, folder, "Validation of the Header", "Expected: "+s,
								"Actual: "+Title);
				}
				else {
					Common_Functions.ResultFail(driver, suit, folder, "Validation of the Header", "Expected: "+s,
								"Actual: "+Title);
				}
					
				i++;
			}

		}
		
		
	public void ValidateTradingCommissionCAD(WebDriver driver, ReportGenerator suit, String folder) throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 30);
			
		String[] ExpectedHeaders = {"Entity","Category","Previous Day (C$)","MTD (C$)","YTD (C$)","Rolling 12 Months (C$)","Previous Year (C$)"};		
			
		int i = 2;
			
		String Title;
			
		for(String s : ExpectedHeaders) {
				
			Title = driver.findElement(By.xpath("//strong[text()='Commissions (CAD)']/parent::div/following-sibling::div[@class='infiniteScrollDiv']//table/thead/tr/th["+i+"]")).getAttribute("title").toString();
				
			if(s.equalsIgnoreCase(Title)) {
				Common_Functions.ResultPass(driver, suit, folder, "Validation of the Header", "Expected: "+s,
							"Actual: "+Title);
			}
			else {
				Common_Functions.ResultFail(driver, suit, folder, "Validation of the Header", "Expected: "+s,
							"Actual: "+Title);
			}
				
			i++;
		}

	}
	
	
	
	public void ValidateTradingCommissionTableOrder(WebDriver driver, ReportGenerator suit, String folder) throws Exception{
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		List<WebElement> TradingCommissionHeader = driver.findElements(By.xpath("//strong[contains(text(),'Commissions')]"));
		
		ArrayList<String> str = new ArrayList<String>();
		
		for(WebElement e: TradingCommissionHeader)
        {
            str.add(e.getText());
        }
		
		if((str.get(0).equalsIgnoreCase("Commissions (USD)")) && (str.get(1).equalsIgnoreCase("Commissions (GBP)")) && (str.get(2).equalsIgnoreCase("Commissions (EUR)") && (str.get(3).equalsIgnoreCase("Commissions (CAD)")))){
			Common_Functions.ResultPass(driver, suit, folder, "Validation of the Commission tables", "Commission tables should be in order", "Commission tables is in order");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Validation of the Commission tables", "Commission tables should be in order", "Commission tables is NOT in order");
		}
		
	}
	
	

	
	public void SelectCommissionSummaryCurrency(WebDriver driver, ReportGenerator suit, String folder, String Currency) throws Exception{
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		driver.navigate().refresh();
		Thread.sleep(10000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li/a[text()='Revenues'])[1]"))));
		Thread.sleep(10000);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='slds-radio']//span[text()='"+Currency+"']"))).click();
		
		WebElement element = driver.findElement(By.xpath("//span[@class='slds-radio']//span[text()='"+Currency+"']/preceding-sibling::span"));
		
		if (driver.findElement(By.xpath("//div[contains(@class,'custom-size_values-column custom-table_header')]/div[1]/div[1]")).getText().contains(Currency)) {
			Common_Functions.ResultPass(driver, suit, folder, "Selection of Radio Button", "Radio button should be selected",
					"Radio button is selected");

		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Selection of Radio Button", "Radio button should be selected",
					"Radio button is not selected");
		}
	}
	
	
	public void ValidateSelectedCommissionSummaryCurrency(WebDriver driver, ReportGenerator suit, String folder, String SelectedCurrency) throws Exception {
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		driver.navigate().refresh();
		Thread.sleep(10000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li/a[text()='Revenues'])[1]"))));
		Thread.sleep(10000);
		
		if (driver.findElement(By.xpath("//div[contains(@class,'custom-size_values-column custom-table_header')]/div[1]/div[1]")).getText().contains(SelectedCurrency)) {
			Common_Functions.ResultPass(driver, suit, folder, "Verify selected Radio Button", "Selected Radio button should be correct",
					"Selected Radio button is correct");

		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Verify selected Radio Button", "Selected Radio button should be correct",
					"Selected Radio button is incorrect");
		}


	}
	
	
	public void ValidateBrackettedValues(WebDriver driver, ReportGenerator suit, String folder) throws Exception {
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		List<WebElement> ClientStatusFields = driver.findElements(By.xpath("//div[contains(@class,'no-utility-icon')]//span[contains(text(),'Client Status')]"));
		List<WebElement> TradingStatusFields = driver.findElements(By.xpath("//div[contains(@class,'no-utility-icon')]//span[contains(text(),'Trading Status')]"));
		
		if(ClientStatusFields.get(0).getText().contains("(KBWI)")) {
			Common_Functions.ResultPass(driver, suit, folder, "Verify Bracketted Value KBWI for Client Status", "(KBWI) should be present",
					"(KBWI) is present");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Verify Bracketted Value KBWI for Client Status", "(KBWI) should be present",
					"(KBWI) is NOT present");
		}
		
		if(ClientStatusFields.get(1).getText().contains("(SNC)")) {
			Common_Functions.ResultPass(driver, suit, folder, "Verify Bracketted Value KBWI for Client Status", "(SNC) should be present",
					"(SNC) is present");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Verify Bracketted Value KBWI for Client Status", "(SNC) should be present",
					"(SNC) is NOT present");
		}
		
		if(ClientStatusFields.get(2).getText().contains("(SEBA/SNEL/KBWL)")) {
			Common_Functions.ResultPass(driver, suit, folder, "Verify Bracketted Value KBWI for Client Status", "(SNEL/KBWL) should be present",
					"(SNEL/KBWL) is present");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Verify Bracketted Value KBWI for Client Status", "(SNEL/KBWL) should be present",
					"(SNEL/KBWL) is NOT present");
		}
		
		if(ClientStatusFields.get(3).getText().contains("(SNCN)")) {
			Common_Functions.ResultPass(driver, suit, folder, "Verify Bracketted Value KBWI for Client Status", "(SNCN) should be present",
					"(SNCN) is present");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Verify Bracketted Value KBWI for Client Status", "(SNCN) should be present",
					"(SNCN) is NOT present");
		}
		
		
		if(TradingStatusFields.get(0).getText().contains("(KBWI)")) {
			Common_Functions.ResultPass(driver, suit, folder, "Verify Bracketted Value KBWI for Trading Status", "(KBWI) should be present",
					"(KBWI) is present");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Verify Bracketted Value KBWI for Trading Status", "(KBWI) should be present",
					"(KBWI) is NOT present");
		}
		
		if(TradingStatusFields.get(1).getText().contains("(SNC)")) {
			Common_Functions.ResultPass(driver, suit, folder, "Verify Bracketted Value KBWI for Trading Status", "(SNC) should be present",
					"(SNC) is present");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Verify Bracketted Value KBWI for Trading Status", "(SNC) should be present",
					"(SNC) is NOT present");
		}
		
		if(TradingStatusFields.get(2).getText().contains("(SEBA/SNEL/KBWL)")) {
			Common_Functions.ResultPass(driver, suit, folder, "Verify Bracketted Value KBWI for Trading Status", "(SNEL/KBWL) should be present",
					"(SNEL/KBWL) is present");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Verify Bracketted Value KBWI for Trading Status", "(SNEL/KBWL) should be present",
					"(SNEL/KBWL) is NOT present");
		}
		
		if(TradingStatusFields.get(3).getText().contains("(SNCN)")) {
			Common_Functions.ResultPass(driver, suit, folder, "Verify Bracketted Value KBWI for Trading Status", "(SNCN) should be present",
					"(SNCN) is present");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Verify Bracketted Value KBWI for Trading Status", "(SNCN) should be present",
					"(SNCN) is NOT present");
		}

	}
	
	
	
	public void VerifyTradingStatus(WebDriver driver, ReportGenerator suit, String folder) throws Exception {
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		String ClientStatusKBWI = Utilities.getParameterFromInputSheet("Companies", "Client Status (KBWI)", 41);
		String ClientStatusSNC = Utilities.getParameterFromInputSheet("Companies", "Client Status (SNC)", 41);
		String ClientStatusSNELKBWL = Utilities.getParameterFromInputSheet("Companies", "Client Status (SEBA/SNEL/KBWL)", 41);
		
		//Trading Status (KBWI)
		
		if(ClientStatusKBWI.equalsIgnoreCase("Pending - Onboarding")) {
			if(driver.findElement(By.xpath("//span[text()='Trading Status (KBWI)']/parent::div/following-sibling::div//img")).getAttribute("alt").toString().equalsIgnoreCase("YellowLight")){
				Common_Functions.ResultPass(driver, suit, folder, "Verify the value of Trading Status (KBWI)", "The value should be correct",
						"The value is correct");

			}
			else {
				Common_Functions.ResultFail(driver, suit, folder, "Verify the value of Trading Status (KBWI)", "The value should be correct",
						"The value is NOT correct");
			}
			
		}
		
		else if(ClientStatusKBWI.equalsIgnoreCase("Active - Trading")) {
			if(driver.findElement(By.xpath("//span[text()='Trading Status (KBWI)']/parent::div/following-sibling::div//img")).getAttribute("alt").toString().equalsIgnoreCase("GreenLight")){
				Common_Functions.ResultPass(driver, suit, folder, "Verify the value of Trading Status (KBWI)", "The value should be correct",
						"The value is correct");

			}
			else {
				Common_Functions.ResultFail(driver, suit, folder, "Verify the value of Trading Status (KBWI)", "The value should be correct",
						"The value is NOT correct");
			}
			
		}
		
		else if(ClientStatusKBWI.equalsIgnoreCase("Inactive")) {
			if(driver.findElement(By.xpath("//span[text()='Trading Status (KBWI)']/parent::div/following-sibling::div//img")).getAttribute("alt").toString().equalsIgnoreCase("RedLight")){
				Common_Functions.ResultPass(driver, suit, folder, "Verify the value of Trading Status (KBWI)", "The value should be correct",
						"The value is correct");

			}
			else {
				Common_Functions.ResultFail(driver, suit, folder, "Verify the value of Trading Status (KBWI)", "The value should be correct",
						"The value is NOT correct");
			}
			
		}

		else if(ClientStatusKBWI.equalsIgnoreCase("Active - Research Only")) {
			if(driver.findElement(By.xpath("//span[text()='Trading Status (KBWI)']/parent::div/following-sibling::div//img")).getAttribute("alt").toString().equalsIgnoreCase("RedLight")){
				Common_Functions.ResultPass(driver, suit, folder, "Verify the value of Trading Status (KBWI)", "The value should be correct",
						"The value is correct");

			}
			else {
				Common_Functions.ResultFail(driver, suit, folder, "Verify the value of Trading Status (KBWI)", "The value should be correct",
						"The value is NOT correct");
			}
			
		}
		
		
		//Trading Status (SNC)
		
			if(ClientStatusSNC.equalsIgnoreCase("Pending - Onboarding")) {
				if(driver.findElement(By.xpath("//span[text()='Trading Status (SNC)']/parent::div/following-sibling::div//img")).getAttribute("alt").toString().equalsIgnoreCase("YellowLight")){
					Common_Functions.ResultPass(driver, suit, folder, "Verify the value of Trading Status (SNC)", "The value should be correct",
								"The value is correct");

				}
				else {
						Common_Functions.ResultFail(driver, suit, folder, "Verify the value of Trading Status (SNC)", "The value should be correct",
								"The value is NOT correct");
				}
					
			}
				
			else if(ClientStatusSNC.equalsIgnoreCase("Active - Trading")) {
				if(driver.findElement(By.xpath("//span[text()='Trading Status (SNC)']/parent::div/following-sibling::div//img")).getAttribute("alt").toString().equalsIgnoreCase("GreenLight")){
						Common_Functions.ResultPass(driver, suit, folder, "Verify the value of Trading Status (SNC)", "The value should be correct",
								"The value is correct");

				}
				else {
						Common_Functions.ResultFail(driver, suit, folder, "Verify the value of Trading Status (SNC)", "The value should be correct",
								"The value is NOT correct");
				}
					
			}
				
			else if(ClientStatusSNC.equalsIgnoreCase("Inactive")) {
				if(driver.findElement(By.xpath("//span[text()='Trading Status (SNC)']/parent::div/following-sibling::div//img")).getAttribute("alt").toString().equalsIgnoreCase("RedLight")){
						Common_Functions.ResultPass(driver, suit, folder, "Verify the value of Trading Status (SNC)", "The value should be correct",
								"The value is correct");

				}
				else {
						Common_Functions.ResultFail(driver, suit, folder, "Verify the value of Trading Status (SNC)", "The value should be correct",
								"The value is NOT correct");
				}
					
			}

			else if(ClientStatusSNC.equalsIgnoreCase("Active - Research Only")) {
				if(driver.findElement(By.xpath("//span[text()='Trading Status (SNC)']/parent::div/following-sibling::div//img")).getAttribute("alt").toString().equalsIgnoreCase("RedLight")){
						Common_Functions.ResultPass(driver, suit, folder, "Verify the value of Trading Status (SNC)", "The value should be correct",
								"The value is correct");

				}
				else {
						Common_Functions.ResultFail(driver, suit, folder, "Verify the value of Trading Status (SNC)", "The value should be correct",
								"The value is NOT correct");
				}
					
			}
		
			
			//Trading Status (SNEL/KBWL)
			
			if(ClientStatusSNELKBWL.equalsIgnoreCase("Pending - Onboarding")) {
				if(driver.findElement(By.xpath("//span[text()='Trading Status (SEBA/SNEL/KBWL)']/parent::div/following-sibling::div//img")).getAttribute("alt").toString().equalsIgnoreCase("YellowLight")){
					Common_Functions.ResultPass(driver, suit, folder, "Verify the value of Trading Status (SNEL/KBWL)", "The value should be correct",
								"The value is correct");

				}
				else {
						Common_Functions.ResultFail(driver, suit, folder, "Verify the value of Trading Status (SNEL/KBWL)", "The value should be correct",
								"The value is NOT correct");
				}
					
			}
				
			else if(ClientStatusSNELKBWL.equalsIgnoreCase("Active - Trading")) {
				if(driver.findElement(By.xpath("//span[text()='Trading Status (SEBA/SNEL/KBWL)']/parent::div/following-sibling::div//img")).getAttribute("alt").toString().equalsIgnoreCase("GreenLight")){
						Common_Functions.ResultPass(driver, suit, folder, "Verify the value of Trading Status (SNEL/KBWL)", "The value should be correct",
								"The value is correct");

				}
				else {
						Common_Functions.ResultFail(driver, suit, folder, "Verify the value of Trading Status (SNEL/KBWL)", "The value should be correct",
								"The value is NOT correct");
				}
					
			}
				
			else if(ClientStatusSNELKBWL.equalsIgnoreCase("Inactive")) {
				if(driver.findElement(By.xpath("//span[text()='Trading Status (SEBA/SNEL/KBWL)']/parent::div/following-sibling::div//img")).getAttribute("alt").toString().equalsIgnoreCase("RedLight")){
						Common_Functions.ResultPass(driver, suit, folder, "Verify the value of Trading Status (SNEL/KBWL)", "The value should be correct",
								"The value is correct");

				}
				else {
						Common_Functions.ResultFail(driver, suit, folder, "Verify the value of Trading Status (SNEL/KBWL)", "The value should be correct",
								"The value is NOT correct");
				}
					
			}

			else if(ClientStatusSNELKBWL.equalsIgnoreCase("Active - Research Only")) {
				if(driver.findElement(By.xpath("//span[text()='Trading Status (SEBA/SNEL/KBWL)']/parent::div/following-sibling::div//img")).getAttribute("alt").toString().equalsIgnoreCase("RedLight")){
						Common_Functions.ResultPass(driver, suit, folder, "Verify the value of Trading Status (SNEL/KBWL)", "The value should be correct",
								"The value is correct");

				}
				else {
						Common_Functions.ResultFail(driver, suit, folder, "Verify the value of Trading Status (SNEL/KBWL)", "The value should be correct",
								"The value is NOT correct");
				}
					
			}

		
		
	}
	
	
	public void validateResearchProfilefieldsForCompany(WebDriver driver, ReportGenerator suit, String folder) throws Exception 
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-label='Research Profile']"))).click();
        Thread.sleep(5000);
        boolean canadaResearch=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Canada Research Service Level (SN EQ)']"))).isDisplayed();
        if(canadaResearch)
        {
             Common_Functions.ResultPass(driver, suit, folder, "Verification of Canada Research Profile", "Canada Research Service Level (SN EQ) should be displayed", "Canada Research Service Level (SN EQ) Displayed");
        }
        else
        {
        	Common_Functions.ResultFail(driver, suit, folder, "Verification of Canada Research Profile", "Canada Research Service Level (SN EQ) should be displayed", "Canada Research Service Level (SN EQ) not Displayed");
        }
        boolean ukResearch=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='UK Research Service Level (SN EQ)']"))).isDisplayed();
        if(ukResearch)
        {
             Common_Functions.ResultPass(driver, suit, folder, "Verification of Uk Research Profile", "Uk Research Service Level (SN EQ) should be displayed", "Uk Research Service Level (SN EQ) Displayed");
        }
        else
        {

 

            Common_Functions.ResultFail(driver, suit, folder, "Verification of Uk Research Profile", "Uk Research Service Level (SN EQ) should be displayed", "Uk Research Service Level (SN EQ) not Displayed");
        }
        
    }
	
		
	
	public void CompareRepCodeEmployeeswithCoverage(WebDriver driver, ReportGenerator suit, String folder, String compURL) throws Exception {
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		
		driver.navigate().refresh();
		Thread.sleep(10000);
		
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(6000);
		
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Short Name Coverage']/parent::div/following-sibling::div[2]//table/tbody/tr[1]/td[3]/div/a"))));
        Thread.sleep(10000);
        
        driver.navigate().refresh();
		Thread.sleep(10000);
        
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'Rep_Code')]//span[text()='View All']"))));
        Thread.sleep(6000);
        
        driver.navigate().refresh();
        Thread.sleep(10000);
        
        List<WebElement> NumberofEmployee = driver.findElements(By.xpath("//table/tbody/tr")); 
        
        ArrayList<String> Employees = new ArrayList<String>();
        
        for(int i=1;i<=NumberofEmployee.size();i++) {
        	
        	Employees.add(driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td[2]/span/span")).getText());
        }
        
        driver.get(compURL);
        Thread.sleep(10000);
        
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-label='Coverage']"))).click();
		Thread.sleep(7000);
		
		List<WebElement> NumberofCoverageEmployee = driver.findElements(By.xpath("//strong[text()='Equity Sales & Trading Coverage']/parent::div/following-sibling::div[2]//table/tbody/tr"));
		
		ArrayList<String> CoverageEmployees = new ArrayList<String>();
		
		for(int i=1;i<=NumberofCoverageEmployee.size();i++) {
        	
			CoverageEmployees.add(driver.findElement(By.xpath("//strong[text()='Equity Sales & Trading Coverage']/parent::div/following-sibling::div[2]//table/tbody/tr["+i+"]/td[@data-label='Employee']/div/a")).getText());
        }
		
		
		
		for(int i=0; i<Employees.size(); i++) {
			
			if(Employees.get(i).equalsIgnoreCase(CoverageEmployees.get(i))) {
				Common_Functions.ResultPass(driver, suit, folder, "Comparison of Employees.get("+i+") and CoverageEmployees.get("+i+")", "Employee Names Should match", "Employee Names are matching");
			}
			else {
				 Common_Functions.ResultFail(driver, suit, folder, "Comparison of Employees.get("+i+") and CoverageEmployees.get("+i+")", "Employee Names Should match", "Employee Names are not matching");
			}
		}
		
	}
	
		
	
	
    
    
    public void ValidateResearchProfileFieldEnhancements(WebDriver driver, ReportGenerator suit, String folder) throws Exception {
    	
    	WebDriverWait wait = new WebDriverWait(driver,30);
    	
    	List<WebElement> fields = driver.findElements(By.xpath("//span[text()='Research Eligibility Status (SN)']/parent::div/following-sibling::div"));
    	
    	if(fields.size()>0) {
    		Common_Functions.ResultPass(driver, suit, folder, "Verification of Research Eligibility Status (SN) field", "The field should be present", "The field is present");
    		
    	}
    	else {
    		Common_Functions.ResultFail(driver, suit, folder, "Verification of Research Eligibility Status (SN) field", "The field should be present", "The field is not present");
    	}
    	
    	fields.clear();
    	
    	fields = driver.findElements(By.xpath("//span[text()='Research Strategy Notes (SN)']/parent::div/following-sibling::div"));
    	
    	if(fields.size()>0) {
    		Common_Functions.ResultPass(driver, suit, folder, "Verification of Research Strategy Notes (SN) field", "The field should be present", "The field is present");
    		
    	}
    	else {
    		Common_Functions.ResultFail(driver, suit, folder, "Verification of Research Strategy Notes (SN) field", "The field should be present", "The field is not present");
    	}
    	
    	fields.clear();
    	
    	fields = driver.findElements(By.xpath("//span[text()='Research Eligibility Status (KBW)']/parent::div/following-sibling::div"));
    	
    	if(fields.size()>0) {
    		Common_Functions.ResultPass(driver, suit, folder, "Verification of Research Eligibility Status (KBW) field", "The field should be present", "The field is present");
    		
    	}
    	else {
    		Common_Functions.ResultFail(driver, suit, folder, "Verification of Research Eligibility Status (KBW) field", "The field should be present", "The field is not present");
    	}
    	
    	fields.clear();
    	
    	fields = driver.findElements(By.xpath("//span[text()='Research Strategy Notes (KBW)']/parent::div/following-sibling::div"));
    	
    	if(fields.size()>0) {
    		Common_Functions.ResultPass(driver, suit, folder, "Verification of Research Strategy Notes (KBW) field", "The field should be present", "The field is present");
    		
    	}
    	else {
    		Common_Functions.ResultFail(driver, suit, folder, "Verification of Research Strategy Notes (KBW) field", "The field should be present", "The field is not present");
    	}
    }
    
    
    
    public void ValidateCorporateSolutionsInformation(WebDriver driver, ReportGenerator suit, String folder, String entity) throws Exception {
    	
    	WebDriverWait wait = new WebDriverWait(driver,30);
    	
    	List<WebElement> fields = driver.findElements(By.xpath("//span[text()='Corporate Solutions Information']"));
    	
    	if(fields.size()>0) {
    		Common_Functions.ResultPass(driver, suit, folder, "Verification of Corporate Solutions Information section", "The text should be present", "The text is present");
    		
    	}
    	else {
    		Common_Functions.ResultFail(driver, suit, folder, "Verification of Corporate Solutions Information section", "The text should be present", "The text is not present");
    	}
    	
    	fields.clear();
    	
    	
    	fields = driver.findElements(By.xpath("//span[text()='Designated Sponsorship']/parent::div/following-sibling::div"));
    	
    	if(fields.size()>0) {
    		Common_Functions.ResultPass(driver, suit, folder, "Verification of Designated Sponsorship field", "The field should be present", "The field is present");
    		
    	}
    	else {
    		Common_Functions.ResultFail(driver, suit, folder, "Verification of Designated Sponsorship field", "The field should be present", "The field is not present");
    	}
    	
    	fields.clear();
    	
    	fields = driver.findElements(By.xpath("//span[text()='Sponsored Research']/parent::div/following-sibling::div"));
    	
    	if(fields.size()>0) {
    		Common_Functions.ResultPass(driver, suit, folder, "Verification of Sponsored Research field", "The field should be present", "The field is present");
    		
    	}
    	else {
    		Common_Functions.ResultFail(driver, suit, folder, "Verification of Sponsored Research field", "The field should be present", "The field is not present");
    	}
    	
    	fields.clear();
    	
    	fields = driver.findElements(By.xpath("//span[text()='Rate Card Available ("+entity+")']/parent::div/following-sibling::div"));
    	
    	if(fields.size()>0) {
    		Common_Functions.ResultPass(driver, suit, folder, "Verification of Rate Card Available ("+entity+") field", "The field should be present", "The field is present");
    		
    	}
    	else {
    		Common_Functions.ResultFail(driver, suit, folder, "Verification of Sponsored Research field", "The field should be present", "The field is not present");
    	}
    	
    	
    	fields = driver.findElements(By.xpath("//span[text()='Rate Card Details ("+entity+")']/parent::div/following-sibling::div"));
    	
    	if(fields.size()>0) {
    		Common_Functions.ResultPass(driver, suit, folder, "Verification of Sponsored Research field", "The field should be present", "The field is present");
    		
    	}
    	else {
    		Common_Functions.ResultFail(driver, suit, folder, "Verification of Sponsored Research field", "The field should be present", "The field is not present");
    	}
    	
    }
    
    
    
    public void CheckPresenceofDesignatedSponsorshipEditButton(WebDriver driver, ReportGenerator suit, String folder) throws Exception {
    	
    	WebDriverWait wait = new WebDriverWait(driver,30);
    	
    	List<WebElement> fields = driver.findElements(By.xpath("//span[text()='Designated Sponsorship']/parent::div/following-sibling::div/button"));
    	
    	if(fields.size()>0) {
    		
    		Common_Functions.ResultPass(driver, suit, folder, "Verification of Designated Sponsorship field", "The field should be editable", "The field is editable");
    	}
    	else {
    		Common_Functions.ResultFail(driver, suit, folder, "Verification of Designated Sponsorship field", "The field should be editable", "The field is not editable");
    	}
    	
    }
    
    
    
    public void AddCorporteSolutionCoverage(WebDriver driver, ReportGenerator suit, String folder, String sheetName, int sheetVal, String CompURL) throws Exception {
    	
    	WebDriverWait wait = new WebDriverWait(driver,30);
    	
    	SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);
    	
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Show more actions']/parent::button"))).click();
    	Thread.sleep(2000);
    	
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Add Corporate Solutions Coverage']/parent::a"))).click();
    	Thread.sleep(5000);
    	
    	lwe.selectLookupValue(driver, "Coverage Team Member");
    	
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("arguments[0].click()",wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Save']/parent::button[contains(@class, 'publisherShareButton')]"))));
    	Thread.sleep(10000);
    	
    	nc.LogoutasUser(driver, suit, folder, "Simona Castagno");
    	Thread.sleep(10000);
    	
    	driver.get(CompURL);
    	
    	nc.SwitchTabsinLightning(driver, suit, folder, "Coverage");
    	
    	if(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Corporate Solutions']"))).isDisplayed()) {
    		Common_Functions.ResultPass(driver, suit, folder, "Verification of Addition of Corporate Solutions Coverage", "The coverage should be added", "The coverage is added");
    	}
    	else {
    		Common_Functions.ResultFail(driver, suit, folder, "Verification of Addition of Corporate Solutions Coverage", "The coverage should be added", "The coverage is not added");
    	}
    	
    }
    
    
    
    public void validateAllCurrencyBudgetTableNameAndColumnName(WebDriver driver, ReportGenerator suit, String folder,String currency) throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        boolean CurrencyWiseTableName=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='"+currency+"']"))).isDisplayed();
        if(CurrencyWiseTableName)
        {
            Common_Functions.ResultPass(driver, suit, folder, "Budget Table Name should be displayed", "Budget Table Name displayed successfully", "Budget Table Name displayed successfully"
                    + "Filter tag displayed successfully");
        }
        else             {
            Common_Functions.ResultFail(driver, suit, folder, "Budget Table Name should be displayed", "Budget Table Name displayed successfully", "Budget Table Name not displayed successfully");
        }
        
        List<WebElement> allColumn=driver.findElements(By.xpath("//*[text()='"+currency+"']//parent::div//following-sibling::div//table//thead//tr//th//a[not(contains(@class,'hide')) and contains(@class,'link')]"));
        int Cloumn=allColumn.size();
        
            for(int i=1; i<=Cloumn-1;i++)
            {
                if(allColumn.get(i).isDisplayed())
                {
                    System.out.println(allColumn.get(i).getText()+ " " );
                    Common_Functions.ResultPass(driver, suit, folder, "Expected Column should present", "Column displayed successfully", "Column displayed successfully");
                }
                else             {
                    Common_Functions.ResultFail(driver, suit, folder, "Expected Subscription should match with Actual Subscription", "Column displayed successfully", "Column not displayed successfully");
                }
            }
    }
    
    public void createNewBudget(WebDriver driver,String sheetName, int sheetVal) throws Exception
    {
        Thread.sleep(10000);
        SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);

 

        SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
        LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);

 

        WebDriverWait wait = new WebDriverWait(driver, 50);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@title='More Tabs']")));
        Thread.sleep(3000);
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a//span[text()='Budgets']")));
        Thread.sleep(10000);
        String summary = Utilities.getParameterFromInputSheet(sheetName, "BudgetSummary", sheetVal);
        
//        WebElement we=driver.findElement(By.xpath("//*[text()='"+summary+"']//parent::div//following-sibling::div//table//tbody//td//button[2]"));
//        if(we.isDisplayed())
//        {
//            js.executeScript("arguments[0].click();", we);
//            Thread.sleep(5000);
//            js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[text()='Delete']")));
//            Thread.sleep(10000);
//        }
//        else
//        {
//            System.out.println("No Existing budget present");
//        }
        
        
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[text()='Show more actions']")));
        Thread.sleep(3000);
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[text()='New Budget']")));
        Thread.sleep(10000);
        String year = Utilities.getParameterFromInputSheet(sheetName, "Year", sheetVal);
        String dropdown1 = Utilities.getParameterFromInputSheet(sheetName, "YearLabel", sheetVal);
        Companies.SelectDataFromDropdown(driver, dropdown1, year);
        String currencyType = Utilities.getParameterFromInputSheet(sheetName, "Currency", sheetVal);
        String dropdown2 = Utilities.getParameterFromInputSheet(sheetName, "CurrencyLabel", sheetVal);
        Companies.SelectDataFromDropdown(driver, dropdown2, currencyType);
        String totalBudget = Utilities.getParameterFromInputSheet(sheetName, "TotalBudget", sheetVal);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Total Budget']//parent::label//following-sibling::input"))).sendKeys(totalBudget);
        String researchBudget = Utilities.getParameterFromInputSheet(sheetName, "ResearchBudget", sheetVal);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Research Budget']//parent::label//following-sibling::input"))).sendKeys(researchBudget);
        Thread.sleep(3000);
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//li//span[text()='Save']/parent::button")));
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'footer')]//button//span[text()='Save']"))).click();
        Thread.sleep(20000);
    }
    
    
    public static void SelectDataFromDropdown(WebDriver driver,String label,String Value) throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        //WebElement wb=driver.findElement(By.xpath("//*[text()='"+label+"']//parent::span//following-sibling::div//a"));
        //Select sle=new Select(wb);
        //sle.selectByValue(Value);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[text()='"+label+"']//parent::span//following-sibling::div//a")));
        Thread.sleep(3000);
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='select-options']//li//a[text()='"+Value+"']")));
        Thread.sleep(3000);
    }
    
    
    
    public void validateLastYearBudget(WebDriver driver, ReportGenerator suit, String folder,String sheetName, int sheetVal) throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[text()='Budget Summary (USD)']//parent::div//following-sibling::div//table//tbody//td[not(contains(@class,'hide'))]//a[not(contains(text(),'View More'))]")));
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Budget Summary (USD)']//parent::div//following-sibling::div//table//tbody//td[not(contains(@class,'hide'))][11]"))).click();
        Thread.sleep(10000);
        String year = Utilities.getParameterFromInputSheet(sheetName, "Year", sheetVal);
        String currencyType = Utilities.getParameterFromInputSheet(sheetName, "Currency", sheetVal);
        String totalBudget = Utilities.getParameterFromInputSheet(sheetName, "TotalBudget", sheetVal);
        String researchBudget = Utilities.getParameterFromInputSheet(sheetName, "ResearchBudget", sheetVal);
        String actualTotalBudget=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Total Budget']//parent::div/following-sibling::div//lightning-formatted-number"))).getText().trim();
        String actualResearchBudget=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Research Budget']//parent::div/following-sibling::div//lightning-formatted-number"))).getText().trim();
        String actualYear=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Year']//parent::div/following-sibling::div//lightning-formatted-text"))).getText().trim();
        String actualCurrencyType=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Budget Currency']//parent::div/following-sibling::div//lightning-formatted-text"))).getText().trim();
        boolean lastYearCheckbox=driver.findElement(By.xpath("//*[text()='Previous Year Budget?']//parent::div/following-sibling::div//input[@type='checkbox']")).isSelected();
        if(totalBudget.equalsIgnoreCase(actualTotalBudget))
        {
            Common_Functions.ResultPass(driver, suit, folder, "Expected Budget Dtails should present", "Budget Dtails displayed Correctly", "Budget Dtails displayed correctly");
        }
        else             {
            Common_Functions.ResultFail(driver, suit, folder, " Expected Budget Dtails should present", "Budget Dtails displayed Correctly", "Budget Dtails not displayed correctly");
        }
        if(researchBudget.equalsIgnoreCase(actualResearchBudget))
        {
            Common_Functions.ResultPass(driver, suit, folder, "Expected Budget Dtails should present", "Budget Dtails displayed Correctly", "Budget Dtails displayed correctly");
        }
        else             {
            Common_Functions.ResultFail(driver, suit, folder, " Expected Budget Dtails should present", "Budget Dtails displayed Correctly", "Budget Dtails not displayed correctly");
        }
        if(year.equalsIgnoreCase(actualYear))
        {
            Common_Functions.ResultPass(driver, suit, folder, "Expected Budget Dtails should present", "Budget Dtails displayed Correctly", "Budget Dtails displayed correctly");
        }
        else             {
            Common_Functions.ResultFail(driver, suit, folder, " Expected Budget Dtails should present", "Budget Dtails displayed Correctly", "Budget Dtails not displayed correctly");
        }
        if(currencyType.equalsIgnoreCase(actualCurrencyType))
        {
            Common_Functions.ResultPass(driver, suit, folder, "Expected Budget Dtails should present", "Budget Dtails displayed Correctly", "Budget Dtails displayed correctly");
        }
        else             {
            Common_Functions.ResultFail(driver, suit, folder, " Expected Budget Dtails should present", "Budget Dtails displayed Correctly", "Budget Dtails not displayed correctly");
        }
        if(lastYearCheckbox)
        {
            Common_Functions.ResultPass(driver, suit, folder, "Expected Budget Dtails should present", "Budget Dtails displayed Correctly", "Budget Dtails displayed correctly");
        }
        else             {
            Common_Functions.ResultFail(driver, suit, folder, " Expected Budget Dtails should present", "Budget Dtails displayed Correctly", "Budget Dtails not displayed correctly");
        }
       
       
    }
    
    
    
    
    public void validateCurrentYearBudget(WebDriver driver, ReportGenerator suit, String folder,String sheetName, int sheetVal) throws Exception
    {
       
        WebDriverWait wait = new WebDriverWait(driver,30);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[text()='Budget Summary (GBP)']//parent::div//following-sibling::div//table//tbody//td[not(contains(@class,'hide'))]//a[not(contains(text(),'View More'))]")));
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Budget Summary (USD)']//parent::div//following-sibling::div//table//tbody//td[not(contains(@class,'hide'))][11]"))).click();
        Thread.sleep(10000);
        String year = Utilities.getParameterFromInputSheet(sheetName, "Year", sheetVal);
        String currencyType = Utilities.getParameterFromInputSheet(sheetName, "Currency", sheetVal);
        String totalBudget = Utilities.getParameterFromInputSheet(sheetName, "TotalBudget", sheetVal);
        String researchBudget = Utilities.getParameterFromInputSheet(sheetName, "ResearchBudget", sheetVal);
        String actualTotalBudget=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Total Budget']//parent::div/following-sibling::div//lightning-formatted-number"))).getText().trim();
        String actualResearchBudget=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Research Budget']//parent::div/following-sibling::div//lightning-formatted-number"))).getText().trim();
        String actualYear=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Year']//parent::div/following-sibling::div//lightning-formatted-text"))).getText().trim();
        String actualCurrencyType=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Budget Currency']//parent::div/following-sibling::div//lightning-formatted-text"))).getText().trim();
        boolean currentYearCheckbox=driver.findElement(By.xpath("//*[text()='Current Year Budget?']//parent::div/following-sibling::div//input[@type='checkbox']")).isSelected();
        if(totalBudget.equalsIgnoreCase(actualTotalBudget))
        {
            Common_Functions.ResultPass(driver, suit, folder, "Expected Budget Dtails should present", "Budget Dtails displayed Correctly", "Budget Dtails displayed correctly");
        }
        else             {
            Common_Functions.ResultFail(driver, suit, folder, " Expected Budget Dtails should present", "Budget Dtails displayed Correctly", "Budget Dtails not displayed correctly");
        }
        if(researchBudget.equalsIgnoreCase(actualResearchBudget))
        {
            Common_Functions.ResultPass(driver, suit, folder, "Expected Budget Dtails should present", "Budget Dtails displayed Correctly", "Budget Dtails displayed correctly");
        }
        else             {
            Common_Functions.ResultFail(driver, suit, folder, " Expected Budget Dtails should present", "Budget Dtails displayed Correctly", "Budget Dtails not displayed correctly");
        }
        if(year.equalsIgnoreCase(actualYear))
        {
            Common_Functions.ResultPass(driver, suit, folder, "Expected Budget Dtails should present", "Budget Dtails displayed Correctly", "Budget Dtails displayed correctly");
        }
        else             {
            Common_Functions.ResultFail(driver, suit, folder, " Expected Budget Dtails should present", "Budget Dtails displayed Correctly", "Budget Dtails not displayed correctly");
        }
        if(currencyType.equalsIgnoreCase(actualCurrencyType))
        {
            Common_Functions.ResultPass(driver, suit, folder, "Expected Budget Dtails should present", "Budget Dtails displayed Correctly", "Budget Dtails displayed correctly");
        }
        else             {
            Common_Functions.ResultFail(driver, suit, folder, " Expected Budget Dtails should present", "Budget Dtails displayed Correctly", "Budget Dtails not displayed correctly");
        }
        if(currentYearCheckbox)
        {
            Common_Functions.ResultPass(driver, suit, folder, "Expected Budget Dtails should present", "Budget Dtails displayed Correctly", "Budget Dtails displayed correctly");
        }
        else             {
            Common_Functions.ResultFail(driver, suit, folder, " Expected Budget Dtails should present", "Budget Dtails displayed Correctly", "Budget Dtails not displayed correctly");
        }
       
       
       
    }
    
    
    
    
    public void validateNextYearBudget(WebDriver driver, ReportGenerator suit, String folder,String sheetName, int sheetVal) throws Exception
    {

 

        WebDriverWait wait = new WebDriverWait(driver,30);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[text()='Budget Summary (EUR)']//parent::div//following-sibling::div//table//tbody//td[not(contains(@class,'hide'))]//a[not(contains(text(),'View More'))]")));
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Budget Summary (USD)']//parent::div//following-sibling::div//table//tbody//td[not(contains(@class,'hide'))][11]"))).click();
        Thread.sleep(10000);
        String year = Utilities.getParameterFromInputSheet(sheetName, "Year", sheetVal);
        String currencyType = Utilities.getParameterFromInputSheet(sheetName, "Currency", sheetVal);
        String totalBudget = Utilities.getParameterFromInputSheet(sheetName, "TotalBudget", sheetVal);
        String researchBudget = Utilities.getParameterFromInputSheet(sheetName, "ResearchBudget", sheetVal);
        String actualTotalBudget=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Total Budget']//parent::div/following-sibling::div//lightning-formatted-number"))).getText().trim();
        String actualResearchBudget=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Research Budget']//parent::div/following-sibling::div//lightning-formatted-number"))).getText().trim();
        String actualYear=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Year']//parent::div/following-sibling::div//lightning-formatted-text"))).getText().trim();
        String actualCurrencyType=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Budget Currency']//parent::div/following-sibling::div//lightning-formatted-text"))).getText().trim();
        if(totalBudget.equalsIgnoreCase(actualTotalBudget))
        {
            Common_Functions.ResultPass(driver, suit, folder, "Expected Budget Dtails should present", "Budget Dtails displayed Correctly", "Budget Dtails displayed correctly");
        }
        else             {
            Common_Functions.ResultFail(driver, suit, folder, " Expected Budget Dtails should present", "Budget Dtails displayed Correctly", "Budget Dtails not displayed correctly");
        }
        if(researchBudget.equalsIgnoreCase(actualResearchBudget))
        {
            Common_Functions.ResultPass(driver, suit, folder, "Expected Budget Dtails should present", "Budget Dtails displayed Correctly", "Budget Dtails displayed correctly");
        }
        else             {
            Common_Functions.ResultFail(driver, suit, folder, " Expected Budget Dtails should present", "Budget Dtails displayed Correctly", "Budget Dtails not displayed correctly");
        }
        if(year.equalsIgnoreCase(actualYear))
        {
            Common_Functions.ResultPass(driver, suit, folder, "Expected Budget Dtails should present", "Budget Dtails displayed Correctly", "Budget Dtails displayed correctly");
        }
        else             {
            Common_Functions.ResultFail(driver, suit, folder, " Expected Budget Dtails should present", "Budget Dtails displayed Correctly", "Budget Dtails not displayed correctly");
        }
        if(currencyType.equalsIgnoreCase(actualCurrencyType))
        {
            Common_Functions.ResultPass(driver, suit, folder, "Expected Budget Dtails should present", "Budget Dtails displayed Correctly", "Budget Dtails displayed correctly");
        }
        else             {
            Common_Functions.ResultFail(driver, suit, folder, " Expected Budget Dtails should present", "Budget Dtails displayed Correctly", "Budget Dtails not displayed correctly");
        }
        
        
        
    }
    
    public void openExistingCompany(WebDriver driver,String sheetName, int sheetVal) throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        String url = Utilities.getParameterFromInputSheet(sheetName, "URL", sheetVal);
        driver.get(url);
        Thread.sleep(1000);
    }
    
    
    public void InActivePortfolio(WebDriver driver, ReportGenerator suit, String folder) throws Exception 
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath(""))).click();
        //Thread.sleep(5000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Settings']//parent::button")));
//        Thread.sleep(3000);
//        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Edit']//parent::a")));
//        Thread.sleep(5000);
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//h2[text()='Sponsor Portfolio Relationships']//parent::div//parent::div//parent::div//following-sibling::div[contains(@id,'infiniteScroll')]//tbody//td//button[1]")));
        Thread.sleep(10000);
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//label[text()='Status']/..//input[@role='combobox']")));
        Thread.sleep(5000);
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//label[text()='Status']/..//div[@role='listbox']//span[text()='Inactive']")));
        Thread.sleep(5000);
        lwe.enterCustomDateforSubfunc(driver, "End Date");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='SaveEdit']"))).click();
        Thread.sleep(5000);
    }
    
    public void ValidateRelateCompanyCoverageStatus(WebDriver driver, ReportGenerator suit, String folder) throws Exception 
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//td[@data-label='Related Company']//a")));
        Thread.sleep(5000);
        driver.navigate().refresh();
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//li/a[text()='Coverage']")));
        Thread.sleep(8000);
        boolean CoverageStatus=driver.findElement(By.xpath("//td[@data-label='Coverage Team Member']//following-sibling::td//span[text()='Inactive']")).isDisplayed();
                //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@data-label='Coverage Team Member']//following-sibling::td//span[text()='Inactive']"))).isDisplayed();
        if(CoverageStatus)
        {
             Common_Functions.ResultPass(driver, suit, folder, "Verification of Coverage Status", "Coverage Status should be Inactive", "Coverage Status Inactive, Displayed");
        }
        else
        {
            Common_Functions.ResultFail(driver, suit, folder, "Verification of Coverage Status", "Coverage Status should be Inactive", "Coverage Status Inactive, not Displayed");
        }
    }
    
    
    
    public void ValidatePresenceofRSAfields(WebDriver driver, ReportGenerator suit, String folder) throws Exception {
    	
    	WebDriverWait wait = new WebDriverWait(driver,30);
    	
    	List<WebElement> PresenceofRSAfields = driver.findElements(By.xpath("//span[text()='RSA Contracting Role (SN)']/parent::div/following-sibling::div/span"));
    	
    	if(PresenceofRSAfields.size()>0) {
    		Common_Functions.ResultPass(driver, suit, folder, "Verification of RSA Contracting Role (SN) field", "RSA Contracting Role (SN) should be present", "RSA Contracting Role (SN) is present");
    	}
    	else {
    		Common_Functions.ResultFail(driver, suit, folder, "Verification of RSA Contracting Role (SN) field", "RSA Contracting Role (SN) should be present", "RSA Contracting Role (SN) is not present");
    	}
    	
    	PresenceofRSAfields.clear();
    	
    	PresenceofRSAfields = driver.findElements(By.xpath("//span[text()='RSA Contracting Role (KBW)']/parent::div/following-sibling::div/span"));
    	
    	if(PresenceofRSAfields.size()>0) {
    		Common_Functions.ResultPass(driver, suit, folder, "Verification of RSA Contracting Role (KBW) field", "RSA Contracting Role (KBW) should be present", "RSA Contracting Role (KBW) is present");
    	}
    	else {
    		Common_Functions.ResultFail(driver, suit, folder, "Verification of RSA Contracting Role (KBW) field", "RSA Contracting Role (KBW) should be present", "RSA Contracting Role (KBW) is not present");
    	}
    	
    	PresenceofRSAfields.clear();
    	
    	PresenceofRSAfields = driver.findElements(By.xpath("//span[text()='RSA Fee (SN)']/parent::div/following-sibling::div/span"));
    	
    	if(PresenceofRSAfields.size()>0) {
    		Common_Functions.ResultPass(driver, suit, folder, "Verification of RSA Fee (SN) field", "RSA Fee (SN) should be present", "RSA Fee (SN) is present");
    	}
    	else {
    		Common_Functions.ResultFail(driver, suit, folder, "Verification of RSA Fee (SN) field", "RSA Fee (SN) should be present", "RSA Fee (SN) is not present");
    	}
    	
    	PresenceofRSAfields.clear();
    	
    	PresenceofRSAfields = driver.findElements(By.xpath("//span[text()='RSA Fee (KBW)']/parent::div/following-sibling::div/span"));
    	
    	if(PresenceofRSAfields.size()>0) {
    		Common_Functions.ResultPass(driver, suit, folder, "Verification of RSA Fee (KBW) field", "RSA Fee (KBW) should be present", "RSA Fee (KBW) is present");
    	}
    	else {
    		Common_Functions.ResultFail(driver, suit, folder, "Verification of RSA Fee (KBW) field", "RSA Fee (KBW) should be present", "RSA Fee (KBW) is not present");
    	}
    	
    	PresenceofRSAfields.clear();
    	
    	PresenceofRSAfields = driver.findElements(By.xpath("//span[text()='RSA Fee Currency (SN)']/parent::div/following-sibling::div/span"));
    	
    	if(PresenceofRSAfields.size()>0) {
    		Common_Functions.ResultPass(driver, suit, folder, "Verification of RSA Fee Currency (SN) field", "RSA Fee Currency (SN) should be present", "RSA Fee Currency (SN) is present");
    	}
    	else {
    		Common_Functions.ResultFail(driver, suit, folder, "Verification of RSA Fee Currency (SN) field", "RSA Fee Currency (SN) should be present", "RSA Fee Currency (SN) is not present");
    	}
    	
    	PresenceofRSAfields.clear();
    	
    	PresenceofRSAfields = driver.findElements(By.xpath("//span[text()='RSA Fee Currency (KBW)']/parent::div/following-sibling::div/span"));
    	
    	if(PresenceofRSAfields.size()>0) {
    		Common_Functions.ResultPass(driver, suit, folder, "Verification of RSA Fee Currency (KBW) field", "RSA Fee Currency (KBW) should be present", "RSA Fee Currency (KBW) is present");
    	}
    	else {
    		Common_Functions.ResultFail(driver, suit, folder, "Verification of RSA Fee Currency (KBW) field", "RSA Fee Currency (KBW) should be present", "RSA Fee Currency (KBW) is not present");
    	}
    	
    	PresenceofRSAfields.clear();
    	
    	PresenceofRSAfields = driver.findElements(By.xpath("//span[text()='RSA Billing Company (SN)']/parent::div/following-sibling::div/span"));
    	
    	if(PresenceofRSAfields.size()>0) {
    		Common_Functions.ResultPass(driver, suit, folder, "Verification of RSA Billing Company (SN) field", "RSA Billing Company (SN) should be present", "RSA Billing Company (SN) is present");
    	}
    	else {
    		Common_Functions.ResultFail(driver, suit, folder, "Verification of RSA Billing Company (SN) field", "RSA Billing Company (SN) should be present", "RSA Billing Company (SN) is not present");
    	}
    	
    	PresenceofRSAfields.clear();
    	
    	PresenceofRSAfields = driver.findElements(By.xpath("//span[text()='RSA Billing Company (KBW)']/parent::div/following-sibling::div/span"));
    	
    	if(PresenceofRSAfields.size()>0) {
    		Common_Functions.ResultPass(driver, suit, folder, "Verification of RSA Billing Company (KBW) field", "RSA Billing Company (KBW) should be present", "RSA Billing Company (KBW) is present");
    	}
    	else {
    		Common_Functions.ResultFail(driver, suit, folder, "Verification of RSA Billing Company (KBW) field", "RSA Billing Company (KBW) should be present", "RSA Billing Company (KBW) is not present");
    	}
    	
    	
    }
    
    
    
    public void SetRSAContractingRole(WebDriver driver, ReportGenerator suit, String folder, String sheetName, int sheetVal) throws Exception {
    	
    	WebDriverWait wait = new WebDriverWait(driver,30);
    	SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);
    	
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();" , wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Research Eligibility Status(SN)']/parent::div/following-sibling::div/button[@title='Edit Research Eligibility Status(SN)']"))));
    	Thread.sleep(2000);
    	
    	SelectDataFromDropdown(driver, "Research Eligibility Status(SN)", "Pending - Awaiting Signed Research Service Agreement");
    	
    	js.executeScript("arguments[0].click();" ,wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Save']/parent::button)[2]"))));
    	Thread.sleep(10000);
    	
    	js.executeScript("arguments[0].click();" ,wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='RSA Contracting Role (SN)']/parent::div/following-sibling::div/button[@title='Edit RSA Contracting Role (SN)']"))));
    	Thread.sleep(2000);
    	
    	js.executeScript("arguments[0].click();" ,wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='RSA Contracting Role (SN)']/parent::div//span[text()='Available']/parent::div//li//span[text()='Invoicing Entity']"))));
    	js.executeScript("arguments[0].click();" ,wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='RSA Contracting Role (SN)']/parent::div//span[text()='Available']/parent::div/following-sibling::div//button[@title='Move selection to Chosen']"))));
    	
    	
    	js.executeScript("arguments[0].click();" ,wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Save']/parent::button)[3]"))));
    	Thread.sleep(10000);
    	
    	List<WebElement> Validation = driver.findElements(By.xpath("//li[contains(text(),\"Only MiFID Admins and System Admins can edit Research Information for this company's current Research Eligibility Status and Reason.\")]"));
    	
    	if(Validation.size()>0) {
    		Common_Functions.ResultPass(driver, suit, folder, "Validation of the expected error message", "The error message should appear correctly", "The error message is appearing correctly");
    	}
    	else {
    		Common_Functions.ResultFail(driver, suit, folder, "Validation of the expected error message", "The error message should appear correctly", "The error message is not appearing correctly");
    	}
    	
    	js.executeScript("arguments[0].click();" ,wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Cancel']/parent::button)[3]"))));
    	Thread.sleep(5000);
    	
    	driver.navigate().refresh();
    	Thread.sleep(10000);
    	
    	nc.SwitchTabsinLightning(driver, suit, folder, "Research Profile");
    	
    	js.executeScript("arguments[0].click();" , wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Research Eligibility Status(SN)']/parent::div/following-sibling::div/button[@title='Edit Research Eligibility Status(SN)']"))));
    	Thread.sleep(2000);
    	
    	js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[text()='Research Eligibility Status(SN)']//parent::span//following-sibling::div//a")));
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//div[@class='select-options']//li//a[text()='Eligible - Research Service Agreement Signed'])[1]")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
    	
    	js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[text()='Research Eligibility Status(KBW)']//parent::span//following-sibling::div//a")));
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//div[@class='select-options']//li//a[text()='Eligible - Research Service Agreement Signed'])[2]")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        
        js.executeScript("arguments[0].click();" ,wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Save']/parent::button"))));
    	Thread.sleep(10000);
    	
    	js.executeScript("arguments[0].click();" ,wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='RSA Contracting Role (SN)']/parent::div/following-sibling::div/button"))));
    	Thread.sleep(2000);
    	
    	js.executeScript("arguments[0].click();" ,wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='RSA Contracting Role (SN)']/parent::div//span[text()='Available']/parent::div//li//span[text()='Invoicing Entity']"))));
    	js.executeScript("arguments[0].click();" ,wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='RSA Contracting Role (SN)']/parent::div//span[text()='Available']/parent::div/following-sibling::div//button[@title='Move selection to Chosen']"))));
    	
    	js.executeScript("arguments[0].click();" ,wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Save']/parent::button)[2]"))));
    	
    	js.executeScript("arguments[0].click();" ,wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='RSA Contracting Role (KBW)']/parent::div/following-sibling::div/button"))));
    	
    	js.executeScript("arguments[0].click();" ,wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='RSA Contracting Role (KBW)']/parent::div//span[text()='Available']/parent::div//li//span[text()='Invoicing Entity']"))));
    	js.executeScript("arguments[0].click();" ,wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='RSA Contracting Role (KBW)']/parent::div//span[text()='Available']/parent::div/following-sibling::div//button[@title='Move selection to Chosen']"))));
    	
    	js.executeScript("arguments[0].click();" ,wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Save']/parent::button)[3]"))));
    	Thread.sleep(10000);
    	
    	Validation.clear();
    	
    	Validation = driver.findElements(By.xpath("//span[text()='RSA Billing Company (SN)']/parent::div/following-sibling::div/span/span/img[@class=' checked']"));
    	
    	if(Validation.size()>0) {
    		Common_Functions.ResultPass(driver, suit, folder, "Validation of RSA Billing Company (SN)", "RSA Billing Company (SN) should be checked", "RSA Billing Company (SN) is checked");
    	}
    	else {
    		Common_Functions.ResultFail(driver, suit, folder, "Validation of RSA Billing Company (SN)", "RSA Billing Company (SN) should be checked", "RSA Billing Company (SN) is not checked");
    	}
    	
    	Validation.clear();
    	
    	Validation = driver.findElements(By.xpath("//span[text()='RSA Billing Company (KBW)']/parent::div/following-sibling::div/span/span/img[@class=' checked']"));
    	
    	if(Validation.size()>0) {
    		Common_Functions.ResultPass(driver, suit, folder, "Validation of RSA Billing Company (KBW)", "RSA Billing Company (KBW) should be checked", "RSA Billing Company (KBW) is checked");
    	}
    	else {
    		Common_Functions.ResultFail(driver, suit, folder, "Validation of RSA Billing Company (KBW)", "RSA Billing Company (KBW) should be checked", "RSA Billing Company (KBW) is not checked");
    	}
    }
    
    
    
    public void VerifyandEditRateCardFields(WebDriver driver, ReportGenerator suit, String folder, String sheetName, int sheetVal) throws Exception {
    	
    	WebDriverWait wait = new WebDriverWait(driver,30);
    	SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);
    	
    	nc.SwitchTabsinLightning(driver, suit, folder, "Research Profile");
    	
    	List<WebElement> ValidatePresence = driver.findElements(By.xpath("//span[text()='Rate Card Available (SN)']/parent::div/following-sibling::div/span//img"));
    	
    	if(ValidatePresence.size()>0) {
    		Common_Functions.ResultPass(driver, suit, folder, "Validation of Rate Card Available (SN)", "Rate Card Available (SN) field should be present", "Rate Card Available (SN) field is present");
    	}
    	else {
    		Common_Functions.ResultFail(driver, suit, folder, "Validation of Rate Card Available (SN)", "Rate Card Available (SN) field should be present", "Rate Card Available (SN) field is present");
    	}
    	
    	ValidatePresence.clear();
    	
    	ValidatePresence = driver.findElements(By.xpath("//span[text()='Rate Card Available (KBW)']/parent::div/following-sibling::div/span//img"));
    	
    	if(ValidatePresence.size()>0) {
    		Common_Functions.ResultPass(driver, suit, folder, "Validation of Rate Card Available (KBW)", "Rate Card Available (KBW) field should be present", "Rate Card Available (KBW) field is present");
    	}
    	else {
    		Common_Functions.ResultFail(driver, suit, folder, "Validation of Rate Card Available (KBW)", "Rate Card Available (KBW) field should be present", "Rate Card Available (KBW) field is present");
    	}
    	
    	ValidatePresence.clear();
    	
    	ValidatePresence = driver.findElements(By.xpath("//span[text()='Rate Card Details (SN)']/parent::div/following-sibling::div/span"));
    	
    	if(ValidatePresence.size()>0) {
    		Common_Functions.ResultPass(driver, suit, folder, "Validation of Rate Card Details (SN)", "Rate Card Details (SN) field should be present", "Rate Card Details (SN) field is present");
    	}
    	else {
    		Common_Functions.ResultFail(driver, suit, folder, "Validation of Rate Card Details (SN)", "Rate Card Details (SN) field should be present", "Rate Card Details (SN) field is present");
    	}
    	
    	ValidatePresence.clear();
    	
    	ValidatePresence = driver.findElements(By.xpath("//span[text()='Rate Card Details (KBW)']/parent::div/following-sibling::div/span"));
    	
    	if(ValidatePresence.size()>0) {
    		Common_Functions.ResultPass(driver, suit, folder, "Validation of Rate Card Details (KBW)", "Rate Card Details (KBW) field should be present", "Rate Card Details (KBW) field is present");
    	}
    	else {
    		Common_Functions.ResultFail(driver, suit, folder, "Validation of Rate Card Details (KBW)", "Rate Card Details (KBW) field should be present", "Rate Card Details (KBW) field is present");
    	}
    	
    	ValidatePresence.clear();
    	
    	lwe.ClickFieldEditButton(driver, "Rate Card Available (SN)");
    	lwe.selectCheckboxforSubfunc2(driver, "Rate Card Available (SN)");
    	lwe.enterTextArea(driver, "Rate Card Details (SN)");
    	
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("arguments[0].click();" ,wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Save']/parent::button)[2]"))));
    	Thread.sleep(10000);
    	
    	lwe.ClickFieldEditButton(driver, "Rate Card Available (KBW)");
    	lwe.selectCheckboxforSubfunc2(driver, "Rate Card Available (KBW)");
    	lwe.enterTextArea(driver, "Rate Card Details (KBW)");
    	
    	js.executeScript("arguments[0].click();" ,wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Save']/parent::button)[3]"))));
    	Thread.sleep(10000);
    }
    
    
    public void VerifyEuroUpdateOnCommissionsTab(WebDriver driver, ReportGenerator suit, String folder) throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@value='EUR']")));
        Thread.sleep(5000);
        List<WebElement> allColumn=driver.findElements(By.xpath("//div[contains(@class,'custom-table_header')]//div[contains(text(),'(EUR)') and not(contains(text(),'Day'))]"));
        int Cloumn=allColumn.size();
       
            for(int i=1; i<=Cloumn-1;i++)
            {
                if(allColumn.get(i).isDisplayed())
                {
                    System.out.println(allColumn.get(i).getText()+ " " );
                    Common_Functions.ResultPass(driver, suit, folder, "Expected Column should present", "Column displayed successfully", "Column displayed successfully");
                }
                else             {
                    Common_Functions.ResultFail(driver, suit, folder, "Expected Subscription should match with Actual Subscription", "Column displayed successfully", "Column not displayed successfully");
                }
            }
    }
    
    
    
    public void CreateSecurityMaster(WebDriver driver, ReportGenerator suit, String folder, String sheetName, int sheetVal) throws Exception {
    	
    	WebDriverWait wait = new WebDriverWait(driver,30);
    	Random ran = new Random();
		int Ran3digit = ran.nextInt(999);
    	
		SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);

		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
		
		String SecMasterRecordType = Utilities.getParameterFromInputSheet(sheetName, "Record type", sheetVal);
		
		boolean SecurityMasterPresent = driver.findElements(By.xpath("//span[@class=' label bBody' and text()='Next']"))
				.size() != 0;

		if (SecurityMasterPresent) {
			if (SecMasterRecordType != null && !SecMasterRecordType.isEmpty()) {

				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath(SF_StandardLEX_WebElementLocators.getRadioButton(SecMasterRecordType)))).click();
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click()",
						wait.until(ExpectedConditions.visibilityOf(LightningWE.NextBtn_xpath)));

			}
		}
		
		driver.navigate().refresh();
		Thread.sleep(3000);
		
		lwe.enterTextBoxWithRandomData(driver, "Security Master/Interest Name", Ran3digit);
		lwe.enterTextBoxWithRandomData(driver, "Ticker", Ran3digit);
		lwe.selectLookupValueforSubfunc(driver, "Company");
		lwe.selectPicklistforSubfunc2(driver, "Region");
		lwe.selectPicklistforSubfunc2(driver, "Research Type");
		
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@name='SaveEdit']")));
		Thread.sleep(15000);
		
    }
    
    
    
    public void ValidateSponsoredCheckbox(WebDriver driver, ReportGenerator suit, String folder) throws Exception {
    	
    	WebDriverWait wait = new WebDriverWait(driver,30);
    	
		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		
	//	WebElement element = driver.findElement(By.xpath("//span[text()='Sponsored Research']/parent::div/following-sibling::div/span//label"));
	//	JavascriptExecutor js = (JavascriptExecutor)driver;
	//	String innerHTMLText = js.executeScript("return arguments[0].innerHTML;", element).toString();
		
		if (driver.findElement(By.xpath("//span[text()='Sponsored Research']/parent::div/following-sibling::div/span//input")).isSelected()){
			Common_Functions.ResultPass(driver, suit, folder, "Validate the Sponsored Checkbox", "Sponsored Checkbox should be checked", "Sponsored Checkbox is checked");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Validate the Sponsored Checkbox", "Sponsored Checkbox should be checked", "Sponsored Checkbox is not checked");
		}
    	
    }
    
    
    
    public void ValidateResearchProfilefields(WebDriver driver, ReportGenerator suit, String folder) throws Exception {
    	
    	WebDriverWait wait = new WebDriverWait(driver,30);
    	
		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		
		lwe.ClickFieldEditButton(driver, "RSA Payment Type (SN)");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[text()='RSA Payment Type (SN)']//parent::span//following-sibling::div//a")));
        
        List<WebElement> verifypresence = driver.findElements(By.xpath("//a[@title='Fixed fee for the period']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Fixed fee for the period should be present", "Fixed fee for the period is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Fixed fee for the period should be present", "Fixed fee for the period is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Platform fee with potential for additional variable payments']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Platform fee with potential for additional variable payments should be present", "Platform fee with potential for additional variable payments is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Platform fee with potential for additional variable payments should be present", "Platform fee with potential for additional variable payments is not present");
        }
        
        
        verifypresence = driver.findElements(By.xpath("//a[@title='All payments to be determined on an ex-post basis']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "All payments to be determined on an ex-post basis should be present", "All payments to be determined on an ex-post basis is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "All payments to be determined on an ex-post basis should be present", "All payments to be determined on an ex-post basis is not present");
        }
        
        verifypresence.clear();
        
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[text()='Contracting Research Provider (SN)']//parent::span//following-sibling::div//a")));
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Stifel Nicolaus Europe Limited']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Stifel Nicolaus Europe Limited should be present", "Stifel Nicolaus Europe Limited is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Stifel Nicolaus Europe Limited should be present", "Stifel Nicolaus Europe Limited is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Stifel Europe Bank AG']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Stifel Europe Bank AG should be present", "Stifel Europe Bank AG is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Stifel Europe Bank AG should be present", "Stifel Europe Bank AG is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Stifel Europe Bank AG - Paris Branch']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Stifel Europe Bank AG - Paris Branch should be present", "Stifel Europe Bank AG - Paris Branch is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Stifel Europe Bank AG - Paris Branch should be present", "Stifel Europe Bank AG - Paris Branch is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Stifel Europe Bank AG - Madrid Branch']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Stifel Europe Bank AG - Madrid Branch should be present", "Stifel Europe Bank AG - Madrid Branch is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Stifel Europe Bank AG - Madrid Branch should be present", "Stifel Europe Bank AG - Madrid Branch is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Stifel Europe Bank AG - Milan Branch']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Stifel Europe Bank AG - Milan Branch should be present", "Stifel Europe Bank AG - Milan Branch is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Stifel Europe Bank AG - Milan Branch should be present", "Stifel Europe Bank AG - Milan Branch is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Stifel Schweiz AG']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Stifel Schweiz AG should be present", "Stifel Schweiz AG is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Stifel Schweiz AG should be present", "Stifel Schweiz AG is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Stifel Nicolaus & Co, Inc']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Stifel Nicolaus & Co, Inc should be present", "Stifel Nicolaus & Co, Inc is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Stifel Nicolaus & Co, Inc should be present", "Stifel Nicolaus & Co, Inc is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Stifel Nicolaus & Co, Inc']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Stifel Nicolaus & Co, Inc should be present", "Stifel Nicolaus & Co, Inc is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Stifel Nicolaus & Co, Inc should be present", "Stifel Nicolaus & Co, Inc is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Stifel Nicolaus Canada Inc']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Stifel Nicolaus Canada Inc should be present", "Stifel Nicolaus Canada Inc is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Stifel Nicolaus Canada Inc should be present", "Stifel Nicolaus Canada Inc is not present");
        }
        
        verifypresence.clear();
        
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[text()='Invoicing Frequency (SN)']//parent::span//following-sibling::div//a")));
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Semi-Annually']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Semi-Annually should be present", "Semi-Annually is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Semi-Annually should be present", "Semi-Annually is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Annually']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Annually should be present", "Annually is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Annually should be present", "Annually is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Triannually']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Triannually should be present", "Triannually is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Triannually should be present", "Triannually is not present");
        }
        
        verifypresence.clear();
        
        js.executeScript("arguments[0].click();",wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Cancel']/parent::button)[2]"))));
        Thread.sleep(10000);
        
        lwe.ClickFieldEditButton(driver, "RSA Payment Type (KBW)");
        
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[text()='RSA Payment Type (KBW)']//parent::span//following-sibling::div//a")));
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Fixed fee for the period']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Fixed fee for the period should be present", "Fixed fee for the period is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Fixed fee for the period should be present", "Fixed fee for the period is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Platform fee with potential for additional variable payments']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Platform fee with potential for additional variable payments should be present", "Platform fee with potential for additional variable payments is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Platform fee with potential for additional variable payments should be present", "Platform fee with potential for additional variable payments is not present");
        }
        
        
        verifypresence = driver.findElements(By.xpath("//a[@title='All payments to be determined on an ex-post basis']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "All payments to be determined on an ex-post basis should be present", "All payments to be determined on an ex-post basis is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "All payments to be determined on an ex-post basis should be present", "All payments to be determined on an ex-post basis is not present");
        }
        
        verifypresence.clear();
        
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[text()='Contracting Research Provider (KBW)']//parent::span//following-sibling::div//a")));
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Stifel Nicolaus Europe Limited']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Stifel Nicolaus Europe Limited should be present", "Stifel Nicolaus Europe Limited is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Stifel Nicolaus Europe Limited should be present", "Stifel Nicolaus Europe Limited is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Keefe, Bruyette & Woods, Inc.']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Keefe, Bruyette & Woods, Inc. should be present", "Keefe, Bruyette & Woods, Inc. is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Keefe, Bruyette & Woods, Inc. should be present", "Keefe, Bruyette & Woods, Inc. is not present");
        }
        
        verifypresence.clear();
        
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[text()='Invoicing Frequency (KBW)']//parent::span//following-sibling::div//a")));
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Semi-Annually']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Semi-Annually should be present", "Semi-Annually is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Semi-Annually should be present", "Semi-Annually is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Annually']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Annually should be present", "Annually is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Annually should be present", "Annually is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Triannually']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Triannually should be present", "Triannually is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Triannually should be present", "Triannually is not present");
        }
        
        verifypresence.clear();
        
        js.executeScript("arguments[0].click();",wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Cancel']/parent::button)[3]"))));
        Thread.sleep(10000);
        
        lwe.ClickFieldEditButton(driver, "Research Eligibility Status(SN)");
        
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[text()='Research Eligibility Status(SN)']//parent::span//following-sibling::div//a")));
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Eligible - Research Service Agreement Signed']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Eligible - Research Service Agreement Signed should be present", "Eligible - Research Service Agreement Signed is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Eligible - Research Service Agreement Signed should be present", "Eligible - Research Service Agreement Signed is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Eligible - Exempt Attestation Provided']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Eligible - Exempt Attestation Provided should be present", "Eligible - Exempt Attestation Provided Signed is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Eligible - Exempt Attestation Provided should be present", "Eligible - Exempt Attestation Provided is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Eligible - Free Trial Period']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Eligible - Free Trial Period should be present", "Eligible - Free Trial Period is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Eligible - Free Trial Period should be present", "Eligible - Free Trial Period is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Not Eligible']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Not Eligible should be present", "Not Eligible is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Not Eligible should be present", "Not Eligible is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Pending - Awaiting Signed Research Service Agreement']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Pending - Awaiting Signed Research Service Agreement should be present", "Pending - Awaiting Signed Research Service Agreement is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Pending - Awaiting Signed Research Service Agreement should be present", "Pending - Awaiting Signed Research Service Agreement is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Pending - Awaiting Completed Exemption Attestation']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Pending - Awaiting Completed Exemption Attestation should be present", "Pending - Awaiting Completed Exemption Attestation is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Pending - Awaiting Completed Exemption Attestation should be present", "Pending - Awaiting Completed Exemption Attestation is not present");
        }
        
        verifypresence.clear(); 
        
    }
    
    
    public void ValidateInteractionReportingProfilefields(WebDriver driver, ReportGenerator suit, String folder) throws Exception{
    	
    	WebDriverWait wait = new WebDriverWait(driver,30);
    	
		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		
		lwe.ClickFieldEditButton(driver, "Interaction Reporting Frequency (SN)");
        
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[text()='Interaction Reporting Frequency (SN)']//parent::span//following-sibling::div//a")));
		
		List<WebElement> verifypresence = driver.findElements(By.xpath("//a[@title='Weekly']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Weekly should be present", "Weekly is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Weekly should be present", "Weekly is not present");
        }
        
        verifypresence.clear(); 
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Biweekly']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Biweekly should be present", "Biweekly is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Biweekly should be present", "Biweekly is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Monthly']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Monthly should be present", "Monthly is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Monthly should be present", "Monthly is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Quarterly']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Quarterly should be present", "Quarterly is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Quarterly should be present", "Quarterly is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Biannual']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Biannual should be present", "Biannual is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Biannual should be present", "Biannual is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Biannual']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Biannual should be present", "Biannual is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Biannual should be present", "Biannual is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Annually']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Annually should be present", "Annually is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Annually should be present", "Annually is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Monthly and Annually']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Monthly and Annually should be present", "Monthly and Annually is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Monthly and Annually should be present", "Monthly and Annually is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Monthly and Annually']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Monthly and Annually should be present", "Monthly and Annually is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Monthly and Annually should be present", "Monthly and Annually is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Adhoc']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Adhoc should be present", "Adhoc is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Adhoc should be present", "Adhoc is not present");
        }
        
        verifypresence.clear();
                
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[text()='Interaction Reporting Portal (SN)']//parent::span//following-sibling::div//a")));
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Bloomberg']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Bloomberg should be present", "Bloomberg is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Bloomberg should be present", "Bloomberg is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Commcise']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Commcise should be present", "Commcise is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Commcise should be present", "Commcise is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='CorpAxe']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "CorpAxe should be present", "CorpAxe is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "CorpAxe should be present", "CorpAxe is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Dealogic']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Dealogic should be present", "Dealogic is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Dealogic should be present", "Dealogic is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Direct']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Direct should be present", "Direct is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Direct should be present", "Direct is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Markit']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Markit should be present", "Markit is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Markit should be present", "Markit is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='OneAccess']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "OneAccess should be present", "OneAccess is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "OneAccess should be present", "OneAccess is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Red Deer']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Red Deer should be present", "Red Deer is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Red Deer should be present", "Red Deer is not present");
        }
        
        verifypresence.clear();
        
        verifypresence = driver.findElements(By.xpath("//a[@title='Singletrack']"));
        
        if(verifypresence.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify presence of picklist values", "Singletrack should be present", "Singletrack is present");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify presence of picklist values", "Singletrack should be present", "Singletrack is not present");
        }
        
        verifypresence.clear();

    }
    
	
    public void ValidateErrorMsgResearchServiceLevelNotes(WebDriver driver, ReportGenerator suit, String folder) throws Exception {
    	
    	WebDriverWait wait = new WebDriverWait(driver,30);
    	
		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		
		lwe.ClickFieldEditButton(driver, "Canada Research Service Level (SN)");
		
		SelectDataFromDropdown(driver, "Canada Research Service Level (SN)", "Restricted High Touch");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("(//span[text()='Save']/parent::button)[2]")));
		Thread.sleep(5000);
		
		List<WebElement> ValidateErrMsg = driver.findElements(By.xpath("//li[text()='Please enter what specific research the client has agreed to pay for  in the Research Service Level Notes box']"));
		
		if(ValidateErrMsg.size()>0) {
			Common_Functions.ResultPass(driver, suit, folder, "Verify presence of error message", "Error message should be present", "Error message is present");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Verify presence of error message", "Error message should be present", "Error message is not present");
		}
    }
    
    
    
    public String getTerritories(WebDriver driver)
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        String territories=driver.findElement(By.xpath("//td[text()='Territories Managed']//following-sibling::td[1]")).getText();
        return territories;
    }
   
    public void verifyMyterritoriesException(WebDriver driver, ReportGenerator suit, String folder,String actualTerritories) throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[text()='Save']")));
        Thread.sleep(10000);
        boolean sn=driver.findElement(By.xpath("//*[text()='My Territory (SN)']//parent::div//following-sibling::div//input[@type='checkbox']")).isSelected();
        boolean kbw=driver.findElement(By.xpath("//*[text()='My Territory (KBW)']//parent::div//following-sibling::div//input[@type='checkbox']")).isSelected();
        String expectedTerritories=driver.findElement(By.xpath("//*[text()='Territory (KBW)']//parent::div//following-sibling::div//a//span")).getText();
        if(sn && expectedTerritories.equals(actualTerritories))
        {
           
            Common_Functions.ResultPass(driver, suit, folder, "Territories should match", "Territories match successfully", "Territories matched successfully");
        }
        else             {
            Common_Functions.ResultFail(driver, suit, folder, "Territories should match", "Territories match successfully", "Territories not matched successfully");
        }
        if(kbw && expectedTerritories.equals(actualTerritories))
        {
           
            Common_Functions.ResultPass(driver, suit, folder, "Territories should match", "Territories match successfully", "Territories matched successfully");
        }
        else             {
            Common_Functions.ResultFail(driver, suit, folder, "Territories should match", "Territories match successfully", "Territories not matched successfully");
        }
    }
   
    public void verifyMyterritories(WebDriver driver, ReportGenerator suit, String folder,String actualTerritories) throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[text()='Save']")));
        Thread.sleep(10000);
        boolean sn=driver.findElement(By.xpath("//*[text()='My Territory (SN)']//parent::div//following-sibling::div//input[@type='checkbox']")).isSelected();
        boolean kbw=driver.findElement(By.xpath("//*[text()='My Territory (KBW)']//parent::div//following-sibling::div//input[@type='checkbox']")).isSelected();
        String expectedTerritories=driver.findElement(By.xpath("//*[text()='Territory (KBW)']//parent::div//following-sibling::div//a//span")).getText();
        if(sn && expectedTerritories.equalsIgnoreCase(actualTerritories))
        {
           
            Common_Functions.ResultPass(driver, suit, folder, "Territories should match", "Territories match successfully", "Territories matched successfully");
        }
        else             {
            Common_Functions.ResultFail(driver, suit, folder, "Territories should match", "Territories match successfully", "Territories matched successfully");
        }
        if(kbw && expectedTerritories.equalsIgnoreCase(actualTerritories))
        {
           
            Common_Functions.ResultPass(driver, suit, folder, "Territories should match", "Territories match successfully", "Territories matched successfully");
        }
        else             {
            Common_Functions.ResultFail(driver, suit, folder, "Territories should match", "Territories match successfully", "Territories matched successfully");
        }
    }
    
    
    
    public void addServiceLevel(WebDriver driver,String sheetName) throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        Thread.sleep(20000);
//        WebElement wb=driver.findElement(By.xpath("//button[@title='Edit US Research Service Level (SN)']"));
//        Actions act=new Actions(driver);
//        act.moveToElement(wb).perform();
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Edit US Research Service Level (SN)']"))).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[text()='Show more actions']//parent::button")));
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[text()='Research Profile (SN)']//parent::a")));
        Thread.sleep(10000);
        String data = Utilities.getParameterFromInputSheet(sheetName, "Data", 1);
        String dropdown = Utilities.getParameterFromInputSheet(sheetName, "Level", 1);
        System.out.println(dropdown);
        //Companies.SelectDataFromDropdown(driver, dropdown, data);
        lwe.selectPicklist1(driver, dropdown, data);
        //driver.navigate().refresh();
        Thread.sleep(10000);
        String dropdown1 = Utilities.getParameterFromInputSheet(sheetName, "Level", 2);
        String data1 = Utilities.getParameterFromInputSheet(sheetName, "Data", 2);
        System.out.println(dropdown1);
        //Companies.SelectDataFromDropdown(driver, dropdown1, data1);
        lwe.selectPicklist1(driver, dropdown1, data1);
        //driver.navigate().refresh();
        Thread.sleep(10000);
        String dropdown2 = Utilities.getParameterFromInputSheet(sheetName, "Level", 3);
        String data2 = Utilities.getParameterFromInputSheet(sheetName, "Data", 3);
        System.out.println(dropdown2);
        //Companies.SelectDataFromDropdown(driver, dropdown2, data2);
        lwe.selectPicklist1(driver, dropdown2, data2);
        //driver.navigate().refresh();
        Thread.sleep(10000);
        String dropdown3 = Utilities.getParameterFromInputSheet(sheetName, "Level", 4);
        String data3 = Utilities.getParameterFromInputSheet(sheetName, "Data", 4);
        System.out.println(dropdown3);
        //Companies.SelectDataFromDropdown(driver, dropdown3, data3);
        lwe.selectPicklist1(driver, dropdown3, data3);
        driver.findElement(By.xpath("//div[contains(@class,'InputTextArea')]//textarea")).sendKeys(dropdown3);
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("(//span[text()='Save']/parent::button)[1]")));
        Thread.sleep(10000);
    }

 

    public void verifyFlag(WebDriver driver, ReportGenerator suit, String folder) throws Exception
    {
        boolean flagUS=driver.findElement(By.xpath("//*[@alt='US Flag']")).isDisplayed();
        if(flagUS)
        {
            
            Common_Functions.ResultPass(driver, suit, folder, "Flag should match", "Flag match successfully", "Flag matched successfully");
        }
        else             {
            Common_Functions.ResultFail(driver, suit, folder, "Flag should match", "Flag match successfully", "Flag matched successfully");
        }
        boolean flagCA=driver.findElement(By.xpath("//*[@alt='CAFlag']")).isDisplayed();
        if(flagCA)
        {
            
            Common_Functions.ResultPass(driver, suit, folder, "Flag should match", "Flag match successfully", "Flag matched successfully");
        }
        else             {
            Common_Functions.ResultFail(driver, suit, folder, "Flag should match", "Flag match successfully", "Flag matched successfully");
        }
        boolean flagUK=driver.findElement(By.xpath("//*[@alt='UKFlag']")).isDisplayed();
        if(flagUK)
        {
            
            Common_Functions.ResultPass(driver, suit, folder, "Flag should match", "Flag match successfully", "Flag matched successfully");
        }
        else             {
            Common_Functions.ResultFail(driver, suit, folder, "Flag should match", "Flag match successfully", "Flag matched successfully");
        }
        boolean flagEU=driver.findElement(By.xpath("//*[@alt='EUFlag']")).isDisplayed();
        if(flagEU)
        {
            
            Common_Functions.ResultPass(driver, suit, folder, "Flag should match", "Flag match successfully", "Flag matched successfully");
        }
        else             {
            Common_Functions.ResultFail(driver, suit, folder, "Flag should match", "Flag match successfully", "Flag matched successfully");
        }
        
    }
    
    

//	public void newEventCompany(WebDriver driver, ReportGenerator suit, String folder, String sheetName, int sheetVal)
//			throws InterruptedException {
//		
//		SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);
//
//		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
//		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
//		SF_StandardLEX_ActionOnWebElements sweb = new SF_StandardLEX_ActionOnWebElements();
//
//		WebDriverWait wait = new WebDriverWait(driver, 50);
//
//		//sheetVal=21;
////		lwe.selectLookupValueforSubfunc(driver, "Company");
//		
//		//Fetch Company data and enter in the Company Lookup:
////		String companyName=Utilities.getParameterFromInputSheet(sheetName, "Company Name", sheetVal);
////		WebElement companyNameLookup=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search Companies...']")));
////		Actions a = new Actions(driver);
////		a.moveToElement(companyNameLookup).click().build().perform();
////		a.sendKeys(companyName).build().perform();
////		a.sendKeys(Keys.ENTER);
////		a.sendKeys(Keys.ENTER).build().perform();
////		WebElement lookupResult=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@title, '"+companyName+"')]")));
////		a.moveToElement(lookupResult).click().build().perform();
////		
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@title='Save']")));
//
////		String companyName = Utilities.getParameterFromInputSheet(sheetName, "Company Name", sheetVal);
//
//		wait.until(ExpectedConditions
//				.visibilityOfElementLocated(By.xpath("//th//a[contains(text(), '" + companyName + "')]"))).click();
//		System.out.println("Company name: "+companyName);
//	}

}
