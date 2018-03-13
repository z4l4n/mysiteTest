package com.frontendart.testsuites.records;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import com.frontendart.categories.AddRecordsSuite;
import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.JunitTestClass;
import com.frontendart.locators.records.attributes.general.AchievementPropertyRecordAttributes;
import com.frontendart.locators.records.attributes.general.AdminRecordAttributes;
import com.frontendart.locators.records.attributes.general.AppearanceRecordAttributes;
import com.frontendart.locators.records.attributes.general.AuthorIdTypeRecordAttributes;
import com.frontendart.locators.records.attributes.general.AuthorRecordAttributes;
import com.frontendart.locators.records.attributes.general.AuthorTypeRecordAttributes;
import com.frontendart.locators.records.attributes.general.BookSeriesRecordAttributes;
import com.frontendart.locators.records.attributes.general.CategoryRecordAttributes;
import com.frontendart.locators.records.attributes.general.CitationRecordAttributes;
import com.frontendart.locators.records.attributes.general.CityRecordAttributes;
import com.frontendart.locators.records.attributes.general.ClassificationExternalRecordAttributes;
import com.frontendart.locators.records.attributes.general.ClassificationRecordAttributes;
import com.frontendart.locators.records.attributes.general.ClassificationTreeRecordAttributes;
import com.frontendart.locators.records.attributes.general.ConferenceRecordAttributes;
import com.frontendart.locators.records.attributes.general.CountryRecordAttributes;
import com.frontendart.locators.records.attributes.general.CronJobRecordAttributes;
import com.frontendart.locators.records.attributes.general.DegreeRecordAttributes;
import com.frontendart.locators.records.attributes.general.DisciplineRecordAttributes;
import com.frontendart.locators.records.attributes.general.EduIdProviderRecordAttributes;
import com.frontendart.locators.records.attributes.general.EmailNotificationTimesRecordAttributes;
import com.frontendart.locators.records.attributes.general.ExportFormatRecordAttributes;
import com.frontendart.locators.records.attributes.general.ForumRecordAttributes;
import com.frontendart.locators.records.attributes.general.FunderRecordAttributes;
import com.frontendart.locators.records.attributes.general.GeneralRecordTypes;
import com.frontendart.locators.records.attributes.general.GeneralTableAttributes;
import com.frontendart.locators.records.attributes.general.ImportAliasRecordAttributes;
import com.frontendart.locators.records.attributes.general.ImportFormatRecordAttributes;
import com.frontendart.locators.records.attributes.general.InstituteRecordAttributes;
import com.frontendart.locators.records.attributes.general.InstituteTypeRecordAttributes;
import com.frontendart.locators.records.attributes.general.JournalRecordAttributes;
import com.frontendart.locators.records.attributes.general.KeywordRecordAttributes;
import com.frontendart.locators.records.attributes.general.LanguageRecordAttributes;
import com.frontendart.locators.records.attributes.general.LockListRecordAttributes;
import com.frontendart.locators.records.attributes.general.MabDisciplineRecordAttributes;
import com.frontendart.locators.records.attributes.general.MessageTemplateRecordAttributes;
import com.frontendart.locators.records.attributes.general.NamedListRecordAttributes;
import com.frontendart.locators.records.attributes.general.OrganizationIdTypeRecordAttributes;
import com.frontendart.locators.records.attributes.general.PerodicalRecordAttributes;
import com.frontendart.locators.records.attributes.general.ProjectRecordAttributes;
import com.frontendart.locators.records.attributes.general.PublicationSourceRecordAttributes;
import com.frontendart.locators.records.attributes.general.PublicationSourceTypeRecordAttributes;
import com.frontendart.locators.records.attributes.general.PublicationSubTypeAttributes;
import com.frontendart.locators.records.attributes.general.PublisherRecordAttributes;
import com.frontendart.locators.records.attributes.general.RatingIfRecordAttributes;
import com.frontendart.locators.records.attributes.general.RatingMtaRecordAttributes;
import com.frontendart.locators.records.attributes.general.RatingSjrRecordAttributes;
import com.frontendart.locators.records.attributes.general.RatingTypeRecordAttributes;
import com.frontendart.locators.records.attributes.general.ReorgTypeRecordAttributes;
import com.frontendart.locators.records.attributes.general.ReportRecordAttributes;
import com.frontendart.locators.records.attributes.general.SmartQueryRecordAttributes;
import com.frontendart.locators.records.attributes.general.StateProvinceRecordAttributes;
import com.frontendart.locators.records.attributes.general.VariableRecordAttributes;
import com.frontendart.locators.records.attributes.general.WorkflowRecordAttributes;
import com.frontendart.managers.main.rightpanel.crud.CreateOthersManager;

