package litechart;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestBase {

    protected static final App app = new App();
    protected WebDriver wd;

    @Before()
    public void setUp() throws Exception {
        wd = app.init();
    }

    @After()
    public void tearDown() throws Exception {
        app.stop();
    }

    protected boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    protected boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }

    /**********************************ADMIN PANEL METHODS*****************************/

    protected void login() {
        wd.get("http://localhost/litecart/admin/login.php");
        wd.findElement(By.name("username")).sendKeys("admin");
        wd.findElement(By.name("password")).sendKeys("admin");
        wd.findElement(By.name("login")).click();
    }

    protected int getLeftMenuSize() {
        List<WebElement> menuItems = wd.findElements(By.id("app-"));
        return menuItems.size();
    }

    protected int getSubMenuSize() {
        List<WebElement> menuSubItems = wd.findElements(By.cssSelector("ul.docs li"));
        return menuSubItems.size();
    }

    protected List<String> getMenuItemSubitem() {
        List<WebElement> menuSubItems = wd.findElements(By.cssSelector("ul.docs li"));
        List<String> subitemTitles = new ArrayList<>();
        for (WebElement menuSubItem : menuSubItems) {
            subitemTitles.add(menuSubItem.getText());
        }
        return subitemTitles;
    }

    protected Map<String, String> getAllCountriesWithZones() {
        List<WebElement> rows = wd.findElements(By.cssSelector("tr.row"));
        Map<String, String> countriesAndZones = new HashMap<>();
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String country = cells.get(4).getText();
            String zonesNumber = cells.get(5).getText();
            countriesAndZones.put(country, zonesNumber);
        }
        return countriesAndZones;
    }

    protected List<String> getAllCountries() {
        List<WebElement> rows = wd.findElements(By.cssSelector("tr.row"));
        List<String> countries = new ArrayList<>();
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String country = cells.get(4).getText();
            countries.add(country);
        }
        return countries;
    }

    protected List<String> getAllZones() {
        List<WebElement> rows = wd.findElements(By.cssSelector("table#table-zones tr:not(.header)"));
        List<String> zones = new ArrayList<>();
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.cssSelector("td"));
            String zoneName = cells.get(2).findElement(By.cssSelector("input")).getAttribute("value");
            if (!zoneName.equals("")) {
                zones.add(zoneName);
            }
        }
        return zones;
    }

    /**********************************MAIN PAGE METHODS*****************************/

    protected List<WebElement> getProducts() {
        List<WebElement> items = wd.findElements(By.cssSelector("li.product"));
        return items;
    }
}
