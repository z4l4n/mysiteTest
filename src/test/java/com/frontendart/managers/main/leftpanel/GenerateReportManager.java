package com.frontendart.managers.main.leftpanel;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.frontendart.common.Utils;
import com.frontendart.locators.main.leftpanel.GenerateReportLocators;

/**
 * Manager class for generate report tests.
 * 
 * TODO: refakt
 * 
 * @author Zoli
 *
 */
public class GenerateReportManager {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LogManager.getLogger("GenerateReportManager");

	/**
	 * open report panel window
	 * 
	 */
	public static void openReportGenerationWindow() {
		expandReportPanel();
		Utils.defaultWait();
		Utils.waitForAndClickOnGeneralWebElement(GenerateReportLocators.NEW_REPORT_BUTTON);
	}

	/**
	 * Returns true if list panel is collapsed
	 * 
	 * 
	 * @return
	 */
	public static boolean isReportPanelCollapsed() {
		return Utils.createGeneralWebElementFromEnum(GenerateReportLocators.REPORTINGPANEL).getAttribute("class").contains("collapsed");
	}

	/**
	 * expand report panel
	 * 
	 */
	public static void expandReportPanel() {
		if (isReportPanelCollapsed()) {
			Utils.waitForAndClickOnGeneralWebElement(GenerateReportLocators.EXPAND_BUTTON);
		}
	}

	/**
	 * clicks on generate report button
	 * @param reportPanel
	 */
	public static void clickOnGenerateReportButton() {
		Utils.waitForAndClickOnGeneralWebElement(GenerateReportLocators.GENERATE_REPORT_BUTTON);
	}

	/**
	 * Add random name for report
	 */
	public static String addRandomNameForReport() {
		final String reportName = Utils.randomString();
		Utils.writeTextToThisField(reportName, GenerateReportLocators.NAME_INPUT);
		return reportName;
	}

	/**
	 * Selects first option from report template
	 */
	public static void selectOptionFromReportTemplate() {
		LOGGER.info("Kattintsunk a Riport sablon/Report template legördülő menübe és válasszuk az első opciót.");
		final WebElement reportTemplateField = Utils.createGeneralWebElementFromEnum(GenerateReportLocators.REPORT_TEMPLATE_INPUT);
		reportTemplateField.sendKeys(Keys.DOWN);
		reportTemplateField.sendKeys(Keys.ENTER);
	}

	/**
	 * Selects first option from list
	 */
	public static void selectOptionFromList() {
		LOGGER.info("Kattintsunk a Lista/List legördülő menübe és válasszuk az első opciót.");
		final WebElement listField = Utils.createGeneralWebElementFromEnum(GenerateReportLocators.LIST_INPUT);
		listField.sendKeys(Keys.DOWN);
		Utils.waitMillisec(2000);
		listField.sendKeys(Keys.ENTER);
	}

	/**
	 * Selects first option from saved search
	 * 
	 */
	public static void selectOptionFromSavedSearch() {
		LOGGER.info("Kattintsunk a Mentett keresés/Saved search legördülő menübe és válasszuk az első opciót.");
		Utils.waitMillisec(1000);
		final WebElement savedSearchInput = Utils.createGeneralWebElementFromEnum(GenerateReportLocators.SAVED_SEARCH_INPUT);
		savedSearchInput.sendKeys(Keys.DOWN);
		savedSearchInput.sendKeys(Keys.ENTER);
	}

	/**
	 * Selects PDF option from file type
	 * TODO: randomize...
	 */
	public static void selectRandomOptionFromFileType() {
		//final int myRandomNumber = Utils.randInt(1, ReportTypes.values().length);

		// TODO: randomize report type

		// Selects file type input, and clicks on it, and finds PDF option
		LOGGER.info("Kattintsunk a Fájl formátum/File type legördülő menübe és válasszuk a PDF opciót.");
		final WebElement fileTypeInput = Utils.createGeneralWebElementFromEnum(GenerateReportLocators.FILE_TYPE_INPUT);
		for (int i = 1; i <= 3; i++) {
			fileTypeInput.sendKeys(Keys.DOWN);
		}

		fileTypeInput.sendKeys(Keys.ENTER);
	}

