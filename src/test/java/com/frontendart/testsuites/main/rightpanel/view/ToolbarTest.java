package com.frontendart.testsuites.main.rightpanel.view;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.frontendart.categories.AUTHOR_Suite;
import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.CoreSuite;
import com.frontendart.categories.INSTITUTIONAL_ADMIN_Suite;
import com.frontendart.categories.ToolbarSuite;
import com.frontendart.common.Roles;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.JunitTestClass;
import com.frontendart.locators.general.GeneralLocatorTypes;
import com.frontendart.locators.main.rightpanel.crud.RecordEditorLocators;
import com.frontendart.locators.main.rightpanel.view.ToolbarLocators;
import com.frontendart.locators.records.attributes.general.GeneralRecordTypes;
import com.frontendart.managers.main.leftpanel.RecordSelectionManager;
import com.frontendart.managers.main.leftpanel.SearchManager;
import com.frontendart.managers.main.rightpanel.view.ChangeViewManager;

/**
 * This class is responsible for SearchToolbar test.
 *
 * @author Zoli
 *
 */
@Category({ ToolbarSuite.class, AUTHOR_Suite.class, INSTITUTIONAL_ADMIN_Suite.class, CENTRAL_ADMIN_Suite.class })
@SuppressWarnings({ "PMD.JUnitTestsShouldIncludeAssert", "PMD.JUnitTestContainsTooManyAsserts" })
public class ToolbarTest extends JunitTestClass {

    /**
     * Logger
     */
    private static final Logger LOGGER = LogManager.getLogger("SearchToolbarTest");

    /**
     * Basic search toolbar test Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1047">#1047</a> Redmine issue
     * number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1276">#1276</a>
     */

    /**
     * Search toolbar page check after clicking on Deselect All button
     */
    @Test
    @Category(CoreSuite.class) // SEEMS OK
    public final void testPageAfterDeselectAll() {
        // Select random record type
        RecordSelectionManager.selectRandomRecordTypeFromSelector();
        Utils.defaultWait();

        // Click on select_all button, then on deselect_all button
        Utils.waitForAndClickOnGeneralWebElement(ToolbarLocators.SELECT_ALL_BUTTON);
        Utils.defaultWait();
        Utils.waitForAndClickOnGeneralWebElement(ToolbarLocators.DESELECT_ALL_BUTTON);

        // Validate delete button (wait is needed)
        Utils.defaultWait();
        final WebElement deleteButton = Utils.createGeneralWebElementFromEnum(ToolbarLocators.DELETE_BUTTON);
        Utils.myAssertTrue("A Törlés/Remove gombnak inaktívnak kell lennie.",
                deleteButton.getAttribute("class").contains("disabled"));
        assertTrue("There is a selected row.",
                Utils.myAssertEquals("Egyetlen sornak sem szabad kijelölve lennie.", 0, selectedRowsCount()));
    }

    /**
     * Search toolbar page check after clicking on Select All button Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1243">#1243</a>
     */
    @Test
    @Category(CoreSuite.class) // SEEMS OK
    public final void testPageAfterSelectAll() {
        Utils.writeMyRedmineIssues("#1243");

        // Select random record type
        final GeneralRecordTypes recordType = RecordSelectionManager.selectRandomRecordTypeFromSelector();
        Utils.defaultWait();

        // Click on select all button
        Utils.waitForAndClickOnGeneralWebElement(ToolbarLocators.SELECT_ALL_BUTTON);

        // Validate
        int expected = RecordSelectionManager.gridTableFullSize();
        if ((Utils.getActualRole().equals(Roles.AUTHOR)) && recordType.equals(GeneralRecordTypes.AUTHOR)
                && (expected > 100)) {
            expected = 100;
        }
        assertTrue("Not all items are selected.",
                Utils.myAssertEquals("Az összes elemnek kijelölve kell lennie.", expected, selectedRowsCount()));
    }

