package com.frontendart.locators.records.attributes.general;

import java.util.Arrays;
import java.util.List;

/**
 * Enum class for locating the elements on the Ticket page.
 * @author Zoli
 *
 */

public enum TicketRecordAttributes implements GeneralTableAttributes {
	PUBLISHED(Arrays.asList("Nyilvános", "Published"), FieldTypeLocators.CHECKBOXFIELD, Arrays.asList(RecordAttributeFlags.DISABLED)),
	STATUS(Arrays.asList("Státusz", "Status"), FieldTypeLocators.STATUS_TYPE_COMBOBOX, Arrays.asList(RecordAttributeFlags.DISABLED)),
	ASSIGNEE(Arrays.asList("Címzett", "Assignee"), FieldTypeLocators.ENUMSELECTORCOMBOBOX, Arrays.asList(RecordAttributeFlags.REQUIRED)),
	TYPE(Arrays.asList("Típus", "Type"), FieldTypeLocators.ENUMSELECTORCOMBOBOX, Arrays.asList(RecordAttributeFlags.REQUIRED)),
	BODY(Arrays.asList("Tartalom", "Body"), FieldTypeLocators.TEXTAREAFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED)),
	TARGET_ID(Arrays.asList("Célrekord", "target ID"), FieldTypeLocators.NUMBERFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED)),
	TARGET_TYPE(Arrays.asList("Célrekord típusa", "target type"), FieldTypeLocators.TEXTFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED));

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
	private TicketRecordAttributes(final List<String> names, final FieldTypeLocators fieldType, final List<RecordAttributeFlags> attributeFlags) {
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
