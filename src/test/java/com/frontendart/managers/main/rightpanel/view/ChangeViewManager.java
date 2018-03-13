package com.frontendart.managers.main.rightpanel.view;

import com.frontendart.common.Utils;
import com.frontendart.locators.main.rightpanel.view.ToolbarLocators;

/**
 * Manager class for change view
 * 
 * @author Zoli
 *
 */
public class ChangeViewManager {

	/**
	 * Switches to icon view
	 * 
	 */
	public static void switchToIconView() {
		switchToThisView(ToolbarLocators.CHANGE_VIEW_ICON);
	}

	/**
	 * Switches to grid view
	 * 
	 */
	public static void switchToListGridView() {
		if (!Utils.createGeneralWebElementFromEnum(ToolbarLocators.CHANGE_VIEW_LISTGRID).getAttribute("class").contains("pressed")) {
			switchToThisView(ToolbarLocators.CHANGE_VIEW_LISTGRID);
		}
	}

	/**
	 * Switches to grid view
	 * 
	 */
	public static void switchToGridView() {
		if (!Utils.createGeneralWebElementFromEnum(ToolbarLocators.CHANGE_VIEW_GRID).getAttribute("class").contains("pressed")) {
			switchToThisView(ToolbarLocators.CHANGE_VIEW_GRID);
		}
	}

	/**
	 * Changes to the given view
	 * 
	 * 
	 * @param changeViewIcon
	 */
	public static void switchToThisView(final ToolbarLocators changeToThisView) {
		Utils.waitForAndClickOnGeneralWebElement(changeToThisView);
	}

}
