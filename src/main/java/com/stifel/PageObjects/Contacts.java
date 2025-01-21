package com.stifel.PageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.stifel.LEXCommons.SF_StandardLEX_ActionOnWebElements;
import com.stifel.LEXCommons.SF_StandardLEX_Common_Navigations;
import com.stifel.LEXCommons.SF_StandardLEX_WebElementLocators;
import com.stifel.corefunctions.Common_Functions;
import com.stifel.corefunctions.ReportGenerator;
import com.stifel.corefunctions.Utilities;


public class Contacts {
	
	SF_StandardLEX_Common_Navigations nc = new SF_StandardLEX_Common_Navigations();
	SF_StandardLEX_ActionOnWebElements lwe = new SF_StandardLEX_ActionOnWebElements();
	SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
	String ContactURL;
	public String ContactName;
	
	public void ClickContactTab(WebDriver driver, ReportGenerator currentSuit, String folder) throws Exception {
		driver.navigate().refresh();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
		Thread.sleep(10000);
		nc.SearchandClickObjectTab(driver, currentSuit, folder, "Contacts");
		Thread.sleep(20000);
		
	}
	
	public void ClickContactNewButton(WebDriver driver, ReportGenerator currentSuit, String folder) throws Exception {
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(LightningWE.NewBtn_xpath)).click();
		Thread.sleep(10000);
	}
	
	
	public String CreateExternalContact(WebDriver driver, ReportGenerator currentSuit, String folder, String sheetName, int sheetVal) throws Exception{
		
		// Random digit generator
			Random ran = new Random();
			int Ran4digit = ran.nextInt(9999);
			int Ran6digit = ran.nextInt(999999);

			SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);

			LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
			
			WebDriverWait wait = new WebDriverWait(driver, 50);

			String ConRecordType = Utilities.getParameterFromInputSheet(sheetName, "Record type", sheetVal);
			String email = Utilities.getParameterFromInputSheet(sheetName, "Record type", sheetVal);
			
			boolean accTypePresent = driver.findElements(By.xpath("//span[@class=' label bBody' and text()='Next']")).size()!=0;
			
			if (ConRecordType != null && !ConRecordType.isEmpty()) {

				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getRadioButton2(ConRecordType)))).click();

				wait.until(ExpectedConditions.visibilityOf(LightningWE.NextBtn_xpath_Contact)).click();

				//GenericFunctions.waitForLoad(driver, 80);
			}
			Thread.sleep(5000);
//			driver.navigate().refresh();
//			Thread.sleep(15000);
			
			lwe.selectDropdown(driver, "Salutation", "Mr.");
			String firstName=lwe.enterTextBoxWithRandomData(driver, "First Name", Ran4digit);
			String lastName=lwe.enterTextBoxWithRandomData(driver, "Last Name",Ran4digit);
			lwe.selectSuggestionValue(driver, "Company Name");
			lwe.enterTextBoxWithRandomData(driver, "Email",Ran4digit);
			lwe.enterTextBoxWithRandomData(driver, "Phone",Ran4digit);
			lwe.selectSuggestionValue(driver, "Office Address");
			lwe.selectPicklist(driver, "Top-Level Entity");
			lwe.selectPicklist(driver, "Record Status");
			lwe.selectLookupValue(driver, "Related Internal User");
			lwe.selectPicklist(driver, "Mailing Country");
			lwe.enterTextArea(driver, "Mailing Street");
			lwe.enterTextBox(driver, "Mailing City");
			lwe.selectPicklist(driver, "Mailing State/Province");
			lwe.enterTextBox(driver, "Mailing Zip/Postal Code");
			lwe.selectPicklist(driver, "Billing Country");
			lwe.enterTextArea(driver, "Billing Street");
			lwe.enterTextBox(driver, "Billing City");
			lwe.selectPicklist(driver, "Billing State/Province");
			lwe.enterTextBox(driver, "Billing Zip/Postal Code");
			lwe.selectPicklist(driver, "Branch Office Country");
			lwe.enterTextArea(driver, "Branch Office Street");
			lwe.enterTextBox(driver, "Branch Office City");
			lwe.selectPicklist(driver, "Branch Office State/Province");
			lwe.enterTextBox(driver, "Branch Office Zip/Postal Code");
			lwe.enterTextBox(driver, "Employee Number");
			lwe.selectTodaysDate(driver, "Termination Date");
			lwe.selectPicklist(driver, "Industry Group");
			
			JavascriptExecutor js =(JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@title='Save']")));
			Thread.sleep(20000);
			
			if(driver.findElements(By.xpath("//a[text()='Contact Profile']")).size()!=0) {
				Common_Functions.ResultPass(driver, currentSuit, folder, "Create Contact", "Contact is created", "Contact is created");
			}
			else {
				Common_Functions.ResultFail(driver, currentSuit, folder, "Create Contact", "Contact is created", "Contact is not created");
				
			}
			
			ContactURL = driver.getCurrentUrl();
			ContactName= firstName+" "+lastName;
			return ContactName;
		}



	public String CreateInternalContact(WebDriver driver, ReportGenerator currentSuit, String folder, String sheetName, int sheetVal) throws Exception{
	
	// Random digit generator
		Random ran = new Random();
		int Ran4digit = ran.nextInt(9999);
		int Ran6digit = ran.nextInt(999999);

		SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);

		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
		
		WebDriverWait wait = new WebDriverWait(driver, 50);

		String ConRecordType = Utilities.getParameterFromInputSheet(sheetName, "Record type", sheetVal);
		String email = Utilities.getParameterFromInputSheet(sheetName, "Record type", sheetVal);
		
		boolean accTypePresent = driver.findElements(By.xpath("//span[@class=' label bBody' and text()='Next']")).size()!=0;
		
		if (ConRecordType != null && !ConRecordType.isEmpty()) {

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getRadioButton2(ConRecordType)))).click();

			wait.until(ExpectedConditions.visibilityOf(LightningWE.NextBtn_xpath_Contact)).click();

			//GenericFunctions.waitForLoad(driver, 80);
		}
		Thread.sleep(5000);
