package com.frontendart.testsuites.main.rightpanel.view;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.frontendart.categories.AUTHOR_Suite;
import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.CoreSuite;
import com.frontendart.categories.INSTITUTIONAL_ADMIN_Suite;
import com.frontendart.categories.RightPanelViewSuite;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.SelectRandomRecordTypeJunitTestClass;
import com.frontendart.locators.main.MainPageLocators;
import com.frontendart.managers.main.rightpanel.view.ChangeViewManager;

/**
 * Test class for view record.
 *
 * @author Zoli
 *
 */
@Category({ RightPanelViewSuite.class, AUTHOR_Suite.class, INSTITUTIONAL_ADMIN_Suite.class, CENTRAL_ADMIN_Suite.class })
@SuppressWarnings({ "PMD.JUnitTestsShouldIncludeAssert", "PMD.JUnitTestContainsTooManyAsserts" })
public class ChangeViewTest extends SelectRandomRecordTypeJunitTestClass {

    /**
     * Search page check after clicking ICON display button
     */
    /* No icon view
     * 	@Test
    	@Category(CoreSuite.class)
    	public final void testPageAfterSelectingIconView() {
    		// Click on icon view
    		ChangeViewManager.switchToIconView();
    
    		// Validate icon view is visible
    		Utils.myAssertTrue("Az ikon nézetnek láthatónak kell lennie.", Utils.isThisElementVisible(MainPageLocators.CONTENT_ICON_GRID_PANEL));
    
    		// TODO: more validation
    	}
    */
    /**
     * Reimplement: ID is not always appropriate for checking. Search page check after clicking LISTGRID display button
     */
    /*@Test
    public final void testAfterSelectingListGridView() {
    	final WebElement selectedRow = RecordSelectionManager.selectRandomRenderedRowsFromGridPanel(1).get(0);
    	final String idOfRow = Integer.toString(GeneralTableManager.getIDOfThisRow(selectedRow));
    
    	// Validate
    	ChangeViewManager.switchToListGridView();
    	Utils.myAssertTrue("A széles nézetnek láthatónak kell lennie, és annak az elemnek kell kijelölve lennie, amelyiket "
    			+ "a táblázatos nézetben kiválasztottuk.", Utils.isThisElementVisible(MainPageLocators.CONTENT_LIST_GRID_PANEL));
    
    	// There should be one item selected, we get that
    	final WebElement selectedItem = RecordSelectionManager.getSelectedItemsInView().get(0);
    
    	// Get value of selected element
    	final String valueOfSelectedElement = selectedItem.findElement(By.xpath("./tbody/tr/td[2]/div")).getText();
    
    	// Validate
    	Utils.myAssertTrue("A széles nézetben lévő kijelölt elem adatainak meg kell jelennie a táblázatos nézet "
    			+ "megfelelő sorában lévő értékeknél.", valueOfSelectedElement.contains(idOfRow));
    
    }*/

    /**
     * Search page check after clicking GRID display button
     */
    @Test
    @Category(CoreSuite.class)
    public final void testAfterSelectingGridView() {
        // select random record type and change to grid view
        ChangeViewManager.switchToListGridView();
        Utils.defaultWait();
        ChangeViewManager.switchToGridView();
        Utils.waitForElementPresent(MainPageLocators.CONTENT_GRID_PANEL);

        // Validation
        assertTrue("Table view is not visible.", Utils.myAssertTrue("A táblázatos nézetnek láthatónak kell lennie.",
                Utils.isThisElementVisible(MainPageLocators.CONTENT_GRID_PANEL)));
    }

    @Test
    @Category(CoreSuite.class)
    public final void testAfterSelectingListview() {
        // select random record type and change to list view
        ChangeViewManager.switchToGridView();
        Utils.defaultWait();
        ChangeViewManager.switchToListGridView();
        Utils.waitForElementPresent(MainPageLocators.CONTENT_LIST_GRID_PANEL);

        // Validation
        assertTrue("Table view is not visible.", Utils.myAssertTrue("A listás nézetnek láthatónak kell lennie.",
                Utils.isThisElementVisible(MainPageLocators.CONTENT_LIST_GRID_PANEL)));
    }

}
