package com.frontendart.managers.main.rightpanel.view;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.frontendart.common.Utils;
import com.frontendart.locators.main.rightpanel.view.LockStatusTypes;
import com.frontendart.locators.main.rightpanel.view.ToolbarLocators;

/**
 * Manager class for search toolbar
 * 
 * @author Zoli
 *
 */
@Deprecated
public class LockRecordManager {

	/**
	 * Get record locked status
	 */
	@Deprecated
	public static boolean isSelectedRecordLocked() {
		final String lockButtonText = getLockButtonText();

		// Check if status is locked
		boolean returnValue = false;
		if (LockStatusTypes.LOCKED.getEngName().equals(lockButtonText) || LockStatusTypes.LOCKED.getHunName().equals(lockButtonText)) {
			returnValue = true;
		}

		return returnValue;
	}

	/**
	 * Click on lock button
	 */
	@Deprecated
	public static void lockCurrentRecordAndCheck() {
		lockCurrentRecord();
		// Check lock status
		assertTrue("The selected record should be locked!", isSelectedRecordLocked());
	}

	/**
	 * Locks current record
	 */
	@Deprecated
	public static void lockCurrentRecord() {
		clickOnLockButton();
		Utils.switchToActiveElement().sendKeys(Keys.ENTER);
		Utils.defaultWait();
		Utils.defaultWait();
	}

	/**
	 * Unlocks current record
	 */
	@Deprecated
	public static void unLockCurrentRecord() {
		clickOnLockButton();
		Utils.switchToActiveElement().sendKeys(Keys.DOWN);
		Utils.switchToActiveElement().sendKeys(Keys.ENTER);
		Utils.defaultWait();
		Utils.defaultWait();
	}

	/**
	 * Clicks on lock button
	 */
	@Deprecated
	public static void clickOnLockButton() {
		final WebElement lockButton = Utils.createGeneralWebElementFromEnum(ToolbarLocators.CHANGE_LOCK_BUTTON);
		lockButton.sendKeys(Keys.DOWN);
	}

	/**
	 * Click on lock button
	 */
	@Deprecated
	public static String getLockButtonText() {
		final WebElement lockButtonAsWebelement = Utils.createGeneralWebElementFromEnum(ToolbarLocators.CHANGE_LOCK_BUTTON);
		return lockButtonAsWebelement.findElement(By.xpath("./span/span/span[2]")).getText();

	}
}
