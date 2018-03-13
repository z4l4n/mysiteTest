package com.frontendart.testsuites.login;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.CoreSuite;
import com.frontendart.categories.LoginLogoutSuite;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.DontLoginJunitTestClass;
import com.frontendart.locators.login.ForgotPasswordLocators;
import com.frontendart.locators.login.LoginMessageTypes;
import com.frontendart.managers.login.LoginManager;
import com.frontendart.managers.login.LogoutManager;

/**
 * Test class for login tests.
 * @author Zoli
 *
 */
@Category({ LoginLogoutSuite.class, CENTRAL_ADMIN_Suite.class })
@SuppressWarnings({ "PMD.JUnitTestsShouldIncludeAssert", "PMD.JUnitTestContainsTooManyAsserts" })
public class LoginTest extends DontLoginJunitTestClass {

	// This test class should only run with admin (mtmtuser4) rights - meaning that it will only run once
	@BeforeClass
	public static void beforeMethod() {
		org.junit.Assume.assumeTrue(Utils.actualRoleIsCentralAdmin());
	}

	/**
	 * Simple login with default values
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/897">#897</a>
	 */
	// TODO: fix
	@Test
	@Category(CoreSuite.class)
	public final void testLoginPOZ() {
		Utils.writeMyRedmineIssues("#897");

		// Login with user, and check page
		LoginManager.doSuccessfulLoginWithRole(Utils.getActualRole());

		// Logout (cleanup)
		LogoutManager.logout();
	}

	/**
	 * Login 2 times with wrong values, then login with right values
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/962">#962</a>
	 */
	// TODO: fix
	@Test
	@Category(CoreSuite.class)
	public final void testLoginLockAccount() {
		Utils.writeMyRedmineIssues("#962");

		// Generate random username and password
		final String myUserName = Utils.randomString();
		final String myPassword = Utils.randomString();

		// Login 2 times with wrong password
		LoginManager.loginWithThisUsernameAndPassword(Arrays.asList(myUserName, myPassword));
		assertTrue("There should be a message box present with incorrect field error message.",
				Utils.isMessageBoxPresentWithText(LoginMessageTypes.INCORRECT_FIELD));

		// 2nd time
		LoginManager.loginWithThisUsernameAndPassword(Arrays.asList(myUserName, myPassword));
		Utils.waitMillisec(500);
		assertTrue("There should be a message box present with incorrect field error message.",
				Utils.isMessageBoxPresentWithText(LoginMessageTypes.INCORRECT_FIELD));

		// Try to log in for the 3rd time
		LoginManager.loginWithThisUsernameAndPassword(Arrays.asList(myUserName, myPassword));
		Utils.waitMillisec(500);

		// Validation
		assertTrue("There should be a message box present with try again error message.",
				Utils.isMessageBoxPresentWithText(LoginMessageTypes.TRY_AGAIN_LATER));
	}

	/**
	 * Wrong username test
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1384">#1384</a>
	 */
	@Test
	@Category(CoreSuite.class)
	public final void testLoginWithWrongUsername() {
		Utils.writeMyRedmineIssues("#1384");

		// Login with invalid creditentials - wrong username, valid password
		LoginManager.loginWithThisUsernameAndPassword(Arrays.asList(Utils.randomString(), Utils.getActualRole().getPassword()));

		// Validation
		assertFalse("There is an unexpected alert on Login page!", Utils.isAlertPresent());
		assertTrue("There should be a message box present with incorrect field error message.",
				Utils.isMessageBoxPresentWithText(LoginMessageTypes.INCORRECT_FIELD));
	}

	/**
	 * Login with wrong password
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1384">#1384</a>
	 */
	@Test
	@Category(CoreSuite.class)
	public final void testLoginWithWrongPassword() {
		Utils.writeMyRedmineIssues("#1384");

		// Login with valid username, invalid password
		LoginManager.loginWithThisUsernameAndPassword(Arrays.asList(Utils.getActualRole().getUsername(), Utils.randomString()));

		// Validation
		assertFalse("There is an unexpected alert on Login page!", Utils.isAlertPresent());
		assertTrue("There should be a message box present!",
				Utils.isMessageBoxPresentWithText(LoginMessageTypes.INCORRECT_FIELD));
	}

	/**
	 * Login with empty username and password
	 */
	@Test
	@Category(CoreSuite.class)
	public final void testLoginWithEmptyFields() {
		LoginManager.loginWithThisUsernameAndPassword(Arrays.asList("", ""));
		// Validation
		assertTrue("There should be a message box present with empty field error message.",
				Utils.isMessageBoxPresentWithText(LoginMessageTypes.EMPTY_FIELD));
	}

	/**
	 * Simple login without username
	 */
	@Test
	@Category(CoreSuite.class)
	public final void testLoginWithEmptyUsername() {
		LoginManager.loginWithThisUsernameAndPassword(Arrays.asList("", Utils.randomString()));
		// Validation
		assertTrue("There should be a message box present with empty field error message.",
				Utils.isMessageBoxPresentWithText(LoginMessageTypes.EMPTY_FIELD));
	}

	/**
	 * Simple login with empty password
	 */
	@Test
	@Category(CoreSuite.class)
	public final void testLoginWithEmptyPassword() {
		LoginManager.loginWithThisUsernameAndPassword(Arrays.asList(Utils.getActualRole().getUsername(), ""));

		// Validation
//		assertTrue("Username textfield is not visible.", Utils.isThisElementVisible(ForgotPasswordLocators.FORGOT_PASSWORD_USERNAME_FIELD));
//		assertTrue("Forgot password button is not visible.", Utils.isThisElementVisible(ForgotPasswordLocators.FORGOT_PASSWORD_BUTTON));
//		assertTrue("Reset captcha button is not visible.", Utils.isThisElementVisible(ForgotPasswordLocators.RESET_CAPTCHA_BUTTON));
		assertTrue("There should be a message box present with empty field error message.",
				Utils.isMessageBoxPresentWithText(LoginMessageTypes.EMPTY_FIELD));

	}

}
