package no.enterprise.exam.selenium;

/*
    https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/frontend/src/test/java/org/tsdes/intro/exercises/quizgame/selenium/PageObject.java
 */

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class PageObject {

    protected final WebDriver driver;
    protected final String host;
    protected final int port;

    public PageObject(WebDriver driver, String host, int port) {
        this.driver = driver;
        this.host = host;
        this.port = port;
    }

    public PageObject(PageObject other) {
        this(other.getDriver(), other.getHost(), other.getPort());
    }

    public abstract boolean isOnPage();

    public WebDriver getDriver() {
        return driver;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public void clickAndWait(String id) {
        WebElement element = driver.findElement(By.id(id));
        element.click();
        try {
            Thread.sleep(200);
        } catch (Exception ignored) {
        }
        waitForPageToLoad();
        try {
            Thread.sleep(300);
        } catch (Exception ignored) {
        }
    }

    public String getText(String id) {
        return driver.findElement(By.id(id)).getText();
    }

    public void setText(String id, String text) {
        WebElement element = driver.findElement(By.id(id));
        element.clear();
        element.click();
        element.sendKeys(text);

        assertEquals(text, element.getAttribute("value"));
    }


    protected void waitForPageToLoad() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, 10); //give up after 10 seconds

        //keep executing the given JS till it returns "true", when page is fully loaded and ready
        wait.until((ExpectedCondition<Boolean>) input -> {
            String res = jsExecutor.executeScript("return /loaded|complete/.test(document.readyState);").toString();
            return Boolean.parseBoolean(res);
        });
    }

}
