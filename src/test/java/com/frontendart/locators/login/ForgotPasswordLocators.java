package com.frontendart.locators.login;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.general.GeneralLocatorTypes;

/**
 * Forgot password locators
 * 
 * @author Zoli
 *
 */
public enum ForgotPasswordLocators implements GeneralLocatorTypes {
	LOGIN_MAIN_PANEL("//div[starts-with(@id, 'loginlogin')][starts-with(@class, 'x-panel ')]"),
	TABBAR(LOGIN_MAIN_PANEL + "//*/div[starts-with(@id, 'tabbar')][starts-with(@class, 'x-tab-bar ')]"),
	FORGOT_PASSWORD_TABPANEL_HEADER("(" + TABBAR + "//*/a)[3]", Arrays.asList("Elfelejtett jelszó", "Forgot password")),
	FORGOT_PASSWORD_TABPANEL("//*/div[starts-with(@id, 'tabpanel')][starts-with(@class, 'x-panel ')]"),
	FORGOT_PASSWORD_FORM(FORGOT_PASSWORD_TABPANEL + "//*/div[starts-with(@id, 'form')][starts-with(@class, 'x-panel ')]"),
	FORGOT_PASSWORD_USERNAME_FIELD("(" + FORGOT_PASSWORD_FORM + "//*/input)[2]", Arrays.asList("Felhasználónév", "Username")),
	FORGOT_PASSWORD_CAPTCHA_FIELD("(" + FORGOT_PASSWORD_FORM + "//*/input)[3]", Arrays.asList("Captcha", "Captcha")),
	RESET_CAPTCHA_BUTTON("(" + FORGOT_PASSWORD_TABPANEL + "//*/a)[6]", Arrays.asList("Új captcha", "New captcha")),
	FORGOT_PASSWORD_BUTTON("(" + FORGOT_PASSWORD_TABPANEL + "//*/a)[7]", Arrays.asList("Elfelejtett jelszó küldése", "Send forgot password"));

	private final String locator;
	List<String> names;

	/**
	 * Constructor
	 * @param locator
	 */
	private ForgotPasswordLocators(final String locator) {
		this.locator = locator;
	}

	/**
	 * Constructor
	 * @param type
	 */
	private ForgotPasswordLocators(final String type, final List<String> names) {
		this.locator = type;
		this.names = names;
	}

	/**
	 * Returns locator as string
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
