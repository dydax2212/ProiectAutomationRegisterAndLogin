package HelperMethods;

import Logger.LoggerUtility;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Set;

public class CommonMethods {
    WebDriver driver;
    String mainWindowHandle;

    public CommonMethods(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        Assert.assertTrue(element.isDisplayed(), "Element is not visible!");
    }

    public void waitUntilElementIsPresent(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
            Assert.assertTrue(element.isDisplayed(), "Element is not visible!");
        } catch (Exception e) {
            System.out.println("Element is not visible or interactable: " + e.getMessage());
        }
    }

    public void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Waited for " + seconds + " seconds.");
    }

    public void verifyUrl(String expectedUrl) {
        String actualUrl = driver.getCurrentUrl();

        ChainTestListener.log("Verify URL: " + actualUrl);
        Assert.assertEquals(actualUrl, expectedUrl, "The actual URL does not match the expected URL!");
    }

    public void waitForNewWindow(int expectedWindows) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> driver.getWindowHandles().size() >= expectedWindows);
    }

    public String switchToGoogleLoginWindow() {
        String mainWindowHandle = driver.getWindowHandle(); // Salvează handle-ul ferestrei inițiale

        // Așteptăm până când apare o nouă fereastră
        waitForNewWindow(2);

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(windowHandle);
                LoggerUtility.infoTest("Switched to Google Login window: " + windowHandle);
                ChainTestListener.log("Switched to Google Login window.");
                return mainWindowHandle; // Returnează handle-ul ferestrei principale
            }
        }
        throw new RuntimeException("Google Login window not found!");// Dacă nu găsește fereastra
    }

    public void switchBackToMainWindow() {
        if (mainWindowHandle == null) {
            throw new IllegalStateException("Main window handle is null! Make sure you switched windows first.");
        }

        Set<String> allWindows = driver.getWindowHandles();

        if (!allWindows.contains(mainWindowHandle)) {
            throw new NoSuchElementException("Main window handle not found! The login window might have closed unexpectedly.");
        }

        driver.switchTo().window(mainWindowHandle);
        LoggerUtility.infoTest("Switched back to the main application window: " + mainWindowHandle);
        ChainTestListener.log("Returned to the main application window.");
    }
}
