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
public enum DeleteRecordMessageTypes implements GeneralLocatorTypes {
	CONFIRMATION_MESSAGE(Arrays.asList("Biztosan el kívánja távolítani a kiválasztott rekordokat?", "Are you sure you want to remove the selected records?")),
	ONE_ITEM_SUCCESS(Arrays.asList("A törlés művelet végzett. Sikeresen eltávolítva 1 a kiválasztott 1 elemből.", "Delete operation finished. Successfully removed 1 from the selected 1."));

	List<String> names;

	/**
	 * Constructor
	 * 
	 * @param itemId
	 * @param englishName
	 * @param hungarianName
	 */
	private DeleteRecordMessageTypes(final List<String> names) {
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
