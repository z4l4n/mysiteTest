package com.frontendart.managers.main.leftpanel;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.frontendart.common.Utils;
import com.frontendart.locators.general.GeneralLocatorTypes;
import com.frontendart.locators.main.MainPageLocators;
import com.frontendart.locators.main.leftpanel.QueryEditorLocators;
import com.frontendart.locators.main.leftpanel.TextFieldSearchOptions;
import com.frontendart.locators.records.attributes.general.FieldTypeLocators;
import com.frontendart.locators.records.attributes.general.GeneralRecordTypes;
import com.frontendart.locators.records.attributes.general.GeneralTableAttributes;
import com.frontendart.managers.general.GeneralDataProvider;
import com.frontendart.managers.main.leftpanel.SearchEditorManager.SearchMatchType;

/**
 * Manager class for query editor tests
 *
 * TODO: refaktor
 * 
 * @author Zoli
 *
 */
public class SearchEditorManager {

	enum SearchCondition {
		ANY, EVERY
	}
	
	enum SearchMatchType {
		PREFIX_MATCH,
		POSTFIX_MATCH,
		ANY_MATCH,
		ANY_MATCH_IN_WORDS,
		EXACT_MATCH,
		NOT_EXACT_MATCH,
		IS_NULL,
		NOT_NULL,
		ME,
		ME_OR_DELEGATED
	}
	
	/**
	 * Logger
	 */
	private static final Logger LOGGER = LogManager.getLogger("QueryEditorManager");

	/**
	 * Close query editor panel
	 * 
	 */
	public static void closeQueryEditor() {
		LOGGER.info("Kattintsunk a Keresés összeállítás panel bezárás gombjára.");
		Utils.waitForAndClickOnGeneralWebElement(QueryEditorLocators.HEADER_CLOSE);
	}
	
	/**
	 * Close query editor panel
	 * 
	 */
	public static void clickOnCancelButton() {
		Utils.waitForAndClickOnGeneralWebElement(QueryEditorLocators.CANCEL_BUTTON);
	}

	/**
	 * Get is met button as Webelement
	 * 
	 */
	public static WebElement getIsMetButtonAsWebElement() {
		return Utils.createGeneralWebElementFromEnum(QueryEditorLocators.IS_MET_BUTTON);
	}

	/**
	 * Get every condition button as Webelement
	 * 
	 */
	public static WebElement getEveryConditionButtonAsWebElement() {
		return Utils.createGeneralWebElementFromEnum(QueryEditorLocators.EVERY_CONDITION_BUTTON);
	}

	public static void changeConditionButton(SearchCondition searchCondition) {
		getEveryConditionButtonAsWebElement().click();
		switch (searchCondition) {
		case ANY:
			Utils.waitForAndClickOnGeneralWebElement(QueryEditorLocators.ANY_CONDITION_OPTION);
			break;
		case EVERY:
			Utils.waitForAndClickOnGeneralWebElement(QueryEditorLocators.EVERY_CONDITION_OPTION);
			break;
		default:
			Assert.fail("Not handled search condition");
			break;
		}
	}
	
	/**
	 * Get match selector button as Webelement
	 * 
	 */
	private static void clickLastMatchSelectorButton() {
		// Get last condition
		final List<WebElement> conditions = getConditionsAsWebElements();
		final WebElement myNewCondition = conditions.get(conditions.size() - 1);
		myNewCondition.findElement(By.xpath(QueryEditorLocators.MATCH_SELECTOR_CYCLE.toString())).click();;
	}
	
	/**
	 * Change match selector type of the last condition
	 * @param searchMatchType type of condition
	 */
	public static void changeMatchType(SearchMatchType searchMatchType) {
		clickLastMatchSelectorButton();
		QueryEditorLocators selectedLocator = null;
		switch (searchMatchType) {
		case PREFIX_MATCH:
			selectedLocator = QueryEditorLocators.MATCH_SELECTOR_PREFIX_MATCH_OPTION;
			break;
		case POSTFIX_MATCH:
			selectedLocator = QueryEditorLocators.MATCH_SELECTOR_POSTFIX_MATCH_OPTION;
			break;
		case ANY_MATCH:
			selectedLocator = QueryEditorLocators.MATCH_SELECTOR_ANY_MATCH_OPTION;
			break;
		case ANY_MATCH_IN_WORDS:
			selectedLocator = QueryEditorLocators.MATCH_SELECTOR_ANY_MATCH_IN_WORDS_OPTION;
			break;
		case EXACT_MATCH:
			selectedLocator = QueryEditorLocators.MATCH_SELECTOR_EXACT_MATCH_OPTION;
			break;
		case NOT_EXACT_MATCH:
			selectedLocator = QueryEditorLocators.MATCH_SELECTOR_NOT_EXACT_MATCH_OPTION;
			break;
		case IS_NULL:
			selectedLocator = QueryEditorLocators.MATCH_SELECTOR_IS_NULL_OPTION;
			break;
		case NOT_NULL:
			selectedLocator = QueryEditorLocators.MATCH_SELECTOR_NOT_NULL_OPTION;
			break;
		case ME:
			selectedLocator = QueryEditorLocators.MATCH_SELECTOR_ME_OPTION;
			break;
		case ME_OR_DELEGATED:
			selectedLocator = QueryEditorLocators.MATCH_SELECTOR_ME_OR_DELEGATED_OPTION;
			break;
		default:
			Assert.fail("Not handled match type in condition");
			break;
		}
		Utils.waitForAndClickOnGeneralWebElement(selectedLocator);
	}
	
