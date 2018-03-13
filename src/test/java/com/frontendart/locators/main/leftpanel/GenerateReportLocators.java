package com.frontendart.locators.main.leftpanel;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.general.GeneralLocatorTypes;
import com.frontendart.locators.general.GeneralPageLocators;

/**
 * Enum class for report locators
 * 
 * @author Zoli
 *
 */
public enum GenerateReportLocators implements GeneralLocatorTypes {
	// Refresh button
	LEFT_PANEL("//div[starts-with(@id, 'leftpanel')][starts-with(@class, 'x-panel ')]"),
	REFRESH_BUTTON(LEFT_PANEL + "//a[@data-qtip='Bal oldali táblázatok frissítése' or "
			+ "@data-qtip='Refresh tables in left panel']", Arrays.asList("Frissítés", "Refresh")),

	PANELS("//div[starts-with(@id, 'panel-')][(@role='tablist')]"),
	// Reportingpanel
	//REPORTINGPANEL("//div[starts-with(@id, 'reportingpanel-')][starts-with(@class, 'x-panel ')]"),
	REPORTINGPANEL(PANELS + "/div/div[5]"),

	EXPAND_BUTTON(REPORTINGPANEL + "//div[contains(@class, 'x-tool-img')]", Arrays.asList("Kinyitás-Összecsukás", "Expand-Collapse")),
	NEW_REPORT_BUTTON(REPORTINGPANEL + "//*/a[@data-qtip='Hozzáadás' or @data-qtip='Add']", Arrays.asList("Új riport", "New report")),
	REPORTS_TABLE(REPORTINGPANEL + "//div[starts-with(@id, 'tableview')]"),

	// Reports queries table
	ALL_REPORTS(REPORTS_TABLE + "//*/table"),
	REPORT_TOGGLE("./tbody/tr/td/div/a"),
	REPORT_NAME("./tbody/tr/td[2]/div"),

	// Toggle options
	GET_URL("./../../div[2]"),
	RERUN("./../../div[3]"),
	DELETE("./../../div[5]"),

	// Report URL message box, url
	MESSAGE_BOX_URL(GeneralPageLocators.MESSAGE_BOX_MESSAGE.toString() + "/a"),

	// Report generation window
	REPORT_GENERATION_WINDOW("//div[starts-with(@id, 'window-')][starts-with(@class, 'x-window ')]"),
	REPORT_GENERATION_WINDOW_HEADER(REPORT_GENERATION_WINDOW + "//*/div[starts-with(@data-ref, 'textEl')]",
			Arrays.asList("Új riport", "New report")),
	REPORT_EDITOR_PANEL("//div[starts-with(@id, 'reporteditor-')][starts-with(@class, 'x-panel x-window-item')]",
			Arrays.asList("Új riport", "New report")),
	REPORT_GENERATION_WINDOW_CLOSE_BUTTON(REPORT_GENERATION_WINDOW + "//div[contains(@class, 'x-tool-img')]",
			Arrays.asList("Bezárás", "Close")),

	// Name, template, saved search, file type - input fields
	NAME_INPUT("(" + REPORT_EDITOR_PANEL + "//*/input)[1]", Arrays.asList("Riport név", "Report name")),
	REPORT_TEMPLATE_INPUT("(" + REPORT_EDITOR_PANEL + "//*/input)[2]", Arrays.asList("Riport sablon", "Report template")),
	SAVED_SEARCH_INPUT("(" + REPORT_EDITOR_PANEL + "//*/input)[3]", Arrays.asList("Mentett keresés", "Saved search")),
	FILE_TYPE_INPUT("(" + REPORT_EDITOR_PANEL + "//*/input)[4]", Arrays.asList("Fájl formátum", "File type")),
	LIST_INPUT("(" + REPORT_EDITOR_PANEL + "//*/input)[5]", Arrays.asList("Lista", "List")),

	// Generate report button
	GENERATE_REPORT_BUTTON(REPORT_EDITOR_PANEL + "//*/a", Arrays.asList("Riport generálás", "Generate Report")),

	// Message texts
	REPORT_TEMPLATE_IS_NOT_AVAILABLE("", Arrays.asList("A riport generáláshoz szükséges sablon hiányzik.", "Missing report template for report generation.")),
	REPORT_GENERATION_STARTED_MESSAGEBOX_TEXT("", Arrays.asList("A riport hamarosan elérhető lesz.", "")),
	REPORT_IS_READY_MESSAGEBOX_TEXT("", Arrays.asList("A riport most elérhető a riportok táblázatban.", ""));

	private final String locator;
	List<String> names;

	/**
	 * Constructor
	 * 
	 * @param itemId
	 */
	private GenerateReportLocators(final String locator) {
		this.locator = locator;
	}

	/**
	 * Constructor
	 * 
	 * @param itemId
	 * @param englishName
	 * @param hungarianName
	 */
	private GenerateReportLocators(final String locator, final List<String> names) {
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
