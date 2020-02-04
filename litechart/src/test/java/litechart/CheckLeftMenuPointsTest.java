package litechart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;

public class CheckLeftMenuPointsTest extends TestBase {

    WebDriverWait wait;

    @Test
    public void checkLeftMenu() {

        login();
        wait = new WebDriverWait(wd, 30/*seconds*/);
        wait.until(invisibilityOfElementLocated(By.cssSelector("div#notices")));

        //клик на первый пункт меню
        wait.until(wd -> wd.findElement(By.cssSelector("ul.list-vertical a"))).click();

        for (int i = 0; i < getLeftMenuSize(); i++) {
            if (areElementsPresent(wd, By.cssSelector("ul.docs"))) {
                List<String> menuSubTitles = getMenuItemSubitem();
                for (String menuSubTitle : menuSubTitles) {
                    wait.until(wd -> wd.findElement(By.xpath(String.format("//span[text()='%s']", menuSubTitle)))).click();
                    assertTrue("Title is not presented on the current page",
                            isElementPresent(wd, By.cssSelector("h1")));
                }
            } else {
                assertTrue("Title is not presented on the current page",
                            isElementPresent(wd, By.cssSelector("h1")));
            }
            //переключаемся на следующий пункт меню
            wait.until(wd -> wd.findElement(By.xpath("//li[@id='app-' and @class='selected']/following-sibling::li")))
                    .click();
        }
    }
}
