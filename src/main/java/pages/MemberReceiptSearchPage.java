package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import shared.IElementActivity;
import util.ReportLogger;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static util.TableUtil.getAllColumnValues;

public class MemberReceiptSearchPage extends IElementActivity {
    ReportLogger reportLogger = ReportLogger.getInstance();

    private static final String SEARCH_ICON = "//i[@class='la la-search icon-rotate']";

    private static final String RECEIPT_NO_INPUT = "//input[@name='ReceiptNo']";

    private static final String MEMBER_CODE_INPUT = "//input[@name='MemberCode']";

    private static final String MEMBER_NAME_INPUT = "//input[@name='MemberName']";

    private static final String DATE_INPUT = "//input[@name='PaymentDate']";

    private static final String NEXT_ARROW = ".la.la-angle-right";

    private static final String PAYMENT_DATE_COLUMN = "//th[normalize-space()='Payment Date']";




    public void clickOnSearchIcon() throws InterruptedException {
        Thread.sleep(2000);
        getXPathDriverWebElement(SEARCH_ICON).click();
    }

    public void searchWithReceiptNo(String ReceiptNo) throws InterruptedException {
        getXPathDriverWebElement(RECEIPT_NO_INPUT).sendKeys(ReceiptNo);
        getXPathDriverWebElement(RECEIPT_NO_INPUT).sendKeys(Keys.TAB);
        Thread.sleep(1000);
    }

    public boolean validateReceiptColumnDataIsExpected(String columnName, String ReceiptNo) {
        int columnIndex = getTableColumnIndex(columnName);
        List<String> columnValues = getAllColumnValues(driver, columnIndex);
        System.out.println("Retrieved Column Values: " + columnValues);
        return columnValues.contains(ReceiptNo);
    }

    public boolean validateMemberCodeColumnDataIsExpected(String columnName, String MemberCode) {
        int columnIndex = getTableColumnIndex(columnName);
        List<String> columnValues = getAllColumnValues(driver, columnIndex);
        System.out.println("Retrieved Column Values: " + columnValues);
        return columnValues.contains(MemberCode);
    }

    public boolean validateMemberNameColumnDataIsExpected(String columnName, String MemberName) {
        int columnIndex = getTableColumnIndex(columnName);
        List<String> columnValues = getAllColumnValues(driver, columnIndex);
        System.out.println("Retrieved Column Values: " + columnValues);
        return columnValues.contains(MemberName);
    }

    public boolean validatePaymentDateColumnDataIsExpected(String columnName, String PaymentDate) throws InterruptedException {
        int columnIndex = getTableColumnIndex(columnName);
        boolean nextPageAvailable;
        int pageNumber = 1;
        List<String> allFilteredColumnValues = new ArrayList<>();

        while (true) {
            // Fetch current column values
            List<String> currentColumnValues = getAllColumnValues(driver, columnIndex);

            // Filter based on PaymentDate if provided
            List<String> filteredColumnValues;
            if (!PaymentDate.isEmpty()) {
                filteredColumnValues = currentColumnValues.stream()
                        .filter(value -> value.equals(PaymentDate))
                        .toList();
            } else {
                filteredColumnValues = new ArrayList<>(currentColumnValues);
            }

            // Accumulate filtered values
            allFilteredColumnValues.addAll(filteredColumnValues);

            // Check if next arrow button is enabled and clickable
            nextPageAvailable = isNextArrowButtonClickable();
            System.out.println("Is next arrow button clickable: " + nextPageAvailable);

            if (nextPageAvailable) {
                // Click on next page button
                clickOnNextPageButton();

                // Scroll up to ensure the column header is visible again
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getXPathDriverWebElement(PAYMENT_DATE_COLUMN));

                // Fetch all values from the column after scrolling up to ensure correct data visibility
                currentColumnValues = getAllColumnValues(driver, columnIndex);

                // Filter again based on PaymentDate if provided
                if (!PaymentDate.isEmpty()) {
                    filteredColumnValues = currentColumnValues.stream()
                            .filter(value -> value.equals(PaymentDate))
                            .toList();
                } else {
                    filteredColumnValues = new ArrayList<>(currentColumnValues);
                }

                // Accumulate filtered values
                allFilteredColumnValues.addAll(filteredColumnValues);
                pageNumber++;
            } else {
                break; // Exit the loop if the next arrow button is not clickable
            }
        }

        // Final output of all retrieved values
        System.out.println("All Retrieved Column Values (filtered): " + allFilteredColumnValues);

        // Return true if filtered values were found
        return !allFilteredColumnValues.isEmpty();
    }


    public void clickOnNextPageButton() throws InterruptedException {
        try {
            WebElement nextArrowButton = getCSSDriverWebElement(NEXT_ARROW);

            // Scroll the page until the next arrow button is visible
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextArrowButton);

            // Wait for the button to be clickable
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.elementToBeClickable(nextArrowButton));

            // Use JavaScript to click the button
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextArrowButton);
            System.out.println("Clicked on next arrow button");

            // Additional wait if necessary for the next page to load
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println("Failed to click on next arrow button: " + e.getMessage());
        }

    }

    public boolean isNextArrowButtonClickable() {
        try {
            // Scroll to the bottom of the page
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

            // Optionally, add a small delay to ensure the page has scrolled completely
            Thread.sleep(1000); // Adjust the sleep time as needed

            // Locate the "Next" button and check if it is enabled and displayed
            WebElement nextButton = getCSSDriverWebElement(NEXT_ARROW);
            return nextButton.isEnabled() && nextButton.isDisplayed();
        } catch (Exception e) {
            System.out.println("Next arrow button is not clickable: " + e.getMessage());
            return false; // Return false if an exception occurs
        }
    }

    private int getTableColumnIndex(String columnName) {
        List<WebElement> headerElements = driver.findElements(By.cssSelector("th"));
        //System.out.println("Table Headers:");
        for (int i = 0; i < headerElements.size(); i++) {
            String headerText = headerElements.get(i).getText().trim().replaceAll("\\s+", "");  // Remove all whitespaces
            System.out.println("Index: " + i + ", Header: " + headerText);
            String columnNameWithoutSpaces = columnName.trim().replaceAll("\\s+", "");  // Remove all whitespaces from columnName
            if (headerText.equalsIgnoreCase(columnNameWithoutSpaces)) {
                return i;
            }
        }
        throw new RuntimeException("Column with name '" + columnName + "' not found in the table");
    }

    public void searchWithMemberCode(String MemberCode) {
        getXPathDriverWebElement(MEMBER_CODE_INPUT).sendKeys(MemberCode);
        getXPathDriverWebElement(MEMBER_CODE_INPUT).sendKeys(Keys.TAB);
    }

    public void searchWithMemberName(String MemberName) {
        getXPathDriverWebElement(MEMBER_NAME_INPUT).sendKeys(MemberName);
        getXPathDriverWebElement(MEMBER_NAME_INPUT).sendKeys(Keys.TAB);
    }

    public void searchWithDate(String PaymentDate) throws InterruptedException {
        getXPathDriverWebElement(DATE_INPUT).sendKeys(PaymentDate);
        getXPathDriverWebElement(DATE_INPUT).sendKeys(Keys.TAB);
        Thread.sleep(2000);
    }


}
