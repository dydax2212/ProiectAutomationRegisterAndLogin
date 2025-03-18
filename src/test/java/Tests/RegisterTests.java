package Tests;

import HelperMethods.ElementsMethods;
import ObjectData.RegisterFormObjectData;
import Pages.HomePage;
import Pages.RegisterPage;
import ShareDataBrowser.Hooks;
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

        homePage.acceptCookies();
        homePage.clickOnContulMeu();
        homePage.clickOnRegister();

        registerPage.enterEmailField(data);
        registerPage.fillRegisterFields(data);

    }

    @Test
    public void negativeFlow() throws SQLException {
        registerFormObjectDataMap = xmlReader.loadData("src/test/resources/registerLoginData.xml", RegisterFormObjectData.class);
        RegisterFormObjectData data= registerFormObjectDataMap.get("dataSet_2");
        elementsMethods = new ElementsMethods(getDriver());
        homePage = new HomePage(getDriver());
        registerPage = new RegisterPage(getDriver());

        homePage.acceptCookies();
        homePage.clickOnContulMeu();
        homePage.clickOnRegister();

        registerPage.enterEmailField(data);
        registerPage.fillRegisterFields(data);

    }
}
