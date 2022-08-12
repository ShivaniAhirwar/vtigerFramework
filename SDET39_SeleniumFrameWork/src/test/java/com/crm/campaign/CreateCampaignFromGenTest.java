package com.crm.campaign;

import java.io.FileInputStream;
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
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import comcast.vtiger.genericUtility.BaseClass;
import comcast.vtiger.genericUtility.Excel_utility;
import comcast.vtiger.genericUtility.File_Utility;
import comcast.vtiger.genericUtility.Java_Utility;
import comcast.vtiger.genericUtility.WebDriver_Utility;
import comcast.vtiger.objectRepository.AddCampaign;
import comcast.vtiger.objectRepository.CampaignPage;
import comcast.vtiger.objectRepository.CampaignValidation;
import comcast.vtiger.objectRepository.HomePage;
import comcast.vtiger.objectRepository.LoginPage;

@Listeners(comcast.vtiger.genericUtility.ListenerImplimentationClass.class)
public class CreateCampaignFromGenTest extends BaseClass
{
   
	@Test(groups={"Smoke Testing","Regression Testing"})
	public void createCampaign() throws Throwable
	{		 
		   
		   HomePage home=new HomePage(driver);
		   home.moreLink(driver);
		
		 home.campaignModule();
		
			CampaignPage addcampaign=new CampaignPage(driver);
			addcampaign.newCampaignPage();
			Java_Utility javaLib=new Java_Utility();
			int ran = javaLib.getRandomNum();
			
			  Excel_utility excelLib=new Excel_utility();
			 String campaignName = excelLib.getExcelValue("Sheet1",1,1)+ran;
			 AddCampaign createcampaign=new AddCampaign(driver);
			 createcampaign.campaignTextField(campaignName);
			 createcampaign.saveButton();
				
				CampaignValidation campVali=new CampaignValidation(driver);
				String actualTitleOrg = campVali.actualOrgData();
				Assert.assertEquals(actualTitleOrg.contains(campaignName),true);
			
		     home.imgForSignOut();
		     home.signOutLink();
				 driver.close();
		}
}
