package yahoo_suite;

import java.io.FileInputStream;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Yahoo_Prg.Compose;
import Yahoo_Prg.Inbox;
import Yahoo_Prg.Login;
import Yahoo_Prg.MainClass;

public class ReTest extends MainClass
{
  
  @Test	
  @Parameters({"browser"})
  public void retesting(String str) throws Exception
  {
	  if(str.matches("firefox"))
	  {
		  driver=new FirefoxDriver();
	  }
	  if(str.matches("chrome"))
	  {
		  driver=new ChromeDriver();
	  }
	  FileInputStream fin = new FileInputStream("D:\\sel_dec18\\TestCases.xlsx");
	  XSSFWorkbook wb = new XSSFWorkbook(fin);
	  XSSFSheet ws = wb.getSheet("retest");
	  Row row;
	  String classname,methodname;
	  for(int r=1;r<=ws.getLastRowNum();r++)
	  {
		  row=ws.getRow(r);
		  if(row.getCell(5).getStringCellValue().matches("yes"))
		  {
			  classname=row.getCell(3).getStringCellValue();
			  methodname=row.getCell(4).getStringCellValue();
			  
			  Class c=Class.forName(classname);  //load the sample class into memory............. here   c is referring to the Sample class loaded in the memory
			  Method m=c.getMethod(methodname,null);   /// get the method in the class and refer in m
			  Object obj=c.newInstance();   //create object for the class
			  m.invoke(obj,null);     //call the method			  
		  }
	  }
	  fin.close();
	 
  }
}
