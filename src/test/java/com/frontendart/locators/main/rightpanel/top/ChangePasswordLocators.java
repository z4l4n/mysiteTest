package com.frontendart.locators.main.rightpanel.top;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.general.GeneralLocatorTypes;

/**
 * Enum class for change password
 * 
 * @author Zoli
 *
 */
public enum ChangePasswordLocators implements GeneralLocatorTypes {
	MODEL_EDIT_WINDOW("//div[starts-with(@id, 'modeleditorwindow')][starts-with(@class, 'x-window ')]"),
	MODEL_EDIT_WINDOW_SAVE(MODEL_EDIT_WINDOW + "//span[text()='Mentés' or text()='Save']", Arrays.asList("Mentés", "Save")),
	USERNAME_SEARCH_TRIGGER(MODEL_EDIT_WINDOW + "//div[contains(@id, 'trigger-search')]", Arrays.asList("Felhasználó keresés ikon", "Username search icon")),
	PASSWORD_FIELD(MODEL_EDIT_WINDOW + "//input[starts-with(@id, 'passwordfield-')]", Arrays.asList("Új jelszó", "New password")),
	PASSWORD_AGAIN_FIELD(MODEL_EDIT_WINDOW + "//input[starts-with(@id, 'passwordfield2-')]", Arrays.asList("Új jelszó újra", "New password again")),
	MODEL_EDIT_WINDOW_CANCEL_BUTTON(MODEL_EDIT_WINDOW + "//a[contains(@class, 'x-btn-default-toolbar-small')]//span", Arrays.asList("Mégse", "Cancel")),

	USERNAME_WINDOW("//div[starts-with(@class, 'x-window ')][contains(@data-componentid, 'selectorbox-')]"),
	USERNAME_FILTER_FIELD("//div[starts-with(@id, 'selectorbox')]//input", Arrays.asList("Szűrőmező", "Filter field")),
	USERNAME_FILTER_BUTTON("//div[starts-with(@id, 'selectorbox')]//span[text()='Szűkítés' or text()='Filter']", Arrays.asList("Szűrés", "Filter")),
	USERNAME_FILTER_SAVE("//div[starts-with(@id, 'selectorbox')]//span[text()='Mentés és bezárás' or text()='Save and close' or text()='Csatol & bezár' or text()='Connect & close']", Arrays.asList("Mentés és bezárás", "Save and close")),
	USERNAME_FILTER_FIRST_MATCH("//div[starts-with(@id, 'selectorbox')]//div[starts-with(@class, 'x-grid-item-container')]//table[1]/tbody/tr/td/div", Arrays.asList("Első találat", "First match")),
	USERNAME_FILTER_NO_RESULTS("//div[(@class='x-grid-empty')]", Arrays.asList("Nincs találat", "No Results")),
	USERNAME_FILTER_CLOSE_BUTTON(USERNAME_WINDOW + "//a[contains(@class, 'x-btn-default-toolbar-small')]//span", Arrays.asList("Bezárás", "Close")),
//	USERNAME_FILTER_CLOSE_BUTTON(USERNAME_WINDOW + "//div[2]//div[2]//div[1]//div[1]//a//span", Arrays.asList("Bezárás", "Close")),
	
	SUCCESSFUL_MESSAGE(Arrays.asList("A jelszó megváltozott!", "Password was changed!")),

	// TODO: remove from here
	ADMIN_FORUM_TAB("//span[contains(text(), 'Admin fórum') or contains(text(), 'Admin forum')]", Arrays.asList("Admin fórum", "Admin forum")),
	INSTADMIN_FORUM_TAB("//span[text()='Intézményi fórum' or text()='Institute forum']", Arrays.asList("Intézményi fórum", "Admin forum")),

	// TODO: remove from here
	ERROR_WINDOW_AFTER_LOGIN("//div[starts-with(@id, 'window')]//div[starts-with(@id, 'title')][text()='Figyelem']", Arrays.asList("Figyelem", "Attention")),
	ERROR_WINDOW_CLOSE_BUTTON(ERROR_WINDOW_AFTER_LOGIN + "//../../div[2]/img", Arrays.asList("Bezár", "Close")),
	SECOND_ERROR_WINDOW_CLOSE_BUTTON("(" + ERROR_WINDOW_AFTER_LOGIN + ")[2]//../..//img", Arrays.asList("Bezár", "Close")),

	ERROR_WINDOW("//div[starts-with(@id, 'title')][text()='Figyelem']", Arrays.asList("Figyelem", "Attention")),
	ERROR_WINDOW_REFRESH_BUTTON(ERROR_WINDOW + "//../../../../..//span[text()='Újratöltés']", Arrays.asList("Újratöltés", "Reload")),
	ERROR_WINDOW_CONTINUE_BUTTON(ERROR_WINDOW + "//../../../../..//span[text()='Folytatás']", Arrays.asList("Folytatás", "Continue"));

	private final String locator;
	List<String> names;

	/**
	 * Constructor
	 * 
	 * @param itemId
	 */
	private ChangePasswordLocators(final String locator) {
		this.locator = locator;
	}

	/**
	 * Constructor
	 * 
	 * @param itemId
	 * @param englishName
	 * @param hungarianName
	 */
	private ChangePasswordLocators(final List<String> names) {
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
	private ChangePasswordLocators(final String locator, final List<String> names) {
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
