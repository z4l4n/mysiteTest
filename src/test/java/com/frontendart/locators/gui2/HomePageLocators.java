package com.frontendart.locators.gui2;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.general.GeneralLocatorTypes;

/**
 * Enum locator class for GUI2 home page locators
 * 
 * @author Zoli
 *
 */
public enum HomePageLocators implements GeneralLocatorTypes {
	MTMT_LOGO("//*[@class='navbar navbar-inverse navbar-fixed-top']/div/div[1]", Arrays.asList("MTMT-logo", "MTMT-logo")),
	PANEL_TOGGLE("//*[@class='navbar-toggle opened']", Arrays.asList("Panel váltó", "Panel toggle")),
	SEARCH_CONTAINER("//*[@class='navbar navbar-inverse navbar-fixed-top']/div/div[2]", Arrays.asList("Keresés", "Search")),
	LANGUAGE_SELECTOR("//*[@id='langSelector']", Arrays.asList("Nyelvválasztó", "Language selector")),
	LEFT_COLUMN("//*[@id='left_column']", Arrays.asList("Bal oldali panel", "Left side panel")),
	NAVBAR_TOGGLE("//*[@data-toggle='collapse']", Arrays.asList("Bal panel eltüntető gomb", "Hide left panel button")),
	ADVANCED_SEARCH("//*[@id='advancedSearch']", Arrays.asList("Részletes keresés", "Advanced search"));

	private final String locator;
	List<String> names;

	/**
	 * Constructor
	 * 
	 * @param itemId
	 */
	private HomePageLocators(final String locator) {
		this.locator = locator;
	}

	/**
	 * Constructor
	 * 
	 * @param itemId
	 * @param englishName
	 * @param hungarianName
	 */
	private HomePageLocators(final String locator, final List<String> names) {
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