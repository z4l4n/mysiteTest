package com.frontendart.locators.general;

import java.util.Arrays;
import java.util.List;

/**
 * Enum class for message box locators and load mask locators
 *
 * @author Zoli
 *
 */
public enum GeneralSelectorBoxLocators implements GeneralLocatorTypes {
    // General selector box elements
    SELECTOR_BOX("//div[contains(@id, 'selectorbox')][last()]"),
    SELECTOR_BOX_HEADER(SELECTOR_BOX + "//div[contains(@class, 'x-title-text ')]"),
    SELECTOR_BOX_ITEMS(SELECTOR_BOX + "//table[contains(@id, 'tableview-')]"),
    SELECTOR_BOX_GRID_ITEMS(SELECTOR_BOX + "//table[contains(@id, 'gridview-')]"),
    //	SELECTOR_BOX_NEW_BUTTON(SELECTOR_BOX + "//a[@target='_blank']", Arrays.asList("Új", "New")),
    SELECTOR_BOX_NEW_BUTTON(SELECTOR_BOX + "//span[text()='Új']", Arrays.asList("Új", "New")),

    //	SELECTOR_BOX_SAVE_AND_CLOSE_BUTTON(SELECTOR_BOX + "//a//span[text()='Mentés és bezárás' or "
    //			+ "text()='Save and close']", Arrays.asList("Mentés és bezárás", "Save and close")),
    SELECTOR_BOX_CONNECT_AND_CLOSE_BUTTON(
            SELECTOR_BOX + "//a//span[text()='Csatol & bezár' or "
                    + "text()='Connect & close']",
            Arrays.asList("Csatol & bezár", "Connect & close")),

    SELECTOR_BOX_SAVE_AND_CLOSE_BUTTON(
            SELECTOR_BOX
                    + "//a//span[text()='Kiválaszt és bezár' or text()='Select and close' or text()='Csatol & bezár' or text()='Connect & close']",
            Arrays.asList("Kiválaszt és bezár", "Select and close")),

    SELECTOR_BOX_FILTER_BUTTON(SELECTOR_BOX + "//span[text()='Szűkítés' or text()='Filter']", Arrays.asList("Szűrés", "Filter")),

    //Publication:subtype
    FIND_SUBTYPE_WINDOW("//div[contains(@id, 'selectorbox-') and contains(@class, 'x-window ')]"),
    FIND_SUBTYPE_WINDOW_PART("//div[contains(@id, 'selectorboxcontentpanel-') and contains(@class, 'x-panel ')]"),
    FIND_SUBTYPE_ITEMS(FIND_SUBTYPE_WINDOW_PART + "//table[contains(@id, 'gridview-') and contains(@id, '-record-')]"),
    FIND_SUBTYPE_SAVE_AND_CLOSE_BUTTON(
            FIND_SUBTYPE_WINDOW
                    + "//a//span[text()='Mentés és bezárás' or text()='Save and close' or text()='Csatol & bezár' or text()='Connect & close']",
            Arrays.asList("Mentés és bezárás", "Save and close")),

    // Institute window selector elements
    FIND_INSTITUTES_WINDOW("//div[contains(@id, 'findinstituteswindow')]"),
    FIND_INSTITUTES_ITEMS(FIND_INSTITUTES_WINDOW + "//table[contains(@id, 'treeview-')]"),
    FIND_INSTITUTES_FILTER_FIELD(FIND_INSTITUTES_WINDOW + "//input", Arrays.asList("Szűrő mező", "Filter input")),
    FIND_INSTITUTES_FILTER_BUTTON(
            FIND_INSTITUTES_WINDOW + "//span[text()='Szűkítés' or text()='Filter']",
            Arrays.asList("Szűrés", "Filter")),
    FIND_INSTITUTES_SAVE_AND_CLOSE_BUTTON(
            FIND_INSTITUTES_WINDOW + "//a//span[text()='Kiválaszt és bezár' or "
                    + "text()='Select and close']",
            Arrays.asList("Mentés és bezárás", "Save and close"));

    private final String locator;
    List<String>         names;

    /**
     * Constructor
     *
     * @param itemId
     */
    private GeneralSelectorBoxLocators(final String locator) {
        this.locator = locator;
    }

    /**
     * Constructor
     *
     * @param itemId
     * @param englishName
     * @param hungarianName
     */
    private GeneralSelectorBoxLocators(final String locator, final List<String> names) {
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
