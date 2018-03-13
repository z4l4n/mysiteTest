package com.frontendart.categories;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.frontendart.testsuites.records.AddRecordsTest;

@RunWith(Categories.class)
@SuiteClasses({
		AddRecordsTest.class,
})
@IncludeCategory(AddRecordsSuite.class)
/**
 * Test suite for important workflow related tests
 * 
 */
public class AddRecordsSuite {

}