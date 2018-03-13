package com.frontendart.locators.login;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.general.GeneralLocatorTypes;

/**
 * Enum class for login page locators.
 * @author Zoli
 *
 */
public enum LoginPageLocators implements GeneralLocatorTypes {
	// Login main
	START_PAGE("//div[@class='loading_mtmt']"),
	LOGIN_MAIN_PANEL("//div[starts-with(@id, 'loginlogin')][starts-with(@class, 'x-panel ')]"),
	TABBAR(LOGIN_MAIN_PANEL + "//*/div[starts-with(@id, 'tabbar')][starts-with(@class, 'x-tab-bar ')]"),

	// General buttons
	BOTTOM_TOOLBAR(LOGIN_MAIN_PANEL + "//*/div[starts-with(@id, 'toolbar')][starts-with(@class, 'x-toolbar ')]"),
	HELP_BUTTON("(" + BOTTOM_TOOLBAR + "//*/a)[3]", Arrays.asList("Súgó", "Help")),
	HELPDESK_BUTTON("(" + BOTTOM_TOOLBAR + "//*/a)[4]", Arrays.asList("Helpdesk", "Helpdesk")),
	PUBLIC_SEARCH_BUTTON("(" + BOTTOM_TOOLBAR + "//*/a)[5]", Arrays.asList("Nyilvános keresés", "Public search")),

	// Language change
	LANGUAGE_CHANGE("(" + BOTTOM_TOOLBAR + "//*/a)[6]", Arrays.asList("Nyelvválasztó (Magyar)", "Language picker (English)")),
	LANGUAGE_CHANGE_MENU("//div[starts-with(@id, 'menu')][starts-with(@class, 'x-menu ')]"),
	LANGUAGE_CHANGE_HUNGARIAN("//span[text()='Magyar']", Arrays.asList("Nyelvválasztó (Magyar)", "Language picker (Hungarian)")),
	LANGUAGE_CHANGE_ENGLISH("//span[text()='English']", Arrays.asList("Nyelvválasztó (Angol)", "Language picker (English)")),
	LANGUAGE_CHANGE_FIRST_OPTION("(" + LANGUAGE_CHANGE_MENU + "//*/a)[1]", Arrays.asList("Magyar", "Hungarian")),
	LANGUAGE_CHANGE_SECOND_OPTION("(" + LANGUAGE_CHANGE_MENU + "//*/a)[2]", Arrays.asList("Angol", "English")),

	// Login tab locators
	LOGIN_TABPANEL_HEADER("(" + TABBAR + "//*/a)[1]", Arrays.asList("Belépés", "Login")),
	LOGIN_TABPANEL("(//*/div[starts-with(@id, 'tabpanel')][starts-with(@class, 'x-panel ')])[1]", Arrays.asList("Belépési oldal", "Login tabpanel")),
	LOGIN_FORM(LOGIN_TABPANEL + "//form"),
	USERNAME_TEXTFIELD(LOGIN_FORM + "//input[@name='username']", Arrays.asList("Felhasználónév mező", "Username field")),
	PASSWORD_TEXTFIELD(LOGIN_FORM + "//input[@name='password']", Arrays.asList("Jelszó", "Password")),
	LOGIN_BUTTON("(" + LOGIN_TABPANEL + "//a)[5]", Arrays.asList("Belépés", "Login"));
//	LOGIN_BUTTON("((" + LOGIN_TABPANEL + "//a)[5])//span[1]", Arrays.asList("Belépés", "Login"));

	private final String locator;
	List<String> names;

	/**
	 * Constructor
	 * 
	 * @param itemId
	 */
	private LoginPageLocators(final String locator) {
		this.locator = locator;
	}

	/**
	 * Constructor
	 * 
	 * @param itemId
	 * @param englishName
	 * @param hungarianName
	 */
	private LoginPageLocators(final String locator, final List<String> names) {
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
