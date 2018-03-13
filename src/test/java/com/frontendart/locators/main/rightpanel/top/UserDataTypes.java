package com.frontendart.locators.main.rightpanel.top;

import com.frontendart.locators.main.MainPageLocators;
import com.frontendart.locators.records.attributes.general.GeneralTableAttributes;

/**
 * Enum class for user data types
 * @author Zoli
 *
 */
public enum UserDataTypes {
	SYSTEM_MESSAGES(MainPageLocators.SYSTEM_MESSAGES_BUTTON, SystemMessageTableAttributes.class),
	MESSAGES(MainPageLocators.MAIL_MESSAGES_BUTTON, MessageTableAttributes.class),
	TICKETS(MainPageLocators.TICKETS_BUTTON, TicketTableAttributes.class),
	FORUM(MainPageLocators.FORUM_BUTTON, ForumTableAttributes.class);

	private MainPageLocators button;
	private Class<? extends GeneralTableAttributes> pageToValidate;

	/**
	 * Constructor
	 * 
	 * @param button
	 * @param pageToValidate
	 */
	private UserDataTypes(final MainPageLocators button, final Class<? extends GeneralTableAttributes> pageToValidate) {
		this.button = button;
		this.pageToValidate = pageToValidate;
	}

	/**
	 * Returns button to click on
	 */
	public MainPageLocators getButton() {
		return this.button;
	}

	/**
	 * Returns page to validate
	 * @return
	 */
	public Class<? extends GeneralTableAttributes> getPageToValidate() {
		return this.pageToValidate;
	}
}
