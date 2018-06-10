package com.RightMoveProperties.PAgeObjectRepo;
import java.io.FileNotFoundException;
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
 * @Desciption : This class Finds the Lowest Non-featured property to Rent
 *
 */
public class For_Rent_Properties extends WebDriverCommonLib {

    @FindBy(xpath = "//div/input[contains(@id,'searchLocation')]")
    private WebElement searchBar;
    @FindBy(xpath = "//button[contains(@id,'rent')]")
    private WebElement forRentBtn;
    @FindBy(xpath = "//div/button[contains(@id, 'rent')]")
    private WebElement toRentBtn;
    @FindBy(xpath = "(//select[contains(@name,'radius')])[1]")
    private WebElement radiusDD;
    @FindBy(xpath = "//select[contains(@name,'minPrice')]")
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
    @FindBy(xpath = "(//div[contains(@class,'l-searchResult is-list')])[1]")
    private WebElement searchResult;
    @FindBy(xpath = "//div/span[contains(text(),'Sorry, no results found')]")
    private WebElement errorMsg;
    @FindBy(xpath = "//a[contains(text(),'Discover new areas')]")
    private WebElement discoverNewAreas;
    @FindBy(xpath = "//div[contains(@class,'l-propertySearch-results propertySearch-results')]")
    private WebElement resultPage;
    @FindBy(xpath = "//div/span[contains(text(),'Sorry, no results found')]")
    private WebElement soryTextError;
    @FindBy(xpath = "//select[contains(@id,'sortType')]")
    private WebElement sortBys;
    @FindBy(xpath = "(//div[contains(@class,'propertyCard-moreInfoFeaturedTitle')])[1]")
    private WebElement featuredKeyword;
    @FindBy(xpath = "//a/img[contains(@id,'rm-site-logo')]")
    private WebElement homepageLink;
    @FindBy(xpath = "//div[contains(@class,'no-svg-rightmove-icon-logo globalHeader-logo--house-svg')]")
    private WebElement logo;
    @FindBy(xpath = "//div/span[contains(text(),'Sorry, no results found')]")
    private WebElement errorOnPage;
    @FindBys({
        @FindBy(xpath = "(//div[contains(@class,'l-searchResult is-list')])")
    })
    //It will store list of all web elements displayed on page
    private List<WebElement> elementsInPropertyPage;

    /**
     * @Desciption : This method to search to rent properties by PostCode
     * @param String,integer
     * @throws InterruptedException 
     * @throws IOException 
     * @throws InvalidFormatException 
     * @throws EncryptedDocumentException 
     */
    public void toRentProperty() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

        String postcode = getValuesFromExcel("Sheet1", 1, 0);
        //In this case the value should always return true
        Assert.assertTrue(homepageLink.isDisplayed());
        homepageLink.click();
        wait(1000);
        //In this case the value should always return true
        Assert.assertTrue(searchBar.isDisplayed() &&
            forRentBtn.isDisplayed(),
            "Element not displayed, please check the Locator");
        searchBar.sendKeys(postcode);
        forRentBtn.click();
        wait(1000);
    }
    /**
     * @Desciption : This method find all the properties to rent and click on the first non featured lowest property
     * @param String,integer,WebElement
     * @throws InterruptedException 
     * @throws IOException 
     * @throws InvalidFormatException 
     * @throws EncryptedDocumentException 
     * @throws 
     */
    public void rentExecuteTest() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
        String fileLoc;
        fileLoc = getValuesFromPropertiesFile("TESTDATALOC");
        String SheetName = "Sheet2";
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