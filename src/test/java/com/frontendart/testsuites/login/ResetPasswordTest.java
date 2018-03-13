package com.frontendart.testsuites.login;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.frontendart.categories.AUTHOR_Suite;
import com.frontendart.categories.LoginLogoutSuite;
import com.frontendart.common.Constants;
import com.frontendart.common.Roles;
import com.frontendart.common.TestConfiguration;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.DontLoginJunitTestClass;
import com.frontendart.locators.login.LoginPageLocators;
import com.frontendart.locators.login.ResetPasswordLocators;
import com.frontendart.locators.login.ResetPasswordMessageTypes;
import com.frontendart.locators.main.MainPageLocators;
import com.frontendart.locators.registration.RegistrationLocators;
import com.frontendart.managers.login.LoginManager;
import com.frontendart.managers.login.LogoutManager;
import com.frontendart.managers.login.ResetPasswordManager;
import com.frontendart.managers.main.leftpanel.RecordSelectionManager;

/**
 * Test class for forgot password tests.
 * 
 * The following steps should be done after each testsite update:
 * 	- the email address of the mtmtuser3 user should be approved
 * 
 * TODO: refakt (CBO)
 * 
 * @author Zoli
 *
 */
@Category({ LoginLogoutSuite.class, AUTHOR_Suite.class })
@SuppressWarnings({ "PMD.JUnitTestsShouldIncludeAssert", "PMD.JUnitTestContainsTooManyAsserts" })
public class ResetPasswordTest extends DontLoginJunitTestClass {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LogManager.getLogger("ResetPasswordTest");

	// This test class should only run with author rights - meaning that it will only run once
	@BeforeClass
	public static void beforeMethod() {
		org.junit.Assume.assumeTrue(Roles.AUTHOR.equals(Utils.getActualRole()));
	}

	/**
	 * Simple forgot password test - add new password on new password page
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/957">#957</a>
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1275">#1275</a>
	 * @throws InterruptedException 
	 */
	@Test
	public final void testResetPasswordPOZ() throws InterruptedException {
		Utils.writeMyRedmineIssues("#957#1275");

		// Go to reset password page, and change pwd
		ResetPasswordManager.performResetPasswordProcessWithCurrentRole();
		Utils.isMessageBoxVisible();
		assertTrue("There should be a message box present with success message.",
				Utils.isMessageBoxPresentWithText(ResetPasswordMessageTypes.SUCCESS));
		LOGGER.info("Megjelenik a MyCite2 nyitófelülete ezen az URL-en: " + TestConfiguration.myCiteServerUrl);

		driver.get(TestConfiguration.myCiteServerUrl);
		
		// Click to the login tab
		Utils.waitForAndClickOnGeneralWebElement(LoginPageLocators.LOGIN_TABPANEL_HEADER);
		
		//Refresh the page
		driver.navigate().refresh();
			
		// Login with new password, then logout
		LoginManager.loginWithThisUsernameAndPassword(Arrays.asList(Utils.getActualRole().getUsername(), Constants.FORGOT_PASSWORD_PASSWORD));
		
		// Check for news window
		RecordSelectionManager.waitUntilTableIsReady();
		if (Utils.isThisElementPresent(MainPageLocators.NEWS_WINDOW)) {
			Utils.waitForAndClickOnGeneralWebElement(MainPageLocators.NEWS_WINDOW_CLOSE);
		}

		LogoutManager.logout();

		//TODO:  Change password back
	}

	/**
	 * Simple forgot password test - add new password on new password page, without password1 field
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1144">#1144</a>
	 */
	@Test
	public final void testPasswordField() {
		// Go to reset password page, and fill fields without password
		ResetPasswordManager.navigateToResetPasswordPage();
		ResetPasswordManager.writeThisTextToUsernameField(Utils.getActualRole().getUsername());
		ResetPasswordManager.writeThisTextToPasswordAgainField(Constants.FORGOT_PASSWORD_PASSWORD);
		ResetPasswordManager.clickOnSendButton();

		// Validation
		assertTrue("There should be a message box present with empty field error message.",
				Utils.isMessageBoxPresentWithText(ResetPasswordMessageTypes.EMPTY_FIELD));
	}

