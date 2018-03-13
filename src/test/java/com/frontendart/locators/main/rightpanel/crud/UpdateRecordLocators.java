package com.frontendart.locators.main.rightpanel.crud;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.general.GeneralLocatorTypes;

/**
 * Enum class for new record editor
 * 
 * @author Zoli
 *
 */
public enum UpdateRecordLocators implements GeneralLocatorTypes {
	EDIT_BUTTON("//a[starts-with(@data-qtip, 'Rekord szerkesztése')]", Arrays.asList("Szerkesztés", "Edit")),
	ROW_EDITOR("//div[starts-with(@id, 'roweditor')][starts-with(@class, 'x-panel ')]"),
	ROW_EDITOR_ALL_FIELDS(ROW_EDITOR + "//div[starts-with(@class, 'x-field ')]"),
	ROW_EDITOR_SAVE_BUTTON("(" + ROW_EDITOR + "/..//a)", Arrays.asList("Mentés", "Save"));

	private final String locator;
	List<String> names;

	/**
	 * Constructor
	 * 
	 * @param itemId
	 */
	private UpdateRecordLocators(final String locator) {
		this.locator = locator;
	}

	/**
	 * Constructor
	 * 
	 * @param itemId
	 * @param englishName
	 * @param hungarianName
	 */
	private UpdateRecordLocators(final String locator, final List<String> names) {
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
