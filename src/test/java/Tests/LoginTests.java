package Tests;

import HelperMethods.ElementsMethods;
import ShareDataBrowser.Hooks;
import org.testng.annotations.Test;

public class LoginTests extends Hooks
{
    ElementsMethods elementsMethods;

    @Test
    public void metodaTest()
    {
        elementsMethods = new ElementsMethods(getDriver());
    }
}
