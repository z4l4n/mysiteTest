package com.frontendart.testsuites.workflow;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.frontendart.categories.AUTHOR_Suite;
import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.INSTITUTIONAL_ADMIN_Suite;
import com.frontendart.common.Roles;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.JunitTestClass;
import com.frontendart.locators.general.GeneralPageLocators;
import com.frontendart.locators.main.rightpanel.view.ToolbarLocators;
import com.frontendart.locators.records.attributes.general.CitationRecordAttributes;
import com.frontendart.locators.records.attributes.general.GeneralRecordTypes;
import com.frontendart.managers.general.GeneralTableManager;
import com.frontendart.managers.main.leftpanel.RecordSelectionManager;
import com.frontendart.managers.main.leftpanel.SearchManager;
import com.frontendart.managers.main.rightpanel.crud.CreateRecordManager;
import com.frontendart.managers.main.rightpanel.view.ChangeViewManager;

@Category({ CitationTest.class, AUTHOR_Suite.class, INSTITUTIONAL_ADMIN_Suite.class, CENTRAL_ADMIN_Suite.class })
public class CitationTest extends JunitTestClass {
	/**
	 * Logger
	 */
	private static final Logger LOGGER = LogManager.getLogger("MainSearchTest");
	private static final int CITATION_NUMBER = 2;
	private static final String CHECKBOX_CHECKED_CLASS = "x-grid-checkcolumn-checked";
	private static final String CHECKBOX_CHECKED_UNSET = "x-grid-checkcolumn-null";

	@Before
	public final void prepareTestCitations() {
		RecordSelectionManager.selectThisRecordTypeFromSelectorWithoutRunEmptyQuery(GeneralRecordTypes.CITATION);
		for (int i = 0; i < CITATION_NUMBER; i++) {
			CreateRecordManager.createRecord(GeneralRecordTypes.CITATION);
			Utils.acceptMessageBoxIfVisible();
			Utils.waitForMessageBoxToBeDismissed();
		}
		
		SearchManager.createAndRunOwnQuery(GeneralRecordTypes.CITATION);
		ChangeViewManager.switchToGridView();
	}
	
	@After
	public final void cleanupCitations() {
		SearchManager.cleanup(CITATION_NUMBER);
	}
	
	/**
	 * Creates record and set as self citation directly from Operations menu
	 */
	@Test
	public final void testSelfCitation() {
		// Skip this test if the actual role is author
		Assume.assumeTrue(Utils.getActualRole() != Roles.AUTHOR);
		
		// select a record from the created ones
		List<WebElement> selectedRows = RecordSelectionManager.selectLastCreatedRecordsOnGridPanelWithShift(1);
		final int attributePosition = GeneralTableManager.getColPos(CitationRecordAttributes.EXTERNAL_CITATION);
		for (WebElement row : selectedRows) {
			String classAttribute = row.findElement(By.xpath("./tbody/tr/td[" + attributePosition + "]//span")).getAttribute("class");
			assertTrue("Előfeltétel hiba: Független oszlop checkbox beállítatlan kellene lennie", classAttribute.contains(CHECKBOX_CHECKED_UNSET));
		}
		
		// trigger a Set self citation command from the Operations menu
		triggerSelfCitation();
		
		// check the External citation value of the record
		selectedRows = RecordSelectionManager.getSelectedItemsInView();
		for (WebElement row : selectedRows) {
			String classAttribute = row.findElement(By.xpath("./tbody/tr/td[" + attributePosition + "]//span")).getAttribute("class");
			assertTrue("Független oszlop checkbox kijelöltnek kellene lennie", classAttribute.contains(CHECKBOX_CHECKED_CLASS));
		}
		LOGGER.info("testSelfCitation has finished");
	}
	
