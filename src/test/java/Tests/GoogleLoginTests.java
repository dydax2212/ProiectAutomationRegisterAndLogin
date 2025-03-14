package Tests;

import HelperMethods.ElementsMethods;
import ObjectData.GoogleLoginFormObjectData;
import Pages.GoogleRegisterPage;
import Pages.HomePage;
import ShareDataBrowser.Hooks;
import org.testng.annotations.Test;
import xmlReaderUtility.xmlReader;

import java.sql.SQLException;
import java.util.Map;

public class GoogleLoginTests extends Hooks
{
    ElementsMethods elementsMethods;
    HomePage homePage;
    GoogleRegisterPage googleRegisterPage;

    private Map<String, GoogleLoginFormObjectData> googleLoginFormObjectDataMap;

    @Test
    public void metodaTest() throws SQLException
    {
        googleLoginFormObjectDataMap = xmlReader.loadData("src/test/resources/registerLoginData.xml", GoogleLoginFormObjectData.class);
        GoogleLoginFormObjectData data = googleLoginFormObjectDataMap.get("dataSet_1");

        elementsMethods = new ElementsMethods(getDriver());
        homePage = new HomePage(getDriver());
        googleRegisterPage = new GoogleRegisterPage(getDriver());

        homePage.acceptCookies();
        homePage.clickOnContulMeu();
        homePage.clickOnLogin();

        googleRegisterPage.clickGoogleLogin();
        googleRegisterPage.switchToGoogleLoginWindow();



        googleRegisterPage.addEntryInForm(data);
      //  googleLoginPage.updateEntryInForm(data,3);

       // googleLoginPage.switchBackToMainWindow();

    }
}
