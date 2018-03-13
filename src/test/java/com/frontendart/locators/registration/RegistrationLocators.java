package com.frontendart.locators.registration;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.general.GeneralLocatorTypes;

/**
 * Class for registration locators
 * 
 * @author Zoli
 *
 */
public enum RegistrationLocators implements GeneralLocatorTypes {
	LOGIN_MAIN_PANEL("//div[starts-with(@id, 'loginlogin')][starts-with(@class, 'x-panel ')]"),
	TABBAR(LOGIN_MAIN_PANEL + "//*/div[starts-with(@id, 'tabbar')][starts-with(@class, 'x-tab-bar ')]"),
	REGISTRATION_TABPANEL_HEADER("(" + TABBAR + "//*/a)[2]", Arrays.asList("Regisztráció", "Register")),
	REGISTRATION_TABPANEL("(//*/div[starts-with(@id, 'panel')][starts-with(@class, 'x-panel ')])"),
	REGISTRATION_FORM(REGISTRATION_TABPANEL + "//*/div[starts-with(@id, 'form')][starts-with(@class, 'x-panel ')]"),
	REGISTER_BUTTON(REGISTRATION_TABPANEL + "//a//span[text()='Regisztráció küldése' or text()='Send registration']",
			Arrays.asList("Regisztráció küldése", "Send registration")),

	// input fields
	LANGUAGE("(" + REGISTRATION_FORM + "//div[starts-with(@id, 'enumselectorcombobox')][starts-with(@class, 'x-field ')])[1]", Arrays.asList("Nyelv", "Language")),
	INSTITUTE("(" + REGISTRATION_FORM + "//div[starts-with(@id, 'recordselectorfield')][starts-with(@class, 'x-field ')])[1]", Arrays.asList("Munkahely", "Institute")),
	INSTITUTE_TEXT("(" + REGISTRATION_FORM + "//div[starts-with(@id, 'textfield')][starts-with(@class, 'x-field ')])[1]//*/input", Arrays.asList("Munkahely szövegesen", "Institute")),
	FAMILY_NAME("(" + REGISTRATION_FORM + "//div[starts-with(@id, 'textfield')][starts-with(@class, 'x-field ')])[2]//*/input", Arrays.asList("Családi név", "Family Name")),
	GIVEN_NAME("(" + REGISTRATION_FORM + "//div[starts-with(@id, 'textfield')][starts-with(@class, 'x-field ')])[3]//*/input", Arrays.asList("Utónév", "Given Name")),
	DEGREE("(" + REGISTRATION_FORM + "//div[starts-with(@id, 'recordselectorfield')][starts-with(@class, 'x-field ')])[2]", Arrays.asList("Tudományos fokozat", "Degree")),
	SPECIALITIES("(" + REGISTRATION_FORM + "//div[starts-with(@id, 'recordselectorlistfield')])[0]", Arrays.asList("Szakterület", "Specialities")),
	SPECIALITIES_PLUS_BUTTON("(" + REGISTRATION_FORM + "//div[starts-with(@id, 'recordselectorlistfield')]//div[@role='button'])[1]", Arrays.asList("Csatol", "Connect")),

//	SPECIALITIES_ADD_BUTTON("(" + REGISTRATION_FORM + "//div[@data-qtip='Témakörök keresése'])", Arrays.asList("Témakörök keresése", "Find subjects")),
//	SPECIALITIES_CHECKBOX("//table[starts-with(@id, 'treeview-')]/tbody/tr/td/div/div[2]", Arrays.asList("Témakör checkbox", "Subject checkbox")),
//	SPECIALITIES_SAVE_BUTTON("//a//span[text()='Mentés és bezárás' or text()='Save and close']", Arrays.asList("Mentés és bezárás", "Save and close")),
	
	SPECIALITIES_TEXT("(" + REGISTRATION_FORM + "//div[starts-with(@id, 'textfield')][starts-with(@class, 'x-field ')])[5]//*/input", Arrays.asList("Szakterület szövegese", "Specialities")),
	USERNAME("(" + REGISTRATION_FORM + "//div[starts-with(@id, 'usernameforpasswordfield')][starts-with(@class, 'x-field ')])[1]//*/input", Arrays.asList("Felhasználónév", "Username")),
	PASSWORD("(" + REGISTRATION_FORM + "//div[starts-with(@id, 'passwordfield')][starts-with(@class, 'x-field ')])[1]//*/input", Arrays.asList("Jelszó", "Password")),
	PASSWORD_AGAIN("(" + REGISTRATION_FORM + "//div[starts-with(@id, 'passwordfield2')][starts-with(@class, 'x-field ')])[1]//*/input", Arrays.asList("Jelszó újra", "Password again")),
	BIRTH_PLACE("(" + REGISTRATION_FORM + "//div[starts-with(@id, 'textfield')][starts-with(@class, 'x-field ')])[6]//*/input", Arrays.asList("Születési hely", "Birth Place")),
	BIRTH_DATE("(" + REGISTRATION_FORM + "//div[starts-with(@id, 'datefield')][starts-with(@class, 'x-field ')])[1]//*/input", Arrays.asList("Születési dátum", "Birth Date")),
	EMAIL("(" + REGISTRATION_FORM + "//div[starts-with(@id, 'textfield')][starts-with(@class, 'x-field ')])[7]//*/input", Arrays.asList("E-mail cím", "Email")),
	PHONE("(" + REGISTRATION_FORM + "//div[starts-with(@id, 'textfield')][starts-with(@class, 'x-field ')])[8]//*/input", Arrays.asList("Telefonszám", "Phone")),
	COMMENT("(" + REGISTRATION_FORM + "//div[starts-with(@id, 'textfield')][starts-with(@class, 'x-field ')])[9]//*/input", Arrays.asList("Megjegyzés", "Comment")),
	DATA_HANDLING_POLICY("//div[starts-with(@id, 'checkboxgroup')]//input",
			Arrays.asList("Elfogadom az adatkezelési nyilatkozatot", "Data Handling Policy Accepted")),
	CAPTCHA("(" + REGISTRATION_FORM + "//div[starts-with(@id, 'textfield')][starts-with(@class, 'x-field ')])[10]//*/input", Arrays.asList("Captcha", "Captcha"));

	private final String locator;
	List<String> names;

	/**
	 * Constructor
	 * 
	 * @param itemId
	 */
	private RegistrationLocators(final String locator) {
		this.locator = locator;
	}

	/**
	 * Constructor
	 * 
	 * @param itemId
	 * @param englishName
	 * @param hungarianName
	 */
	private RegistrationLocators(final String locator, final List<String> names) {
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
