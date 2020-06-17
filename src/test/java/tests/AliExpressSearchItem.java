package tests;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import pages.*;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AliExpressSearchItem {
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
    }


    @AfterTest
    public void tearDown() {
        driver.quit();

    }
}
