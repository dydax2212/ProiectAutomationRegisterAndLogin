package Pages;

import HelperMethods.ElementsMethods;
import ObjectData.RegisterFormObjectData;
import dataBase.Queries.RegisterLoginForm;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.sql.SQLException;

public class RegisterPage
{
    WebDriver driver;
    ElementsMethods elementsMethods;
    RegisterFormObjectData registerFormObjectData;

    //Elemente

    @FindBy(xpath = "(//*[@id='email-input']")
    WebElement emailField;

    @FindBy(xpath = "(//*[@id='register-init-submit']")
    WebElement continueButton;

    @FindBy(css = "#maincontent > div.columns > div > div.register-page-wrap > div.gigya-form-box > form.gigya-form.gigya-register-email-form > fieldset > div:nth-child(1) > input")
    WebElement numePrenumeField;

    @FindBy(css = "#maincontent > div.columns > div > div.register-page-wrap > div.gigya-form-box > form.gigya-form.gigya-register-email-form > fieldset > div:nth-child(1) > input")
    WebElement phoneNumberField;

    @FindBy(css = "#maincontent > div.columns > div > div.register-page-wrap > div.gigya-form-box > form.gigya-form.gigya-register-email-form > fieldset > div:nth-child(1) > input")
    WebElement passwordField;

    @FindBy(css = "#maincontent > div.columns > div > div.register-page-wrap > div.gigya-form-box > form.gigya-form.gigya-register-email-form > fieldset > div:nth-child(1) > input")
    WebElement repeatPassowrdField;

    @FindBy(xpath = "//*[@id='age_confirmed_email']")
    WebElement ofAgeCheckbox;

    @FindBy(xpath = "//*[@id='email-newsletter']")
    WebElement newslettersCheckbox;

    @FindBy(css = "#maincontent > div.columns > div > div.register-page-wrap > div.gigya-form-box > form.gigya-form.gigya-register-email-form > button.action.primary.medium.full-width")
    WebElement continueRegisterButton;

    public RegisterPage(WebDriver driver){
        this.driver = driver;
        this.elementsMethods = new ElementsMethods(driver);
        registerFormObjectData = new RegisterFormObjectData();
        PageFactory.initElements(driver,this);
    }

    //Metode

    public void enterEmailField(RegisterFormObjectData data){
        elementsMethods.waitUntilElementIsPresent(emailField);
        elementsMethods.sendTextToField(emailField, data.getEmail());

        elementsMethods.waitForElementToBeClickable(continueButton);
        elementsMethods.clickOnElement(continueButton);
    }

    public void enterFullName(RegisterFormObjectData data){
        elementsMethods.waitUntilElementIsPresent(numePrenumeField);
        elementsMethods.sendTextToField(numePrenumeField, data.getFullName());
    }

    public void enterPhoneNumber(RegisterFormObjectData data){
        elementsMethods.waitUntilElementIsPresent(phoneNumberField);
        elementsMethods.sendTextToField(phoneNumberField, data.getPhoneNumber());
    }

    public void enterPassword1(RegisterFormObjectData data){
        elementsMethods.waitUntilElementIsPresent(passwordField);
        elementsMethods.sendTextToField(passwordField, data.getPassword1());
    }

    public void enterPassword2(RegisterFormObjectData data){
        elementsMethods.waitUntilElementIsPresent(repeatPassowrdField);
        elementsMethods.sendTextToField(repeatPassowrdField, data.getPassword2());
    }

    public void fillRegisterFields(RegisterFormObjectData data){
        enterFullName(data);
        enterPhoneNumber(data);
        enterPassword1(data);
        enterPassword2(data);

        elementsMethods.clickOnElement(ofAgeCheckbox);
        elementsMethods.clickOnElement(newslettersCheckbox);
        elementsMethods.clickOnElement(continueRegisterButton);
    }
}
