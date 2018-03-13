package com.frontendart.testsuites.gui2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.CoreSuite;
import com.frontendart.categories.Gui2Suite;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.Gui2JunitTestClass;
import com.frontendart.locators.gui2.InstitutePageLocators;
import com.frontendart.managers.gui2.Gui2LeftPanelLabelManager;
import com.frontendart.managers.gui2.Gui2PublicationManager;
import com.frontendart.managers.gui2.Gui2SelectorManager;

/**
 * Test class for gui2 tests.
 *
 * @author Zoli
 *
 */
//@Ignore
@Category({ Gui2Suite.class, CENTRAL_ADMIN_Suite.class })
@SuppressWarnings({ "PMD.JUnitTestsShouldIncludeAssert", "PMD.JUnitTestContainsTooManyAsserts" })
public class Gui2LeftPanelLabelTest extends Gui2JunitTestClass {

    // This test class should only run with author (mtmtuser3) rights - meaning
    // that it will only run once
    @BeforeClass
    public static void beforeMethod() {
        org.junit.Assume.assumeTrue(Utils.actualRoleIsCentralAdmin());

    }

    /**
     * Logger
     */
    private static final Logger LOGGER = LogManager.getLogger("SelectTest");

    /**
     * Simple left panel label test Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1400">#1400</a>
     */
    @Test
    @Category(CoreSuite.class)
    public final void testCheckLeftPanelTabLabels() {
        Utils.writeMyRedmineIssues("#1400");

        // Check left panel labels
        Gui2LeftPanelLabelManager.checkLeftPanelLabels();

        // Navigate to random record type, which has publications
        Gui2SelectorManager.navigateToRandomItemWhichHasPublications();

        // Check publication list
        Gui2PublicationManager.checkCountOfPublication();

        Utils.waitForElementPresent(InstitutePageLocators.LINE_CHART);
        // Select random publication and click on it
        final WebElement selectedPublication = Gui2PublicationManager.selectRandomPublication();
        LOGGER.info(
                "Kiválasztott publikáció címe: " + selectedPublication.findElement(By.xpath("./div/div[2]/h4/a")).getAttribute("title"));

        Gui2PublicationManager.clickOnThisPublication(selectedPublication);

        // Click on the title of the publication
        LOGGER.info("Kattintsunk a kiválasztott publikáció címére.");
        selectedPublication.findElement(By.xpath("./div/div[2]/h4/a")).click();

        // Check left panel labels
        Gui2LeftPanelLabelManager.checkLeftPanelLabels();

    }

}
