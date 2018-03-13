package com.frontendart.managers.main.rightpanel.top;

import com.frontendart.common.Utils;
import com.frontendart.locators.main.MainPageLocators;
import com.frontendart.locators.main.rightpanel.top.ChangeUserLocators;

/**
 * class for change user manager
 * 
 * @author Zoli
 *
 */
public class ChangeUserManager {

	/**
	 * Opens about window
	 * 
	 */
	public static void openChangeUserWindow() {
		// Click on change user button
		Utils.waitForAndClickOnGeneralWebElement(MainPageLocators.MY_PROFILE_BUTTON);
		Utils.waitUntilUsernameTooltipHide();
		Utils.waitForAndClickOnGeneralWebElement(ChangeUserLocators.CHANGE);
	}

	/**
	 * Change back
	 */
	public static void changeBackUser() {
		// Click on change user button
		Utils.waitForAndClickOnGeneralWebElement(MainPageLocators.MY_PROFILE_BUTTON);
		Utils.waitUntilUsernameTooltipHide();
		Utils.waitForAndClickOnGeneralWebElement(ChangeUserLocators.CHANGE_BACK);
	}

}
