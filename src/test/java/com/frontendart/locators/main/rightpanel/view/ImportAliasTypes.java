package com.frontendart.locators.main.rightpanel.view;

import java.util.Arrays;
import java.util.List;

/**
 * Record status types
 * @author Zoli
 *
 */
public enum ImportAliasTypes {
	ADMIN(Arrays.asList("Admin", "Admin")),
	APPEARANCE(Arrays.asList("Megjelenés", "Appearance"));

	private String hunName;
	private String engName;

	/**
	 * Constructor
	 * 
	 * @param engName
	 * @param hunName
	 * @param availableStatusTypes
	 */
	private ImportAliasTypes(final List<String> names) {
		this.hunName = names.get(0);
		this.engName = names.get(1);
	}

	/**
	 * Returns the english name of locator
	 * @return
	 */
	public String getEngName() {
		return this.engName;
	}

	/**
	 * Returns the hungarian name of locator
	 * @return
	 */
	public String getHunName() {
		return this.hunName;
	}

}
