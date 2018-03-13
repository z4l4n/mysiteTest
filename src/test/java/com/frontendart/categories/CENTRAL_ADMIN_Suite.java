package com.frontendart.categories;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.frontendart.testsuites.gui2.Gui2BasicTest;
import com.frontendart.testsuites.gui2.Gui2LeftPanelLabelTest;
import com.frontendart.testsuites.gui2.Gui2PublicationTest;
import com.frontendart.testsuites.gui2.Gui2SearchTest;
import com.frontendart.testsuites.gui2.Gui2Test;
import com.frontendart.testsuites.login.ForgotPasswordTest;
import com.frontendart.testsuites.login.LoginTest;
import com.frontendart.testsuites.login.LogoutTest;
import com.frontendart.testsuites.main.leftpanel.GenerateReportTest;
import com.frontendart.testsuites.main.leftpanel.RecordSelectionTest;
import com.frontendart.testsuites.main.leftpanel.SavedListsTest;
import com.frontendart.testsuites.main.leftpanel.SearchTest;
import com.frontendart.testsuites.main.rightpanel.crud.CreateRecordTest;
import com.frontendart.testsuites.main.rightpanel.crud.UpdateRecordTest;
import com.frontendart.testsuites.main.rightpanel.top.AboutTest;
import com.frontendart.testsuites.main.rightpanel.top.ChangeUserTest;
import com.frontendart.testsuites.main.rightpanel.top.ForumTest;
import com.frontendart.testsuites.main.rightpanel.top.MessagesTest;
import com.frontendart.testsuites.main.rightpanel.top.TicketsTest;
import com.frontendart.testsuites.main.rightpanel.top.UserProfileTest;
import com.frontendart.testsuites.main.rightpanel.view.ChangeViewTest;
import com.frontendart.testsuites.main.rightpanel.view.DuplicateRecordTest;
import com.frontendart.testsuites.main.rightpanel.view.FilterTest;
import com.frontendart.testsuites.main.rightpanel.view.LockRecordTest;
import com.frontendart.testsuites.main.rightpanel.view.SortTest;
import com.frontendart.testsuites.main.rightpanel.view.ToolbarTest;
import com.frontendart.testsuites.main.rightpanel.view.ViewRecordTest;
import com.frontendart.testsuites.main.rightpanel.view.ViewVersionsTest;
import com.frontendart.testsuites.misc.HelpTest;
import com.frontendart.testsuites.misc.LanguageTest;
import com.frontendart.testsuites.misc.RedmineIssuesTest;
import com.frontendart.testsuites.registration.RegistrationTest;
import com.frontendart.testsuites.workflow.AuthorTest;
import com.frontendart.testsuites.workflow.PublicationTest;
import com.frontendart.testsuites.records.AddRecordsTest;

@RunWith(Categories.class)
@SuiteClasses({
		Gui2BasicTest.class,
		Gui2LeftPanelLabelTest.class,
		Gui2PublicationTest.class,
		Gui2SearchTest.class,
		Gui2Test.class,
		LoginTest.class,
		ForgotPasswordTest.class,
		LogoutTest.class,
		GenerateReportTest.class,
		RecordSelectionTest.class,
		SavedListsTest.class,
		SearchTest.class,
		CreateRecordTest.class,
		UpdateRecordTest.class,
		AboutTest.class,
		ForumTest.class,
		MessagesTest.class,
		UserProfileTest.class,
		TicketsTest.class,
		ChangeUserTest.class,
		ChangeViewTest.class,
		DuplicateRecordTest.class,
		FilterTest.class,
		LockRecordTest.class,
		SortTest.class,
		ToolbarTest.class,
		ViewRecordTest.class,
		ViewVersionsTest.class,
		HelpTest.class,
		LanguageTest.class,
		RedmineIssuesTest.class,
		RegistrationTest.class,
		PublicationTest.class,
		AuthorTest.class,
		AddRecordsTest.class,
})
@IncludeCategory(CENTRAL_ADMIN_Suite.class)
/**
 * Test suite for search tests
 * 
 * @author Zoli
 *
 */
public class CENTRAL_ADMIN_Suite {

}