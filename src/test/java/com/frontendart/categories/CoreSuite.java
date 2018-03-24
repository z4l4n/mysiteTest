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
import com.frontendart.testsuites.main.leftpanel.SearchTest;
import com.frontendart.testsuites.main.rightpanel.top.AboutTest;
import com.frontendart.testsuites.main.rightpanel.top.ChangeUserTest;
import com.frontendart.testsuites.main.rightpanel.top.MessagesTest;
import com.frontendart.testsuites.main.rightpanel.top.UserProfileTest;
import com.frontendart.testsuites.main.rightpanel.view.ChangeViewTest;
import com.frontendart.testsuites.main.rightpanel.view.FilterTest;
import com.frontendart.testsuites.main.rightpanel.view.LockRecordTest;
import com.frontendart.testsuites.main.rightpanel.view.SortTest;
import com.frontendart.testsuites.main.rightpanel.view.ToolbarTest;
import com.frontendart.testsuites.main.rightpanel.view.ViewVersionsTest;
import com.frontendart.testsuites.misc.HelpTest;
import com.frontendart.testsuites.misc.LanguageTest;
import com.frontendart.testsuites.misc.RedmineIssuesTest;
import com.frontendart.testsuites.registration.RegistrationTest;

//java -jar "selenium-server-standalone-2.53.0.jar" -Dwebdriver.firefox.bin="/home/zalan/ff46/firefox/firefox"
@RunWith(Categories.class)
@SuiteClasses({ Gui2BasicTest.class, // ASSUME_ROLE: CENTRAL ADMIN | CHROME, FF OK
	Gui2LeftPanelLabelTest.class, // ASSUME_ROLE: CENTRAL ADMIN | FF, CHROME OK
	Gui2PublicationTest.class, // ASSUME_ROLE: CENTRAL ADMIN
	Gui2SearchTest.class, // ASSUME_ROLE: CENTRAL ADMIN | FF, CHROME OK
	Gui2Test.class, // ASSUME_ROLE: CENTRAL ADMIN | FF, CHROME OK
	LoginTest.class, // FIREFOX OK: CENTRAL ADMIN, többi nem kell elvileg (?) | CHROME OK: CENTRAL
			 // ADMIN
	ForgotPasswordTest.class, // FIREFOX OK: CENTRAL ADMIN, AUTHOR, INSTITUTIONAL ADMIN | CHROME OK: CENTRAL
				  // ADMIN, AUTHOR, INSTITUTIONAL ADMIN
	LogoutTest.class, // FIREFOX OK: CENTRAL ADMIN, INSTITUTIONAL ADMIN, AUTHOR | CHROME OK: CENTRAL
			  // ADMIN, AUTHOR, INSTITUTIONAL ADMIN
	GenerateReportTest.class, // FIREFOX OK: CENTRAL ADMIN, AUTHOR, INSTITUTIONAL ADMIN | CHROME OK: CENTRAL
				  // ADMIN, AUTHOR, INSTITUTIONAL ADMIN
	RecordSelectionTest.class, // FIREFOX, CHROME OK ALL_ROLES
	SearchTest.class, // CHROME OK: INSTITUTIONAL ADMIN, CENTRAL ADMIN, AUTHOR | FIREFOX OK: AUTHOR,
			  // CENTRAL ADMIN, INSTITUTIONAL ADMIN
	AboutTest.class, // FIREFOX, CHROME OK ALL_ROLES
	MessagesTest.class, // FIREFOX, CHROME OK ALL_ROLES
	UserProfileTest.class, // FF, CHR OK ALL_ROLES
	ChangeUserTest.class, // FF, CHR OK ALL_ROLES
	ChangeViewTest.class, // FF, CHR OK ALL_ROLES, contains DB writing
	FilterTest.class, // FF, CHR OK ALL_ROLES
	LockRecordTest.class, SortTest.class, // TODO TUDOMÁNYOS FOKOZAT RENDEZÉS BUG, testSortBySelectingHeader bugot
					      // talál sztem..
	ToolbarTest.class, ViewVersionsTest.class, HelpTest.class, LanguageTest.class, RedmineIssuesTest.class,
	RegistrationTest.class, })
@IncludeCategory(CoreSuite.class)
/**
 * Test suite for core tests
 *
 * @author Zoli
 *
 */
public class CoreSuite {

}
