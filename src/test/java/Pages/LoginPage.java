package Pages;

import HelperMethods.ElementsMethods;
import Logger.LoggerUtility;
import ObjectData.RegisterFormObjectData;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage
{
    WebDriver driver;
    ElementsMethods elementsMethods;
    RegisterFormObjectData registerFormObjectData;

    //Elemente

    @FindBy(xpath = "//*[@class='action secondary medium orange']")
    WebElement loginButton;

    @FindBy(xpath = "//*[@id='email-input']")
    WebElement emailField;

    @FindBy(xpath = "//*[@id='password-input']")
    WebElement passwordField;

    @FindBy(css = "#maincontent > div.page.messages")
    WebElement errorMessage;

    @FindBy(css = "#maincontent > div.columns > div > div.login-container > div.gigya-form-box > form > fieldset > div.password-actions > div > span")
    WebElement rememberMeButton;

    @FindBy(xpath = "//*[@id=\"maincontent\"]/div[2]/div/div[1]/div[2]/form/fieldset/button")
    WebElement continueButton;

    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
        this.elementsMethods = new ElementsMethods(driver);
        registerFormObjectData = new RegisterFormObjectData();
        PageFactory.initElements(driver,this);
    }

    //Metode

    public void enterEmailField(RegisterFormObjectData data) {
        elementsMethods.waitUntilElementIsPresent(emailField);
        elementsMethods.clickOnElement(emailField);
        elementsMethods.sendTextToField(emailField, data.getEmail());
        LoggerUtility.infoTest("Email entered: " + data.getEmail());

        String emailValue = emailField.getAttribute("value");
        assert emailValue != null;
        Assert.assertFalse(emailValue.isEmpty(), "Error: Email field is empty!");
        LoggerUtility.infoTest("Email field filled successfully with value: " + emailValue);

        ChainTestListener.log("Email entered successfully.");
    }

    public void enterPasswords(RegisterFormObjectData data) {
        elementsMethods.waitUntilElementIsPresent(passwordField);
        elementsMethods.sendTextToField(passwordField, data.getPassword1());

        LoggerUtility.infoTest("Password entered: " + data.getPassword1());

        ChainTestListener.log("Password entered and verified.");
    }

    public void finishLogin() {
        elementsMethods.actionsClick(rememberMeButton);
        LoggerUtility.infoTest("Clicked on 'Remember me' button.");

        elementsMethods.clickOnElement(continueButton);
        LoggerUtility.infoTest("Clicked on 'Continue' button.");

        ChainTestListener.log("'Remember me' and 'Continue' buttons clicked.");
    }

    public void pageLoad()
    {
        elementsMethods.waitUsingThreadSleep(6000);
        elementsMethods.verifyUrl("https://www.dedeman.ro/ro/customer/account");
    }

    public void waitForErrorMessageAndValidate() {
        String expectedMessage = "Nume de utilizator sau parolă incorectă.";

        By errorMessageLocator = By.xpath("//*[contains(text(), '" + expectedMessage + "')]");
        WebElement errorMessageElement = elementsMethods.findElementAndWait(errorMessageLocator, 10); // Timeout de 10 secunde

        String actualMessage = errorMessageElement.getText();

        LoggerUtility.infoTest("Actual error message: " + actualMessage);
        Assert.assertEquals(actualMessage, expectedMessage, "Error: Password or email is wrong!");
        ChainTestListener.log("Error message displayed correctly: " + actualMessage);
    }
}
