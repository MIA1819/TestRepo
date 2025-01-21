package com.stifel.corefunctions;



import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//import com.amex.stif.Common_Functions;
//import com.amex.esoteric.ReportGenerator;
//import com.amex.esoteric.Utilities;

public class Login_Page {

	public static String userName_ID = "username";
	public static String userName_CSS = "[name='username']";
	public static String userName_xPath = "//input[@id='username']";

	public static String passWord_ID = "password";
	public static String passWord_CSS = "[type='password']";
	public static String passWord_xPath = "//input[@id='password']";

	public static String loginBtn_ID = "Login";
	public static String loginBtn_CSS = "[value='Log In to Sandbox']";
	public static String loginBtn_xPath = "//input[@id='Login']";
	public static String searchBox_ID = "phSearchInput";

	public static String SearchButton_ID = "phSearchButton";
	public static String SearchButton_CSS = "[value='Search']";
	public static String SearchButton_xPath = ".//*[@id='phSearchButton']";

	public static String ModeratorButton_ID = "moderatorMutton";
	public static String ModeratorButton_CSS = "moderatorMutton";
	public static String ModeratorButton_xPath = "//a[@id='moderatorMutton']";

	public static String UserDetail_ID = "USER_DETAIL";
	public static String UserDetail_CSS = "[title='User Detail']";
	public static String UserDetail_xPath = ".//*[@id='USER_DETAIL']/span";

	public static String UserLogin_ID = "";
	public static String UserLogin_CSS = "[title='Login']";
	public static String UserLogin_xPath = ".//*[@id='topButtonRow']/input[4]";

	public static String UserNameInTopBar_CSS = "#globalHeaderNameMink";
	public static String setupButton_CSS = "#globalHeaderBar > div.globalHeaderNameMenuContainer > div > div > div > ul > li:nth-child(2) > a";
	public static String setupSearchBar_CSS = "#setupSearch";
	public static String setupSearchButton_CSS = "#setupSearchButton";

	public static String accountNameValue_id = "acc2_ileinner";

	public static String UserContact_xPath = "//td[text()='Contact']/following-sibling::td/a";
	public static String MngExtUser_xPath = "//div[@id='workWithPortalButton']";
	public static String EMEALogin_xPath = "//td[@id='topButtonRow']//a[.='Log in to Community as User']";

	/*public static long test_step_unique_id;
	public static String userNamE, passWorD, URL, rembrTextToBeVerified;*/

	public static void salesforceLogin(WebDriver driver, String folder, ReportGenerator currentsuit, String sheetName, int sheetValue) throws Exception {
		long test_step_unique_id;
		String userNamE, passWorD, URL, rembrTextToBeVerified;

		verifyUserNameField(driver, currentsuit, folder, sheetName, sheetValue);
		verifyPasswordField(driver, currentsuit, folder, sheetName, sheetValue);
		verifySubmitButton(driver, currentsuit, folder, sheetName, sheetValue);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		Thread.sleep(20000);
	}

	public static boolean loadApplicationURL(WebDriver driver, String folder, ReportGenerator currentsuit, String sheetName, int i) throws Exception {
		boolean loginStatus = false;
		long test_step_unique_id;
		String userNamE, passWorD, rembrTextToBeVerified;
		String URL = Utilities.getParameterFromInputSheet(sheetName, "URL", i);
	//	Common_Functions.waitForLoad(driver, 200);

		loginStatus = Common_Functions.LoadApplication(URL, driver, userName_ID, 30, currentsuit, folder, "Sales Force Login page");
		if (!loginStatus) {

		} else {

		//	Common_Functions.waitForLoad(driver, 200);
		}
		return loginStatus;
	}

	// Verify the UserName field
	public static boolean verifyUserNameField(WebDriver driver, ReportGenerator currentSuit, String destination_Result, String sheetName, int i) throws Exception {
		boolean userNameExists = false;
		long test_step_unique_id;
		String userNamE, passWorD, URL, rembrTextToBeVerified;
		String userName = Utilities.getParameterFromInputSheet(sheetName, "userName", i);

		WebElement userNameObject = Common_Functions.RetrieveWebElementFromApplication(userName_ID, userName_CSS, userName_xPath, driver);
		userNameExists = Common_Functions.isDisplayed(userNameObject, driver, currentSuit, destination_Result, "Username field");
		if (!userNameExists) {

		} else {
			Common_Functions.EnterValues(userName, userNameObject, driver, currentSuit, destination_Result, "Username field");
		}
		return userNameExists;
	}

