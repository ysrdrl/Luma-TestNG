package StepDefinitions;

import Pages.CollectiveLocators;
import Utilities.GWD;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class _02_ShoppingCart extends GWD {

    @Test
    public static void shoppingCartFunctionality() {
        CollectiveLocators cl = new CollectiveLocators();

        cl.findAndSend("searchBox", "Messenger Bug");
        cl.findAndClick("searchButton");
        cl.findAndWait("productItem", "Rival Field Messenger");

        cl.findAndClick("productItem");
        cl.findAndClick("addToCard");

        GWD.getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("img[alt='Loading...'][title='Loading...']")));

        cl.findAndClick("shoppingCartButton");
        cl.findAndClick("viewAndEditCart");


    }


}
