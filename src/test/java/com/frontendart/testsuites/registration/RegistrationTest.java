package com.frontendart.testsuites.registration;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.CoreSuite;
import com.frontendart.categories.RegistrationSuite;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.RegistrationJunitTestClass;
import com.frontendart.locators.registration.RegistrationLocators;
import com.frontendart.locators.registration.RegistrationMessageTypes;
import com.frontendart.managers.registration.RegistrationManager;

/**
 * Test class for registration tests.
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
	 * Simple registration test with fill all fields
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1062">#1062</a>
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1123">#1123</a>
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1153">#1153</a>
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1154">#1154</a>
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1325">#1325</a>
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1357">#1357</a>
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1719">#1719</a>
	 */
	@Test
	public final void testRegistrationPOZ() {
		Utils.writeMyRedmineIssues("#1062#1123#1153#1154#1325#1357#1719");

		// Successful registration
		RegistrationManager.doSuccessfulRegistration();

	}

	/**
	 * Simple registration test with fill required fields
	 */
	@Test
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
	 * Registration test with wrong email
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/966">#966</a>
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

		// Click register button and validate
		RegistrationManager.clickOnRegisterButtonAndCheckIncorrectField();
	}

	/**
	 * Registration test without accept the data handling policy
	 */
	@Test
	@Category(CoreSuite.class)
	public final void testRegisterWithoutDataHandling() {
		// Fill all fields, clear data handling
		RegistrationManager.fillAllRegistrationFields();
		Utils.waitForAndClickOnGeneralWebElement(RegistrationLocators.DATA_HANDLING_POLICY);

		// Click register button and validate
		RegistrationManager.clickOnRegisterButtonAndCheckMessage(RegistrationMessageTypes.UNSUCCESSFUL_DATA_POLICY);
	}

	// FURTHER TESTS TO IMPLEMENT

	/**
	 * Registration test fill with an already registered family name
	 * 
	 *
	 */

	/**
	 * Registration test without fill the required password fields
	 * 
	 *
	 */

	/**
	 * Registration test fill with an already registered given name
	 * 
	 *
	 */

	/**
	 * Registration test without fill the required birth place field
	 * 
	 *
	 */

	/**
	 * Registration test without fill the required birth date field
	 * 
	 *
	 */

	/**
	 * Registration test with adding too short password
	 * 
	 *
	 */

	/**
	 * Registration test without fill the required specialities field
	 * 
	 *
	 */

	/**
	 * Registration test without fill the required institute field
	 * 
	 *
	 */

	/**
	 * Registration test without fill the required email field
	 * 
	 *
	 */

	/**
	 * Registration test fill with an already registered email-address
	 * 
	 *
	 */

	/**
	 * Registration test without accept the data handling policy
	 * 
	 *
	 */

	/**
	 * Registration test fill with an already registered familyname, givenname, birth place and birth date
	 * 
	 *
	 */

	/**
	 * Registration test with adding one day later than actual date as birth
	 * date
	 * 
	 *
	 */

	/**
	 * Registration test with adding the actual date as birth date
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
	 * Registration test with not a same passwords
	 * 
	 *
	 */

	/**
	 * Registration test without password1
	 * 
	 *
	 */

	/**
	 * Registration test without password2
	 * 
	 *
	 */

	/**
	 * Registration test: with invalid institute
	 * 
	 *
	 */

	/**
	 * Registration test fill with an already registered username
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1271">#1271</a>
	 */

}
