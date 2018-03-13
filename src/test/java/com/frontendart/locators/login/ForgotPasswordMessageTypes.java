package com.frontendart.locators.login;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.general.GeneralLocatorTypes;

/**
 * Enum class for forgot password messages
 * 
 * @author Zoli
 *
 */
public enum ForgotPasswordMessageTypes implements GeneralLocatorTypes {
	WRONG_CAPTCHA(Arrays.asList("Hibás captcha!", "Invalid captcha!")),
	INVALID_USERNAME(Arrays.asList("Hibás felhasználónév!", "Invalid username!")),
	SUCCESS(Arrays.asList("Az új jelszó generáló linkjét kiküldtük az email címére!",
			"Your password reset link has been sent to your email address!")),
	ADMIN(Arrays.asList("MTMT adminisztrátornak a jogkörben felette lévő admin küldhet jelszó-módosítót. Kérjük vegye fel a kapcsolatot az intézménye 4-es adminisztrátorával vagy valamely központi adminisztrátorral.", "MTMT admins' password recovery is initiated by a superior. Please contact your institute's admin4 or any central admin."));

	List<String> names;

	/**
	 * Constructor
	 * 
	 * @param itemId
	 * @param englishName
	 * @param hungarianName
	 */
	private ForgotPasswordMessageTypes(final List<String> names) {
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
