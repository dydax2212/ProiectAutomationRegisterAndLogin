package HelperMethods;

import Logger.LoggerUtility;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class JavascriptMethods
{
    WebDriver driver;

    public JavascriptMethods(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollToHalfPage() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight / 2);");
    }

    public void scrollToElement(WebElement element) {
        Assert.assertTrue(element.isDisplayed(), "Element is not visible!");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }

    public void scrollByPixels(int pixels) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + pixels + ");");
    }

    public void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public void forceClick(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
            LoggerUtility.infoTest("Forced click performed on element: " + element);
            ChainTestListener.log("Forced click executed on element.");
        } catch (Exception e) {
            throw new RuntimeException("Failed to force click on element: " + e.getMessage());
        }
    }
}