package com.frontendart.categories;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.frontendart.testsuites.main.rightpanel.crud.CreateCitationTest;
import com.frontendart.testsuites.main.rightpanel.crud.CreateRecordTest;
import com.frontendart.testsuites.main.rightpanel.crud.UpdateRecordTest;

@RunWith(Categories.class)
@SuiteClasses({
		CreateRecordTest.class,
		UpdateRecordTest.class,
		CreateCitationTest.class,
})
@IncludeCategory(RightPanelCrudSuite.class)
/**
 * Test suite for create, update, delete records tests
 * 
 * @author Zoli
 *
 */
public class RightPanelCrudSuite {

}