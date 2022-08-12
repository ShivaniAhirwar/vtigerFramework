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
import comcast.vtiger.objectRepository.CreateProduct;
import comcast.vtiger.objectRepository.HomePage;
import comcast.vtiger.objectRepository.LoginPage;
import comcast.vtiger.objectRepository.ProductPage;
import comcast.vtiger.objectRepository.ProductValidation;

public class ProductGenTest extends BaseClass
{
	 
	@Test(groups= {"Smoke Testing","Regression Testing"})
	public void createProduct()throws Throwable 
	   { 
		HomePage homePage = new HomePage(driver);
		  homePage.productModule();
		  
		  ProductPage productPage = new ProductPage(driver);
		  productPage.newProduct();
		   Java_Utility javaLib=new Java_Utility();
		   int random = javaLib.getRandomNum();
		   
		   Excel_utility excelLib=new Excel_utility();
		    String excelData = excelLib.getExcelValue("Sheet1",1,1)+random;
		    HomePage home=new HomePage(driver);
		    home.productModule();
		 
		   ProductPage addpro=new ProductPage(driver);
		   addpro.newProduct();
		   CreateProduct createProPage=new CreateProduct(driver);
		   createProPage.createProduct(excelData);
		   
		  ProductValidation proTitle=new ProductValidation(driver);
		    String actualTitle = proTitle.actualProductData();
		    
		    Assert.assertEquals(actualTitle.contains(excelData),true);
		      
		   home.imgForSignOut();
		   home.signOutLink();
		   driver.close();
	   }
	
}
