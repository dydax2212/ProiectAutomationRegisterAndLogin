package HelperMethods;

import Logger.LoggerUtility;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.Random;

public class ElementsMethods extends CommonMethods {
    WebDriver driver;
    Actions actions;

    public ElementsMethods(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    private String getElementInfo(WebElement element) {
        return element.toString().split("->")[1]; // Extracts locator information
    }

    public void clickOnElement(WebElement element) {
        Assert.assertTrue(element.isDisplayed(), "Element is not visible!");
        Assert.assertTrue(element.isEnabled(), "Element is not clickable!");

        try {
            LoggerUtility.infoTest("Clicking on element: " + getElementInfo(element));
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public void sendTextToField(WebElement element, String text){
        element.sendKeys(text);
    }

    public void hoverOnElement(WebElement element) {
        Assert.assertTrue(element.isDisplayed(), "Element is not visible!");
        actions.moveToElement(element).perform();
    }

    public boolean isElementPresent(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void moveSlider(WebElement sliderHandle, int maxOffset) {
        Actions actions = new Actions(driver);
        Random random = new Random();

        int xOffset = random.nextBoolean() ? maxOffset : -maxOffset;

        actions.clickAndHold(sliderHandle)
                .moveByOffset(xOffset, 0)
                .release()
                .perform();

        LoggerUtility.infoTest("Slider moved by " + xOffset + " pixels.");
        ChainTestListener.log("Slider adjusted by " + xOffset + " pixels.");
    }

    public String getText(WebElement element) {
        Assert.assertTrue(element.isDisplayed(), "Element is not visible!");

        String text = element.getText();
        Assert.assertNotNull(text, "Element text is null!");
        Assert.assertFalse(text.isEmpty(), "Element text is empty!");

        LoggerUtility.infoTest("Element text is: " + text);
        ChainTestListener.log("Extracted text: " + text);
        return text;
    }
}
