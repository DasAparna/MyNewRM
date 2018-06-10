package com.RightMoveProperties.PAgeObjectRepo;
import java.io.IOException;
import java.util.List;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.testng.Assert;
import com.RightMoveProperties.GenericLlib.WebDriverCommonLib;

/**
 * @author: Aparna
 * @Desciption : This class Finds the Newest Non-featured property for Sale
 *
 */
public class For_Sale_Properties extends WebDriverCommonLib {

    @FindBy(xpath = "//div/input[contains(@id,'searchLocation')]")
    private WebElement searchBar;
    @FindBy(xpath = "//div/button[contains(@id, 'buy')]")
    private WebElement forSaleBtn;
    @FindBy(xpath = "//div/button[contains(@id, 'rent')]")
    private WebElement toRentBtn;
    @FindBy(xpath = "(//select[contains(@name,'radius')])[1]")
    private WebElement radiusDD;
    @FindBy(xpath = "(//select[contains(@name,'minPrice')])[1]")
    private WebElement minPriceRangeDD;
    @FindBy(xpath = "(//select[contains(@name,'maxPrice')])[1]")
    private WebElement maxPriceRageDD;
    @FindBy(xpath = "(//select[contains(@name,'minBedrooms')])[1]")
    private WebElement minBedRoomsDD;
    @FindBy(xpath = "//select[contains(@name,'maxBedrooms')]")
    private WebElement maxBedRoomsDD;
    @FindBy(xpath = "(//select[contains(@name,'displayPropertyType')])[1]")
    private WebElement propertyTypeDD;
    @FindBy(xpath = "(//select[contains(@name,'maxDaysSinceAdded')])[1]")
    private WebElement addedToSiteDD;
    @FindBy(xpath = "//button[contains(text(),'Find properties')]")
    private WebElement findPropertiesBtn;
    @FindBy(xpath = "//select[contains(@name,'sortType')]")
    private WebElement priceDD;
    @FindBy(xpath = "(//div[contains(@class,'l-searchResult is-list')])[1]")
    private WebElement searchResult;
    @FindBy(xpath = "(//div[contains(@class,'propertyCard-moreInfoFeaturedTitle')])[1]")
    private WebElement featuredKeyword;
    @FindBy(xpath = "//div/span[contains(text(),'Sorry, no results found')]")
    private WebElement errorMsg;
    @FindBy(xpath = "//a[contains(text(),'Discover new areas')]")
    private WebElement discoverNewAreas;
    @FindBy(xpath = "//div[contains(@class,'l-propertySearch-results propertySearch-results')]")
    private WebElement resultPage;
    @FindBy(xpath = "//a/img[contains(@id,'rm-site-logo')]")
    private WebElement homepageLink;
    @FindBy(xpath = "//div/span[contains(text(),'Sorry, no results found')]")
    private WebElement soryTextError;
    @FindBy(xpath = "//select[contains(@id,'sortType')]")
    private WebElement sortBys;
    @FindBy(xpath = "//div/span[contains(text(),'Sorry, no results found')]")
    private WebElement errorOnPage;
    @FindBys({
        @FindBy(xpath = "(//div[contains(@class,'l-searchResult is-list')])")
    })
    //
    private List < WebElement > elementsInPropertyPage;

    /**
     * @Desciption : This method to search to Sale properties by PostCode
     * @param String,integer
     * @throws InvalidFormatException 
     * @throws EncryptedDocumentException 
     * @throws InterruptedException 
     * @throws IOException 
     */
    public void forSaleProperty() throws EncryptedDocumentException,
        								 InvalidFormatException, InterruptedException,
        								 IOException {

            String postcode = getValuesFromExcel("Sheet1", 1, 0);
            Assert.assertTrue(searchBar.isDisplayed() && forSaleBtn.isDisplayed(), "Element not displayed, please check the Locator");
            searchBar.sendKeys(postcode);
            forSaleBtn.click();
            wait(1000);
        }

    /**
     * @Desciption : This method finds all the properties to Rent and 
     * 				 click on the first Newest non-featured property displayed
     * @param String integer,WebElement
     * @throws InterruptedException 
     * @throws IOException 
     * @throws InvalidFormatException 
     * @throws EncryptedDocumentException 
     */
    public void salesExecuteTest() throws EncryptedDocumentException,
        								  InvalidFormatException,
        								  IOException, InterruptedException {

            String fileLoc = getValuesFromPropertiesFile("TESTDATALOC");
            String SheetName = "Sheet1";
            //This finds the property for Sale by taking values from excel and clicks on
            //the first non featured property
            findAProperty(fileLoc, SheetName, resultPage,
			                errorOnPage, radiusDD,
			                minPriceRangeDD, maxPriceRageDD,
			                minBedRoomsDD, maxBedRoomsDD,
			                soryTextError, propertyTypeDD,
			                addedToSiteDD, findPropertiesBtn,
			                elementsInPropertyPage, sortBys,
			                featuredKeyword);
        }
}