package br.com.felipemira.ui.automator.utils.core;

import br.com.felipemira.ui.automator.utils.po.GooglePage;
import cucumber.api.java.After;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.Duration;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * 
 *  @author felipe.mira.ext criado em 01/04/2019.
 * 
 * Classe Utils para o Selenium Identifica qual o browser escolhido no
 * config.properties e inicializa o webdriver correspondente
 * 
 */
@Component
public class Selenium {

	public static Wait wait = null;
	public static WebDriver driver = null;
	private static ChromeDriverService service = null;

	@Getter
	@Setter
	@Value("${selenium.browser.name}")
	private String borwserName;


	/**
	 * Verifica qual o browser escolhido no arquivo de propriedades inicializa o
	 * driver apropriado e o retorna
	 * 
	 * @return retorna instancia do WebDriver
	 */
	public WebDriver getDriver() {
		String os = System.getProperty("os.name").toLowerCase();
		String pathDriver = System.getProperty("user.dir") + "/src/test/java/br/com/felipemira/ui/automator/utils/driver/";
		if (driver == null) {
			if (borwserName.equals("chrome")) {
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--headless");
				if (os.contains("windows")) {
					File file = new File(pathDriver + "windows/chromedriver.exe");
					System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
					driver = new ChromeDriver(chromeOptions);
				} else if (os.contains("mac")) {
					File file = new File(pathDriver + "mac/chromedriver");
					System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
					driver = new ChromeDriver(chromeOptions);
				} else if(os.contains("linux")){
					File file = new File(pathDriver + "mac/chromedriver");
					System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
					driver = new ChromeDriver(chromeOptions);
				}
			} else if (borwserName.equals("ie")) {
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				capabilities.setCapability("ignoreProtectedModeSettings", true);
				File file = new File(pathDriver + "windows/IEDriverServer_x64.exe");
				System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
				driver = new InternetExplorerDriver(new InternetExplorerOptions(capabilities));
			} else if (borwserName.equals("edge")) {
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				capabilities.setCapability("ignoreProtectedModeSettings", true);
				File file = new File(pathDriver + "windows/MicrosoftWebDriver.exe");
				System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
				driver = new InternetExplorerDriver(new InternetExplorerOptions(capabilities));
			}else if (borwserName.equals("firefox")) {
				File file = new File(pathDriver + "windows/geckodriver.exe");
				System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
				System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
				System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

				FirefoxProfile firefoxProfile = new FirefoxProfile();
				// Set profile to accept untrusted certificates
				firefoxProfile.setAcceptUntrustedCertificates(true);

				// Set profile to not assumet certificate issuer is untrusted
				firefoxProfile.setAssumeUntrustedCertificateIssuer(false);

				// Set download location and file types
				firefoxProfile.setPreference("browser.download.folderList", 2);
				firefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);

				firefoxProfile.setPreference("browser.download.dir", System.getProperty("java.io.tmpdir"));
				firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);

				firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",
						"text/csv,application/pdf,application/csv,application/vnd.ms-excel,application/aspx,text/html,text/xhtml,application/xhtml+xml,application/xml");

				firefoxProfile.setPreference("plugin.scan.Acrobat", "99.0");
				firefoxProfile.setPreference("plugin.scan.plid.all", false);

				// Set to false so popup not displayed when download finished.
				firefoxProfile.setPreference("browser.download.manager.showAlertOnComplete", false);

				firefoxProfile.setPreference("browser.download.panel.shown", false);
				firefoxProfile.setPreference("browser.download.useToolkitUI", true);

				// Set this to true to disable the pdf opening
				firefoxProfile.setPreference("pdfjs.disabled", true);

				// desabilita botao de imprimir
				// firefoxProfile.setPreference("dom.disable_beforeunload", true);
				firefoxProfile.setPreference("print.always_print_silent", true);
				firefoxProfile.setPreference("print.show_print_progress", false);

				// desabilita o proxy
				firefoxProfile.setPreference("network.proxy.type", 0);

					driver = new FirefoxDriver(new FirefoxOptions().setProfile(firefoxProfile));
			}
		}

		wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
		driver.manage().timeouts().setScriptTimeout(100,SECONDS);

		return driver;
	}

	/**
	 * comentarios: Para o servico do browser.
	 */
	public static void resetDriver() {
		driver.close();
		driver.quit();
		driver = null;
	}
}