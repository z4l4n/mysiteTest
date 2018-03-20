package com.frontendart.locators.records.attributes.general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

import com.frontendart.common.RecordPermissionType;
import com.frontendart.common.Utils;
import com.frontendart.locators.records.attributes.hidden.HiddenRecordTypes;
import com.frontendart.locators.records.types.RecordTypeFlags;

/**
 * General record types
 *
 * @author Zoli
 *
 */
public enum GeneralRecordTypes {
    ADMIN(Arrays.asList("Admin", "Admin"), AdminRecordAttributes.class),
    ACHIEVEMENT_PROPERTY(Arrays.asList("Alkotás tulajdonság", "Achievement Property"), AchievementPropertyRecordAttributes.class),
    APPEARANCE(Arrays.asList("Megjelenés", "Appearance"), AppearanceRecordAttributes.class),
    AUTHOR(
            Arrays.asList("Szerző", "Author"),
            AuthorRecordAttributes.class,
            Arrays.asList(RecordTypeFlags.HAS_REQUIRED_FIELDS, RecordTypeFlags.HAS_EXTENDABLE_FIELDS)),
    AUTHOR_TYPE(Arrays.asList("Szerzőségtípus", "Authorship Type"), AuthorTypeRecordAttributes.class),
    BOOK_SERIES(Arrays.asList("Könyvsorozat", "Book Series"), BookSeriesRecordAttributes.class),
    CATEGORY(Arrays.asList("Jelleg", "Category"), CategoryRecordAttributes.class),
    CITATION(Arrays.asList("Idézés", "Citation"), CitationRecordAttributes.class),
    CITY(Arrays.asList("Település", "City"), CityRecordAttributes.class),
    CONFERENCE(Arrays.asList("Konferencia", "Conference"), ConferenceRecordAttributes.class),
    COUNTRY(Arrays.asList("Ország", "Country"), CountryRecordAttributes.class),
    DEGREE(Arrays.asList("Tudományos fokozat", "Degree"), DegreeRecordAttributes.class),
    DISCIPLINE(Arrays.asList("Tudományág", "Discipline"), DisciplineRecordAttributes.class),
    FORUM(Arrays.asList("Fórum", "Forum"), ForumRecordAttributes.class),
    FUNDER(Arrays.asList("Támogató", "Funder"), FunderRecordAttributes.class),
    IMPORT_ALIAS(Arrays.asList("Import alias", "Import alias"), ImportAliasRecordAttributes.class),
    INSTITUTE(
            Arrays.asList("Intézmény", "Institute"),
            InstituteRecordAttributes.class,
            Arrays.asList(RecordTypeFlags.HAS_REQUIRED_FIELDS, RecordTypeFlags.HAS_EXTENDABLE_FIELDS)),
    INSTITUTE_TYPE(
            Arrays.asList("Intézménytípus", "Institute Type"),
            InstituteTypeRecordAttributes.class,
            Arrays.asList(RecordTypeFlags.NONE)),

    // Journal creating is tricky
    JOURNAL(Arrays.asList("Folyóirat", "Journal"), JournalRecordAttributes.class),
    KEYWORD(Arrays.asList("Kulcsszó", "Keyword"), KeywordRecordAttributes.class),
    LANGUAGE(Arrays.asList("Nyelv", "Language"), LanguageRecordAttributes.class),

    // Medium type has a reported issue: https://redmine.mt2.dsd.sztaki.hu:18018/issues/3630
    //MEDUIM_TYPE(Arrays.asList("Médiumtípus", "Medium Type"), MediumTypeRecordAttributes.class),
    NAMED_LIST(Arrays.asList("Nevesített lista", "Named list"), NamedListRecordAttributes.class, Arrays.asList(RecordTypeFlags.NONE)),

    // Search in rating is tricky because of the extended journal field
    //RATING(Arrays.asList("Értékelés", "Rating"), RatingRecordAttributes.class),
    RATING_TYPE(Arrays.asList("Értékeléstípus", "Rating type"), RatingTypeRecordAttributes.class),
    PROJECT(Arrays.asList("Projekt", "Project"), ProjectRecordAttributes.class),
    PUBLICATION(Arrays.asList("Közlemény", "Publication"), PublicationRecordAttributes.class),
    PUBLISHER(Arrays.asList("Kiadó", "Publisher"), PublisherRecordAttributes.class),
    SMART_QUERY(Arrays.asList("Keresés", "Smart Query"), SmartQueryRecordAttributes.class),

