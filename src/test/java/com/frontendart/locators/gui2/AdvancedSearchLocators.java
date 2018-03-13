package com.frontendart.locators.gui2;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.general.GeneralLocatorTypes;

/**
 * Enum class for GUI2 advanced search locators
 * @author Zoli
 *
 */
public enum AdvancedSearchLocators implements GeneralLocatorTypes {
	ADVANCED_SEARCH("//*[@id='advancedSearch']", Arrays.asList("Részletes keresés", "Advanced search")),
	AUTHOR("//input[@id='author-input']", Arrays.asList("Szerző", "Author")),
	TITLE("//input[@id='title-input']", Arrays.asList("Cím", "Title")),
	FROMDATE("//input[@id='fromdate-input']", Arrays.asList("Kezdő év", "From")),
	TILLDATE("//input[@id='tilldate-input']", Arrays.asList("Záró év", "Till")),
	SUBMIT_BUTTON("//*[@class='submitButton submit']", Arrays.asList("Keresés", "Submit")),

	//TODO: here the hungarian and english names are not correct...
	ALL_LABELS("//*[@class='advancedSearchFormInner']/li[@id]", Arrays.asList("Szerző", "Author"));

	private final String locator;
	List<String> names;

	/**
	 * Constructor
	 * 
	 * @param itemId
	 */
	private AdvancedSearchLocators(final String locator) {
		this.locator = locator;
	}

	/**
	 * Constructor
	 * 
	 * @param itemId
	 * @param englishName
	 * @param hungarianName
	 */
	private AdvancedSearchLocators(final String locator, final List<String> names) {
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
