package com.frontendart.locators.login;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.general.GeneralLocatorTypes;

/**
 * Enum class for login message types
 * 
 * @author Zoli
 *
 */
public enum LoginMessageTypes implements GeneralLocatorTypes {
	EMPTY_FIELD(Arrays.asList("Kérjük töltse ki a bejelentkezési mezőket!", "Please complete the login form!")),
	INCORRECT_FIELD(Arrays.asList("Nem sikerült a lekérés a szerverről.\nHelytelen felhasználónév vagy jelszó! (hibakód: 403)", "Incorrect username or password!")),
	ERROR(Arrays.asList("Belépési hiba. Kérjük próbálja újra!", "Login failure. Try again!")),
	NO_MESSAGE_ERROR(Arrays.asList("Nem sikerült a lekérés a szerverről.\nNo message available (hibakód: 500)", "")),
	TRY_AGAIN_LATER(Arrays.asList("Helytelen felhasználónév vagy jelszó, várjon 5 percet, vagy kérjen új jelszót!",
			"Incorrect username or password, you will have to wait 5 minutes to try again, or have to get a new password!"));

	List<String> names;

	/**
	 * Constructor
	 * 
	 * @param itemId
	 * @param englishName
	 * @param hungarianName
	 */
	private LoginMessageTypes(final List<String> names) {
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
