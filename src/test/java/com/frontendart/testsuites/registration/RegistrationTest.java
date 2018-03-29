package com.frontendart.testsuites.registration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.CoreSuite;
import com.frontendart.categories.RegistrationSuite;
import com.frontendart.common.Constants;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.RegistrationJunitTestClass;
import com.frontendart.locators.registration.RegistrationLocators;
import com.frontendart.locators.registration.RegistrationMessageTypes;
import com.frontendart.managers.registration.RegistrationManager;

/**
 * Test class for registration tests.
 *
 * @author Zoli
 *
 */
@Category({ RegistrationSuite.class, CENTRAL_ADMIN_Suite.class })
@SuppressWarnings({ "PMD.JUnitTestsShouldIncludeAssert", "PMD.JUnitTestContainsTooManyAsserts" })
public class RegistrationTest extends RegistrationJunitTestClass {

    // This test class should only run with admin (mtmtuser4) rights - meaning that it will only run once
    @BeforeClass
    public static void beforeMethod() {
        org.junit.Assume.assumeTrue(Utils.actualRoleIsCentralAdmin());
    }

    /**
     * Simple registration test with fill all fields Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1062">#1062</a> Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1123">#1123</a> Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1153">#1153</a> Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1154">#1154</a> Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1325">#1325</a> Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1357">#1357</a> Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1719">#1719</a>
     */
    @Test
    @Ignore // db-be nem írunk
    public final void testRegistrationPOZ() {
        Utils.writeMyRedmineIssues("#1062#1123#1153#1154#1325#1357#1719");

        // Successful registration
        RegistrationManager.doSuccessfulRegistration();

    }

    /**
     * Simple registration test with fill required fields
     */
    @Test
    @Ignore // db-be nem írunk
    public final void testRegistrationWithRequiredFieldsOnly() {
        // Fill all fields
        RegistrationManager.fillRequiredFields();

        // Click register button
        RegistrationManager.clickOnRegisterButtonAndCheckMessage(RegistrationMessageTypes.SUCCESSFUL);
    }

    /**
     * Register button without fill anything
     */
    @Test
    @Category(CoreSuite.class)
    public final void testEmptyForm() {
        // Click register button
        RegistrationManager.clickOnRegisterButtonAndCheckMessage(RegistrationMessageTypes.UNSUCCESSFUL_DATA_POLICY);
    }

    /**
     * Register without captcha
     */
    @Test
    @Category(CoreSuite.class)
    public final void testRegisterWithoutCaptcha() {
        // Fill all fields
        RegistrationManager.fillAllRegistrationFields();

        // Clear Captcha field
        Utils.writeTextToThisField("", RegistrationLocators.CAPTCHA);

        // Click register button
        RegistrationManager.clickOnRegisterButtonAndCheckIncorrectField();
    }

    /**
     * Register with wrong captcha
     */
    @Test
    @Category(CoreSuite.class)
    public final void testRegisterWithWrongCaptcha() {
        // Fill all fields
        RegistrationManager.fillAllRegistrationFields();

        // Clear Captcha field
        Utils.writeTextToThisField(Utils.randomString(), RegistrationLocators.CAPTCHA);

        // Click register button
        RegistrationManager.clickOnRegisterButtonAndCheckMessage(RegistrationMessageTypes.WRONG_CAPTCHA);
    }

    /**
     * Registration test with wrong email Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/966">#966</a>
     */
    @Test
    @Category(CoreSuite.class)
    public final void testWithWrongEmailFormat() {
        Utils.writeMyRedmineIssues("#966");

        // Fill all fields, clear captcha
        RegistrationManager.fillAllRegistrationFields();
        Utils.writeTextToThisField(Utils.randomString(), RegistrationLocators.EMAIL);

        // Click register button, and validate
        RegistrationManager.clickOnRegisterButtonAndCheckIncorrectField();

    }

    /**
     * Register without family name
     */
    @Test
    @Category(CoreSuite.class)
    public final void testRegisterWithoutFamilyName() {
        // Fill all fields, clear family name
        RegistrationManager.fillAllRegistrationFields();
        Utils.writeTextToThisField("", RegistrationLocators.FAMILY_NAME);

        // Click register button and validate
        RegistrationManager.clickOnRegisterButtonAndCheckIncorrectField();
    }

    /**
     * Register without given name
     */
    @Test
    @Category(CoreSuite.class)
    public final void testRegisterWithoutGivenName() {
        // Fill all fields, clear given name
        RegistrationManager.fillAllRegistrationFields();
        Utils.writeTextToThisField("", RegistrationLocators.GIVEN_NAME);

        // Click register button and validate
        RegistrationManager.clickOnRegisterButtonAndCheckIncorrectField();
    }

