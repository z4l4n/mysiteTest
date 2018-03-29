package com.frontendart.testsuites.login;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.frontendart.categories.AUTHOR_Suite;
import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.CoreSuite;
import com.frontendart.categories.INSTITUTIONAL_ADMIN_Suite;
import com.frontendart.categories.LoginLogoutSuite;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.JunitTestClass;
import com.frontendart.locators.login.LoginPageLocators;
import com.frontendart.locators.main.MainPageLocators;
import com.frontendart.managers.login.LoginManager;
import com.frontendart.managers.login.LogoutManager;

/**
 * Test class for logout tests.
 *
 * @author Zoli
 *
 */
@Category({ LoginLogoutSuite.class, AUTHOR_Suite.class, INSTITUTIONAL_ADMIN_Suite.class, CENTRAL_ADMIN_Suite.class })
@SuppressWarnings({ "PMD.JUnitTestsShouldIncludeAssert", "PMD.JUnitTestContainsTooManyAsserts" })
public class LogoutTest extends JunitTestClass {

    /**
     * Logger
     */
    private static final Logger LOGGER = LogManager.getLogger("LogoutTest");

    /**
     * Simple logout with default values
     *
     * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1337">#1337</a>
     */
    @Test
    @Category(CoreSuite.class)
    public final void testLogoutWithConfirm() {
        Utils.writeMyRedmineIssues("#1337");

        // Click on Logout button
        LogoutManager.logout();
        Utils.defaultWait();

        // Validation
        //Utils.waitForElementPresent(LoginPageLocators.LOGIN_TABPANEL);
        Utils.myAssertTrue("Látszódnia kell a Belépés/Login gombnak.", Utils.isThisElementVisible(LoginPageLocators.LOGIN_BUTTON));
        LoginManager.loginSuccessfullyWithThisRole(Utils.getActualRole());
    }

    /**
     * Simple test logout button
     */
    @Test
    @Category(CoreSuite.class)
    public final void testLogoutWithCancel() {
        // Test logout cancel button
        Utils.waitForAndClickOnGeneralWebElement(MainPageLocators.LOGOUT_BUTTON);
        Utils.defaultWait();
        LOGGER.info("Kattintsunk a megjelenő figyelmeztető üzeneten a Mégsem/Cancel gombra.");
        Utils.cancelMessageBoxIfVisible();

        // Validation
        Utils.myAssertTrue("Látszódnia kell a Kijelentkezés/Logout gombnak.", Utils.isThisElementVisible(MainPageLocators.LOGOUT_BUTTON));
    }

}
