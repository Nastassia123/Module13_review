package listeners;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.log4testng.Logger;
import pages.basepagesconfiguration.BasePageClass;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TestListeners extends BasePageClass implements ITestListener {
    public static Logger logger = Logger.getLogger(TestListeners.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("On Test Start " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("On Test Success " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("On Test Failed " + result.getName());
     takeScreenshot();

    }



    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }


}
