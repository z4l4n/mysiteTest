package com.frontendart.testsuites.main.rightpanel.top;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.frontendart.categories.AUTHOR_Suite;
import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.CoreSuite;
import com.frontendart.categories.INSTITUTIONAL_ADMIN_Suite;
import com.frontendart.categories.RightPanelTopSuite;
import com.frontendart.common.Roles;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.JunitTestClass;
import com.frontendart.locators.main.rightpanel.crud.RecordEditorLocators;
import com.frontendart.locators.main.rightpanel.top.UserProfileTableAttributes;
import com.frontendart.managers.main.rightpanel.top.UserDataManager;

/**
 * Class for user data test
 * @author Zoli
 *
 */
@Category({ RightPanelTopSuite.class, AUTHOR_Suite.class, INSTITUTIONAL_ADMIN_Suite.class, CENTRAL_ADMIN_Suite.class })
public class UserProfileTest extends JunitTestClass {

	/**
	 * Select my profile
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1137">#1137</a>
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1138">#1138</a>
	 */
	@Test
	@Category(CoreSuite.class)
	public final void testMyProfileWindowIsPresent() throws InterruptedException {
		Utils.writeMyRedmineIssues("#1137#1138");

		// Click on my username button
		UserDataManager.openProfileEditor();

		assertTrue("The profile window is not visible.", Utils.isThisElementVisible(RecordEditorLocators.EDITOR_WINDOW));
		RecordEditorLocators.checkAttributesAreVisible(UserProfileTableAttributes.class);

		// TODO: redmine issue: 1137

		// TODO: redmine issue: 1138

		// Close
		Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.CANCEL_BUTTON);
	}

	/**
	 * Edit my profile
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1126">#1126</a>
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1200">#1200</a>
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1358">#1358</a>
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1427">#1427</a>
	 * 
	 * TODO: review
	 */
	@Test
	public final void testEditMyProfile() throws InterruptedException {
		Utils.writeMyRedmineIssues("#1126#1200#1358#1427");

		// Click on my username button
		UserDataManager.openProfileEditor();

		if(Utils.getActualRole().equals(Roles.AUTHOR)){
			UserDataManager.fillRequiredDatasWhenRoleIsAuthor();
		}
		
		// Edit
		assertTrue("Error while editing user profile!", UserDataManager.editRandomField());
	}

	// FURTHER TESTS TO BE IMPLEMENTED

	/**
	 * Test left panel slider
	 */
}
