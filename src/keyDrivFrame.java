import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class keyDrivFrame {

	WebDriver driver;
	@Test
	public void myTest() throws Exception{
	openBrowser("Chrome");
	enterURL("https://www.youtube.com/");
	searchText("search","India Election");
	clickElement("search-icon-legacy");
	Thread.sleep(1000);
	verifyText("test text", "//yt-formatted-string[@id='description-text']");
	verifyText("test text", "(//yt-formatted-string[@id='description-text'])[2]");
	verifyText("test text", "(//yt-formatted-string[@id='description-text'])[3]");
	verifyText("test text", "(//yt-formatted-string[@id='description-text'])[4]");
	}
	
	
//	openBrower
	public void openBrowser(String browser){
		if(browser.equals("Chrome")){
		System.setProperty("webdriver.chrome.driver","D:\\Selenium files\\Chrome Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		}
		else if(browser.equals("InternetExplorer")){
			System.setProperty("webdriver.chrome.driver","D:\\Selenium files\\Chrome Driver\\chromedriver.exe");
			driver = new ChromeDriver();
				}
		else if(browser.equals("Edge")){
			System.setProperty("webdriver.chrome.driver","D:\\Selenium files\\Chrome Driver\\chromedriver.exe");
			driver = new ChromeDriver();
				}
		else if(browser.equals("Firefox")){
			System.setProperty("webdriver.chrome.driver","D:\\Selenium files\\Chrome Driver\\chromedriver.exe");
			driver = new ChromeDriver();
				}
		driver.manage().window().maximize();
	}
	
//	navigate
	
	public void enterURL(String Url){
		driver.get(Url);
		
	}
//	searchText
	public void searchText(String searchid, String searchtxt){
		driver.findElement(By.id(searchid)).sendKeys(searchtxt);
	}
//	clickElement
	public void clickElement(String clickid){
		driver.findElement(By.id(clickid)).click();;
	}
	
	
//	verifyText
	public void verifyText(String expectedText, String actualTextid){
		String actualText = driver.findElement(By.xpath(actualTextid)).getText();
		if (expectedText.equals(actualText)){
			System.out.println("Text matched");
		}
		else
			System.out.println("Text didnt matched");
	}
	
	
}
