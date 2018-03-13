package com.frontendart.managers.main.rightpanel.crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.frontendart.common.Utils;
import com.frontendart.locators.main.MainPageLocators;
import com.frontendart.locators.main.rightpanel.crud.RecordEditorLocators;
import com.frontendart.locators.main.rightpanel.crud.UpdateRecordLocators;
import com.frontendart.locators.main.rightpanel.crud.UpdateTypes;
import com.frontendart.locators.main.rightpanel.view.ToolbarLocators;
import com.frontendart.locators.records.attributes.general.GeneralRecordTypes;
import com.frontendart.locators.records.attributes.general.GeneralTableAttributes;
import com.frontendart.managers.general.GeneralDataProvider;
import com.frontendart.managers.general.GeneralTableManager;
import com.frontendart.managers.main.leftpanel.RecordSelectionManager;
import com.frontendart.managers.main.rightpanel.view.ChangeViewManager;

/**
 * Manager class for editing records
 * 
 * @author Zoli
 *
 */
public class UpdateRecordManager {

	/**
	 * do form update
	 * 
	 */
	public static void doFormUpdate() {
		doUpdate(UpdateTypes.FORM);
	}

	/**
	 * do form update
	 * 
	 */
	public static void doInlineUpdate() {
		doUpdate(UpdateTypes.INLINE);
	}

	/**
	 * Do update
	 */
	public static void doUpdate(final UpdateTypes updateType) {
		// Select record type and go there
		final GeneralRecordTypes recordType = GeneralRecordTypes.PUBLICATION;
		RecordSelectionManager.selectThisRecordTypeFromSelector(recordType);

		// Show all columns
		Utils.waitForAndClickOnGeneralWebElement(ToolbarLocators.CHANGE_VIEW_GRID);
		Utils.waitForAndClickOnGeneralWebElement(ToolbarLocators.CUSTOM_VIEW_MENU_ALL_COLUMNS);

		// Get mtID of selected row
		final String label = Utils.createGeneralWebElementFromEnum(MainPageLocators.CHANGE_LANGUAGE_BUTTON).getText();
		if ("English".equals(label)) {
			Utils.waitForAndClickOnGeneralWebElement(MainPageLocators.CHANGE_LANGUAGE_BUTTON);
			Utils.waitForAndClickOnGeneralWebElement(MainPageLocators.CHANGE_LANGUAGE_HUNGARIAN);
			Utils.waitMillisec(10000);
		}
		List<String> oldVisibleHeaders = GeneralTableManager.getAllVisibleHeadersText();
		if (!oldVisibleHeaders.contains("MTMT azonosító")) {
			GeneralTableManager.changeTheVisibilityOfThisHeader("MTMT azonosító");
		}
		// Select random attribute
		final GeneralTableAttributes attributeToEdit = selectRandomNotDisabledAttribute(recordType);
		final int attributePosition = GeneralTableManager.getColPos(attributeToEdit);

		// Get old value
		WebElement selectedRow = RecordSelectionManager.selectRandomRenderedRowsFromGridPanel(1).get(0);
		final int selectedID = GeneralTableManager.getIDOfThisRow(selectedRow);
		final String oldValue = getValueOfRow(selectedRow, attributePosition);

		// Start to edit
		String value = "";
		if (updateType.equals(UpdateTypes.FORM)) {
			clickOnEditButton();
			value = performFormEditWithRandomValue(attributeToEdit);
		} else {
			value = performInlineEditWithRandomValue(selectedRow, attributeToEdit);
		}

		// Refresh and find same row (by ID)
		refresh();
		ChangeViewManager.switchToGridView();
		oldVisibleHeaders = GeneralTableManager.getAllVisibleHeadersText();
		if (!oldVisibleHeaders.contains("MTMT azonosító")) {
			GeneralTableManager.changeTheVisibilityOfThisHeader("MTMT azonosító");
		}
		selectedRow = GeneralTableManager.findThisRecordByID(selectedID);
		Utils.clickOnThisRow(selectedRow);

		// Validate
		final String newValue = getValueOfRow(selectedRow, attributePosition);
		assertNotEquals("The two values should not equal! Edited attribute is: " + attributeToEdit, oldValue, newValue);
		assertEquals("The value is not correct!", value, newValue);

		// Cleanup: Set old value inline
		final Map<GeneralTableAttributes, String> attributeValues = new HashMap<GeneralTableAttributes, String>();
		attributeValues.put(attributeToEdit, oldValue);
		if (updateType.equals(UpdateTypes.FORM)) {
			clickOnEditButton();
			value = performFormEditWithValue(attributeValues);
		} else {
			value = performInlineEditWithValue(selectedRow, attributeValues);
		}
	}

