package com.frontendart.testsuites.main.leftpanel;

import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

import org.junit.Ignore;
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

        RecordSelectionManager.selectThisRecordTypeFromSelectorWithoutRunEmptyQuery(shouldHaveTemplate.get(0));

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

        RecordSelectionManager.selectThisRecordTypeFromSelectorWithoutRunEmptyQuery(iterator.next());

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

    @Test
    @Category(CoreSuite.class)
    public void testGeneratingAndDeletingAReport() {
        Utils.writeMyRedmineIssues("#1272, #1538");

        RecordSelectionManager.selectThisRecordTypeFromSelector(GeneralRecordTypes.PUBLICATION);
        Utils.defaultWait();

        String name = GenerateReportManager.createRandomReport();
        Utils.defaultWait();
        List<String> ll = GenerateReportManager.getMyReportNamesAsString();

        Utils.myAssertTrue("The report list should contain the report named " + name,
                GenerateReportManager.getMyReportNamesAsString().contains(name));
        Utils.defaultWait();
        GenerateReportManager.deleteThisReport(name);

    }

    @Test
    @Ignore
    public void testReportURL() {
        RecordSelectionManager.selectThisRecordTypeFromSelector(GeneralRecordTypes.PUBLICATION);
        Utils.defaultWait();

        String name = GenerateReportManager.createRandomReport();
        Utils.defaultWait();

        Utils.myAssertTrue("The report list should contain the report named " + name,
                GenerateReportManager.getMyReportNamesAsString().contains(name));
        Utils.defaultWait();
        GenerateReportManager.verifyURLOfThisReport(name);
        Utils.defaultWait();

        GenerateReportManager.deleteThisReport(name);
    }

}
