package com.frontendart.managers.main.leftpanel;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.frontendart.common.Constants;
import com.frontendart.common.Utils;
import com.frontendart.locators.main.leftpanel.SavedListsLocators;
import com.frontendart.managers.general.GeneralSelectorBoxManager;

/**
 * Manager class for saved lists
 * 
 * TODO: refakt
 * 
 * @author Zoli
 *
 */
public class SavedListsManager {

	/**
	 * Clicks on Save button
	 * 
	 */
	public static void clickOnListButton() {
		Utils.waitForAndClickOnGeneralWebElement(SavedListsLocators.LIST_BUTTON);
	}

	/**
	 * Selects new list option from save menu
	 * 
	 */
	public static void selectNewListOption() {
		clickOnListButton();
		Utils.waitForAndClickOnGeneralWebElement(SavedListsLocators.MENU_NEW_LIST_OPTION);
	}

	/**
	 * Selects change existing list option from save menu
	 * 
	 */
	public static void selectAddToExistingListOption() {
		Utils.waitForAndClickOnGeneralWebElement(SavedListsLocators.MENU_ADD_TO_EXISTING_LIST_OPTION);
	}

	/**
	 * Selects change existing list option from save menu
	 * 
	 */
	public static void selectChangeExistingListOption() {
		Utils.waitForAndClickOnGeneralWebElement(SavedListsLocators.MENU_CHANGE_EXISTING_LIST_OPTION);
	}

	/**
	 * Writes given name to name field
	 * 
	 */
	public static void addThisNameToListName(final String name) {
		Utils.writeTextToThisField(name, SavedListsLocators.SAVED_LIST_WINDOW_INPUT_FIELD);
	}

	/**
	 * Clicks on Save button
	 * 
	 */
	public static void clickOnSaveListButton() {
		Utils.waitForAndClickOnGeneralWebElement(SavedListsLocators.SAVED_LIST_WINDOW_SAVE_LIST_BUTTON);
		Utils.defaultWait();
	}

	/**
	 * Clicks on Close button
	 * 
	 */
	public static void clickOnCloseButton() {
		Utils.waitForAndClickOnGeneralWebElement(SavedListsLocators.SAVED_LIST_WINDOW_CLOSE_BUTTON);
		Utils.defaultWait();
	}

	/**
	 * Saves current selection to  list
	 * 
	 * @return
	 */
	public static String saveCurrentSelectionToList() {
		selectNewListOption();
		final String name = Constants.PREFIX+Utils.randomString();
		addThisNameToListName(name);
		clickOnSaveListButton();

		return name;
	}

	/**
	 * Expand list panel
	 * 
	 */
	public static void expandListPanel() {
		if (isListPanelCollapsed()) {
			Utils.waitForAndClickOnGeneralWebElement(SavedListsLocators.EXPAND_BUTTON);
		}
		
//		Utils.waitForAndClickOnGeneralWebElement(SavedListsLocators.EXPAND_BUTTON);
	}

	/**
	 * Returns true if list panel is collapsed
	 * 
	 * 
	 * @return
	 */
	public static boolean isListPanelCollapsed() {
		return Utils.createGeneralWebElementFromEnum(SavedListsLocators.SAVED_LISTS_PANEL).getAttribute("class").contains("collapsed");
	}

	/**
	 * Returns the saved lists as a list of webElements.
	 * @return
	 * 
	 */
	public static List<WebElement> getSavedListsAsWebElement() {
		int size = Utils.createGeneralWebElementsFromEnum(SavedListsLocators.ALL_LISTS).size();
		return Utils.createGeneralWebElementsFromEnum(SavedListsLocators.ALL_LISTS);
	}

	/**
	 * Returns the saved lists names.
	 * @return
	 * 
	 */
	public static List<String> getSavedListsNamesAsString() {
		final List<String> myReportNames = new ArrayList<String>();

		for (final WebElement myReport : getSavedListsAsWebElement()) {
			myReportNames.add(myReport.findElement(By.xpath(SavedListsLocators.LIST_NAME.toString())).getText());
		}

		return myReportNames;
	}

	/**
	 * Finds list with the given name
	 * @return
	 */
	public static WebElement findThisListByName(final String name) {
		final int index = getSavedListsNamesAsString().indexOf(name);
		return getSavedListsAsWebElement().get(index);
	}

	/**
	 * opens list by name
	 * 
	 * 
	 * @param listName
	 * @return
	 */
	public static void openThisListByName(final String name) {
		expandListPanel();
		final WebElement myList = findThisListByName(name);
		openToggleOfThisList(myList);
		Utils.switchToActiveElement().findElement(By.xpath(SavedListsLocators.OPEN.toString())).click();
		Utils.defaultWait();
	}

