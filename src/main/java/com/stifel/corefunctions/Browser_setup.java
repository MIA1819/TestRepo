
package com.stifel.corefunctions;

//import com.saucelabs.common.SauceOnDemandAuthentication;
//import com.saucelabs.common.SauceOnDemandSessionIdProvider;
//import com.saucelabs.saucerest.SauceREST;
//import com.saucelabs.testng.SauceOnDemandAuthenticationProvider;
//import com.saucelabs.testng.SauceOnDemandTestListener;

//import bsh.This;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

//@Listeners({ SauceOnDemandTestListener.class })
public class Browser_setup {//implements SauceOnDemandSessionIdProvider, SauceOnDemandAuthenticationProvider { //Ankith Changes
	public String username;
	public String accesskey;
	//public SauceOnDemandAuthentication authentication;
	//private SauceREST client;
	public Date start;
	public String browser_Specification;
	public String run_Platform;
	public String functionSuit;
	public String folder_Screen;
	public String browser_Target;
	public String browser_Resolutn;
	public String screen_Header;
	public String folder;
	public String app_Name_Final;
	public String proj_Name_Final;
	public String script_View;
	public String browser_version;
	public String running_os;
	public String module_Pack;
	public String testCaseID;
	public String status1;
	public String platform_Status;
	public String deviceID;
	public ReportGenerator suit;
	public WebDriver driver;
	int x;
	int y;
	int stepCount;
	private Dimension size;
	protected ThreadLocal<RemoteWebDriver> threadDriver;
	protected ThreadLocal<WebDriver> threadWebDriver ;


	private static FrameworkParameters frameworkParameters = FrameworkParameters.getInstance();
	private ThreadLocal<String> sessionId;

	public Browser_setup() {  

		this.size = null;
		//this.threadDriver = null;
		this.threadWebDriver = null;

		this.sessionId = new ThreadLocal();
	}

	
	protected static ThreadLocal<String> threadDataSheetName = new ThreadLocal() {
		protected String initialValue() {
			return new String("Data.xls");
		}
	};
	
	
	
	
	public static synchronized String getThreadDataSheetName() {
		return ((String) threadDataSheetName.get());
	}

	public void setThreadDataSheetName(String testDataSheetName) {
		threadDataSheetName.set(testDataSheetName);
	}

	@BeforeClass(alwaysRun = true)
	@Parameters({ "Suit", "Browser", "testCaseId"})
	public void openBroswer(String suit ,String browser, @Optional("testcase") String testCaseId
			) throws IOException {
		this.browser_Specification = browser;
		this.functionSuit = suit;
		this.testCaseID = testCaseId;
	
		
//		if (this.browser_Target.equals("WebDriver"))
//			this.browser_Target = "WebDriver";
	}

	public String getBrowser_Specification() {
		return this.browser_Specification;
	}

	public void setBrowser_Specification(String browser_Specification) {
		this.browser_Specification = browser_Specification;
	}

	public String getRun_Platform() {
		return this.run_Platform;
	}

