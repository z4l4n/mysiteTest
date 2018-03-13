package com.frontendart.junitclasses.main.rightpanel.top;

import org.junit.Before;

import com.frontendart.common.Utils;
import com.frontendart.junitclasses.JunitTestClass;
import com.frontendart.locators.main.MainPageLocators;

/**
 * junit class for system messages tests
 * @author Zoli
 *
 */
public class SystemMessagesJunitTestClass extends JunitTestClass {

	/**
	 * Setup 
	 */
	@Override
	@Before
	public void setUp() {
		super.setUp();
		Utils.waitForAndClickOnGeneralWebElement(MainPageLocators.SYSTEM_MESSAGES_BUTTON);
		Utils.defaultWait();
	}
}
