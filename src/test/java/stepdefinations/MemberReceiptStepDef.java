package stepdefinations;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.DashboardPage;
import pages.MemberReceiptSearchPage;
import pages.MemberReceiptsPage;
import pages.ReportPage;
import util.CustomAssert;
import util.ReportLogger;

import java.util.List;
import java.util.Map;

public class MemberReceiptStepDef {
    ReportLogger reportLogger = ReportLogger.getInstance();
    private String ReceiptNo;
    private String MemberCode;
    private String MemberName;
    private String PaymentDate;

    private String memberCodeValue;
    private String totalOutstandingAmt;

    @And("User selects {string} from side menu")
    public void userSelectsFromSideMenu(String menu) throws InterruptedException {
        new DashboardPage().selectMenu(menu);
        Thread.sleep(1000);
    }

    @And("User clicks on plus icon")
    public void user_Click_On_PlusIcon() throws InterruptedException {
        new MemberReceiptsPage().clickOnPlusIcon();
    }

    @And("User enters Member Code")
    public void user_Enters_MemberCode(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> data = dataTable.asMaps();
        for (Map<String, String> row : data) {
            String memberId = row.get("MemberCode");
            reportLogger.info("User Enter Member code:" + memberId);
            new MemberReceiptsPage().selectMemberID(memberId);
        }
    }

    @And("User selects Payment Type")
    public void user_Selects_PaymentType(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> data = dataTable.asMaps();
        Map<String, String> row = data.getFirst();
        String paymentType = row.get("PaymentType");
        new DashboardPage().selectValueFromDropDown(paymentType);
    }

    @And("User click on Save button")
    public void user_Click_On_SaveButton() throws InterruptedException {
        new DashboardPage().clickOnSaveButton();
    }

    @And("Check total amount and available credit after adding advance amount")
    public void check_Total_Amount_And_Available_Credit_After_Adding_Advance_Amount(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> data = dataTable.asMaps();
        Map<String, String> row = data.getFirst();
        String amount = row.get("advanceAmount");
        reportLogger.info("Advance amount is:" + amount);
        new MemberReceiptsPage().calculateTotalWithAdvanceAmount(amount);
    }

    @And("User enter bank details")
    public void user_Enter_Bank_Details(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        Map<String, String> row = data.getFirst();
        String branchDetails = row.get("Branch");
        String bankInfo = row.get("BankInfo");
        reportLogger.info("Branch name is:" + branchDetails);
        reportLogger.info("Bank name is:" + bankInfo);
        new MemberReceiptsPage().enterBankDetails(branchDetails, bankInfo);
    }


    @Then("Member Receipt generated successfully")
    public void member_Receipt_Generated_Successfully() throws InterruptedException {
        new MemberReceiptsPage().navigateToReceipt();
    }

    @And("User validate the Receipt details")
    public void user_Validate_The_ReceiptDetails() throws InterruptedException {
        new MemberReceiptsPage().getReceiptNumber();
        new MemberReceiptsPage().validatePaymentType();
        new MemberReceiptsPage().validateDateOnReceipt();
        new MemberReceiptsPage().validateChequeNumberOnReceipt();
        new MemberReceiptsPage().validateBankDetailsOnReceipt();
    }
    @And("User enter cheque No")
    public void user_Enter_ChequeNo(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        Map<String, String> row = data.getFirst();
        String chequeNo = row.get("chequeNo");
        new MemberReceiptsPage().userEnterChequeNo(chequeNo);
    }

    @And("User click on Cancel Button")
    public void user_Click_On_Cancel_Button() {
        new DashboardPage().clickOnCancelButton();
    }

    @And("User enter credit card details")
    public void user_Enter_Credit_Card_Details(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        Map<String, String> row = data.getFirst();
        String CreditCardNo = row.get("CreditCardNo");
        new MemberReceiptsPage().enterCardNumber(CreditCardNo);
    }

    @And("User enter Card Holder Name")
    public void user_Enter_Card_Holder_Name(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        Map<String, String> row = data.getFirst();
        String CardHolderName = row.get("CardHolderName");
        new MemberReceiptsPage().enterCardHolderName(CardHolderName);
    }

    @And("User validate the Receipt details when payment type is credit card")
    public void user_Validate_The_Receipt_Details_When_PaymentType_Is_CreditCard() throws InterruptedException {
        new MemberReceiptsPage().getReceiptNumber();
        new MemberReceiptsPage().validatePaymentType();
        new MemberReceiptsPage().validateDateOnReceipt();
    }

    @And("User enter reference number")
    public void user_Enter_Reference_Number(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        Map<String, String> row = data.getFirst();
        String ReferenceNo = row.get("ReferenceNo");
        new MemberReceiptsPage().userEnterReferenceNumber(ReferenceNo);
    }

