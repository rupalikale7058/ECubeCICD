package stepdefinations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.MemberPage;
import util.ReportLogger;
import io.cucumber.datatable.DataTable;
import util.CustomAssert;

import java.util.List;
import java.util.Map;

public class MemberStepDef {

    ReportLogger reportLogger = ReportLogger.getInstance();

    private String memberName;
    private String corporateCode;

    private String referenceCode;

    @And("User search with Membership Category")
    public void user_Search_With_Membership_Category() {

    }

    @Then("User search with Membership Category and Validate the search result in {string} column in member table")
    public void user_Search_With_MembershipCategory_And_Validate_The_Search_Result_In_ColumnInMember_Table(String columnName) throws InterruptedException {
        new MemberPage().selectMembershipCategoryFromListAndValidate(columnName);
    }

    @Then("User search with Membership ID and Validate the search result in {string} column in member table")
    public void user_Search_With_MembershipID_And_Validate_The_SearchResult_InColumn_InMemberTable(String columnName) throws InterruptedException {
        new MemberPage().selectMembershipIDFromListAndValidate(columnName);
    }

    @And("User enter Member Name as below")
    public void user_Enter_Member_Name_AsBelow(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> data = dataTable.asMaps();
        for (Map<String, String> row : data) {
            memberName = row.get("memberName");
            reportLogger.info("User Enter Member code:" + memberName);
            new MemberPage().selectMember(memberName);
        }
    }

    @Then("User Validate the search result of Member Name in {string} column in member table")
    public void user_Validate_The_SearchResult_Of_MemberName_In_Column_In_MemberTable(String columnName) {
        boolean isMemberNamePresent = new MemberPage().validateMemberNameColumnDataIsExpected(columnName, memberName);
        CustomAssert.assertTrue("The Member Name is not found in the specified column", isMemberNamePresent);
    }

    @And("User enter Corporate code")
    public void user_Enter_Corporate_Code(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> data = dataTable.asMaps();
        for (Map<String, String> row : data) {
            corporateCode = row.get("corporateCode");
            reportLogger.info("User Enter Corporate code:" + corporateCode);
            new MemberPage().enterCorporateCode(corporateCode);
        }
    }

    @Then("User Validate the search result of Corporate code in {string} column in member table")
    public void user_Validate_The_Search_Result_Of_Corporate_Code_In_Column_In_MemberTable(String columnName) {
        boolean isCorporateCodePresent = new MemberPage().validateCorporateNameColumnDataIsExpected(columnName, corporateCode);
        CustomAssert.assertTrue("The Corporate code is not found in the specified column", isCorporateCodePresent);
    }

    @And("User enter Reference code")
    public void userEnterReferenceCode(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> data = dataTable.asMaps();
        for (Map<String, String> row : data) {
            referenceCode = row.get("referenceCode");
            reportLogger.info("User Enter Corporate code:" + referenceCode);
            new MemberPage().enterReferenceName(referenceCode);
        }
    }

    @Then("User Validate the search result of Reference code in {string} column in member table")
    public void user_Validate_The_SearchResult_Of_ReferenceCode_In_Column_In_MemberTable(String columnName) {
        boolean isReferenceCodePresent = new MemberPage().validateReferenceColumnDataIsExpected(columnName, referenceCode);
        CustomAssert.assertTrue("The Reference Name is not found in the specified column", isReferenceCodePresent);
    }

    @When("User clicks on the Refresh button")
    public void user_Clicks_On_The_Refresh_Button() throws InterruptedException {
        new MemberPage().clicksOnRefreshButton();
    }

    @Then("The search fields should be cleared and the default state should be restored")
    public void the_Search_Fields_Should_Be_Cleared_And_The_Default_State_Should_Be_Restored() {
        reportLogger.info("Successfully cleared all the field and the default state is restored");
    }

    @And("User generate and fill the data into the General Tab In Member Module")
    public void user_Generate_And_Fill_The_Data_Into_The_General_Tab_In_Member_Module() throws InterruptedException {
        new MemberPage().fillTheDetailsInGeneralTab();
    }

    @And("User generate and fill the data into the Personal Tab In Member Module")
    public void user_Generate_And_Fill_The_Data_Into_The_Personal_Tab_In_MemberModule() throws InterruptedException {
        new MemberPage().fillTheDetailsInPersonalTab();
    }

    @And("User generate and fill the data into the Membership Tab In Member Module")
    public void user_Generate_And_Fill_The_Data_Into_The_Membership_Tab_In_MemberModule() throws InterruptedException {
        new MemberPage().fillTheDetailsInMembershipTab();
    }

    @And("User generate and fill the data into the Sub Member Tab In Member Module")
    public void user_Generate_And_Fill_The_Data_Into_The_SubMember_Tab_In_MemberModule() throws InterruptedException {
        new MemberPage().fillTheDetailsInSubMemberTab();
    }

    @And("User Clicks on Save button")
    public void userClicksOnSaveButton() throws InterruptedException {
        new MemberPage().saveMemberDetails();
        reportLogger.info("Successfully created member!");
    }

    @And("User generate and fill the data into the Contact Tab In Member Module")
    public void userGenerateAndFillTheDataIntoTheContactTabInMemberModule() throws InterruptedException {
        new MemberPage().fillTheDetailsInContact();
    }

    @And("User generate and enter Member code")
    public void user_Generate_And_Enter_MemberCode() throws InterruptedException {
        new MemberPage().enterMemberCode();
    }
}
