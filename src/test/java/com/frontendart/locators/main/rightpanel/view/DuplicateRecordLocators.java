package com.frontendart.locators.main.rightpanel.view;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.general.GeneralLocatorTypes;

/**
 * Enum class for new record editor
 * 
 * @author Zoli
 *
 */
public enum DuplicateRecordLocators implements GeneralLocatorTypes {
	
	
	DUPLICATE_OK_BUTTON("//span[text()='OK']", Arrays.asList("Duplikálás", "Duplicate")),
	
//	DUPLICATE_BUTTON("//a[starts-with(@data-qtip, 'Tétel másolása') or "
//			+ "starts-with(@data-qtip, 'Copy')]", Arrays.asList("Másolás", "Copy")),
//	DUPLICATE_BUTTON("//span[starts-with(@id, 'duplicatebutton-') and contains(@id, '-btnWrap')]", Arrays.asList("Duplikálás", "Copy")),
	DUPLICATE_BUTTON("//span[text()='Duplikálás' or text()='Duplicate']", Arrays.asList("Duplikálás", "Duplicate")),
	SEARCH_DUPLICATES_BUTTON("//a[starts-with(@data-qtip, 'Rekord duplikátumok')]", Arrays.asList("Duplumkeresés", "Search duplicates")),
	MARK_AS_DUPLUM_BUTTON("//a[starts-with(@data-qtip, 'A kiválasztott elemek megjelölése duplumként')]", Arrays.asList("Dupl.jelöl", "")),
	SELECTOR_WINDOW("//div[starts-with(@id, 'selectorwindow')][starts-with(@class, 'x-window ')]", Arrays.asList("Duplumkereső ablak", "Duplum search window")),
	CLOSE_BUTTON("(" + SELECTOR_WINDOW + "//a)[13]", Arrays.asList("Bezár", "Close")),
	
	DUPLUM_RESOLVER_WINDOW("//div[contains(@id, 'duplumresolverwindow-') and contains(@class, 'x-window ')]", Arrays.asList("", "")),
	DUPLUM_RESOLVER_WINDOW_CLOSE_BUTTON(DUPLUM_RESOLVER_WINDOW + "//div[contains(@class, 'x-tool-close ')]", Arrays.asList("Bezár", "Close"));

	private final String locator;
	List<String> names;

	/**
	 * Constructor
	 * 
	 * @param itemId
	 */
	private DuplicateRecordLocators(final String locator) {
		this.locator = locator;
	}

	/**
	 * Constructor
	 * 
	 * @param itemId
	 * @param englishName
	 * @param hungarianName
	 */
	private DuplicateRecordLocators(final String locator, final List<String> names) {
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
