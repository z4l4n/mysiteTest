package com.frontendart.managers.gui2;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.frontendart.common.Utils;
import com.frontendart.locators.gui2.Gui2RecordTypes;
import com.frontendart.locators.gui2.SearchPanelLocators;

/**
 * Manager class for gui2 search tests
 * @author Zoli
 *
 */
public class Gui2SearchManager {

	/**
	 * Writes this text to search box
	 * 
	 * @param searchText
	 */
	public static void writeThisToSearchField(final String searchText) {
		Utils.writeTextToThisField(searchText, SearchPanelLocators.SEARCH_BOX);
	}

	/**
	 * Clicks on search button
	 * 
	 */
	public static void clickOnSearchButton() {
		Utils.waitForAndClickOnGeneralWebElement(SearchPanelLocators.SEARCH_BUTTON);
	}

	/**
	 * Clicks on search button
	 * 
	 */
	public static void performSearchWithText(final Gui2RecordTypes myRecordType, final String searchText) {
		selectThisGui2RecordTypeFromTopSearchBox(myRecordType);
		writeThisToSearchField(searchText);
		clickOnSearchButton();
		Utils.waitMillisec(2000);
	}

	/**
	 * Clicks on search mode select button
	 * 
	 */
	public static void clickOnSearchModeSelectButton() {
		Utils.waitForAndClickOnGeneralWebElement(SearchPanelLocators.SEARCH_MODE_SELECT);
	}

	/**
	 * Selects option from top search selector
	 * 
	 * @param myRecordType
	 */
	public static void selectThisGui2RecordTypeFromTopSearchBox(final Gui2RecordTypes myRecordType) {
		clickOnSearchModeSelectButton();

		// Get options and check
		final List<WebElement> options = Utils.createGeneralWebElementsFromEnum(SearchPanelLocators.SEARCH_MODE_OPTIONS);
		final List<String> optionsAsString = Utils.convertThisWebElementArrayToStringArray(options);
		options.get(optionsAsString.indexOf(myRecordType.getHungarianName())).click();
	}
}