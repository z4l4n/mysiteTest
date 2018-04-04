package com.frontendart.locators.general;

import java.util.Arrays;
import java.util.List;

/**
 * Enum class for message box locators and load mask locators
 * 
 * @author Zoli
 *
 */
public enum GeneralPageLocators implements GeneralLocatorTypes {
    // Message box locators
    MESSAGE_BOX("//div[starts-with(@id, 'messagebox-')][starts-with(@class, 'x-window ')]", Arrays.asList("Felugró üzenet", "Message box")),
    //	MESSAGE_BOX("//div[starts-with(@id, 'messagebox-') or starts-with(@id, 'modeleditorwindow-')][starts-with(@class, 'x-window ')]", Arrays.asList("Felugró üzenet", "Message box")),

    MESSAGE_BOX_MESSAGE(MESSAGE_BOX + "//div[contains(@id, '-msg')][contains(@class, 'x-window-text')]"),
    //	MESSAGE_BOX_MESSAGE(MESSAGE_BOX + "//div[contains(@id, '-msg')][contains(@class, 'x-window-text')]|//label[contains(@class, 'x-component ')]"),
    MESSAGE_BOX_OK_BUTTON(
            "//div[contains(@id, 'messagebox')]//a[starts-with(@aria-hidden,'false')]//span[text()='OK' or text()='Save']",
            Arrays.asList("OK", "OK")),
    MESSAGE_BOX_YES_BUTTON("//div[contains(@id, 'messagebox')]//span[text()='Igen' or text()='Yes']", Arrays.asList("Igen", "Yes")),
    MESSAGE_BOX_SAVE_BUTTON("//div[contains(@id, 'messagebox')]//span[text()='Mentés' or text()='Save']", Arrays.asList("Mentés", "Save")),
    MESSAGE_BOX_CANCEL_BUTTON(
            "//div[contains(@id, 'messagebox')]//span[text()='Mégsem' or text()='Cancel']",
            Arrays.asList("Mégse", "Cancel")),
    MESSAGE_BOX_SHOW_RESULTS_BUTTON(
            "//div[contains(@id, 'messagebox')]//span[text()='Eredmények megtekintése' or text()='Show results']",
            Arrays.asList("Eredmények megtekintése", "Show results")),

    // Message window locators
    MESSAGE_WINDOW(
            "//div[starts-with(@id, 'window')][starts-with(@class, 'x-window ')]",
            Arrays.asList("Törlés eredménye ablak", "Message removal result window")),
    MESSAGE_TEXT(MESSAGE_WINDOW + "//div[starts-with(@class, 'x-form-display-field')]"),
    MESSAGE_WINDOW_CLOSE_BUTTON(MESSAGE_WINDOW + "//a", Arrays.asList("Bezár", "Close")),

    // Messagecenter window locators
    MESSAGE_CENTER_WINDOW(
            "//div[starts-with(@class, 'x-window ') and (starts-with(@id, 'messagecenterwindow-') or starts-with(@id, 'selectorwindow-'))]"),
    //	MESSAGE_CENTER_WINDOW("//div[starts-with(@id, 'messagecenterwindow ')] and //div[(@class, 'x-box-target')]"),
    MESSAGE_CENTER_WINDOW_GRIDPANEL(MESSAGE_CENTER_WINDOW + "//div[starts-with(@id, 'contentfullgrid')][starts-with(@class, 'x-panel ')]"),
    MESSAGE_CENTER_WINDOW_SENT_MESSAGES_GRIDPANEL("(" + MESSAGE_CENTER_WINDOW_GRIDPANEL + ")[2]"),
    MESSAGE_CENTER_WINDOW_NEW_BUTTON("//a[@tabindex='0']//span[text()='Új' or text()='New']", Arrays.asList("Új", "New")),
    MESSAGE_CENTER_WINDOW_MESSAGES_TAB(
            MESSAGE_CENTER_WINDOW + "//a//span[contains(text(),'Üzeneteim') or contains(text(),'My messages')]",
            Arrays.asList("Üzeneteim", "Messages")),
    MESSAGE_CENTER_WINDOW_SENT_MESSAGES_TAB(
            "//a//span[text()='Küldött üzenetek' or text()='Sent messages']",
            Arrays.asList("Küldött üzenetek", "Sent messages")),
    MESSAGE_CENTER_WINDOW_REMOVE_BUTTON("(//a//span[text() = 'Törlés' or text() = 'Remove'])[last()]", Arrays.asList("Törlés", "Remove")),
    MESSAGE_CENTER_WINDOW_CLOSE_BUTTON(
            MESSAGE_CENTER_WINDOW + "//a//span[text()='Bezár' or text()='Close']",
            Arrays.asList("Bezár", "Close")),
    //	MESSAGE_CENTER_WINDOW_CLOSE_BUTTON(MESSAGE_CENTER_WINDOW + "//div//div//div[2]", Arrays.asList("Bezár", "Close")),
    MESSAGE_CENTER_WINDOW_CLOSE_BUTTON_ALTERNATIVE(
            MESSAGE_CENTER_WINDOW + "//div[(@data-qtip='Ablak bezárása' or @data-qtip='Close window')]//div",
            Arrays.asList("Ablak bezárása", "Close window")),
    //	MESSAGE_CENTER_WINDOW_CLOSE_BUTTON_ALTERNATIVE(MESSAGE_CENTER_WINDOW + "[last()]//div[contains(@class, 'x-tool-img')]", Arrays.asList("Bezár", "Close")),
    MESSAGE_CENTER_WINDOW_ALL_ROWS(MESSAGE_CENTER_WINDOW + "//div[starts-with(@id, 'contentfullgrid')]//table"),
    MESSAGE_CENTER_WINDOW_TABLE_VIEW(
            MESSAGE_CENTER_WINDOW + "//span[text()='Táblázat' or text()='Grid']",
            Arrays.asList("Táblázat nézet", "Table view")),

