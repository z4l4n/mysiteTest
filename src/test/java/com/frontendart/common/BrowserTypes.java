package com.frontendart.common;

import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Class for browser types
 * @author Zoli
 *
 */
public enum BrowserTypes {

	/**
	 * Browser type enum
	 * @author Zoli
	 *
	 */
	FIREFOX(DesiredCapabilities.firefox(), "firefox"),
	GOOGLECHROME(DesiredCapabilities.chrome(), "googlechrome"),
	IEXPLORER(DesiredCapabilities.internetExplorer(), "iexplorer");

	private DesiredCapabilities capability;
	String nameAsString;

	/**
	 * Constructor
	 * @param capability
	 */
	BrowserTypes(final DesiredCapabilities capability, final String nameAsString) {
		this.capability = capability;
		this.nameAsString = nameAsString;
	}

	/**
	 * Getter
	 * @return
	 */
	public DesiredCapabilities getCapability() {
		return this.capability;
	}

	/**
	 * Getter
	 * @return
	 */
	public String getNameAsString() {
		return this.nameAsString;
	}

}
