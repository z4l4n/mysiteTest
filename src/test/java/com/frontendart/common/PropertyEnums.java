package com.frontendart.common;

/**
 * Class for property
 * @author Zoli
 *
 */
public enum PropertyEnums {

	/**
	 * Property enum
	 * @author Zoli
	 *
	 */
	DEFAULT_WAIT_TIME("defaultWaitTimeInSec"),
	MAX_WAIT_TIME("maxWaitTimeInSec"),
	TARGET_PC("targetPC"),
	BROWSER("browser"),
	ROLE("roleAsString"),
	MYCITE_SERVER_URL("myCiteSercerUrl"),
	GUI2_SERVER_URL("gui2ServerUrl");

	String propName;

	/**
	 * Constructor
	 * @param capability
	 */
	PropertyEnums(final String propName) {
		this.propName = propName;
	}

	/**
	 * Getter
	 * @return
	 */
	public String getPropName() {
		return this.propName;
	}

}