	/**
	 * Open toggle of this list
	 * 
	 * @param myList
	 */
	public static void openToggleOfThisList(final WebElement myList) {
		final WebElement splitButton = myList.findElement(By.xpath(SavedListsLocators.LIST_TOGGLE.toString()));
		splitButton.sendKeys(Keys.DOWN);
	}

	/**
	 * 
	 * 
	 * @param listName
	 */
	public static void deleteThisListByName(final String name) {
		expandListPanel();
		final WebElement myList = findThisListByName(name);
		
		Utils.scrollIntoView(myList);
		
		openToggleOfThisList(myList);
		Utils.switchToActiveElement().findElement(By.xpath(SavedListsLocators.DELETE.toString())).click();
		Utils.defaultWait();
	}

	/**
	 * Returns list name after creating it
	 * 
	 * @return
	 */
	public static String createListAndNavigateToListPanel() {
		// Select random rows
		RecordSelectionManager.selectRandomRenderedRowsFromGridPanel();

		// Save them to list, navigate to the list, and open list panel
		final String listName = saveCurrentSelectionToList();
		expandListPanel();

		return listName;
	}

	/**
	 * changes existing list
	 * 
	 */
	public static void changeExistingList(final String name) {
		clickOnListButton();
		selectChangeExistingListOption();
		selectThisListFromSelector(name);
		
		WebElement listName = null;
		
		List<WebElement> savedListsNames = Utils.createGeneralWebElementsFromEnum(SavedListsLocators.SAVED_LIST_ADD_LIST_WINDOW_LIST_NAMES);
		for(int i = 0; i < savedListsNames.size(); i++){
			if(name.equals(savedListsNames.get(i).getText())){
				listName = savedListsNames.get(i);
			}
		}
		
		Utils.defaultWait();
		listName.click();
		Utils.defaultWait();
		
		Utils.waitForAndClickOnGeneralWebElement(SavedListsLocators.SAVED_LIST_ADD_LIST_WINDOW_TOOLBAR_SAVE_BUTTON);
		Utils.defaultWait();
		
		clickOnSaveListButton();
	}

	/**
	 * Selects saved list name from selector
	 * 
	 * 
	 * @param name
	 */
	public static void selectThisListFromSelector(final String name) {
		Utils.waitForAndClickOnGeneralWebElement(SavedListsLocators.SAVED_LIST_WINDOW_LIST_SELECTOR);
		Utils.defaultWait();
		GeneralSelectorBoxManager.selectThisFromSelectbox(name);
	}

	/**
	 * Adds to existing list
	 * 
	 * @param name
	 */
	public static void addToExistingList(final String name) {
		clickOnListButton();
		selectAddToExistingListOption();
		selectThisListFromSelector(name);
		
		//Utils.createGeneralWebElementFromEnum(SavedListsLocators.SAVED_LIST_ADD_LIST_WINDOW_INPUT_FIELD).sendKeys("ASD");
		
		WebElement listName = null;
		
		List<WebElement> savedListsNames = Utils.createGeneralWebElementsFromEnum(SavedListsLocators.SAVED_LIST_ADD_LIST_WINDOW_LIST_NAMES);
		for(int i = 0; i < savedListsNames.size(); i++){
			if(name.equals(savedListsNames.get(i).getText())){
				listName = savedListsNames.get(i);
			}
		}
		
		Utils.defaultWait();
		listName.click();
		Utils.defaultWait();
		
		Utils.waitForAndClickOnGeneralWebElement(SavedListsLocators.SAVED_LIST_ADD_LIST_WINDOW_TOOLBAR_SAVE_BUTTON);
		Utils.defaultWait();
		
		clickOnSaveListButton();
	}

	/**
	 * Writes to filter field
	 * 
	 * 
	 * @param text
	 */
	public static void filterWithText(final String text) {
		expandListPanel();
		Utils.writeTextToThisField(text, SavedListsLocators.FILTER_INPUT_FIELD);
		Utils.waitForAndClickOnGeneralWebElement(SavedListsLocators.FILTER_BUTTON);
		Utils.defaultWait();
	}

	/**
	 * clicks on filter clear (x) button
	 * 
	 */
	public static void clickOnFilterClearButton() {
		expandListPanel();
		Utils.waitForAndClickOnGeneralWebElement(SavedListsLocators.FILTER_CLEAR_BUTTON);
		Utils.defaultWait();
	}
}
