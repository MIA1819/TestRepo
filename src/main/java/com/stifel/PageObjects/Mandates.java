package com.stifel.PageObjects;

import java.text.ParseException;
import java.util.List;
import java.util.Random;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


//Object creation code to create a new Mandate - Added by Apeksha on 08/05
public class Mandates  
{
	SF_StandardLEX_Common_Navigations nc = new SF_StandardLEX_Common_Navigations();
	SF_StandardLEX_ActionOnWebElements lwe = new SF_StandardLEX_ActionOnWebElements();
	public static  String CompanyName;
	
	
	//Method to click on Mandates tabs
	public void ClickMandatesTabs(WebDriver driver, ReportGenerator suit, String folder) throws Exception
	{
		driver.navigate().refresh();
//		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
//		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
		Thread.sleep(10000);
		nc.SearchandClickObjectTab(driver, suit, folder, "Mandates");
		Thread.sleep(10000);
		
	}
	//Method to click on new button 
	public void ClickMandatesNewbutton(WebDriver driver, ReportGenerator suit, String folder) throws Exception
	{
		 WebDriverWait wait = new WebDriverWait(driver,30);
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a/div[text()='New']"))).click();
		 Thread.sleep(5000);
	}
	
	
	//Method to validate counterparty added  on Mandate 
	public void ValidateCounterpartiesAddedonMandate(WebDriver driver, ReportGenerator suit, String folder,String Companyname) throws Exception
	{
		driver.navigate().refresh();
		Thread.sleep(10000);
		nc.SwitchTabsinLightning(driver, suit, folder, "Counterparties");
		
		List<WebElement> comp = driver.findElements(By.xpath("//a[text()='"+Companyname+"']"));
		if(comp.size()>0)
		{
			Common_Functions.ResultPass(driver, suit, folder, "Validate  Counterparties", "Counterparties should be present", "Counterparties is present");

		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Validate  Counterparties", "Counterparties should be present", "Counterparties is not present");
		}
	}
	
