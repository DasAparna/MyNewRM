package com.RightMoveProperties.GenericLlib;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

/**
 * @author: Aparna
 * @Desciption : This is a Generic class that contains all the common functionalities
 * related to Rent Properties and Sale properties test cases
 *
 */
public class WebDriverCommonLib extends Log {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static WebElement ele;

    /**
     * @Desciption : This method to report the log messages
     * @param String
     */
    public void logReporter(String msg) {
        Reporter.log(msg);
    }

    public boolean assertAndVerify(boolean b) {
        return false;
    }

    /**
     * @Desciption : This method to get the values from propertyFile
     * @param String
     * @throws : FileNotFoundException
     */
    public String getValuesFromPropertiesFile(String data) throws FileNotFoundException {
        String value = "";
        String loc = "C:\\Users\\bijay\\workspace\\RightMove_Properties\\src\\main\\java\\com\\RightMoveProperties\\GenericLlib\\GlobalData.properties";
        //It will input the file from the local system and fetch the details
        FileInputStream fis = new FileInputStream(loc);
        Properties prop = new Properties();
        try {
            //It will load the details from the property file
            prop.load(fis);
            value = prop.getProperty(data);
        } catch (IOException e) {
            Log.info(e.getMessage());
        }
        return value;
    }

    /**
     * @Desciption : This method to get the values from ExcelSheet
     * @param String,Integer
     * @exception : FileNotFoundException
     * @throws FileNotFoundException
     * @throws : InvalidFormatException
     * @throws IOException
     */
    public String getValuesFromExcel(String sheetName, int rowNum, int colNum) throws EncryptedDocumentException, InvalidFormatException, IOException {
        String value = "";
        String loc;
        try {
            loc = getValuesFromPropertiesFile("TESTDATALOC");
            FileInputStream fis = new FileInputStream(loc);
            Workbook wb = WorkbookFactory.create(fis);
            //Gets the Sheet
            Sheet sh = wb.getSheet(sheetName);
            //Gets the rowNum
            Row rw = sh.getRow(rowNum);
            //it gets the string type cell value and store it in a variable
            value = rw.getCell(colNum).getStringCellValue();
        } catch (FileNotFoundException exp) {
            Log.info(exp.getMessage());
        }
        return value;
    }

    /**
     * @Desciption : This method will wait for a desired time
     * @param Integer in miliSec
     * @throws : InterruptedException
     */

