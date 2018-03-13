package com.frontendart.managers.general;

import static org.junit.Assert.assertFalse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.frontendart.common.Utils;
import com.frontendart.locators.general.GeneralPageLocators;
import com.frontendart.locators.general.GeneralSelectorBoxLocators;
import com.frontendart.locators.records.attributes.general.FieldTypeLocators;
import com.frontendart.locators.records.attributes.general.GeneralTableAttributes;

/**
 * General manager class for adding, editing, removing records
 * 
 * @author Zoli
 *
 */
public class GeneralDataProvider {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LogManager.getLogger("GeneralRecordManager");

	/**
	 * Gets field to edit
	 * @param attribute
	 * @param field
	 * @return
	 */
	public static WebElement getFieldToEdit(final GeneralTableAttributes attribute, final WebElement field) {
		return field.findElement(By.xpath(attribute.getFieldType().getEditableFieldXpath()));
	}
	
	public static boolean getFieldToEditIsEnabled(final GeneralTableAttributes attribute, final WebElement field) {
		boolean isEnabled = true;
		
//		if(attribute.getFieldType().equals(FieldTypeLocators.CHECKBOXFIELD)){
//			if(field.findElement(By.xpath(attribute.getFieldType().getEditableFieldXpath()+"/parent::span/parent::div/parent::div")).getAttribute("class").contains("x-item-disabled")){
//				isEnabled = false;
//			}
//		}
//		
//		else {
		if(!attribute.getFieldType().equals(FieldTypeLocators.CHECKBOXFIELD)){
			if(field.findElement(By.xpath(attribute.getFieldType().getEditableFieldXpath()+"/parent::div/parent::div/parent::div")).getAttribute("class").contains("x-item-disabled")){
				isEnabled = false;
			}
		}
//		}
		
		
		return isEnabled;
	}

	/**
	 * Gives a random value for this attribute
	 */
	public static String getRandomValueForThisAttribute(final GeneralTableAttributes attribute) {
		return attribute.getFieldType().getRandomValue();
	}

	/**
	 * writes random value to field
	 * @param findLocator
	 * @param field
	 * 
	 */
	public static String setRandomValueToThisAttribute(final WebElement field, final GeneralTableAttributes attribute) {
		final String randomValue = getRandomValueForThisAttribute(attribute);
		final Map<GeneralTableAttributes, String> attributeValues = new HashMap<GeneralTableAttributes, String>();
		attributeValues.put(attribute, randomValue);
		setThisValueToAttribute(field, attributeValues);

		return randomValue;
	}
	
	public static String setValueToThisAttribute(final WebElement field, final GeneralTableAttributes attribute, String value) {
		final Map<GeneralTableAttributes, String> attributeValues = new HashMap<GeneralTableAttributes, String>();
		attributeValues.put(attribute, value);
		setThisValueToAttribute(field, attributeValues);

		return value;
	}

	/**
	 * writes exact value to field
	 * @param findLocator
	 * @param field
	 * 
	 */
	public static void setThisValueToAttribute(final WebElement field, final Map<GeneralTableAttributes, String> attributeValues) {
		// Finds the appropriate field to edit (by label)
		final GeneralTableAttributes attribute = attributeValues.keySet().iterator().next();
		final String value = attributeValues.values().iterator().next();
		final WebElement fieldToEdit = getFieldToEdit(attribute, field);
		
		if(getFieldToEditIsEnabled(attribute, field)==true){

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fieldToEdit.click();

			// Add value by field type
			switch (attribute.getFieldType()) {
			case CHECKBOXFIELD:
				//fieldToEdit.click();
				break;
			case STATUS_TYPE_COMBOBOX:
			case IMPORT_ALIAS_TYPE_COMBOBOX:
			case ENUMSELECTORCOMBOBOX:
				fieldToEdit.sendKeys(Keys.DOWN);
				selectThisValueFromCombobox(attribute, value);
				break;
			case RECORDSELECTORLISTFIELD:
			case RECORDSELECTORFIELD:
				Utils.waitMillisec(5000);
				LOGGER.info("Kattintsunk a " + Utils.getAllMyLabelsAsString(attribute) + " attribútumnál látható nagyító (vagy '+') ikonra.");
				GeneralSelectorBoxManager.selectFromRecordSelector();
				break;
			case AFFILIATION:
				Utils.waitMillisec(3000);
				final List<WebElement> items = Utils.createGeneralWebElementsFromEnum(GeneralSelectorBoxLocators.FIND_INSTITUTES_ITEMS);
				Utils.clickOnThisRow(items.get(Utils.randInt(1, items.size()) - 1));
				Utils.waitForAndClickOnGeneralWebElement(GeneralSelectorBoxLocators.FIND_INSTITUTES_SAVE_AND_CLOSE_BUTTON);
				break;
			case NO_INPUT:
				break;
			default:
				writeToTextField(attribute, fieldToEdit, value);
				break;
			}
		}
		
		
	}

	/**
	 * Select value form combobox
	 * 
	 * TODO: with locator...
	 * 
	 * @param attribute
	 * @param fieldToEdit
	 */
	public static void selectThisValueFromCombobox(final GeneralTableAttributes attribute, final String value) {
		// Checks for options...
		Utils.waitForElementPresent(GeneralPageLocators.COMBOBOX_OPTIONS);
		final List<WebElement> options = Utils.createGeneralWebElementsFromEnum(GeneralPageLocators.COMBOBOX_OPTIONS);

		// There should be options in the combobox.
		assertFalse("There are no options in this combobox!", options.isEmpty());

		final List<String> optionsAsString = Utils.convertThisWebElementArrayToStringArray(options);
		String selectedOption = "";

		if ("".equals(value)) {
			final int random = Utils.randInt(0, options.size() - 1);
			selectedOption = optionsAsString.get(random);
			options.get(random).click();
		} else {
			selectedOption = value;
			options.get(optionsAsString.indexOf(value)).click();
		}

		LOGGER.info("Válasszuk ki az alábbi értéket a " + Utils.getAllMyLabelsAsString(attribute) + " legördülő menüből: " + selectedOption);
	}

	/**
	 * Write text to field
	 * @param attribute
	 * @param fieldToEdit
	 * @param value
	 */
	public static void writeToTextField(final GeneralTableAttributes attribute, final WebElement fieldToEdit, final String value) {
		LOGGER.info("Töröljük a(z) " + Utils.getAllMyLabelsAsString(attribute) + " mező tartalmát");
		Utils.waitMillisec(500);
		if (!" ".equals(value)) {
			LOGGER.info("Írjuk be a(z) " + Utils.getAllMyLabelsAsString(attribute) + " mezőbe a következő értéket: " + value);
			fieldToEdit.clear();
			fieldToEdit.sendKeys(value);
		}
	}

	/**
	 * Returns the current value of field
	 * @param driver
	 * @param attribute
	 * @return
	 */
	public static String getCurrentValueOfThisField(final WebElement field, final GeneralTableAttributes attribute) {
		final WebElement fieldToEdit = getFieldToEdit(attribute, field);
		return fieldToEdit.getAttribute("value");
	}

}
