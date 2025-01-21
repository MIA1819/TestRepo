package com.stifel.PageObjects;

import java.time.LocalDate;
import java.util.Random;

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

import com.stifel.LEXCommons.SF_StandardLEX_ActionOnWebElements;
import com.stifel.LEXCommons.SF_StandardLEX_Common_Navigations;
import com.stifel.LEXCommons.SF_StandardLEX_WebElementLocators;
import com.stifel.corefunctions.Common_Functions;
import com.stifel.corefunctions.ReportGenerator;
import com.stifel.corefunctions.Utilities;

public class LogInteraction {
	
	SF_StandardLEX_Common_Navigations nc = new SF_StandardLEX_Common_Navigations();
	SF_StandardLEX_ActionOnWebElements lwe = new SF_StandardLEX_ActionOnWebElements();
	
	
	
	public void LogInteractions(WebDriver driver, ReportGenerator currentSuit, String folder, String sheetName, int sheetVal, String URL) throws Exception {
		
		// Random digit generator
		String Org = Utilities.getParameterFromInputSheet(sheetName, "Org", sheetVal);
		
		JavascriptExecutor js =(JavascriptExecutor)driver;
		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
		
		if(Org.equalsIgnoreCase("IB")) {
			
			Random ran = new Random();
			int Ran4digit = ran.nextInt(9999);
		
			SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);
		
			WebDriverWait wait = new WebDriverWait(driver, 50);
		
			driver.get(URL);
			Thread.sleep(10000);
			
			nc.SwitchTabsinLightning(driver, currentSuit, folder, "Interactions");
			Thread.sleep(10000);
			
			js.executeScript("arguments[0].click();", wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Log Interaction') or contains(text(),'Log  Interaction')]/parent::button"))));
			Thread.sleep(5000);
		
			wait.until(ExpectedConditions.visibilityOf(LightningWE.SubjectLogInteractionBox)).sendKeys("Interaction"+Ran4digit);
		
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Type']/parent::label/following-sibling::input")));
			Thread.sleep(2000);
		
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[text()='Meeting']")));
			Thread.sleep(2000);
			
			String MeetingPurpose = Utilities.getParameterFromInputSheet("Interactions", "Meeting Purpose", 1);
			
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@data-value='"+MeetingPurpose+"']")));
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@title='Move selection to Selected Meeting Purpose']")));
			
			enterautoboxforInteration(driver, "Companies", "Interactions",1);
		
			enterautoboxforInteration(driver, "Contacts", "Interactions",1);
		
			enterautoboxforInteration(driver, "Opportunity", "Interactions",1);
		
			String MandatefromExcel = Utilities.getParameterFromInputSheet("Interactions", "Mandates", 1);
			
