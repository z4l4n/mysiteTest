package com.frontendart.locators.general;

import java.util.List;

/**
 * General interface for locators. Every locator class should implement this class.
 * 
 * @author Zoli
 * 
 */
public interface GeneralLocatorTypes {
	/**
	 * This will return the locator names in different languages
	 * @return
	 */
	List<String> getNames();
}
