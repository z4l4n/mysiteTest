package com.frontendart.managers.gui2;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.frontendart.common.Utils;
import com.frontendart.locators.gui2.Gui2MainPageLocators;
import com.frontendart.locators.gui2.Gui2RecordTypes;
import com.frontendart.locators.gui2.InstitutePageLocators;
import com.frontendart.locators.gui2.LeftPanelLocators;

/**
 * Manager class for gui2 selector tests
 * @author Zoli
 *
 */
public class Gui2SelectorManager {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LogManager.getLogger("Gui2SelectorManager");

	/**
	 * gets selectable items of panel
	 */
	public static List<WebElement> getSelectableItemsOfLeftPanel() {
		return Utils.createGeneralWebElementsFromEnum(LeftPanelLocators.ALL_VISIBLE_ELEMENTS_ON_LEFT_PANEL);
	}

	/**
	 * Selects random item from left panel
	 */
	public static WebElement selectRandomItemFromLeftPanel() {
		LOGGER.info("Válasszunk ki egy rekordot a bal oldali menüből.");

		// Select one item from left panel
		final List<WebElement> selectableItems = getSelectableItemsOfLeftPanel();

		// TODO: Randomize this for real
		final int random = Utils.randInt(1, selectableItems.size());
		selectableItems.get(random - 1).click();
		Utils.waitForElementPresent(Gui2MainPageLocators.TITLE_ROW);
		return selectableItems.get(random - 1);
	}

	/**
	 * Olyan rekord kiválasztása, amihez tartoznak publikációk
	 * 
	 * @return
	 */
	public static void selectItemWithPublications() {
		LOGGER.info("Válasszunk ki egy olyan rekordot a bal oldali menüből, amihez tartoznak publikációk.");
		// Select one item from left panel
		final List<WebElement> selectableItems = getSelectableItemsOfLeftPanel();

		boolean found = false;
		int tryCount = 0;
		while ((!found) && (tryCount < 20)) {
			final int random = Utils.randInt(1, selectableItems.size()-1);
			selectableItems.get(random).click();
			Utils.waitForElementPresent(InstitutePageLocators.PIE_CHART_TEXT);
			
			String originalString = Utils.createGeneralWebElementFromEnum(InstitutePageLocators.PIE_CHART_TEXT).getText();
			System.out.println(originalString);
			
			originalString = originalString.replace(".", "");
			System.out.println(originalString);
			
			final int publicationNumber = Integer.parseInt(originalString);
			if (publicationNumber > 0) {
				found = true;
				Utils.waitForElementPresent(Gui2MainPageLocators.PUBLICATION_LIST);
			}
			tryCount++;

		}

	}

	/**
	 * Navigates to random item which has publication
	 * 
	 */
	public static void navigateToRandomItemWhichHasPublications() {
		// Navigate to random record type
		final Gui2RecordTypes gui2RecordType = getRandomGui2RecordType();
		navigateToMyRecordType(gui2RecordType);

		// Select item with publication
		if (gui2RecordType.equals(Gui2RecordTypes.TOPIC)) {
			selectRandomItemFromLeftPanel();
		} else {
			selectItemWithPublications();
			Utils.waitMillisec(2000);
		}

	}

	/**
	 * Return random column position
	 * 
	 * @param myUserDataType
	 * @return
	 */
	public static Gui2RecordTypes getRandomGui2RecordType() {
		// Get random column number to edit - this will return institute now...
		// final int randNumber = Utils.randInt(0, Gui2RecordTypes.values().length - 1);
		//return Gui2RecordTypes.values()[randNumber];
		return Gui2RecordTypes.INSTITUTE;
	}

	/**
	 * Navigates to topic type record
	 * @param selectedPublication
	 */
	public static void navigateToTopicRecordType() {
		navigateToMyRecordType(Gui2RecordTypes.TOPIC);
	}

	/**
	 * Navigates to my Record type
	 * @param myRecordType
	 */
	public static void navigateToMyRecordType(final Gui2RecordTypes myRecordType) {
		switch (myRecordType) {
		case AUTHOR:
			Utils.waitForAndClickOnGeneralWebElement(LeftPanelLocators.AUTHORS_TAB);
			break;
		case INSTITUTE:
			Utils.waitForAndClickOnGeneralWebElement(LeftPanelLocators.INSTITUTES_TAB);
			Utils.waitForElementPresent(LeftPanelLocators.ALL_DISPLAYED_INSTITUTES);
			break;
		case TOPIC:
			Utils.waitForAndClickOnGeneralWebElement(LeftPanelLocators.TOPICS_TAB);
			break;
		default:
			break;
		}
	}
}