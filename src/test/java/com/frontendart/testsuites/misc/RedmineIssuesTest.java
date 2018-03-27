package com.frontendart.testsuites.misc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.CoreSuite;
import com.frontendart.categories.MiscSuite;
import com.frontendart.common.Constants;
import com.frontendart.common.Roles;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.JunitTestClass;
import com.frontendart.locators.general.GeneralPageLocators;
import com.frontendart.locators.main.MainPageLocators;
import com.frontendart.locators.main.rightpanel.crud.RecordEditorLocators;
import com.frontendart.locators.main.rightpanel.top.ChangePasswordLocators;
import com.frontendart.locators.main.rightpanel.top.TicketTableAttributes;
import com.frontendart.locators.main.rightpanel.view.ToolbarLocators;
import com.frontendart.locators.records.attributes.general.GeneralRecordTypes;
import com.frontendart.locators.records.attributes.general.PublicationRecordAttributes;
import com.frontendart.managers.login.LoginManager;
import com.frontendart.managers.login.LogoutManager;
import com.frontendart.managers.main.leftpanel.RecordSelectionManager;
import com.frontendart.managers.main.leftpanel.SearchManager;
import com.frontendart.managers.main.rightpanel.top.TopGeneralManager;
import com.frontendart.managers.main.rightpanel.view.ChangeViewManager;
import com.frontendart.managers.main.rightpanel.view.FilterManager;

/**
 * Test class for help tests.
 *
 * @author Zoli
 *
 */
@Category({ MiscSuite.class, CENTRAL_ADMIN_Suite.class })
@SuppressWarnings({ "PMD.JUnitTestsShouldIncludeAssert", "PMD.JUnitTestContainsTooManyAsserts" })

public class RedmineIssuesTest extends JunitTestClass {

    // This test class should only run with central admin rights
    @BeforeClass
    public static void beforeMethod() {
        org.junit.Assume.assumeTrue(Utils.actualRoleIsCentralAdmin());
    }

    /**
     * Create ticket for Admin record type Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/2915">#2915</a>
     */
    @Test
    @Ignore //DB-be nem írunk
    public final void testCreateTicketForAdminRecordType() {
        Utils.writeMyRedmineIssues("#2915");

        RecordSelectionManager.selectThisRecordTypeFromSelector(GeneralRecordTypes.ADMIN);
        Utils.defaultWait();
        SearchManager.createAndRunEmptyQuery();
        Utils.defaultWait();
        ChangeViewManager.switchToGridView();
        Utils.defaultWait();
        FilterManager.performFilterWithText("Bálint Ágnes");
        Utils.defaultWait();
        RecordSelectionManager.selectRandomRenderedRowsFromGridPanel(1);
        Utils.defaultWait();
        Utils.waitForAndClickOnGeneralWebElement(ToolbarLocators.NEW_TICKET_BUTTON);

        TopGeneralManager.addElement(TicketTableAttributes.class);
        Utils.defaultWait();
        TopGeneralManager.closeWindow();
        //TopGeneralManager.checkGeneralElementDeletion(TicketTableAttributes.class);
    }

    /**
     * Further tests to implement:
     *
     * Duplicate - check admin comment field is empty Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/2899">#2899</a>
     */

    /**
     * Check publications attribute order Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/2957">#2957</a>
     */
    @Test
    @Category(CoreSuite.class)
    public final void testPublicationsAttributeOrder() {
        Utils.writeMyRedmineIssues("#2957");

        SearchManager.createAndRunEmptyQuery();
        // Click on New button
        Utils.waitForAndClickOnGeneralWebElement(ToolbarLocators.NEW_BUTTON);

        // Click on Next button
        Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.NEXT_BUTTON);

        // Get all visible labels
        final List<WebElement> allLabels = Utils
                .createGeneralWebElementsFromEnum(RecordEditorLocators.EDITOR_FORM_FIELDS_VISIBLE_LABELS_ALTERNATIVE);
        final List<String> allLabelsAsString = Utils.convertThisWebElementArrayToStringArray(allLabels);

