package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import shared.IElementActivity;
import util.ReportLogger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static util.TableUtil.getAllColumnValues;

public class SmartSearchPage extends IElementActivity {
    ReportLogger reportLogger = ReportLogger.getInstance();

    private static final String MEMBER_CODE_INPUT_SS = "//input[@name='MCodeWithPrefix']";

    private static final String NEXT_ARROW_SS = ".la.la-angle-right";

    private static final String MEMBER_CODE_COLUMN = "//th[normalize-space()='Member Code']";

    private static final String BIRTH_DATE_FROM_MONTH_SS = "//select[@name='BirthDateFromMonth']";

    private static final String BIRTH_DATE_FROM_DATE_SS = "//select[@name='BirthDateFromDay']";

    private static final String BIRTH_DATE_TO_MONTH_SS = "//select[@name='BirthDateToMonth']";

    private static final String BIRTH_DATE_TO_DATE_SS = "//select[@name='BirthDateToDay']";

    private static final String SEARCH_BUTTON = "//button[contains(text(),'Search')]";

    private static final String BIRTH_DATE_COLUMN_SS = "//th[normalize-space()='Birth Date']";

    private static final String PDF_ICON_SS = ".la.la-file-pdf-o";

    private static final String CITY_DROPDOWN = "(//select[@name='City'])[1]";

    private static final String MEMBER_CODE_COLUMN_DATA = "//table/tbody/tr[1]/td[2]";

    private static final String AGE_TEXT_INPUT="input[placeholder='> Age']";


    //xpath for Advance filter in smart search
    private String getDynamicXPath(String AdvanceFilterOption) {
        String xpathTemplate = "//label[contains(text(),'%s')]";
        return String.format(xpathTemplate, AdvanceFilterOption);
    }

    public void selectAdvanceFilterOption(String AdvanceFilterOption) {
        String dynamicXPath = getDynamicXPath(AdvanceFilterOption);
        getXPathDriverWebElement(dynamicXPath).click();
    }

    public void enterMemberCodeForSearch(String memberCode) throws InterruptedException {
        getXPathDriverWebElement(MEMBER_CODE_INPUT_SS).sendKeys(memberCode);
        getXPathDriverWebElement(MEMBER_CODE_INPUT_SS).sendKeys(Keys.TAB);
        Thread.sleep(200);
    }

    public void selectValueFromMonthDropDown(String BirthDateFromMonth) {
        selectValueFromDropDown(BIRTH_DATE_FROM_MONTH_SS, BirthDateFromMonth);
    }

    public void selectValueFromDateDropDown(String BirthDateFromDate) {
        selectValueFromDropDown(BIRTH_DATE_FROM_DATE_SS, BirthDateFromDate);
    }

    public void selectValueToMonthDropDown(String BirthDateToMonth) {
        selectValueFromDropDown(BIRTH_DATE_TO_MONTH_SS, BirthDateToMonth);
    }

    public void selectValueToDateDropDown(String BirthDateToDate) {
        selectValueFromDropDown(BIRTH_DATE_TO_DATE_SS, BirthDateToDate);
    }

    public void selectBirthDateToSearch(String BirthDateFromMonth, String BirthDateFromDate, String BirthDateToMonth, String BirthDateToDate) throws InterruptedException {
        selectValueFromMonthDropDown(BirthDateFromMonth);
        selectValueFromDateDropDown(BirthDateFromDate);
        selectValueToMonthDropDown(BirthDateToMonth);
        selectValueToDateDropDown(BirthDateToDate);
        Thread.sleep(1000);
    }

    public void clickOnSearchButton() {
        getXPathDriverWebElement(SEARCH_BUTTON).click();
    }

    public boolean validateMemberCodeColumnDataIsDisplayed(String columnName, String memberCode) throws InterruptedException {
        int columnIndex = getTableColumnIndex(columnName);
        boolean nextPageAvailable;
        int pageNumber = 1;
        List<String> allFilteredColumnValues = new ArrayList<>();

        while (true) {
            // Fetch current column values
            List<String> currentColumnValues = getAllColumnValues(driver, columnIndex);

            // Filter based on PaymentDate if provided
            List<String> filteredColumnValues;
            if (!memberCode.isEmpty()) {
                filteredColumnValues = currentColumnValues.stream()
                        .filter(value -> value.equals(memberCode))
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
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getXPathDriverWebElement(MEMBER_CODE_COLUMN));

                // Fetch all values from the column after scrolling up to ensure correct data visibility
                currentColumnValues = getAllColumnValues(driver, columnIndex);

                // Filter again based on PaymentDate if provided
                if (!memberCode.isEmpty()) {
                    filteredColumnValues = currentColumnValues.stream()
                            .filter(value -> value.equals(memberCode))
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
            WebElement nextArrowButton = getCSSDriverWebElement(NEXT_ARROW_SS);

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
            WebElement nextButton = getCSSDriverWebElement(NEXT_ARROW_SS);
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

    public boolean validateBirthDateColumnDataIsDisplayed(String columnName, String startDate, String endDate) throws InterruptedException, ParseException {
        Thread.sleep(3000);
        int columnIndex = getTableColumnIndex(columnName);
        List<String> columnValues = getAllColumnValues(driver, columnIndex);
        System.out.println("Retrieved Column Values: " + columnValues);
        System.out.println("No of records found: " + columnValues.size());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM");
        Date start = dateFormat.parse(startDate);
        Date end = dateFormat.parse(endDate);

        for (String value : columnValues) {
            SimpleDateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dateValue = originalFormat.parse(value);
            String formattedValue = dateFormat.format(dateValue);
            Date compareDate = dateFormat.parse(formattedValue);

            if (compareDate.before(start) || compareDate.after(end)) {
                return false;
            }
        }

        return true;
    }

    public void clickOnPDFIcon() throws InterruptedException {
        Thread.sleep(5000);
        getCSSDriverWebElement(PDF_ICON_SS).click();
        Thread.sleep(10000);
    }

    public void navigateToPDFReport() throws InterruptedException {
        switchToChildWindow();
        Thread.sleep(3000);
    }

    public void selectValueFromDropDown(String city) throws InterruptedException {
        Thread.sleep(1000);
        selectValueFromDropDown(CITY_DROPDOWN, city);
    }

    public boolean validateCityColumnDataIsExpected(String columnName, String city) {
        int columnIndex = getTableColumnIndex(columnName);
        List<String> columnValues = getAllColumnValues(driver, columnIndex);
        System.out.println("Retrieved Column Values: " + columnValues);
        return columnValues.contains(city);
    }

    public void getAdvanceSearchResult() {
        String memberDetails = getXPathDriverWebElement(MEMBER_CODE_COLUMN_DATA).getText();
        System.out.println(memberDetails);
    }
    public void enterAge(String ageValue){
        getCSSDriverWebElement(AGE_TEXT_INPUT).sendKeys(ageValue);
    }

}
