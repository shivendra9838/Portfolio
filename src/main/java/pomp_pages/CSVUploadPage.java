package pomp_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CSVUploadPage extends BasePage {
    private final By listName = By.cssSelector("input[name*='list' i], input[id*='list' i]");
    private final By agentCheckbox = By.cssSelector("input[type='checkbox']");
    private final By fileInput = By.cssSelector("input[type='file']");
    private final By nextButton = By.xpath("//button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'next') or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'upload') or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'submit')] | //input[@type='submit']");
    private final By importButton = By.xpath("//button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'import')] | //input[contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'import')]");
    private final By successMessage = By.xpath("//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'success') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'imported') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'uploaded')]");

    public CSVUploadPage(WebDriver driver) {
        super(driver);
    }

    public void uploadList(String name, String csvAbsolutePath) {
        typeFirst(name, listName);
        selectFirstAgentIfPresent();
        waitForVisible(fileInput).sendKeys(csvAbsolutePath);
        clickFirst(nextButton);
        mapFieldsIfDropdownsArePresent();
        clickFirst(importButton, nextButton);
    }

    public boolean isUploadSuccessful() {
        return isVisible(successMessage);
    }

    private void selectFirstAgentIfPresent() {
        List<WebElement> checkboxes = driver.findElements(agentCheckbox);
        if (!checkboxes.isEmpty() && !checkboxes.get(0).isSelected()) {
            checkboxes.get(0).click();
        }
    }

    private void mapFieldsIfDropdownsArePresent() {
        List<WebElement> dropdowns = driver.findElements(By.tagName("select"));
        String[] preferredValues = {"Name", "Phone", "Email", "Notes"};
        for (int i = 0; i < dropdowns.size() && i < preferredValues.length; i++) {
            Select select = new Select(dropdowns.get(i));
            selectByTextIfAvailable(select, preferredValues[i]);
        }
    }

    private void selectByTextIfAvailable(Select select, String text) {
        for (WebElement option : select.getOptions()) {
            if (option.getText().equalsIgnoreCase(text)) {
                select.selectByVisibleText(option.getText());
                return;
            }
        }
    }
}
