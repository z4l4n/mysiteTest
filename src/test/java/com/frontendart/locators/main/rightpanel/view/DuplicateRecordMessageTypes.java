package com.frontendart.locators.main.rightpanel.view;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.general.GeneralLocatorTypes;

/**
 * Enum class for duplicate record messages
 * 
 * @author Zoli
 *
 */
public enum DuplicateRecordMessageTypes implements GeneralLocatorTypes {
	ONE_ITEM_SUCCESS(Arrays.asList("A kiválasztott tartalom másolása megtörtént.", "The selected content has been duplicated.")),
	NO_DUPLICATES_FOUND(Arrays.asList("Nem található duplikátum", "")),
	MARK_AS_DUPLUM_CONTINUE(Arrays.asList("A kiválasztott 2 elem egymás duplumjainak lesz jelölve. Tovább?", "")),
	MARK_AS_DUPLUM_SUCCESS(Arrays.asList("A kiválasztott elemek meg lettek jelölve duplumként", "")),
	DIFFERENT_TYPE(Arrays.asList("Nem sikerült megjelölni a kiválasztott elemeket duplumként", ""));

	List<String> names;

	/**
	 * Constructor
	 * 
	 * @param itemId
	 * @param englishName
	 * @param hungarianName
	 */
	private DuplicateRecordMessageTypes(final List<String> names) {
		this.names = names;
	}

	/**
	 * Returns names
	 */
	@Override
	public List<String> getNames() {
		return this.names;
	}

}
