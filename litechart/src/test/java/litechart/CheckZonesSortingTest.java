package litechart;

import com.google.common.collect.Ordering;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class CheckZonesSortingTest extends TestBase {

    @Test
    public void CheckCountriesSortingTest() {
        login();
        wd.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        //получаем список стран, у которых есть зоны
        List<String> countriesWithZones = new ArrayList<>();
        for (Map.Entry entry : getAllCountriesWithZones().entrySet()) {
            if (!entry.getValue().equals("0")) {
                countriesWithZones.add(entry.getKey().toString());
            }
        }

        //проверяем, что список зон в у каждой страны отсортирован в алфавитном порядке
        for (String country : countriesWithZones) {
            wd.findElement(By.xpath(String.format("//a[text()='%s']", country))).click();
            List<String> zones = getAllZones();
            assertTrue("Zones are not sorted in A-Z order.", Ordering.natural().isOrdered(zones));
            wd.navigate().back();
        }
    }
}
