package com.Actitime.GenericLibrary;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.Actitime.pom.HomePage;
import com.Actitime.pom.LoginPage;

public class BaseClass {
	public static WebDriver d;
	FileLibrary f =new FileLibrary();
	
	@BeforeSuite
	public void databaseconnection() {
		Reporter.log("Database connected successfully",true);
	}
	
	@AfterSuite
	public void databasedisconnection() {
		Reporter.log("Database disconnected succesfully",true);
	}
	
	@BeforeClass
	public void launchbrowser() throws IOException {
		ChromeOptions option =new ChromeOptions();
		option.addArguments("--remote-allow-origins=*");
		
		 d =new ChromeDriver(option);
		d.manage().window().maximize();
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		String URL = f.readDataFromProperty("url");
		d.get(URL);
		Reporter.log("Browser launched",true);
		
	}
	@AfterClass
	public void closeBrowser() {
	 d.close();
	 Reporter.log("browser close",true);
	}
	@BeforeMethod
	public void login() throws IOException
	{
		
		String UN = f.readDataFromProperty("username");
		String pw = f.readDataFromProperty("password");
		
//		d.findElement(By.id("username")).sendKeys(UN);
//		d.findElement(By.name("pwd")).sendKeys(pw);
//		d.findElement(By.xpath("//div[.='Login ']")).click();
		LoginPage lp=new LoginPage(d);
		lp.getUntbx().sendKeys(UN);
		lp.getPwtbx().sendKeys(pw);
		lp.getLgbtn().click();
		
		Reporter.log("Logged in successfully",true);
		
	}
	@AfterMethod
	public void logout() {
		HomePage hp=new HomePage(d);
		hp.getLogoutlink().click();
		//d.findElement(By.id("logoutLink")).click();
		Reporter.log("Logged out successfully",true);
	}

}
