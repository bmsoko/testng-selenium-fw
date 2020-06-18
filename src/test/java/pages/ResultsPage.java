package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ResultsPage extends BasePage {
    // Mappings
    By paginationElements = By.cssSelector("[class='next-pagination-list'] button");
    By totalPageLabel     = By.className("total-page");
    By goToPageBox        = By.cssSelector("[aria-label='Large']");
    By goToPageBtn        = By.className("jump-btn");
    By bestMatchFilter    = By.linkText("Best Match");
    By soldItemLink    = By.className("sale-value-link");
//    By listProductCardDescription = By.cssSelector("product-card");

    public ResultsPage(WebDriver driver) {
        super(driver);
    }


    //Actions
    public int getTotalPage() throws IOException, InterruptedException {
//        selectBestMatchFilter();
        scrollToBottom();
//        waitUntilVisible(totalPageLabel);
        String text = getText(totalPageLabel);
        String[] ele = text.split(" ");
        System.out.println(ele[1]);
        return parseInt(ele[1]);
    }

    public void selectBestMatchFilter() throws IOException, InterruptedException {
        waitUntilVisible(bestMatchFilter);
        click(bestMatchFilter);
    }


    public boolean hasPageResultsMoreThanOnePage () throws IOException, InterruptedException {
        return getTotalPage() > 1;
    }

    public void goToPageNumber (String pageNumber) throws IOException, InterruptedException {
        waitUntilVisible(goToPageBox);
        type(pageNumber, goToPageBox);
        click(goToPageBtn);
    }

    public void goToSoldItems(int goToLinkNumber) {
        List<WebElement> link =  findElements(soldItemLink);
        link.get(goToLinkNumber).click();

    }
}