	/**
	 * Creates records and set multiple record as self citation directly from Operations menu
	 */
	@Test
	public final void testMultipleSelfCitation() {
		// Skip this test if the actual role is author
		Assume.assumeTrue(Utils.getActualRole() != Roles.AUTHOR);
		
		// select a record from the created ones
		List<WebElement> selectedRows = RecordSelectionManager.selectLastCreatedRecordsOnGridPanelWithShift(CITATION_NUMBER);
		final int attributePosition = GeneralTableManager.getColPos(CitationRecordAttributes.EXTERNAL_CITATION);
		for (WebElement row : selectedRows) {
			String classAttribute = row.findElement(By.xpath("./tbody/tr/td[" + attributePosition + "]//span")).getAttribute("class");
			assertTrue("Előfeltétel hiba: Független oszlop checkbox beállítatlan kellene lennie", classAttribute.contains(CHECKBOX_CHECKED_UNSET));
		}
		
		// trigger a Set self citation command from the Operations menu
		triggerSelfCitation();
		
		// check the External citation value of the record
		selectedRows = RecordSelectionManager.getSelectedItemsInView();
		for (WebElement row : selectedRows) {
			String classAttribute = row.findElement(By.xpath("./tbody/tr/td[" + attributePosition + "]//span")).getAttribute("class");
			assertTrue("Független oszlop checkbox kijelöltnek kellene lennie", classAttribute.contains(CHECKBOX_CHECKED_CLASS));
		}
		LOGGER.info("testMultipleSelfCitation has finished");
	}

	/**
	 * Creates record and cancel the Check citation operation without change
	 */
	@Test
	public final void testCheckCitationFirstPageCancel() {
		// select a record from the created ones
		RecordSelectionManager.selectLastCreatedRecordsOnGridPanelWithShift(1);
		
		// open Operations > Check citation menu
		openCitationWindow();
		
		// hit the close button
		Utils.waitForAndClickOnGeneralWebElement(GeneralPageLocators.SELF_CITATION_CLOSE);
		
		// check the External citation value of the record didn't changed
		List<WebElement> selectedRows = RecordSelectionManager.selectLastCreatedRecordsOnGridPanelWithShift(1);
		final int attributePosition = GeneralTableManager.getColPos(CitationRecordAttributes.EXTERNAL_CITATION);
		for (WebElement row : selectedRows) {
			String classAttribute = row.findElement(By.xpath("./tbody/tr/td[" + attributePosition + "]//span")).getAttribute("class");
			assertTrue("Független oszlop checkbox beállítatlan kellene lennie", classAttribute.contains(CHECKBOX_CHECKED_UNSET));
		}
		LOGGER.info("testCheckCitationFirstPageCancel has finished");
	}
	
	/**
	 * Creates record and cancel the Check citation operation without change
	 */
	@Test
	public final void testCheckCitationSecondPageCancel() {
		// select a record from the created ones
		RecordSelectionManager.selectLastCreatedRecordsOnGridPanelWithShift(1);
		
		// open Operations > Check citation menu
		openCitationWindow();
		
		// select all
		Utils.clickToWebelementWithAction(Utils.createGeneralWebElementFromEnum(GeneralPageLocators.SELF_CITATION_CHECK_ALL));
		Utils.clickToWebelementWithAction(Utils.createGeneralWebElementFromEnum(GeneralPageLocators.SELF_CITATION_SET_ALL));
		
		// hit Next button
		Utils.waitForAndClickOnGeneralWebElement(GeneralPageLocators.SELF_CITATION_NEXT);
		Utils.defaultWait();
		
		// select all Yes options
		Utils.clickToWebelementWithAction(Utils.createGeneralWebElementFromEnum(GeneralPageLocators.SELF_CITATION_INDEPENDENT_YES));
		Utils.clickToWebelementWithAction(Utils.createGeneralWebElementFromEnum(GeneralPageLocators.SELF_CITATION_FOREIGN_YES));
		Utils.clickToWebelementWithAction(Utils.createGeneralWebElementFromEnum(GeneralPageLocators.SELF_CITATION_NATIONAL_YES));
		
		// hit the close button
		Utils.waitForAndClickOnGeneralWebElement(GeneralPageLocators.SELF_CITATION_CLOSE);
		
		// check the External citation value of the record didn't changed
		List<WebElement> selectedRows = RecordSelectionManager.selectLastCreatedRecordsOnGridPanelWithShift(1);
		final int attributePosition = GeneralTableManager.getColPos(CitationRecordAttributes.EXTERNAL_CITATION);
		for (WebElement row : selectedRows) {
			String classAttribute = row.findElement(By.xpath("./tbody/tr/td[" + attributePosition + "]//span")).getAttribute("class");
			assertTrue("Független oszlop checkbox beállítatlan kellene lennie", classAttribute.contains(CHECKBOX_CHECKED_UNSET));
		}
		LOGGER.info("testCheckCitationSecondPageCancel has finished");
	}
	
