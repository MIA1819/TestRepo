package com.stifel.LEXCommons;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Keys;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.stifel.corefunctions.Browser_setup;
import com.stifel.corefunctions.Common_Functions;
import com.stifel.corefunctions.ReportGenerator;


public class SF_StandardLEX_Common_Navigations extends Browser_setup {

	/************ Xpaths related to Common Navigations ************************************************************************************************************/

	public String Dropdown_xpath1 = "//a[@title='";
	public String Dropdown_xpath2 = "']";

	
	public String ValidateRCHeader(String labelName) {
		return "//p[text()='" + labelName + "']";
	}

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'No results')]")
	public List<WebElement> NoResults_xpath;

	@FindBy(how = How.XPATH, using = "//input[@name='login']/parent::td[@id='topButtonRow']//input[4]")
	public WebElement Loginbtn;


	@FindBy(how = How.XPATH, using = "//span[@class='uiImage']/ancestor::span[@class='photoContainer forceSocialPhoto']")
	public WebElement UserIconImg_xpath;

	@FindBy(how = How.XPATH, using = "//a[text()='Log Out']")
	public WebElement LogoutLink_xpath;

	@FindBy(how = How.XPATH, using = "(//a[text()='Switch to Salesforce Classic'])[1]")
	public WebElement SwitchtoClassic_xpath;

	@FindBy(how = How.XPATH, using = "//span[contains(@class,'globalHeaderProfilePhoto')]")
	public WebElement headerprofile_xpath;

	@FindBy(how = How.XPATH, using = "//span[contains(@class,'globalHeaderProfilePhoto')]")
	public List<WebElement> headerprofilelist_xpath;

	@FindBy(how = How.XPATH, using = "//div[@class='linkElements']/a[text()='Switch to Lightning Experience']")
	public WebElement Switchtolight_xpath;

	@FindBy(how = How.XPATH, using = "//img[@title='User']")
	public List<WebElement> Lightninguserimage_xpath;

	@FindBy(how = How.XPATH, using = "//input[@title='Search Salesforce']")
	public WebElement GlobalSearch_xpath;

	//@FindBy(how = How.XPATH, using = "//nav[contains(@class,'appLauncher')]//button")   
	@FindBy(how = How.XPATH, using = "//*[contains(@class,'oneAppNavContainer')]/one-appnav/div/div/div/div/one-app-launcher-header/button")  // Modified by Bhabani last time	
	public WebElement Applauncher_xpath;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@class,'oneAppNavContainer')]/one-appnav/div/div/div/div/one-app-launcher-header/button")
	public WebElement OneForceConsoleApplauncher_xpath;  //"//*[@class='oneAppNavContainer']//div/one-app-launcher-header/button") 
	
	@FindBy(how = How.XPATH, using = "//input[@placeholder='Search apps or items...']")
	public WebElement SearchApp_xpath;
	
	@FindBy(how = How.XPATH, using = "//div[@class='linkElements']/a[text()='Switch to Lightning Experience']")
	public WebElement Switch_LightningView;
	
	
	@FindBy(how = How.XPATH, using = "//img[@src='/resource/Apttus_Button_SendContractMang']")
	public WebElement Send_To_CM;
	
	@FindBy (how=How.XPATH, using = "//a[contains(@title,'Accounts/Companies')]")
	public WebElement Accounts_Company_Btn_Xpath;
	
	@FindBy (how=How.XPATH, using = "//button[contains(@title,'Save') and @class='slds-button slds-button--neutral uiButton--default uiButton--brand uiButton forceActionButton']")
	public WebElement Save_Btn_Xpath;
	
	@FindBy (how=How.XPATH, using = "//*[starts-with(@id,'48:')]/div/div[5]/div[1]/div/div[2]/span/div/a")
	public WebElement Account_Name_Xpath;
	

	public String ObjectTab1_xpath = "//mark[text()='";
	public String ObjectTab2_xpath = "']/ancestor::a";;

	public String ObjectScreen1_xpath = "//span[contains(@class,' slds-text-link')][text()='";
	public String ObjectScreen2_xpath = "']";

	@FindBy(how = How.XPATH, using = ".//button[@title = 'Save']")
	public WebElement SaveButton_xpath;
	