			if(MandatefromExcel!=null) {
			
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='FieldLabelText'][contains(text(),'Mandates')]/following-sibling::div//input")));
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//input[@placeholder='Search Mandates...']")).sendKeys(MandatefromExcel);
			Thread.sleep(5000);
			
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[text()='Search']")));
			Thread.sleep(3000);
			
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//table[@id='dataTable']/tbody/tr[1]//input[@type='checkbox']")));
			
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[text()='Add Mandate(s)']")));
			Thread.sleep(3000);
			
			}

			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[text()='Save']")));
			Thread.sleep(20000);
		
			if(LightningWE.InteractionNotePageValidation.isDisplayed()) {
				Common_Functions.ResultPass(driver, currentSuit, folder, "Create Interaction Note Validation", "Interaction Note should be created", "Interaction Note is created");
			}
		
			else {
				Common_Functions.ResultFail(driver, currentSuit, folder, "Create Interaction Note Validation", "Interaction Note should be created", "Interaction Note is not created");
			}
		}
		
		else if(Org.equalsIgnoreCase("RST")){
			Random ran = new Random();
			int Ran4digit = ran.nextInt(9999);
		
			SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);
		
			WebDriverWait wait = new WebDriverWait(driver, 50);
		
			driver.get(URL);
			Thread.sleep(15000);
			
			nc.SwitchTabsinLightning(driver, currentSuit, folder, "Interactions");
			Thread.sleep(10000);
		
			
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[contains(text(), 'Log  Interaction')]/parent::button")));
			Thread.sleep(5000);
			
			//js.executeScript("arguments[0].click();", wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Type']/parent::label/following-sibling::input"))));
			//Thread.sleep(2000);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Type']/parent::label/following-sibling::input"))).sendKeys(Keys.ENTER);
			
			js.executeScript("arguments[0].click();", wait.until(ExpectedConditions.elementToBeClickable(LightningWE.SelectaTypeOptionLogInteractionBox_RST)));
			Thread.sleep(2000);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Sub-Type']/parent::label/following-sibling::input"))).sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			
			js.executeScript("arguments[0].click();",wait.until(ExpectedConditions.visibilityOf(LightningWE.SelectaSubTypeOptionLogInteractionBox_RST)));
			Thread.sleep(2000);
		
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[text()='Save']")));
			Thread.sleep(20000);
		
			if(driver.findElement(By.xpath("//table/tbody/tr[1]/td[6]//div[text()='1x1 Meeting']")).isDisplayed()) {
				Common_Functions.ResultPass(driver, currentSuit, folder, "Create Interaction Note Validation", "Interaction Note should be created", "Interaction Note is created");
			}
		
			else {
				Common_Functions.ResultFail(driver, currentSuit, folder, "Create Interaction Note Validation", "Interaction Note should be created", "Interaction Note is not created");
			}
		}
	}
	
	
	
	
	public void enterautoboxforInteration(WebDriver driver, String labelName, String sheetName, int sheetVal) throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		String excelValue = Utilities.getParameterFromInputSheet(sheetName, labelName, sheetVal);
		
		if (excelValue != null && !excelValue.isEmpty()) {	
			
			driver.findElement(By.xpath("//div[@class='FieldLabelText'][contains(text(),'"+labelName+"')]/following-sibling::div//input")).sendKeys(excelValue);
			Thread.sleep(5000);
			
			Actions builder = new Actions(driver);
			builder.moveToElement(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='FieldLabelText'][contains(text(),'"+labelName+"')]/following-sibling::div//ul/li[1]/a[@class='searchVals']")))).click().build().perform();
			
			//JavascriptExecutor js =(JavascriptExecutor)driver;
			//js.executeScript("arguments[0].click();", wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='FieldLabelText'][contains(text(),'"+labelName+"')]/following-sibling::div//ul/li[1]/a[@class='searchVals']"))));
			//driver.findElement(By.xpath("//div[@class='FieldLabelText'][contains(text(),'"+labelName+"')]/following-sibling::div//ul/li[1]/a[@class='searchVals']")).sendKeys(Keys.RETURN);
			Thread.sleep(3000);
		}

	}
	
	
	public void currencyValidationCAD(WebDriver driver, ReportGenerator currentSuit, String folder, String sheetName, int sheetVal, String URL) throws Exception {
	       
        // Random digit generator
        String Org = Utilities.getParameterFromInputSheet(sheetName, "Org", sheetVal);
       
        JavascriptExecutor js =(JavascriptExecutor)driver;
        SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
        LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
        Random ran = new Random();
        int Ran4digit = ran.nextInt(9999);
   
        SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);
   
        WebDriverWait wait = new WebDriverWait(driver, 50);
   
        driver.get(URL);
        Thread.sleep(15000);
       
        //nc.SwitchTabsinLightning(driver, currentSuit, folder, "Interactions");
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Interactions']")));
        Thread.sleep(10000);
   
       
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button/span[contains(text(), 'Interaction')]")));
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Type']/parent::label/following-sibling::input"))).sendKeys(Keys.ENTER);
        js.executeScript("arguments[0].click();", wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Custom Work']"))));
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Sub-Type']/parent::label/following-sibling::input"))).sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        js.executeScript("arguments[0].click();", wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Delivered']"))));
        Thread.sleep(2000);
        WebElement dropdown=driver.findElement(By.xpath("(//select[contains(@class,'UiInputselect')])[1]"));
        Select select=new Select(dropdown);
        Thread.sleep(2000);
        java.util.List<WebElement> optionsList=select.getOptions();
        String options="";
        for(WebElement option : optionsList)
        {
            options=option.getText();
        }
        if(options.contains("CAD"))
        {
            Common_Functions.ResultPass(driver, currentSuit, folder, "CAD currency picklist value added", "CAD currency picklist value should be Displayed", "CAD currency picklist value is Displayed");
        }
        else
        {
            Common_Functions.ResultFail(driver, currentSuit, folder, "CAD currency picklist value added", "CAD currency picklist value should be Displayed", "CAD currency picklist value not Displayed");
        }
	}
	
	
	public void NewMandateDateFormat(WebDriver driver, ReportGenerator currentSuit, String folder, String sheetName, int sheetVal, String URL, String MandateName) throws Exception {
		
		JavascriptExecutor js =(JavascriptExecutor)driver;
		SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
		
		Random ran = new Random();
		int Ran4digit = ran.nextInt(9999);
	
		SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);
	
		WebDriverWait wait = new WebDriverWait(driver, 50);
	
		driver.get(URL);
		Thread.sleep(10000);
		
		nc.SwitchTabsinLightning(driver, currentSuit, folder, "Interactions");
		Thread.sleep(10000);
		
		js.executeScript("arguments[0].click();", wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Log Interaction') or contains(text(),'Log  Interaction')]/parent::button"))));
		Thread.sleep(5000);
	
		wait.until(ExpectedConditions.visibilityOf(LightningWE.SubjectLogInteractionBox)).sendKeys("Interaction"+Ran4digit);
	
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Type']/parent::label/following-sibling::input")));
		Thread.sleep(2000);
	
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[text()='Meeting']")));
		Thread.sleep(2000);
		
		String MeetingPurpose = Utilities.getParameterFromInputSheet("Interactions", "Meeting Purpose", 1);
		
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@data-value='"+MeetingPurpose+"']")));
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@title='Move selection to Selected Meeting Purpose']")));
		
		enterautoboxforInteration(driver, "Companies", "Interactions",1);
	
		enterautoboxforInteration(driver, "Contacts", "Interactions",1);
	
		enterautoboxforInteration(driver, "Opportunity", "Interactions",1);
	
	//	String MandatefromExcel = Utilities.getParameterFromInputSheet("Interactions", "Mandates", 1);
		

		LocalDate date = LocalDate.now();
		
		if(driver.findElement(By.xpath("//span[contains(text(),'"+MandateName+"')]")).getText().trim().equalsIgnoreCase(MandateName+" "+":"+" "+date)) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Validate tagged mandate date format", "Only date should be displayed along with the Mandate Name", "Only date is displayed along with the Mandate Name");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Validate tagged mandate date format", "Only date should be displayed along with the Mandate Name", "Only date is not displayed along with the Mandate Name");
		}
	}
	
	public void addIntrestAndVerify(WebDriver driver, ReportGenerator currentSuit, String folder) throws Exception {
	    {
	        WebDriverWait wait = new WebDriverWait(driver, 50);
	        JavascriptExecutor js =(JavascriptExecutor)driver;
	        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[text()='Subject']//parent::div//parent::a//parent::th//parent::tr//parent::thead//following-sibling::tbody//tr//td//a[contains(text(),'1x1 Meeting')]")));
	        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Subject']//parent::div//parent::a//parent::th//parent::tr//parent::thead//following-sibling::tbody//tr//td//a[contains(text(),'1x1 Meeting')]"))).click();
	        Thread.sleep(10000);
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Search Interests']"))).sendKeys("Test 0723");
	        Thread.sleep(5000);
	        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[@data-selindx='0']//div[text()='Test 0723']")));
	        Thread.sleep(5000);
	        driver.navigate().refresh();
	        Thread.sleep(15000);
	        String options=driver.findElement(By.xpath("//span[text()='Tickers & Interests Discussed']//parent::div//following-sibling::div//lightning-formatted-text")).getText();
	        if(options.contains("0723"))
	        {
	            Common_Functions.ResultPass(driver, currentSuit, folder, "AR Ticker and Other Interest value added", "AR Ticker and Other Interest value value should be Displayed", "AR Ticker and Other Interest value value is Displayed");
	        }
	        else
	        {
	            Common_Functions.ResultFail(driver, currentSuit, folder, "AR Ticker and Other Interest value value added", "AR Ticker and Other Interest value value should be Displayed", "AR Ticker and Other Interest value value not Displayed");
	        }
	    }
	}
	
	
	public void SelectInteractionType(WebDriver driver,ReportGenerator currentSuit, String folder,String sheetName,int sheetVal, String URL)throws Exception
    {
        JavascriptExecutor js =(JavascriptExecutor)driver;
        SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
        LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
            Random ran = new Random();
            int Ran4digit = ran.nextInt(9999);
       
            //SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);
       
            WebDriverWait wait = new WebDriverWait(driver, 50);
       
            driver.get(URL);
            Thread.sleep(15000);
           
            nc.SwitchTabsinLightning(driver, currentSuit, folder, "Interactions");
            Thread.sleep(10000);
       
           
            js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[contains(text(), 'Log  Interaction')]/parent::button")));
            Thread.sleep(5000);
           
            //js.executeScript("arguments[0].click();", wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Type']/parent::label/following-sibling::input"))));
            //Thread.sleep(2000);
            String Type = Utilities.getParameterFromInputSheet(sheetName, "Type", sheetVal);
            String SubType = Utilities.getParameterFromInputSheet(sheetName, "Sub-Type", sheetVal);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Type']/parent::label/following-sibling::input"))).sendKeys(Keys.ENTER);
           
            js.executeScript("arguments[0].click();", wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='"+Type+"']"))));
            Thread.sleep(2000);
           
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Sub-Type']/parent::label/following-sibling::input"))).sendKeys(Keys.ENTER);
            Thread.sleep(2000);
           
            js.executeScript("arguments[0].click();", wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='"+SubType+"']"))));
            Thread.sleep(2000);
       
            js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[text()='Save']")));
            Thread.sleep(20000);
    }
	
	
	
	
	public void verifyNewsubTypesDefaultValidation(WebDriver driver, ReportGenerator currentSuit, String folder,String sheetName,int sheetValue)throws Exception {
	       
        WebDriverWait wait = new WebDriverWait(driver, 50);
        JavascriptExecutor js =(JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[text()='Subject']//parent::div//parent::a//parent::th//parent::tr//parent::thead//following-sibling::tbody//tr//td//a[contains(text(),'Model')]")));
        Thread.sleep(10000);
        String Duration = Utilities.getParameterFromInputSheet(sheetName, "Duration", sheetValue);
        String SubType = Utilities.getParameterFromInputSheet(sheetName, "OA Activity Sub-Type", sheetValue);
        String actualDuration=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Duration']//parent::div//following-sibling::div//lightning-formatted-number"))).getText();
        String actuaSubType=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='OA Activity Sub-Type']//parent::div//following-sibling::div//lightning-formatted-text"))).getText();
        if(Duration.equalsIgnoreCase(actualDuration))
        {
            Common_Functions.ResultPass(driver, currentSuit, folder, "Duration added", "Duration value should be Displayed", "Duration value is Displayed");
        }
        else
        {
            Common_Functions.ResultFail(driver, currentSuit, folder, "Duration added", "Duration value should be Displayed", "Duration value is not Displayed");
        }
        if(SubType.equalsIgnoreCase(actuaSubType))
        {
            Common_Functions.ResultPass(driver, currentSuit, folder, "SubType added", "SubType should be Displayed", "SubType is Displayed");
        }
        else
        {
            Common_Functions.ResultFail(driver, currentSuit, folder, "SubType added", "SubType should be Displayed", "SubType is not Displayed");
        }
}
	
}
