package com.frontendart.locators.main.rightpanel.top;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.general.GeneralLocatorTypes;

/**
 * Enum class for change user
 * 
 * @author Zoli
 *
 */
public enum ChangeUserLocators implements GeneralLocatorTypes {
	LOGIN_AS_WINDOW("//div[starts-with(@id, 'loginaswindow')][starts-with(@class, 'x-window ')]"),
	LOGIN_AS_WINDOW_LOGIN(LOGIN_AS_WINDOW + "//span[text()='Átjelentkezés...' or text()='Log in as...']", Arrays.asList("Átjelentkezés", "Login")),
	LOGIN_AS_WINDOW_CLOSE(LOGIN_AS_WINDOW + "//span[text()='Bezár' or text()='Close']", Arrays.asList("Bezár", "Close")),
	LOGIN_AS_WINDOW_FILTER_FIELD(LOGIN_AS_WINDOW + "//input", Arrays.asList("Szűrőmező", "Filter field")),
	LOGIN_AS_WINDOW_FILTER_BUTTON(LOGIN_AS_WINDOW + "//span[text()='Szűkítés' or text()='Filter']", Arrays.asList("Szűkítés", "Filter")),
	LOGIN_AS_WINDOW_FIRST_MATCH(LOGIN_AS_WINDOW + "//div[starts-with(@id, 'toolbarcontainerpanel')]//div[starts-with(@class, 'x-grid-item-container')]//table[1]/tbody/tr/td/div", Arrays.asList("Első találat", "First match")),
	LOGIN_AS_WINDOW_NO_RESULTS(LOGIN_AS_WINDOW + "//div[(@class='x-grid-empty')]", Arrays.asList("Nincs találat", "No results")),
	
	
	CHANGE("//span[starts-with(@id, 'menuitem')][text()='Átjelentkezés...' or text()='Log in as...']", Arrays.asList("Átjelentkezés...", "Login as")),
	CHANGE_BACK("//span[text()='Visszajelentkezés...']", Arrays.asList("Visszajelentkezés", "Login back")), ;

	private final String locator;
	List<String> names;

	/**
	 * Constructor
	 * 
	 * @param itemId
	 */
	private ChangeUserLocators(final String locator) {
		this.locator = locator;
	}

	/**
	 * Constructor
	 * 
	 * @param itemId
	 * @param englishName
	 * @param hungarianName
	 */
	private ChangeUserLocators(final List<String> names) {
		this.locator = "";
		this.names = names;
	}

	/**
	 * Constructor
	 * 
	 * @param itemId
	 * @param englishName
	 * @param hungarianName
	 */
	private ChangeUserLocators(final String locator, final List<String> names) {
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
