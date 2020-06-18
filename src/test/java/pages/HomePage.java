package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }
    // Mappings
    By searchBox  = By.id("search-key");
    By searchBtn  = By.className("search-button");

    By algo = By.cssSelector("[data-role='newuser-welcome']");




    //Actions
    public void searchForItem (String itemToSearch) {
        clear(searchBox);
        getText(algo);
        System.out.println(getText(algo));
        type(itemToSearch, searchBox);
        click(searchBtn);
    }
}
