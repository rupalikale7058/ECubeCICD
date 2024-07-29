package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import shared.IElementActivity;
import util.ReportLogger;

import java.util.Random;


public class MemberReceiptsPage extends IElementActivity {
    ReportLogger reportLogger = ReportLogger.getInstance();

    private static final String PLUS_ICON = "//i[@class='la la-plus']";
    private static final String MEMBER_ID_INPUT = "//input[@name='MemberId']";
    private static final String MEMBER_ID_SELECT = "div[class='autoCompleteMenu scrollable'] div";

    private static final String ADVANCE_AMOUNT_INPUT = "//input[@name='AdvanceAmount']";

    private static final String TOTAL_AMOUNT_INPUT = "//input[@name='AmountPaid']";
    private static final String BRANCH_INPUT = "//input[@name='ChequeBranch']";

    private static final String BRANCH_INPUT_REG = "(//input[@placeholder='Branch'])[2]";

    private static final String BANK_INPUT_REG = "(//input[@placeholder='Bank Information'])[2]";
    private static final String BANK_INPUT = "//input[@name='BankInfo']";

    private static final String RECEIPT_NO = "(//span[@id='receiptno'])[1]";
    private static final String PAYMENT_TYPE_FIELD = "//span[@id='PaymentType']";
    private static final String DATE_FIELD = "//span[@id='ReceiptDate']";
    private static final String CHEQUE_NO_INPUT = "//input[@name='ChequeNo']";
    private static final String REF_NO_OR_CHEQUE_NO = "//span[@id='ChequeNo']";
    private static final String BANK_INFO_ON_RECEIPT = "//span[@id='BankInfo']";
    private static final String CREDIT_CARD_NUMBER_INPUT = "//input[@name='CreditCardNo']";
    private static final String CARD_HOLDER_NAME_INPUT = "//input[@name='CreditCardholder']";

    private static final String PRINT_CHECKBOX = "(//div[@class='custom-control custom-checkbox cursor-pointer'])[2]";

    private static final String PRINT_ICON = "//a[@href='#']//i[@class='la la-print']";
    private static final String SELECT_ALL_RECORD_TO_DELETE = "//label[@for='SelectedAll']";

    private static final String DELETE_ICON = "//i[@class='la la-trash']";

    private static final String OK_BUTTON = "button[type='submit']";

    private static final String REASON_TEXT_INPUT_BOX = "textarea[name='Reason']";
    private static final String SAVE_BUTTON = "button[type='submit']";

    private static final String DESCRIPTION_BOX_INPUT = "div[id='Services'] textarea[name='Description']";
    private static final String REGISTRATION_LINK = "//a[contains(text(),'Registration')]";
    private static final String TAX_DROPDOWN = "//select[@name='TaxId']";

    private static final String RECEIPT_AMOUNT_INPUT = "(//input[@name='AmountPaid'])[2]";

    private static final String PAYMENT_DROPDOWN_LIST = "(//select[@name='PaymentType'])[2]";

    private static final String CHEQUE_NO_INPUT_REG = "(//input[@placeholder='Cheque No'])[2]";

    private static final String EDIT_ICON = "(//i[contains(@class,'la la-edit')])[1]";

    private static final String TOTAL_OUTSTANDING_AMOUNT = ".text-white.mb-0.font-weight-medium";

    private static final String MEMBER_CODE_VALUE = "//table/tbody/tr/td[3]";

    private static final String MEMBER_CODE_INPUT = "//input[@name='MemberCode']";

    private static final String PACKAGE_OPTION = "//a[normalize-space()='Package']";

    private static final String PACKAGE_LIST = "//select[@name='PackageSubId']";

    private static final String PAYMENT_TYPE_LIST = "(//select[@name='PaymentType'])[2]";

    private static final String CC_NO_INPUT = "(//input[@placeholder='Credit Card No'])[2]";

    private static final String BRANCH_INPUT_PKG = "(//input[@placeholder='Branch'])[2]";

    private static final String BANK_INPUT_PKG = "(//input[@placeholder='Bank Information'])[2]";

    public void clickOnPlusIcon() throws InterruptedException {
        Thread.sleep(2000);
        getXPathDriverWebElement(PLUS_ICON).click();
    }

