package com.stifel.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.stifel.corefunctions.Browser_setup;
import com.stifel.corefunctions.Common_Functions;
import com.stifel.corefunctions.ReportGenerator;

public class CalendarPageObject extends Browser_setup{
	
	public void SelectListFromDropdown(WebDriver driver,String label,String Value,String event,ReportGenerator suit, String folder) throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        //driver.navigate().refresh();
        Thread.sleep(50000);
        WebElement we=driver.findElement(By.xpath("//div[@class='iframe-parent slds-template_iframe slds-card']//iframe"));
        driver.switchTo().frame(we);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@data-type='PICKLIST']//following-sibling::span[text()='Entity']")));
        Thread.sleep(3000);
        //driver.findElement(By.xpath("//div[@data-type='PICKLIST']//following-sibling::span[text()='Entity']")).click();
        WebElement wb=driver.findElement(By.xpath("//span[text()='"+label+"']//following-sibling::div//select[@name='value']"));
        Select sle=new Select(wb);
        sle.selectByValue(Value);
        Thread.sleep(3000);
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Apply']")));
        Thread.sleep(20000);
        int count=0;
        while(count<12)
        {
            try
            {
                js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='"+event+"']")));
                break;
            }
            catch(Throwable t)
            {
                driver.findElement(By.xpath("//span[@data-nav='l']//span")).click();
                count++;
            }
        }
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Details']")));
        Thread.sleep(10000);
        driver.switchTo().defaultContent();
        Thread.sleep(5000);
        String actual=driver.findElement(By.xpath("//span[text()='Entity']//parent::div//following-sibling::div//span//span")).getText();
        if(actual.equalsIgnoreCase(Value))
        {
           
            Common_Functions.ResultPass(driver, suit, folder, "Entity should match", "Entity match successfully", "Entity matched successfully");
        }
        else{
            Common_Functions.ResultFail(driver, suit, folder, "Entity should match", "Entity match successfully", "Entity matched successfully");
        }
    }
}
