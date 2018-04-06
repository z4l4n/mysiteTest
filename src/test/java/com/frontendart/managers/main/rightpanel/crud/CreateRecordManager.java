package com.frontendart.managers.main.rightpanel.crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import com.frontendart.common.Constants;
import com.frontendart.common.TestConfiguration;
import com.frontendart.common.Utils;
import com.frontendart.locators.main.MainPageLocators;
import com.frontendart.locators.main.rightpanel.crud.RecordEditorLocators;
import com.frontendart.locators.main.rightpanel.crud.RecordEditorMessageTypes;
import com.frontendart.locators.main.rightpanel.top.UserProfileTableAttributes;
import com.frontendart.locators.records.attributes.general.GeneralRecordTypes;
import com.frontendart.locators.records.attributes.general.GeneralTableAttributes;
import com.frontendart.locators.records.attributes.general.RecordAttributeFlags;
import com.frontendart.managers.general.GeneralDataProvider;

/**
 * Manager class for adding new records
 *
 * TODO: refakt?
 *
 * @author Zoli
 *
 */
public class CreateRecordManager {

    /**
     * simple create new record
     *
     */

    public static void fillPasswordConfirmAfterChangingUserData() {

    }

    public static void createRecord(final GeneralRecordTypes myRecordType) {
        // click on new button
        clickOnNewButton();

        // Fill fields and click on save
        try {
            fillAllFields(myRecordType.getAttributes());
        } catch (final IndexOutOfBoundsException exception) {
            closeEditorWindow();
            fail("We cannot find an attribute at this record type: " + myRecordType + " - " + exception.getMessage());
        }

        clickOnSaveAndCloseButton();
    }

