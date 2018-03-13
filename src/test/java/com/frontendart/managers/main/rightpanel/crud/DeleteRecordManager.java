package com.frontendart.managers.main.rightpanel.crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebElement;

import com.frontendart.common.Utils;
import com.frontendart.locators.general.GeneralPageLocators;
import com.frontendart.locators.main.rightpanel.crud.DeleteRecordMessageTypes;
import com.frontendart.locators.main.rightpanel.view.ToolbarLocators;
import com.frontendart.managers.general.GeneralTableManager;
import com.frontendart.managers.main.leftpanel.RecordSelectionManager;

/**
 * Manager class for removing records
 * 
 * @author Zoli
 *
 */
public class DeleteRecordManager {

	/**
	 * removes random element from table
	 */
	public static int removeRandomRecord() {
		// Select row and click on delete button
		final WebElement selectedRow = RecordSelectionManager.selectRandomRenderedRowsFromGridPanel(1).get(0);
		final int selectedRowID = GeneralTableManager.getIDOfThisRow(selectedRow);
		removeThisRecord(selectedRowID);

		return selectedRowID;
	}

	/**
	 * Removes the given record
	 */
	public static void removeThisRecord(final int recordToRemove) {
		final WebElement myRecord = GeneralTableManager.findThisRecordByID(recordToRemove);
		remove(myRecord);
	}

	/**
	 * Removes the given record
	 */
	public static void removeFirstRecord() {
		final WebElement myRecord = RecordSelectionManager.getGridElementsOnPage().get(0);
		remove(myRecord);
	}

	/**
	 * Removes the given record
	 */
	public static void remove(final WebElement myRecord) {
		Utils.clickOnThisRow(myRecord);

		// Clicks on Delete button, and waits for confirmation message box
		Utils.waitForAndClickOnGeneralWebElement(ToolbarLocators.DELETE_BUTTON);
		Utils.waitForMessageBoxToBePresent();
		assertTrue("There should be a message box present!",
				Utils.isMessageBoxPresentWithText(DeleteRecordMessageTypes.CONFIRMATION_MESSAGE));

		// Waits for delete message window to be present
		Utils.waitForElementPresent(GeneralPageLocators.MESSAGE_WINDOW);
		final String deleteMessage = getTextOfDeleteMessage();
		closeDeleteMessage();
		assertTrue("Delete message is not correct!", Utils.getAllMyLabelsAsString(DeleteRecordMessageTypes.ONE_ITEM_SUCCESS).contains(deleteMessage));
	}

	/**
	 * Returns text of delete message
	 * @return
	 */
	public static String getTextOfDeleteMessage() {
		return Utils.createGeneralWebElementFromEnum(GeneralPageLocators.MESSAGE_TEXT).getText();
	}

	/**
	 * Closes delete message
	 */
	public static void closeDeleteMessage() {
		Utils.waitForAndClickOnGeneralWebElement(GeneralPageLocators.MESSAGE_WINDOW_CLOSE_BUTTON);
	}

}
