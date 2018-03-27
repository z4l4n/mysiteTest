package com.frontendart.managers.registration;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.frontendart.common.Constants;
import com.frontendart.common.Utils;
import com.frontendart.locators.general.GeneralSelectorBoxLocators;
import com.frontendart.locators.registration.RegistrationLocators;
import com.frontendart.locators.registration.RegistrationMessageTypes;
import com.frontendart.managers.general.GeneralSelectorBoxManager;

/**
 * Manager class for registration tests.
 *
 * @author Zoli
 *
 */
public class RegistrationManager {

    /**
     * Select from a Field WebElement
     */
    public static void selectRandomFromThisRegistrationSelectBox(final RegistrationLocators elementType) {
        final WebElement element = Utils.createGeneralWebElementFromEnum(elementType);
        final WebElement trigger = element.findElement(By.xpath("./div/div/div[2]"));
        trigger.click();
        Utils.defaultWait();

        // Selects random from selectbox
        GeneralSelectorBoxManager.selectRandomFromSelectbox();
    }

    /**
     * Select from language selector
     */
    public static void selectFromLanguageSelector() {
        final WebElement element = Utils.createGeneralWebElementFromEnum(RegistrationLocators.LANGUAGE);
        final WebElement input = element.findElement(By.xpath(".//input"));
        input.sendKeys(Keys.DOWN);
        Utils.switchToActiveElement().sendKeys(Keys.ENTER);
    }

    /**
     * Select from a Field WebElement
     */
    public static void selectRandomFromInstituteSelectBox() {
        final WebElement element = Utils.createGeneralWebElementFromEnum(RegistrationLocators.INSTITUTE);
        final WebElement trigger = element.findElement(By.xpath("./div/div/div[2]"));
        trigger.click();
        Utils.defaultWait();

        // Filter (TODO: Random text filtering)
        Utils.writeTextToThisField("sze", GeneralSelectorBoxLocators.FIND_INSTITUTES_FILTER_FIELD);
        Utils.waitForAndClickOnGeneralWebElement(GeneralSelectorBoxLocators.FIND_INSTITUTES_FILTER_BUTTON);
        Utils.defaultWait();

        // Select
        final List<WebElement> items = Utils.createGeneralWebElementsFromEnum(GeneralSelectorBoxLocators.FIND_INSTITUTES_ITEMS);
        Utils.clickOnThisRow(items.get(Utils.randInt(1, items.size()) - 1));
        Utils.waitForAndClickOnGeneralWebElement(GeneralSelectorBoxLocators.FIND_INSTITUTES_SAVE_AND_CLOSE_BUTTON);
    }

    /**
     * Select from a Field WebElement
     */
    private static void selectRandomFromSpecialitiesSelectBox() {
        final WebElement element = Utils.createGeneralWebElementFromEnum(RegistrationLocators.SPECIALITIES_PLUS_BUTTON);
        element.click();
        Utils.defaultWait();

        // Select
        GeneralSelectorBoxManager.selectRandomFromSelectbox();
    }

    /**
     * Clicks on register button
     *
     *
     */
    public static void clickOnRegisterButton() {
        Utils.waitForAndClickOnGeneralWebElement(RegistrationLocators.REGISTER_BUTTON);
        Utils.waitForMessageBoxToBePresent();
    }

    /**
     * Clicks on register button and waits
     *
     *
     */
    public static void clickOnRegisterButtonAndCheckMessage(final RegistrationMessageTypes message) {
        clickOnRegisterButton();
        assertTrue("There should be a message box present with this text: " + Utils.getAllMyLabelsAsString(message),
                Utils.isMessageBoxPresentWithText(message));
    }

    /**
     * Clicks on register button and checks incorrect fields
     *
     *
     * @param unsuccessfulIncorrectField
     */
    public static void clickOnRegisterButtonAndCheckIncorrectField() {
        // This is needed to navigate the screen to the send registration button
        Utils.createGeneralWebElementFromEnum(RegistrationLocators.CAPTCHA).click();
        Utils.switchToActiveElement().sendKeys(Keys.TAB);
        Utils.switchToActiveElement().sendKeys(Keys.TAB);

        // Click
        clickOnRegisterButtonAndCheckMessage(RegistrationMessageTypes.UNSUCCESSFUL_INCORRECT_FIELD);

        // TODO: check incorrect field
    }

