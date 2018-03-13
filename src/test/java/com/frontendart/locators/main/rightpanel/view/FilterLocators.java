package com.frontendart.locators.main.rightpanel.view;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.general.GeneralLocatorTypes;

/**
 * Enum class for filter locators
 * 
 * @author Zoli
 *
 */
public enum FilterLocators implements GeneralLocatorTypes {
	RIGHT_PANEL("//div[starts-with(@id, 'rightpanel')][starts-with(@class, 'x-panel ')]"),
	FILTER_WIDGET(RIGHT_PANEL + "//*/div[starts-with(@id, 'filterwidget')][starts-with(@class, 'x-panel')]"),
	INPUT_FIELD(FILTER_WIDGET + "//*/input"),
	DELETE_BUTTON(FILTER_WIDGET + "//*/div[starts-with(@class, 'x-form-trigger ')]"),
	FILTER_BUTTON(FILTER_WIDGET + "//*/a", Arrays.asList("Szűrés", "Filter"));

	private final String locator;
	List<String> names;

	/**
	 * Constructor
	 * 
	 * @param itemId
	 */
	private FilterLocators(final String locator) {
		this.locator = locator;
	}

	/**
	 * Constructor
	 * 
	 * @param itemId
	 * @param englishName
	 * @param hungarianName
	 */
	private FilterLocators(final String locator, final List<String> names) {
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
