package com.frontendart.categories;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.frontendart.testsuites.main.leftpanel.SearchTest;

@RunWith(Categories.class)
@SuiteClasses({
		SearchTest.class,
})
@IncludeCategory(SimpleSearchSuite.class)
/**
 * Test suite for search tests
 * 
 * @author Zoli
 *
 */
public class SimpleSearchSuite {

}