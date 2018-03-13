package com.frontendart.locators.gui2;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.general.GeneralLocatorTypes;

/**
 * Enum class for GUI2 left panel locators
 * 
 * @author Zoli
 *
 */
public enum LeftPanelLocators implements GeneralLocatorTypes {
	LEFT_COLUMN("//section[@id='left_column']", Arrays.asList("Bal oldali panel", "Left panel")),
	ALL_TAB_LABELS(LEFT_COLUMN + "//*/h2", Arrays.asList("Bal oldali panel feliratok", "Left panel tab labels")),
	INSTITUTES_TAB(LEFT_COLUMN + "//h2[@title='Intézmény']", Arrays.asList("Intézmény tab", "Institute tab")),
	AUTHORS_TAB(LEFT_COLUMN + "//h2[@title='Szerző']", Arrays.asList("Szerző tab", "Author tab")),
	TOPICS_TAB(LEFT_COLUMN + "//h2[@title='Témakör']", Arrays.asList("Témakör tab", "Topic tab")),
	SEARCH_FIELD_INPUT("//input[@id='blSearch']", Arrays.asList("Bal oldali keresőmező", "Left side search field")),
	LIST_CONTAINER(LEFT_COLUMN + "//div[2]/ul[@class='active']", Arrays.asList("Találati lista", "List container")),
	ALL_DISPLAYED_INSTITUTES("//*[@class='bl-list']/ul[1]/li/ul/li", Arrays.asList("Megjelenített intézmények", "Displayed institutes")),
	ALL_DISPLAYED_AUTHORS("//*[@class='bl-list']/ul[2]/li/ul/li[not(contains(@class, 'moreitemLink closed'))]", Arrays.asList("Megjelenített szerzők", "Displayed authors")),
	ALL_DISPLAYED_TOPICS("//*[@class='bl-list']/ul[3]/li/ul/li[not(contains(@class, 'moreitemLink closed'))]", Arrays.asList("Megjelenített témakörök", "Displayed topics")),
	ALL_VISIBLE_ELEMENTS_ON_LEFT_PANEL("//ul[@class='active']/li[not(contains(@style, 'display: none'))]/ul[@class='selectable']/li[not(contains(@class, 'moreitemLink closed'))]");

	private final String locator;
	List<String> names;

	/**
	 * Constructor
	 * 
	 * @param itemId
	 */
	private LeftPanelLocators(final String locator) {
		this.locator = locator;
	}

	/**
	 * Constructor
	 * 
	 * @param itemId
	 * @param englishName
	 * @param hungarianName
	 */
	private LeftPanelLocators(final String locator, final List<String> names) {
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
