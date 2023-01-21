package StepDefinitions;

import Pages.CollectiveLocators;
import Utilities.GWD;
import org.testng.annotations.Test;

public class _01_SearchFunchtionality extends GWD {

    CollectiveLocators cl=new CollectiveLocators();
    @Test
    public void searchFunctionality() {
        cl.findAndSend("searchBox","Messenger Bug");
        cl.findAndClick("searchButton");
        cl.findAndWait("productItem","Rival Field Messenger");


    }


}
