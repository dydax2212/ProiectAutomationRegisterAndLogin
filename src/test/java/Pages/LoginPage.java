package Pages;

import HelperMethods.ElementsMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage
{
    WebDriver driver;
    ElementsMethods elementsMethods;

    //Elemente

    @FindBy(xpath = "//*[@class='action secondary medium orange']")
    WebElement registerButton;

    @FindBy(xpath = "//*[@id='email-input']")
    WebElement emailField;

    @FindBy(xpath = "//*[@id='password-input']")
    WebElement passwordField;

    @FindBy(xpath = "//*[@id='gigya-remember']")
    WebElement rememberMeButton;

    @FindBy(xpath = "//*[@class='action primary medium full-width']")
    WebElement continueButton;



    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
        this.elementsMethods = new ElementsMethods(driver);
        PageFactory.initElements(driver,this);
    }

    //Metode

}
