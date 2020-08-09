package TestFrame;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ApplicationLocators extends BaseTest {
    @AndroidFindBy(xpath = "//*[@text='Continue']")
    public MobileElement ContinueButton;

    @AndroidFindBy(id = "buyLayout")
    public MobileElement GoToBuyTab;

    @AndroidFindBy(id = "searchEditHome")
    public MobileElement SearchBox;

    @AndroidFindBy(id = "spinnergo")
    public MobileElement CitySelectionDropDown;

    @AndroidFindBy(id = "localityAutoCompleteTxt")
    public MobileElement LocalitySearch;

    @AndroidFindBy(id = "nearByRadio")
    public MobileElement NearByCheckBox;

    @AndroidFindBy(id = "bhktwo")
    public MobileElement TwoBHK;

    @AndroidFindBy(id = "bhkthree")
    public MobileElement ThreeBHK;

    @AndroidFindBy(id = "searchProperty")
    public MobileElement SearchButton;

    @AndroidFindBy(id = "cv")
    public List<MobileElement> SearchResults;

    @AndroidFindBy(id = "scrollView")
    public MobileElement PropertyDetailTab;

    @AndroidFindBy(id = "tv_report_wrong_info")
    public MobileElement WrongInfoButton;

    @AndroidFindBy(id = "et_signup_phone")
    public MobileElement UserName;

    @AndroidFindBy(id = "rb_signup_pwd")
    public MobileElement PasswordRadioButton;

    @AndroidFindBy(id = "et_signup_pwd")
    public MobileElement PasswordBox;

    @AndroidFindBy(id = "btn_signup")
    public MobileElement SignInButton;

    @AndroidFindBy(xpath = "//*[@class='android.widget.CheckBox']")
    public List<MobileElement> WrongInfoFields;

    @AndroidFindBy(id = "btn_report")
    public MobileElement ReportButton;

    @AndroidFindBy(id = "sp_bhk_type")
    public MobileElement ChangeFlatSizeDropDown;

    @AndroidFindBy(xpath = "//*[@text='4+ BHK']")
    public MobileElement ChangedFlatSize;

    @AndroidFindBy(id = "edt_others")
    public MobileElement FeedBackSubmitBox;

    @AndroidFindBy(id = "btn_save")
    public MobileElement SaveConfigurationButton;

    @AndroidFindBy(xpath = "//*[@text='Thank you for the feedback']")
    public MobileElement FeedBackMessage;


    public ApplicationLocators(AppiumDriver<MobileElement> driver) {
        this.apmdriver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
}
