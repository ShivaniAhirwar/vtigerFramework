package com.crm.SDET.product;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import comcast.vtiger.genericUtility.BaseClass;
import comcast.vtiger.genericUtility.Excel_utility;
import comcast.vtiger.genericUtility.File_Utility;
import comcast.vtiger.genericUtility.Java_Utility;
import comcast.vtiger.genericUtility.WebDriver_Utility;
import comcast.vtiger.objectRepository.CreateProduct;
import comcast.vtiger.objectRepository.DeleteCreatedProduct;
import comcast.vtiger.objectRepository.HomePage;
import comcast.vtiger.objectRepository.LoginPage;
import comcast.vtiger.objectRepository.ProductPage;

public class CreateDeleteProductGenTest extends BaseClass
{
	@Test(groups= {"Smoke Testing","Regression Testing"})
	 public void createDeletePro() throws Throwable 
	   {
		   
		 HomePage home=new HomePage(driver);
		 home.productModule();
		 
		   Java_Utility javaLib=new Java_Utility();
		   int random = javaLib.getRandomNum();
		   
		   Excel_utility excelLib=new Excel_utility();
		   String excelData = excelLib.getExcelValue("Sheet1",1, 0)+random;
	
		
		   ProductPage addPro=new ProductPage(driver);
		   addPro.newProduct();
		   CreateProduct createProPage=new CreateProduct(driver);
		   createProPage.createProduct(excelData);
		
		   DeleteCreatedProduct deletePro=new DeleteCreatedProduct(driver);
		   deletePro.deleteCreatePro();
	     WebDriver_Utility webLib=new WebDriver_Utility();
	     webLib.switchToalertAndAccept(driver);
	   
	 	   home.imgForSignOut();
	 	   home.signOutLink();
	     driver.close();
	   }
}