    public void selectMemberID(String memberId) throws InterruptedException {
        getXPathDriverWebElement(MEMBER_ID_INPUT).sendKeys(memberId);
        getCSSDriverWebElement(MEMBER_ID_SELECT).click();
        Thread.sleep(3000);
    }

    public void getTotalAmount() {
        WebElement totalAmountElement = getXPathDriverWebElement(TOTAL_AMOUNT_INPUT);
        // Retrieve the text using JavaScript
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String totalAmountStr = (String) js.executeScript("return arguments[0].innerText;", totalAmountElement);

        // Log the retrieved total amount
        reportLogger.log("Original Total Amount (via JS): " + totalAmountStr);
    }

    public void calculateTotalWithAdvanceAmount(String amount) throws InterruptedException {
        getTotalAmount();
        Thread.sleep(2000);
        // Step:Update the advance amount
        WebElement element = getXPathDriverWebElement(ADVANCE_AMOUNT_INPUT);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(amount);
        element.sendKeys(Keys.TAB);
        Thread.sleep(1000);
    }

    public void enterBankDetails(String branchDetails, String bankInfo) {
        getXPathDriverWebElement(BRANCH_INPUT).sendKeys(branchDetails);
        getXPathDriverWebElement(BANK_INPUT).sendKeys(bankInfo);
    }

    public void enterBankDetailsInPackage(String branchDetails, String bankInfo) {
        getXPathDriverWebElement(BRANCH_INPUT_PKG).sendKeys(branchDetails);
        getXPathDriverWebElement(BANK_INPUT_PKG).sendKeys(bankInfo);
    }

    public void enterBankDetailsInRegPage(String branchDetails, String bankInfo) {
        getXPathDriverWebElement(BRANCH_INPUT_REG).sendKeys(branchDetails);
        getXPathDriverWebElement(BANK_INPUT_REG).sendKeys(bankInfo);
    }

    public void navigateToReceipt() throws InterruptedException {
        switchToChildWindow();
        Thread.sleep(1000);
    }

    public void getReceiptNumber() throws InterruptedException {
        Thread.sleep(500);
        String receiptNo = getXPathDriverWebElement(RECEIPT_NO).getText();
        if (receiptNo == null || receiptNo.trim().isEmpty()) {
            reportLogger.error("Error: Receipt number is null or empty!");
        } else {
            reportLogger.info("Receipt No is: " + receiptNo);
        }
    }

    public void validatePaymentType() {
        String paymentTypeValue = getXPathDriverWebElement(PAYMENT_TYPE_FIELD).getText();
        if (paymentTypeValue == null || paymentTypeValue.trim().isEmpty()) {
            reportLogger.error("Error: payment type is null or empty!");
        } else {
            reportLogger.info("Payment Type is: " + paymentTypeValue);
        }
    }

    public void enterDescription() {
        getCSSDriverWebElement(DESCRIPTION_BOX_INPUT).sendKeys(generateRandomString());
    }

    private String generateRandomString() {
        StringBuilder builder = new StringBuilder(125);
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()";
        Random random = new Random();
        for (int i = 0; i < 125; i++) {
            int charIndex = random.nextInt(characters.length());
            builder.append(characters.charAt(charIndex));
        }
        return builder.toString();
    }

    public void validateDateOnReceipt() {
        String dateValue = getXPathDriverWebElement(DATE_FIELD).getText();
        if (dateValue == null || dateValue.trim().isEmpty()) {
            reportLogger.error("Error: payment type is null or empty!");
        } else {
            reportLogger.info("Date is: " + dateValue);
        }
    }

    public void userEnterChequeNo(String chequeNo) {
        getXPathDriverWebElement(CHEQUE_NO_INPUT).sendKeys(chequeNo);
    }

    public void userEnterChequeNoInRegPage(String chequeNo) {
        getXPathDriverWebElement(CHEQUE_NO_INPUT_REG).sendKeys(chequeNo);
    }

    public void validateChequeNumberOnReceipt() {
        String chequeDetails = getXPathDriverWebElement(REF_NO_OR_CHEQUE_NO).getText();
        if (chequeDetails == null || chequeDetails.trim().isEmpty()) {
            reportLogger.error("Error: Cheque No is null or empty!");
        } else {
            reportLogger.info("Cheque No: " + chequeDetails);
        }
    }

