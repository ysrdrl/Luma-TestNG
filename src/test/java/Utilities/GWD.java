package Utilities;

import Pages.CollectiveLocators;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GWD {
    private static WebDriverWait wait;
    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
    private static ThreadLocal<String> threadBrowserName = new ThreadLocal<>();


    public static WebDriver getDriver() {
        if (threadBrowserName.get() != null) {

            if (threadDriver.get() == null) {
                String browserName = threadBrowserName.get();
//                System.out.println("threadBrowserName:" + threadBrowserName.get());

                switch (browserName) {
                    case "chrome":
                        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
                        WebDriverManager.chromedriver().setup();

                        ChromeOptions options = new ChromeOptions();
                        options.addArguments("--remote-allow-origins=*");
//                        options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu", "--window-size=1400,2400");

                        threadDriver.set(new ChromeDriver(options));
                        break;

                    case "firefox":
                        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
                        WebDriverManager.firefoxdriver().setup();
                        threadDriver.set(new FirefoxDriver());
                        break;

                    case "safari":
                        WebDriverManager.safaridriver().setup();
                        threadDriver.set(new SafariDriver());
                        break;

                    case "edge":
                        WebDriverManager.edgedriver().setup();
                        threadDriver.set(new EdgeDriver());
                        break;

                }
            }
            Locale.setDefault(new Locale("EN"));
            System.setProperty("user.language", "EN");

            Logger.getLogger("").setLevel(Level.SEVERE);
            System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "Error");
        }

        return threadDriver.get();
    }

    @BeforeClass
    @Parameters("browser")
    public void baslangicIslemleri(@Optional("chrome") String browser) {
//        System.out.println("Browser Name:" + browser);
        threadBrowserName.set(browser);

        getDriver().manage().window().maximize(); // max
        getDriver().manage().deleteAllCookies();  //

        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));

        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30)); // sadece ana sayfa yüklenirken en başta
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // bütün webElement için geçerli
        getDriver().get("https://magento.softwaretestingboard.com/");
        login();
    }


    public void login() {
        CollectiveLocators cl = new CollectiveLocators();

        cl.findAndClick("signIN");
        cl.findAndSend("email", "durgun.kemal@gmail.com");
        cl.findAndSend("password", "Kemal.Durgun");
        cl.findAndClick("signInButton");
        cl.findAndWait("welcome", "Welcome");
    }

    @AfterClass
    public static void quitDriver() {
        Bekle(2);
        if (getDriver() != null) {
            GWD.getDriver().quit();

            WebDriver driver = threadDriver.get();
            driver = null;
            threadDriver.set(driver);
        } else {
            if (GWD.getDriver() == null) {
                GWD.getDriver().quit();
            }
        }
    }

    public static void Bekle(int saniye) {

        try {
            Thread.sleep(saniye * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public static WebDriverWait getWait() {
        if (wait == null) {
            wait = new WebDriverWait(GWD.getDriver(), Duration.ofSeconds(30));
        }
        return wait;
    }

}
