package com.frontendart.locators.records.attributes.general;

import java.util.Arrays;
import java.util.List;

public enum GeneralRecordAttributes implements GeneralTableAttributes {
    CREATOR(Arrays.asList("Létrehozó", "Creator"), FieldTypeLocators.NO_INPUT),
    MTMT_ID(Arrays.asList("MTMT azonosító", "MTMT ID"), FieldTypeLocators.TEXTFIELD),
    EXTERNAL_IDENTIFIERS(Arrays.asList("Külső azonosítók", "External identifiers"), FieldTypeLocators.TEXTFIELD),
    LAST_MODIFICATION(
            Arrays.asList("Utolsó módosítás", "Last Modified"),
            FieldTypeLocators.NO_INPUT,
            Arrays.asList(RecordAttributeFlags.DISABLED));

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
    private GeneralRecordAttributes(final List<String> names, final FieldTypeLocators fieldType) {
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
    private GeneralRecordAttributes(final List<String> names, final FieldTypeLocators fieldType,
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
