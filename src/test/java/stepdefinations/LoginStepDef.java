package stepdefinations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import pages.LoginPage;
import util.ApiUtil;
import util.ReportLogger;

public class LoginStepDef {
    ReportLogger reportLogger = ReportLogger.getInstance();

    @Given("User Login with valid credentials")
    public void user_login_with_valid_credentials() {
        String username = ApiUtil.readProperty("username");
        String password = ApiUtil.readProperty("password");
        new LoginPage().login(username, password);
    }


    @And("User successfully logged in")
    public void userSuccessfullyLoggedIn() {
        reportLogger.info("User Logged in successfully !!!");
    }
}
