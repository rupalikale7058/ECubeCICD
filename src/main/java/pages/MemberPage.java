package pages;

import moduls.Member_General_Tab;
import moduls.Member_Personal_Tab;
import moduls.Membership_Tab;
import moduls.SubMember_Tab;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import shared.IElementActivity;
import util.ReportLogger;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static util.TableUtil.getAllColumnValues;

public class MemberPage extends IElementActivity {
    ReportLogger reportLogger = ReportLogger.getInstance();
    Member_General_Tab dataGenerator;
    Member_Personal_Tab dataGenerator1;
    private Membership_Tab dataGenerator2;

    private SubMember_Tab dataGenerator3;

    public MemberPage() {
        this.dataGenerator = new Member_General_Tab();
        this.dataGenerator1 = new Member_Personal_Tab();
        this.dataGenerator2 = new Membership_Tab();
        this.dataGenerator3 = new SubMember_Tab();
    }

    private static final String MEMBERSHIP_CATEGORY_DROPDOWN = "//select[@name='MembershipCategory']";
    private static final String MEMBERSHIP_ID_DROPDOWN = "//select[@name='MembershipId']";

    private static final String MEMBER_NAME_INPUT = "input[placeholder='Member Code/Name']";

    private static final String MEMBER_NAME_SELECT = "div[class='autoCompleteMenu scrollable'] div";

    private static final String VALUE_SELECT = ".autoCompleteMenu.scrollable";

    private static final String CORPORATE_CODE_INPUT = "//input[@name='CorporateId']";

    private static final String REFERENCE_NAME_INPUT = "//input[@name='ReferenceId']";

    private static final String REFRESH_BUTTON = ".la.la-refresh";

    //Member page locators
    private static final String TITLE_DROPDOWN = "//select[@name='Title']";
    private static final String FIRST_NAME_INPUT = "//input[@name='FName']";
    private static final String MIDDLE_NAME_INPUT = "//input[@name='MName']";
    private static final String LAST_NAME_INPUT = "//input[@name='LName']";
    private static final String ADDRESS_INPUT = "//textarea[@name='Address']";
    private static final String ZIP_CODE_INPUT = "//input[@name='ZipCode']";
    private static final String AREA_INPUT = "//input[@name='Area']";
    private static final String CITY_INPUT = "//input[@name='City']";
    private static final String STATE_INPUT = "//input[@name='State']";
    private static final String COUNTRY_DROPDOWN = "//select[@name='Country']";

    private static final String PERMANENT_ADDRESS_CHECKBOX = "label[for='SameAddressChk']";

    private static final String MARITAL_STATUS_DROPDOWN = "//select[@name='MarittalStatus']";
    private static final String GENDER_DROPDOWN = "//select[@name='Gender']";
    private static final String DATE_OF_BIRTH_INPUT = "//input[@name='BirthDate']";
    private static final String CATEGORY_DROPDOWN = "//select[@name='Category']";
    private static final String NATIONALITY_INPUT = "//input[@name='Nationality']";

    private static final String SAVE_BUTTON = "//button[contains(text(),'Save')]";

    private static final String PERSONAL_TAB = "//a[normalize-space()='Personal']";

    //Personal Tab Locators

    private static final String PROFESSION_CATEGORY_DROPDOWN = "//select[@name='ProfessionCategory']";

    //Membership page locators
    private static final String MEMBERSHIP_TAB = "//a[normalize-space()='Membership']";
    private static final String MEMBERSHIP_CATEGORY_SELECT = "//select[@name='MembershipCategoryId']";
    private static final String MEMBERSHIP_TYPE_SELECT = "//select[@name='MembershipId']";
    private static final String MEMBER_CODE_INPUT = "//*[@id=\"membershipinfo\"]/div/div[1]/div/div[1]/div[3]/div[2]/input[2]";

    //SubMember TAB locators
    private static final String SUB_MEMBER_TAB = "//a[normalize-space()='Sub Member']";
    private static final String SUB_MEMBER_TITLE_DROPDOWN = "//select[@name='Title']";
    private static final String SUB_MEMBER_FIRST_NAME_INPUT = "//input[@name='SubmemberName']";
    private static final String SUB_MEMBER_MIDDLE_NAME_INPUT = "//input[@name='MiddleName']";
    private static final String SUB_MEMBER_LAST_NAME_INPUT = "//input[@name='LName']";
    private static final String SUB_MEMBER_DATE_OF_BIRTH_INPUT = "//input[@name='BirthDate']";
    private static final String SUB_MEMBER_CATEGORY_DROPDOWN = "//select[@name='Category']";
    private static final String SUB_MEMBER_BLOOD_GROUP_DROPDOWN = "//select[@name='BloodGroup']";
    private static final String SUB_MEMBER_RELATION_DROPDOWN = "//select[@name='Relation']";
    private static final String SUB_MEMBER_MARITAL_STATUS_DROPDOWN = "//select[@name='MaritalStatus']";
    private static final String SUB_MEMBER_GENDER_DROPDOWN = "//select[@name='Gender']";

