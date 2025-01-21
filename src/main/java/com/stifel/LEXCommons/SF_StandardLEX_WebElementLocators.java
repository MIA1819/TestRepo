package com.stifel.LEXCommons;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.stifel.corefunctions.Common_Functions;
import com.stifel.corefunctions.ReportGenerator;

public class SF_StandardLEX_WebElementLocators {
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Add Attendee')]")
	public WebElement AddAttendeebutton; // Ank change

	@FindBy(how = How.XPATH, using = "//button[@title='Attendees Management and Requests']")
	public WebElement LittleManIcon; // Ank change

	@FindBy(how = How.XPATH, using = "//*[text()='Search Contacts']")
	public WebElement searchContactsLink; // Ank change

	@FindBy(how = How.XPATH, using = "(//button/parent::div/parent::div//input[@placeholder='Search...'])[1]")
	public WebElement searchContactTextBox; // Ank change

	@FindBy(how = How.XPATH, using = "//button[text()='Search']")
	public WebElement searchButton; // Ank change

	@FindBy(how = How.XPATH, using ="//*[@id=\"dataTable\"]/tbody/tr/td[1]//label/span[1]")
	public WebElement attendeeCheckBox; // Ank change

	@FindBy(how = How.XPATH, using = "//button[text()='Add Contact(s)']")
	public WebElement addContactButton; // Ank change

	@FindBy(how = How.XPATH, using = "//button[text()='Save']")
	public WebElement saveContactButton; // Ank change

	@FindBy(how = How.XPATH, using = "//td[6]//select")
	public WebElement attendeeStatusDropDown; // Ank change

	@FindBy(how = How.XPATH, using = "//td[@data-label='Attendees']")
	public List<WebElement> checkEventAttendeeInTable; // Ank change

	@FindBy(how = How.XPATH, using = "//span[@class=' label bBody' and text()='Next']")
	public WebElement NextBtn_xpath;
	
	@FindBy(how = How.XPATH, using = "//button[@title='Next']")
	public WebElement NextBtn_xpath_Contact;
	
	@FindBy(how = How.XPATH, using = "//a[@title='Edit']")
	public WebElement Editbtn_xpath;
	
	@FindBy(how = How.XPATH, using = "//button[contains(@title,'Save')]/span[text()='Save']")
	public WebElement SaveButton_xpath;
	
	@FindBy(how = How.XPATH, using = "//button[3]/span")
	public WebElement SaveBtn_xpath;
	

	@FindBy(how = How.XPATH, using = "//*[@class='windowViewMode-normal oneContent active lafPageHost']//a[contains(@title, 'Related')]")
	public WebElement RelatedBtn_xpath;	
	
	@FindBy(how = How.XPATH, using = "//*[@class='windowViewMode-normal oneContent active lafPageHost']//a[contains(@title, 'Details')]")
	public WebElement DetailsBtn_xpath;	
	
	@FindBy(how = How.XPATH, using = "//button[@title='Add']")
	public WebElement AddBtn_xpath;
	
	@FindBy(how = How.XPATH, using = "//a/div[text()='New']")
	public WebElement NewBtn_xpath;
	
	@FindBy(how = How.XPATH, using = "//button[text()='Continue']")
	public WebElement continueButton_xpath;
	
	@FindBy(how = How.XPATH, using = "//span[text()='No']/../span[1]")
	public WebElement radioButton_xpath;
	
	@FindBy(how = How.XPATH, using = "//p[@title='Marketing Event']/following-sibling::p//a")
	public WebElement MarketingEventLink;
	
	@FindBy(how = How.XPATH, using = "//li[text()='Selected contact is already requested for this meeting']")
	public WebElement AddAttendee_ErrorMsg;
	
	@FindBy(how = How.XPATH, using = "//div[text()='You logged a call']")
	public WebElement callLoggedVerify_xpath;
	
	@FindBy(how = How.XPATH, using = "//a[@title='Create An Event Meeting']")
	public WebElement CreateEventMeetingButton;
	
	@FindBy(how = How.XPATH, using = "//td[@data-label='Name']//a")
	public WebElement MeetingNameLink;
	
