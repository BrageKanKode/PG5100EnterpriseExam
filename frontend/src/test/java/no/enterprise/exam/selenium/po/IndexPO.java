package no.enterprise.exam.selenium.po;

import no.enterprise.exam.selenium.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class IndexPO extends LayoutPO {

    public IndexPO(WebDriver driver, String host, int port) {
        super(driver, host, port);
    }

    public IndexPO(PageObject other) {
        super(other);
    }

    public void toStartingPage() {
        getDriver().get(host + ":" + port);
    }

    public int getNumberOfItemsDisplayed() {
        return getDriver().findElements(By.xpath("//table//tr")).size() - 1;
    }

    public IndexPO searchOnPage(String selection, String query) {
        setText("queryInputID", query);
        WebElement dropDown = getDriver().findElement(By.id("selectOneID"));
        Select searchType = new Select(dropDown);
        searchType.selectByValue(selection);
        IndexPO indexPO = new IndexPO(this);
        clickAndWait("searchBtn");
        assertTrue(indexPO.isOnPage());
        return indexPO;
    }

    @Override
    public boolean isOnPage() {
        return getDriver().getTitle().contains("Home page");
    }

    public UserPO redeemLootbox() {

        clickAndWait("openLootboxBtn");

        UserPO po = new UserPO(this);
        assertTrue(po.isOnPage());

        return po;
    }

    public UserPO buyLootbox() {
        clickAndWait("buyBtn");

        UserPO po = new UserPO(this);
        assertTrue(po.isOnPage());

        return po;
    }

    public UserPO millLootbox() {
        clickAndWait("itemTable:2:millBtn");

        UserPO po = new UserPO(this);
        assertTrue(po.isOnPage());

        return po;


    }


}
