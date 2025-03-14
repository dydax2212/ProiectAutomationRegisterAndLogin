package Pages;

import HelperMethods.ElementsMethods;
import HelperMethods.JavascriptMethods;
import Logger.LoggerUtility;
import ObjectData.GoogleLoginFormObjectData;
import com.aventstack.chaintest.plugins.ChainTestListener;
import dataBase.Queries.GmailLoginForm;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.sql.SQLException;
import java.util.Set;

public class GoogleLoginPage
{
    WebDriver driver;
    ElementsMethods elementsMethods;
    String mainWindowHandle;
    GmailLoginForm gmailLoginForm;
    JavascriptMethods javascriptMethods;

    //Elemente

    @FindBy(xpath = "//*[@data-provider='googleplus']")
    WebElement googleLoginButton;

    @FindBy(css = "#identifierId")
    WebElement gmailField;

    @FindBy(css = "#identifierNext > div > button > span")
    WebElement nextStepButton;

    @FindBy(xpath = "//*[@class='whsOnd zHQkBf'][1]")
    WebElement gmailPasswordField;

    @FindBy(xpath = "//*[@class='VfPpkd-muHVFf-bMcfAe']")
    WebElement showPasswordToggle;

    @FindBy(css = "#passwordNext > div > button")
    WebElement gmailContinueButton;

    public GoogleLoginPage(WebDriver driver) throws SQLException {
        this.driver = driver;
        this.elementsMethods = new ElementsMethods(driver);
        this.gmailLoginForm = new GmailLoginForm();
        this.javascriptMethods = new JavascriptMethods(driver);
        PageFactory.initElements(driver,this);
    }

    //Metode

    public void clickGoogleLogin()
    {
        elementsMethods.waitUntilElementIsPresent(googleLoginButton);
        javascriptMethods.forceClick(googleLoginButton);
    }

    public void switchToGoogleLoginWindow() {
        mainWindowHandle = elementsMethods.switchToGoogleLoginWindow();
    }

    public void switchBackToMainWindow() {
        if (mainWindowHandle == null) {
            throw new IllegalStateException("Main window handle is null! Make sure you switched windows first.");
        }
        elementsMethods.switchBackToMainWindow();
        LoggerUtility.infoTest("Switched back to the main window: " + mainWindowHandle);
        ChainTestListener.log("Returned to the main application window.");
    }

    public void addEntryInForm(GoogleLoginFormObjectData data) throws SQLException {
        gmailLoginForm.insertGoogleObject(data);
    }

    public void updateEntryInForm(GoogleLoginFormObjectData data, Integer id) throws SQLException {
        gmailLoginForm.updateEntryById(data, id);
    }
}
