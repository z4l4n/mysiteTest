package com.frontendart.locators.records.attributes.general;

import com.frontendart.common.Constants;
import com.frontendart.common.Utils;
import com.frontendart.locators.main.rightpanel.view.ChangeStatusTypes;
import com.frontendart.locators.main.rightpanel.view.ImportAliasTypes;

/**
 * Enum class for field types
 *
 * @author Zoli
 *
 */
public enum FieldTypeLocators {
    DATEFIELD(".//input[starts-with(@id, 'datefield-')]", "1985-07-13"),
    PARTIAL_DATE(Constants.FIELD_INPUT_XPATH, "1985-07-13"),
    EMAILFIELD(Constants.FIELD_INPUT_XPATH, "featest-" + Utils.randomString() + "dummy@gmail.com"),
    ENUMSELECTORCOMBOBOX,
    NUMBERFIELD(Constants.FIELD_INPUT_XPATH, Integer.toString(Utils.randInt(1900, 2000))),
    CHECKBOXFIELD,
    PUBLISHED_YEAR(Constants.FIELD_INPUT_XPATH, Integer.toString(Utils.randInt(1900, 2000))),
    AFFILIATION(".//div[starts-with(@class, 'x-form-trigger ')]"),
    RECORDSELECTORFIELD(".//div[starts-with(@class, 'x-form-trigger ')]"),
    RECORDSELECTORLISTFIELD(".//div[@data-qtip='Csatol' or @data-qtip='Connect']/div"),
    STATUS_TYPE_COMBOBOX(
            Constants.FIELD_INPUT_XPATH,
            ChangeStatusTypes.values()[Utils.randInt(1, ChangeStatusTypes.values().length) - 1].getHunName()),
    IMPORT_ALIAS_TYPE_COMBOBOX(
            Constants.FIELD_INPUT_XPATH,
            ImportAliasTypes.values()[Utils.randInt(1, ImportAliasTypes.values().length) - 1].getHunName()),
    TEXTAREAFIELD("./div/div/div/*[contains(@class, 'x-form-field ')]", Constants.PREFIX + Utils.randomString()),
    TEXTFIELD(Constants.FIELD_INPUT_XPATH, Constants.PREFIX + Utils.randomString()),
    URL(Constants.FIELD_INPUT_XPATH, "http://" + Constants.PREFIX + Utils.randomString() + ".hu"),
    NO_INPUT();

    String editableFieldXpath;
    String randomValue;

    /**
     * Constructor
     * 
     * @param locator
     */
    private FieldTypeLocators() {
        editableFieldXpath = Constants.FIELD_INPUT_XPATH;
        randomValue = "";
    }

    /**
     * Constructor
     * 
     * @param locator
     */
    private FieldTypeLocators(final String locator) {
        editableFieldXpath = locator;
        randomValue = "";
    }

    /**
     * Constructor
     * 
     * @param locator
     */
    private FieldTypeLocators(final String locator, final String randomValue) {
        editableFieldXpath = locator;
        this.randomValue = randomValue;
    }

    /**
     * Returns xpath
     * 
     * @return
     */
    public String getEditableFieldXpath() {
        return editableFieldXpath;
    }

    /**
     * Returns random value
     */
    public String getRandomValue() {
        return randomValue;
    }
}