	/**
	 * Returns the reports as a list of webElements.
	 * @return
	 * 
	 */
	public static List<WebElement> getMyReportsAsWebElement() {
		return Utils.createGeneralWebElementsFromEnum(GenerateReportLocators.ALL_REPORTS);
	}

	/**
	 * Returns the report names.
	 * @return
	 * 
	 */
	public static List<String> getMyReportNamesAsString() {
		final List<String> myReportNames = new ArrayList<String>();

		for (final WebElement myReport : getMyReportsAsWebElement()) {
			final String name = myReport.findElement(By.xpath(GenerateReportLocators.REPORT_NAME.toString())).getText();
			myReportNames.add(name);
		}

		return myReportNames;
	}

	/**
	 * Delete report
	 * 
	 */
	public static void deleteThisReport(final String reportName) {
		final WebElement myReport = findThisReport(reportName);

		// Click on toggle and select delete
		LOGGER.info("Kattintsunk a(z) " + reportName + " nevű riport első oszlopában lévő legördülő ikonra és válasszuk a Törlés opciót.");
		final WebElement splitButton = myReport.findElement(By.xpath(GenerateReportLocators.REPORT_TOGGLE.toString()));
		splitButton.sendKeys(Keys.DOWN);
		Utils.switchToActiveElement().findElement(By.xpath(GenerateReportLocators.DELETE.toString())).click();
		Utils.defaultWait();

	}

	/**
	 * Get report URL
	 * 
	 */
	public static String getUrlOfThisReport(final String reportName) {
		final WebElement myReport = findThisReport(reportName);

		// Click on toggle and select delete
		LOGGER.info("Kattintsunk a(z) " + reportName + " nevű riport első oszlopában lévő legördülő ikonra és válasszuk az URL lekérése opciót.");
		final WebElement splitButton = myReport.findElement(By.xpath(GenerateReportLocators.REPORT_TOGGLE.toString()));
		splitButton.sendKeys(Keys.DOWN);
		Utils.switchToActiveElement().findElement(By.xpath(GenerateReportLocators.GET_URL.toString())).click();

		return Utils.createGeneralWebElementFromEnum(GenerateReportLocators.MESSAGE_BOX_URL).getText();
	}

	/**
	 * Open report URL
	 * 
	 */
	public static void openUrlOfThisReport(final String reportName) {
		getUrlOfThisReport(reportName);
		Utils.createGeneralWebElementFromEnum(GenerateReportLocators.MESSAGE_BOX_URL).click();
	}

	/**
	 * Finds the given report, and returns as web element
	 * 
	 */
	public static WebElement findThisReport(final String reportName) {
		final List<WebElement> myReportsAsWebElement = getMyReportsAsWebElement();
		final List<String> myReportNames = getMyReportNamesAsString();

		// Find report
		return myReportsAsWebElement.get(myReportNames.indexOf(reportName));
	}

	/**
	 * Returns the number of reports
	 * @return
	 * 
	 */
	public static int getNumberOfReports() {
		expandReportPanel();
		Utils.defaultWait();
		return getMyReportsAsWebElement().size();
	}

	/**
	 * Creates a random report
	 */
	public static String createRandomReport() {
		// Open generate window
		expandReportPanel();
		openReportGenerationWindow();

		// Add name
		final String reportName = addRandomNameForReport();

		// Select template, saved search, file type, list
		selectOptionFromReportTemplate();
		selectRandomOptionFromFileType();
		selectOptionFromList();

		// Click on generate riport button, and wait
		try {
			clickOnGenerateReportButton();
		} catch (final ElementNotVisibleException e) {
			Utils.waitForAndClickOnGeneralWebElement(GenerateReportLocators.REPORT_GENERATION_WINDOW_CLOSE_BUTTON);
			fail("Report generation button is not visible!");
		}

		Utils.isMessageBoxVisible();
		assertTrue("Egy üzenetnek kell tájékoztatnia a riport generálás kezdetéről!",
				Utils.isMessageBoxPresentWithText(GenerateReportLocators.REPORT_GENERATION_STARTED_MESSAGEBOX_TEXT));

		// TODO: This should be done in another way - wait until the report is ready...
		Utils.waitMillisec(180000);
		Utils.acceptMessageBoxIfVisible();
		Utils.waitForAndClickOnGeneralWebElement(GenerateReportLocators.REFRESH_BUTTON);
		Utils.acceptMessageBoxIfVisible();

		return reportName;
	}

}
