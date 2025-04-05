package tests;

import ConfigReader.ConfigPropReader;
import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LanguagePage;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class LanguageTest {

    DriverFactory df;
    ConfigPropReader cp;
    Properties prop;
    WebDriver driver;
    LanguagePage languagePage;

    @BeforeTest
    public void setUp() {
        cp = new ConfigPropReader();
        prop = cp.initLangProp("english");
        df = new DriverFactory();
        driver = df.initDriver(prop);
        languagePage = new LanguagePage(driver);
    }

    @Test
    public void headerTest() {
        Assert.assertTrue(languagePage.isHeaderExist(prop.getProperty("header")));
        Assert.assertTrue(languagePage.isHeader2Exist(prop.getProperty("header2")));
    }

    @Test
    public void navMenuItemTest(){
        List<String> navItems = Arrays.asList(
                prop.getProperty("navMenuItem1"),
                prop.getProperty("navMenuItem2"),
                prop.getProperty("navMenuItem3"),
                prop.getProperty("navMenuItem4")
        );
        for (String item : navItems) {
            Assert.assertTrue(languagePage.isNavMenuItemExist(item));
        }
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