    public void validateBankDetailsOnReceipt() {
        String bankInfo = getXPathDriverWebElement(BANK_INFO_ON_RECEIPT).getText();
        if (bankInfo == null || bankInfo.trim().isEmpty()) {
            reportLogger.error("Error: Bank details is null or empty!");
        } else {
            reportLogger.info("Bank Info: " + bankInfo);
        }
    }

    public void enterCardNumber(String CreditCardNo) {
        getXPathDriverWebElement(CREDIT_CARD_NUMBER_INPUT).sendKeys(CreditCardNo);
    }

    public void enterCardHolderName(String CardHolderName) {
        getXPathDriverWebElement(CARD_HOLDER_NAME_INPUT).sendKeys(CardHolderName);
    }

    public void userEnterReferenceNumber(String ReferenceNo) {
        getXPathDriverWebElement(CHEQUE_NO_INPUT).sendKeys(ReferenceNo);
    }

    public void selectRecordFToPrint() {
        getXPathDriverWebElement(PRINT_CHECKBOX).click();
    }

    public void clickOnPrintIcon() {
        getXPathDriverWebElement(PRINT_ICON).click();
    }

    public void selectRecordsToDelete() {
        getXPathDriverWebElement(SELECT_ALL_RECORD_TO_DELETE).click();
    }

    public void clickOnDeleteIcon() {
        getXPathDriverWebElement(DELETE_ICON).click();
    }

    public void switchToAlertPopUpAndAccept() throws InterruptedException {
        Thread.sleep(2000);
        switchToChildWindow();
        getCSSDriverWebElement(OK_BUTTON).click();
        System.out.println("clicked on Ok Button");
        getCSSDriverWebElement(REASON_TEXT_INPUT_BOX).sendKeys("Delete records");
        getCSSDriverWebElement(SAVE_BUTTON).click();
        Thread.sleep(2000);
    }

    public void clickRegistrationLink() throws InterruptedException {
        Thread.sleep(2000);
        getXPathDriverWebElement(REGISTRATION_LINK).click();
    }

    public void selectTaxFromList(String Tax) throws InterruptedException {
        Thread.sleep(1000);
        selectValueFromDropDown(TAX_DROPDOWN, Tax);
    }

    public void enterReceiptAmount(String receiptAmount) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(2000);
        WebElement element = getXPathDriverWebElement(RECEIPT_AMOUNT_INPUT);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(receiptAmount);
        element.sendKeys(Keys.TAB);
    }

    public void selectValueFromPaymentDropDown(String PaymentType) throws InterruptedException {
        Thread.sleep(1000);
        selectValueFromDropDown(PAYMENT_DROPDOWN_LIST, PaymentType);
        Thread.sleep(2000);
    }

    public void clickOnEditIcon() throws InterruptedException {
        getXPathDriverWebElement(EDIT_ICON).click();
        Thread.sleep(10000);
    }

    public String getTotalOutStandingAmount() {
        String totalOutStdAmount = getCSSDriverWebElement(TOTAL_OUTSTANDING_AMOUNT).getText();
        System.out.println(totalOutStdAmount);
        return totalOutStdAmount;

    }

    public String getMemberCode() throws InterruptedException {
        String memberCodeValue = getXPathDriverWebElement(MEMBER_CODE_VALUE).getText();
        System.out.println(memberCodeValue);
        Thread.sleep(2000);
        return memberCodeValue;
    }

    public void enterMemberCode(String memberCodeValue) throws InterruptedException {
        Thread.sleep(1000);
        getXPathDriverWebElement(MEMBER_CODE_INPUT).sendKeys(memberCodeValue);
        Thread.sleep(2000);
    }

    public void selectPackageOption() throws InterruptedException {
        Thread.sleep(2000);
        getXPathDriverWebElement(PACKAGE_OPTION).click();
        Thread.sleep(2000);
    }

    public void selectPackageTypeFromList(String packageType) throws InterruptedException {
        Thread.sleep(1000);
        selectValueFromDropDown(PACKAGE_LIST, packageType);
    }

    public void selectPaymentTypeFromList(String paymentType) throws InterruptedException {
        Thread.sleep(1000);
        selectValueFromDropDown(PAYMENT_TYPE_LIST, paymentType);
        Thread.sleep(500);
    }

    public void enterCCdetails(String CreditCardNo) {
        getXPathDriverWebElement(CC_NO_INPUT).sendKeys(CreditCardNo);
    }


}
