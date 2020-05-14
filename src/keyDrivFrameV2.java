import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class keyDrivFrameV2 {
	static String xlPath = "D:\\Selenium files\\Data Driver Framework.xls" ;
	static String xlPath_Result = "D:\\Selenium files\\Data Driver Framework_Result.xls" ;
	static String TestCases[][];
	static String TestSteps[][];
	static int TestCasesLength;
	static int TestStepsLength;
	static String tcId, step, stepDesc, keyword, data, elementId, vResult; 
	static WebDriver driver;
	static long vTime_Start, vTime_Stop, vTime_Total;
	
	public static void main (String[] args) throws Exception{
		
	//Read 2 excel sheets
		TestCases = readExcel(xlPath,"TestCases");
		TestSteps = readExcel(xlPath,"TestSteps");
	//display
	//System.out.println(TestCases);
	//System.out.println(TestSteps);
	TestCasesLength = TestCases.length;
	TestStepsLength = TestSteps.length;	
	System.out.println("test cases length:" + TestCasesLength);
	System.out.println("test steps lenght:" + TestStepsLength);
	
	for (int i = 1; i<TestCasesLength; i++){
		if(TestCases[i][2].equals("Y")){
			vTime_Start = System.currentTimeMillis();
			System.out.println("__________________________________________________________________________________________");
			System.out.println("Execution True for TCid" + TestCases[i][0]);
			TestCases[i][3] = "PASS";
		
		for(int j = 1; j<TestStepsLength; j++){
			
			if(TestCases[i][0].equals(TestSteps[j][0])){
				TestSteps[j][6] = "PASS";
				System.out.println("match");
				tcId = TestSteps[j][0];
				step = TestSteps[j][1];
				stepDesc = TestSteps[j][2];
				keyword = TestSteps[j][3];
				data = TestSteps[j][4];
				elementId = TestSteps[j][5];
				System.out.print(tcId + "||" + step +"||"+ stepDesc +"||"+ keyword +"||"+ data +"||" + elementId +";");
				System.out.println();
	try {			
				if(executeKw(keyword, data, elementId).equals("Fail")){
					TestSteps[j][6] = "FAIL";
					System.out.println("TEST FAILED");
					File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					// Now you can do whatever you need to do with it, for example copy somewhere
					FileUtils.copyFile(scrFile, new File("D:\\Selenium files\\Results\\screenshots\\"+tcId+"_"+step+".jpg"));
					TestSteps[j][8]= "D:\\Selenium files\\Results\\screenshots\\"+tcId+"_"+step+".jpg";
				}
				else System.out.println("TEST PASS");
				
	} catch (Exception e) {
		System.out.println("TEST PASS" + e);
		TestCases[i][3] = "FAIL";
		TestSteps[j][6] = "FAIL";
		TestSteps[j][7] = "Error is" + e; 
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy somewhere
		FileUtils.copyFile(scrFile, new File("D:\\Selenium files\\Results\\screenshots\\"+tcId+"_"+step+".jpg"));
		TestSteps[j][8]= "D:\\Selenium files\\Results\\screenshots\\"+tcId+"_"+step+".jpg";
	}	
	
	}
			else {
				System.out.println("no match");
				}
		}
		vTime_Stop = System.currentTimeMillis();
		vTime_Total = (vTime_Stop - vTime_Start)/1000;
		System.out.println("Test Execution Time:" + vTime_Total);
		TestCases[i][4] = Long.toString(vTime_Total);
		//Updated test results to excel
		writeXL(xlPath_Result, "TestCases", TestCases);
		writeXL(xlPath_Result, "TestSteps", TestSteps);
		}
		
		else {
			System.out.println("__________________________________________________________________________________________");
			System.out.println("Execution False for TCid" + TestCases[i][0]);
			TestCases[i][3] = "Skipped";
		}
	}
	}
	
//Reusable functions
public static String executeKw(String keyW, String vData, String veId ) throws InterruptedException{
	String TestStepResult = "Pass";
	if (keyW.equals("openBrowser")){
		System.out.println("Opening Browser");
		openBrowser(vData);
	}
	if (keyword.equals("navigate")){
		System.out.println("navigating to website");
		enterURL(vData);
	}
	
	if (keyword.equals("typeText")){
		System.out.println("typing text");
		searchText(veId, vData); 	
	}
	
	if (keyword.equals("clickElement")){
		System.out.println("clicking on search button");
		clickElement(veId);
	}
	
	if (keyword.equals("verifyText")){
		System.out.println("verifying the Text");
		Thread.sleep(1000);
		TestStepResult = verifyText(vData, veId);
	}
	return TestStepResult;
	}
	
public static String [][] readExcel(String xlPath, String xlSheet) throws Exception{
		
		int localRows, localCols;
		//FOrmat excel data, like remove $ etv
		DataFormatter dataFormatter = new DataFormatter();
		//Provide path for excel workbook, create object of excel
		File myExcel = new File(xlPath);
		//Open file for Input
		FileInputStream myStream = new FileInputStream(myExcel);
		//create object for  workbook 
		HSSFWorkbook myWorkBook = new HSSFWorkbook(myStream);
		//set the worksheet we want within our workbook
		HSSFSheet myWorkSheet = myWorkBook.getSheet(xlSheet);
		System.out.println("myWorkSheet is:"+myWorkSheet);
		//get the rows and columns length
		localRows = myWorkSheet.getLastRowNum()+1;
		localCols = myWorkSheet.getRow(0).getLastCellNum();
		//System.out.println("Rows:"+ localRows);
		//System.out.println("Cols" + localCols);
		//Create a new string with rows and cols length we found above
		String[][] localData = new String[localRows][localCols];
		
		for(int i=0;i<localRows;i++){
			//System.out.println("RowsL:"+ localRows);
			//System.out.println("I:"+ i);
			HSSFRow row = myWorkSheet.getRow(i);
			for(int j=0;j<localCols;j++){
				//System.out.println("j:"+ j);
				String cellValue = "-";
				cellValue = dataFormatter.formatCellValue(row.getCell(j));
				if (cellValue != null) {
					localData[i][j] = cellValue;
					//System.out.print(cellValue);
					//System.out.print(i);
					//System.out.println(j);
				}
			}
		}
		
		return localData;
	}

public static void writeXL(String xlPath, String xlSheet, String[][] xlData ) throws Exception{
	File outFile = new File(xlPath);
	//create object for  workbook 
	HSSFWorkbook wb = new HSSFWorkbook();
	//create sheet if alreay not present
	HSSFSheet wSheet = wb.createSheet();
	//get length of rows and cols of 2D Array
	int writeRows = xlData.length;
	int writeCols = xlData[0].length;
	//goto each row and create a new row
	for (int myrow = 0; myrow < writeRows; myrow++){
		HSSFRow row = wSheet.createRow(myrow);
		//goto each col and create 1 cell at a time 
		for (int mycol =0; mycol < writeCols; mycol++){
			HSSFCell cell = row.createCell(mycol);
			//cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(xlData[myrow][mycol]);
		}
		FileOutputStream fOut = new FileOutputStream(outFile);
		wb.write(fOut);
		fOut.flush();
		fOut.close();
	}
}
//openBrower
public static void openBrowser(String browser){
	if(browser.equals("Chrome")){
	System.setProperty("webdriver.chrome.driver","D:\\Selenium files\\Chrome Driver\\V77\\chromedriver.exe");
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

//navigate
public static void enterURL(String Url){
	driver.get(Url);
	
}

//searchText
public static void searchText(String searchid, String searchtxt){
	driver.findElement(By.id(searchid)).sendKeys(searchtxt);
}

//clickElement
public static void clickElement(String clickid){
	driver.findElement(By.id(clickid)).click();;
}

//verifyText
public static String verifyText(String expectedText, String actualTextid){
	String actualText = driver.findElement(By.xpath(actualTextid)).getText();
	actualText = actualText.substring(0,35);
	expectedText = expectedText.substring(0,35);
	System.out.println("expected text is:" + expectedText);
	System.out.println("actual text is:" + actualText);
	if (expectedText.equals(actualText)){
		System.out.println("Text matched");
		return "Pass";
	}
	else
		System.out.println("Text didnt matched");
		return "Fail";

}
}
