package com.frontendart.testsuites.workflow;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.WorkflowSuite;
import com.frontendart.common.Constants;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.JunitTestClass;
import com.frontendart.locators.general.GeneralPageLocators;
import com.frontendart.locators.general.GeneralSelectorBoxLocators;
import com.frontendart.locators.main.leftpanel.TodosLocators;
import com.frontendart.locators.main.rightpanel.crud.RecordEditorLocators;
import com.frontendart.locators.records.attributes.general.AchievementPropertyRecordAttributes;
import com.frontendart.locators.records.attributes.general.AuthorRecordAttributes;
import com.frontendart.locators.records.attributes.general.CitationRecordAttributes;
import com.frontendart.locators.records.attributes.general.GeneralRecordAttributes;
import com.frontendart.locators.records.attributes.general.GeneralRecordTypes;
import com.frontendart.locators.records.attributes.general.GeneralTableAttributes;
import com.frontendart.locators.records.attributes.general.PublicationRecordAttributes;
import com.frontendart.locators.records.attributes.hidden.AffiliationRecordAttributes;
import com.frontendart.managers.general.GeneralDataProvider;
import com.frontendart.managers.main.leftpanel.RecordSelectionManager;
import com.frontendart.managers.main.leftpanel.SearchManager;
import com.frontendart.managers.main.rightpanel.crud.CreateOthersManager;
import com.frontendart.managers.main.rightpanel.crud.CreatePublicationManager;
import com.frontendart.managers.main.rightpanel.crud.CreateRecordManager;
import com.frontendart.managers.main.rightpanel.view.FilterManager;

@Category({ WorkflowSuite.class, CENTRAL_ADMIN_Suite.class })
public class PublicationTest extends JunitTestClass {
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
	public final void testCreatePublicationWithDOI() {

		SearchManager.createAndRunEmptyQuery();
		CreateRecordManager.createPublicationWithDOI();

		// get mtmt id
		CreateRecordManager.clickOnNewButton();
		Utils.writeTextToThisField(Constants.MAGIC_DOI.toString(), RecordEditorLocators.DOI_FIELD);
		Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.NEXT_BUTTON);

		Utils.waitForMessageBoxPresentAndAccept();
		String mtmtId = Utils.createGeneralWebElementsFromEnum(RecordEditorLocators.VALUES_LIST_PUBLICATON).get(0)
				.getText();
		CreateRecordManager.closeEditorWindow();

		String queryName = SearchManager.createAndRunOwnQueryWithTheseAttributes(GeneralRecordTypes.PUBLICATION,
				GeneralRecordAttributes.MTMT_ID, mtmtId);
		RecordSelectionManager.selectRandomRenderedRowsFromGridPanel(1);

		// Check status
		CreateRecordManager.checkActualStatusIsCorrect("Nem jóváhagyott");

