package com.frontendart.junitclasses;

import org.junit.Before;

import com.frontendart.locators.records.attributes.general.GeneralRecordTypes;
import com.frontendart.managers.main.leftpanel.RecordSelectionManager;
import com.frontendart.managers.main.leftpanel.SearchManager;

/**
 * Select one random record type
 * @author Zoli
 *
 */
public class SelectRandomRecordTypeJunitTestClass extends JunitTestClass {

	/**
	 * Setup 
	 */
	@Override
	@Before
	public void setUp() {
		super.setUp();
		// Go to random record type
		RecordSelectionManager.selectRandomRecordTypeFromSelector();
		SearchManager.createAndRunEmptyQuery();
	}
}
