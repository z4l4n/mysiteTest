package com.frontendart.testsuites.gui2;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.CoreSuite;
import com.frontendart.categories.Gui2Suite;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.Gui2JunitTestClass;
import com.frontendart.locators.gui2.AdvancedSearchLocators;
import com.frontendart.locators.gui2.SearchPanelLocators;
import com.frontendart.managers.gui2.Gui2SearchManager;
import com.frontendart.managers.gui2.Gui2SelectorManager;

/**
 * Test class for author tests.
 * @author Zoli
 *
 */
//@Ignore
@Category({ Gui2Suite.class, CENTRAL_ADMIN_Suite.class })
@SuppressWarnings({ "PMD.JUnitTestsShouldIncludeAssert", "PMD.JUnitTestContainsTooManyAsserts" })
public class Gui2SearchTest extends Gui2JunitTestClass {
	/**
	 * Logger
	 */
	private static final Logger LOGGER = LogManager.getLogger("SearchTest");

	//This test class should only run with author (mtmtuser3) rights - meaning that it will only run once
	@BeforeClass
	public static void beforeMethod() {
		org.junit.Assume.assumeTrue(Utils.actualRoleIsCentralAdmin());
	}

	/**
	 * Simple search for string
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1371">#1371</a>
	 * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1720">#1720</a>
	 * 
	 * TODO: refaktor if possible
	 * 
	 */
	@Test
	@Category(CoreSuite.class)
	public final void testSimpleSearch() {
		Utils.writeMyRedmineIssues("#1371");

		// Write to search field
		Gui2SearchManager.performSearchWithText(Gui2SelectorManager.getRandomGui2RecordType(), "d");

		// Validate
		Utils.waitForElementPresent(SearchPanelLocators.FILTER_BOX);
		final WebElement filterBox = Utils.createGeneralWebElementFromEnum(SearchPanelLocators.FILTER_BOX);
		Utils.myAssertTrue("Meg kell jelennie egy kis dobozkának a keresőmező alatt.",
				filterBox.isDisplayed());
		Utils.myAssertTrue("A dobozkának tartalmaznia kell a keresett kifejezést.",
				filterBox.getText().contains("d"));

		
		
		List<WebElement> allSearchResults = Utils.createGeneralWebElementsFromEnum(SearchPanelLocators.RESULTS);
		int actualSearchResultsNumber = allSearchResults.size();
		Utils.scrollIntoView(allSearchResults.get(actualSearchResultsNumber-1));
		allSearchResults = Utils.createGeneralWebElementsFromEnum(SearchPanelLocators.RESULTS);
		int newSearchResultsNumber = allSearchResults.size();
		
		while(actualSearchResultsNumber!=newSearchResultsNumber){
			actualSearchResultsNumber = allSearchResults.size();
			Utils.scrollIntoView(allSearchResults.get(actualSearchResultsNumber-1));
			allSearchResults = Utils.createGeneralWebElementsFromEnum(SearchPanelLocators.RESULTS);
			newSearchResultsNumber = allSearchResults.size();
		}
		
		// Check search results
		final List<WebElement> searchResults = Utils.createGeneralWebElementsFromEnum(SearchPanelLocators.RESULTS);
		LOGGER.info("Elvárt eredmény: A megjelenő találati lista elemeinek tartalmazniuk kell a keresett kifejezést.");
		for (final WebElement result : searchResults) {
			final String textOfThisResult = result.findElement(By.xpath(SearchPanelLocators.RESULT_HEADER.toString())).getText();
			assertTrue("This text does not contain the search text: " + textOfThisResult, textOfThisResult.toLowerCase(Locale.ENGLISH).contains("d".toLowerCase(Locale.ENGLISH)));
		}

		// Validate result number
		final String resultNumberText = Utils.createGeneralWebElementFromEnum(SearchPanelLocators.RESULT_NUMBER).getText();
		
		Utils.myAssertTrue("A találatok számának meg kell egyeznie a kiírt találatok számával.",
				resultNumberText.contains(Integer.toString(searchResults.size())));

	}