    /**
     * Register without username
     */
    @Test
    @Category(CoreSuite.class)
    public final void testRegisterWithoutUsername() {
        // Fill all fields, clear given name
        RegistrationManager.fillAllRegistrationFields();
        Utils.writeTextToThisField("", RegistrationLocators.USERNAME);
        Utils.defaultWait();
        Utils.defaultWait();
        // Click register button and validate
        RegistrationManager.clickOnRegisterButtonAndCheckIncorrectField();
    }

    /**
     * Registration test without accepting the data handling policy
     */
    @Test
    @Category(CoreSuite.class)
    public final void testRegisterWithoutDataHandling() {
        // Fill all fields, clear data handling
        RegistrationManager.fillAllRegistrationFields();
        Utils.createGeneralWebElementFromString(RegistrationLocators.DATA_HANDLING_POLICY.toString()).click();

        // Click register button and validate
        RegistrationManager.clickOnRegisterButtonAndCheckMessage(RegistrationMessageTypes.UNSUCCESSFUL_DATA_POLICY);
    }

    // FURTHER TESTS TO IMPLEMENT

    /**
     * Registration test without filling the required password fields
     *
     *
     */
    @Test
    @Category(CoreSuite.class)
    public final void testRegistrationWithoutAnyPasswordsShouldShowErrorMessage() {
        // Fill all fields, clear data handling
        RegistrationManager.fillAllRegistrationFields();

        Utils.writeTextToThisField("", RegistrationLocators.PASSWORD);
        Utils.writeTextToThisField("", RegistrationLocators.PASSWORD_AGAIN);

        // Click register button and validate
        RegistrationManager.clickOnRegisterButtonAndCheckIncorrectField();
    }

    /**
     * Registration test without fill the required birth place field
     */

    @Test
    @Category(CoreSuite.class)
    public final void testRegistrationWithoutBirthPlaceShouldShowErrorMessage() {
        // Fill all fields, clear data handling
        RegistrationManager.fillAllRegistrationFields();

        Utils.writeTextToThisField("", RegistrationLocators.BIRTH_PLACE);

        // Click register button and validate
        RegistrationManager.clickOnRegisterButtonAndCheckIncorrectField();
    }

    /**
     * Registration test without filling the required birth date field
     */
    @Test
    @Category(CoreSuite.class)
    public final void testRegistrationWithoutBirthDateShouldShowErrorMessage() {
        // Fill all fields, clear data handling
        RegistrationManager.fillAllRegistrationFields();

        Utils.writeTextToThisField("", RegistrationLocators.BIRTH_DATE);

        // Click register button and validate
        RegistrationManager.clickOnRegisterButtonAndCheckIncorrectField();
    }

    /**
     * Registration test with too short password. Minimal length is 6 characters for strong pw
     */

    @Test
    @Category(CoreSuite.class)
    public final void testRegistrationWithTooShortStrongPasswordShouldShowErrorMessage() {
        // Fill all fields, clear data handling
        RegistrationManager.fillAllRegistrationFields();

        //generating random pw which contains lowercase, uppercase and number characters and its length is 5
        String pw = RegistrationManager.generateStrongPassword(5);
        // List<Character> pw = Arrays.asList((lowerCase + upperCase + number).toCharArray());
        Utils.writeTextToThisField(pw, RegistrationLocators.PASSWORD);
        Utils.writeTextToThisField(pw, RegistrationLocators.PASSWORD_AGAIN);
        Utils.defaultWait();
        // Click register button and validate
        RegistrationManager.clickOnRegisterButtonAndCheckIncorrectField();

    }

    /**
     * Registration test with too short weak password. Minimal length is 7 characters for weak pw
     */
    @Test
    @Category(CoreSuite.class)
    public final void testRegistrationWithTooShortWeakPasswordShouldShowErrorMessage() {
        // Fill all fields, clear data handling
        RegistrationManager.fillAllRegistrationFields();

        //generating random pw which contains lowercase, uppercase and number characters and its length is 5
        String pw = Utils.randomString("abcdefghijklmnopqrstuvwxyz".toCharArray(), 6);
        // List<Character> pw = Arrays.asList((lowerCase + upperCase + number).toCharArray());
        Utils.writeTextToThisField(pw, RegistrationLocators.PASSWORD);
        Utils.writeTextToThisField(pw, RegistrationLocators.PASSWORD_AGAIN);
        Utils.defaultWait();
        // Click register button and validate
        RegistrationManager.clickOnRegisterButtonAndCheckIncorrectField();
    }

    /**
     * Registration test without filling the required specialities field
     */
    @Test
    @Category(CoreSuite.class)
    public final void testRegistrationWithoutSpecialitiesShouldShowErrorMessage() {
        // Fill all fields, clear data handling
        RegistrationManager.fillAllRegistrationFields();
        Utils.createGeneralWebElementFromEnum(RegistrationLocators.SPECIALITIES_MINUS_BUTTON).click();
        Utils.defaultWait();
        // Click register button and validate
        RegistrationManager.clickOnRegisterButtonAndCheckIfThisMessageIsShown(RegistrationMessageTypes.UNSUCCESSFUL_SPECIALITIES);
    }

