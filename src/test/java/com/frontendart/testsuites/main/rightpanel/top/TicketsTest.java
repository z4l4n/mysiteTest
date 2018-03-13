package com.frontendart.testsuites.main.rightpanel.top;

import static org.junit.Assert.assertTrue;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebElement;

import com.frontendart.categories.AUTHOR_Suite;
import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.INSTITUTIONAL_ADMIN_Suite;
import com.frontendart.categories.RightPanelTopSuite;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.JunitTestClass;
import com.frontendart.locators.main.rightpanel.crud.RecordEditorLocators;
import com.frontendart.locators.main.rightpanel.top.TicketTableAttributes;
import com.frontendart.locators.main.rightpanel.view.ToolbarLocators;
import com.frontendart.locators.records.attributes.general.GeneralRecordTypes;
import com.frontendart.managers.main.leftpanel.RecordSelectionManager;
import com.frontendart.managers.main.leftpanel.SearchManager;
import com.frontendart.managers.main.rightpanel.top.TopGeneralManager;

/**
 * Class for tickets test
 * @author Zoli
 *
 */
@Category({ RightPanelTopSuite.class, CENTRAL_ADMIN_Suite.class, INSTITUTIONAL_ADMIN_Suite.class, AUTHOR_Suite.class })
@SuppressWarnings({ "PMD.JUnitTestsShouldIncludeAssert", "PMD.JUnitTestContainsTooManyAsserts" })
public class TicketsTest extends JunitTestClass {

	/**
	 * Create random ticket
	 */
	@Test
	public final void testCreateTicket() throws InterruptedException {
		// Create ticket and check
		RecordSelectionManager.selectRandomRecordTypeFromSelector();
		SearchManager.createAndRunEmptyQuery();
		RecordSelectionManager.selectRandomRenderedRowsFromGridPanel(1);
		Utils.waitForAndClickOnGeneralWebElement(ToolbarLocators.NEW_TICKET_BUTTON);
		TopGeneralManager.checkGeneralElementCreation(TicketTableAttributes.class);
	}

	/**
	 * TODO: Delete ticket - currentlyno one has rights to do that...
	 */

	/**
	 * Check assignee field is present for certain record types
	 */
	@Test
	public final void testAssigneeFieldIsPresent() throws InterruptedException {
		// Navigate random record type
		final EnumSet<GeneralRecordTypes> records = EnumSet.of(GeneralRecordTypes.AUTHOR, GeneralRecordTypes.INSTITUTE,
				GeneralRecordTypes.PUBLICATION);
		final int randNumber = Utils.randInt(0, records.size() - 1);
		final Iterator<GeneralRecordTypes> iterator = records.iterator();
		for (int index = 0; index < randNumber; index++) {
			iterator.next();
		}
		RecordSelectionManager.selectThisRecordTypeFromSelector(iterator.next());
		RecordSelectionManager.selectRandomRenderedRowsFromGridPanel(1);
		Utils.defaultWait();
		Utils.waitForAndClickOnGeneralWebElement(ToolbarLocators.NEW_TICKET_BUTTON);

		// Click on New button and get visible labels
		TopGeneralManager.clickOnNewButton();
		final List<WebElement> visibleLabels = Utils.createGeneralWebElementsFromEnum(RecordEditorLocators.EDITOR_FORM_FIELDS_VISIBLE_LABELS_ALTERNATIVE);
		final List<String> visibleLabelsAsString = Utils.convertThisWebElementArrayToStringArray(visibleLabels);

		// Check and close
		final boolean result = Utils.isThisStringListContainsThisAttribute(visibleLabelsAsString, TicketTableAttributes.ASSIGNEE);
		Utils.waitForAndClickOnGeneralWebElement(RecordEditorLocators.CLOSE);
		TopGeneralManager.closeWindow();
		assertTrue("Assignee field should be visible!", result);
	}

}