//		driver.navigate().refresh();
//		Thread.sleep(15000);
		
		lwe.selectPicklistforSubfunc2(driver, "Salutation");
		String firstName=lwe.enterTextBoxWithRandomData(driver, "First Name", Ran4digit);
		String lastName=lwe.enterTextBoxWithRandomData(driver, "Last Name",Ran4digit);
		lwe.selectLookupValueforSubfunc(driver, "Company Name");
		lwe.enterTextBoxWithRandomData2(driver, "Email",Ran4digit);
		lwe.selectPicklist(driver, "Top-Level Entity");
		lwe.selectPicklist(driver, "Record Status");
		lwe.selectLookupValue(driver, "Related Internal User");
		lwe.selectPicklist(driver, "Mailing Country");
		lwe.enterTextArea(driver, "Mailing Street");
		lwe.enterTextBox(driver, "Mailing City");
		lwe.selectPicklist(driver, "Mailing State/Province");
		lwe.enterTextBox(driver, "Mailing Zip/Postal Code");
		lwe.selectPicklist(driver, "Billing Country");
		lwe.enterTextArea(driver, "Billing Street");
		lwe.enterTextBox(driver, "Billing City");
		lwe.selectPicklist(driver, "Billing State/Province");
		lwe.enterTextBox(driver, "Billing Zip/Postal Code");
		lwe.selectPicklist(driver, "Branch Office Country");
		lwe.enterTextArea(driver, "Branch Office Street");
		lwe.enterTextBox(driver, "Branch Office City");
		lwe.selectPicklist(driver, "Branch Office State/Province");
		lwe.enterTextBox(driver, "Branch Office Zip/Postal Code");
		lwe.enterTextBox(driver, "Employee Number");
		lwe.selectTodaysDate(driver, "Termination Date");
		lwe.selectPicklist(driver, "Industry Group");
		lwe.selectSuggestionValue2(driver, "Office Address");
		
		
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[text()='Save']")));
		Thread.sleep(10000);
		
		if(driver.findElements(By.xpath("//a[text()='Contact Profile']")).size()!=0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Create Contact", "Contact is created", "Contact is created");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Create Contact", "Contact is created", "Contact is not created");
			
		}
		
		ContactURL = driver.getCurrentUrl();
		ContactName= firstName+" "+lastName;
		return ContactName;
	}


public void ClickContactEditbutton(WebDriver driver) throws Exception {
    //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	 WebDriverWait wait = new WebDriverWait(driver,30);
	 JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//lightning-button/button[@name='Edit']")));
    Thread.sleep(10000);
}

