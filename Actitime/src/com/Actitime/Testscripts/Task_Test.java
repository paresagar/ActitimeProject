package com.Actitime.Testscripts;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.Actitime.GenericLibrary.BaseClass;
import com.Actitime.GenericLibrary.FileLibrary;
import com.Actitime.pom.HomePage;
import com.Actitime.pom.TaskPage;

public class Task_Test  extends BaseClass{
	@Test
	public void createCustomer() throws EncryptedDocumentException, IOException {
		HomePage hp=new HomePage(d);
		hp.getTasklink().click();
		
		TaskPage tp=new TaskPage(d);
		tp.getAddnewbtn().click();
		tp.getCreatecust().click();
		
		FileLibrary f=new FileLibrary();
		String name = f.readDataFromExcelFile("Sheet1", 2, 1);
		tp.getCreatenamecust().sendKeys(name);
		String desc = f.readDataFromExcelFile("Sheet1", 2, 2);
		tp.getCreatedesccust().sendKeys(desc);
		
		tp.getAddcust().click();
			
		
		
	}

}
