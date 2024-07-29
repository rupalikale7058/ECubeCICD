package pages;

import java.util.List;

import org.openqa.selenium.WebElement;

import shared.IElementActivity;
import util.GeneralUtility;
import util.ReportLogger;

public class DashboardPage extends IElementActivity {
    ReportLogger reportLogger = ReportLogger.getInstance();

    private static final String PAGE_TITLE = "//h2[normalize-space()='Rysun Club & Resort']";

    private static final String DROPDOWN_SELECT = "//select[@name='PaymentType']";
    private static final String SAVE_BUTTON = "button[type='submit']";
    private static final String CANCEL_BUTTON = "//a[contains(text(),'Cancel')]";

    private static final String BACK_BUTTON="//button[contains(text(),'Back')]";

    public void selectMenu(String menu) {
        String dynamicXPath = "//span[normalize-space()='" + menu + "']";
        getXPathDriverWebElement(dynamicXPath).click();
    }

    public String getPageTitle() {
        return getXPathDriverWebElement(PAGE_TITLE).getText();
    }

    public void selectValueFromDropDown(String paymentType) throws InterruptedException {
        Thread.sleep(1000);
        selectValueFromDropDown(DROPDOWN_SELECT, paymentType);
        Thread.sleep(500);
    }

    public void clickOnSaveButton() throws InterruptedException {
        getCSSDriverWebElement(SAVE_BUTTON).click();
        Thread.sleep(2000);
    }

    public void clickOnCancelButton() {
        getXPathDriverWebElement(CANCEL_BUTTON).click();
    }

    public void clickOnBackButton(){
        getXPathDriverWebElement(BACK_BUTTON).click();
    }

}
