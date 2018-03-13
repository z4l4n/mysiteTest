package com.frontendart.managers.main.rightpanel.crud;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.frontendart.common.Utils;
import com.frontendart.junitclasses.JunitTestClass;
import com.frontendart.locators.general.GeneralPageLocators;
import com.frontendart.locators.main.rightpanel.crud.RecordEditorLocators;
import com.frontendart.locators.records.attributes.general.AdminRecordAttributes;
import com.frontendart.locators.records.attributes.general.AuthorRecordAttributes;
import com.frontendart.locators.records.attributes.general.FieldTypeLocators;
import com.frontendart.locators.records.attributes.general.GeneralRecordTypes;
import com.frontendart.locators.records.attributes.general.GeneralTableAttributes;
import com.frontendart.locators.records.attributes.general.KeywordRecordAttributes;
import com.frontendart.locators.records.attributes.hidden.AdminRoleRecordAttributes;
import com.frontendart.managers.general.GeneralDataProvider;
import com.frontendart.managers.main.leftpanel.RecordSelectionManager;
import com.frontendart.managers.main.leftpanel.SearchManager;

/**
 * Manager class for adding new author
 *
 */
public class CreateOthersManager extends JunitTestClass {

	/**
	 * Fills author fields TODO: the number of final steps are varying based on
	 * the publication
	 */
	public static String fillAuthorFieldsAndSave() {
		String username = "";
		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();

		attributes.addAll(EnumSet.of(AuthorRecordAttributes.USERNAME, AuthorRecordAttributes.FAMILY_NAME,
				AuthorRecordAttributes.GIVEN_NAME, AuthorRecordAttributes.EMAIL,
				AuthorRecordAttributes.EMAIL_ADDRESS_CONFIRMED, AuthorRecordAttributes.LOGIN_ENABLED,
				AuthorRecordAttributes.BIRTH_PLACE, AuthorRecordAttributes.BIRTH_DATE));

		for (final GeneralTableAttributes attribute : attributes) {

			String actualValue = "";
			final WebElement field = CreateRecordManager.findFieldOfThisAttribute(attribute);
			System.out.println(field);
			actualValue = GeneralDataProvider.setRandomValueToThisAttribute(field, attribute);

			if (attribute.equals(AuthorRecordAttributes.USERNAME)) {
				username = actualValue;
			}
		}

		CreateRecordManager.clickOnSaveAndCloseButton();
		System.out.println("Username: " + username);
		return username;
	}
	
	/**
	 * Fills author fields TODO: the number of final steps are varying based on
	 * the publication
	 */
	public static String fillAuthorFieldsAndSaveWithInstAdmin() {
		String username = "";
		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();

		attributes.addAll(EnumSet.of(AuthorRecordAttributes.USERNAME, AuthorRecordAttributes.FAMILY_NAME,
				AuthorRecordAttributes.GIVEN_NAME, AuthorRecordAttributes.EMAIL,
				AuthorRecordAttributes.LOGIN_ENABLED,
				AuthorRecordAttributes.BIRTH_PLACE, AuthorRecordAttributes.BIRTH_DATE));

		for (final GeneralTableAttributes attribute : attributes) {

			String actualValue = "";
			final WebElement field = CreateRecordManager.findFieldOfThisAttribute(attribute);
			System.out.println(field);
			actualValue = GeneralDataProvider.setRandomValueToThisAttribute(field, attribute);

			if (attribute.equals(AuthorRecordAttributes.USERNAME)) {
				username = actualValue;
			}
		}

		CreateRecordManager.clickOnSaveAndCloseButton();
		System.out.println("Username: " + username);
		return username;
	}

	/**
	 * Fills keyword fields TODO: the number of final steps are varying based on
	 * the publication
	 */
	public static String fillKeywordFieldsAndSave() {
		String keyword = "";
		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();

		attributes.addAll(EnumSet.of(KeywordRecordAttributes.KEYWORD));

		for (final GeneralTableAttributes attribute : attributes) {

			String actualValue = "";
			final WebElement field = CreateRecordManager.findFieldOfThisAttribute(attribute);
			actualValue = GeneralDataProvider.setRandomValueToThisAttribute(field, attribute);

			if (attribute.equals(KeywordRecordAttributes.KEYWORD)) {
				keyword = actualValue;
			}
		}

		CreateRecordManager.clickOnSaveAndCloseButton();
		return keyword;
	}

