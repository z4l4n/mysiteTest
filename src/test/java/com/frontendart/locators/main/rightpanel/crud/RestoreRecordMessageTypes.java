package com.frontendart.locators.main.rightpanel.crud;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.general.GeneralLocatorTypes;

/**
 * Enum class for delete record messages
 * 
 * @author Zoli
 *
 */
public enum RestoreRecordMessageTypes implements GeneralLocatorTypes {
	ONE_ITEM_SUCCESS(Arrays.asList("A visszaállítás művelet végrehajtása lezajlott. Sikeresen visszaállítva 1 a kiválasztott 1 elemből.", ""));

	List<String> names;

	/**
	 * Constructor
	 * 
	 * @param itemId
	 * @param englishName
	 * @param hungarianName
	 */
	private RestoreRecordMessageTypes(final List<String> names) {
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