    /**
     * Registration test without filling the required institute field
     */
    @Test
    @Category(CoreSuite.class)
    public final void testRegistrationWithoutInstituteShouldShowErrorMessage() {
        // Fill all fields, clear data handling
        RegistrationManager.fillAllRegistrationFields();
        Utils.createGeneralWebElementFromString(RegistrationLocators.INSTITUTE.toString() + "/div/div/div[4]").click();
        Utils.defaultWait();
        Utils.writeTextToThisField("", RegistrationLocators.INSTITUTE_TEXT);
        // Click register button and validate
        RegistrationManager.clickOnRegisterButtonAndCheckIfThisMessageIsShown(RegistrationMessageTypes.UNSUCCESSUL_INSTITUTES_BOTH);
    }

    /**
     * Registration test without fill the required email field
     */
    @Test
    @Category(CoreSuite.class)
    public final void testRegistrationWithoutEmailShouldShowErrorMessage() {
        // Fill all fields, clear data handling
        RegistrationManager.fillAllRegistrationFields();

        Utils.writeTextToThisField("", RegistrationLocators.EMAIL);
        // Click register button and validate
        RegistrationManager.clickOnRegisterButtonAndCheckIncorrectField();
    }

    /**
     * Registration test with adding one day later than actual date as birth date
     */
    @Test
    @Category(CoreSuite.class)
    public final void testRegistrationWithLaterBirthDateThanTodayShouldShowErrorMessage() {
        // Fill all fields, clear data handling
        RegistrationManager.fillAllRegistrationFields();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String tomorrow = df.format(calendar.getTime());
        System.out.println(tomorrow);
        Utils.writeTextToThisField(tomorrow, RegistrationLocators.BIRTH_DATE);

        // Click register button and validate
        Utils.defaultWait();
        String dateErrorMessage = Utils.createGeneralWebElementFromString(RegistrationLocators.DATE_ERROR_MESSAGE.toString()).getText();
        Utils.myAssertTrue("There should be an error message about the date!", dateErrorMessage.contains("A dátum nem lehet későbbi, mint")
                || dateErrorMessage.contains("The date in this field must be equal to or before"));
        RegistrationManager.clickOnRegisterButtonAndCheckIncorrectField();
    }

    /**
     * Registration test with not a same passwords
     */

    @Test
    @Category(CoreSuite.class)
    public final void testRegistrationWithDifferentPasswordsShouldShowErrorMessage() {
        // Fill all fields, clear data handling
        RegistrationManager.fillAllRegistrationFields();

        Utils.writeTextToThisField(Utils.randomString(Constants.CHARSET, 11), RegistrationLocators.PASSWORD_AGAIN);
        // Click register button and validate
        RegistrationManager.clickOnRegisterButtonAndCheckIncorrectField();
    }

    /**
     * Registration test without password1
     */
    @Test
    @Category(CoreSuite.class)
    public final void testRegistrationWithoutFirstPasswordsShouldShowErrorMessage() {
        // Fill all fields, clear data handling
        RegistrationManager.fillAllRegistrationFields();
        Utils.writeTextToThisField("", RegistrationLocators.PASSWORD);
        // Click register button and validate
        RegistrationManager.clickOnRegisterButtonAndCheckIncorrectField();
    }

    /**
     * Registration test without password2
     */
    @Test
    @Category(CoreSuite.class)
    public final void testRegistrationWithoutSecondPasswordsShouldShowErrorMessage() {
        // Fill all fields, clear data handling
        RegistrationManager.fillAllRegistrationFields();
        Utils.writeTextToThisField("", RegistrationLocators.PASSWORD_AGAIN);
        // Click register button and validate
        RegistrationManager.clickOnRegisterButtonAndCheckIncorrectField();
    }

    /**
     * Registration test: with invalid institute
     *
     *
     */

    /**
     * Registration test fill with an already registered given name
     *
     *
     */

    /**
     * Registration test fill with an already registered family name
     *
     *
     */

    /**
     * Registration test fill with an already registered email-address
     *
     *
     */

    /**
     * Registration test fill with an already registered familyname, givenname, birth place and birth date
     *
     *
     */

    /**
     * DEMO TEST Simple test - register with invalid phonenumber
     *
     *
     */

    /**
     * Registration test fill with an already registered phone-number
     *
     *
     */

    /**
     * Registration test: click on the help button
     *
     *
     */

    /**
     * Registration test: click on the helpdesk button
     *
     *
     */

    /**
     * Registration test: click on the public search button
     *
     *
     */

    /**
     * Registration test fill with an already registered username Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1271">#1271</a>
     */

}
