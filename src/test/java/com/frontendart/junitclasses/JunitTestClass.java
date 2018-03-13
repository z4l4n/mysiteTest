package com.frontendart.junitclasses;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ThreadGuard;

import com.frontendart.common.BrowserTypes;
import com.frontendart.common.TestConfiguration;
import com.frontendart.common.Utils;
import com.frontendart.locators.login.LoginPageLocators;
import com.frontendart.managers.login.LoginManager;
import com.frontendart.managers.login.LogoutManager;

/**
 * Basic Junit test class.
 *
 * @author Zoli
 *
 */
public class JunitTestClass {

    /**
     * Logger
     */
    private static final Logger LOGGER = LogManager.getLogger("JunitTestClass");

    @Rule
    public TestName testName = new TestName();

    // Driver
    protected static WebDriver driver = null;
    static {

        // java -jar "selenium-server-standalone-2.53.0.jar" -Dwebdriver.firefox.bin="/home/zalan/ff46/firefox/firefox"
        //

        System.setProperty("webdriver.gecko.driver", "/home/zalan/geckodriver");
        System.setProperty("webdriver.chrome.driver", "/home/zalan/chromedriver");
    }

    /**
     * Constructor
     */
    public JunitTestClass() {

        if (TestConfiguration.getDesiredBrowser() == null) {
            driver = initWebDriverOfBrowserType(BrowserTypes.FIREFOX);
        } else {
            driver = initWebDriverOfBrowserType(TestConfiguration.getDesiredBrowser());
        }

        // Maximize window
        //driver.manage().window().maximize();

        if (!(TestConfiguration.getDesiredBrowser().equals(BrowserTypes.GOOGLECHROME))) {
            driver.manage().window().maximize();
        }

    }

    /**
     * initializing webdriver of the given browser type
     *
     * @param desiredBrowserType
     * @return
     * @throws MalformedURLException
     */
    private static WebDriver initWebDriverOfBrowserType(final BrowserTypes desiredBrowserType) {
        WebDriver result = null;

        // Start cycle
        int attempts = 1;
        boolean driverIsInitialized = false;
        while ((!driverIsInitialized) && (attempts < 16)) {
            LOGGER.info("WebDriver initialization try count: " + attempts);
            try {
                driverIsInitialized = true;
                result = doInit(desiredBrowserType);
            } catch (final WebDriverException e) {
                System.out.println(e.getMessage());
                driverIsInitialized = false;
            }
            attempts++;
        }

        // If web driver initialization fails, we fail the test
        assertNotEquals("WebDriver initialization problem.", result, null);

        return result;
    }

    /**
     * Does initialization
     *
     * @param desiredBrowserType
     */
    private static WebDriver doInit(final BrowserTypes desiredBrowserType) {
        WebDriver result = null;

        try {
            // result = new RemoteWebDriver(new URL(TestConfiguration.targetPC),
            // desiredBrowserType.getCapability());

            if (TestConfiguration.getDesiredBrowser().equals(BrowserTypes.GOOGLECHROME)) {
                DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("test-type");
                options.addArguments("start-maximized");
                options.addArguments("--js-flags=--expose-gc");
                options.addArguments("--enable-precise-memory-info");
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--disable-default-apps");
                options.addArguments("test-type=browser");
                options.addArguments("disable-infobars");
                chromeCapabilities.setCapability(ChromeOptions.CAPABILITY, options);

                result = ThreadGuard.protect(
                        new RemoteWebDriver(new URL(TestConfiguration.targetPC), chromeCapabilities));
            }

            else {
                result = ThreadGuard.protect(
                        new RemoteWebDriver(new URL(TestConfiguration.targetPC), desiredBrowserType.getCapability()));
            }

        } catch (final MalformedURLException e) {
            fail(e.getMessage());
        }

        return result;
    }

    /**
     * Setup.
     *
     * @throws MalformedURLException
     * @throws WebTestException
     *
     * @throws Exception
     */
    @Before
    public void setUp() {

        Utils.switchTab();

        LOGGER.info("Teszteset azonosítója: " + testName.getMethodName());
        driver.get(TestConfiguration.myCiteServerUrl);
        Utils.waitForElementPresent(LoginPageLocators.START_PAGE);
        LOGGER.info(
                "Előfeltétel: Megjelenik a MyCite2 nyitófelülete ezen az URL-en: " + TestConfiguration.myCiteServerUrl);

        // Login
        LoginManager.loginSuccessfullyWithThisRole(Utils.getActualRole());
    }

    /**
     * TearDown
     */
    @After
    public void tearDown() {
        // Logout
        LogoutManager.logout();

        // Close driver
        Utils.closeDriver();

        LOGGER.info("Teszteset vége.\n");

    }

//	/**
//	 * TearDown after classes
//	 * Clean temp folder
//	 */
//	@AfterClass
//	public static void tearDownAfterClass(){
//		// delete all "scoped_dir" from temp folders
//				File dir = new File(System.getProperty("user.home") + "\\AppData\\Local\\Temp");
//				File[] foundFiles = dir.listFiles(new FilenameFilter() {
//					public boolean accept(File dir, String name) {
//						return name.startsWith("scoped_dir");
//					}
//				});
//
//				try {
//
//					for (File file : foundFiles) {
//						try {
//							FileUtils.deleteDirectory(file);
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							LOGGER.info(e.getMessage());
//						}
//					}
//
//				} catch (NullPointerException e) {
//					e.printStackTrace();
//				}
//	}
}
