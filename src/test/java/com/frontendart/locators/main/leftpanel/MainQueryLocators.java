package com.frontendart.locators.main.leftpanel;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.general.GeneralLocatorTypes;

/**
 * Enum class for main search locators
 *
 * @author Zoli
 *
 */
public enum MainQueryLocators implements GeneralLocatorTypes {
    SMART_QUERIES_PANEL("//div[starts-with(@id, 'smartQueriesPanel')][starts-with(@class, 'x-panel ')]"),
    SMART_QUERIES_GRIDPANEL(SMART_QUERIES_PANEL + "//*/div[starts-with(@id, 'grid')][starts-with(@class, 'x-panel ')]"),

    // Filter field, filter button and new query button
    FILTER_INPUT_FIELD(
            SMART_QUERIES_GRIDPANEL + "//*/div[starts-with(@id, 'filterwidget')]//*/input",
            Arrays.asList("Filter mező", "Filter field")),
    FILTER_BUTTON(
            SMART_QUERIES_GRIDPANEL + "//*/a[@data-qtip='Rekordok szűkítése' or "
                    + "@data-qtip='Filter records']",
            Arrays.asList("Szűkítés", "Filter")),
    MY_QUERIES_CHECKBOXFIELD(
            "(" + SMART_QUERIES_GRIDPANEL + "//div/div/div/span[starts-with(@id, 'checkbox')])[4]",
            Arrays.asList("Saját keresések checkbox", "My own queries checkbox")),
    NEW_QUERY_BUTTON(
            SMART_QUERIES_GRIDPANEL + "//span[text()='Új keresés' or text()='New search']",
            Arrays.asList("Új keresés", "New query")),

    // Query Template window
    TEMPLATE_PANEL("//div[starts-with(@id, 'querytemplatepanel')][starts-with(@class, 'x-window ')]"),
    NEW_QUERY_BUTTON_ON_TEMPLATE_PAGE(TEMPLATE_PANEL + "//a[2]", Arrays.asList("Új keresés", "New query")),

    // Smart queries table
    ALL_QUERIES(SMART_QUERIES_GRIDPANEL + "//*/table"),
    ALL_QUERIES_NAME(SMART_QUERIES_GRIDPANEL + "//*/table/tbody/tr/td[2]/div"),
    QUERY_TOGGLE("./tbody/tr/td/div/a"),
    QUERY_NAME("./tbody/tr/td[2]/div"),
    QUERY_NUMBER_OF_RESULTS("./tbody/tr/td[3]/div"),
    QUERY_OPEN_EDITOR("./tbody/tr/td[4]/div/img"),

    RUN_QUERY("//div[@aria-expanded='true' and starts-with(@id, 'menu-')]/div/div/div[2]/div/div[5]/a/span"),
    DELETE_QUERY("//div[@aria-expanded='true' and starts-with(@id, 'menu-')]/div/div/div[2]/div/div[10]/a/span"),
    COPY_QUERY("//div[@aria-expanded='true' and starts-with(@id, 'menu-')]/div/div/div[2]/div/div[9]/a/span"),
    SAVE_RESULTS_AS_QUERY("//div[@aria-expanded='true' and starts-with(@id, 'menu-')]/div/div/div[2]/div/div[7]/a/span"),

    // Toggle options
    RUN("./../../div//span[text()='Keresés' or text()='Search']", Arrays.asList("Keresés", "Run")),
    SAVE_AS_LIST(
            "./../../div//span[text()='Eredmények mentése listaként' or text()='Save results as list']",
            Arrays.asList("Mentés listaként", "Save results as list")),
    DUPLICATE("./../../div//span[text()='Másolás' or text()='Copy']", Arrays.asList("Másolás", "Copy")),
    DELETE("./../../div//span[text()='Törlés' or text()='Remove']", Arrays.asList("Töröl", "Delete")),
    SAVE_AS_DEFAULT(
            "./../../div//span[text()='Beállítás alapértelmezettként' or text()='Set as default']",
            Arrays.asList("Beállítás alapértelmezettként", "Set as default")),
    ADVANCED("./../../div//span[text()='Haladó beállítások' or text()='Advanced settings']", Arrays.asList("Új keresés", "New query"));

    private final String locator;
    List<String>         names;

    /**
     * Constructor
     *
     * @param itemId
     */
    private MainQueryLocators(final String locator) {
        this.locator = locator;
    }

    /**
     * Constructor
     *
     * @param itemId
     * @param englishName
     * @param hungarianName
     */
    private MainQueryLocators(final String locator, final List<String> names) {
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
