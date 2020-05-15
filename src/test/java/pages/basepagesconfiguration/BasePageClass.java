package pages.basepagesconfiguration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.log4testng.Logger;

import java.io.File;
import java.io.IOException;


public class BasePageClass {

    private static final int WAITER_TIME_OUT = 20;
    private static final int DRIVER_WAIT_TIME = 120;
    protected static WebDriver driver;
    protected static Logger logger = Logger.getLogger(BasePageClass.class);



    public WebDriver getDriver() {
        return driver;
    }

    public static void takeScreenshot()     {
        //take screenshot of the page
        File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(src, new File("Failed Test"));
        } catch (IOException e) {
            e.printStackTrace();
        }     }

    public WebDriver initializeDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\D\\SeleniumDrivers\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        maximizeWindow();
        return driver;
    }


    public WebElement waitForExpectedElement(By webElementLocator) {
        return new WebDriverWait(driver, WAITER_TIME_OUT)
                .until(ExpectedConditions.visibilityOfElementLocated(webElementLocator));
    }


    @BeforeClass
    public void setUp() {
        initializeDriver();
        logger.info("Set up Selenium Webdriver");
    }

    @AfterClass
    public void closeDown() {
        driver.quit();
    }


    public void waitPageIsLoadedAndJQueryIsProcessed() {
        waitPageIsLoaded();
        waitJQueryIsProcessed();
    }

    public void waitPageIsLoaded() {
        try {
            ExpectedCondition<Boolean> expectedCondition = driver -> getDocumentReadyState().equals("complete");
            Wait<WebDriver> wait = new WebDriverWait(getDriver(), DRIVER_WAIT_TIME);
            wait.until(expectedCondition);
        } catch (Exception ex) {
            logger.warn("Fail waiting for document ready state. Current state:" + getDocumentReadyState());
        }
    }

    private String getDocumentReadyState() {
        return (String) ((JavascriptExecutor) getDriver()).executeScript("return document.readyState");
    }

    private void waitJQueryIsProcessed() {
        new WebDriverWait(getDriver(), DRIVER_WAIT_TIME) {
        }.until((ExpectedCondition<Boolean>) driver -> (Boolean) ((JavascriptExecutor) driver)
                .executeScript("return typeof jQuery != 'undefined' && jQuery.active == 0"));
    }


    public void open(String url) {
        getDriver().get(url);
    }


    public void maximizeWindow() {
        driver.manage().window().maximize();
    }


    public void highlightElement(WebElement element) {
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", element);
        }
    }

    public boolean isElementDisplayed(By element){
        WebElement foundElement = driver.findElement(element);
        highlightElement(foundElement);
        return foundElement != null && foundElement.isDisplayed();
    }

    public boolean isElementClickable(By element){
        WebElement foundElement = driver.findElement(element);
        highlightElement(foundElement);
        return foundElement != null && foundElement.isEnabled();
    }

       }