    private static final String SUB_MEMBER_ADD_BUTTON = "(//button[normalize-space()='Add'])[1]";

    private static final String CONTACT_TAB = "//a[normalize-space()='Contact']";

    private static final String CONTACT_ADDRESS_RADIO_BUTTON = "//label[normalize-space()='Resident']";

    public void fillTheDetailsInContact() throws InterruptedException {
        getXPathDriverWebElement(CONTACT_TAB).click();
        Thread.sleep(1000);
        getXPathDriverWebElement(CONTACT_ADDRESS_RADIO_BUTTON).click();
    }
    public void fillTheDetailsInGeneralTab() throws InterruptedException {
        String title = dataGenerator.getTitle();
        String firstName = dataGenerator.getFirstName();
        String middleName = dataGenerator.getMiddleName();
        String lastName = dataGenerator.getLastName();
        String address = dataGenerator.getAddress();
        String zipCode = dataGenerator.getZipCode();
        String area = dataGenerator.getArea();
        String city = dataGenerator.getCity();
        String state = dataGenerator.getState();
        String country = dataGenerator.getCountry();
        String maritalStatus = dataGenerator.getMaritalStatus();
        String gender = dataGenerator.getGender();
        String dateOfBirth = dataGenerator.getDateOfBirth();
        String category = dataGenerator.getCategory();
        String nationality = dataGenerator.getNationality();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        selectValueFromDropDown(TITLE_DROPDOWN, title);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(TITLE_DROPDOWN), title));

        enterText(FIRST_NAME_INPUT, firstName, wait);
        enterText(MIDDLE_NAME_INPUT, middleName, wait);
        enterText(LAST_NAME_INPUT, lastName, wait);
        enterText(ADDRESS_INPUT, address, wait);
        enterText(ZIP_CODE_INPUT, zipCode, wait);
        enterText(AREA_INPUT, area, wait);
        enterText(CITY_INPUT, city, wait);
        enterText(STATE_INPUT, state, wait);

        selectValueFromDropDown(COUNTRY_DROPDOWN, country);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(COUNTRY_DROPDOWN), country));

        getCSSDriverWebElement(PERMANENT_ADDRESS_CHECKBOX).click();

        selectValueFromDropDown(MARITAL_STATUS_DROPDOWN, maritalStatus);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(MARITAL_STATUS_DROPDOWN), maritalStatus));

        selectValueFromDropDown(GENDER_DROPDOWN, gender);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(GENDER_DROPDOWN), gender));

        enterText(DATE_OF_BIRTH_INPUT, dateOfBirth, wait);

        selectValueFromDropDown(CATEGORY_DROPDOWN, category);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(CATEGORY_DROPDOWN), category));

        enterText(NATIONALITY_INPUT, nationality, wait);
        Thread.sleep(2000);
    }

    private void enterText(String xpath, String text, WebDriverWait wait) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement element = getXPathDriverWebElement(xpath);
                element.clear();
                element.sendKeys(text);
                wait.until(ExpectedConditions.attributeToBe(element, "value", text));
                return;
            } catch (StaleElementReferenceException e) {
                attempts++;
            }
        }
        throw new RuntimeException("Failed to enter text after multiple attempts");
    }
    public void fillTheDetailsInPersonalTab() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        String professionCategory = dataGenerator1.getProfessionCategory();

        getXPathDriverWebElement(PERSONAL_TAB).click();

        selectValueFromDropDown(PROFESSION_CATEGORY_DROPDOWN, professionCategory);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(PROFESSION_CATEGORY_DROPDOWN), professionCategory));
        Thread.sleep(2000);
    }

    private void clickElement(String xpath, WebDriverWait wait) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement element = getXPathDriverWebElement(xpath);
                element.click();
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
                return;
            } catch (StaleElementReferenceException e) {
                attempts++;
            }
        }
        throw new RuntimeException("Failed to click element after multiple attempts");
    }

    public void fillTheDetailsInMembershipTab() throws InterruptedException {
        String membershipCategory = dataGenerator2.getMembershipCategory();
        String membershipType = dataGenerator2.getMembershipType();
        //String memberCode = dataGenerator2.getMemberCode();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Click on Membership Tab
        clickElement(MEMBERSHIP_TAB, wait);

        selectValueFromDropDown(MEMBERSHIP_CATEGORY_SELECT, membershipCategory);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(MEMBERSHIP_CATEGORY_SELECT), membershipCategory));
        Thread.sleep(1000);
        selectValueFromDropDown(MEMBERSHIP_TYPE_SELECT, membershipType);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(MEMBERSHIP_TYPE_SELECT), membershipType));
        Thread.sleep(5000);
    }
    public void enterMemberCode() throws InterruptedException {
        String memberCode = generate4DigitMemberCode();
        reportLogger.info("Generated Member code is: " + memberCode);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MEMBER_CODE_INPUT)));
        element.sendKeys(memberCode);
        Thread.sleep(1000);
    }
    private String generate4DigitMemberCode() {
        Random random = new Random();
        int memberCode = 1000 + random.nextInt(9000); // Generates a random number between 1000 and 9999
        return String.valueOf(memberCode);
    }
    public void fillTheDetailsInSubMemberTab() throws InterruptedException {
        String title = dataGenerator3.getTitle();
        String firstName = dataGenerator3.getFirstName();
        String middleName = dataGenerator3.getMiddleName();
        String lastName = dataGenerator3.getLastName();
        String dateOfBirth = dataGenerator3.getDateOfBirth();
        String category = dataGenerator3.getCategory();
        String bloodGroup = dataGenerator3.getBloodGroup();
        String relation = dataGenerator3.getRelation();
        String maritalStatus = dataGenerator3.getMaritalStatus();
        String gender = dataGenerator3.getGender();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Click on Sub Member Tab
        clickElement(SUB_MEMBER_TAB, wait);
        Thread.sleep(500);
        selectValueFromDropDown(SUB_MEMBER_TITLE_DROPDOWN, title);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(SUB_MEMBER_TITLE_DROPDOWN), title));

        enterText(SUB_MEMBER_FIRST_NAME_INPUT, firstName, wait);
        enterText(SUB_MEMBER_MIDDLE_NAME_INPUT, middleName, wait);
        enterText(SUB_MEMBER_LAST_NAME_INPUT, lastName, wait);
        enterText(SUB_MEMBER_DATE_OF_BIRTH_INPUT, dateOfBirth, wait);

        selectValueFromDropDown(SUB_MEMBER_CATEGORY_DROPDOWN, category);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(SUB_MEMBER_CATEGORY_DROPDOWN), category));

        selectValueFromDropDown(SUB_MEMBER_BLOOD_GROUP_DROPDOWN, bloodGroup);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(SUB_MEMBER_BLOOD_GROUP_DROPDOWN), bloodGroup));

        selectValueFromDropDown(SUB_MEMBER_RELATION_DROPDOWN, relation);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(SUB_MEMBER_RELATION_DROPDOWN), relation));

        selectValueFromDropDown(SUB_MEMBER_MARITAL_STATUS_DROPDOWN, maritalStatus);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(SUB_MEMBER_MARITAL_STATUS_DROPDOWN), maritalStatus));

        selectValueFromDropDown(SUB_MEMBER_GENDER_DROPDOWN, gender);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(SUB_MEMBER_GENDER_DROPDOWN), gender));

        // Click Add Button
        WebElement addButton = getXPathDriverWebElement(SUB_MEMBER_ADD_BUTTON);
        Actions actions = new Actions(driver);
        actions.moveToElement(addButton).click().perform();

        Thread.sleep(2000);
    }

    public void saveMemberDetails() throws InterruptedException {
        Thread.sleep(2000);
        WebElement saveButton = getXPathDriverWebElement(SAVE_BUTTON);
        // Move to the element and click
        Actions actions = new Actions(driver);
        actions.moveToElement(saveButton).click().perform();
        Thread.sleep(50000);
    }


    public void selectMembershipCategoryFromListAndValidate(String columnName) throws InterruptedException {

        WebElement dropdownElement = getXPathDriverWebElement(MEMBERSHIP_CATEGORY_DROPDOWN);
        // Create a Select object for the dropdown
        Select select = new Select(dropdownElement);
        // Get all the options in the dropdown
        List<WebElement> options = select.getOptions();
        // List to store all option values
        List<String> optionValues = new ArrayList<>();

        // Iterate through each option
        for (WebElement option : options) {
            String optionValue = option.getText();
            // Select the current option
            select.selectByVisibleText(optionValue);
            // Add the current option value to the list
            optionValues.add(optionValue);
            System.out.println("Selected value from list is: " + optionValue);
            Thread.sleep(2000);
            // Validate if the data is displayed as per the search input
            boolean isDataDisplayed = validateDataIsDisplayedAsPerTheSearchInput(columnName, optionValue);
            System.out.println("Validation result for '" + optionValue + "': " + isDataDisplayed);
        }
    }

    public void selectMembershipIDFromListAndValidate(String columnName) throws InterruptedException {

        WebElement dropdownElement = getXPathDriverWebElement(MEMBERSHIP_ID_DROPDOWN);
        // Create a Select object for the dropdown
        Select select = new Select(dropdownElement);
        // Get all the options in the dropdown
        List<WebElement> options = select.getOptions();
        // List to store all option values
        List<String> optionValues = new ArrayList<>();

        // Iterate through each option
        for (WebElement option : options) {
            String optionValue = option.getText();
            // Select the current option
            select.selectByVisibleText(optionValue);
            // Add the current option value to the list
            optionValues.add(optionValue);
            System.out.println("Selected value from list is: " + optionValue);
            Thread.sleep(5000);
            // Validate if the data is displayed as per the search input
            boolean isDataDisplayed = validateDataIsDisplayedAsPerTheSearchInput(columnName, optionValue);
            System.out.println("Validation result for '" + optionValue + "': " + isDataDisplayed);
        }
    }

    public boolean validateDataIsDisplayedAsPerTheSearchInput(String columnName, String value) {
        int columnIndex = getTableColumnIndex(columnName);
        List<String> columnValues = getAllColumnValues(driver, columnIndex);
        System.out.println("Retrieved Column Values: " + columnValues);
        return columnValues.stream().anyMatch(v -> v.equalsIgnoreCase(value));
    }

    private int getTableColumnIndex(String columnName) {
        List<WebElement> headerElements = driver.findElements(By.cssSelector("th"));
        //System.out.println("Table Headers:");
        for (int i = 0; i < headerElements.size(); i++) {
            String headerText = headerElements.get(i).getText().trim().replaceAll("\\s+", "");
            System.out.println("Index: " + i + ", Header: " + headerText);
            String columnNameWithoutSpaces = columnName.trim().replaceAll("\\s+", "");
            if (headerText.equalsIgnoreCase(columnNameWithoutSpaces)) {
                return i;
            }
        }
        throw new RuntimeException("Column with name '" + columnName + "' not found in the table");
    }

    public void selectMember(String memberName) throws InterruptedException {
        getCSSDriverWebElement(MEMBER_NAME_INPUT).sendKeys(memberName);
        getCSSDriverWebElement(MEMBER_NAME_SELECT).click();
        Thread.sleep(3000);
    }

    public boolean validateMemberNameColumnDataIsExpected(String columnName, String memberName) {
        int columnIndex = getTableColumnIndex(columnName);
        List<String> columnValues = getAllColumnValues(driver, columnIndex);
        System.out.println("Retrieved Column Values: " + columnValues);
        return columnValues.contains(memberName);
    }

    public void enterCorporateCode(String corporateCode) throws InterruptedException {
        getXPathDriverWebElement(CORPORATE_CODE_INPUT).sendKeys(corporateCode);
        getCSSDriverWebElement(VALUE_SELECT).click();
        Thread.sleep(1000);
    }

    public boolean validateCorporateNameColumnDataIsExpected(String columnName, String corporateCode) {
        int columnIndex = getTableColumnIndex(columnName);
        List<String> columnValues = getAllColumnValues(driver, columnIndex);
        System.out.println("Retrieved Column Values: " + columnValues);
        //return columnValues.contains(corporateCode);
        String regex = ".*" + corporateCode + ".*";
        return columnValues.stream().anyMatch(value -> value.matches(regex));
    }

    public void enterReferenceName(String referenceCode) throws InterruptedException {
        getXPathDriverWebElement(REFERENCE_NAME_INPUT).sendKeys(referenceCode);
        getCSSDriverWebElement(VALUE_SELECT).click();
        Thread.sleep(1000);
    }

    public boolean validateReferenceColumnDataIsExpected(String columnName, String referenceCode) {
        int columnIndex = getTableColumnIndex(columnName);
        List<String> columnValues = getAllColumnValues(driver, columnIndex);
        System.out.println("Retrieved Column Values: " + columnValues);
        String regex = ".*" + referenceCode + ".*";
        return columnValues.stream().anyMatch(value -> value.matches(regex));
    }

    public void clicksOnRefreshButton() throws InterruptedException {
        getCSSDriverWebElement(REFRESH_BUTTON).click();
    }

}
