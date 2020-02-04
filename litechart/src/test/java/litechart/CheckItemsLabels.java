package litechart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CheckItemsLabels extends TestBase {

    @Test
    public void checkItemsLabels() {
        wd.get("http://localhost/litecart/en/");

        for (int i = 0; i < getProducts().size(); i++) {
            List<WebElement> items = wd.findElements(By.cssSelector("li.product"));
            for (WebElement item : items) {
                List<WebElement> stickers = item.findElements(By.cssSelector("div.sticker"));
                assertEquals("Product has more than 1 sticker.",1, stickers.size());
            }
        }
    }
}
