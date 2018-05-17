import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class CognitiveTest {
 
	WebDriver driver;
	String userName="";     //Please Enter User Name
	String password="";     //Please Enter Password
	String browserName="";   //Please enter browserName Name
	
  @BeforeTest 
  public void lauchBrowser() throws Exception {

	  if(browserName.equalsIgnoreCase("firefox")) {
		  System.setProperty("webdriver.firefox.marionette", ".\\Drivers\\geckodriver.exe");
		  driver = new FirefoxDriver();
	  }
	  if(browserName.equalsIgnoreCase("chrome")) {
		  System.setProperty("webdriver.chrome.driver",".\\Drivers\\chromedriver.exe");
		  driver = new ChromeDriver();
	  }
	  else if(browserName.equalsIgnoreCase("Edge")) {
		  System.setProperty("webdriver.edge.driver",".\\Drivers\\MicrosoftWebDriver.exe");
		  driver =new EdgeDriver();
		  
	  }
	  else {
		  throw new Exception("browserName is not valid");
	  }
	  
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  
  }

  
  
  @Test
  public void login() {
	  driver.get("https://www.facebook.com/");
	  driver.findElement(By.id("email")).sendKeys(userName);
	  driver.findElement(By.id("pass")).sendKeys(password);
	  driver.findElement(By.id("loginbutton")).click();
	  
	  if(driver.getCurrentUrl().contains("login_attempt")) {
		  System.out.println("Invalid details ,  Username : "+ userName + "and Password : " + password);
	  }	 
  }
  
  
  @Test
  public void loginWithXpaths() {
	  driver.get("https://www.facebook.com/");
	  driver.findElement(By.xpath("//input[@id = 'email']")).sendKeys(userName);  
	  driver.findElement(By.xpath("//input[@id = 'email']/following::input[1]")).sendKeys(password); 
	  driver.findElement(By.xpath("//input[contains(@value, 'Log In')][@type = 'submit']")).click(); 
	  if(driver.getCurrentUrl().contains("login_attempt")) {
		  System.out.println("Invalid details,  Username : "+ userName + "and Password : " + password);
	  }
	 
  }
  
  
  @Test
  public void loginWithCompletePath() {
	  driver.get("https://www.facebook.com/");
	  driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(userName); 
	  driver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys(password);
	  driver.findElement(By.xpath("//*[@id=\"u_0_2\"]")).click();
	  
	  if(driver.getCurrentUrl().contains("login_attempt")) {
		  System.out.println("Invalid details,  Username : "+ userName + "and Password : " + password);
	  }
	 
  }
  
  @Test
  public void verifyFontColorAlign() {
	  driver.get("https://www.facebook.com/");
	  WebElement logInButton = driver.findElement(By.id("loginbutton"));
	  
	  String fontSize = logInButton.getCssValue("font-size");
	  System.out.println("Font Size -> "+ fontSize);

	  String fontColor = logInButton.getCssValue("color");
	  System.out.println("Font color -> "+ fontColor);

	  String fontTxtAlign = logInButton.getCssValue("text-align");
	  System.out.println("Font Text Alignment -> "+fontTxtAlign);	  
	
	  
  }
  
  
  
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
