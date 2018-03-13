package com.frontendart.common;

/**
 * Class for constants
 * 
 * @author Zoli
 *
 */
public final class Constants {
	/**
	 * character set
	 */
	public static final char[] CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

	/**
	 * constant for random generate string length
	 */
	public static final int RANDOM_STRING_LENGTH = 10;

	/**
	 * constant for property file
	 */
	public static final String PROPERTIES_PATH = "test.properties";

	/**
	 * constant for captcha
	 */
	public static final String CAPTCHA_MAGIC_STRING = "eig6noung1Iequoocha3";

	/**
	 * Username for forgot password testcases
	 */
	public static final String FORGOT_PASSWORD_USERNAME = "mtmtuser3";

	/**
	 * Password for forgot password testcases
	 */
	public static final String FORGOT_PASSWORD_PASSWORD = "SztakiMtmtSztaki";

	/**
	 * Magic WoS DOI, which is needed for publication import
	 */
	public static final CharSequence MAGIC_DOI = "10.1002/ijc.25857";

	/**
	 * Magic WoS DOI, which is needed for publication import
	 */
	public static final CharSequence EXISTING_DOI = "10.1007/s10994-007-5038-2";

	/**
	 * empty Query name
	 */
	public static final String MY_EMPTY_QUERY_NAME = "myEmptyQuery";

	/**
	 *  Query name for predefined subset of all records 
	 */
	public static final String SUBSET_QUERY_NAME = "subsetQuery";
	
	/**
	 * Query name for own records
	 */
	public static final String OWN_QUERY_NAME = "createdByMe";
	
	/**
	 * deleted records query name
	 */
	public static final String MY_DELETED_RECORDS_QUERY_NAME = "myDeletedRecords";

	/**
	 * Test email for reset password tests.
	 */
	public static final String TEST_EMAIL = "test.mtmt.test@gmail.com";

	/**
	 * Test email password
	 */
	public static final String TEST_EMAIL_PWD = "SztakiSztaki";

	/**
	 * Field input path
	 */
	public static final String FIELD_INPUT_XPATH = ".//*/input";
	
	/**
	 * Fea prefix
	 */
	public static final String PREFIX = "featest_";

}
