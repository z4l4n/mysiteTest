package com.frontendart.categories;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.frontendart.testsuites.main.leftpanel.GenerateReportTest;
import com.frontendart.testsuites.main.leftpanel.RecordSelectionTest;
import com.frontendart.testsuites.main.leftpanel.SavedListsTest;
import com.frontendart.testsuites.main.leftpanel.SearchTest;

@RunWith(Categories.class)
@SuiteClasses({
		GenerateReportTest.class,
		RecordSelectionTest.class,
		SavedListsTest.class,
		SearchTest.class,
})
@IncludeCategory(LeftPanelSuite.class)
/**
 * Test suite for queries tests
 * 
 * @author Zoli
 *
 */
public class LeftPanelSuite {

}