package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class BasePage {
    private WebDriver driver;
    By loaderElement = By.className("load-container");
    By closePopUp = By.cssSelector("[class='close-layer']");

    public BasePage (WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver chromeDriverConnection() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName(BrowserType.FIREFOX);
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);
        return driver;
    }

    public WebElement findElement (By locator) {
        return driver.findElement(locator);
    }

    public List<WebElement> findElements (By locator) {
        return driver.findElements(locator);
    }

    public String getText (WebElement element) {
        return element.getText();
    }

    public String getText (By locator) {
        return driver.findElement(locator).getText();
    }

    public void type(String inputText, By locator) {
        driver.findElement(locator).sendKeys(inputText);
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public boolean isDisplayed (By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void visit(String url) {
        driver.get(url);
    }

    public void waitUntilVisible (By locator) throws InterruptedException, IOException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println(e);
        }
    }

    public void waitUntilNotVisible (By locator) throws InterruptedException, IOException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        }catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println(e);
        }
    }

    public boolean isElementPresent (By locator) {
        return  driver.findElements(locator).size() > 0;

    }

    public void clear (By locator) {
        driver.findElement(locator).clear();

    }

    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }

    public void scrollToElement (By locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", locator);
    }

    public void scrollToBottom () {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void waitForLoadToDisappear () throws IOException, InterruptedException {
        waitUntilVisible(loaderElement);
        waitUntilNotVisible(loaderElement);
    }

    public void closeIFrame () throws IOException, InterruptedException {
        waitForPageLoaded();
        if (isElementPresent(closePopUp)) {
            waitUntilVisible(closePopUp);
            click(closePopUp);
        }


    }
}