    //Error window
    ERROR_WINDOW("//div[starts-with(@id, 'errorwindow-')]", Arrays.asList("Hiba", "Error")),
    ERROR_WINDOW_REFRESH_BUTTON(ERROR_WINDOW + "//span[text()='Újratöltés']", Arrays.asList("Újratöltés", "Refresh")),

    WARNING_WINDOW("//div[contains(@id, 'messagebox-') and (text()='Figyelem')]", Arrays.asList("Figyelem", "Warning")),
    INVALID_WINDOW(
            "//div[contains(@id, 'messagebox-') and ((text()='Érvénytelen bejegyzés') or text()='Invalid record']",
            Arrays.asList("Érvénytelen bejegyzés", "Invalid record")),

    // Load mask locators
    SMART_QUERIES_PANEL_LOAD_MASK("//div[starts-with(@id, 'smartQueriesPanel')]//*/div[starts-with(@id, 'loadmask')]"),
    RIGHT_PANEL_LOAD_MASK(
            "//div[starts-with(@id, 'rightpanel')]//*/div[starts-with(@id, 'loadmask')][starts-with(@class, 'x-component ')]"),

    // General selector window (Such as versions window)
    SELECTOR_WINDOW("//div[starts-with(@id, 'selectorwindow')][starts-with(@class, 'x-window ')]"),
    SELECTOR_WINDOW_ALL_HEADERS(SELECTOR_WINDOW + "//*/div[starts-with(@class, 'x-column-header ')]"),

    // Self citation window
    // general
    SELF_CITATION_WINDOW("//div[starts-with(@id, 'selfcitationwindow')][starts-with(@class, 'x-window ')]"),
    SELF_CITATION_CLOSE(SELF_CITATION_WINDOW + "//*/span[text()='Bezár' or text()='Close']", Arrays.asList("Bezár", "Close")),
    SELF_CITATION_NEXT(SELF_CITATION_WINDOW + "//*/span[text()='Következő' or text()='Next']", Arrays.asList("Következő", "Next")),
    SELF_CITATION_SKIP(SELF_CITATION_WINDOW + "//*/span[text()='Kihagyás' or text()='Skip']", Arrays.asList("Kihagyás", "Skip")),
    SELF_CITATION_SAVE_AND_NEXT(
            SELF_CITATION_WINDOW + "//*/span[text()='Mentés és ugrás a következőre' or text()='Save and go to next']",
            Arrays.asList("Mentés és ugrás a következőre", "Save and go to next")),