	public void setRun_Platform(String run_Platform) {
		this.run_Platform = run_Platform;
	}

	

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws IOException, InterruptedException {
		try {
//			boolean screenShotStatus = true;
//			boolean videoStatus = true;
//			DesiredCapabilities dc = new DesiredCapabilities();
//			this.threadWebDriver = new ThreadLocal();
//			this.threadDriver = new ThreadLocal();
			this.browser_Specification="chrome";
			String path = System.getProperty("user.dir");   
			String ChromeDriverPathLocal = path + "\\driverfolder\\chromedriver.exe"; 
			System.out.println(ChromeDriverPathLocal);
			System.out.println("browser specs: "+this.browser_Specification);

				if (this.browser_Specification.equalsIgnoreCase("chrome")) {
					System.out.println("Browser Selected : chrome \nin PC " + this.functionSuit);
						ChromeOptions options = new ChromeOptions();
						options.addArguments(new String[] { "test-type" });
						options.addArguments(new String[] { "disable-popup-blocking" });
						options.addArguments(new String[] { "--disable-extensions" });
						options.addArguments("--disable-notifications");	
						options.addArguments("start-maximized");
						options.setCapability("chromeOptions", options);
					    options.setCapability("screenResolution","1920x1080"); 

						if (!ChromeDriverPathLocal.isEmpty()) {
							System.setProperty("webdriver.chrome.driver", ChromeDriverPathLocal);
							this.threadWebDriver = new ThreadLocal<WebDriver>();
						}
						this.threadWebDriver.set(new ChromeDriver(options));
					} 
				
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}	
	
	
	//Set Zoom of browser to desired level
	
	public void browserzoom(WebDriver driver, int zoompercentage)
	{
	    JavascriptExecutor jse = (JavascriptExecutor)driver;
	    jse.executeScript("document.body.style.zoom='"+zoompercentage+"%';");
	}
	
	
	//@BeforeClass(alwaysRun = true)
//	public void setUp1() throws IOException, InterruptedException {
//		try {
//			boolean screenShotStatus = true;
//			boolean videoStatus = true;
//			DesiredCapabilities dc = new DesiredCapabilities();
//			this.threadWebDriver = new ThreadLocal();
//			this.threadDriver = new ThreadLocal();
//			GetDataFromRunsheet getData = new GetDataFromRunsheet();
//			String IEDriverPathLocal = getData.getDetails("IEDriverPath");
//			String ChromeDriverPathLocal = getData.getDetails("ChromeDriverPath");
//			if (StringUtils.equalsIgnoreCase(getData.getDetails("TakeScreenShot"), "Yes"))
//				screenShotStatus = true;
//			else {
//				screenShotStatus = false;
//			}
//			if (StringUtils.equalsIgnoreCase(getData.getDetails("RecordVideoOfExecution"), "Yes"))
//				videoStatus = true;
//			else {
//				videoStatus = false;
//			}
//
//			if (this.run_Platform.equalsIgnoreCase("PC")) {
//				this.platform_Status = "PC";
//				if (this.browser_Specification.equalsIgnoreCase("Firefox")) {
//					System.out.println("Browser Selected : Firefox \nin PC " + this.functionSuit);
//					if (this.browser_Target.equals("WebDriver")) {
//						this.threadWebDriver.set(new FirefoxDriver());
//					} else {
//						dc.setBrowserName(this.browser_Specification);
//						dc.setCapability("version", this.browser_version);
//						dc.setCapability("platform", this.running_os);
//						dc.setCapability("name", this.functionSuit);
//
//						dc.setCapability("tags", this.testCaseID);
//						dc.setCapability("recordScreenshots", screenShotStatus);
//						dc.setCapability("recordVideo", videoStatus);
//						dc.setCapability("recordLogs", false);
////						this.threadDriver.set(new RemoteWebDriver(
////								new URL("http://" + this.authentication.getUsername() + ":"
////										+ this.authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"),
////								dc));
//
//						this.sessionId.set(((RemoteWebDriver) this.threadDriver.get()).getSessionId().toString());
//					}
//				}
//
//				if (this.browser_Specification.equalsIgnoreCase("internet Explorer")) {
//					System.out.println("Browser Selected : internet Explorer \nin PC " + this.functionSuit);
//					if (this.browser_Target.equals("WebDriver")) {
//						dc.setCapability("ignoreProtectedModeSettings", true);
//						dc.setCapability("ignoreZoomSetting", true);
//						if (StringUtils.isNotEmpty(IEDriverPathLocal)) {
//							System.setProperty("webdriver.ie.driver", IEDriverPathLocal);
//						}
//						this.threadWebDriver.set(new InternetExplorerDriver(dc));
//					} else {
//						dc.setBrowserName(this.browser_Specification);
//						dc.setCapability("version", this.browser_version);
//						dc.setCapability("platform", this.running_os);
//						dc.setCapability("name", this.functionSuit);
//
//						dc.setCapability("tags", this.testCaseID);
//						dc.setCapability("recordScreenshots", screenShotStatus);
//						dc.setCapability("recordVideo", videoStatus);
//						dc.setCapability("recordLogs", false);
////						this.threadDriver.set(new RemoteWebDriver(
////								new URL("http://" + this.authentication.getUsername() + ":"
////										+ this.authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"),
////								dc));
//
//						this.sessionId.set(((RemoteWebDriver) this.threadDriver.get()).getSessionId().toString());
//					}
//				}
//
//				if (this.browser_Specification.equalsIgnoreCase("chrome")) {
//					System.out.println("Browser Selected : chrome \nin PC " + this.functionSuit);
//					if (this.browser_Target.equals("WebDriver")) {
//						ChromeOptions options = new ChromeOptions();
//						options.addArguments(new String[] { "test-type" });
//						options.addArguments(new String[] { "disable-popup-blocking" });
//						options.addArguments(new String[] { "--disable-extensions" });
//						options.addArguments("--disable-notifications");
//						//options.addArguments("--window-size=1920,1080");  //1920x1080
//						DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//						capabilities.setCapability("chromeOptions", options);
//					//	capabilities.setCapability("screenResolution","1920x1080"); //Ankith changes
//
//						if (StringUtils.isNotEmpty(ChromeDriverPathLocal)) {
//							System.setProperty("webdriver.chrome.driver", ChromeDriverPathLocal);
//						}
//						this.threadWebDriver.set(new ChromeDriver(capabilities));
//					} else {
//						ChromeOptions options = new ChromeOptions();
//						options.addArguments("--disable-notifications");
//						dc.setBrowserName(this.browser_Specification);
//						dc.setCapability("version", this.browser_version);
//						dc.setCapability("platform", this.running_os);
//						dc.setCapability("name", this.functionSuit);
//
//						dc.setCapability("tags", this.testCaseID);
//						dc.setCapability("recordScreenshots", screenShotStatus);
//						dc.setCapability("recordVideo", videoStatus);
//						dc.setCapability("recordLogs", false);
//						dc.setCapability("screenResolution", this.browser_Resolutn); // Ankith Changes 1440x900 
//						dc.setCapability(ChromeOptions.CAPABILITY, options); 
////						this.threadDriver.set(new RemoteWebDriver(
//////								new URL("http://" + this.authentication.getUsername() + ":"
//////										+ this.authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"),
//////								dc));
//////						
//						System.out.println("setting browser width");
//
//						this.sessionId.set(((RemoteWebDriver) this.threadDriver.get()).getSessionId().toString());
//					}
//				}
//			} else if (this.run_Platform.equalsIgnoreCase("Mobile")) {
//				this.platform_Status = "Mobile";
//				if (this.browser_Specification.equalsIgnoreCase("Firefox")) {
//					System.out.println("Browser Selected : Firefox");
//					dc.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
//					if (this.browser_Target.equals("WebDriver"))
//						this.threadWebDriver.set(new FirefoxDriver());
//					else {
//						this.threadDriver
//								.set(new RemoteWebDriver(new URL("http://" + this.browser_Target + "/wd/hub"), dc));
//					}
//				}
//
//				if (this.browser_Specification.equalsIgnoreCase("ie")) {
//					System.out.println("Browser Selected : ie");
//					dc.setBrowserName(DesiredCapabilities.internetExplorer().getBrowserName());
//					if (this.browser_Target.equals("WebDriver"))
//						this.threadWebDriver.set(new InternetExplorerDriver());
//					else {
//						this.threadDriver
//								.set(new RemoteWebDriver(new URL("http://" + this.browser_Target + "/wd/hub"), dc));
//					}
//				}
//
//				if (this.browser_Specification.equalsIgnoreCase("chrome")) {
//					System.out.println("Browser Selected : chrome");
//					dc.setBrowserName(DesiredCapabilities.chrome().getBrowserName());
//					if (this.browser_Target.equals("WebDriver"))
//						this.threadWebDriver.set(new ChromeDriver());
//					else {
//						this.threadDriver
//								.set(new RemoteWebDriver(new URL("http://" + this.browser_Target + "/wd/hub"), dc));
//					}
//				}
//				System.out.println("in Mobile " + this.functionSuit);
//				if (this.browser_Resolutn.equalsIgnoreCase("Default")) {
//					this.x = 360;
//					this.y = 660;
//				} else {
//					this.x = Integer.parseInt(this.browser_Resolutn.split("x")[0]);
//					this.y = Integer.parseInt(this.browser_Resolutn.split("x")[1]);
//				}
//
//				ManageWindowSize();
//			} else if (this.run_Platform.equalsIgnoreCase("TAB")) {
//				this.platform_Status = "TAB";
//				if (this.browser_Specification.equalsIgnoreCase("Firefox")) {
//					System.out.println("Browser Selected : Firefox");
//					dc.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
//					if (this.browser_Target.equals("WebDriver"))
//						this.threadWebDriver.set(new FirefoxDriver());
//					else {
//						this.threadDriver
//								.set(new RemoteWebDriver(new URL("http://" + this.browser_Target + "/wd/hub"), dc));
//					}
//				}
//				if (this.browser_Specification.equalsIgnoreCase("ie")) {
//					System.out.println("Browser Selected : ie");
//					dc.setBrowserName(DesiredCapabilities.internetExplorer().getBrowserName());
//					if (this.browser_Target.equals("WebDriver"))
//						this.threadWebDriver.set(new InternetExplorerDriver());
//					else {
//						this.threadDriver
//								.set(new RemoteWebDriver(new URL("http://" + this.browser_Target + "/wd/hub"), dc));
//					}
//				}
//				if (this.browser_Specification.equalsIgnoreCase("chrome")) {
//					System.out.println("Browser Selected : chrome");
//					dc.setBrowserName(DesiredCapabilities.chrome().getBrowserName());
//					if (this.browser_Target.equals("WebDriver"))
//						this.threadWebDriver.set(new ChromeDriver());
//					else {
//						this.threadDriver
//								.set(new RemoteWebDriver(new URL("http://" + this.browser_Target + "/wd/hub"), dc));
//					}
//				}
//				System.out.println("in TAB " + this.functionSuit);
//				if (this.browser_Resolutn.equalsIgnoreCase("Default")) {
//					this.x = 1024;
//					this.y = 527;
//				} else {
//					this.x = Integer.parseInt(this.browser_Resolutn.split("x")[0]);
//					this.y = Integer.parseInt(this.browser_Resolutn.split("x")[1]);
//				}
//				ManageWindowSize();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	//	}

	public WebDriver getDriver() {
		try {
			if (this.threadDriver.get() != null) {
				return ((WebDriver) this.threadDriver.get());
			}
			return ((WebDriver) this.threadWebDriver.get());
		} catch (Exception e) {
		}
		return ((WebDriver) this.threadWebDriver.get());
	}
//
	public WebDriver ManageWindowSize() {
		this.size = new Dimension(this.x, this.y);
		getDriver().manage().window().setSize(this.size);
		return getDriver();
	}
//
	public void setTheTestForRun() {
		this.driver = getDriver();
		this.driver.manage().window().maximize();
		this.suit = new ReportGenerator();
		try {
			try {
				this.app_Name_Final = " Test Applications";
				this.proj_Name_Final = "QA Automation";
			} catch (Exception newEx) {
				this.app_Name_Final = " Test Applications";
				this.proj_Name_Final = "QA Automation";
			}

			this.folder_Screen = getRun_Platform() + "_" + getBrowser_Specification();
			Thread.sleep(50L);
			this.screen_Header = this.suit.writeReportHeader(this.proj_Name_Final, this.app_Name_Final,
					"Test_Release", this.folder_Screen, this.functionSuit);

			this.folder = this.suit.writeSubHeader("Test run result for " + this.functionSuit,
					this.screen_Header);
		} catch (Exception E) {
		}
	}
//	
//	
//
	public String getSessionId() {
		return ((String) this.sessionId.get());
	}
	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() {
		if ((this.suit.getFailStp() == 0)
				&& (this.suit.getPassStp() == 0)) {
			System.out.println(this.functionSuit + " - Pass/Fail Count is 0");
			frameworkParameters.setOverallNoRun(frameworkParameters
					.getOverallNoRun() + 1);
		} else if (this.suit.getFailStp() > 0) {
			System.out.println(this.functionSuit + " - Failed");
			frameworkParameters.setOverallFail(frameworkParameters
					.getOverallFail() + 1);
		} else {
			System.out.println(this.functionSuit + " - Passed");
			frameworkParameters.setOverallPass(frameworkParameters
					.getOverallPass() + 1);
		}
		try {
			getDriver().quit();
		} finally {
			getDriver().quit();
		}
	}

//	public SauceOnDemandAuthentication getAuthentication() {
//		return this.authentication;
//	}
	
//	@BeforeClass(alwaysRun = true)
//	@Parameters({ "browser", "platform","suit", "Target", "Resolution", "os", "version"
//		})
//	public void openBroswer(String browser, String platform,String suit,
//			String Target, String Resolution, String os, String version, String testCaseId
//			) throws IOException {
//		this.browser_Specification = browser;
//		this.run_Platform = platform;
//		this.functionSuit = suit;
//     	this.browser_Target = Target;
//    	this.browser_Resolutn = Resolution;
//		this.browser_version = version;
//		this.running_os = os;
//		this.testCaseID = testCaseId;
//	
//		
//		if (this.browser_Target.equals("WebDriver"))
//			this.browser_Target = "WebDriver";
//	}
}