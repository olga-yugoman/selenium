package litechart;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

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

    protected void login() {
        wd.get("http://localhost/litecart/admin/login.php");
        wd.findElement(By.name("username")).sendKeys("admin");
        wd.findElement(By.name("password")).sendKeys("admin");
        wd.findElement(By.name("login")).click();
    }

    protected List<String> getLeftMenuMainItems() {
        List<WebElement> menuItems = wd.findElements(By.id("app-"));
        List<String> itemTitles = new ArrayList<>();
        for (WebElement menuItem : menuItems) {
            itemTitles.add(menuItem.getText());
        }
        return itemTitles;
    }

    protected List<String> getMenuItemSubitem(String menuTitle) {
        List<WebElement> menuSubItems = wd.findElements(By.cssSelector("ul.docs li"));
        List<String> subitemTitles = new ArrayList<>();
        for (WebElement menuSubItem : menuSubItems) {
            subitemTitles.add(menuSubItem.getText());
        }
        return subitemTitles;
    }
}