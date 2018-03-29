package com.frontendart.testsuites.main.leftpanel;

import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.frontendart.categories.AUTHOR_Suite;
import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.CoreSuite;
import com.frontendart.categories.INSTITUTIONAL_ADMIN_Suite;
import com.frontendart.categories.LeftPanelSuite;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.JunitTestClass;
import com.frontendart.locators.main.leftpanel.GenerateReportLocators;
import com.frontendart.locators.records.attributes.general.GeneralRecordTypes;
import com.frontendart.managers.main.leftpanel.GenerateReportManager;
import com.frontendart.managers.main.leftpanel.RecordSelectionManager;
import com.frontendart.managers.main.leftpanel.SearchManager;

/**
 * Test class for generate report
 *
 * @author Zoli
 *
 */
@Category({ LeftPanelSuite.class, AUTHOR_Suite.class, INSTITUTIONAL_ADMIN_Suite.class, CENTRAL_ADMIN_Suite.class })
@SuppressWarnings({ "PMD.JUnitTestsShouldIncludeAssert", "PMD.JUnitTestContainsTooManyAsserts" })
public class GenerateReportTest extends JunitTestClass {

    /**
     * Simple report page check (with existing report template) Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1557">#1557</a> Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1561">#1561</a>
     */
    @Test
    @Category(CoreSuite.class)
    public final void testExistingReportTemplate() {
        Utils.writeMyRedmineIssues("#1557#1561");

        // Select random record type which should have report template

        List<GeneralRecordTypes> shouldHaveTemplate = Arrays.asList(GeneralRecordTypes.AUTHOR, GeneralRecordTypes.PUBLICATION,
                GeneralRecordTypes.INSTITUTE);
        Collections.shuffle(shouldHaveTemplate);

        //RecordSelectionManager.selectThisRecordTypeFromSelector(iterator.next());
        RecordSelectionManager.selectThisRecordTypeFromSelectorWithoutRunEmptyQuery(shouldHaveTemplate.get(0));
        SearchManager.createAndRunEmptyQuery();
        Utils.defaultWait();
        // Open report generation window, and get window label text
        GenerateReportManager.openReportGenerationWindow();
        Utils.defaultWait();

        final String windowLabel = Utils.createGeneralWebElementFromEnum(GenerateReportLocators.REPORT_GENERATION_WINDOW_HEADER).getText();
        //System.out.println(windowLabel);
        // Validate
        Utils.myAssertTrue("Egy új felugró ablaknak kell megjelennie Riport generálása / Generate Report megnevezéssel.",
                "Riport generálása".equals(windowLabel) || "Generate Report".equals(windowLabel));
        Utils.myAssertFalse("Nem várt hibaüzenet látható a riport generálása oldalon!", Utils.acceptMessageBoxIfVisible());

        // Close report window
        Utils.waitForAndClickOnGeneralWebElement(GenerateReportLocators.REPORT_GENERATION_WINDOW_CLOSE_BUTTON);
    }

    /**
     * Simple report page check
     */
    @Test
    @Category(CoreSuite.class)
    public final void testNotExistingReportTemplate() {
        // Select random record type which should not have report template
        final EnumSet<GeneralRecordTypes> shouldNotHaveTemplate = Utils.getActualRole().getVisibleRecordTypes();
        shouldNotHaveTemplate
                .removeAll(EnumSet.of(GeneralRecordTypes.AUTHOR, GeneralRecordTypes.PUBLICATION, GeneralRecordTypes.INSTITUTE));
        final int randNumber = Utils.randInt(0, shouldNotHaveTemplate.size() - 1);
        final Iterator<GeneralRecordTypes> iterator = shouldNotHaveTemplate.iterator();
        for (int index = 0; index < randNumber; index++) {
            iterator.next();
        }
        //RecordSelectionManager.selectThisRecordTypeFromSelector(iterator.next());

        RecordSelectionManager.selectThisRecordTypeFromSelectorWithoutRunEmptyQuery(iterator.next());
        SearchManager.createAndRunEmptyQuery();
        GenerateReportManager.openReportGenerationWindow();
        Utils.defaultWait();

        // Validate
        assertFalse("The report generation windows shouldn't appear",
                Utils.isThisElementPresent(GenerateReportLocators.REPORT_GENERATION_WINDOW));
    }

