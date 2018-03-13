package com.frontendart.locators.main.rightpanel.view;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.general.GeneralLocatorTypes;

/**
 * Enum class for search toolbar locators
 * @author Zoli
 *
 */
public enum ToolbarLocators implements GeneralLocatorTypes {
	// Toolbar
	RIGHT_PANEL("//div[starts-with(@id, 'rightpanel')][starts-with(@class, 'x-panel ')]"),
	RIGHT_PANEL_TOOLBAR(RIGHT_PANEL + "//div[starts-with(@id,'toolbar')][starts-with(@class, 'x-toolbar ')]"),
	INFO_BAR("(" + RIGHT_PANEL_TOOLBAR + "//*/table)[1]"),
	INFO_BAR_QUERY_NAME("(" + RIGHT_PANEL_TOOLBAR + "//*/table//*/div[@data-ref='innerCt'])[1]"),
	INFO_BAR_NUMBERS("(" + RIGHT_PANEL_TOOLBAR + "//*/table//*/div[@data-ref='innerCt'])[2]"),

	// Buttons
	SELECT_ALL_BUTTON(RIGHT_PANEL_TOOLBAR + "//a[starts-with(@data-qtip, 'Összes kijelölés be') or "
			+ "starts-with(@data-qtip, 'Select all')]", Arrays.asList("Mind", "All")),
	DESELECT_ALL_BUTTON(RIGHT_PANEL_TOOLBAR + "//a[starts-with(@data-qtip, 'Összes kijelölés ki') or "
			+ "starts-with(@data-qtip, 'Deselect all')]", Arrays.asList("Semmi", "None")),
	@Deprecated
	CHANGE_LOCK_BUTTON(RIGHT_PANEL_TOOLBAR + "//a[starts-with(@data-qtip, 'Zárolási műveletek') or "
			+ "starts-with(@data-qtip, 'Lock operations')]", Arrays.asList("Zárol/felold", "Unlocked")),
	CHANGE_STATE_INPUT(RIGHT_PANEL_TOOLBAR + "//input[starts-with(@id, 'changestatecombobox')]", Arrays.asList("Rekord státusz állító", "Change status dropdown")),
	CHANGE_STATE_OPTIONS("//div[starts-with(@id, 'boundlist-')]/div/ul/li"),
	NEW_BUTTON(RIGHT_PANEL_TOOLBAR + "//a[starts-with(@data-qtip, 'Új rekord felvitele') or "
			+ "starts-with(@data-qtip, 'Add new record')]", Arrays.asList("Új", "New")),
	SAVE_BUTTON(RIGHT_PANEL_TOOLBAR + "//a[starts-with(@data-qtip, 'Exportálás, vagy mentés listaként')]", Arrays.asList("Mentés", "Save")),
	EDIT_BUTTON(RIGHT_PANEL_TOOLBAR + "//a[starts-with(@data-qtip, 'Rekord szerkesztése') or "
			+ "starts-with(@data-qtip, 'Edit record')]", Arrays.asList("Szerkesztés", "Edit")),
	IMPORT_BUTTON(RIGHT_PANEL_TOOLBAR + "//a[starts-with(@data-qtip, 'Egyéni import műveletek') or "
			+ "starts-with(@data-qtip, 'Custom import operations')]", Arrays.asList("Import", "Import")),
//	DUPLICATE_BUTTON(RIGHT_PANEL_TOOLBAR + "//a[starts-with(@data-qtip, 'Tétel másolása') or "
//			+ "starts-with(@data-qtip, 'Copy item')]", Arrays.asList("Másolás", "Copy")),
//	DUPLICATE_BUTTON("//span[starts-with(@id, 'duplicatebutton-') and contains(@id, '-btnWrap')]", Arrays.asList("Másolás", "Copy")),
	
	OPERATIONS(RIGHT_PANEL_TOOLBAR + "//a[starts-with(@data-qtip, 'Egyéb műveletek') or "
			+ "starts-with(@data-qtip, 'Other operations')]", Arrays.asList("Műveletek", "Operations")),
	OPERATIONS_CITATION_MENU("//span[text()='Idézések jelölése' or text()='Check citations']", Arrays.asList("Idézések jelölése", "Check citations")),
	OPERATIONS_SELF_CITATION_MENU("//span[text()='Önidézés jelölése' or text()='Set self citation']", Arrays.asList("Önidézés jelölése", "Set self citation")),
	
