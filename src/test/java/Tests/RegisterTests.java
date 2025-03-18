package Tests;

import HelperMethods.ElementsMethods;
import Logger.LoggerUtility;
import ObjectData.RegisterFormObjectData;
import Pages.HomePage;
import Pages.RegisterPage;
import ShareDataBrowser.Hooks;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.testng.annotations.Test;
import xmlReaderUtility.xmlReader;

import java.sql.SQLException;
import java.util.Map;

public class RegisterTests extends Hooks
{
    ElementsMethods elementsMethods;
    HomePage homePage;
    RegisterPage registerPage;
    private Map<String, RegisterFormObjectData> registerFormObjectDataMap;

    @Test
    public void happyFlow() throws SQLException {
        registerFormObjectDataMap = xmlReader.loadData("src/test/resources/registerLoginData.xml", RegisterFormObjectData.class);
        RegisterFormObjectData data= registerFormObjectDataMap.get("dataSet_1");
        elementsMethods = new ElementsMethods(getDriver());
        homePage = new HomePage(getDriver());
        registerPage = new RegisterPage(getDriver());

        LoggerUtility.infoTest("Test started: Registration on Dedeman website");
        ChainTestListener.log("Chrome opened");

        // Accept cookies and navigate to registration page
        homePage.acceptCookies();
        LoggerUtility.infoTest("Cookies accepted.");

        homePage.clickOnContulMeu();
        LoggerUtility.infoTest("Clicked on 'Contul Meu'.");

        homePage.clickOnRegister();
        LoggerUtility.infoTest("Clicked on 'Register'.");

        // Complete registration fields
        registerPage.enterEmailField(data);
        LoggerUtility.infoTest("Email entered: " + data.getEmail());

        registerPage.fillRegisterFields(data);
        LoggerUtility.infoTest("Registration fields filled.");

        LoggerUtility.infoTest("Successfully entered the registration fields.");
        ChainTestListener.log("Happy flow test completed.");
    }

    @Test
    public void negativeFlow() throws SQLException {
        registerFormObjectDataMap = xmlReader.loadData("src/test/resources/registerLoginData.xml", RegisterFormObjectData.class);
        RegisterFormObjectData data= registerFormObjectDataMap.get("dataSet_2");
        elementsMethods = new ElementsMethods(getDriver());
        homePage = new HomePage(getDriver());
        registerPage = new RegisterPage(getDriver());

        LoggerUtility.infoTest("Test started: Registration on Dedeman website");
        ChainTestListener.log("Chrome opened");

        // Accept cookies and navigate to registration page
        homePage.acceptCookies();
        LoggerUtility.infoTest("Cookies accepted.");

        homePage.clickOnContulMeu();
        LoggerUtility.infoTest("Clicked on 'Contul Meu'.");

        homePage.clickOnRegister();
        LoggerUtility.infoTest("Clicked on 'Register'.");

        // Complete registration fields
        registerPage.enterEmailField(data);
        LoggerUtility.infoTest("Email entered: " + data.getEmail());

        registerPage.fillRegisterFields(data);
        LoggerUtility.infoTest("Registration fields filled.");

        LoggerUtility.infoTest("User registration failed as expected for negative flow.");
        ChainTestListener.log("Negative flow test completed.");
    }
}
