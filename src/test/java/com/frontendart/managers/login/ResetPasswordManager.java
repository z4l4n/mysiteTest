package com.frontendart.managers.login;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.SubjectTerm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import com.frontendart.common.Constants;
import com.frontendart.common.Utils;
import com.frontendart.locators.login.ResetPasswordLocators;

/**
 * Manager class for reset password tests
 * 
 * @author Zoli
 *
 */
public class ResetPasswordManager {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LogManager.getLogger("ResetPasswordManager");

	/**
	 * Redirect to reset password page
	 */
	public static String navigateToResetPasswordPage() {
		// Go to forgot password page, fill the fields, and click on forgot password button
		ForgotPasswordManager.performForgotPasswordProcessWithThisUsername(Constants.FORGOT_PASSWORD_USERNAME);
		Utils.waitForMessageBoxToBePresent();
		Utils.acceptMessageBoxIfVisible();

		// Get email
		Utils.waitMillisec(10000);
		int tryCount = 0;
		LOGGER.info("Lépjünk be a " + Utils.getActualRole().getRoleName() + " jogosultsághoz tartozó felhasználónévhez "
				+ "regisztrált e-mail cím postafiókjába, és a kapott email-ben kattintsunk a megadott linkre");
		String newPasswordLink = readEmail(Arrays.asList(Constants.TEST_EMAIL, Constants.TEST_EMAIL_PWD));
		while ((newPasswordLink == null) && (tryCount < 5)) {
			tryCount++;
			Utils.waitMillisec(10000);
			newPasswordLink = readEmail(Arrays.asList(Constants.TEST_EMAIL, Constants.TEST_EMAIL_PWD));
		}

		if (newPasswordLink == null) {
			Assert.fail("Couldn't get password reset link from email. Has the email arrived?");
		}
		// Navigate to new password
		Utils.refresh();
		Utils.navigateToThisURL(newPasswordLink);

		return newPasswordLink;
	}
	
	/**
	 * Redirect to reset password page
	 */
	public static String navigateToResetPasswordPageWithThisDatas(String username, String emailAddress, String emailPassword) {
		// Go to forgot password page, fill the fields, and click on forgot password button
		ForgotPasswordManager.performForgotPasswordProcessWithThisUsername(username);
		Utils.waitForMessageBoxToBePresent();
		Utils.acceptMessageBoxIfVisible();

		// Get email
		Utils.waitMillisec(10000);
		int tryCount = 0;
		LOGGER.info("Lépjünk be a " + Utils.getActualRole().getRoleName() + " jogosultsághoz tartozó felhasználónévhez "
				+ "regisztrált e-mail cím postafiókjába, és a kapott email-ben kattintsunk a megadott linkre");
		String newPasswordLink = readEmail(Arrays.asList(emailAddress, emailPassword));
		while ((newPasswordLink == null) && (tryCount < 5)) {
			tryCount++;
			Utils.waitMillisec(10000);
			newPasswordLink = readEmail(Arrays.asList(emailAddress, emailPassword));
		}

		if (newPasswordLink == null) {
			Assert.fail("Couldn't get password reset link from email. Has the email arrived?");
		}
		// Navigate to new password
		Utils.refresh();
		Utils.navigateToThisURL(newPasswordLink);

		return newPasswordLink;
	}

	/**
	 * Read email
	 */
	public static String readEmail(final List<String> userAndPwd) {
		// create email session 
		final Session emailSession = createEmailSession();

		String newPasswordLink = null;
		try {
			// create the POP3 store object and connect with the pop server
			final Store store = emailSession.getStore("imaps");
			store.connect("imap.gmail.com", userAndPwd.get(0), userAndPwd.get(1));

			// create the folder object and open it
			final Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			// Get link		
			newPasswordLink = getResetPasswordLink(emailFolder);

			// close the store and folder objects
			emailFolder.close(false);
			store.close();

		} catch (final NoSuchProviderException e) {
			LOGGER.error(e);
			Assert.fail(e.getMessage());
		} catch (final MessagingException e) {
			LOGGER.error(e);
			Assert.fail(e.getMessage());
		} catch (final IOException e) {
			LOGGER.error(e);
			Assert.fail(e.getMessage());
		}

		return newPasswordLink;
	}

