package com.frontendart.locators.records.attributes.general;

import java.util.Arrays;
import java.util.List;

/**
 * Enum class for locating the attributes on the Author page.
 * @author Zoli
 *
 */
public enum ClassificationRecordAttributes implements GeneralTableAttributes {
	CODE(Arrays.asList("Kód", "Code"), FieldTypeLocators.TEXTFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED)),
	NAME(Arrays.asList("Név", "Name"), FieldTypeLocators.TEXTFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED));

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
	private ClassificationRecordAttributes(final List<String> names, final FieldTypeLocators fieldType) {
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
	private ClassificationRecordAttributes(final List<String> names, final FieldTypeLocators fieldType, final List<RecordAttributeFlags> attributeFlags) {
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
