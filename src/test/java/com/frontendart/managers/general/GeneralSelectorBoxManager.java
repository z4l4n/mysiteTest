package com.frontendart.managers.general;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.frontendart.common.Utils;
import com.frontendart.locators.general.GeneralSelectorBoxLocators;
import com.frontendart.locators.records.attributes.general.GeneralRecordTypes;
import com.frontendart.managers.main.rightpanel.crud.CreateRecordManager;

/**
 * General manager class for selectorbox
 * 
 * @author Zoli
 *
 */
public class GeneralSelectorBoxManager {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LogManager.getLogger("GeneralSelectorBoxManager");

	/**
	 * Selects from recordselectionlistfield - which means either that it selects from selectorbox, or fills a form
	 * 
	 * @param driver
	 */
	public static void selectFromRecordSelector() {
		// If it's a selectbox, then select from it
		if (Utils.isThisElementPresent(GeneralSelectorBoxLocators.SELECTOR_BOX)) {
			selectRandomFromSelectbox();
		} else {
			// Otherwise it should be an editor - Get record type, and fill form
			CreateRecordManager.getEditorHeaderAndFillFields();
		}
	}

	private static List<WebElement> getSelectboxItems() {
		List<WebElement> itemsOfSelectorBox =  getItemsOfSelectorBox();
		
		if (itemsOfSelectorBox.isEmpty()) {
			itemsOfSelectorBox= getGridItemsOfSelectorBox();
		}
		
		return itemsOfSelectorBox;
	}
	
	/**
	 * Select random from available selectbox which can contain gridview elements. First search for tableviews than gridview.
	 */
	public static void selectRandomFromSelectbox() {
		List<WebElement> itemsOfSelectorBox =  getSelectboxItems();
		
		if (itemsOfSelectorBox.isEmpty()) {
			itemsOfSelectorBox= getGridItemsOfSelectorBox();
		}
		
		// Filter
		if (itemsOfSelectorBox.isEmpty()) {
			Utils.waitForAndClickOnGeneralWebElement(GeneralSelectorBoxLocators.SELECTOR_BOX_FILTER_BUTTON);
			Utils.defaultWait();
			itemsOfSelectorBox = getSelectboxItems();
		}
		
		// Create
		if (itemsOfSelectorBox.isEmpty()) {
			// Get record type
			final String header = Utils.createGeneralWebElementFromEnum(GeneralSelectorBoxLocators.SELECTOR_BOX_HEADER).getText();
			final String recordTypeName = header.split(":")[1].substring(1);

			// Click on New button, and create one new record
			Utils.waitForAndClickOnGeneralWebElement(GeneralSelectorBoxLocators.SELECTOR_BOX_NEW_BUTTON);
			CreateRecordManager.fillFields(GeneralRecordTypes.findAttributesTypeByName(recordTypeName));
			CreateRecordManager.clickOnSaveAndCloseButton();
			Utils.defaultWait();
		}

		// TODO: size
		int size = itemsOfSelectorBox.size();
		if (size > 20) {
			size = 20;
		}

		final WebElement selectedOption = itemsOfSelectorBox.get(Utils.randInt(0, size - 1));
		Utils.clickOnThisRow(selectedOption);
		Utils.defaultWait();
		LOGGER.info("Válasszuk ki az alábbi opciót: " + selectedOption.findElement(By.xpath(".//td/div")).getText());
		Utils.waitForAndClickOnGeneralWebElement(GeneralSelectorBoxLocators.SELECTOR_BOX_SAVE_AND_CLOSE_BUTTON);
		
	}

	/**
	 * Select this text from available selectbox
	 */
	public static void selectThisFromSelectbox(final String text) {
		final List<WebElement> items = getItemsOfSelectorBox();

		for (final WebElement item : items) {
			final String value = item.findElement(By.xpath("./tbody/tr/td/div")).getText();
			if (value.equals(text)) {
				Utils.clickOnThisRow(item);
			}
		}

		Utils.waitForAndClickOnGeneralWebElement(GeneralSelectorBoxLocators.SELECTOR_BOX_SAVE_AND_CLOSE_BUTTON);
	}

	/**
	 * Returns the items of the selectorbox on page. If its empty, it creates one item
	 * @param selectorBox
	 * @return
	 */
	public static List<WebElement> getItemsOfSelectorBox() {
		return Utils.createGeneralWebElementsFromEnum(GeneralSelectorBoxLocators.SELECTOR_BOX_ITEMS);
	}
	
	/**
	 * Return grid items found on the current selectorbox if any.
	 * @return list of WebElements
	 */
	public static List<WebElement> getGridItemsOfSelectorBox() {
		return Utils.createGeneralWebElementsFromEnum(GeneralSelectorBoxLocators.SELECTOR_BOX_GRID_ITEMS);
	}
}
