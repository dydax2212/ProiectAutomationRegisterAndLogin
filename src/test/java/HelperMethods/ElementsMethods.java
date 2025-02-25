package HelperMethods;

import com.aventstack.chaintest.plugins.ChainTestListener;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ElementsMethods
{
    WebDriver driver;

    public ElementsMethods(WebDriver driver)
    {
        this.driver = driver;
    }

    public void clickOnElement(WebElement element)
    {
        Assert.assertTrue(element.isDisplayed(), "Element is not visible!");
        Assert.assertTrue(element.isEnabled(), "Element is not clickable!");

        try
        {
            element.click();
        } catch (Exception e)
        {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public void hoverOnElement(WebElement element)
    {
        Assert.assertTrue(element.isDisplayed(), "Element is not visible!");

        Actions action = new Actions(driver);
        action.moveToElement(element).perform();

    }

    public void waitForElementToBeClickable(WebElement element)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));

        Assert.assertTrue(element.isDisplayed(), "Element is not visible!");
    }

    public void waitUntilElementIsPresent(WebElement element)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try
        {
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
            Assert.assertTrue(element.isDisplayed(), "Element is not visible!");
        } catch (Exception e)
        {
            ChainTestListener.log("Element is not visible or interactable: " + e.getMessage());
        }
    }

    public void waitForSeconds(int seconds)
    {
        try
        {
            Thread.sleep(seconds * 500L);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        ChainTestListener.log("Waited for " + seconds + " seconds.");
    }

    public void scrollToHalfPage()
    {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight / 2);");
        ChainTestListener.log("Successfully scrolled to the middle of the page.");
    }

    public void scrollToElement(WebElement element)
    {
        Assert.assertTrue(element.isDisplayed(), "Element is not visible!");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }

    public boolean isDisplayed(WebElement element)
    {
        return element.isDisplayed();
    }

    public boolean isElementPresent(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void fillElement(WebElement element, String text)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(element));
        Assert.assertTrue(element.isDisplayed(), "Element is not visible!");
        Assert.assertTrue(element.isEnabled(), "Element is not interactable!");

        String textelement = element.getAttribute("value");
        Assert.assertNotNull(textelement, "Text attribute is null!");
        Assert.assertTrue(textelement.isEmpty(), "Field is not empty!");

        element.sendKeys(text);
        wait.until(ExpectedConditions.attributeToBe(element, "value", text));

        Assert.assertEquals(text, element.getAttribute("value"), "Text input failed!");
        ChainTestListener.log("Filled element with text: " + text);
    }

    public void sendKeys(WebElement element, String text)
    {
        Assert.assertTrue(element.isDisplayed(), "Element is not visible!");
        Assert.assertTrue(element.isEnabled(), "Element is not interactable!");

        element.clear();
        element.sendKeys(text);

        Assert.assertEquals(element.getAttribute("value"), text, "Text input does not match expected!");
        ChainTestListener.log("Sent keys: '" + text + "' to element.");
    }

    public String getText(WebElement element)
    {
        Assert.assertTrue(element.isDisplayed(), "Element is not visible!");

        String text = element.getText();
        Assert.assertNotNull(text, "Element text is null!");
        Assert.assertFalse(text.isEmpty(), "Element text is empty!");

        ChainTestListener.log("Extracted text: " + text);
        return text;
    }

    public void scrollByPixels(int pixels)
    {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + pixels + ");");
        ChainTestListener.log("Scrolled by " + pixels + " pixels.");
    }
}
