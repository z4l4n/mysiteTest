package com.frontendart.managers.main.rightpanel.top;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.frontendart.common.Utils;
import com.frontendart.locators.general.GeneralPageLocators;
import com.frontendart.locators.main.rightpanel.crud.RecordEditorLocators;
import com.frontendart.locators.main.rightpanel.top.ForumTableAttributes;
import com.frontendart.locators.main.rightpanel.top.MessageTableAttributes;
import com.frontendart.locators.main.rightpanel.top.TicketTableAttributes;
import com.frontendart.locators.records.attributes.general.GeneralTableAttributes;
import com.frontendart.managers.general.GeneralDataProvider;
import com.frontendart.managers.general.GeneralTableManager;
import com.frontendart.managers.main.rightpanel.crud.CreateRecordManager;

/**
 * General manager for messages, system messages, tickets, forums
 * @author Zoli
 *
 */
public class TopGeneralManager {

	/**
	 * Clicks on new ticket button
	 */
	public static void clickOnNewButton() {
		Utils.waitForElementPresent(GeneralPageLocators.MESSAGE_CENTER_WINDOW_NEW_BUTTON);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Utils.waitForAndClickOnGeneralWebElement(GeneralPageLocators.MESSAGE_CENTER_WINDOW_NEW_BUTTON);
	}

