package com.frontendart.testsuites.misc;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.frontendart.categories.AUTHOR_Suite;
import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.CoreSuite;
import com.frontendart.categories.INSTITUTIONAL_ADMIN_Suite;
import com.frontendart.categories.MiscSuite;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.DontLoginJunitTestClass;
import com.frontendart.locators.login.LoginPageLocators;
import com.frontendart.locators.main.MainPageLocators;
import com.frontendart.managers.login.LoginManager;
import com.frontendart.managers.login.LogoutManager;

/**
 * Test class for language change test
 * @author Zoli
 *
 */
@Category({ MiscSuite.class, AUTHOR_Suite.class, INSTITUTIONAL_ADMIN_Suite.class, CENTRAL_ADMIN_Suite.class })
public class LanguageTest extends DontLoginJunitTestClass {

	/**
	 * Simple language change (assumes that Hungarian is the active)
	 */
	@Test
	@Category(CoreSuite.class)
	public final void testSimpleLanguageChange() {
		// Click
		Utils.waitForAndClickOnGeneralWebElement(LoginPageLocators.LANGUAGE_CHANGE);
		Utils.waitForAndClickOnGeneralWebElement(LoginPageLocators.LANGUAGE_CHANGE_SECOND_OPTION);
		Utils.defaultWait();
		Utils.waitForElementPresent(LoginPageLocators.USERNAME_TEXTFIELD);

		// Validate
		assertTrue("The english language picker is not visible!",
				Utils.isThisElementVisible(LoginPageLocators.LANGUAGE_CHANGE_ENGLISH));
		assertFalse("The hungarian language picker should not be present!",
				Utils.isThisElementPresent(LoginPageLocators.LANGUAGE_CHANGE_HUNGARIAN));
	}

	/**
	 * Language change
	 */
	@Test
	@Category(CoreSuite.class)
	public final void testInsideLanguageChange() {
		// Login
		LoginManager.loginSuccessfullyWithThisRole(Utils.getActualRole());

		// Remember original language
		Utils.waitMillisec(10000);
		String labelBeforeChange = Utils.createGeneralWebElementFromEnum(MainPageLocators.CHANGE_LANGUAGE_BUTTON).getText();
		if ("English".equals(labelBeforeChange)) {
			Utils.waitForAndClickOnGeneralWebElement(MainPageLocators.CHANGE_LANGUAGE_BUTTON);
			Utils.waitForAndClickOnGeneralWebElement(MainPageLocators.CHANGE_LANGUAGE_HUNGARIAN);
			Utils.waitMillisec(10000);
			labelBeforeChange = Utils.createGeneralWebElementFromEnum(MainPageLocators.CHANGE_LANGUAGE_BUTTON).getText();
		}

		// Change language
		Utils.waitForAndClickOnGeneralWebElement(MainPageLocators.CHANGE_LANGUAGE_BUTTON);
		Utils.waitForAndClickOnGeneralWebElement(MainPageLocators.CHANGE_LANGUAGE_ENGLISH);
		Utils.waitMillisec(10000);

		// Validate
		final String labelAfterChange = Utils.createGeneralWebElementFromEnum(MainPageLocators.CHANGE_LANGUAGE_BUTTON).getText();
		assertNotEquals("The system didn't change the language.", labelAfterChange, labelBeforeChange);

		// Cleanup - set original language
		Utils.waitForAndClickOnGeneralWebElement(MainPageLocators.CHANGE_LANGUAGE_BUTTON);
		Utils.waitForAndClickOnGeneralWebElement(MainPageLocators.CHANGE_LANGUAGE_HUNGARIAN);

		// Logout
		Utils.waitMillisec(10000);
		LogoutManager.logout();
	}

	/**
	 * Simple language change, storing language option
	 */
	@Test
	@Category(CoreSuite.class)
	public final void testSimpleRememberLanguage() {
		// Click - TODO: this assumes hungarian as first language...
		Utils.waitForAndClickOnGeneralWebElement(LoginPageLocators.LANGUAGE_CHANGE);
		Utils.waitForAndClickOnGeneralWebElement(LoginPageLocators.LANGUAGE_CHANGE_SECOND_OPTION);
		LoginManager.loginSuccessfullyWithThisRole(Utils.getActualRole());

		// Get logout element text
		final String valueFirst = Utils.createGeneralWebElementFromEnum(MainPageLocators.LOGOUT_BUTTON).getText();

		// Logout-Login
		LogoutManager.logout();
		LoginManager.loginSuccessfullyWithThisRole(Utils.getActualRole());

		// Get the logout element text again
		final String valueSecond = Utils.createGeneralWebElementFromEnum(MainPageLocators.LOGOUT_BUTTON).getText();

		// Validation
		assertTrue("The system didn't remember the selected language.",
				Utils.myAssertEquals("A rendszernek meg kell jegyeznie a korábban kiválasztott nyelvet.",
						valueFirst, valueSecond));

		// Logout
		LogoutManager.logout();
	}
}
