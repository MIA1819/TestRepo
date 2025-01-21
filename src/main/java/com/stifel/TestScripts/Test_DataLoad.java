package com.stifel.TestScripts;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.stifel.LEXCommons.SF_StandardLEX_Common_Navigations;
import com.stifel.LEXCommons.SF_StandardLEX_WebElementLocators;
import com.stifel.corefunctions.Browser_setup;
import com.stifel.corefunctions.Common_Functions;
import com.stifel.corefunctions.Login_Page;
import com.stifel.corefunctions.Utilities;

public class Test_DataLoad extends Browser_setup{
	
	@Test(groups = "Test_DataLoad", enabled = true)
	public void Test_DataLoad() throws Exception{
		
		try {
			SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
			setThreadDataSheetName("TestData_Stifel.xls");
			setTheTestForRun();
			
			String User = null;
			
			// Login to SalesForce
			Login_Page.loadApplicationURL(driver, folder, suit, "Login", 3);
			Login_Page.salesforceLogin(driver, folder,suit, "Login", 3);
			
			Thread.sleep(10000);
			
			String HomepageURL = driver.getCurrentUrl();
			
			//Open User Details page
			
			HashMap<String, String> datafromexcel = new HashMap<String, String>();
			
			
			
			int i=1;	
			
			while(Utilities.getParameterFromInputSheet("MF User Load Template", "FirstName", i)!=null || !Utilities.getParameterFromInputSheet("MF User Load Template", "FirstName", i).isEmpty()) {
				
				
			datafromexcel.put("Name", Utilities.getParameterFromInputSheet("MF User Load Template", "FirstName", i)+" "+Utilities.getParameterFromInputSheet("MF User Load Template", "LastName", i));
			datafromexcel.put("Email", Utilities.getParameterFromInputSheet("MF User Load Template", "Email", i));
			datafromexcel.put("Title", Utilities.getParameterFromInputSheet("MF User Load Template", "Title", i));
			datafromexcel.put("EmployeeNumber", "000"+Utilities.getParameterFromInputSheet("MF User Load Template", "EmployeeNumber", i));
			datafromexcel.put("Federation Identifier", "000"+Utilities.getParameterFromInputSheet("MF User Load Template", "EmployeeNumber", i));
			datafromexcel.put("Profile Name", Utilities.getParameterFromInputSheet("MF User Load Template", "Profile Name", i));
			datafromexcel.put("Role Name", Utilities.getParameterFromInputSheet("MF User Load Template", "Role Name", i));
			datafromexcel.put("Location", Utilities.getParameterFromInputSheet("MF User Load Template", "Location", i));
			datafromexcel.put("Time Zone", Utilities.getParameterFromInputSheet("MF User Load Template", "Time Zone", i));
			datafromexcel.put("EmailEncodingKey", Utilities.getParameterFromInputSheet("MF User Load Template", "EmailEncodingKey", i));
			datafromexcel.put("LanguageLocaleKey", Utilities.getParameterFromInputSheet("MF User Load Template", "LanguageLocaleKey", i));
			datafromexcel.put("LocaleSidKey", Utilities.getParameterFromInputSheet("MF User Load Template", "LocaleSidKey", i));
			datafromexcel.put("Alias", Utilities.getParameterFromInputSheet("MF User Load Template", "Alias", i));
			datafromexcel.put("BusinessProcessRole", Utilities.getParameterFromInputSheet("MF User Load Template", "Business_Process_Role__c", i));
			datafromexcel.put("Username", Utilities.getParameterFromInputSheet("MF User Load Template", "Username", i));
			datafromexcel.put("CompanyName", Utilities.getParameterFromInputSheet("MF User Load Template", "CompanyName", i));
			datafromexcel.put("UserPreference", Utilities.getParameterFromInputSheet("MF User Load Template", "User_Preferences__c", i));
			datafromexcel.put("Org level", Utilities.getParameterFromInputSheet("MF User Load Template", "Org level", i));
			datafromexcel.put("Marketing User", Utilities.getParameterFromInputSheet("MF User Load Template", "Marketing User", i).replace("TRUE", "Checked"));
			datafromexcel.put("Service Cloud User", Utilities.getParameterFromInputSheet("MF User Load Template", "Service Cloud User", i).replace("TRUE", "Checked"));
			datafromexcel.put("IsActive", Utilities.getParameterFromInputSheet("MF User Load Template", "IsActive", i).replace("TRUE", "Checked"));
			
			
			i++;
			
			User = datafromexcel.get("Name");
			
			
			cn.OpenUserDetailPage(driver, suit, folder, User);
			
			cn.CaptureDetailsfromUserandCompare(driver, suit, folder, datafromexcel);
			
			driver.get(HomepageURL);
			Thread.sleep(10000);
			
			cn.ContactValidationDataLoad(driver, suit, folder, User, datafromexcel);
			
			driver.get(HomepageURL);
			
			datafromexcel.clear();
			
			System.out.println("Successfully validated for:"+User);
			System.out.println("=========================================================================================");
			}
		}
		
		catch (Exception e) 
		{
		    String className = this.getClass().getSimpleName();
			String functionName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
					+ ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
			Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName,
					"failed");
		}
	}
}
