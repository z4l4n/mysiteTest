package com.frontendart.locators.main.rightpanel.crud;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.frontendart.common.Utils;
import com.frontendart.locators.general.GeneralLocatorTypes;
import com.frontendart.locators.records.attributes.general.GeneralTableAttributes;
import com.frontendart.locators.records.attributes.general.RecordAttributeFlags;

/**
 * Enum class for new record editor
 * 
 * @author Zoli
 *
 */
public enum RecordEditorLocators implements GeneralLocatorTypes {
	NEW_BUTTON("//a[starts-with(@data-qtip, 'Új rekord felvitele') or "
			+ "starts-with(@data-qtip, 'Add new record')]", Arrays.asList("Új", "New")),
	EDIT_BUTTON("//a[starts-with(@data-qtip, 'Rekord szerkesztése') or "
			+ "starts-with(@data-qtip, 'Edit record')]", Arrays.asList("Szerkeszt", "Edit")),
	REMOVE_BUTTON("//a[starts-with(@data-qtip, 'Rekord törlése') or "
			+ "starts-with(@data-qtip, 'Delete record')]", Arrays.asList("Törlés", "Delete")),
	
	OPERATIONS("//a[starts-with(@data-qtip, 'Egyéb műveletek') or "
			+ "starts-with(@data-qtip, 'Other Operations')]", Arrays.asList("Egyéb műveletek", "Other Operations")),
	
	LOCK(OPERATIONS + "//div[contains(@id, 'lockmenuitem-')]/a[starts-with(@id, 'lockmenuitem-')]/span[text()='Zárol']"),
	UNLOCK(OPERATIONS + "//div[contains(@id, 'unlockmenuitem-')]/a[starts-with(@id, 'unlockmenuitem-')]/span[text()='Felold']"),
	ENABLE_USER(OPERATIONS + "//div[contains(@id, 'enableusermenuitem-')]/a[starts-with(@id, 'enableusermenuitem-')]/span[text()='Felhasználó belépésének engedélyezése']"),
	DISABLE_USER(OPERATIONS + "//div[contains(@id, 'disableusermenuitem-')]/a[starts-with(@id, 'disableusermenuitem-')]/span[text()='Felhasználó belépésének tiltása']"),
	
	CHANGE_STATE_COMBOBOX_PICKER("//div[starts-with(@id, 'changestatecombobox-') and contains(@id, '-trigger-picker')]", Arrays.asList("Státusz-választó lenyitó gomb", "Change state picker")),
	CHANGE_STATE_UNAPPROVED("//li[contains(@class, 'combo-status-unapproved')]", Arrays.asList("Nem jóváhagyott", "Unapproved")),
	CHANGE_STATE_APPROVED("//li[contains(@class, 'combo-status-approved')]", Arrays.asList("Jóváhagyott", "Approved")),
	CHANGE_STATE_ADMIN_APPROVED("//li[contains(@class, 'combo-status-admin_approved')]", Arrays.asList("Admin láttamozott", "Admin approved")),
	CHANGE_STATE_VALIDATED("//li[contains(@class, 'combo-status-validated')]", Arrays.asList("Érvényesített", "Validated")),
	CHANGE_STATE_CHECKED("//li[contains(@class, 'combo-status-checked')]", Arrays.asList("Hitelesített", "Checked")),
	
	//EDITOR_WINDOW("//div[starts-with(@id, 'modeleditorwindow')][starts-with(@class, 'x-window ')][last()]", Arrays.asList("Szerkesztő ablak", "Editor window")),
	EDITOR_WINDOW("//div[starts-with(@class, 'x-window ')][last()]", Arrays.asList("Szerkesztő ablak", "Editor window")),
	MODEL_EDITOR_WINDOW("//div[starts-with(@id, 'modeleditorwindow')][starts-with(@class, 'x-window ')][last()]", Arrays.asList("Szerkesztő ablak", "Editor window")),
	EDITOR_HEADER(EDITOR_WINDOW + "//*/div[contains(@class, 'x-window-header-title')]/div"),
	EDITOR_FORM(EDITOR_WINDOW + "//*/div[starts-with(@id, 'modeleditform')][starts-with(@class, 'x-panel ')]"),
	EDITOR_FORM_FIELDS(EDITOR_FORM + "//div[contains(@class, ' x-form-item x-form-item-default ')]"),
	EDITOR_FORM_VISIBLE_FIELDS(EDITOR_FORM + "//div[contains(@class, ' x-form-item x-form-item-default ')][not(contains(@style, 'display: none'))]"),
	EDITOR_FORM_FIELDS_LABEL(EDITOR_FORM_FIELDS + "//label[contains(@class, 'x-form-item-label ')]"),
	EDITOR_FORM_FIELDS_VISIBLE_LABELS(EDITOR_FORM_FIELDS + "[not(contains(@style, 'display: none'))]//span"),
	EDITOR_FORM_FIELDS_VISIBLE_LABELS_ALTERNATIVE(EDITOR_FORM_FIELDS + "[not(contains(@style, 'display: none'))]//label"),
	CANCEL_BUTTON(EDITOR_WINDOW + "//*/span[text()='Mégse' or text()='Cancel']", Arrays.asList("Mégsem", "Cancel")),
	SAVE_AND_CLOSE(EDITOR_WINDOW + "//*/span[text()='Mentés és bezárás' or text()='Save and close' or text()='Csatol & bezár' or text()='Connect & close']", Arrays.asList("Mentés és bezárás", "Save and close")),
	//CONNECT_AND_CLOSE(EDITOR_WINDOW + "//*/span[text()='Csatol & bezár' or text()='Connect & close']", Arrays.asList("Csatol & bezár", "Connect & close")),
	SEND(EDITOR_WINDOW + "//*/span[text()='Küldés' or text()='Send']", Arrays.asList("Küldés", "Send")),
	CLOSE(MODEL_EDITOR_WINDOW + "//div[(@data-qtip='Ablak bezárása' or @data-qtip='Close window')]//div", Arrays.asList("Ablak bezárása", "Close window")),

