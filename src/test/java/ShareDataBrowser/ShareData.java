package ShareDataBrowser;

import ShareDataBrowser.Browser.BrowserFactory;
import org.openqa.selenium.WebDriver;

public class ShareData
{
    private WebDriver driver;

    public void initializeBrowser()
    {
        driver = new BrowserFactory().getBrowserFactory();
        driver.get("https://www.dedeman.ro/ro");
        driver.manage().window().maximize();
    }

    public void clearBrowser()
    {
        driver.quit();
    }

    public WebDriver getDriver()
    {
        return driver;
    }
}
