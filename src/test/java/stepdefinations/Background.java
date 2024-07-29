package stepdefinations;

import driver.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import util.ApiUtil;
import util.ReportLogger;

public class Background {
    ReportLogger reportLogger = ReportLogger.getInstance();

    @Given("User open Ecube login page")
    public void user_login_with_valid_credentials() {
        String browser = ApiUtil.readProperty("Browser");
        String url = ApiUtil.readProperty("url");
        WebDriver driver = DriverFactory.getInstance().initDriver(browser);
        driver.get(url);
    }

}
