package com.frontendart.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;
import java.util.Properties;

import org.junit.Assert;

/**
 * Basic test configuration
 *
 * The following steps should be done after each testsite update: - the email
 * address of the mtmtuser3 user should be approved
 *
 *
 * @author Zoli
 *
 */
public class TestConfiguration {
    // public static String myCiteServerUrl = "http://testsite.mt2.dsd.sztaki.hu/";
    public static String myCiteServerUrl = "https://mtmt2-adminteszt.mtmt.hu/frontend/";
    public static String gui2ServerUrl = "https://mtmt2-adminteszt.mtmt.hu/gui2/";
    public static String gui2AuthenticatedServerUrl;
    public static String gui2Username = "GUI2";
    public static String gui2Password = "GUI2teszt";
    public static int defaultWaitTimeInSec = 5;
    public static int maxWaitTimeInSec = 30;
    public static Roles role = Roles.CENTRAL_ADMIN;
    public static String targetPC = "http://127.0.0.1:4444/wd/hub";
    // public static String targetPC =
    // "http://jenkins.mt2.dsd.sztaki.hu:4444/wd/hub";
    public static BrowserTypes browser = BrowserTypes.FIREFOX;

    // java -jar "selenium-server-standalone-2.53.0.jar"
    // -Dwebdriver.firefox.bin="/home/zalan/ff46/firefox/firefox"

    static {
	try {
	    loadProperties();
	    createGui2AuthenticatedUrl();
	} catch (final IOException | URISyntaxException e) {
	    Assert.fail(e.getMessage());
	}
    }

    /**
     * Loads properties
     *
     * @throws IOException
     */
    public static void loadProperties() throws IOException {
	// Get property file
	final Properties prop = new Properties();

	final File propertieFile = new File(Constants.PROPERTIES_PATH);
	if (propertieFile.exists()) {
	    final InputStream input = new FileInputStream(propertieFile);
	    prop.load(input);
	    setProperties(prop);
	    input.close();
	} else {
	    System.out.println("Properties file not found, initializing from code defaults...");
	}

    }

    private static void createGui2AuthenticatedUrl() throws URISyntaxException {
	URI serverUrl = new URI(gui2ServerUrl);
	URI authenticatedServerUrl = new URI(serverUrl.getScheme(),
		String.format("%s:%s@%s", gui2Username, gui2Password, serverUrl.getHost()), serverUrl.getPath(),
		serverUrl.getQuery(), serverUrl.getFragment());
	gui2AuthenticatedServerUrl = authenticatedServerUrl.toString();
    }

    /**
     * Sets properties
     *
     * @param prop
     */
    public static void setProperties(final Properties prop) {
	for (final PropertyEnums property : PropertyEnums.values()) {
	    // Get property name
	    final String propertyName = property.getPropName();
	    final String propertyValue = prop.getProperty(propertyName);
	    setProperty(property, propertyValue);
	}
    }

    /**
     * Set this property
     *
     * @param property
     * @param propertyValue
     */
    public static void setProperty(final PropertyEnums property, final String propertyValue) {
	if (propertyValue != null) {
	    // Set property value
	    switch (property) {
	    case DEFAULT_WAIT_TIME:
		defaultWaitTimeInSec = Integer.parseInt(propertyValue);
		break;
	    case MAX_WAIT_TIME:
		maxWaitTimeInSec = Integer.parseInt(propertyValue);
		break;
	    case TARGET_PC:
		targetPC = propertyValue;
		break;
	    case BROWSER:
		browser = BrowserTypes.valueOf(propertyValue.toUpperCase(Locale.ENGLISH));
		break;
	    case ROLE:
		role = Roles.valueOf(propertyValue);
		break;
	    case MYCITE_SERVER_URL:
		myCiteServerUrl = propertyValue;
		break;
	    case GUI2_SERVER_URL:
		gui2ServerUrl = propertyValue;
		break;
	    default:
		break;
	    }
	}
    }

    /**
     * returns desired browser types
     *
     * @return
     */
    public static BrowserTypes getDesiredBrowser() {
	return browser;
    }
}
