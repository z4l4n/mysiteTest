package com.frontendart.locators.main;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.general.GeneralLocatorTypes;

/**
 * Locator class for main page locators
 * @author Zoli
 *
 */
public enum MainPageLocators implements GeneralLocatorTypes {
	// Login tabpanel
	LOGIN_TABPANEL("(//*/div[starts-with(@id, 'panel')][starts-with(@class, 'x-panel ')])[1]", Arrays.asList("Belépési oldal", "Login tabpanel")),

	// Left panel elements
	LEFT_PANEL("//div[starts-with(@id, 'leftpanel')][starts-with(@class, 'x-panel ')]"),
	HELP_BUTTON(LEFT_PANEL + "//a[@data-qtip='Súgó' or @data-qtip='Help']", Arrays.asList("?", "?")),
	HELP_WINDOW("//div[starts-with(@id, 'helpwindow')][starts-with(@class, 'x-window ')]",
			Arrays.asList("Help ablak", "Help window")),
	HELP_WINDOW_TICKET_LINK("//li//a[text()='Cédulák' or text()='Tickets']",
			Arrays.asList("Help ablak cédulák szakasz", "Help window ticket section")),
	HELP_WINDOW_CLOSE(HELP_WINDOW + "//div[contains(@class, 'x-tool-close')]",
			Arrays.asList("Help ablak bezárás", "Help window close")),
	HELP_WINDOW_HEADER(HELP_WINDOW + "/div/div/div/div/div",
			Arrays.asList("Help ablak fejléc", "Help ablak header")),
	HELP_WINDOW_TABLE_OF_CONTENTS("id('zero')",
			Arrays.asList("Help ablak tartalomjegyzék", "Help window table of contents")),

	// Language-change and refresh button
	CHANGE_LANGUAGE_BUTTON("(" + LEFT_PANEL + "//a)[3]", Arrays.asList("Nyelv választó", "Change language")),
	CHANGE_LANGUAGE_HUNGARIAN("//span[starts-with(@id, 'menucheckitem')][text()='Magyar']", Arrays.asList("Magyar", "Hungarian")),
	CHANGE_LANGUAGE_ENGLISH("//span[starts-with(@id, 'menucheckitem')][text()='English']", Arrays.asList("Angol", "English")),
	REFRESH_BUTTON("(" + LEFT_PANEL + "//a)[4]", Arrays.asList("Frissítés", "Refresh")),

	// Right panel elements
	RIGHT_PANEL("//div[starts-with(@id, 'rightpanel')][starts-with(@class, 'x-panel ')]"),
	RIGHT_TOP_PANEL(RIGHT_PANEL + "//*/div[starts-with(@class, 'x-panel customSeparator')]"),
	ABOUT_BUTTON("(" + RIGHT_TOP_PANEL + "//*/a)[1]", Arrays.asList("Névjegy", "About")),
	DATE_LABEL("(" + RIGHT_TOP_PANEL + "//*/a)[1]", Arrays.asList("Az aktuális dátum", "Date")),
	SYSTEM_MESSAGES_BUTTON("(" + RIGHT_TOP_PANEL + "//*/a)[2]", Arrays.asList("Rendszerüzenetek", "System messages")),
	FORUM_BUTTON("(" + RIGHT_TOP_PANEL + "//*/a)[3]", Arrays.asList("Fórum", "Forum")),
	MAIL_MESSAGES_BUTTON("(" + RIGHT_TOP_PANEL + "//*/a)[4]", Arrays.asList("Üzenetek", "Messages")),
	TICKETS_BUTTON("(" + RIGHT_TOP_PANEL + "//*/a)[5]", Arrays.asList("Cédulák", "Tickets")),
	MY_PROFILE_BUTTON("(" + RIGHT_TOP_PANEL + "//*/a)[6]",
			Arrays.asList("A bejelentkezett felhasználó nevét tartalmazó gomb", "A bejelentkezett felhasználó nevét tartalmazó gomb")),
	MY_PROFILE_MENU("//div[starts-with(@id, 'menu')][starts-with(@class, 'x-menu x-layer')]"),
	MY_PROFILE_EDIT_OPTION("//span[text()='Saját adatok szerkesztése' or text()='Edit own data']",
			Arrays.asList("Saját adatok szerkesztése", "Edit own data")),
	MY_PROFILE_PASSWORD_CHANGE_OPTION("//span[(text()='Jelszó változtatás' or text()='Change password')]",
			Arrays.asList("Jelszó változtatás", "Change password")),
	LOGGED_IN_TOOLTIP("//div[starts-with(@class, 'x-tip ')]", Arrays.asList("Felhasználónév tooltip", "Username tooltip")),
	LOGOUT_BUTTON("(" + RIGHT_TOP_PANEL + "//*/a)[7]", Arrays.asList("Kijelentkezés", "Logout")),

