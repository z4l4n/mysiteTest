package com.frontendart.testsuites.login;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.frontendart.categories.AUTHOR_Suite;
import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.CoreSuite;
import com.frontendart.categories.INSTITUTIONAL_ADMIN_Suite;
import com.frontendart.categories.LoginLogoutSuite;
import com.frontendart.common.Constants;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.DontLoginJunitTestClass;
import com.frontendart.locators.login.ForgotPasswordMessageTypes;
import com.frontendart.managers.login.ForgotPasswordManager;

/**
 * Test class for forgot password tests.
 *
 * @author Zoli
 *
 */
@Category({ LoginLogoutSuite.class, AUTHOR_Suite.class, INSTITUTIONAL_ADMIN_Suite.class, CENTRAL_ADMIN_Suite.class })
@SuppressWarnings({ "PMD.JUnitTestsShouldIncludeAssert", "PMD.JUnitTestContainsTooManyAsserts" })
public class ForgotPasswordTest extends DontLoginJunitTestClass {

    /**
     * Simple forgot password test Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1194">#1194</a> Redmine
     * issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1507">#1507</a>
     */
    @Test
    @Category(CoreSuite.class)
    public final void testSimpleForgotPasswordTestPOZ() {
        Utils.writeMyRedmineIssues("#1194#1507");

        // Fill the fields
        ForgotPasswordManager.performForgotPasswordProcessWithThisUsername(Utils.getActualRole().getUsername());

        // Validate
        switch (Utils.getActualRole()) {
            case CENTRAL_ADMIN:
                assertTrue("There should be a message box present with an error message.",
                        Utils.isMessageBoxPresentWithText(ForgotPasswordMessageTypes.ADMIN));
                break;
            case INSTITUTIONAL_ADMIN:
                assertTrue("There should be a message box present with an error message.",
                        Utils.isMessageBoxPresentWithText(ForgotPasswordMessageTypes.ADMIN));
                break;
            case AUTHOR:
                assertTrue("There should be a message box present with success message.",
                        Utils.isMessageBoxPresentWithText(ForgotPasswordMessageTypes.SUCCESS));
                break;
            default:
                break;
        }

    }

    /**
     * Simple forgot password test - with empty Captcha
     */
    @Test
    @Category(CoreSuite.class)
    public final void testEmptyCaptchaWithCorrectUserame() {
        Utils.writeMyRedmineIssues("#1605");

        // Empty captcha
        ForgotPasswordManager.navigateToForgotPasswordHeader();
        ForgotPasswordManager.writeThisTextToUsernameField(Utils.getActualRole().getUsername());
        ForgotPasswordManager.clickOnSendButton();

        // Validation
        assertTrue("There should be a message box present with empty captcha error message.",
                Utils.isMessageBoxPresentWithText(ForgotPasswordMessageTypes.WRONG_CAPTCHA));
    }

    /**
     * Simple forgot password test - with wrong Captcha
     */
    @Test
    @Category(CoreSuite.class)
    public final void testWrongCaptchaWithCorrectUsername() {
        Utils.writeMyRedmineIssues("#1605");

        // Wrong captcha
        ForgotPasswordManager.navigateToForgotPasswordHeader();
        ForgotPasswordManager.writeThisTextToUsernameField(Utils.getActualRole().getUsername());
        ForgotPasswordManager.writeThisTextToCaptchaField(Utils.randomString());
        ForgotPasswordManager.clickOnSendButton();

        // Validation
        assertTrue("There should be a message box present with wrong captcha error message.",
                Utils.isMessageBoxPresentWithText(ForgotPasswordMessageTypes.WRONG_CAPTCHA));
    }

    @Test
    @Category(CoreSuite.class)
    public final void testEmptyUsernameWithCorrectCaptcha() {

        ForgotPasswordManager.navigateToForgotPasswordHeader();
        ForgotPasswordManager.writeThisTextToCaptchaField(Constants.CAPTCHA_MAGIC_STRING);
        ForgotPasswordManager.clickOnSendButton();

        assertTrue("There should be a message box present with wrong captcha error message.",
                Utils.isMessageBoxPresentWithText(ForgotPasswordMessageTypes.INVALID_USERNAME));
    }

    @Test
    @Category(CoreSuite.class)
    public final void testWrongUsernameWithCorrectCaptcha() {

        ForgotPasswordManager.navigateToForgotPasswordHeader();
        ForgotPasswordManager.writeThisTextToUsernameField(Utils.randomString());
        ForgotPasswordManager.writeThisTextToCaptchaField(Constants.CAPTCHA_MAGIC_STRING);
        ForgotPasswordManager.clickOnSendButton();

        assertTrue("There should be a message box present with wrong captcha error message.",
                Utils.isMessageBoxPresentWithText(ForgotPasswordMessageTypes.INVALID_USERNAME));
    }

    @Test
    @Category(CoreSuite.class)
    public final void testEmptyUsernameAndCaptcha() {

        ForgotPasswordManager.navigateToForgotPasswordHeader();
        ForgotPasswordManager.clickOnSendButton();

        assertTrue("There should be a message box present with wrong captcha error message.",
                Utils.isMessageBoxPresentWithText(ForgotPasswordMessageTypes.WRONG_CAPTCHA));
    }
}
