package com.frontendart.locators.main.rightpanel.top;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.records.attributes.general.FieldTypeLocators;
import com.frontendart.locators.records.attributes.general.GeneralTableAttributes;
import com.frontendart.locators.records.attributes.general.RecordAttributeFlags;

/**
 * Enum class for locating the elements on the user data page
 * @author Zoli
 *
 */
public enum UserProfileTableAttributes implements GeneralTableAttributes {	
	GIVEN_NAME(Arrays.asList("Keresztnév", "given name"), FieldTypeLocators.TEXTFIELD),
	AUX_NAME(Arrays.asList("Egyértelműsítő név", "aux. name"), FieldTypeLocators.TEXTFIELD),
	FAMILY_NAME(Arrays.asList("Családnév", "family name"), FieldTypeLocators.TEXTFIELD);
	//BIRTH_PLACE(Arrays.asList("Születési hely", "Birth Place"), FieldTypeLocators.TEXTFIELD),
	//BIRTH_DATE(Arrays.asList("Születés ideje", "Birth Date"), FieldTypeLocators.PARTIAL_DATE);
	
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
	private UserProfileTableAttributes(final List<String> names, final FieldTypeLocators fieldType) {
		this.names = names;
		this.fieldType = fieldType;
		this.attributeFlags = Arrays.asList(RecordAttributeFlags.NONE);
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
