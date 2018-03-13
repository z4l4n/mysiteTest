package com.frontendart.testsuites.main.rightpanel.crud;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.RightPanelCrudSuite;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.JunitTestClass;
import com.frontendart.locators.general.GeneralSelectorBoxLocators;
import com.frontendart.locators.records.attributes.general.AuthorRecordAttributes;
import com.frontendart.locators.records.attributes.general.GeneralRecordTypes;
import com.frontendart.locators.records.attributes.hidden.AffiliationRecordAttributes;
import com.frontendart.managers.main.leftpanel.RecordSelectionManager;
import com.frontendart.managers.main.rightpanel.crud.CreateRecordManager;

/**
 * Parameterized test class for record editing. 
 * @author Zoli
 *
 */
@Category({ RightPanelCrudSuite.class, CENTRAL_ADMIN_Suite.class })
//@RunWith(value = Parameterized.class)
@SuppressWarnings({ "PMD.JUnitTestsShouldIncludeAssert", "PMD.JUnitTestContainsTooManyAsserts" })
public class UpdateRecordTest extends JunitTestClass {

	// This test class should only run with admin rights - meaning that it will only run once
	@BeforeClass
	public static void beforeMethod() {
		org.junit.Assume.assumeTrue(Utils.actualRoleIsCentralAdmin());
	}

	/**
	 * Simple update - with form
	 * 
	 * TODO: reimplement
	 */

	/**
	 * Create affiliation for author
	 * Redmine issue: https://redmine.mt2.dsd.sztaki.hu:18018/issues/2487
	 */
	@Test
	public final void testCreateAffiliationForAuthor() {
		// Selects author record type
		RecordSelectionManager.selectThisRecordTypeFromSelector(GeneralRecordTypes.AUTHOR);

		// Click on new button
		CreateRecordManager.clickOnNewButton();

		// Set affiliation (TODO: refact)
		final WebElement field = CreateRecordManager.findFieldOfThisAttribute(AuthorRecordAttributes.AFFILIATIONS);
		
		Utils.scrollIntoView(field.findElement(By.xpath(".//div[contains(@class, 'x-tool-img')]")));
		
		field.findElement(By.xpath(".//div[contains(@class, 'x-tool-img')]")).click();
		
		final WebElement field2 = CreateRecordManager.findFieldOfThisAttribute(AffiliationRecordAttributes.INSTITUTE);
		field2.findElement(By.xpath(".//div[contains(@id, '-search')]")).click();
		Utils.defaultWait();
		final List<WebElement> items = Utils.createGeneralWebElementsFromEnum(GeneralSelectorBoxLocators.FIND_INSTITUTES_ITEMS);
		Utils.clickOnThisRow(items.get(Utils.randInt(1, items.size()) - 1));
		Utils.waitForAndClickOnGeneralWebElement(GeneralSelectorBoxLocators.FIND_INSTITUTES_SAVE_AND_CLOSE_BUTTON);
		//Utils.acceptMessageBoxIfVisible();

		// Save and close affiliation
		CreateRecordManager.clickOnSaveAndCloseButton();
		Utils.acceptMessageBoxIfVisible();
		assertFalse("There should be no error messages present!", Utils.isAlertPresent());

		// Close editor
		CreateRecordManager.closeEditorWindow();
	}

}
