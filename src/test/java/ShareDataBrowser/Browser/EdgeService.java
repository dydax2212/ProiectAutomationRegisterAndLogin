package ShareDataBrowser.Browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeService implements BrowserServiceInterface
{
    private WebDriver driver;

    @Override
    public void openBrowser()
    {
        EdgeOptions options = (EdgeOptions) browserOptions();
        driver = new EdgeDriver(options);
    }
    @Override
    public Object browserOptions()
    {
        String ci_cd = System.getProperty("ci_cd");

        EdgeOptions options = new EdgeOptions();
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--window-size=2560,1440");
        options.addArguments("--disable-blink-features=BlockCredentialedSubresources");

        options.addArguments("--disable-blink-features=AutomationControlled");

        // Additional flags to reduce CAPTCHA triggers
        options.addArguments("--disable-features=IsolateOrigins,site-per-process");
        options.addArguments("--disable-web-security");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-features=MediaSessionService");
        options.addArguments("--disable-extensions");

        // Optional: Set a real user-agent to avoid detection
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36 Edg/119.0.0.0");

        if (Boolean.parseBoolean(ci_cd))
            options.addArguments("--headless");

        return options;
    }

    public WebDriver getDriver()
    {
        return driver;
    }
}
