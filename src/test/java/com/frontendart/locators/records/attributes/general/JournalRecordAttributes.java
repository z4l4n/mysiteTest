package com.frontendart.locators.records.attributes.general;

import java.util.Arrays;
import java.util.List;

/**
 * Enum class for locating the elements on the Journal page.
 * @author Zoli
 *
 */
public enum JournalRecordAttributes implements GeneralTableAttributes {
	PUBLISHED(Arrays.asList("Nyilvános", "Published"), FieldTypeLocators.CHECKBOXFIELD, Arrays.asList(RecordAttributeFlags.DISABLED)),
	STATUS(Arrays.asList("Státusz", "Status"), FieldTypeLocators.STATUS_TYPE_COMBOBOX, Arrays.asList(RecordAttributeFlags.DISABLED)),
	LONG_NAME(Arrays.asList("Hosszú név", "Long Name"), FieldTypeLocators.TEXTAREAFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED));
	//SHORT_NAME(Arrays.asList("Rövid név", "Short Name"), FieldTypeLocators.TEXTFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED));

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
	private JournalRecordAttributes(final List<String> names, final FieldTypeLocators fieldType) {
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
	private JournalRecordAttributes(final List<String> names, final FieldTypeLocators fieldType, final List<RecordAttributeFlags> attributeFlags) {
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
