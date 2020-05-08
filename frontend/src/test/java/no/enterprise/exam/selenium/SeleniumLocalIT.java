package no.enterprise.exam.selenium;

/*
    Something of https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/frontend/src/test/java/org/tsdes/intro/exercises/quizgame/selenium/SeleniumLocalIT.java
 */

import no.enterprise.exam.Application;
import no.enterprise.exam.backend.entity.Item;
import no.enterprise.exam.backend.service.ItemService;
import no.enterprise.exam.selenium.po.IndexPO;
import no.enterprise.exam.selenium.po.SignUpPO;
import no.enterprise.exam.selenium.po.UserPO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
public class SeleniumLocalIT {

    private static WebDriver driver;

    @LocalServerPort
    private int port;


    @BeforeAll
    public static void initClass() {

        driver = SeleniumDriverHandler.getChromeDriver();

        assumeTrue(driver != null, "Cannot find/initialize Chrome driver");
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.close();
        }
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected String getServerHost() {
        return "localhost";
    }

    protected int getServerPort() {
        return port;
    }

    private static final AtomicInteger counter = new AtomicInteger(0);

    private String getUniqueId() {
        return "foo_SeleniumLocalIT_" + counter.getAndIncrement();
    }

    private IndexPO home;
    private UserPO userHome;

    private IndexPO createNewUser(String username, String password) {
        home.toStartingPage();

        SignUpPO signUpPO = home.toSignUp();

        IndexPO indexPO = signUpPO.signUP(username, password);
        assertNotNull(indexPO);

        return indexPO;
    }

    @BeforeEach
    public void initTest() {

        getDriver().manage().deleteAllCookies();

        home = new IndexPO(getDriver(), getServerHost(), getServerPort());
        userHome = new UserPO(getDriver(), getServerHost(), getServerPort());

        home.toStartingPage();

        assertTrue(home.isOnPage(), "Failed to start from home page");
    }

    @Test
    public void testCreateAndLogoutAndLogInUser() {
        assertFalse(home.isLoggedIn());

        String userID = getUniqueId();
        String password = "123456";

        home = createNewUser(userID, password);

        assumeTrue(home.isLoggedIn());
        assumeTrue(home.getDriver().getPageSource().contains(userID));

        home.doLogout();

        assertFalse(home.isLoggedIn());
        assertFalse(home.getDriver().getPageSource().contains(userID));
    }

    @Test
    public void testDisplayHomePage() {

        assertTrue(home.isOnPage());
        assertEquals(20, home.getNumberOfItemsDisplayed());
    }

    @Test
    public void testEmptyCollection() {
        assertFalse(home.isLoggedIn());

        String userID = getUniqueId();
        String password = "123456";

        home = createNewUser(userID, password);

        assumeTrue(home.isLoggedIn());
        assumeTrue(home.getDriver().getPageSource().contains(userID));

        home.toUserPage();

        assertTrue(home.getDriver().getPageSource().contains(userID));

        assertEquals(1, home.getNumberOfItemsDisplayed());
    }

    @Test
    public void testRedeemLootBox() {
        assertFalse(home.isLoggedIn());

        String userID = getUniqueId();
        String password = "123456";

        home = createNewUser(userID, password);

        assumeTrue(home.isLoggedIn());
        assumeTrue(home.getDriver().getPageSource().contains(userID));

        home.toUserPage();
        assertTrue(home.getDriver().getPageSource().contains(userID));

        String lootboxValue = home.getText("lootboxValue");
        assertEquals(1, home.getNumberOfItemsDisplayed());
        assertEquals("3", lootboxValue.split(" ")[1]);

        for(int i=0; i<2; i++){
            userHome.redeemLootbox();
        }

        lootboxValue = home.getText("lootboxValue");
        assertEquals(2, home.getNumberOfItemsDisplayed());
        assertEquals("1", lootboxValue.split(" ")[1]);
    }

    @Test
    public void testFailedReedemLootBox() {
        assertFalse(home.isLoggedIn());
        String userID = getUniqueId();
        String password = "123456";
        home = createNewUser(userID, password);
        assumeTrue(home.isLoggedIn());
        assumeTrue(home.getDriver().getPageSource().contains(userID));
        home.toUserPage();
        assertTrue(home.getDriver().getPageSource().contains(userID));


        String lootboxValue = home.getText("lootboxValue");
        assertEquals("3", lootboxValue.split(" ")[1]);
        assertEquals(1, home.getNumberOfItemsDisplayed());

        for(int i=0; i<3; i++){
            userHome.redeemLootbox();
        }

        lootboxValue = home.getText("lootboxValue");
        assertEquals("0", lootboxValue.split(" ")[1]);
        assertEquals(3, home.getNumberOfItemsDisplayed());

        assertTrue(home.getDriver().getPageSource().contains("You don't have more lootboxes"));
    }

    @Test
    public void testMillItem() {
        assertFalse(home.isLoggedIn());
        String userID = getUniqueId();
        String password = "123456";
        home = createNewUser(userID, password);
        assumeTrue(home.isLoggedIn());
        assumeTrue(home.getDriver().getPageSource().contains(userID));
        home.toUserPage();
        assertTrue(home.getDriver().getPageSource().contains(userID));

        assertEquals(1, home.getNumberOfItemsDisplayed());

        for(int i=0; i<3; i++){
            userHome.redeemLootbox();
        }

        String currencyValue = home.getText("currencyValue").split(" ")[1];
        assertEquals("100", currencyValue);
        assertEquals(3, home.getNumberOfItemsDisplayed());

        userHome.millLootbox();
        currencyValue = home.getText("currencyValue").split(" ")[1];
        assertNotEquals("100", currencyValue);
        assertEquals(2, home.getNumberOfItemsDisplayed());



    }

    @Test
    public void testBuyLootbox() {
        assertFalse(home.isLoggedIn());

        String userID = getUniqueId();
        String password = "123456";

        home = createNewUser(userID, password);

        assumeTrue(home.isLoggedIn());
        assumeTrue(home.getDriver().getPageSource().contains(userID));

        home.toUserPage();
        assertTrue(home.getDriver().getPageSource().contains(userID));


        String currencyValue = home.getText("currencyValue").split(" ")[1];
        String lootboxValue = home.getText("lootboxValue").split(" ")[1];

        assertEquals("100", currencyValue);
        assertEquals("3", lootboxValue);

        userHome.buyLootbox();

        currencyValue = home.getText("currencyValue").split(" ")[1];
        lootboxValue = home.getText("lootboxValue").split(" ")[1];

        assertEquals("0", currencyValue);
        assertEquals("4", lootboxValue);
    }

    @Autowired
    ItemService itemService;

    @Test
    public void testSearch() {
        List<Item> allItems = itemService.getAllItems(false);
        Item firstItem = allItems.get(0);
        home = home.searchOnPage("byValue", firstItem.getValue().toString());

        assertTrue(home.isInFirstColumn(firstItem.getId().toString()));

        home.toStartingPage();
        home = home.searchOnPage("byName", firstItem.getName());

        assertTrue(home.isInFirstColumn(firstItem.getId().toString()));
    }
}