	/**
	 * Clicks on new condition button
	 */
	public static WebElement clickOnNewConditionButton() {
		Utils.waitForElementVisible(QueryEditorLocators.NEW_CONDITION_INPUT);
		Utils.defaultWait();
		WebElement element = Utils.createGeneralWebElementFromEnum(QueryEditorLocators.NEW_CONDITION_INPUT);
		element.sendKeys(Keys.ARROW_DOWN);
		return element;
	}

	/**
	 * Selects attribute from menu
	 * @param attribute
	 */
	public static void selectThisAttributeFromConditionsMenu(final GeneralTableAttributes attribute) {
		clickOnNewConditionButton();

		final WebElement activeElement = Utils.switchToActiveElement();
		Utils.defaultWait();
		activeElement.sendKeys(Keys.ARROW_DOWN);
		Utils.defaultWait();
//		activeElement.sendKeys(Keys.ARROW_UP);
//		Utils.defaultWait();

		// Get subelements
		final List<WebElement> subElements = Utils.createGeneralWebElementsFromEnum(QueryEditorLocators.NEW_CONDITION_MENU_ITEMS);
		final int sizeOfSubelements = subElements.size();
		
		// Language check (If English is the active, we should check for the English name of the attribute)
		int nameIndex = 0;
		final String language = Utils.createGeneralWebElementFromEnum(MainPageLocators.CHANGE_LANGUAGE_BUTTON).getText();
		if ("English".equals(language)) {
			nameIndex = 1;
		}

		// Start search
		
		int index = 0;
		boolean found = false;
		while ((!found) && (index < sizeOfSubelements)) {

			final WebElement activeMenuItem = subElements.get(sizeOfSubelements - (index + 1));
			final String label = activeMenuItem.getAttribute("textContent").trim();
			
			// Check
			if (label.equals(attribute.getNames().get(nameIndex))) {
				Utils.scrollIntoView(activeMenuItem);
				activeMenuItem.click();
				found = true;
			} else {
//				activeElement.sendKeys(Keys.ARROW_UP);
				//activeMenuItem.sendKeys(Keys.ARROW_UP);
				index++;
			}
		}

		if (!found) {
			closeQueryEditor();
			fail("The following attribute has not been found: " + Utils.getAllMyLabelsAsString(attribute));
		}

	}

	/**
	 * Add new condition
	 * 
	 * @param columnType 
	 * 
	 */
	public static GeneralTableAttributes addConditionWithThisAttribute(final GeneralTableAttributes attribute) {
		final Map<GeneralTableAttributes, String> attributeValues = new HashMap<GeneralTableAttributes, String>();
		attributeValues.put(attribute, "");
		addConditionWithThisAttribute(attributeValues);

		return attribute;
	}

	/**
	 * Add new condition
	 * 
	 * @param columnType 
	 * 
	 */
	public static GeneralTableAttributes addConditionWithThisAttribute(final Map<GeneralTableAttributes, String> attributeValues) {
		final GeneralTableAttributes attribute = attributeValues.keySet().iterator().next();
		LOGGER.info("Az Új feltétel/New Condition legördülő menüből válasszuk ki a következő elemet: " + Utils.getAllMyLabelsAsString(attribute));

		// Click on new condition button, and select menuitem
		selectThisAttributeFromConditionsMenu(attribute);

		// Check for errors window
		Utils.checkForErrorsWindow();

		// add value to condition
		final List<WebElement> conditions = getConditionsAsWebElements();
		final WebElement myNewCondition = conditions.get(conditions.size() - 1);
		WebElement field = myNewCondition;
		if (attribute.getFieldType() != FieldTypeLocators.NO_INPUT && attribute.getFieldType() != FieldTypeLocators.RECORDSELECTORFIELD && attribute.getFieldType() != FieldTypeLocators.RECORDSELECTORLISTFIELD) {
			field = myNewCondition.findElement(By.xpath(QueryEditorLocators.CONDITION_FIELD.toString()));
		}
		
		// Set value (if there is no value provided, then it will be a random value
		final String value = attributeValues.values().iterator().next();
		if ("".equals(value)) {
			GeneralDataProvider.setRandomValueToThisAttribute(field, attribute);
		} else {
			GeneralDataProvider.setThisValueToAttribute(field, attributeValues);
		}

		return attribute;
	}
	
