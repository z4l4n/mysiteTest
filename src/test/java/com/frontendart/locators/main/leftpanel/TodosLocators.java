package com.frontendart.locators.main.leftpanel;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.general.GeneralLocatorTypes;

/**
 * Enum class for saved lists locators
 * 
 * @author Zoli
 *
 */
public enum TodosLocators implements GeneralLocatorTypes {
	TODOS_PANEL("//div[starts-with(@id, 'workflowpanel-')][starts-with(@class, 'x-panel ')]"),
	EXPAND_BUTTON(TODOS_PANEL + "//div[contains(@class, 'x-tool-img')]", Arrays.asList("Kinyitás-Összecsukás", "Expand-Collapse")),
	
	//Idézés import
	CITATION_IMPORT(TODOS_PANEL + "//table[1]"),
	
	//Duplumok kezelése
	MANAGE_DUPLICATES(TODOS_PANEL + "//table[2]"),
	
	//Forrásközlemény import
	CORE_PUBLICATION_IMPORT(TODOS_PANEL + "//table[3]"),
	
	//Problémás idézések kezelése
	MANAGING_PROBLEMS_WITH_CITATIONS(TODOS_PANEL + "//table[4]"),
	MANAGING_PROBLEMS_WITH_CITATIONS_EXPAND(MANAGING_PROBLEMS_WITH_CITATIONS + "/tbody/tr[1]/td[1]/div/div", Arrays.asList("Problémás idézések kezelése kinyitás", "Managing problems with citations expand")),
	
	EXTERNAL_CITATION_NOT_SET("//div[text()='Nem jelölt függő, független idézők']", Arrays.asList("Nem jelölt függő, független idézők", "External citation not set")),
	FOREIGN_EDITION_FIELD_NOT_SET("//div[text()='Nem jelölt külföldi kiadású idézők']", Arrays.asList("Nem jelölt külföldi kiadású idézők", "Foreign edition field not set")),
	NATIONAL_ORIGIN_FIELD_NOT_SET("//div[text()='Nem jelölt hazai szerzős idézők']", Arrays.asList("Nem jelölt hazai szerzős idézők", "National origin field not set")),
	
	//Problémás közlemények kezelése
	MANAGING_PROBLEMS_WITH_PUBLICATIONS(TODOS_PANEL + "//table[5]"),
	MANAGING_PROBLEMS_WITH_PUBLICATIONS_EXPAND(MANAGING_PROBLEMS_WITH_PUBLICATIONS + "/tbody/tr[1]/td[1]/div/div", Arrays.asList("Problémás közlemények kezelése kinyitás", "Managing problems with publications expand")),
	
	ASSIGN_AUTHOR("//div[text()='Forrásközlemény nincs hozzárendelve szerzőhöz']", Arrays.asList("Forrásközlemény nincs hozzárendelve szerzőhöz", "Assign authors")),
	IMCOMPLETE_CORE_PUBLICATION_ENTRY("//div[text()='Forrásközlemény hiányos']", Arrays.asList("Forrásközlemény hiányos", "Incomplete core publication entry")),
	UNAPPRVOED_CORE_PUBLICATION("//div[text()='Forrásközlemény jóváhagyatlan']", Arrays.asList("Forrásközlemény jóváhagyatlan", "Unapproved core publication")),
	INCOMPLETE_CITING_PUBLICATION("//div[text()='Idézőközlemény hiányos']", Arrays.asList("Idézőközlemény hiányos", "Incomplete citing publication")),
	UNAPPROVED_CITING_PUBLICATION("//div[text()='Idézőközlemény jóváhagyatlan']", Arrays.asList("Idézőközlemény jóváhagyatlan", "Unapproved citing publication")),
	MISSING_AFFILIATION_FOR_AUTHOR("//div[text()='Affiliáció nincs megadva']", Arrays.asList("Affiliáció nincs megadva", "Missing affiliation for author")),
	
	//Regisztráció jóváhagyása
	REGISTRATION_APPROVAL(TODOS_PANEL + "//table[6]");
	

	private final String locator;
	List<String> names;

	/**
	 * Constructor
	 * 
	 * @param itemId
	 */
	private TodosLocators(final String locator) {
		this.locator = locator;
	}

	/**
	 * Constructor
	 * 
	 * @param itemId
	 * @param englishName
	 * @param hungarianName
	 */
	private TodosLocators(final String locator, final List<String> names) {
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
