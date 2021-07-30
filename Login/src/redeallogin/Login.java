package redeallogin;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import java.io.File;
import java.io.IOException;

public class Login extends TestListenerAdapter {
	
	WebDriver driver;
    @BeforeClass
   public void beforeClass() 
  {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\SeleniumDrivers\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.manage().window().maximize();
   }
    @Test
  void Logintest() 
  {
	  driver.get("https://manager3.auth.eu-central-1.amazoncognito.com/login?redirect_uri=https%3A%2F%2Ftest-manager3.redeal."
	       		+ "se%2Flogin%2Foauth2%2Fcode%2Fcognito&response_type=token&client_id=5qi52b52t67n4ftlhfhpph3nu2&state=QSx6MUzk1yDR7XZm5aAzOOvO7mYCi6nO&scope=openid%20email");
	 
		//pass Password
	  driver.findElement(By.xpath("//body/div[1]/div[1]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/input[1]")).sendKeys("palak@redeal.se");
		driver.findElement(By.xpath("//body/div[1]/div[1]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/form[1]/div[2]/input[1]")).sendKeys("palakoza@123");
		
	    //Click on Button
		driver.findElement(By.xpath("//body/div[1]/div[1]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/form[1]/input[3]")).click();
		
		Assert.assertEquals(driver.getTitle(),"RedealManager");
  }

  
  @AfterClass
  public void closeBrowser() {
	  driver.close();
  }
  
  @AfterMethod
  public void CaptureScreen(ITestResult result) throws IOException
  {
	  if(result.getStatus() == ITestResult.FAILURE)
	  {
		  TakesScreenshot ts = (TakesScreenshot) driver;
		  
		  File source = ts.getScreenshotAs(OutputType.FILE);
		  File target = new File (System.getProperty("user.dir")+"/Screenshots/"+result.getName()+".png");
		  FileUtils.copyFile(source,target);
		  System.out.println("screenshot captured");
  }
  
  }

}
