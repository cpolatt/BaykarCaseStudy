package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ElementUtil;

public class LanguagePage {
    private WebDriver driver;
    private ElementUtil elementUtil;

    public LanguagePage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);

    }

    private WebElement getHeaderElement(String headerValue) {
        String headerXpathValue = "//h1[text()='" + headerValue + "']";
        return elementUtil.getElement("xpath", headerXpathValue);
    }

    public boolean isHeaderExist(String headerValue) {
        String header = getHeaderElement(headerValue).getText();
        System.out.println(header);
        return getHeaderElement(headerValue).isDisplayed();
    }

    private WebElement getHeader2Element(String headerValue) {
        String headerXpathValue = "//h3[text()='" + headerValue + "']";
        return elementUtil.getElement("xpath", headerXpathValue);
    }

    public boolean isHeader2Exist(String headerValue) {
        String header = getHeader2Element(headerValue).getText();
        System.out.println(header);
        return getHeader2Element(headerValue).isDisplayed();
    }

    private WebElement getNavMenuItemElement(String menuItemText) {
        String xpath = "//a[normalize-space(text())='" + menuItemText + "']";
        return elementUtil.getElement("xpath", xpath);
    }

    public boolean isNavMenuItemExist(String menuItemText) {
        WebElement element = getNavMenuItemElement(menuItemText);
        System.out.println("Menu Text: " + element.getText());
        return element.isDisplayed();
    }
}
