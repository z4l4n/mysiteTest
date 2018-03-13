package com.frontendart.locators.main.leftpanel;

import java.util.List;

import com.frontendart.locators.general.GeneralLocatorTypes;

/**
 * Enum class for record selection locators
 * @author Zoli
 *
 */
public enum RecordSelectionLocators implements GeneralLocatorTypes {
	RIGHT_PANEL("//div[starts-with(@id, 'rightpanel')][starts-with(@class, 'x-panel ')]"),
	RIGHT_PANEL_TOOLBAR(RIGHT_PANEL + "//div[starts-with(@id,'toolbar')][starts-with(@class, 'x-toolbar ')]"),
	INFO_BAR("(" + RIGHT_PANEL_TOOLBAR + "//*/table)[1]"),
	INFO_BAR_NUMBERS("(" + RIGHT_PANEL_TOOLBAR + "//*/table//*/div[@data-ref='innerCt'])[2]"),
	LEFT_PANEL("//div[starts-with(@id, 'leftpanel')][starts-with(@class, 'x-panel ')]"),
	RECORD_SELECTOR_CONTAINER("(" + LEFT_PANEL + "//div[starts-with(@id, 'container')][starts-with(@class, 'x-container ')])[2]"),
	RECORD_SELECTOR_BUTTONS(RECORD_SELECTOR_CONTAINER + "//a[not(contains(@id, 'cycle'))]"),
	RECORD_SELECTOR("(//div[starts-with(@id, 'leftpanel')]//*/a[starts-with(@id, 'cycle')])[1]"),
	RECORD_SELECTOR_MENU("//div[starts-with(@id, 'menu-')][contains(@class, 'cycle')]"),
	RECORD_SELECTOR_MENU_OPTIONS(RECORD_SELECTOR_MENU + "//*/span[starts-with(@id, 'menucheckitem-')]"),
	RECORD_SELECTOR_BEFORE_SCROLLER("//div[starts-with(@id, 'menu')]//*/div[contains(@id, 'before-scroller')]"),
	RECORD_SELECTOR_AFTER_SCROLLER("//div[starts-with(@id, 'menu')]//*/div[contains(@id, 'after-scroller')]"),
	RECORD_SELECTION_SELECT_ROW("./tbody/tr/td/div");

	private final String locator;

	/**
	 * Constructor
	 * 
	 * @param itemId
	 */
	private RecordSelectionLocators(final String locator) {
		this.locator = locator;
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
		return null;
	}
}
