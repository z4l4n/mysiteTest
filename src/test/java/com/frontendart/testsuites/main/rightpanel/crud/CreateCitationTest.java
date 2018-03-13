package com.frontendart.testsuites.main.rightpanel.crud;

import static org.junit.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.frontendart.categories.AUTHOR_Suite;
import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.INSTITUTIONAL_ADMIN_Suite;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.JunitTestClass;
import com.frontendart.locators.records.attributes.general.GeneralRecordTypes;
import com.frontendart.managers.main.leftpanel.RecordSelectionManager;
import com.frontendart.managers.main.leftpanel.SearchManager;
import com.frontendart.managers.main.rightpanel.crud.CreateRecordManager;
import com.frontendart.managers.main.rightpanel.view.ChangeViewManager;

@Category({ CreateCitationTest.class, AUTHOR_Suite.class, INSTITUTIONAL_ADMIN_Suite.class, CENTRAL_ADMIN_Suite.class })
public class CreateCitationTest extends JunitTestClass {
	/**
	 * Logger
	 */
	private static final Logger LOGGER = LogManager.getLogger("MainSearchTest");

	/**
	 * Creates own citations 
	 */
	@Test
	public final void testCreateRandomCitations() {
		RecordSelectionManager.selectThisRecordTypeFromSelector(GeneralRecordTypes.CITATION);
		SearchManager.createAndRunOwnQuery(GeneralRecordTypes.CITATION);
		ChangeViewManager.switchToGridView();

		// Get old number of table
		final int oldNumberOfTable = RecordSelectionManager.gridTableFullSize();

		CreateRecordManager.createRecord(GeneralRecordTypes.CITATION);
		Utils.acceptMessageBoxIfVisible();
		Utils.waitForMessageBoxToBeDismissed();
		
		SearchManager.createAndRunOwnQuery(GeneralRecordTypes.CITATION);
		final int newNumberOfTable = RecordSelectionManager.gridTableFullSize();
		assertEquals("The number of rows in the table is not correct!", oldNumberOfTable + 1, newNumberOfTable);
		
		SearchManager.cleanup();
		LOGGER.info("testCreateRandomCitations has finished");
	}
}