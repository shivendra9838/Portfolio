import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Properties;

public class BaseClass {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static Properties data;

    @BeforeSuite(alwaysRun = true)
    public void setUpSuite() throws IOException {
        data = loadProperties();
        String browser = System.getProperty("browser", data.getProperty("browser", "chrome"));
        boolean headless = Boolean.parseBoolean(data.getProperty("headless", "false"));

        driver = createDriver(browser, headless);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        wait = new WebDriverWait(driver, Duration.ofSeconds(getTimeoutSeconds()));
    }

    @AfterMethod(alwaysRun = true)
    public void captureFailureScreenshot(ITestResult result) throws IOException {
        if (!result.isSuccess() && driver instanceof TakesScreenshot) {
            Files.createDirectories(Path.of("test-output", "screenshots"));
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String stamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            Path target = Path.of("test-output", "screenshots", result.getName() + "_" + stamp + ".png");
            Files.copy(screenshot.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownSuite() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected static String getData(String key) {
        return data.getProperty(key);
    }

    protected static String getAbsolutePath(String path) {
        return Path.of(path).toAbsolutePath().normalize().toString();
    }

    protected static String uniqueEmail(String email) {
        int atIndex = email.indexOf("@");
        String stamp = String.valueOf(System.currentTimeMillis());
        if (atIndex < 1) {
            return "automation." + stamp + "@example.com";
        }
        return email.substring(0, atIndex) + "+" + stamp + email.substring(atIndex);
    }

    private static Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        try (FileInputStream stream = new FileInputStream("src/main/resources/data.properties")) {
            properties.load(stream);
        }
        return properties;
    }

    private static int getTimeoutSeconds() {
        return Integer.parseInt(data.getProperty("timeoutSeconds", "25"));
    }

    private static WebDriver createDriver(String browser, boolean headless) {
        String normalizedBrowser = browser.toLowerCase(Locale.ROOT).trim();
        switch (normalizedBrowser) {
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) {
                    edgeOptions.addArguments("--headless=new");
                }
                return new EdgeDriver(edgeOptions);
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    firefoxOptions.addArguments("-headless");
                }
                return new FirefoxDriver(firefoxOptions);
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                }
                chromeOptions.addArguments("--remote-allow-origins=*");
                return new ChromeDriver(chromeOptions);
        }
    }

    protected static WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected static WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected static void click(By locator) {
        waitForClickable(locator).click();
    }

    protected static void type(By locator, String value) {
        WebElement element = waitForVisible(locator);
        element.clear();
        element.sendKeys(value);
    }

    protected static void selectByVisibleText(By locator, String visibleText) {
        new Select(waitForVisible(locator)).selectByVisibleText(visibleText);
    }

    protected static boolean isVisible(By locator) {
        try {
            waitForVisible(locator);
            return true;
        } catch (TimeoutException exception) {
            return false;
        }
    }

    protected static By firstAvailable(By... locators) {
        for (By locator : locators) {
            if (!driver.findElements(locator).isEmpty()) {
                return locator;
            }
        }
        throw new TimeoutException("None of the provided locators were found.");
    }

    protected static void clickFirst(By... locators) {
        click(firstAvailable(locators));
    }

    protected static void typeFirst(String value, By... locators) {
        type(firstAvailable(locators), value);
    }
}
