package com.frontendart.junitclasses.main.rightpanel.top;

import org.junit.Before;

import com.frontendart.common.Utils;
import com.frontendart.junitclasses.JunitTestClass;
import com.frontendart.locators.general.GeneralPageLocators;
import com.frontendart.locators.main.MainPageLocators;

/**
 * junit class for messages tests
 * @author Zoli
 *
 */
public class MessagesJunitTestClass extends JunitTestClass {

	/**
	 * Setup 
	 */
	@Override
	@Before
	public void setUp() {
		super.setUp();
		Utils.waitForAndClickOnGeneralWebElement(MainPageLocators.MAIL_MESSAGES_BUTTON);
		Utils.waitMillisec(10000);
		// Click on table view
		Utils.waitForAndClickOnGeneralWebElement(GeneralPageLocators.MESSAGE_CENTER_WINDOW_TABLE_VIEW);
		Utils.defaultWait();
	}
}