	/**
	 * Creates record and cancel the Check citation operation without change
	 */
	@Test
	public final void testCheckCitationSkip() {
		// select a record from the created ones
		RecordSelectionManager.selectLastCreatedRecordsOnGridPanelWithShift(1);
		
		// open Operations > Check citation menu
		openCitationWindow();
		
		// select all
		Utils.clickToWebelementWithAction(Utils.createGeneralWebElementFromEnum(GeneralPageLocators.SELF_CITATION_CHECK_ALL));
		Utils.clickToWebelementWithAction(Utils.createGeneralWebElementFromEnum(GeneralPageLocators.SELF_CITATION_SET_ALL));
		
		// hit Next button
		Utils.waitForAndClickOnGeneralWebElement(GeneralPageLocators.SELF_CITATION_NEXT);
		Utils.defaultWait();
		
		// select all Yes options
		Utils.clickToWebelementWithAction(Utils.createGeneralWebElementFromEnum(GeneralPageLocators.SELF_CITATION_INDEPENDENT_YES));
		Utils.clickToWebelementWithAction(Utils.createGeneralWebElementFromEnum(GeneralPageLocators.SELF_CITATION_FOREIGN_YES));
		Utils.clickToWebelementWithAction(Utils.createGeneralWebElementFromEnum(GeneralPageLocators.SELF_CITATION_NATIONAL_YES));
		
		// hit the skip button
		Utils.waitForAndClickOnGeneralWebElement(GeneralPageLocators.SELF_CITATION_SKIP);
		
		// hit the close button
		Utils.waitForAndClickOnGeneralWebElement(GeneralPageLocators.SELF_CITATION_CLOSE);
		
		// check the External citation value of the record didn't changed
		List<WebElement> selectedRows = RecordSelectionManager.selectLastCreatedRecordsOnGridPanelWithShift(1);
		final int attributePosition = GeneralTableManager.getColPos(CitationRecordAttributes.EXTERNAL_CITATION);
		for (WebElement row : selectedRows) {
			String classAttribute = row.findElement(By.xpath("./tbody/tr/td[" + attributePosition + "]//span")).getAttribute("class");
			assertTrue("Független oszlop checkbox beállítatlan kellene lennie", classAttribute.contains(CHECKBOX_CHECKED_UNSET));
		}
		LOGGER.info("testCheckCitationSkip has finished");
	}
	
	private void performManualCitation(GeneralPageLocators independenceType) {
		// select a record from the created ones
				RecordSelectionManager.selectLastCreatedRecordsOnGridPanelWithShift(1);
				
				// open Operations > Check citation menu
				openCitationWindow();
				
				// select Mode: Manual, Check: All, Set: All
				Utils.clickToWebelementWithAction(Utils.createGeneralWebElementFromEnum(GeneralPageLocators.SELF_CITATION_MODE_MANUAL));
				Utils.clickToWebelementWithAction(Utils.createGeneralWebElementFromEnum(GeneralPageLocators.SELF_CITATION_CHECK_ALL));
				Utils.clickToWebelementWithAction(Utils.createGeneralWebElementFromEnum(GeneralPageLocators.SELF_CITATION_SET_ALL));
				
				// hit Next button
				Utils.waitForAndClickOnGeneralWebElement(GeneralPageLocators.SELF_CITATION_NEXT);
				Utils.defaultWait();
				
				// select Independent: Yes (repeated with No and I don't know options)
				Utils.clickToWebelementWithAction(Utils.createGeneralWebElementFromEnum(independenceType));
				
				// hit the skip button
				Utils.waitForAndClickOnGeneralWebElement(GeneralPageLocators.SELF_CITATION_SAVE_AND_NEXT);
				Utils.waitForRightPanelLoadingFinished();
				
				// hit the close button
				Utils.waitForAndClickOnGeneralWebElement(GeneralPageLocators.SELF_CITATION_CLOSE);
	}
	
