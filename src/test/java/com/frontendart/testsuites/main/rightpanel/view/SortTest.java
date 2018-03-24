package com.frontendart.testsuites.main.rightpanel.view;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.frontendart.categories.AUTHOR_Suite;
import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.CoreSuite;
import com.frontendart.categories.INSTITUTIONAL_ADMIN_Suite;
import com.frontendart.categories.RightPanelViewSuite;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.JunitTestClass;
import com.frontendart.locators.main.rightpanel.view.SortLocators;
import com.frontendart.locators.records.attributes.general.GeneralRecordTypes;
import com.frontendart.locators.records.attributes.general.GeneralTableAttributes;
import com.frontendart.managers.general.GeneralTableManager;
import com.frontendart.managers.main.leftpanel.RecordSelectionManager;
import com.frontendart.managers.main.rightpanel.view.ChangeViewManager;
import com.frontendart.managers.main.rightpanel.view.SortManager;

/**
 * This class is responsible for Sort tests.
 *
 * @author Zoli
 */
@Category({ RightPanelViewSuite.class, AUTHOR_Suite.class, INSTITUTIONAL_ADMIN_Suite.class, CENTRAL_ADMIN_Suite.class })
@SuppressWarnings({ "PMD.JUnitTestsShouldIncludeAssert", "PMD.JUnitTestContainsTooManyAsserts" })
public class SortTest extends JunitTestClass {
    /**
     * Logger
     */
    private static final Logger LOGGER = LogManager.getLogger("SortTest");

    /**
     * Full sort test
     *
     * TODO: Make it work.
     *
     * Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1347">#1347</a>
     */
    @Test
    @Category(CoreSuite.class)
    public final void testCreateRandomSorter() {
	// Navigate to random record type

	final GeneralRecordTypes recordType = RecordSelectionManager.selectRandomRecordTypeFromSelector();
	Utils.defaultWait();
	ChangeViewManager.switchToGridView();
	Utils.defaultWait();

	// Create sorter for this record type
	SortManager.createRandomSorterForThisRecordType(recordType);
    }

    /**
     * Simple sort test, clicks on New sorting button Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1128">#1128</a>
     */
    @Test
    @Category(CoreSuite.class)
    public final void testClickOnNewSortingButton() {
	Utils.writeMyRedmineIssues("#1128");

	// Navigate to random record type, and check sort number
	RecordSelectionManager.selectRandomRecordTypeFromSelector();
	Utils.defaultWait();
	ChangeViewManager.switchToGridView();
	Utils.defaultWait();
	final int oldNumberOfSortings = SortManager.getNumberOfSorters();

	// Click on new sorting button
	Utils.waitForAndClickOnGeneralWebElement(SortLocators.NEW_SORTING_BUTTON);
	Utils.defaultWait();

	// Validate
	if (oldNumberOfSortings == 3) {
	    SortManager.checkNumberOfSorters(oldNumberOfSortings);
	} else {
	    SortManager.checkNumberOfSorters(oldNumberOfSortings + 1);
	}
    }

    /**
     * Test sort button is inactive after three sorts
     */
    @Test
    @Category(CoreSuite.class)
    public final void testSortButtonActive() {
	// Select random record type
	RecordSelectionManager.selectRandomRecordTypeFromSelector();
	Utils.defaultWait();
	ChangeViewManager.switchToGridView();
	Utils.defaultWait();
	// Close all existing sorters
	SortManager.closeAllSorters();
	Utils.defaultWait();
	// Create sortings, and check numbers (1,2,3)
	for (int index = 1; index <= 3; index++) {
	    SortManager.addNewSorter();
	    Utils.defaultWait();
	    SortManager.checkNumberOfSorters(index);
	}

	// Close one sorting, and check that there are 2 left
	SortManager.closeThisSorter(1);
	SortManager.checkNumberOfSorters(2);

	// Delete all sortings and check
	SortManager.closeAllSorters();
	Utils.defaultWait();
	SortManager.checkNumberOfSorters(0);
    }

