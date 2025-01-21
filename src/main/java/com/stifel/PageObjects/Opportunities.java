package com.stifel.PageObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.stifel.LEXCommons.SF_StandardLEX_ActionOnWebElements;
import com.stifel.LEXCommons.SF_StandardLEX_Common_Navigations;
import com.stifel.LEXCommons.SF_StandardLEX_WebElementLocators;
import com.stifel.corefunctions.Common_Functions;
import com.stifel.corefunctions.ReportGenerator;
import com.stifel.corefunctions.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.stifel.corefunctions.ReportGenerator;
import com.stifel.corefunctions.Utilities;

//Object creation code to create a new Opportunity - Added by Apeksha on 08/04
public class Opportunities   {
	SF_StandardLEX_Common_Navigations nc = new SF_StandardLEX_Common_Navigations();
	SF_StandardLEX_ActionOnWebElements lwe = new SF_StandardLEX_ActionOnWebElements();
	public static  String CompanyName;
	
	//To create label for Project Name
	public String ProjectName;
	
	//To create label for Total Estimated Fee text box
	public String EstimatedFee;
	
	//Method to click on Opportunities tabs
	public void ClickOpportunitiesTabs(WebDriver driver, ReportGenerator suit, String folder) throws Exception {
		driver.navigate().refresh();		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Thread.sleep(15000);
		nc.SearchandClickObjectTab(driver, suit, folder, "Opportunities");
		
	}
	//Method to click on new button 
	public void ClickOpportunitiesNewbutton(WebDriver driver, ReportGenerator suit, String folder) throws Exception {
		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
		Thread.sleep(20000);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(LightningWE.NewBtn_xpath)).click(); 
	}
	
	
	public void ClickOpportunityEditbutton(WebDriver driver) throws Exception {
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.navigate().refresh();
		Thread.sleep(10000);
		 WebDriverWait wait = new WebDriverWait(driver,30);
		 JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].click()", wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Edit']"))));
        Thread.sleep(3000);
	}
	
	
	//Method to create opportunity 
	public void CreateOpportunites(WebDriver driver, ReportGenerator suit, String folder, String sheetName, int sheetVal) throws Exception
	{
		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
		// Random digit generator
				Random ran = new Random();
				int Ran4digit = ran.nextInt(9999);
				int Ran6digit = ran.nextInt(999999);
				SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);				
				WebDriverWait wait = new WebDriverWait(driver, 50);

				//To select any recordtype for opportunity
				String OpptyRecordType = Utilities.getParameterFromInputSheet(sheetName, "Record type", sheetVal);
				//Thread.sleep(10000);
				boolean opptyTypePresent = driver.findElements(By.xpath("//span[@class=' label bBody' and text()='Next']")).size()!=0;
				
				if (opptyTypePresent) {
					if (OpptyRecordType != null && !OpptyRecordType.isEmpty()) {

						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getRadioButton(OpptyRecordType)))).click();
						JavascriptExecutor jse = (JavascriptExecutor)driver;
						jse.executeScript("arguments[0].click()", wait.until(ExpectedConditions.visibilityOf(LightningWE.NextBtn_xpath)));

					}
				}
				//To create Opportunity object
				ProjectName = lwe.enterTextBoxWithRandomData(driver, "Mandate Name", Ran6digit);
				lwe.selectPicklistforSubfunc2(driver, "Primary Transaction Type");
				lwe.selectLookupValueforSubfunc(driver , "Company Name");
				lwe.selectPicklistforSubfunc2(driver, "Primary Product Type");
				lwe.selectPicklistforSubfunc2(driver, "Secondary Transaction Type");
				lwe.selectPicklistforSubfunc2(driver, "Secondary Product Type");
				lwe.selectPicklistforSubfunc2(driver, "Primary Industry Group");
				lwe.selectPicklistforSubfunc2(driver, "Sub-Sector");
				lwe.enterDateforSubfunc(driver , "Expected Transaction Close Date");
				lwe.selectPicklistforSubfunc2(driver, "Status");
				lwe.selectPicklistforSubfunc2(driver, "Primary Broker-Dealer");
				lwe.selectPicklistforSubfunc2(driver, "Opportunity (Fee) Currency");
				EstimatedFee = lwe.enterTextBoxWithRandomData(driver, "Total Estimated Fee (Whole Values)", Ran4digit);
                lwe.enterTextBox(driver, "Fee Structure Description");
                lwe.selectLookupValueforSubfunc(driver, "Referring Company");
                lwe.selectLookupValueforSubfunc(driver, "Referring Contact");
                lwe.selectPicklistforSubfunc2(driver, "Debt - Currency");
                lwe.enterTextBox(driver, "EBITDA (in millions)");
                lwe.enterTextBox(driver, "Debt (in millions)");
                lwe.selectPicklistforSubfunc2(driver, "Pitch (Low) - Currency");
                lwe.enterTextBox(driver, "Revenue (in millions)");
                lwe.selectPicklistforSubfunc2(driver, "Enterprise Value - Currency");
                lwe.enterTextBox(driver, "Enterprise Value (in millions)");
                lwe.enterTextBox(driver, "Pitch (Low) (in millions)");
                lwe.selectPicklistforSubfunc2(driver, "EBITDA - Currency");
                lwe.selectPicklistforSubfunc2(driver, "Sub-Sector");
				
				
                JavascriptExecutor js = (JavascriptExecutor) driver;
        		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[text()='Save']/parent::lightning-button")));
				Thread.sleep(10000);
				boolean oppty = driver.findElements(By.xpath("//h1/div[text()='Opportunity']")).size()>0;
				if(oppty)
				{
					Common_Functions.ResultPass(driver, suit, folder, "Create Opportunity", "Opportunity is created", "Opportunity is created");
					System.out.println("Opportunity Created succcessfully");
				}
				else {
					Common_Functions.ResultFail(driver, suit, folder, "Create Opportunity", "Opportunity is created", "Opportunity is not created");
					System.out.println("Opportunity creation failed");
				}
				
				
				
	}
	
	public void CreateWorkingGroup_ForOppty(WebDriver driver,ReportGenerator suit, String folder, String sheetName, int sheetVal) throws Exception
	{
		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);			
				WebDriverWait wait = new WebDriverWait(driver, 50);
				SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);
				
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("arguments[0].click()",wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[text()='Opportunity Internal Team']/ancestor::div[2]/following-sibling::div//button[text()='New']"))));
				Thread.sleep(4000);
				
				lwe.selectLookupValueforSubfunc(driver , "Team Member");
				lwe.selectPicklistforSubfunc2(driver, "Status");
				lwe.enterDateforSubfunc(driver , "Start Date");
				lwe.selectPicklistforSubfunc2(driver, "Role");
				lwe.selectPicklistforSubfunc2(driver, "Access");
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Save']"))).click();
				Thread.sleep(10000);
				
				if(driver.findElement(By.xpath("//div[text()='Opportunity Internal Team']/following-sibling::slot//lightning-formatted-text")).getText().contains("OIT"))
				{
					Common_Functions.ResultPass(driver, suit, folder, "Create Working group", "Working Group is created", "Working Group record is created");
					
				}
				else {
					Common_Functions.ResultFail(driver, suit, folder, "Create Working Group", "Working Group record is created", "Working Group record is not created");
				
				}
		
	}
	
	public void EditOpportunites(WebDriver driver, ReportGenerator suit, String folder, String sheetName, int sheetVal) throws Exception {
		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
		// Random digit generator
				Random ran = new Random();
				int Ran4digit = ran.nextInt(9999);
				int Ran6digit = ran.nextInt(999999);
				SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);				
				WebDriverWait wait = new WebDriverWait(driver, 50);
				

				
				//To update Opportunity object
			    ProjectName = lwe.enterTextBoxWithRandomData(driver, "Project Name", Ran6digit);
				lwe.selectPicklistforSubfunc2(driver, "Type");
				lwe.selectLookupValueforSubfunc(driver , "Company Name");
				lwe.selectPicklistforSubfunc2(driver, "Industry Group");
				lwe.enterSpecifiedDate(driver , "Expected Transaction Date");
				lwe.selectPicklistforSubfunc2(driver, "Status");
				lwe.selectPicklistforSubfunc2(driver, "Primary Broker-Dealer");
				lwe.selectPicklistforSubfunc2(driver, "Opportunity (Fee) Currency");
				lwe.selectPicklistforSubfunc2(driver, "Substage");
				lwe.selectPicklistforSubfunc2(driver, "Win/Loss Reason");
				EstimatedFee = lwe.enterTextBoxWithRandomData(driver, "Total Estimated Fee (Whole Values)", Ran4digit);
				
				lwe.enterTextBox(driver, "Industry Banker Primary");
				
				Thread.sleep(5000);
				
				JavascriptExecutor js = (JavascriptExecutor) driver;
        		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[text()='Save']/parent::lightning-button")));
				Thread.sleep(10000);
				
				boolean oppty = driver.findElements(By.xpath("//h1/div[text()='Opportunity']")).size()>0;
				if(oppty)
				{
					Common_Functions.ResultPass(driver, suit, folder, "Update Opportunity", "Opportunity is Updated", "Opportunity is Updated");
					System.out.println("Opportunity Update succcessfully");
				}
				else {
					Common_Functions.ResultFail(driver, suit, folder, "Update Opportunity", "Opportunity is Updated", "Opportunity is not Updated");
					System.out.println("Opportunity Update failed");
				}
				
				
				
	}
	
	public void ChangeOpptyStage(WebDriver driver, String stageName, int sheetVal) {
		
		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
		//SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);
		WebDriverWait wait = new WebDriverWait(driver, 50);
		Actions a=new Actions(driver);
		
		WebElement ele=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='"+stageName+"']/span[text()='"+stageName+"']")));
		a.moveToElement(ele).click().build().perform();
	}
	
	
	
	public void clickButton(WebDriver driver, ReportGenerator suit, String folder, String buttonText) {
		
		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
		WebDriverWait wait = new WebDriverWait(driver, 50);
		Actions a=new Actions(driver);
		
		WebElement ele=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='"+buttonText+"']")));
		a.moveToElement(ele).click().build().perform();
	}

	public void selectStageChangeDependencies(WebDriver driver, ReportGenerator suit, String folder, String sheetName, String labelName, int sheetVal) throws Exception {
		//label[text()='Stage']/following::div[1]//input	
		
		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
		WebDriverWait wait = new WebDriverWait(driver, 50);
		Actions a=new Actions(driver);
		
		
		
		SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);
		
		lwe.selectPicklistforSubfunc(driver, labelName);
		
