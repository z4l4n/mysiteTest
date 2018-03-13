package com.frontendart.testsuites.main.rightpanel.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.frontendart.categories.AUTHOR_Suite;
import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.INSTITUTIONAL_ADMIN_Suite;
import com.frontendart.categories.RightPanelViewSuite;
import com.frontendart.common.Roles;
import com.frontendart.common.TestConfiguration;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.JunitTestClass;
import com.frontendart.locators.main.rightpanel.view.DuplicateRecordLocators;
import com.frontendart.locators.main.rightpanel.view.DuplicateRecordMessageTypes;
import com.frontendart.locators.main.rightpanel.view.ToolbarLocators;
import com.frontendart.locators.records.attributes.general.CategoryRecordAttributes;
import com.frontendart.locators.records.attributes.general.GeneralRecordTypes;
import com.frontendart.locators.records.attributes.general.PublicationRecordAttributes;
import com.frontendart.locators.records.attributes.general.ReportTemplateRecordAttributes;
import com.frontendart.managers.general.GeneralTableManager;
import com.frontendart.managers.main.leftpanel.RecordSelectionManager;
import com.frontendart.managers.main.leftpanel.SearchManager;
import com.frontendart.managers.main.rightpanel.crud.DeleteRecordManager;
import com.frontendart.managers.main.rightpanel.view.ChangeViewManager;
import com.frontendart.managers.main.rightpanel.view.DuplicateRecordManager;
import com.frontendart.managers.main.rightpanel.view.SortManager;

/**
 * This class is responsible for Duplicate record test.
 * @author Zoli
 *
 */
@Category({ RightPanelViewSuite.class, CENTRAL_ADMIN_Suite.class, INSTITUTIONAL_ADMIN_Suite.class, AUTHOR_Suite.class })
@SuppressWarnings({ "PMD.JUnitTestsShouldIncludeAssert", "PMD.JUnitTestContainsTooManyAsserts" })
public class DuplicateRecordTest extends JunitTestClass {

	// This test class should only run with central admin and author
	@BeforeClass
	public static void beforeMethod() {
		org.junit.Assume.assumeTrue(!Roles.AUTHOR.equals(Utils.getActualRole()));
	}

	/**
	 * Simple copy record (currently works for REPORT_TEMPLATE record type only)
	 * 
	 * TODO: run query, make it language independent
	 */
	@Test
	public final void testCopyRecord() {
//		SearchManager.createAndRunSubsetQuery(GeneralRecordTypes.PUBLICATION);
//			
//		ChangeViewManager.switchToGridView();	
//		driver.findElement(By.xpath(ToolbarLocators.CUSTOM_VIEW_MENU_ALL_COLUMNS.toString())).click();
		
		RecordSelectionManager.selectThisRecordTypeFromSelectorWithoutRunEmptyQuery(GeneralRecordTypes.REPORT_TEMPLATE);
		SearchManager.createAndRunSubsetQuery(GeneralRecordTypes.REPORT_TEMPLATE);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Get old number of table
		final int oldNumberOfTable = RecordSelectionManager.gridTableFullSize();

		// Select random record type, and select random record
		final WebElement myRecordRow = RecordSelectionManager.selectRandomRenderedRowsFromGridPanel(1).get(0);
		//final int titleColPos = GeneralTableManager.getColPos(PublicationRecordAttributes.TITLE);
		int titleColPos = GeneralTableManager.getColPos(ReportTemplateRecordAttributes.COMMENT);
		
		Utils.scrollIntoView(myRecordRow.findElement(By.xpath("./tbody/tr/td[" + titleColPos + "]/div")));
		final String myRecordComment = myRecordRow.findElement(By.xpath("./tbody/tr/td[" + titleColPos + "]/div")).getText();

		// Click on Duplicate button
		DuplicateRecordManager.clickOnCopyButton();
		Utils.defaultWait();
		assertTrue("There should be a message with the successful duplication.",
				Utils.isMessageBoxPresentWithText(DuplicateRecordMessageTypes.ONE_ITEM_SUCCESS));
		Utils.acceptMessageBoxIfVisible();

		// TODO: More validation? (Row number, check record, etc...)
		Utils.defaultWait();
		
		
		//SearchManager.createAndRunSubsetQuery(GeneralRecordTypes.PUBLICATION);
		SearchManager.createAndRunSubsetQuery(GeneralRecordTypes.REPORT_TEMPLATE);
		
		ChangeViewManager.switchToGridView();
		final int newNumberOfTable = RecordSelectionManager.gridTableFullSize();
		assertEquals("The number of rows in the table is not correct!", oldNumberOfTable + 1, newNumberOfTable);
		
		driver.findElement(By.xpath(ToolbarLocators.CUSTOM_VIEW_MENU_ALL_COLUMNS.toString())).click();
		
		//SortManager.sortTableByIdDesc(); The copy of the record should be the first record in the table

		int newRowNumber = newNumberOfTable -1;
		
		//System.out.println("GeneralTableManager.getAllRowsOfMainGrid().size(): "+GeneralTableManager.getAllRowsOfMainGrid().size());
		
		final WebElement firstRow = GeneralTableManager.getAllRowsOfMainGrid().get(newRowNumber);
		Utils.scrollIntoView(firstRow.findElement(By.xpath("./tbody/tr/td[" + titleColPos + "]/div")));
		final String newRecordComment = firstRow.findElement(By.xpath("./tbody/tr/td[" + titleColPos + "]/div")).getText();
		
		assertTrue("The new record does not contain the title of the old record!", newRecordComment.contains(myRecordComment));
		//assertTrue("The new record does not contain the word <<MÁSOLAT>> in its title!", newRecordTitle.contains("MÁSOLAT"));
		assertTrue("The new record does not contain the word <<MÁSOLAT>> in its title!", newRecordComment.contains("másolata"));

		SearchManager.createAndRunSubsetQuery(GeneralRecordTypes.REPORT_TEMPLATE);
		// Cleanup, remove duplicated record
		//SearchManager.cleanup();
		
		SortManager.sortTableByIDDesc();
		//ChangeViewManager.switchToGridView();
		DeleteRecordManager.removeFirstRecord();
	}

