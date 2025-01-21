package com.stifel.PageObjects;

import java.text.ParseException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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

public class Mylist extends Browser_setup{
	
	

	SF_StandardLEX_Common_Navigations nc = new SF_StandardLEX_Common_Navigations();
	SF_StandardLEX_ActionOnWebElements lwe = new SF_StandardLEX_ActionOnWebElements();
	public static  String ListName;
	
	//Click on MyLists tab
	public void ClickMyListsTab(WebDriver driver,ReportGenerator suit, String folder) throws Exception {
				driver.navigate().refresh();
				Thread.sleep(10000);
				nc.SearchandClickObjectTab(driver,suit, folder, "My Lists");
				Thread.sleep(10000);
			}
	
	//Selecting a contact to be added to Static list
	public void selectContact(WebDriver driver,List<String>s, int i) throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver,30);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='slds-checkbox--faux'])["+i+"]"))));

		 String contactname = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='slds-checkbox--faux'])["+i+"]/ancestor::td/following-sibling::td[@data-label='First Name']/span/div/div"))).getText();
		 
	     s.add(contactname);
	}
	
		//Click on an existing list
    public void ClickonExistingList(WebDriver driver,String ListType, String ListName,ReportGenerator suit, String folder) throws Exception
		{
			WebDriverWait wait = new WebDriverWait(driver,30);
			//driver.findElement(By.xpath("//span[@class='o-toggle__icon']")).click();
			Thread.sleep(5000);
			List<WebElement> verifylist=driver.findElements(By.xpath("//a[@title ='"+ListName+"']"));
			if(verifylist.size()>0)
			{
				verifylist.get(0).click();
				Thread.sleep(10000);
				String listname =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[contains(@class,'customHeaderStyle')]/div)[1]"))).getText();
				if(listname.contains(ListName))
				{
					Common_Functions.ResultPass(driver, suit, folder, "List should be clicked successfully", "List is clicked successfully", "List is  clicked successfully");
				}
				else {
					Common_Functions.ResultFail(driver, suit, folder, "List should be clicked successfully", "List is clicked successfully", "List is not clicked successfully");
				}
						
			}
			
			else {
				Common_Functions.ResultFail(driver, suit, folder, "List should be present", "List is present", "List is not present");
				
			}
			
		}

    
	//Create list from My lists object
	public String CreateList(WebDriver driver,String ListType,int noofcontacts,ReportGenerator suit, String folder,String sheetName, int sheetVal) throws Exception
	{   
		Random ran = new Random();
		int Ran4digit = ran.nextInt(9999);
		WebDriverWait wait = new WebDriverWait(driver,30);
		 List<String> contactnames = new ArrayList<String>(); 
		 SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);

			SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
			LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);


			String AccRecordType = Utilities.getParameterFromInputSheet(sheetName, "Record type", sheetVal);
			//Thread.sleep(10000);
			
		
		boolean verify = false;
		String m ;
		//click on AllContacts link
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-label='All Contacts']"))).click();
		Thread.sleep(20000);
		
		driver.navigate().refresh();
		Thread.sleep(10000);
		
		for(int i =1;i<=noofcontacts;i++)
		{
			selectContact(driver,contactnames,i);
			
		}
		
		//Click on Add to Static list button
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Add To Static List')]"))).click();
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='New List']"))).click();
		 ListName = lwe.enterTextBoxWithRandomData(driver, "List Name", Ran4digit);
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Save']"))).click();
		 Thread.sleep(10000);
	//	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='>']/../.."))).click();
	//	 Thread.sleep(5000);
		 ClickonExistingList(driver, ListType, ListName, suit, folder); 
		 Thread.sleep(10000);
		 
		for(String s :contactnames)
		{
			List<WebElement> ContactVerify=driver.findElements(By.xpath("//td[@data-label='First Name']/span/div/div[text()='"+s+"']"));
			if(ContactVerify.size()>0)
			{
				System.out.println("Contact "+s+" is added in the list");
				verify= true;
				
			}
			else {
				verify= false;
				Common_Functions.ResultFail(driver, suit, folder, "List should be created successfully", "List is created successfully", "List is not created successfully");
				break;
			}
		}
			if(verify)
			{
				Common_Functions.ResultPass(driver, suit, folder, "List should be created successfully", "List is created successfully", "List is created successfully");
			}
			return ListName;
			
		
		
	}
	
	//Share list with other users
	public void Sharethelist(WebDriver driver,String User,String accesstype,ReportGenerator suit, String folder) throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Share')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='search']"))).sendKeys(User);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='"+User+"']/parent::td/preceding-sibling::td//input[@type='checkbox']"))).click();
		if(accesstype.equalsIgnoreCase("read"))
		{
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Share Read Access']"))).sendKeys(User);
			Thread.sleep(4000);
			//List<WebElement> success = driver.findElements(By.xpath("//h4[text()='SUCCESS']"));   
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Shared With')]"))).sendKeys(Keys.ENTER);
				String Access = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='"+User+"']/../following-sibling::td/div"))).getText();
				if(Access.equalsIgnoreCase(accesstype))
				{
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Close']"))).sendKeys(Keys.ENTER);
				Common_Functions.ResultPass(driver, suit, folder, "List should be shared successfully", "List is shared successfully", "List is shared successfully");
				
				}
				else {
					Common_Functions.ResultFail(driver, suit, folder, "List should be shared successfully", "List is shared successfully", "List is not shared successfully");	
				}
			
		}
		
		if(accesstype.equalsIgnoreCase("edit"))
		{
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Share Edit Access']"))).sendKeys(User);
			Thread.sleep(3000);
			//List<WebElement> success = driver.findElements(By.xpath("//h4[text()='SUCCESS']"));
			  
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Shared With')]"))).sendKeys(Keys.ENTER);
				String Access = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='"+User+"']/../following-sibling::td/div"))).getText();
				if(Access.equalsIgnoreCase(accesstype))
				{
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Close']/.."))).click();
				Common_Functions.ResultPass(driver, suit, folder, "List should be shared successfully", "List is shared successfully", "List is shared successfully");
				
				}
				else {
					Common_Functions.ResultFail(driver, suit, folder, "List should be shared successfully", "List is shared successfully", "List is not shared successfully");	
				}
				}

			
		}
		
	
	
	public void deletelist(WebDriver driver,ReportGenerator suit, String folder) throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button/lightning-primitive-icon/*[@data-key='delete']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='DELETE']"))).click();
		Thread.sleep(10000);
		List<WebElement> ele = driver.findElements(By.xpath("//div[text()='All Contacts']"));
		if(ele.size()>0)
		{   System.out.println("Deleted");
			Common_Functions.ResultPass(driver, suit, folder, "List should be deleted successfully", "List is deleted successfully", "List is deleted successfully");
		
		}
		else 			{
			System.out.println("Not Deleted");
			Common_Functions.ResultFail(driver, suit, folder, "List should be deleted successfully", "List is deleted successfully", "List is not deleted successfully");
		}
		
	}
		
	public void createSmartList (WebDriver driver,String ListType,int noofcontacts,ReportGenerator suit, String folder,String sheetName, int sheetVal) throws Exception
	{   
		Random ran = new Random();
		int Ran4digit = ran.nextInt(9999);
		WebDriverWait wait = new WebDriverWait(driver,30);
		 List<String> contactnames = new ArrayList<String>(); 
		 SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);
		 JavascriptExecutor executor = (JavascriptExecutor) driver;

			SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
			LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
		
		
		boolean verify = false;

		//click on AllContacts link
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-label='All Contacts']"))).click();
		Thread.sleep(5000);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add Filter']"))).click();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/ul//li[@data-value='Country']/a[text()='Country']"))).click();
		Thread.sleep(2000);
		WebElement ele = driver.findElement(By.xpath("//label/span[text()='United States of America']/preceding-sibling::span"));
		executor.executeScript("arguments[0].click();", ele);
		Thread.sleep(1500);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Apply']"))).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Save Filters']"))).click();
		Thread.sleep(2000);
		lwe.enterTextBoxWithRandomData(driver, "List Name", Ran4digit);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Save']"))).click();
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(10000);
		for(int i =1;i<=noofcontacts;i++)
		{
			selectContact(driver,contactnames,i);
			
		}
		
		//Click on Add to Static list button
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Add To Static List')]"))).click();
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='New List']"))).click();
		 ListName = lwe.enterTextBoxWithRandomData(driver, "List Name", Ran4digit);
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Save']"))).click();
		 Thread.sleep(10000);
	//	 driver.findElement(By.xpath("//span[@class='o-toggle__icon']")).click();
		 ClickonExistingList(driver, ListType, ListName, suit, folder); 
		 
		for(String s :contactnames)
		{
			List<WebElement> ContactVerify=driver.findElements(By.xpath("//td[@data-label='First Name']/span/div/div[text()='"+s+"']"));
			if(ContactVerify.size()>0)
			{
				System.out.println("Contact "+s+" is added in the list");
				verify= true;
				
			}
			else {
				verify= false;
				Common_Functions.ResultFail(driver, suit, folder, "List should be created successfully", "List is created successfully", "List is not created successfully");
				break;
			}
		}
			if(verify)
			{
				Common_Functions.ResultPass(driver, suit, folder, "List should be created successfully", "List is created successfully", "List is created successfully");
			}
			
		
		
	}
	
	public void logMultipleInteractions(WebDriver driver, int noOfContacts, ReportGenerator suit, String folder,
			String sheetName, int sheetVal) throws Exception {

		List<String> contactnames = new ArrayList<String>();
		WebDriverWait wait = new WebDriverWait(driver, 50);

		Thread.sleep(3000);
		for (int i = 1; i <= noOfContacts; i++) {
			selectContact(driver, contactnames, i);

		}
		System.out.println(contactnames);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Actions')]"))).click();
		Thread.sleep(2000);
		WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'Log Multiple Interactions')]"));
		wait.until(ExpectedConditions.elementToBeClickable(ele)).click();
		Thread.sleep(3000);
		List<WebElement> lstele = driver.findElements(By.xpath("(//span[text()='Internal Notes']/parent::label/following::textarea[1])[2]"));
		wait.until(ExpectedConditions.visibilityOf(lstele.get(lstele.size() - 1))).clear();
		wait.until(ExpectedConditions.visibilityOf(lstele.get(lstele.size() - 1))).sendKeys("Test Notes");
		Thread.sleep(2000);
		List<WebElement> typeEle = driver
				.findElements(By.xpath("//span[text()='Type']/parent::label/following-sibling::input"));
		wait.until(ExpectedConditions.visibilityOf(typeEle.get(typeEle.size() - 1))).click();
		Thread.sleep(2000);
		String type = Utilities.getParameterFromInputSheet(sheetName, "Type", sheetVal);
		WebElement typeValue = driver
				.findElement(By.xpath("(//div[@role='listbox']/ul/div//*[text()='" + type + "'])[2]"));
		wait.until(ExpectedConditions.visibilityOf(typeValue)).click();
		Thread.sleep(2000);
		List<WebElement> sveEle = driver.findElements(By.xpath("//button[text()='Save Multiple']"));
		wait.until(ExpectedConditions.visibilityOf(sveEle.get(sveEle.size() - 1))).click();
		Thread.sleep(15000);

		for (int i = 1; i <= noOfContacts; i++) {

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='slds-checkbox--faux'])[" + i
					+ "]/ancestor::td/following-sibling::td[@data-label='First Name']/span/div/div"))).click();
			Thread.sleep(5000);

			String contactName = wait
					.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//span[@class='slds-page-header__title']/a")))
					.getText();
			if (contactName.contains(contactnames.get(i - 1))) {

				Common_Functions.ResultPass(driver, suit, folder, "Multiple interaction should be created",
						"Expected contact name is  " + contactnames.get(i - 1) + "",
						"Actual contract name is " + contactName + "");

			} else {
				Common_Functions.ResultFail(driver, suit, folder, "Multiple interaction should be created",
						"Expected contact name is  " + contactnames.get(i - 1) + "",
						"Actual contract name is " + contactName + "");

			}

			String typeName = wait
					.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("(//h3[@class='slds-truncate']/a/strong)[1]")))
					.getText();

			if (type.equalsIgnoreCase(typeName)) {

				Common_Functions.ResultPass(driver, suit, folder, "Interaction type name ",
						"Expected interaction type name is  " + type + "", "Actual type name is " + typeName + "");

			} else {
				Common_Functions.ResultFail(driver, suit, folder, "Interaction type name ",
						"Expected interaction type name is  " + type + "", "Actual type name is " + typeName + "");

			}

			driver.findElement(By.xpath("//button[@title='Close']")).click();

		}

	}
	
	public void shareTheSmartList(WebDriver driver,String User,String accesstype,ReportGenerator suit, String folder) throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Share')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='search']"))).sendKeys(User);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='"+User+"']/parent::td/preceding-sibling::td//input[@type='checkbox']"))).click();
		if(accesstype.equalsIgnoreCase("read"))
		{
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Share Read Access']"))).click();
			Thread.sleep(3000);
			List<WebElement> success = driver.findElements(By.xpath("//h4[text()='SUCCESS']"));
			if(success.size()>0)
			{
				Common_Functions.ResultPass(driver, suit, folder, "List should be shared successfully", "List is shared successfully", "List is shared successfully");
			}
			else 			{
				Common_Functions.ResultFail(driver, suit, folder, "List should be shared successfully", "List is shared successfully", "List is not shared successfully");
			}
			
		}
		
		if(accesstype.equalsIgnoreCase("edit"))
		{
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Share Edit Access']"))).sendKeys(User);
			Thread.sleep(3000);
			List<WebElement> success = driver.findElements(By.xpath("//h4[text()='SUCCESS']"));
			if(success.size()>0)
			{
				Common_Functions.ResultPass(driver, suit, folder, "List should be shared successfully", "List is shared successfully", "List is shared successfully");
			}
			else 			{
				Common_Functions.ResultFail(driver, suit, folder, "List should be shared successfully", "List is shared successfully", "List is not shared successfully");
			}
			
		}
		
	}
	
	
	public void ValidateEuroCanadaResearchServiceLevel(WebDriver driver, ReportGenerator suit, String folder) throws Exception {
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add Filter']"))).click();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/ul//li[@data-value='Euro Research Service Level']/a[text()='Euro Research Service Level']"))).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Full High Touch']"))).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Restricted High Touch']"))).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Apply']"))).click();
		Thread.sleep(12000);
		
		List<WebElement> e1= driver.findElements(By.xpath("//span[text()='Full High Touch,Restricted High Touch']"));
		
		if(e1.size()>0) {
			Common_Functions.ResultPass(driver, suit, folder, "Verification of Euro Research Service Level filter", "Filter should be applied successfully", "Filter is applied successfully");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Verification of Euro Research Service Level filter", "Filter should be applied successfully", "Filter is not applied successfully");
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Euro Research Service Level']/following::button[1]"))).click();
		Thread.sleep(12000);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add Filter']"))).click();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/ul//li[@data-value='CA Research Service Level']/a[text()='CA Research Service Level']"))).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Full High Touch']"))).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Restricted High Touch']"))).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Apply']"))).click();
		Thread.sleep(12000);
		
		List<WebElement> e2= driver.findElements(By.xpath("//span[text()='Full High Touch,Restricted High Touch']"));
		
		if(e2.size()>0) {
			Common_Functions.ResultPass(driver, suit, folder, "Verification of CA Research Service Level filter", "Filter should be applied successfully", "Filter is applied successfully");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Verification of CA Research Service Level filter", "Filter should be applied successfully", "Filter is not applied successfully");
		}

	}
	
	
	public void ValidateCanadianTiersFilter(WebDriver driver, ReportGenerator suit, String folder) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver,30);
        //click on All Contacts Link
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-label='All Contacts']"))).click();
           Thread.sleep(10000);
           
           wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add Filter']"))).click();
           Thread.sleep(4000);
           wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='CA Tier']"))).click();
           Thread.sleep(4000);



           List<WebElement> e1= driver.findElements(By.xpath("//span[@class='slds-checkbox']"));
           for(int i=0;i<e1.size();i++)
           {
               if(e1.get(i).isDisplayed())
               {
                   Common_Functions.ResultPass(driver, suit, folder, "Verification of CanadianTiersFilter", "Filter should be Displayed", "Filter is Displayed");
               }
               else
               {
                   Common_Functions.ResultFail(driver, suit, folder, "Verification of CanadianTiersFilter", "Filter should be Displayed", "Filter is not Displayed");
               }
               
           }
           e1.get(0).click();
           Thread.sleep(4000);
           wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Apply']"))).click();
           Thread.sleep(12000);
           boolean filterApplied=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='CA Tier']"))).isDisplayed();
           if(filterApplied)
           {
                Common_Functions.ResultPass(driver, suit, folder, "Verification of CanadianTiersFilter", "Filter should be applied successfully", "Filter is applied successfully");
           }
           else
           {



               Common_Functions.ResultFail(driver, suit, folder, "Verification of CanadianTiersFilter", "Filter should be applied successfully", "Filter is not applied successfully");
           }
           
   }
		
	

	
	public void ValidateFilterByStaticListCount(WebDriver driver,ReportGenerator suit, String folder) throws Exception
    {
        driver.navigate().refresh();
        Thread.sleep(10000);
        List<WebElement> staticList=driver.findElements(By.xpath("//span[contains(text(),'STATIC LISTS')]//parent::*//following-sibling::ul//a"));
        int expectedStaticListPresent=staticList.size();
        List<WebElement> sharedList=driver.findElements(By.xpath("//span[contains(text(),'SHARED LISTS')]//parent::*//following-sibling::ul//a[contains(@title,'Static')]"));
        int expectedSharedListPresent=sharedList.size();
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Filter by Static List']"))).click();
        List<WebElement> staticListByFilter=driver.findElements(By.xpath("//li[text()='STATIC LISTS']//following-sibling::lightning-input"));
        List<WebElement> sharedListByFilter=driver.findElements(By.xpath("//li[text()='SHARED LISTS']//following-sibling::lightning-input"));
        int actualSharedListPresentByFilter=sharedListByFilter.size();
        int actualStaticListPresentByFilter=staticListByFilter.size()-sharedListByFilter.size();
        if(expectedStaticListPresent==actualStaticListPresentByFilter)
        {
            Common_Functions.ResultPass(driver, suit, folder, "Expected Static list present should match with Filter By Actual static List", "Expected List displayed successfully", "Expected Static List are displayed successfully on filter by static List");
        }
        else             {
            Common_Functions.ResultFail(driver, suit, folder, "Expected Static list present should match with Filter By Actual static List", "Expected List displayed successfully", "Expected Static List are not displayed successfully on filter by static List");
        }
        if(expectedSharedListPresent==actualSharedListPresentByFilter)
        {
            Common_Functions.ResultPass(driver, suit, folder, "Expected Share list present should match with Filter By Actual static List", "Expected List displayed successfully", "Expected Shared List are displayed successfully on filter by static List");
        }
        else             {
            Common_Functions.ResultFail(driver, suit, folder, "Expected Share list present should match with Filter By Actual static List", "Expected List displayed successfully", "Expected Shared List are not displayed successfully on filter by static List");
        }
    }
    
    
    
    public void ValidateAllStaticListOnFilterByStaticList(WebDriver driver,ReportGenerator suit, String folder) throws Exception
    {
        //ArrayList<String> firstExpected = new ArrayList<String>();
        //ArrayList<String> secondActual = new ArrayList<String>();
        List<WebElement> staticList=driver.findElements(By.xpath("//span[contains(text(),'STATIC LISTS')]//parent::*//following-sibling::ul//a"));
        List<WebElement> staticListByFilter=driver.findElements(By.xpath("//li[text()='STATIC LISTS']//following-sibling::lightning-input//label//span[2]"));
        List<WebElement> sharedListByFilter=driver.findElements(By.xpath("//li[text()='SHARED LISTS']//following-sibling::lightning-input//label//span[2]"));
        int expectedStaticListPresent=staticList.size();
        int actualSharedListPresentByFilter=sharedListByFilter.size();
        int actualStaticListPresentByFilter=staticListByFilter.size()-sharedListByFilter.size();
       
        for(int i=0; i<=expectedStaticListPresent-1;i++)
        {
           
            if(staticList.get(i).getText().equalsIgnoreCase(staticListByFilter.get(i).getText()))
            {
                System.out.println(staticList.get(i).getText()+ " "+staticListByFilter.get(i).getText() );
                Common_Functions.ResultPass(driver, suit, folder, "Expected static list present should match with Filter By Actual static List", "Expected List displayed successfully", "Expected static List are displayed successfully on filter by static List");
            }
            else             {
                Common_Functions.ResultFail(driver, suit, folder, "Expected static list present should match with Filter By Actual static List", "Expected List displayed successfully", "Expected static List are not displayed successfully on filter by static List");
            }
        }
       
    }
    
    
    public void ValidateAllSharedListOnFilterByStaticList(WebDriver driver,ReportGenerator suit, String folder) throws Exception
    {
        
        List<WebElement> sharedList=driver.findElements(By.xpath("//span[contains(text(),'SHARED LISTS')]//parent::*//following-sibling::ul//a[contains(@title,'Static')]"));
        List<WebElement> sharedListByFilter=driver.findElements(By.xpath("//li[text()='SHARED LISTS']//following-sibling::lightning-input//label//span[2]"));
        int expectedSharedListPresent=sharedList.size();

 

 

 

        for(int i=0; i<=expectedSharedListPresent-1;i++)
        {
            
            if(sharedList.get(i).getText().equalsIgnoreCase(sharedListByFilter.get(i).getText()))
            {
                System.out.println(sharedList.get(i).getText()+ " "+sharedListByFilter.get(i).getText() );
                Common_Functions.ResultPass(driver, suit, folder, "Expected shared list present should match with Filter By Actual static List", "Expected List displayed successfully", "Expected shared List are displayed successfully on filter by static List");
            }
            else             {
                Common_Functions.ResultFail(driver, suit, folder, "Expected shared list present should match with Filter By Actual static List", "Expected List displayed successfully", "Expected shared List are not displayed successfully on filter by static List");
            }
        }
        
    }
    
    
    public void ValidateNumberOfSubscriptionInStaticList(WebDriver driver,ReportGenerator suit, String folder,String listName) throws Exception
    {
        List<WebElement> numberOfSubscriptionPresent=driver.findElements(By.xpath("//div[@class='listViewTableContainer']//table//tbody//tr//td[@data-label='First Name']//div//div[@class='imgSize uiOutputRichText']"));
        int totalSubscription=numberOfSubscriptionPresent.size();
        System.out.println(totalSubscription);
        System.out.println(numberOfSubscriptionPresent.get(1).getText());
        WebDriverWait wait = new WebDriverWait(driver,30);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[contains(text(),'All Contacts')]")));
       // wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'All Contacts')]"))).click();
        Thread.sleep(10000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Filter by Static List']"))).click();
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='STATIC LISTS']//following-sibling::lightning-input//label//span[text()='"+listName+"']"))).click();
       // wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Apply']"))).click();
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[text()='STATIC LISTS']//following-sibling::lightning-input//label//span[text()='"+listName+"']")));
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[text()='Apply']")));
        Thread.sleep(10000);
        //driver.navigate().refresh();
       // Thread.sleep(10000);
        List<WebElement> numberOfSubscriptionShouldPresent=driver.findElements(By.xpath("//div[@class='listViewTableContainer']//table//tbody//tr//td[@data-label='First Name']//div//div[@class='imgSize uiOutputRichText']"));
        int totalSubscriptionpActual=numberOfSubscriptionShouldPresent.size();
        System.out.println(totalSubscriptionpActual);
        System.out.println(numberOfSubscriptionShouldPresent.get(1).getText());
//        if(totalSubscription==totalSubscriptionpActual)
//        {
//            for(int i=0; i<=totalSubscription-1;i++)
//            {
//                if(numberOfSubscriptionPresent.get(i).getText().equalsIgnoreCase(numberOfSubscriptionShouldPresent.get(i).getText()))
//                {
//                    System.out.println(numberOfSubscriptionPresent.get(i).getText()+ " "+numberOfSubscriptionShouldPresent.get(i).getText() );
//                    Common_Functions.ResultPass(driver, suit, folder, "Expected Subscription should match with Actual Subscription", "Expected Subscription displayed successfully", "Actual Subscriptions are displayed successfully on static List");
//                }
//                else             {
//                    Common_Functions.ResultFail(driver, suit, folder, "Expected Subscription should match with Actual Subscription", "Expected Subscription displayed successfully", "Actual Subscriptions are not displayed successfully on static List");
//                }
//            }
//        }
        boolean filterTag=driver.findElement(By.xpath("//div[text()='"+listName+"']")).isDisplayed();
                //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='"+listName+"']"))).isDisplayed();
        if(filterTag)
        {
            Common_Functions.ResultPass(driver, suit, folder, "filter Tag should be displayed", "Filter tag displayed successfully", "Filter tag displayed successfully");
        }
        else             {
            Common_Functions.ResultFail(driver, suit, folder, "filter Tag should be displayed", "Filter tag displayed successfully", "Filter tag not displayed successfully");
        }
    }
   
   
   
    public void VerifyAddFilterTag(WebDriver driver,ReportGenerator suit, String folder) throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add Filter']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='CONTACT']//following-sibling::li//a[text()='US Tier']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='US Tier']//parent::*//following-sibling::span[2]//span//div//label"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Apply']"))).click();
        Thread.sleep(10000);
        boolean filterTag=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='US Tier']"))).isDisplayed();
        if(filterTag)
        {
            Common_Functions.ResultPass(driver, suit, folder, "Add filter Tag should be displayed", "Add Filter tag displayed successfully", "Add"
                    + "Filter tag displayed successfully");
        }
        else             {
            Common_Functions.ResultFail(driver, suit, folder, "Add filter Tag should be displayed", "Add Filter tag displayed successfully", "Add Filter tag not displayed successfully");
        }
      
    }
   
   
   
    public void SaveandVerifyFilter(WebDriver driver,ReportGenerator suit, String folder,String listName) throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Save Filters']"))).click();
        String newListName=lwe.getAlphaNumericString();
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='listName']"))).sendKeys(newListName);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Save']"))).click();
        Thread.sleep(10000);
        String expectedCapsulTag=listName+",US Tier=1";
        //boolean filterTag=driver.findElement(By.xpath("//span[contains(text(),'"+expectedCapsulTag+"')]")).isDisplayed();
                //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'"+expectedCapsulTag+"')]"))).isDisplayed();
        String Actual=driver.findElement(By.xpath("//div[contains(@class,'page-header__title')]//span[3]")).getText().trim();
        if(Actual.contains(expectedCapsulTag))
        {
            Common_Functions.ResultPass(driver, suit, folder, "Add filter Tag should be displayed", "Add Filter tag displayed successfully", "Add Filter tag displayed successfully"
                    + "Filter tag displayed successfully");
        }
        else             {
            Common_Functions.ResultFail(driver, suit, folder, "Add filter Tag should be displayed", "Add Filter tag displayed successfully", "Add Filter tag not displayed successfully");
        }
      
        String newList=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='"+newListName+"']"))).getText().trim();
        if(newList.contains(newListName))
        {
            Common_Functions.ResultPass(driver, suit, folder, "new List name should be displayed", "new List name displayed successfully", "new List name displayed successfully"
                    + "Filter tag displayed successfully");
        }
        else             {
            Common_Functions.ResultFail(driver, suit, folder, "new List name should be displayed", "new List name displayed successfully", "new List name not displayed successfully");
        }
    }
    
    
    public void newFilterCompanyRecordTypeValidation(WebDriver driver,ReportGenerator suit, String folder) throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add Filter']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='CONTACT']//following-sibling::li//a[text()='Company Record Type']"))).click();
        applyAndVerifyFilter(driver,suit,folder,"checkbox","Institutional");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'IndividialFilter')]//span"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(@class,'checkbox')]//span[text()='Institutional']"))).click();
        applyAndVerifyFilter(driver,suit,folder,"checkbox","Corporate");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'IndividialFilter')]//span"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(@class,'checkbox')]//span[text()='Corporate']"))).click();
        applyAndVerifyFilter(driver,suit,folder,"checkbox","Other");
        driver.navigate().refresh();
        Thread.sleep(10000);
       
    }
   
    public void applyAndVerifyFilter(WebDriver driver,ReportGenerator suit, String folder, String filterType, String filter) throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        String ActualFilter=filter;
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(@class,'"+filterType+"')]//span[text()='"+filter+"']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Apply']"))).click();
        Thread.sleep(10000);
        String ExpectedFilterCapsul=driver.findElement(By.xpath("//div[contains(@class,'IndividialFilter')]//span")).getText().trim();
        if(ExpectedFilterCapsul.toLowerCase().contains(ExpectedFilterCapsul.toLowerCase()))
        {
            Common_Functions.ResultPass(driver, suit, folder, "new Filter Applied should be displayed", "new Filter List displayed successfully", "new Filter List  displayed successfully"
                    + "Filter tag displayed successfully");
        }
        else             {
            Common_Functions.ResultFail(driver, suit, folder, "new Filter Applied should be displayed", "new filter List  displayed successfully", "new filter List not displayed successfully");
        }
    }
   
    public void newFilterSponsoredResearchOptOutValidation(WebDriver driver,ReportGenerator suit, String folder) throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add Filter']"))).click();
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='CONTACT']//following-sibling::li//a[text()='Sponsored Research Opt-Out']"))).click();
        applyAndVerifyFilter(driver,suit,folder,"radio__label","True");
        driver.navigate().refresh();
        Thread.sleep(10000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add Filter']"))).click();
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='CONTACT']//following-sibling::li//a[text()='Sponsored Research Opt-Out']"))).click();
        applyAndVerifyFilter(driver,suit,folder,"radio__label","False");
       
       
    }
   
   
    public void verifyRegions(WebDriver driver,ReportGenerator suit, String folder,String RegionType, String Region) throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        //nc.clickonObjectTab(driver, suit, folder, "My Lists");
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'Autocomplete')]//input[@role='combobox']"))).sendKeys(Region);
//        Actions act= new Actions(driver);
//        act.sendKeys(Keys.ENTER).perform();
        Thread.sleep(10000);
        nc.SearchwithObjectType(driver, suit, folder, RegionType, Region);
        Thread.sleep(10000);
        String ExpectedTopLevelRegion=driver.findElement(By.xpath("//span[text()='Top Level Region']//parent::div/following-sibling::div//span//lightning-formatted-text")).getText();
        if(ExpectedTopLevelRegion.equalsIgnoreCase("Europe"))
        {
            Common_Functions.ResultPass(driver, suit, folder, "Expected Top Level Region should be displayed", "Expected Top Level Region displayed successfully", "Expected Top Level Region  displayed successfully"
                    + "Filter tag displayed successfully");
        }
        else             {
            Common_Functions.ResultFail(driver, suit, folder, "Expected Top Level Region should be displayed", "Expected Top Level Region  displayed successfully", "Expected Top Level Region not displayed successfully");
        }
       
        driver.navigate().refresh();
        Thread.sleep(10000);
    }
    
    
    
    public void SelectContactList(WebDriver driver, String listName) throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        driver.navigate().refresh();
        Thread.sleep(10000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Recently Viewed']"))).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='List Views']//parent::li//following-sibling::li//a//span[text()='All']"))).click();
        Thread.sleep(5000);
      //input[contains(@placeholder,'Search this list')]
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@placeholder,'Search this list')]"))).sendKeys(listName,Keys.ENTER);
        Thread.sleep(8000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='"+listName+"']")));
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='"+listName+"']"))).click();
        Thread.sleep(10000);
       
    }
   
    public void shareListWithGroup(WebDriver driver, String groupName) throws InterruptedException
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Sharing']"))).click();
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'entityIcon')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Public Group']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@title='Search Public Group']"))).click();
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@title='Search Public Group']"))).sendKeys(groupName);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@title='TestDummyPublicGroup']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//lightning-icon[contains(@class,'inputLookupIcon')]"))).click();
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Save']"))).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[text()='Save']")));
        Thread.sleep(10000);
    }
   
    public void verifySharedListWithGroup(WebDriver driver,ReportGenerator suit, String folder,String listName) throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        boolean actualList=driver.findElement(By.xpath("//span[contains(text(),'SHARED LISTS')]//parent::*//following-sibling::ul//a[text()='"+listName+"']")).isDisplayed();
        if(actualList)
        {
            Common_Functions.ResultPass(driver, suit, folder, "Expected List should be displayed", "Expected List displayed successfully", "Expected List  displayed successfully"
                    + "Filter tag displayed successfully");
        }
        else             {
            Common_Functions.ResultFail(driver, suit, folder, "Expected List should be displayed", "Expected List  displayed successfully", "Expected List not displayed successfully");
        }
    }
	
}
