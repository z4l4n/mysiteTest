package com.frontendart.locators.records.attributes.general;

import java.util.Arrays;
import java.util.List;

/**
 * Enum class for locating the attributes on the Author page.
 * @author Zoli
 *
 */
public enum ReportTemplateRecordAttributes implements GeneralTableAttributes {
	ID(Arrays.asList("Azonosító", "ID"), FieldTypeLocators.NUMBERFIELD, Arrays.asList(RecordAttributeFlags.DISABLED)),
	TITLE(Arrays.asList("Cím", "Title"), FieldTypeLocators.TEXTFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED)),
	TITLE_IN_ENGLISH(Arrays.asList("Cím angolul", "Title in English"), FieldTypeLocators.TEXTFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED)),
	RECORD_TYPE(Arrays.asList("Rekordtípus", "Record type"), FieldTypeLocators.RECORDSELECTORFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED)),
	COMMENT(Arrays.asList("Megjegyzés", "Comment"), FieldTypeLocators.TEXTAREAFIELD, Arrays.asList(RecordAttributeFlags.NOT_SEARCHABLE));

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
	private ReportTemplateRecordAttributes(final List<String> names, final FieldTypeLocators fieldType) {
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
	private ReportTemplateRecordAttributes(final List<String> names, final FieldTypeLocators fieldType, final List<RecordAttributeFlags> attributeFlags) {
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
