package hooks;

import driver.DriverFactory;
import io.cucumber.core.backend.TestCaseState;
import io.cucumber.java.*;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestCase;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import util.EnvPropertiesReader;
import util.FileUtil;
import util.ReportLogger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class MyHooks {
	public static ReportLogger reportLogger = ReportLogger.getInstance();
	private int currentStepDefIndex = 0;
	private String currentStepDescr = null;
	private int stepCount = 0;

	@BeforeAll
	public static void before_all() {
		cleanUP();
		setup();
		reportLogger.log("----------------------- Test Automation Started-----------------------");
	}

	public static void cleanUP() {
		File logs = new File("./logs");
		if (logs.exists())
			FileUtil.recursiveDelete(logs);
		File reports = new File("./Reports");
		if (reports.exists())
			FileUtil.recursiveDelete(reports);
	}

	public static void setup() {
		try {
			EnvPropertiesReader.getInstance();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@After
	public static void afterScenario(Scenario scenario) throws IOException {

		if (scenario.isFailed()) {
			String scenarioName = scenario.getName();
			WebDriver driver = DriverFactory.drivers.get();
			// TakesScreenshot ts = (TakesScreenshot) driver;
			// byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
			Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
					.takeScreenshot(driver);
			BufferedImage img = screenshot.getImage();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(img, "png", baos);
			byte[] bytes = baos.toByteArray();
			scenario.attach(bytes, "image/png", scenarioName);
		}
		reportLogger.log("---------------------- TEST COMPLETED ----------------------");
		reportLogger.log(scenario.getStatus().toString().toUpperCase() + " ==> " + scenario.getName());
		reportLogger.log("------------------------------------------------------------");
		if (DriverFactory.drivers.get() != null) {
			DriverFactory.drivers.get().quit();
			DriverFactory.clearDriverSession();
		}
	}

	@Before
	public void beforeScenario(Scenario scenario) {
		reportLogger.log("----------------------- TEST STARTED -----------------------");
		reportLogger.log("STARTING Scenario ==> " + scenario.getName());
		reportLogger.log("------------------------------------------------------------");
	}

	@BeforeStep
	public void getCurrentStep(Scenario scenario) throws Exception {
		Field delegate = scenario.getClass().getDeclaredField("delegate");
		delegate.setAccessible(true);
		TestCaseState testCaseState = (TestCaseState) delegate.get(scenario);
		Field testCaseField = testCaseState.getClass().getDeclaredField("testCase");
		testCaseField.setAccessible(true);
		TestCase testCase = (TestCase) testCaseField.get(testCaseState);
		List<PickleStepTestStep> testStepTitles = testCase.getTestSteps().stream()
				.filter(step -> step instanceof PickleStepTestStep).map(step -> (PickleStepTestStep) step)
				.collect(Collectors.toList());
		PickleStepTestStep stepDefs = (PickleStepTestStep) testStepTitles.get(currentStepDefIndex);
		currentStepDescr = stepDefs.getStep().getText();
		reportLogger.log("Step " + stepCount + ": " + currentStepDescr);
		stepCount++;
		currentStepDefIndex++;
	}

	@AfterAll
	public static void before_or_after_all() {
		reportLogger.log("----------------------- Test Execution Completed!-----------------------");
	}
}