    // first page
    SELF_CITATION_MODE_RADIOGROUP(
            SELF_CITATION_WINDOW + "//span[text()='Mode:' or text()='Mód:']/ancestor::div[starts-with(@id, 'radiogroup')]"),
    SELF_CITATION_MODE_MANUAL(
            SELF_CITATION_MODE_RADIOGROUP + "//label[text()='Manual' or text()='Manuális']/preceding-sibling::span/input"),
    SELF_CITATION_MODE_AUTOMATIC(
            SELF_CITATION_MODE_RADIOGROUP + "//label[text()='Automatic' or text()='Automatikus']/preceding-sibling::span/input"),
    SELF_CITATION_CHECK_RADIOGROUP(
            SELF_CITATION_WINDOW + "//span[text()='Check:' or text()='Ellenőrzés:']/ancestor::div[starts-with(@id, 'radiogroup')]"),
    SELF_CITATION_CHECK_ONLY_UNCHECKED(
            SELF_CITATION_CHECK_RADIOGROUP
                    + "//label[text()='Only unchecked' or text()='Csak a nem jelöltek']/preceding-sibling::span/input"),
    SELF_CITATION_CHECK_ALL(
            SELF_CITATION_CHECK_RADIOGROUP
                    + "//label[text()='All (checked and unchecked)' or text()='Összes (jelölt és jelöletlen is)']/preceding-sibling::span/input"),
    SELF_CITATION_SET_RADIOGROUP(
            SELF_CITATION_WINDOW + "//span[text()='Set:' or text()='Beállít:']/ancestor::div[starts-with(@id, 'radiogroup')]"),
    SELF_CITATION_SET_ONLY_INDEPENDENT(
            SELF_CITATION_CHECK_RADIOGROUP + "//label[text()='Only Independent' or text()='Csak Független']/preceding-sibling::span/input"),
    SELF_CITATION_SET_ALL(
            SELF_CITATION_SET_RADIOGROUP
                    + "//label[text()='All (independent, national origin, foreign edition)' or text()='Összes (független, magyarországi szerző, külföldi kiadás)']/preceding-sibling::span/input"),

    // second page
    SELF_CITATION_INDEPENDENT_RADIOGROUP(
            SELF_CITATION_WINDOW + "//span[text()='Independent:' or text()='Független:']/ancestor::div[starts-with(@id, 'radiogroup')]"),
    SELF_CITATION_INDEPENDENT_YES(
            SELF_CITATION_INDEPENDENT_RADIOGROUP + "//label[text()='Yes (i)' or text()='Igen (i)']/preceding-sibling::span/input"),
    SELF_CITATION_INDEPENDENT_NO(
            SELF_CITATION_INDEPENDENT_RADIOGROUP + "//label[text()='No (n)' or text()='Nem (n)']/preceding-sibling::span/input"),
    SELF_CITATION_INDEPENDENT_IDN(
            SELF_CITATION_INDEPENDENT_RADIOGROUP + "//label[text()=\"I don't know\" or text()='Nem tudom']/preceding-sibling::span/input"),

    SELF_CITATION_FOREIGN_RADIOGROUP(
            SELF_CITATION_WINDOW
                    + "//span[text()='Foreign edition (optional):' or text()='Külföldi kiadás (opcionális):']/ancestor::div[starts-with(@id, 'radiogroup')]"),
    SELF_CITATION_FOREIGN_YES(SELF_CITATION_FOREIGN_RADIOGROUP + "//label[text()='Yes' or text()='Igen']/preceding-sibling::span/input"),
    SELF_CITATION_FOREIGN_NO(SELF_CITATION_FOREIGN_RADIOGROUP + "//label[text()='No' or text()='Nem']/preceding-sibling::span/input"),
    SELF_CITATION_FOREIGN_IDN(
            SELF_CITATION_FOREIGN_RADIOGROUP + "//label[text()=\"I don't know\" or text()='Nem tudom']/preceding-sibling::span/input"),

    SELF_CITATION_NATIONAL_RADIOGROUP(
            SELF_CITATION_WINDOW
                    + "//span[text()='National origin (optional):' or text()='Magyarországi szerző (opcionális):']/ancestor::div[starts-with(@id, 'radiogroup')]"),
    SELF_CITATION_NATIONAL_YES(SELF_CITATION_NATIONAL_RADIOGROUP + "//label[text()='Yes' or text()='Igen']/preceding-sibling::span/input"),
    SELF_CITATION_NATIONAL_NO(SELF_CITATION_NATIONAL_RADIOGROUP + "//label[text()='No' or text()='Nem']/preceding-sibling::span/input"),
    SELF_CITATION_NATIONAL_IDN(
            SELF_CITATION_NATIONAL_RADIOGROUP + "//label[text()=\"I don't know\" or text()='Nem tudom']/preceding-sibling::span/input"),

    // combo box options
    COMBOBOX_OPTIONS("//div[not(contains(@style, 'display: none'))]/div/ul/li[contains(@class, 'x-boundlist-item')]");

    private final String locator;
    List<String>         names;

    /**
     * Constructor
     * 
     * @param itemId
     */
    private GeneralPageLocators(final String locator) {
        this.locator = locator;
    }

    /**
     * Constructor
     * 
     * @param itemId
     * @param englishName
     * @param hungarianName
     */
    private GeneralPageLocators(final String locator, final List<String> names) {
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
