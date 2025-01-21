package com.stifel.PageObjects;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.stifel.LEXCommons.SF_StandardLEX_ActionOnWebElements;
import com.stifel.LEXCommons.SF_StandardLEX_Common_Navigations;
import com.stifel.LEXCommons.SF_StandardLEX_WebElementLocators;
import com.stifel.corefunctions.Common_Functions;
import com.stifel.corefunctions.ReportGenerator;
import com.stifel.corefunctions.Utilities;

public class Coverages {
	
	SF_StandardLEX_Common_Navigations nc = new SF_StandardLEX_Common_Navigations();
	SF_StandardLEX_ActionOnWebElements lwe = new SF_StandardLEX_ActionOnWebElements();
	
	
	public void CreateCoverageforRST(WebDriver driver, ReportGenerator suit, String folder, String sheetName, int sheetVal) throws Exception
	{           Random ran = new Random();
	            int Ran4digit = ran.nextInt(9999);
	            SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);
	            
	            SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
	            SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
	            LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);			
				WebDriverWait wait = new WebDriverWait(driver, 50);
				//company name
				String CompanyName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Company']/following::slot[@name='primaryField']//lightning-formatted-text"))).getText();
				Utilities.SetParameterFromInputSheet(sheetName, "Company", sheetVal, CompanyName);
				
				//WebElement clickNew=driver.findElement(By.xpath("//span[text()='Short Names']/ancestor::lst-list-view-manager-header//ul/li/a/div[text()='New']"));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Short Names']/ancestor::lst-list-view-manager-header//ul//button[text()='New']"))).click();
				Thread.sleep(4000);	
				
				//driver.navigate().refresh();
				Thread.sleep(10000);	
				
				//lwe.selectLookupValueforSubfunc(driver, CompanyName);
				lwe.enterTextBox(driver, "Description");
		        lwe.enterTextBoxWithRandomData(driver, "Short Name", Ran4digit);
				

				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Save']"))).click();
				Thread.sleep(7000);
				
				//cn.SwitchTabsinLightning(driver, suit, folder, "Coverage");
				
				
		       boolean ShortNamecreated = driver.findElements(By.xpath("//tbody/tr/th//span[starts-with(text(),'SN-')]")).size()>0;
				
				if(ShortNamecreated)
				{
					Common_Functions.ResultPass(driver, suit, folder, "Create Short Name", "Short Name is created", "Short Name is created");
				
				}
				
				else {
					Common_Functions.ResultFail(driver, suit, folder, "Create Short Name", "Short Name is created", "Short Name is not created");
				
				}
				
				
				AssignRep(driver, suit, folder, sheetName, sheetVal);
					
															
				
	 }
	
	public void AssignRep(WebDriver driver, ReportGenerator suit, String folder, String sheetName, int sheetVal) throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver, 50);
		SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);
		String Repcodeval = Utilities.getParameterFromInputSheet(sheetName, "Rep Code", sheetVal);
		//Click on SN record
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr/th//span[starts-with(text(),'SN-')]"))).click();
		/*List<WebElement> test= driver.findElements(By.xpath("//tbody/tr/th//span[starts-with(text(),'SN-')]"));
// Instead of using the for each loop, get the size of the list and iterate through it
		for (int i=0; i<test.size(); i++) {
			try {
				test.get(i).click();
			} catch (StaleElementReferenceException e) {
				// If the exception occurs, find the elements again and click on it
				test = test= driver.findElements(By.xpath("//tbody/tr/th//span[starts-with(text(),'SN-')]"));
				test.get(i).click();
			}
		}*/
		Thread.sleep(5000);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Assign Rep']"))).click();
		Thread.sleep(5000);
		
		//lwe.selectPicklist(driver, "Type");
		lwe.selectOtherLookupValue(driver, "Rep Code");
		lwe.selectOtherPicklist(driver, "Role");
		//lwe.enterCustomDateforSubfunc(driver, "Start Date");
		Thread.sleep(3000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Save']"))));
		
		Thread.sleep(10000);
		
       boolean Repcode = driver.findElements(By.xpath("//td[@data-label='Rep Code']/div/a[text()='"+Repcodeval+"']")).size()>0;
		
		if(Repcode)
		{
			Common_Functions.ResultPass(driver, suit, folder, "Add Repcode", "Repcode should be added", "Repcode is added");
			//System.out.println("Account Created succcessfully");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Add Repcode", "Repcode should be added", "Repcode is not added");
			//System.out.println("Account creation failed");
		}
	}
	
	public void CreateCoverageforIB(WebDriver driver, ReportGenerator suit, String folder, String sheetName, int sheetVal) throws Exception
	{
	            SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
	            SF_StandardLEX_Common_Navigations cn = new SF_StandardLEX_Common_Navigations();
	            LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);			
				WebDriverWait wait = new WebDriverWait(driver, 50);
				SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);
				String Company = Utilities.getParameterFromInputSheet(sheetName, "Company", sheetVal);
				//Click on New button
				cn.Newbuttonclick2(driver);
				lwe.selectLookupValueforSubfunc(driver , "Company");
				lwe.selectPicklistforSubfunc2(driver, "Status");
				lwe.selectLookupValueforSubfunc(driver , "Coverage Team Member");
				lwe.enterDateforSubfunc(driver, "Coverage Start Date");
				lwe.selectPicklistforSubfunc2(driver, "Type");
				lwe.selectPicklistforSubfunc2(driver, "Coverage Role");
				lwe.selectPicklistforSubfunc2(driver, "Broker-Dealer");
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Save']"))).click();
				Thread.sleep(10000);
				
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("arguments[0].click()", wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a/div//span[text()='View All']"))));
				
				Thread.sleep(10000);
				
				boolean Companyadded = driver.findElements(By.xpath("//a[text()='"+Company+"']")).size()>0;
				
				if(Companyadded)
				{
					Common_Functions.ResultPass(driver, suit, folder, "Create Coverage for IB", "Coverage should be created", "Coverage is created");
					//System.out.println("Account Created succcessfully");
				}
				else {
					Common_Functions.ResultFail(driver, suit, folder, "Create Coverage for IB", "Coverage should be created", "Coverage is not created");
					//System.out.println("Account creation failed");
				}
					
	 }

	
	
	public void verifyCoverageOnCompany(WebDriver driver, ReportGenerator suit, String folder,String CoverageType, String Contact) throws Exception
	{	
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		if(CoverageType.equalsIgnoreCase("IB")){
			
			
			String ContactName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Investment Banking Company Coverage')]/ancestor::article/div[2]//td/a"))).getText();
			if(ContactName.equalsIgnoreCase(Contact))
			{
				Common_Functions.ResultPass(driver, suit, folder, "Verify Coverage added to Account", "Coverage should be added to  Account", "Coverage is added");
			}
			else {
				Common_Functions.ResultFail(driver, suit, folder, "Verify Coverage added to Account", "Coverage should be added to  Account","Coverage is not added");
			}
			
				}
		if(CoverageType.equalsIgnoreCase("rst"))
		{

	     String ContactName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Research Sales and Trading Company Coverage')]/ancestor::article/div[2]//td/a"))).getText();
	    if(ContactName.equalsIgnoreCase(Contact))
	       {
		      Common_Functions.ResultPass(driver, suit, folder, "Verify Coverage added to Account", "Coverage should be added to  Account", "Coverage is added");
	       }
	     else {
		       Common_Functions.ResultFail(driver, suit, folder, "Verify Coverage added to Account", "Coverage should be added to  Account","Coverage is not added");
	       }
	
		}
	}
	
	
	public void PutEndDateToCoverage(WebDriver driver, ReportGenerator suit, String folder) throws Exception
	{
	        WebDriverWait wait = new WebDriverWait(driver, 50);
	        JavascriptExecutor jse = (JavascriptExecutor)driver;
	        jse.executeScript("arguments[0].click()", wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody/tr/th//span[starts-with(text(),'SN-')]"))));
	        Thread.sleep(10000);
	        jse.executeScript("arguments[0].click()", wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Short Name Coverage']//parent::div/following::div[@class='infiniteScrollDiv']//table/tbody/tr[1]/td[2]//button[1]"))));
	        Thread.sleep(10000);
	        lwe.enterCustomDateforSubfunc(driver, "End Date");
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Save']"))).click();
	        Thread.sleep(10000);
	       
	}
	
	
	public void VerifyCoverageEnds(WebDriver driver, ReportGenerator suit, String folder) throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Coverage']"))).click();
        Thread.sleep(5000);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click()", wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//table/tbody/tr[1]/td[2]//button)[1]"))));
        boolean expectedStatus=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/span[text()='Date Status']//parent::div//following-sibling::div//records-formula-output"))).isDisplayed();
        Thread.sleep(10000);
        if(expectedStatus)
        {
              Common_Functions.ResultPass(driver, suit, folder, "Verify Coverage End", "Coverage should be Former", "Coverage is Former");
           }
         else {
               Common_Functions.ResultFail(driver, suit, folder, "Verify Coverage End", "Coverage should be Former","Coverage is not Former");
           }
    }
	
	
	public void VerifyEditSNCoverage(WebDriver driver, ReportGenerator suit, String folder) throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        boolean reviewError=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='container']//li"))).isDisplayed();
        if(reviewError)
        {
              Common_Functions.ResultPass(driver, suit, folder, "Verify errors message on this page", "Error message should be Displayed", "Error Message Displayed");
           }
         else {
               Common_Functions.ResultFail(driver, suit, folder, "Verify errors message on this page", "Error message should be Displayed","Error Message not Displayed");
           }
        String errorMsg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='container']//li"))).getText();
        System.out.println(errorMsg);
        if(errorMsg.equals("Oops...you don't have the necessary privileges to edit this record. See your administrator for help."))
        {
              Common_Functions.ResultPass(driver, suit, folder, "Verify errors message on this page", "Error message should be Displayed correctly", "Error Messsage is correct");
           }
         else {
               Common_Functions.ResultFail(driver, suit, folder, "Verify errors message on this page", "Error message should be Displayed correctly","Error Messsage is not Correct");
           }
       
    }
	
	
	public void EditShortNames(WebDriver driver, ReportGenerator suit, String folder, String sheetName, int sheetVal) throws Exception {
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Edit']"))).click();
        Thread.sleep(5000);
        
        lwe.enterTextBox(driver, "Description");
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Save']"))).click();
        Thread.sleep(10000);
        
        List<WebElement> li = driver.findElements(By.xpath("//div[@class='container']//li"));
        
        if(li.size()>0) {
        	Common_Functions.ResultPass(driver, suit, folder, "Verify errors message for editing Short Name with different entity", "Error message should be Displayed correctly", "Error Messsage is correct");
        }
        else {
        	Common_Functions.ResultFail(driver, suit, folder, "Verify errors message for editing Short Name with different entity", "Error message should be Displayed correctly", "Error Messsage is NOT correct");
        }
	}
	
	
}

