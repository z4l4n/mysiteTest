package com.frontendart.testsuites.main.rightpanel.top;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.frontendart.categories.AUTHOR_Suite;
import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.CoreSuite;
import com.frontendart.categories.INSTITUTIONAL_ADMIN_Suite;
import com.frontendart.categories.RightPanelTopSuite;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.JunitTestClass;
import com.frontendart.locators.main.MainPageLocators;
import com.frontendart.managers.main.rightpanel.top.AboutManager;

/**
 * Class for about test
 * @author Zoli
 *
 */
@Category({ RightPanelTopSuite.class, AUTHOR_Suite.class, INSTITUTIONAL_ADMIN_Suite.class, CENTRAL_ADMIN_Suite.class })
public class AboutTest extends JunitTestClass {

	/**
	 * Check that about window is present
	 */
	@Test
	@Category(CoreSuite.class)
	public final void testAboutWindowIsPresent() throws InterruptedException {
		// Click on about button
		AboutManager.openAboutWindow();

		// Check about window is present
		assertTrue("The about window is not visible!", Utils.isThisElementVisible(MainPageLocators.ABOUT_WINDOW));

		// Close about window
		Utils.waitForAndClickOnGeneralWebElement(MainPageLocators.ABOUT_WINDOW_CLOSE);
	}
}
