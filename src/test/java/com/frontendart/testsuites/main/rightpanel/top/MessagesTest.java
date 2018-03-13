package com.frontendart.testsuites.main.rightpanel.top;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.frontendart.categories.AUTHOR_Suite;
import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.CoreSuite;
import com.frontendart.categories.INSTITUTIONAL_ADMIN_Suite;
import com.frontendart.categories.RightPanelTopSuite;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.main.rightpanel.top.MessagesJunitTestClass;
import com.frontendart.locators.general.GeneralPageLocators;
import com.frontendart.managers.general.GeneralTableManager;
import com.frontendart.managers.main.rightpanel.top.TopGeneralManager;

/**
 * Class for message test
 * @author Zoli
 *
 */
@Category({ RightPanelTopSuite.class, AUTHOR_Suite.class, INSTITUTIONAL_ADMIN_Suite.class, CENTRAL_ADMIN_Suite.class })
@SuppressWarnings({ "PMD.JUnitTestsShouldIncludeAssert", "PMD.JUnitTestContainsTooManyAsserts" })
public class MessagesTest extends MessagesJunitTestClass {

	/**
	 * Create random message
	 * 
	 * TODO: reimplement
	 */

	/**
	 * Check table headers
	 * 
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/2183">#2183</a>
	 */
	@Test
	@Category(CoreSuite.class)
	public final void testCheckDuplicatedTableHeaders() throws InterruptedException {
		Utils.writeMyRedmineIssues("#2183");

		// Get visible headers
		final List<String> listOfHeaders = GeneralTableManager.getAllVisibleHeadersText(GeneralPageLocators.MESSAGE_CENTER_WINDOW_GRIDPANEL);
		final Set<String> setOfHeaders = new HashSet<String>(listOfHeaders);
		TopGeneralManager.closeWindow();

		// Validation
		assertEquals("There are duplicate headers in the Messages table!", setOfHeaders.size(), listOfHeaders.size());
	}

}