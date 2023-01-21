package Pages;

import Utilities.GWD;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class Parent {

    public void sendKeysFunction(WebElement element, String value){

        waitUntilVisible(element);//Gözükene Kadar Bekle
        scrollToElement(element);//Elemente Scroll Yap
        element.clear();//Kutucuğu Temizle
        element.sendKeys(value);//Değeri Gönder
    }

    public void waitUntilVisible(WebElement element){
        //Beklemeyi Yap
        WebDriverWait wait=new WebDriverWait(GWD.getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(element));

        }

    public void scrollToElement(WebElement element){
        //Kaydırmayı Yap
        JavascriptExecutor js=(JavascriptExecutor) GWD.getDriver();
        js.executeScript("arguments[0].scrollIntoView();",element);
    }

    public void clickFunction(WebElement element){
        waitUntilClickable(element);//Gözükene Kadar Bekle
            scrollToElement(element);//Elemente Scroll Yap
            element.click();//Click Yap
    }

    public void waitUntilClickable(WebElement element){
        //Beklemeyi Yap
        WebDriverWait wait=new WebDriverWait(GWD.getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public void verifyContainsText (WebElement element,String text){
        waitUntilVisible(element);
        Assert.assertTrue(element.getText().toLowerCase().contains(text.toLowerCase()));
    }
    public List<WebElement> waitUntilVisibleAll(List<WebElement> element){
        //Beklemeyi Yap
        WebDriverWait wait=new WebDriverWait(GWD.getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
        return element;
    }








}