	/**
	 * Adds element
	 */
	public static void addElement(final Class<? extends GeneralTableAttributes> attributes) {
		// Click on New button
		clickOnNewButton();
		if (attributes.equals(TicketTableAttributes.class)) {
			checkForAssigneeField();
		}
		// Fill the fields
		CreateRecordManager.fillAllFields(attributes);

		// Click on save and close button
		if (attributes.equals(TicketTableAttributes.class)) {
			Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.SAVE_AND_CLOSE);
			Utils.defaultWait();
			if (!Utils.actualRoleIsCentralAdmin() && Utils.isThisElementPresent(GeneralPageLocators.MESSAGE_BOX_SAVE_BUTTON)) {
				Utils.waitForAndClickOnGeneralWebElement(GeneralPageLocators.MESSAGE_BOX_SAVE_BUTTON);
			}
		} else {
			Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.SEND);
		}

		Utils.defaultWait();
		Utils.acceptMessageBoxIfVisible();
		assertFalse("An error window is present after creating the element!", Utils.isErrorWindowVisible());
	}

	/**
	 * Checks for assignee field present. If yes, fills it.
	 * 
	 */
	private static void checkForAssigneeField() {
		final List<WebElement> visibleLabels = Utils.createGeneralWebElementsFromEnum(RecordEditorLocators.EDITOR_FORM_FIELDS_VISIBLE_LABELS_ALTERNATIVE);
		final List<String> visibleLabelsAsString = Utils.convertThisWebElementArrayToStringArray(visibleLabels);

		final GeneralTableAttributes findThisField = TicketTableAttributes.ASSIGNEE;
		if (Utils.isThisStringListContainsThisAttribute(visibleLabelsAsString, findThisField)) {
			final WebElement field = CreateRecordManager.findFieldOfThisAttribute(findThisField);
			GeneralDataProvider.setRandomValueToThisAttribute(field, findThisField);
		}
	}

	/**
	 * Returns table size
	 * 
	 * TODO: table size should be counted with the "numbers" above the table
	 * 
	 * @param attributes 
	 * @return
	 */
	private static int getTableSize(final Class<? extends GeneralTableAttributes> attributes) {
		Utils.defaultWait();
		int size = GeneralTableManager.getAllRowsOfThisGrid(GeneralPageLocators.MESSAGE_CENTER_WINDOW_GRIDPANEL).size();

		// If the attribute type is Message, then we should check the sent messages tab.
		if (attributes.equals(MessageTableAttributes.class)) {
			Utils.waitForAndClickOnGeneralWebElement(GeneralPageLocators.MESSAGE_CENTER_WINDOW_SENT_MESSAGES_TAB);
			// TODO: do it without default wait.
			Utils.defaultWait();
			size = GeneralTableManager.getAllRowsOfThisGrid(GeneralPageLocators.MESSAGE_CENTER_WINDOW_SENT_MESSAGES_GRIDPANEL).size();
			Utils.waitForAndClickOnGeneralWebElement(GeneralPageLocators.MESSAGE_CENTER_WINDOW_MESSAGES_TAB);
		}

		return size;
	}

	/**
	 * Removes element from table
	 * TODO: remove by id!
	 * @param attributes 
	 */
	private static void removeElement(final Class<? extends GeneralTableAttributes> attributes) {
		// If the attribute type is Message, then we cannot delete the message
		if (!attributes.equals(MessageTableAttributes.class)) {
			final WebElement rowToRemove = GeneralTableManager.getAllRowsOfThisGrid(GeneralPageLocators.MESSAGE_CENTER_WINDOW_GRIDPANEL).get(0);
			Utils.clickOnThisRow(rowToRemove);

			//Only forum entries
			if (attributes.equals(ForumTableAttributes.class)) {
				clickOnRemoveButton();

				// There should be a confirmation message box present
				Utils.waitForMessageBoxToBePresent();
				Utils.acceptMessageBoxIfVisible();

				// There should be a message box present with the result of the removal
				try {
					Utils.waitForElementPresent(GeneralPageLocators.MESSAGE_WINDOW);
					Utils.waitForAndClickOnGeneralWebElement(GeneralPageLocators.MESSAGE_WINDOW_CLOSE_BUTTON);
				} catch (final AssertionError e) {
					closeWindow();
					fail("There is no result message after the removal of the element!");
				}
			}
		}
	}

	/**
	 * Clicks on remove button
	 */
	private static void clickOnRemoveButton() {
		Utils.waitForAndClickOnGeneralWebElement(GeneralPageLocators.MESSAGE_CENTER_WINDOW_REMOVE_BUTTON);
	}

	/**
	 * Closes window
	 */
	public static void closeWindow() {
//		Utils.waitForAndClickOnGeneralWebElement(GeneralPageLocators.MESSAGE_CENTER_WINDOW_CLOSE_BUTTON);
		Utils.waitForAndClickOnGeneralWebElement(GeneralPageLocators.MESSAGE_CENTER_WINDOW_CLOSE_BUTTON_ALTERNATIVE);
		
	}

	/**
	 * Checks general element addition
	 */
	public static void checkGeneralElementCreation(final Class<? extends GeneralTableAttributes> attributes) {
		// Get table size before
		final int rowNumberBefore = getTableSize(attributes);

		// Add element 
		addElement(attributes);

		// Validate: There should be one more row in window
		final int rowNumberAfter = getTableSize(attributes);
		if (attributes.equals(MessageTableAttributes.class)) {
			// TODO: Normal validation for messages
			assertTrue("There should be one more element present.", rowNumberBefore <= rowNumberAfter);
		} else {
			assertEquals("There should be one more element present.", rowNumberBefore + 1, rowNumberAfter);
		}

		// Remove
		removeElement(attributes);
		Utils.defaultWait();
		closeWindow();
	}

	/**
	 * Checks general element removal
	 */
	public static void checkGeneralElementDeletion(final Class<? extends GeneralTableAttributes> attributes) {
		// Get table size before
		final int rowNumberBefore = getTableSize(attributes);

		// Add element
		removeElement(attributes);

		// Validate: There should be one more row in window
		final int rowNumberAfter = getTableSize(attributes);
		if (Utils.actualRoleIsCentralAdmin() || attributes.equals(ForumTableAttributes.class)) {
			assertEquals("There should be one element less present.", rowNumberBefore - 1, rowNumberAfter);
		} else {
			assertEquals("The element number shouldn't change.", rowNumberBefore, rowNumberAfter);

		}
		// Closes window
		closeWindow();
	}
}
