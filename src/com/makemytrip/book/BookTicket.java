package com.makemytrip.book;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Driver;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.makemytrip.Base.Base;

public class BookTicket extends Base 
{
	@Test(dataProvider="getData")
	public void bookTicket(String source,String dest)
	{
		WebElement sourceddl=driver.findElement(By.xpath("//input[@id='hp-widget__sfrom']"));
		WebElement destddl=driver.findElement(By.xpath("//input[@id='hp-widget__sTo']"));
		sourceddl.clear();
		destddl.clear();
		sourceddl.sendKeys(source);
		driver.findElement(By.xpath("//div[contains(@class,'autocomplete_from')]/ul/li//span[contains(text(),"+source+")]")).click();
		destddl.sendKeys(dest);
		driver.findElement(By.xpath("//div[contains(@class,'autocomplete_to')]/ul/li//span[contains(text(),"+dest+")]")).click();
		driver.findElement(By.id("hp-widget__depart")).click();
		driver.findElement(By.xpath("//div[@class='dateFilter hasDatepicker']//table[@class='ui-datepicker-calendar']//td[contains(@class,'ui-datepicker-today')]")).click();
		driver.findElement(By.xpath("//button[text()='Search']")).click();	
		//driver.get("https://www.makemytrip.com/");		
	}	
	@DataProvider
	public Object[][] getData() throws InvalidFormatException, IOException
	{
		
		String path="D:\\Maven_Framework\\MakeMyTripBookingDemo\\FilpkartExcel.xlsx";
		FileInputStream fis=new FileInputStream(path);
		Workbook book=WorkbookFactory.create(fis);
		Sheet s=book.getSheet("Sheet1");
		int rowcount =s.getLastRowNum();
		Object[][] obj=new Object[rowcount][2];
		for(int i=0;i<rowcount;i++)
		{
			Row row=s.getRow(i+1);
			obj[i][0]=row.getCell(0).getStringCellValue();
			obj[i][1]=row.getCell(1).getStringCellValue();			
		}
		return obj;		
	}
}
