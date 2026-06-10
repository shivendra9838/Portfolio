package pomp_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }

    protected WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void clickFirst(By... locators) {
        waitForClickable(firstAvailable(locators)).click();
    }

    protected void typeFirst(String value, By... locators) {
        WebElement element = waitForVisible(firstAvailable(locators));
        element.clear();
        element.sendKeys(value);
    }

    protected boolean isVisible(By locator) {
        try {
            waitForVisible(locator);
            return true;
        } catch (TimeoutException exception) {
            return false;
        }
    }

    protected void clickIfPresent(By locator) {
        if (!driver.findElements(locator).isEmpty()) {
            waitForClickable(locator).click();
        }
    }

    protected void typeIfPresent(By locator, String value) {
        if (!driver.findElements(locator).isEmpty()) {
            WebElement element = waitForVisible(locator);
            element.clear();
            element.sendKeys(value);
        }
    }

    protected By firstAvailable(By... locators) {
        for (By locator : locators) {
            if (!driver.findElements(locator).isEmpty()) {
                return locator;
            }
        }
        throw new TimeoutException("None of the provided locators were found.");
    }
}