    public void wait(int time) throws InterruptedException {
        Thread.sleep(time);
    }
    /**
     * @Desciption : This method will return browser driver
     * @param String
     * @exception : FileNotFoundException
     * @return driver
     */
    public WebDriver getBrowser(String browser) {
        try {
            switch (browser) {
                case "Chrome":
                    System.setProperty("webdriver.chrome.driver", getValuesFromPropertiesFile("CHROMEDRIVER_PATH"));
                    driver = new ChromeDriver();
                    break;
                case "Firefox":
                    System.setProperty("webdrivder.gecko.driver", getValuesFromPropertiesFile("FIREFOX_PATH"));
                    driver = new FirefoxDriver();
                    break;
                default:
                    driver = new ChromeDriver();
            }
        } catch (FileNotFoundException exp) {
            Log.info(exp.getMessage());
        }
        return driver;
    }
    /**
     * @Desciption : This method will take screenshot
     * @exception : Exception
     */
    public void takescreenshot() throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("AddOwnPath\\screenshot1.png"));
    }
    /**
     * @Desciption : This method will load the URL
     * @param String
     * @exception : FileNotFoundException
     */
    public void loadURL(String URL) {
        try {
            driver.get(getValuesFromPropertiesFile("URL"));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        } catch (FileNotFoundException exp) {
            Log.info(exp.getMessage());
        }
    }
    /**
     * @Desciption : This method will select from Dropdown by Value
     * @param String
     */
    public void selectFromDDbyValue(WebElement locator, String dropdown) {
        WebElement ele = locator;
        Select s1 = new Select(ele);
        s1.selectByValue(dropdown);
    }
    /**
     * @Desciption : This method will select from Dropdown by VisibleText
     * @param String
     */
    public void selectFromDDbyVisibleText(WebElement locator, String dropdown) {
        WebElement ele = locator;
        Select s1 = new Select(ele);
        s1.selectByVisibleText(dropdown);
    }
    /**
     * @Desciption : This method will select from Dropdown by Index
     * @param String
     * @throws InterruptedException 
     */
    public void selectFromDDbyIndex(WebElement locator, int dropdown) {
        WebElement ele = locator;
        Select s1 = new Select(ele);
        s1.selectByIndex(dropdown);
    }
    /**
     * @Desciption : This method will Check the properties if it is featured or not 
     * @param String,WebElement
     * @throws :IOException 
     * @throws InterruptedException
     */
    public void genericPropertyDetailsCheck(String sort,
									        WebElement sortBys,
									        WebElement featuredKeyword,
									        String expectedValue,
									        List < WebElement > elementsInPropertyPage)
									        throws IOException, InterruptedException {
    	
        //It selects values from drop down by visible text
        selectFromDDbyVisibleText(sortBys, sort);
        File src1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src1, new File("AddOwnPath\\lowestprice.png"));
       
        //This for loop checks all the blocks one by one and 
        //matches if that contains Featured Property keyword
        for (int i = 0; i <= elementsInPropertyPage.size() - 1; i++) {
           
        	//This will check first block on the page displayed does not contain Featured keyword in it
            //and Featured key word is not displayed. If both satisfies it will click 
            //on the first element
        	
            if (!elementsInPropertyPage.get(i).getText().contains(expectedValue) && !featuredKeyword.isDisplayed()) {
            	elementsInPropertyPage.get(i).click();
                
                break;
                
                //This will check the 2nd block on the page displayed does not contain Featured keyword in it's 2nd block.
                //If it satisfies then it will click on the 2nd element

            } else if (!elementsInPropertyPage.get(i + 1).getText().contains(expectedValue)) {
                elementsInPropertyPage.get(i + 1).click();
            }
        }
        wait(60);
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("AddOwnPath\\screenshotRent.png"));
    }
    /**
     * @Desciption : This method will Find a property with the filtered values 
     * 				and if no results found then it will search with another value 
     * 				from excel till it finds property details on the page and will sort accordingly
     * @param String,WebElement
     * @exception : EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
     */

    public void findAProperty(String fileLoc,
				            String SheetName,
				            WebElement resultPage,
				            WebElement errorOnPage,
				            WebElement radiusDD,
				            WebElement minPriceRangeDD,
				            WebElement maxPriceRageDD,
				            WebElement minBedRoomsDD,
				            WebElement maxBedRoomsDD,
				            WebElement errorTextError,
				            WebElement propertyTypeDD,
				            WebElement addedToSiteDD,
				            WebElement findPropertiesBtn,
				            List < WebElement > elementsInPropertyPage,
				            WebElement sortBys,
				            WebElement featuredKeyword)
    						throws EncryptedDocumentException,
    						InvalidFormatException, IOException,
    						InterruptedException {

            String errorResult = "Sorry, no results found";
            String sort = getValuesFromExcel(SheetName, 1, 8);
            String expectedValue = "Featured Property";
            String actualSalesPropertyText = "";

            try {
                //It fetches the vales from excel and stores in a string type reference variable
                String radius = getValuesFromExcel(SheetName, 7, 1);
                String minPrice = getValuesFromExcel(SheetName, 4, 2);
                String maxPrice = getValuesFromExcel(SheetName, 5, 3);
                String minBdRooms = getValuesFromExcel(SheetName, 3, 4);
                String maxBedRooms = getValuesFromExcel(SheetName, 4, 5);
                String propertyType = getValuesFromExcel(SheetName, 2, 6);
                String addedToSite = getValuesFromExcel(SheetName, 1, 7);

                //Here assertion is used to check the condition always returns true
                //and the execution does not stop
                Assert.assertTrue(radiusDD.isDisplayed() &&
                    minPriceRangeDD.isDisplayed() && maxPriceRageDD.isDisplayed() &&
                    minBedRoomsDD.isDisplayed() && maxBedRoomsDD.isDisplayed() &&
                    propertyTypeDD.isDisplayed() && addedToSiteDD.isDisplayed() &&
                    findPropertiesBtn.isDisplayed(), "Element not displayed please check the locator");

                Log.info("We are in Find properties Page");
                Log.info("Adding details from the Dropdown");
                wait(1000);
                selectFromDDbyVisibleText(radiusDD, radius);
                Log.info("Radius selected as" + " " + radius);

                selectFromDDbyVisibleText(minPriceRangeDD, minPrice);
                Log.info("Min price is selected as" + " " + minPrice);

                selectFromDDbyVisibleText(maxPriceRageDD, maxPrice);
                Log.info("Max price is selected as" + " " + maxPrice);

                selectFromDDbyVisibleText(minBedRoomsDD, minBdRooms);
                Log.info("Min rooms selected as" + " " + minBdRooms);

                selectFromDDbyVisibleText(maxBedRoomsDD, maxBedRooms);
                Log.info("Max rooms selected as" + " " + maxBedRooms);

                selectFromDDbyVisibleText(propertyTypeDD, propertyType);
                Log.info("property type selected as" + " " + propertyType);

                selectFromDDbyVisibleText(addedToSiteDD, addedToSite);
                Log.info("added to site selected as" + " " + addedToSite);
                findPropertiesBtn.click();
                wait(1000);
            } catch (FileNotFoundException e) {
                e.getMessage();
            }

            // It will get the error text from the page and 
            //store it in String type result variable
            String result = errorTextError.getText();
            /*if (result.contentEquals(errorResult)) {
                Log.info("searching for another value");
            }*/
            try {
                FileInputStream fis = new FileInputStream(fileLoc);
                Workbook wb = WorkbookFactory.create(fis);
                int sh = wb.getSheet("Sheet3").getLastRowNum();

                //it will fetch values from excel one by one and check until values appear
                for (int j = 10; j < sh; j++) {

                    //This will check if No Results found text displayed
                    if (result.contains(errorResult) && errorOnPage.isDisplayed()) {

                        Log.info("searching for another value");
                        String minPrice1 = getValuesFromExcel("Sheet3", j, 2);
                        int minpriceIndex1 = Integer.parseInt(minPrice1);
                        String maxPrice1 = getValuesFromExcel("Sheet3", j + 1, 3);
                        int maxpriceIndex1 = Integer.parseInt(maxPrice1);
                        selectFromDDbyIndex(minPriceRangeDD, minpriceIndex1);
                        Log.info("Minimum price selected as" + " " + minPrice1);
                        selectFromDDbyIndex(maxPriceRageDD, maxpriceIndex1);
                        Log.info("Maximum price selected as" + " " + maxPrice1);

                        // if error page not displayed then it will sort and 
                        //click on the first non featured property

                    } else if (!result.contains(errorResult) &&
                        !elementsInPropertyPage.isEmpty() &&
                        !errorOnPage.isDisplayed()) {

                        //Selects value from drop down by Visible Text
                        selectFromDDbyVisibleText(sortBys, sort);
                        wait(1000);

                        //This method will Check the properties if it is featured or not
                        genericPropertyDetailsCheck(sort, sortBys,featuredKeyword,
                            expectedValue, elementsInPropertyPage);

                        //Here assertAndVerify checks, the actual result 
                        // does not contain Featured Property Keyword in it
                        assertAndVerify(!actualSalesPropertyText.equalsIgnoreCase(expectedValue));
                        break;
                    }
                }
            } catch (FileNotFoundException exp) {
                System.out.println(exp.getMessage());
            }
        }
}