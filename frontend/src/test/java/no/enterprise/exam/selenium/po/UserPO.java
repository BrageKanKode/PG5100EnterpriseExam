package no.enterprise.exam.selenium.po;

import no.enterprise.exam.selenium.PageObject;
import org.openqa.selenium.WebDriver;



import static org.junit.jupiter.api.Assertions.assertTrue;

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
