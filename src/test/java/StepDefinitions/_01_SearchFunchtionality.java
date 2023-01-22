package StepDefinitions;

import Pages.CollectiveLocators;
import Utilities.GWD;
import org.testng.annotations.Test;

public class _01_SearchFunchtionality extends GWD {
    @Test
    public static void searchFunctionality() {
        CollectiveLocators cl=new CollectiveLocators();
        cl.findAndSend("searchBox","Messenger Bug");
        cl.findAndClick("searchButton");
        cl.findAndWait("productItem","Rival Field Messenger");

    }


}
