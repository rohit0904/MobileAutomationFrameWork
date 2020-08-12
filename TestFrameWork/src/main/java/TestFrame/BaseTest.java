package TestFrame;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    public AppiumDriver<MobileElement> apmdriver;
    public Actions ac;
    public String appiumServiceUrl;
    public AppiumDriverLocalService appiumService;
    public DesiredCapabilities cap;
    //public Process StartDevice;
    public Properties props;
    public AndroidTouchAction atc;
    public Dimension dimension;
    public int x,Starty,Endy;
    public String Application_Package_Name,Application_Activity_Name,Device_UDID;


    @BeforeClass
    public void tearUp() throws Throwable{
        SetValues();
        StartApplication();
    }

    @AfterClass
    public void tearDown() throws Throwable{
        Thread.sleep(3000);
        apmdriver.quit();
        Thread.sleep(2000);
        //StartDevice.destroy();
        Thread.sleep(2000);
        appiumService.stop();

    }

    public void SetValues()throws Throwable{
        props = new Properties();
        InputStream ip = new FileInputStream("./PropertyFile/Global.properties");
        props.load(ip);
        Application_Package_Name = props.getProperty("Android_No_Broker_Package");
        Application_Activity_Name= props.getProperty("Android_No_Broker_Activity");
        Device_UDID = props.getProperty("Device_UDID");
    }

    public void StartApplication() throws Throwable {
        //StartDevice = Runtime.getRuntime().exec("emulator -avd Pixel3");
        Thread.sleep(3000);
        appiumService = AppiumDriverLocalService.buildDefaultService();
        appiumService.start();
        appiumServiceUrl = appiumService.getUrl().toString();
        cap = new DesiredCapabilities();
        cap.setCapability("deviceName", "Rohit Singh");
        cap.setCapability("udid", Device_UDID);
        cap.setCapability("platformName", "Android");
        cap.setCapability("platformVersion", "9");
        cap.setCapability("appPackage", Application_Package_Name);
        cap.setCapability("appActivity", Application_Activity_Name);
        cap.setCapability("autoGrantPermissions", false);
        apmdriver = new AndroidDriver<>(new URL(appiumServiceUrl), cap);
        Thread.sleep(2000);
        ac = new Actions(apmdriver);
        atc = new AndroidTouchAction(apmdriver);

    }

    public void ScrollSystem(String direction,int k)throws Throwable{
        for (int i=0;i<=k;i++) {
            x = dimension.getWidth()/2;
            Starty=0;
            Endy=0;

            switch (direction){
                case "up":
                    Starty = (int) (dimension.getHeight()*0.8);
                    Endy = (int) (dimension.getHeight()*0.2);
                    break;
            }
            atc.press(PointOption.point(x,Starty)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                    .moveTo(PointOption.point(x,Endy)).release().perform();
        }
    }
}
