package com.frontendart.managers.main.rightpanel.crud;

import org.openqa.selenium.WebElement;

import com.frontendart.common.Utils;
import com.frontendart.locators.general.GeneralPageLocators;
import com.frontendart.locators.main.rightpanel.view.ToolbarLocators;
import com.frontendart.managers.general.GeneralTableManager;
import com.frontendart.managers.main.leftpanel.SearchManager;

/**
 * Manager class for removing records
 * 
 * TODO: refakt? (CBO...)
 * 
 * @author Zoli
 *
 */
public class RestoreRecordManager {

	/**
	 * Restores the record with the given ID 
	 */
	public static void restoreRecordWithThisID(final int recordToRestore) {
		SearchManager.createAndRunDeletedRecordsQuery();

		// Find record and click on it
		final WebElement myRecord = GeneralTableManager.findThisRecordByID(recordToRestore);
		Utils.clickOnThisRow(myRecord);

		// Click on restore button
		Utils.waitForAndClickOnGeneralWebElement(ToolbarLocators.RESTORE_BUTTON);

		// TODO: message...
		Utils.waitForAndClickOnGeneralWebElement(GeneralPageLocators.MESSAGE_WINDOW_CLOSE_BUTTON);
	}

}
