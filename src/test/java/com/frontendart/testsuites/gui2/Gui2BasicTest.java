package com.frontendart.testsuites.gui2;

import java.awt.AWTException;

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
import com.frontendart.locators.gui2.Gui2MainPageLocators;
import com.frontendart.locators.gui2.Gui2RecordTypes;
import com.frontendart.managers.gui2.Gui2GeneralManager;
import com.frontendart.managers.gui2.Gui2SelectorManager;

/**
 * Test class for gui2 basic tests.
 *
 * @author Zoli
 *
 */
//@Ignore
@Category({ Gui2Suite.class, CENTRAL_ADMIN_Suite.class })
@SuppressWarnings({ "PMD.JUnitTestsShouldIncludeAssert", "PMD.JUnitTestContainsTooManyAsserts" })
public class Gui2BasicTest extends Gui2JunitTestClass {

    //This test class should only run with central admin - meaning that it will only run once
    @BeforeClass
    public static void beforeMethod() {

        org.junit.Assume.assumeTrue(Utils.actualRoleIsCentralAdmin());

    }

    /**
     * Basic Page check Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1399">#1399</a> Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1582">#1582</a>
     *
     * @throws AWTException
     * @throws InterruptedException
     */
    @Test
    @Category(CoreSuite.class)
    public final void testBasicPageCheck() throws AWTException, InterruptedException {

        Utils.writeMyRedmineIssues("#1399#1582");

        // Select random record type
        final Gui2RecordTypes gui2RecordType = Gui2SelectorManager.getRandomGui2RecordType();
        Gui2SelectorManager.navigateToMyRecordType(gui2RecordType);

        // Random select from left panel
        final WebElement selectedItem = Gui2SelectorManager.selectRandomItemFromLeftPanel();

        // Check colour
        Utils.myAssertTrue("A kijelölt elemnek más színű jelölést kell kapnia.",
                selectedItem.findElement(By.xpath("./span")).getAttribute("class").contains("selected"));
        Utils.waitMillisec(10000);
        // Check name
        final String nameFromTitleRow = Utils.createGeneralWebElementFromEnum(Gui2MainPageLocators.TITLE_ROW).getText();
        final String nameFromElement = selectedItem.findElement(By.xpath("./span")).getText();

        System.out.println("nameFromTitleRow: " + nameFromTitleRow);
        System.out.println("nameFromElement: " + nameFromElement);

        Utils.myAssertTrue("A jobb fenti címsávnak tartalmaznia kell az intézmény/szerző/témakör nevét.",
                nameFromElement.contains(nameFromTitleRow));

        // Further check
        Gui2GeneralManager.doPageCheckForThisType(driver, gui2RecordType);
    }
}
