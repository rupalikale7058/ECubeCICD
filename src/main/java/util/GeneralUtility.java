package util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebElement;

public class GeneralUtility {


    public static void selectFromText(String value, List<WebElement> valueList) {
        String expectedValue = value.trim().toLowerCase();
        for (WebElement element : valueList) {
            String actualValue = element.getText().trim().toLowerCase();
            if (actualValue.equals(expectedValue)) {
                element.click();
                break;
            }
        }
    }

    public static void selectFromTitle(String value, List<WebElement> valueList) {
        String expectedValue = value.trim().toLowerCase();
        for (WebElement element : valueList) {
            String actualValue = element.getAttribute("title").trim().toLowerCase();
            if (actualValue.equals(expectedValue)) {
                element.click();
                break;
            }
        }
    }

    public static List<String> convertWebElementListToStringList(List<WebElement> webElements) {
        List<String> stringList = new ArrayList<>();
        for (WebElement element : webElements) {
            String value = element.getText().trim().toLowerCase().replace("\n", " ").replace("\r", " ");
            stringList.add(value);
        }
        return stringList;
    }

}
