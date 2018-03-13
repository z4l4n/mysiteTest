package com.frontendart.junitclasses;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import com.frontendart.common.TestConfiguration;
import com.frontendart.common.Utils;
import com.frontendart.locators.gui2.LeftPanelLocators;

/**
 * Junit test class for tests that don't need login
 * @author Zoli
 *
 */
public class Gui2JunitTestClass extends JunitTestClass {
	/**
	 * Logger
	 */
	private static final Logger LOGGER = LogManager.getLogger("DontLoginJunitTestClass");

	@Rule
	public TestName testName = new TestName();

	/**
	 * Setup
	 * 
	 * @author gyizol
	 */
	@Before
	@Override
	public void setUp() {
		Utils.switchTab();
		
		LOGGER.info("Teszteset azonosítója: " + testName.getMethodName());
		
		driver.get(TestConfiguration.gui2AuthenticatedServerUrl);		
		Utils.defaultWait();
		
		driver.get(TestConfiguration.gui2ServerUrl);
		LOGGER.info("Előfeltétel: Megjelenik a GUI2 nyitófelülete ezen az URL-en: " + TestConfiguration.gui2ServerUrl);
		Utils.defaultWait();

		Utils.waitForElementPresent(LeftPanelLocators.ALL_DISPLAYED_INSTITUTES);
	}

	/**
	 * Teardown
	 * 
	 * @author gyizol
	 */
	@After
	@Override
	public void tearDown() {
		// Close driver
		Utils.closeDriver();

		LOGGER.info("Teszteset vége.\n");
	}
}
