import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class myFirstTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("My first java test");
		System.setProperty("webdriver.gecko.driver","D:\\Selenium files\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.google.com");
		driver.close();
		driver.quit();
}
}