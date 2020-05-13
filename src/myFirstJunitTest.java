import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;


public class myFirstJunitTest {

	WebDriver driver; //Global driver
	
		@Before
		public void myBeforeTest(){
		System.out.println("First test started");
		System.setProperty("webdriver.gecko.driver","D:\\Selenium files\\geckodriver.exe");
		driver = new FirefoxDriver();  //creating object of class Webdriver
		driver.get("https://www.google.com");
		}
		
		@Test
		public void myTestTest(){
		
		driver.findElement(By.name("q")).sendKeys("search");
	//	driver.findElement(By.name("btnk"));
		}
		
//		@After
//		public void myAfterTest(){
//		driver.close();
//		driver.quit();
//	}

}

