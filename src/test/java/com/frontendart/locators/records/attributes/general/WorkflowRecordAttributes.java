package com.frontendart.locators.records.attributes.general;

import java.util.Arrays;
import java.util.List;

/**
 * Enum class for locating the attributes on the Author page.
 * 
 * @author Zoli
 *
 */
public enum WorkflowRecordAttributes implements GeneralTableAttributes {
    NAME(Arrays.asList("Név", "name"), FieldTypeLocators.TEXTFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED)),
    NAME_ENG(Arrays.asList("Név angolul", "name in English"), FieldTypeLocators.TEXTFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED)),
    RECORD_TYPE(
            Arrays.asList("Kezelt típus", "record Type"),
            FieldTypeLocators.ENUMSELECTORCOMBOBOX,
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
    private WorkflowRecordAttributes(final List<String> names, final FieldTypeLocators fieldType) {
        this.names = names;
        this.fieldType = fieldType;
        attributeFlags = Arrays.asList(RecordAttributeFlags.NONE);
    }

    /**
     * Constructor
     * 
     * @param names
     * @param fieldType
     * @param required
     * @param isDisabledByDefault
     */
    private WorkflowRecordAttributes(final List<String> names, final FieldTypeLocators fieldType,
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
