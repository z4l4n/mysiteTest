package com.frontendart.locators.main.leftpanel;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.general.GeneralLocatorTypes;

/**
 * Enum class for query editor locators
 * 
 * @author Zoli
 *
 */
public enum QueryEditorLocators implements GeneralLocatorTypes {
	// Main query editor panel
	SEARCH_PANEL("//div[starts-with(@id, 'searchpanel')][contains(@class, 'x-panel ')]"),

	// Header
	SEARCH_PANEL_HEADER(SEARCH_PANEL + "//div[contains(@id, 'header')]"),
//	HEADER_CLOSE(SEARCH_PANEL_HEADER + "//div[contains(@class, 'x-tool-img')]", Arrays.asList("Bezárás gomb", "Close button")),
//	HEADER_CLOSE(SEARCH_PANEL_HEADER + "//span[text()='Mégse' or text()='Cancel']", Arrays.asList("Mégse gomb", "Cancel button")),
	HEADER_CLOSE(SEARCH_PANEL + "//span[text()='Mégse' or text()='Cancel']", Arrays.asList("Mégse gomb", "Cancel button")),

	// Every condition and is met buttons
	EVERY_CONDITION_BUTTON("(" + SEARCH_PANEL + "//*/div[contains(@id, 'toolbar')]//*/a)[2]"),
	EVERY_CONDITION_OPTION("//span[starts-with(@id, 'menucheckitem')][text()='Every condition' or text()='Minden szabály']", Arrays.asList("Mindegyik feltétel", "Every condition")),
	ANY_CONDITION_OPTION("//span[starts-with(@id, 'menucheckitem')][text()='Any condition' or text()='Valamelyik szabály']", Arrays.asList("Bármelyik feltétel", "Any condition")),
	IS_MET_BUTTON("(" + SEARCH_PANEL + "//*/div[contains(@id, 'toolbar')]//*/a)[3]", Arrays.asList("Teljesül", "Is Met")),

	// Name input field
	NAME_INPUT_FIELD(SEARCH_PANEL + "//input[@placeholder='Név' or @placeholder='Name']", Arrays.asList("Keresés neve", "Query name")),
	MATCH_SELECTOR_CYCLE(".//a[starts-with(@id, 'cycle')]"),
	MATCH_SELECTOR_PREFIX_MATCH_OPTION("//span[starts-with(@id, 'menucheckitem')][text()='prefix match' or text()='Szöveg eleji egyezés']", Arrays.asList("prefix match", "Szöveg eleji egyezés")),
	MATCH_SELECTOR_POSTFIX_MATCH_OPTION("//span[starts-with(@id, 'menucheckitem')][text()='postfix match' or text()='Szöveg végi egyezés']", Arrays.asList("postfix match", "Szöveg végi egyezés")),
	MATCH_SELECTOR_ANY_MATCH_OPTION("//span[starts-with(@id, 'menucheckitem')][text()='any match' or text()='Bármilyen egyezés']", Arrays.asList("any match", "Bármilyen egyezés")),
	MATCH_SELECTOR_ANY_MATCH_IN_WORDS_OPTION("//span[starts-with(@id, 'menucheckitem')][text()='any match in words' or text()='Bármilyen egyezés szavanként']", Arrays.asList("any match in words", "Bármilyen egyezés szavanként")),
	MATCH_SELECTOR_EXACT_MATCH_OPTION("//span[starts-with(@id, 'menucheckitem')][text()='exact match' or text()='Teljes egyezés' or text()='=']", Arrays.asList("exact match", "Teljes egyezés")),
	MATCH_SELECTOR_NOT_EXACT_MATCH_OPTION("//span[starts-with(@id, 'menucheckitem')][text()='not exact match' or text()='Nem teljes egyezés'  or text()='!=']", Arrays.asList("not exact match", "Nem teljes egyezés")),
	MATCH_SELECTOR_IS_NULL_OPTION("//span[starts-with(@id, 'menucheckitem')][text()='is null' or text()='Üres']", Arrays.asList("is null", "Üres")),
	MATCH_SELECTOR_NOT_NULL_OPTION("//span[starts-with(@id, 'menucheckitem')][text()='not null' or text()='Nem üres']", Arrays.asList("not null", "Nem üres")),
	MATCH_SELECTOR_ME_OPTION("//span[starts-with(@id, 'menucheckitem')][text()='is me' or text()='én vagyok']", Arrays.asList("is me", "én vagyok")),
	MATCH_SELECTOR_ME_OR_DELEGATED_OPTION("//span[starts-with(@id, 'menucheckitem')][text()='is me or delegated' or text()='én vagyok, vagy egy delegált szerző']", Arrays.asList("is me or delegated", "én vagyok, vagy egy delegált szerző")),

