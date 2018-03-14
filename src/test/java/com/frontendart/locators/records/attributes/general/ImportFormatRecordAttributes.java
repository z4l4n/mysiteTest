package com.frontendart.locators.records.attributes.general;

import java.util.Arrays;
import java.util.List;

/**
 * Enum class for locating the elements on the Import alias page.
 * 
 * @author Zoli
 *
 */
public enum ImportFormatRecordAttributes implements GeneralTableAttributes {
    NAME(Arrays.asList("Név", "Name"), FieldTypeLocators.TEXTFIELD, Arrays.asList(RecordAttributeFlags.DISABLED)),
    TYPE(Arrays.asList("Típus", "Type"), FieldTypeLocators.ENUMSELECTORCOMBOBOX, Arrays.asList(RecordAttributeFlags.REQUIRED));

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
    private ImportFormatRecordAttributes(final List<String> names, final FieldTypeLocators fieldType,
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