    // There are no Speciality and State/Province type records present by default.
    //SPECIALITY(Arrays.asList("Témakör", "Speciality"), SpecialityRecordAttributes.class),
    STATE_PROVINCE(Arrays.asList("Megye/Állam", "State/Province"), StateProvinceRecordAttributes.class),
    SUB_TYPE(Arrays.asList("Besorolás", "Sub Type"), PublicationSubTypeAttributes.class),
    REPORT_TEMPLATE(Arrays.asList("Riport Sablon", "Report Template"), ReportTemplateRecordAttributes.class),
    TICKET(Arrays.asList("Cédula", "Ticket"), TicketRecordAttributes.class),
    VARIABLE(Arrays.asList("Rendszerváltozó", "Variable"), VariableRecordAttributes.class),

    //New types
    EMAIL_NOTIFICATIONS_TIMES(
            Arrays.asList("E-mail értesítési idők", "E-mail notification times"),
            EmailNotificationTimesRecordAttributes.class),
    EDUID_PROVIDER(Arrays.asList("EduID szolgáltató", "EduId Provider"), EduIdProviderRecordAttributes.class),
    EXPORT_FORMAT(Arrays.asList("Export sablon", "Export Format"), ExportFormatRecordAttributes.class),
    RATING_IF(Arrays.asList("Értékelés IF", "Rating IF"), RatingIfRecordAttributes.class),
    RATING_MTA(Arrays.asList("Értékelés MTA", "Rating MTA"), RatingMtaRecordAttributes.class),
    RATING_SJR(Arrays.asList("Értékelés SJR", "Rating SJR"), RatingSjrRecordAttributes.class),
    WORKFLOW(Arrays.asList("FolyamatSablon", "Workflow"), WorkflowRecordAttributes.class),
    CRONJOB(Arrays.asList("Időzített feladat", "CronJob"), CronJobRecordAttributes.class),
    IMPORT_FORMAT(Arrays.asList("ImportSablon", "Import Format"), ImportFormatRecordAttributes.class),
    ORGANIZATION_ID_TYPE(Arrays.asList("IntézményAzonosítóTípus", "OrganizationIdType"), OrganizationIdTypeRecordAttributes.class),
    PUBLICATION_SOURCE(Arrays.asList("KözleményForrás", "PublicationSource"), PublicationSourceRecordAttributes.class),
    PUBLICATION_SOURCE_TYPE(Arrays.asList("KözleményForrásTípus", "PublicationSourceType"), PublicationSourceTypeRecordAttributes.class),
    MESSAGE_TEMPLATE(Arrays.asList("Levélsablon", "Message Template"), MessageTemplateRecordAttributes.class),
    MAB_DISCIPLINE(Arrays.asList("MAB tudományág", "MAB Discipline"), MabDisciplineRecordAttributes.class),
    PERODICAL(Arrays.asList("Periodika", "Perodical"), PerodicalRecordAttributes.class),
    REPORT(Arrays.asList("Riport", "Report"), ReportRecordAttributes.class),
    AUTHOR_ID_TYPE(Arrays.asList("SzerzőAzonosítóTípus", "AuthorIdType"), AuthorIdTypeRecordAttributes.class),
    CLASSIFICATION(Arrays.asList("Tudományterület", "Classification"), ClassificationRecordAttributes.class),
    CLASSIFICATION_EXTERNAL(
            Arrays.asList("Tudományterület (külső)", "Classification (external)"),
            ClassificationExternalRecordAttributes.class),
    CLASSIFICATION_TREE(Arrays.asList("Tudományterület hierarchia", "Classification Tree"), ClassificationTreeRecordAttributes.class),
    LOCK_LIST(Arrays.asList("Zárolási lista", "Lock list"), LockListRecordAttributes.class),
    REORG_TYPE(Arrays.asList("Átalakulástípus", "Reorg Type"), ReorgTypeRecordAttributes.class);

    List<String>                            names;
    Class<? extends GeneralTableAttributes> attributes;
    List<RecordTypeFlags>                   recordFlags;

    /**
     * Constructor
     *
     * @param engName
     * @param hungName
     * @param pageToValidate
     */
    private GeneralRecordTypes(final List<String> names, final Class<? extends GeneralTableAttributes> attributes) {
        this.names = names;
        this.attributes = attributes;
        recordFlags = Arrays.asList(RecordTypeFlags.HAS_REQUIRED_FIELDS);
    }

    /**
     * Constructor
     *
     * @param engName
     * @param hungName
     * @param pageToValidate
     */
    private GeneralRecordTypes(final List<String> names, final Class<? extends GeneralTableAttributes> attributes,
            final List<RecordTypeFlags> flags) {
        this.names = names;
        this.attributes = attributes;
        recordFlags = flags;
    }

    /**
     * Returns names
     */
    public List<String> getNames() {
        return names;
    }

    /**
     * Returns attribute flags
     *
     * @return
     */
    public List<RecordTypeFlags> getRecordFlags() {
        return recordFlags;
    }