	// New condition
//	NEW_CONDITION_INPUT(SEARCH_PANEL + "//div[starts-with(@id, 'searchconditionselector')]//input[@placeholder='Új feltétel' or "
//			+ "@placeholder='New Condition']", Arrays.asList("Új szabály", "New condition")),
	NEW_CONDITION_INPUT(SEARCH_PANEL + "//div[starts-with(@id, 'conditionselectorwithmenu')]//input[@placeholder='Új feltétel' or "
			+ "@placeholder='New Condition']", Arrays.asList("Új szabály", "New condition")),
	
	NEW_CONDITION_MENU("//div[starts-with(@id, 'menu-')][contains(@class, 'x-menu ')][last()]"),
//	NEW_CONDITION_MENU_ITEMS("//ul[contains(@id, 'searchconditionselector')]//li[contains(@class, 'x-boundlist-item')]"),
//	NEW_CONDITION_MENU_ITEMS("//div[contains(@class, 'x-menu-item-default')]//a[starts-with(@id, 'menuitem-')]//span"),
	
	NEW_CONDITION_MENU_ITEMS("//div[starts-with(@id, 'window-') and contains(@class, 'x-window-default-resizable')]/following-sibling::div[2]/div[(@class='x-menu-bodyWrap')]/div[(@data-ref='body')]/div[starts-with(@id, 'menu-') and contains(@id, '-innerCt')]/div[starts-with(@id, 'menu-') and contains(@id, '-targetEl')]/div[contains(@class, 'x-menu-item-default')]/a[starts-with(@id, 'menuitem-') and starts-with(@data-componentid, 'menuitem-')]/span[not(ancestor::div[contains(@style,'display: none')])]"),
	
	NEW_CONDITION_MENU_AFTER_SCROLLER(NEW_CONDITION_MENU + "//div[starts-with(@id, 'after-scroller')]"),

	// conditions
	CONDITIONS_CONTAINER(SEARCH_PANEL + "//*/div[contains(@id, 'conditionscontainer')][contains(@class, 'x-panel searchconditions-container')]"),
	ALL_CONDITIONS(CONDITIONS_CONTAINER + "//div[contains(@id, 'searchcondition-')][contains(@class, 'searchcondition-')]"),
	CONDITION_FIELD("./div/div/div/div/div[3]/div/div/div"),
	CONDITION_FIELD_RECORD_SELECTOR("./div/div/div[2]//fieldset"),
	
	CONDITION_FIELD_TO_EXTERNAL_IDENTIFIERS("//div[starts-with(@id, 'fieldset-') and contains(@id, '-innerCt')]/div/div/div/div/input"),
	
	CONDITION_ACTIVE_CHECKBOX(".//input[@type='checkbox']"),
	CONDITION_LABEL("./div/div/div/div/label"),
	ALL_CONDITION_LABELS("//div[contains(@id, 'searchcondition-')]//label[contains(@class, 'x-component')]"),
	CONDITION_DELETE("./div/div/div/div/a[contains(@class, ' deleteButton ')]"),
//	CONDITION_DELETE(".//a[contains(@class, ' deleteButton ')]"),
	LAST_COUPLED_CONDITION_NEW(SEARCH_PANEL + "//div[starts-with(@id, 'coupledsearchcondition')]//a[contains(@id, 'searchconditionselector')][last()]"),

	// Save, Save and run buttons
	CANCEL_BUTTON(SEARCH_PANEL + "//span[text()='Mégse' or text()='Cancel']", Arrays.asList("Mégse", "Cancel")),
	SAVE_BUTTON(SEARCH_PANEL + "//span[text()='Mentés' or text()='Save']", Arrays.asList("Mentés", "Save")),
	SAVE_AND_RUN_BUTTON(SEARCH_PANEL + "//span[text()='Mentés és keresés' or text()='Save and run'])",
			Arrays.asList("Mentés és keresés", "Save and run"));

	private final String locator;
	List<String> names;

	/**
	 * Constructor
	 * 
	 * @param itemId
	 */
	private QueryEditorLocators(final String locator) {
		this.locator = locator;
	}

	/**
	 * Constructor
	 * 
	 * @param itemId
	 * @param englishName
	 * @param hungarianName
	 */
	private QueryEditorLocators(final String locator, final List<String> names) {
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
}