    /**
     * Reimplement: Test sort by clicking on header Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1549">#1549</a>
     */
    /*
     * @Test public final void testSortByClickOnHeader() {
     * Utils.writeMyRedmineIssues("#1549");
     * 
     * // Navigate to random record type final GeneralRecordTypes recordType =
     * RecordSelectionManager.selectRandomRecordTypeFromSelector();
     * ChangeViewManager.switchToGridView();
     * 
     * // Select a header and click on it // TODO: if that header is not visible, we
     * should make it visible. final GeneralTableAttributes randomLocator =
     * GeneralRecordTypes.getRandomAttribute(recordType);
     * LOGGER.info("A következő oszlop szerint szeretnénk rendezni: " +
     * Utils.getAllMyLabelsAsString(randomLocator)); for (final WebElement header :
     * GeneralTableManager.getAllVisibleHeadersAsWebElements()) { final String label
     * = header.findElement(By.xpath("./div/span/span/span")).getText(); if
     * (Utils.isThisStringListContainsThisAttribute(Arrays.asList(label),
     * randomLocator)) { LOGGER.info("Kattintsunk a " +
     * Utils.getAllMyLabelsAsString(randomLocator) + " nevű oszlop fejlécére.");
     * header.click(); Utils.defaultWait(); header.click(); Utils.defaultWait(); } }
     * 
     * // Validate SortManager.checkNumberOfSorters(1); }
     */
    /**
     * Test sort by selecting header Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1549">#1549</a>
     */
    @Test
    @Category(CoreSuite.class)
    public final void testSortBySelectingHeader() {
	Utils.writeMyRedmineIssues("#1549");

	// Navigate to random record type
	final GeneralRecordTypes recordType = RecordSelectionManager.selectRandomRecordTypeFromSelector();
	ChangeViewManager.switchToGridView();

	// Select a header and click on it
	final GeneralTableAttributes randomLocator = GeneralRecordTypes.getRandomAttribute(recordType);
	LOGGER.info("A következő oszlop szerint szeretnénk rendezni: " + Utils.getAllMyLabelsAsString(randomLocator));
	for (final WebElement header : GeneralTableManager.getAllVisibleHeadersAsWebElements()) {
	    final String label = header.findElement(By.xpath("./div/div/div/div/span")).getText();
	    if (Utils.doesThisStringListContainsThisAttribute(Arrays.asList(label), randomLocator)) {
		LOGGER.info("Kattintsunk a " + Utils.getAllMyLabelsAsString(randomLocator)
			+ " oszlop fejlécénél lévő legördülő nyílra, és válasszuk a növekvő sorrendezés opciót.");
		header.sendKeys(Keys.DOWN);
		Utils.switchToActiveElement().click();
		Utils.defaultWait();
		SortManager.checkNumberOfSorters(1);
	    }
	}
    }

    /**
     * Test sort by selecting header Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1549">#1549</a> I
     * think this would be a proper testing because we have to have one sorting
     * before clicking on the header to check if the former one will be removed
     */
    @Test
    @Category(CoreSuite.class)
    public final void testSortBySelectingHeader2() {
	final GeneralRecordTypes recordType = RecordSelectionManager.selectRandomRecordTypeFromSelector();
	Utils.defaultWait();
	ChangeViewManager.switchToGridView();
	Utils.defaultWait();
	SortManager.closeAllSorters();
	Utils.defaultWait();

	// Create sorter for this record type
	SortManager.createRandomSorterForThisRecordType(recordType);
	Utils.defaultWait();
	// Select a header and click on it
	final GeneralTableAttributes randomLocator = GeneralRecordTypes.getRandomAttribute(recordType);
	LOGGER.info("A következő oszlop szerint szeretnénk rendezni: " + Utils.getAllMyLabelsAsString(randomLocator));
	for (final WebElement header : GeneralTableManager.getAllVisibleHeadersAsWebElements()) {
	    final String label = header.findElement(By.xpath("./div/div/div/div/span")).getText();
	    if (Utils.doesThisStringListContainsThisAttribute(Arrays.asList(label), randomLocator)) {
		LOGGER.info("Kattintsunk a " + Utils.getAllMyLabelsAsString(randomLocator)
			+ " oszlop fejlécénél lévő legördülő nyílra, és válasszuk a növekvő sorrendezés opciót.");
		header.sendKeys(Keys.DOWN);
		Utils.switchToActiveElement().click();
		Utils.defaultWait();
		SortManager.checkNumberOfSorters(1);
	    }
	}
    }

    // FURTHER TESTS TO BE IMPLEMENTED

    /**
     * Check sorting by multiple columns
     */

}
