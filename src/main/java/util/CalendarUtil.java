package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CalendarUtil {
    public static void navigateToYearOptions(WebDriver driver, int currentYear) {
        String xpath = String.format("//button[normalize-space()='%d']", currentYear);
        WebElement yearValue = driver.findElement(By.xpath(xpath));
        yearValue.click();
    }

    public static void selectYear(WebDriver driver, int year) {
        String yearXPath = String.format("//span[normalize-space()='%d']", year);
        driver.findElement(By.xpath(yearXPath)).click();
    }

    public static void selectMonth(WebDriver driver, String month) {
        String formattedMonth = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase();
        String monthXPath = String.format("//span[normalize-space()='%s']", formattedMonth);
        WebElement monthElement = driver.findElement(By.xpath(monthXPath));
        monthElement.click();
    }

    public static void selectDay(WebDriver driver, int day) {
        String dayXPath = String.format("//span[normalize-space()='%d']", day);
        WebElement dayElement = driver.findElement(By.xpath(dayXPath));
        dayElement.click();
    }

    public static void selectDate(WebDriver driver, int currentYear, int year, String month, int day) {
        navigateToYearOptions(driver, currentYear);
        selectYear(driver, year);
        selectMonth(driver, month);
        selectDay(driver, day);
    }


}
