package com.frontendart.managers.main.rightpanel.top;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;

import com.frontendart.common.Utils;
import com.frontendart.locators.main.MainPageLocators;
import com.frontendart.locators.main.rightpanel.crud.RecordEditorLocators;
import com.frontendart.locators.main.rightpanel.top.ChangePasswordLocators;
import com.frontendart.locators.main.rightpanel.top.UserProfileTableAttributes;
import com.frontendart.locators.records.attributes.general.AuthorRecordAttributes;
import com.frontendart.locators.records.attributes.general.GeneralTableAttributes;
import com.frontendart.managers.general.GeneralDataProvider;
import com.frontendart.managers.main.rightpanel.crud.CreateRecordManager;

/**
 * class for user data manager
 *
 * TODO: refaktor
 *
 * @author Zoli
 *
 */
public class UserDataManager {

    /**
     * Edit random user data field
     *
     */
    public static boolean editRandomField() {
        final int random = Utils.randInt(1, UserProfileTableAttributes.values().length - 2);
        final UserProfileTableAttributes randomFieldToEdit = UserProfileTableAttributes.values()[random - 1];

        performEditAndCheckField(randomFieldToEdit);

        return true;

    }

    /**
     * Opens profile editor
     *
     */
    public static void openProfileEditor() {
        Utils.waitForAndClickOnGeneralWebElement(MainPageLocators.MY_PROFILE_BUTTON);

        Utils.waitUntilUsernameTooltipHide();

        Utils.waitForAndClickOnGeneralWebElement(MainPageLocators.MY_PROFILE_EDIT_OPTION);
    }

    /**
     * Opens profile editor
     *
     */
    public static void fillRequiredDatasWhenRoleIsAuthor() {
        AuthorRecordAttributes birthDate = AuthorRecordAttributes.BIRTH_DATE;
        WebElement fieldOfBirthDate = CreateRecordManager.findFieldOfThisAttribute(birthDate);
        fieldOfBirthDate = CreateRecordManager.findFieldOfThisAttribute(birthDate);
        GeneralDataProvider.setRandomValueToThisAttribute(fieldOfBirthDate, birthDate);

        AuthorRecordAttributes birthPlace = AuthorRecordAttributes.BIRTH_PLACE;
        WebElement fieldOfBirthPlace = CreateRecordManager.findFieldOfThisAttribute(birthPlace);
        fieldOfBirthPlace = CreateRecordManager.findFieldOfThisAttribute(birthPlace);
        GeneralDataProvider.setRandomValueToThisAttribute(fieldOfBirthPlace, birthPlace);
    }

    /**
     * performs edit and checks field
     */
    public static void performEditAndCheckField(final UserProfileTableAttributes attribute) {

        WebElement field = CreateRecordManager.findFieldOfThisAttribute(attribute);
        final String myOldValue = GeneralDataProvider.getCurrentValueOfThisField(field, attribute);

        // Write new value, and save
        field = CreateRecordManager.findFieldOfThisAttribute(attribute);
        final String randomNewValue = GeneralDataProvider.setRandomValueToThisAttribute(field, attribute);

        Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.USER_SAVE_AND_CLOSE);
        Utils.defaultWait();
        Utils.createGeneralWebElementFromEnum(RecordEditorLocators.PASSWORD_CONFIRM_INPUT_FIELD)
                .sendKeys(Utils.getActualRole().getPassword());
        Utils.defaultWait();

        Utils.createGeneralWebElementFromEnum(RecordEditorLocators.PASSWORD_CONFIRM_OK_BUTTON).click();
        Utils.defaultWait();
        // This is needed for emtpy fields question
        Utils.acceptMessageBoxIfVisible();

        final boolean isErrorWindowPresent = Utils.isThisElementPresent(ChangePasswordLocators.ERROR_WINDOW);
        if (isErrorWindowPresent) {
            Utils.acceptMessageBoxIfVisible();
            Utils.waitForAndClickOnGeneralWebElement(ChangePasswordLocators.ERROR_WINDOW_REFRESH_BUTTON);
            Utils.defaultWait();
            fail("There is an unexpected error window present after trying to edit user profile!");
        }

        // check that there is no error window present
        Utils.myAssertFalse("Nem szabad hibaüzenetnek megjelennie.", Utils.acceptMessageBoxIfVisible());
        Utils.defaultWait();

        // refresh
        openProfileEditor();

        // Check value
        field = CreateRecordManager.findFieldOfThisAttribute(attribute);
        final String myNewValue = GeneralDataProvider.getCurrentValueOfThisField(field, attribute);

        Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.USER_CANCEL_BUTTON);
        Utils.myAssertFalse("Az új értéknek nem szabad megegyeznie a régivel.", myOldValue.equals(myNewValue));
        Utils.myAssertTrue("Az új értékenk a következőnek kell lennie: " + randomNewValue, myNewValue.equals(randomNewValue));

        // write back old value
        openProfileEditor();
        field = CreateRecordManager.findFieldOfThisAttribute(attribute);
        final Map<GeneralTableAttributes, String> attributeValues = new HashMap<>();
        attributeValues.put(attribute, myOldValue);
        GeneralDataProvider.setThisValueToAttribute(field, attributeValues);
        Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.USER_SAVE_AND_CLOSE);
        Utils.defaultWait();
        Utils.createGeneralWebElementFromEnum(RecordEditorLocators.PASSWORD_CONFIRM_INPUT_FIELD)
                .sendKeys(Utils.getActualRole().getPassword());
        Utils.defaultWait();

        Utils.createGeneralWebElementFromEnum(RecordEditorLocators.PASSWORD_CONFIRM_OK_BUTTON).click();
        Utils.defaultWait();
        Utils.acceptMessageBoxIfVisible();

        // refresh and validate
        openProfileEditor();
        field = CreateRecordManager.findFieldOfThisAttribute(attribute);
        final String myFinalValue = GeneralDataProvider.getCurrentValueOfThisField(field, attribute);
        Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.USER_CANCEL_BUTTON);
        Utils.myAssertTrue("Az új értéknek a régivel kell megegyeznie.", myOldValue.equals(myFinalValue));
    }

}
