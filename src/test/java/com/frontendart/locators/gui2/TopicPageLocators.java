package com.frontendart.locators.gui2;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.general.GeneralLocatorTypes;

/**
 * Enum class for GUI2 topic page locators
 * 
 * @author Zoli
 *
 */
public enum TopicPageLocators implements GeneralLocatorTypes {
	TOP_INFO("//*[@class='top-info']", Arrays.asList("Egy információs sor, benne a témakör nevével.", ""));

	private final String locator;
	List<String> names;

	/**
	 * Constructor
	 * 
	 * @param itemId
	 * @param englishName
	 * @param hungarianName
	 */
	private TopicPageLocators(final String locator, final List<String> names) {
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