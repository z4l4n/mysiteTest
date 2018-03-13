package com.frontendart.locators.gui2;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.general.GeneralLocatorTypes;

/**
 * Enum class for GUI2 search panel locators
 * 
 * @author Zoli
 *
 */
public enum SearchPanelLocators implements GeneralLocatorTypes {
	SEARCH_MODE_SELECT("//button[@id='searchModeSelect']", Arrays.asList("Keresés rekordválasztó", "")),
	SEARCH_MODE_OPTIONS(SEARCH_MODE_SELECT + "/..//a", Arrays.asList("Keresés rekordválasztó opciók", "")),
	SEARCH_BOX("//input[@id='searchbox']", Arrays.asList("Keresőmező", "Search box")),
	SEARCH_BUTTON("//button[@id='searchButton']", Arrays.asList("Keresés", "Search")),
	FILTER_BOX("//*[@class='filterBox']/li", Arrays.asList("", "")),
	ADVANCED_SEARCH("//button[@id='advancedSearch']", Arrays.asList("Részletes keresés", "Advanced search")),
	RESULT_NUMBER("//*[@class='search-result']", Arrays.asList("", "")),
	RESULTS("//*[@class='publication-list selectable']//li[contains(@class, 'list-item')]", Arrays.asList("", "")),
	RESULT_HEADER(".//h4/a", Arrays.asList("", "")),
	LEFT_SEARCH_FIELD("//*[@id='blSearch']", Arrays.asList("Bal oldali kereső", "Left panel search")),
	ALL_VISIBLE_ELEMENTS_ON_LEFT_PANEL("//ul[@class='active']/li[not(contains(@style, 'display: none'))]/ul[@class='selectable']/li[contains(@class, 'shown')]");

	private final String locator;
	List<String> names;

	/**
	 * Constructor
	 * 
	 * @param itemId
	 */
	private SearchPanelLocators(final String locator) {
		this.locator = locator;
	}

	/**
	 * Constructor
	 * 
	 * @param itemId
	 * @param englishName
	 * @param hungarianName
	 */
	private SearchPanelLocators(final String locator, final List<String> names) {
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
