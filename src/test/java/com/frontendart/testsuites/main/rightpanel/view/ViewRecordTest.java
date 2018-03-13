package com.frontendart.testsuites.main.rightpanel.view;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;

import com.frontendart.categories.AUTHOR_Suite;
import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.INSTITUTIONAL_ADMIN_Suite;
import com.frontendart.categories.RightPanelViewSuite;
import com.frontendart.common.Constants;
import com.frontendart.common.TestConfiguration;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.JunitTestClass;
import com.frontendart.locators.main.rightpanel.view.ToolbarLocators;
import com.frontendart.locators.records.attributes.general.GeneralRecordTypes;
import com.frontendart.managers.general.GeneralTableManager;
import com.frontendart.managers.login.LoginManager;
import com.frontendart.managers.login.LogoutManager;
import com.frontendart.managers.main.leftpanel.RecordSelectionManager;
import com.frontendart.managers.main.leftpanel.SearchManager;
import com.frontendart.managers.main.rightpanel.view.ChangeViewManager;

/**
 * Parameterized test class for record view. 
 * @author Zoli
 *
 */
@Category({ RightPanelViewSuite.class, AUTHOR_Suite.class, INSTITUTIONAL_ADMIN_Suite.class, CENTRAL_ADMIN_Suite.class })
@SuppressWarnings("PMD.JUnitTestContainsTooManyAsserts")
public class ViewRecordTest extends JunitTestClass {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LogManager.getLogger("ViewRecordTest");

	/**
	 * Simple grid table - disable column
	 */
	@Test
	public final void testDisableGridColumn() {
		Utils.writeMyRedmineIssues("#1362");

		RecordSelectionManager.selectRandomRecordTypeFromSelector();
		SearchManager.createAndRunEmptyQuery();
		ChangeViewManager.switchToGridView();

		// Select random header from visible grid table headers 
		List<String> visibleHeaders = GeneralTableManager.getAllVisibleHeadersText();
		//(index 0 is "Sorszám", which cannot be disabled)
		final String randomHeader = visibleHeaders.get(Utils.randInt(1, visibleHeaders.size() - 1));
		LOGGER.info("A következő oszlop láthatóságát szeretnénk állítani: " + randomHeader);

		// Disable header, and validate
		GeneralTableManager.changeTheVisibilityOfThisHeader(randomHeader);
		visibleHeaders = GeneralTableManager.getAllVisibleHeadersText();
		assertFalse("This header should not be visible: " + randomHeader, visibleHeaders.contains(randomHeader));

		// Enable the header again, and validate
		GeneralTableManager.changeTheVisibilityOfThisHeader(randomHeader);
		visibleHeaders = GeneralTableManager.getAllVisibleHeadersText();
		assertTrue("This header should be visible: " + randomHeader, visibleHeaders.contains(randomHeader));
	}

	/**
	 * Test create new view
	 */
	@Test
	public final void testCreateNewView() {
		final GeneralRecordTypes selectedRecordType = RecordSelectionManager.selectRandomRecordTypeFromSelector();
		ChangeViewManager.switchToGridView();

		// Select header from visible grid table headers (not random)
		final List<String> oldVisibleHeaders = GeneralTableManager.getAllVisibleHeadersText();
		final String randomHeader = oldVisibleHeaders.get(1);

		// Disable header
		GeneralTableManager.changeTheVisibilityOfThisHeader(randomHeader);

		// Save view as new, and set as default
		Utils.waitForAndClickOnGeneralWebElement(ToolbarLocators.CHANGE_VIEW_GRID);
		Utils.waitForAndClickOnGeneralWebElement(ToolbarLocators.CUSTOM_VIEW_MENU_NEW);
		final String nameOfView = Constants.PREFIX+Utils.randomString();
		Utils.writeTextToThisField(nameOfView, ToolbarLocators.CUSTOM_VIEW_WINDOW_NAME_FIELD);
		Utils.waitForAndClickOnGeneralWebElement(ToolbarLocators.CUSTOM_VIEW_WINDOW_DEFAULT_CHECKBOX);
		Utils.waitForAndClickOnGeneralWebElement(ToolbarLocators.CUSTOM_VIEW_WINDOW_SAVE_BUTTON);
		Utils.defaultWait();

		// Logout, login and validate
		LogoutManager.logout();
		LoginManager.loginSuccessfullyWithThisRole(TestConfiguration.role);
		RecordSelectionManager.selectThisRecordTypeFromSelector(selectedRecordType);
		final String gridButtonName = Utils.createGeneralWebElementFromEnum(ToolbarLocators.CHANGE_VIEW_GRID).findElement(By.xpath(".//span")).getText();
		assertTrue("The default view is not correct!", gridButtonName.contains(nameOfView));
		final List<String> newVisibleHeaders = GeneralTableManager.getAllVisibleHeadersText();
		assertFalse("The count of the headers is not correct!", newVisibleHeaders.contains(randomHeader));
		//assertEquals("The count of the headers is not correct!", oldVisibleHeaders.size() - 1, newVisibleHeaders.size());

		// Cleanup
		Utils.waitForAndClickOnGeneralWebElement(ToolbarLocators.CHANGE_VIEW_GRID);
		Utils.waitForAndClickOnGeneralWebElement(ToolbarLocators.CUSTOM_VIEW_MENU_DELETE);
		Utils.waitForMessageBoxPresentAndAccept();
		Utils.waitForMessageBoxPresentAndAccept();

	}

	// FURTHER TESTS TO BE IMPLEMENTED

	/**
	 * Test left panel button (random select one)
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1446">#1446</a>
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1513">#1513</a>
	 * 
	 * TODO: reimplement
	 */

}