	// Verify the Password field
	public static void verifyPasswordField(WebDriver driver, ReportGenerator currentSuit, String destination_Result, String sheetName, int i) throws Exception {
		boolean passWordExists = false;
		long test_step_unique_id;
		String userNamE, passWorD, URL, rembrTextToBeVerified;
		String passWord = Utilities.getParameterFromInputSheet(sheetName, "passWord", i);

		WebElement passWordObject = Common_Functions.RetrieveWebElementFromApplication(passWord_ID, passWord_CSS, passWord_xPath, driver);
		passWordExists = Common_Functions.isDisplayed(passWordObject, driver, currentSuit, destination_Result, "Password field");

		if (!passWordExists) {

		} else {

			Common_Functions.EnterValues(passWord, passWordObject, driver, currentSuit, destination_Result, "Password field");
		}

	}

	public static void verifySubmitButton(WebDriver driver, ReportGenerator currentSuit, String destination_Result, String sheetName, int i) throws Exception {
		boolean submitButtonExists = false;
		long test_step_unique_id;
		String userNamE, passWorD, URL, rembrTextToBeVerified;
		// postLoginVerify = false;
		WebElement submitButton = Common_Functions.RetrieveWebElementFromApplication(loginBtn_ID, loginBtn_CSS, loginBtn_xPath, driver);
		submitButtonExists = Common_Functions.isDisplayed(submitButton, driver, currentSuit, destination_Result, "Log in to Salesforce button");
		if (!submitButtonExists) {
		} else {
			Common_Functions.Submit(submitButton, driver, currentSuit, destination_Result, "Log in to Salesforce button");
			//Common_Functions.waitForLoad(driver, 200);
			Thread.sleep(5000);
		}
	}

	public static void searchUser(WebDriver driver, ReportGenerator currentSuit, String folder, String sheetName, int sheetVal) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		long test_step_unique_id;
		String userNamE, passWorD, URL, rembrTextToBeVerified;

		String user = null;

		boolean searchBoxExists = false;
		WebElement searchBox = driver.findElement(By.id(searchBox_ID));
		searchBoxExists = Common_Functions.isDisplayed(searchBox, driver, currentSuit, folder, "Home Page Search Box");

		if (!searchBoxExists) {
		} else {
			user = Utilities.getParameterFromInputSheet(sheetName, "User", sheetVal);
			Thread.sleep(1000);
			Common_Functions.EnterValues(user, searchBox, driver, currentSuit, folder, "Home Page Search box field");
			Common_Functions.waitForLoad(driver, 80);
			Thread.sleep(1000);
		}

