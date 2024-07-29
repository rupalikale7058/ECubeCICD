package stepdefinations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.MemberReceiptsPage;
import pages.ReportPage;
import util.ReportLogger;

public class ReportStepDef {
    ReportLogger reportLogger = ReportLogger.getInstance();

    @And("User Select Finances and Invoice option from the Report")
    public void user_Select_Finances_And_Invoice_Option_From_The_Report() {
        new ReportPage().selectFinanceAndInvoiceOption();
    }

    @And("User click on Member statement option")
    public void user_Click_On_Member_Statement_Option() {
       new ReportPage().selectMemberStatementsOption();
    }


    @And("User click on Predicted button")
    public void user_Click_On_Predicted_Button() throws InterruptedException {
      new ReportPage().clickOnPredictedButton();
    }

    @And("User navigated to receipt details page")
    public void user_Navigated_To_Receipt_Details_Page() throws InterruptedException {
        new ReportPage().navigateToReceiptDetailsPage();
    }

    @And("User click on Go button")
    public void user_Click_On_Go_Button() throws InterruptedException {
        new ReportPage().clickOnGoButton();
    }

    @Then("User validate Balance amount is displayed properly")
    public void user_Validate_Balance_Amount_IsDisplayed_Properly() throws InterruptedException {
         new ReportPage().validateBalanceAmount();
    }


}
