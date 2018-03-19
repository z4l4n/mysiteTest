package com.frontendart.testsuites.main.rightpanel.top;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.frontendart.categories.AUTHOR_Suite;
import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.CoreSuite;
import com.frontendart.categories.INSTITUTIONAL_ADMIN_Suite;
import com.frontendart.categories.RightPanelTopSuite;
import com.frontendart.common.Roles;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.JunitTestClass;
import com.frontendart.locators.main.MainPageLocators;
import com.frontendart.locators.main.rightpanel.top.ChangeUserLocators;
import com.frontendart.managers.main.leftpanel.RecordSelectionManager;
import com.frontendart.managers.main.rightpanel.top.ChangeUserManager;

/**
 * Class for about test
 *
 * @author Zoli
 *
 */
@Category({ RightPanelTopSuite.class, CENTRAL_ADMIN_Suite.class, INSTITUTIONAL_ADMIN_Suite.class, AUTHOR_Suite.class })
public class ChangeUserTest extends JunitTestClass {

    /**
     * Check basic user change
     */
    @Test
    @Category(CoreSuite.class)
    // In the system mtmtuser3 should be named as: "MTMT2 User3" (Family Name /
    // Given name)
    public final void testBasicChangeUser() {
        if (Utils.getActualRole().equals(Roles.AUTHOR)) {
            Utils.waitForAndClickOnGeneralWebElement(MainPageLocators.MY_PROFILE_BUTTON);
            assertFalse("There should be no opportunity to login as other user!",
                    Utils.isThisElementVisible(ChangeUserLocators.CHANGE));
        } else {
            // Click on change user window
            ChangeUserManager.openChangeUserWindow();
            Utils.defaultWait();

            // Select mtmtuser3 from selector
            if (Utils.getActualRole().equals(Roles.CENTRAL_ADMIN)) {
                Utils.writeTextToThisField("User Teszt", ChangeUserLocators.LOGIN_AS_WINDOW_FILTER_FIELD);
                Utils.waitForAndClickOnGeneralWebElement(ChangeUserLocators.LOGIN_AS_WINDOW_FILTER_BUTTON);
            } else {
                Utils.writeTextToThisField("Author Bela (aux)", ChangeUserLocators.LOGIN_AS_WINDOW_FILTER_FIELD);
                Utils.waitForAndClickOnGeneralWebElement(ChangeUserLocators.LOGIN_AS_WINDOW_FILTER_BUTTON);
            }
            Utils.defaultWait();

            //If no results, then skip the testcase
            List<WebElement> noResults = driver
                    .findElements(By.xpath(ChangeUserLocators.LOGIN_AS_WINDOW_NO_RESULTS.toString()));

            if (noResults != null && !noResults.isEmpty()) {
                Utils.waitForAndClickOnGeneralWebElement(ChangeUserLocators.LOGIN_AS_WINDOW_CLOSE);
            }

            org.junit.Assume.assumeTrue(noResults.isEmpty());

            Utils.waitForAndClickOnGeneralWebElement(ChangeUserLocators.LOGIN_AS_WINDOW_FIRST_MATCH);
            Utils.waitForAndClickOnGeneralWebElement(ChangeUserLocators.LOGIN_AS_WINDOW_LOGIN);

            // Validate
            RecordSelectionManager.waitUntilTableIsReady();
            String usernameText = Utils.createGeneralWebElementFromEnum(MainPageLocators.MY_PROFILE_BUTTON)
                    .findElement(By.xpath(".//span[contains(@class, 'x-btn-button')]")).getText();
            if (Utils.getActualRole().equals(Roles.CENTRAL_ADMIN)) {
                assertEquals("Username is not correct after login as user!", "user", usernameText);
            } else {
                assertEquals("Username is not correct after login as user!", "author", usernameText);
            }

            // change back
            ChangeUserManager.changeBackUser();
            Utils.waitMillisec(10000);
            usernameText = Utils.createGeneralWebElementFromEnum(MainPageLocators.MY_PROFILE_BUTTON)
                    .findElement(By.xpath(".//span[contains(@class, 'x-btn-button')]")).getText();
            assertEquals("Username is not correct after switching back!", Utils.getActualRole().getUsername(),
                    usernameText);
        }
    }
}