	/**
	 * Creates record and proceed with a Check citation operation and manually check it as self cited
	 */
	@Test
	public final void testCheckCitationManualSelfCited() {
		
		performManualCitation(GeneralPageLocators.SELF_CITATION_INDEPENDENT_YES);
		
		// check the External citation value of the record has changed
		List<WebElement> selectedRows = RecordSelectionManager.selectLastCreatedRecordsOnGridPanelWithShift(1);
		int attributePosition = GeneralTableManager.getColPos(CitationRecordAttributes.EXTERNAL_CITATION);
		for (WebElement row : selectedRows) {
			String classAttribute = row.findElement(By.xpath("./tbody/tr/td[" + attributePosition + "]//span")).getAttribute("class");
			assertTrue("Független oszlop checkbox kijelöltnek kellene lennie", classAttribute.contains(CHECKBOX_CHECKED_CLASS));
		}
		
		performManualCitation(GeneralPageLocators.SELF_CITATION_INDEPENDENT_NO);
		
		// check the External citation value of the record has changed
		selectedRows = RecordSelectionManager.selectLastCreatedRecordsOnGridPanelWithShift(1);
		attributePosition = GeneralTableManager.getColPos(CitationRecordAttributes.EXTERNAL_CITATION);
		for (WebElement row : selectedRows) {
			String classAttribute = row.findElement(By.xpath("./tbody/tr/td[" + attributePosition + "]//span")).getAttribute("class");
			assertTrue("Független oszlop checkbox nem kijelöltnek kellene lennie", !classAttribute.contains(CHECKBOX_CHECKED_CLASS) && !classAttribute.contains(CHECKBOX_CHECKED_UNSET));
		}
		
		performManualCitation(GeneralPageLocators.SELF_CITATION_INDEPENDENT_IDN);
		
		// check the External citation value of the record has changed
		selectedRows = RecordSelectionManager.selectLastCreatedRecordsOnGridPanelWithShift(1);
		attributePosition = GeneralTableManager.getColPos(CitationRecordAttributes.EXTERNAL_CITATION);
		for (WebElement row : selectedRows) {
			String classAttribute = row.findElement(By.xpath("./tbody/tr/td[" + attributePosition + "]//span")).getAttribute("class");
			assertTrue("Független oszlop checkbox beállítatlan kellene lennie", classAttribute.contains(CHECKBOX_CHECKED_UNSET));
		}
		
		LOGGER.info("testCheckCitationManualSelfCitation has finished");
	}
	
	/**
	 * Creates record and proceed with a Check citation operation and manually check it as foreign edition or not
	 */
	@Test
	@Ignore
	public final void testCheckCitationManualForeignEdition() {
		// select a record from the created ones
		RecordSelectionManager.selectLastCreatedRecordsOnGridPanelWithShift(1);
		// open Operations > Check citation menu
		// select Mode: Manual, Check: All, Set: All
		// hit Next button
		// select Foreign edition (optional): Yes (repeated with No and I don't know options)
		// hit the Save and go to next button
		// find and check the publication's property has changed
		LOGGER.info("testCheckCitationManualForeignEdition has finished");
	}
	
	/**
	 * Creates record and proceed with a Check citation operation and manually check it as national origin or not
	 */
	@Test
	@Ignore
	public final void testCheckCitationManualNationalOrigin() {
		// select a record from the created ones
		RecordSelectionManager.selectLastCreatedRecordsOnGridPanelWithShift(1);
		// open Operations > Check citation menu
		// select Mode: Manual, Check: All, Set: All
		// hit Next button
		// select National origin (optional): Yes (repeated with No and I don't know options)
		// hit the Save and go to next button
		// find and check the publication's property has changed
		LOGGER.info("testCheckCitationManualNationalOrigin has finished");
	}
	
