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
    REPORT_PANEL("//div[starts-with(@id, 'reportingpanel') and contains(@class, 'x-panel-bodyWrap')]"),
    LEFT_PANEL("//div[starts-with(@id, 'leftpanel')][starts-with(@class, 'x-panel ')]"),
    REFRESH_BUTTON(
            LEFT_PANEL + "//a[@data-qtip='Bal oldali táblázatok frissítése' or "
                    + "@data-qtip='Refresh tables in left panel']",
            Arrays.asList("Frissítés", "Refresh")),

    PANELS("//div[starts-with(@id, 'panel-')][(@role='tablist')]"),
    // Reportingpanel
    //REPORTINGPANEL("//div[starts-with(@id, 'reportingpanel-')][starts-with(@class, 'x-panel ')]"),
    REPORTINGPANEL(PANELS + "/div/div[5]"),

    EXPAND_BUTTON(REPORTINGPANEL + "//div[contains(@class, 'x-tool-img')]", Arrays.asList("Kinyitás-Összecsukás", "Expand-Collapse")),
    //NEW_REPORT_BUTTON(REPORTINGPANEL + "//*/a[@data-qtip='Hozzáadás' or @data-qtip='Add']", Arrays.asList("Új riport", "New report")),
    REPORTS_TABLE(REPORTINGPANEL + "//div[starts-with(@id, 'tableview')]"),
    NEW_REPORT_BUTTON(REPORT_PANEL + "//text()[contains(.,'+')]/ancestor::*[self::a][1]", Arrays.asList("Új riport", "New report")),
    // FILTERING
    FILTER_FIELD(REPORTINGPANEL + "/div[2]/div[2]/div[1]/div//input"),

    FILTER_BUTTON(
            REPORTINGPANEL
                    + "/div[2]/div[2]/div/div/div[1]/div//a[@data-qtip='Rekordok szűkítése' or @data-qtip='Filter records']",
            Arrays.asList("Szűkítés", "Filter")),

    CLEAR_FILTERING_BUTTON(REPORTINGPANEL + "//div[contains(@id, 'trigger-clear')]", Arrays.asList("Szűkítés törlése", "Clear filter")),

    // Reports queries table
    ALL_REPORTS(REPORT_PANEL + "//table"),
    REPORT_TOGGLE(ALL_REPORTS + "/tbody/tr/td/div/a"),
    REPORT_NAMES(ALL_REPORTS + "/tbody/tr/td[2]/div"),

    REPORT_TABLE_TEXT(
            REPORT_PANEL + "//div[contains(@class, 'x-grid-item-container')]//tr/td[2]/div"),

    // Toggle options
    GET_URL("./../../div[2]"),
    RERUN("./../../div[3]"),
    DELETE("./../../div[5]"),

    // Report URL message box, url
    MESSAGE_BOX_URL(GeneralPageLocators.MESSAGE_BOX_MESSAGE.toString() + "/a"),

    // Report generation window
    NEW_REPORT_GENERATION_WINDOW(
            "//text()[contains(.,'Riport generálása') or contains(., 'Generate Report')]/ancestor::*[self::div[contains(@class, 'x-window') and contains(@role, 'dialog')]][1]"),
    REPORT_GENERATION_WINDOW("//div[starts-with(@id, 'window-')][starts-with(@class, 'x-window ')]"),
    REPORT_GENERATION_WINDOW_HEADER(
            REPORT_GENERATION_WINDOW + "//*/div[starts-with(@data-ref, 'textEl')]",
            Arrays.asList("Új riport", "New report")),
    REPORT_EDITOR_PANEL(
            "//div[starts-with(@id, 'reporteditor-')][starts-with(@class, 'x-panel x-window-item')]",
            Arrays.asList("Új riport", "New report")),
    REPORT_GENERATION_WINDOW_CLOSE_BUTTON(
            REPORT_GENERATION_WINDOW + "//div[contains(@class, 'x-tool-img')]",
            Arrays.asList("Bezárás", "Close")),
    NAME_BOX(
            NEW_REPORT_GENERATION_WINDOW
                    + "//text()[contains(.,'Riport név:') or contains(., 'Report name:')]/ancestor::*[self::div][1]"),
    TEMPLATE_BOX(
            NEW_REPORT_GENERATION_WINDOW
                    + "//text()[contains(.,'Riport sablon:') or contains(., 'Report Template:')]/ancestor::*[self::div][1]"),
    FILE_TYPE_BOX(
            NEW_REPORT_GENERATION_WINDOW
                    + "//text()[contains(.,'Fájl formátum:') or contains(., 'File Type:')]/ancestor::*[self::div][1]"),
    LIST_BOX(
            NEW_REPORT_GENERATION_WINDOW
                    + "//text()[contains(.,'Lista:') or contains(., 'List:')]/ancestor::*[self::div][1]"),
    //div[starts-with(@id, 'reporteditor') and ends-with(@id, 'innerCt')]/div[1]//input
    // Name, template, saved search, file type - input fields
    NAME_INPUT(
            NAME_BOX + "//input",
            Arrays.asList("Riport név", "Report name")),
    REPORT_TEMPLATE_INPUT(TEMPLATE_BOX + "//input", Arrays.asList("Riport sablon", "Report template")),

    START_GENERATING_BUTTON(
            NEW_REPORT_GENERATION_WINDOW + "//text()[contains(.,'Start') or contains(., 'Indítás')]/ancestor::*[self::a][1]",
            Arrays.asList("Start", "Start")),

    // NAME_INPUT("(" + REPORT_EDITOR_PANEL + "//*/input)[1]", Arrays.asList("Riport név", "Report name")),
    // REPORT_TEMPLATE_INPUT("(" + REPORT_EDITOR_PANEL + "//*/input)[2]", Arrays.asList("Riport sablon", "Report template")),
    SAVED_SEARCH_INPUT("(" + REPORT_EDITOR_PANEL + "//*/input)[3]", Arrays.asList("Mentett keresés", "Saved search")),
    FILE_TYPE_INPUT(FILE_TYPE_BOX + "//input", Arrays.asList("Fájl formátum", "File type")),
    LIST_INPUT(LIST_BOX + "//input", Arrays.asList("Lista", "List")),

    // Generate report button
    GENERATE_REPORT_BUTTON(REPORT_EDITOR_PANEL + "//*/a", Arrays.asList("Riport generálás", "Generate Report")),

    // Message texts
    REPORT_TEMPLATE_IS_NOT_AVAILABLE(
            "",
            Arrays.asList("A riport generáláshoz szükséges sablon hiányzik.", "Missing report template for report generation.")),
    REPORT_GENERATION_STARTED_MESSAGEBOX_TEXT(
            "",
            Arrays.asList("A riport hamarosan elérhető lesz.", "The generated report will be available shortly.")),
    REPORT_IS_READY_MESSAGEBOX_TEXT("", Arrays.asList("A riport most elérhető a riportok táblázatban.", "")),
    REPORT_URL_WINDOW(
            "//text()[contains(.,'URL a riporthoz') or contains(., 'URL to the report')]/ancestor::*[self::div[starts-with(@id, 'messagebox') and contains(@role, 'alertdialog')]][1]"),
    REPORT_URL_LINK(REPORT_URL_WINDOW + "//a[starts-with(@href, 'https')]");

    private final String locator;
    List<String>         names;

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
        return names;
    }
}
