package com.frontendart.testsuites.main.rightpanel.top;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.frontendart.categories.AUTHOR_Suite;
import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.INSTITUTIONAL_ADMIN_Suite;
import com.frontendart.categories.RightPanelTopSuite;
import com.frontendart.junitclasses.main.rightpanel.top.ForumJunitTestClass;
import com.frontendart.locators.main.rightpanel.top.ForumTableAttributes;
import com.frontendart.managers.main.rightpanel.top.TopGeneralManager;

/**
 * Class for forum test
 * @author Zoli
 *
 */
@Category({ RightPanelTopSuite.class, CENTRAL_ADMIN_Suite.class, INSTITUTIONAL_ADMIN_Suite.class, AUTHOR_Suite.class })
@SuppressWarnings({ "PMD.JUnitTestsShouldIncludeAssert", "PMD.JUnitTestContainsTooManyAsserts" })
public class ForumTest extends ForumJunitTestClass {

	/**
	 * Create random forum
	 */
	@Test
	public final void testCreateForum() throws InterruptedException {
		TopGeneralManager.checkGeneralElementCreation(ForumTableAttributes.class);
	}

	/**
	 * Delete forum
	 */
	@Test
	public final void testDeleteForum() throws InterruptedException {
		TopGeneralManager.addElement(ForumTableAttributes.class);
		TopGeneralManager.checkGeneralElementDeletion(ForumTableAttributes.class);
	}

	/**
	 * TODO: Sorts forum elements by state
	 * 
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/2187">#2187</a>
	 */
}