package com.frontendart.testsuites.main.rightpanel.top;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebElement;

import com.frontendart.categories.AUTHOR_Suite;
import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.CoreSuite;
import com.frontendart.categories.INSTITUTIONAL_ADMIN_Suite;
import com.frontendart.categories.RightPanelTopSuite;
import com.frontendart.common.Roles;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.JunitTestClass;
import com.frontendart.locators.main.rightpanel.crud.RecordEditorLocators;
import com.frontendart.locators.main.rightpanel.top.ChangePasswordLocators;
import com.frontendart.locators.main.rightpanel.top.UserProfileTableAttributes;
import com.frontendart.locators.records.attributes.general.GeneralTableAttributes;
import com.frontendart.managers.general.GeneralDataProvider;
import com.frontendart.managers.login.LoginManager;
import com.frontendart.managers.login.LogoutManager;
import com.frontendart.managers.main.rightpanel.crud.CreateRecordManager;
import com.frontendart.managers.main.rightpanel.top.UserDataManager;

/**
 * Class for user data test
 *
 * @author Zoli
 *
 */
@Category({ RightPanelTopSuite.class, AUTHOR_Suite.class, INSTITUTIONAL_ADMIN_Suite.class, CENTRAL_ADMIN_Suite.class })
public class UserProfileTest extends JunitTestClass {

    /**
     * Select my profile Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1137">#1137</a> Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1138">#1138</a>
     */
    @Test
    @Category(CoreSuite.class)
    public final void testMyProfileWindowIsPresent() throws InterruptedException {
        Utils.writeMyRedmineIssues("#1137#1138");

        // Click on my username button
        UserDataManager.openProfileEditor();
        Utils.defaultWait();

        assertTrue("The profile window is not visible.", Utils.isThisElementVisible(RecordEditorLocators.USER_EDITOR_WINDOW));

        RecordEditorLocators.checkAttributesAreVisible(UserProfileTableAttributes.class);

        // Close
        Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.USER_CANCEL_BUTTON);
    }

    /**
     * Edit my profile Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1126">#1126</a> Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1200">#1200</a> Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1358">#1358</a> Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1427">#1427</a>
     */

    @Test
    @Category(CoreSuite.class)
    public void testChangingEmailAtUserProfileEditor() {
        Utils.writeMyRedmineIssues("1126");
        UserDataManager.openProfileEditor();
        Utils.defaultWait();
        UserDataManager.performEditAndCheckField(UserProfileTableAttributes.EMAIL);

    }

    @Test
    @Category(CoreSuite.class)
    public void testLoggingInAfterChangingDisambiguatingName() {
        Utils.writeMyRedmineIssues("#1200");
        UserDataManager.openProfileEditor();

        WebElement field = CreateRecordManager.findFieldOfThisAttribute(UserProfileTableAttributes.AUX_NAME);
        final String myOldValue = GeneralDataProvider.getCurrentValueOfThisField(field, UserProfileTableAttributes.AUX_NAME);

        // Write new value, and save
        field = CreateRecordManager.findFieldOfThisAttribute(UserProfileTableAttributes.AUX_NAME);
        final String randomNewValue = GeneralDataProvider.setRandomValueToThisAttribute(field, UserProfileTableAttributes.AUX_NAME);

        Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.USER_SAVE_AND_CLOSE);
        Utils.defaultWait();

        // This is needed for emtpy fields question
        Utils.acceptMessageBoxIfVisible();

        final boolean isErrorWindowPresent = Utils.isThisElementPresent(ChangePasswordLocators.ERROR_WINDOW);
        if (isErrorWindowPresent) {
            Utils.acceptMessageBoxIfVisible();
            Utils.waitForAndClickOnGeneralWebElement(ChangePasswordLocators.ERROR_WINDOW_REFRESH_BUTTON);
            Utils.defaultWait();
            fail("There is an unexpected error window present after trying to edit user profile!");
        }

        // check that there is no error window present
        Utils.myAssertFalse("Nem szabad hibaüzenetnek megjelennie.", Utils.acceptMessageBoxIfVisible());
        Utils.defaultWait();

        // refresh
        UserDataManager.openProfileEditor();

        // Check value
        field = CreateRecordManager.findFieldOfThisAttribute(UserProfileTableAttributes.AUX_NAME);
        final String myNewValue = GeneralDataProvider.getCurrentValueOfThisField(field, UserProfileTableAttributes.AUX_NAME);

        Utils.myAssertFalse("Az új értéknek nem szabad megegyeznie a régivel.", myOldValue.equals(myNewValue));
        Utils.myAssertTrue("Az új értékenk a következőnek kell lennie: " + randomNewValue, myNewValue.equals(randomNewValue));

        final Map<GeneralTableAttributes, String> attributeValues = new HashMap<>();
        attributeValues.put(UserProfileTableAttributes.AUX_NAME, "");
        GeneralDataProvider.setThisValueToAttribute(field, attributeValues);
        Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.USER_SAVE_AND_CLOSE);
        Utils.defaultWait();

        Utils.acceptMessageBoxIfVisible();

        LogoutManager.logout();
        Utils.defaultWait();
        LoginManager.loginSuccessfullyWithThisRole(Utils.getActualRole());

        // refresh and validate
        UserDataManager.openProfileEditor();
        Utils.defaultWait();
        field = CreateRecordManager.findFieldOfThisAttribute(UserProfileTableAttributes.AUX_NAME);
        final String myFinalValue = GeneralDataProvider.getCurrentValueOfThisField(field, UserProfileTableAttributes.AUX_NAME);

        Utils.myAssertTrue("Az új értéknek a régivel kell megegyeznie.", myFinalValue.equals(""));

        final Map<GeneralTableAttributes, String> finalValues = new HashMap<>();
        finalValues.put(UserProfileTableAttributes.AUX_NAME, myOldValue);
        GeneralDataProvider.setThisValueToAttribute(field, finalValues);
        Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.USER_SAVE_AND_CLOSE);
        Utils.defaultWait();

        // last validation
        UserDataManager.openProfileEditor();
        field = CreateRecordManager.findFieldOfThisAttribute(UserProfileTableAttributes.AUX_NAME);
        final String myFinalValue2 = GeneralDataProvider.getCurrentValueOfThisField(field, UserProfileTableAttributes.AUX_NAME);
        Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.USER_CANCEL_BUTTON);
        Utils.myAssertTrue("Az új értéknek a régivel kell megegyeznie.", myOldValue.equals(myFinalValue2));

    }

    @Ignore
    @Test
    public final void testEditMyProfile() throws InterruptedException {
        Utils.writeMyRedmineIssues("#1126#1200#1358#1427");

        // Click on my username button
        UserDataManager.openProfileEditor();

        if (Utils.getActualRole().equals(Roles.AUTHOR)) {
            UserDataManager.fillRequiredDatasWhenRoleIsAuthor();
        }

        // Edit
        assertTrue("Error while editing user profile!", UserDataManager.editRandomField());
    }

    // FURTHER TESTS TO BE IMPLEMENTED

    /**
     * Test left panel slider
     */
}