	/**
	 * Refresh
	 */
	public static void refresh() {
		Utils.refresh();
		RecordSelectionManager.waitUntilTableIsReady();
	}

	/**
	 * Select an attribute which is not disabled
	 */
	public static GeneralTableAttributes selectRandomNotDisabledAttribute(final GeneralRecordTypes myGeneralRecordType) {
		final List<GeneralTableAttributes> notDisabledAttributes = GeneralRecordTypes.getNotDisabledAttributes(myGeneralRecordType.getAttributes());
		return notDisabledAttributes.get(Utils.randInt(0, notDisabledAttributes.size() - 1));
	}

	/**
	 * Clicks on Edit button
	 */
	public static void clickOnEditButton() {
		Utils.waitForAndClickOnGeneralWebElement(UpdateRecordLocators.EDIT_BUTTON);
		Utils.waitMillisec(1000);
	}

	/**
	 * 
	 * @param recordType
	 * @param columnPosition
	 */
	public static String getValueOfRow(final WebElement row, final int columnPosition) {
		return row.findElement(By.xpath("./tbody/tr/td[" + columnPosition + "]")).getText();
	}

	/**
	 * Double clicks on cell of first row
	 * 
	 * @param columnPosition
	 */
	public static String performInlineEditWithRandomValue(final WebElement row, final GeneralTableAttributes attribute) {
		final Map<GeneralTableAttributes, String> attributeValues = new HashMap<GeneralTableAttributes, String>();
		attributeValues.put(attribute, Utils.randomString());

		return performInlineEditWithValue(row, attributeValues);
	}

	/**
	 * Double clicks on cell of first row
	 * @param columnPosition
	 */
	public static String performInlineEditWithValue(final WebElement row, final Map<GeneralTableAttributes, String> attributeValues) {
		// Get attribute position and double-click on cell
		final GeneralTableAttributes columnLocator = attributeValues.keySet().iterator().next();
		final String value = attributeValues.values().iterator().next();

		final int columnPosition = GeneralTableManager.getColPos(columnLocator);
		final WebElement myCellToEdit = row.findElement(By.xpath("./tbody/tr/td[" + columnPosition + "]/div"));
		final Actions myAction = Utils.createActionForDriver();
		myAction.doubleClick(myCellToEdit).build().perform();

		// Get field to edit
		final WebElement field = Utils.createGeneralWebElementsFromEnum(UpdateRecordLocators.ROW_EDITOR_ALL_FIELDS).get(columnPosition - 1);
		final WebElement fieldToEdit = GeneralDataProvider.getFieldToEdit(columnLocator, field);

		// Write text
		fieldToEdit.clear();
		fieldToEdit.sendKeys(value);
		clickOnRowEditorSaveButton();

		return value;
	}

	/**
	 * Clicks on row editor save button
	 */
	public static void clickOnRowEditorSaveButton() {
		Utils.waitForAndClickOnGeneralWebElement(UpdateRecordLocators.ROW_EDITOR_SAVE_BUTTON);
		Utils.defaultWait();
	}

	/**
	 * Perform edit with form
	 * 
	 * @param columnLocator
	 * @return
	 */
	public static String performFormEditWithRandomValue(final GeneralTableAttributes attribute) {
		final WebElement field = CreateRecordManager.findFieldOfThisAttribute(attribute);
		final String value = GeneralDataProvider.setRandomValueToThisAttribute(field, attribute);
		Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.SAVE_AND_CLOSE);
		Utils.defaultWait();
		Utils.acceptMessageBoxIfVisible();
		Utils.defaultWait();
		Utils.acceptMessageBoxIfVisible();
		Utils.defaultWait();
		Utils.defaultWait();
		return value;
	}

	/**
	 * Perform edit with form
	 * 
	 * @param columnLocator
	 * @return
	 */
	public static String performFormEditWithValue(final Map<GeneralTableAttributes, String> attributeValues) {
		final GeneralTableAttributes attribute = attributeValues.keySet().iterator().next();
		final String value = attributeValues.values().iterator().next();
		final WebElement field = CreateRecordManager.findFieldOfThisAttribute(attribute);
		GeneralDataProvider.setThisValueToAttribute(field, attributeValues);
		Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.SAVE_AND_CLOSE);
		Utils.defaultWait();
		Utils.acceptMessageBoxIfVisible();
		Utils.defaultWait();
		Utils.acceptMessageBoxIfVisible();
		Utils.defaultWait();
		Utils.defaultWait();
		return value;
	}

}
