package com.frontendart.locators.records.attributes.general;

import java.util.Arrays;
import java.util.List;

/**
 * Enum class for locating the elements on the BookSeries page.
 *
 * @author Zoli
 *
 */
public enum BookSeriesRecordAttributes implements GeneralTableAttributes {
    STATUS(Arrays.asList("Státusz", "Status"), FieldTypeLocators.STATUS_TYPE_COMBOBOX, Arrays.asList(RecordAttributeFlags.DISABLED)),
    PUBLISHED(Arrays.asList("Nyilvános", "Published"), FieldTypeLocators.CHECKBOXFIELD, Arrays.asList(RecordAttributeFlags.DISABLED)),
    TITLE(Arrays.asList("Hosszú név", "Long Name"), FieldTypeLocators.TEXTAREAFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED)),
    COMMENT(Arrays.asList("Megjegyzés", "Comment"), FieldTypeLocators.TEXTAREAFIELD),
    START_YEAR(Arrays.asList("Megjelenés kezdete", "Start Year"), FieldTypeLocators.PUBLISHED_YEAR),
    END_YEAR(Arrays.asList("Megjelenés vége", "End Year"), FieldTypeLocators.PUBLISHED_YEAR);
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
    private BookSeriesRecordAttributes(final List<String> names, final FieldTypeLocators fieldType) {
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
    private BookSeriesRecordAttributes(final List<String> names, final FieldTypeLocators fieldType,
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
