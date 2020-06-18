package tests;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import pages.*;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.testng.Assert.*;

public class AliExpressSearchItemTest {
    private WebDriver driver;
    HomePage homePage;

    @BeforeTest
    public void setUp() {
        homePage = new HomePage(driver);
        driver = homePage.chromeDriverConnection();
        homePage.visit("https://www.aliexpress.com");
    }

    @Test
    public void searchItem(){
        homePage.searchForItem("iphone");
    }

    @AfterTest
    public void tearDown() {
//        driver.quit();

    }
}
