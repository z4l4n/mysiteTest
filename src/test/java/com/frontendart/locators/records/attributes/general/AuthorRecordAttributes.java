package com.frontendart.locators.records.attributes.general;

import java.util.Arrays;
import java.util.List;

/**
 * Enum class for locating the attributes on the Author page.
 *
 * @author Zoli
 *
 */
public enum AuthorRecordAttributes implements GeneralTableAttributes {
    PUBLISHED(Arrays.asList("Nyilvános", "Published"), FieldTypeLocators.CHECKBOXFIELD, Arrays.asList(RecordAttributeFlags.DISABLED)),
    ID(Arrays.asList("MTMT azonosító", "MTMT ID"), FieldTypeLocators.NUMBERFIELD, Arrays.asList(RecordAttributeFlags.DISABLED)),
    STATUS(Arrays.asList("Státusz", "Status"), FieldTypeLocators.STATUS_TYPE_COMBOBOX, Arrays.asList(RecordAttributeFlags.DISABLED)),
    BIRTH_PLACE(
            Arrays.asList("Születési hely", "Birth Place"),
            FieldTypeLocators.TEXTFIELD,
            Arrays.asList(RecordAttributeFlags.REQUIRED, RecordAttributeFlags.NOT_SEARCHABLE)),
    USERNAME(
            Arrays.asList("Felhasználónév", "Username"),
            FieldTypeLocators.TEXTFIELD,
            Arrays.asList(RecordAttributeFlags.REQUIRED, RecordAttributeFlags.IS_NOT_DISPLAYED_BY_DEFAULT)),
    BIRTH_DATE(
            Arrays.asList("Születés ideje", "Birth Date"),
            FieldTypeLocators.PARTIAL_DATE,
            Arrays.asList(RecordAttributeFlags.REQUIRED, RecordAttributeFlags.NOT_SEARCHABLE)),
    FAMILY_NAME(
            Arrays.asList("Családnév", "Family name"),
            FieldTypeLocators.TEXTFIELD,
            Arrays.asList(RecordAttributeFlags.REQUIRED, RecordAttributeFlags.NOT_SEARCHABLE)),
    GIVEN_NAME(
            Arrays.asList("Keresztnév", "Given name"),
            FieldTypeLocators.TEXTFIELD,
            Arrays.asList(RecordAttributeFlags.REQUIRED, RecordAttributeFlags.NOT_SEARCHABLE)),
    AFFILIATIONS(
            Arrays.asList("Intézményhez csatolás", "Affiliations"),
            FieldTypeLocators.RECORDSELECTORLISTFIELD,
            Arrays.asList(RecordAttributeFlags.EXTENDABLE)),
    EMAIL_ADDRESS_CONFIRMED(
            Arrays.asList("Email cím jóváhagyva", "Email address confirmed"),
            FieldTypeLocators.CHECKBOXFIELD,
            Arrays.asList(RecordAttributeFlags.REQUIRED)),
    LOGIN_ENABLED(
            Arrays.asList("Belépés engedélyezve", "Login enabled"),
            FieldTypeLocators.CHECKBOXFIELD,
            Arrays.asList(RecordAttributeFlags.IS_NOT_DISPLAYED_BY_DEFAULT)),
    EMAIL(
            Arrays.asList("Email", "Email"),
            FieldTypeLocators.EMAILFIELD,
            Arrays.asList(RecordAttributeFlags.REQUIRED, RecordAttributeFlags.NOT_SEARCHABLE)),
    DEATH_DATE(
            Arrays.asList("Halálozás ideje", "Death date"),
            FieldTypeLocators.PARTIAL_DATE,
            Arrays.asList(RecordAttributeFlags.NOT_SEARCHABLE));

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
    private AuthorRecordAttributes(final List<String> names, final FieldTypeLocators fieldType,
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
