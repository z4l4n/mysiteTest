package com.frontendart.managers.login;

import com.frontendart.common.Constants;
import com.frontendart.common.Utils;
import com.frontendart.locators.login.ForgotPasswordLocators;

/**
 * Manager class for forgot password tests.
 * @author Zoli
 *
 */
public class ForgotPasswordManager {

	/**
	 * Clicks on forgot password header
	 */
	public static void navigateToForgotPasswordHeader() {
		Utils.waitForAndClickOnGeneralWebElement(ForgotPasswordLocators.FORGOT_PASSWORD_TABPANEL_HEADER);
	}

	/**
	 * Writes text to forgot password username field
	 */
	public static void writeThisTextToUsernameField(final String text) {
		Utils.writeTextToThisField(text, ForgotPasswordLocators.FORGOT_PASSWORD_USERNAME_FIELD);
	}

	/**
	 * Writes text to forgot password captcha field
	 */
	public static void writeThisTextToCaptchaField(final String text) {
		Utils.writeTextToThisField(text, ForgotPasswordLocators.FORGOT_PASSWORD_CAPTCHA_FIELD);
	}

	/**
	 * Clicks on send button
	 */
	public static void clickOnSendButton() {
		Utils.waitForAndClickOnGeneralWebElement(ForgotPasswordLocators.FORGOT_PASSWORD_BUTTON);
	}

	/**
	 * Whole process with username
	 * @param username
	 */
	public static void performForgotPasswordProcessWithThisUsername(final String username) {
		navigateToForgotPasswordHeader();
		writeThisTextToUsernameField(username);
		writeThisTextToCaptchaField(Constants.CAPTCHA_MAGIC_STRING);
		clickOnSendButton();
	}
}
