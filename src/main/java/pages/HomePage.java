package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "ul[class='navbar-nav'] li[class='nav-item dropdown']")
    WebElement dropDownElement;

    @FindBy(css = "ul[class='navbar-nav'] li[class='nav-item dropdown'] .dropdown-item")
    List<WebElement> dropdownItems;

    @FindBy(css = "ul[class='navbar-nav'] li[class='nav-item'] .nav-link")
    List<WebElement> navLinks;

    @FindBy(css = "ul[class='navbar-nav'] div[class='offcanvas-button'] .nav-link")
    List<WebElement> navButtons;

    // Sayfa geçişi doğrulaması yapmak için yardımcı metot
    private boolean isPageLoaded(String currentUrl) {
        try {
            // Sayfanın yüklendiğini doğrulamak için URL değişimi yapılabilir
            return !driver.getCurrentUrl().equals(currentUrl);
        } catch (Exception e) {
            return false;
        }
    }

    // Dropdown öğelerini tıklayıp doğrulama yapmak
    public void testDropdownItems() throws InterruptedException {
        for (int i = 0; i < dropdownItems.size(); i++) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(dropDownElement));
                dropDownElement.click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[class='nav-item dropdown'] .dropdown-item")));

                List<WebElement> currentItems = driver.findElements(By.cssSelector("li[class='nav-item dropdown'] .dropdown-item"));
                WebElement currentLink = currentItems.get(i);

                String linkText = currentLink.getText().trim();
                String hrefBefore = driver.getCurrentUrl();

                System.out.println("Dropdown item tıklanıyor: " + linkText);

                wait.until(ExpectedConditions.elementToBeClickable(currentLink));
                currentLink.click();
                Thread.sleep(1000);

                if (isPageLoaded(hrefBefore)) {
                    System.out.println(" → Sayfa geçişi başarılı. Geri dönülüyor...");
                    driver.navigate().back();
                    Thread.sleep(1500);
                } else {
                    System.out.println(" → Sayfa içi geçiş (anchor).");
                    Thread.sleep(500);
                }

            } catch (StaleElementReferenceException e) {
                System.out.println("Dropdown elemanı değişti. Yeniden alınıyor...");
                dropdownItems = driver.findElements(By.cssSelector("li[class='nav-item dropdown'] .dropdown-item"));
                i--;
            } catch (Exception e) {
                System.out.println("Hata oluştu: " + e.getMessage());
            }
        }
    }

    // Nav öğelerini tıklayıp doğrulama yapmak
    public void testNavLinks() throws InterruptedException {
        for (int i = 0; i < navLinks.size(); i++) {
            try {
                navLinks = driver.findElements(By.cssSelector("li[class='nav-item'] .nav-link"));
                WebElement currentLink = navLinks.get(i);

                if (currentLink.getAttribute("class").contains("dropdown-toggle")) {
                    continue;
                }

                String linkText = currentLink.getText().trim();
                String hrefBefore = driver.getCurrentUrl();

                System.out.println("Nav link tıklanıyor → " + linkText);
                wait.until(ExpectedConditions.elementToBeClickable(currentLink));
                currentLink.click();
                Thread.sleep(1000);

                if (isPageLoaded(hrefBefore)) {
                    System.out.println(" → Sayfa geçişi başarılı. Geri dönülüyor...");
                    driver.navigate().back();
                    Thread.sleep(1500);
                } else {
                    System.out.println(" → Sayfa içi geçiş (anchor).");
                    Thread.sleep(500);
                }

            } catch (StaleElementReferenceException e) {
                System.out.println("Nav link değişti. Yeniden alınıyor...");
                i--;
            } catch (Exception e) {
                System.out.println("Hata oluştu: " + e.getMessage());
            }
        }
    }

    // Offcanvas butonlarını tıklayıp doğrulama yapmak
    public void testOffcanvasButtons() throws InterruptedException {
        for (int i = 0; i < navButtons.size(); i++) {
            try {
                navButtons = driver.findElements(By.cssSelector("div[class='offcanvas-button'] .nav-link"));
                WebElement currentButton = navButtons.get(i);

                String buttonText = currentButton.getText().trim();
                String hrefBefore = driver.getCurrentUrl();

                System.out.println("Offcanvas button tıklanıyor → " + buttonText);
                wait.until(ExpectedConditions.elementToBeClickable(currentButton));
                currentButton.click();
                Thread.sleep(1000);

                if (isPageLoaded(hrefBefore)) {
                    System.out.println(" → Sayfa geçişi başarılı. Geri dönülüyor...");
                    driver.navigate().back();
                    Thread.sleep(1500);
                } else {
                    System.out.println(" → Sayfa içi geçiş veya popup olabilir.");
                    Thread.sleep(500);
                }

            } catch (StaleElementReferenceException e) {
                System.out.println("Offcanvas buton değişti. Yeniden alınıyor...");
                i--;
            } catch (Exception e) {
                System.out.println("Hata oluştu: " + e.getMessage());
            }
        }
    }

    // Ana metot, tüm menüleri test etmek için
    public void testAllMenuItems() throws InterruptedException {
        testDropdownItems();
        testNavLinks();
        testOffcanvasButtons();
    }
}



