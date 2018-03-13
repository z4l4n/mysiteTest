package com.frontendart.locators.main.rightpanel.view;

/**
 * Record locked status types
 * @author Zoli
 *
 */
public enum LockStatusTypes {
	LOCKED("Locked", "Zárolt"),
	UNLOCKED("Unlocked", "Feloldva");

	private String engName;
	private String hunName;

	/**
	 * Constructor 
	 * @param engName
	 * @param hunName
	 */
	private LockStatusTypes(final String engName, final String hunName) {
		this.engName = engName;
		this.hunName = hunName;
	}

	/**
	 * Returns english name
	 * @return
	 */
	public String getEngName() {
		return this.engName;
	}

	/**
	 * Returns hungarian name
	 * @return
	 */
	public String getHunName() {
		return this.hunName;
	}

}
