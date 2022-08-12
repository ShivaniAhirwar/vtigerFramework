package Practice;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import comcast.vtiger.genericUtility.File_Utility;
import comcast.vtiger.genericUtility.WebDriver_Utility;
import comcast.vtiger.objectRepository.LoginPage;

public class ScreenShot 
{


	public static void main(String[] args) throws Throwable 
    {
	   WebDriver driver =new ChromeDriver();
	   driver.get("http://localhost:8888/index.php");
	   WebDriver_Utility webLib=new WebDriver_Utility();
	   webLib.waitForPageLoad(driver);
	   
	TakesScreenshot ts=(TakesScreenshot) driver;
	File Ram = ts.getScreenshotAs(OutputType.FILE);
	 File dest = new File("./photo/vtiger.png");
	 FileUtils.copyFile(Ram, dest);
	   
	}
    
    
}