    /**
     *
     * report filtering test
     */
    @Test
    @Category(CoreSuite.class)
    public void testReportFiltering() {
        List<GeneralRecordTypes> shouldHaveTemplate = Arrays.asList(GeneralRecordTypes.AUTHOR, GeneralRecordTypes.PUBLICATION,
                GeneralRecordTypes.INSTITUTE);
        Collections.shuffle(shouldHaveTemplate);

        RecordSelectionManager.selectThisRecordTypeFromSelectorWithoutRunEmptyQuery(shouldHaveTemplate.get(0));
        GenerateReportManager.expandReportPanel();
        Utils.defaultWait();
        GenerateReportManager.performFiltering("PDF");
        Utils.defaultWait();

        //Checking the filtering
        GenerateReportManager.checkIfReportListContainsThisString("PDF");

    }

    /**
     * Reimplement Generates simple report Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1237">#1237</a>
     * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1238">#1238</a> Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1270">#1270</a> Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1272">#1272</a> Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1538">#1538</a>
     *
     */
    /*
    @Test
    public final void testGenerateSimpleRandomReport() {
    	Utils.writeMyRedmineIssues("#1237#1238#1270#1272#1538");

    	// Get report number
    	final int reportNumberBeforeCreation = GenerateReportManager.getNumberOfReports();

    	// Create report and wait
    	final String myNewReportName = GenerateReportManager.createRandomReport();

    	// Validate report number
    	final int reportNumberAfterCreation = GenerateReportManager.getNumberOfReports();
    	Utils.myAssertEquals("A riportokat tartalmazó táblázatban egy riporttal többnek kell lennie.",
    			reportNumberBeforeCreation + 1, reportNumberAfterCreation);
    	Utils.myAssertTrue("A riportokat tartalmazó táblázatban szerepelnie kell egy " + myNewReportName + " nevű riportnak.",
    			GenerateReportManager.getMyReportNamesAsString().contains(myNewReportName));

    	// Delete report
    	GenerateReportManager.deleteThisReport(myNewReportName);
    }*/

    /**
     * Reimplement: Wait for the popup window... Delete a report
     */
    /*@Test
    public final void testDeleteReport() {
    	// Create report and wait
    	final String myNewReportName = GenerateReportManager.createRandomReport();

    	// Get report number
    	final int reportNumberBeforeDeletion = GenerateReportManager.getNumberOfReports();

    	// Delete report
    	GenerateReportManager.deleteThisReport(myNewReportName);

    	// Validate report number
    	final int reportNumberAfterDeletion = GenerateReportManager.getNumberOfReports();
    	Utils.myAssertEquals("A riportokat tartalmazó táblázatban egy riporttal kevesebbnek kell lennie.",
    			reportNumberBeforeDeletion - 1, reportNumberAfterDeletion);
    	Utils.myAssertFalse("A riportokat tartalmazó táblázatban nem szabad szerepelnie egy " + myNewReportName + " nevű riportnak.",
    			GenerateReportManager.getMyReportNamesAsString().contains(myNewReportName));
    }*/

    /**
     * Reimplement Get report URL
     */
    /*@Test
    public final void testOpenReportURL() {
    	// Create report and wait
    	final String myNewReportName = GenerateReportManager.createRandomReport();

    	// get URL
    	final String reportUrlName = GenerateReportManager.getUrlOfThisReport(myNewReportName);
    	Utils.acceptMessageBoxIfVisible();
    	GenerateReportManager.openUrlOfThisReport(myNewReportName);

    	// Switch to new window
    	final String winHandleBefore = driver.getWindowHandle();
    	for (final String winHandle : driver.getWindowHandles()) {
    		driver.switchTo().window(winHandle);
    		Utils.defaultWait();
    	}

    	// Get Url, and check
    	final String currentUrl = driver.getCurrentUrl();
    	Utils.myAssertEquals("The current Url should equal with the report Url on the message box!", reportUrlName, currentUrl);

    	// Close and switch back to original window
    	driver.close();
    	driver.switchTo().window(winHandleBefore);

    	// Cleanup
    	Utils.acceptMessageBoxIfVisible();
    	GenerateReportManager.deleteThisReport(myNewReportName);
    }*/

    // FURTHER TEST TO BE IMPLEMENTED

    /**
     * Download a report
     */

    /**
     * Filter report panel
     */
}