	/**
	 * Simple forgot password test - add new password on new password page, without password2
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1144">#1144</a>
	 */
	@Test
	public final void testPasswordAgainField() {
		Utils.writeMyRedmineIssues("#1144");

		// Go to reset password page, and fill fields without password again
		ResetPasswordManager.navigateToResetPasswordPage();
		ResetPasswordManager.writeThisTextToUsernameField(Utils.getActualRole().getUsername());
		ResetPasswordManager.writeThisTextToPasswordField(Constants.FORGOT_PASSWORD_PASSWORD);
		ResetPasswordManager.clickOnSendButton();

		// Validation
		assertTrue("There should be a message box present with empty field error message.",
				Utils.isMessageBoxPresentWithText(ResetPasswordMessageTypes.EMPTY_FIELD));
	}

	/**
	 * Simple forgot password test - use the reset password link twice
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1204">#1204</a>
	 */
	@Test
	public final void testUseResetPasswordTokenTwice() {
		Utils.writeMyRedmineIssues("#1204");

		// Use reset password link for the first time (success)
		final String resetPwdLink = ResetPasswordManager.performResetPasswordProcessWithCurrentRole();
		Utils.waitForMessageBoxToBePresent();
		assertTrue("There should be a message box present with success message.",
				Utils.isMessageBoxPresentWithText(ResetPasswordMessageTypes.SUCCESS));
		LOGGER.info("Megjelenik a MyCite2 nyitófelülete ezen az URL-en: " + TestConfiguration.myCiteServerUrl);

		// Use link for the 2nd time
		driver.navigate().refresh();
		driver.get(resetPwdLink);
		ResetPasswordManager.writeThisTextToUsernameField(Utils.getActualRole().getUsername());
		ResetPasswordManager.writeThisTextToPasswordField(Constants.FORGOT_PASSWORD_PASSWORD);
		Utils.waitMillisec(1000);
		ResetPasswordManager.writeThisTextToPasswordAgainField(Constants.FORGOT_PASSWORD_PASSWORD);
		Utils.waitMillisec(1000);
		ResetPasswordManager.clickOnSendButton();

		// Validation
		assertTrue("There should be a message box present with wrong link error message.",
				Utils.isMessageBoxPresentWithText(ResetPasswordMessageTypes.WRONG_LINK));
	}

	/**
	 * Simple forgot password test - use the reset password link after admin login
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1135">#1135</a>
	 */
	@Test
	public final void testUseResetPasswordTokenAfterAdminLogin() {
		Utils.writeMyRedmineIssues("#1135");

		// Get reset password link
		final String resetPwdLink = ResetPasswordManager.navigateToResetPasswordPage();

		driver.get(TestConfiguration.myCiteServerUrl);
		
		// Click to the login tab
		Utils.waitForAndClickOnGeneralWebElement(LoginPageLocators.LOGIN_TABPANEL_HEADER);
				
		//Refresh the page
		driver.navigate().refresh();
			
		// Login with admin
		LoginManager.loginSuccessfullyWithThisRole(Roles.CENTRAL_ADMIN);

		// Try to use the reset pws link
		driver.navigate().refresh();
		driver.get(resetPwdLink);

		// Validation
		Utils.waitForElementPresent(ResetPasswordLocators.RESET_PASSWORD_BUTTON);
		assertTrue("Reset password page should be present!", Utils.isThisElementPresent(ResetPasswordLocators.RESET_PASSWORD_BUTTON));
	}

	// FURTHER TESTS TO BE IMPLEMENTED

	/**
	 * Simple forgot password test - add new password on new password page, without username
	 */

	/**
	 * Simple forgot password test - add new password on new password page, without password1 and password2
	 */

	/**
	 * Simple forgot password test - add new password on new password page, without username, password1 and password2
	 */

	/**
	 * Simple forgot password test - add new password on new password page, with not a valid username
	 */

	/**
	 * Simple forgot password test - add new password on new password page, with too short password
	 */

	/**
	 * Simple forgot password test - add new password on new password page, with not a same passwords
	 */
	@Test
	public final void testDifferentPasswords() {
		// Go to reset password page, and fill fields without password again
		ResetPasswordManager.navigateToResetPasswordPage();
		ResetPasswordManager.writeThisTextToUsernameField(Utils.getActualRole().getUsername());
		ResetPasswordManager.writeThisTextToPasswordField(Constants.FORGOT_PASSWORD_PASSWORD);
		ResetPasswordManager.writeThisTextToPasswordAgainField(Constants.FORGOT_PASSWORD_PASSWORD + "wrong");
		ResetPasswordManager.clickOnSendButton();

		// Validation
		assertTrue("There should be a message box present with different password message.",
				Utils.isMessageBoxPresentWithText(ResetPasswordMessageTypes.INCORRECT_FIELD));
	}

}