@Category({ AddRecordsSuite.class, CENTRAL_ADMIN_Suite.class })
public class AddRecordsTest extends JunitTestClass {

	// This test class should only run with central admin - meaning that it will
	// only run once
	@BeforeClass
	public static void beforeMethod() {

		org.junit.Assume.assumeTrue(Utils.actualRoleIsCentralAdmin());

	}

	/**
	 * Create new admin record
	 */
	@Test
	public final void testCreateNewAdminRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(AdminRecordAttributes.USERNAME, AdminRecordAttributes.FAMILY_NAME,
				AdminRecordAttributes.GIVEN_NAME, AdminRecordAttributes.ADMIN_ROLES, AdminRecordAttributes.EMAIL));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.ADMIN, attributes,
				AdminRecordAttributes.USERNAME);
	}

	/**
	 * Create new achievement property record
	 */
	@Test
	public final void testCreateNewAchievementPropertyRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(AchievementPropertyRecordAttributes.NAME));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.ACHIEVEMENT_PROPERTY, attributes,
				AchievementPropertyRecordAttributes.NAME);
	}

	/**
	 * Create new reorg type record
	 */
	@Test
	public final void testCreateNewReorgTypeRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(ReorgTypeRecordAttributes.CODE, ReorgTypeRecordAttributes.NAME,
				ReorgTypeRecordAttributes.NAME_ENG));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.REORG_TYPE, attributes,
				ReorgTypeRecordAttributes.NAME);
	}

	/**
	 * Create new sub type record
	 */
	@Test
	public final void testCreateNewSubTypeRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(PublicationSubTypeAttributes.CODE, PublicationSubTypeAttributes.NAME,
				PublicationSubTypeAttributes.NAME_ENG, PublicationSubTypeAttributes.TYPE_CODE));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.SUB_TYPE, attributes,
				PublicationSubTypeAttributes.NAME);
	}

	/**
	 * Create new email notification times record
	 */
	@Test
	public final void testCreateNewEmailNotificationTimesRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(EmailNotificationTimesRecordAttributes.NAME));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.EMAIL_NOTIFICATIONS_TIMES, attributes,
				EmailNotificationTimesRecordAttributes.NAME);
	}

	/**
	 * Create new eduid provider record
	 */
	@Test
	public final void testCreateNewEduIdProviderRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(EduIdProviderRecordAttributes.NAME));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.EDUID_PROVIDER, attributes,
				EduIdProviderRecordAttributes.NAME);
	}

	/**
	 * Create new rating if record
	 */
	@Test
	public final void testCreateNewRatingIfRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(RatingIfRecordAttributes.PERIODICAL, RatingIfRecordAttributes.TYPE,
				RatingIfRecordAttributes.COMMENT, RatingIfRecordAttributes.YEAR));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.RATING_IF, attributes,
				RatingIfRecordAttributes.COMMENT);
	}

	/**
	 * Create new rating mta record
	 */
	@Test
	public final void testCreateNewRatingMtaRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(RatingMtaRecordAttributes.PERIODICAL, RatingMtaRecordAttributes.TYPE,
				RatingMtaRecordAttributes.COMMENT, RatingMtaRecordAttributes.VALUE));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.RATING_MTA, attributes,
				RatingMtaRecordAttributes.COMMENT);
	}

	/**
	 * Create new rating sjr record
	 */
	@Test
	public final void testCreateNewRatingSjrRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(RatingSjrRecordAttributes.PERIODICAL, RatingSjrRecordAttributes.TYPE,
				RatingSjrRecordAttributes.COMMENT, RatingSjrRecordAttributes.YEAR, RatingSjrRecordAttributes.RANKING,
				RatingSjrRecordAttributes.SUBJECT_CATEGORY));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.RATING_SJR, attributes,
				RatingSjrRecordAttributes.COMMENT);
	}

	/**
	 * Create new rating type record
	 */
	@Test
	public final void testCreateNewRatingTypeRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(RatingTypeRecordAttributes.CLASS, RatingTypeRecordAttributes.NAME,
				RatingTypeRecordAttributes.NAME_ENG));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.RATING_TYPE, attributes,
				RatingTypeRecordAttributes.NAME);
	}

	/**
	 * Create new export format record
	 */
	@Test
	public final void testCreateNewExportFormatRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(ExportFormatRecordAttributes.NAME, ExportFormatRecordAttributes.TYPE));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.EXPORT_FORMAT, attributes,
				ExportFormatRecordAttributes.NAME);
	}

	/**
	 * Create new workflow record
	 */
	@Test
	public final void testCreateNewWorkflowRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(WorkflowRecordAttributes.NAME, WorkflowRecordAttributes.NAME_ENG,
				WorkflowRecordAttributes.RECORD_TYPE));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.WORKFLOW, attributes,
				WorkflowRecordAttributes.NAME);
	}

	/**
	 * Create new journal record
	 */
	@Test
	public final void testCreateNewJournalRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(JournalRecordAttributes.LONG_NAME));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.JOURNAL, attributes,
				JournalRecordAttributes.LONG_NAME);
	}

	/**
	 * Create new forum record
	 */
	@Test
	public final void testCreateNewForumRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(ForumRecordAttributes.NAME, ForumRecordAttributes.NAME_ENG,
				ForumRecordAttributes.AVAILABILITY));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.FORUM, attributes, ForumRecordAttributes.NAME);
	}

	/**
	 * Create new citation record
	 */
	@Test
	public final void testCreateNewCitationRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(CitationRecordAttributes.PUBLICATION, CitationRecordAttributes.RELATED,
				CitationRecordAttributes.COMMENT));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.CITATION, attributes,
				CitationRecordAttributes.COMMENT);
	}