//	public  String getPositionAutoNum_xpath(String positionName){
//		return "//span[text()='"+positionName+"']/../../../th/span/a";
//		}
//		
//		
//		public  String getPositionTableUsers_xpath(){
//			return "//table[@class='slds-table forceRecordLayout slds-table--header-fixed slds-table--edit slds-table--bordered resizable-cols slds-table--resizable-cols uiVirtualDataTable']/tbody/tr[*]/td[4]/span/a']";
//			}		//table[@class='slds-table forceRecordLayout slds-table--header-fixed slds-table--edit slds-table--bordered resizable-cols slds-table--resizable-cols uiVirtualDataTable']/tbody/tr[*]/td[4]/span/a

	/*****************************Core functions for Common Navigations ******************************************************************************************/

	// ***********This is a method to Switch from Lightning to Classic**************//
	public void SwitchtoClassic(WebDriver driver,  ReportGenerator suit, String folder) throws Exception {
		SF_StandardLEX_Common_Navigations LightningWE = new SF_StandardLEX_Common_Navigations();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_Common_Navigations.class);
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOf(LightningWE.UserIconImg_xpath)).click();
		Thread.sleep(10000);
		wait.until(ExpectedConditions.visibilityOf(LightningWE.SwitchtoClassic_xpath)).click();
		Thread.sleep(5000);
		WebElement Switchtolight = wait.until(ExpectedConditions.visibilityOf(LightningWE.Switchtolight_xpath));
		if (Switchtolight.isDisplayed()) {
			Common_Functions.ResultPass(driver, suit, folder, "Switching from Lighting to Classic ", "Switching from Lighting to Classic should be done","Switching from Lighting to Classic successful");
		} else {
			Common_Functions.ResultFail(driver, suit, folder, "Switching from Lighting to Classic", "Switching from Lighting to Classic should be done","Switching from Lighting to Classic unsuccessful");
		}

	}

	// ***********This is a method to Switch from Classic to Lightning**************//
	public void SwitchtoLightning(WebDriver driver,  ReportGenerator suit, String folder) throws Exception {
		SF_StandardLEX_Common_Navigations LightningWE = new SF_StandardLEX_Common_Navigations();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_Common_Navigations.class);
		WebDriverWait wait = new WebDriverWait(driver, 120);


			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(LightningWE.Switchtolight_xpath)).click();
			Thread.sleep(2000);
			//System.out.println("Lightning link clicked successfully");
			WebElement Applaunch = wait.until(ExpectedConditions.visibilityOf(LightningWE.Applauncher_xpath));

			if (Applaunch.isDisplayed()) {
				Common_Functions.ResultPass(driver, suit, folder, "Switching from Classic to Lighting", "Switching from Classic to Lighting should be done","ing from Classic to Lighting successful");
			} else {
				Common_Functions.ResultFail(driver, suit, folder, "Switching from Classic to Lighting", "Switching from Classic to Lighting should be done","Switching from Classic to Lighting unsuccessful");
			}
		}


	// ***********This is a method to navigate to an Object tab. For e.g.
	// Account/Companies, Opportunity etc.**************//
	public void SearchandClickObjectTab(WebDriver driver,  ReportGenerator suit, String folder, String Object) throws Exception {
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		System.out.println(suit);
		System.out.println(folder);
		SF_StandardLEX_Common_Navigations LightningWE = new SF_StandardLEX_Common_Navigations();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_Common_Navigations.class);
		driver.navigate().refresh();
		Thread.sleep(15000);
		boolean ApplauncherPresent =  driver.findElements(By.xpath("//*[contains(@class,'oneAppNavContainer')]/one-appnav/div/div/div/div/one-app-launcher-header/button")).size()!=0;
		
		if (ApplauncherPresent) {
		wait.until(ExpectedConditions.visibilityOf(LightningWE.Applauncher_xpath)).sendKeys(Keys.ENTER);;
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@placeholder,'Search apps and items')]"))).sendKeys(Object);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a//p/b[text()='"+Object+"']"))).click();
		
		Thread.sleep(5000);
		
		if(Object.equalsIgnoreCase("My Lists"))
		{
			boolean list = driver.findElements(By.xpath("//div[text()='All Contacts']")).size()>0;	
			if(list)
			{//System.out.println("Navigated to "+Object+" list screen");
				Common_Functions.ResultPass(driver, suit, folder, "Navigate to "+Object+" list screen", "Navigate to "+Object+" list screen", "Navigated to "+Object+" list screen");
			}
			
			else {
				
				//System.out.println("Failed to Navigate to "+Object+" list screen");
				Common_Functions.ResultFail(driver, suit, folder, "Navigate to "+Object+" list screen", "Navigate to "+Object+" list screen", "Failed to Navigate to "+Object+" list screen");
			}
			
		}
		else {
		     boolean li = driver.findElements(By.xpath("//div[contains(@class,'headerRegion')]//span[text()='"+Object+"']")).size()>0;
		if(li)
		{//System.out.println("Navigated to "+Object+" list screen");
			Common_Functions.ResultPass(driver, suit, folder, "Navigate to "+Object+" list screen", "Navigate to "+Object+" list screen", "Navigated to "+Object+" list screen");
		}
		
		else {
			
			//System.out.println("Failed to Navigate to "+Object+" list screen");
			Common_Functions.ResultFail(driver, suit, folder, "Navigate to "+Object+" list screen", "Navigate to "+Object+" list screen", "Failed to Navigate to "+Object+" list screen");
		}
		}
		}
	}
	

	// ***********This is a method to search and click on an App in Applauncher
	// **************//
	public void SearchandClickApp(WebDriver driver,  ReportGenerator suit, String folder, String app) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		SF_StandardLEX_Common_Navigations LightningWE = new SF_StandardLEX_Common_Navigations();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_Common_Navigations.class);
		Thread.sleep(5000);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", LightningWE.Applauncher_xpath);

		//wait.until(ExpectedConditions.visibilityOf(LightningWE.Applauncher_xpath)).click();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getAppFromAppLauncher(app)))).click();
	}

	// ***********This is a method to Change the owner of the record  **************//
	
	public void ChangeOwner(WebDriver driver,  ReportGenerator suit, String folder, String Objecttype, String OwnerName) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		SF_StandardLEX_Common_Navigations LightningWE = new SF_StandardLEX_Common_Navigations();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_Common_Navigations.class);
		driver.navigate().refresh();
		Thread.sleep(15000);
		WebElement changeOwnerBtn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='Change Owner']")));
		Actions a=new Actions(driver);
		a.moveToElement(changeOwnerBtn).click().build().perform();
		Thread.sleep(5000);
		List<WebElement> SearchPeopleModal = driver.findElements(By.xpath("//input[@placeholder='Search People...']"));
		if(SearchPeopleModal.size()==0) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,200)");
			a.moveToElement(changeOwnerBtn).click().build().perform();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search People...']"))).sendKeys(OwnerName);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'in People')]"))).click();

		Thread.sleep(3000);
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search People...']"))).sendKeys(Keys.ENTER);
		 Thread.sleep(10000);
		 List<WebElement> lists = driver.findElements(By.xpath("(//div[@class='name']//a[text()='"+OwnerName+"'])[1]"));
		Thread.sleep(5000); 

		if(lists.size()>1)
        {
        wait.until(ExpectedConditions.visibilityOf(lists.get(lists.size() - 1))).click();
        }
        else 
        {
            wait.until(ExpectedConditions.visibilityOf(lists.get(0))).click();
        }
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@value='change owner']"))).click();
		Thread.sleep(10000);
	}

	// ***********This is a method to switch among object tabs 
	// **************//
	public void clickonObjectTab(WebDriver driver,  ReportGenerator suit, String folder, String Tabname) throws Exception {
       
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		SF_StandardLEX_Common_Navigations LightningWE = new SF_StandardLEX_Common_Navigations();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_Common_Navigations.class);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SF_StandardLEX_WebElementLocators.getobjectTab(Tabname))));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		System.out.println(SF_StandardLEX_WebElementLocators.getobjectTab(Tabname));
		Thread.sleep(10000);
		String value =wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SF_StandardLEX_WebElementLocators.getobjectTab(Tabname)+"/.."))).getAttribute("class");
		System.out.println(value);
		if (value.contains("slds-is-active")) {
             System.out.println("Object Tab Clicked");
			//Common_Functions.ResultPass(driver, suit, folder, "The tab should be displayed", "The tab should be displayed", "The tab is displayed");
		} else {
			 System.out.println("Object Tab not Clicked");
			///Common_Functions.ResultFail(driver, suit, folder, "The  tab should be displayed", "The tab should be displayed", "The tab is not displayed");
		}
	}

	public void Newbuttonclick(WebDriver driver) throws Exception {

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        WebDriverWait wait = new WebDriverWait(driver,30);
        jse.executeScript("arguments[0].click()", wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@title='New']"))));
        Thread.sleep(10000);

	}
	
	
	
	public void Newbuttonclick2(WebDriver driver) throws Exception {

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        WebDriverWait wait = new WebDriverWait(driver,30);
        jse.executeScript("arguments[0].click()", wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='New']"))));
        Thread.sleep(10000);

	}
	
	
	public void ClickEditbutton(WebDriver driver) throws Exception {
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.navigate().refresh();
		Thread.sleep(15000);
		 WebDriverWait wait = new WebDriverWait(driver,30);
		 JavascriptExecutor jse = (JavascriptExecutor)driver;
		 jse.executeScript("arguments[0].click()", wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Edit']"))));
        Thread.sleep(3000);
	}
	public void SwitchTabsinLightning(WebDriver driver,  ReportGenerator suit, String folder, String Tabname) throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		SF_StandardLEX_Common_Navigations LightningWE = new SF_StandardLEX_Common_Navigations();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_Common_Navigations.class);
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.getTabs(Tabname))));
		e.click();
		Thread.sleep(10000);
		String value = e.getAttribute("aria-selected");
		System.out.println(value);
		if (value.equalsIgnoreCase("true")) {

			Common_Functions.ResultPass(driver, suit, folder, "The tab should be displayed", "The tab should be displayed", "The tab is displayed");
		} else {
			Common_Functions.ResultFail(driver, suit, folder, "The  tab should be displayed", "The tab should be displayed", "The tab is not displayed");
		}
	}
	
	//click on show more dropdown
	public void clickonShowMore(WebDriver driver)
	{
		WebDriverWait Wait = new WebDriverWait(driver, 50);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Show 2 more actions']"))));
		
	}

	// ***********This is a method to Search and click on a Related list on the
	// record **************//
	public void ClickonRelatedList(WebDriver driver,  ReportGenerator suit, String folder, String RelatedlistName) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Actions a=new Actions(driver);
		driver.navigate().refresh();
		Thread.sleep(5000);
		SwitchTabsinLightning(driver, suit, folder, "Related");
		// driver.navigate().refresh();
		Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("window.scrollBy(0,250)", "");

		Thread.sleep(3000);

		List<WebElement> ShowAllink = driver.findElements(By.xpath("//a[contains(text(), 'Show All')]"));
		if (ShowAllink.size() > 0) {
			for (WebElement e : ShowAllink) {
				a.moveToElement(e).build().perform();	//using actions class to click the element - Last Modified by Ankur
				 js.executeScript("arguments[0].click();", e);
			}

		}
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),
		// 'Show All')]"))).click();
		Thread.sleep(3000);

		List<WebElement> loginSeismic = driver.findElements(By.xpath("//button[@title='Close']"));
		if (loginSeismic.size() > 0) {
			System.out.println("PASSSSSSS");
			for (WebElement e : loginSeismic) {
				e.click();
			}
			System.out.println("PASSSSSSS");
		}

		List<WebElement> Relatedlistlink = driver.findElements(By.xpath(SF_StandardLEX_WebElementLocators.RelatedlistLink(RelatedlistName)));

		if (Relatedlistlink.size() > 0) {
			for (WebElement e : Relatedlistlink) {
				a.moveToElement(e).build().perform();	//using actions class to click the element - Last Modified by Ankur
				 js.executeScript("arguments[0].click();", e);
				Common_Functions.ResultPass(driver, suit, folder, "The Relatedlist  should be clicked", "The Relatedlist is clicked", "The Relatedlist " + RelatedlistName + " is clicked");
				break;
			}
		} else {
			Common_Functions.ResultPass(driver, suit, folder, "The Relatedlist  should be clicked", "The Relatedlist should be clicked","The Relatedlist " + RelatedlistName + " is not present");
		}

	}

	// ***********This is a method to Login as a user via System Admin
	// **************//

	public void LoginasUser(WebDriver driver,  ReportGenerator suit, String folder, String Searchvalue) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		SF_StandardLEX_Common_Navigations LightningWE = new SF_StandardLEX_Common_Navigations();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_Common_Navigations.class);
		Thread.sleep(11000);
		SearchwithObjectType(driver, suit, folder, Searchvalue, "People");
		Thread.sleep(9000);
		LoginProcess(driver);
		driver.navigate().refresh();
		Thread.sleep(10000);
		//driver.navigate().refresh();
		List<WebElement> LoginPopup = driver.findElements(By.xpath("//button[text()='Log In']"));
		if (LoginPopup.size() > 0) {
			driver.navigate().refresh();
			// wait.until(ExpectedConditions.visibilityOf(LoginPopup.get(LoginPopup.size()
			// - 1))).click();
			Thread.sleep(5000);

			List<WebElement> LoggedinUser = driver.findElements(By.xpath("//span[contains(text(),'" + Searchvalue + "')]"));
			List<WebElement> NotLogged = driver.findElements(By.cssSelector("iframe[title*=User]"));
			if (LoggedinUser.size() > 0) {
				Common_Functions.ResultPass(driver, suit, folder, "Validate Whether the user is logged in", "User is logged in", "User is logged in");

			} else if (NotLogged.size() > 0) {
				Search(driver, suit, folder, Searchvalue);
				Thread.sleep(5000);
				LoginProcess(driver);
				LoggedinUser = driver.findElements(By.xpath("//span[contains(text(),'" + Searchvalue + "')]"));
				if (LoggedinUser.size() > 0) {
					Common_Functions.ResultPass(driver, suit, folder, "Validate Whether the user is logged in", "User is logged in", "User is logged in");

				} else {
					Common_Functions.ResultFail(driver, suit, folder, "Validate Whether the user is logged in", "User is not logged in", "User is not logged in");

				}

			}
		} else {

			driver.navigate().refresh();
			Thread.sleep(10000);
			List<WebElement> LoggedinUser = driver.findElements(By.xpath("//span[contains(text(),'" + Searchvalue + "')]"));
			List<WebElement> NotLogged = driver.findElements(By.xpath("//iframe[contains(@title,'User:')]"));
			if (LoggedinUser.size() > 0) {
				Common_Functions.ResultPass(driver, suit, folder, "Validate Whether the user is logged in", "User is logged in", "User is logged in");

			} else if (NotLogged.size() > 0) {
				Search(driver, suit, folder, Searchvalue);
				Thread.sleep(5000);
				LoginProcess(driver);
				LoggedinUser = driver.findElements(By.xpath("//span[contains(text(),'" + Searchvalue + "')]"));
				if (LoggedinUser.size() > 0) {
					Common_Functions.ResultPass(driver, suit, folder, "Validate Whether the user is logged in", "User is logged in", "User is logged in");

				} else {
					Common_Functions.ResultFail(driver, suit, folder, "Validate Whether the user is logged in", "User is not logged in", "User is not logged in");

				}

			}
		}

	}

	public String getrecIdfromURL(WebDriver driver) {
		String[] RecIDVal = null;
		String RecID = driver.getCurrentUrl();
		RecIDVal = RecID.split("/");
		return RecIDVal[6];
	}
	
	
	public void LogoutUser(WebDriver driver,  ReportGenerator suit, String folder) throws Exception {
		SF_StandardLEX_Common_Navigations LightningWE = new SF_StandardLEX_Common_Navigations();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_Common_Navigations.class);
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOf(LightningWE.UserIconImg_xpath)).click();
		Thread.sleep(5000);
		//WaitUntilElementIsVisible("//a[text()='Log Out']", driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//a[text()='Log Out']")));
		Thread.sleep(6000);
		driver.navigate().refresh();
		Thread.sleep(15000);
		
		WaitUntilElementIsVisible("//*[text()='Home']", driver);
		boolean Hometab = driver.findElement(By.xpath("//*[text()='Home']")).isDisplayed();
		
		if (Hometab) {
			Common_Functions.ResultPass(driver, suit, folder, "Logout as USer ", "Logout should be successful", "Logged out successfully");
		} else {
			Common_Functions.ResultFail(driver, suit, folder, "Logout as USer ", "Logout should be successful", "Log out was unsuccessful");
		}
	}

	// ***********This is a method to Logout as a user and come to Sys admin
	// screen **************//
	public void LogoutasUser(WebDriver driver,  ReportGenerator suit, String folder, String appName) throws Exception {
		SF_StandardLEX_Common_Navigations LightningWE = new SF_StandardLEX_Common_Navigations();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_Common_Navigations.class);
		WebDriverWait wait = new WebDriverWait(driver, 80);
		wait.until(ExpectedConditions.visibilityOf(LightningWE.UserIconImg_xpath)).click();
		Thread.sleep(5000);
		//WaitUntilElementIsVisible("//a[text()='Log Out']", driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//a[text()='Log Out']")));
		//wait.until(ExpectedConditions.visibilityOf(LightningWE.LogoutLink_xpath)).click();
		Thread.sleep(10000);
		driver.navigate().refresh();
		Thread.sleep(10000);

	}

	// ***********This is a method to Logout from Sys admin **************//
	public void CompleteLogout(WebDriver driver,  ReportGenerator suit, String folder) throws Exception {
		SF_StandardLEX_Common_Navigations LightningWE = new SF_StandardLEX_Common_Navigations();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_Common_Navigations.class);
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOf(LightningWE.UserIconImg_xpath)).click();
		wait.until(ExpectedConditions.visibilityOf(LightningWE.LogoutLink_xpath)).click();
		Thread.sleep(5000);
		List<WebElement> MainScreen = driver.findElements(By.xpath("//img[@name='logo']"));
		if (MainScreen.size() > 0) {
			Common_Functions.ResultPass(driver, suit, folder, "Logout as USer ", "Logout should be successful", "Logged out successfully");
		} else {
			Common_Functions.ResultFail(driver, suit, folder, "Logout as USer ", "Logout should be successful", "Log out was unsuccessful");
		}

	}

	// ***********This is a method to Search a record as per it's object type
	// **************//
	public void SearchandClickrecordwithObjectType(WebDriver driver,  ReportGenerator suit, String folder, String ObjectType, String Searchvalue) throws Exception {
		SF_StandardLEX_Common_Navigations ClassicWE = new SF_StandardLEX_Common_Navigations();
		ClassicWE = PageFactory.initElements(driver, SF_StandardLEX_Common_Navigations.class);
		WebDriverWait wait = new WebDriverWait(driver, 50);
		SearchwithObjectType(driver, suit, folder, Searchvalue, ObjectType);
		Thread.sleep(5000);
		driver.navigate().refresh();

		Thread.sleep(5000);
		if (ObjectType.equals("Phone DNCs")) {

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@title,'DNC - ')]"))).click();
			Thread.sleep(5000);
			Common_Functions.ResultPass(driver, suit, folder, "Validate Whether the Record was found and is clicked", "Record should be clicked", "Record is found and clicked");

		} else {

			List<WebElement> NoResults = driver.findElements(By.xpath("(//span[text()='" + Searchvalue + "'])[1]"));
			System.out.println("NoResults: " + NoResults.size());
			if (NoResults.size() == 0) {
				Common_Functions.ResultFail(driver, suit, folder, "There are no results for Searched value", "There are no results for Searched value","There are no results for Searched value");
			} else {

				String SearchedValue_xpath = SF_StandardLEX_WebElementLocators.SearchedValueLink(Searchvalue);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SearchedValue_xpath))).click();
				Thread.sleep(5000);
				/*wait.until(
				ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//h1//span[text()='"+Searchvalue+"']")));*/
				List<WebElement> RecName = driver.findElements(By.xpath("//h1//span[text()='" + Searchvalue + "']"));
				System.out.println("RecName:" + RecName.size());

				if (RecName.size() > 0) {
					Common_Functions.ResultPass(driver, suit, folder, "Validate Whether the Record was found and is clicked", "Record should be clicked", "Record is found and clicked");
				} else {
					Common_Functions.ResultFail(driver, suit, folder, "There are no results for Searched value", "There are no results for Searched value","There are no results for Searched value");

				}
			}
		}
	}

	// ***********This is a method to perform global Search on a record and
	// click on it **************//
	public void SearchandClickrecord(WebDriver driver,  ReportGenerator suit, String folder, String ObjectType, String Searchvalue) throws Exception {
		SF_StandardLEX_Common_Navigations LightningWE = new SF_StandardLEX_Common_Navigations();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_Common_Navigations.class);
		WebDriverWait wait = new WebDriverWait(driver, 50);
		Search(driver, suit, folder, Searchvalue);

		Thread.sleep(5000);
		driver.navigate().refresh();

		Thread.sleep(5000);

		List<WebElement> NoResults = driver.findElements(By.xpath("(//span[text()='" + Searchvalue + "'])[1]"));
		System.out.println("NoResults: " + NoResults.size());
		if (NoResults.size() == 0) {
			Common_Functions.ResultFail(driver, suit, folder, "There are no results for Searched value", "There are no results for Searched value", "There are no results for Searched value");
		} else {
	
			Common_Functions.ResultPass(driver, suit, folder, "Validate Whether the Record was found and is clicked", "Record should be clicked", "Record is found and clicked");
		}
	}

	// ***********This is a method to switch from one window to other by
	// providing the Window title **************//
	public void SwitchToSpecificTitleWindow(WebDriver driver, String title) {
		WebDriver popup = null;
		Iterator<String> windowIterator = driver.getWindowHandles().iterator();
		while (windowIterator.hasNext()) {
			String windowHandle = windowIterator.next();
			popup = driver.switchTo().window(windowHandle);
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			String currtitle = popup.getTitle();
			if (currtitle.contains(title)) {
				System.out.println(currtitle);
				break;
			}
		}

	}


	/********************************Supporting functions for Common Navigations*****************************************************************************/

	public void Search(WebDriver driver,  ReportGenerator suit, String folder, String Searchvalue) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 50);
		driver.navigate().refresh();
		Thread.sleep(10000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@placeholder,'Search')]"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@placeholder,'Search')]"))).sendKeys(Searchvalue/*, Keys.ENTER*/);

		Thread.sleep(10000);
		List<WebElement> Results = driver.findElements(By.xpath(SF_StandardLEX_WebElementLocators.GetGlobalSearchLookupDropdown(Searchvalue)));
		System.out.println("Results=" + Results.size());
		if (Results.size() > 0) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SF_StandardLEX_WebElementLocators.GetGlobalSearchLookupDropdown(Searchvalue)))).click();
			Common_Functions.ResultPass(driver, suit, folder, "Validate Whether the Record was found and is clicked", "Record should be clicked", "Record is found and clicked");

		} else {
			Common_Functions.ResultFail(driver, suit, folder, "There are no results for Searched value", "There are no results for Searched value", "There are no results for Searched value");
		}

	}


	public void LoginProcess(WebDriver driver) throws Exception {
		SF_StandardLEX_Common_Navigations LightningWE = new SF_StandardLEX_Common_Navigations();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_Common_Navigations.class);
		WebDriverWait wait = new WebDriverWait(driver, 80);

		// JavascriptExecutor executor = (JavascriptExecutor) driver;

		Thread.sleep(8000);

		List<WebElement> Userdetailbutton = driver.findElements(By.xpath(SF_StandardLEX_WebElementLocators.getTopPanelButton("User Detail")));

		if (Userdetailbutton.size() == 0) {
			Thread.sleep(10000);
			driver.switchTo().frame(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@name,'vfFrameId')]"))));
			Thread.sleep(5000);
			
		//	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe[title*=User]")));
			wait.until(ExpectedConditions.visibilityOf(LightningWE.Loginbtn)).click();
			Thread.sleep(5000);
			
		}

		else {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a/div[text()='User Detail']"))).click();
			Thread.sleep(9000);
			
			driver.switchTo().frame(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@name,'vfFrameId')]"))));
			Thread.sleep(5000);
			
			
			wait.until(ExpectedConditions.visibilityOf(LightningWE.Loginbtn)).click();
			Thread.sleep(5000);
			driver.switchTo().defaultContent();
			
		}
	}


	public void SearchwithObjectType(WebDriver driver,  ReportGenerator suit, String folder, String Searchvalue, String ObjectType) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 200);
		driver.navigate().refresh();
		Thread.sleep(15000);
		WaitUntilElementIsVisible("//button[contains(@aria-label, 'Search')]", driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@aria-label, 'Search')]"))).click();
		WaitUntilElementIsVisible("//input[contains(@placeholder, 'Search...')]", driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@placeholder, 'Search...')]"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@placeholder, 'Search...')]"))).sendKeys(Searchvalue);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@placeholder, 'Search...')]"))).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class,'SEARCH_OPTION')]"))).click();
		Thread.sleep(5000);
		List<WebElement> Results = driver.findElements(By.xpath("//div[contains(text(),'No results yet')]"));
		System.out.println("Results=" + Results.size()+"");
		if (Results.size() > 0) {
			Common_Functions.ResultFail(driver, suit, folder, "There are no results for Searched value", "There are no results for Searched value", "There are no results for Searched value");

		} else {
			driver.navigate().refresh();
				Thread.sleep(10000);
				WaitUntilElementIsVisibleAndClickUsingXpath("//button/span[text()='Show More']", driver);
				WaitUntilElementIsVisibleAndClickUsingXpath("(//h2[text()='Searchable objects from navigation bar']/..//span[text()='"+ObjectType+"'])[1]",driver);
				Thread.sleep(5000);
	            WaitUntilElementIsVisibleAndClickUsingXpath("//table/tbody/tr[1]/th[1]//a[text()='" + Searchvalue + "']", driver);
	           
				//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='" + Searchvalue + "']"))).click();
				Common_Functions.ResultPass(driver, suit, folder, "Validate Whether the Record was found and is clicked", "Record should be clicked", "Record is found and clicked");
			
		}

	}	
	
	
	public void SearchwithEmployeeNumber(WebDriver driver,  ReportGenerator suit, String folder, String Searchvalue, String ObjectType) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 200);
		Thread.sleep(3000);
		WaitUntilElementIsVisible("//input[contains(@placeholder,'Search')]", driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@placeholder,'Search')]"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@placeholder,'Search')]"))).sendKeys(Searchvalue);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class,'SEARCH_OPTION')]"))).click();
		Thread.sleep(5000);
		List<WebElement> Results = driver.findElements(By.xpath("//div[contains(text(),'No results yet')]"));
		System.out.println("Results=" + Results.size()+"");
		if (Results.size() > 0) {
			Common_Functions.ResultFail(driver, suit, folder, "There are no results for Searched value", "There are no results for Searched value", "There are no results for Searched value");

		} else {
			driver.navigate().refresh();
				Thread.sleep(10000);
				WaitUntilElementIsVisibleAndClickUsingXpath("//button/span[text()='Show More']", driver);
				WaitUntilElementIsVisibleAndClickUsingXpath("(//h2[text()='Searchable objects from navigation bar']/..//span[text()='"+ObjectType+"'])[1]",driver);
				Thread.sleep(5000);
	            WaitUntilElementIsVisibleAndClickUsingXpath("//table/tbody/tr[1]/th[1]", driver);
	           
				//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='" + Searchvalue + "']"))).click();
				Common_Functions.ResultPass(driver, suit, folder, "Validate Whether the Record was found and is clicked", "Record should be clicked", "Record is found and clicked");
			
		}

	}	




	

	public void logOutUser(WebDriver driver) throws Exception
	{
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		Thread.sleep(5000);
        WebElement ele = driver.findElement(By.xpath("//span[@class='photoContainer forceSocialPhoto']//span/img[@title='User']"));
		Actions act = new Actions(driver);
		Thread.sleep(5000);
		act.moveToElement(ele).click().build().perform();
		Thread.sleep(5000);
		WebElement ele1 = driver.findElement(By.xpath("//a[text()='Log Out']"));
		boolean display = ele1.isDisplayed();
		System.out.println("Logout Button Present : " + display);
		Thread.sleep(3000);
		act.moveToElement(ele1).click().build().perform();
		Thread.sleep(10000);
		driver.navigate().refresh();
		Thread.sleep(7000);
		driver.navigate().refresh();
		Thread.sleep(7000);
		
	}

	
	/**
	 * Search the specific object from app launcher
	 * 
	 * @param driver
	 * @param suit
	 * @param folder
	 * @param Object      which you want to search
	 * @throws Exception
	 * @author ankgp
	 */
	public void searchObject(WebDriver driver,  ReportGenerator suit, String folder, String Object) throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 60);

		SF_StandardLEX_Common_Navigations LightningWE = new SF_StandardLEX_Common_Navigations();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_Common_Navigations.class);
		driver.navigate().refresh();
		WaitUntilElementIsVisibleAndClickUsingXpath("//nav[contains(@class,'appLauncher')]//button", driver);
		Thread.sleep(2000);
		WaitUntilElementIsVisibleAndClickUsingXpath("//input[contains(@placeholder,'Search apps')]", driver);
		driver.findElement(By.xpath("//input[contains(@placeholder,'Search apps')]")).sendKeys(Object);
		Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WaitUntilElementIsVisibleAndClickUsingXpath("//p/b[contains(text(),'" + Object + "')]", driver);  ////mark[contains(text(),'" + Object + "')]
	//	js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//mark[contains(text(),'" + Object + "')]")));
		
		Thread.sleep(5000);
	}
	
	
	int counter = 60;

	/**
	 * Accepts the By element and perform click on the element once it
	 *         is displayed
	 */
	final public void WaitUntilElementIsVisibleAndClickUsingByele(By element, WebDriver driver) {

		try {
			int i;
			for (i = 0; i < counter; i++) {
				try {
					// WebElement e1
					boolean flag = driver.findElement(element).isDisplayed();
					// boolean flag=e1.isDisplayed();
					if (flag) {
						driver.findElement(element).click();
						break;
					} else {
						waitFor(1);
					}

				} catch (Exception e) {
					waitFor(1);
				}
			}

			if (i >= counter) {
				System.err.println("Element is not displayed after " + i + " seconds element is :: " + element);
				String className = this.getClass().getSimpleName();
				String functionName = new Object() {
				}.getClass().getEnclosingMethod().getName();
				System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
						+  "\n" + "---***End***---");
				driver.quit();
			}

		} catch (Exception e) {

			System.err.println("****** final ******** " + e);
			String className = this.getClass().getSimpleName();
			String functionName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			//System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"+ ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");

		}
	}

	final public void waitFor(float timeToWait) {
		try {
			Thread.sleep((long) (timeToWait * 1000));
		} catch (InterruptedException e) {
		}

	}

	/**
	 Accepts String [xpath] and perform click on that element
	 * 
	 */
	final public void WaitUntilElementIsVisibleAndClickUsingXpath(String element, WebDriver driver) {

		try {
			int i;
			for (i = 0; i < counter; i++) {
				try {
					// WebElement e1
					boolean flag = driver.findElement(By.xpath(element)).isDisplayed();
					if (flag) {
						driver.findElement(By.xpath(element)).click();
						break;
					} else {
						waitFor(1);
					}

				} catch (Exception e) {
					waitFor(1);
				}
			}

			if (i >= counter) {
				System.err.println("Element is not displayed after " + i + " seconds element is :: " + element);
				String className = this.getClass().getSimpleName();
				String functionName = new Object() {
				}.getClass().getEnclosingMethod().getName();
				System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
						+  "\n" + "---***End***---");
				driver.quit();
			}

		} catch (Exception e) {

			System.err.println("****** final ******** " + e);
			String className = this.getClass().getSimpleName();
			String functionName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			//System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"+ ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");

		}
	}

	/**
	 * Accepts String [xpath] and wait until the element is displayed
	 * 
	 *
	 */
	final public void WaitUntilElementIsVisible(String element, WebDriver driver) {

		try {
			int i;
			for (i = 0; i < counter; i++) {
				try {
					boolean flag = driver.findElement(By.xpath(element)).isDisplayed();
					if (flag) {
						break;
					} else {
						waitFor(1);
					}

				} catch (Exception e) {
					waitFor(1);
				}
			}

			if (i >= counter) {
				System.err.println("Element is not displayed after " + i + " seconds element is :: " + element);
				String className = this.getClass().getSimpleName();
				String functionName = new Object() {
				}.getClass().getEnclosingMethod().getName();
				System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
						+  "\n" + "---***End***---");
				driver.quit();
			}

		} catch (Exception e) {

			System.err.println("****** final ******** " + e);
			String className = this.getClass().getSimpleName();
			String functionName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			//System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"+ ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");

		}
	}
	
	
	public void OpenUserDetailPage(WebDriver driver,  ReportGenerator suit, String folder, String User) throws Exception {
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		SF_StandardLEX_Common_Navigations LightningWE = new SF_StandardLEX_Common_Navigations();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_Common_Navigations.class);
		Thread.sleep(11000);
		SearchwithObjectType(driver, suit, folder, User, "People");
		Thread.sleep(9000);
	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a/div[text()='User Detail']"))).click();
		Thread.sleep(9000);
		
		driver.navigate().refresh();
		Thread.sleep(10000);
			
		driver.switchTo().frame(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@name,'vfFrameId')]"))));
		Thread.sleep(10000);
		
	}
	
	
	
	public void CaptureDetailsfromUserandCompare(WebDriver driver,  ReportGenerator suit, String folder, HashMap datafromexcel) throws Exception {
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		SF_StandardLEX_Common_Navigations LightningWE = new SF_StandardLEX_Common_Navigations();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_Common_Navigations.class);
		Thread.sleep(11000);
		
		HashMap<String, String> datafromApplication = new HashMap<String, String>();
		
		datafromApplication.put("Name", driver.findElement(By.xpath("//table[@class='detailList']//tr/td[text()='Name']/following-sibling::td[1]")).getText());
		
		datafromApplication.put("Email", driver.findElement(By.xpath("//table[@class='detailList']//tr/td[text()='Email']/following-sibling::td[1]/a")).getText());
		
		datafromApplication.put("Title", driver.findElement(By.xpath("//table[@class='detailList']//tr/td[text()='Title']/following-sibling::td[1]")).getText());
		
		datafromApplication.put("EmployeeNumber", driver.findElement(By.xpath("//table[@class='detailList']//tr/td[text()='Employee Number']/following-sibling::td[1]")).getText());
		
		datafromApplication.put("Federation Identifier", driver.findElement(By.xpath("//table[@class='detailList']//tr/td[text()='Federation ID']/following-sibling::td[1]")).getText());
		
		datafromApplication.put("Profile Name", driver.findElement(By.xpath("//table[@class='detailList']//tr/td[text()='Profile']/following-sibling::td[1]/a")).getText());
		
		datafromApplication.put("Role Name", driver.findElement(By.xpath("//table[@class='detailList']//tr/td[text()='Role']/following-sibling::td[1]/a")).getText());
		
		datafromApplication.put("Location", driver.findElement(By.xpath("//table[@class='detailList']//tr/td[text()='Location']/following-sibling::td[1]")).getText());
		
		datafromApplication.put("Time Zone", driver.findElement(By.xpath("//table[@class='detailList']//tr/td[text()='Time Zone']/following-sibling::td[1]")).getText());
		
		datafromApplication.put("EmailEncodingKey", driver.findElement(By.xpath("//table[@class='detailList']//tr/td[text()='Email Encoding']/following-sibling::td[1]")).getText());
		
		datafromApplication.put("LanguageLocaleKey", driver.findElement(By.xpath("//table[@class='detailList']//tr/td[text()='Language']/following-sibling::td[1]")).getText());
		
		datafromApplication.put("LocaleSidKey", driver.findElement(By.xpath("//table[@class='detailList']//tr/td[text()='Locale']/following-sibling::td[1]")).getText());
		
		datafromApplication.put("Alias", driver.findElement(By.xpath("//table[@class='detailList']//tr/td[text()='Alias']/following-sibling::td[1]")).getText());
		
		datafromApplication.put("BusinessProcessRole", driver.findElement(By.xpath("//table[@class='detailList']//tr/td[text()='Business Process Role']/following-sibling::td[1]")).getText());
		
		datafromApplication.put("Username", driver.findElement(By.xpath("//table[@class='detailList']//tr/td[text()='Username']/following-sibling::td[1]")).getText());
		
		datafromApplication.put("CompanyName", driver.findElement(By.xpath("//table[@class='detailList']//tr/td[text()='Company']/following-sibling::td[1]")).getText());
		
		datafromApplication.put("UserPreference", driver.findElement(By.xpath("//table[@class='detailList']//tr//span[text()='User Preferences']/ancestor::td/following::td[1]")).getText());
		
		datafromApplication.put("IsActive", driver.findElement(By.xpath("//table[@class='detailList']//tr/td[text()='Active']/following-sibling::td[1]/img")).getAttribute("title"));
		
		datafromApplication.put("Marketing User", driver.findElement(By.xpath("//table[@class='detailList']//tr/td[text()='Marketing User']/following-sibling::td[1]/img")).getAttribute("title"));
		
		datafromApplication.put("Service Cloud User", driver.findElement(By.xpath("//table[@class='detailList']//tr/td[text()='Service Cloud User']/following-sibling::td[1]/img")).getAttribute("title"));
		
		
		System.out.println("Name:" +datafromApplication.get("Name"));
		System.out.println("-----------------------------------------------------------------");
		
		for (String key : datafromApplication.keySet()) {
			
			if(datafromexcel.get(key)==null) {
			
				datafromexcel.put(key,"");
			}
				
			if(!(datafromexcel.get(key).equals(datafromApplication.get(key)))){
				
				if(!key.equalsIgnoreCase("Time Zone")) {
					
					System.out.println("Not Matched:" +key);
					System.out.println("Expected:" +datafromexcel.get(key));
					System.out.println("Actual:" +datafromApplication.get(key));
					}
					else {
						
						if(!datafromApplication.get("Time Zone").toString().contains(datafromexcel.get("Time Zone").toString())){
						
						System.out.println("Name:" +datafromApplication.get("Name"));
						System.out.println("Not Matched: Time Zone");
						System.out.println("Expected:" +datafromexcel.get("Time Zone"));
						System.out.println("Actual:" +datafromApplication.get("Time Zone"));
						}
						
					}
				}
			}
		
		
		
		datafromApplication.clear();
	}
	
	
	
	public void ContactValidationDataLoad(WebDriver driver,  ReportGenerator suit, String folder, String user,HashMap datafromexcel) throws Exception {
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		SF_StandardLEX_Common_Navigations LightningWE = new SF_StandardLEX_Common_Navigations();
		LightningWE = PageFactory.initElements(driver, SF_StandardLEX_Common_Navigations.class);
		Thread.sleep(11000);
		
		SearchwithEmployeeNumber(driver, suit, folder, datafromexcel.get("EmployeeNumber").toString(), "Contacts");
		Thread.sleep(10000);
		
		driver.navigate().refresh();
		Thread.sleep(10000);
		
		System.out.println("Starting Contact Validation...");
		
		String StifelOrganizationfromApp = driver.findElement(By.xpath("//span[text()='Stifel Organization']/ancestor::div/following-sibling::div//lightning-formatted-text")).getText();
		
		if(!datafromexcel.get("Org level").toString().equalsIgnoreCase(StifelOrganizationfromApp)) {
			System.out.println("Not Matched: Stifel Organization");
			System.out.println("Expected: "+datafromexcel.get("Org level"));
			System.out.println("Actual: "+StifelOrganizationfromApp);
		}
		
		String ToplevelEntityfromApp = driver.findElement(By.xpath("//span[text()='Top-Level Entity']/ancestor::div/following-sibling::div//lightning-formatted-text")).getText();
		
		String EmployeeEntityfromApp = driver.findElement(By.xpath("//span[text()='Employee Entity']/ancestor::div/following-sibling::div//lightning-formatted-text")).getText();
		
		if(!ToplevelEntityfromApp.equalsIgnoreCase("Stifel")) {
			System.out.println("Not Matched: Top Level Entity");
			System.out.println("Expected: Stifel");
			System.out.println("Actual:"+ToplevelEntityfromApp);
		}
		
		if(!EmployeeEntityfromApp.equalsIgnoreCase("SEBA")) {
			System.out.println("Not Matched: Employee Entity");
			System.out.println("Expected: SEBA");
			System.out.println("Actual:"+EmployeeEntityfromApp);
		}
		
		String RelatedInternalUser = driver.findElement(By.xpath("//span[text()='Related Internal User']/ancestor::div/following-sibling::div//a/span")).getText();
		
		if(!RelatedInternalUser.equalsIgnoreCase(user)) {
			System.out.println("Not Matched: Related Internal User");
			System.out.println("Expected: "+user);
			System.out.println("Actual: "+RelatedInternalUser);
		}
	}
	
	
	
		
	/**
	 * Accepts String input and element xpath in string to which we need to enter the data
	 */
	final public void enterDataInTextBox(String eleXpath, String data, WebDriver driver ) {
		
		WaitUntilElementIsVisible(eleXpath, driver);
		
		try {
			driver.findElement(By.xpath(eleXpath)).click();
			driver.findElement(By.xpath(eleXpath)).clear();
			driver.findElement(By.xpath(eleXpath)).sendKeys(data);
			
		}catch (Exception e) {
			System.err.println("****** final ******** " + e);
			String className = this.getClass().getSimpleName();
			String functionName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			//System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"+ ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
			
		}
		
	}
	
