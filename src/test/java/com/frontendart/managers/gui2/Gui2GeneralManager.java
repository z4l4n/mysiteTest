package com.frontendart.managers.gui2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.frontendart.common.Utils;
import com.frontendart.locators.general.GeneralLocatorTypes;
import com.frontendart.locators.gui2.AuthorPageLocators;
import com.frontendart.locators.gui2.Gui2MainPageLocators;
import com.frontendart.locators.gui2.Gui2RecordTypes;
import com.frontendart.locators.gui2.InstitutePageLocators;

/**
 * Manager class for select tests.
 * @author Zoli
 *
 */
public class Gui2GeneralManager {

	/**
	 * Check publication number on page
	 */
	public static void checkPublicationNumberOnPage(final WebDriver driver) {
		// Check item numbers
		final int publicationNumberFromTopInfo = Integer.parseInt(driver.findElement(By.xpath("//div[starts-with(@class, 'pieChart')]/div[3]/span")).getText());

		String publicationNumberFromTopNumbers = "0";
		int publicationNumberFromPublicationList = 0;
		if (publicationNumberFromTopInfo > 0) {
			Utils.waitForElementPresent(Gui2MainPageLocators.PUBLICATION_LIST);
			publicationNumberFromTopNumbers = driver.findElement(By.xpath("//*[@class='top-numbers']/div[2]")).getText();
			publicationNumberFromPublicationList = Utils.createGeneralWebElementsFromEnum(Gui2MainPageLocators.PUBLICATION_LIST).size();
		}

		// Validation
		Utils.myAssertEquals("A fenti információs sávban lévő tortadiagram mellett lévő publikációs számnak meg kell egyeznie a találatok számával.",
				publicationNumberFromTopInfo, publicationNumberFromPublicationList);
		Utils.myAssertTrue("A fenti információs sávban lévő tortadiagram mellett lévő publikációs számnak meg kell egyeznie az "
				+ "információs sáv alatt lévő publikációk számával.",
				publicationNumberFromTopNumbers.contains(Integer.toString(publicationNumberFromTopInfo)));
	}

	/**
	 * Check citation number on page
	 * 
	 */
	public static void checkCitationNumberOnPage(final WebDriver driver) {
		final int citationNumberFromTopInfo = Integer.parseInt(driver.findElement(By.xpath("//div[starts-with(@class, 'barChart')]/div[3]/span")).getText());
		String citationNumberFromTopNumbers = "";

		// 4 is for institute, 3 is for author
		if (driver.findElements(By.xpath("//*[@class='top-numbers']/div")).size() == 4) {
			citationNumberFromTopNumbers = driver.findElement(By.xpath("//*[@class='top-numbers']/div[4]")).getText();
		} else {
			citationNumberFromTopNumbers = driver.findElement(By.xpath("//*[@class='top-numbers']/div[3]")).getText();
		}

		// Validation
		Utils.myAssertTrue("A fenti információs sávban lévő oszlopdiagram mellett lévő idézet számnak meg kell egyeznie az "
				+ "információs sáv alatt lévő idézetszámmal.",
				citationNumberFromTopNumbers.contains(Integer.toString(citationNumberFromTopInfo)));

	}

	/**
	 * Check Gui2Page
	 * 
	 * @param class1
	 */
	public static void checkGui2Page(final Class<? extends GeneralLocatorTypes> gui2Class) {
		for (final GeneralLocatorTypes locator : gui2Class.getEnumConstants()) {
			Utils.myAssertTrue("A következő elemnek láthatónak kell lennie: " +
					locator.getNames().get(0) + "/" + locator.getNames().get(1),
					Utils.createGeneralWebElementFromEnum(locator).isDisplayed());
		}
	}

	/**
	 * Do page check for this record type
	 * 
	 * 
	 * @param gui2RecordType
	 */
	public static void doPageCheckForThisType(final WebDriver driver, final Gui2RecordTypes gui2RecordType) {
		switch (gui2RecordType) {
		case AUTHOR:
			checkGui2Page(AuthorPageLocators.class);
			checkPublicationNumberOnPage(driver);
			checkCitationNumberOnPage(driver);
			// Check infos 
			Utils.waitForAndClickOnGeneralWebElement(InstitutePageLocators.CAROUSEL_CONTROL_RIGHT);
			Utils.myAssertTrue("Az információs sávon meg kell jelennie az intézményhez/szerzőhöz tartozó főbb információknak. ", driver.findElement(By.xpath("//*[@class='carousel-inner']/div[2]")).isDisplayed());
			break;
		case INSTITUTE:
			checkGui2Page(InstitutePageLocators.class);
			// TODO: checkPublicationNumberOnPage(driver);
			// TODO: checkCitationNumberOnPage(driver);
			// Check infos 
			Utils.waitForAndClickOnGeneralWebElement(InstitutePageLocators.CAROUSEL_CONTROL_RIGHT);
			Utils.myAssertTrue("Az információs sávon meg kell jelennie az intézményhez/szerzőhöz tartozó főbb információknak. ", driver.findElement(By.xpath("//*[@class='carousel-inner']/div[2]")).isDisplayed());
			break;
		default:
			break;
		}
	}

}