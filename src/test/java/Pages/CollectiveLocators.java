package Pages;

import Utilities.GWD;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CollectiveLocators extends Parent {
    @FindBy(linkText = "Sign In")
    public WebElement signIN;

    @FindBy(id = "email")
    public WebElement email;

    @FindBy(name = "login[password]")
    public WebElement password;

    @FindBy(xpath = "(//span[contains(text(),'Sign In')])[1]")
    public WebElement signInButton;

    @FindBy(xpath = "(//span[contains(text(),'Welcome')])[1]")
    public WebElement welcome;

    @FindBy(id = "search")
    public WebElement searchBox;

    @FindBy(css = "button[class='action search']")
    public WebElement searchButton;

    @FindBy(xpath = "(//a[@class='product-item-link'])[2]")
    public WebElement productItem;

    @FindBy(id = "product-addtocart-button")
    public WebElement addToCard;

    @FindBy(xpath = "(//span[@class='counter qty'])[1]")
    public WebElement shoppingCartButton;

    @FindBy(xpath = "//span[contains(@data-bind,'View and Edit Cart')]")
    public WebElement viewAndEditCart;

    @FindBy(id = "ui-id-4")
    public WebElement women;

    public CollectiveLocators() {

        PageFactory.initElements(GWD.getDriver(),this);
    }


    private WebElement myElement;

    public void findAndClick(String element) {
        switch (element) {
            case "signIN":
                myElement = signIN;
                break;
            case "signInButton":
                myElement = signInButton;
                break;
            case "women":
                myElement = women;
                break;
            case "searchButton":
                myElement = searchButton;
                break;
            case "addToCard":
                myElement = addToCard;
                break;
            case "shoppingCartButton":
                myElement = shoppingCartButton;
                break;
            case "viewAndEditCart":
                myElement = viewAndEditCart;
                break;
        }
        clickFunction(myElement);
    }

    public void findAndSend(String element, String value) {
        switch (element) {
            case "email":
                myElement = email;
                break;
            case "password":
                myElement = password;
                break;
            case "searchBox":
                myElement = searchBox;
                break;
        }
        sendKeysFunction(myElement, value);
    }

    public void findAndWait(String element,String text){
        switch (element) {
            case "welcome":
                myElement = welcome;
                break;
            case "productItem":
                myElement = productItem;
                break;
        }
        verifyContainsText(myElement,text);
    }
    public void findAndWait(WebElement element,String text){

        verifyContainsText(myElement,text);
    }

    public void scrollInto(WebElement element) {

        JavascriptExecutor js=(JavascriptExecutor) GWD.getDriver();

        js.executeScript("arguments[0].scrollIntoView()", element); // elemente kadar scroll yapar

        //js.executeScript("arguments[0].click()", element); // elemente click yapma


    }


}
