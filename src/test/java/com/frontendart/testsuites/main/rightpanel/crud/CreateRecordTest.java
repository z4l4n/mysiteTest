package com.frontendart.testsuites.main.rightpanel.crud;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.frontendart.categories.AUTHOR_Suite;
import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.INSTITUTIONAL_ADMIN_Suite;
import com.frontendart.categories.RightPanelCrudSuite;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.JunitTestClass;
import com.frontendart.locators.records.attributes.general.GeneralRecordTypes;
import com.frontendart.managers.main.leftpanel.RecordSelectionManager;
import com.frontendart.managers.main.leftpanel.SearchManager;
import com.frontendart.managers.main.rightpanel.crud.CreatePublicationManager;
import com.frontendart.managers.main.rightpanel.crud.CreateRecordManager;
import com.frontendart.managers.main.rightpanel.view.ChangeViewManager;

/**
 * Parameterized test class for record creation. 
 * @author Zoli
 *
 */
@Category({ RightPanelCrudSuite.class, AUTHOR_Suite.class, INSTITUTIONAL_ADMIN_Suite.class, CENTRAL_ADMIN_Suite.class })
@SuppressWarnings("PMD.JUnitTestContainsTooManyAsserts")
public class CreateRecordTest extends JunitTestClass {

	/**
	 * Record type attribute
	 */
	private static GeneralRecordTypes recordType;

	/**
	 * Reimplement: Simple record creation with empty req. fields 
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1050">#1050</a>
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1051">#1051</a>
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1052">#1052</a>
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1286">#1286</a>
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1291">#1291</a>
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1355">#1355</a>
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1363">#1363</a>
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1369">#1369</a>
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1373">#1373</a>
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1414">#1414</a>
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1421">#1421</a>
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1429">#1429</a>
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1533">#1533</a>
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1598">#1598</a>
	 */
	/*	@Test
		public final void testCreateRecordWithEmptyRequiredFields() {
			Utils.writeMyRedmineIssues("#1050#1051#1052#1286#1291#1355#1363#1369#1373#1414#1421#1429#1533#1598");

			// Select random record type, go there, and run empty query
			recordType = GeneralRecordTypes.getCreatableRecordTypeWithRequiredFieldsForActualRole();
			goToThisRecordTypeAndRunEmptyQuery();
			// Get old number of table
			final int oldNumberOfTable = RecordSelectionManager.gridTableFullSize();

			// With empty required fields
			CreateRecordManager.clickOnNewButton();
			CreateRecordManager.checkForPublicationRecordTypeAndClickNext(recordType);
			final List<GeneralTableAttributes> notDisabledAttributes = GeneralRecordTypes.getNotDisabledAttributes(recordType.getAttributes());
			CreateRecordManager.fillNotRequiredFieldsOnly(notDisabledAttributes);
			CreateRecordManager.clickOnSaveAndCloseButton();

			// Accept message box
			Utils.acceptMessageBoxIfVisible();
			Utils.defaultWait();

			// Validation
			assertTrue("Message box should be present", Utils.isMessageBoxVisible());

			// Click cancel button
			Utils.cancelMessageBoxIfVisible();

			// Close window
			CreateRecordManager.closeEditorWindow();
			final int newNumberOfTable = RecordSelectionManager.gridTableFullSize();
			assertEquals("The number of rows in the table is not correct!", oldNumberOfTable, newNumberOfTable);

			// remove the query which was needed to get all of the records
			SearchManager.deleteThisQuery(Constants.MY_EMPTY_QUERY_NAME);
		}
	*/

	/**
	 * Creates random record
	 */
	@Test
	public final void testCreateRandomRecord() {
		// Select random record type (which is not PUBLICATION, it's a different case...), go there, and run empty query
		recordType = GeneralRecordTypes.getCreatableRecordTypeForActualRole();
		int count = 0;
		while (recordType.equals(GeneralRecordTypes.PUBLICATION) && (count < 10)) {
			recordType = GeneralRecordTypes.getCreatableRecordTypeForActualRole();
			count++;
		}
		
		
		//RecordSelectionManager.selectThisRecordTypeFromSelector(recordType);
		RecordSelectionManager.selectThisRecordTypeFromSelectorWithoutRunEmptyQuery(recordType);
		//SearchManager.createAndRunSubsetQuery(recordType);
		SearchManager.createAndRunEmptyQuery();
		RecordSelectionManager.waitUntilTableIsReady();
		
		// Get old number of table
		final int oldNumberOfTable = RecordSelectionManager.gridTableFullSize();
		
		// Create record, with required fields
		CreateRecordManager.createRecord(recordType);
		Utils.acceptMessageBoxIfVisible();
		Utils.defaultWait();

		//Rerun empty query (to get the new result), then validate
		//SearchManager.createAndRunSubsetQuery(recordType);
		SearchManager.createAndRunEmptyQuery();
		RecordSelectionManager.waitUntilTableIsReady();
		final int newNumberOfTable = RecordSelectionManager.gridTableFullSize();		
		Utils.defaultWait();
		assertEquals("The number of rows in the table is not correct!", oldNumberOfTable + 1, newNumberOfTable);

		// cleanup
		// TODO:
		/*
		if (TestConfiguration.role.getEditableRecordTypes().contains(recordType)) {
			SearchManager.cleanup();
		}
		*/
	}

	/**
	 * Creates random publication
	 * @TODO step 3 should dynamically inputs required data
	 */
	@Test
	@Ignore
	public final void testCreateRandomPublication() {
		SearchManager.createAndRunSubsetQuery(GeneralRecordTypes.PUBLICATION);
		ChangeViewManager.switchToGridView();

		// Get old number of table
		final int oldNumberOfTable = RecordSelectionManager.gridTableFullSize();

		// Start: Click on New button, then Next
		CreateRecordManager.clickOnNewButton();
		CreateRecordManager.clickOnNextButton();

		// Step 1: Fill Type, SubType, Category
		CreatePublicationManager.fillFirstStepAndClickOnNextButton();

		// Step 2: Check for in-between-steps
		CreatePublicationManager.checkForInBetweenStep();
		CreatePublicationManager.checkForInBetweenStep();

		// Step 3: Final fields
		CreatePublicationManager.fillFinalFieldsAndSave();

		// Check for message boxes
		CreatePublicationManager.checkForMessageBoxes();

		// Check SWORD window
		CreatePublicationManager.checkForSwordWindow();

		//Rerun empty query (to get the new result), then validate
		SearchManager.createAndRunSubsetQuery(GeneralRecordTypes.PUBLICATION);
		final int newNumberOfTable = RecordSelectionManager.gridTableFullSize();
		assertEquals("The number of rows in the table is not correct!", oldNumberOfTable + 1, newNumberOfTable);

		// cleanup
		// TODO
		/*
		if (TestConfiguration.role.getEditableRecordTypes().contains(GeneralRecordTypes.PUBLICATION)) {
			SearchManager.cleanup();
		}
		*/
	}

	// FURTHER TESTS TO BE IMPLEMENTED

	/**
	 * Record creation with adding extendable fields
	 * 
	 *  TODO: refaktor
	 */

	/**
	 * Simple publication creation
	 * 
	 *  TODO:
	 */

	/**
	 * Create publication with DOI
	 */
	@Test
	public final void testCreatePublicationWithExistingDOI() {
		SearchManager.createAndRunEmptyQuery();
		CreateRecordManager.createPublicationWithExistingDOI();
	}

	/**
	 * Simple publication creation with extendable fields
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1282">#1282</a>
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1444">#1444</a>
	 * 
	 * TODO:
	 */
}