    /**
     * Clicks on new button
     */
    public static void clickOnNewButton() {
        Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.NEW_BUTTON);
        try {
            Utils.waitForElementVisible(RecordEditorLocators.EDITOR_WINDOW);
        } catch (TimeoutException e) {
            Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.NEW_BUTTON);
            Utils.defaultWait();
        }
    }

    /**
     * Clicks on new button
     */
    public static void clickOnEditButton() {
        Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.EDIT_BUTTON);
        Utils.waitForElementPresent(RecordEditorLocators.EDITOR_WINDOW);
    }

    /**
     * Clicks on Next button
     */
    public static void clickOnNextButton() {
        Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.NEXT_BUTTON);
    }

    /**
     * perform record creation until Save button click
     */
    public static void fillFields(final Class<? extends GeneralTableAttributes> attributes) {
        fillAllFields(attributes);
    }

    /**
     * perform record creation until Save button click
     */
    public static void fillAllFields(final Class<? extends GeneralTableAttributes> attributes) {
        final List<GeneralTableAttributes> notDisabledAttributes = GeneralRecordTypes.getNotDisabledAttributes(attributes);
        fillNotRequiredFieldsOnly(notDisabledAttributes);
        fillRequiredFieldsOnly(notDisabledAttributes);
    }

    /**
     * fills required fields only
     */
    public static void fillRequiredFieldsOnly(final List<? extends GeneralTableAttributes> notDisabledAttributes) {
        for (final GeneralTableAttributes attribute : notDisabledAttributes) {
            if (attribute.getAttributeFlags().contains(RecordAttributeFlags.REQUIRED)) {
                final WebElement field = findFieldOfThisAttribute(attribute);
                GeneralDataProvider.setRandomValueToThisAttribute(field, attribute);
            }
        }
    }

    /**
     * fills not required fields only
     */
    public static void fillNotRequiredFieldsOnly(final List<GeneralTableAttributes> notDisabledAttributes) {
        for (final GeneralTableAttributes attribute : notDisabledAttributes) {
            if (!attribute.getAttributeFlags().contains(RecordAttributeFlags.REQUIRED)) {
                final WebElement field = findFieldOfThisAttribute(attribute);
                GeneralDataProvider.setRandomValueToThisAttribute(field, attribute);
            }
        }
    }

    /**
     * Finds the field
     *
     * @param attribute
     */
    public static WebElement findFieldOfThisAttribute(final GeneralTableAttributes attribute) {
        Utils.defaultWait();

        final List<WebElement> allFields;
        final List<WebElement> allLabels;
        if (attribute instanceof UserProfileTableAttributes) {
            allFields = Utils.createGeneralWebElementsFromEnum(RecordEditorLocators.USER_EDITOR_FORM_FIELDS);
            allLabels = Utils.createGeneralWebElementsFromEnum(RecordEditorLocators.USER_EDITOR_FORM_FIELDS_LABEL);
        } else {
            allFields = Utils.createGeneralWebElementsFromEnum(RecordEditorLocators.EDITOR_FORM_FIELDS);
            allLabels = Utils.createGeneralWebElementsFromEnum(RecordEditorLocators.EDITOR_FORM_FIELDS_LABEL);
        }

        final List<String> allLabelsAsString = Utils.convertThisWebElementArrayToStringArray(allLabels);

        // Language check (If English is the active, we should check for the English name of the attribute)
        int nameIndex = 0;
        final String language = Utils.createGeneralWebElementFromEnum(MainPageLocators.CHANGE_LANGUAGE_BUTTON).getText();
        if ("English".equals(language)) {
            nameIndex = 1;
        }

        // Tries to find given attribute field.
        WebElement returnValue = null;
        try {
            returnValue = allFields.get(allLabelsAsString.indexOf(attribute.getNames().get(nameIndex).concat(":")));
        } catch (final ArrayIndexOutOfBoundsException e) {
            try {
                returnValue = allFields.get(allLabelsAsString.indexOf(attribute.getNames().get(nameIndex).concat(":*")));
            } catch (final ArrayIndexOutOfBoundsException exc) {
                System.out.println("Keresett field: " + attribute.getNames().get(nameIndex).concat(":*"));
                System.out.println("Összes mező:" + allFields);

                fail("Could not find this attribute on page: " + Utils.getAllMyLabelsAsString(attribute) + ". Error message is: "
                        + exc.getMessage());
            }
        }

        return returnValue;
    }

    /**
     * Click on save and close button
     *
     */
    public static void clickOnSaveAndCloseButton() {
        Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.SAVE_AND_CLOSE);
        Utils.defaultWait();
    }

    /**
     * Clicks on cancel button
     */
    public static void closeEditorWindow() {
        Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.CLOSE);

        Utils.acceptMessageBoxIfVisible();
    }

    /**
     * Clicks on cancel button
     */
    public static void cancelEditorWindow() {
        Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.CANCEL_BUTTON);

        Utils.acceptMessageBoxIfVisible();
    }

    /**
     * Gets editor text and fill fields
     */
    public static void getEditorHeaderAndFillFields() {
        final String header = Utils.createGeneralWebElementFromEnum(RecordEditorLocators.EDITOR_HEADER).getText();

        final String recordTypeName = header.split(":")[1].substring(1);
        fillFields(GeneralRecordTypes.findAttributesTypeByName(recordTypeName));
        clickOnSaveAndCloseButton();

//		try{
//			clickOnSaveAndCloseButton();
//		}
//
//		catch( ){
//
//		}
    }

    /**
     * Checks messages
     */
    public static void checkSuccessfulRecordCreationMessage() {
        switch (Utils.getActualRole()) {
            case AUTHOR:
                assertTrue("There should be a message present on the new record creation page!",
                        Utils.isMessageBoxPresentWithText(RecordEditorMessageTypes.AUTHOR_MESSAGE));
                break;
            case INSTITUTIONAL_ADMIN:
                assertTrue("There should be a message present on the new record creation page!",
                        Utils.isMessageBoxPresentWithText(RecordEditorMessageTypes.INSTADMIN_MESSAGE));
                break;
            default:
                break;
        }
        Utils.defaultWait();
    }

    /**
     * Checks messages
     */
    public static boolean checkEmptyFieldMessage() {
        return Utils.isMessageBoxPresentWithText(RecordEditorMessageTypes.EMPTY_FIELD);
    }

    /**
     * Creates publication with DOI
     */
    public static void createPublicationWithExistingDOI() {
        // click on new button
        clickOnNewButton();

        // Write to DOI field and click next
        Utils.writeTextToThisField(Constants.EXISTING_DOI.toString(), RecordEditorLocators.DOI_FIELD);
        Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.NEXT_BUTTON);
        Utils.defaultWait();
        Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.NEXT_BUTTON);
        switch (TestConfiguration.role) {
            case CENTRAL_ADMIN:
                assertTrue("There should be a message present on the new record creation page with existing DOI!",
                        Utils.isMessageBoxPresentWithText(RecordEditorMessageTypes.EXISTING_DOI));
                break;
            case INSTITUTIONAL_ADMIN:
            case AUTHOR:
                assertTrue("There should be a message present on the new record creation page with existing DOI!",
                        Utils.isMessageBoxPresentWithText(RecordEditorMessageTypes.EXISTING_DOI_INSTADMIN));
                break;
            default:
                break;
        }

        Utils.waitForMessageBoxPresentAndAccept();

        // Click on save and close button
        closeEditorWindow();
    }

    /**
     * Creates publication with DOI
     */
    public static void createPublicationWithDOI() {
        // click on new button
        clickOnNewButton();

        // Write to DOI field and click next
        Utils.writeTextToThisField(Constants.MAGIC_DOI.toString(), RecordEditorLocators.DOI_FIELD);
        Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.NEXT_BUTTON);

        Utils.waitForElementPresent(RecordEditorLocators.PUBLICATION_SAVE_WINDOW);

        //Utils.createGeneralWebElementFromEnum(RecordEditorLocators.MTMT_ID_VALUE).getText();

        CreatePublicationManager.fillFinalFieldsAndSave();

        // Check for message boxes
        CreatePublicationManager.checkForMessageBoxes();

        // Check SWORD window
        CreatePublicationManager.checkForSwordWindow();

        //Rerun empty query (to get the new result), then validate
        //SearchManager.createAndRunSubsetQuery(GeneralRecordTypes.PUBLICATION);

    }

    /**
     * Creates publication with DOI
     */
    public static void createPublicationWithDOIDefiecient() {
        // click on new button
        clickOnNewButton();

        // Write to DOI field and click next
        Utils.writeTextToThisField(Constants.MAGIC_DOI.toString(), RecordEditorLocators.DOI_FIELD);
        Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.NEXT_BUTTON);

        Utils.waitForElementPresent(RecordEditorLocators.PUBLICATION_SAVE_WINDOW);

        //Utils.createGeneralWebElementFromEnum(RecordEditorLocators.MTMT_ID_VALUE).getText();

        CreatePublicationManager.fillFinalFieldsWithoutSubtypeAndCategoryAndSave();

        // Check for message boxes
        CreatePublicationManager.checkForMessageBoxes();

        // Check SWORD window
        CreatePublicationManager.checkForSwordWindow();

        //Rerun empty query (to get the new result), then validate
        //SearchManager.createAndRunSubsetQuery(GeneralRecordTypes.PUBLICATION);

    }

    public static void checkActualStatusIsCorrect(String expectedStatus) {
        Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.EDIT_BUTTON);
        Utils.waitForElementPresent(RecordEditorLocators.MODEL_EDITOR_WINDOW);
        Utils.defaultWait();
        String status = Utils.createGeneralWebElementsFromEnum(RecordEditorLocators.VALUES_LIST_PUBLICATON).get(1).getText();
        CreateRecordManager.closeEditorWindow();

        assertEquals(expectedStatus, status);
    }
}