	//@FindBy(how = How.XPATH, using = "//button[text()='Add Attendee']")
	//public WebElement AddAttendeebutton;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Log Interaction']")
	public WebElement LogInteractionbutton_IB;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(), 'Interaction')]")
	public WebElement LogInteractionbutton_RST;
	
	@FindBy(how = How.XPATH, using = "//input[@placeholder='Subject']")
	public WebElement SubjectLogInteractionBox;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Type']/parent::label/following-sibling::input")
	public WebElement TypeLogInteractionBox;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Sub-Type']/parent::label/following-sibling::input")
	public WebElement SubTypeLogInteractionBox;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Meeting']")
	public WebElement SelectaTypeOptionLogInteractionBox_IB;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Analyst Meeting / Call']")
	public WebElement SelectaTypeOptionLogInteractionBox_RST;
	
	@FindBy(how = How.XPATH, using = "//div[text()='1x1 Meeting']")
	public WebElement SelectaSubTypeOptionLogInteractionBox_RST;

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Search Contacts']")
	public WebElement ContactsLogInteractionBox;
	
	@FindBy(how = How.XPATH, using = "(//ul/li[1]/a[@class='searchVals'])[1]")
	public WebElement SelectContactfromSuggestion;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Interaction Note']")
	public WebElement InteractionNotePageValidation; 
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'rateCard')]/span")
	public WebElement RateCardDetailsText; 
	
	@FindBy(how = How.XPATH, using = "//a[text()='View More']")
	public WebElement ViewMoreLinkRateCardDetails; 
	
	@FindBy(how = How.XPATH, using = "//div[@role='dialog']//div[contains(@class,'slds-modal__content')]")
	public WebElement ViewMoreDialogBox; 
	
	@FindBy(how = How.XPATH, using = "//span[text()='Show more actions']/parent::button")
	public WebElement Showmoreactions;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Suggested Rates']/parent::div/following-sibling::div/button/span[text()='Edit Suggested Rates']")
	public WebElement SuggestedRatesEditButton;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Suggested Rates']/parent::div/following-sibling::div/span/span")
	public WebElement SuggestedRatesfield;
	
	
	
	
	public static String getTextBox(String labelName) {

	//	return "//*[text()='" + labelName + "']/parent::*/parent::*//input";
	    return	"//*[text()='" + labelName + "']/parent::*/parent::*//input[contains(@type,'text') or contains(@type,'tel') or contains(@type,'email') or @type='url']"; // Update the textbox xpath by Ankith 16-APR-2019

		// return "//*[text()='"+labelName+"']/parent::*/parent::*//input";
	}
	
	
	public static String getTextBox3(String labelName) {
		
		return "//input[@name='"+labelName+"']";
	}
	
	
	
	public static String getTextBoxforSubfunc(String labelName) {
		return "//label/span[text()='" + labelName + "']/parent::label/parent::div/input";
	}

	public static String getDateBox(String labelName) {

		return "//span[text()='" + labelName + "']/parent::label/following-sibling::div//input";
				
	}
	
	public static String getDateBoxforSubfunc(String labelName) {

		return "//label[text()='"+labelName+"']/..//input";
				
	}
	
    public static String getTextAreainSection(String labelName,String sectionname) {

        return "//*[text()='"+sectionname+"']/parent::h3/following-sibling::div//*[text()='"+labelName+"']/parent::label/following-sibling::textarea";

    }
    
