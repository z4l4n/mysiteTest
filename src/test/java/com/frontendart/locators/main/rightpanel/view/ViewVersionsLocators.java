package com.frontendart.locators.main.rightpanel.view;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.general.GeneralLocatorTypes;

/**
 * Enum class for locating the elements on the Versions page.
 * @author Zoli
 *
 */
public enum ViewVersionsLocators implements GeneralLocatorTypes {
	VERSIONS_BUTTON("//a[starts-with(@data-qtip, 'Rekord előző verziók') or starts-with(@data-qtip, 'Record earlier versions')]",
			Arrays.asList("Verziók", "Versions")),
	VERSIONS_WINDOW("//div[starts-with(@id, 'selectorwindow')][starts-with(@class, 'x-window ')]"),
	VERSIONS_WINDOW_GRIDPANEL(VERSIONS_WINDOW + "//div[starts-with(@id, 'contentfullgrid')][starts-with(@class, 'x-panel ')]"),
	VERSIONS_WINDOW_RESTORE_BUTTON("(" + VERSIONS_WINDOW + "//a)[1]", Arrays.asList("Visszaállít", "Restore")),
	VERSIONS_WINDOW_CLOSE_BUTTON(VERSIONS_WINDOW + "//span[text()='Bezár' or text()='Close']", Arrays.asList("Bezár", "Close")),
	VERSIONS_WINDOW_FILTER_FIELD(VERSIONS_WINDOW + "//input", Arrays.asList("Szűrő mező", "Filter field")),
	VERSIONS_WINDOW_FILTER_BUTTON(VERSIONS_WINDOW + "//span[text()='Szűrés']", Arrays.asList("Szűrés", "Filter")),
	ALL_ROWS(VERSIONS_WINDOW + "//div[starts-with(@id, 'contentfullgrid')]//table", Arrays.asList("Összes sor", "All rows")),
	FIRST_ROW(VERSIONS_WINDOW + "//div[starts-with(@id, 'contentfullgrid')]//table", Arrays.asList("Első sor", "First row"));

	private final String locator;
	List<String> names;

	/**
	 * Constructor
	 * 
	 * @param itemId
	 */
	private ViewVersionsLocators(final String locator) {
		this.locator = locator;
	}

	/**
	 * Constructor
	 * 
	 * @param itemId
	 * @param englishName
	 * @param hungarianName
	 */
	private ViewVersionsLocators(final String locator, final List<String> names) {
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
