package com.frontendart.locators.main.leftpanel;

import java.util.Arrays;
import java.util.List;

/**
 * Enum class for textfield search option
 * @author Zoli
 *
 */
public enum TextFieldSearchOptions {
	EXACT_MATCH(Arrays.asList("Teljes egyezés", "exact match"));

	private List<String> values;

	/**
	 * Constructor
	 * @param value
	 */
	private TextFieldSearchOptions(final List<String> values) {
		this.values = values;
	}

	/**
	 * Returns the value of enum
	 * 
	 * @return
	 */
	public List<String> getValue() {
		return this.values;
	}
}
