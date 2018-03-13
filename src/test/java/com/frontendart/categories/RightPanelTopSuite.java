package com.frontendart.categories;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.frontendart.testsuites.main.rightpanel.top.AboutTest;
import com.frontendart.testsuites.main.rightpanel.top.ChangeUserTest;
import com.frontendart.testsuites.main.rightpanel.top.ForumTest;
import com.frontendart.testsuites.main.rightpanel.top.MessagesTest;
import com.frontendart.testsuites.main.rightpanel.top.TicketsTest;
import com.frontendart.testsuites.main.rightpanel.top.UserProfileTest;

@RunWith(Categories.class)
@SuiteClasses({
		UserProfileTest.class,
		TicketsTest.class,
		ForumTest.class,
		MessagesTest.class,
		AboutTest.class,
		ChangeUserTest.class,
})
@IncludeCategory(RightPanelTopSuite.class)
/**
 * Test suite for right panel top elements
 * 
 * @author Zoli
 *
 */
public class RightPanelTopSuite {

}