		// change status
		Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.CHANGE_STATE_COMBOBOX_PICKER);
		Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.CHANGE_STATE_APPROVED);
		Utils.defaultWait();

		// Check status
		CreateRecordManager.checkActualStatusIsCorrect("Jóváhagyott");

		// change status
		Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.CHANGE_STATE_COMBOBOX_PICKER);
		Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.CHANGE_STATE_ADMIN_APPROVED);
		Utils.defaultWait();

		// Check status
		CreateRecordManager.checkActualStatusIsCorrect("Admin láttamozott");

		// change status
		Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.CHANGE_STATE_COMBOBOX_PICKER);
		Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.CHANGE_STATE_VALIDATED);
		Utils.defaultWait();

		// Check status
		CreateRecordManager.checkActualStatusIsCorrect("Érvényesített");

		// change status
		Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.CHANGE_STATE_COMBOBOX_PICKER);
		Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.CHANGE_STATE_CHECKED);
		Utils.defaultWait();

		// Check status
		CreateRecordManager.checkActualStatusIsCorrect("Hitelesített");

		// change status
		Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.CHANGE_STATE_COMBOBOX_PICKER);
		Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.CHANGE_STATE_UNAPPROVED);
		Utils.defaultWait();

		// Check status
		CreateRecordManager.checkActualStatusIsCorrect("Nem jóváhagyott");

		// delete publication
		Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.REMOVE_BUTTON);
		Utils.waitForMessageBoxPresentAndAccept();
		Utils.waitForAndClickOnGeneralWebElement(GeneralPageLocators.MESSAGE_WINDOW_CLOSE_BUTTON);

		// delete query
		SearchManager.deleteThisQuery(queryName);
	}

	@Test
	public final void testCreateDeficientPublication() {

		SearchManager.createAndRunEmptyQuery();
		CreateRecordManager.createPublicationWithDOIDefiecient();

		// get mtmt id
		CreateRecordManager.clickOnNewButton();
		Utils.writeTextToThisField(Constants.MAGIC_DOI.toString(), RecordEditorLocators.DOI_FIELD);
		Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.NEXT_BUTTON);

		Utils.waitForMessageBoxPresentAndAccept();
		String mtmtId = Utils.createGeneralWebElementsFromEnum(RecordEditorLocators.VALUES_LIST_PUBLICATON).get(0)
				.getText();
		CreateRecordManager.closeEditorWindow();

		// open todos
		Utils.waitForAndClickOnGeneralWebElement(TodosLocators.EXPAND_BUTTON);
		Utils.waitForAndClickOnGeneralWebElement(TodosLocators.MANAGING_PROBLEMS_WITH_PUBLICATIONS_EXPAND);
		Utils.waitForAndClickOnGeneralWebElement(TodosLocators.IMCOMPLETE_CORE_PUBLICATION_ENTRY);
		RecordSelectionManager.waitUntilTableIsReady();
		Utils.defaultWait();

		// search for mtmtid
		FilterManager.performFilterWithText(mtmtId);
		RecordSelectionManager.waitUntilTableIsReady();
		RecordSelectionManager.selectRandomRenderedRowsFromGridPanel(1);

		// fill subtype and category
		Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.EDIT_BUTTON);

		
		// Set affiliation (TODO: refact)
				final WebElement field = CreateRecordManager.findFieldOfThisAttribute(PublicationRecordAttributes.SUBTYPE);		
				Utils.scrollIntoView(field.findElement(By.xpath(".//div[contains(@class, 'x-form-search-trigger')]")));				
				field.findElement(By.xpath(".//div[contains(@class, 'x-form-search-trigger')]")).click();
				Utils.defaultWait();
				final List<WebElement> items = Utils.createGeneralWebElementsFromEnum(GeneralSelectorBoxLocators.FIND_SUBTYPE_ITEMS);
				Utils.clickOnThisRow(items.get(Utils.randInt(1, items.size()) - 1));
				
				Utils.waitForAndClickOnGeneralWebElement(GeneralSelectorBoxLocators.FIND_SUBTYPE_SAVE_AND_CLOSE_BUTTON);
				Utils.defaultWait();
				
				Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.SAVE_AND_CLOSE);
		
		Utils.defaultWait();
		
		// search for mtmtid
		FilterManager.performFilterWithText(mtmtId);
		RecordSelectionManager.waitUntilTableIsReady();
		assertTrue("Nem szabad találatnak megjelennie", 0 == RecordSelectionManager.gridTableFullSize());

		Utils.waitForAndClickOnGeneralWebElement(TodosLocators.UNAPPRVOED_CORE_PUBLICATION);
		RecordSelectionManager.waitUntilTableIsReady();
		Utils.defaultWait();
		
		// search for mtmtid
		FilterManager.performFilterWithText(mtmtId);
		RecordSelectionManager.waitUntilTableIsReady();
		RecordSelectionManager.selectRandomRenderedRowsFromGridPanel(1);

		// change status
		Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.CHANGE_STATE_COMBOBOX_PICKER);
		Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.CHANGE_STATE_APPROVED);
		Utils.defaultWait();

		CreatePublicationManager.checkForSwordWindow();
		Utils.defaultWait();

		// search for mtmtid
		FilterManager.performFilterWithText(mtmtId);
		RecordSelectionManager.waitUntilTableIsReady();
		assertTrue("Nem szabad találatnak megjelennie", 0 == RecordSelectionManager.gridTableFullSize());

		
		String queryName = SearchManager.createAndRunOwnQueryWithTheseAttributes(GeneralRecordTypes.PUBLICATION,
				GeneralRecordAttributes.MTMT_ID, mtmtId);
		RecordSelectionManager.selectRandomRenderedRowsFromGridPanel(1);
		
		// delete publication
		Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.REMOVE_BUTTON);
		Utils.waitForMessageBoxPresentAndAccept();
		Utils.waitForAndClickOnGeneralWebElement(GeneralPageLocators.MESSAGE_WINDOW_CLOSE_BUTTON);

		// delete query
		SearchManager.deleteThisQuery(queryName);

	}
	
	@Test
	public final void testCreateDeficientCitation() {

		final GeneralRecordTypes recordType = GeneralRecordTypes.CITATION;
		RecordSelectionManager.selectThisRecordTypeFromSelectorWithoutRunEmptyQuery(recordType);
		
		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(CitationRecordAttributes.PUBLICATION, CitationRecordAttributes.RELATED,
				CitationRecordAttributes.COMMENT));
		
		String searchValue = CreateOthersManager.createRecord(recordType, attributes, CitationRecordAttributes.COMMENT);
		String queryName = SearchManager.createAndRunOwnQueryWithTheseAttributes(GeneralRecordTypes.CITATION,
				CitationRecordAttributes.COMMENT, searchValue);
		RecordSelectionManager.selectRandomRenderedRowsFromGridPanel(1);
		
		// get mtmt id
		CreateRecordManager.clickOnEditButton();
		Utils.defaultWait();
		System.out.println("Size: "+Utils.createGeneralWebElementsFromEnum(RecordEditorLocators.VALUES_LIST_CITATION).size());
		
		String mtmtId = Utils.createGeneralWebElementsFromEnum(RecordEditorLocators.VALUES_LIST_CITATION).get(0)
				.getText();
		CreateRecordManager.closeEditorWindow();

		// open todos
		Utils.waitForAndClickOnGeneralWebElement(TodosLocators.EXPAND_BUTTON);
		Utils.waitForAndClickOnGeneralWebElement(TodosLocators.MANAGING_PROBLEMS_WITH_CITATIONS_EXPAND);
		Utils.waitForAndClickOnGeneralWebElement(TodosLocators.EXTERNAL_CITATION_NOT_SET);
		RecordSelectionManager.waitUntilTableIsReady();
		Utils.defaultWait();

		// search for mtmtid
		FilterManager.performFilterWithText(mtmtId);
		RecordSelectionManager.waitUntilTableIsReady();
		RecordSelectionManager.selectRandomRenderedRowsFromGridPanel(1);

		// fill subtype and category
		Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.EDIT_BUTTON);

		
		// Set affiliation (TODO: refact)
		List<WebElement> radioButtons = driver.findElements(By.xpath("//input[@name='externalCitation-rb']"));
		System.out.println("Radiobuttons: "+radioButtons.size());
		
		final WebElement radioButtonYes = radioButtons.get(1);
		radioButtonYes.click();
		
		Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.SAVE_AND_CLOSE);
		CreatePublicationManager.checkForMessageBoxes();
		Utils.defaultWait();
		
		// search for mtmtid
		FilterManager.performFilterWithText(mtmtId);
		RecordSelectionManager.waitUntilTableIsReady();
		assertTrue("Nem szabad találatnak megjelennie", 0 == RecordSelectionManager.gridTableFullSize());

		Utils.waitForAndClickOnGeneralWebElement(TodosLocators.FOREIGN_EDITION_FIELD_NOT_SET);
		RecordSelectionManager.waitUntilTableIsReady();
		Utils.defaultWait();
		
		// search for mtmtid
		FilterManager.performFilterWithText(mtmtId);
		RecordSelectionManager.waitUntilTableIsReady();
		
		if(RecordSelectionManager.gridTableFullSize()>0){
			RecordSelectionManager.selectRandomRenderedRowsFromGridPanel(1);
			
			// fill subtype and category
			Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.EDIT_BUTTON);

			// Set affiliation (TODO: refact)
			final WebElement field = CreateRecordManager.findFieldOfThisAttribute(CitationRecordAttributes.RELATED);
			Utils.scrollIntoView(field.findElement(By.xpath(".//div[contains(@class, 'open-trigger-default')]")));				
			field.findElement(By.xpath(".//div[contains(@class, 'open-trigger-default')]")).click();
			
			
			List<WebElement> radioButtons2 = driver.findElements(By.xpath("//input[@name='foreignEdition-rb']"));
			System.out.println("Radiobuttons: "+radioButtons2.size());
			
			final WebElement radioButtonForeignEditionYes = radioButtons2.get(1);
			radioButtonForeignEditionYes.click();

			Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.PUBLICATION_SAVE_AND_CLOSE_BUTTON);
			CreatePublicationManager.checkForMessageBoxes();
			
			Utils.defaultWait();
			
			Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.SAVE_AND_CLOSE);
			CreatePublicationManager.checkForMessageBoxes();

			Utils.defaultWait();

			// search for mtmtid
			FilterManager.performFilterWithText(mtmtId);
			RecordSelectionManager.waitUntilTableIsReady();
			assertTrue("Nem szabad találatnak megjelennie", 0 == RecordSelectionManager.gridTableFullSize());
		}
		

		SearchManager.runThisQuery(queryName);
		RecordSelectionManager.selectRandomRenderedRowsFromGridPanel(1);
		
		// delete publication
		Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.REMOVE_BUTTON);
		Utils.waitForMessageBoxPresentAndAccept();
		Utils.waitForAndClickOnGeneralWebElement(GeneralPageLocators.MESSAGE_WINDOW_CLOSE_BUTTON);

		// delete query
		SearchManager.deleteThisQuery(queryName);

	}

}
