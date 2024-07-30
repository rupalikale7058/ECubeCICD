package runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import util.ReportLogger;

@CucumberOptions(features = "src/test/resources/features", glue = {"stepdefinations",
        "hooks"}, publish = true, plugin = {"progress", "html:target/cucumber-reports/cucumber-report.html",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}, tags = "@REG")

public class TestRunner extends AbstractTestNGCucumberTests {
    ReportLogger reportLogger = ReportLogger.getInstance();

} 