    public static void clickOnRegisterButtonAndCheckIfThisMessageIsShown(RegistrationMessageTypes messageType) {
        // This is needed to navigate the screen to the send registration button
        Utils.createGeneralWebElementFromEnum(RegistrationLocators.CAPTCHA).click();
        Utils.switchToActiveElement().sendKeys(Keys.TAB);
        Utils.switchToActiveElement().sendKeys(Keys.TAB);

        // Click
        clickOnRegisterButtonAndCheckMessage(messageType);

    }

    /**
     * write text to password fields
     *
     */
    public static void writeRandomTextToPasswordFields() {
        final String password = Utils.randomString(Constants.CHARSET, 30);
        Utils.writeTextToThisField(password, RegistrationLocators.PASSWORD);
        Utils.writeTextToThisField(password, RegistrationLocators.PASSWORD_AGAIN);
    }

    /**
     * fill all fields
     *
     */
    public static void fillAllRegistrationFields() {
        fillRequiredFields();
        fillNotRequiredFields();
    }

    /**
     * fill required fields fields
     *
     */
    public static void fillRequiredFields() {
        // Language
        selectFromLanguageSelector();

        // Institute
        selectRandomFromInstituteSelectBox();

        // Family name, given name
        Utils.writeTextToThisField(Constants.PREFIX + Utils.randomString(), RegistrationLocators.FAMILY_NAME);
        Utils.writeTextToThisField(Constants.PREFIX + Utils.randomString(), RegistrationLocators.GIVEN_NAME);

        // Speciality
        selectRandomFromSpecialitiesSelectBox();

        Utils.writeTextToThisField(Constants.PREFIX + Utils.randomString(), RegistrationLocators.SPECIALITIES_TEXT);

        // Username, password, password again
        Utils.writeTextToThisField(Constants.PREFIX + Utils.randomString(), RegistrationLocators.USERNAME);
        writeRandomTextToPasswordFields();

        // Birth place, birth date
        Utils.writeTextToThisField(Constants.PREFIX + Utils.randomString(), RegistrationLocators.BIRTH_PLACE);
        Utils.writeTextToThisField("1985-01-01", RegistrationLocators.BIRTH_DATE);

        // Email, phone, comment
        Utils.writeTextToThisField(Constants.PREFIX + Utils.randomString() + "@gmail.com", RegistrationLocators.EMAIL);

        // Data handling
        Utils.createGeneralWebElementFromString(RegistrationLocators.DATA_HANDLING_POLICY.toString()).click();

        // Captcha
        Utils.writeTextToThisField(Constants.CAPTCHA_MAGIC_STRING, RegistrationLocators.CAPTCHA);
    }

    /**
     * fill not required fields
     *
     */
    public static void fillNotRequiredFields() {
        Utils.writeTextToThisField(Constants.PREFIX + Utils.randomString(), RegistrationLocators.INSTITUTE_TEXT);

        // Degree
        try {
            selectRandomFromThisRegistrationSelectBox(RegistrationLocators.DEGREE);
        } catch (final NoSuchElementException e) {
            fail("There is an unexpected error message box present on the registration form, while adding degree attribute.");
        }

        // Specialities
        //selectRandomFromThisRegistrationSelectBox(RegistrationLocators.SPECIALITIES);

        // Phone comment
        Utils.writeTextToThisField("10/111-11-11", RegistrationLocators.PHONE);
        Utils.writeTextToThisField(Constants.PREFIX + Utils.randomString(), RegistrationLocators.COMMENT);
    }

    /**
     * Successful registration
     *
     */
    public static void doSuccessfulRegistration() {
        fillAllRegistrationFields();

        // Check successful registration
        clickOnRegisterButtonAndCheckMessage(RegistrationMessageTypes.SUCCESSFUL);

    }

}
