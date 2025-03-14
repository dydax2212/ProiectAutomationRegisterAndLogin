package Tests;

import HelperMethods.ElementsMethods;
import ObjectData.GoogleLoginFormObjectData;
import Pages.GoogleLoginPage;
import Pages.HomePage;
import ShareDataBrowser.Hooks;
import dataBase.Queries.GmailLoginForm;
import org.testng.annotations.Test;
import xmlReaderUtility.xmlReader;

import java.sql.SQLException;
import java.util.Map;

public class GoogleLoginTests extends Hooks
{
    ElementsMethods elementsMethods;
    HomePage homePage;
    GoogleLoginPage googleLoginPage;

    private Map<String, GoogleLoginFormObjectData> googleLoginFormObjectDataMap;

    @Test
    public void metodaTest() throws SQLException
    {
        googleLoginFormObjectDataMap = xmlReader.loadData("src/test/resources/gmailLoginData.xml", GoogleLoginFormObjectData.class);
        GoogleLoginFormObjectData data = googleLoginFormObjectDataMap.get("dataSet_1");

        elementsMethods = new ElementsMethods(getDriver());
        homePage = new HomePage(getDriver());
        googleLoginPage = new GoogleLoginPage(getDriver());

        homePage.acceptCookies();
        homePage.clickOnContulMeu();
        homePage.clickOnLogin();

        googleLoginPage.clickGoogleLogin();
        googleLoginPage.switchToGoogleLoginWindow();



        googleLoginPage.addEntryInForm(data);
      //  googleLoginPage.updateEntryInForm(data,3);

       // googleLoginPage.switchBackToMainWindow();

    }
}
