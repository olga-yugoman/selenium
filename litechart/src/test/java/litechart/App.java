package litechart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class App {

    private WebDriver wd;

    public WebDriver init() throws IOException {
        if (wd == null) {
            wd = new ChromeDriver();
            wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }
        return wd;
    }

    public void stop() {
        wd.quit();
        wd = null;
    }
}
