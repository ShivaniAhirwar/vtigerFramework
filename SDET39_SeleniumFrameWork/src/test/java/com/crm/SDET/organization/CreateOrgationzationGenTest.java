package com.crm.SDET.organization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import comcast.vtiger.genericUtility.BaseClass;
import comcast.vtiger.genericUtility.Excel_utility;
import comcast.vtiger.genericUtility.File_Utility;
import comcast.vtiger.genericUtility.Java_Utility;
import comcast.vtiger.genericUtility.WebDriver_Utility;
import comcast.vtiger.objectRepository.CreateOrganization;
import comcast.vtiger.objectRepository.HomePage;
import comcast.vtiger.objectRepository.LoginPage;
import comcast.vtiger.objectRepository.OrganizationPage;
import comcast.vtiger.objectRepository.OrganizationValidation;

public class CreateOrgationzationGenTest extends BaseClass
{
	@Test(groups= {"Smoke Testing","Regression Testing"},retryAnalyzer=comcast.vtiger.genericUtility.RetryImpClass.class)
   public void createOrganization() throws Throwable 
   {
	   
		 HomePage home=new HomePage(driver);
		   home.organizationModule();
	   WebDriver_Utility webLib=new WebDriver_Utility();
	   
	   webLib.waitForPageLoad(driver);
	   Java_Utility ju=new Java_Utility();
	   int random = ju.getRandomNum();
	   
      Excel_utility eu=new Excel_utility();
      String excelData = eu.getExcelValue("Sheet1", 1, 2)+random;
	
	   OrganizationPage addOrg=new OrganizationPage(driver);
	   addOrg.organizationPageObjects();
	 
	   CreateOrganization createOrg=new CreateOrganization(driver);
	   createOrg.createOrg(excelData);
	   
	   OrganizationValidation orgTitle=new OrganizationValidation(driver);
	   String actualTitle = orgTitle.orgActualTitle();
	   Assert.assertEquals(actualTitle.contains(excelData),true);
	    home.imgForSignOut();
		   home.signOutLink();
	   driver.close();
   }
}
