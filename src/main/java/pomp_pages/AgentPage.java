package pomp_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AgentPage extends BasePage {
    private final By addAgent = By.xpath("//a[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'add') and contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'agent')] | //button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'add') and contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'agent')]");
    private final By name = By.cssSelector("input[name*='name' i], input[id*='name' i]");
    private final By email = By.cssSelector("input[type='email'], input[name*='email' i], input[id*='email' i]");
    private final By phone = By.cssSelector("input[type='tel'], input[name*='mobile' i], input[id*='mobile' i], input[name*='phone' i], input[id*='phone' i]");
    private final By password = By.cssSelector("input[type='password'], input[name*='password' i], input[id*='password' i]");
    private final By submit = By.cssSelector("button[type='submit'], input[type='submit'], button[id*='save' i], input[id*='save' i], button[id*='submit' i], input[id*='submit' i]");
    private final By successMessage = By.xpath("//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'success') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'added') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'agent')]");

    public AgentPage(WebDriver driver) {
        super(driver);
    }

    public void addAgent(String agentName, String agentEmail, String agentPhone, String agentPassword) {
        clickIfPresent(addAgent);
        typeFirst(agentName, name);
        typeFirst(agentEmail, email);
        typeFirst(agentPhone, phone);
        typeIfPresent(password, agentPassword);
        clickFirst(submit);
    }

    public boolean isAgentAdded() {
        return isVisible(successMessage);
    }
}
