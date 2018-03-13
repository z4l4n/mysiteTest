package com.frontendart.testsuites.main.leftpanel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebElement;

import com.frontendart.categories.AUTHOR_Suite;
import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.INSTITUTIONAL_ADMIN_Suite;
import com.frontendart.categories.LeftPanelSuite;
import com.frontendart.common.Constants;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.SelectRandomRecordTypeJunitTestClass;
import com.frontendart.managers.main.leftpanel.RecordSelectionManager;
import com.frontendart.managers.main.leftpanel.SavedListsManager;
import com.frontendart.managers.main.leftpanel.SearchManager;

/**
 * Test class for saved list tests
 * @author Zoli
 *
 */
@Category({ LeftPanelSuite.class, AUTHOR_Suite.class, INSTITUTIONAL_ADMIN_Suite.class, CENTRAL_ADMIN_Suite.class })
@SuppressWarnings("PMD.JUnitTestContainsTooManyAsserts")
public class SavedListsTest extends SelectRandomRecordTypeJunitTestClass {

	/**
	 * Select random rows, and save them to a new saved list
	 */
	@Test
	public final void testCreateNewSavedList() {
		// Select random rows
		final List<WebElement> selectedRows = RecordSelectionManager.selectRandomRenderedRowsFromGridPanel();

		// Save them to list, navigate to the list, and open it
		final String name = SavedListsManager.saveCurrentSelectionToList();
		SavedListsManager.openThisListByName(name);

		// Validate row number
		assertEquals("The displayed size of the rows is not correct!", selectedRows.size(), RecordSelectionManager.gridTableFullSize());

		// Validate list name
		assertTrue("There should be a list with this name: " + name,
				SavedListsManager.getSavedListsNamesAsString().contains(name));

		// Cleanup
		SavedListsManager.deleteThisListByName(name);
	}

	/**
	 * Check deleting a saved list.
	 */
	@Test
	public final void testDeleteNewSavedList() {
		// Create
		final String name = SavedListsManager.createListAndNavigateToListPanel();

		// Get list number before delete, then remove list.
		final int listsNumberBefore = SavedListsManager.getSavedListsAsWebElement().size();
		SavedListsManager.deleteThisListByName(name);

		// Get list number after delete, and validate
		final int listsNumberAfter = SavedListsManager.getSavedListsAsWebElement().size();
		assertEquals("There should be one saved list less.", listsNumberBefore - 1, listsNumberAfter);

		// Validate list name
		assertFalse("There should not be a list with this name: " + name,
				SavedListsManager.getSavedListsNamesAsString().contains(name));

	}

	/**
	 * Check changing an existing list
	 */
	@Test
	public final void testChangeExistingList() {
		
		// Select random rows, and create list
		RecordSelectionManager.selectRandomRenderedRowsFromGridPanel();
		final String name = SavedListsManager.saveCurrentSelectionToList();

		// Select random rows and change existing list
		final List<WebElement> newSelectedRows = RecordSelectionManager.selectFromGridPanelWithShift();
		SavedListsManager.changeExistingList(name);

		// Validate: open list, and check row number
		SavedListsManager.openThisListByName(name);
		
		System.out.println("newSelectedRows.size()"+newSelectedRows.size());
		System.out.println("RecordSelectionManager.gridTableFullSize()"+RecordSelectionManager.gridTableFullSize());
		
		assertEquals("The displayed size of the rows is not correct!", newSelectedRows.size(), RecordSelectionManager.gridTableFullSize());

		// Cleanup
		SavedListsManager.deleteThisListByName(name);
	}

	/**
	 * Check add to an existing list
	 */
	@Test
	public final void testAddToExistingList() {
		// Select random rows, and create list
		final List<WebElement> selectedRows = RecordSelectionManager.selectRandomRenderedRowsFromGridPanel();
		final String name = SavedListsManager.saveCurrentSelectionToList();

		// Select random rows and change existing list
		RecordSelectionManager.selectFromGridPanelWithShift();
		SavedListsManager.addToExistingList(name);

		// Validate: open list, and check row number
		SavedListsManager.openThisListByName(name);
		assertTrue("The displayed size of the rows is not correct!", RecordSelectionManager.gridTableFullSize() >= selectedRows.size());

		// Cleanup
		SavedListsManager.deleteThisListByName(name);
	}

	/**
	 * Check add to an existing list - do not select any list
	 * 
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1761">#1761</a>
	 */
	@Test
	public final void testAddToExistingListWithoutSelectingAnyList() {
		Utils.writeMyRedmineIssues("#1761");

		// Select random rows, and create list
		RecordSelectionManager.selectRandomRenderedRowsFromGridPanel();
		final String name = SavedListsManager.saveCurrentSelectionToList();
		Utils.defaultWait();

		// Select random rows and change existing list
		RecordSelectionManager.selectFromGridPanelWithShift();
		SavedListsManager.clickOnListButton();
		SavedListsManager.selectAddToExistingListOption();
		SavedListsManager.clickOnSaveListButton();

		// Validate
		assertTrue("There should be a messagebox present!", Utils.acceptMessageBoxIfVisible());
		SavedListsManager.clickOnCloseButton();

		// Cleanup
		SavedListsManager.deleteThisListByName(name);
	}

	/**
	 * Check filtering
	 */
	@Test
	public final void testFilterSavedListsPanel() {
		// Create
		final String name = SavedListsManager.createListAndNavigateToListPanel();

		// List number before
		final int listsNumberBefore = SavedListsManager.getSavedListsAsWebElement().size();

		// Filter with the created name and validate
		SavedListsManager.filterWithText(name);
		assertEquals("There should be one item present!", 1, SavedListsManager.getSavedListsAsWebElement().size());
		assertTrue("There should be a list with this name: " + name,
				SavedListsManager.getSavedListsNamesAsString().contains(name));

		// Filter with random string and validate
		SavedListsManager.filterWithText(Utils.randomString());
		assertEquals("There should be no items present.", 0, SavedListsManager.getSavedListsAsWebElement().size());
		assertFalse("There should not be a list with this name: " + name,
				SavedListsManager.getSavedListsNamesAsString().contains(name));

		// Filter with empty string and validate
		SavedListsManager.clickOnFilterClearButton();
		assertEquals("All items should be present!", listsNumberBefore, SavedListsManager.getSavedListsAsWebElement().size());
		assertTrue("There should be a list with this name: " + name,
				SavedListsManager.getSavedListsNamesAsString().contains(name));

		// Cleanup
		SavedListsManager.deleteThisListByName(name);
	}
}