    @And("User click on search icon")
    public void user_Click_On_SearchIcon() throws InterruptedException {
        new MemberReceiptSearchPage().clickOnSearchIcon();
    }

    @And("User search with Receipt number")
    public void user_Search_With_ReceiptNumber(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> data = dataTable.asMaps();
        for (Map<String, String> row : data) {
            ReceiptNo = row.get("ReceiptNo");
            reportLogger.info("User Enter Member code:" + ReceiptNo);
            new MemberReceiptSearchPage().searchWithReceiptNo(ReceiptNo);
        }
    }

    @Then("User Validate the search result in {string} column")
    public void userValidateTheSearchResultInColumn(String columnName) {
        boolean isReceiptNoPresent = new MemberReceiptSearchPage().validateReceiptColumnDataIsExpected(columnName, ReceiptNo);
        CustomAssert.assertTrue("The receipt number is not found in the specified column", isReceiptNoPresent);
    }

    @And("User search with Member Code")
    public void user_Search_With_MemberCode(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        for (Map<String, String> row : data) {
            MemberCode = row.get("MemberCode");
            reportLogger.info("User Enter Member code:" + MemberCode);
            new MemberReceiptSearchPage().searchWithMemberCode(MemberCode);
        }
    }

    @Then("User Validate the search results in {string} column")
    public void user_Validate_The_SearchResultsIn_Column(String columnName) {
        boolean isMemberCodePresent = new MemberReceiptSearchPage().validateMemberCodeColumnDataIsExpected(columnName, MemberCode);
        CustomAssert.assertTrue("The Member code is not found in the specified column", isMemberCodePresent);
    }

    @And("User search with Member Name")
    public void user_Search_With_MemberName(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        for (Map<String, String> row : data) {
            MemberName = row.get("MemberName");
            reportLogger.info("User Enter Member code:" + MemberName);
            new MemberReceiptSearchPage().searchWithMemberName(MemberName);
        }
    }

    @Then("User Validate the search result of Member Name in {string} column")
    public void user_Validate_The_Search_Result_Of_MemberName_I_nColumn(String columnName) {
        boolean isMemberNamePresent = new MemberReceiptSearchPage().validateMemberNameColumnDataIsExpected(columnName, MemberName);
        CustomAssert.assertTrue("The Member Name is not found in the specified column", isMemberNamePresent);
    }

    @And("User search with Date")
    public void user_Search_With_Date(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> data = dataTable.asMaps();
        Map<String, String> row = data.getFirst();
        PaymentDate = row.get("PaymentDate");
        new MemberReceiptSearchPage().searchWithDate(PaymentDate);
    }

    @Then("User Validate the search result of Date in {string} column")
    public void user_Validate_The_Search_Result_Of_Date_InColumn(String columnName) throws InterruptedException {
        boolean isPaymentDatePresent = new MemberReceiptSearchPage().validatePaymentDateColumnDataIsExpected(columnName, PaymentDate);
        CustomAssert.assertTrue("The Member Name is not found in the specified column", isPaymentDatePresent);
    }

    @And("User select record to print")
    public void user_Select_Record_ToPrint() {
        new MemberReceiptsPage().selectRecordFToPrint();
    }

    @And("User click on Print icon")
    public void user_Click_On_PrintIcon() {
        new MemberReceiptsPage().clickOnPrintIcon();
    }

    @And("User selects records to delete")
    public void user_Selects_Records_To_Delete() {
        new MemberReceiptsPage().selectRecordsToDelete();
    }

    @And("User click on Delete icon")
    public void user_Click_On_Delete_Icon() throws InterruptedException {
        new MemberReceiptsPage().clickOnDeleteIcon();
    }

    @Then("User confirm delete records")
    public void user_Confirm_Delete_Records() throws InterruptedException {
        new MemberReceiptsPage().switchToAlertPopUpAndAccept();
    }

    @And("User select Registration option")
    public void user_Select_Registration_Option() {
    }

    @And("User enter description")
    public void user_Enter_Description() {
        new MemberReceiptsPage().enterDescription();
    }

    @And("User Navigate to Registration option")
    public void user_Navigate_To_Registration_Option() throws InterruptedException {
        new MemberReceiptsPage().clickRegistrationLink();
    }

    @And("User select {string} from list")
    public void user_Select_From_List(String Tax) throws InterruptedException {
        new MemberReceiptsPage().selectTaxFromList(Tax);
    }