	/**
	 * Gets reset password link
	 * @throws MessagingException 
	 * @throws IOException 
	 */
	public static Session createEmailSession() {
		//create properties field
		final Properties properties = new Properties();
		properties.put("mail.pop3.host", "pop.gmail.com");
		properties.put("mail.pop3.port", "995");
		properties.put("mail.pop3.starttls.enable", "true");
		return Session.getDefaultInstance(properties);
	}

	/**
	 * Gets reset password link
	 * @throws MessagingException 
	 * @throws IOException 
	 */
	public static String getResetPasswordLink(final Folder emailFolder) throws IOException, MessagingException {

		// retrieve the messages from the folder in an array and print it
		final Message[] messages = emailFolder.search(new SubjectTerm("Jelszóváltoztatás az mtmt2-ben"), emailFolder.getMessages());

		// Get the link...
		String newPasswordLink = null;
		String returnValue = null;
		for (final Message message : messages) {
			final String emailText = message.getContent().toString();
			newPasswordLink = emailText.split(": ")[1].split(" ")[0];
			returnValue = newPasswordLink.substring(0, newPasswordLink.length() - 1);
		}

		return returnValue;
	}

	/**
	 * Writes text to reset password username field
	 */
	public static void writeThisTextToUsernameField(final String text) {
		Utils.writeTextToThisField(text, ResetPasswordLocators.USERNAME);
	}

	/**
	 * Writes text to reset password - password field
	 */
	public static void writeThisTextToPasswordField(final String text) {
		Utils.writeTextToThisField(text, ResetPasswordLocators.PASSWORD);
	}

	/**
	 * Writes text to reset password - password field
	 */
	public static void writeThisTextToPasswordAgainField(final String text) {
		Utils.writeTextToThisField(text, ResetPasswordLocators.PASSWORD_AGAIN);
	}

	/**
	 * Clicks on send
	 */
	public static void clickOnSendButton() {
		Utils.waitForAndClickOnGeneralWebElement(ResetPasswordLocators.RESET_PASSWORD_BUTTON);
	}

	/**
	 * reset password process
	 */
	public static String performResetPasswordProcessWithCurrentRole() {
		final String link = navigateToResetPasswordPage();
		writeThisTextToUsernameField(Utils.getActualRole().getUsername());
		writeThisTextToPasswordField(Constants.FORGOT_PASSWORD_PASSWORD);
		Utils.waitMillisec(2000);
		writeThisTextToPasswordAgainField(Constants.FORGOT_PASSWORD_PASSWORD);
		Utils.waitMillisec(2000);
		clickOnSendButton();

		return link;
	}
	
	/**
	 * reset password process
	 */
	public static String performResetPasswordProcess(String username, String newPassword) {
		final String link = navigateToResetPasswordPageWithThisDatas(username, "featest.dummy@gmail.com", Constants.TEST_EMAIL_PWD);
		writeThisTextToUsernameField(username);
		writeThisTextToPasswordField(newPassword);
		Utils.waitMillisec(2000);
		writeThisTextToPasswordAgainField(newPassword);
		Utils.waitMillisec(2000);
		clickOnSendButton();

		return link;
	}

	/**
	 * reset password process
	 */
	public static void performResetOldPasswordWithCurrentRole() {
		navigateToResetPasswordPage();
		writeThisTextToUsernameField(Utils.getActualRole().getUsername());
		writeThisTextToPasswordField(Utils.getActualRole().getPassword());
		writeThisTextToPasswordAgainField(Utils.getActualRole().getPassword());
		Utils.waitMillisec(1000);
		clickOnSendButton();
	}

}
