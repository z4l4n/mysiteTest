package com.frontendart.locators.records.attributes.hidden;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.records.attributes.general.FieldTypeLocators;
import com.frontendart.locators.records.attributes.general.GeneralTableAttributes;
import com.frontendart.locators.records.attributes.general.RecordAttributeFlags;

/**
 * Enum class for locating the attributes on the Authorship page.
 * @author Zoli
 *
 */
public enum AdditionalPublicationAttributes implements GeneralTableAttributes {
	INCLUDER(Arrays.asList("Befoglaló", "Includer"), FieldTypeLocators.RECORDSELECTORFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED)),
	CONFERENCE(Arrays.asList("Konferencia", "Conference"), FieldTypeLocators.RECORDSELECTORFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED)),
	JOURNAL(Arrays.asList("Folyóirat", "Journal"), FieldTypeLocators.RECORDSELECTORFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED)),
	AUTHORSHIP(Arrays.asList("Szerzők", "Authors"), FieldTypeLocators.RECORDSELECTORLISTFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED, RecordAttributeFlags.EXTENDABLE)),
	TITLE(Arrays.asList("Cím", "Title"), FieldTypeLocators.TEXTAREAFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED)),
	PUBLISHED_YEAR(Arrays.asList("Megjelenés éve", "Published year"), FieldTypeLocators.NUMBERFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED, RecordAttributeFlags.NOT_SEARCHABLE)),
	NISBN(Arrays.asList("ISBN", "ISBN"), FieldTypeLocators.NUMBERFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED));

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
	private AdditionalPublicationAttributes(final List<String> names, final FieldTypeLocators fieldType) {
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
	private AdditionalPublicationAttributes(final List<String> names, final FieldTypeLocators fieldType, final List<RecordAttributeFlags> attributeFlags) {
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
