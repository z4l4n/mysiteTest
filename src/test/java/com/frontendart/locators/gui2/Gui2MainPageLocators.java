package com.frontendart.locators.gui2;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.general.GeneralLocatorTypes;

/**
 * Enum locator class for GUI2 main page locators
 * 
 * @author Zoli
 *
 */
public enum Gui2MainPageLocators implements GeneralLocatorTypes {	
	TITLE_ROW("//*[@class='fixed-title']/h3", Arrays.asList("Címsor", "Title row")),
	PUBLICATION_LIST("//*[@class='publication-list selectable']//li[contains(@class, 'list-item')]", Arrays.asList("Publikációs lista", "Publication list")),
	EMPTY_PUBLICATION_LIST("//*[@id='simpleList']//ul");

	private final String locator;
	List<String> names;

	/**
	 * Constructor
	 * 
	 * @param itemId
	 */
	private Gui2MainPageLocators(final String locator) {
		this.locator = locator;
	}

	/**
	 * Constructor
	 * 
	 * @param itemId
	 * @param englishName
	 * @param hungarianName
	 */
	private Gui2MainPageLocators(final String locator, final List<String> names) {
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