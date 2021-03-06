import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class mortgageCalcwithFunctions {

	WebDriver driver;
	
	//Declare and Initialize input variables\
		String homeValue = "200000";
		String downPayment = "10000";
		String loanAmount = "100000";
		String interestRate = "5";
		String myExpectedValue = "$1,382.46";
		String myActualValue;
		
		
		@Before
		public void myBeforeTest(){
			System.setProperty("webdriver.chrome.driver","D:\\Selenium files\\Chrome Driver\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.get("https://www.mortgagecalculator.org/");
			driver.manage().window().maximize();
		
			
	}
		@Test
		public void myTestTest(){
			for(int i=2;i<6;i++){
			System.out.println("Test for interest =" +i);	
			putData(i);
			getResult();
			}

		}
		
		@After
		public void myAfterTest(){
	driver.close();
		}	
		
		public void putData(int interest){
			String interestString = Integer.toString(interest);
			driver.findElement(By.id("homeval")).clear();
			driver.findElement(By.id("homeval")).sendKeys(homeValue);
			driver.findElement(By.id("downpayment")).clear();
			driver.findElement(By.id("downpayment")).sendKeys(downPayment);
			//driver.findElement(By.id("loanamt")).sendKeys(loanAmount);
			driver.findElement(By.id("intrstsrate")).sendKeys(interestString);
			driver.findElement(By.name("cal")).click();
		
		}
		
		public void getResult(){
			
			myActualValue = driver.findElement(By.xpath("(//h3)[2]")).getText();
			System.out.println(myActualValue);
			System.out.println(myExpectedValue);

			if (myActualValue.equals(myExpectedValue)){
				System.out.println("Test Passed");
			}
			else {
				System.out.println("Test fail");
			}
			
		}
	}