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
import com.frontendart.testsuites.workflow.AuthorTest;
import com.frontendart.testsuites.workflow.BookSeriesTest;

//java -jar "selenium-server-standalone-2.53.0.jar" -Dwebdriver.firefox.bin="/home/zalan/ff46/firefox/firefox"
@RunWith(Categories.class)
@SuiteClasses({ Gui2BasicTest.class,
        Gui2LeftPanelLabelTest.class,
        Gui2PublicationTest.class,
        Gui2SearchTest.class,
        Gui2Test.class,
        LoginTest.class,
        ForgotPasswordTest.class,
        LogoutTest.class,
        GenerateReportTest.class,
        RecordSelectionTest.class,
        SearchTest.class,
        AboutTest.class,
        MessagesTest.class,
        UserProfileTest.class,
        ChangeUserTest.class,
        ChangeViewTest.class,
        FilterTest.class,
        LockRecordTest.class,
        SortTest.class, // TODO TUDOMÁNYOS FOKOZAT RENDEZÉS BUG, testSortBySelectingHeader bugot talál sztem
        AuthorTest.class,
        ToolbarTest.class, // FF, CHR ALL ROLES OK EXCEPT THESE ->|  testToolbarCitationsButton pl Forum rekordtípusnál bugot talál sztem (már nem..)
        ViewVersionsTest.class, // Ok, viszont failel, elvileg verziók gomb mindenhol kéne legyen..
        HelpTest.class,
        LanguageTest.class,
        RedmineIssuesTest.class, // Ticket teszt offolva (db írás miatt)
        RegistrationTest.class,
        BookSeriesTest.class })
@IncludeCategory(CoreSuite.class)
/**
 * Test suite for core tests
 *
 * @author Zoli
 *
 */
public class CoreSuite {

}
