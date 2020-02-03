package litechart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CheckLeftMenuPointsTest extends TestBase {

    WebDriverWait wait;

    @Test
    public void checkLeftMenu() {

        login();
        wait = new WebDriverWait(wd, 10/*seconds*/);

        List<String> menuTitles = getLeftMenuMainItems();

        for (String menuTitle : menuTitles) {
            wait.until(wd -> wd.findElement(By.xpath(String.format("//span[text()='%s']", menuTitle)))).click();
            String pageTitle;
            if (areElementsPresent(wd, By.cssSelector("ul.docs"))) {
                List<String> menuSubTitles = getMenuItemSubitem();
                for (String menuSubTitle : menuSubTitles) {
                    wait.until(wd -> wd.findElement(By.xpath(String.format("//span[text()='%s']", menuSubTitle)))).click();
                    pageTitle = wd.findElement(By.cssSelector("h1")).getText();
                    assertEquals("Title of the page is not equal to submenu title.", menuSubTitle, pageTitle);
                }
            } else {
                pageTitle = wd.findElement(By.cssSelector("h1")).getText();
                assertEquals("Title of the page is not equal to menu title.", menuTitle, pageTitle);
            }
        }
    }
}
