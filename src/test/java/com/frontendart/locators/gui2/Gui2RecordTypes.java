package com.frontendart.locators.gui2;

/**
 * General record types 
 * @author Zoli
 *
 */
public enum Gui2RecordTypes {
	AUTHOR("Author", "Szerző"),
	INSTITUTE("Institute", "Intézmény"),
	TOPIC("Topic", "Témakör");

	String englishName;
	String hungarianName;

	/**
	 * Constructor
	 * 
	 * @param engName
	 * @param hungName
	 */
	private Gui2RecordTypes(final String engName, final String hungName) {
		this.englishName = engName;
		this.hungarianName = hungName;
	}

	/**
	 * Returns the english name of locator
	 * @return
	 */
	public String getEnglishName() {
		return this.englishName;
	}

	/**
	 * Returns the hungarian name of locator
	 * @return
	 */
	public String getHungarianName() {
		return this.hungarianName;
	}

}
