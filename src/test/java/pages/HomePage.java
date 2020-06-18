package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }
    // Mappings
    By searchBox  = By.id("search-key");
    By searchBtn  = By.className("search-button");
    By closePopUp = By.cssSelector("[class=\"close-layer\"]");
    By iFrame     = By.id("localstorage-proxy-ifr-alibabadotcom2");




    //Actions
    public void searchForItem (String itemToSearch) {
        clear(searchBox);
        type(itemToSearch, searchBox);
        click(searchBtn);
    }
}