	/**
	 * Simple mark records as duplum (currently works for PUBLICATION record type only)
	 */
	@Test
	public final void testMarkAsDuplum() {
		SearchManager.createAndRunSubsetQuery(GeneralRecordTypes.PUBLICATION);
//		if (SearchManager.getMyQueriesNameAsString().contains("Közleményeim")) {
//			SearchManager.runThisQuery("Közleményeim");
//		} else {
//			SearchManager.createAndRunEmptyQuery();
//		}
		ChangeViewManager.switchToGridView();

		// Check if there are enough records present
		if (RecordSelectionManager.gridTableFullSize() < 2) {
			SearchManager.createAndRunEmptyQuery();
			ChangeViewManager.switchToGridView();
			assertTrue("There are not enough records to perform this test case! At least 2 records should be present!",
					RecordSelectionManager.gridTableFullSize() > 1);
		}

		// Select random record type, and select two random records
		final List<String> oldVisibleHeaders = GeneralTableManager.getAllVisibleHeadersText();
		if (!oldVisibleHeaders.contains("MTMT azonosító")) {
			GeneralTableManager.changeTheVisibilityOfThisHeader("MTMT azonosító");
			Utils.defaultWait();
		}
		final List<WebElement> selectedRecords = RecordSelectionManager.selectRandomRenderedRowsFromGridPanel(2);
		final WebElement firstRecord = selectedRecords.get(0);
		final int firstRecordId = GeneralTableManager.getIDOfThisRow(firstRecord);
		final WebElement secondRecord = selectedRecords.get(1);
		final int secondRecordId = GeneralTableManager.getIDOfThisRow(secondRecord);

		// Get types
		final int typeColPos = GeneralTableManager.getColPos(PublicationRecordAttributes.TYPE);
		final String firstRecordType = firstRecord.findElement(By.xpath("./tbody/tr/td[" + typeColPos + "]/div")).getText();
		final String secondRecordType = secondRecord.findElement(By.xpath("./tbody/tr/td[" + typeColPos + "]/div")).getText();

		// Click on Duplicate button
		DuplicateRecordManager.clickOnMarkAsDuplumButton();

		// Check duplum message
		assertTrue("There should be an alert message which asks if we would like to continue.",
				Utils.isMessageBoxPresentWithText(DuplicateRecordMessageTypes.MARK_AS_DUPLUM_CONTINUE));
		Utils.defaultWait();

		// Validate - if type is the same
		if (firstRecordType.equals(secondRecordType)) {
			assertTrue("There should be a message with the success of the procedure.",
					Utils.isMessageBoxPresentWithText(DuplicateRecordMessageTypes.MARK_AS_DUPLUM_SUCCESS));

			// Find row - Click on search duplicates button, and validate
			final WebElement firstRecordAgain = GeneralTableManager.findThisRecordByID(firstRecordId);
			Utils.clickOnThisRow(firstRecordAgain);
			DuplicateRecordManager.clickOnSearchDuplicatesButton();
			
			
			
			Utils.defaultWait();
			Utils.acceptMessageBoxIfVisible();

			// Validate: Check duplum window is present
			assertTrue("In the duplum handling window, we should see the first record!",
					GeneralTableManager.getAllRecordIdsFromGrid().contains(firstRecordId));
			assertTrue("In the duplum handling window, we should see the second record!",
					GeneralTableManager.getAllRecordIdsFromGrid().contains(secondRecordId));
			//	assertTrue("The duplum handler window should be present",
//					DuplicateRecordManager.isDuplumWindowPresentAndClose();

		} else { // Validate - if type is not the same
			Utils.waitForMessageBoxToBePresent();
			final String messageBoxText = Utils.getMessageBoxText();

			assertTrue("There should be a message with the different type",
					messageBoxText.contains("Nem sikerült megjelölni a kiválasztott elemeket duplumként"));
			Utils.acceptMessageBoxIfVisible();

		}
		
		Utils.waitForAndClickOnGeneralWebElement(DuplicateRecordLocators.DUPLUM_RESOLVER_WINDOW_CLOSE_BUTTON);
	}

}
