package pomp_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage {
    private final By name = By.cssSelector("#txtName, input[name*='name' i], input[id*='name' i]");
    private final By email = By.cssSelector("#txtEmail, input[type='email'], input[name*='email' i], input[id*='email' i]");
    private final By phone = By.cssSelector("#txt_mobile, input[type='tel'], input[name*='mobile' i], input[id*='mobile' i], input[name*='phone' i], input[id*='phone' i]");
    private final By password = By.cssSelector("#txtPassword, input[type='password'], input[name*='password' i], input[id*='password' i]");
    private final By company = By.cssSelector("input[name*='company' i], input[id*='company' i], input[name*='organization' i], input[id*='organization' i]");
    private final By termsCheckbox = By.cssSelector("#checkbox-signup, input[type='checkbox'][id*='terms' i], input[type='checkbox'][name*='terms' i]");
    private final By teamsPlan = By.xpath("//*[self::label or self::span or self::div or self::button][contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'calley teams') or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'team')]");
    private final By submit = By.cssSelector("#btnSignUp, button[type='submit'], input[type='submit'], button[id*='register' i], input[id*='register' i]");
    private final By planContinue = By.xpath("//button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'continue') or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'start') or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'select')] | //input[contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'continue') or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'start') or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'select')]");
    private final By successMessage = By.xpath("//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'success') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'registered') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'dashboard')]");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void open(String url) {
        driver.get(url);
    }

    public void register(String userName, String userEmail, String userPhone, String userPassword, String companyName) {
        typeFirst(userName, name);
        typeFirst(userEmail, email);
        typeFirst(userPhone, phone);
        typeFirst(userPassword, password);
        typeIfPresent(company, companyName);
        clickIfPresent(termsCheckbox);
        clickIfPresent(teamsPlan);
        clickFirst(submit);
        clickIfPresent(teamsPlan);
        clickIfPresent(planContinue);
    }

    public boolean isRegistrationSuccessful() {
        return isVisible(successMessage) || driver.getCurrentUrl().toLowerCase().contains("dashboard");
    }
}
