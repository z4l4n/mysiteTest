package com.frontendart.locators.main.rightpanel.top;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.records.attributes.general.FieldTypeLocators;
import com.frontendart.locators.records.attributes.general.GeneralTableAttributes;
import com.frontendart.locators.records.attributes.general.RecordAttributeFlags;

/**
 * Enum class for locating the elements on the Message page.
 * @author Zoli
 *
 */
public enum MessageTableAttributes implements GeneralTableAttributes {
	RECIPIENT(Arrays.asList("Címzettek", "Recipients"), FieldTypeLocators.RECORDSELECTORLISTFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED)),
	SUBJECT(Arrays.asList("Tárgy", "subject"), FieldTypeLocators.TEXTFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED)),
	TYPE(Arrays.asList("Típus", "type"), FieldTypeLocators.ENUMSELECTORCOMBOBOX, Arrays.asList(RecordAttributeFlags.REQUIRED)),
	TEXT(Arrays.asList("Üzenet szövege", "body"), FieldTypeLocators.TEXTAREAFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED));

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
	private MessageTableAttributes(final List<String> names, final FieldTypeLocators fieldType) {
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
	private MessageTableAttributes(final List<String> names, final FieldTypeLocators fieldType, final List<RecordAttributeFlags> attributeFlags) {
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
