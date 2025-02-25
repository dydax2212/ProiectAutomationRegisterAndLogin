package ShareDataBrowser.Browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeService implements BrowserServiceInterface
{
    private WebDriver driver;

    @Override
    public void openBrowser()
    {
        ChromeOptions options = (ChromeOptions) browserOptions();
        driver = new ChromeDriver(options);
    }
    @Override
    public Object browserOptions()
    {
        String ci_cd = System.getProperty("ci_cd");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--window-size=2560,1440");
        options.addArguments("--disable-blink-features=BlockCredentialedSubresources");

        //Helps with Google login to not be detected for automation testing
        options.addArguments("--disable-blink-features=AutomationControlled");

        // Optional: Set a real user-agent to avoid detection
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");

        if (Boolean.parseBoolean(ci_cd))
            options.addArguments("--headless");

        return options;
    }

    public WebDriver getDriver()
    {
        return driver;
    }
}
