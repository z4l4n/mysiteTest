package com.frontendart.managers.login;

import com.frontendart.common.Utils;
import com.frontendart.locators.login.LoginPageLocators;
import com.frontendart.locators.main.MainPageLocators;

/**
 * Manager class for logout tests.
 * @author Zoli
 *
 */
public class LogoutManager {

	/**
	 * simple logout
	 */
	public static void logout() {
		if (Utils.isThisElementPresent(MainPageLocators.LOGOUT_BUTTON)) {
			Utils.waitForAndClickOnGeneralWebElement(MainPageLocators.LOGOUT_BUTTON);
			Utils.waitForMessageBoxPresentAndAccept();
			Utils.waitForElementPresent(LoginPageLocators.LOGIN_BUTTON);
		}
	}
}
