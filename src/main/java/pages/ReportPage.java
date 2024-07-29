package pages;

import shared.IElementActivity;
import util.CustomAssert;
import util.ReportLogger;

public class ReportPage extends IElementActivity {
    ReportLogger reportLogger = ReportLogger.getInstance();

    private static final String REPORT_MENU = "//span[contains(text(),'Report')]";

    private static final String FINANCE_AND_INVOICE_LINK = "//a[normalize-space()='Finances & Invoice']";

    private static final String MEMBER_STATEMENTS_LINK = "//a[normalize-space()='Member Statements']";

    private static final String PREDICTED_BUTTON = "//button[normalize-space()='Predicted']";
    private static final String TOTAL_BALANCE = "//*[@id='tableSummary']//tbody//tr[last()]//td[last()]";

    private static final String GO_BUTTON = "//button[normalize-space()='Go']";

    private static final String DEBIT_AMT = "//*[@id='tableSummary']//tbody//tr[last()]//td[last()]//preceding-sibling::td[2]";

    private static final String CREDIT_AMT = "//*[@id='tableSummary']//tbody//tr[last()]//td[last()]//preceding-sibling::td[1]";

    public void selectFinanceAndInvoiceOption() {
        getXPathDriverWebElement(FINANCE_AND_INVOICE_LINK).click();
    }

    public void selectMemberStatementsOption() {
        getXPathDriverWebElement(MEMBER_STATEMENTS_LINK).click();
    }

    public void clickOnPredictedButton() throws InterruptedException {
        Thread.sleep(2000);
        getXPathDriverWebElement(PREDICTED_BUTTON).click();
    }

    public void navigateToReceiptDetailsPage() throws InterruptedException {
        switchToChildWindow();
    }

    public String getTotalBalance() {
        return getXPathDriverWebElement(TOTAL_BALANCE).getText();
    }

    public void clickOnGoButton() throws InterruptedException {
        Thread.sleep(500);
        getXPathDriverWebElement(GO_BUTTON).click();
    }

    public void validateBalanceAmount() throws InterruptedException {
        Thread.sleep(2000);
        String totalBalanceStr = getTotalBalance();
        String debitAmountStr = getXPathDriverWebElement(DEBIT_AMT).getText();
        String creditAmountStr = getXPathDriverWebElement(CREDIT_AMT).getText();

        totalBalanceStr = totalBalanceStr.replace(" Dr", "").trim();

        // Convert String to double
        double creditAmount = Double.parseDouble(creditAmountStr);
        double debitAmount = Double.parseDouble(debitAmountStr);
        double totalBalance = Double.parseDouble(totalBalanceStr);

        double result = debitAmount - creditAmount;

        System.out.println("Debit Amount: " + debitAmount + " Credit Amount: " + creditAmount + " Total Balance: " + totalBalance);
        System.out.println("Result (Debit Amount - Credit Amount): " + result);

        CustomAssert.assertEquals("The total balance does not match the expected result :",totalBalance,result);
    }

}
