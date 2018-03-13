package com.frontendart.categories;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.frontendart.testsuites.main.rightpanel.view.ChangeViewTest;
import com.frontendart.testsuites.main.rightpanel.view.DuplicateRecordTest;
import com.frontendart.testsuites.main.rightpanel.view.FilterTest;
import com.frontendart.testsuites.main.rightpanel.view.LockRecordTest;
import com.frontendart.testsuites.main.rightpanel.view.SortTest;
import com.frontendart.testsuites.main.rightpanel.view.ViewRecordTest;

@RunWith(Categories.class)
@SuiteClasses({
		ChangeViewTest.class,
		DuplicateRecordTest.class,
		FilterTest.class,
		LockRecordTest.class,
		SortTest.class,
		ViewRecordTest.class,

})
@IncludeCategory(RightPanelViewSuite.class)
/**
 * Test suite for record view tests
 * 
 * @author Zoli
 *
 */
public class RightPanelViewSuite {

}