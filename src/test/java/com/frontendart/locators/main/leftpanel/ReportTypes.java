package com.frontendart.locators.main.leftpanel;

/**
 * Enum class for report types.
 * @author Zoli
 *
 */
public enum ReportTypes {
	HTML_ONLINE("html-online"),
	HTML_ZIP("html-zip"),
	PDF("pdf"),
	DOCX("docx"),
	XLSX("xlsx"),
	ODT("odt"),
	ODS("ods");

	private String reportName;

	/**
	 * Constructor
	 * @param reportName
	 */
	private ReportTypes(final String reportName) {
		this.reportName = reportName;
	}

	/**
	 * Returns locator as string
	 */
	@Override
	public String toString() {
		return this.reportName;
	}
}
