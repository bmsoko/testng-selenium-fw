package tests;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import pages.*;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.testng.Assert.*;

import java.io.IOException;

public class AliExpressSearchItemTest {
    private WebDriver driver;
    HomePage homePage;
    ResultsPage resultsPage;

    @BeforeTest
    public void setUp() throws IOException, InterruptedException {
        homePage = new HomePage(driver);

        driver = homePage.chromeDriverConnection();
        homePage.visit("https://www.aliexpress.com");
        homePage.waitForPageLoaded();
        homePage.closeIFrame();
    }

    @Test
    public void searchItem() throws IOException, InterruptedException {
        // This method will close the iFrame if shown when navigating to the home page of AliExpress
        homePage.closeIFrame();
        homePage.searchForItem("iphone");
        homePage.closeIFrame();
        resultsPage = new ResultsPage(driver);
        resultsPage.closeIFrame();
        driver.navigate().refresh();
        Assert.assertTrue(resultsPage.hasPageResultsMoreThanOnePage(), "The results did not have more that 1 page");
        resultsPage.waitForPageLoaded();
        resultsPage.goToPageNumber("2");
        resultsPage.waitForLoadToDisappear();
        resultsPage.goToSoldItems(2);
    }

    @AfterTest
    public void tearDown() {
//        driver.quit();

    }
}
