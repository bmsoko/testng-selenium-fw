package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static java.lang.Integer.parseInt;

public class ProductDetailsPage extends BasePage {
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }
    // Mappings
    By productQLabel = By.className("product-quantity-tip");




    // Actions
    public boolean hasAvailableProducts () throws IOException, InterruptedException {
        return getAmountOfProducts() > 0;
    }

    public int getAmountOfProducts() throws IOException, InterruptedException {
        scrollToBottom();
        String text = getText(productQLabel);
        String[] ele = text.split(" ");
        return parseInt(ele[0]);
    }

}
