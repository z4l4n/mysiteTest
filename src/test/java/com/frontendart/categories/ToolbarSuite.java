package com.frontendart.categories;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.frontendart.testsuites.main.rightpanel.view.ToolbarTest;

@RunWith(Categories.class)
@SuiteClasses({
		ToolbarTest.class,
})
@IncludeCategory(ToolbarSuite.class)
/**
 * Test suite for search tests
 * 
 * @author Zoli
 *
 */
public class ToolbarSuite {

}