//		WebElement ele=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='"+labelName+"']/following-sibling::div//input")));
//		ele.sendKeys(excelVal);
//		ele.sendKeys(Keys.ENTER);
	}

	public void verifyStageChange(WebDriver driver, ReportGenerator suit, String folder, String stageName) throws Exception {
		
		WebDriverWait wait = new WebDriverWait(driver, 50);

		List<WebElement> oppty = driver.findElements(By.xpath("//a[@title='"+stageName+"']/span[text()='"+stageName+"']/ancestor::li[contains(@class, 'current')]"));
		if (oppty.size()>0) {
			Common_Functions.ResultPass(driver, suit, folder, "Change Opportunity Stage to: "+stageName, "Opportunity stage should change",
					"Opportunity Stage has changed");
			System.out.println("Opportunity Update succcessfully");
		} else {
			Common_Functions.ResultFail(driver, suit, folder, "Change Opportunity Stage to: "+stageName, "Opportunity stage should change",
					"Opportunity Stage has not changed");
			System.out.println("Opportunity Update failed");
		}

	}
	
	
	public String GetMandateURL(WebDriver driver, ReportGenerator suit, String folder) throws InterruptedException {
		
		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		driver.navigate().refresh();
		Thread.sleep(10000);

		String NewMandateURL = driver.findElement(By.xpath("//span[contains(text(),'New Mandate')]/following::a[1]")).getText();
		
		return NewMandateURL;
		
	}
	
	
	public void CompareOpportunityandMandate(WebDriver driver, ReportGenerator suit, String folder) throws Exception {
		
		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		driver.navigate().refresh();
		Thread.sleep(10000);
		
		String OpportunityName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Mandate Name']/parent::div/following-sibling::div//lightning-formatted-text"))).getText();
		String ReferringCompanyOpp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Referring Company']/parent::div/following-sibling::div//a//span"))).getText();
		String ReferringContactOpp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Referring Contact']/parent::div/following-sibling::div//a//span"))).getText();
		String TotalEstimatedFeeOpp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Total Estimated Fee (Whole Values)']/parent::div/following-sibling::div//lightning-formatted-number"))).getText();
		String TotalEstimatedFeeUSDOpp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Total Estimated Fee (USD - Whole Values)']/parent::div/following-sibling::div//lightning-formatted-number"))).getText();
		String TotalEstimatedFeeGBPOpp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Total Estimated Fee (GBP - Whole Values)']/parent::div/following-sibling::div//lightning-formatted-number"))).getText();
		String TotalEstimatedFeeEUROpp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Total Estimated Fee (EUR - Whole Values)']/parent::div/following-sibling::div//lightning-formatted-number"))).getText();
		String DebtCurrencyOpp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Debt - Currency']/parent::div/following-sibling::div//lightning-formatted-text"))).getText();
		String EBITDAinmillionsOpp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='EBITDA (in millions)']/parent::div/following-sibling::div//lightning-formatted-number"))).getText();
		String DebtinmillionsOpp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Debt (in millions)']/parent::div/following-sibling::div//lightning-formatted-number"))).getText();
		String PitchLowCurrencyOpp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Pitch (Low) - Currency']/parent::div/following-sibling::div//lightning-formatted-text"))).getText();
		String PitchLowinmillionsOpp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Pitch (Low) (in millions)']/parent::div/following-sibling::div//lightning-formatted-number"))).getText();
		String RevenueinmillionsOpp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Revenue (in millions)']/parent::div/following-sibling::div//lightning-formatted-number"))).getText();
		String EnterpriseValueCurrencyOpp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Enterprise Value - Currency']/parent::div/following-sibling::div//lightning-formatted-text"))).getText();
		String EnterpriseValueinmillionsOpp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Enterprise Value (in millions)']/parent::div/following-sibling::div//lightning-formatted-number"))).getText();
		String EBITDACurrencyOpp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='EBITDA - Currency']/parent::div/following-sibling::div//lightning-formatted-text"))).getText();
		String OpportunityCurrencyOpp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Opportunity (Fee) Currency']/parent::div/following-sibling::div//lightning-formatted-text"))).getText();
		String PrimaryBrokerDealerOpp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Primary Broker-Dealer']/parent::div/following-sibling::div//lightning-formatted-text"))).getText();
		String SubSectorOpp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Sub-Sector']/parent::div/following-sibling::div//lightning-formatted-text"))).getText();
		
		
		driver.navigate().refresh();
		Thread.sleep(10000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'New Mandate')]/following::a[1]"))));
		Thread.sleep(10000);
		
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		
		String MandateName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Mandate Name']/parent::div/following-sibling::div//lightning-formatted-text"))).getText();
		String ReferringCompanyMan = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Referring Company']/parent::div/following-sibling::div//a/span"))).getText();
		String ReferringContactMan = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Referring Contact']/parent::div/following-sibling::div//a/span"))).getText();
		String TotalEstimatedFeeMan = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Total Est Fee (Whole/Total Values)']/parent::div/following-sibling::div//lightning-formatted-number"))).getText();
		String TotalEstimatedFeeUSDMan = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Total Est Fee (USD - Whole/Total Values)']/parent::div/following-sibling::div//lightning-formatted-number"))).getText();
		String TotalEstimatedFeeGBPMan = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Total Est Fee (GBP - Whole/Total Values)']/parent::div/following-sibling::div//lightning-formatted-number"))).getText();
		String TotalEstimatedFeeEURMan = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Total Est Fee (EUR - Whole/Total Values)']/parent::div/following-sibling::div//lightning-formatted-number"))).getText();
		String DebtCurrencyMan = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Debt - Currency']/parent::div/following-sibling::div//lightning-formatted-text"))).getText();
		String EBITDAinmillionsMan = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='EBITDA (in millions)']/parent::div/following-sibling::div//lightning-formatted-number"))).getText();
		String DebtinmillionsMan = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Debt (in millions)']/parent::div/following-sibling::div//lightning-formatted-number"))).getText();
		String PitchLowCurrencyMan = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Pitch (Low) - Currency']/parent::div/following-sibling::div//lightning-formatted-text"))).getText();
		String PitchLowinmillionsMan = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Pitch (Low) (in millions)']/parent::div/following-sibling::div//lightning-formatted-number"))).getText();
		String RevenueinmillionsMan = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Revenue (in millions)']/parent::div/following-sibling::div//lightning-formatted-number"))).getText();
		String EnterpriseValueCurrencyMan = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Enterprise Value - Currency']/parent::div/following-sibling::div//lightning-formatted-text"))).getText();
		String EnterpriseValueinmillionsMan = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Enterprise Value (in millions)']/parent::div/following-sibling::div//lightning-formatted-number"))).getText();
		String EBITDACurrencyMan = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='EBITDA - Currency']/parent::div/following-sibling::div//lightning-formatted-text"))).getText();
		String OpportunityCurrencyMan = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Mandate (Fee) Currency']/parent::div/following-sibling::div//lightning-formatted-text"))).getText();
		String PrimaryBrokerDealerMan = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Primary Broker-Dealer']/parent::div/following-sibling::div//lightning-formatted-text"))).getText();
		String SubSectorMan = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Sub-Sector']/parent::div/following-sibling::div//lightning-formatted-text"))).getText();

		
		if(MandateName.contains(OpportunityName)) {
			Common_Functions.ResultPass(driver, suit, folder, "Comparison of Opportunity and mandate","OpportunityName and MandateName should match", "OpportunityName and MandateName is a match");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Comparison of Opportunity and mandate","OpportunityName and MandateName should match", "OpportunityName and MandateName is not a match");
		}
		
		if(ReferringCompanyOpp.equalsIgnoreCase(ReferringCompanyMan)) {
			Common_Functions.ResultPass(driver, suit, folder, "Comparison of Opportunity and mandate","Referring Company should match", "Referring Company is a match");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Comparison of Opportunity and mandate","Referring Company should match", "Referring Company is not a match");
		}
		
		if(ReferringContactOpp.equalsIgnoreCase(ReferringContactMan)) {
			Common_Functions.ResultPass(driver, suit, folder, "Comparison of Opportunity and mandate","Referring Contact should match", "Referring Contact is a match");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Comparison of Opportunity and mandate","Referring Contact should match", "Referring Contact is not a match");
		}
		
		if(TotalEstimatedFeeOpp.equalsIgnoreCase(TotalEstimatedFeeMan)) {
			Common_Functions.ResultPass(driver, suit, folder, "Comparison of Opportunity and mandate","TotalEstimatedFee should match", "TotalEstimatedFee is a match");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Comparison of Opportunity and mandate","TotalEstimatedFee should match", "TotalEstimatedFee is not a match");
		}
		
		
		if(TotalEstimatedFeeUSDOpp.equalsIgnoreCase(TotalEstimatedFeeUSDMan)) {
			Common_Functions.ResultPass(driver, suit, folder, "Comparison of Opportunity and mandate","TotalEstimatedFeeUSD should match", "TotalEstimatedFeeUSD is a match");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Comparison of Opportunity and mandate","TotalEstimatedFeeUSD should match", "TotalEstimatedFeeUSD is not a match");
		}
		
		if(TotalEstimatedFeeGBPOpp.equalsIgnoreCase(TotalEstimatedFeeGBPMan)) {
			Common_Functions.ResultPass(driver, suit, folder, "Comparison of Opportunity and mandate","TotalEstimatedFeeGBP should match", "TotalEstimatedFeeGBP is a match");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Comparison of Opportunity and mandate","TotalEstimatedFeeGBP should match", "TotalEstimatedFeeGBP is not a match");
		}
		
		if(TotalEstimatedFeeEUROpp.equalsIgnoreCase(TotalEstimatedFeeEURMan)) {
			Common_Functions.ResultPass(driver, suit, folder, "Comparison of Opportunity and mandate","TotalEstimatedFeeEUR should match", "TotalEstimatedFeeEUR is a match");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Comparison of Opportunity and mandate","TotalEstimatedFeeEUR should match", "TotalEstimatedFeeEUR is not a match");
		}
		
		
		if(DebtCurrencyOpp.equalsIgnoreCase(DebtCurrencyMan)) {
			Common_Functions.ResultPass(driver, suit, folder, "Comparison of Opportunity and mandate","DebtCurrency should match", "DebtCurrency is a match");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Comparison of Opportunity and mandate","DebtCurrency should match", "DebtCurrency is not a match");
		}
		
		if(EBITDAinmillionsOpp.equalsIgnoreCase(EBITDAinmillionsMan)) {
			Common_Functions.ResultPass(driver, suit, folder, "Comparison of Opportunity and mandate","EBITDA in millions should match", "EBITDA in millions is a match");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Comparison of Opportunity and mandate","EBITDA in millions should match", "EBITDA in millions is not a match");
		}
		
		if(DebtinmillionsOpp.equalsIgnoreCase(DebtinmillionsMan)) {
			Common_Functions.ResultPass(driver, suit, folder, "Comparison of Opportunity and mandate","Debt in millions should match", "Debt in millions is a match");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Comparison of Opportunity and mandate","Debt in millions should match", "Debt in millions is not a match");
		}
		
		if(PitchLowCurrencyOpp.equalsIgnoreCase(PitchLowCurrencyMan)) {
			Common_Functions.ResultPass(driver, suit, folder, "Comparison of Opportunity and mandate","PitchLowCurrency should match", "PitchLowCurrency is a match");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Comparison of Opportunity and mandate","PitchLowCurrency should match", "PitchLowCurrency is not a match");
		}
		
		if(PitchLowinmillionsOpp.equalsIgnoreCase(PitchLowinmillionsMan)) {
			Common_Functions.ResultPass(driver, suit, folder, "Comparison of Opportunity and mandate","PitchLowinmillions should match", "PitchLowinmillions is a match");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Comparison of Opportunity and mandate","PitchLowinmillions should match", "PitchLowinmillions is not a match");
		}
		
		if(RevenueinmillionsOpp.equalsIgnoreCase(RevenueinmillionsMan)) {
			Common_Functions.ResultPass(driver, suit, folder, "Comparison of Opportunity and mandate","Revenueinmillions should match", "Revenueinmillions is a match");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Comparison of Opportunity and mandate","Revenueinmillions should match", "Revenueinmillions is not a match");
		}
		
		if(EnterpriseValueCurrencyOpp.equalsIgnoreCase(EnterpriseValueCurrencyMan)) {
			Common_Functions.ResultPass(driver, suit, folder, "Comparison of Opportunity and mandate","EnterpriseValueCurrency should match", "EnterpriseValueCurrency is a match");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Comparison of Opportunity and mandate","EnterpriseValueCurrency should match", "EnterpriseValueCurrency is not a match");
		}
		
		
		if(EnterpriseValueinmillionsOpp.equalsIgnoreCase(EnterpriseValueinmillionsMan)) {
			Common_Functions.ResultPass(driver, suit, folder, "Comparison of Opportunity and mandate","EnterpriseValueinmillions should match", "EnterpriseValueinmillions is a match");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Comparison of Opportunity and mandate","EnterpriseValueinmillions should match", "EnterpriseValueinmillions is not a match");
		}
		
		if(EBITDACurrencyOpp.equalsIgnoreCase(EBITDACurrencyMan)) {
			Common_Functions.ResultPass(driver, suit, folder, "Comparison of Opportunity and mandate","EBITDACurrency should match", "EBITDACurrency is a match");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Comparison of Opportunity and mandate","EBITDACurrency should match", "EBITDACurrency is not a match");
		}
		
		if(OpportunityCurrencyOpp.equalsIgnoreCase(OpportunityCurrencyMan)) {
			Common_Functions.ResultPass(driver, suit, folder, "Comparison of Opportunity and mandate","OpportunityCurrency should match", "OpportunityCurrency is a match");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Comparison of Opportunity and mandate","OpportunityCurrency should match", "OpportunityCurrency is not a match");
		}
		
		if(PrimaryBrokerDealerOpp.equalsIgnoreCase(PrimaryBrokerDealerMan)) {
			Common_Functions.ResultPass(driver, suit, folder, "Comparison of Opportunity and mandate","PrimaryBrokerDealer should match", "PrimaryBrokerDealer is a match");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Comparison of Opportunity and mandate","PrimaryBrokerDealer should match", "PrimaryBrokerDealer is not a match");
		}
		
		if(SubSectorOpp.equalsIgnoreCase(SubSectorMan)) {
			Common_Functions.ResultPass(driver, suit, folder, "Comparison of Opportunity and mandate","SubSector should match", "SubSector is a match");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Comparison of Opportunity and mandate","SubSector should match", "SubSector is not a match");
		}
		
	}
	
	
	public void verifyStageChangeMessage(WebDriver driver, ReportGenerator suit, String folder) throws Exception {
        
        WebDriverWait wait = new WebDriverWait(driver, 50);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Mark Stage as Complete']")));
        Thread.sleep(10000);
        String msg=driver.findElement(By.xpath("//div//span[@data-aura-class='forceActionsText']")).getText();
        if (msg.contains("lead industry banker")) {
            Common_Functions.ResultPass(driver, suit, folder, "Validate Message", "Message Should Displayed",
                    "Message Displayed");
        } else {
            Common_Functions.ResultFail(driver, suit, folder, "Validate Message", "Message Should Displayed",
                    "Message Not Displayed");
        }
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[text()='You encountered some errors when trying to save this record']//parent::*//parent::*//following-sibling::button")));

 

    }
    
    public void EditOpportunityInternalTeamMember(WebDriver driver, ReportGenerator suit, String folder) throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//h2[text()='Opportunity Internal Team']/ancestor::div[3]/following-sibling::div[2]//tbody/tr[1]/td[2]//button[1]")));
        Thread.sleep(5000);
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//label[text()='Role']//following-sibling::div//input")));
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Lead Industry Banker']")));    
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[text()='Save']")));
        Thread.sleep(5000);
    }
    
    public void verifyStageChangeMessageForPitch(WebDriver driver, ReportGenerator suit, String folder) throws Exception {
        
        WebDriverWait wait = new WebDriverWait(driver, 50);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Mark Stage as Complete']")));
        Thread.sleep(10000);
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Mark Stage as Complete']")));
        Thread.sleep(10000);
        String msg=driver.findElement(By.xpath("//div//span[@data-aura-class='forceActionsText']")).getText();
        if (msg.contains("Please provide the pitch date to continue")) {
            Common_Functions.ResultPass(driver, suit, folder, "Validate Message", "Message Should Displayed",
                    "Message Displayed");
        } else {
            Common_Functions.ResultFail(driver, suit, folder, "Validate Message", "Message Should Displayed",
                    "Message Not Displayed");
        }
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[text()='You encountered some errors when trying to save this record']//parent::*//parent::*//following-sibling::button")));

 

    }
    
    public void AddPitchDate(WebDriver driver, ReportGenerator suit, String folder) throws Exception
    {
           WebDriverWait wait = new WebDriverWait(driver, 50);
           JavascriptExecutor js = (JavascriptExecutor) driver;
              js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[text()='Edit']")));
              Thread.sleep(5000);
           //lwe.enterDateforSubfunc(driver, "Pitch Date");
              wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Pitch Date']//following-sibling::div//input"))).sendKeys("5/26/2021");
              Thread.sleep(5000);
           js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[text()='Save']")));
        //   js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[text()='Save']")));
           
    }
    
    
    
    public void CloseOpportunityConvertItToMandate(WebDriver driver, ReportGenerator suit, String folder) throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        JavascriptExecutor js = (JavascriptExecutor) driver;
           js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//label[text()='Win/Loss Reason']/..//input")));
           js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Long-Standing Relationship']")));
           js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//label[text()='Substage']/..//input")));
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Primary Product Mandated Only']")));
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[text()='Done']")));
        Thread.sleep(10000);
        
        driver.navigate().refresh();
		Thread.sleep(10000);
	
		js.executeScript("arguments[0].click();",wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'New Mandate')]/following::a[1]"))));
		Thread.sleep(10000);
		
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
        
    }
    
    public void verifyCostCenterPickListValues(WebDriver driver, ReportGenerator suit, String folder)throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        JavascriptExecutor js = (JavascriptExecutor) driver;
           js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[contains(text(),'New Mandate ')]//following-sibling::a")));
           Thread.sleep(10000);
           js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[text()='Edit']")));
        Thread.sleep(5000);
        List<WebElement> CheckListValuesExists = driver.findElements(By.xpath("//label[text()='Cost Center']/..//span[2]/span"));
        if (CheckListValuesExists.size()==50) {
             Common_Functions.ResultPass(driver, suit, folder, "Validate List Value", "All List Value Should Displayed",
                     "List Values are Displayed");
         } else {
             Common_Functions.ResultFail(driver, suit, folder, "Validate List Value", "All List Value Should Displayed",
                     "List Values are not Displayed");
         }
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[text()='You encountered some errors when trying to save this record']//parent::*//parent::*//following-sibling::button")));
    }
    
    public void Add_Est_Actual_TransSize(WebDriver driver, ReportGenerator suit, String folder) throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        JavascriptExecutor js = (JavascriptExecutor) driver;
           js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[text()='Edit']")));
           Thread.sleep(5000);
           wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Est/Actual Trans. Size (MM)']/..//input"))).sendKeys("10.00");
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[text()='Save']")));
        Thread.sleep(5000);
     }
    
    public void verifyStageChangeMessageForEngaged_Passed_CC_Committee(WebDriver driver, ReportGenerator suit, String folder) throws Exception {
        
        WebDriverWait wait = new WebDriverWait(driver, 50);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String msg=driver.findElement(By.xpath("//div//span[@data-aura-class='forceActionsText']")).getText();
        if (msg.contains("Please provide the committee approval date, date our economics approved date, or EL signed date to continue")) {
            Common_Functions.ResultPass(driver, suit, folder, "Validate Message", "Message Should Displayed",
                    "Message Displayed");
        } else {
            Common_Functions.ResultFail(driver, suit, folder, "Validate Message", "Message Should Displayed",
                    "Message Not Displayed");
        }
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[text()='You encountered some errors when trying to save this record']//parent::*//parent::*//following-sibling::button")));



    }
    
    public void verifyStageChangeMessageForClosed(WebDriver driver, ReportGenerator suit, String folder) throws Exception {
           
           WebDriverWait wait = new WebDriverWait(driver, 50);
           JavascriptExecutor js = (JavascriptExecutor) driver;
           js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[text()='Edit']")));
         Thread.sleep(5000);
         js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//label[text()='Status']/..//input")));
         js.executeScript("arguments[0].click();", driver.findElement(By.xpath("///span[@title='Closed']")));
         js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//label[text()='Closed Reason']/..//input")));
         js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[@title='Settled/Paid']")));
         js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[text()='Save']")));
         Thread.sleep(5000);
           String msg=driver.findElement(By.xpath("//div[@class='pageLevelErrors']//li[3]")).getText();
           
           if (msg.contains("Please provide values for Shares Underwritten, Price per Share, Economics, and Spread to continue")) {
               Common_Functions.ResultPass(driver, suit, folder, "Validate Message", "Message Should Displayed",
                       "Message Displayed");
           } else {
               Common_Functions.ResultFail(driver, suit, folder, "Validate Message", "Message Should Displayed",
                       "Message Not Displayed");
           }
           js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[text()='You encountered some errors when trying to save this record']//parent::*//parent::*//following-sibling::button")));



       }
	
	
		
}