    /**
     * Returns the page to validate
     *
     * @return
     */
    public Class<? extends GeneralTableAttributes> getAttributes() {
        return attributes;
    }

    /**
     * Return creatable record type
     *
     * @param driver
     * @param myUserDataType
     * @return
     */
    public static GeneralRecordTypes getCreatableRecordTypeWithRequiredFieldsForActualRole() {
        // Get visible record types for this user
        final EnumSet<GeneralRecordTypes> creatableRecordTypes = Utils.getActualRole().getCreatableRecordTypes();

        boolean found = false;
        GeneralRecordTypes chosenRecordType = null;
        while (!found) {
            chosenRecordType = getThisRecordTypeForActualRole(RecordPermissionType.VISIBLE);

            // Check if this is creatable for this user
            if ((creatableRecordTypes.contains(chosenRecordType)) &&
                    (chosenRecordType.getRecordFlags().contains(RecordTypeFlags.HAS_REQUIRED_FIELDS))) {
                found = true;
            }
        }

        return chosenRecordType;
    }

    /**
     * Return creatable record type
     *
     * @param driver
     * @param myUserDataType
     * @return
     */
    public static GeneralRecordTypes getCreatableRecordTypeForActualRole() {
        // Get creatable and visible record type for user
        final EnumSet<GeneralRecordTypes> creatableRecordTypes = Utils.getActualRole().getCreatableRecordTypes();

        boolean found = false;
        GeneralRecordTypes chosenRecordType = null;
        while (!found) {
            chosenRecordType = getThisRecordTypeForActualRole(RecordPermissionType.VISIBLE);

            // Check if this is creatable for this user
            if (creatableRecordTypes.contains(chosenRecordType)) {
                found = true;
            }
        }

        return chosenRecordType;
    }

    /**
     * Return (visible) record type
     *
     * @param driver
     * @param myUserDataType
     * @return
     */
    public static GeneralRecordTypes getVisibleRecordTypeForActualRole() {
        return getThisRecordTypeForActualRole(RecordPermissionType.VISIBLE);
    }

    /**
     * Return editable record type
     *
     * @param driver
     * @param myUserDataType
     * @return
     */
    public static GeneralRecordTypes getEditableRecordTypeForActualRole() {
        return getThisRecordTypeForActualRole(RecordPermissionType.EDITABLE);
    }

    /**
     * Returns the given record type
     *
     * @param driver
     * @param myUserDataType
     * @return
     */
    private static GeneralRecordTypes getThisRecordTypeForActualRole(final RecordPermissionType permissionType) {
        // Get record types for this user
        EnumSet<GeneralRecordTypes> recordTypes = null;
        if (permissionType.equals(RecordPermissionType.EDITABLE)) {
            recordTypes = Utils.getActualRole().getEditableRecordTypes();
        } else {
            recordTypes = Utils.getActualRole().getVisibleRecordTypes();
        }

        System.out.println(recordTypes.size());

        // Check if types size greater than 0
        org.junit.Assume.assumeTrue(recordTypes.size() != 0);

        // Get random record type
        final int randNumber = Utils.randInt(0, recordTypes.size() - 1);
        final Iterator<GeneralRecordTypes> iterator = recordTypes.iterator();
        for (int index = 0; index < randNumber; index++) {
            iterator.next();
        }

        return iterator.next();
    }

    /**
     * Return random column position
     *
     * @param myGeneralRecordType
     * @return
     */
    public static GeneralTableAttributes getRandomAttribute(final GeneralRecordTypes myGeneralRecordType) {
        // Get random column number to edit
        final int columnNumber = selectRandomAttribute(myGeneralRecordType.getAttributes());
        return myGeneralRecordType.getAttributes().getEnumConstants()[columnNumber - 1];
    }

    public static GeneralTableAttributes getRandomUnextendableAttribute(final GeneralRecordTypes myGeneralRecordType) {
        List<GeneralTableAttributes> resList = new ArrayList<>();
        GeneralTableAttributes[] origList = myGeneralRecordType.getAttributes().getEnumConstants();
        for (GeneralTableAttributes i : origList) {
            if (!(i.getAttributeFlags().contains(RecordAttributeFlags.EXTENDABLE))) {
                resList.add(i);
            }
        }
        if (resList.size() > 0) {
            return resList.get(Utils.randInt(0, resList.size() - 1));
        } else {
            throw new RuntimeException("Couldnt get a random attribute because there aren't unextendable attributes in this record type!");
        }
    }

    /**
     * Select a column from this
     */
    private static int selectRandomAttribute(final Class<? extends GeneralTableAttributes> table) {
        return Utils.randInt(1, table.getEnumConstants().length);
    }

