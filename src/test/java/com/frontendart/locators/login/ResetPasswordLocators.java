package com.frontendart.locators.login;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.general.GeneralLocatorTypes;

/**
 * Reset password locators
 * 
 * @author Zoli
 *
 */
public enum ResetPasswordLocators implements GeneralLocatorTypes {
	RESET_PASSWORD_FORM("//*/div[starts-with(@id, 'form')][starts-with(@class, 'x-panel ')]"),
	USERNAME("(" + RESET_PASSWORD_FORM + "//*/input)[2]", Arrays.asList("Felhasználónév", "Username")),
	PASSWORD("(" + RESET_PASSWORD_FORM + "//*/input)[3]", Arrays.asList("Jelszó", "Password")),
	PASSWORD_AGAIN("(" + RESET_PASSWORD_FORM + "//*/input)[4]", Arrays.asList("Jelszó újra", "Password again")),
	RESET_PASSWORD_BUTTON(RESET_PASSWORD_FORM + "//*/span[text()='Küldés' or text()='Send']", Arrays.asList("Küldés", "Send"));

	private final String locator;
	List<String> names;

	/**
	 * Constructor
	 * 
	 * @param itemId
	 */
	private ResetPasswordLocators(final String locator) {
		this.locator = locator;
	}

	/**
	 * Constructor
	 * 
	 * @param itemId
	 * @param englishName
	 * @param hungarianName
	 */
	private ResetPasswordLocators(final String locator, final List<String> names) {
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
