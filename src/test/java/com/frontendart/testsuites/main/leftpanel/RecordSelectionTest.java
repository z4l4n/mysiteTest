package com.frontendart.testsuites.main.leftpanel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.frontendart.categories.AUTHOR_Suite;
import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.CoreSuite;
import com.frontendart.categories.INSTITUTIONAL_ADMIN_Suite;
import com.frontendart.categories.LeftPanelSuite;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.JunitTestClass;
import com.frontendart.locators.main.leftpanel.RecordSelectionLocators;
import com.frontendart.locators.records.attributes.general.GeneralRecordTypes;
import com.frontendart.managers.general.GeneralTableManager;
import com.frontendart.managers.main.leftpanel.LeftPanelButtonsSelectionManager;
import com.frontendart.managers.main.leftpanel.RecordSelectionManager;
import com.frontendart.managers.main.leftpanel.SearchManager;
import com.frontendart.managers.main.rightpanel.view.ChangeViewManager;

/**
 * Parameterized test class for record selection.
 *
 * @author Zoli
 *
 */
@Category({ LeftPanelSuite.class, AUTHOR_Suite.class, INSTITUTIONAL_ADMIN_Suite.class, CENTRAL_ADMIN_Suite.class })
@SuppressWarnings({ "PMD.JUnitTestsShouldIncludeAssert", "PMD.JUnitTestContainsTooManyAsserts" })
public class RecordSelectionTest extends JunitTestClass {

    /**
     * Simple selection and deselection on random row(s) - without using SHIFT key
     */
    @Test
    @Category(CoreSuite.class)
    public final void testDeselectRows() {
        // Select random record type
        RecordSelectionManager.selectRandomRecordTypeFromSelector();
        Utils.defaultWait();
        SearchManager.createAndRunEmptyQuery();
        Utils.defaultWait();
        ChangeViewManager.switchToGridView();
        Utils.defaultWait();
        // Select rows without shift key
        List<WebElement> selectedRows = new ArrayList<>();
        selectedRows = RecordSelectionManager.selectRandomRenderedRowsFromGridPanel();
        RecordSelectionManager.clickRandomRenderedRowsFromGridPanel(selectedRows);
    }

    /**
     * Simple selection and deselection on random row(s) - with using SHIFT key Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1508">#1508</a>
     */
    @Test
    @Category(CoreSuite.class)
    public final void testSelectRowsWithShiftThenPerformClicks() {
        Utils.writeMyRedmineIssues("#1508");

        // Select random record type, then select rows with shift
        RecordSelectionManager.selectRandomRecordTypeFromSelector();
        final List<WebElement> selectedRows = RecordSelectionManager.selectFromGridPanelWithShift();
        RecordSelectionManager.clickRandomRenderedRowsFromGridPanel(selectedRows);
    }

    /**
     * Test all implemented record types Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1602">#1602</a>
     * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1390">#1390</a>
     */
    @Test
    @Category(CoreSuite.class)
    public final void testRandomRecordTypeHeader() {
        Utils.writeMyRedmineIssues("#1602");

        // Open selector and go down until actual record
        final GeneralRecordTypes recordType = GeneralRecordTypes.getVisibleRecordTypeForActualRole();
        RecordSelectionManager.selectThisRecordTypeFromSelector(recordType);
        ChangeViewManager.switchToGridView();
        GeneralTableManager.checkGridTableHeadersAfterSelection(recordType.getAttributes());
        Utils.myAssertFalse("Nem szabad hibaüzenetnek megjelennie.", Utils.isAlertPresent());
    }

    /**
     * Test left panel record buttons
     */
    @Test
    @Category(CoreSuite.class)
    public final void testLeftPanelRecordSelectorButtons() {
        // Get random left panel button and click on it
        final String buttonName = LeftPanelButtonsSelectionManager.selectRandomLeftPanelRecordSelectorButton();

        // Validate
        final String selectorValue = Utils
                .getNameOfThisButton(Utils.createGeneralWebElementFromEnum(RecordSelectionLocators.RECORD_SELECTOR));
        assertEquals("The name of the record selector is not correct!", buttonName, selectorValue);

        // TODO: further validation (select same record from selector, and check)
    }

    /**
     * Test grid panel HOME and END buttons
     */
    @Test
    @Category(CoreSuite.class)
    public final void testHomeAndEndButtons() {
        // Select random record type
        RecordSelectionManager.selectRandomRecordTypeFromSelector();
        SearchManager.createAndRunEmptyQuery();
        ChangeViewManager.switchToGridView();
        Utils.defaultWait();
        // Select random row
        final WebElement row = RecordSelectionManager.selectRandomRenderedRowsFromGridPanel(1).get(0);

        // Click HOME button
        row.findElement(By.xpath(".//tbody/tr/td[1]")).sendKeys(Keys.HOME);
        Utils.defaultWait();

        //Check table rows
        org.junit.Assume.assumeTrue(!GeneralTableManager.getAllRowsOfMainGrid().isEmpty());

        // Get first row of table
        final WebElement firstRow = GeneralTableManager.getAllRowsOfMainGrid().get(0);
        assertTrue("The first row should be selected!", firstRow.getAttribute("class").contains("selected"));

        // Click END button
        firstRow.findElement(By.xpath(".//tbody/tr/td[1]")).sendKeys(Keys.END);
        Utils.defaultWait();

        // Get last row of table
        final WebElement lastRow = GeneralTableManager.getAllRowsOfMainGrid().get(GeneralTableManager.getAllRowsOfMainGrid().size() - 1);
        assertTrue("The last row should be selected!", lastRow.getAttribute("class").contains("selected"));
    }
}
