package com.frontendart.managers.gui2;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.frontendart.common.Utils;
import com.frontendart.locators.gui2.Gui2RecordTypes;
import com.frontendart.locators.gui2.LeftPanelLocators;

/**
 * Manager class for select tests.
 * @author Zoli
 *
 */
public class Gui2LeftPanelLabelManager {

	/**
	 * Get left panel labels as string
	 */
	public static List<String> getLeftPanelLabelsAsString() {
		final List<WebElement> labelsAsWebelement = Utils.createGeneralWebElementsFromEnum(LeftPanelLocators.ALL_TAB_LABELS);
		return Utils.convertThisWebElementArrayToStringArray(labelsAsWebelement);
	}

	/**
	 * Check left panel labels
	 */
	public static void checkLeftPanelLabels() {
		Utils.waitForElementPresent(LeftPanelLocators.ALL_TAB_LABELS);
		final List<String> labelsAsString = getLeftPanelLabelsAsString();
		for (final Gui2RecordTypes locator : Gui2RecordTypes.values()) {
			Utils.myAssertTrue("A bal oldali kereső tab feliratoknak tartalmaznia kell a következő labelt: " +
					locator.getHungarianName() + "/" + locator.getEnglishName(),
					labelsAsString.contains(locator.getHungarianName()) || (labelsAsString.contains(locator.getEnglishName())));
		}
	}

}