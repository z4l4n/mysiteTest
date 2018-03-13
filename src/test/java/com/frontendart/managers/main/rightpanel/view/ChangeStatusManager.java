package com.frontendart.managers.main.rightpanel.view;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.frontendart.common.Utils;
import com.frontendart.locators.main.rightpanel.view.ChangeStatusTypes;
import com.frontendart.locators.main.rightpanel.view.ToolbarLocators;
import com.frontendart.managers.general.GeneralTableManager;

/**
 * Manager class for change view
 * 
 * @author Zoli
 *
 */
public class ChangeStatusManager {

	/**
	 * Get value of change status dropdown
	 */
	public static ChangeStatusTypes getStatusValueOfRecord(final WebElement row) {
		final String valueAsString = GeneralTableManager.getStatusOfThisRow(row);
		return getStatusTypeFromString(valueAsString);
	}

	/**
	 * returns the enum status given a string
	 */
	public static ChangeStatusTypes getStatusTypeFromString(final String myString) {
		ChangeStatusTypes returnValue = null;

		for (final ChangeStatusTypes myType : ChangeStatusTypes.values()) {
			if ((myType.getEngName().equals(myString)) || (myType.getHunName().equals(myString))) {
				returnValue = myType;
			}
		}

		return returnValue;
	}

	/**
	 * Clicks on change state input
	 */
	public static void clickOnChangeStateInput() {
		Utils.waitForElementPresent(ToolbarLocators.CHANGE_STATE_INPUT);
		Utils.createGeneralWebElementFromEnum(ToolbarLocators.CHANGE_STATE_INPUT).sendKeys(Keys.DOWN);
	}

	/**
	 * Selects the second option form the status selector
	 */
	public static ChangeStatusTypes changeStatusTypeOfSelectedRow(final int myRecordID) {
		clickOnChangeStateInput();
		Utils.switchToActiveElement().sendKeys(Keys.DOWN);
		Utils.switchToActiveElement().sendKeys(Keys.ENTER);
		// TODO: do it without default wait
		Utils.defaultWait();
		final WebElement myRecordRow = GeneralTableManager.findThisRecordByID(myRecordID);
		return getStatusValueOfRecord(myRecordRow);
	}

	/**
	 * Selects the given option
	 * 
	 * TODO: check this function!
	 */
	public static void changeStatusTypeOfSelectedRowToThis(final ChangeStatusTypes statusType) {
		clickOnChangeStateInput();
		final List<WebElement> options = Utils.createGeneralWebElementsFromEnum(ToolbarLocators.CHANGE_STATE_OPTIONS);

		// Go through options and find the given option, if exists
		boolean found = false;
		int tryCount = 0;
		while ((!found) && (tryCount < options.size())) {
			final WebElement activeElement = Utils.switchToActiveElement();
			final String optionText = activeElement.getAttribute("value");
			if ((statusType.getEngName().equals(optionText)) || (statusType.getHunName().equals(optionText))) {
				found = true;
				activeElement.sendKeys(Keys.ENTER);
				// TODO: do it without default wait
				Utils.defaultWait();
			} else {
				activeElement.sendKeys(Keys.DOWN);
			}
			tryCount++;
		}
	}

}
