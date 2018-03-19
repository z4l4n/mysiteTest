package com.frontendart.locators.main.rightpanel.view;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.general.GeneralLocatorTypes;

/**
 * Enum class for sort items
 * 
 * @author Zoli
 *
 */
public enum SortLocators implements GeneralLocatorTypes {
    // Sorting widget
    RIGHT_PANEL("//div[starts-with(@id, 'rightpanel')][starts-with(@class, 'x-panel')]"),
    SORTING_WIDGET(RIGHT_PANEL + "//*/div[starts-with(@id, 'sortingwidget')][starts-with(@class, 'x-container')]"),
    NEW_SORTING_BUTTON(
            SORTING_WIDGET + "//*/a[starts-with(@data-qtip, 'Új rendezés hozzáadása') or "
                    + "starts-with(@data-qtip, 'Add new sorting')]",
            Arrays.asList("Új rendezés", "New sorting")),
    ALL_SORTERS(SORTING_WIDGET + "//*/div[starts-with(@id, 'sorter')][starts-with(@class, 'x-btn-group ')]"),
    // ALL_SORTERS(//div[translate(@id,'0123456789','')='sorter-'])
    SORTER_SELECTOR("./div/div/div/div/a"),
    SORTER_ARROW("./div/div/div/div/a[2]/span/span"),
    SORT_OPTION_ASCENDING("(//a[contains(@class, 'x-menu-item-focus')]/../..//a)[1]", Arrays.asList("Növekvő", "Ascending")),
    SORT_OPTION_DESCENDING("(//a[contains(@class, 'x-menu-item-focus')]/../..//a)[2]", Arrays.asList("Csökkenő", "Descending")),
    CLOSE_SORTER("./div/div/div/div/a[3]"),
    DELETE_ALL_SORTING_BUTTON(
            SORTING_WIDGET + "//*/a[starts-with(@data-qtip, 'Minden rendezés törlése') or "
                    + "starts-with(@data-qtip, 'Remove all sortings')]",
            Arrays.asList("Rendezések törlése", "Delete sortings")),

    // SORTER_MENU
    SORTER_MENU("//*/div[starts-with(@id, 'menu')][starts-with(@class, 'x-menu x-cycle-menu')][last()]"),
    SORTER_MENU_OPTIONS(SORTER_MENU + "//*/span");

    private final String locator;
    List<String>         names;

    /**
     * Constructor
     * 
     * @param itemId
     */
    private SortLocators(final String locator) {
        this.locator = locator;
    }

    /**
     * Constructor
     * 
     * @param itemId
     * @param englishName
     * @param hungarianName
     */
    private SortLocators(final String locator, final List<String> names) {
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
