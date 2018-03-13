package com.frontendart.testsuites.main.rightpanel.view;

import static org.junit.Assert.assertFalse;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.CoreSuite;
import com.frontendart.categories.VersionsSuite;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.SelectRandomRecordTypeJunitTestClass;
import com.frontendart.locators.main.rightpanel.view.ViewVersionsLocators;
import com.frontendart.managers.main.leftpanel.RecordSelectionManager;
import com.frontendart.managers.main.rightpanel.view.ViewVersionsManager;

/**
 * Parameterized test class for view record. 
 * @author Zoli
 *
 */
@Category({ VersionsSuite.class, CENTRAL_ADMIN_Suite.class })
@SuppressWarnings({ "PMD.JUnitTestsShouldIncludeAssert", "PMD.JUnitTestContainsTooManyAsserts" })
public class ViewVersionsTest extends SelectRandomRecordTypeJunitTestClass {

	// This test class should only run with admin (mtmtuser4) rights - meaning that it will only run once
	@BeforeClass
	public static void beforeMethod() {
		org.junit.Assume.assumeTrue(Utils.actualRoleIsCentralAdmin());
	}

	/**
	 * Simple view version
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1362">#1362</a>
	 * 
	 * TODO: review
	 */
	@Test
	@Category(CoreSuite.class)
	public final void testViewVersions() {
		Utils.writeMyRedmineIssues("#1362");

		// Select random record type, and click on random row
		RecordSelectionManager.selectRandomRenderedRowsFromGridPanel(1);

		// Click on Versions button and switch to versions window
		final int versionsNumber = ViewVersionsManager.getRowNumberOfVersionsTableForActualRecord();

		// Validate
		Utils.myAssertTrue("Legalább 1 sornak lennie kell a verzió ablakban.", versionsNumber > 0);

	}

	/**
	 * Reimplement: View version after status change (duplicated record works different than other)
	 */
	/*@Test
	public final void testViewVersionsAfterStatusChange() {
		// Select random record type, and select random record
		final WebElement myRecordRow = RecordSelectionManager.selectRandomRenderedRowsFromGridPanel(1).get(0);
		final int myRecordID = GeneralTableManager.getIDOfThisRow(myRecordRow);

		// View versions row number
		final int oldNumberOfRows = ViewVersionsManager.getRowNumberOfVersionsTableForActualRecord();

		// Change record status
		final ChangeStatusTypes originalStatusType = ChangeStatusManager.getStatusValueOfRecord(myRecordRow);
		ChangeStatusManager.changeStatusTypeOfSelectedRow(myRecordID);
		Utils.cancelMessageBoxIfVisible();

		// Validate: View versions row number
		final int newNumberOfRows = ViewVersionsManager.getRowNumberOfVersionsTableForActualRecord();
		assertEquals("There should be one more row in the versions table!", oldNumberOfRows + 1, newNumberOfRows);

		// Cleanup: change status back to original - if possible
		ChangeStatusManager.changeStatusTypeOfSelectedRowToThis(originalStatusType);
	}*/

	/**
	 * Reimplement: Restore version (duplicated record works different than other)
	 */
	/*	@Test
		public final void testRestoreVersion() {
			// Select random record type, and select random record
			WebElement myRecordRow = RecordSelectionManager.selectRandomRenderedRowsFromGridPanel(1).get(0);
			final int myRecordID = GeneralTableManager.getIDOfThisRow(myRecordRow);
			final ChangeStatusTypes originalStatusType = ChangeStatusManager.getStatusValueOfRecord(myRecordRow);

			// View versions, get version number of top row
			final int versionID = ViewVersionsManager.getActualVersionIDOfRecord();

			// Change record status
			ChangeStatusManager.changeStatusTypeOfSelectedRow(myRecordID);

			// Restore version (the one before actual)
			ViewVersionsManager.restoreThisVersion(versionID);
			assertTrue("There should be a success message present",
					Utils.isMessageBoxPresentWithText(VersionsMessageTypes.SUCCESS));

			// Validate: Check record status (should be the original)
			myRecordRow = GeneralTableManager.findThisRecordByID(myRecordID);
			final ChangeStatusTypes newStatusType = ChangeStatusManager.getStatusValueOfRecord(myRecordRow);
			assertEquals("The record statuses are not equal!", originalStatusType, newStatusType);

		}
	*/

	/**
	 * Simple filter in version view
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/2612">#2612</a>
	 * 
	 */
	@Test
	@Category(CoreSuite.class)
	public final void testFilterInVersionsWindow() {
		Utils.writeMyRedmineIssues("#2612");

		// Select random record type, and click on random row
		RecordSelectionManager.selectRandomRenderedRowsFromGridPanel(1);

		// Click on Versions button and switch to versions window
		ViewVersionsManager.openVersionsWindow();

		// Check that there is no filter field present
		assertFalse("There should not be a filter field in the versions window!",
				Utils.isThisElementPresent(ViewVersionsLocators.VERSIONS_WINDOW_FILTER_FIELD));
		assertFalse("There should not be a filter button in the versions window!",
				Utils.isThisElementPresent(ViewVersionsLocators.VERSIONS_WINDOW_FILTER_BUTTON));

		// Close versions window
		ViewVersionsManager.closeVersionsWindow();
	}

}
