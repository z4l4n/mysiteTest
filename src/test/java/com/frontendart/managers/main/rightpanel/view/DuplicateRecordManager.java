package com.frontendart.managers.main.rightpanel.view;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.frontendart.common.Utils;
import com.frontendart.locators.main.rightpanel.view.DuplicateRecordLocators;
import com.frontendart.locators.main.rightpanel.view.ToolbarLocators;

/**
 * Manager class for change view
 * 
 * @author Zoli
 *
 */
public class DuplicateRecordManager {

	/**
	 * clicks on duplicate button
	 * 
	 * 
	 * @param changeViewIcon
	 */
	public static void clickOnCopyButton() {
		Utils.waitForAndClickOnGeneralWebElement(ToolbarLocators.OPERATIONS);
		Utils.switchToActiveElement();
		Utils.waitForAndClickOnGeneralWebElement(DuplicateRecordLocators.DUPLICATE_BUTTON);
		Utils.switchToActiveElement();
		Utils.waitForAndClickOnGeneralWebElement(DuplicateRecordLocators.DUPLICATE_OK_BUTTON);
	}

	/**
	 * clicks on search duplicates button
	 * 
	 * 
	 * @param changeViewIcon
	 */
	public static void clickOnSearchDuplicatesButton() {
		final WebElement duplumButton = Utils.createGeneralWebElementFromEnum(ToolbarLocators.DUPLUM_OPERATIONS_BUTTON);
		duplumButton.sendKeys(Keys.DOWN);
		Utils.switchToActiveElement().sendKeys(Keys.ENTER);
	}

	/**
	 * clicks on mark as duplum button
	 * 
	 * 
	 * @param changeViewIcon
	 */
	public static void clickOnMarkAsDuplumButton() {
		final WebElement duplumButton = Utils.createGeneralWebElementFromEnum(ToolbarLocators.DUPLUM_OPERATIONS_BUTTON);
		duplumButton.sendKeys(Keys.DOWN);
		Utils.switchToActiveElement().sendKeys(Keys.DOWN);
		Utils.switchToActiveElement().sendKeys(Keys.DOWN);
		Utils.switchToActiveElement().sendKeys(Keys.ENTER);
	}

	/**
	 * Checks if the duplum handling window is visible
	 * 
	 * @return
	 */
	public static boolean isDuplumWindowPresentAndClose() {
		boolean returnValue = false;
		if (Utils.createGeneralWebElementFromEnum(DuplicateRecordLocators.SELECTOR_WINDOW).isDisplayed()) {
			returnValue = true;
			closeDuplumWindowWithoutSave();
		}
		return returnValue;
	}

	/**
	 * Closes duplum handling window without save
	 * 
	 */
	public static void closeDuplumWindowWithoutSave() {
		Utils.waitForAndClickOnGeneralWebElement(DuplicateRecordLocators.CLOSE_BUTTON);
		Utils.acceptMessageBoxIfVisible();
	}

}
