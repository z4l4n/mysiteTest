package com.frontendart.managers.login;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import com.frontendart.common.Roles;
import com.frontendart.common.Utils;
import com.frontendart.locators.login.LoginMessageTypes;
import com.frontendart.locators.login.LoginPageLocators;
import com.frontendart.locators.main.MainPageLocators;

/**
 * Manager class for login tests.
 * @author Zoli
 *
 */
public class LoginManager {

	/**
	 * Checks main page is loaded after login
	 * 
	 * @param myUserName
	 */
	public static void checkPageLoadWithUsername(final String myUserName) {
		Utils.defaultWait();
		assertTrue("My profile button is not present!", Utils.isThisElementPresent(MainPageLocators.MY_PROFILE_BUTTON));
		final String textOfLabel = Utils.createGeneralWebElementFromEnum(MainPageLocators.MY_PROFILE_BUTTON).getText();

		// Username is present
		Utils.myAssertTrue("A megjelenő felületnek tartalmaznia kell a bejelentkezett felhasználó nevét.", textOfLabel.contains(myUserName));

		// PageLoad test
		assertTrue(Utils.isThisElementVisible(MainPageLocators.DATE_LABEL));
		assertTrue(Utils.isThisElementVisible(MainPageLocators.FORUM_BUTTON));
		assertTrue(Utils.isThisElementVisible(MainPageLocators.SYSTEM_MESSAGES_BUTTON));
		assertTrue(Utils.isThisElementVisible(MainPageLocators.MAIL_MESSAGES_BUTTON));
		assertTrue(Utils.isThisElementVisible(MainPageLocators.TICKETS_BUTTON));
		assertTrue(Utils.isThisElementVisible(MainPageLocators.MY_PROFILE_BUTTON));
		assertTrue(Utils.isThisElementVisible(MainPageLocators.LOGOUT_BUTTON));
	}

	/**
	 * Login with username and password
	 */
	public static void loginWithThisUsernameAndPassword(final List<String> list) {
		Utils.defaultWait();
		Utils.waitForElementPresent(LoginPageLocators.LOGIN_TABPANEL);

		// Fill fields and press login button
		Utils.writeTextToThisField(list.get(0), LoginPageLocators.USERNAME_TEXTFIELD);
		Utils.writeTextToThisField(list.get(1), LoginPageLocators.PASSWORD_TEXTFIELD);
		Utils.waitForAndClickOnGeneralWebElement(LoginPageLocators.LOGIN_BUTTON);
	}

	/**
	 * Login with role
	 */
	public static void loginSuccessfullyWithThisRole(final Roles role) {
		loginWithThisUsernameAndPassword(Arrays.asList(role.getUsername(), role.getPassword()));

		// TODO: Check that no error is present - this is a bit shaky
		assertTrue("An unexpected messagebox is present on the login page! Could not login with this username: " + role.getUsername(),
				Utils.isMessageBoxNotPresentWithText(LoginMessageTypes.ERROR));

		Utils.acceptMessageBoxIfVisible();
		Utils.defaultWait();
		// If table is does not load, we throw an error
		/*	try {
				RecordSelectionManager.waitUntilTableIsReady();
			} catch (final AssertionError e) {
				fail("After the login, the content panel did not loaded in " + TestConfiguration.maxWaitTimeInSec + " seconds! "
						+ "Error message is: " + e.getMessage());
			}
		*/
		// Check for no message error
		assertTrue("An unexpected messagebox is present after login with this username: " + role.getUsername(),
				Utils.isMessageBoxNotPresentWithText(LoginMessageTypes.NO_MESSAGE_ERROR));

		//Check for other message boxes (like system message)
		Utils.acceptMessageBoxIfVisible();

		// Check for news window
		if (Utils.isThisElementPresent(MainPageLocators.NEWS_WINDOW)) {
			Utils.waitForAndClickOnGeneralWebElement(MainPageLocators.NEWS_WINDOW_CLOSE);
		}
	}

	/**
	 * Do successful login
	 * 
	 * @param role
	 */
	public static void doSuccessfulLoginWithRole(final Roles role) {
		loginSuccessfullyWithThisRole(role);
		checkPageLoadWithUsername(role.getUsername());
	}

}
