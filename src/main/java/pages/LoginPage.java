package pages;

import shared.IElementActivity;
import util.ReportLogger;

public class LoginPage extends IElementActivity {
    ReportLogger reportLogger = ReportLogger.getInstance();
    private static final String USERNAME = "[id='UserName']";
    private static final String PASSWORD = "[id='Password']";
    private static final String LOGIN_BUTTON = "//button[@type='submit']";

    public void login(String username, String password) {
        getCSSDriverWebElement(USERNAME).sendKeys(username);
        getCSSDriverWebElement(PASSWORD).sendKeys(password);
        getXPathDriverWebElement(LOGIN_BUTTON).click();
        return;
    }
}