//	/**
//	 * Create new cron job record
//	 */
//	@Test
//	public final void testCreateNewCronJobRecord() {
//
//		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
//		attributes.addAll(EnumSet.of(CronJobRecordAttributes.COMMENT));
//
//		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.CRONJOB, attributes,
//				CronJobRecordAttributes.COMMENT);
//	}

	/**
	 * Create new import alias record
	 */
	@Test
	public final void testCreateNewImportAliasRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(ImportAliasRecordAttributes.ALIAS, ImportAliasRecordAttributes.TYPE,
				ImportAliasRecordAttributes.ALIAS_MAPPED_ID));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.IMPORT_ALIAS, attributes,
				ImportAliasRecordAttributes.ALIAS);
	}

	/**
	 * Create new import format record
	 */
	@Test
	public final void testCreateNewImportFormatRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(ImportFormatRecordAttributes.NAME, ImportFormatRecordAttributes.TYPE));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.IMPORT_FORMAT, attributes,
				ImportFormatRecordAttributes.NAME);
	}

	/**
	 * Create new institute record
	 */
	@Test
	public final void testCreateNewInstituteRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(InstituteRecordAttributes.NAME, InstituteRecordAttributes.ABBREVIATION,
				InstituteRecordAttributes.NAME_ENG, InstituteRecordAttributes.ABBREVIATION_ENG,
				InstituteRecordAttributes.INSTITUTE_TYPE, InstituteRecordAttributes.ONLINE_REGISTRATION));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.INSTITUTE, attributes,
				InstituteRecordAttributes.NAME);
	}

	/**
	 * Create new organization id type record
	 */
	@Test
	public final void testCreateNewOrganizationIdTypeRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(OrganizationIdTypeRecordAttributes.NAME));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.ORGANIZATION_ID_TYPE, attributes,
				OrganizationIdTypeRecordAttributes.NAME);
	}

	/**
	 * Create new institute type record
	 */
	@Test
	public final void testCreateNewInstituteTypeRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(InstituteTypeRecordAttributes.CODE, InstituteTypeRecordAttributes.NAME,
				InstituteTypeRecordAttributes.NAME_ENG));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.INSTITUTE_TYPE, attributes,
				InstituteTypeRecordAttributes.NAME);
	}

	/**
	 * Create new category record
	 */
	@Test
	public final void testCreateNewCategoryRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(CategoryRecordAttributes.NAME, CategoryRecordAttributes.NAME_ENG));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.CATEGORY, attributes,
				CategoryRecordAttributes.NAME);
	}
	
	/**
	 * Create new smart query record
	 */
	@Test
	public final void testCreateNewSmartQueryRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(SmartQueryRecordAttributes.NAME, SmartQueryRecordAttributes.RESULT_TYPE));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.SMART_QUERY, attributes,
				SmartQueryRecordAttributes.NAME);
	}
	
	/**
	 * Create new publisher record
	 */
	@Test
	public final void testCreateNewPublisherRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(PublisherRecordAttributes.NAME, PublisherRecordAttributes.LOCATION));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.PUBLISHER, attributes,
				PublisherRecordAttributes.NAME);
	}
	
	/**
	 * Create new conference record
	 */
	@Test
	public final void testCreateNewConferenceRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(ConferenceRecordAttributes.NAME, ConferenceRecordAttributes.LOCATION, ConferenceRecordAttributes.START_DATE));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.CONFERENCE, attributes,
				ConferenceRecordAttributes.NAME);
	}
	
	/**
	 * Create new book series record
	 */
	@Test
	public final void testCreateNewBookSeriesRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(BookSeriesRecordAttributes.TITLE));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.BOOK_SERIES, attributes,
				BookSeriesRecordAttributes.TITLE);
	}
	
	/**
	 * Create new publication source record
	 */
	@Test
	public final void testCreateNewPublicationSourceRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(PublicationSourceRecordAttributes.NAME, PublicationSourceRecordAttributes.SUBTYPE, PublicationSourceRecordAttributes.TYPE));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.PUBLICATION_SOURCE, attributes,
				PublicationSourceRecordAttributes.NAME);
	}
	
	/**
	 * Create new publication source type record
	 */
	@Test
	public final void testCreateNewPublicationSourceTypeRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(PublicationSourceTypeRecordAttributes.CODE, PublicationSourceTypeRecordAttributes.NAME, PublicationSourceTypeRecordAttributes.NAME_ENG));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.PUBLICATION_SOURCE_TYPE, attributes,
				PublicationSourceTypeRecordAttributes.NAME);
	}
	
	/**
	 * Create new keyword record
	 */
	@Test
	public final void testCreateNewKeywordRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(KeywordRecordAttributes.KEYWORD, KeywordRecordAttributes.COMMENT));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.KEYWORD, attributes,
				KeywordRecordAttributes.COMMENT);
	}
	
	/**
	 * Create new message template record
	 */
	@Test
	public final void testCreateNewMessageTemplateRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(MessageTemplateRecordAttributes.NAME, MessageTemplateRecordAttributes.SHORT_NAME));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.MESSAGE_TEMPLATE, attributes,
				MessageTemplateRecordAttributes.NAME);
	}
	
	/**
	 * Create new mab discipline record
	 */
	@Test
	public final void testCreateNewMabDisciplineRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(MabDisciplineRecordAttributes.GROUP_NAME, MabDisciplineRecordAttributes.GROUP_NAME_ENG, MabDisciplineRecordAttributes.NAME, MabDisciplineRecordAttributes.NAME_ENG));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.MAB_DISCIPLINE, attributes,
				MabDisciplineRecordAttributes.NAME);
	}
	
	/**
	 * Create new appearance record
	 */
	@Test
	public final void testCreateNewAppearanceRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(AppearanceRecordAttributes.NAME, AppearanceRecordAttributes.NAME_ENG));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.APPEARANCE, attributes,
				AppearanceRecordAttributes.NAME);
	}
	
	/**
	 * Create new state/province record
	 */
	@Test
	public final void testCreateNewStateProvinceRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(StateProvinceRecordAttributes.NAME));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.STATE_PROVINCE, attributes,
				StateProvinceRecordAttributes.NAME);
	}
	
	/**
	 * Create new named list record
	 */
	@Test
	public final void testCreateNewNamedListRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(NamedListRecordAttributes.NAME));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.NAMED_LIST, attributes,
				NamedListRecordAttributes.NAME);
	}
	
	/**
	 * Create new language record
	 */
	@Test
	public final void testCreateNewLanguageRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(LanguageRecordAttributes.NAME, LanguageRecordAttributes.NAME_ENG, LanguageRecordAttributes.THREE_CHAR_CODE));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.LANGUAGE, attributes,
				LanguageRecordAttributes.NAME);
	}
	
	/**
	 * Create new country record
	 */
	@Test
	public final void testCreateNewCountryRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(CountryRecordAttributes.NAME));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.COUNTRY, attributes,
				CountryRecordAttributes.NAME);
	}
	
	/**
	 * Create new perodical record
	 */
	@Test
	public final void testCreateNewPerodicalRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(PerodicalRecordAttributes.LONG_NAME));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.PERODICAL, attributes,
				PerodicalRecordAttributes.LONG_NAME);
	}
	
	/**
	 * Create new project record
	 */
	@Test
	public final void testCreateNewProjectRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(ProjectRecordAttributes.NUMBER));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.PROJECT, attributes,
				ProjectRecordAttributes.NUMBER);
	}
	
	/**
	 * Create new variable record
	 */
	@Test
	public final void testCreateNewVariableRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(VariableRecordAttributes.NAME));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.VARIABLE, attributes,
				VariableRecordAttributes.NAME);
	}
	
	/**
	 * Create new report record
	 */
	@Test
	public final void testCreateNewReportRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(ReportRecordAttributes.REPORT_REQUEST, ReportRecordAttributes.TITLE));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.REPORT, attributes,
				ReportRecordAttributes.TITLE);
	}
	
	/**
	 * Create new author id type record
	 */
	@Test
	public final void testCreateNewAuthorIdTypeRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(AuthorIdTypeRecordAttributes.NAME));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.AUTHOR_ID_TYPE, attributes,
				AuthorIdTypeRecordAttributes.NAME);
	}
	
	/**
	 * Create new author type record
	 */
	@Test
	public final void testCreateNewAuthorTypeRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(AuthorTypeRecordAttributes.CODE, AuthorTypeRecordAttributes.NAME, AuthorTypeRecordAttributes.NAME_ENG));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.AUTHOR_TYPE, attributes,
				AuthorTypeRecordAttributes.NAME);
	}
	
	/**
	 * Create new funder record
	 */
	@Test
	public final void testCreateNewFunderRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(FunderRecordAttributes.NAME, FunderRecordAttributes.NAME_ENG, FunderRecordAttributes.ABBREVIATION, FunderRecordAttributes.ABBREVIATION_ENG));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.FUNDER, attributes,
				FunderRecordAttributes.NAME);
	}
	
	/**
	 * Create new funder record
	 */
	@Test
	public final void testCreateNewCityRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(CityRecordAttributes.NAME, CityRecordAttributes.COUNTRY));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.CITY, attributes,
				CityRecordAttributes.NAME);
	}
	
	/**
	 * Create new discipline record
	 */
	@Test
	public final void testCreateNewDisciplineRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(DisciplineRecordAttributes.NAME, DisciplineRecordAttributes.NAME_ENG));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.DISCIPLINE, attributes,
				DisciplineRecordAttributes.NAME);
	}
	
	/**
	 * Create new degree record
	 */
	@Test
	public final void testCreateNewDegreeRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(DegreeRecordAttributes.NAME, DegreeRecordAttributes.NAME_ENG));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.DEGREE, attributes,
				DegreeRecordAttributes.NAME);
	}
	
	/**
	 * Create new classification record
	 */
	@Test
	public final void testCreateNewClassificationRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(ClassificationRecordAttributes.CODE, ClassificationRecordAttributes.NAME));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.CLASSIFICATION, attributes,
				ClassificationRecordAttributes.NAME);
	}
	
	/**
	 * Create new external classification record
	 */
	@Test
	public final void testCreateNewExternalClassificationRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(ClassificationExternalRecordAttributes.TREE, ClassificationExternalRecordAttributes.CODE, ClassificationExternalRecordAttributes.NAME));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.CLASSIFICATION_EXTERNAL, attributes,
				ClassificationExternalRecordAttributes.NAME);
	}
	
	/**
	 * Create new classification tree record
	 */
	@Test
	public final void testCreateNewClassificationTreeRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(ClassificationTreeRecordAttributes.NAME, ClassificationTreeRecordAttributes.NAME_ENG));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.CLASSIFICATION_TREE, attributes,
				ClassificationTreeRecordAttributes.NAME);
	}
	
	/**
	 * Create new lock list record
	 */
	@Test
	public final void testCreateNewLockListRecord() {

		final List<GeneralTableAttributes> attributes = new ArrayList<GeneralTableAttributes>();
		attributes.addAll(EnumSet.of(LockListRecordAttributes.NAME));

		CreateOthersManager.createRecordForThisType(GeneralRecordTypes.LOCK_LIST, attributes,
				LockListRecordAttributes.NAME);
	}

}
