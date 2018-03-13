package com.frontendart.testsuites.workflow;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;

import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.WorkflowSuite;
import com.frontendart.common.Constants;
import com.frontendart.common.Roles;
import com.frontendart.common.TestConfiguration;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.JunitTestClass;
import com.frontendart.locators.general.GeneralPageLocators;
import com.frontendart.locators.main.rightpanel.crud.RecordEditorLocators;
import com.frontendart.locators.records.attributes.general.AuthorRecordAttributes;
import com.frontendart.locators.records.attributes.general.GeneralRecordAttributes;
import com.frontendart.locators.records.attributes.general.GeneralRecordTypes;
import com.frontendart.locators.records.attributes.general.KeywordRecordAttributes;
import com.frontendart.managers.login.LoginManager;
import com.frontendart.managers.login.LogoutManager;
import com.frontendart.managers.login.ResetPasswordManager;
import com.frontendart.managers.main.leftpanel.RecordSelectionManager;
import com.frontendart.managers.main.leftpanel.SearchManager;
import com.frontendart.managers.main.rightpanel.crud.CreateOthersManager;
import com.frontendart.managers.main.rightpanel.crud.CreateRecordManager;

@Category({ WorkflowSuite.class, CENTRAL_ADMIN_Suite.class })
public class AuthorTest extends JunitTestClass {
	/**
	 * Logger
	 */
	private static final Logger LOGGER = LogManager.getLogger("MainSearchTest");

	// This test class should only run with central admin - meaning that it will
	// only run once
	@BeforeClass
	public static void beforeMethod() {

		org.junit.Assume.assumeTrue(Utils.actualRoleIsCentralAdmin());

	}

	/**
	 * Creates own citations
	 */
	@Test
	public final void testCreateNewAuthor() {

		final GeneralRecordTypes recordType = GeneralRecordTypes.AUTHOR;
		RecordSelectionManager.selectThisRecordTypeFromSelectorWithoutRunEmptyQuery(recordType);

		CreateRecordManager.clickOnNewButton();

		String username = CreateOthersManager.fillAuthorFieldsAndSave();

		LogoutManager.logout();

		ResetPasswordManager.performResetPasswordProcess(username, Constants.FORGOT_PASSWORD_PASSWORD);

		driver.get(TestConfiguration.myCiteServerUrl);

		List<String> usernameAndPassword = new ArrayList<String>();
		usernameAndPassword.add(username);
		usernameAndPassword.add(Constants.FORGOT_PASSWORD_PASSWORD);

		LoginManager.loginWithThisUsernameAndPassword(usernameAndPassword);
		LoginManager.checkPageLoadWithUsername(username);

		final GeneralRecordTypes recordTypeKeyword = GeneralRecordTypes.KEYWORD;
		RecordSelectionManager.selectThisRecordTypeFromSelectorWithoutRunEmptyQuery(recordTypeKeyword);

		CreateRecordManager.clickOnNewButton();

		CreateOthersManager.fillKeywordFieldsAndSave();
		Utils.waitForMessageBoxPresentAndAccept();

		SearchManager.createAndRunOwnQuery(GeneralRecordTypes.KEYWORD);
		RecordSelectionManager.selectRandomRenderedRowsFromGridPanel(1);

		Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.EDIT_BUTTON);
		Utils.waitForElementPresent(RecordEditorLocators.MODEL_EDITOR_WINDOW);
		Utils.defaultWait();
		String id = Utils.createGeneralWebElementsFromEnum(RecordEditorLocators.VALUES_LIST_KEYWORD).get(0).getText();
		CreateRecordManager.closeEditorWindow();

		System.out.println(id);

		Utils.defaultWait();
		LogoutManager.logout();

		LoginManager.doSuccessfulLoginWithRole(Roles.CENTRAL_ADMIN);

		RecordSelectionManager.selectThisRecordTypeFromSelectorWithoutRunEmptyQuery(recordTypeKeyword);

		// String keywordQueryName =
		// SearchManager.createAndRunOwnQueryWithTheseAttributes(GeneralRecordTypes.KEYWORD,
		// KeywordRecordAttributes.KEYWORD, keyword);
		String keywordQueryName = SearchManager.createAndRunOwnQueryWithTheseAttributes(GeneralRecordTypes.KEYWORD,
				KeywordRecordAttributes.ID, id);
		RecordSelectionManager.selectRandomRenderedRowsFromGridPanel(1);

		Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.REMOVE_BUTTON);
		Utils.waitForMessageBoxPresentAndAccept();
		Utils.waitForAndClickOnGeneralWebElement(GeneralPageLocators.MESSAGE_WINDOW_CLOSE_BUTTON);

		SearchManager.deleteThisQuery(keywordQueryName);

		final GeneralRecordTypes recordTypeAuthor = GeneralRecordTypes.AUTHOR;
		RecordSelectionManager.selectThisRecordTypeFromSelectorWithoutRunEmptyQuery(recordTypeAuthor);

		String authorQueryName = SearchManager.createAndRunOwnQueryWithTheseAttributes(GeneralRecordTypes.AUTHOR,
				AuthorRecordAttributes.USERNAME, username);
		RecordSelectionManager.selectRandomRenderedRowsFromGridPanel(1);

		Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.REMOVE_BUTTON);
		Utils.waitForMessageBoxPresentAndAccept();
		Utils.waitForAndClickOnGeneralWebElement(GeneralPageLocators.MESSAGE_WINDOW_CLOSE_BUTTON);

		SearchManager.deleteThisQuery(authorQueryName);
	}
	
}
