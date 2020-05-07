package no.enterprise.exam.selenium.po;

import no.enterprise.exam.selenium.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

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

    public String getUserName() {
        return getText("userNameID");
    }




    public String getRandomLootbox() {
        List<WebElement> buttons = getDriver().findElements(By.xpath("//*[contains(./@id, 'openLootboxBtn')]"));
        int indexOfButton = new Random().nextInt(buttons.size());
        return buttons.get(indexOfButton).getAttribute("id");
    }


}
