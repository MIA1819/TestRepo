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

public class Mylist_12 extends Browser_setup{
	
	

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
	public void selectContact(WebDriver driver,List<String>s, int i)
	{
		WebDriverWait wait = new WebDriverWait(driver,30);
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='slds-checkbox--faux'])["+i+"]"))).click();
		 String contactname = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='slds-checkbox--faux'])["+i+"]/ancestor::td/following-sibling::td[@data-label='Full Name']/span/div/div"))).getText();
	     s.add(contactname);
	}
	
		//Click on an existing list
    public void ClickonExistingList(WebDriver driver,String ListType, String ListName,ReportGenerator suit, String folder) throws Exception
		{
			WebDriverWait wait = new WebDriverWait(driver,30);
			List<WebElement> verifylist=driver.findElements(By.xpath("//span[contains(text(),'"+ListType.toUpperCase()+"')]/../following-sibling::ul//a[text()='"+ListName+"']"));
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
		Thread.sleep(5000);
		
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
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='>']/../.."))).click();
		 Thread.sleep(5000);
		 ClickonExistingList(driver, ListType, ListName, suit, folder); 
		 
		for(String s :contactnames)
		{
			List<WebElement> ContactVerify=driver.findElements(By.xpath("//td[@data-label='Full Name']/span/div/div[text()='"+s+"']"));
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
		/*for(int i =1;i<=noofcontacts;i++)
		{
			selectContact(driver,contactnames,i);
			
		}
		
		//Click on Add to Static list button
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Add To Static List')]"))).click();
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='New List']"))).click();
		 ListName = lwe.enterTextBoxWithRandomData(driver, "List Name", Ran4digit);
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Save']"))).click();
		 
		 ClickonExistingList(driver, ListType, ListName, suit, folder); 
		 
		for(String s :contactnames)
		{
			List<WebElement> ContactVerify=driver.findElements(By.xpath("//td[@data-label='Full Name']/span/div/div[text()='"+s+"']"));
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
			}*/
			
		
		
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
		List<WebElement> lstele = driver.findElements(By.xpath("//textarea[@placeholder='Notes']"));
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
		Thread.sleep(5000);

		for (int i = 1; i <= noOfContacts; i++) {

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='slds-checkbox--faux'])[" + i
					+ "]/ancestor::td/following-sibling::td[@data-label='Full Name']/span/div/div"))).click();
			Thread.sleep(4000);

			String contactName = wait
					.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//span[@class='slds-page-header__title']/a")))
					.getText();
			if (contactnames.get(i - 1).equalsIgnoreCase(contactName)) {

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
}
