package litechart;

import com.google.common.collect.Ordering;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class CheckCountriesSortingTest extends TestBase {

    @Test
    public void CheckCountriesSortingTest() {
        login();
        wd.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        //проверяем, что страны в таблице отсортированы в алфавитном порядке
        List<String> countries = getAllCountries();
        assertTrue("Countries are not sorted in A-Z order.", Ordering.natural().isOrdered(countries));
    }
}
