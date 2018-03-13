package com.frontendart.managers.main.rightpanel.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.frontendart.common.Utils;
import com.frontendart.locators.main.rightpanel.view.FilterLocators;

/**
 * Manager class for filter tests.
 * 
 * @author Zoli
 *
 */
public class FilterManager {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LogManager.getLogger("FilterManager");

	/**
	 * Clicks on Filter button
	 */
	public static void clickOnFilterButton() {
		Utils.waitForAndClickOnGeneralWebElement(FilterLocators.FILTER_BUTTON);
	}

	/**
	 * writes string to filter field
	 */
	public static void writeThisToFilterField(final String text) {
		LOGGER.info("Írjuk a szűrő mezőbe a következő karaktersorozatot: " + text);
		final WebElement filterField = getFilterField();
		filterField.clear();
		filterField.sendKeys(text);
	}

	/**
	 * returns filter field
	 * @return
	 */
	public static WebElement getFilterField() {
		return Utils.createGeneralWebElementFromEnum(FilterLocators.INPUT_FIELD);
	}

	/**
	 * Performs a random filter process
	 */
	public static void performFilterWithRandomText() {
		performFilterWithText(Utils.randomString());
	}

	/**
	 * Performs a random filter process
	 */
	public static void performFilterWithText(final String text) {
		writeThisToFilterField(text);
		clickOnFilterButton();
		Utils.defaultWait();
	}
}