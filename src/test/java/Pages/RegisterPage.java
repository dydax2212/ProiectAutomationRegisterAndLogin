package Pages;

import HelperMethods.ElementsMethods;
import HelperMethods.JavascriptMethods;
import Logger.LoggerUtility;
import ObjectData.RegisterFormObjectData;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class RegisterPage
{
    WebDriver driver;
    ElementsMethods elementsMethods;
    JavascriptMethods javascriptMethods;
    RegisterFormObjectData registerFormObjectData;

    //Elemente

    @FindBy(xpath = "//*[@id='email-input']")
    WebElement emailField;

    @FindBy(xpath = "//*[@id='register-init-submit']")
    WebElement continueButton;

    @FindBy(css = "#maincontent > div.columns > div > div.register-page-wrap > div.gigya-form-box > form.gigya-form.gigya-register-email-form > fieldset > div:nth-child(1) > input")
    WebElement numePrenumeField;

    @FindBy(css = "#maincontent > div.columns > div > div.register-page-wrap > div.gigya-form-box > form.gigya-form.gigya-register-email-form > fieldset > div:nth-child(2) > div > input")
    WebElement phoneNumberField;

    @FindBy(css = "#email-password")
    WebElement passwordField;

    @FindBy(css = "#maincontent > div.columns > div > div.register-page-wrap > div.gigya-form-box > form.gigya-form.gigya-register-email-form > fieldset > div:nth-child(3) > div.password-strength-success.strong")
    WebElement strongPasswordMessage;

    @FindBy(css = "#email-confirm-password")
    WebElement repeatPasswordField;

    @FindBy(css = "#email-confirm-password-error")
    WebElement correctPasswordMessage;

    @FindBy(xpath = "(//*[@name='age_confirmed'])[1]")
    WebElement ofAgeCheckbox;

    @FindBy(xpath = "(//*[@name='newsletter'])[1]")
    WebElement newslettersCheckbox;

    @FindBy(css = "#maincontent > div.columns > div > div.register-page-wrap > div.gigya-form-box > form.gigya-form.gigya-register-email-form > button.action.primary.medium.full-width")
    WebElement continueRegisterButton;

    public RegisterPage(WebDriver driver){
        this.driver = driver;
        this.elementsMethods = new ElementsMethods(driver);
        this.javascriptMethods = new JavascriptMethods(driver);
        registerFormObjectData = new RegisterFormObjectData();
        PageFactory.initElements(driver,this);
    }

    //Metode

    public void enterEmailField(RegisterFormObjectData data) {
        elementsMethods.waitUntilElementIsPresent(emailField);
        elementsMethods.sendTextToField(emailField, data.getEmail());

        elementsMethods.waitForElementToBeClickable(continueButton);
        elementsMethods.clickOnElement(continueButton);

        Assert.assertNotNull(emailField.getAttribute("value"), "Error: Email field is empty!");
        ChainTestListener.log("Entered email: " + data.getEmail());
        LoggerUtility.infoTest("Email field filled successfully.");
    }

    public void enterFullName(RegisterFormObjectData data){
        elementsMethods.waitUntilElementIsPresent(numePrenumeField);
        elementsMethods.sendTextToField(numePrenumeField, data.getFullName());
    }

    public void enterPhoneNumber(RegisterFormObjectData data){
        elementsMethods.waitUntilElementIsPresent(phoneNumberField);
        elementsMethods.sendTextToField(phoneNumberField, data.getPhoneNumber());
    }

    public void enterPasswords(RegisterFormObjectData data){
        elementsMethods.waitUntilElementIsPresent(passwordField);
        elementsMethods.sendTextToField(passwordField, data.getPassword1());

        elementsMethods.waitUntilElementIsPresent(strongPasswordMessage);
        String actualMessage = strongPasswordMessage.getText();
        String expectedMessage = "Parola este sigură";
        Assert.assertEquals(actualMessage, expectedMessage, "Error: Password confirmation message is incorrect!");

        elementsMethods.waitUntilElementIsPresent(repeatPasswordField);
        elementsMethods.sendTextToField(repeatPasswordField, data.getPassword2());

        if(!data.getPassword1().equals(data.getPassword2())){
            String actualMessage2 = correctPasswordMessage.getText();
            String expectedMessage2 = "Parolele nu se potrivesc.";
            Assert.assertEquals(actualMessage2, expectedMessage2, "Error: Passwords don't match!");
        }

        ChainTestListener.log("Password fields filled and verified.");
        LoggerUtility.infoTest("Password entered successfully.");
    }

    public void fillRegisterFields(RegisterFormObjectData data){
        enterFullName(data);
        enterPhoneNumber(data);
        enterPasswords(data);

        ChainTestListener.log("Registration fields completed.");
        LoggerUtility.infoTest("User registration fields completed.");

        elementsMethods.actionsClick(ofAgeCheckbox);
        elementsMethods.actionsClick(newslettersCheckbox);
//        elementsMethods.clickOnElement(continueRegisterButton);
//
//        Assert.assertTrue(continueRegisterButton.isDisplayed(), "Error: Continue button is not displayed!");
//        ChainTestListener.log("Completed registration form.");
//        LoggerUtility.infoTest("User registration form submitted successfully.");
    }
}
