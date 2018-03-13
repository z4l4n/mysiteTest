package com.frontendart.junitclasses;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

import com.frontendart.common.TestConfiguration;
import com.frontendart.common.Utils;
import com.frontendart.locators.login.LoginPageLocators;

/**
 * Junit test class for tests that don't need login
 *
 * @author Zoli
 *
 */
public class DontLoginJunitTestClass extends JunitTestClass {
    /**
     * Logger
     */
    private static final Logger LOGGER = LogManager.getLogger("DontLoginJunitTestClass");

    @Rule
    public TestName testName = new TestName();

    /**
     * Setup
     *
     * @author gyizol
     */
    @Override
    @Before
    public void setUp() {

        //Utils.switchTab();

        LOGGER.info("Teszteset azonosítója: " + testName.getMethodName());
        driver.get(TestConfiguration.gui2AuthenticatedServerUrl);
        driver.get(TestConfiguration.myCiteServerUrl);
        LOGGER.info("Előfeltétel: Megjelenik a MyCite2 nyitófelülete ezen az URL-en: " + TestConfiguration.myCiteServerUrl);

        Utils.waitForElementPresent(LoginPageLocators.LOGIN_TABPANEL);
    }

    /**
     * Teardown
     *
     * @author gyizol
     */
    @Override
    @After
    public void tearDown() {
        // Close driver
        Utils.closeDriver();

        LOGGER.info("Teszteset vége.\n");
    }
}
