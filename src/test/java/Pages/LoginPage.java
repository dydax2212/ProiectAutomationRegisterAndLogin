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

    @FindBy(xpath = "(//*[@class='onchange-validate'])[1]")
    WebElement emailField;

    @FindBy(xpath = "(//*[@class='onchange-validate'])[2]")
    WebElement passwordField;

    @FindBy(xpath = "//*[@class='checkmark']")
    WebElement rememberMeCheckbox;

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
