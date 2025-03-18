package Tests;

import HelperMethods.ElementsMethods;
import Logger.LoggerUtility;
import ObjectData.RegisterFormObjectData;
import Pages.HomePage;
import Pages.LoginPage;
import ShareDataBrowser.Hooks;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.testng.annotations.Test;
import xmlReaderUtility.xmlReader;

import java.util.Map;

public class LoginTests extends Hooks
{
    ElementsMethods elementsMethods;
    HomePage homePage;
    LoginPage loginPage;
    private Map<String, RegisterFormObjectData> registerFormObjectDataMap;

    @Test
    public void happyFlow() {
        // Load data from the XML file
        registerFormObjectDataMap = xmlReader.loadData("src/test/resources/registerLoginData.xml", RegisterFormObjectData.class);
        RegisterFormObjectData data = registerFormObjectDataMap.get("dataSet_3");
        elementsMethods = new ElementsMethods(getDriver());
        homePage = new HomePage(getDriver());
        loginPage = new LoginPage(getDriver());

        // Accept cookies and navigate to the login page
        homePage.acceptCookies();
        LoggerUtility.infoTest("Cookies accepted.");

        homePage.clickOnContulMeu();
        LoggerUtility.infoTest("Clicked on 'Contul Meu'.");

        homePage.clickOnLogin();
        LoggerUtility.infoTest("Navigated to login page.");

        // Enter an email
        loginPage.enterEmailField(data);
        LoggerUtility.infoTest("Email entered: " + data.getEmail());

        // Enter a correct password
        loginPage.enterPasswords(data);
        LoggerUtility.infoTest("Password entered.");

        // Check "Remember me" option and click the continue button
        loginPage.finishLogin();
        LoggerUtility.infoTest("Clicked on 'Remember me' and 'Continue' buttons.");

        loginPage.pageLoad();
        LoggerUtility.infoTest("New page load.");

        // Log for ChainTestListener
        ChainTestListener.log("Happy flow test completed: User signed in successfully.");
    }

    @Test
    public void negativeFlow() {
        // Load data from the XML file
        registerFormObjectDataMap = xmlReader.loadData("src/test/resources/registerLoginData.xml", RegisterFormObjectData.class);
        RegisterFormObjectData data = registerFormObjectDataMap.get("dataSet_4");
        elementsMethods = new ElementsMethods(getDriver());
        homePage = new HomePage(getDriver());
        loginPage = new LoginPage(getDriver());

        // Accept cookies and navigate to the login page
        homePage.acceptCookies();
        LoggerUtility.infoTest("Cookies accepted.");

        homePage.clickOnContulMeu();
        LoggerUtility.infoTest("Clicked on 'Contul Meu'.");

        homePage.clickOnLogin();
        LoggerUtility.infoTest("Navigated to login page.");

        // Enter an email
        loginPage.enterEmailField(data);
        LoggerUtility.infoTest("Email entered: " + data.getEmail());

        // Enter an incorrect password
        loginPage.enterPasswords(data);

        // Check "Remember me" option and click the continue button
        loginPage.finishLogin();
        LoggerUtility.infoTest("Clicked on 'Remember me' and 'Continue' buttons.");

        loginPage.waitForErrorMessageAndValidate();
        LoggerUtility.infoTest("Error message received.");

        // Log for ChainTestListener
        ChainTestListener.log("Negative flow test completed: User login failed as expected.");
    }
}