	// Right panel grid table elements
	CONTENT_GRID_PANEL("//div[starts-with(@id, 'contentfullgrid')][starts-with(@class, 'x-panel ')]", Arrays.asList("Táblázat", "Grid")),
	CONTENT_GRID_PANEL_HEADER_CONTAINER(CONTENT_GRID_PANEL + "//*/div[starts-with(@class, 'x-grid-header-ct ')]"),
	CONTENT_GRID_PANEL_ALL_HEADERS(CONTENT_GRID_PANEL + "//*/div[starts-with(@class, 'x-column-header ')]", Arrays.asList("Táblázat fejléc", "Table header")),
	GRID_PANEL_HEADERS_MENU("(//div[starts-with(@id, 'menu')][starts-with(@class, 'x-menu ')])[2]"),
	GRID_PANEL_HEADERS_MENU_OPTIONS(GRID_PANEL_HEADERS_MENU + "//a"),
	GRID_PANEL_HEADERS_MENU_OPTIONS_COLUMN("//a//span[text()='Oszlopok' or text()='Columns']"),
	GRID_PANEL_HEADERS_COLUMNS_MENU("//div[starts-with(@id, 'menu')][starts-with(@class, 'x-menu ')][last()]//a"),
	GRID_PANEL_HEADERS_COLUMNS_MENU_OPTIONS("//div[starts-with(@id, 'menu')][starts-with(@class, 'x-menu ')][last()]//a//span"),
	CONTENT_GRID_PANEL_ALL_ROWS(CONTENT_GRID_PANEL + "//*/table"),
	CONTENT_GRID_PANEL_FIRST_ROW("(" + CONTENT_GRID_PANEL + "//*/table)[1]", Arrays.asList("Első sor", "First row")),
	GRID_ELEMENTS_ON_PAGE("//div[starts-with(@id, 'toolbarcontainerpanel')]//*/div[starts-with(@id, 'gridview')]//*/table", Arrays.asList("", "")),
	

	// Icon and list view
	CONTENT_ICON_GRID_PANEL("//div[starts-with(@id, 'contenticongrid')][starts-with(@class, 'x-panel ')]", Arrays.asList("Ikon", "Icon")),
	CONTENT_ICON_GRID_PANEL_ALL_ELEMENTS(CONTENT_ICON_GRID_PANEL + "//*/table"),
	CONTENT_LIST_GRID_PANEL("//div[starts-with(@id, 'contentlistgrid')][starts-with(@class, 'x-panel ')]", Arrays.asList("Lista", "List")),
	CONTENT_LIST_GRID_PANEL_ALL_ROWS(CONTENT_LIST_GRID_PANEL + "//*/table"),

	// User data panel - TODO: move from here
	USER_DATA_PANEL("//div[starts-with(@id, 'modeleditorwindow')][starts-with(@class, 'x-window ')]",
			Arrays.asList("Felhasználói panel", "UserDataPanel")),

	// About window - TODO: move from here
	ABOUT_WINDOW("//div[starts-with(@id, 'aboutwindow')][starts-with(@class, 'x-window ')]",
			Arrays.asList("Névjegy panel", "About window")),
	ABOUT_WINDOW_CLOSE(ABOUT_WINDOW + "//div[contains(@class, 'x-tool-img')]",
			Arrays.asList("Névjegy panel bezárás", "About window close")),

	// News window
	NEWS_WINDOW("//div[starts-with(@id, 'newswindow')][starts-with(@class, 'x-window ')]", Arrays.asList("Hírek", "News")),
	NEWS_WINDOW_CLOSE(NEWS_WINDOW + "//div[contains(@class, 'x-tool-close')]", Arrays.asList("Bezár", "Close")),

	// Editor window and form - TODO: move from here 
	MODEL_EDIT_WINDOW("//div[starts-with(@id, 'modeleditorwindow')][starts-with(@class, 'x-window ')]"),
	MODEL_EDIT_FORM(MODEL_EDIT_WINDOW + "//*/div[starts-with(@id, 'modeleditform')][starts-with(@class, 'x-panel ')]"),
	MODEL_EDIT_WINDOW_SAVE(MODEL_EDIT_WINDOW + "//span[text()='Mentés']", Arrays.asList("Mentés", "Save")), ;

	private final String locator;
	List<String> names;

	/**
	 * Constructor
	 * 
	 * @param itemId
	 */
	private MainPageLocators(final String locator) {
		this.locator = locator;
	}

	/**
	 * Constructor
	 * 
	 * @param itemId
	 * @param englishName
	 * @param hungarianName
	 */
	private MainPageLocators(final String locator, final List<String> names) {
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