	/**
	 * Add new external identifiers condition
	 * 
	 * @param columnType 
	 * 
	 */
	public static GeneralTableAttributes addExternalIdentifierConditionWithThisAttribute(final Map<GeneralTableAttributes, String> attributeValues) {
		final GeneralTableAttributes attribute = attributeValues.keySet().iterator().next();
		LOGGER.info("Az Új feltétel/New Condition legördülő menüből válasszuk ki a következő elemet: " + Utils.getAllMyLabelsAsString(attribute));

		// Click on new condition button, and select menuitem
		selectThisAttributeFromConditionsMenu(attribute);
		
		SearchEditorManager.changeMatchType(SearchMatchType.ANY_MATCH);

		// Check for errors window
		Utils.checkForErrorsWindow();

		// add value to condition
		final List<WebElement> conditions = getConditionsAsWebElements();
		final WebElement myNewCondition = conditions.get(conditions.size() - 1);
		WebElement field = myNewCondition;
		if (attribute.getFieldType() != FieldTypeLocators.NO_INPUT && attribute.getFieldType() != FieldTypeLocators.RECORDSELECTORFIELD && attribute.getFieldType() != FieldTypeLocators.RECORDSELECTORLISTFIELD) {
			//field = myNewCondition.findElement(By.xpath(QueryEditorLocators.CONDITION_FIELD.toString()));
			field = Utils.createGeneralWebElementFromEnum(QueryEditorLocators.CONDITION_FIELD_TO_EXTERNAL_IDENTIFIERS);
			
		}
		
		// Set value (if there is no value provided, then it will be a random value
		final String value = attributeValues.values().iterator().next();
		if ("".equals(value)) {
			GeneralDataProvider.setRandomValueToThisAttribute(field, attribute);
		} else {
			GeneralDataProvider.setThisValueToAttribute(field, attributeValues);
		}

		return attribute;
	}

	/**
	 * returns all condition options as string
	 * @return
	 *
	 */
	public static List<String> getAllConditionOptionsAsString() {
		// Click on new condition button, and get options
		Utils.defaultWait();
		clickOnNewConditionButton();

		final List<WebElement> subElements = Utils.createGeneralWebElementsFromEnum(QueryEditorLocators.NEW_CONDITION_MENU_ITEMS);
		final List<String> optionsAsString = new ArrayList<String>();
		for (int index = 0; index < subElements.size(); index++) {

			final WebElement activeMenuItem = subElements.get(index);
			
			if (activeMenuItem.getText().isEmpty()) {
				// scrolls to the last not empty option to load the followings
				Utils.scrollIntoView(subElements.get(index - 1));
			}
			optionsAsString.add(activeMenuItem.getText());

		}

		// Close search options
//		Utils.createGeneralWebElementFromEnum(QueryEditorLocators.NEW_CONDITION_INPUT).sendKeys(Keys.ESCAPE);
		Utils.createGeneralWebElementFromEnum(QueryEditorLocators.NEW_CONDITION_INPUT).sendKeys(Keys.ARROW_UP);
		
		return optionsAsString;
	}

	/**
	 * Add new condition
	 * @param columnType 
	 * 
	 * TODO: return key-value pairs? (attribute-value)
	 * 
	 */
	public static GeneralTableAttributes addRandomCondition(final GeneralRecordTypes myGeneralRecordType) {
		final GeneralTableAttributes attribute = GeneralRecordTypes.selectRandomSearchableAttribute(myGeneralRecordType);
		addConditionWithThisAttribute(attribute);
		return attribute;

	}

	/**
	 * Get conditions as Webelements
	 * 
	 */
	public static List<WebElement> getConditionsAsWebElements() {
		return Utils.createGeneralWebElementsFromEnum(QueryEditorLocators.ALL_CONDITIONS);
	}

	/**
	 * Get number of conditions
	 * 
	 */
	public static int getNumberOfConditions() {
		return getConditionsAsWebElements().size();
	}

	/**
	 * Get the label of condition
	 * 
	 */
	public static List<WebElement> getAllConditionLabels() {
		return Utils.createGeneralWebElementsFromEnum(QueryEditorLocators.ALL_CONDITION_LABELS);
	}

