package stepdefinations;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.MemberReceiptSearchPage;
import pages.SmartSearchPage;
import util.CustomAssert;
import util.ReportLogger;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public class SmartSearchStepDef {
    ReportLogger reportLogger = ReportLogger.getInstance();
    String memberCode;
    String BirthDateFromMonth;
    String BirthDateFromDate;
    String BirthDateToMonth;
    String BirthDateToDate;

    String city;

    @And("User enter Member code to search the details")
    public void user_Enter_MemberCode_To_Search_The_Details(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> data = dataTable.asMaps();
        Map<String, String> row = data.getFirst();
        memberCode = row.get("memberCode");
        reportLogger.info("Member code entered is:" + memberCode);
        new SmartSearchPage().enterMemberCodeForSearch(memberCode);
    }

    @Then("User validate the search result in {string} column")
    public void user_Validate_The_Search_Result_In_Column(String columnName) throws InterruptedException {
        boolean isMemberCodePresent = new SmartSearchPage().validateMemberCodeColumnDataIsDisplayed(columnName, memberCode);
        CustomAssert.assertTrue("The Member Name is not found in the specified column", isMemberCodePresent);
    }

    @And("User select Birth Date From and To")
    public void user_Select_BirthDate_From_And_To(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> data = dataTable.asMaps();
        Map<String, String> row = data.getFirst();
        BirthDateFromMonth = row.get("BirthDateFromMonth");
        BirthDateFromDate = row.get("BirthDateFromDate");
        BirthDateToMonth = row.get("BirthDateToMonth");
        BirthDateToDate = row.get("BirthDateToDate");
        new SmartSearchPage().selectBirthDateToSearch(BirthDateFromMonth, BirthDateFromDate, BirthDateToMonth, BirthDateToDate);
    }

    @And("User clicks on the search button")
    public void userClicksOnTheSearchButton() throws InterruptedException {
        new SmartSearchPage().clickOnSearchButton();
        Thread.sleep(1000);
    }

    @Then("User validates the search results as per the selected Birth Date in the {string} column")
    public void user_Validates_The_Search_Results_AsPerThe_Selected_BirthDate_InTheColumn(String columnName) throws ParseException, InterruptedException {
        String startDate = BirthDateFromDate + " " + BirthDateFromMonth;
        String endDate = BirthDateToDate + " " + BirthDateToMonth;
        boolean result = new SmartSearchPage().validateBirthDateColumnDataIsDisplayed(columnName, startDate, endDate);
        CustomAssert.assertTrue("The dates are not within the selected range.", result);
    }

    @And("User clicks on PDF icon to export report in PDF")
    public void user_Clicks_On_PDF_Icon_To_ExportReport_In_PDF() throws InterruptedException {
        new SmartSearchPage().clickOnPDFIcon();
    }

    @Then("PDF report generated successfully")
    public void pdf_Report_Generated_Successfully() throws InterruptedException {
        new SmartSearchPage().navigateToPDFReport();
    }

    @And("User select City to search the details")
    public void user_Select_City_To_Search_The_Details(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> data = dataTable.asMaps();
        Map<String, String> row = data.getFirst();
        city = row.get("city");
        new SmartSearchPage().selectValueFromDropDown(city);
        reportLogger.info("Value selected from city dropdown");

    }

    @Then("User validates the search result as per the selected {string} column")
    public void user_Validates_The_Search_Result_As_Per_The_Selected_Column(String columnName) {
        boolean isCityPresent = new SmartSearchPage().validateCityColumnDataIsExpected(columnName, city);
        CustomAssert.assertTrue("The City is not found in the specified column", isCityPresent);
    }

    @And("User select {string} from Advance filter")
    public void user_Select_From_Advance_Filter(String AdvanceFilterOption) {
        new SmartSearchPage().selectAdvanceFilterOption(AdvanceFilterOption);
    }

    @Then("User validate the search result")
    public void user_Validate_The_Search_Result() {
        new SmartSearchPage().getAdvanceSearchResult();
    }

    @And("User enter age limit")
    public void user_Enter_Age_Limit(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        Map<String, String> row = data.getFirst();
        String ageValue = row.get("age");
        new SmartSearchPage().enterAge(ageValue);
    }
}
