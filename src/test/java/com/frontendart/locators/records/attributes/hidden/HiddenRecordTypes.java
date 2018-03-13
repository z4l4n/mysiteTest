package com.frontendart.locators.records.attributes.hidden;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.records.attributes.general.GeneralTableAttributes;
import com.frontendart.locators.records.types.RecordTypeFlags;

/**
 * General record types 
 * @author Zoli
 *
 */
public enum HiddenRecordTypes {
	ADMIN_ROLES(Arrays.asList("Adminszerep", "Admin roles"), AdminRoleRecordAttributes.class, Arrays.asList(RecordTypeFlags.HAS_REQUIRED_FIELDS)),
	AFFILIATION(Arrays.asList("Intézményhez csatolás", "Affiliation"), AffiliationRecordAttributes.class, Arrays.asList(RecordTypeFlags.HAS_REQUIRED_FIELDS)),
	AUTHORSHIP(Arrays.asList("Szerzőség", "Authorship"), AuthorshipRecordAttributes.class, Arrays.asList(RecordTypeFlags.HAS_REQUIRED_FIELDS));

	List<String> names;
	Class<? extends GeneralTableAttributes> attributes;
	List<RecordTypeFlags> recordFlags;

	/**
	 * Constructor
	 * @param engName
	 * @param hungName
	 * @param pageToValidate
	 */
	private HiddenRecordTypes(final List<String> names, final Class<? extends GeneralTableAttributes> attributes) {
		this.names = names;
		this.attributes = attributes;
		this.recordFlags = Arrays.asList(RecordTypeFlags.NONE);
	}

	/**
	 * Constructor
	 * @param engName
	 * @param hungName
	 * @param pageToValidate
	 */
	private HiddenRecordTypes(final List<String> names, final Class<? extends GeneralTableAttributes> attributes, final List<RecordTypeFlags> flags) {
		this.names = names;
		this.attributes = attributes;
		this.recordFlags = flags;
	}

	/**
	 * Returns names
	 */
	public List<String> getNames() {
		return this.names;
	}

	/**
	 * Returns attribute flags
	 * @return
	 */
	public List<RecordTypeFlags> getRecordFlags() {
		return this.recordFlags;
	}

	/**
	 * Returns the page to validate
	 * @return
	 */
	public Class<? extends GeneralTableAttributes> getAttributes() {
		return this.attributes;
	}

}