	/**
	 * Returns the index of condition
	 * 
	 * @param locator
	 * @return
	 */
	public static int getConditionIndex(final GeneralTableAttributes attribute) {
		final List<WebElement> conditionLabels = getAllConditionLabels();
		final List<String> conditionLabelsAsString = Utils.convertThisWebElementArrayToStringArray(conditionLabels);
		
//		final int returnValue = conditionLabelsAsString.indexOf(attribute.getNames().get(0).concat(":"));
//		return (returnValue == -1) ? conditionLabelsAsString.indexOf(attribute.getNames().get(1).concat(":")) : returnValue;
		
		final int returnValue = conditionLabelsAsString.indexOf(attribute.getNames().get(0));
		return (returnValue == -1) ? conditionLabelsAsString.indexOf(attribute.getNames().get(1)) : returnValue;
	}

	/**
	 * Removes condition by locator name
	 * @param locator
	 *
	 */
	public static void removeCondition(final GeneralTableAttributes locator) {
		final List<WebElement> conditions = getConditionsAsWebElements();
		LOGGER.info("A feltételek közül töröljük azt, aminek a következő a felirata: " + Utils.getAllMyLabelsAsString(locator));
		final int index = getConditionIndex(locator);
		
		conditions.get(index).findElement(By.xpath(QueryEditorLocators.CONDITION_DELETE.toString())).click();
		Utils.acceptMessageBoxIfVisible();
	}

	/**
	 * Clicks on active checkbox
	 * @param locator
	 * 
	 */
	public static void clickOnActiveCheckbox(final GeneralTableAttributes locator) {
		final List<WebElement> conditions = getConditionsAsWebElements();
		LOGGER.info("Kattintsunk annak a feltételnek a sor elején lévő checkbox-ába, amelyik feltételnek a következő a felirata: " + Utils.getAllMyLabelsAsString(locator));
		final int index = getConditionIndex(locator);
		conditions.get(index).findElement(By.xpath(QueryEditorLocators.CONDITION_ACTIVE_CHECKBOX.toString())).click();
	}

	/**
	 * checks if condition is present in conditions (by locator name = conditionLabel)
	 */
	public static boolean isConditionPresent(final GeneralTableAttributes locator) {
		final List<WebElement> conditions = getConditionsAsWebElements();
		boolean isPresent = false;

		if (getConditionIndex(locator) <= conditions.size()) {
			isPresent = true;
		}

		return isPresent;
	}

	/**
	 * Gets the value of the last condition
	 * 
	 * TODO: locators
	 * @param locator
	 * @return
	 *
	 */
	public static String getValueFromLastCondition(final GeneralTableAttributes attribute) {
		final List<WebElement> conditions = getConditionsAsWebElements();
		final WebElement myNewCondition = conditions.get(conditions.size() - 1);
		final WebElement field = myNewCondition.findElement(By.xpath(QueryEditorLocators.CONDITION_FIELD.toString()));

		return GeneralDataProvider.getCurrentValueOfThisField(field, attribute);

	}

	/**
	 * Select option from search option
	 * 
	 * TODO: locators
	 * @param locator
	 * @return
	 *
	 */
	// TODO: randomize
	public static void selectThisOptionFromSortSearchOption(final TextFieldSearchOptions option) {
		final List<WebElement> conditions = getConditionsAsWebElements();
		final WebElement myNewCondition = conditions.get(conditions.size() - 1);
		final WebElement selector = myNewCondition.findElement(By.xpath(".//*/a"));
		String text = selector.findElement(By.xpath("./span/span/span[2]")).getText();

		while ((!text.contains(option.getValue().get(0))) && (!text.contains(option.getValue().get(1)))) {
			selector.click();
			text = selector.findElement(By.xpath("./span/span/span[2]")).getText();
		}
	}

	/**
	 * Changes the "is met"/"is not met" selector of a query
	 * 
	 * TODO: now it selects is not met condition...
	 * @param locator
	 * @return
	 *
	 */
	public static void changeIsMetConditionOfThisQuery(final String name) {
		SearchManager.openQueryEditorByName(name);
		LOGGER.info("Kattintsunk a teljesül/is met gombra.");
		getIsMetButtonAsWebElement().click();
		Utils.switchToActiveElement().sendKeys(Keys.DOWN);
		Utils.switchToActiveElement().sendKeys(Keys.DOWN);
		Utils.switchToActiveElement().sendKeys(Keys.ENTER);
		SearchManager.clickSaveButton();
		Utils.defaultWait();
	}

	/**
	 * Click on new condition field, type the desired condition and checks the visible dropdown for result
	 * @param attribute condition to find
	 * @return true if the option found
	 */
	public static boolean searchForOption(GeneralTableAttributes attribute) {
		Utils.writeTextToThisField(attribute.getNames().get(0), QueryEditorLocators.NEW_CONDITION_INPUT);
		Utils.defaultWait();
		final List<WebElement> subElements = Utils.createGeneralWebElementsFromEnum(QueryEditorLocators.NEW_CONDITION_MENU_ITEMS);
		
		// check the list has visible element
		return !subElements.isEmpty() && subElements.get(0).isDisplayed();
	}

}
