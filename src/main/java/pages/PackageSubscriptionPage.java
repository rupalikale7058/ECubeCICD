package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import shared.IElementActivity;
import util.ReportLogger;

public class PackageSubscriptionPage extends IElementActivity {
    ReportLogger reportLogger = ReportLogger.getInstance();

    private static final String VIEW_ALL_LINK = "(//a[contains(@class,'viewall')][normalize-space()='View All'])[3]";

    private static final String ADD_ICON = "(//i[contains(@class,'la la-plus')])[1]";

    private static final String MEMBER_NAME_INPUT = "input[placeholder='Member Name']";

    private static final String CHECKBOX = "//div[@class='custom-control custom-checkbox cursor-pointer']//child::input[@id='0']";

    private static final String SAVE_BUTTON = "//button[normalize-space()='Save']";

    public void clickOnViewAllLink() {
        getXPathDriverWebElement(VIEW_ALL_LINK).click();
    }

    public void clickOnPlusIcon() {
        getXPathDriverWebElement(ADD_ICON).click();
    }

    public void enterMemberName(String memberName) throws InterruptedException {
        Thread.sleep(1000);
        WebElement element = getCSSDriverWebElement(MEMBER_NAME_INPUT);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().sendKeys(memberName).sendKeys(Keys.ENTER).perform();
    }

    public void checkCheckBoxToSelectMemberName() throws InterruptedException {
        Thread.sleep(1000);
        WebElement checkBox = getXPathDriverWebElement(CHECKBOX);
        mouseHoverToElement(checkBox);
    }

    public void clickOnSaveButton() throws InterruptedException {
        getXPathDriverWebElement(SAVE_BUTTON).click();
        Thread.sleep(1000);
    }

}