//	
	public void searchAndloginAsUser(WebDriver driver,  ReportGenerator suit, String folder, String userName)
			throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 120);

		// Step 0: select the search as People
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//lightning-grouped-combobox//lightning-base-combobox//input")))
				.click();
		Thread.sleep(3000);

		// Step 1: Select the search option as People
		Thread.sleep(3000);
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//lightning-grouped-combobox//lightning-base-combobox//input")))
				.sendKeys(Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE);

		
		Thread.sleep(3000);
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//lightning-grouped-combobox//lightning-base-combobox//input")))
				.sendKeys("People", Keys.ENTER);
		Thread.sleep(3000);

		// Step 2: Click on the search icon
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@placeholder,'Search')]")))
				.sendKeys(userName, Keys.ENTER);

		Thread.sleep(8000);

		// Step 3: Click on the user name
		List<WebElement> userNameList = driver.findElements(By.xpath("//div[@class='name']//descendant::a"));

		for (WebElement user : userNameList) {
			user.click();
			break;
		}
		Thread.sleep(5000);

		// Step 4: Click on the user Detail
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(SF_StandardLEX_WebElementLocators.getTopPanelButton("User Detail")))).click();
		
		
		Thread.sleep(14000);
		driver.navigate().refresh();
		Thread.sleep(18000);

		// ***********Switching to iframe******************
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@title,'User') and contains(@name,'vfFrame')]")));
		
		Thread.sleep(6000);
		// Click on the login button
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='topButtonRow']/input[4]"))).click();

		Thread.sleep(5000);
		//driver.switchTo().defaultContent();

	}

