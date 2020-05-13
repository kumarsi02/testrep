package march2018;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class NewTest {
  @Test(priority=1, description="first test description")
  public void firstTest() {
	  System.out.println("this is firstTest");
  }
  @Test(priority=2, description="second test description")
  public void secondTest() {
	  System.out.println("this is secondTest");
  }
  
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("this is before method");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("this is after method");
  }

  @BeforeClass
  public void beforeClass() {
	  System.out.println("this is before class");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("this is after class");
  }

}
