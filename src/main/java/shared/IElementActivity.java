package shared;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;


import driver.DriverFactory;
import util.ReportLogger;

import static util.TableUtil.getAllColumnValues;


public abstract class IElementActivity {
    ReportLogger reportLogger = ReportLogger.getInstance();
    static long EXPLICIT_WAIT_SEC = 30;
    static long POLLING_MILLS = 200;
    protected static WebDriver driver;
    private Wait<WebDriver> wait;
    private JavascriptExecutor jsExecutor;

    public IElementActivity() {
        driver = DriverFactory.drivers.get();
        wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(EXPLICIT_WAIT_SEC))
                .pollingEvery(Duration.ofMillis(POLLING_MILLS)).ignoring(NoSuchElementException.class);
        jsExecutor = (JavascriptExecutor) driver;
    }

    protected void selectValueFromDropDown(String xpathLocator, String targetValue) {
        // Find the dropdown element
        WebElement dropdownElement = getXPathDriverWebElement(xpathLocator);
        // Create a Select object for the dropdown
        Select select = new Select(dropdownElement);
        // Get all the options in the dropdown
        List<WebElement> options = select.getOptions();

        // Iterate through each option
        for (WebElement option : options) {
            String optionValue = option.getText();
            // Check if the option value matches the target value
            if (optionValue.equalsIgnoreCase(targetValue)) {
                // Select the matching option
                select.selectByVisibleText(optionValue);
                System.out.println("Selected value from list is: " + optionValue);
                break; // Exit the loop after selection
            }
        }
    }
    protected List<String> selectAllValuesFromDropDown(String xpathLocator) {
        // Find the dropdown element
        WebElement dropdownElement = getXPathDriverWebElement(xpathLocator);
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
        }
        // Return the list of option values
        return optionValues;
    }
    private void waitForJSReadyState() {
        wait.until(driver -> (jsExecutor.executeScript("return document.readyState").equals("complete")));
    }

    private void waitForAjaxToComplete() {
        jsExecutor.executeAsyncScript("var callback = arguments[arguments.length - 1];"
                + "var xhr = new XMLHttpRequest();" + "xhr.open('GET', '/Ajax_call', true);"
                + "xhr.onreadystatechange = function() {" + "  if (xhr.readyState == 4) {"
                + "    callback(xhr.responseText);" + "  }" + "};" + "xhr.send();");
    }

    protected WebElement getCSSDriverWebElement(String cssSelector) {
        WebElement foundElement = null;
        waitForPageToLoad();
        try {
            foundElement = (WebElement) wait
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
        } catch (Exception ignoreException) {
            reportLogger
                    .log("Exception getCSSDriverWebElement for " + cssSelector + " " + ignoreException.getMessage());
        }
        return foundElement;
    }

    protected WebElement getCSSRelativeWebElement(WebElement element, String cssSelector) {
        WebElement foundElement = null;
        waitForPageToLoad();
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
            foundElement = element.findElement(By.cssSelector(cssSelector));
        } catch (Exception ignoreException) {
            reportLogger
                    .log("Exception getCSSRelativeWebElement for " + cssSelector + " " + ignoreException.getMessage());
        }

        return foundElement;
    }

    protected List<WebElement> getCSSRelativeWebElements(WebElement element, String cssSelector) {
        List<WebElement> foundElement = null;
        waitForPageToLoad();
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
            foundElement = element.findElements(By.cssSelector(cssSelector));
        } catch (Exception ignoreException) {
            reportLogger
                    .log("Exception getCSSRelativeWebElements for " + cssSelector + " " + ignoreException.getMessage());
        }

        return foundElement;

    }

    protected List<WebElement> getCSSDriverWebElements(String cssSelector) {
        List<WebElement> foundElement = null;
        waitForPageToLoad();
        try {
            foundElement = (List<WebElement>) wait
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(cssSelector)));

        } catch (Exception ignoreException) {
            reportLogger
                    .log("Exception getCSSDriverWebElements for " + cssSelector + " " + ignoreException.getMessage());
        }
        return foundElement;
    }

    protected WebElement getXPathDriverWebElement(String xpathLocator) {
        WebElement foundElement = null;
        waitForPageToLoad();
        try {
            foundElement = (WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathLocator)));
        } catch (Exception ignoreException) {
            reportLogger
                    .log("Exception getXPathDriverWebElement for " + xpathLocator + " " + ignoreException.getMessage());
        }
        return foundElement;
    }

    protected WebElement performClickActionOnClassName(String className) {
        WebElement foundElement = null;
        waitForPageToLoad();

        foundElement = driver.findElement(By.className(className));
        if (foundElement.isEnabled() && foundElement.isDisplayed()) {
            foundElement.click();
        }
        return foundElement;
    }

    protected WebElement assertWebElementAvailabilityByXpath(WebDriver driver, String xpathString) {
        WebElement webElement = driver.findElement(By.xpath(xpathString));
        Assert.assertTrue(webElement.isEnabled() && webElement.isDisplayed());
        return webElement;
    }

    protected WebElement getXPathRelativeWebElement(WebElement element, String xpathLocator) {
        WebElement foundElement = null;
        waitForPageToLoad();
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathLocator)));
            foundElement = element.findElement(By.xpath(xpathLocator));
        } catch (Exception ignoreException) {
            reportLogger.log(
                    "Exception getXPathRelativeWebElement for " + xpathLocator + " " + ignoreException.getMessage());
        }

        return foundElement;
    }

    protected List<WebElement> getXPathDriverWebElements(String xpathLocator) {
        List<WebElement> foundElement = null;
        waitForPageToLoad();
        try {
            foundElement = (List<WebElement>) wait
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathLocator)));

        } catch (Exception ignoreException) {
            reportLogger.log(
                    "Exception getXPathDriverWebElements for " + xpathLocator + " " + ignoreException.getMessage());
        }
        return foundElement;
    }

    protected WebElement getIFrameWebElement(String iframeTitle, String csslocator) {
        List<WebElement> foundElement = null;
        WebElement foundIframeElement = null;
        waitForPageToLoad();
        try {
            foundElement = (List<WebElement>) wait
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("iframe")));
            for (WebElement iframe : foundElement) {
                if (iframeTitle.equals(iframe.getAttribute("title"))) {
                    foundIframeElement = driver.switchTo().frame(iframe).findElement(By.cssSelector(csslocator));
                    break;
                }
            }
        } catch (Exception ignoreException) {
            reportLogger.log("Exception getIFrameWebElement for " + csslocator + " " + ignoreException.getMessage());
        }
        return foundIframeElement;
    }

    protected WebElement getIFrameWebElementByiFrameNo(int iframeNo, String csslocator) {
        WebElement foundIframeElement = null;
        foundIframeElement = driver.switchTo().frame(iframeNo).findElement(By.cssSelector(csslocator));
        return foundIframeElement;
    }

    protected WebElement getNewTabWebElement(String csslocator) {
        WebElement foundElement = null;
        String currentHandle = null;
        waitForPageToLoad();
        try {

            Set<String> tabList = driver.getWindowHandles();
            currentHandle = driver.getWindowHandle();

            for (String actual : tabList) {
                if (!actual.equalsIgnoreCase(currentHandle)) {
                    foundElement = driver.switchTo().window(actual).findElement(By.cssSelector(csslocator));
                    break;
                }
            }
        } catch (Exception ignoreException) {
            reportLogger.log("Exception getNewTabWebElement for " + csslocator + " " + ignoreException.getMessage());
        }
        return foundElement;
    }

    protected void driverOnDefaultContent() {
        driver.switchTo().defaultContent();
    }

    protected void switchToChildWindow() throws InterruptedException {
        String parentWindowHandle = driver.getWindowHandle();
        // Get all window handles after opening the child window
        Set<String> allWindowHandles = driver.getWindowHandles();

        // Loop through the window handles and switch to the child window
        for (String handle : allWindowHandles) {
            if (!handle.equals(parentWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        Thread.sleep(5000);
        // Get the URL of the child window
        String childWindowUrl = driver.getCurrentUrl();
        System.out.println("Child window URL:" + childWindowUrl);
    }

    protected void scrollToElement(WebElement element) {
        waitForPageToLoad();
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected Select getCSSSelectOptions(String cssSelector) {
        Select foundElement = null;
        waitForPageToLoad();
        try {
            foundElement = new Select(getCSSDriverWebElement(cssSelector));
        } catch (Exception ignoreException) {
            reportLogger.log("Exception getCSSSelectOptions for " + cssSelector + " " + ignoreException.getMessage());
        }
        return foundElement;
    }

    protected void elementToBeClickableByXPath(String xpathSelector) {
        waitForPageToLoad();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathSelector)));
    }

    protected void waitforVisibilityofElement(String cssSelector) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
    }

    protected void waitforVisibilityofElementXpath(String xpathSelector) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathSelector)));
    }

    protected void waitForInvisibilityOfElement(String cssSelector) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(cssSelector)));
    }

    protected void elementToBeClickableByCSS(String cssSelector) {
        waitForPageToLoad();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
    }

    protected void jsClick(WebElement clickObject) {
        jsExecutor.executeScript("arguments[0].click()", clickObject);
    }

    protected void waitForPageToLoad() {
        waitForJSReadyState();
        waitForAjaxToComplete();
    }

    protected void mouseHoverToElementByCss(String cssSelector) {
        Actions actions = new Actions(driver);
        actions.moveToElement(getCSSDriverWebElement(cssSelector)).perform();
    }


    protected void controlClick(WebElement clickObject) {
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).click(clickObject).keyUp(Keys.CONTROL).build().perform();
    }

    protected void mouseHoverToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }

    protected boolean isDisplayWithoutWait(String cssSelector) {
        if (!driver.findElements(By.cssSelector(cssSelector)).isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    protected void jsMouseHoverToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "var evObj = document.createEvent('MouseEvents');"
                + "evObj.initEvent('mouseover', true, false);" + "arguments[0].dispatchEvent(evObj);";
        js.executeScript(script, element);
    }

    protected String getHTMLContent() {
        return driver.getPageSource();
    }

    protected void forceRefresh() {
        driver.navigate().to(driver.getCurrentUrl());
    }

    protected void switchToAlertPopUp() {
        Alert alert = driver.switchTo().alert();
        alert.getText();
        alert.accept();
    }

    protected void switchToAlertPopUpAndDismiss() {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    protected WebElement getLinkTextDriverWebElement(String linkText) {
        WebElement foundElement = null;
        waitForPageToLoad();
        try {
            foundElement = (WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(linkText)));
        } catch (Exception ignoreException) {
            reportLogger
                    .log("Exception getLinkTextDriverWebElement for " + linkText + " " + ignoreException.getMessage());
        }
        return foundElement;
    }



}
