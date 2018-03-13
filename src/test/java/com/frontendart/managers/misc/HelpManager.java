package com.frontendart.managers.misc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.frontendart.common.Utils;
import com.frontendart.locators.login.LoginPageLocators;

/**
 * Manager class for help tests
 * 
 * @author Zoli
 *
 */
public class HelpManager {

	/**
	 * Clicks on Help or Helpdesk buttons
	 * 
	 * @param driver
	 */
	public static void checkHelpButtons(final WebDriver driver, final LoginPageLocators button) {
		Utils.waitForAndClickOnGeneralWebElement(button);
		Utils.defaultWait();
		final String winHandleBefore = driver.getWindowHandle();

		// Switch to new window opened
		for (final String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

		// Perform the actions on new window
		final String text = driver.switchTo().activeElement().findElement(By.xpath("//body/h1")).getText();

		if (button.equals(LoginPageLocators.HELP_BUTTON)) {
			Utils.myAssertTrue("A megjelenő oldalnak tartalmaznia kell a segítség szöveget.", text.contains("Tartalomjegyzék"));
		} else {
			Utils.myAssertTrue("A megjelenő oldalnak tartalmaznia kell a helpdesk szöveget.", text.contains("Helpdesk oldal"));
		}

		// Close
		driver.close();
		driver.switchTo().window(winHandleBefore);
	}
}
