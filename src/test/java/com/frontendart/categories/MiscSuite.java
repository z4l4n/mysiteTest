package com.frontendart.categories;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.frontendart.testsuites.misc.HelpTest;
import com.frontendart.testsuites.misc.LanguageTest;
import com.frontendart.testsuites.misc.RedmineIssuesTest;

@RunWith(Categories.class)
@SuiteClasses({
		HelpTest.class,
		LanguageTest.class,
		RedmineIssuesTest.class,
})
@IncludeCategory(MiscSuite.class)
/**
 * Test suite for misc tests
 * 
 * @author Zoli
 *
 */
public class MiscSuite {

}