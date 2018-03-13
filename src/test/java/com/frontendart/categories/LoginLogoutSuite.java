package com.frontendart.categories;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.frontendart.testsuites.login.ForgotPasswordTest;
import com.frontendart.testsuites.login.LoginTest;
import com.frontendart.testsuites.login.LogoutTest;
import com.frontendart.testsuites.login.ResetPasswordTest;

@RunWith(Categories.class)
@SuiteClasses({
		LoginTest.class,
		LogoutTest.class,
		ForgotPasswordTest.class,
		ResetPasswordTest.class
})
@IncludeCategory(LoginLogoutSuite.class)
/**
 * Test suite for login, logout and forgot password tests
 * 
 * @author Zoli
 *
 */
public class LoginLogoutSuite {

}