package com.frontendart.testsuites.main.rightpanel.view;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebElement;

import com.frontendart.categories.AUTHOR_Suite;
import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.CoreSuite;
import com.frontendart.categories.INSTITUTIONAL_ADMIN_Suite;
import com.frontendart.categories.RightPanelViewSuite;
import com.frontendart.common.Constants;
import com.frontendart.common.TestConfiguration;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.JunitTestClass;
import com.frontendart.locators.main.rightpanel.view.FilterLocators;
import com.frontendart.locators.records.attributes.general.GeneralRecordTypes;
import com.frontendart.managers.login.LoginManager;
import com.frontendart.managers.login.LogoutManager;
import com.frontendart.managers.main.leftpanel.RecordSelectionManager;
import com.frontendart.managers.main.rightpanel.view.ChangeViewManager;
import com.frontendart.managers.main.rightpanel.view.FilterManager;

/**
* Test class for filter
* @author ratkaik
*/
@Category({ RightPanelViewSuite.class, AUTHOR_Suite.class, INSTITUTIONAL_ADMIN_Suite.class, CENTRAL_ADMIN_Suite.class })
@SuppressWarnings({ "PMD.JUnitTestsShouldIncludeAssert", "PMD.JUnitTestContainsTooManyAsserts" })
public class FilterTest extends JunitTestClass {

	/**
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1532">#1532</a>
	 * 
	 * SimpleFilterTest
	 */
	@Test
	@Category(CoreSuite.class)
	public final void testSimpleFilter() {
		Utils.writeMyRedmineIssues("#1532");

		RecordSelectionManager.selectRandomRecordTypeFromSelector();

		// Get record numbers
		final int gridTableFullSizeBeforeFilter = RecordSelectionManager.gridTableFullSize();

		// write random string to filter field
		FilterManager.performFilterWithRandomText();

		// Validation: get record numbers again and check
		final int gridTableFullSizeAfterFilter = RecordSelectionManager.gridTableFullSize();
		Utils.myAssertEquals("A táblázatban nem szabad megjelennie egyetlen sornak sem.",
				0, gridTableFullSizeAfterFilter);

		// clear filter field
		FilterManager.performFilterWithText("");

		// Validation: get record numbers again and check
		final int gridTableFullSizeAfterClearingFilter = RecordSelectionManager.gridTableFullSize();
		Utils.myAssertEquals("A táblázatban lévő sorok számának meg kell egyeznie a szűrés előtti sorok számával.",
				gridTableFullSizeBeforeFilter, gridTableFullSizeAfterClearingFilter);
	}

	/**
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1572">#1572</a>
	 * 
	 * Filter character numbers
	 */
	@Test
	@Category(CoreSuite.class)
	public final void testFilterCharacterNumber() {
		Utils.writeMyRedmineIssues("#1572");
		RecordSelectionManager.selectRandomRecordTypeFromSelector();
		ChangeViewManager.switchToGridView();

		// Get record numbers
		final int gridTableFullSizeBeforeFilter = RecordSelectionManager.gridTableFullSize();

		// Write random sting of length 1,2,3
		for (int index = 1; index < 4; index++) {

			// write random string to filter field
			final String filterText = Utils.randomString(Constants.CHARSET, index);
			FilterManager.performFilterWithText(filterText);
			RecordSelectionManager.waitUntilTableIsReady();

			// Validate,
			final int gridTableFullSizeAfterFilter = RecordSelectionManager.gridTableFullSize();
			final WebElement filterField = FilterManager.getFilterField();
			final String classAttribute = filterField.getAttribute("class");

			// Check filter text length
			if (index < 3) {
				Utils.myAssertEquals("Nem szabad frissülnie a táblázatnak.",
						gridTableFullSizeBeforeFilter, gridTableFullSizeAfterFilter);
				Utils.myAssertTrue("A szűrő mezőnek piros szegélyűnek kell lennie (helytelen érték)", classAttribute.contains("invalid"));
			} else {
				Utils.myAssertNotEquals("A szűrésnek végre kell hajtódnia.",
						gridTableFullSizeBeforeFilter, gridTableFullSizeAfterFilter);
				Utils.myAssertFalse("A szűrő mezőnek kék szegélyűnek kell lennie.", classAttribute.contains("invalid"));
			}
		}

		// Clean filterfield
		FilterManager.performFilterWithText("");
	}

	/**
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/2895">#2895</a>
	 */
	@Test
	@Category(CoreSuite.class)
	public final void testFilterIsEmptyAfterLogout() {
		Utils.writeMyRedmineIssues("#2895");

		// Select random record type
		final GeneralRecordTypes recordType = GeneralRecordTypes.getVisibleRecordTypeForActualRole();
		RecordSelectionManager.selectThisRecordTypeFromSelector(recordType);

		// Get record numbers
		final int gridTableFullSizeBeforeFilter = RecordSelectionManager.gridTableFullSize();

		// write random string to filter field
		FilterManager.performFilterWithRandomText();

		// Logout, login
		LogoutManager.logout();
		LoginManager.loginSuccessfullyWithThisRole(TestConfiguration.role);

		// Select same record type
		RecordSelectionManager.selectThisRecordTypeFromSelector(recordType);

		// Check filter field is empty
		final String filterInputValue = Utils.createGeneralWebElementFromEnum(FilterLocators.INPUT_FIELD).getAttribute("value");
		Utils.myAssertEquals("A szűrőmezőnek üresnek kell lennie.", "", filterInputValue);

		// Check record size is same as the original
		final int gridTableFullSizeAfterLogin = RecordSelectionManager.gridTableFullSize();
		Utils.myAssertEquals("A táblázatban az eredeti rekordszámnak kell szerepelnie.",
				gridTableFullSizeBeforeFilter, gridTableFullSizeAfterLogin);

	}
}