package com.frontendart.managers.main.rightpanel.top;

import com.frontendart.common.Utils;
import com.frontendart.locators.main.MainPageLocators;

/**
 * class for about window manager
 * 
 * @author Zoli
 *
 */
public class AboutManager {

	/**
	 * Opens about window
	 * 
	 */
	public static void openAboutWindow() {
		Utils.waitForAndClickOnGeneralWebElement(MainPageLocators.ABOUT_BUTTON);
	}

}
