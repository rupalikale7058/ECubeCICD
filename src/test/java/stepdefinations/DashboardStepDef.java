package stepdefinations;

import io.cucumber.java.en.Then;
import pages.DashboardPage;
import util.CustomAssert;
import util.ReportLogger;

public class DashboardStepDef {
    ReportLogger reportLogger = ReportLogger.getInstance();

    @Then("User should be redirected to the home page")
    public void user_ShouldBe_Redirected_ToThe_HomePage() {
        CustomAssert.assertEquals("Page Title Dispalyed as expected", "Rysun Club & Resort", new DashboardPage().getPageTitle());
        reportLogger.info("User is on home page!");
    }

}
