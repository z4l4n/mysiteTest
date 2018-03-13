package com.frontendart.locators.main.rightpanel.crud;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.general.GeneralLocatorTypes;

/**
 * Enum class for new record messages
 * 
 * @author Zoli
 *
 */
public enum RecordEditorMessageTypes implements GeneralLocatorTypes {
	EMPTY_FIELD(Arrays.asList("Néhány szükséges mezőt nem helyesen töltött ki!", "")),
	INSTADMIN_MESSAGE(Arrays.asList("Mentés után nem fogja tudni szerkeszteni ezt az objektumot, de fogja tudni keresni és olvasni.", "")),
	AUTHOR_MESSAGE(Arrays.asList("Mentés után nem fogja tudni szerkeszteni ezt az objektumot, de fogja tudni keresni és olvasni.")),
//	EXISTING_DOI(Arrays.asList("A megadott azonosítóval már létezik publikáció. Ezt a publikációt szerkesztheti.", "A publication already exists for the given DOI. You can edit this publication.")),
	EXISTING_DOI(Arrays.asList("A megadott azonosítóval már létezik publikáció. Ezt a publikációt szerkesztheti.", "A publication already exists for the given ID. You can edit this publication.")),
	
	EXISTING_DOI_INSTADMIN(Arrays.asList("Az importálttal megegyező publikáció található az adatbázisban. Az alábbi publikációk közül nyithat meg egyet szerkesztésre:",
			"@An identical publication to the imported one is already in the database. Select below if you want to edit that:")),
	PUBLICATION_TYPE_MISMATCH(Arrays.asList("Ez a jelleg ehhez a típushoz nem társítható!"));

	List<String> names;

	/**
	 * Constructor
	 * 
	 * @param itemId
	 * @param englishName
	 * @param hungarianName
	 */
	private RecordEditorMessageTypes(final List<String> names) {
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
