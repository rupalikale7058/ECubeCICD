package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class TableUtil {

    public static WebElement findCellByTextNew(WebDriver driver, String tableId, String expectedText) {
        int rowCount = getTableRowCount(driver, tableId);
        for (int i = 1; i <= rowCount; i++) {
            String normalizedExpectedText = normalizeText(expectedText);
            String xpath = "//table[@id='" + tableId + "']/tbody/tr[" + i + "]/td[normalize-space()='" + normalizedExpectedText + "']";
            try {
                return driver.findElement(By.xpath(xpath));
            } catch (NoSuchElementException e) {
                // Continue iterating if cell not found in this row
            }
        }
        // If loop completes without finding the cell, return null
        System.out.println("Cell with text '" + expectedText + "' not found in table '" + tableId + "'");
        return null;
    }

    private static String normalizeText(String text) {
        // Remove leading, trailing, and consecutive whitespace characters
        return text.trim().replaceAll("\\s+", " ");
    }

    private static int getTableRowCount(WebDriver driver, String tableId) {
        String rowCountXPath = "//table[@id='" + tableId + "']/tbody/tr";
        List<WebElement> rows = driver.findElements(By.xpath(rowCountXPath));
        return rows.size();
    }

    public static List<String> getAllAmountColumnValues(WebDriver driver, int columnIndex) {
        List<String> amountValues = new ArrayList<>();
        List<WebElement> rows = driver.findElements(By.cssSelector("tr"));
        for (int i = 1; i < rows.size(); i++) { // Skip the header row
            List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
            if (cells.size() > columnIndex) {
                String amountText = cells.get(columnIndex).getText().trim();
                amountValues.add(extractAmountValue(amountText));  // Extract and add the actual amount value
            } else {
                throw new RuntimeException("Invalid table structure. Column index " + columnIndex + " is out of bounds");
            }
        }
        return amountValues;
    }

    public static String extractAmountValue(String amountText) {
        String amountWithoutSymbol = amountText.replaceAll("\\$\\s*", "");
        String integerValue = amountWithoutSymbol.split("\\.")[0];
        // Remove commas before parsing
        integerValue = integerValue.replaceAll(",", "");  // This line removes commas from the integer part
        return integerValue;
    }

    public static List<String> getAllColumnValues(WebDriver driver, int columnIndex) {
        List<String> columnValues = new ArrayList<>();
        List<WebElement> rows = driver.findElements(By.cssSelector("tr"));
        for (int i = 1; i < rows.size(); i++) { // Skip the header row
            List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
            if (cells.size() > columnIndex) {
                String colText = cells.get(columnIndex).getText().trim();
                columnValues.add(colText);
            } else {
                throw new RuntimeException("Invalid table structure. Column index " + columnIndex + " is out of bounds");
            }
        }
        return columnValues;
    }
}
