package TestFrame;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.Keys;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Listeners(ExtentReporteFile.TestListeners.class)

public class NoBrokerTest extends BaseTest {
    public ApplicationLocators NBL;

    @Test(description = "NoBroker Application For Search for Property and Submit Feedback for Wrong Information.")
    public void NoBrokerFeedBackTest() throws Throwable {
        NBL = new ApplicationLocators(apmdriver);
        apmdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        NBL.ContinueButton.click();
        Thread.sleep(2000);
        apmdriver.switchTo().alert().accept();
        Thread.sleep(1000);
        apmdriver.switchTo().alert().accept();
        Thread.sleep(1000);
        apmdriver.switchTo().alert().accept();
        System.out.println("Launch NoBroker App and Land on the Home Page.");

        //Step-2
        NBL.GoToBuyTab.click();
        MobileElement searchField = NBL.SearchBox;
        ac.moveToElement(searchField).click().build().perform();
        System.out.println("Select ‘Buy’ property related option and Click on the ‘Search’ related box.");

        //Step-3
        NBL.CitySelectionDropDown.click();
        Thread.sleep(2000);
        MobileElement CitySearch = (MobileElement) apmdriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable("
                + "new UiSelector().scrollable(true)).scrollIntoView("
                + "new UiSelector().textContains(\"Bangalore\"));"));
        CitySearch.click();
        Thread.sleep(2000);
        MobileElement searchBox = NBL.LocalitySearch;
        ac.moveToElement(searchBox).sendKeys("Marathahalli").build().perform();
        Thread.sleep(5000);
        ac.sendKeys(Keys.ARROW_DOWN,Keys.ENTER).build().perform();
        Thread.sleep(2000);
        ac.moveToElement(searchBox).sendKeys("HSR Layout").build().perform();
        Thread.sleep(5000);
        ac.sendKeys(Keys.ARROW_DOWN,Keys.ENTER).build().perform();
        System.out.println("Click on the Search Box bar and select two localities(Marathahalli and HSR Layout from the Autosuggestion locality dropdown.");

        //Step-4
        try {
            NBL.NearByCheckBox.click();
            Thread.sleep(1000);
            NBL.TwoBHK.click();
            NBL.ThreeBHK.click();
        }catch (Exception e){
            System.out.println("Near By Property Already Enable");
            NBL.TwoBHK.click();
            NBL.ThreeBHK.click();
        }
        NBL.SearchButton.click();
        Thread.sleep(8000);

        //Step-5
        MobileElement CitySearch1 = (MobileElement) apmdriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable("
                + "new UiSelector().scrollable(true)).scrollIntoView("
                + "new UiSelector().textContains(\"CHECK LOAN ELIGIBILITY\"));"));

        List<MobileElement> Place = NBL.SearchResults;
        Place.get(1).click();
        System.out.println("Scroll down on the Property listing page and click on the 4th property.");

        //Step-6
         dimension = NBL.PropertyDetailTab.getSize();

         ScrollSystem("up",10);
         NBL.WrongInfoButton.click();
        System.out.println("Launch NoBroker App and Land on the Home Page.");

         //Step-7
        NBL.UserName.sendKeys("1237567899");
        Thread.sleep(1000);
        apmdriver.navigate().back();
        Thread.sleep(1000);
        NBL.PasswordRadioButton.click();
        Thread.sleep(1000);
        NBL.PasswordBox.sendKeys("nobroker123");
        Thread.sleep(1000);
        NBL.SignInButton.click();
        Thread.sleep(5000);
        System.out.println("Login Successfully in NoBroker Application.");

        //Step-8
        List<MobileElement> WhatsWrongChecks = NBL.WrongInfoFields;
        System.out.println("COUNT LOGS:- "+WhatsWrongChecks.size());
        for (int i=0;i<WhatsWrongChecks.size();i++){
            WhatsWrongChecks.get(i).click();
            Thread.sleep(1000);
        }
        NBL.ReportButton.click();
        Thread.sleep(3000);
        System.out.println("Select All Checkboxes for incorrect info.");

        //Step-9
        MobileElement AddBHKDetails = (MobileElement) apmdriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable("
                + "new UiSelector().scrollable(true)).scrollIntoView("
                + "new UiSelector().textContains(\"What is the correct configuration?\"));"));
        NBL.ChangeFlatSizeDropDown.click();
        Thread.sleep(1000);
        NBL.ChangedFlatSize.click();
        Thread.sleep(1000);

        MobileElement AddNote = (MobileElement) apmdriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable("
                + "new UiSelector().scrollable(true)).scrollIntoView("
                + "new UiSelector().textContains(\"Tell us what is wrong\"));"));

        Thread.sleep(1000);
        NBL.FeedBackSubmitBox.sendKeys("Process Done Successfully");
        Thread.sleep(1000);
        NBL.SaveConfigurationButton.click();
        Thread.sleep(5000);
        NBL.FeedBackMessage.isDisplayed();
        Thread.sleep(5000);

    }
}
