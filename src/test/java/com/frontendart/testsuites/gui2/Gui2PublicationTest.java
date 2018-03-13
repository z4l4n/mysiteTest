package com.frontendart.testsuites.gui2;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.CoreSuite;
import com.frontendart.categories.Gui2Suite;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.Gui2NavigateToRandomPublicationJunitTestClass;
import com.frontendart.managers.gui2.Gui2PublicationManager;

/**
 * Test class for gui2 tests.
 * 
 * @author Zoli
 *
 */
//@Ignore
@Category({ Gui2Suite.class, CENTRAL_ADMIN_Suite.class })
@SuppressWarnings({ "PMD.JUnitTestsShouldIncludeAssert", "PMD.JUnitTestContainsTooManyAsserts" })
public class Gui2PublicationTest extends Gui2NavigateToRandomPublicationJunitTestClass {

	// This test class should only run with central admin (mtmtuser4) rights -
	// meaning that it will only run once
	@BeforeClass
	public static void beforeMethod() {
		org.junit.Assume.assumeTrue(Utils.actualRoleIsCentralAdmin());
	}

	/**
	 * Publication details
	 */
	@Test
	@Category(CoreSuite.class)
	public final void testPublicationDetails() {

		// Check publication list
		Gui2PublicationManager.checkCountOfPublication();

		// Check publication details
		Gui2PublicationManager.checkPublicationDetails(driver);

	}

	/**
	 * Navigate to author
	 */
	@Test
	@Category(CoreSuite.class)
	public final void testNavigateToAuthorFromPublication() {

		// Check publication list
		Gui2PublicationManager.checkCountOfPublication();

		// Navigate to author and check page
		Gui2PublicationManager.navigateToAuthorAndCheckPage(driver);

	}

	/**
	 * Publication details
	 */
	@Test
	@Category(CoreSuite.class)
	public final void testPublicationSortings() {

		// Check publication list
		Gui2PublicationManager.checkCountOfPublication();

		// Check sorting
		Gui2PublicationManager.checkPublicationSortings(driver);

	}

}