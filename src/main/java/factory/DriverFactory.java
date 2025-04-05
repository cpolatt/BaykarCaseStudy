package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;

public class DriverFactory {
    public WebDriver driver;
    public WebDriverWait wait;

    public WebDriver initDriver(Properties prop) {

        driver = new ChromeDriver();
        driver.get(prop.getProperty("url"));
        driver.manage().deleteAllCookies();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();
        return driver;

    }
}
