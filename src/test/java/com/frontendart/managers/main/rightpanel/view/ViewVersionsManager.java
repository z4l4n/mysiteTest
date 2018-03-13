package com.frontendart.managers.main.rightpanel.view;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.frontendart.common.Utils;
import com.frontendart.locators.main.rightpanel.view.VersionsTableLocators;
import com.frontendart.locators.main.rightpanel.view.ViewVersionsLocators;
import com.frontendart.managers.general.GeneralTableManager;

/**
 * Manager class for view version tests.
 * 
 * @author Zoli
 *
 */
public class ViewVersionsManager {

	/**
	 * Opens version window
	 */
	public static void openVersionsWindow() {
		Utils.waitForAndClickOnGeneralWebElement(ViewVersionsLocators.VERSIONS_BUTTON);
		Utils.defaultWait();
	}

	/**
	 * Closes version window
	 */
	public static void closeVersionsWindow() {
		Utils.waitForAndClickOnGeneralWebElement(ViewVersionsLocators.VERSIONS_WINDOW_CLOSE_BUTTON);
	}

	/**
	 * Get how many rows are in the versions window
	 * @return
	 */
	public static int getRowNumberOfVersionsTableForActualRecord() {
		openVersionsWindow();
		Utils.waitForElementPresent(ViewVersionsLocators.ALL_ROWS);
		final int rowNumbers = GeneralTableManager.getAllRowsOfThisGrid(ViewVersionsLocators.VERSIONS_WINDOW_GRIDPANEL).size();
		closeVersionsWindow();

		return rowNumbers;
	}

	/**
	 * Returns the actual version id (the id of the first row)
	 * @return
	 */
	public static int getActualVersionIDOfRecord() {
		openVersionsWindow();
		Utils.waitForElementPresent(ViewVersionsLocators.ALL_ROWS);
		final WebElement firstRow = GeneralTableManager.getAllRowsOfThisGrid(ViewVersionsLocators.VERSIONS_WINDOW_GRIDPANEL).get(0);

		// Searches for the versionID column, and get value of first row
		final int columnPositionOfVersionID = GeneralTableManager.getColPos(VersionsTableLocators.VERSION_ID, ViewVersionsLocators.VERSIONS_WINDOW_GRIDPANEL);
		final int versionID = Integer.parseInt(firstRow.findElement(By.xpath("./tbody/tr/td[" + columnPositionOfVersionID + "]/div")).getText());
		closeVersionsWindow();

		return versionID;
	}

	/**
	 * Restores the given version
	 * @param versionID
	 */
	public static void restoreThisVersion(final int versionID) {
		openVersionsWindow();

		// Finds the given version ID
		final int index = findThisVersion(versionID);

		// Click on the appropriate row, and then restore
		final WebElement secondRow = GeneralTableManager.getAllRowsOfThisGrid(ViewVersionsLocators.VERSIONS_WINDOW_GRIDPANEL).get(index);
		Utils.clickOnThisRow(secondRow);
		clickOnRestoreButton();
	}

	/**
	 * Returns the version id-s from grid
	 */
	public static List<Integer> getAllVersionIdsFromGrid() {
		final List<String> stringValues = GeneralTableManager.getTheseValuesFromThisGrid(VersionsTableLocators.VERSION_ID, ViewVersionsLocators.VERSIONS_WINDOW_GRIDPANEL);
		return Utils.convertThisStringArrayToIntArray(stringValues);
	}

	/**
	 * Finds the given version
	 * @param versionID
	 * @return
	 */
	public static int findThisVersion(final int versionID) {
		return getAllVersionIdsFromGrid().indexOf(versionID);
	}

	/**
	 * Restores the given version
	 */
	public static void clickOnRestoreButton() {
		Utils.waitForAndClickOnGeneralWebElement(ViewVersionsLocators.VERSIONS_WINDOW_RESTORE_BUTTON);
	}
}
