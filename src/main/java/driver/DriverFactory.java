package driver;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import util.ApiUtil;
import util.ReportLogger;

public class DriverFactory {
	ReportLogger reportLogger = ReportLogger.getInstance();
	public static ThreadLocal<WebDriver> drivers = new ThreadLocal<>();
	static final String DEFAULT_BROWSER = "chrome";
	static final String DEFAULT_OS = "windows";
	static final String HEADLESS = ApiUtil.readProperty("Headless");
	static final long IMPLICITLY_WAIT = 3;
	public String downloadPath = "";

	private DriverFactory() {
		String operatingSystem = ApiUtil.readProperty("OS");
		reportLogger.log("OS: " + operatingSystem);
		if (operatingSystem == null)
			System.setProperty("OS", DEFAULT_OS);

		downloadPath = (operatingSystem.equals("windows") ? System.getProperty("user.dir") + "\\" + "downloads"
				: System.getProperty("user.dir") + "/" + "downloads");
	}

	private static DriverFactory instance = new DriverFactory();

	public static DriverFactory getInstance() {
		return instance;
	}

	public WebDriver initDriver(String browser) {
		WebDriver driver = drivers.get();
		if (driver == null) {
			if (browser == null || browser.isEmpty())
				browser = DEFAULT_BROWSER;

			switch (browser) {
			case "chrome":
				driver = new ChromeDriver(chromeOptionsSettings());
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT));
				driver.manage().window().maximize();
				break;
			}
		}
		drivers.set(driver);
		return driver;
	}

	public ChromeOptions chromeOptionsSettings() {
		HashMap<String, Object> chromePrefs = new HashMap<>();
		chromePrefs.put("download.default_directory", downloadPath);
		reportLogger.log("Chrome download folder: " + downloadPath);
		chromePrefs.put("plugins.plugins_disabled", new String[] { "Adobe Flash Player", "Chrome PDF Viewer" });
		chromePrefs.put("plugins.always_open_pdf_externally", true);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		options.addArguments("--remote-allow-origins=*");
		if (HEADLESS == null || HEADLESS.equalsIgnoreCase("true")) {
			options.addArguments("--no-sandbox");
			options.addArguments("--headless");
			options.addArguments("--disable-gup");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--window-size=1920,1080");
			options.addArguments("chrome://settings/clearBrowserData");
		}
		return options;
	}

	public static void clearDriverSession() {
		drivers.set(null);
	}
}
