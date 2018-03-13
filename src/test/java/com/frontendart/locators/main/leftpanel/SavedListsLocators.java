package com.frontendart.locators.main.leftpanel;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.general.GeneralLocatorTypes;

/**
 * Enum class for saved lists locators
 * 
 * @author Zoli
 *
 */
public enum SavedListsLocators implements GeneralLocatorTypes {
	LIST_BUTTON("//span[text()='Lista' or text()='List']", Arrays.asList("Lista", "List")),
	MENU_NEW_LIST_OPTION("//span[text()='Új lista' or text()='New list']", Arrays.asList("Új lista", "New list")),
	MENU_ADD_TO_EXISTING_LIST_OPTION("//span[text()='Listához hozzáadás' or text()='Add to list']", Arrays.asList("Listához hozzáadás", "Add to existing list")),
	MENU_CHANGE_EXISTING_LIST_OPTION("//span[text()='Lista lecserélése' or text()='Replace list']", Arrays.asList("Lista lecserélése", "Change list")),

	// Saved list window
	SAVED_LIST_WINDOW("//div[starts-with(@id, 'window')][starts-with(@class, 'x-window ')]"),
	SAVED_LIST_WINDOW_INPUT_FIELD(SAVED_LIST_WINDOW + "//input", Arrays.asList("Név", "Name")),
//	SAVED_LIST_WINDOW_INPUT_FIELD(SAVED_LIST_WINDOW + "//div[starts-with(@id, 'textfield-')]/input[starts-with(@id, 'textfield-')]", Arrays.asList("Név", "Name")),
	SAVED_LIST_WINDOW_LIST_SELECTOR(SAVED_LIST_WINDOW + "//div[contains(@id, 'trigger-search')]", Arrays.asList("Mentett lista", "Saved list")),
	SAVED_LIST_WINDOW_LIST_SELECTOR_OPTIONS("//li[contains(@class, 'boundlist-item')]", Arrays.asList("Mentett lista választó", "Saved list selector")),
	SAVED_LIST_WINDOW_CLOSE_BUTTON("(" + SAVED_LIST_WINDOW + "//a)[1]", Arrays.asList("Bezár", "Close")),
	SAVED_LIST_WINDOW_SAVE_LIST_BUTTON("(" + SAVED_LIST_WINDOW + "//a)[2]", Arrays.asList("Mentés", "Save")),
	
	//add list
	SAVED_LIST_ADD_LIST_WINDOW("//div[starts-with(@id, 'selectorbox-')][starts-with(@class, 'x-window ')]"),
	SAVED_LIST_ADD_LIST_WINDOW_INPUT_FIELD(SAVED_LIST_ADD_LIST_WINDOW + "//div[starts-with(@id, 'textfield-')]/input[starts-with(@id, 'textfield-')]", Arrays.asList("Név", "Name")),
	SAVED_LIST_ADD_LIST_WINDOW_LIST_CONTAINER(SAVED_LIST_ADD_LIST_WINDOW + "//div[(@class='x-grid-item-container')]"),
	SAVED_LIST_ADD_LIST_WINDOW_LIST(SAVED_LIST_ADD_LIST_WINDOW_LIST_CONTAINER + "//*/table"),
//	SAVED_LIST_ADD_LIST_WINDOW_LIST_NAMES("./tbody/tr/td/div"),
	SAVED_LIST_ADD_LIST_WINDOW_LIST_NAMES(SAVED_LIST_ADD_LIST_WINDOW_LIST_CONTAINER + "//table/tbody/tr/td/div"),
	SAVED_LIST_ADD_LIST_WINDOW_TOOLBAR(SAVED_LIST_ADD_LIST_WINDOW + "//div[contains(@class, 'x-toolbar-default-docked-bottom')]"),
	SAVED_LIST_ADD_LIST_WINDOW_TOOLBAR_SAVE_BUTTON(SAVED_LIST_ADD_LIST_WINDOW_TOOLBAR + "//span[text()='Mentés és bezárás' or text()='Save and close' or text()='Csatol & bezár' or text()='Connect & close']", Arrays.asList("Mentés és bezárás", "Save")),
	
	
	// Saved lists panel
//	SAVED_LISTS_PANEL("//div[starts-with(@id, 'namedlistspanel-')][starts-with(@class, 'x-panel ')]"),
	SAVED_LISTS_PANEL("//div[starts-with(@id, 'tabpanel-')][starts-with(@class, 'x-panel ')][2]"),
	EXPAND_BUTTON(SAVED_LISTS_PANEL + "//div[contains(@class, 'x-tool-img')]", Arrays.asList("Kinyitás-Összecsukás", "Expand-Collapse")),
	SAVED_LISTS_TABLE(SAVED_LISTS_PANEL + "//*/div[starts-with(@id, 'gridview-')]"),

	// Filter field, filter button and new query button
	FILTER_INPUT_FIELD(SAVED_LISTS_PANEL + "//div[starts-with(@id, 'filterwidget')]//*/input",
			Arrays.asList("Filter mező", "Filter field")),
	FILTER_CLEAR_BUTTON(SAVED_LISTS_PANEL + "//div[contains(@id, 'trigger-clear')]", Arrays.asList("Szűrés", "Filter")),
	FILTER_BUTTON(SAVED_LISTS_PANEL + "//a[@data-qtip='Rekordok szűkítése' or @data-qtip='Filter records']", Arrays.asList("Szűkítés", "Filter")),

	// Saved lists table
	ALL_LISTS(SAVED_LISTS_TABLE + "//*/table"),
	LIST_TOGGLE("./tbody/tr/td/div/a"),
	LIST_NAME("./tbody/tr/td[2]/div"),

	// Toggle options
	OPEN("./../../div[1]", Arrays.asList("Lista megnyitása", "Open list")),
	DELETE("./../../div[5]", Arrays.asList("Töröl", "Delete list"));

	private final String locator;
	List<String> names;

	/**
	 * Constructor
	 * 
	 * @param itemId
	 */
	private SavedListsLocators(final String locator) {
		this.locator = locator;
	}

	/**
	 * Constructor
	 * 
	 * @param itemId
	 * @param englishName
	 * @param hungarianName
	 */
	private SavedListsLocators(final String locator, final List<String> names) {
		this.locator = locator;
		this.names = names;
	}

	/**
	 * Returns the locator as string
	 */
	@Override
	public String toString() {
		return locator;
	}

	/**
	 * Returns names
	 */
	@Override
	public List<String> getNames() {
		return this.names;
	}
}