	/**
	 * Creates record and proceed with a Check citation operation and automatic check it as self cited
	 */
	@Test
	@Ignore
	public final void testCheckCitationAutomaticSelfCited() {
		// select a record from the created ones
		RecordSelectionManager.selectLastCreatedRecordsOnGridPanelWithShift(1);
		// open Operations > Check citation menu
		// select Mode: Automatic
		// hit Next button
		// hit Close button
		// find and check the publication's property has changed
		LOGGER.info("testCheckCitationAutomaticSelfCited has finished");
	}
	
	/**
	 * Creates record and proceed with an automatic Check citation operation and repeat it to see works only for unchecked
	 */
	@Test
	@Ignore
	public final void testCheckCitationOnlyUnchecked() {
		// select a record from the created ones
		RecordSelectionManager.selectLastCreatedRecordsOnGridPanelWithShift(1);
		// open Operations > Check citation menu
		// select Mode: Automatic
		// hit Next button
		// hit Close button
		// repeat again and check if a warning arrives because of no unchecked item selected
		LOGGER.info("testCheckCitationOnlyUnchecked has finished");
	}
	
	/**
	 * Creates record and proceed with an automatic Check citation operation and repeat it to see works only for independent
	 */
	@Test
	@Ignore
	public final void testCheckCitationOnlyIndependent() {
		// select a record from the created ones
		RecordSelectionManager.selectLastCreatedRecordsOnGridPanelWithShift(1);
		// open Operations > Check citation menu
		// select Mode: Manual, Check: Only unchecked, Set: Only independent
		// hit Next button
		// select Independent Yes
		// hit Close button
		// find and check the publication's property has changed
		LOGGER.info("testCheckCitationOnlyIndependent has finished");
	}
	
	/**
	 * Creates record and proceed with an automatic Check citation operation on multiple records
	 */
	@Test
	@Ignore
	public final void testMultipleAutomaticCheckCitation() {
		// select multiple record from the created ones
		RecordSelectionManager.selectLastCreatedRecordsOnGridPanelWithShift(CITATION_NUMBER);
		// open Operations > Check citation menu
		// select Mode: Automatic, Check: Only unchecked, Set: Only independent
		// hit Next button
		// hit Close button
		// check the External citation value of the records has changed
		LOGGER.info("testCheckCitationOnlyIndependent has finished");
	}
	
	/**
	 * Creates record and proceed with a manual Check citation operation on multiple records
	 */
	@Test
	@Ignore
	public final void testMultipleManualCheckCitation() {
		// select multiple record from the created ones
		RecordSelectionManager.selectLastCreatedRecordsOnGridPanelWithShift(CITATION_NUMBER);
		// open Operations > Check citation menu
		// select Mode: Manual, Check: Only unchecked, Set: Only independent
		// hit Next button
		// select Independent: Yes
		// hit the Save and go to next button
		// repeat the previous steps as many as record was selected
		// hit Close button
		// check the External citation value of the records has changed
		LOGGER.info("testCheckCitationOnlyIndependent has finished");
	}
	
	private void triggerSelfCitation() {
		Utils.waitForAndClickOnGeneralWebElement(ToolbarLocators.OPERATIONS);
		Utils.switchToActiveElement();
		Utils.waitForAndClickOnGeneralWebElement(ToolbarLocators.OPERATIONS_SELF_CITATION_MENU);
		Utils.switchToActiveElement();
		Utils.defaultWait();
		Utils.acceptMessageBoxIfVisible();
		Utils.waitForMessageBoxToBeDismissed();
	}
	
	private void openCitationWindow() {
		Utils.waitForAndClickOnGeneralWebElement(ToolbarLocators.OPERATIONS);
		Utils.switchToActiveElement();
		Utils.waitForAndClickOnGeneralWebElement(ToolbarLocators.OPERATIONS_CITATION_MENU);
		Utils.switchToActiveElement();
		Utils.waitForElementVisible(GeneralPageLocators.SELF_CITATION_WINDOW);
	}
}
