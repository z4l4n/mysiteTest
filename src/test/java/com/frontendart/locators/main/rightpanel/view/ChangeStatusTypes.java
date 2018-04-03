package com.frontendart.locators.main.rightpanel.view;

import java.util.Arrays;
import java.util.List;

/**
 * Record status types
 * 
 * @author Zoli
 *
 */
public enum ChangeStatusTypes {
    NOT_APPROVED(Arrays.asList("Nem jóváhagyott", "Unapproved")),
    APPROVED(Arrays.asList("Jóváhagyott", "Approved"));
    //ADMIN_APPROVED(Arrays.asList("Admin láttamozott", "Admin approved")),
//	MERGED(Arrays.asList("Összevont", "Merged")),
    //VALIDATED(Arrays.asList("Érvényesített", "Validated")),
    //CHECKED(Arrays.asList("Hitelesített", "Checked"));

    private String hunName;
    private String engName;

    /**
     * Constructor
     * 
     * @param engName
     * @param hunName
     * @param availableStatusTypes
     */
    private ChangeStatusTypes(final List<String> names) {
        hunName = names.get(0);
        engName = names.get(1);
    }

    /**
     * Returns the english name of locator
     * 
     * @return
     */
    public String getEngName() {
        return engName;
    }

    /**
     * Returns the hungarian name of locator
     * 
     * @return
     */
    public String getHunName() {
        return hunName;
    }

}
