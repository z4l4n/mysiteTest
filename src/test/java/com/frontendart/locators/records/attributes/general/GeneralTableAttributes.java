package com.frontendart.locators.records.attributes.general;

import java.util.List;

/**
 * Interface for table attributes
 * @author Zoli
 *
 */
public interface GeneralTableAttributes {
	/**
	 *  This will return the attribute names in different languages
	 * @return
	 */
	List<String> getNames();

	/**
	 *  This will return the attribute type
	 * @return
	 */
	FieldTypeLocators getFieldType();

	/**
	 *  This will return special flags for the attribute (e.g.: required)
	 * @return
	 */
	List<RecordAttributeFlags> getAttributeFlags();

}
