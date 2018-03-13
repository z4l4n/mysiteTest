package com.frontendart.junitclasses;

import org.junit.Before;

import com.frontendart.managers.gui2.Gui2SelectorManager;

/**
 * Junit test class for gui2 tests which navigates to random publication
 * @author Zoli
 *
 */
public class Gui2NavigateToRandomPublicationJunitTestClass extends Gui2JunitTestClass {

	/**
	 * Setup
	 * 
	 * @author gyizol
	 */
	@Before
	@Override
	public void setUp() {
		super.setUp();

		// Navigate to random record type, which has publications
		Gui2SelectorManager.navigateToRandomItemWhichHasPublications();
	}

}
