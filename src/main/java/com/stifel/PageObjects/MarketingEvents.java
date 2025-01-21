package com.stifel.PageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import com.stifel.corefunctions.Browser_setup;
import com.stifel.corefunctions.Common_Functions;
import com.stifel.corefunctions.ReportGenerator;
import com.stifel.corefunctions.Utilities;



public class MarketingEvents extends Browser_setup{
	
	SF_StandardLEX_Common_Navigations nc = new SF_StandardLEX_Common_Navigations();
	SF_StandardLEX_ActionOnWebElements lwe = new SF_StandardLEX_ActionOnWebElements();
	SF_StandardLEX_WebElementLocators LightningWE = new SF_StandardLEX_WebElementLocators();
	Common_Functions CF = new Common_Functions();
	
	public void ClickMarketingEventNewButton(WebDriver driver, ReportGenerator currentSuit, String folder) throws Exception {
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(LightningWE.NewBtn_xpath)).click();
		Thread.sleep(10000);
	}
	
	
	public String CreateMarketingEvent(WebDriver driver, ReportGenerator currentSuit, String folder, String sheetName, int sheetVal) throws Exception{
		
		SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);

		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		Random ran = new Random();
		int Ran6digit = ran.nextInt(999999);

		String MarketingEventRecordType = Utilities.getParameterFromInputSheet(sheetName, "Record type", sheetVal);
		
		boolean accTypePresent = driver.findElements(By.xpath("//span[@class=' label bBody' and text()='Next']")).size()!=0;
		
		if (MarketingEventRecordType != null && !MarketingEventRecordType.isEmpty()) {

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getRadioButton(MarketingEventRecordType)))).click();

			wait.until(ExpectedConditions.visibilityOf(LightningWE.NextBtn_xpath)).click();

			//GenericFunctions.waitForLoad(driver, 80);
		}
		Thread.sleep(7000);
		driver.navigate().refresh();
		Thread.sleep(15000);
		
		String EventName = lwe.enterTextBoxWithRandomData(driver, "Marketing Event Name", Ran6digit);
		lwe.selectPicklist(driver, "Type");
		lwe.selectPicklist(driver, "Status");
		lwe.selectTodaysDate(driver, "Start Date");
		lwe.selectTodaysDate(driver, "End Date");
		lwe.selectPicklist(driver, "Country");
		lwe.enterTextArea(driver, "Description");
		lwe.enterTextArea(driver, "Itinerary Name");
		lwe.selectLookupValue(driver, "Region");
		lwe.enterTextArea(driver, "City");
		lwe.enterTextArea(driver, "Dial-In");
		lwe.selectPicklist(driver, "Top Level Entity");
		lwe.enterTextBox(driver, "Location");
		
		wait.until(ExpectedConditions.visibilityOf(LightningWE.SaveBtn_xpath)).click();
		Thread.sleep(30000);
		
		if(driver.findElement(By.xpath("//*[text()='Marketing Event Name']/parent::div/following-sibling::div/span/span")).getText().equalsIgnoreCase(EventName)) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Create Event Validation", "Event should be created", "Event is created");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Create Event Validation", "Event should be created", "Event is not created");
		}
			
		System.out.println("Marketing Event: "+EventName);
		String EventURL = driver.getCurrentUrl();
		return EventName;
		
	}
	
	
	public void CreateEventMeeting(WebDriver driver, ReportGenerator currentSuit, String folder, String sheetName, int sheetVal) throws Exception {
		
		SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);

		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		Random ran = new Random();
		int Ran4digit = ran.nextInt(9999);
		
		wait.until(ExpectedConditions.visibilityOf(LightningWE.CreateEventMeetingButton)).click();
		Thread.sleep(7000);
		
		//lwe.enterTextBoxWithRandomData(driver, "Name", Ran4digit);
		lwe.selectTodaysDate2(driver, "Start Date");
		lwe.selectTodaysDate2(driver, "End Date");
		lwe.enterTextBox2(driver, "Meeting Start Time");
		lwe.enterTextBox2(driver, "Meeting End Time");
		lwe.selectPicklist2(driver, "Start Time Timezone");
		lwe.selectPicklist2(driver, "End Time Timezone");
		
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[text()='Save & Close']")));
		Thread.sleep(20000);
		driver.navigate().refresh();
		Thread.sleep(20000);
		
		
		if(driver.findElement(By.xpath("//div[contains(@id, infiniteScrollDiv)]/table/tbody/tr[1]")).isDisplayed()) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Create Event Meeting Validation", "Event Meeting should be created", "Event Meeting is created");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Create Event Meeting Validation", "Event Meeting should be created", "Event Meeting is NOT created");
		}
	}
	
	
	public void AddAttendee(WebDriver driver, ReportGenerator currentSuit, String folder, String sheetName, int sheetVal) throws Exception {
		
		SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);

		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		wait.until(ExpectedConditions.visibilityOf(LightningWE.MeetingNameLink)).click();
		Thread.sleep(7000);
		
		wait.until(ExpectedConditions.visibilityOf(LightningWE.AddAttendeebutton)).click();
		Thread.sleep(10000);
		
		driver.navigate().refresh();
		
		lwe.selectLookupValue(driver, "Contact");
		Thread.sleep(3000);
		lwe.selectPicklist(driver, "Participant Type");
		lwe.selectPicklist(driver, "Status");
		
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@title='Save']")));
		Thread.sleep(15000);
		
		driver.navigate().refresh();
		Thread.sleep(10000);
		
		String Status = Utilities.getParameterFromInputSheet(sheetName, "Status", sheetVal);
		String Contact = Utilities.getParameterFromInputSheet(sheetName, "Contact", sheetVal);
		
		String ContactfromApp;
		
		if(Status.equalsIgnoreCase("Approved")) {
			ContactfromApp = driver.findElement(By.xpath("//*[text()='Approved Attendees']/parent::div/following-sibling::div[2]/table/tbody/tr[1]/td[@data-label=\"First Name\"]//a[2]")).getText()+" "+driver.findElement(By.xpath("//*[text()='Approved Attendees']/parent::div/following-sibling::div[2]/table/tbody/tr[1]/td[@data-label=\"Last Name\"]//a[2]")).getText();
			
			if(ContactfromApp.equalsIgnoreCase(Contact)) {
				Common_Functions.ResultPass(driver, currentSuit, folder, "Add Attendee Validation", "Attendee should be added", "Attendee is added");
			}
			else {
				Common_Functions.ResultFail(driver, currentSuit, folder, "Add Attendee Validation", "Attendee should be added", "Attendee is not added");
			}
		}
		else if(Status.equalsIgnoreCase("Requested")) {
			
			ContactfromApp = driver.findElement(By.xpath("//*[text()='Requested Attendees']/parent::div/following-sibling::div[2]/table/tbody/tr[1]/td[@data-label=\"First Name\"]//a[2]")).getText()+" "+driver.findElement(By.xpath("//*[text()='Requested Attendees']/parent::div/following-sibling::div[2]/table/tbody/tr[1]/td[@data-label=\"Last Name\"]//a[2]")).getText();
			
			if(ContactfromApp.equalsIgnoreCase(Contact)) {
				Common_Functions.ResultPass(driver, currentSuit, folder, "Add Attendee Validation", "Attendee should be added", "Attendee is added");
			}
			else {
				Common_Functions.ResultFail(driver, currentSuit, folder, "Add Attendee Validation", "Attendee should be added", "Attendee is not added");
			}
		}	
		
		driver.navigate().refresh();
		Thread.sleep(10000);
		
		js.executeScript("arguments[0].click();",wait.until(ExpectedConditions.visibilityOf(LightningWE.MarketingEventLink)));
		Thread.sleep(10000);
		
		driver.navigate().refresh();
		Thread.sleep(10000);
	}
	
	
	public void AddDuplicateAttendee_Error(WebDriver driver, ReportGenerator currentSuit, String folder, String sheetName, int sheetVal) throws Exception {
		
		SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);

		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		wait.until(ExpectedConditions.visibilityOf(LightningWE.MeetingNameLink)).click();
		Thread.sleep(7000);
		
		wait.until(ExpectedConditions.visibilityOf(LightningWE.AddAttendeebutton)).click();
		Thread.sleep(10000);
		
		driver.navigate().refresh();
		
		lwe.selectLookupValue(driver, "Contact");
		Thread.sleep(3000);
		lwe.selectPicklist(driver, "Participant Type");
		lwe.selectPicklist(driver, "Status");
		
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@title='Save']")));
		Thread.sleep(5000);
		
		if(LightningWE.AddAttendee_ErrorMsg.isDisplayed()) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Add duplicate Attendee Validation", "Error message should display", "Error message is displayed");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Add duplicate Attendee Validation", "Error message should display", "Error message is not displayed");
		}
	}
	
	
	public void DeleteAnAttendee(WebDriver driver, ReportGenerator currentSuit, String folder, String sheetName, int sheetVal) throws Exception {
		
		SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);

		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		wait.until(ExpectedConditions.visibilityOf(LightningWE.MeetingNameLink)).click();
		Thread.sleep(7000);
		
		driver.navigate().refresh();
		Thread.sleep(10000);
		
		String AttendeeType = Utilities.getParameterFromInputSheet(sheetName, "Attendee Type for Delete", sheetVal);
		
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[text()='"+AttendeeType+"']/parent::div/following-sibling::div[2]//table/tbody/tr[1]/td[2]//button[2]")));
		Thread.sleep(3000);
		
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[text()='Delete']")));
		Thread.sleep(10000);
		
		List<WebElement> Validation = driver.findElements(By.xpath("//*[text()='"+AttendeeType+"']/parent::div/following-sibling::div[2]//table/tbody/tr[1]"));
		
		if(Validation.size()==0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Delete Attendee Validation", "Attendee should be deleted", "Attendee is deleted");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Delete Attendee Validation", "Attendee should be deleted", "Attendee is not deleted");
		}
	}

	
	public void ChangeStatusofApprovedAttendee(WebDriver driver, ReportGenerator currentSuit, String folder, String sheetName, int sheetVal) throws Exception {
		
		SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);

		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		wait.until(ExpectedConditions.visibilityOf(LightningWE.MeetingNameLink)).click();
		Thread.sleep(7000);
		
		driver.navigate().refresh();
		Thread.sleep(10000);
		
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[text()='Approved Attendees']/parent::div/following-sibling::div[2]//table/tbody/tr[1]/td[2]//button[1]")));
		Thread.sleep(5000);
		
		String Status = Utilities.getParameterFromInputSheet(sheetName, "Status", sheetVal);
		
		driver.findElement(By.xpath("//label[text()='Status']/following-sibling::div//input")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//span[@title='"+Status+"']")).click();
		
		
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@title='Save' or text()='Save']")));
		Thread.sleep(15000);
		
		if(driver.findElement(By.xpath("//*[text()='Approved Attendees']/parent::div/following-sibling::div[2]//table/tbody/tr[1]/td[@data-label='Status']/div/div/div")).getText().equalsIgnoreCase("Cancelled")) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Status of the Attendee Validation", "Attendee Status should change", "Attendee Status is changed");
		}
		else if(driver.findElement(By.xpath("//*[text()='Approved Attendees']/parent::div/following-sibling::div[2]//table/tbody/tr[1]/td[@data-label='Status']/div/div/div")).getText().equalsIgnoreCase("No Show")){
			Common_Functions.ResultPass(driver, currentSuit, folder, "Status of the Attendee Validation", "Attendee Status should change", "Attendee Status is changed");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Status of the Attendee Validation", "Attendee Status should change", "Attendee Status is not changed");
		}
	}
	
	public void AddAttendeeFromPeopleIcon(WebDriver driver, ReportGenerator currentSuit, String folder, String sheetName, int sheetVal) throws Exception {
		SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);

		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		wait.until(ExpectedConditions.visibilityOf(LightningWE.LittleManIcon)).click();
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.visibilityOf(LightningWE.AddAttendeebutton)).click();
		//Thread.sleep(10000);
		
		//driver.navigate().refresh();
		
		//lwe.selectLookupValue(driver, "Contact");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", wait.until(ExpectedConditions.visibilityOf(LightningWE.searchContactsLink)));
		//wait.until(ExpectedConditions.visibilityOf(LightningWE.searchContactsLink)).click();
