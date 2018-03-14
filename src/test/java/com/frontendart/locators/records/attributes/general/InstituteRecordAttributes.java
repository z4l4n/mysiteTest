package com.frontendart.locators.records.attributes.general;

import java.util.Arrays;
import java.util.List;

/**
 * Enum class for locating the elements on the Institute page.
 * 
 * @author Zoli
 *
 */

public enum InstituteRecordAttributes implements GeneralTableAttributes {
    PUBLISHED(Arrays.asList("Nyilvános", "Published"), FieldTypeLocators.CHECKBOXFIELD, Arrays.asList(RecordAttributeFlags.DISABLED)),
    STATUS(Arrays.asList("Státusz", "Status"), FieldTypeLocators.STATUS_TYPE_COMBOBOX, Arrays.asList(RecordAttributeFlags.DISABLED)),
    NAME(Arrays.asList("Név", "Name"), FieldTypeLocators.TEXTFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED)),
    NAME_ENG(Arrays.asList("Név angolul", "Name Eng"), FieldTypeLocators.TEXTFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED)),
    ABBREVIATION(Arrays.asList("Rövidítés", "Abbreviation"), FieldTypeLocators.TEXTFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED)),
    ABBREVIATION_ENG(
            Arrays.asList("Rövidítés angolul", "Abbreviation En"),
            FieldTypeLocators.TEXTFIELD,
            Arrays.asList(RecordAttributeFlags.REQUIRED)),
    INSTITUTE_TYPE(
            Arrays.asList("Típus", "Type"),
            FieldTypeLocators.RECORDSELECTORFIELD,
            Arrays.asList(RecordAttributeFlags.REQUIRED, RecordAttributeFlags.EXTENDABLE)),
    ONLINE_REGISTRATION(
            Arrays.asList("Online regisztráció", "Online registration"),
            FieldTypeLocators.CHECKBOXFIELD,
            Arrays.asList(RecordAttributeFlags.DISABLED, RecordAttributeFlags.EXTENDABLE)),
    OFFLINE_REGISTRATION_GUIDE(
            Arrays.asList("Offline regisztrációs útmutató", "Offline registration guide"),
            FieldTypeLocators.TEXTAREAFIELD,
            Arrays.asList(RecordAttributeFlags.REQUIRED));

    List<String>               names;
    FieldTypeLocators          fieldType;
    List<RecordAttributeFlags> attributeFlags;

    /**
     * Constructor
     * 
     * @param names
     * @param fieldType
     * @param required
     * @param isDisabledByDefault
     */
    private InstituteRecordAttributes(final List<String> names, final FieldTypeLocators fieldType,
            final List<RecordAttributeFlags> attributeFlags) {
        this.names = names;
        this.fieldType = fieldType;
        this.attributeFlags = attributeFlags;
    }

    /**
     * Returns names
     */
    @Override
    public List<String> getNames() {
        return names;
    }

    /**
     * Returns attribute flags
     * 
     * @return
     */
    @Override
    public List<RecordAttributeFlags> getAttributeFlags() {
        return attributeFlags;
    }

    /**
     * Returns field types
     */
    @Override
    public FieldTypeLocators getFieldType() {
        return fieldType;
    }
}
