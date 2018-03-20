package com.frontendart.managers.main.rightpanel.crud;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.frontendart.common.Utils;
import com.frontendart.locators.general.GeneralPageLocators;
import com.frontendart.locators.main.rightpanel.crud.RecordEditorLocators;
import com.frontendart.locators.records.attributes.general.GeneralTableAttributes;
import com.frontendart.locators.records.attributes.general.PublicationRecordAttributes;
import com.frontendart.locators.records.attributes.hidden.AdditionalPublicationAttributes;
import com.frontendart.managers.general.GeneralDataProvider;

/**
 * Manager class for adding new publication
 * 
 * @author Zoli
 *
 */
public class CreatePublicationManager {

	/**
	 * Fills type, subtype and category and then clicks on next button
	 */
	public static void fillFirstStepAndClickOnNextButton() {
		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(PublicationRecordAttributes.TYPE, PublicationRecordAttributes.SUBTYPE,
				PublicationRecordAttributes.CATEGORY));

		for (final GeneralTableAttributes attribute : attributes) {
			final WebElement field = CreateRecordManager.findFieldOfThisAttribute(attribute);
			GeneralDataProvider.setRandomValueToThisAttribute(field, attribute);
		}

		CreateRecordManager.clickOnNextButton();
		Utils.defaultWait();
	}

	/**
	 * If there should be some additional information...
	 */
	public static void checkForInBetweenStep() {
		if (Utils.isThisElementPresent(RecordEditorLocators.NEXT_BUTTON)) {
			fillAttributesOnPage();
			CreateRecordManager.clickOnNextButton();
			Utils.defaultWait();
			checkForInBetweenStep();
		}

	}

	/**
	 * Fills final steps
	 * TODO: the number of final steps are varying based on the publication 
	 */
	public static void fillFinalFieldsAndSave() {
		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		
//		attributes.addAll(EnumSet.of(PublicationRecordAttributes.LANGUAGE, PublicationRecordAttributes.AUTHORSHIP,
//				PublicationRecordAttributes.TITLE, PublicationRecordAttributes.SUBTITLE, PublicationRecordAttributes.PUBLISHED_YEAR));
		
		attributes.addAll(EnumSet.of(PublicationRecordAttributes.LANGUAGE, PublicationRecordAttributes.AUTHORSHIP,
				PublicationRecordAttributes.TITLE, PublicationRecordAttributes.SUBTITLE, PublicationRecordAttributes.PUBLISHED_YEAR, PublicationRecordAttributes.SUBTYPE, PublicationRecordAttributes.CATEGORY, PublicationRecordAttributes.SUBJECTS));
		
		for (final GeneralTableAttributes attribute : attributes) {
			final WebElement field = CreateRecordManager.findFieldOfThisAttribute(attribute);
			GeneralDataProvider.setRandomValueToThisAttribute(field, attribute);
		}

		CreateRecordManager.clickOnSaveAndCloseButton();
	}
	
	/**
	 * Fills final steps
	 * TODO: the number of final steps are varying based on the publication 
	 */
	public static void fillFinalFieldsWithoutSubtypeAndCategoryAndSave() {
		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		
//		attributes.addAll(EnumSet.of(PublicationRecordAttributes.LANGUAGE, PublicationRecordAttributes.AUTHORSHIP,
//				PublicationRecordAttributes.TITLE, PublicationRecordAttributes.SUBTITLE, PublicationRecordAttributes.PUBLISHED_YEAR));
		
		attributes.addAll(EnumSet.of(PublicationRecordAttributes.LANGUAGE, PublicationRecordAttributes.AUTHORSHIP,
				PublicationRecordAttributes.TITLE, PublicationRecordAttributes.SUBTITLE, PublicationRecordAttributes.PUBLISHED_YEAR, PublicationRecordAttributes.SUBJECTS, PublicationRecordAttributes.CATEGORY));
		
		for (final GeneralTableAttributes attribute : attributes) {
			final WebElement field = CreateRecordManager.findFieldOfThisAttribute(attribute);
			GeneralDataProvider.setRandomValueToThisAttribute(field, attribute);
		}

		CreateRecordManager.clickOnSaveAndCloseButton();
	}

	/**
	 * Fills fields on page
	 */
	static void fillAttributesOnPage() {
		final List<WebElement> visibleLabels = Utils.createGeneralWebElementsFromEnum(RecordEditorLocators.EDITOR_FORM_FIELDS_VISIBLE_LABELS_ALTERNATIVE);
		final List<String> visibleLabelsAsString = Utils.convertThisWebElementArrayToStringArray(visibleLabels);

		// Find and fill attribute for label
		for (final String label : visibleLabelsAsString) {
			GeneralTableAttributes fillThisAttribute = null;

			// Find attribute
			for (final GeneralTableAttributes attribute : AdditionalPublicationAttributes.values()) {
				if (Utils.doesThisStringListContainsThisAttribute(Arrays.asList(label), attribute)) {
					fillThisAttribute = attribute;
				}
			}

			// Fill attribute
			if (fillThisAttribute != null) {
				final WebElement field = CreateRecordManager.findFieldOfThisAttribute(fillThisAttribute);
				GeneralDataProvider.setRandomValueToThisAttribute(field, fillThisAttribute);
			}
		}
	}

	/**
	 * checks for Sword window
	 */
	public static void checkForSwordWindow() {
		if (Utils.isThisElementPresent(RecordEditorLocators.SWORD_WINDOW_HEADER)) {
			Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.SWORD_WINDOW_CLOSE);
			Utils.defaultWait();
		}

	}

	/**
	 * Checks for message boxes
	 */
	public static void checkForMessageBoxes() {
		if (Utils.isThisElementPresent(GeneralPageLocators.MESSAGE_BOX)) {
			final String messageBoxText = Utils.getMessageBoxText();
			Utils.acceptMessageBoxIfVisible();
			Utils.defaultWait();
			if (Utils.isThisElementPresent(RecordEditorLocators.CLOSE)) {
				Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.CLOSE);
				Utils.acceptMessageBoxIfVisible();
				fail("An unexpected message box prevented saving publication. Text: " + messageBoxText);
			}
		}
	}

}
