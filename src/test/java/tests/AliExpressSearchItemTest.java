package tests;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import pages.*;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class AliExpressSearchItemTest {
    private WebDriver driver;
    HomePage homePage;
    ResultsPage resultsPage;
    ProductDetailsPage productDetailsPage;

    @BeforeTest
    public void setUp() throws IOException, InterruptedException {
        homePage = new HomePage(driver);

        driver = homePage.chromeDriverConnection();
        homePage.visit("https://www.aliexpress.com");
        homePage.waitForPageLoaded();
        homePage.closeIFrame();
    }

    @Test(groups = { "regression" })
    public void searchItem() throws IOException, InterruptedException {
        homePage.closeIFrame();
        homePage.searchForItem("iphone");
        homePage.closeIFrame();
        resultsPage = new ResultsPage(driver);
        resultsPage.closeIFrame();
        // to remove the iFrame
        driver.navigate().refresh();
        Assert.assertTrue(resultsPage.hasPageResultsMoreThanOnePage(), "The results did not have more that 1 page");
        resultsPage.waitForPageLoaded();
        resultsPage.goToPageNumber("2");
        resultsPage.waitForLoadToDisappear();
        resultsPage.goToSoldItems(2);
        String winHandleBefore = driver.getWindowHandle();
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
        productDetailsPage = new ProductDetailsPage(driver);
        productDetailsPage.waitForPageLoaded();
        Assert.assertTrue(productDetailsPage.hasAvailableProducts(), "There were no available products");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();

    }
}
