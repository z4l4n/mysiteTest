package com.frontendart.categories;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.frontendart.testsuites.registration.RegistrationTest;

@RunWith(Categories.class)
@SuiteClasses({
		RegistrationTest.class,
})
@IncludeCategory(RegistrationSuite.class)
/**
 * Test suite for registration tests
 * 
 * @author Zoli
 *
 */
public class RegistrationSuite {

}