package ExtentReporteFile;

import TestFrame.BaseTest;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;


public class TestListeners implements ITestListener {

    private static  ExtentReports extent  = ExtentManager.createInstance();
    private static  ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    public static ExtentTest test;
    ExtentTest node;


    public void onTestStart(ITestResult iTestResult) {
        test = extent.createTest(iTestResult.getTestClass().getName()+" :: "+iTestResult.getMethod().getMethodName());
        extentTest.set(test);

    }

    public void onTestSuccess(ITestResult iTestResult) {
        String logText ="<b>Test Method:- " + iTestResult.getMethod().getMethodName() +" is Passed.</b>";
        Markup m1 = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        extentTest.get().log(Status.PASS,m1);
        extentTest.get().createNode(iTestResult.getTestClass().getName()).pass(iTestResult.getMethod().getDescription());
    }

    public void onTestFailure(ITestResult iTestResult) {
        String exceptionMessage = Arrays.toString(iTestResult.getThrowable().getStackTrace());
       // extentTest.get().fail("Exception Occured, click to see details:"+exceptionMessage.replaceAll(",","<br>"));
        String logText = "<b>Test Method:- " + iTestResult.getMethod().getMethodName() + " is Failed.</b>";
        Markup m = MarkupHelper.createLabel(logText,ExtentColor.RED);
        extentTest.get().log(Status.FAIL, m);
        AppiumDriver<MobileElement> driver = ((BaseTest)iTestResult.getInstance()).apmdriver;
        String path = takeScreenShot(driver,iTestResult.getMethod().getMethodName());

        try {
            byte[] fileContent = FileUtils.readFileToByteArray(new File(path));
            String encodedString = Base64.getEncoder().encodeToString(fileContent);
            extentTest.get().fail((Markup) extentTest.get().addScreenCaptureFromBase64String(encodedString,"Click To Open"));
        }catch (Exception e){
            extentTest.get().fail("Fail To Take ScreenShot");
        }

        extentTest.get().createNode(iTestResult.getTestClass().getName()).fail(iTestResult.getMethod().getDescription());
    }

    public void onTestSkipped(ITestResult iTestResult) {
        String logText ="<b>Test Method:- " + iTestResult.getMethod().getMethodName() +" is Skipped.</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.ORANGE);
        extentTest.get().log(Status.SKIP,m);
        extentTest.get().createNode(iTestResult.getTestClass().getName()).skip(iTestResult.getMethod().getDescription());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {
        if(extent != null){
            extent.flush();
        }
    }

    public String takeScreenShot(WebDriver driver,String methodName){
        String fileName =  getScreenShotName(methodName);
        String directory = System.getProperty("user.dir")+"/screenshots/";
        new File(directory).mkdirs();
        String path = directory + fileName;

        try{
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot,new File(path));
        }catch(Exception e){
            e.printStackTrace();
        }
        return path;

    }


    public static String getScreenShotName(String methodName){
        Date d = new Date();
        String fileName = methodName + "_"+ d.toString().replace(":","_").replace(" ","_") + ".png";
        return fileName;
    }
}