        // Get indexes of Type, SubType, Category
        // Language check (If English is the active, we should check for the English name of the attribute)
        int nameIndex = 0;
        final String language = Utils.createGeneralWebElementFromEnum(MainPageLocators.CHANGE_LANGUAGE_BUTTON).getText();
        if ("English".equals(language)) {
            nameIndex = 1;
        }
        final int indexOfType = allLabelsAsString.indexOf(PublicationRecordAttributes.TYPE.getNames().get(nameIndex).concat(":*"));
        final int indexOfSubType = allLabelsAsString.indexOf(PublicationRecordAttributes.SUBTYPE.getNames().get(nameIndex).concat(":"));
        final int indexOfCategory = allLabelsAsString.indexOf(PublicationRecordAttributes.CATEGORY.getNames().get(nameIndex).concat(":*"));

        // Validate
        assertEquals("Subtype attribute is not after Type attribute!", indexOfSubType, indexOfType + 1);
        assertEquals("Category attribute is not after Subtype attribute!", indexOfCategory, indexOfSubType + 1);

        // Close editor
        Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.CANCEL_BUTTON);
    }

    /**
     * Change password for user
     *
     * This test needs a "gyizol" user to be present.
     *
     * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/2593">#2593</a>
     */
    @Test
    public final void testChangePasswordForAdmin() {
        Utils.writeMyRedmineIssues("#2593");

        // Click on change password button
        Utils.waitForAndClickOnGeneralWebElement(MainPageLocators.MY_PROFILE_BUTTON);
        //Utils.defaultWait();
        Utils.waitUntilUsernameTooltipHide();
        Utils.waitForAndClickOnGeneralWebElement(MainPageLocators.MY_PROFILE_PASSWORD_CHANGE_OPTION);

        // Fill fields, and click Save
        Utils.waitForAndClickOnGeneralWebElement(ChangePasswordLocators.USERNAME_SEARCH_TRIGGER);
        Utils.defaultWait();
        Utils.writeTextToThisField("Bálint Ágnes", ChangePasswordLocators.USERNAME_FILTER_FIELD);
        Utils.defaultWait();
        Utils.waitForAndClickOnGeneralWebElement(ChangePasswordLocators.USERNAME_FILTER_BUTTON);
        Utils.defaultWait();

        List<WebElement> noResults = null;
        noResults = driver.findElements(By.xpath(ChangePasswordLocators.USERNAME_FILTER_NO_RESULTS.toString()));

        if (noResults.isEmpty()) {
            Utils.waitForAndClickOnGeneralWebElement(ChangePasswordLocators.USERNAME_FILTER_CLOSE_BUTTON);
            Utils.waitForAndClickOnGeneralWebElement(ChangePasswordLocators.MODEL_EDIT_WINDOW_CANCEL_BUTTON);

            org.junit.Assume.assumeFalse("No results.", noResults != null);
        }
        System.out.println("asd1");

        Utils.waitForAndClickOnGeneralWebElement(ChangePasswordLocators.USERNAME_FILTER_FIRST_MATCH);

        System.out.println("asd2");

        Utils.waitForAndClickOnGeneralWebElement(ChangePasswordLocators.USERNAME_FILTER_SAVE);
        System.out.println("asd3");
        final String newPass = Utils.randomString(Constants.CHARSET, 30);
        Utils.writeTextToThisField(newPass, ChangePasswordLocators.PASSWORD_FIELD);
        Utils.writeTextToThisField(newPass, ChangePasswordLocators.PASSWORD_AGAIN_FIELD);
        Utils.waitMillisec(1000);
        Utils.waitForAndClickOnGeneralWebElement(ChangePasswordLocators.MODEL_EDIT_WINDOW_SAVE);

        // Validate message
        assertTrue("There should be a successful message present!",
                Utils.isMessageBoxPresentWithText(ChangePasswordLocators.SUCCESSFUL_MESSAGE));

        // Logout
        LogoutManager.logout();

        // Try login with user and new password
        LoginManager.loginWithThisUsernameAndPassword(Arrays.asList("agnes.balint", newPass));
//		RecordSelectionManager.waitUntilTableIsReady();

        // Check for news window
        if (Utils.isThisElementPresent(MainPageLocators.NEWS_WINDOW)) {
            Utils.waitForAndClickOnGeneralWebElement(MainPageLocators.NEWS_WINDOW_CLOSE);
        }

        final boolean isErrorWindowPresent = Utils.isThisElementPresent(ChangePasswordLocators.ERROR_WINDOW_AFTER_LOGIN);
        if (isErrorWindowPresent) {
            Utils.waitForAndClickOnGeneralWebElement(ChangePasswordLocators.ERROR_WINDOW_CLOSE_BUTTON);
            fail("There is an unexpected error window present after logging in with the new password!");
        }

        LoginManager.checkPageLoadWithUsername("agnes.balint");
    }

    /**
     * Admin forum button after logout/login Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/3135">#3135</a>
     */
    @Test
    @Category(CoreSuite.class)
    public final void testAdminForumIsNotVisibleWithAuthor() {
        Utils.writeMyRedmineIssues("#3135");

        // Click on Forum button
        Utils.waitForAndClickOnGeneralWebElement(MainPageLocators.FORUM_BUTTON);
        Utils.defaultWait();
//		Utils.waitForAndClickOnGeneralWebElement(GeneralPageLocators.MESSAGE_CENTER_WINDOW_CLOSE_BUTTON);
        Utils.waitForAndClickOnGeneralWebElement(GeneralPageLocators.MESSAGE_CENTER_WINDOW_CLOSE_BUTTON_ALTERNATIVE);

        // Logout and login with author
        LogoutManager.logout();

        //driver.navigate().refresh();

        LoginManager.loginSuccessfullyWithThisRole(Roles.AUTHOR);

        driver.navigate().refresh();

        // Click on Forum button and check that admin forums are not present
        Utils.waitForAndClickOnGeneralWebElement(MainPageLocators.FORUM_BUTTON);
        Utils.defaultWait();
        final boolean result = Utils.isThisElementVisible(ChangePasswordLocators.ADMIN_FORUM_TAB);

//		System.out.println(Utils.createGeneralWebElementsFromEnum(GeneralPageLocators.MESSAGE_CENTER_WINDOW_CLOSE_BUTTON_ALTERNATIVE).size());

        Utils.waitForAndClickOnGeneralWebElement(GeneralPageLocators.MESSAGE_CENTER_WINDOW_CLOSE_BUTTON_ALTERNATIVE);
        assertFalse("Admin forum is visible with author user!", result);
    }

    /**
     * Checks citations list view Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/3140">#3140</a>
     */
    @Test
    @Category(CoreSuite.class)
    @Ignore //Nincs már idézés típus, az idézés kapcsolat pedig más formátumú már lista nézetben
    public final void testCitationListGridView() {
        Utils.writeMyRedmineIssues("#3140");

        // Select citations record type and go to list view
        RecordSelectionManager.selectThisRecordTypeFromSelector(GeneralRecordTypes.CITATION);
        ChangeViewManager.switchToListGridView();
        final WebElement selectedRow = RecordSelectionManager.selectRandomRenderedRowsFromGridPanel(1).get(0);
        System.out.println(selectedRow.findElement(By.xpath(".//tbody/tr/td[2]/div")).getText());
        // Validate
        assertNotEquals("The list grid view of the Citations record type is not correct!",
                "Függő: ${code}", selectedRow.findElement(By.xpath(".//tbody/tr/td[2]/div")).getText());

    }

}
