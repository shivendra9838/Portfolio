package pomp_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final By email = By.cssSelector("#txtEmailId, input[type='email'], input[name*='email' i], input[id*='email' i], input[name*='user' i], input[id*='user' i]");
    private final By password = By.cssSelector("#txtPassword, input[type='password'], input[name*='password' i], input[id*='password' i]");
    private final By loginButton = By.cssSelector("#btnLogIn, button[type='submit'], input[type='submit'], button[id*='login' i], input[id*='login' i]");
    private final By dashboardMarker = By.xpath("//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'dashboard') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'logout') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'call list')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open(String url) {
        driver.get(url);
    }

    public void login(String userEmail, String userPassword) {
        typeFirst(userEmail, email);
        typeFirst(userPassword, password);
        clickFirst(loginButton);
    }

    public boolean isLoginSuccessful() {
        return isVisible(dashboardMarker) || driver.getCurrentUrl().toLowerCase().contains("dashboard");
    }
}
