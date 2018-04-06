package com.frontendart.testsuites.workflow;

import static org.junit.Assume.assumeTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebElement;

import com.frontendart.categories.CoreSuite;
import com.frontendart.common.Constants;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.JunitTestClass;
import com.frontendart.locators.main.rightpanel.crud.RecordEditorLocators;
import com.frontendart.locators.records.attributes.general.BookSeriesRecordAttributes;
import com.frontendart.locators.records.attributes.general.GeneralRecordTypes;
import com.frontendart.managers.general.GeneralDataProvider;
import com.frontendart.managers.main.leftpanel.RecordSelectionManager;
import com.frontendart.managers.main.leftpanel.SearchManager;
import com.frontendart.managers.main.rightpanel.crud.CreateRecordManager;

public class BookSeriesTest extends JunitTestClass {

    @Test
    @Category({ CoreSuite.class })
    public void testStartPublishingYearCantBeSmallerThanEndYear() {
        assumeTrue(Utils.actualRoleIsCentralAdmin());

        Utils.writeMyRedmineIssues("#5183");

        RecordSelectionManager.selectThisRecordTypeFromSelector(GeneralRecordTypes.BOOK_SERIES);
        Utils.defaultWait();
        CreateRecordManager.clickOnNewButton();
        Utils.defaultWait();

        WebElement id = CreateRecordManager.findFieldOfThisAttribute(BookSeriesRecordAttributes.TITLE);
        WebElement sy = CreateRecordManager.findFieldOfThisAttribute(BookSeriesRecordAttributes.START_YEAR);
        WebElement ey = CreateRecordManager.findFieldOfThisAttribute(BookSeriesRecordAttributes.END_YEAR);

        GeneralDataProvider.setValueToThisAttribute(id, BookSeriesRecordAttributes.TITLE, Constants.PREFIX + Utils.randomString());
        GeneralDataProvider.setValueToThisAttribute(sy, BookSeriesRecordAttributes.START_YEAR, "1996");
        GeneralDataProvider.setValueToThisAttribute(ey, BookSeriesRecordAttributes.START_YEAR, "1992");
        Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.SAVE_AND_CLOSE);
        Utils.defaultWait();
        boolean wasError = Utils.isInvalidWindowVisible();

        if (wasError) {
            Utils.cancelMessageBoxIfVisible();
            Utils.defaultWait();
            Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.CANCEL_BUTTON);
        } else {
            SearchManager.cleanup();
        }

        Utils.myAssertTrue("There must be an error message because of the invalid date interval.", wasError);
    }

}
