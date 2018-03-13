package com.frontendart.categories;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.frontendart.testsuites.gui2.Gui2BasicTest;
import com.frontendart.testsuites.gui2.Gui2LeftPanelLabelTest;
import com.frontendart.testsuites.gui2.Gui2PublicationTest;
import com.frontendart.testsuites.gui2.Gui2Test;
import com.frontendart.testsuites.gui2.Gui2SearchTest;

@RunWith(Categories.class)
@SuiteClasses({
		Gui2Test.class,
		Gui2PublicationTest.class,
		Gui2LeftPanelLabelTest.class,
		Gui2BasicTest.class,
		Gui2SearchTest.class,
})
@IncludeCategory(Gui2Suite.class)
/**
 * Test suite for gui2 tests
 * 
 * @author Zoli
 *
 */
public class Gui2Suite {

}