	DELETE_BUTTON(RIGHT_PANEL_TOOLBAR + "//a[starts-with(@data-qtip, 'Rekord törlése') or "
			+ "starts-with(@data-qtip, 'Remove record')]", Arrays.asList("Törlés", "Remove")),
	RESTORE_BUTTON(RIGHT_PANEL_TOOLBAR + "//a[starts-with(@data-qtip, 'Törölt elemek visszaállítása') or "
			+ "starts-with(@data-qtip, 'Restore deleted records')]", Arrays.asList("Visszaállítás", "Undelete")),
	NEW_TICKET_BUTTON(RIGHT_PANEL_TOOLBAR + "//a[starts-with(@data-qtip, 'Rekordhoz tartozó cédulák') or "
			+ "contains(@data-qtip, 'View record')]", Arrays.asList("Cédulák", "Tickets")),
	VERSIONS_BUTTON(RIGHT_PANEL_TOOLBAR + "//a[starts-with(@data-qtip, 'Rekord előző verziók') or "
			+ "starts-with(@data-qtip, 'Record earlier versions')]", Arrays.asList("Verziók", "Versions")),
	CITATIONS_BUTTON(RIGHT_PANEL_TOOLBAR + "//a[starts-with(@data-qtip, 'Rekordra mutató hivatkozások megtekintése') or "
			+ "contains(@data-qtip, 'citations')]", Arrays.asList("Idézések", "Citations")),
	DUPLUM_OPERATIONS_BUTTON(RIGHT_PANEL_TOOLBAR + "//a[starts-with(@data-qtip, 'Duplum műveletek') or "
			+ "starts-with(@data-qtip, 'Duplum operations')]", Arrays.asList("Duplumok", "Search duplicates")),

	// TODO: remove these from here...
	CHANGE_VIEW_ALL(RIGHT_PANEL_TOOLBAR + "//div[contains(@id, 'segmented')]", Arrays.asList("Nézet választó", "Change view")),
	CHANGE_VIEW_GRID(CHANGE_VIEW_ALL + "//a[1]", Arrays.asList("Táblázat nézet", "Grid view")),
	CHANGE_VIEW_LISTGRID(CHANGE_VIEW_ALL + "//a[2]", Arrays.asList("Lista nézet", "List view")),
	CHANGE_VIEW_ICON(CHANGE_VIEW_ALL + "//a[3]", Arrays.asList("Ikon nézet", "Icon view")),

	// Create custom view locators
	CUSTOM_VIEW_MENU("//div[starts-with(@id, 'menu-')][starts-with(@class, 'x-menu')]"),
	CUSTOM_VIEW_MENU_DELETE(CUSTOM_VIEW_MENU + "//span[contains(text(), ' - Töröl') or "
			+ "contains(text(), ' - Delete')]", Arrays.asList("Töröl", "Delete")),
	CUSTOM_VIEW_MENU_ALL_COLUMNS(CUSTOM_VIEW_MENU + "//span[text()='Összes oszlop' or "
			+ "text()='All columns']", Arrays.asList("Összes oszlop", "All columns")),
	CUSTOM_VIEW_MENU_NEW(CUSTOM_VIEW_MENU + "//span[text()='Új...' or text()='New...']", Arrays.asList("Új...", "New...")),
	CUSTOM_VIEW_WINDOW("//div[starts-with(@id, 'columnsettingwindow')]"),
	CUSTOM_VIEW_WINDOW_NAME_FIELD(CUSTOM_VIEW_WINDOW + "//input[starts-with(@id, 'textfield')]", Arrays.asList("Név", "Name")),
	CUSTOM_VIEW_WINDOW_DEFAULT_CHECKBOX(CUSTOM_VIEW_WINDOW + "//span[starts-with(@id, 'checkbox')]", Arrays.asList("Beállítás alapértelmezettként", "Default")),
	CUSTOM_VIEW_WINDOW_SAVE_BUTTON(CUSTOM_VIEW_WINDOW + "//span[text()='Mentés és alkalmaz' or "
			+ "text()='Save and apply']", Arrays.asList("Mentés és alkalmaz", "Save"));

	private final String locator;
	List<String> names;

	/**
	 * Constructor
	 * 
	 * @param itemId
	 */
	private ToolbarLocators(final String locator) {
		this.locator = locator;
	}

	/**
	 * Constructor
	 * 
	 * @param itemId
	 * @param englishName
	 * @param hungarianName
	 */
	private ToolbarLocators(final String locator, final List<String> names) {
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