    @And("User enter receipt amount")
    public void user_Enter_Receipt_Amount(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> data = dataTable.asMaps();
        Map<String, String> row = data.getFirst();
        String receiptAmount = row.get("receiptAmount");
        reportLogger.info("The Receipt amount is:" + receiptAmount);
        new MemberReceiptsPage().enterReceiptAmount(receiptAmount);
    }

    @And("User selects Payment Type in Registration option")
    public void user_Selects_PaymentType_In_RegistrationOption(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> data = dataTable.asMaps();
        Map<String, String> row = data.getFirst();
        String PaymentType = row.get("PaymentType");
        reportLogger.info("Payment type is:" + PaymentType);
        new MemberReceiptsPage().selectValueFromPaymentDropDown(PaymentType);
    }

    @And("User enter cheque No in Registration option")
    public void user_Enter_ChequeNo_In_Registration_Option(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        Map<String, String> row = data.getFirst();
        String chequeNo = row.get("chequeNo");
        new MemberReceiptsPage().userEnterChequeNoInRegPage(chequeNo);
    }

    @And("User enter bank details in Registration option")
    public void user_Enter_BankDetails_In_RegistrationOption(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        Map<String, String> row = data.getFirst();
        String branchDetails = row.get("Branch");
        String bankInfo = row.get("BankInfo");
        reportLogger.info("Branch name is:" + branchDetails);
        reportLogger.info("Bank name is:" + bankInfo);
        new MemberReceiptsPage().enterBankDetailsInRegPage(branchDetails, bankInfo);
    }


    @And("User navigate to member details page")
    public void user_Navigate_To_Member_Details_Page() throws InterruptedException {
        new MemberReceiptsPage().clickOnEditIcon();
    }

    @And("User capture the Total OutStanding amount from member details page")
    public void user_Capture_The_Total_OutStanding_Amount_From_MemberDetailsPage() {
        totalOutstandingAmt = new MemberReceiptsPage().getTotalOutStandingAmount();
        reportLogger.info(totalOutstandingAmt);
    }

    @And("User Click on Back Button")
    public void user_Click_On_Back_Button() {
        new DashboardPage().clickOnBackButton();
    }

    @And("User capture the Member code details from member receipt")
    public void user_Capture_The_Member_Code_Details_From_Member_Receipt() throws InterruptedException {
        memberCodeValue = new MemberReceiptsPage().getMemberCode();
    }

    @And("User enter Member code")
    public void user_Enter_MemberCode() throws InterruptedException {
        new MemberReceiptsPage().enterMemberCode(memberCodeValue);
    }

    @Then("User check the total balance amount is equal with Total Outstanding amount")
    public void user_Check_The_Total_Balance_Amount_Is_Equal_With_TotalOutstanding_Amount() {
        String totalBalance = new ReportPage().getTotalBalance();
        reportLogger.info("Total Balance: " + totalBalance);
        reportLogger.info("Total Outstanding Amount: " + totalOutstandingAmt);
        CustomAssert.assertEquals("Total balance equals to Total OutStanding amount:", totalOutstandingAmt, totalBalance);
    }

    @And("User select package option")
    public void user_Select_Package_Option() throws InterruptedException {
        new MemberReceiptsPage().selectPackageOption();
    }


    @And("User select package type from list")
    public void user_Select_PackageType_From_List(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> data = dataTable.asMaps();
        Map<String, String> row = data.getFirst();
        String packageType = row.get("packageType");
        new MemberReceiptsPage().selectPackageTypeFromList(packageType);
    }

    @And("User selects Payment Type in Package type")
    public void user_Selects_PaymentType_In_PackageType(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> data = dataTable.asMaps();
        Map<String, String> row = data.getFirst();
        String paymentType = row.get("PaymentType");
        new MemberReceiptsPage().selectPaymentTypeFromList(paymentType);
    }

    @And("User enter below credit card details")
    public void user_Enter_Below_CreditCard_Details(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        Map<String, String> row = data.getFirst();
        String CreditCardNo = row.get("CreditCardNo");
        new MemberReceiptsPage().enterCCdetails(CreditCardNo);
    }

    @And("User validates the Receipt details")
    public void userValidatesTheReceiptDetails() throws InterruptedException {
        new MemberReceiptsPage().getReceiptNumber();
        new MemberReceiptsPage().validatePaymentType();
        new MemberReceiptsPage().validateDateOnReceipt();
    }

    @And("User enter bank details in Package")
    public void user_Enter_Bank_Details_In_Package(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        Map<String, String> row = data.getFirst();
        String branchDetails = row.get("Branch");
        String bankInfo = row.get("BankInfo");
        reportLogger.info("Branch name is:" + branchDetails);
        reportLogger.info("Bank name is:" + bankInfo);
        new MemberReceiptsPage().enterBankDetailsInPackage(branchDetails,bankInfo);
    }
}
