package stepdefinations;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import util.ReportLogger;

@CucumberOptions(
		plugin = { "progress", "html:target/cucumber-reports/cucumber-report.html",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"timeline:test-output-thread/"
				}, 
		monochrome = true,
		glue = { "stepdefinations", "hooks"},
		features = { "src/test/resources/features" }, tags = "@RegularVendorUITT"
)

public class ParallelRun extends AbstractTestNGCucumberTests {
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
	ReportLogger reportLogger = ReportLogger.getInstance();
}