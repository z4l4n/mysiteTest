package com.frontendart.managers.main.leftpanel;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.frontendart.common.Utils;
import com.frontendart.locators.main.leftpanel.RecordSelectionLocators;

/**
 * Manager class for record selection with left panel button
 * 
 * @author Zoli
 *
 */
public class LeftPanelButtonsSelectionManager {

	/**
	 * Clicks on random left panel button
	 * 
	 * @return
	 */
	public static String selectRandomLeftPanelRecordSelectorButton() {
		// Select random button and get name
		final List<WebElement> leftPanelButtons = Utils.createGeneralWebElementsFromEnum(RecordSelectionLocators.RECORD_SELECTOR_BUTTONS);
		final WebElement randomLeftPanelButton = leftPanelButtons.get(Utils.randInt(0, leftPanelButtons.size() - 1));
		final String buttonName = Utils.getNameOfThisButton(randomLeftPanelButton);

		// Clicks on button
		randomLeftPanelButton.click();
		Utils.defaultWait();

		return buttonName;
	}
}