//	
//	public void UpdateStatusandApprovedProcessUsingDevConsole(WebDriver driver,  ReportGenerator suit, String folder, String ID) throws Exception {
//		
//		WebDriverWait wait = new WebDriverWait(driver, 60);
//		
//		String MainWindow = driver.getWindowHandle();
//		
//		driver.get("https://gcpaexp--sit.my.salesforce.com/_ui/common/apex/debug/ApexCSIPage");
//		Thread.sleep(15000);
//		
//		//List<WebElement> Messagebox = driver.findElements(By.xpath("//span[text() ='OK']"));
//		
//		//if(Messagebox.size()!=0) {
//			//driver.findElement(By.xpath("//span[text() ='OK']")).click();
//		//}
//		
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Debug']"))).click();
//		Thread.sleep(2000);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Open Execute Anonymous Window']"))).click();
//		Thread.sleep(3000);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='openExternalEditorToolButton']"))).click();
//		Thread.sleep(3000);
//		
//		Set<String> WindowHandles = driver.getWindowHandles();
//		
//		Iterator<String> itr= WindowHandles.iterator();
//		
//		while(itr.hasNext()) {
//			String childWindow = itr.next();
//			if((!childWindow.equals(MainWindow))){
//				 driver.switchTo().window(childWindow);
//			}
//		}
//		
//		String UpdateQuery = "Signing_Form__c sfobj = [select Id,Signing_Form_Status__c from Signing_Form__c where Id = '"+ID+"'];\r\n" + 
//				"sfobj.Signing_Form_Status__c='ITS Approved';\r\n" + 
//				"sfobj.ApprovedProcess__c=true;\r\n" +
//				"update sfobj;\r\n" ;
//				 
//				
//		Actions actions = new Actions(driver);
//
//		By linesBy = By.xpath("//*[@class='CodeMirror-code']//pre/span");    
//		List<WebElement> lines = driver.findElements(linesBy);
//
//		while (lines.size() > 0) {//deleting old Apex code    
//		    lines.get(0).click();
//		    actions.sendKeys(lines.get(0),Keys.chord(Keys.CONTROL, "A")).click().perform();
//		    actions.sendKeys(Keys.BACK_SPACE).perform();
//		    lines = driver.findElements((linesBy));
//		}
//
//		actions.sendKeys(UpdateQuery).perform();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Execute']"))).click();
//		Thread.sleep(15000);
//		
//		driver.close();
//		driver.switchTo().window(MainWindow);
//	}
//	
//	
//	public void UpdateDealFeedbackExpiryDate(WebDriver driver,  ReportGenerator suit, String folder, String ID) throws Exception {
//		
//		WebDriverWait wait = new WebDriverWait(driver, 60);
//		
//		String MainWindow = driver.getWindowHandle();
//		
//		driver.get("https://gcpaexp--sit.my.salesforce.com/_ui/common/apex/debug/ApexCSIPage");
//		Thread.sleep(15000);
//		
//		//List<WebElement> Messagebox = driver.findElements(By.xpath("//span[text() ='OK']"));
//		
//		//if(Messagebox.size()!=0) {
//			//driver.findElement(By.xpath("//span[text() ='OK']")).click();
//		//}
//		
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Debug']"))).click();
//		Thread.sleep(2000);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Open Execute Anonymous Window']"))).click();
//		Thread.sleep(3000);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='openExternalEditorToolButton']"))).click();
//		Thread.sleep(3000);
//		
//		Set<String> WindowHandles = driver.getWindowHandles();
//		
//		Iterator<String> itr= WindowHandles.iterator();
//		
//		while(itr.hasNext()) {
//			String childWindow = itr.next();
//			if((!childWindow.equals(MainWindow))){
//				 driver.switchTo().window(childWindow);
//			}
//		}
//		
//		String UpdateQuery = "Deal_Feedback__c dfobj = [select calc_expr_dt__c from Deal_Feedback__c where Signing_Id__c ='"+ ID +"'];\r\n" + 
//				"date mydate = date.parse('06/20/2021');\r\n" + 
//				"dfobj.calc_expr_dt__c =mydate;\r\n" + 
//				"update dfobj;";
//		
//		Actions actions = new Actions(driver);
//
//		By linesBy = By.xpath("//*[@class='CodeMirror-code']//pre/span");    
//		List<WebElement> lines = driver.findElements(linesBy);
//
//		while (lines.size() > 0) {//deleting old Apex code    
//		    lines.get(0).click();
//		    actions.sendKeys(lines.get(0),Keys.chord(Keys.CONTROL, "A")).click().perform();
//		    actions.sendKeys(Keys.BACK_SPACE).perform();
//		    lines = driver.findElements((linesBy));
//		}
//
//		actions.sendKeys(UpdateQuery).perform();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Execute']"))).click();
//		Thread.sleep(15000);
//		
//		driver.close();
//		driver.switchTo().window(MainWindow);
//
//	}
//	
//	
//	
	int counter1 = 60;
	
	final public void WaitUntilElementIsVisibleAndClick(By element, WebDriver driver) {

		try {
			int i;
			for (i = 0; i < counter1; i++) {
				try {
					// WebElement e1
					 boolean flag= driver.findElement(element).isDisplayed();
					// boolean flag=e1.isDisplayed();
					if (flag) {
					driver.findElement(element).click();
						break;
					}
					 else {
						System.out.println("Waiting for element to be visible! " + i + " seconds :: " + element);
						waitFor(1);
					}

				} catch (Exception e) {

					System.err.println("****** Time taken ******** ");
					waitFor(1);
				}
			}

			if (i >= counter1) {
				System.err.println("Element is not displayed after " + i + " seconds element is :: " + element);

			}

		} catch (Exception e) {

			System.err.println("****** final ******** " + e);
			String className = this.getClass().getSimpleName();
			String functionName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
					+ ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");

		} 
	}
	

}