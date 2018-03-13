package com.frontendart.categories;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.frontendart.testsuites.workflow.AuthorTest;
import com.frontendart.testsuites.workflow.CitationTest;
import com.frontendart.testsuites.workflow.PublicationTest;

@RunWith(Categories.class)
@SuiteClasses({
		CitationTest.class,
		AuthorTest.class,
		PublicationTest.class,
})
@IncludeCategory(WorkflowSuite.class)
/**
 * Test suite for important workflow related tests
 * 
 */
public class WorkflowSuite {

}