//		Thread.sleep(3000);
//		lwe.selectPicklist(driver, "Participant Type");
//		lwe.selectPicklist(driver, "Status");
		
		String Contact = Utilities.getParameterFromInputSheet(sheetName, "Contact", sheetVal);	
		Actions a=new Actions(driver);
		a.moveToElement(wait.until(ExpectedConditions.visibilityOf(LightningWE.searchContactTextBox)))
		.click().build().perform();
		wait.until(ExpectedConditions.visibilityOf(LightningWE.searchContactTextBox)).sendKeys(Contact);
		
		js.executeScript("arguments[0].click();", wait.until(ExpectedConditions.visibilityOf(LightningWE.attendeeCheckBox)));
		js.executeScript("arguments[0].click();", wait.until(ExpectedConditions.visibilityOf(LightningWE.addContactButton)));
		
		
//		a.moveToElement(wait.until(ExpectedConditions.visibilityOf(LightningWE.attendeeCheckBox))).click().build().perform();
//		a.moveToElement(wait.until(ExpectedConditions.visibilityOf(LightningWE.addContactButton))).click().build().perform();
		
//		JavascriptExecutor js =(JavascriptExecutor)driver;
//		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@title='Save']")));
//		Thread.sleep(15000);
		
//		driver.navigate().refresh();
//		Thread.sleep(10000);
		
