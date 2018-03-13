package com.frontendart.testsuites.misc;

import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.CoreSuite;
import com.frontendart.categories.MiscSuite;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.DontLoginJunitTestClass;
import com.frontendart.locators.gui2.HomePageLocators;
import com.frontendart.locators.login.LoginPageLocators;
import com.frontendart.locators.main.MainPageLocators;
import com.frontendart.managers.gui2.Gui2GeneralManager;
import com.frontendart.managers.login.LoginManager;
import com.frontendart.managers.login.LogoutManager;
import com.frontendart.managers.misc.HelpManager;

/**
 * Test class for help tests.
 * @author Zoli
 *
 */
@Category({ MiscSuite.class, CENTRAL_ADMIN_Suite.class })
@SuppressWarnings({ "PMD.JUnitTestsShouldIncludeAssert", "PMD.JUnitTestContainsTooManyAsserts" })
public class HelpTest extends DontLoginJunitTestClass {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LogManager.getLogger("HelpTest");

	// This test class should only run with central admin rights
	@BeforeClass
	public static void beforeMethod() {
		org.junit.Assume.assumeTrue(Utils.actualRoleIsCentralAdmin());
	}

	/**
	 * basic help test
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/963">#963</a>
	 */
	@Test
	@Category(CoreSuite.class)
	public final void testBasicHelp() {
		Utils.writeMyRedmineIssues("#963");

		HelpManager.checkHelpButtons(driver, LoginPageLocators.HELP_BUTTON);
	}

	/**
	 * basic helpdesk test
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/964">#964</a>
	 */
	@Test
	@Category(CoreSuite.class)
	public final void testBasicHelpdesk() {
		Utils.writeMyRedmineIssues("#964");

		HelpManager.checkHelpButtons(driver, LoginPageLocators.HELPDESK_BUTTON);
	}

	/**
	 * basic public search
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1418">#1418</a>
	 */
	@Test
	@Category(CoreSuite.class)
	public final void testBasicPublicSearch() {
		Utils.writeMyRedmineIssues("#1418");

		// Click on gui2 button
		Utils.waitForAndClickOnGeneralWebElement(LoginPageLocators.PUBLIC_SEARCH_BUTTON);

		// Switch to new window
		final String winHandleBefore = driver.getWindowHandle();
		for (final String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

		// Validate
		Gui2GeneralManager.checkGui2Page(HomePageLocators.class);
		Utils.myAssertTrue("A publikus keresőnek kell betöltődnie.", driver.getCurrentUrl().contains("gui2"));

		// Close and switch back to original window
		driver.close();
		driver.switchTo().window(winHandleBefore);
	}

	/**
	 * basic help icon
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1372">#1372</a>
	 */
	@Test
	@Category(CoreSuite.class)
	public final void testBasicHelpIcon() {
		Utils.writeMyRedmineIssues("#1372");

		// Login
		LoginManager.loginSuccessfullyWithThisRole(Utils.getActualRole());

		// Click on Help
		Utils.waitForAndClickOnGeneralWebElement(MainPageLocators.HELP_BUTTON);

		// Validate
		assertTrue("Meg kell jelennie a help ablaknak.", Utils.isThisElementVisible(MainPageLocators.HELP_WINDOW));
		Utils.myAssertFalse("Nem szabad hibaüzenetnek megjelennie.", Utils.acceptMessageBoxIfVisible());

		// Validate
		final String helpWindowHeaderText = Utils.createGeneralWebElementFromEnum(MainPageLocators.HELP_WINDOW_HEADER).getText();
		Utils.myAssertTrue("A megjelenő Help ablak fejlécének tartalaznia kell a Segítség/Help szót.",
				helpWindowHeaderText.contains("Súgó") || helpWindowHeaderText.contains("Help"));

		// Close and validate
		Utils.waitForAndClickOnGeneralWebElement(MainPageLocators.HELP_WINDOW_CLOSE);
		LogoutManager.logout();
	}

	/**
	 * Help contains Ticket information
	 * 
	 * TODO: fix this 
	 * 
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/2896">#2896</a>
	 */
	/*@Test
	public final void testHelpContainsTicketInfo() {
		Utils.writeMyRedmineIssues("#2896");

		// Login
		LoginManager.loginSuccessfullyWithThisRole(Utils.getActualRole());

		// Click on Help
		Utils.waitForAndClickOnGeneralWebElement(MainPageLocators.HELP_BUTTON);

		// Validate
		Utils.waitForElementPresent(MainPageLocators.HELP_WINDOW_TICKET_LINK);
		assertTrue("A help ablakban kell lennie leírásnak a cédulakezelésről.",
				Utils.isThisElementVisible(MainPageLocators.HELP_WINDOW_TICKET_LINK));

		// Close
		Utils.waitForAndClickOnGeneralWebElement(MainPageLocators.HELP_WINDOW_CLOSE);
		LogoutManager.logout();
	}*/
}
