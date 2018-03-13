package com.frontendart.categories;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.frontendart.testsuites.login.ForgotPasswordTest;
import com.frontendart.testsuites.login.LoginTest;
import com.frontendart.testsuites.login.LogoutTest;
import com.frontendart.testsuites.login.ResetPasswordTest;
import com.frontendart.testsuites.main.leftpanel.GenerateReportTest;
import com.frontendart.testsuites.main.leftpanel.RecordSelectionTest;
import com.frontendart.testsuites.main.leftpanel.SavedListsTest;
import com.frontendart.testsuites.main.leftpanel.SearchTest;
import com.frontendart.testsuites.main.rightpanel.crud.CreateRecordTest;
import com.frontendart.testsuites.main.rightpanel.top.AboutTest;
import com.frontendart.testsuites.main.rightpanel.top.ChangeUserTest;
import com.frontendart.testsuites.main.rightpanel.top.ForumTest;
import com.frontendart.testsuites.main.rightpanel.top.MessagesTest;
import com.frontendart.testsuites.main.rightpanel.top.TicketsTest;
import com.frontendart.testsuites.main.rightpanel.top.UserProfileTest;
import com.frontendart.testsuites.main.rightpanel.view.ChangeViewTest;
import com.frontendart.testsuites.main.rightpanel.view.DuplicateRecordTest;
import com.frontendart.testsuites.main.rightpanel.view.FilterTest;
import com.frontendart.testsuites.main.rightpanel.view.SortTest;
import com.frontendart.testsuites.main.rightpanel.view.ToolbarTest;
import com.frontendart.testsuites.main.rightpanel.view.ViewRecordTest;
import com.frontendart.testsuites.misc.LanguageTest;

@RunWith(Categories.class)
@SuiteClasses({
		ForgotPasswordTest.class,
		LogoutTest.class,
		ResetPasswordTest.class,
		GenerateReportTest.class,
		RecordSelectionTest.class,
		SavedListsTest.class,
		SearchTest.class,
		CreateRecordTest.class,
		AboutTest.class,
		TicketsTest.class,
		ForumTest.class,
		MessagesTest.class,
		UserProfileTest.class,
		ChangeUserTest.class,
		LanguageTest.class,
		ChangeViewTest.class,
		DuplicateRecordTest.class,
		FilterTest.class,
		SortTest.class,
		ToolbarTest.class,
		ViewRecordTest.class,
})
@IncludeCategory(AUTHOR_Suite.class)
/**
 * Test suite for search tests
 * 
 * @author Zoli
 *
 */
public class AUTHOR_Suite {

}