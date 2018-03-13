package com.frontendart.locators.gui2;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.general.GeneralLocatorTypes;

/**
 * Enum class for GUI2 publication details locators
 * 
 * @author Zoli
 *
 */
public enum PublicationDetailsLocators implements GeneralLocatorTypes {
	TITLE_ROW("//*[@class='fixed-title']/h3", Arrays.asList("Cím sor", "Title row")),
	TITLE("//*[@class='top-info']/div/div[2]", Arrays.asList("Cím", "Title")),
	AUTHORS("//*[@class='top-info']/a", Arrays.asList("Szerzők", "Authors")),
	OLD_ID("//div[@class='id']", Arrays.asList("Régi MTMT azonosító", "Old MTMT id")),

	// Title and id on new page
	TITLE_ON_NEW_PAGE("//*[@class='pcim']", Arrays.asList("Cím", "Title")),
	OLD_ID_ROW_ON_NEW_PAGE("//*[@class='ptipus']", Arrays.asList("Régi MTMT azonosító", "Old MTMT id")), ;

	private final String locator;
	List<String> names;

	/**
	 * Constructor
	 * 
	 * @param itemId
	 */
	private PublicationDetailsLocators(final String locator) {
		this.locator = locator;
	}

	/**
	 * Constructor
	 * 
	 * @param itemId
	 * @param englishName
	 * @param hungarianName
	 */
	private PublicationDetailsLocators(final String locator, final List<String> names) {
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