//		String Status = Utilities.getParameterFromInputSheet(sheetName, "Status", sheetVal);
//		String Contact = Utilities.getParameterFromInputSheet(sheetName, "Contact", sheetVal);
		
	//	a.moveToElement(wait.until(ExpectedConditions.visibilityOf(LightningWE.searchButton))).click().build().perform();
		
		
		Select s=new Select(wait.until(ExpectedConditions.visibilityOf(LightningWE.attendeeStatusDropDown)));
//		s.selectByValue("None");
		
		js.executeScript("arguments[0].click();", wait.until(ExpectedConditions.visibilityOf(LightningWE.saveContactButton)));
//		a.moveToElement(wait.until(ExpectedConditions.visibilityOf(LightningWE.saveContactButton))).click().build().perform();
		
		List<WebElement> attendeeList= wait.until(ExpectedConditions.visibilityOfAllElements(LightningWE.checkEventAttendeeInTable));
		
		
		if (attendeeList.size()>1) {
			System.out.println("Attendee list size: "+attendeeList.size());
			Common_Functions.ResultFail(driver, currentSuit, folder, "Add Attendee Validation", "Attendee should not be present", "Attendee is present");
		}else {
			System.out.println("Pass");
			Common_Functions.ResultPass(driver, currentSuit, folder, "Add Attendee Validation", "Attendee should not be present", "Attendee is not present");
		}
		
	/*	if(Status.equalsIgnoreCase("Approved")) {
			if(driver.findElement(By.xpath("//a[text()='"+Contact+"']")).isDisplayed()) {
				Common_Functions.ResultPass(driver, currentSuit, folder, "Add Attendee Validation", "Attendee should be added", "Attendee is added");
			}
			else {
				Common_Functions.ResultFail(driver, currentSuit, folder, "Add Attendee Validation", "Attendee should be added", "Attendee is not added");
			}
		}
		else if(Status.equalsIgnoreCase("Requested")) {
			if(driver.findElement(By.xpath("//*[text()='Requested Attendees']/parent::div/following-sibling::div//a[text()='"+Contact+"']")).isDisplayed()) {
				Common_Functions.ResultPass(driver, currentSuit, folder, "Add Attendee Validation", "Attendee should be added", "Attendee is added");
			}
			else {
				Common_Functions.ResultFail(driver, currentSuit, folder, "Add Attendee Validation", "Attendee should be added", "Attendee is not added");
			}
		}	
		
		driver.navigate().refresh();
		Thread.sleep(10000);
		
		wait.until(ExpectedConditions.visibilityOf(LightningWE.MarketingEventLink)).click();
		Thread.sleep(10000);
		
		driver.navigate().refresh();
		Thread.sleep(10000);*/
	}
	
	
	
	public void AddApprovedAttendee(WebDriver driver, ReportGenerator currentSuit, String folder, String sheetName, int sheetVal) throws Exception {
        
        SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);

        LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
        
        WebDriverWait wait = new WebDriverWait(driver, 50);
        
        wait.until(ExpectedConditions.visibilityOf(LightningWE.MeetingNameLink)).click();
        Thread.sleep(7000);
        
        wait.until(ExpectedConditions.visibilityOf(LightningWE.AddAttendeebutton)).click();
        Thread.sleep(10000);
        
        driver.navigate().refresh();
        
        lwe.selectLookupValue(driver, "Contact");
        Thread.sleep(3000);
        lwe.selectPicklist(driver, "Participant Type");
        lwe.selectPicklist(driver, "Status");
        
        JavascriptExecutor js =(JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@title='Save']")));
        Thread.sleep(15000);
        
        driver.navigate().refresh();
        Thread.sleep(10000);
        
        String Status = Utilities.getParameterFromInputSheet(sheetName, "Status", sheetVal);
        String Contact = Utilities.getParameterFromInputSheet(sheetName, "Contact", sheetVal);
        
        if(Status.equalsIgnoreCase("Approved")) {
            if(driver.findElement(By.xpath("//*[text()='Approved Attendees']/parent::div/following-sibling::div[2]/table/tbody/tr[1]/td[@data-label='First Name']//a[2]")).isDisplayed()) {
                Common_Functions.ResultPass(driver, currentSuit, folder, "Add Attendee Validation", "Attendee should be added", "Attendee is added");
            }
            else {
                Common_Functions.ResultFail(driver, currentSuit, folder, "Add Attendee Validation", "Attendee should be added", "Attendee is not added");
            }
        }
        else if(Status.equalsIgnoreCase("Requested")) {
            if(driver.findElement(By.xpath("//*[text()='Requested Attendees']/parent::div/following-sibling::div[2]/table/tbody/tr[1]/td[@data-label='Last Name']//a[2]")).isDisplayed()) {
                Common_Functions.ResultPass(driver, currentSuit, folder, "Add Attendee Validation", "Attendee should be added", "Attendee is added");
            }
            else {
                Common_Functions.ResultFail(driver, currentSuit, folder, "Add Attendee Validation", "Attendee should be added", "Attendee is not added");
            }
        }    
        
        driver.navigate().refresh();
        Thread.sleep(10000);
    }
	
	
	
	public void ConferencePageLayoutValidation(WebDriver driver, ReportGenerator currentSuit, String folder) throws Exception {
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		driver.navigate().refresh();
		
		List<WebElement> fields = driver.findElements(By.xpath("//a[@title='Sync with MeetMax']"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Sync with Meetmax button", "The button should be present", "The button is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Sync with Meetmax button", "The button should be present", "The button is not present");
		}
		
		fields.clear();
		
		fields = driver.findElements(By.xpath("//a[@title='Request Attendance']"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Request Attendance button", "The button should be present", "The button is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Request Attendance button", "The button should be present", "The button is not present");
		}
		
		fields.clear();
		
		fields = driver.findElements(By.xpath("//a[@title='Create An Event Meeting']"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Create An Event Meeting button", "The button should be present", "The button is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Create An Event Meeting button", "The button should be present", "The button is not present");
		}
		
		fields.clear();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@title, 'more actions')]"))).click();
		
		fields = driver.findElements(By.xpath("//a[@title='Edit']"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Edit button", "The button should be present", "The button is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Edit button", "The button should be present", "The button is not present");
		}
		
		fields.clear();
		
		fields = driver.findElements(By.xpath("//a[@title='Delete']"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Delete button", "The button should be present", "The button is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Delete button", "The button should be present", "The button is not present");
		}
		
		fields.clear();
		
		fields = driver.findElements(By.xpath("//span[@title='Type']"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Type field", "The field should be present", "The field is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Type field", "The field should be present", "The field is not present");
		}
		
		fields.clear();
		
		fields = driver.findElements(By.xpath("//span[@title='Location']"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Location field", "The field should be present", "The field is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Location field", "The field should be present", "The field is not present");
		}
		
		fields.clear();
		
		fields = driver.findElements(By.xpath("//span[@title='Status']"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Status field", "The field should be present", "The field is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Status field", "The field should be present", "The field is not present");
		}
		
		fields.clear();
		
		fields = driver.findElements(By.xpath("//div/span[@title='Companies']"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Companies field", "The field should be present", "The field is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Companies field", "The field should be present", "The field is not present");
		}
		
		fields.clear();
		
		fields = driver.findElements(By.xpath("//div[@class='slds-truncate ']/div[text()='Start Date']"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Start Date column", "The column should be present", "The column is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Start Date column", "The column should be present", "The column is not present");
		}
		
		fields.clear();
		
		
		fields = driver.findElements(By.xpath("//div[@class='slds-truncate ']/div[text()='Start Time']"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Start Time column", "The column should be present", "The column is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Start Time column", "The column should be present", "The column is not present");
		}
		
		fields.clear();
		
		fields = driver.findElements(By.xpath("//div[@class='slds-truncate ']/div[text()='End Time']"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of End Time column", "The column should be present", "The column is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of End Time column", "The column should be present", "The column is not present");
		}
		
		fields.clear();
		
		fields = driver.findElements(By.xpath("//div[@class='slds-truncate ']/div[text()='Name']"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Name column", "The column should be present", "The column is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Name column", "The column should be present", "The column is not present");
		}
		
		fields.clear();
		
		fields = driver.findElements(By.xpath("//div[@class='slds-truncate ']/div[text()='Attendees']"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Attendees column", "The column should be present", "The column is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Attendees column", "The column should be present", "The column is not present");
		}
		
		fields.clear();
		
		fields = driver.findElements(By.xpath("//div[@class='testClassMargin']/button"));
		
		if(fields.size()==3) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Edit, Delete and little man icon", "The buttons should be present", "The button is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Edit, Delete and little man icon", "The buttons should be present", "The button is not present");
		}
		
		fields.clear();
		
		fields = driver.findElements(By.xpath("//a[@title='Event Details']"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Event Details tab", "The tab should be present", "The tab is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Event Details tab", "The tab should be present", "The tab is not present");
		}
		
		fields.clear();
		
		
		fields = driver.findElements(By.xpath("//a[@title='Attendees']"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Attendees tab", "The tab should be present", "The tab is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Attendees tab", "The tab should be present", "The tab is not present");
		}
		
		fields.clear();
		
		fields = driver.findElements(By.xpath("//a[@title='Notes']"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Notes tab", "The tab should be present", "The tab is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Notes tab", "The tab should be present", "The tab is not present");
		}
		
		fields.clear();
		
		fields = driver.findElements(By.xpath("//a[contains(@class, 'baseCard')]/span[text()='Companies']"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Companies link", "The link should be present", "The link is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Companies link", "The link should be present", "The link is not present");
		}
		
		fields.clear();
		
		fields = driver.findElements(By.xpath("//a[contains(@class, 'baseCard')]/span[text()='Sectors']"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Sectors link", "The link should be present", "The link is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Sectors link", "The link should be present", "The link is not present");
		}
		
		fields.clear();
		
		fields = driver.findElements(By.xpath("//a[contains(@class, 'baseCard')]/span[text()='Files']"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Files link", "The link should be present", "The link is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Files link", "The link should be present", "The link is not present");
		}
		
		fields.clear();
		
		fields = driver.findElements(By.xpath("//span[text()='Marketing Event Name']/parent::div/following-sibling::div/span/span"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Marketing Event Name field", "The field should be present", "The field is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Marketing Event Name field", "The field should be present", "The field is not present");
		}
		
		fields.clear();
		
		fields = driver.findElements(By.xpath("//span[text()='Type']/parent::div/following-sibling::div/span/span"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Type field", "The field should be present", "The field is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Type field", "The field should be present", "The field is not present");
		}
		
		fields.clear();
		
		fields = driver.findElements(By.xpath("//span[text()='Status']/parent::div/following-sibling::div/span/span"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Status field", "The field should be present", "The field is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Status field", "The field should be present", "The field is not present");
		}
		
		fields.clear();
		
		fields = driver.findElements(By.xpath("//span[text()='Entity']/parent::div/following-sibling::div/span/span"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Employee Entity field", "The field should be present", "The field is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Employee Entity field", "The field should be present", "The field is not present");
		}
		
		fields.clear();
		
		fields = driver.findElements(By.xpath("//span[text()='Start Date']/parent::div/following-sibling::div/span/span"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Start Date field", "The field should be present", "The field is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Start Date field", "The field should be present", "The field is not present");
		}
		
		fields.clear();
		
		fields = driver.findElements(By.xpath("//span[text()='End Date']/parent::div/following-sibling::div/span/span"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of End Date field", "The field should be present", "The field is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of End Date field", "The field should be present", "The field is not present");
		}
		
		fields.clear();
		
		fields = driver.findElements(By.xpath("//span[text()='Time Zone']/parent::div/following-sibling::div/span/span"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Time Zone field", "The field should be present", "The field is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Time Zone field", "The field should be present", "The field is not present");
		}
		
		fields.clear();
		
		fields = driver.findElements(By.xpath("//span[text()='Location']/parent::div/following-sibling::div/span/span"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Location field", "The field should be present", "The field is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Location field", "The field should be present", "The field is not present");
		}
		
		fields.clear();
		
		fields = driver.findElements(By.xpath("//span[text()='City']/parent::div/following-sibling::div/span/span"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of City field", "The field should be present", "The field is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of City field", "The field should be present", "The field is not present");
		}
		
		fields.clear();
		
		fields = driver.findElements(By.xpath("//span[text()='Suggested Rates']/parent::div/following-sibling::div/span/span"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Suggested Rates field", "The field should be present", "The field is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Suggested Rates field", "The field should be present", "The field is not present");
		}
		
		fields.clear();
		
		fields = driver.findElements(By.xpath("//span[text()='Top Level Entity']/parent::div/following-sibling::div/span/span"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Top Level Entity field", "The field should be present", "The field is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Top Level Entity field", "The field should be present", "The field is not present");
		}
		
		fields.clear();
		
		
		fields = driver.findElements(By.xpath("//span[text()='Meetmax ID']/parent::div/following-sibling::div/span/span"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Meetmax ID field", "The field should be present", "The field is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Meetmax ID field", "The field should be present", "The field is not present");
		}
		
		fields.clear();
		
		fields = driver.findElements(By.xpath("//span[text()='Synced Corporate Event Meetings']/parent::div/following-sibling::div/span/span"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Synced Corporate Event Meetings field", "The field should be present", "The field is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Synced Corporate Event Meetings field", "The field should be present", "The field is not present");
		}
		
		fields.clear();
		
		fields = driver.findElements(By.xpath("//span[text()='MeetMax - Last Synced']/parent::div/following-sibling::div/span/span"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of MeetMax - Last Synced field", "The field should be present", "The field is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of MeetMax - Last Synced field", "The field should be present", "The field is not present");
		}
		
		fields.clear();
		
		fields = driver.findElements(By.xpath("//span[text()='Created By']/parent::div/following-sibling::div/span/span"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of MeetMax - Last Synced field", "The field should be present", "The field is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of MeetMax - Last Synced field", "The field should be present", "The field is not present");
		}
		
		fields.clear();
		
		fields = driver.findElements(By.xpath("//span[text()='Last Modified By']/parent::div/following-sibling::div/span/span"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of MeetMax - Last Synced field", "The field should be present", "The field is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of MeetMax - Last Synced field", "The field should be present", "The field is not present");
		}
		
		fields.clear();
		
		fields = driver.findElements(By.xpath("//span[text()='Last Modified By']/parent::div/following-sibling::div/span/span"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Last Modified By field", "The field should be present", "The field is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Last Modified By field", "The field should be present", "The field is not present");
		}
		
		fields.clear();
		
		fields = driver.findElements(By.xpath("//span[text()='Event Coordinator']/parent::div/following-sibling::div/span"));
		
		if(fields.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Event Coordinator field", "The field should be present", "The field is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Event Coordinator field", "The field should be present", "The field is not present");
		}
		
		fields.clear();
		
	}
	
	
	
	public void AddAttendeeFromLittleManIcon(WebDriver driver, ReportGenerator currentSuit, String folder, String sheetName, int sheetVal) throws Exception {
		SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);

		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		wait.until(ExpectedConditions.visibilityOf(LightningWE.LittleManIcon)).click();
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.visibilityOf(LightningWE.AddAttendeebutton)).click();
		//Thread.sleep(10000);
		
		//driver.navigate().refresh();
		
		//lwe.selectLookupValue(driver, "Contact");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", wait.until(ExpectedConditions.visibilityOf(LightningWE.searchContactsLink)));
		//wait.until(ExpectedConditions.visibilityOf(LightningWE.searchContactsLink)).click();
//		Thread.sleep(3000);
//		lwe.selectPicklist(driver, "Participant Type");
//		lwe.selectPicklist(driver, "Status");
		
		String Contact = Utilities.getParameterFromInputSheet(sheetName, "Contact", sheetVal);	
		Actions a=new Actions(driver);
		a.moveToElement(wait.until(ExpectedConditions.visibilityOf(LightningWE.searchContactTextBox)))
		.click().build().perform();
		wait.until(ExpectedConditions.visibilityOf(LightningWE.searchContactTextBox)).sendKeys(Contact);
		js.executeScript("arguments[0].click();", wait.until(ExpectedConditions.visibilityOf(LightningWE.attendeeCheckBox)));
		js.executeScript("arguments[0].click();", wait.until(ExpectedConditions.visibilityOf(LightningWE.addContactButton)));
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();", wait.until(ExpectedConditions.visibilityOf(LightningWE.saveContactButton)));
		Thread.sleep(20000);
		
	}
	
	
	public void VerifyRateCardDetails(WebDriver driver, ReportGenerator currentSuit, String folder) throws Exception {
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		wait.until(ExpectedConditions.visibilityOf(LightningWE.LittleManIcon)).click();
		Thread.sleep(2000);
		
		String RateCardDetailsTextfromUI = wait.until(ExpectedConditions.visibilityOf(LightningWE.RateCardDetailsText)).getText();
		
		String RateCardDetailsTextfromExcel = Utilities.getParameterFromInputSheet("Companies", "Rate Card Details (SN)", 42);	
		
		if(RateCardDetailsTextfromUI.equalsIgnoreCase(RateCardDetailsTextfromExcel)) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Rate Card Details", "The text should be present", "The text is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Rate Card Details", "The text should be present", "The text is not present");
		}
		
		wait.until(ExpectedConditions.visibilityOf(LightningWE.ViewMoreLinkRateCardDetails)).click();
		Thread.sleep(2000);
		
		String RateCardDetailsTextfromDialogboxUI = wait.until(ExpectedConditions.visibilityOf(LightningWE.ViewMoreDialogBox)).getText();
		
		if(RateCardDetailsTextfromDialogboxUI.equalsIgnoreCase(RateCardDetailsTextfromExcel)) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Rate Card Details in dialog box", "The text should be present", "The text is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Rate Card Details in dialog box", "The text should be present", "The text is not present");
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Cancel']"))).click();
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Cancel & Close']"))).click();
		Thread.sleep(2000);
		
	}
	
	
	public void AddContactfromRequestAttendance(WebDriver driver, ReportGenerator currentSuit, String folder, String sheetName, int sheetVal) throws Exception {
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@title='Request Attendance']/parent::a"))).click();
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		String Contact = Utilities.getParameterFromInputSheet(sheetName, "Contact", sheetVal);	
		Actions a=new Actions(driver);
		a.moveToElement(wait.until(ExpectedConditions.visibilityOf(LightningWE.searchContactTextBox)))
		.click().build().perform();
		wait.until(ExpectedConditions.visibilityOf(LightningWE.searchContactTextBox)).sendKeys(Contact);
		js.executeScript("arguments[0].click();", wait.until(ExpectedConditions.visibilityOf(LightningWE.attendeeCheckBox)));
		js.executeScript("arguments[0].click();", wait.until(ExpectedConditions.visibilityOf(LightningWE.addContactButton)));
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();", wait.until(ExpectedConditions.visibilityOf(LightningWE.saveContactButton)));
		Thread.sleep(20000);
	}
	
	
	
	public void validateLablechagesForEntityToTopLabelEntity(WebDriver driver, ReportGenerator currentSuit, String folder) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        boolean labelUpdate=wait.until(ExpectedConditions.visibilityOf(LightningWE.MeetingNameLink)).isDisplayed();
        if(labelUpdate) {
            Common_Functions.ResultPass(driver, currentSuit, folder, "Label Update Validation", "Label Updated", "Label is added");
        }
        else {
            Common_Functions.ResultFail(driver, currentSuit, folder, "Label Update Validation", "Label Updated", "Label is not added");
        }
    }
	
	
	public void ValidateSuggestedRateFieldEditbutton(WebDriver driver, ReportGenerator currentSuit, String folder) throws Exception {
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		driver.navigate().refresh();
		Thread.sleep(10000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		List<WebElement> Presenceofsuggestedrates = driver.findElements(By.xpath("//span[text()='Suggested Rates']/parent::div/following-sibling::div/button/span[text()='Edit Suggested Rates']"));
		
		if(Presenceofsuggestedrates.size()>0) {
            Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of edit button for Suggested Rates", "The edit button for Suggested Rates should be present", "The edit button for Suggested Rates is present");
        }
        else {
            Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of edit button for Suggested Rates", "The edit button for Suggested Rates should be present", "The edit button for Suggested Rates is not present");
        }
		
	}
	
	
	public void EditSuggestedRate(WebDriver driver, ReportGenerator currentSuit, String folder) throws Exception {
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		driver.navigate().refresh();
		Thread.sleep(10000);
		
		js.executeScript("arguments[0].click();", wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@class,'oneActionsDropDown')]/div[@class='uiMenu']"))));
		Thread.sleep(3000);
		
		wait.until(ExpectedConditions.visibilityOf(LightningWE.Editbtn_xpath)).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Suggested Rates']/parent::label/following-sibling::textarea"))).sendKeys("Test1234");
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@title,'Save')]/span[text()='Save']"))).click();
		Thread.sleep(10000);
		
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		String UpdatedSuggestedRates = wait.until(ExpectedConditions.visibilityOf(LightningWE.SuggestedRatesfield)).getText();
		
		if(UpdatedSuggestedRates.equalsIgnoreCase("Test1234")) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Update Suggested Rate field", "Suggested Rates field updated successfully", "The Suggested Rates field is updated");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Update Suggested Rate field", "Suggested Rates field updated successfully", "The Suggested Rates field is not updated");
		}
	}
	
	
	public void ValidateNegotiatedRateColumninAttendeestab(WebDriver driver, ReportGenerator currentSuit, String folder) throws Exception {
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		List<WebElement> PresenceofNegotiatedRate = driver.findElements(By.xpath("(//strong[text()='Attendance Requests']/parent::div/following-sibling::div[2]//table/thead//div[text()='Negotiated Rate'])[1]"));
		
		if(PresenceofNegotiatedRate.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Negotiated Rate in Attendance Request Section", "Negotiated Rate column should be present", "Negotiated Rate column is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Negotiated Rate in Attendance Request Section", "Negotiated Rate column should be present", "Negotiated Rate column is not present");
		}
		
		PresenceofNegotiatedRate.clear();
		
		PresenceofNegotiatedRate = driver.findElements(By.xpath("(//strong[text()='Approved Attendees']/parent::div/following-sibling::div[2]//table/thead//div[text()='Negotiated Rate'])[1]"));
		
		if(PresenceofNegotiatedRate.size()>0) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Negotiated Rate in Approved Attendees Section", "Negotiated Rate column should be present", "Negotiated Rate column is present");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Negotiated Rate in Approved Attendees Section", "Negotiated Rate column should be present", "Negotiated Rate column is not present");
		}
		
	}
	
	
	public void EditAttendeeswithSalesLiason(WebDriver driver, ReportGenerator currentSuit, String folder, String sheetName, int sheetVal) throws Exception {
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Attendance Requests']/parent::div/following-sibling::div[2]//table/tbody/tr/td[2]//button[1]"))).click();
		Thread.sleep(3000);
		
		lwe.enterTextBox2(driver, "Negotiated Rate");
		lwe.selectPicklistforSubfunc2(driver, "Currency");
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Save']"))).click();
		Thread.sleep(10000);
		
		String ExpectedNR = Utilities.getParameterFromInputSheet("Add Attendee", "Negotiated Rate", 9) +" "+Utilities.getParameterFromInputSheet("Add Attendee", "Currency", 9);
		
		String ActualNR = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Attendance Requests']/parent::div/following-sibling::div[2]/table//td[@data-label='Negotiated Rate']/div/div/div"))).getText();
		
		if(ExpectedNR.equalsIgnoreCase(ActualNR)) {
			Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Negotiated Rate in Attendance Request table", "Negotiated Rate should be correct", "Negotiated Rate is correct");
		}
		else {
			Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Negotiated Rate in Attendance Request table", "Negotiated Rate should be correct", "Negotiated Rate is incorrect");
		}
		
	}
	
	
	public void ValidateEntityfield(WebDriver driver, ReportGenerator currentSuit, String folder, String sheetName, int sheetVal) throws Exception {
	
		WebDriverWait wait = new WebDriverWait(driver, 50);
		SF_StandardLEX_ActionOnWebElements.SetSheetDetails(sheetName, sheetVal);
		
		nc.OpenUserDetailPage(driver, currentSuit, folder, "Markus Bayrle");
		
     // Capture the Employee Entity
        String EmpEntityfromUserDetailsPage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@class='detailList']//tr/td[text()='Employee Entity']/following-sibling::td[1]"))).getText();
        
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@title='Login']"))).click();
        Thread.sleep(10000);
		driver.navigate().refresh();
		Thread.sleep(10000);
		
	// Click On Marketing Event Tab
		nc.clickonObjectTab(driver, suit, folder, "Marketing Events");
		
	// Create a Marketing Event of type Event
		ClickMarketingEventNewButton(driver, currentSuit, folder);
		CreateMarketingEvent(driver, currentSuit, folder, "Marketing Events", 4);
		
	// Capture the Entity
	   String EntityfromME = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Entity']/parent::div/following-sibling::div/span"))).getText();
		
	// Compare
	   if(EmpEntityfromUserDetailsPage.equalsIgnoreCase(EntityfromME)) {
		   Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Employee Entity Field", "Entity field in Marketing Event should be correctly updated", "Entity field in Marketing Event is updated correctly");
	   }
	   else {
		   Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Employee Entity Field", "Entity field in Marketing Event should be correctly updated", "Entity field in Marketing Event is not updated correctly");
	   }
		
	}
	
	
	public void CompareRequestedAttendeesIBRST(WebDriver driver, ReportGenerator currentSuit, String folder, ArrayList<String> tabs) throws Exception {
		
		ArrayList<String> RSTColumns = new ArrayList<String>();
		ArrayList<String> IBColumns = new ArrayList<String>();
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		driver.switchTo().window(tabs.get(0));
		
		wait.until(ExpectedConditions.visibilityOf(LightningWE.MeetingNameLink)).click();
		Thread.sleep(10000);
		
		RSTColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Requested Attendees']/parent::div/following-sibling::div[2]/table//td[@data-label='Contact']/div/a"))).getText());
		RSTColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Requested Attendees']/parent::div/following-sibling::div[2]/table//td[@data-label='Company']/div/a"))).getText());
		RSTColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Requested Attendees']/parent::div/following-sibling::div[2]/table//td[@data-label='US Tier (SN)']/div/div/div"))).getText());
		RSTColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Requested Attendees']/parent::div/following-sibling::div[2]/table//td[@data-label='Euro Tier (SN)']/div/div/div"))).getText());
		RSTColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Requested Attendees']/parent::div/following-sibling::div[2]/table//td[@data-label='Requester']/div/a"))).getText());
	//	RSTColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Requested Attendees']/parent::div/following-sibling::div[2]/table//td[@data-label='Requester Notes']/div/a"))).getText());
		RSTColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Requested Attendees']/parent::div/following-sibling::div[2]/table//td[@data-label='Status']/div/div/div"))).getText());
		
		
		driver.switchTo().window(tabs.get(1));
		
		IBColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Requested Attendees']/parent::div/following-sibling::div[3]/table/tbody/tr[1]/td[@data-label='Contact']//a"))).getText());
		IBColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Requested Attendees']/parent::div/following-sibling::div[3]/table/tbody/tr[1]/td[@data-label='Company']//a"))).getText());
		IBColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Requested Attendees']/parent::div/following-sibling::div[3]/table/tbody/tr[1]/td[@data-label='US Tier']//a"))).getText());
		IBColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Requested Attendees']/parent::div/following-sibling::div[3]/table/tbody/tr[1]/td[@data-label='Euro Tier']//a"))).getText());
		IBColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Requested Attendees']/parent::div/following-sibling::div[3]/table/tbody/tr[1]/td[@data-label='Requester']//a"))).getText());
	//	IBColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Requested Attendees']/parent::div/following-sibling::div[3]/table/tbody/tr[1]/td[@data-label='Requester Notes']//a"))).getText());
		IBColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Requested Attendees']/parent::div/following-sibling::div[3]/table/tbody/tr[1]/td[@data-label='Status']//a"))).getText());
		
		for(int i=0; i<RSTColumns.size(); i++) {
			
			if(RSTColumns.get(i).equalsIgnoreCase(IBColumns.get(i))) {
				Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Requested Attendees - RSTColumns.get("+i+")", "The values should match", "The values are matched");
			}
			else {
				Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Requested Attendees - RSTColumns.get("+i+")", "The values should match", "The values are not matched");
			}
		}	
	}
		
	
	
	
	public void CompareApprovedAttendeesIBRST(WebDriver driver, ReportGenerator currentSuit, String folder, ArrayList<String> tabs) throws Exception {
		
		ArrayList<String> RSTColumns = new ArrayList<String>();
		ArrayList<String> IBColumns = new ArrayList<String>();
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		driver.switchTo().window(tabs.get(0));
		
		RSTColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Approved Attendees']/parent::div/following-sibling::div[2]/table//td[@data-label='Contact']/div/a"))).getText());
		RSTColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Approved Attendees']/parent::div/following-sibling::div[2]/table//td[@data-label='Company']/div/a"))).getText());
		RSTColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Approved Attendees']/parent::div/following-sibling::div[2]/table//td[@data-label='US Tier (SN)']/div/a"))).getText());
		RSTColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Approved Attendees']/parent::div/following-sibling::div[2]/table//td[@data-label='Euro Tier (SN)']/div/a"))).getText());
		RSTColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Approved Attendees']/parent::div/following-sibling::div[2]/table//td[@data-label='Requester']/div/a"))).getText());
		RSTColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Approved Attendees']/parent::div/following-sibling::div[2]/table//td[@data-label='Requester Notes']/div/a"))).getText());
		RSTColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Approved Attendees']/parent::div/following-sibling::div[2]/table//td[@data-label='Status']/div/a"))).getText());
		
		
		driver.switchTo().window(tabs.get(1));
		
		IBColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Approved Attendees']/parent::div/following-sibling::div[3]/table/tbody/tr[1]/td[@data-label='Contact']//a"))).getText());
		IBColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Approved Attendees']/parent::div/following-sibling::div[3]/table/tbody/tr[1]/td[@data-label='Company']//a"))).getText());
		IBColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Approved Attendees']/parent::div/following-sibling::div[3]/table/tbody/tr[1]/td[@data-label='US Tier']//a"))).getText());
		IBColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Approved Attendees']/parent::div/following-sibling::div[3]/table/tbody/tr[1]/td[@data-label='Euro Tier']//a"))).getText());
		IBColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Approved Attendees']/parent::div/following-sibling::div[3]/table/tbody/tr[1]/td[@data-label='Requester']//a"))).getText());
		IBColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Approved Attendees']/parent::div/following-sibling::div[3]/table/tbody/tr[1]/td[@data-label='Requester Notes']//a"))).getText());
		IBColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Approved Attendees']/parent::div/following-sibling::div[3]/table/tbody/tr[1]/td[@data-label='Status']//a"))).getText());
		
		for(int i=0; i<RSTColumns.size(); i++) {
			
			if(RSTColumns.get(i).equalsIgnoreCase(IBColumns.get(i))) {
				Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Approved Attendees - RSTColumns.get("+i+")", "The values should match", "The values are matched");
			}
			else {
				Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Approved Attendees - RSTColumns.get("+i+")", "The values should match", "The values are not matched");
			}
		}
	}
	
	
	
	public void CompanyApprovedAttendeesIBRST(WebDriver driver, ReportGenerator currentSuit, String folder, ArrayList<String> tabs) throws Exception {
		
		ArrayList<String> RSTColumns = new ArrayList<String>();
		ArrayList<String> IBColumns = new ArrayList<String>();
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		driver.switchTo().window(tabs.get(0));
		
		RSTColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Company Attendees']/parent::div/following-sibling::div[2]/table//td[@data-label='Contact']/div/a"))).getText());
		RSTColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Company Attendees']/parent::div/following-sibling::div[2]/table//td[@data-label='Company']/div/a"))).getText());
		RSTColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Company Attendees']/parent::div/following-sibling::div[2]/table//td[@data-label='US Tier (SN)']/div/a"))).getText());
		RSTColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Company Attendees']/parent::div/following-sibling::div[2]/table//td[@data-label='Euro Tier (SN)']/div/a"))).getText());
		RSTColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Company Attendees']/parent::div/following-sibling::div[2]/table//td[@data-label='Requester']/div/a"))).getText());
		RSTColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Company Attendees']/parent::div/following-sibling::div[2]/table//td[@data-label='Requester Notes']/div/a"))).getText());
		RSTColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Company Attendees']/parent::div/following-sibling::div[2]/table//td[@data-label='Status']/div/a"))).getText());
		
		driver.switchTo().window(tabs.get(1));
		
		IBColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Company Attendees']/parent::div/following-sibling::div[3]/table/tbody/tr[1]/td[@data-label='Contact']//a"))).getText());
		IBColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Company Attendees']/parent::div/following-sibling::div[3]/table/tbody/tr[1]/td[@data-label='Company']//a"))).getText());
		IBColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Company Attendees']/parent::div/following-sibling::div[3]/table/tbody/tr[1]/td[@data-label='US Tier']//a"))).getText());
		IBColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Company Attendees']/parent::div/following-sibling::div[3]/table/tbody/tr[1]/td[@data-label='Euro Tier']//a"))).getText());
		IBColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Company Attendees']/parent::div/following-sibling::div[3]/table/tbody/tr[1]/td[@data-label='Requester']//a"))).getText());
		IBColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Company Attendees']/parent::div/following-sibling::div[3]/table/tbody/tr[1]/td[@data-label='Requester Notes']//a"))).getText());
		IBColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Company Attendees']/parent::div/following-sibling::div[3]/table/tbody/tr[1]/td[@data-label='Status']//a"))).getText());
		
		for(int i=0; i<RSTColumns.size(); i++) {
			
			if(RSTColumns.get(i).equalsIgnoreCase(IBColumns.get(i))) {
				Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Company Attendees - RSTColumns.get("+i+")", "The values should match", "The values are matched");
			}
			else {
				Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Company Attendees - RSTColumns.get("+i+")", "The values should match", "The values are not matched");
			}
		}
	}
	
	
	
	public void StifelApprovedAttendeesIBRST(WebDriver driver, ReportGenerator currentSuit, String folder, ArrayList<String> tabs) throws Exception {
		
		ArrayList<String> RSTColumns = new ArrayList<String>();
		ArrayList<String> IBColumns = new ArrayList<String>();
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		driver.switchTo().window(tabs.get(0));
		
		RSTColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Stifel Attendees']/parent::div/following-sibling::div[2]/table//td[@data-label='Contact']/div/a"))).getText());
		RSTColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Stifel Attendees']/parent::div/following-sibling::div[2]/table//td[@data-label='Company']/div/a"))).getText());
		RSTColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Stifel Attendees']/parent::div/following-sibling::div[2]/table//td[@data-label='US Tier (SN)']/div/a"))).getText());
		RSTColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Stifel Attendees']/parent::div/following-sibling::div[2]/table//td[@data-label='Euro Tier (SN)']/div/a"))).getText());
		RSTColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Stifel Attendees']/parent::div/following-sibling::div[2]/table//td[@data-label='Requester']/div/a"))).getText());
		RSTColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Stifel Attendees']/parent::div/following-sibling::div[2]/table//td[@data-label='Requester Notes']/div/a"))).getText());
		RSTColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Stifel Attendees']/parent::div/following-sibling::div[2]/table//td[@data-label='Status']/div/a"))).getText());
		
		driver.switchTo().window(tabs.get(1));
		
		IBColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Stifel Attendees']/parent::div/following-sibling::div[3]/table/tbody/tr[1]/td[@data-label='Contact']//a"))).getText());
		IBColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Stifel Attendees']/parent::div/following-sibling::div[3]/table/tbody/tr[1]/td[@data-label='Company']//a"))).getText());
		IBColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Stifel Attendees']/parent::div/following-sibling::div[3]/table/tbody/tr[1]/td[@data-label='US Tier']//a"))).getText());
		IBColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Stifel Attendees']/parent::div/following-sibling::div[3]/table/tbody/tr[1]/td[@data-label='Euro Tier']//a"))).getText());
		IBColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Stifel Attendees']/parent::div/following-sibling::div[3]/table/tbody/tr[1]/td[@data-label='Requester']//a"))).getText());
		IBColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Stifel Attendees']/parent::div/following-sibling::div[3]/table/tbody/tr[1]/td[@data-label='Requester Notes']//a"))).getText());
		IBColumns.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='Stifel Attendees']/parent::div/following-sibling::div[3]/table/tbody/tr[1]/td[@data-label='Status']//a"))).getText());
		
		for(int i=0; i<RSTColumns.size(); i++) {
			
			if(RSTColumns.get(i).equalsIgnoreCase(IBColumns.get(i))) {
				Common_Functions.ResultPass(driver, currentSuit, folder, "Verification of Stifel Attendees - RSTColumns.get("+i+")", "The values should match", "The values are matched");
			}
			else {
				Common_Functions.ResultFail(driver, currentSuit, folder, "Verification of Stifel Attendees - RSTColumns.get("+i+")", "The values should match", "The values are not matched");
			}
		}
	}
}
