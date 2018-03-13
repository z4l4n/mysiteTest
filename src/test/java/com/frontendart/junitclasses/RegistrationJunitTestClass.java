package com.frontendart.junitclasses;

import org.junit.Before;

import com.frontendart.common.Utils;
import com.frontendart.locators.registration.RegistrationLocators;

/**
 * Registration Junit test class.
 * @author Zoli
 *
 */
public class RegistrationJunitTestClass extends DontLoginJunitTestClass {

	/**
	 * Setup 
	 */
	@Override
	@Before
	public void setUp() {
		super.setUp();

		// Clicks on Registration tab header
		Utils.waitForAndClickOnGeneralWebElement(RegistrationLocators.REGISTRATION_TABPANEL_HEADER);
	}
}
