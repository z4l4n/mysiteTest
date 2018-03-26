package com.frontendart.common;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import com.frontendart.locators.records.attributes.general.GeneralRecordTypes;

/**
 * Enum class for roles
 *
 * @author Zoli
 *
 */

public enum Roles {
    CENTRAL_ADMIN(
            Arrays.asList("Központi adminisztrátor", "mtmtuser4", "SztakiMtmtSztaki"),
            Arrays.asList(
                    EnumSet.allOf(GeneralRecordTypes.class),
                    EnumSet.allOf(GeneralRecordTypes.class),
                    EnumSet.allOf(GeneralRecordTypes.class))),

    //	INSTITUTIONAL_ADMIN(Arrays.asList("Intézményi adminisztrátor", "instadmin", "password"),
    INSTITUTIONAL_ADMIN(
            Arrays.asList("Intézményi adminisztrátor", "mtmtuser2", "SztakiMtmtSztaki"),
            Arrays.asList(
                    EnumSet.of(GeneralRecordTypes.ADMIN, GeneralRecordTypes.AUTHOR, GeneralRecordTypes.BOOK_SERIES, GeneralRecordTypes.CITY,
                            GeneralRecordTypes.CONFERENCE, GeneralRecordTypes.INSTITUTE,
                            GeneralRecordTypes.FUNDER, GeneralRecordTypes.KEYWORD, GeneralRecordTypes.PROJECT,
                            GeneralRecordTypes.PUBLISHER, GeneralRecordTypes.PUBLICATION),
                    // TODO: Add more creatable record types for instadmin
                    EnumSet.of(GeneralRecordTypes.INSTITUTE, GeneralRecordTypes.PUBLICATION),
                    EnumSet.allOf(GeneralRecordTypes.class))),

    AUTHOR(
            Arrays.asList("Szerző", "mtmtuser3", "SztakiMtmtSztaki"),
            Arrays.asList(
                    EnumSet.of(GeneralRecordTypes.KEYWORD, GeneralRecordTypes.PUBLICATION),
                    EnumSet.of(GeneralRecordTypes.PUBLICATION),
                    EnumSet.of(GeneralRecordTypes.AUTHOR, GeneralRecordTypes.INSTITUTE,
                            GeneralRecordTypes.KEYWORD, GeneralRecordTypes.PUBLICATION)));

    private String                      roleName;
    private String                      username;
    private String                      password;
    private EnumSet<GeneralRecordTypes> creatableRecordTypes;
    private EnumSet<GeneralRecordTypes> editableRecordTypes;
    private EnumSet<GeneralRecordTypes> visibleRecordTypes;

    /**
     * Constructor
     *
     * @param creditentials
     * @param rights
     */
    private Roles(final List<String> creditentials, final List<EnumSet<GeneralRecordTypes>> rights) {
        roleName = creditentials.get(0);
        username = creditentials.get(1);
        password = creditentials.get(2);
        creatableRecordTypes = rights.get(0);
        editableRecordTypes = rights.get(1);
        visibleRecordTypes = rights.get(2);
    }

    /**
     * Returns the name of the role
     *
     * @return
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Returns the username of role
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password of role
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the not creatable record types for this role
     *
     * @return
     */
    public EnumSet<GeneralRecordTypes> getCreatableRecordTypes() {
//		if ("Központi adminisztrátor".equals(this.roleName)) {
//			this.creatableRecordTypes.remove(GeneralRecordTypes.INSTITUTE);
//		}
        creatableRecordTypes.remove(GeneralRecordTypes.TICKET);
        return creatableRecordTypes;
    }

    /**
     * Returns editable record types for this role
     *
     * @return
     */
    public EnumSet<GeneralRecordTypes> getEditableRecordTypes() {
        // Editing these record type is tricky
        if ("Központi adminisztrátor".equals(roleName)) {
            editableRecordTypes.removeAll(EnumSet.of(GeneralRecordTypes.CITATION,
                    GeneralRecordTypes.CONFERENCE, GeneralRecordTypes.AUTHOR,
                    GeneralRecordTypes.INSTITUTE, GeneralRecordTypes.PUBLISHER));
        }
        return editableRecordTypes;
    }

    /**
     * Returns visible rcord types for this role
     *
     * @return
     */
    public EnumSet<GeneralRecordTypes> getVisibleRecordTypes() {
        if ("Intézményi adminisztrátor".equals(roleName)) {
            visibleRecordTypes
                    .removeAll(EnumSet.of(GeneralRecordTypes.CITATION, GeneralRecordTypes.SMART_QUERY, GeneralRecordTypes.NAMED_LIST,
                            GeneralRecordTypes.TICKET, GeneralRecordTypes.VARIABLE, GeneralRecordTypes.FORUM));
        }

        return visibleRecordTypes;
    }

}
