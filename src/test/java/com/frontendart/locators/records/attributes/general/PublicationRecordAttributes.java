package com.frontendart.locators.records.attributes.general;

import java.util.Arrays;
import java.util.List;

/**
 * Enum class for locating the elements on the Publication page.
 * @author Zoli
 *
 */
public enum PublicationRecordAttributes implements GeneralTableAttributes {
	TYPE(Arrays.asList("Típus", "Type"), FieldTypeLocators.RECORDSELECTORFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED, RecordAttributeFlags.EXTENDABLE)),
	SUBTYPE(Arrays.asList("Besorolás", "Sub Type"), FieldTypeLocators.RECORDSELECTORFIELD, Arrays.asList(RecordAttributeFlags.EXTENDABLE)),
	CATEGORY(Arrays.asList("Jelleg", "Category"), FieldTypeLocators.RECORDSELECTORFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED, RecordAttributeFlags.EXTENDABLE)),
	BOOK(Arrays.asList("Befoglaló mű", "Book"), FieldTypeLocators.RECORDSELECTORFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED, RecordAttributeFlags.EXTENDABLE)),
	LANGUAGE(Arrays.asList("Nyelv", "Languages"), FieldTypeLocators.RECORDSELECTORLISTFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED, RecordAttributeFlags.EXTENDABLE)),
	AUTHORSHIP(Arrays.asList("Szerzőségek", "Authorships"), FieldTypeLocators.RECORDSELECTORLISTFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED, RecordAttributeFlags.EXTENDABLE)),
	TITLE(Arrays.asList("Cím", "Title"), FieldTypeLocators.TEXTAREAFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED)),
	SUBTITLE(Arrays.asList("Alcím", "Sub Title"), FieldTypeLocators.TEXTAREAFIELD, Arrays.asList(RecordAttributeFlags.NOT_SEARCHABLE)),
	PUBLISHED_YEAR(Arrays.asList("Megjelenés éve", "Published Year"), FieldTypeLocators.NUMBERFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED, RecordAttributeFlags.NOT_SEARCHABLE)),
	SUBJECTS(Arrays.asList("Tudományterület", "Subjects"), FieldTypeLocators.RECORDSELECTORLISTFIELD, Arrays.asList(RecordAttributeFlags.REQUIRED, RecordAttributeFlags.EXTENDABLE)),
	NUMBER_OF_CITATIONS(Arrays.asList("Idéző közlemények száma", "Citation count"), FieldTypeLocators.NUMBERFIELD, Arrays.asList(RecordAttributeFlags.DISABLED)),

	MTMT_AUTHORS(Arrays.asList("Szerzők MTMT-ben", "MTMT authors"), FieldTypeLocators.RECORDSELECTORFIELD, Arrays.asList(RecordAttributeFlags.DISABLED));
	
	
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
	private PublicationRecordAttributes(final List<String> names, final FieldTypeLocators fieldType) {
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
	private PublicationRecordAttributes(final List<String> names, final FieldTypeLocators fieldType, final List<RecordAttributeFlags> attributeFlags) {
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