public String EditContact(WebDriver driver, ReportGenerator suit, String folder,String sheetName, int sheetVal) throws Exception {
	
		Random ran = new Random();
		int Ran4digit = ran.nextInt(9999);
		int Ran6digit = ran.nextInt(999999);

		SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);

		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		lwe.selectPicklist(driver, "Salutation");
		lwe.enterTextBoxWithRandomData(driver, "First Name", Ran4digit);
		lwe.enterTextBoxWithRandomData(driver, "Last Name",Ran4digit);
		lwe.selectLookupValue(driver, "Company Name");
		lwe.enterTextBoxWithRandomData2(driver, "Email",Ran4digit);
		//lwe.selectPicklist(driver, "Top-Level Entity");
		lwe.selectPicklist(driver, "Record Status");
		lwe.selectLookupValue(driver, "Related Internal User");
		lwe.selectPicklist(driver, "Mailing Country");
		lwe.enterTextArea(driver, "Mailing Street");
		lwe.enterTextBox(driver, "Mailing City");
		lwe.selectPicklist(driver, "Mailing State/Province");
		lwe.enterTextBox(driver, "Mailing Zip/Postal Code");
		lwe.selectPicklist(driver, "Billing Country");
		lwe.enterTextArea(driver, "Billing Street");
		lwe.enterTextBox(driver, "Billing City");
		lwe.selectPicklist(driver, "Billing State/Province");
		lwe.enterTextBox(driver, "Billing Zip/Postal Code");
		lwe.selectPicklist(driver, "Branch Office Country");
		lwe.enterTextArea(driver, "Branch Office Street");
		lwe.enterTextBox(driver, "Branch Office City");
		lwe.selectPicklist(driver, "Branch Office State/Province");
		lwe.enterTextBox(driver, "Branch Office Zip/Postal Code");
		lwe.enterTextBox(driver, "Employee Number");
		lwe.selectTodaysDate(driver, "Termination Date");
		lwe.selectPicklist(driver, "Industry Group");
		
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[text()='Save']")));
		
		Thread.sleep(10000);
		boolean Company = driver.findElements(By.xpath("//h1/div[text()='Contact']")).size()>0;
		
		if(Company)
		{
			Common_Functions.ResultPass(driver, suit, folder, "Update Contact", "Contact is Updated", "Contact is Updated");
			//System.out.println("Account updated succcessfully");
		}
		else {
			Common_Functions.ResultFail(driver, suit, folder, "Update Contact", "Contact is Updated", "Contact is not Updated");
			//System.out.println("Account updated failed");
		}
		
		ContactURL = driver.getCurrentUrl();
		System.out.println(ContactURL);
		return ContactURL;
		
	}

	public void validateResearchProfileForContact(WebDriver driver, ReportGenerator suit, String folder) throws Exception{

    WebDriverWait wait = new WebDriverWait(driver,30);
   
    boolean canadaResearch=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Canada Research Service Level (SN)']/parent::div/following-sibling::div/span"))).isDisplayed();
    if(canadaResearch)
    {
         Common_Functions.ResultPass(driver, suit, folder, "Verification of Canada Research Profile", "Canada Research Service Level (SN) should be displayed", "Canada Research Service Level (SN) Displayed");
    }
    else
    {

 

        Common_Functions.ResultFail(driver, suit, folder, "Verification of Canada Research Profile", "Canada Research Service Level (SN) should be displayed", "Canada Research Service Level (SN) not Displayed");
    }
    boolean ukResearch=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='UK Research Service Level (SN)']/parent::div/following-sibling::div/span"))).isDisplayed();
    if(ukResearch)
    {
         Common_Functions.ResultPass(driver, suit, folder, "Verification of Uk Research Profile", "Uk Research Service Level (SN) should be displayed", "Uk Research Service Level (SN) Displayed");
    }
    else
    {

 

        Common_Functions.ResultFail(driver, suit, folder, "Verification of Uk Research Profile", "Uk Research Service Level (SN) should be displayed", "Uk Research Service Level (SN) not Displayed");
    }
    
	}
	
	
	public void validateDescriptionFieldLabel(WebDriver driver, ReportGenerator suit, String folder) throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        driver.navigate().refresh();
        Thread.sleep(10000);
        boolean UpdatedDescriptionLabel=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Description']"))).isDisplayed();
        if(UpdatedDescriptionLabel)
        {
             Common_Functions.ResultPass(driver, suit, folder, "Verification of Updated Description Field", "Description Field should be displayed", "Description Field Displayed");
        }
        else
        { 
            Common_Functions.ResultFail(driver, suit, folder, "Verification of Updated Description Field", "Description Field should be displayed", "Description Field not Displayed");
        }
    }
	
	
	
	public void addContactToStaticListFromExternalContact(WebDriver driver, ReportGenerator suit, String folder) throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        boolean selectedStaticList=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Selected Static Lists']"))).isDisplayed();
        if(selectedStaticList)
        {
             Common_Functions.ResultPass(driver, suit, folder, "Verification of Selected static list sections", "Selected static list sections should be displayed", "Selected static list sections Displayed");
        }
        else
        {
            Common_Functions.ResultFail(driver, suit, folder, "Verification of Selected static list sections", "Selected static list sections should be displayed", "Selected static list sections not Displayed");
        }
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[text()='test']//parent::th//preceding-sibling::th//label[contains(@class,'checkbox')]//span[contains(@class,'checkbox')]")));
        Thread.sleep(5000);
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@title='Add To Static List']")));
        Thread.sleep(5000);
        boolean ListOnSelectedStaticList=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Selected Static Lists']//parent::div//following-sibling::table//tbody//div[text()='test']"))).isDisplayed();
        if(ListOnSelectedStaticList)
        {
             Common_Functions.ResultPass(driver, suit, folder, "Verification of List Present in Selected static list sections", "List Under Selected static list should be displayed", "List Under Selected static list Displayed");
        }
        else
        {
            Common_Functions.ResultFail(driver, suit, folder, "Verification of List Present in Selected static list sections", "List Under Selected static list should be displayed", "List Under Selected static list not Displayed");
        }
    }
   
    public void removeContactfromStaticListFromExternalContact(WebDriver driver, ReportGenerator suit, String folder) throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        boolean availableStaticList=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Available Static Lists']"))).isDisplayed();
        if(availableStaticList)
        {
             Common_Functions.ResultPass(driver, suit, folder, "Verification of Available static list sections", "Available static list sections should be displayed", "Available static list sections Displayed");
        }
        else
        {
            Common_Functions.ResultFail(driver, suit, folder, "Verification of Available static list sections", "Available static list sections should be displayed", "Available static list sections not Displayed");
        }
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[text()='test']//parent::th//preceding-sibling::th//label[contains(@class,'checkbox')]//span[contains(@class,'checkbox')]")));
        Thread.sleep(5000);
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@title='Remove From Static List']")));
        Thread.sleep(5000);
        boolean ListOnAvailableStaticList=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Available Static Lists']//parent::div//following-sibling::table//tbody//div[text()='test']"))).isDisplayed();
        if(ListOnAvailableStaticList)
        {
             Common_Functions.ResultPass(driver, suit, folder, "Verification of List Present in Available static list sections", "List Under Available static list should be displayed", "List Under Available static list Displayed");
        }
        else
        {
            Common_Functions.ResultFail(driver, suit, folder, "Verification of List Present in Available static list sections", "List Under Available static list should be displayed", "List Under Available static list not Displayed");
        }
    }
   
    public void validateStaticListUnderAvailableStaticList(WebDriver driver,ReportGenerator suit, String folder) throws Exception
    {
        List<WebElement> staticListFromAvaailableList=driver.findElements(By.xpath("//div[@title='Vaishali Jain']//parent::td//preceding-sibling::th[@scope='row']//div"));
        ArrayList<String> al=new ArrayList<String>();
        for(int i=0; i<=staticListFromAvaailableList.size()-1;i++)
        {
            al.add(staticListFromAvaailableList.get(i).getText());
        }
        nc.clickonObjectTab(driver, suit, folder, "My Lists");
        Thread.sleep(10000);
        List<WebElement> staticList=driver.findElements(By.xpath("//*[contains(text(),'STATIC LISTS')]//parent::h3//following-sibling::ul//a"));
        int expectedStaticListPresent=staticList.size();
       
       System.out.println(al.get(1) +"" +staticList.get(1).getText() );
      
        for(int i=0; i<=expectedStaticListPresent-1;i++)
        {
          
            if(al.get(i).equalsIgnoreCase(staticList.get(i).getText()))
            {
                System.out.println(al.get(i)+ " "+staticList.get(i).getText() );
                Common_Functions.ResultPass(driver, suit, folder, "Expected static list present should match with Actual static List", "Expected List displayed successfully", "Expected static List are displayed successfully on static List");
            }
            else             {
                Common_Functions.ResultFail(driver, suit, folder, "Expected static list present should match with Actual static List", "Expected List displayed successfully", "Expected static List are not displayed successfully on static List");
            }
        }
    }
   
    public void validateSharedListUnderAvailableStaticList(WebDriver driver,ReportGenerator suit, String folder) throws Exception
    {
        List<WebElement> sharedListFromAvaailableList=driver.findElements(By.xpath("//div[@title='vazid pasha']//parent::td//preceding-sibling::th[@scope='row']//div"));
        ArrayList<String> al=new ArrayList<String>();
        for(int i=0; i<=sharedListFromAvaailableList.size()-1;i++)
        {
            al.add(sharedListFromAvaailableList.get(i).getText());
        }
        nc.clickonObjectTab(driver, suit, folder, "My Lists");
        Thread.sleep(10000);
        List<WebElement> sharedList=driver.findElements(By.xpath("//*[contains(text(),'SHARED LISTS')]//parent::h3//following-sibling::ul//a"));
        int expectedStaticListPresent=sharedList.size();
       
       System.out.println(al.get(1) +"" +sharedList.get(1).getText() );
      
        for(int i=0; i<=expectedStaticListPresent-1;i++)
        {
          
            if(al.get(i).equalsIgnoreCase(sharedList.get(i).getText()))
            {
                System.out.println(al.get(i)+ " "+sharedList.get(i).getText() );
                Common_Functions.ResultPass(driver, suit, folder, "Expected shared list present should match with Actual static List", "Expected List displayed successfully", "Expected shared List are displayed successfully on static List");
            }
            else             {
                Common_Functions.ResultFail(driver, suit, folder, "Expected shared list present should match with Actual static List", "Expected List displayed successfully", "Expected shared List are not displayed successfully on static List");
            }
        }
    }
    
    
    public void validateLongTextArea(WebDriver driver,ReportGenerator suit, String folder,String sheetName,int sheetVal) throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        JavascriptExecutor js =(JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@name='New']")));
        Thread.sleep(10000);
        String longTextComments = Utilities.getParameterFromInputSheet(sheetName, "Comments", sheetVal);
        driver.findElement(By.xpath("//label[text()='Comments']//following-sibling::div//textarea")).sendKeys(longTextComments);
        Thread.sleep(10000);
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@name='SaveEdit']")));
        Thread.sleep(10000);
        boolean validateAalystAndVotes=driver.findElement(By.xpath("//*[text()='Analyst Vote Name']//parent::span//parent::div//parent::lightning-primitive-header-factory//parent::th//parent::tr//parent::thead//following-sibling::tbody//tr//th//a")).isDisplayed();
        if(validateAalystAndVotes)
        {
             Common_Functions.ResultPass(driver, suit, folder, "Verification of Updated Long text Area Comments Field", "Long Text Area Working Expected", "Long Text Area Working Expected");
        }
        else
        {
            Common_Functions.ResultFail(driver, suit, folder, "Verification of Updated Long text Area Comments Field", "Long Text Area Working Expected", "Long Text Area not Working Expected");
        }
    }
	
    
    
    public void ValidateSEBAinEmployeeEntity(WebDriver driver,ReportGenerator suit, String folder) throws Exception {
    	
    	WebDriverWait wait = new WebDriverWait(driver,30);
    	
    	JavascriptExecutor js =(JavascriptExecutor)driver;
    	js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//label[text()='Employee Entity']//following::div[1]//div")));
    	
    	List<WebElement> ValidatePresence = driver.findElements(By.xpath("//label[text()='Employee Entity']//following::div[1]//lightning-base-combobox-item[@data-value='SEBA']"));
    	
    	if(ValidatePresence.size()>0) {
    		Common_Functions.ResultPass(driver, suit, folder, "Verification if SEBA is present in Employee Entity Picklist", "SEBA should be present in Employee Entity picklist", "SEBA is present in Employee Entity picklist");
    	}
    	else {
    		Common_Functions.ResultFail(driver, suit, folder, "Verification if SEBA is present in Employee Entity Picklist", "SEBA should be present in Employee Entity picklist", "SEBA is NOT present in Employee Entity picklist");
    	}
    }
}
