package com.frontendart.locators.records.attributes.general;

import java.util.Arrays;
import java.util.List;

/**
 * Enum class for locating the elements on the State/Province page.
 *
 * @author Zoli
 *
 */
public enum StateProvinceRecordAttributes implements GeneralTableAttributes {
    STATUS(Arrays.asList("Státusz", "Status"), FieldTypeLocators.STATUS_TYPE_COMBOBOX, Arrays.asList(RecordAttributeFlags.DISABLED)),
    NAME(Arrays.asList("Név", "name"), FieldTypeLocators.TEXTAREAFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED)),
    NAME_ENG(Arrays.asList("Név helyi nyelven", "name eng"), FieldTypeLocators.TEXTAREAFIELD),
    COUNTRY(
            Arrays.asList("Ország", "Country"),
            FieldTypeLocators.RECORDSELECTORFIELD,
            Arrays.asList(RecordAttributeFlags.DISABLED, RecordAttributeFlags.EXTENDABLE)),
    COMMENT(Arrays.asList("Megjegyzés", "Comment"), FieldTypeLocators.TEXTAREAFIELD);

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
    private StateProvinceRecordAttributes(final List<String> names, final FieldTypeLocators fieldType) {
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
    private StateProvinceRecordAttributes(final List<String> names, final FieldTypeLocators fieldType,
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