	// Publication only
	DOI_FIELD(EDITOR_WINDOW + "//*/input[starts-with(@id, 'textfield')]", Arrays.asList("DOI", "DOI")),
	NEXT_BUTTON(EDITOR_WINDOW + "//span[text()='Tovább' or text()='Next']", Arrays.asList("Tovább", "Next")),
	SWORD_WINDOW_HEADER("//div[starts-with(@id, 'sworduploadwindow')][starts-with(@class, 'x-window-header')]", Arrays.asList("SWORD feltöltés", "SWORD upload")),
	SWORD_WINDOW_CLOSE(SWORD_WINDOW_HEADER + "//img", Arrays.asList("SWORD feltöltés bezárás", "SWORD upload close")),
	
	PUBLICATION_SAVE_WINDOW(EDITOR_WINDOW + "//div[starts-with(@id, 'PublicationSaveMEF-') and contains(@id, '-bodyWrap')]", Arrays.asList("Közlemény szerkesztő ablak", "Publication editor window")),
	PUBLICATION_EDITOR_WINDOW(MODEL_EDITOR_WINDOW + "//div[starts-with(@id, 'PublicationSaveMEF-') and contains(@id, '-bodyWrap')]", Arrays.asList("Közlemény szerkesztő ablak", "Publication editor window")),
	PUBLICATION_SAVE_AND_CLOSE_BUTTON(PUBLICATION_SAVE_WINDOW+"//*/span[text()='Mentés és bezárás' or text()='Save and close' or text()='Csatol & bezár' or text()='Connect & close']", Arrays.asList("Mentés és bezárás", "Save and close")),
	
	KEYWORD_EDITOR_WINDOW(MODEL_EDITOR_WINDOW + "//div[starts-with(@id, 'GenericMEF-') and contains(@id, '-bodyWrap')]", Arrays.asList("Kulcsszó szerkesztő ablak", "Keyword editor window")),
	
	VALUES_LIST_PUBLICATON(PUBLICATION_EDITOR_WINDOW + "//div[starts-with(@id, 'displayfield-') and contains(@id, '-bodyEl')]//div"),
	VALUES_LIST_KEYWORD(KEYWORD_EDITOR_WINDOW + "//div[starts-with(@id, 'displayfield-') and contains(@id, '-bodyEl')]//div"),
	
	//Citation only
	CITATION_EDITOR_WINDOW(MODEL_EDITOR_WINDOW + "//div[starts-with(@id, 'GenericMEF-') and contains(@id, '-bodyWrap')]", Arrays.asList("Közlemény szerkesztő ablak", "Publication editor window")),
	VALUES_LIST_CITATION(CITATION_EDITOR_WINDOW + "//div[starts-with(@id, 'displayfield-') and contains(@id, '-bodyEl')]//div");
	

	private final String locator;
	List<String> names;

	/**
	 * Constructor
	 * 
	 * @param itemId
	 */
	private RecordEditorLocators(final String locator) {
		this.locator = locator;
	}

	/**
	 * Constructor
	 * 
	 * @param itemId
	 * @param englishName
	 * @param hungarianName
	 */
	private RecordEditorLocators(final String locator, final List<String> names) {
		this.locator = locator;
		this.names = names;
	}

	/**
	 * Returns the locator as string
	 */
	@Override
	public String toString() {
		return locator;
	}

	/**
	 * Returns names
	 */
	@Override
	public List<String> getNames() {
		return this.names;
	}

	/**
	 * Checks if table attributes are present on editor page
	 * 
	 * @param pageToValidate
	 * @return
	 */
	public static boolean checkAttributesAreVisible(final Class<? extends GeneralTableAttributes> pageToValidate) {
		boolean returnValue = true;

		// Get labels
		final List<WebElement> labelsAsWebElement = Utils.createGeneralWebElementsFromEnum(EDITOR_FORM_FIELDS_VISIBLE_LABELS);
		final List<String> labelsAsString = Utils.convertThisWebElementArrayToStringArray(labelsAsWebElement);

		// Check that all not disabled locators are present
		for (final GeneralTableAttributes locator : pageToValidate.getEnumConstants()) {
			if ((!locator.getAttributeFlags().contains(RecordAttributeFlags.DISABLED)) && (!Utils.doesThisStringListContainsThisAttribute(labelsAsString, locator))) {
				returnValue = false;
			}
		}

		return returnValue;
	}
}