    /**
     * New button check for creatable record types
     */
    @Test
    @Category(CoreSuite.class) // SEEMS OK
    public final void testNewButtonForCreatableRecordTypes() {
        // Select random creatable record type
        final GeneralRecordTypes recordType = GeneralRecordTypes.getCreatableRecordTypeForActualRole();
        RecordSelectionManager.selectThisRecordTypeFromSelector(recordType);
        Utils.defaultWait();
        // Validation
        assertFalse("The new button should be enabled!", isThisElementDisabled(ToolbarLocators.NEW_BUTTON));

        // Click on new button
        Utils.waitForAndClickOnGeneralWebElement(ToolbarLocators.NEW_BUTTON);
        Utils.defaultWait();
        // Validate
        // final WebElement editorWindow = Utils.switchToActiveElement();
        final WebElement editorWindow = Utils
                .createGeneralWebElementFromString("//div[starts-with(@aria-labelledby, 'modeleditorwindow')]");

        final String textOfWindow = editorWindow.findElement(By.xpath("./div/div/div/div/div")).getText();

        Utils.myAssertTrue(
                "A megjelenő felugró ablak fejlécének tartalmaznia kell az \"Új hozzáadása\" / \"Add new\"szöveget.",
                textOfWindow.contains("Új hozzáadása") || textOfWindow.contains("Add new"));

        // Close
        LOGGER.info("Zárjuk be az ablakot.");
        Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.CANCEL_BUTTON);
        Utils.acceptMessageBoxIfVisible();
    }

    /**
     * New button check for not creatable record types
     */
    @Test
    @Category(CoreSuite.class) // seems ok
    public final void testNewButtonForNotCreatableRecordTypes() {
        // Select random not creatable record type
        final EnumSet<GeneralRecordTypes> notCreatableRecordTypes = Utils.getActualRole().getVisibleRecordTypes();
        final EnumSet<GeneralRecordTypes> creatableRecordTypes = Utils.getActualRole().getCreatableRecordTypes();
        for (final GeneralRecordTypes recordType : creatableRecordTypes) {
            notCreatableRecordTypes.remove(recordType);
        }

        // Check if types size greater than 0
        org.junit.Assume.assumeTrue(notCreatableRecordTypes.size() != 0);

        // Get random record type
        final int randNumber = Utils.randInt(0, notCreatableRecordTypes.size() - 1);
        final Iterator<GeneralRecordTypes> iterator = notCreatableRecordTypes.iterator();
        for (int index = 0; index < randNumber; index++) {
            iterator.next();
        }
        RecordSelectionManager.selectThisRecordTypeFromSelector(iterator.next());
        Utils.defaultWait();
        // Validation

        System.out.println("isThisElementEnabled" + isThisElementEnabled(ToolbarLocators.NEW_BUTTON));
        System.out.println("isThisElementVisible" + Utils.isThisElementVisible(ToolbarLocators.NEW_BUTTON));

        assertFalse("The new button either should not be visible or it should be disabled!",
                (isThisElementEnabled(ToolbarLocators.NEW_BUTTON)
                        && Utils.isThisElementVisible(ToolbarLocators.NEW_BUTTON)));
    }

    /**
     * Test new button for Ticket record type
     */
    @Test
    @Category(CoreSuite.class) // seems ok
    public final void testNewButtonForTicketType() {
        if (Utils.actualRoleIsCentralAdmin()) {
            RecordSelectionManager.selectThisRecordTypeFromSelector(GeneralRecordTypes.TICKET);
            Utils.defaultWait();

            // Validation
            assertFalse("The new button should not be visisble!",
                    Utils.isThisElementVisible(ToolbarLocators.NEW_BUTTON));
        } else {
            assertFalse("Ticket record type should not be present!",
                    RecordSelectionManager.selectThisRecordTypeFromSelector(GeneralRecordTypes.TICKET));

        }
    }

    /**
     * Search toolbar page check Edit button label (Szerkeszt/Megtekint)
     *
     * TODO: reimplement - problematic because of author record type
     */
    /*
     * @Test public final void testUpdateButtonLabel() { // Select random record
     * type final GeneralRecordTypes recordType =
     * RecordSelectionManager.selectRandomRecordTypeFromSelector();
     * RecordSelectionManager.selectRandomRenderedRowsFromGridPanel(1);
     *
     * // Get edit button label final String editButtonLabel =
     * Utils.getNameOfThisButton(Utils.createGeneralWebElementFromEnum(
     * ToolbarLocators.EDIT_BUTTON));
     *
     * // Validate: Check edit button label status depending on role if
     * (Utils.getActualRole().getEditableRecordTypes().contains(recordType)) {
     * assertTrue("The label of the edit button is not correct!",
     * editButtonLabel.equals("Edit") || editButtonLabel.equals("Szerkeszt")); }
     * else { assertTrue("The label of the edit button is not correct!",
     * editButtonLabel.equals("View") || editButtonLabel.equals("Megtekint")); }
     *
     * }
     */

    /**
     * Reimplement: Search toolbar page check editor, after clicking on Edit button
     */
    /*
     * @Test public final void testUpdateEditor() { // Select random record type
     * final GeneralRecordTypes recordType =
     * RecordSelectionManager.selectRandomRecordTypeFromSelector();
     * RecordSelectionManager.selectRandomRenderedRowsFromGridPanel(1);
     *
     * // Validation (open editor and check labels)
     * Utils.waitForAndClickOnGeneralWebElement(ToolbarLocators.EDIT_BUTTON);
     * Utils.waitForElementPresent(RecordEditorLocators.
     * EDITOR_FORM_FIELDS_VISIBLE_LABELS);
     * assertTrue("The labels should be present on the editor!",
     * RecordEditorLocators.checkAttributesAreVisible(recordType.getAttributes()));
     *
     * // Validate: Check there is no save button present if we have no right if
     * (Utils.getActualRole().getEditableRecordTypes().contains(recordType)) {
     * assertTrue("There should be a button present with name: " +
     * Utils.getAllMyLabelsAsString(RecordEditorLocators.SAVE_AND_CLOSE),
     * Utils.isThisElementVisible(RecordEditorLocators.SAVE_AND_CLOSE)); } else {
     * assertFalse("There should not be a button present with name: " +
     * Utils.getAllMyLabelsAsString(RecordEditorLocators.SAVE_AND_CLOSE),
     * Utils.isThisElementPresent(RecordEditorLocators.SAVE_AND_CLOSE)); }
     *
     * // Close editor
     * Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.CLOSE); }
     */
    /**
     * Search toolbar page check after one item is selected. Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1278">#1278</a>
     */
    @Test
    @Category(CoreSuite.class) // seems ok
    public final void testToolbarWhenOneRecordIsSelected() {
        Utils.writeMyRedmineIssues("#1278");

        // Select random record type
        RecordSelectionManager.selectRandomRecordTypeFromSelector();
        Utils.defaultWait();
        SearchManager.createAndRunEmptyQuery();
        Utils.defaultWait();
        RecordSelectionManager.selectRandomRenderedRowsFromGridPanel(1);
        Utils.defaultWait();
        // Create list of disabled buttons
        // final List<ToolbarLocators> disabledLocators =
        // Arrays.asList(ToolbarLocators.RESTORE_BUTTON);

        // Validation
        // assertTrue("The restore, and mark as duplum buttons should be disabled!",
        // areTheseElementsDisabled(disabledLocators));
        assertTrue("The restore button should be disabled!", isThisElementDisabled(ToolbarLocators.RESTORE_BUTTON));
    }

    /**
     * Search toolbar page check after more than one items are selected. Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1426">#1426</a>
     */
    @Test
    @Category(CoreSuite.class) // SEEMS OK
    public final void testToolbarWhenMoreThanOneRecordIsSelected() {
        Utils.writeMyRedmineIssues("#1426");

        // Select random record type
        // RecordSelectionManager.selectRandomRecordTypeFromSelector();
        RecordSelectionManager.selectThisRecordTypeFromSelector(GeneralRecordTypes.AUTHOR);
        Utils.defaultWait();
        ChangeViewManager.switchToGridView();
        Utils.defaultWait();
        // Check if there are enough records present
        if (RecordSelectionManager.gridTableFullSize() < 2) {
            SearchManager.createAndRunEmptyQuery();
            Utils.defaultWait();
            ChangeViewManager.switchToGridView();
            Utils.defaultWait();
            assertTrue("There are not enough records to perform this test case! At least 2 records should be present!",
                    RecordSelectionManager.gridTableFullSize() > 1);
        }

        // Select two rows
        RecordSelectionManager.selectRandomRenderedRowsFromGridPanel(2);

        // Create list of disabled buttons
        // final List<ToolbarLocators> disabledLocators =
        // Arrays.asList(ToolbarLocators.VERSIONS_BUTTON,
        // ToolbarLocators.NEW_TICKET_BUTTON, ToolbarLocators.DUPLICATE_BUTTON);

        // Validation
        Utils.defaultWait();
        // System.out.println(isThisElementDisabled(ToolbarLocators.VERSIONS_BUTTON));
        // System.out.println(isThisElementDisabled(ToolbarLocators.NEW_TICKET_BUTTON));
        //
        // assertTrue("The versions, new ticket, copy buttons should be disabled!",
        // areTheseElementsDisabled(disabledLocators));

        assertTrue("The versions button should be disabled.", isThisElementDisabled(ToolbarLocators.VERSIONS_BUTTON));
        assertTrue("The new ticket button should be disabled.",
                isThisElementDisabled(ToolbarLocators.NEW_TICKET_BUTTON));
    }

    /**
     * Search toolbar page - citations button is present only if publication record type is selected.
     */
    @Test
    @Category(CoreSuite.class) // seems ok
    public final void testToolbarCitationsButton() {
        // Select random record type

        final GeneralRecordTypes recordType = RecordSelectionManager.selectRandomRecordTypeFromSelector();

        Utils.defaultWait();
        // Check citations button is present only if publication record type is
        // selected.
        if (recordType.equals(GeneralRecordTypes.PUBLICATION)) {
            assertTrue(
                    "Citations button should be present at this record type: "
                            + RecordSelectionManager.getAllMyLabelsAsString(recordType),
                    Utils.isThisElementVisible(ToolbarLocators.CITATIONS_BUTTON));
        } else {
            assertFalse(
                    "Citations button should not be present at this record type: "
                            + RecordSelectionManager.getAllMyLabelsAsString(recordType),
                    Utils.isThisElementVisible(ToolbarLocators.CITATIONS_BUTTON));
        }
    }

    // /**
    // * Search toolbar page - import button is present only if publication type is
    // selected.
    // */
    // @Test
    // @Category(CoreSuite.class)
    // public final void testToolbarImportButton() {
    // // Select random record type
    // final GeneralRecordTypes recordType =
    // RecordSelectionManager.selectRandomRecordTypeFromSelector();
    //
    // // publication, institute or author record type
    // final List<GeneralRecordTypes> importVisibleRecordTypes =
    // Arrays.asList(GeneralRecordTypes.PUBLICATION, GeneralRecordTypes.AUTHOR,
    // GeneralRecordTypes.INSTITUTE);
    //
    // // import button is present only if publication record type is selected.
    // if (importVisibleRecordTypes.contains(recordType)) {
    // assertTrue("Import button should be present at this record type: " +
    // RecordSelectionManager.getAllMyLabelsAsString(recordType),
    // Utils.isThisElementVisible(ToolbarLocators.IMPORT_BUTTON));
    // } else {
    // assertFalse("Import button should not be present at this record type: " +
    // RecordSelectionManager.getAllMyLabelsAsString(recordType),
    // Utils.isThisElementVisible(ToolbarLocators.IMPORT_BUTTON));
    // }
    // }

    // FURTHER TESTS TO BE IMPLEMENTED

    /**
     * simple citation creation Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1629">#1629</a>
     *
     * TODO: reimplement
     */

    /**
     * Search toolbar page check after clicking on Select All button Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1345">#1345</a>
     */

    // Helper methods, only for this class

    /**
     * Gets the selected row count
     */
    private static int selectedRowsCount() {
        final String myInfoText = Utils.createGeneralWebElementFromEnum(ToolbarLocators.INFO_BAR_NUMBERS).getText();
        final List<String> myList = Arrays.asList(myInfoText.split(" "));
        final String[] myNumbersList = myList.get(0).split("/");

        return Integer.parseInt(myNumbersList[0]);
    }

    /**
     * Returns true if element is disabled
     *
     * @param newButton
     * @return
     */
    private static boolean isThisElementDisabled(final GeneralLocatorTypes locator) {
        return Utils.createGeneralWebElementFromEnum(locator).getAttribute("class").contains("disabled");
        // return !Utils.createGeneralWebElementFromEnum(locator).isEnabled();
    }

    /**
     * Returns true if element is enabled
     *
     * @param newButton
     * @return
     */
    private static boolean isThisElementEnabled(final GeneralLocatorTypes locator) {
        return !Utils.createGeneralWebElementFromEnum(locator).getAttribute("class").contains("disabled");
        // return Utils.createGeneralWebElementFromEnum(locator).isEnabled();
    }

    /**
     * returns true if the given locators are disabled
     *
     * @param locators
     * @return
     */
    private static boolean areTheseElementsDisabled(final List<ToolbarLocators> locators) {
        boolean returnValue = false;
        for (final GeneralLocatorTypes locator : locators) {
            if (!isThisElementDisabled(locator)) {
                returnValue = true;
            }
        }

        return returnValue;
    }

}
