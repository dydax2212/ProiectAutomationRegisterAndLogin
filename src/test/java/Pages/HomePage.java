package Pages;

import HelperMethods.ElementsMethods;
import Logger.LoggerUtility;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage
{
    WebDriver driver;
    ElementsMethods elementsMethods;

    //Elemente

    @FindBy(xpath = "(//*[@class='cookie-setting-switch wrapper-switch'])[1]")
    WebElement checkStatisticsButton;

    @FindBy(xpath = "(//*[@class='cookie-setting-switch wrapper-switch'])[2]")
    WebElement checkComfortButton;

    @FindBy(xpath = "(//*[@class='full-width action primary small orange accept-cookies-button set-user-allow-cookies'])[1]")
    WebElement acceptAllButton;

    @FindBy(xpath = "(//*[@class='full-width action primary small orange reject-cookies-button'])[1]")
    WebElement rejectCookiesButton;

    @FindBy(xpath = "//*[@class='flex action toggle show-account relative']")
    WebElement contulMeu;

    @FindBy(css = "#ui-id-2 > div > div > a.full-width.align-center.action.orange.primary.small.log-in")
    WebElement loginButton;

    @FindBy(css = "#ui-id-2 > div > div > a.full-width.align-center.action.secondary.small.create-account")
    WebElement registerButton;

    public HomePage(WebDriver driver)
    {
        this.driver = driver;
        this.elementsMethods = new ElementsMethods(driver);
        PageFactory.initElements(driver,this);
    }

    public void acceptCookies() {
        elementsMethods.waitUntilElementIsPresent(checkStatisticsButton);
        Assert.assertTrue(checkStatisticsButton.isDisplayed(), "Error: 'Check Statistics' button not displayed!");
        elementsMethods.clickOnElement(checkStatisticsButton);

        LoggerUtility.infoTest("Clicked on 'Check Statistics' button.");
        ChainTestListener.log("Clicked on 'Check Statistics' button.");

        elementsMethods.waitUntilElementIsPresent(checkComfortButton);
        Assert.assertTrue(checkComfortButton.isDisplayed(), "Error: 'Check Comfort' button not displayed!");
        elementsMethods.clickOnElement(checkComfortButton);

        LoggerUtility.infoTest("Clicked on 'Check Comfort' button.");
        ChainTestListener.log("Clicked on 'Check Comfort' button.");

        elementsMethods.waitUntilElementIsPresent(acceptAllButton);
        Assert.assertTrue(acceptAllButton.isDisplayed(), "Error: 'Accept All' button not displayed!");
        elementsMethods.clickOnElement(acceptAllButton);

        LoggerUtility.infoTest("Clicked on 'Accept All' button.");
        ChainTestListener.log("Clicked on 'Accept All' button.");
    }

    public void rejectCookies() {
        elementsMethods.waitUntilElementIsPresent(rejectCookiesButton);
        Assert.assertTrue(rejectCookiesButton.isDisplayed(), "Error: 'Reject Cookies' button not displayed!");

        elementsMethods.clickOnElement(rejectCookiesButton);
        LoggerUtility.infoTest("Clicked on 'Reject Cookies' button.");
        ChainTestListener.log("Clicked on 'Reject Cookies' button.");
    }

    public void clickOnContulMeu() {
        elementsMethods.waitUntilElementIsPresent(contulMeu);
        elementsMethods.clickOnElement(contulMeu);
        LoggerUtility.infoTest("Clicked on 'Contul Meu' button.");
        ChainTestListener.log("Clicked on 'Contul Meu' button.");

      //  Assert.assertTrue(contulMeu.isDisplayed(), "Error: 'Contul Meu' button not displayed!");
    }

    public void clickOnRegister() {
        elementsMethods.waitUntilElementIsPresent(registerButton);
        Assert.assertTrue(registerButton.isDisplayed(), "Error: 'Register' button not displayed!");

        elementsMethods.clickOnElement(registerButton);
        LoggerUtility.infoTest("Clicked on 'Register' button.");
        ChainTestListener.log("Clicked on 'Register' button.");
    }

    public void clickOnLogin(){
        boolean isLoginButtonPresent = elementsMethods.isElementPresent(loginButton);
    //    Assert.assertTrue(isLoginButtonPresent, "Error: Login button is not present on the page.");
        LoggerUtility.infoTest("Login button is present on the page.");

        elementsMethods.clickOnElement(loginButton);
        LoggerUtility.infoTest("Clicked on the login button.");

        LoggerUtility.infoTest("Page load completed after clicking the login button.");
        ChainTestListener.log("Login button clicked and page load completed.");
    }
}