//	public static String getdataBox(String labelName) {
//
//		return "//span[text()='" + labelName + "']/parent::label/following-sibling::*[contains(@class,'textarea')]";
//			}
//		
	
	public static String getDateBoxActivities(String labelName) {

		//return "//span[text()='" + labelName + "']/parent::legend/following-sibling::div//input";
		return "//legend[text()='"+labelName+"']/following::div//input";
	}

	public static String getPicklist(String labelName) {

		return "//span[text()='" + labelName + "']/parent::*[contains(@class,'label')]/following-sibling::div//a";

	}

	public static String getOtherPicklist(String labelName) {

		return "//Select[@name='"+labelName+"']";

	}
	
	public static String getPicklistforSubfunc(String labelName) {

		return "//label[text()='"+labelName+"']/..//div[@role='combobox']";

	}
	
	
	public static String getPicklistforSubfunc2(String labelName) {

		return "//label[text()='"+labelName+"']/..//input[@role='combobox']";

	}
	

	
	public static String getTextBox2(String labelName) {
		return "//label[text()='" + labelName + "']/following-sibling::div//input";
	}
	
	public static String getPicklist2(String labelName) {

		return "//label[text()='" + labelName + "']/following-sibling::div//a";

	}

	public static String gettextElementOnViewScreen(String labelName) {

		//return "//span[text()='" + labelName + "']/parent::div//following-sibling::div/span[1]";
		 return "//span[text()='" + labelName + "']/parent::div//following-sibling::div/span/span[1]|//span[text()='" + labelName + "']/parent::div//following-sibling::div/span";
	}
	public static String getlinkElementOnViewScreen(String labelName) {

		return "//span[text()='" + labelName + "']/parent::div//following-sibling::div//a";

	}

	public static String getLookupInputBox(String labelName) {

		return "//span[text()='" + labelName + "']/parent::label/following-sibling::div//input";

	}

	public static String getOtherLookupInputBox(String labelName) {

		return "//*[contains(@placeholder,'" + labelName + "')]";

	}
	
	public static String getLookupInputBoxforSubfunc(String labelName) {

		return "//label[text()='"+labelName+"']/../div//input";

	}
	
	
	public static String getUserInputSearchBox() {
		return "//table/tbody/tr[1]/th/span/span[2]/button";
	}
	


	public static String getObjectFromAppLauncher(String objectName) {

		return "//span[contains(@class,' slds-text-link')][text()='" + objectName + "']";

	}

	public static String getAppFromAppLauncher(String app) {

//		return "//a[@class='appTileTitle'][text()='" + app + "']";
		return "//a[contains(@class,'appTileTitle')][text()='"+ app +"']";
	}

	public static String lookupDetail(String excelValue) {

		return "//span[contains(@title,'" + excelValue + " in')]";
		// return "//div[@title='" + excelValue + "']";

	}
	

	public static String lookupValueOnDetailScreen(String excelValue) {

		// return "(//a[text()='" + excelValue + "'])[1]";
		return "//div[contains(@class,'forceSearchResultsRegion')]//table//a[text()='" + excelValue + "']";

	}
	
	public static String lookupValueOnDetailScreen2(String excelValue) {

		// return "(//a[text()='" + excelValue + "'])[1]";
		return "//a[contains(text(),'" + excelValue + "')]";

	}

	public static String lookupValueOnDetailScreen3(String excelValue) {

		return "//div[@role='option']//span//mark[text()='"+excelValue+"']";

	}

	public static String getLookupvalueDeleteicon(String labelName) {
        
		return "//span[text()='" + labelName + "']/parent::label/following-sibling::div//a[@class='deleteAction']/span[1]";

	}
	
	public static String getLookupvalueDeleteiconforSubfunc(String labelName) {
        
		return "//label[text()='"+labelName+"']/../div//button[@title='Clear Selection']";

	}
	public static String getLookupSelection(String searchValue) {

		return "//div[@title='" + searchValue + "']";

	}

	public static String getMultiPicklist(String labelName) {

		return "//*[text()='" + labelName + "']/parent::div//span[text()='Available']/parent::div//li";

	}

	public static String getMultiPicklistinSection(String labelName,String sectionname) {

		return "//*[text()='"+sectionname+"']/parent::h3/following-sibling::div//*[text()='"+labelName+"']/parent::div";

	}
	
