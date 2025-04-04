package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.HomePage;

public class NavbarTest extends BaseTest {

    @Test
    public void testNavBarLinks() throws InterruptedException {

        HomePage homePage = new HomePage(driver, wait);
        homePage.testAllMenuItems();
    }
}