	/**
	 * General create record method
	 * 
	 * @param type
	 * @param attributes
	 * @param attributeForSearch
	 */
	public static void createRecordForThisType(GeneralRecordTypes type, List<GeneralTableAttributes> attributes, GeneralTableAttributes attributeForSearch) {

		// Get admin type
		final GeneralRecordTypes recordType = type;
		RecordSelectionManager.selectThisRecordTypeFromSelectorWithoutRunEmptyQuery(recordType);

		
		
		
		
//		// Click new button
//		CreateRecordManager.clickOnNewButton();
//
//		// Fill required fields
//		String valueForSearch = "";
//		
//		if(type.equals(GeneralRecordTypes.JOURNAL)){
//			Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.NEXT_BUTTON);
//		}
//
//		for (final GeneralTableAttributes attribute : attributes) {
//
//			//Special case
//			if (attribute.equals(AdminRecordAttributes.ADMIN_ROLES)) {
//				forAdminType();
//			}
//
//			else {
//				String actualValue = "";
//				final WebElement field = CreateRecordManager.findFieldOfThisAttribute(attribute);
//				actualValue = GeneralDataProvider.setRandomValueToThisAttribute(field, attribute);
//
//				if (attribute.equals(attributeForSearch)) {
//					valueForSearch = actualValue;
//				}
//			}
//
//		}
//
//		CreateRecordManager.clickOnSaveAndCloseButton();

		String valueForSearch = createRecord(type, attributes, attributeForSearch);
		

		String queryName = SearchManager.createAndRunOwnQueryWithTheseAttributes(type,
				attributeForSearch, valueForSearch);

		assertEquals("Egy találatnak kell lennie.", 1, RecordSelectionManager.gridTableFullSize());
		RecordSelectionManager.selectRandomRenderedRowsFromGridPanel(1);

		Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.REMOVE_BUTTON);
		Utils.waitForMessageBoxPresentAndAccept();
		Utils.waitForAndClickOnGeneralWebElement(GeneralPageLocators.MESSAGE_WINDOW_CLOSE_BUTTON);

		SearchManager.deleteThisQuery(queryName);
	}
	
	/**
	 * For admin type
	 */
	public static void forAdminType(){
		final WebElement field = CreateRecordManager
				.findFieldOfThisAttribute(AdminRecordAttributes.ADMIN_ROLES);
		Utils.scrollIntoView(field.findElement(By.xpath(".//div[contains(@class, 'x-tool-img')]")));
		field.findElement(By.xpath(".//div[contains(@class, 'x-tool-img')]")).click();

		final WebElement field2 = CreateRecordManager
				.findFieldOfThisAttribute(AdminRoleRecordAttributes.ROLE_TYPE);
		GeneralDataProvider.setValueToThisAttribute(field2, AdminRoleRecordAttributes.ROLE_TYPE, "Admin1");

		CreateRecordManager.clickOnSaveAndCloseButton();
	}
	
	
	public static String createRecord(GeneralRecordTypes type, List<GeneralTableAttributes> attributes, GeneralTableAttributes attributeForSearch){
		// Click new button
				CreateRecordManager.clickOnNewButton();

				// Fill required fields
				String valueForSearch = "";
				
				if(type.equals(GeneralRecordTypes.JOURNAL)){
					Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.NEXT_BUTTON);
				}

				for (final GeneralTableAttributes attribute : attributes) {

					//Special case
					if (attribute.equals(AdminRecordAttributes.ADMIN_ROLES)) {
						forAdminType();
					}

					else {
						String actualValue = "";
						final WebElement field = CreateRecordManager.findFieldOfThisAttribute(attribute);
						actualValue = GeneralDataProvider.setRandomValueToThisAttribute(field, attribute);

						if (attribute.equals(attributeForSearch)) {
							valueForSearch = actualValue;
						}
					}

				}

				CreateRecordManager.clickOnSaveAndCloseButton();
				CreatePublicationManager.checkForMessageBoxes();
				
				return valueForSearch;
	}

}
