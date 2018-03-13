package com.frontendart.locators.gui2;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.general.GeneralLocatorTypes;

/**
 * Enum class for GUI2 author page locators 
 * 
 * @author Zoli
 *
 */
public enum AuthorPageLocators implements GeneralLocatorTypes {
	IMAGE("//img", Arrays.asList("Egy emberi figurát ábrázoló piktogram", "")),
	CAROUSEL_CONTROL_LEFT("//*[@id='infoCarousel']/a", Arrays.asList("Egy balra mutató nyíl (lapozó) a vonaldiagram mellett", "")),
	LINE_CHART("//*[@class='lineChart']", Arrays.asList("Vonal diagram a publikációk számának eloszlásával - évenkénti bontásban", "")),
	PIE_CHART("//*[@class='pieChart']", Arrays.asList("Torta diagram a publikációk típusának eloszlásával", "")),
	BAR_CHART("//*[@class='barChart']", Arrays.asList("Oszlop diagram az idézők számának eloszlásával", "")),
	CAROUSEL_CONTROL_RIGHT("//*[@id='infoCarousel']/a[2]", Arrays.asList("Egy jobbra mutató nyíl (lapozó) az oszlop diagram mellett", ""));

	private final String locator;
	List<String> names;

	/**
	 * Constructor
	 * 
	 * @param itemId
	 */
	private AuthorPageLocators(final String locator) {
		this.locator = locator;
	}

	/**
	 * Constructor
	 * 
	 * @param itemId
	 * @param englishName
	 * @param hungarianName
	 */
	private AuthorPageLocators(final String locator, final List<String> names) {
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