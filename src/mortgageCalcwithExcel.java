
	import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.junit.After;
	import org.junit.Before;
	import org.junit.Test;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;



	public class mortgageCalcwithExcel {

		WebDriver driver;
		
		//Declare and Initialize input variables\
			String homeValue = "200000";
			String downPayment = "10000";
			String loanAmount = "100000";
			String interestRate = "5";
			String myExpectedValue = "$1,382.46";
			String myActualValue;
			String excelPath, excelSheet;
			String [][] excelData;
			
			@Before
			public void myBeforeTest() throws Exception{
				System.setProperty("webdriver.chrome.driver","D:\\Selenium files\\Chrome Driver\\chromedriver.exe");
				driver = new ChromeDriver();
				driver.get("https://www.mortgagecalculator.org/");
				driver.manage().window().maximize();
				
				//Read excel data in 2D array
				excelPath = "D:\\Selenium files\\MortgageCalc.xls";
				excelSheet = "Mort";
				excelData = readExcel(excelPath,excelSheet);	
				System.out.println("excelData =" + excelData);
				int excelRows = excelData.length;
				System.out.println("excelRows =" + excelRows);
					
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
public static String [][] readExcel(String xlPath, String xlSheet) throws Exception{
		
		int localRows, localCols;
		//FOrmat excel data, like remove $ etv
		DataFormatter dataFormatter = new DataFormatter();
		//Provide path for excel workbook
		File myExcel = new File(xlPath);
		//Open file for Input
		FileInputStream myStream = new FileInputStream(myExcel);
		//create object for  workbook 
		HSSFWorkbook myWorkBook = new HSSFWorkbook(myStream);
		//set the worksheet we want within our workbook
		HSSFSheet myWorkSheet = myWorkBook.getSheet(xlSheet);
		System.out.println("myWorkSheet is:"+myWorkSheet);
		//create the rows and columns
		localRows = myWorkSheet.getLastRowNum()+1;
		localCols = myWorkSheet.getRow(0).getLastCellNum();
		System.out.println("Rows:"+ localRows);
		System.out.println("Cols" + localCols);
		//Create a new string with rows and cols we found above
		String[][] localData = new String[localRows][localCols];
		
		for(int i=0;i<localRows;i++){
			System.out.println("RowsL:"+ localRows);
			System.out.println("I:"+ i);
			HSSFRow row = myWorkSheet.getRow(i);
			for(int j=0;j<localCols;j++){
				System.out.println("j:"+ j);
				String cellValue = "-";
				cellValue = dataFormatter.formatCellValue(row.getCell(j));
				if (cellValue != null) {
					localData[i][j] = cellValue;
					System.out.print(cellValue);
					System.out.print(i);
					System.out.println(j);
				}
			}
		}
		
		return localData;
		
		
		
		

}


}