		boolean searchButtonExists = false;
		WebElement searchButton = Common_Functions.RetrieveWebElementFromApplication(SearchButton_ID, SearchButton_CSS, SearchButton_xPath, driver);
		searchButtonExists = Common_Functions.isDisplayed(searchButton, driver, currentSuit, folder, "Home Page Search Button");
		if (!searchButtonExists) {
		} else {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", searchButton);
			Common_Functions.waitForLoad(driver, 80);
		}
		String userVal = "//div[@class='displayName']//a[.='" + user + "']";
		WebElement userSearch = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(userVal)));

		userSearch.findElement(By.xpath(userVal)).click();
		Common_Functions.ResultPass(driver, currentSuit, folder, "Validate Whether the Search User is displayed", "Search User should be displayed", "Search User is displayed");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		// executor.executeScript("arguments[0].click();", userSearch);
		/*	} catch (Exception e) {
		}*/
		Common_Functions.waitForLoad(driver, 80);

		boolean userDropdownExists = false;
		WebElement userDropdownButton = Common_Functions.RetrieveWebElementFromApplication(ModeratorButton_ID, ModeratorButton_CSS, ModeratorButton_xPath, driver);
		userDropdownExists = Common_Functions.isDisplayed(userDropdownButton, driver, currentSuit, folder, "User Dropdown Button");
		if (!userDropdownExists) {
		} else {
			/*JavascriptExecutor executor2 = (JavascriptExecutor) driver;
			executor2.executeScript("arguments[0].click();", userDropdownButton);*/
			userDropdownButton.click();
			Common_Functions.waitForLoad(driver, 1000);
		}

		boolean userDetailsLinkExists = false;
		WebElement userDetailsLink = Common_Functions.RetrieveWebElementFromApplication(UserDetail_ID, UserDetail_CSS, UserDetail_xPath, driver);
		Common_Functions.waitForLoad(driver, 2500);
		userDetailsLinkExists = Common_Functions.isDisplayed(userDetailsLink, driver, currentSuit, folder, "User Detail Link");
		if (!userDetailsLinkExists) {
		} else {
			/*JavascriptExecutor executor2 = (JavascriptExecutor) driver;
			executor2.executeScript("arguments[0].click();", userDetailsLink);*/
			userDetailsLink.click();
			Common_Functions.waitForLoad(driver, 80);
		}
		/*	} catch (Exception e) {
			e.printStackTrace();
		}*/

		boolean loginButtonExists = false;
		WebElement loginButton = Common_Functions.RetrieveWebElementFromApplication(UserLogin_ID, UserLogin_CSS, UserLogin_xPath, driver);
		Common_Functions.waitForLoad(driver, 2500);
		loginButtonExists = Common_Functions.isDisplayed(loginButton, driver, currentSuit, folder, "Login Button");
		if (!loginButtonExists) {
		} else {
			JavascriptExecutor executor2 = (JavascriptExecutor) driver;
			executor2.executeScript("arguments[0].click();", loginButton);
			Common_Functions.waitForLoad(driver, 80);
		}
	}

	/**
	 * Searches a user through set up. Use this function when the other generic search functions aren't reliable on your
	 * system. This has been found to be reliable enough.
	 * @param driver
	 * @param currentSuit
	 * @param folder
	 * @param sheetName
	 * @param parameter
	 * @param sheetVal
	 * @throws Exception
	 */

	public static void searchUserThroughSetup(WebDriver driver, ReportGenerator currentSuit, String folder, String sheetName, String parameter, int sheetVal) throws Exception {
		WebElement currentUsername = driver.findElement(By.cssSelector(UserNameInTopBar_CSS));
		currentUsername.click();

		WebElement setupButton = driver.findElement(By.cssSelector(setupButton_CSS));
		setupButton.click();

		String userToSearch = Utilities.getParameterFromInputSheet(sheetName, parameter, sheetVal);
		WebElement setupSearchBar = driver.findElement(By.cssSelector(setupSearchBar_CSS));
		setupSearchBar.sendKeys(userToSearch);
		WebElement setupSearchButton = driver.findElement(By.cssSelector(setupSearchButton_CSS));
		setupSearchButton.click();

		Common_Functions.waitForUIElementByxPath("//*[@id=\"auraElement-1\"]/div/div[3]/div/h2", driver);

		Common_Functions.clickByLinkText(userToSearch, driver, currentSuit, folder, "Clicked username.");

		Common_Functions.waitForUIElementByxPath("//*[@id=\"bodyCell\"]/div[1]/div[1]/div[1]/h2", driver);

		WebElement loginButton = driver.findElement(By.cssSelector("#topButtonRow > input:nth-child(4)"));
		loginButton.click();

	}

	/**
	 * Searches for a user and if there are multiple results, select the one indicated by the parameter indexInResults
	 * @param driver
	 * @param sheetName
	 * @param indexInResults
	 * @param parameter
	 * @param sheetVal
	 * @throws Exception
	 */
	public static void searchUserThroughSetupByLink(WebDriver driver, String sheetName, int indexInResults, String parameter, int sheetVal) throws Exception {
		WebElement currentUsername = driver.findElement(By.cssSelector(UserNameInTopBar_CSS));
		currentUsername.click();

		WebElement setupButton = driver.findElement(By.cssSelector(setupButton_CSS));
		setupButton.click();

		String userToSearch = Utilities.getParameterFromInputSheet(sheetName, parameter, sheetVal);
		WebElement setupSearchBar = driver.findElement(By.cssSelector(setupSearchBar_CSS));
		setupSearchBar.sendKeys(userToSearch);
		WebElement setupSearchButton = driver.findElement(By.cssSelector(setupSearchBar_CSS));
		setupSearchButton.click();

		Common_Functions.waitForUIElementByxPath("//*[@id=\"auraElement-1\"]/div/div[3]/div/h2", driver);

		List<WebElement> matchingUsers = driver.findElements(By.linkText(userToSearch));
		if (!matchingUsers.isEmpty()) {
			if (matchingUsers.size() == 1) {
				matchingUsers.get(0).click();
			} else {
				matchingUsers.get(indexInResults).click();
			}
		}

		Common_Functions.waitForUIElementByxPath("//*[@id=\"bodyCell\"]/div[1]/div[1]/div[1]/h2", driver);

		WebElement loginButton = driver.findElement(By.cssSelector("#topButtonRow > input:nth-child(4)"));
		loginButton.click();
	}

	public static String navigateUsingID(WebDriver driver, ReportGenerator currentSuit, String folder, String oppSheetName, int sheetValue, String accountID) throws Exception {
		long test_step_unique_id;
		String userNamE, passWorD, URL, rembrTextToBeVerified;
		String url = driver.getCurrentUrl();
		String[] value = url.split("/");
		String newURL = value[0] + "//" + value[2] + "/" + accountID;
		driver.navigate().to(newURL);
		return driver.findElement(By.id(accountNameValue_id)).getText();
	}

	public static void navigateUsingID(WebDriver driver, ReportGenerator currentSuit, String oppSheetName, int sheetValue, String ID) throws Exception {
		long test_step_unique_id;
		String userNamE, passWorD, URL, rembrTextToBeVerified;
		String url = driver.getCurrentUrl();
		String[] value = url.split("/");
		String newURL = value[0] + "//" + value[2] + "/" + ID;
		driver.navigate().to(newURL);
	}

	public static void searchEMEAESAPartner(WebDriver driver, ReportGenerator currentSuit, String folder, String sheetName, int sheetVal) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 100);
		long test_step_unique_id;
		String userNamE, passWorD, URL, rembrTextToBeVerified;
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SFDCVariables.User_xPath))).click();
		//
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SFDCVariables.Setup_xPath))).click();
		// Common_Functions.waitForLoad(driver, 80);

		String user = null;

		boolean searchBoxExists = false;
		WebElement searchBox = driver.findElement(By.id(searchBox_ID));
		searchBoxExists = Common_Functions.isDisplayed(searchBox, driver, currentSuit, folder, "Home Page Search Box");

		if (!searchBoxExists) {
		} else {
			user = Utilities.getParameterFromInputSheet(sheetName, "User", sheetVal);
			Thread.sleep(1000);
			Common_Functions.EnterValues(user, searchBox, driver, currentSuit, folder, "Home Page Search box field");
			Common_Functions.waitForLoad(driver, 80);
			Thread.sleep(1000);
		}
		boolean searchButtonExists = false;
		WebElement searchButton = Common_Functions.RetrieveWebElementFromApplication(SearchButton_ID, SearchButton_CSS, SearchButton_xPath, driver);
		searchButtonExists = Common_Functions.isDisplayed(searchButton, driver, currentSuit, folder, "Home Page Search Button");
		if (!searchButtonExists) {
		} else {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", searchButton);
			Common_Functions.waitForLoad(driver, 80);
		}
		String userVal = "//a[text()='" + user + "']";
		WebElement userSearch = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(userVal)));

		userSearch.findElement(By.xpath(userVal)).click();
		Common_Functions.ResultPass(driver, currentSuit, folder, "Validate Whether the Search User is displayed", "Search User should be displayed", "Search User is displayed");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", userSearch);

		Common_Functions.waitForLoad(driver, 100);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UserContact_xPath))).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MngExtUser_xPath))).click();
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EMEALogin_xPath))).click();
		/*	}	catch (Exception e) {
			e.printStackTrace();
		}
		 */
	}

}
