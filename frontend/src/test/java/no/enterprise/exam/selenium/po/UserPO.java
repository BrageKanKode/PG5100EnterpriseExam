package no.enterprise.exam.selenium.po;

import no.enterprise.exam.selenium.PageObject;
import org.openqa.selenium.WebDriver;



import static org.junit.jupiter.api.Assertions.assertTrue;

/*
    Altered the other PO's to fit the assignment
 */

public class UserPO extends LayoutPO {

    public UserPO(WebDriver driver, String host, int port) {
        super(driver, host, port);
    }

    public UserPO(PageObject other) {
        super(other);
    }

    @Override
    public boolean isOnPage() {
        return getDriver().getTitle().contains("User page");
    }

    public void redeemLootbox() {

        clickAndWait("openLootboxBtn");

        UserPO po = new UserPO(this);
        assertTrue(po.isOnPage());

    }

    public void buyLootbox() {
        clickAndWait("buyBtn");

        UserPO po = new UserPO(this);
        assertTrue(po.isOnPage());

    }

    public void millLootbox() {
        clickAndWait("itemTable:2:millBtn");

        UserPO po = new UserPO(this);
        assertTrue(po.isOnPage());

    }
}
