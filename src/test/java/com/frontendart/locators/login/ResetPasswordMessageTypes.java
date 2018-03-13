package com.frontendart.locators.login;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.general.GeneralLocatorTypes;

/**
 * Enum class for reset password message types
 * 
 * @author Zoli
 *
 */
public enum ResetPasswordMessageTypes implements GeneralLocatorTypes {
	EMPTY_FIELD(Arrays.asList("Néhány szükséges mezőt nem helyesen töltött ki!", "")),
	WEAK_PASSWORD(Arrays.asList("A jelszó túl gyenge!", "")),
	INCORRECT_FIELD(Arrays.asList("Néhány szükséges mezőt nem helyesen töltött ki!", "")),
	SUCCESS(Arrays.asList("Átirányítjuk a kezdőoldalra, ahol a felhasználónevével és az új jelszavával beléphet az MTMT rendszerébe.", "You will be redirected to the login page. You can login from there with your username and with your new password.")),
	WRONG_LINK(Arrays.asList("Hibás azonosító adatok! A felhasználónév vagy a jelszóvisszanyerő link hibás, vagy a jelszóvisszanyerő linket már felhasználták!",
			"Wrong token!"));

	List<String> names;

	/**
	 * Constructor
	 * 
	 * @param itemId
	 * @param englishName
	 * @param hungarianName
	 */
	private ResetPasswordMessageTypes(final List<String> names) {
		this.names = names;
	}

	/**
	 * Returns names
	 */
	@Override
	public List<String> getNames() {
		return this.names;
	}
}