    /**
     * Finds the given record type by name
     *
     * TODO: refaktor
     *
     * @param recordTypeName
     * @return
     */
    public static Class<? extends GeneralTableAttributes> findAttributesTypeByName(final String recordTypeName) {
        Class<? extends GeneralTableAttributes> returnValue = null;

        // Search for record attributes in general record types
        returnValue = findAttributesInGeneralRecordTypes(recordTypeName);

        // If we did not find, then we search in hidden record types
        if (returnValue == null) {
            returnValue = findAttributesInHiddenRecordTypes(recordTypeName);
        }

        return returnValue;
    }

    /**
     * Finds the given record type, and returns with the attributes
     *
     * TODO: refaktor - possible clone smell...
     *
     * @param recordTypeName
     * @return
     */
    private static Class<? extends GeneralTableAttributes> findAttributesInGeneralRecordTypes(final String recordTypeName) {
        Class<? extends GeneralTableAttributes> returnValue = null;
        for (final GeneralRecordTypes recordType : GeneralRecordTypes.values()) {
            if (recordType.getNames().contains(recordTypeName)) {
                returnValue = recordType.getAttributes();
            }
        }
        return returnValue;
    }

    /**
     * Finds the given record type, and returns with the attributes
     *
     * TODO: refaktor - possible clone smell...
     *
     * @param recordTypeName
     * @return
     */
    private static Class<? extends GeneralTableAttributes> findAttributesInHiddenRecordTypes(final String recordTypeName) {
        Class<? extends GeneralTableAttributes> returnValue = null;
        for (final HiddenRecordTypes recordType : HiddenRecordTypes.values()) {
            if (recordType.getNames().contains(recordTypeName)) {
                returnValue = recordType.getAttributes();
            }
        }
        return returnValue;
    }

    /**
     * returns not disabled attributes
     *
     * @return
     */
    public static List<GeneralTableAttributes> getNotDisabledAttributes(final Class<? extends GeneralTableAttributes> attributes) {
        final List<GeneralTableAttributes> notDisabledAttributes = new ArrayList<>();
        for (final GeneralTableAttributes attribute : attributes.getEnumConstants()) {
            if (!attribute.getAttributeFlags().contains(RecordAttributeFlags.DISABLED)) {
                notDisabledAttributes.add(attribute);
            }
        }
        return notDisabledAttributes;
    }

    /**
     * Select an attribute which is not disabled
     */
    public static GeneralTableAttributes selectRandomNotDisabledAttribute(final GeneralRecordTypes myGeneralRecordType) {
        final List<GeneralTableAttributes> notDisabledAttributes = getNotDisabledAttributes(myGeneralRecordType.getAttributes());
        return notDisabledAttributes.get(Utils.randInt(0, notDisabledAttributes.size() - 1));
    }

    /**
     * returns not extendable attributes
     *
     * @return
     */
    public static List<GeneralTableAttributes> getNotExtendableAttributes(final Class<? extends GeneralTableAttributes> attributes) {
        final List<GeneralTableAttributes> notExtendableAttributes = new ArrayList<>();
        for (final GeneralTableAttributes attribute : attributes.getEnumConstants()) {
            if (!attribute.getAttributeFlags().contains(RecordAttributeFlags.EXTENDABLE)) {
                notExtendableAttributes.add(attribute);
            }
        }
        return notExtendableAttributes;
    }

    /**
     * Select an attribute which is not extendable
     */
    public static GeneralTableAttributes selectRandomNotExtendableAttribute(final GeneralRecordTypes myGeneralRecordType) {
        final List<GeneralTableAttributes> notExtendableAttributes = getNotExtendableAttributes(myGeneralRecordType.getAttributes());
        return notExtendableAttributes.get(Utils.randInt(0, notExtendableAttributes.size() - 1));
    }

    /**
     * returns not disabled attributes
     *
     * @return
     */
    public static List<GeneralTableAttributes> getSearchableAttributes(final Class<? extends GeneralTableAttributes> attributes) {
        final List<GeneralTableAttributes> searchableAttributes = new ArrayList<>();
        for (final GeneralTableAttributes attribute : attributes.getEnumConstants()) {
            if (!attribute.getAttributeFlags().contains(RecordAttributeFlags.NOT_SEARCHABLE)) {
                searchableAttributes.add(attribute);
            }
        }
        return searchableAttributes;
    }

    /**
     * Select an attribute which is not extendable
     */
    public static GeneralTableAttributes selectRandomSearchableAttribute(final GeneralRecordTypes myGeneralRecordType) {
        final List<GeneralTableAttributes> searchableAttributes = getSearchableAttributes(myGeneralRecordType.getAttributes());
        return searchableAttributes.get(Utils.randInt(0, searchableAttributes.size() - 1));
    }
}
