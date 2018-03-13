package com.frontendart.locators.records.attributes.general;

import java.util.Arrays;
import java.util.List;

/**
 * Enum class for locating the attributes on the Rating page.
 * @author Zoli
 *
 */
public enum RatingRecordAttributes implements GeneralTableAttributes {
	PUBLISHED(Arrays.asList("Nyilvános", "Published"), FieldTypeLocators.CHECKBOXFIELD, Arrays.asList(RecordAttributeFlags.DISABLED)),
	STATUS(Arrays.asList("Státusz", "Status"), FieldTypeLocators.STATUS_TYPE_COMBOBOX, Arrays.asList(RecordAttributeFlags.DISABLED)),
	JOURNAL(Arrays.asList("Folyóirat", "Journal"), FieldTypeLocators.RECORDSELECTORFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED)),
	RATING_TYPE(Arrays.asList("Típus", "Rating Type"), FieldTypeLocators.RECORDSELECTORFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED)),
	NUM_VALUE(Arrays.asList("Számérték", "Num Value"), FieldTypeLocators.NUMBERFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED)),
	COMMENT(Arrays.asList("Megjegyzés", "Comment"), FieldTypeLocators.TEXTAREAFIELD);

	List<String> names;
	FieldTypeLocators fieldType;
	List<RecordAttributeFlags> attributeFlags;

	/**
	 * Constructor
	 * @param names
	 * @param fieldType
	 * @param required
	 * @param isDisabledByDefault
	 */
	private RatingRecordAttributes(final List<String> names, final FieldTypeLocators fieldType) {
		this.names = names;
		this.fieldType = fieldType;
		this.attributeFlags = Arrays.asList(RecordAttributeFlags.NONE);
	}

	/**
	 * Constructor
	 * @param names
	 * @param fieldType
	 * @param required
	 * @param isDisabledByDefault
	 */
	private RatingRecordAttributes(final List<String> names, final FieldTypeLocators fieldType, final List<RecordAttributeFlags> attributeFlags) {
		this.names = names;
		this.fieldType = fieldType;
		this.attributeFlags = attributeFlags;
	}

	/**
	 * Returns names
	 */
	@Override
	public List<String> getNames() {
		return this.names;
	}

	/**
	 * Returns attribute flags
	 * @return
	 */
	@Override
	public List<RecordAttributeFlags> getAttributeFlags() {
		return this.attributeFlags;
	}

	/**
	 * Returns field types
	 */
	@Override
	public FieldTypeLocators getFieldType() {
		return this.fieldType;
	}

}