package webdriver;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * 
 * author Hézio D. Fernandes <br>
 * Neoway - 12/06/2015 <br>
 * <a href="mailto:fernandes.bcc@gmail.com">fernandes.bcc@gmail.com</a>
 *
 */
public class ChromeDriver {
	public ChromeDriver() {
		
	}

	private static RemoteWebDriver driver;
	private static ChromeDriverService service;

	public static WebDriver createAndStartService() {
		if (driver == null) {
			service = new ChromeDriverService.Builder()
					.usingDriverExecutable(new File("/usr/bin/chromedriver"))
					.usingAnyFreePort().build();
			try {
				service.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
			driver = new RemoteWebDriver(service.getUrl(),
					DesiredCapabilities.chrome());
		}
		return driver;

	}
}