	/**
	 * Keresés stringre
	 */
	@Test
	@Category(CoreSuite.class)
	public final void testLeftSearch() {
		// Navigate to random record type
		Gui2SelectorManager.navigateToMyRecordType(Gui2SelectorManager.getRandomGui2RecordType());

		// Write one character to search field
		final WebElement searchField = Utils.createGeneralWebElementFromEnum(SearchPanelLocators.LEFT_SEARCH_FIELD);
		final String searchText = "d";
		Utils.waitMillisec(3000);
		LOGGER.info("A bal oldali keresőbe írjuk be a következő karaktersorozatot: " + searchText);
		searchField.sendKeys(searchText);

		// Validate result
		LOGGER.info("Elvárt eredmény: A megjelenő találati lista elemeinek tartalmazniuk kell a keresett kifejezést.");
		for (final WebElement topLevel : Utils.createGeneralWebElementsFromEnum(SearchPanelLocators.ALL_VISIBLE_ELEMENTS_ON_LEFT_PANEL)) {
			topLevel.getText().toLowerCase(Locale.ENGLISH).contains(searchText.toLowerCase(Locale.ENGLISH));
		}
	}

	/**
	 * Összetett keresés évszám szerint
	 * 
	 * TODO: refaktor if possible
	 */
	@Test
	@Category(CoreSuite.class)
	public final void testAdvancedSearchByYear() {
		// Click on advanced search button
		Utils.waitForAndClickOnGeneralWebElement(AdvancedSearchLocators.ADVANCED_SEARCH);

		// Fill search fields
		final int fromDate = Utils.randInt(2000, 2004);
		final int tillDate = fromDate + 10;
		Utils.writeTextToThisField(Integer.toString(fromDate), AdvancedSearchLocators.FROMDATE);
		Utils.writeTextToThisField(Integer.toString(tillDate), AdvancedSearchLocators.TILLDATE);

		// Click on Submit button
		Utils.waitForAndClickOnGeneralWebElement(AdvancedSearchLocators.SUBMIT_BUTTON);

		// Validate 
		Utils.waitForElementPresent(SearchPanelLocators.FILTER_BOX);
		final WebElement filterBox = Utils.createGeneralWebElementFromEnum(SearchPanelLocators.FILTER_BOX);
		Utils.myAssertTrue("Meg kell jelennie egy kis dobozkának a keresőmező alatt.",
				filterBox.isDisplayed());
		Utils.myAssertTrue("A dobozkának tartalmaznia kell a kezdődátumot.",
				filterBox.getText().contains(Integer.toString(fromDate)));
		Utils.myAssertTrue("A dobozkának tartalmaznia kell a záródátumot.",
				filterBox.getText().contains(Integer.toString(tillDate)));

		// Validate search results
		final List<WebElement> searchResults = Utils.createGeneralWebElementsFromEnum(SearchPanelLocators.RESULTS);
		LOGGER.info("A találati listában kapott publikációk megjelenési évének a megadott dátumtartományba kell esnie.");
		for (final WebElement result : searchResults) {
			final int resultDate = Integer.parseInt(result.findElement(By.xpath("./div/div/span")).getText());
			assertTrue("Publication date is not correct! Expected greater or equal than " + fromDate, resultDate >= fromDate);
			assertTrue("Publication date is not correct! Expected less or equal than " + tillDate, resultDate <= tillDate);
		}
	}

	/**
	 * Összetett keresés mező label-ek ellenőrzése
	 */
	@Test
	@Category(CoreSuite.class)
	public final void testAdvancedSearchFieldLabels() {
		// Click on advanced search
		Utils.waitForAndClickOnGeneralWebElement(AdvancedSearchLocators.ADVANCED_SEARCH);

		// get labels
		final List<WebElement> labelsAsWebelements = Utils.createGeneralWebElementsFromEnum(AdvancedSearchLocators.ALL_LABELS);
		final List<String> labelsAsString = new ArrayList<String>();
		for (final WebElement labelWebelement : labelsAsWebelements) {
			labelsAsString.add(labelWebelement.findElement(By.xpath("./label")).getText());
		}

		// add search button label
		labelsAsString.add(Utils.createGeneralWebElementFromEnum(AdvancedSearchLocators.SUBMIT_BUTTON).getText());

		// Validate
		final EnumSet<AdvancedSearchLocators> visibleLocators = EnumSet.allOf(AdvancedSearchLocators.class);
		visibleLocators.remove(AdvancedSearchLocators.ADVANCED_SEARCH);
		for (final AdvancedSearchLocators locator : visibleLocators) {
			Utils.myAssertTrue("A megjelenő keresőpanelnek tartalmaznia kell az alábbi mező feliratot: " +
					Utils.getAllMyLabelsAsString(locator),
					labelsAsString.contains(locator.getNames().get(0)) || (labelsAsString.contains(locator.getNames().get(1))));
		}
	}
}
