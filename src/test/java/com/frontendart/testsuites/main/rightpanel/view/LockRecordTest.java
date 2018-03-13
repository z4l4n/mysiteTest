package com.frontendart.testsuites.main.rightpanel.view;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.CoreSuite;
import com.frontendart.categories.RightPanelViewSuite;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.JunitTestClass;
import com.frontendart.locators.records.attributes.general.GeneralRecordTypes;
import com.frontendart.managers.main.leftpanel.RecordSelectionManager;
import com.frontendart.managers.main.rightpanel.view.LockRecordManager;

/**
 * This class is responsible for LockTest test.
 * @author Zoli
 *
 */
@Category({ RightPanelViewSuite.class, CENTRAL_ADMIN_Suite.class })
@SuppressWarnings({ "PMD.JUnitTestsShouldIncludeAssert", "PMD.JUnitTestContainsTooManyAsserts" })
@Deprecated
@Ignore
public class LockRecordTest extends JunitTestClass {

	// This test class should only run with admin rights - meaning that it will only run once
	@BeforeClass
	public static void beforeMethod() {
		org.junit.Assume.assumeTrue(Utils.actualRoleIsCentralAdmin());
	}

	/**
	 * Simple lock button label check
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1481">#1481</a>
	 */
	@Test
	@Category(CoreSuite.class)
	public final void testLockButtonLabel() {
		Utils.writeMyRedmineIssues("#1481");

		final GeneralRecordTypes recordType = GeneralRecordTypes.getEditableRecordTypeForActualRole();
		RecordSelectionManager.selectThisRecordTypeFromSelector(recordType);
		// Get lock button label text
		final String label = LockRecordManager.getLockButtonText();

		// Validate
		Utils.myAssertNotEquals("A rekord zárolásra használt gombnak nem szabad üres feliratúnak lennie.", "", label);
	}

	/**
	 * Simple record lock check
	 * 
	 * TODO: Should be fixed, issue related to the MTMT ID header visibility
	 */
	/*@Test
	public final void testSimpleLockRecord() {
		final String label = Utils.createGeneralWebElementFromEnum(MainPageLocators.CHANGE_LANGUAGE_BUTTON).getText();
		if ("English".equals(label)) {
			Utils.waitForAndClickOnGeneralWebElement(MainPageLocators.CHANGE_LANGUAGE_BUTTON);
			Utils.waitForAndClickOnGeneralWebElement(MainPageLocators.CHANGE_LANGUAGE_HUNGARIAN);
			Utils.waitMillisec(10000);
		}
		RecordSelectionManager.selectThisRecordTypeFromSelector(GeneralRecordTypes.AUTHOR);
		ChangeViewManager.switchToGridView();
		// Create record, with required fields
		final List<String> oldVisibleHeaders = GeneralTableManager.getAllVisibleHeadersText();
		if (!oldVisibleHeaders.contains("MTMT azonosító")) {
			GeneralTableManager.changeTheVisibilityOfThisHeader("MTMT azonosító");
		}
		final int recordID = GeneralTableManager.getIDOfThisRow(GeneralTableManager.getAllRowsOfMainGrid().get(0));

		// Lock record
		final WebElement row = GeneralTableManager.findThisRecordByID(recordID);
		Utils.clickOnThisRow(row);
		LockRecordManager.lockCurrentRecordAndCheck();

		// TODO: Check record is locked with another role

		// Cleanup
		LockRecordManager.unLockCurrentRecord();
	}
	*/

	// FURTHER TESTS TO BE ImPLEMENTED

	/**
	 * Simple lock button label check
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1603">#1603</a>
	 * 
	 * TODO: reimplement
	 */

}