	public void ClickMandateEditbutton(WebDriver driver) throws Exception {
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.navigate().refresh();
		Thread.sleep(10000);
		 WebDriverWait wait = new WebDriverWait(driver,30);
		 JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].click()", wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Edit']"))));
        Thread.sleep(3000);
	}
	
	//Method to create mandate
	public String CreateMandates(WebDriver driver, ReportGenerator suit, String folder, String sheetName, int sheetVal) throws Exception
	{
		
		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
		// Random digit generator
				Random ran = new Random();
				int Ran4digit = ran.nextInt(9999);
				int Ran6digit = ran.nextInt(999999);
							
				WebDriverWait wait = new WebDriverWait(driver, 50);
				SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);	
				//To select any recordtype for mandate
				String MandateRecordType = Utilities.getParameterFromInputSheet(sheetName, "Record Type", sheetVal);
				
	            boolean MandateTypePresent = driver.findElements(By.xpath("//span[@class=' label bBody' and text()='Next']")).size()!=0;
				
				if (MandateTypePresent) {
					if (MandateRecordType != null && !MandateRecordType.isEmpty()) {

						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getRadioButton(MandateRecordType)))).click();
						JavascriptExecutor jse = (JavascriptExecutor)driver;
						jse.executeScript("arguments[0].click()", wait.until(ExpectedConditions.visibilityOf(LightningWE.NextBtn_xpath)));

					}
				}
				
				
				Thread.sleep(11000);
				
				lwe.selectLookupValueforSubfunc(driver , "Subject Company");
				lwe.selectPicklistforSubfunc2(driver, "Status");
				String MandateName = lwe.enterTextBoxWithRandomData(driver, "Mandate Name", Ran6digit);			
				lwe.selectPicklistforSubfunc2(driver, "Primary Industry Group");
				lwe.selectPicklistforSubfunc2(driver, "Product Type");
				lwe.selectPicklistforSubfunc2(driver, "Sub-Sector");
				lwe.selectPicklistforSubfunc2(driver, "Transaction Type");
				lwe.selectPicklistforSubfunc2(driver, "Primary Broker-Dealer");
				lwe.enterTextBoxWithRandomData(driver, "Est/Actual Trans. Size (MM)", Ran4digit);
				lwe.enterDateforSubfunc(driver , "Est/Actual Close Date");
				lwe.selectPicklistforSubfunc2(driver, "Mandate (Fee) Currency");			
			    lwe.enterTextBoxWithRandomData(driver, "Total Est Fee (Whole/Total Values)", Ran4digit);
                
                
					
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Save']"))).click();
				Thread.sleep(15000);	
				
				
				boolean oppty = driver.findElements(By.xpath("//h1/div[text()='Mandate']")).size()>0;
				if(oppty)
				{
					Common_Functions.ResultPass(driver, suit, folder, "Create Mandate", "Mandate is created", "Mandate is created");
					System.out.println("Mandate Created succcessfully");
				}
				else {
					Common_Functions.ResultFail(driver, suit, folder, "Create Mandate", "Mandate is created", "Mandate is not created");
					System.out.println("Mandate creation failed");
				}
				
				
				return MandateName;
				
	}
	
	
	
	public void EditMandates(WebDriver driver, ReportGenerator suit, String folder, String sheetName, int sheetVal) throws Exception
	{
		
		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
		// Random digit generator
				Random ran = new Random();
				int Ran4digit = ran.nextInt(9999);
				int Ran6digit = ran.nextInt(999999);
				SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);				
				WebDriverWait wait = new WebDriverWait(driver, 50);
				Thread.sleep(10000);
				
				lwe.selectLookupValueforSubfunc(driver , "Subject Company");
				lwe.selectPicklistforSubfunc2(driver, "Status");
				String MandateName = lwe.enterTextBoxWithRandomData(driver, "Mandate Name", Ran6digit);			
				lwe.selectPicklistforSubfunc(driver, "Primary Industry Group");
				lwe.selectPicklistforSubfunc(driver, "Product Type");
				lwe.selectPicklistforSubfunc(driver, "Sub-Sector");
				lwe.selectPicklistforSubfunc2(driver, "Transaction Type");
				lwe.selectPicklistforSubfunc2(driver, "Primary Broker-Dealer");
				lwe.selectPicklistforSubfunc2(driver, "Closed Reason");
				lwe.enterTextBoxWithRandomData(driver, "Est/Actual Trans. Size (MM)", Ran4digit);
				lwe.enterDateforSubfunc(driver , "Est/Actual Close Date");
				lwe.selectPicklistforSubfunc(driver, "Mandate Currency");			
			    lwe.enterTextBoxWithRandomData(driver, "Total Est Fee (Whole/Total Values)", Ran4digit);
			    lwe.enterTextBoxWithRandomData(driver, "Deal Code", Ran4digit);
                
                
			    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Save']"))).click();
				Thread.sleep(20000);	
				
				
				boolean oppty = driver.findElements(By.xpath("//h1/div[text()='Mandate']")).size()>0;
				if(oppty)
				{
					Common_Functions.ResultPass(driver, suit, folder, "Update Mandate", "Mandate is Updated", "Mandate is Updated");
					System.out.println("Mandate Updated succcessfully");
				}
				else {
					Common_Functions.ResultFail(driver, suit, folder, "Update Mandate", "Mandate is Updated", "Mandate is not Updated");
					System.out.println("Mandate Update failed");
				}
				
			
				
	}
	
	public void CreateWorkingGroup_ForMandates(WebDriver driver, ReportGenerator suit, String folder, String sheetName, int sheetVal) throws Exception
	{
	            SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
	            LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);			
				WebDriverWait wait = new WebDriverWait(driver, 50);

				//To create a working group for mandate
				WebElement workingGroup=driver.findElement(By.xpath("//a[text()=\"Working Group\"]"));
				wait.until(ExpectedConditions.visibilityOf(workingGroup)).click();
				Thread.sleep(10000);
				
				WebElement clickonNew=driver.findElement(By.xpath("(//div[@title='New'])[5]"));
				wait.until(ExpectedConditions.visibilityOf(clickonNew)).click();
				Thread.sleep(10000);
				
				lwe.selectLookupValue(driver , "Team Member");
				lwe.selectPicklist(driver, "Status");
				lwe.enterSpecifiedDate(driver , "Start Date");
				lwe.selectPicklist(driver, "Role");
				lwe.selectPicklist(driver, "Access");
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Save']"))).click();
				Thread.sleep(20000);
												
				
	 }
	
	public void clickonAddNewCounterPartybutton(WebDriver driver) throws InterruptedException
	{
	WebDriverWait wait = new WebDriverWait(driver, 50);

	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Add New']"))).sendKeys(Keys.ENTER);
	Thread.sleep(12000);
	
	}
	public void CreateCounterParty(WebDriver driver, ReportGenerator suit, String folder, String companyname1) throws Exception
	{
	            SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
	            LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);			
				WebDriverWait wait = new WebDriverWait(driver, 50);
				JavascriptExecutor js = (JavascriptExecutor)driver;	
				
				driver.switchTo().frame(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//iframe"))));
				Thread.sleep(5000);
				
//				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='add OR row']"))).sendKeys(Keys.ENTER);
//				Thread.sleep(10000);
			
				Select fieldfilter1 = new Select(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='sf_filter_field_1']"))));
				fieldfilter1.selectByVisibleText("Company Name");
				
				Select Operatorfilter1 = new Select(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='sf_filter_operator_1']"))));
				Operatorfilter1.selectByVisibleText("equals");
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='sf_value_1']"))).clear();
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='sf_value_1']"))).sendKeys(companyname1);
			
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Search']"))).click();
				Thread.sleep(10000);
				
				js.executeScript("arguments[0].scrollIntoView(true);", wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Select All']/.."))));
				Thread.sleep(3000); 
				
				js.executeScript("arguments[0].click();", wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Select All']/.."))));
				
				//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Select All']/.."))).click();
				Thread.sleep(5000);
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='add_btn']"))).sendKeys(Keys.ENTER);
				Thread.sleep(10000);
				

				
			
				boolean counterparty=driver.findElements(By.xpath("//h4[text()='Success:']")).size()>0;
				if(counterparty)
				{
					Common_Functions.ResultPass(driver, suit, folder, "Create Counterparty", "Counterparty should be  created", "Counterparty record is created");
					driver.switchTo().defaultContent();
				}
				else {
					Common_Functions.ResultFail(driver, suit, folder, "Create Counterparty", "Counter party should be created", "Counter party record is not created");
					driver.switchTo().defaultContent();
				}											
	 }
	
	

	public void CreateBid(WebDriver driver, ReportGenerator suit, String folder, String companyname1, String companyname2) throws Exception
	{
	            SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
	            LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);			
				WebDriverWait wait = new WebDriverWait(driver, 50);	
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@id='myTab']/div/button[text()='Manage']"))).sendKeys(Keys.ENTER);
				Thread.sleep(3000);
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='New Bid Round']"))).sendKeys(Keys.ENTER);
				Thread.sleep(3000);
				
				Select Operatorfilter2 = new Select(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='mRoundPurposeInput']"))));
				Operatorfilter2.selectByVisibleText("IOI");
				
				String minBidcomp1 = Utilities.getParameterFromInputSheet("Bids", "Min Bid", 1);
			    String minBidcomp2 = Utilities.getParameterFromInputSheet("Bids", "Min Bid", 2);
			    String maxBidcomp1 = Utilities.getParameterFromInputSheet("Bids", "Max Bid", 1);
				String maxBidcomp2 = Utilities.getParameterFromInputSheet("Bids", "Max Bid", 2);
				
				
				Actions builder = new Actions(driver);
				builder.moveToElement(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='"+companyname1+"']/../parent::td/preceding-sibling::td//input")))).click().build().perform();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='"+companyname1+"']/../parent::td/following-sibling::td[2]//input"))).sendKeys(minBidcomp1);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='"+companyname1+"']/../parent::td/following-sibling::td[6]//input"))).sendKeys(maxBidcomp1);
				
				builder.moveToElement(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='"+companyname2+"']/../parent::td/preceding-sibling::td//input")))).click().build().perform();	
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='"+companyname2+"']/../parent::td/following-sibling::td[2]//input"))).sendKeys(minBidcomp2);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='"+companyname2+"']/../parent::td/following-sibling::td[6]//input"))).sendKeys(maxBidcomp2);
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Save & Close']"))).sendKeys(Keys.ENTER);
				Thread.sleep(5000);
				
			
				boolean size_1=driver.findElements(By.xpath("//a[contains(text(),'Round')]/parent::li[contains(@class,'slds-is-active')]")).size()>0;
				if(size_1)
				{
					Common_Functions.ResultPass(driver, suit, folder, "Create Bid round ", "Bid round is created", "Bid round is created");
					
				}
				else {
					Common_Functions.ResultFail(driver, suit, folder, "Create Bid round ", "Bid round is created", "Bid round is not created");
				
				}	
				
															
	 }
	
	
	public void ValidateCompanypresentinBid(WebDriver driver, ReportGenerator suit, String folder, String companyname) throws Exception
	{
	            SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
	            LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);			
				WebDriverWait wait = new WebDriverWait(driver, 50);	
				
				boolean size_1=driver.findElements(By.xpath("//td/span[text()='"+companyname+"']")).size()>0;
				
				if(size_1)
				{
					Common_Functions.ResultPass(driver, suit, folder, "Company present in Bid round", "Company present in Bid round", "Company is  present in Bid round");
					
				}
				else {
					Common_Functions.ResultPass(driver, suit, folder, "Company present in Bid round", "Company present in Bid round", "Company is not present in Bid round");
				
				}	
				
				
	 }	
	


	public void RemoveCompanyfromBid(WebDriver driver, ReportGenerator suit, String folder, String companyname) throws Exception
	{
	            SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
	            LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);			
				WebDriverWait wait = new WebDriverWait(driver, 50);	
				Thread.sleep(3000);
				
				Actions actions = new Actions(driver);

				actions.moveToElement(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@id='myTab']/div/button[text()='Manage']")))).click().perform();
				Thread.sleep(5000);
				
				actions.moveToElement(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='"+companyname+"']/../parent::td/preceding-sibling::td//input")))).click().build().perform();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Save & Close']"))).sendKeys(Keys.ENTER);
				Thread.sleep(5000);
				
			
				boolean size_1=driver.findElements(By.xpath("//a[contains(text(),'Round')]/parent::li[contains(@class,'slds-is-active')]")).size()>0;
				if(size_1)
				{
					Common_Functions.ResultPass(driver, suit, folder, "Create Bid round ", "Bid round is created", "Bid round is created");
					
				}
				else {
					Common_Functions.ResultFail(driver, suit, folder, "Create Bid round ", "Bid round is created", "Bid round is not created");
				
				}															
	 }
	
	
	
	public void AddCounterpartyfromBids(WebDriver driver, ReportGenerator suit, String folder, String companyname) throws Exception
	{
	            SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
	            LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);			
				WebDriverWait wait = new WebDriverWait(driver, 50);	
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@id='myTab']/div/button[text()='Manage']"))).sendKeys(Keys.ENTER);
				Thread.sleep(3000);
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add Counterparty']"))).sendKeys(Keys.ENTER);
				Thread.sleep(3000);
				
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Search Counterparty']"))).sendKeys(companyname);
				
				Actions builder = new Actions(driver);
				builder.moveToElement(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='searchVals'][@data-textvalue='"+companyname+"']")))).click().build().perform();
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add']"))).sendKeys(Keys.ENTER);
				Thread.sleep(5000);
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Save & Close']"))).sendKeys(Keys.ENTER);
				Thread.sleep(5000);
				
			
				boolean size_1=driver.findElements(By.xpath("//a[contains(text(),'Round')]/parent::li[contains(@class,'slds-is-active')]")).size()>0;
				if(size_1)
				{
					Common_Functions.ResultPass(driver, suit, folder, "Create Bid round ", "Bid round is created", "Bid round is created");
					
				}
				else {
					Common_Functions.ResultFail(driver, suit, folder, "Create Bid round ", "Bid round is created", "Bid round is not created");
				
				}															
	 }
	
	
	
	public void ValidateCompanyremovedfromBid(WebDriver driver, ReportGenerator suit, String folder, String companyname) throws Exception
	{
	            SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
	            LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);			
				WebDriverWait wait = new WebDriverWait(driver, 50);	
				
				boolean size_1=driver.findElements(By.xpath("//td/span[text()='"+companyname+"']")).size()>0;
				
				if(size_1)
					if(size_1)
					{
						Common_Functions.ResultFail(driver, suit, folder, "Company should be removed from Bid round", "Company present in Bid round", "Company is  present in Bid round");
						
					}
					else {
						Common_Functions.ResultFail(driver, suit, folder, "Company should be removed from Bid round", "Company should be removed from Bid round", "Company is removed in Bid round");
					}								
	 }	
	
	public void CreateWorkingGroup_ForMandate(WebDriver driver,ReportGenerator suit, String folder, String sheetName, int sheetVal) throws Exception
	{
		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);			
				WebDriverWait wait = new WebDriverWait(driver, 50);
				SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);
				
				JavascriptExecutor js = (JavascriptExecutor)driver;
				js.executeScript("arguments[0]. click();", wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[text()='Mandate Internal Team']/ancestor::div[2]/following-sibling::div//button[text()='New']"))));
				
				Thread.sleep(4000);
				
				lwe.selectLookupValueforSubfunc(driver , "Team Member");
				lwe.selectPicklistforSubfunc2(driver, "Status");
				lwe.enterDateforSubfunc(driver , "Start Date");
				lwe.selectPicklistforSubfunc2(driver, "Role");
				lwe.selectPicklistforSubfunc2(driver, "Access");
				
				
				js.executeScript("arguments[0]. click();", wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Save']"))));
				Thread.sleep(20000);
				
				boolean workgroup = driver.findElements(By.xpath("//h2[text()='Mandate Internal Team']/ancestor::div[contains(@class, 'slds-card__header')][2]/following-sibling::div[2]/table/tbody")).size()>0;
				if(workgroup)
				{
					Common_Functions.ResultPass(driver, suit, folder, "Create Working group", "Working Group is created", "Working Group record is created");
					
				}
				else {
					Common_Functions.ResultFail(driver, suit, folder, "Create Working Group", "Working Group record is created", "Working Group record is not created");
				
				}
		
	}
	
	
	public void ClickAddMandateFeeButton(WebDriver driver,ReportGenerator suit, String folder) throws Exception{
		
		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);			
		WebDriverWait wait = new WebDriverWait(driver, 50);
		driver.navigate().refresh();
		Thread.sleep(10000);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Show more actions']/ancestor::button"))).click();
		Thread.sleep(3000);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='New Mandate Fee']/ancestor::a"))).click();
		Thread.sleep(5000);
		
	}
	
	
	public void CreateMandateFee(WebDriver driver,ReportGenerator suit, String folder, String sheetName, int sheetVal) throws Exception{
		
		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);			
		WebDriverWait wait = new WebDriverWait(driver, 50);

		SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);
		
		lwe.selectPicklist(driver, "Company");
		lwe.selectPicklist(driver, "Fee Type");
		lwe.selectPicklist(driver, "Fee Sub-Type");
		lwe.selectPicklist(driver, "Amount Currency");
		lwe.selectPicklist(driver, "Org");
		lwe.enterTextBox(driver, "Amount");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Save']/parent::button[contains(@class,'publisherShareButton')]"))).click();
		Thread.sleep(10000);

	}
	
	
	public void EditMandateFee(WebDriver driver,ReportGenerator suit, String folder, String sheetName, int sheetVal) throws Exception {
		
		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);			
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);
		
		driver.navigate().refresh();
		Thread.sleep(10000);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Fees']"))).click();
		Thread.sleep(5000);
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0]. click();",wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Fees']/parent::div/following-sibling::div[@id='infiniteScrollDiv']//table/tbody/tr[1]/td[2]//button[1]"))));
		Thread.sleep(5000);
		
		lwe.selectPicklistforSubfunc2(driver, "Org");
		lwe.enterTextBox2(driver, "Amount");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Save']"))).click();
		Thread.sleep(10000);

	}
	
	
	public void DeleteMandateFee(WebDriver driver,ReportGenerator suit, String folder) throws Exception {
		
		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);			
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		driver.navigate().refresh();
		Thread.sleep(10000);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Fees']"))).click();
		Thread.sleep(5000);
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0]. click();",wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Fees']/parent::div/following-sibling::div[@id='infiniteScrollDiv']//table/tbody/tr[1]/td[2]//button[2]"))));
		Thread.sleep(3000);
		js.executeScript("arguments[0]. click();",wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Delete']"))));
		Thread.sleep(6000);

	}
	
	
	
	
	public void ValidationofAmount(WebDriver driver,ReportGenerator suit, String folder) throws Exception {
		
		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);			
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		driver.navigate().refresh();
		Thread.sleep(10000);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Fees']"))).click();
		Thread.sleep(5000);
		
		if(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='System Projected Fees']/parent::div/following-sibling::div[@id='infiniteScrollDiv']//table/tbody/tr[1]/td[@data-label='Amount']//span"))).getText().equalsIgnoreCase("0")) {
			Common_Functions.ResultPass(driver, suit, folder, "Amount Validation after adding Mandate Fee", "Amount should be 0", "Amount is 0");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Amount Validation after adding Mandate Fee", "Amount should be 0", "Amount is not 0");
		}
		
		driver.navigate().refresh();
	}
	
	
	
	public void ValidateSystemProjectedFees1(WebDriver driver,ReportGenerator suit, String folder) throws Exception {
		
		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);			
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		driver.navigate().refresh();
		Thread.sleep(10000);
		
		String EstimatedFeefromUI = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Total Est Fee (Whole/Total Values)']/parent::div/following-sibling::div//lightning-formatted-number"))).getText();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Fees']"))).click();
		Thread.sleep(5000);
		
		if((wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='System Projected Fees']/parent::div/following-sibling::div[@id='infiniteScrollDiv']//table/tbody/tr[1]/td[@data-label='Amount']//span"))).getText()+".00").equalsIgnoreCase(EstimatedFeefromUI)) {
			Common_Functions.ResultPass(driver, suit, folder, "Amount Validation of System Projected Fee", "Amount should be equal to estimated fee", "Amount is correct");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Amount Validation of System Projected Fee", "Amount should be equal to estimated fee", "Amount is incorrect");
		}
		
	}
	
	
	public void ValidateSystemProjectedFees2(WebDriver driver,ReportGenerator suit, String folder) throws Exception {
		
		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);			
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		driver.navigate().refresh();
		Thread.sleep(10000);
		
		int ManualFeeEntered =  Integer.parseInt(Utilities.getParameterFromInputSheet("Mandate Fee", "Amount", 3));
		int ProjectedFeeEntered = Integer.parseInt(Utilities.getParameterFromInputSheet("Mandate Fee", "Amount", 4));
		
		String TotalProjectedFee = String.valueOf(ManualFeeEntered+ProjectedFeeEntered); 
		
		String EstimatedFeefromUI = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Total Est Fee (Whole/Total Values)']/parent::div/following-sibling::div//lightning-formatted-number"))).getText();
		
		EstimatedFeefromUI = EstimatedFeefromUI.substring(0, (EstimatedFeefromUI.indexOf("."))).replace(",", "");
		
		String ExpectedSystemProjectedFee = String.valueOf(Integer.parseInt(EstimatedFeefromUI)-Integer.parseInt(TotalProjectedFee));
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Fees']"))).click();
		Thread.sleep(5000);
		
		String ActualSystemProjectedFee = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='System Projected Fees']/parent::div/following-sibling::div[@id='infiniteScrollDiv']//table/tbody/tr[1]/td[@data-label='Amount']//span"))).getText().replace(",", "");
		
		if(ExpectedSystemProjectedFee.equalsIgnoreCase(ActualSystemProjectedFee)) {
			Common_Functions.ResultPass(driver, suit, folder, "Amount Validation in System Projected Fee", "Amount should be sum of manual and projected fee", "Amount is correct");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Amount Validation in System Projected Fee", "Amount should be sum of manual and projected fee", "Amount is incorrect");
		}
	}
	
	
	
	public void OutstandingEstimatedFees(WebDriver driver,ReportGenerator suit, String folder) throws Exception {
		
		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		SF_StandardLEX_Common_Navigations cn = new SF_StandardLEX_Common_Navigations();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);			
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		driver.navigate().refresh();
		Thread.sleep(10000);
		
		String MandateURL = driver.getCurrentUrl();
		
		String OutstandingDetailPageUSD = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Outstanding Estimated Fee (USD)']/parent::div/following-sibling::div//lightning-formatted-number"))).getText();
		String OutstandingDetailPageGBP = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Outstanding Estimated Fee (GBP)']/parent::div/following-sibling::div//lightning-formatted-number"))).getText();
		String OutstandingDetailPageEUR = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Outstanding Estimated Fee (EUR)']/parent::div/following-sibling::div//lightning-formatted-number"))).getText();
		String OutstandingDetailPageCAD = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Outstanding Estimated Fee (CAD)']/parent::div/following-sibling::div//lightning-formatted-number"))).getText();
		String OutstandingDetailPageILS = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Outstanding Estimated Fee (ILS)']/parent::div/following-sibling::div//lightning-formatted-number"))).getText();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Fees']"))).click();
		Thread.sleep(5000);
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0]. click();",wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Projected Fees']/parent::div/following-sibling::div[@id='infiniteScrollDiv']//table/tbody/tr[1]/td[2]//button[1]"))));
		Thread.sleep(5000);
		
		String ExchangeRateofFees = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Exchange Rate']/following::div[1]//input"))).getAttribute("placeholder").toString();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Cancel']"))).click();
		
		cn.SearchwithObjectType(driver, suit, folder, ExchangeRateofFees, "Exchange Rates");
		
		String USDtoGBPRate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='USD to GBP']/parent::div/following-sibling::div//lightning-formatted-number"))).getText();
		String USDtoEURRate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='USD to EUR']/parent::div/following-sibling::div//lightning-formatted-number"))).getText();
		String USDtoCADRate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='USD to CAD']/parent::div/following-sibling::div//lightning-formatted-number"))).getText();
		String USDtoILSRate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='USD to ILS']/parent::div/following-sibling::div//lightning-formatted-number"))).getText();
		
		driver.get(MandateURL);
		Thread.sleep(10000);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Fees']"))).click();
		Thread.sleep(5000);
		
		String ActualSystemProjectedFeeUSD = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Projected Fees']/parent::div/following-sibling::div[@id='infiniteScrollDiv']//table/tbody/tr[1]/td[@data-label='Amount']//span"))).getText()+".00";
		
		if(OutstandingDetailPageUSD.equalsIgnoreCase(ActualSystemProjectedFeeUSD)) {
			Common_Functions.ResultPass(driver, suit, folder, "Outstanding Estimated Fee (USD) Validation", "Outstanding Estimated Fee (USD) should match", "Outstanding Estimated Fee (USD) is matching");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Outstanding Estimated Fee (USD) Validation", "Outstanding Estimated Fee (USD) should match", "Outstanding Estimated Fee (USD) is not matching");
		}
		
		String ActualSystemProjectedFeeGBP = String.valueOf(Float.parseFloat(OutstandingDetailPageUSD.replace(",", ""))*Float.parseFloat(USDtoGBPRate))+"0";
		
		if(OutstandingDetailPageGBP.replace(",", "").equalsIgnoreCase(ActualSystemProjectedFeeGBP)) {
			Common_Functions.ResultPass(driver, suit, folder, "Outstanding Estimated Fee (GBP) Validation", "Outstanding Estimated Fee (GBP) should match", "Outstanding Estimated Fee (GBP) is matching");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Outstanding Estimated Fee (GBP) Validation", "Outstanding Estimated Fee (GBP) should match", "Outstanding Estimated Fee (GBP) is not matching");
		}
		
		String ActualSystemProjectedFeeEUR = String.valueOf(Float.parseFloat(OutstandingDetailPageUSD.replace(",", ""))*Float.parseFloat(USDtoEURRate))+"0";
		
		if(OutstandingDetailPageEUR.replace(",", "").equalsIgnoreCase(ActualSystemProjectedFeeEUR)) {
			Common_Functions.ResultPass(driver, suit, folder, "Outstanding Estimated Fee (EUR) Validation", "Outstanding Estimated Fee (EUR) should match", "Outstanding Estimated Fee (EUR) is matching");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Outstanding Estimated Fee (EUR) Validation", "Outstanding Estimated Fee (EUR) should match", "Outstanding Estimated Fee (EUR) is not matching");
		}
		
		String ActualSystemProjectedFeeILS = String.valueOf(Float.parseFloat(OutstandingDetailPageUSD.replace(",", ""))*Float.parseFloat(USDtoILSRate))+"0";
		
		if(OutstandingDetailPageILS.replace(",", "").equalsIgnoreCase(ActualSystemProjectedFeeILS)) {
			Common_Functions.ResultPass(driver, suit, folder, "Outstanding Estimated Fee (ILS) Validation", "Outstanding Estimated Fee (ILS) should match", "Outstanding Estimated Fee (ILS) is matching");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Outstanding Estimated Fee (ILS) Validation", "Outstanding Estimated Fee (ILS) should match", "Outstanding Estimated Fee (ILS) is not matching");
		}
	}
	
	
	
	public void fillCloneForm(WebDriver driver,String sheetName,int sheetValue) throws InterruptedException
    {
        Mandates mn=new Mandates();
        WebDriverWait wait = new WebDriverWait(driver,30);
        JavascriptExecutor js =(JavascriptExecutor)driver;
        Thread.sleep(10000);
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//strong[text()='System Projected Fees']//parent::div//following-sibling::div//td//div//a[contains(text(),'MF')]")));
        Thread.sleep(10000);
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@name='Clone']")));
        Thread.sleep(10000);
        String FeeTypeValue = Utilities.getParameterFromInputSheet(sheetName, "Fee Type", sheetValue);
        mn.selectCloneFromData(driver, "Fee Type", FeeTypeValue);
        String FeeSubType = Utilities.getParameterFromInputSheet(sheetName, "Fee Sub-Type", sheetValue);
        mn.selectCloneFromData(driver, "Fee Sub-Type", FeeSubType);
        String orgType = Utilities.getParameterFromInputSheet(sheetName, "Org", sheetValue);
        mn.selectCloneFromData(driver, "Org", orgType);
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='Amount__c']"))).clear();
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='Amount__c']"))).sendKeys("300");
        Thread.sleep(5000);
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@name='SaveEdit']")));
        Thread.sleep(15000);
    }
   
    public void selectCloneFromData(WebDriver driver,String label,String DropdownValue) throws InterruptedException
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        JavascriptExecutor js =(JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//label[text()='"+label+"']//following-sibling::div//input")));
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div//lightning-base-combobox-item//span[text()='"+DropdownValue+"']")));
        Thread.sleep(5000);
    }
   
    public void verifyCloneData(WebDriver driver,ReportGenerator suit, String folder,String sheetName,int sheetValue,String lable) throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        JavascriptExecutor js =(JavascriptExecutor)driver;
        Thread.sleep(5000);
        String FeeSubType = Utilities.getParameterFromInputSheet(sheetName, "Fee Sub-Type", sheetValue);
        String orgType = Utilities.getParameterFromInputSheet(sheetName, "Org", sheetValue);
        String actualFeeSubType=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='"+lable+"']//parent::div//following-sibling::div//td[@data-label='Fee Sub-Type']//span"))).getText();
       // String actualorgType=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='"+lable+"']//parent::div//following-sibling::div//td[@data-label='Org']//span"))).getText();
        if(FeeSubType.equalsIgnoreCase(actualFeeSubType)) {
            Common_Functions.ResultPass(driver, suit, folder, "Fee Sub-type  Validation", "Fee Sub-type should match", "Fee Sub-type is matching");
        }
        else {
            Common_Functions.ResultFail(driver, suit, folder, "Fee Sub-type Validation", "Fee Sub-type should match", "Fee Sub-type is not matching");
        }
//        if(orgType.replace(" ", "").equalsIgnoreCase(actualorgType.replace(" ", ""))) {
//            Common_Functions.ResultPass(driver, suit, folder, "Org type  Validation", "Org type should match", "Org type is matching");
//        }
//        else {
//            Common_Functions.ResultFail(driver, suit, folder, "Org type Validation", "Org type should match", "Org type is not matching");
//        }
    }
	
}
