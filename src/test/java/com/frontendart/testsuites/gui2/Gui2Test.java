package com.frontendart.testsuites.gui2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.CoreSuite;
import com.frontendart.categories.Gui2Suite;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.Gui2JunitTestClass;
import com.frontendart.locators.gui2.HomePageLocators;
import com.frontendart.locators.gui2.LeftPanelLocators;
import com.frontendart.managers.gui2.Gui2GeneralManager;

/**
 * Test class for logout tests.
 * @author Zoli
 *
 */
//@Ignore
@Category({ Gui2Suite.class, CENTRAL_ADMIN_Suite.class })
@SuppressWarnings({ "PMD.JUnitTestsShouldIncludeAssert", "PMD.JUnitTestContainsTooManyAsserts" })
public class Gui2Test extends Gui2JunitTestClass {

	//This test class should only run with author (mtmtuser3) rights - meaning that it will only run once
	@BeforeClass
	public static void beforeMethod() {
		org.junit.Assume.assumeTrue(Utils.actualRoleIsCentralAdmin());
	}

	/**
	 * Simple page load test
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1364">#1364</a>
	 */
	@Test
	@Category(CoreSuite.class)
	public final void testBasicGui2Test() {
		Gui2GeneralManager.checkGui2Page(HomePageLocators.class);
	}

	/**
	 * Simple left panel label test
	 */
	@Test
	@Category(CoreSuite.class)
	public final void testCheckHideLeftPanel() {
		// Click and check not visible
		Utils.waitForAndClickOnGeneralWebElement(HomePageLocators.NAVBAR_TOGGLE);
		assertFalse("Left panel should not be visible!",
				Utils.createGeneralWebElementFromEnum(LeftPanelLocators.LEFT_COLUMN).getAttribute("class").contains("opened"));

		// Click and check visible
		Utils.defaultWait();
		Utils.waitForAndClickOnGeneralWebElement(HomePageLocators.NAVBAR_TOGGLE);
		assertTrue("Left panel should be visible!",
				Utils.createGeneralWebElementFromEnum(LeftPanelLocators.LEFT_COLUMN).getAttribute("class").contains("opened"));
	}

}
