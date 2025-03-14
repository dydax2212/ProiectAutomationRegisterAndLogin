package Pages;

import HelperMethods.ElementsMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage
{
    WebDriver driver;
    ElementsMethods elementsMethods;

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

    public RegisterPage(WebDriver driver)
    {
        this.driver = driver;
        this.elementsMethods = new ElementsMethods(driver);
        PageFactory.initElements(driver,this);
    }

    //Metode


}