public static String getMultiPicklistmovenextbutton(String labelName) {

		return "//*[text()='" + labelName + "']/parent::div//span[text()='Available']/parent::div/following-sibling::div//button[@title='Move selection to Chosen']";

	}

	public static String getTopPanelButton(String labelName) {

		return "//div[contains(@class,'slds-page-header')]//a/div[text()='" + labelName + "']";

	}
	
	@FindBy(how = How.XPATH, using = "//a[contains(@title,'User Detail')]/span")
	public WebElement UserDetailsButton;
	
	public static String getTopPanelButtonClassic(String labelName) {

		return "//a/span[text()='" + labelName + "']";
				

	}
	
	@FindBy(how = How.XPATH, using = "//*[@id='brandBand_1']/div/div[1]/div/div/div/div[1]/div[1]/div[2]/ul/li/a")

	public WebElement NewOpportunityBtn_xpath;
	




	public static String getRelatedListButton(String labelName) {

		return "//div[@data-aura-class='forceRelatedListContainer']//span[@title='" + labelName + "']/ancestor::header/following-sibling::div//a/div[text()='" + labelName + "']";

	}

	public static String getTextViewMode(String labelName) {

		return "//*[text()='" + labelName + "']//following::span[2]";

	}



	public static String getDetailScreenLink(String linkText) {

		return "//*[text()='" + linkText + "']//following::a[1]";

	}
	

	public static String getCheckbox(String labelName) {

		return "//span[text()='" + labelName + "']/parent::label/following-sibling::input";

	}
	
	public static String getCheckboxforSubfunc(String labelName) {

		return "//span[text()='"+labelName+"']/parent::label/following-sibling::div//input[@type='checkbox']";

	}
	
	
	public static String getCheckboxforSubfunc2(String labelName) {

		return "//span[text()='"+labelName+"']/parent::label/following::input[1][@type='checkbox']";

	}

	
	public static String getDateIcon2(String labelName) {

		return "//label[text()='" + labelName + "']/following-sibling::div//a";

	}


	
	
	public static String getRightArrowButtonforGlobal()
	{
		return "//td/a/img[@class='picklistArrowRight']";
	}
	public static String getCheckboxGlobal() {
		return "//input[contains(@id,'agmtInfo:GlobalagreementInformationForm:showstate:SelectAll') and (contains(@type,'checkbox'))]";
	}
	
	public static String getLogin_Pop_up_XPath() {
		return "//button[text()='Log In']";
	}
	
	public static String getRelatedList_Link_Header_Xpath(String labelName) {
		
		return "//h1[contains(@title,'" + labelName + "')]";
	}
	
	public static String getSaveButton() {

		return "//input[contains(@value,'Save & Continue')]";

	}
	
	public static String getDoneButtonGlobal() {
		return "//input[contains(@value,'Done') and contains(@type,'button')]";
	}
	
	public static String getDoneButton() {

		return "//input[contains(@value,'Done') and contains(@type,'submit')]";

	}
	
	public static String getReturnButton() {

		return "//input[@type='submit' and @value='Return']";

	}
	
	

	
	public static String selectOKButton() {
		return "//div/a[text()='OK']";
	}
	
	public static String selectAcceptButton() {
		return "//input[contains(@value,'Accept')]";
	}
	
	public static String selectHomeButtonLightning() {
		return "//a[contains(@href,'/lightning/page/home') or @title='Home']/span";
	}
	
	
	public static String getRecentButton() {
		return "//span[contains(text(),'Related')]";
	}
	

	public static String getShowAllLink() {
		return "//div/a[contains(text(),'Show All')]";
	}
	

	public static String getRelatedList_Items(String labelName) {

		return "//div/a/span[contains(text(),'" + labelName + "')]";

	}
	public static String getOpportunitiesLink() {
		return "//div/a/span[contains(text(),'Opportunities')]";
	}
	
	
	public static String getDateIcon(String labelName) {

		return "//span[text()='" + labelName + "']/parent::label/following-sibling::div/a";

	}

	public static String getDateIconforSubFunc(String labelName) {

		return "//label[text()='"+labelName+"']/..//button[@title='Select a date']";

	}

	public static String getDateSelection() {

		return "//*[text()='Today']";

	}

	public static String getRadioButton(String labelName) {

		return "//*[contains(text(),'" + labelName + "')]/parent::div/preceding-sibling::div/span[@class='slds-radio--faux']";

	}
	
	
	public static String getRadioButton2(String labelName) {

		return "//*[contains(text(),'" + labelName + "')]/preceding-sibling::span[@class='slds-radio_faux']";

	}


	public static String getTabs(String tabName) {

		return 
				"//li/a[@title='"+tabName+"' or text()='"+tabName+"']";
				//span[@class='title'][text()='" + tabName + "']//ancestor::a";

	}

	
	public static String getobjectTab(String tabName) {

		return 
				"//one-app-nav-bar-item-root/a[@title='"+tabName+"']";

	}
	public static String RelatedlistLink(String RelatedlistName) {

		return "//a[@class='rlql-relatedListLink']/span[contains(text(),'" + RelatedlistName + "')]";

	}

	public static String SearchedValueLink(String SearchedValue) {

		return "//a[contains(@class,'outputLookupLink')][contains(@data-ownerid,'a')][text()='" + SearchedValue + "']";

	}

	public static String getTextArea(String Labelname) {

		return "//span[text()='" + Labelname + "']/parent::label/following-sibling::textarea";

	}
	
	public static String getTextAreaforSubfunc(String Labelname) {

		return "//label[text()='"+Labelname+"']/..//textarea";

	}


	public static String getLookupDropdown(String searchValue) {

		return "(//div[contains(@class,'slds-lookup__result-text')][@title='" + searchValue + "'])[1]";
		// "//mark[@class='data-match'][text()='"+searchValue+"']";

	}

	public static String GetGlobalSearchLookupDropdown(String searchValue) {

		return "(//span[contains(@class,'mruName')][@title='" + searchValue + "'])[1]";
		// "//mark[@class='data-match'][text()='"+searchValue+"']";

	}

	public static void SelectValueinPicklist(WebDriver driver, String PicklistLabelname, String PicklistValue) {
		WebDriverWait wait = new WebDriverWait(driver, 50);
		WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(getPicklist(PicklistLabelname))));
		((JavascriptExecutor) driver).executeScript("var ele=arguments[0]; ele.innerHTML = '" + PicklistValue + "';", ele);
		// System.out.println(ele.getText());

	}

	public static void SelectValueinMultiPicklist(WebDriver driver, String MultiPicklistLabelname, String MultiPicklistValue) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 50);

		WebElement x = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(getMultiPicklist(MultiPicklistLabelname))));

		Thread.sleep(5000);
		x.findElement(By.xpath(".//span[text()='" + MultiPicklistValue + "']")).click();
		Thread.sleep(5000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(getMultiPicklistmovenextbutton(MultiPicklistLabelname)))).click();

		Thread.sleep(2000);

	}

	public static void SelectValueinMultiPicklistinSection(WebDriver driver, String MultiPicklistLabelname, String MultiPicklistValue, String Sectioname) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 50);

		WebElement x = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(getMultiPicklistinSection(MultiPicklistLabelname,Sectioname))));

		Thread.sleep(5000);
		//Generic_Functions.makeElementViewByxpath("//span[text()='" + MultiPicklistValue + "']", driver);
		Thread.sleep(5000);
		x.findElement(By.xpath(".//li//*[text()='" + MultiPicklistValue + "']")).click();
		Thread.sleep(5000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(getMultiPicklistmovenextbutton(MultiPicklistLabelname)))).click();

		Thread.sleep(2000);

	}
	// Pawan- Xpath for Main Tabs on Screen
	public static String GetMAinTabs(String TabName) {

		return "//a[@title='" + TabName + "']/span";

	}

	public static void SelectScrollableValueinMultiPicklist(WebDriver driver, String MultiPicklistLabelname, String MultiPicklistValue) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 50);
	
			WebElement x = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(getMultiPicklist(MultiPicklistLabelname))));
			Thread.sleep(5000);
			com.stifel.corefunctions.Common_Functions.makeElementViewByxpath("//span[text()='" + MultiPicklistValue + "']", driver);
			Thread.sleep(5000);
			x.findElement(By.xpath("//span[text()='" + MultiPicklistValue + "']")).click();
			Thread.sleep(5000);
	
			com.stifel.corefunctions.Common_Functions.makeElementViewByxpath(getMultiPicklistmovenextbutton(MultiPicklistLabelname), driver);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(getMultiPicklistmovenextbutton(MultiPicklistLabelname)))).click();
			Thread.sleep(5000);
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static String getInputTextBox(String labelName) {
		return "//*[text()='" + labelName + "']/parent::*/parent::*//input[@class=' input']";
	}

	public static String getToggle(String labelName) {

		return "//*[text()='" + labelName + "']//preceding::*[@class='slds-checkbox_faux']";

	}

	
public static String confirmBtnXpath () {
	return "//*[@id='lightning']/div[4]/div/div/table/tr/div[1]/div/div/div[3]/button[1]";
}

public static  String SaveBtnXpath() {
	return "//*[@id='lightning']/div[4]/div/div/table/tr/div[1]/div/div/div[3]/button";
}

public static String datePicker() {
	return "//a[@class='datePicker-openIcon display']";
}

public static String  submitSigningsbtn() {
	return "//a[text()='Submit Signing']";
}

public static String  provideAccNumbtn() {
	return "//a[text()='Provide Account Number']";
}

}