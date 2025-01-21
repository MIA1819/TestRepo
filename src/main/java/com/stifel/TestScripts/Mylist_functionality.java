package com.stifel.TestScripts;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.annotations.Test;

import com.stifel.LEXCommons.SF_StandardLEX_Common_Navigations;
import com.stifel.PageObjects.Companies;
import com.stifel.PageObjects.Mylist;
import com.stifel.PageObjects.SecurityMaster_Interest;
import com.stifel.corefunctions.Browser_setup;
import com.stifel.corefunctions.Common_Functions;
import com.stifel.corefunctions.Login_Page;
import com.stifel.corefunctions.Utilities;

public class Mylist_functionality extends Browser_setup

{
		@Test(groups = "MylistCreateDelete", enabled = true)
		public void MylistCreateDelete() throws Exception {
			try{	
			SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
			Mylist ml = new Mylist();
			setThreadDataSheetName("TestData_Stifel.xls");
			setTheTestForRun();

			// Login to salesforce
			Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
			Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
			//ml.ClickMyListsTab(driver, suit, folder);
			cn.clickonObjectTab(driver, suit, folder, "My Lists");
			ml.CreateList(driver, "Static list", 3, suit, folder,"MyList",1);
			//ml.Sharethelist(driver, "Vaishali Jain", "edit", suit, folder);
			ml.deletelist(driver,  suit, folder);
			


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
		
		
		
		@Test(groups = "StaticListShare", enabled = true)
		public void StaticListShare() throws Exception {
			try{	
			SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
			Mylist ml = new Mylist();
			setThreadDataSheetName("TestData_Stifel.xls");
			setTheTestForRun();

			// Login to salesforce
			Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
			Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
			ml.ClickMyListsTab(driver, suit, folder);
			String listname = ml.CreateList(driver, "Static list", 3, suit, folder,"MyList",1);
			ml.Sharethelist(driver, "Priyanka Dhongade", "edit", suit, folder);
			cn.LoginasUser(driver, suit, folder, "Priyanka Dhongade");
			ml.ClickMyListsTab(driver, suit, folder);
			ml.ClickonExistingList(driver, "Shared Lists", listname, suit, folder); 



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

		@Test(groups = "multipleInteractionsOnStaticList", enabled = true)
		public void multipleInteractionsOnStaticList() throws Exception {
			try{	
			SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
			Mylist ml = new Mylist();
			setThreadDataSheetName("TestData_Stifel.xls");
			setTheTestForRun();

			// Login to salesforce
			Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
			Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
			ml.ClickMyListsTab(driver, suit, folder);
			ml.CreateList(driver, "Static list", 3, suit, folder,"MyList",2);
		//	ml.Sharethelist(driver, "Vaishali Jain", "edit", suit, folder);
			ml.logMultipleInteractions(driver, 3, suit, folder, "MyList", 2);  

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
		
		
		@Test(groups = "SmartList", enabled = true)
		public void SmartList() throws Exception {
			try{	
			SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
			Mylist ml = new Mylist();
			setThreadDataSheetName("TestData_Stifel.xls");
			setTheTestForRun();

			// Login to salesforce
			Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
			Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
			ml.ClickMyListsTab(driver, suit, folder);
			ml.createSmartList(driver, "Static list", 2, suit, folder,"MyList",3);
			ml.shareTheSmartList(driver, "Priyanka Dhongade", "read", suit, folder); 

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
		
		
		@Test(groups = "EuroCanadaResearchServiceLevel", enabled = true)
		public void EuroCanadaResearchServiceLevel() throws Exception {
			try{	
			SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
			Mylist ml = new Mylist();
			setThreadDataSheetName("TestData_Stifel.xls");
			setTheTestForRun();

			// Login to salesforce
			Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
			Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
			
			ml.ClickMyListsTab(driver, suit, folder);
			ml.ValidateEuroCanadaResearchServiceLevel(driver, suit, folder);
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
		
		
		@Test(groups = "MylistCanadianTiersFilter", enabled = true)
        public void MylistCanadianTiersFilter() throws Exception {
            try{    
            SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
            Mylist ml = new Mylist();
            
            setThreadDataSheetName("TestData_Stifel.xls");
            setTheTestForRun();

 

            // Login to salesforce
            Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
            Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
            ml.ClickMyListsTab(driver, suit, folder);


            //Validate CanadianTiers Filter
            ml.ValidateCanadianTiersFilter(driver, suit, folder);
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
		
		
		@Test(groups = "NYSF-2212_MylistFilterBy", enabled = true)
        public void MylistFilterValidation() throws Exception {
            try{    
            SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
            Mylist ml = new Mylist();
            setThreadDataSheetName("TestData_Stifel.xls");
            setTheTestForRun();

 

            // Login to salesforce
            Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
            Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
            //ml.ClickMyListsTab(driver, suit, folder);
            cn.clickonObjectTab(driver, suit, folder, "My Lists");
            //ml.CreateList(driver, "Static list", 3, suit, folder,"MyList",1);
            String listname = ml.CreateList(driver, "Static list", 3, suit, folder,"MyList",1);
            ml.Sharethelist(driver, "Priyanka Dhongade", "edit", suit, folder);
            cn.LoginasUser(driver, suit, folder, "Priyanka Dhongade");
            ml.ClickMyListsTab(driver, suit, folder);
            //ml.ClickonExistingList(driver, "Shared Lists", listname, suit, folder); 
            ml.ValidateFilterByStaticListCount(driver, suit, folder);
            ml.ValidateAllStaticListOnFilterByStaticList(driver, suit, folder);
            ml.ValidateAllSharedListOnFilterByStaticList(driver, suit, folder);
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
		
		
		
		
		@Test(groups = "NYSF-2212_MylistFilterByAddFilter", enabled = true)
        public void MylistFilterByAddFilterValidation() throws Exception {
            try{    
            SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
            Mylist ml = new Mylist();
            setThreadDataSheetName("TestData_Stifel.xls");
            setTheTestForRun();

 

            // Login to salesforce
            Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
            Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
            //ml.ClickMyListsTab(driver, suit, folder);
            cn.clickonObjectTab(driver, suit, folder, "My Lists");
            //ml.CreateList(driver, "Static list", 3, suit, folder,"MyList",1);
            String listname = ml.CreateList(driver, "Static list", 3, suit, folder,"MyList",1);
            ml.ValidateNumberOfSubscriptionInStaticList(driver, suit, folder, listname);
            ml.VerifyAddFilterTag(driver, suit, folder);
            ml.SaveandVerifyFilter(driver, suit, folder, listname);
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
		
		
		@Test(groups = "NYSF-2584_NewFilterOnMylist", enabled = true)
        public void MylistNewFilterBValidation() throws Exception {
            try{    
            SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
            Mylist ml = new Mylist();
            setThreadDataSheetName("TestData_Stifel.xls");
            setTheTestForRun();
 

            // Login to salesforce
            Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
            Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
            //ml.ClickMyListsTab(driver, suit, folder);
            cn.clickonObjectTab(driver, suit, folder, "My Lists");
            
            //Apply Filter and Verify
            ml.newFilterCompanyRecordTypeValidation(driver, suit, folder);
            ml.newFilterSponsoredResearchOptOutValidation(driver, suit, folder);
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
		
		
		
		@Test(groups = "NYSF-1763_RegionsValidation", enabled = true)
        public void allRegionsValidation() throws Exception {
            try{    
            SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
            Mylist ml = new Mylist();
            setThreadDataSheetName("TestData_Stifel.xls");
            setTheTestForRun();

 

            // Login to salesforce
            Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
            Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
            ml.ClickMyListsTab(driver, suit, folder);
            ml.verifyRegions(driver, suit, folder, "France", "Regions");
            ml.verifyRegions(driver, suit, folder, "Benelux", "Regions");
            ml.verifyRegions(driver, suit, folder, "Germany", "Regions");
            ml.verifyRegions(driver, suit, folder, "Iberia", "Regions");
            ml.verifyRegions(driver, suit, folder, "Italy", "Regions");
            ml.verifyRegions(driver, suit, folder, "Nordics", "Regions");
            ml.verifyRegions(driver, suit, folder, "Switzerland", "Regions");
            ml.verifyRegions(driver, suit, folder, "United Kingdom", "Regions");
                       
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
		
		
		@Test(groups = "NYSF-3195_MyListSharedGroupValidation", enabled = true)
        public void MyListSharedGroupValidation() throws Exception {
            try{    
            SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
            Mylist ml = new Mylist();
            setThreadDataSheetName("TestData_Stifel.xls");
            setTheTestForRun();


            // Login to salesforce
            Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
            Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
            //ml.ClickMyListsTab(driver, suit, folder);
            cn.clickonObjectTab(driver, suit, folder, "My Lists");
            //ml.CreateList(driver, "Static list", 3, suit, folder,"MyList",1);
            String listname = ml.CreateList(driver, "Static list", 3, suit, folder,"MyList",1);
            cn.SearchandClickObjectTab(driver, suit, folder, "Contact Lists"); 
            ml.SelectContactList(driver, listname);
            ml.shareListWithGroup(driver, "TestDummyPublicGroup");
            
          //Login as a user
            String user = Utilities.getParameterFromInputSheet("Coverage", "Account Opening User", 8);
            cn.LoginasUser(driver, suit, folder, user);
            cn.clickonObjectTab(driver, suit, folder, "My Lists");
            ml.verifySharedListWithGroup(driver, suit, folder, listname);
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


