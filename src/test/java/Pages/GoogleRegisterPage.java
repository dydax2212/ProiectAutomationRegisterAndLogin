package Pages;

import HelperMethods.ElementsMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class GoogleRegisterPage
{
    WebDriver driver;
    ElementsMethods elementsMethods;

    //Elemente



    public GoogleRegisterPage(WebDriver driver)
    {
        this.driver = driver;
        this.elementsMethods = new ElementsMethods(driver);
        PageFactory.initElements(driver,this);
    }

    //Metode



}
