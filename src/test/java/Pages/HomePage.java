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

    public void acceptCookies()
    {
        elementsMethods.waitUntilElementIsPresent(checkStatisticsButton);
        elementsMethods.clickOnElement(checkStatisticsButton);

        elementsMethods.waitUntilElementIsPresent(checkComfortButton);
        elementsMethods.clickOnElement(checkComfortButton);

        elementsMethods.waitUntilElementIsPresent(acceptAllButton);
        elementsMethods.clickOnElement(acceptAllButton);
    }

    public  void rejectCookies()
    {
        elementsMethods.waitUntilElementIsPresent(rejectCookiesButton);
        elementsMethods.clickOnElement(rejectCookiesButton);
    }

    public void clickOnContulMeu()
    {
        elementsMethods.waitUntilElementIsPresent(contulMeu);
        elementsMethods.clickOnElement(contulMeu);
    }

    public void clickOnRegister()
    {
        elementsMethods.waitUntilElementIsPresent(registerButton);
        elementsMethods.clickOnElement(registerButton);
    }

    public void clickOnLogin(){
        boolean isLoginButtonPresent = elementsMethods.isElementPresent(loginButton);
        Assert.assertTrue(isLoginButtonPresent, "Error: Login button is not present on the page.");
        LoggerUtility.infoTest("Login button is present on the page.");

        elementsMethods.clickOnElement(loginButton);
        LoggerUtility.infoTest("Clicked on the login button.");

        LoggerUtility.infoTest("Page load completed after clicking the login button.");
        ChainTestListener.log("Login button clicked and page load completed.");
    }
}
