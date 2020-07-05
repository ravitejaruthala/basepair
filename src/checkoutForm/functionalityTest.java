package checkoutForm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.*;

class functionalityTest {

	//Declaring the global variables
	Properties prop;		
	FileInputStream propFile;
	public static WebDriver driver;
	File file;
	FileInputStream dataFile;
	public static Workbook dataWB;
	public static Sheet dataSheet;
	
	By wb_firstName = By.id("firstName");
	By wb_lastName = By.id("lastName");
	By wb_eMail = By.id("email");
	By wb_address1 = By.id("address");
	By wb_address2 = By.id("address2");
	By wb_country = By.id("country");
	By wb_state = By.id("state");
	By wb_zip = By.id("zip");
	By checkBox = By.xpath("//label[@class='custom-control-label']");
	By submit = By.xpath("//button[@type='submit']");
	

	@BeforeClass
	public void before() throws IOException {

		//Loading properties file
		prop = new Properties();
		propFile = new FileInputStream("Test_Data//data.properties");
		prop.load(propFile);

		//Integrating excel sheet for test data
		file = new File(prop.getProperty("DataPath"));
		dataFile = new FileInputStream(file);
		dataWB = new XSSFWorkbook (dataFile); 
		dataSheet=dataWB.getSheet(prop.getProperty("SheetName"));

		//Launching and customizing the web browser display
		System.setProperty("webdriver.chrome.driver", prop.getProperty("DriverPath"));
		driver = new ChromeDriver();
		driver.get(prop.getProperty("URL"));
		driver.manage().window().maximize();

		//Implicit and Explicit wait declaration
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@AfterMethod
	public void before_test_method() {
		driver.navigate().refresh();
		driver.manage().deleteAllCookies();
	}

	@Test(priority =1)
	public void positiveTest(){
		Row row = dataSheet.getRow(1);
		driver.findElement(wb_firstName).sendKeys(row.getCell(0).getStringCellValue());
		driver.findElement(wb_lastName).sendKeys(row.getCell(1).getStringCellValue());
		driver.findElement(wb_eMail).sendKeys(row.getCell(2).getStringCellValue());
		driver.findElement(wb_address1).sendKeys(row.getCell(3).getStringCellValue());
		driver.findElement(wb_address2).sendKeys(row.getCell(4).getStringCellValue());
		new Select(driver.findElement(wb_country)).selectByVisibleText(row.getCell(5).getStringCellValue());
		new Select(driver.findElement(wb_state)).selectByVisibleText(row.getCell(6).getStringCellValue());
		driver.findElement(wb_zip).sendKeys(row.getCell(7).toString());
		driver.findElement(checkBox).click();
		driver.findElement(submit).click();
		
		if (driver.findElement(By.xpath("//div[@class='invalid-feedback']")).isDisplayed()){
			Reporter.log("Result is not as expected - Failed");
		}
		else { Reporter.log("Result is as expected - Passed"); }
	}

	@Test(priority =2)
	public void eroorMessage_FirstName(){
		Row row = dataSheet.getRow(2);
		String msg = row.getCell(8).toString();
		driver.findElement(wb_firstName).sendKeys(row.getCell(0).getStringCellValue());
		driver.findElement(wb_lastName).sendKeys(row.getCell(1).getStringCellValue());
		driver.findElement(wb_eMail).sendKeys(row.getCell(2).getStringCellValue());
		driver.findElement(wb_address1).sendKeys(row.getCell(3).getStringCellValue());
		driver.findElement(wb_address2).sendKeys(row.getCell(4).getStringCellValue());
		new Select(driver.findElement(wb_country)).selectByVisibleText(row.getCell(5).getStringCellValue());
		new Select(driver.findElement(wb_state)).selectByVisibleText(row.getCell(6).getStringCellValue());
		driver.findElement(wb_zip).sendKeys(row.getCell(7).toString());
		driver.findElement(checkBox).click();
		driver.findElement(submit).click();
		
		if (driver.findElement(By.xpath("//div[@class='invalid-feedback']")).isEnabled()){
			if(driver.findElement(By.xpath("//div[contains(text(),'Valid first name is required.')]")).getText().equals(msg)) {
				Reporter.log("Result is as expected - Pass"); 
			}
			else {
				Reporter.log("Error message for first name is not as expected - Failed");
			}		
		}
		else { Reporter.log("Result is not as expected - Failed"); }
	}

	@Test(priority =3)
	public void eroorMessage_LastName(){
		Row row = dataSheet.getRow(3);
		String msg = row.getCell(8).toString();
		driver.findElement(wb_firstName).sendKeys(row.getCell(0).getStringCellValue());
		driver.findElement(wb_lastName).sendKeys(row.getCell(1).getStringCellValue());
		driver.findElement(wb_eMail).sendKeys(row.getCell(2).getStringCellValue());
		driver.findElement(wb_address1).sendKeys(row.getCell(3).getStringCellValue());
		driver.findElement(wb_address2).sendKeys(row.getCell(4).getStringCellValue());
		new Select(driver.findElement(wb_country)).selectByVisibleText(row.getCell(5).getStringCellValue());
		new Select(driver.findElement(wb_state)).selectByVisibleText(row.getCell(6).getStringCellValue());
		driver.findElement(wb_zip).sendKeys(row.getCell(7).toString());
		driver.findElement(checkBox).click();
		driver.findElement(submit).click();
		
		if (driver.findElement(By.xpath("//div[@class='invalid-feedback']")).isEnabled()){
			if(driver.findElement(By.xpath("//div[contains(text(),'Valid last name is required.')]")).getText().equals(msg)) {
				Reporter.log("Result is as expected - Pass"); 
			}
			else {
				Reporter.log("Error message for last name is not as expected - Failed");
			}		
		}
		else { Reporter.log("Result is not as expected - Failed"); }
	}
	
	@Test(priority =4)
	public void eroorMessage_eMail(){
		Row row = dataSheet.getRow(4);
		String msg = row.getCell(8).toString();
		driver.findElement(wb_firstName).sendKeys(row.getCell(0).getStringCellValue());
		driver.findElement(wb_lastName).sendKeys(row.getCell(1).getStringCellValue());
		driver.findElement(wb_eMail).sendKeys(row.getCell(2).getStringCellValue());
		driver.findElement(wb_address1).sendKeys(row.getCell(3).getStringCellValue());
		driver.findElement(wb_address2).sendKeys(row.getCell(4).getStringCellValue());
		new Select(driver.findElement(wb_country)).selectByVisibleText(row.getCell(5).getStringCellValue());
		new Select(driver.findElement(wb_state)).selectByVisibleText(row.getCell(6).getStringCellValue());
		driver.findElement(wb_zip).sendKeys(row.getCell(7).toString());
		driver.findElement(checkBox).click();
		driver.findElement(submit).click();
		
		if (driver.findElement(By.xpath("//div[@class='invalid-feedback']")).isEnabled()){
			if(driver.findElement(By.xpath("//div[contains(text(),'Please enter a valid email address for shipping up')]")).getText().equals(msg)) {
				Reporter.log("Result is as expected - Pass"); 
			}
			else {
				Reporter.log("Error message for last name is not as expected - Failed");
			}			
		}
		else { Reporter.log("Result is not as expected - Failed"); }
	}
	
	@Test(priority =5)
	public void eroorMessage_address1(){
		Row row = dataSheet.getRow(5);
		String msg = row.getCell(8).toString();
		driver.findElement(wb_firstName).sendKeys(row.getCell(0).getStringCellValue());
		driver.findElement(wb_lastName).sendKeys(row.getCell(1).getStringCellValue());
		driver.findElement(wb_eMail).sendKeys(row.getCell(2).getStringCellValue());
		driver.findElement(wb_address1).sendKeys(row.getCell(3).getStringCellValue());
		driver.findElement(wb_address2).sendKeys(row.getCell(4).getStringCellValue());
		new Select(driver.findElement(wb_country)).selectByVisibleText(row.getCell(5).getStringCellValue());
		new Select(driver.findElement(wb_state)).selectByVisibleText(row.getCell(6).getStringCellValue());
		driver.findElement(wb_zip).sendKeys(row.getCell(7).toString());
		driver.findElement(checkBox).click();
		driver.findElement(submit).click();

		if (driver.findElement(By.xpath("//div[@class='invalid-feedback']")).isEnabled()){
			if(driver.findElement(By.xpath("//div[contains(text(),'Please enter your shipping address.')]")).getText().equals(msg)) {
				Reporter.log("Result is as expected - Pass"); 
			}
			else {
				Reporter.log("Error message for last name is not as expected - Failed");
			}		
		}
		else { Reporter.log("Result is not as expected - Failed"); }
	}
	
	@Test(priority =6)
	public void eroorMessage_address2(){
		Row row = dataSheet.getRow(6);
		driver.findElement(wb_firstName).sendKeys(row.getCell(0).getStringCellValue());
		driver.findElement(wb_lastName).sendKeys(row.getCell(1).getStringCellValue());
		driver.findElement(wb_eMail).sendKeys(row.getCell(2).getStringCellValue());
		driver.findElement(wb_address1).sendKeys(row.getCell(3).getStringCellValue());
		driver.findElement(wb_address2).sendKeys(row.getCell(4).getStringCellValue());
		new Select(driver.findElement(wb_country)).selectByVisibleText(row.getCell(5).getStringCellValue());
		new Select(driver.findElement(wb_state)).selectByVisibleText(row.getCell(6).getStringCellValue());
		driver.findElement(wb_zip).sendKeys(row.getCell(7).toString());
		driver.findElement(checkBox).click();
		driver.findElement(submit).click();
		
		if (driver.findElement(By.xpath("//div[@class='invalid-feedback']")).isDisplayed()){
			Reporter.log("Result is not as expected - Failed");
		}
		else { Reporter.log("Result is as expected - Passed"); }
	}
	
	@Test(priority =7)
	public void eroorMessage_zip(){
		Row row = dataSheet.getRow(7);
		String msg = row.getCell(8).toString();
		driver.findElement(wb_firstName).sendKeys(row.getCell(0).getStringCellValue());
		driver.findElement(wb_lastName).sendKeys(row.getCell(1).getStringCellValue());
		driver.findElement(wb_eMail).sendKeys(row.getCell(2).getStringCellValue());
		driver.findElement(wb_address1).sendKeys(row.getCell(3).getStringCellValue());
		driver.findElement(wb_address2).sendKeys(row.getCell(4).getStringCellValue());
		new Select(driver.findElement(wb_country)).selectByVisibleText(row.getCell(5).getStringCellValue());
		new Select(driver.findElement(wb_state)).selectByVisibleText(row.getCell(6).getStringCellValue());
		driver.findElement(wb_zip).sendKeys(row.getCell(7).toString());
		driver.findElement(checkBox).click();
		driver.findElement(submit).click();
		
		if (driver.findElement(By.xpath("//div[@class='invalid-feedback']")).isEnabled()){
			if(driver.findElement(By.xpath("//div[contains(text(),'Zip code required.')]")).getText().equals(msg)) {
				Reporter.log("Result is as expected - Pass"); 
			}
			else {
				Reporter.log("Error message for last name is not as expected - Failed");
			}		
		}
		else { Reporter.log("Result is not as expected - Failed"); }
	}
	
	@Test(priority =8)
	public void checkBox_Validation(){
		Row row = dataSheet.getRow(1);
		driver.findElement(wb_firstName).sendKeys(row.getCell(0).getStringCellValue());
		driver.findElement(wb_lastName).sendKeys(row.getCell(1).getStringCellValue());
		driver.findElement(wb_eMail).sendKeys(row.getCell(2).getStringCellValue());
		driver.findElement(wb_address1).sendKeys(row.getCell(3).getStringCellValue());
		driver.findElement(wb_address2).sendKeys(row.getCell(4).getStringCellValue());
		new Select(driver.findElement(wb_country)).selectByVisibleText(row.getCell(5).getStringCellValue());
		new Select(driver.findElement(wb_state)).selectByVisibleText(row.getCell(6).getStringCellValue());
		driver.findElement(wb_zip).sendKeys(row.getCell(7).toString());
		driver.findElement(submit).click();

		String checkbox_color = driver.findElement(By.xpath("//label[@class='custom-control-label']")).getCssValue("color");
		if (checkbox_color.equals("rgba(220, 53, 69, 1)")){
			Reporter.log("Result is as expected - Pass"); 	
		}
		else{ 
			Reporter.log("Result is not as expected - Failed"); 
			}
	}
	
	@AfterClass
	public void after(){
		//closing the browser window
		driver.close();
	}
}