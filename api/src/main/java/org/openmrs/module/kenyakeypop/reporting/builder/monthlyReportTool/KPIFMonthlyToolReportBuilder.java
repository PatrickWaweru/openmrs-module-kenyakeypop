/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * <p>
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.kenyakeypop.reporting.builder.monthlyReportTool;

import org.openmrs.module.kenyacore.report.ReportDescriptor;
import org.openmrs.module.kenyacore.report.ReportUtils;
import org.openmrs.module.kenyacore.report.builder.AbstractReportBuilder;
import org.openmrs.module.kenyacore.report.builder.Builds;
import org.openmrs.module.kenyaemr.reporting.ColumnParameters;
import org.openmrs.module.kenyaemr.reporting.EmrReportingUtils;
import org.openmrs.module.kenyakeypop.reporting.library.ETLReports.monthlyReport.MonthlyReportIndicatorLibrary;
import org.openmrs.module.kenyakeypop.reporting.library.shared.common.CommonKpDimensionLibrary;
import org.openmrs.module.reporting.dataset.definition.CohortIndicatorDataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.DataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Report builder for KPIF Monthly report
 */
@Component
@Builds({ "kenyaemr.kenyakeypop.kenyakeypop.report.monthlyReport" })
public class KPIFMonthlyToolReportBuilder extends AbstractReportBuilder {
	
	@Autowired
	private CommonKpDimensionLibrary commonDimensions;
	
	@Autowired
	private MonthlyReportIndicatorLibrary monthlyReportIndicator;
	
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	
	ColumnParameters below_15 = new ColumnParameters(null, "<15", "age=<15");
	
	ColumnParameters kp15_to_19 = new ColumnParameters(null, "15-19", "age=15-19");
	
	ColumnParameters kp20_to_24 = new ColumnParameters(null, "20-24", "age=20-24");
	
	ColumnParameters kp25_and_above = new ColumnParameters(null, "25+", "age=25+");
	
	ColumnParameters colTotal = new ColumnParameters(null, "Total", "");
	
	List<ColumnParameters> kpAgeDisaggregation = Arrays.asList(below_15, kp15_to_19, kp20_to_24, kp25_and_above, colTotal);
	
	@Override
	protected List<Parameter> getParameters(ReportDescriptor reportDescriptor) {
		return Arrays.asList(new Parameter("startDate", "Start Date", Date.class), new Parameter("endDate", "End Date",
		        Date.class), new Parameter("dateBasedReporting", "", String.class));
	}
	
	@Override
	protected List<Mapped<DataSetDefinition>> buildDataSets(ReportDescriptor reportDescriptor,
	        ReportDefinition reportDefinition) {
		return Arrays.asList(ReportUtils.map(kpContactDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpEverEnrolledDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpContactNewDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpContactHCWDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpNetEnrolledDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpKnownPositiveDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpNewlyEnrolledDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpHtsTestedNegDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpEnrolledAndTestedHIVDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpEnrolledTestedPosDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpScreenedForSTIDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpAssistedSelfTestedDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpUnAssistedSelfTestedDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpHtsTstSelfConfirmedPositive(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpScreenedPositiveForSTIDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpStartedSTITxDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpScreenedForGbvDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpExperiencedGbvDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpReceivedGbvLegalSupportDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpNewOnARTDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpCurrOnARTDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpHIVTXRttDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpEligibleForRetestDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpHTSTstEligibleRetestedDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpRetestedHIVPositiveDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpOfferedPNSDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpAcceptedPNSDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpElicitedPNSDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpPNSKnownPositiveAtEntryDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpPNSTestedPositiveDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpPNSTestedNegativeDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpEnrolledInARTSupportGroupDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpReceivedGbvClinicalCareDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpHIVSuppressedVlDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kplhivWithVlResultDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kplhivSuppressedVlArtOtherPEPFARSiteDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kplhivVlResultArtOtherPEPFARSiteDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kplhivSuppressedVlArtNonPEPFARSiteDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kplhivVlResultArtNonPEPFARSiteDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpReferredAndInitiatedPrEPPepfarDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpReferredAndInitiatedPrEPNonPepfarDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kplhivInitiatedARTOtherPEPFARDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kplhivInitiatedARTNonPEPFARDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kplhivCurrentOnARTOtherPEPFARDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kplhivCurrentOnARTNonPEPFARDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpCurrDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpLHIVCurrDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kplhivLTFURecentlyDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpEverPosDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(txEverDiceDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpTxEverVerifyPEPFARDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpTxEverVerifyNonPEPFARDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpTxPvlsEligibleDiceDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpTxPvlsEligibleDoneDiceDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpTxPvlsEligibleVerifyPEPFARDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpTxPvlsEligibleDoneVerifyPEPFARDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpTxPvlsEligibleVerifyNonPEPFARDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpTxPvlsEligibleDoneVerifyNonPEPFARDataset(), "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(kpOnMultiMonthARTDataset(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	//1. CONTACT_ALL - r8QR7Iqit3z
	protected DataSetDefinition kpContactDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("1");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		
		EmrReportingUtils.addRow(cohortDsd, "CONTACT_ALL_FSW", "All KP Contact",
		    ReportUtils.map(monthlyReportIndicator.contactAll("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "CONTACT_ALL_MSM", "All KP Contact",
		    ReportUtils.map(monthlyReportIndicator.contactAll("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "CONTACT_ALL_MSW", "All KP Contact",
		    ReportUtils.map(monthlyReportIndicator.contactAll("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "CONTACT_ALL_PWUD", "All KP Contact",
		    ReportUtils.map(monthlyReportIndicator.contactAll("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "CONTACT_ALL_PWID", "All KP Contact",
		    ReportUtils.map(monthlyReportIndicator.contactAll("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "CONTACT_ALL_Transman", "All KP Contact",
		    ReportUtils.map(monthlyReportIndicator.contactAll("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "CONTACT_ALL_Transwoman", "All KP Contact",
		    ReportUtils.map(monthlyReportIndicator.contactAll("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//2. KP_EVER_ENROL - azaGW41sWgz
	protected DataSetDefinition kpEverEnrolledDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("2");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		
		EmrReportingUtils.addRow(cohortDsd, "KP_EVER_ENROLLED_FSW", "Ever enrolled",
		    ReportUtils.map(monthlyReportIndicator.everEnroll("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KP_EVER_ENROLLED_MSM", "Ever enrolled",
		    ReportUtils.map(monthlyReportIndicator.everEnroll("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KP_EVER_ENROLLED_MSW", "Ever enrolled",
		    ReportUtils.map(monthlyReportIndicator.everEnroll("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KP_EVER_ENROLLED_PWUD", "Ever enrolled",
		    ReportUtils.map(monthlyReportIndicator.everEnroll("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KP_EVER_ENROLLED_PWID", "Ever enrolled",
		    ReportUtils.map(monthlyReportIndicator.everEnroll("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KP_EVER_ENROLLED_Transman", "Ever enrolled",
		    ReportUtils.map(monthlyReportIndicator.everEnroll("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KP_EVER_ENROLLED_Transwoman", "Ever enrolled",
		    ReportUtils.map(monthlyReportIndicator.everEnroll("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//3. CONTACT_NEW - nFg8SCUal7w
	protected DataSetDefinition kpContactNewDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("3");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "KP_CONTACT_NEW_FSW", "New KP Contact",
		    ReportUtils.map(monthlyReportIndicator.contactNew("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KP_CONTACT_NEW_MSM", "New KP Contact",
		    ReportUtils.map(monthlyReportIndicator.contactNew("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KP_CONTACT_NEW_MSW", "New KP Contact",
		    ReportUtils.map(monthlyReportIndicator.contactNew("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KP_CONTACT_NEW_PWUD", "New KP Contact",
		    ReportUtils.map(monthlyReportIndicator.contactNew("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KP_CONTACT_NEW_PWID", "New KP Contact",
		    ReportUtils.map(monthlyReportIndicator.contactNew("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KP_CONTACT_NEW_Transman", "New KP Contact",
		    ReportUtils.map(monthlyReportIndicator.contactNew("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KP_CONTACT_NEW_Transwoman", "New KP Contact",
		    ReportUtils.map(monthlyReportIndicator.contactNew("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//4. CONTACT_HCW - WnS2CYAnhhg
	protected DataSetDefinition kpContactHCWDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("4");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "CONTACT_HCW_FSW", "Had contact with a health care worker",
		    ReportUtils.map(monthlyReportIndicator.contactHCW("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "CONTACT_HCW_MSM", "Had contact with a health care worker",
		    ReportUtils.map(monthlyReportIndicator.contactHCW("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "CONTACT_HCW_MSW", "Had contact with a health care worker",
		    ReportUtils.map(monthlyReportIndicator.contactHCW("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "CONTACT_HCW_PWUD", "Had contact with a health care worker",
		    ReportUtils.map(monthlyReportIndicator.contactHCW("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "CONTACT_HCW_PWID", "Had contact with a health care worker",
		    ReportUtils.map(monthlyReportIndicator.contactHCW("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "CONTACT_HCW_Transman", "Had contact with a health care worker",
		    ReportUtils.map(monthlyReportIndicator.contactHCW("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "CONTACT_HCW_Transwoman", "Had contact with a health care worker",
		    ReportUtils.map(monthlyReportIndicator.contactHCW("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//5. KP_NET_ENROLLED n35ZQZJ9qYj
	
	protected DataSetDefinition kpNetEnrolledDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("5");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "KP_NET_ENROLLED_FSW", "Net Enrolled KP",
		    ReportUtils.map(monthlyReportIndicator.netEnroll("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KP_NET_ENROLLED_MSM", "Net Enrolled KP",
		    ReportUtils.map(monthlyReportIndicator.netEnroll("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KP_NET_ENROLLED_MSW", "Net Enrolled KP",
		    ReportUtils.map(monthlyReportIndicator.netEnroll("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KP_NET_ENROLLED_PWUD", "Net Enrolled KP",
		    ReportUtils.map(monthlyReportIndicator.netEnroll("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KP_NET_ENROLLED_PWID", "Net Enrolled KP",
		    ReportUtils.map(monthlyReportIndicator.netEnroll("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KP_NET_ENROLLED_Transman", "Net Enrolled KP",
		    ReportUtils.map(monthlyReportIndicator.netEnroll("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KP_NET_ENROLLED_Transwoman", "Net Enrolled KP",
		    ReportUtils.map(monthlyReportIndicator.netEnroll("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//6. ENROL_KNOWN POSITIVE - pbIycq1Q1aR
	protected DataSetDefinition kpKnownPositiveDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("6");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "ENROL_KNOWN POSITIVE_FSW", "Known positive at enrolment",
		    ReportUtils.map(monthlyReportIndicator.kpKnownPositiveEnrolled("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "ENROL_KNOWN POSITIVE_MSM", "Known positive at enrolment",
		    ReportUtils.map(monthlyReportIndicator.kpKnownPositiveEnrolled("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "ENROL_KNOWN POSITIVE_MSW", "Known positive at enrolment",
		    ReportUtils.map(monthlyReportIndicator.kpKnownPositiveEnrolled("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "ENROL_KNOWN POSITIVE_PWUD", "Known positive at enrolment",
		    ReportUtils.map(monthlyReportIndicator.kpKnownPositiveEnrolled("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "ENROL_KNOWN POSITIVE_PWID", "Known positive at enrolment",
		    ReportUtils.map(monthlyReportIndicator.kpKnownPositiveEnrolled("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "ENROL_KNOWN POSITIVE_Transman", "Known positive at enrolment",
		    ReportUtils.map(monthlyReportIndicator.kpKnownPositiveEnrolled("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "ENROL_KNOWN POSITIVE_Transwoman", "Known positive at enrolment",
		    ReportUtils.map(monthlyReportIndicator.kpKnownPositiveEnrolled("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//7. ENROL_NEW - VhJ7miYpzzZ
	protected DataSetDefinition kpNewlyEnrolledDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("7");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "ENROL_NEW_FSW", "Newly Enrolled",
		    ReportUtils.map(monthlyReportIndicator.enrollNew("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "ENROL_NEW_MSM", "Newly Enrolled",
		    ReportUtils.map(monthlyReportIndicator.enrollNew("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "ENROL_NEW_MSW", "Newly Enrolled",
		    ReportUtils.map(monthlyReportIndicator.enrollNew("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "ENROL_NEW_PWUD", "Newly Enrolled",
		    ReportUtils.map(monthlyReportIndicator.enrollNew("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "ENROL_NEW_PWID", "Newly Enrolled",
		    ReportUtils.map(monthlyReportIndicator.enrollNew("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "ENROL_NEW_Transman", "Newly Enrolled",
		    ReportUtils.map(monthlyReportIndicator.enrollNew("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "ENROL_NEW_Transwoman", "Newly Enrolled",
		    ReportUtils.map(monthlyReportIndicator.enrollNew("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//8. HTS_TST_NEG - K4NPVo3Ee1E
	protected DataSetDefinition kpHtsTestedNegDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("8");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "HTS_TST_NEG_FSW", "Tested HIV -ve",
		    ReportUtils.map(monthlyReportIndicator.htsTestedNegative("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "HTS_TST_NEG_MSM", "Tested HIV -ve",
		    ReportUtils.map(monthlyReportIndicator.htsTestedNegative("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "HTS_TST_NEG_MSW", "Tested HIV -ve",
		    ReportUtils.map(monthlyReportIndicator.htsTestedNegative("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "HTS_TST_NEG_PWUD", "Tested HIV -ve",
		    ReportUtils.map(monthlyReportIndicator.htsTestedNegative("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "HTS_TST_NEG_PWID", "Tested HIV -ve",
		    ReportUtils.map(monthlyReportIndicator.htsTestedNegative("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "HTS_TST_NEG_Transman", "Tested HIV -ve",
		    ReportUtils.map(monthlyReportIndicator.htsTestedNegative("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "HTS_TST_NEG_Transwoman", "Tested HIV -ve",
		    ReportUtils.map(monthlyReportIndicator.htsTestedNegative("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//9. ENROL_HTS_TST - cakCs9wAFh1
	protected DataSetDefinition kpEnrolledAndTestedHIVDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("9");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "ENROL_HTS_TST_FSW", "Newly enrolled and Tested for HIV",
		    ReportUtils.map(monthlyReportIndicator.enrollHtsTst("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "ENROL_HTS_TST_MSM", "Newly enrolled and Tested for HIV",
		    ReportUtils.map(monthlyReportIndicator.enrollHtsTst("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "ENROL_HTS_TST_MSW", "Newly enrolled and Tested for HIV",
		    ReportUtils.map(monthlyReportIndicator.enrollHtsTst("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "ENROL_HTS_TST_PWUD", "Newly enrolled and Tested for HIV",
		    ReportUtils.map(monthlyReportIndicator.enrollHtsTst("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "ENROL_HTS_TST_PWID", "Newly enrolled and Tested for HIV",
		    ReportUtils.map(monthlyReportIndicator.enrollHtsTst("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "ENROL_HTS_TST_Transman", "Newly enrolled and Tested for HIV",
		    ReportUtils.map(monthlyReportIndicator.enrollHtsTst("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "ENROL_HTS_TST_Transwoman", "Newly enrolled and Tested for HIV",
		    ReportUtils.map(monthlyReportIndicator.enrollHtsTst("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//10.ENROL_HTS_TST_POS - cakCs9wAFh1
	protected DataSetDefinition kpEnrolledTestedPosDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("10");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "ENROL_HTS_TST_POS_FSW", "Newly enrolled and Tested for HIV+",
		    ReportUtils.map(monthlyReportIndicator.enrollHtsTstPos("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "ENROL_HTS_TST_POS_MSM", "Newly enrolled and Tested for HIV+",
		    ReportUtils.map(monthlyReportIndicator.enrollHtsTstPos("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "ENROL_HTS_TST_POS_MSW", "Newly enrolled and Tested for HIV+",
		    ReportUtils.map(monthlyReportIndicator.enrollHtsTstPos("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "ENROL_HTS_TST_POS_PWUD", "Newly enrolled and Tested for HIV+",
		    ReportUtils.map(monthlyReportIndicator.enrollHtsTstPos("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "ENROL_HTS_TST_POS_PWID", "Newly enrolled and Tested for HIV+",
		    ReportUtils.map(monthlyReportIndicator.enrollHtsTstPos("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "ENROL_HTS_TST_POS_Transman", "Newly enrolled and Tested for HIV+",
		    ReportUtils.map(monthlyReportIndicator.enrollHtsTstPos("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "ENROL_HTS_TST_POS_Transwoman", "Newly enrolled and Tested for HIV+",
		    ReportUtils.map(monthlyReportIndicator.enrollHtsTstPos("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//11.STI_SCREEN - D1UmxuQdovX
	protected DataSetDefinition kpScreenedForSTIDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("11");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "STI_SCREEN_FSW", "Screened for STI",
		    ReportUtils.map(monthlyReportIndicator.screenedForSTI("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "STI_SCREEN_MSM", "Screened for STI",
		    ReportUtils.map(monthlyReportIndicator.screenedForSTI("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "STI_SCREEN_MSW", "Screened for STI",
		    ReportUtils.map(monthlyReportIndicator.screenedForSTI("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "STI_SCREEN_PWUD", "Screened for STI",
		    ReportUtils.map(monthlyReportIndicator.screenedForSTI("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "STI_SCREEN_PWID", "Screened for STI",
		    ReportUtils.map(monthlyReportIndicator.screenedForSTI("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "STI_SCREEN_Transman", "Screened for STI",
		    ReportUtils.map(monthlyReportIndicator.screenedForSTI("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "STI_SCREEN_Transwoman", "Screened for STI",
		    ReportUtils.map(monthlyReportIndicator.screenedForSTI("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//12.HTS_SELF_ASSISTED - tePCfFFkby5
	protected DataSetDefinition kpAssistedSelfTestedDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("12");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "HTS_SELF_ASSISTED_FSW", "Self Assisted HIV Testing",
		    ReportUtils.map(monthlyReportIndicator.assistedSelfTested("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "HTS_SELF_ASSISTED_MSM", "Self Assisted HIV Testing",
		    ReportUtils.map(monthlyReportIndicator.assistedSelfTested("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "HTS_SELF_ASSISTED_MSW", "Self Assisted HIV Testing",
		    ReportUtils.map(monthlyReportIndicator.assistedSelfTested("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "HTS_SELF_ASSISTED_PWUD", "Self Assisted HIV Testing",
		    ReportUtils.map(monthlyReportIndicator.assistedSelfTested("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "HTS_SELF_ASSISTED_PWID", "Self Assisted HIV Testing",
		    ReportUtils.map(monthlyReportIndicator.assistedSelfTested("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "HTS_SELF_ASSISTED_Transman", "Self Assisted HIV Testing",
		    ReportUtils.map(monthlyReportIndicator.assistedSelfTested("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "HTS_SELF_ASSISTED_Transwoman", "Self Assisted HIV Testing",
		    ReportUtils.map(monthlyReportIndicator.assistedSelfTested("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//13. HTS_SELF_UNASSISTED - f9yUfz3UW7m
	protected DataSetDefinition kpUnAssistedSelfTestedDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("13");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "HTS_SELF_UNASSISTED_FSW", "Unassisted HIV self testing",
		    ReportUtils.map(monthlyReportIndicator.unAssistedSelfTested("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "HTS_SELF_UNASSISTED_MSM", "Unassisted HIV self testing",
		    ReportUtils.map(monthlyReportIndicator.unAssistedSelfTested("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "HTS_SELF_UNASSISTED_MSW", "Unassisted HIV self testing",
		    ReportUtils.map(monthlyReportIndicator.unAssistedSelfTested("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "HTS_SELF_UNASSISTED_PWUD", "Unassisted HIV self testing",
		    ReportUtils.map(monthlyReportIndicator.unAssistedSelfTested("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "HTS_SELF_UNASSISTED_PWID", "Unassisted HIV self testing",
		    ReportUtils.map(monthlyReportIndicator.unAssistedSelfTested("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "HTS_SELF_UNASSISTED_Transman", "Unassisted HIV self testing",
		    ReportUtils.map(monthlyReportIndicator.unAssistedSelfTested("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "HTS_SELF_UNASSISTED_Transwoman", "Unassisted HIV self testing",
		    ReportUtils.map(monthlyReportIndicator.unAssistedSelfTested("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//14. HTS_SELF_CONFIRMED_+VE - oeX7WkboEik
	protected DataSetDefinition kpHtsTstSelfConfirmedPositive() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("14");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "HTS_SELF_CONFIRMED_+VE_FSW", "Confirmed HIV+ on Self Test",
		    ReportUtils.map(monthlyReportIndicator.htsTstSelfConfirmedPositive("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "HTS_SELF_CONFIRMED_+VE_MSM", "Confirmed HIV+ on Self Test",
		    ReportUtils.map(monthlyReportIndicator.htsTstSelfConfirmedPositive("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "HTS_SELF_CONFIRMED_+VE_MSW", "Confirmed HIV+ on Self Test",
		    ReportUtils.map(monthlyReportIndicator.htsTstSelfConfirmedPositive("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "HTS_SELF_CONFIRMED_+VE_PWUD", "Confirmed HIV+ on Self Test",
		    ReportUtils.map(monthlyReportIndicator.htsTstSelfConfirmedPositive("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "HTS_SELF_CONFIRMED_+VE_PWID", "Confirmed HIV+ on Self Test",
		    ReportUtils.map(monthlyReportIndicator.htsTstSelfConfirmedPositive("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "HTS_SELF_CONFIRMED_+VE_Transman", "Confirmed HIV+ on Self Test",
		    ReportUtils.map(monthlyReportIndicator.htsTstSelfConfirmedPositive("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "HTS_SELF_CONFIRMED_+VE_Transwoman", "Confirmed HIV+ on Self Test",
		    ReportUtils.map(monthlyReportIndicator.htsTstSelfConfirmedPositive("Transwoman"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//15.STI_SCREEN_POS - cccAY22KB4P
	protected DataSetDefinition kpScreenedPositiveForSTIDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("15");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "STI_SCREEN_POS_FSW", "Screened +ve for STI",
		    ReportUtils.map(monthlyReportIndicator.screenedPositiveForSTI("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "STI_SCREEN_POS_MSM", "Screened +ve for STI",
		    ReportUtils.map(monthlyReportIndicator.screenedPositiveForSTI("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "STI_SCREEN_POS_MSW", "Screened +ve for STI",
		    ReportUtils.map(monthlyReportIndicator.screenedPositiveForSTI("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "STI_SCREEN_POS_PWUD", "Screened +ve for STI",
		    ReportUtils.map(monthlyReportIndicator.screenedPositiveForSTI("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "STI_SCREEN_POS_PWID", "Screened +ve for STI",
		    ReportUtils.map(monthlyReportIndicator.screenedPositiveForSTI("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "STI_SCREEN_POS_Transman", "Screened +ve for STI",
		    ReportUtils.map(monthlyReportIndicator.screenedPositiveForSTI("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "STI_SCREEN_POS_Transwoman", "Screened +ve for STI",
		    ReportUtils.map(monthlyReportIndicator.screenedPositiveForSTI("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//16. STI_TX - EbkN2jKcxym
	protected DataSetDefinition kpStartedSTITxDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("16");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "STI_TX_FSW", "Started STI Treatment",
		    ReportUtils.map(monthlyReportIndicator.startedSTITx("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "STI_TX_MSM", "Started STI Treatment",
		    ReportUtils.map(monthlyReportIndicator.startedSTITx("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "STI_TX_MSW", "Started STI Treatment",
		    ReportUtils.map(monthlyReportIndicator.startedSTITx("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "STI_TX_PWUD", "Started STI Treatment",
		    ReportUtils.map(monthlyReportIndicator.startedSTITx("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "STI_TX_PWID", "Started STI Treatment",
		    ReportUtils.map(monthlyReportIndicator.startedSTITx("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "STI_TX_Transman", "Started STI Treatment",
		    ReportUtils.map(monthlyReportIndicator.startedSTITx("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "STI_TX_Transwoman", "Started STI Treatment",
		    ReportUtils.map(monthlyReportIndicator.startedSTITx("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//17. GBV_SCREEN - mrSyW3UFeWI
	protected DataSetDefinition kpScreenedForGbvDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("17");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "GBV_SCREEN_FSW", "Screened for GBV",
		    ReportUtils.map(monthlyReportIndicator.screenedForGbv("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "GBV_SCREEN_MSM", "Screened for GBV",
		    ReportUtils.map(monthlyReportIndicator.screenedForGbv("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "GBV_SCREEN_MSW", "Screened for GBV",
		    ReportUtils.map(monthlyReportIndicator.screenedForGbv("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "GBV_SCREEN_PWUD", "Screened for GBV",
		    ReportUtils.map(monthlyReportIndicator.screenedForGbv("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "GBV_SCREEN_PWID", "Screened for GBV",
		    ReportUtils.map(monthlyReportIndicator.screenedForGbv("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "GBV_SCREEN_Transman", "Screened for GBV",
		    ReportUtils.map(monthlyReportIndicator.screenedForGbv("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "GBV_SCREEN_Transwoman", "Screened for GBV",
		    ReportUtils.map(monthlyReportIndicator.screenedForGbv("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//18. GBV_POS - J8GBNrQbDs7
	protected DataSetDefinition kpExperiencedGbvDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("18");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "GBV_POS_FSW", "Experienced GBV",
		    ReportUtils.map(monthlyReportIndicator.experiencedGbv("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "GBV_POS_MSM", "Experienced GBV",
		    ReportUtils.map(monthlyReportIndicator.experiencedGbv("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "GBV_POS_MSW", "Experienced GBV",
		    ReportUtils.map(monthlyReportIndicator.experiencedGbv("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "GBV_POS_PWUD", "Experienced GBV",
		    ReportUtils.map(monthlyReportIndicator.experiencedGbv("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "GBV_POS_PWID", "Experienced GBV",
		    ReportUtils.map(monthlyReportIndicator.experiencedGbv("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "GBV_POS_Transman", "Experienced GBV",
		    ReportUtils.map(monthlyReportIndicator.experiencedGbv("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "GBV_POS_Transwoman", "Experienced GBV",
		    ReportUtils.map(monthlyReportIndicator.experiencedGbv("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//19. GBV_LEGAL_SUPPORT - vY1Uy4cUKiG
	protected DataSetDefinition kpReceivedGbvLegalSupportDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("19");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "GBV_LEGAL_SUPPORT_FSW", "Received Legal support for GBV",
		    ReportUtils.map(monthlyReportIndicator.receivedGbvLegalSupport("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "GBV_LEGAL_SUPPORT_MSM", "Received Legal support for GBV",
		    ReportUtils.map(monthlyReportIndicator.receivedGbvLegalSupport("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "GBV_LEGAL_SUPPORT_MSW", "Received Legal support for GBV",
		    ReportUtils.map(monthlyReportIndicator.receivedGbvLegalSupport("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "GBV_LEGAL_SUPPORT_PWUD", "Received Legal support for GBV",
		    ReportUtils.map(monthlyReportIndicator.receivedGbvLegalSupport("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "GBV_LEGAL_SUPPORT_PWID", "Received Legal support for GBV",
		    ReportUtils.map(monthlyReportIndicator.receivedGbvLegalSupport("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "GBV_LEGAL_SUPPORT_Transman", "Received Legal support for GBV",
		    ReportUtils.map(monthlyReportIndicator.receivedGbvLegalSupport("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "GBV_LEGAL_SUPPORT_Transwoman", "Received Legal support for GBV",
		    ReportUtils.map(monthlyReportIndicator.receivedGbvLegalSupport("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//20. TX_NEW_DICE - ayMFkwavWB7
	protected DataSetDefinition kpNewOnARTDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("20");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "TX_NEW_DICE_FSW", "Started ART this month",
		    ReportUtils.map(monthlyReportIndicator.newOnARTKP("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_NEW_DICE_MSM", "Started ART this month",
		    ReportUtils.map(monthlyReportIndicator.newOnARTKP("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_NEW_DICE_MSW", "Started ART this month",
		    ReportUtils.map(monthlyReportIndicator.newOnARTKP("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_NEW_DICE_PWUD", "Started ART this month",
		    ReportUtils.map(monthlyReportIndicator.newOnARTKP("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_NEW_DICE_PWID", "Started ART this month",
		    ReportUtils.map(monthlyReportIndicator.newOnARTKP("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_NEW_DICE_Transman", "Started ART this month",
		    ReportUtils.map(monthlyReportIndicator.newOnARTKP("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_NEW_DICE_Transwoman", "Started ART this month",
		    ReportUtils.map(monthlyReportIndicator.newOnARTKP("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//21. TX_CURR_DICE - bVnurJnr7SM
	protected DataSetDefinition kpCurrOnARTDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("21");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "TX_CURR_DICE_FSW", "Current on ART",
		    ReportUtils.map(monthlyReportIndicator.currOnARTKP("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_CURR_DICE_MSM", "Current on ART",
		    ReportUtils.map(monthlyReportIndicator.currOnARTKP("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_CURR_DICE_MSW", "Current on ART",
		    ReportUtils.map(monthlyReportIndicator.currOnARTKP("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_CURR_DICE_PWUD", "Current on ART",
		    ReportUtils.map(monthlyReportIndicator.currOnARTKP("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_CURR_DICE_PWID", "Current on ART",
		    ReportUtils.map(monthlyReportIndicator.currOnARTKP("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_CURR_DICE_Transman", "Current on ART",
		    ReportUtils.map(monthlyReportIndicator.currOnARTKP("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_CURR_DICE_Transwoman", "Current on ART",
		    ReportUtils.map(monthlyReportIndicator.currOnARTKP("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//22. TX_RTT - Hm9jOlXPqlx
	protected DataSetDefinition kpHIVTXRttDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("22");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "TX_RTT_FSW", "Returned to treatment",
		    ReportUtils.map(monthlyReportIndicator.kplhivTXRtt("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_RTT_MSM", "Returned to treatment",
		    ReportUtils.map(monthlyReportIndicator.kplhivTXRtt("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_RTT_MSW", "Returned to treatment",
		    ReportUtils.map(monthlyReportIndicator.kplhivTXRtt("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_RTT_PWUD", "Returned to treatment",
		    ReportUtils.map(monthlyReportIndicator.kplhivTXRtt("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_RTT_PWID", "Returned to treatment",
		    ReportUtils.map(monthlyReportIndicator.kplhivTXRtt("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_RTT_Transman", "Returned to treatment",
		    ReportUtils.map(monthlyReportIndicator.kplhivTXRtt("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_RTT_Transwoman", "Returned to treatment",
		    ReportUtils.map(monthlyReportIndicator.kplhivTXRtt("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//23. RETEST_ELIGIBLE - kOmj7azOXf0
	protected DataSetDefinition kpEligibleForRetestDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("23");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "RETEST_ELIGIBLE_FSW", "Eligible for Retest",
		    ReportUtils.map(monthlyReportIndicator.eligibleForRetest("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "RETEST_ELIGIBLE_MSM", "Eligible for Retest",
		    ReportUtils.map(monthlyReportIndicator.eligibleForRetest("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "RETEST_ELIGIBLE_MSW", "Eligible for Retest",
		    ReportUtils.map(monthlyReportIndicator.eligibleForRetest("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "RETEST_ELIGIBLE_PWUD", "Eligible for Retest",
		    ReportUtils.map(monthlyReportIndicator.eligibleForRetest("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "RETEST_ELIGIBLE_PWID", "Eligible for Retest",
		    ReportUtils.map(monthlyReportIndicator.eligibleForRetest("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "RETEST_ELIGIBLE_Transman", "Eligible for Retest",
		    ReportUtils.map(monthlyReportIndicator.eligibleForRetest("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "RETEST_ELIGIBLE_Transwoman", "Eligible for Retest",
		    ReportUtils.map(monthlyReportIndicator.eligibleForRetest("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//24. RETEST_HTS_TST - KxPBLMcMfXX
	protected DataSetDefinition kpHTSTstEligibleRetestedDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("24");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "RETEST_HTS_TST_FSW", "Retested for HIV",
		    ReportUtils.map(monthlyReportIndicator.htsTstEligibleRetested("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "RETEST_HTS_TST_MSM", "Retested for HIV",
		    ReportUtils.map(monthlyReportIndicator.htsTstEligibleRetested("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "RETEST_HTS_TST_MSW", "Retested for HIV",
		    ReportUtils.map(monthlyReportIndicator.htsTstEligibleRetested("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "RETEST_HTS_TST_PWUD", "Retested for HIV",
		    ReportUtils.map(monthlyReportIndicator.htsTstEligibleRetested("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "RETEST_HTS_TST_PWID", "Retested for HIV",
		    ReportUtils.map(monthlyReportIndicator.htsTstEligibleRetested("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "RETEST_HTS_TST_Transman", "Retested for HIV",
		    ReportUtils.map(monthlyReportIndicator.htsTstEligibleRetested("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "RETEST_HTS_TST_Transwoman", "Retested for HIV",
		    ReportUtils.map(monthlyReportIndicator.htsTstEligibleRetested("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//25. RETEST_HTS_TST_POS - wHTtM3KK8xt
	protected DataSetDefinition kpRetestedHIVPositiveDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("25");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "RETEST_HTS_TST_POS_FSW", "Retested HIV Positive",
		    ReportUtils.map(monthlyReportIndicator.retestedHIVPositive("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "RETEST_HTS_TST_POS_MSM", "Retested HIV Positive",
		    ReportUtils.map(monthlyReportIndicator.retestedHIVPositive("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "RETEST_HTS_TST_POS_MSW", "Retested HIV Positive",
		    ReportUtils.map(monthlyReportIndicator.retestedHIVPositive("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "RETEST_HTS_TST_POS_PWUD", "Retested HIV Positive",
		    ReportUtils.map(monthlyReportIndicator.retestedHIVPositive("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "RETEST_HTS_TST_POS_PWID", "Retested HIV Positive",
		    ReportUtils.map(monthlyReportIndicator.retestedHIVPositive("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "RETEST_HTS_TST_POS_Transman", "Retested HIV Positive",
		    ReportUtils.map(monthlyReportIndicator.retestedHIVPositive("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "RETEST_HTS_TST_POS_Transwoman", "Retested HIV Positive",
		    ReportUtils.map(monthlyReportIndicator.retestedHIVPositive("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//26. PNS_OFFERED - IRLMFsFMPeE
	protected DataSetDefinition kpOfferedPNSDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("26");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "PNS_OFFERED_FSW", "Offered PNS",
		    ReportUtils.map(monthlyReportIndicator.offeredPNS("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_OFFERED_MSM", "Offered PNS",
		    ReportUtils.map(monthlyReportIndicator.offeredPNS("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_OFFERED_MSW", "Offered PNS",
		    ReportUtils.map(monthlyReportIndicator.offeredPNS("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_OFFERED_PWUD", "Offered PNS",
		    ReportUtils.map(monthlyReportIndicator.offeredPNS("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_OFFERED_PWID", "Offered PNS",
		    ReportUtils.map(monthlyReportIndicator.offeredPNS("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_OFFERED_Transman", "Offered PNS",
		    ReportUtils.map(monthlyReportIndicator.offeredPNS("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_OFFERED_Transwoman", "Offered PNS",
		    ReportUtils.map(monthlyReportIndicator.offeredPNS("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//27. PNS_ACCEPTED - Aj6vP6Zlw7A
	protected DataSetDefinition kpAcceptedPNSDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("27");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "PNS_ACCEPTED_FSW", "Accepted PNS",
		    ReportUtils.map(monthlyReportIndicator.acceptedPNS("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_ACCEPTED_MSM", "Accepted PNS",
		    ReportUtils.map(monthlyReportIndicator.acceptedPNS("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_ACCEPTED_MSW", "Accepted PNS",
		    ReportUtils.map(monthlyReportIndicator.acceptedPNS("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_ACCEPTED_PWUD", "Accepted PNS",
		    ReportUtils.map(monthlyReportIndicator.acceptedPNS("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_ACCEPTED_PWID", "Accepted PNS",
		    ReportUtils.map(monthlyReportIndicator.acceptedPNS("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_ACCEPTED_Transman", "Accepted PNS",
		    ReportUtils.map(monthlyReportIndicator.acceptedPNS("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_ACCEPTED_Transwoman", "Accepted PNS",
		    ReportUtils.map(monthlyReportIndicator.acceptedPNS("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//28. PNS_ELICITED - Om2TkuDV50S
	protected DataSetDefinition kpElicitedPNSDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("28");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "PNS_ELICITED_FSW", "Elicited PNS",
		    ReportUtils.map(monthlyReportIndicator.elicitedPNS("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_ELICITED_MSM", "Elicited PNS",
		    ReportUtils.map(monthlyReportIndicator.elicitedPNS("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_ELICITED_MSW", "Elicited PNS",
		    ReportUtils.map(monthlyReportIndicator.elicitedPNS("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_ELICITED_PWUD", "Elicited PNS",
		    ReportUtils.map(monthlyReportIndicator.elicitedPNS("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_ELICITED_PWID", "Elicited PNS",
		    ReportUtils.map(monthlyReportIndicator.elicitedPNS("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_ELICITED_Transman", "Elicited PNS",
		    ReportUtils.map(monthlyReportIndicator.elicitedPNS("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_ELICITED_Transwoman", "Elicited PNS",
		    ReportUtils.map(monthlyReportIndicator.elicitedPNS("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//29. PNS_KNOWN_POSITIVE_ENTRY - KMxOHc1sq6A
	protected DataSetDefinition kpPNSKnownPositiveAtEntryDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("29");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "PNS_KNOWN_POSITIVE_ENTRY_FSW", "PNS known positive at entry",
		    ReportUtils.map(monthlyReportIndicator.pnsKnownPositiveAtEntry("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_KNOWN_POSITIVE_ENTRY_MSM", "PNS known positive at entry",
		    ReportUtils.map(monthlyReportIndicator.pnsKnownPositiveAtEntry("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_KNOWN_POSITIVE_ENTRY_MSW", "PNS known positive at entry",
		    ReportUtils.map(monthlyReportIndicator.pnsKnownPositiveAtEntry("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_KNOWN_POSITIVE_ENTRY_PWUD", "PNS known positive at entry",
		    ReportUtils.map(monthlyReportIndicator.pnsKnownPositiveAtEntry("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_KNOWN_POSITIVE_ENTRY_PWID", "PNS known positive at entry",
		    ReportUtils.map(monthlyReportIndicator.pnsKnownPositiveAtEntry("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_KNOWN_POSITIVE_ENTRY_Transman", "PNS known positive at entry",
		    ReportUtils.map(monthlyReportIndicator.pnsKnownPositiveAtEntry("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_KNOWN_POSITIVE_ENTRY_Transwoman", "PNS known positive at entry",
		    ReportUtils.map(monthlyReportIndicator.pnsKnownPositiveAtEntry("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//30. PNS_NEW_HIV_POS - FZJvTrHEG9I
	protected DataSetDefinition kpPNSTestedPositiveDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("30");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "PNS_NEW_HIV_POS_FSW", "PNS tested Positive",
		    ReportUtils.map(monthlyReportIndicator.pnsTestedPositive("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_NEW_HIV_POS_MSM", "PNS tested Positive",
		    ReportUtils.map(monthlyReportIndicator.pnsTestedPositive("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_NEW_HIV_POS_MSW", "PNS tested Positive",
		    ReportUtils.map(monthlyReportIndicator.pnsTestedPositive("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_NEW_HIV_POS_PWUD", "PNS tested Positive",
		    ReportUtils.map(monthlyReportIndicator.pnsTestedPositive("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_NEW_HIV_POS_PWID", "PNS tested Positive",
		    ReportUtils.map(monthlyReportIndicator.pnsTestedPositive("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_NEW_HIV_POS_Transman", "PNS tested Positive",
		    ReportUtils.map(monthlyReportIndicator.pnsTestedPositive("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_NEW_HIV_POS_Transwoman", "PNS tested Positive",
		    ReportUtils.map(monthlyReportIndicator.pnsTestedPositive("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//31. PNS_NEW_HIV_NEG - zJTUyNwTvCX
	protected DataSetDefinition kpPNSTestedNegativeDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("31");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "PNS_NEW_HIV_NEG_FSW", "PNS tested Negative",
		    ReportUtils.map(monthlyReportIndicator.pnsTestedNegative("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_NEW_HIV_NEG_MSM", "PNS tested Negative",
		    ReportUtils.map(monthlyReportIndicator.pnsTestedNegative("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_NEW_HIV_NEG_MSW", "PNS tested Negative",
		    ReportUtils.map(monthlyReportIndicator.pnsTestedNegative("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_NEW_HIV_NEG_PWUD", "PNS tested Negative",
		    ReportUtils.map(monthlyReportIndicator.pnsTestedNegative("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_NEW_HIV_NEG_PWID", "PNS tested Negative",
		    ReportUtils.map(monthlyReportIndicator.pnsTestedNegative("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_NEW_HIV_NEG_Transman", "PNS tested Negative",
		    ReportUtils.map(monthlyReportIndicator.pnsTestedNegative("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PNS_NEW_HIV_NEG_Transwoman", "PNS tested Negative",
		    ReportUtils.map(monthlyReportIndicator.pnsTestedNegative("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//32. SUPPORT_GROUPS - zfiXKEUJgR8
	protected DataSetDefinition kpEnrolledInARTSupportGroupDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("32");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "SUPPORT_GROUPS_FSW", "Enrolled in support group",
		    ReportUtils.map(monthlyReportIndicator.kpEnrolledInARTSupportGroup("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "SUPPORT_GROUPS_MSM", "Enrolled in support group",
		    ReportUtils.map(monthlyReportIndicator.kpEnrolledInARTSupportGroup("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "SUPPORT_GROUPS_MSW", "Enrolled in support group",
		    ReportUtils.map(monthlyReportIndicator.kpEnrolledInARTSupportGroup("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "SUPPORT_GROUPS_PWUD", "Enrolled in support group",
		    ReportUtils.map(monthlyReportIndicator.kpEnrolledInARTSupportGroup("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "SUPPORT_GROUPS_PWID", "Enrolled in support group",
		    ReportUtils.map(monthlyReportIndicator.kpEnrolledInARTSupportGroup("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "SUPPORT_GROUPS_Transman", "Enrolled in support group",
		    ReportUtils.map(monthlyReportIndicator.kpEnrolledInARTSupportGroup("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "SUPPORT_GROUPS_Transwoman", "Enrolled in support group",
		    ReportUtils.map(monthlyReportIndicator.kpEnrolledInARTSupportGroup("Transwoman"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//33. GBV_CLINICAL - ZgJZbVBlRTP
	protected DataSetDefinition kpReceivedGbvClinicalCareDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("33");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "GBV_CLINICAL_FSW", "Received Clinical services for GBV case",
		    ReportUtils.map(monthlyReportIndicator.receivedGbvClinicalCare("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "GBV_CLINICAL_MSM", "Received Clinical services for GBV case",
		    ReportUtils.map(monthlyReportIndicator.receivedGbvClinicalCare("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "GBV_CLINICAL_MSW", "Received Clinical services for GBV case",
		    ReportUtils.map(monthlyReportIndicator.receivedGbvClinicalCare("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "GBV_CLINICAL_PWUD", "Received Clinical services for GBV case",
		    ReportUtils.map(monthlyReportIndicator.receivedGbvClinicalCare("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "GBV_CLINICAL_PWID", "Received Clinical services for GBV case",
		    ReportUtils.map(monthlyReportIndicator.receivedGbvClinicalCare("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "GBV_CLINICAL_Transman", "Received Clinical services for GBV case",
		    ReportUtils.map(monthlyReportIndicator.receivedGbvClinicalCare("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "GBV_CLINICAL_Transwoman", "Received Clinical services for GBV case",
		    ReportUtils.map(monthlyReportIndicator.receivedGbvClinicalCare("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//34. TX_PVLS_DICE_(N) - gKzh3U8KiEF
	protected DataSetDefinition kpHIVSuppressedVlDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("34");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_DICE_(N)_FSW", "KPLHIV with Suppressed VL ",
		    ReportUtils.map(monthlyReportIndicator.kplhivSuppressedVl("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_DICE_(N)_MSM", "KPLHIV with Suppressed VL ",
		    ReportUtils.map(monthlyReportIndicator.kplhivSuppressedVl("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_DICE_(N)_MSW", "KPLHIV with Suppressed VL ",
		    ReportUtils.map(monthlyReportIndicator.kplhivSuppressedVl("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_DICE_(N)_PWUD", "KPLHIV with Suppressed VL ",
		    ReportUtils.map(monthlyReportIndicator.kplhivSuppressedVl("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_DICE_(N)_PWID", "KPLHIV with Suppressed VL ",
		    ReportUtils.map(monthlyReportIndicator.kplhivSuppressedVl("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_DICE_(N)_Transman", "KPLHIV with Suppressed VL ",
		    ReportUtils.map(monthlyReportIndicator.kplhivSuppressedVl("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_DICE_(N)_Transwoman", "KPLHIV with Suppressed VL ",
		    ReportUtils.map(monthlyReportIndicator.kplhivSuppressedVl("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//35. TX_PVLS_DICE (D) - Ob0tw9E09m6
	protected DataSetDefinition kplhivWithVlResultDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("35");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_DICE_(D)_FSW", "KPLHIV with VL result ",
		    ReportUtils.map(monthlyReportIndicator.kplhivWithVlResult("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_DICE_(D)_MSM", "KPLHIV with VL result ",
		    ReportUtils.map(monthlyReportIndicator.kplhivWithVlResult("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_DICE_(D)_MSW", "KPLHIV with VL result ",
		    ReportUtils.map(monthlyReportIndicator.kplhivWithVlResult("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_DICE_(D)_PWUD", "KPLHIV with VL result ",
		    ReportUtils.map(monthlyReportIndicator.kplhivWithVlResult("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_DICE_(D)_PWID", "KPLHIV with VL result ",
		    ReportUtils.map(monthlyReportIndicator.kplhivWithVlResult("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_DICE_(D)_Transman", "KPLHIV with VL result ",
		    ReportUtils.map(monthlyReportIndicator.kplhivWithVlResult("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_DICE_(D)_Transwoman", "KPLHIV with VL result ",
		    ReportUtils.map(monthlyReportIndicator.kplhivWithVlResult("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//36. TX_PVLS_VERIFY_PEPFAR_SITE_(N) - HDX9dYK1S8C
	protected DataSetDefinition kplhivSuppressedVlArtOtherPEPFARSiteDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("36");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_VERIFY_PEPFAR_SITE_(N)_FSW",
		    "KPLHIV with suppressed VL result  from other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivSuppressedVlArtOtherPEPFARSite("FSW"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_VERIFY_PEPFAR_SITE_(N)_MSM",
		    "KPLHIV with suppressed VL result from other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivSuppressedVlArtOtherPEPFARSite("MSM"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_VERIFY_PEPFAR_SITE_(N)_MSW",
		    "KPLHIV with suppressed VL result from other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivSuppressedVlArtOtherPEPFARSite("MSW"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_VERIFY_PEPFAR_SITE_(N)_PWUD",
		    "KPLHIV with suppressed VL result from other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivSuppressedVlArtOtherPEPFARSite("PWUD"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_VERIFY_PEPFAR_SITE_(N)_PWID",
		    "KPLHIV with suppressed VL result from other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivSuppressedVlArtOtherPEPFARSite("PWID"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_VERIFY_PEPFAR_SITE_(N)_Transman",
		    "KPLHIV with suppressed VL result from other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivSuppressedVlArtOtherPEPFARSite("Transman"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_VERIFY_PEPFAR_SITE_(N)_Transwoman",
		    "KPLHIV with suppressed VL result from other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivSuppressedVlArtOtherPEPFARSite("Transwoman"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//37. TX_PVLS_VERIFY_PEPFAR_SITE_(D) - FNVxVWLWSlP
	protected DataSetDefinition kplhivVlResultArtOtherPEPFARSiteDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("37");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_VERIFY_PEPFAR_SITE_(D)_FSW",
		    "KPLHIV with VL result from other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivVlResultArtOtherPEPFARSite("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_VERIFY_PEPFAR_SITE_(D)_MSM",
		    "KPLHIV with VL result from other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivVlResultArtOtherPEPFARSite("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_VERIFY_PEPFAR_SITE_(D)_MSW",
		    "KPLHIV with VL result from other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivVlResultArtOtherPEPFARSite("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_VERIFY_PEPFAR_SITE_(D)_PWUD",
		    "KPLHIV with VL result from other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivVlResultArtOtherPEPFARSite("PWUD"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_VERIFY_PEPFAR_SITE_(D)_PWID",
		    "KPLHIV with VL result from other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivVlResultArtOtherPEPFARSite("PWID"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_VERIFY_PEPFAR_SITE_(D)_Transman",
		    "KPLHIV with VL result from other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivVlResultArtOtherPEPFARSite("Transman"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_VERIFY_PEPFAR_SITE_(D)_Transwoman",
		    "KPLHIV with VL result from other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivVlResultArtOtherPEPFARSite("Transwoman"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//38.TX_PVLS_VERIFY_NON_PEPFAR_SITE_(N) -  YFVW39TeCC5
	protected DataSetDefinition kplhivSuppressedVlArtNonPEPFARSiteDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("38");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_VERIFY_NON_PEPFAR_SITE_(N)_FSW",
		    "KPLHIV with VL result from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivSuppressedVlArtNonPEPFARSite("FSW"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_VERIFY_NON_PEPFAR_SITE_(N)_MSM",
		    "KPLHIV with VL result from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivSuppressedVlArtNonPEPFARSite("MSM"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_VERIFY_NON_PEPFAR_SITE_(N)_MSW",
		    "KPLHIV with VL result from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivSuppressedVlArtNonPEPFARSite("MSW"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_VERIFY_NON_PEPFAR_SITE_(N)_PWUD",
		    "KPLHIV with VL result from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivSuppressedVlArtNonPEPFARSite("PWUD"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_VERIFY_NON_PEPFAR_SITE_(N)_PWID",
		    "KPLHIV with VL result from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivSuppressedVlArtNonPEPFARSite("PWID"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_VERIFY_NON_PEPFAR_SITE_(N)_Transman",
		    "KPLHIV with VL result from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivSuppressedVlArtNonPEPFARSite("Transman"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_VERIFY_NON_PEPFAR_SITE_(N)_Transwoman",
		    "KPLHIV with VL result from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivSuppressedVlArtNonPEPFARSite("Transwoman"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//39. TX_PVLS_VERIFY_NON_PEPFAR_SITE_(D) - MyaBDZA5l8o
	protected DataSetDefinition kplhivVlResultArtNonPEPFARSiteDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("39");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_VERIFY_NON_PEPFAR_SITE_(D)_FSW",
		    "KPLHIV with VL result from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivVlResultArtNonPEPFARSite("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_VERIFY_NON_PEPFAR_SITE_(D)_MSM",
		    "KPLHIV with VL result from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivVlResultArtNonPEPFARSite("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_VERIFY_NON_PEPFAR_SITE_(D)_MSW",
		    "KPLHIV with VL result from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivVlResultArtNonPEPFARSite("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_VERIFY_NON_PEPFAR_SITE_(D)_PWUD",
		    "KPLHIV with VL result from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivVlResultArtNonPEPFARSite("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_VERIFY_NON_PEPFAR_SITE_(D)_PWID",
		    "KPLHIV with VL result from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivVlResultArtNonPEPFARSite("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_VERIFY_NON_PEPFAR_SITE_(D)_Transman",
		    "KPLHIV with VL result from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivVlResultArtNonPEPFARSite("Transman"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_VERIFY_NON_PEPFAR_SITE_(D)_Transwoman",
		    "KPLHIV with VL result from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivVlResultArtNonPEPFARSite("Transwoman"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//40. PrEP_CURR_VERIFY_PEPFAR_SITE - CCdVD9plUwm
	protected DataSetDefinition kpReferredAndInitiatedPrEPPepfarDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("40");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "PrEP_CURR_VERIFY_PEPFAR_SITE_FSW",
		    "Clients initiated on PrEP from another PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.referredAndInitiatedPrEPPepfar("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PrEP_CURR_VERIFY_PEPFAR_SITE_MSM",
		    "Clients initiated on PrEP from another PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.referredAndInitiatedPrEPPepfar("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PrEP_CURR_VERIFY_PEPFAR_SITE_MSW",
		    "Clients initiated on PrEP from another PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.referredAndInitiatedPrEPPepfar("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PrEP_CURR_VERIFY_PEPFAR_SITE_PWUD",
		    "Clients initiated on PrEP from another PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.referredAndInitiatedPrEPPepfar("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PrEP_CURR_VERIFY_PEPFAR_SITE_PWID",
		    "Clients initiated on PrEP from another PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.referredAndInitiatedPrEPPepfar("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PrEP_CURR_VERIFY_PEPFAR_SITE_Transman",
		    "Clients initiated on PrEP from another PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.referredAndInitiatedPrEPPepfar("Transman"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PrEP_CURR_VERIFY_PEPFAR_SITE_Transwoman",
		    "Clients initiated on PrEP from another PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.referredAndInitiatedPrEPPepfar("Transwoman"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//41. PrEP_CURR_VERIFY_NON_PEPFAR_SITE - nWMnSfWQw3F
	protected DataSetDefinition kpReferredAndInitiatedPrEPNonPepfarDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("41");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "PrEP_CURR_VERIFY_NON_PEPFAR_SITE_FSW",
		    "Clients initiated on PrEP from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.referredAndInitiatedPrEPNonPepfar("FSW"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PrEP_CURR_VERIFY_NON_PEPFAR_SITE_MSM",
		    "Clients initiated on PrEP from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.referredAndInitiatedPrEPNonPepfar("MSM"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PrEP_CURR_VERIFY_NON_PEPFAR_SITE_MSW",
		    "Clients initiated on PrEP from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.referredAndInitiatedPrEPNonPepfar("MSW"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PrEP_CURR_VERIFY_NON_PEPFAR_SITE_PWUD",
		    "Clients initiated on PrEP from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.referredAndInitiatedPrEPNonPepfar("PWUD"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PrEP_CURR_VERIFY_NON_PEPFAR_SITE_PWID",
		    "Clients initiated on PrEP from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.referredAndInitiatedPrEPNonPepfar("PWID"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PrEP_CURR_VERIFY_NON_PEPFAR_SITE_Transman",
		    "Clients initiated on PrEP from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.referredAndInitiatedPrEPNonPepfar("Transman"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "PrEP_CURR_VERIFY_NON_PEPFAR_SITE_Transwoman",
		    "Clients initiated on PrEP from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.referredAndInitiatedPrEPNonPepfar("Transwoman"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//42. TX_NEW_VERIFY_PEPFAR_SITE - bVPCYIWpJAs
	protected DataSetDefinition kplhivInitiatedARTOtherPEPFARDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("42");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "TX_NEW_VERIFY_PEPFAR_SITE_FSW",
		    "Clients initiated on ART from other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivInitiatedARTOtherPEPFAR("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_NEW_VERIFY_PEPFAR_SITE_MSM",
		    "Clients initiated on ART from other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivInitiatedARTOtherPEPFAR("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_NEW_VERIFY_PEPFAR_SITE_MSW",
		    "Clients initiated on ART from other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivInitiatedARTOtherPEPFAR("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_NEW_VERIFY_PEPFAR_SITE_PWUD",
		    "Clients initiated on ART from other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivInitiatedARTOtherPEPFAR("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_NEW_VERIFY_PEPFAR_SITE_PWID",
		    "Clients initiated on ART from other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivInitiatedARTOtherPEPFAR("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_NEW_VERIFY_PEPFAR_SITE_Transman",
		    "Clients initiated on ART from other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivInitiatedARTOtherPEPFAR("Transman"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_NEW_VERIFY_PEPFAR_SITE_Transwoman",
		    "Clients initiated on ART from other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivInitiatedARTOtherPEPFAR("Transwoman"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//43. TX_NEW_VERIFY_NON_PEPFAR - zYZe7ERpD0Z
	protected DataSetDefinition kplhivInitiatedARTNonPEPFARDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("43");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "TX_NEW_VERIFY_NON_PEPFAR_FSW",
		    "Clients initiated on ART from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivInitiatedARTNonPEPFAR("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_NEW_VERIFY_NON_PEPFAR_MSM",
		    "Clients initiated on ART from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivInitiatedARTNonPEPFAR("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_NEW_VERIFY_NON_PEPFAR_MSW",
		    "Clients initiated on ART from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivInitiatedARTNonPEPFAR("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_NEW_VERIFY_NON_PEPFAR_PWUD",
		    "Clients initiated on ART from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivInitiatedARTNonPEPFAR("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_NEW_VERIFY_NON_PEPFAR_PWID",
		    "Clients initiated on ART from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivInitiatedARTNonPEPFAR("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_NEW_VERIFY_NON_PEPFAR_Transman",
		    "Clients initiated on ART from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivInitiatedARTNonPEPFAR("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_NEW_VERIFY_NON_PEPFAR_Transwoman",
		    "Clients initiated on ART from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivInitiatedARTNonPEPFAR("Transwoman"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//44. TX_CURR_VERIFY_PEPFAR_SITE - mKinaTjSI8O
	
	protected DataSetDefinition kplhivCurrentOnARTOtherPEPFARDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("44");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "TX_CURR_VERIFY_PEPFAR_SITE_FSW",
		    "Clients Current on ART from other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivCurrentOnARTOtherPEPFAR("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_CURR_VERIFY_PEPFAR_SITE_MSM",
		    "Clients Current on ART from other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivCurrentOnARTOtherPEPFAR("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_CURR_VERIFY_PEPFAR_SITE_MSW",
		    "Clients Current on ART from other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivCurrentOnARTOtherPEPFAR("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_CURR_VERIFY_PEPFAR_SITE_PWUD",
		    "Clients Current on ART from other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivCurrentOnARTOtherPEPFAR("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_CURR_VERIFY_PEPFAR_SITE_PWID",
		    "Clients Current on ART from other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivCurrentOnARTOtherPEPFAR("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_CURR_VERIFY_PEPFAR_SITE_Transman",
		    "Clients Current on ART from other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivCurrentOnARTOtherPEPFAR("Transman"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_CURR_VERIFY_PEPFAR_SITE_Transwoman",
		    "Clients Current on ART from other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivCurrentOnARTOtherPEPFAR("Transwoman"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//45. TX_CURR_VERIFY_NON_PEPFAR_SITE - UG1nFQQ7Yz2
	protected DataSetDefinition kplhivCurrentOnARTNonPEPFARDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("45");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "TX_CURR_VERIFY_NON_PEPFAR_SITE_FSW",
		    "Clients Current on ART from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivCurrentOnARTNonPEPFAR("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_CURR_VERIFY_NON_PEPFAR_SITE_MSM",
		    "Clients Current on ART from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivCurrentOnARTNonPEPFAR("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_CURR_VERIFY_NON_PEPFAR_SITE_MSW",
		    "Clients Current on ART from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivCurrentOnARTNonPEPFAR("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_CURR_VERIFY_NON_PEPFAR_SITE_PWUD",
		    "Clients Current on ART from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivCurrentOnARTNonPEPFAR("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_CURR_VERIFY_NON_PEPFAR_SITE_PWID",
		    "Clients Current on ART from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivCurrentOnARTNonPEPFAR("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_CURR_VERIFY_NON_PEPFAR_SITE_Transman",
		    "Clients Current on ART from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivCurrentOnARTNonPEPFAR("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_CURR_VERIFY_NON_PEPFAR_SITE_Transwoman",
		    "Clients Current on ART from other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.kplhivCurrentOnARTNonPEPFAR("Transwoman"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//46. KP_CURR - IfZnCTNMiec
	protected DataSetDefinition kpCurrDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("46");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "KP_CURR_FSW", "Cuurent on KP",
		    ReportUtils.map(monthlyReportIndicator.kpCurr("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KP_CURR_MSM", "Cuurent on KP",
		    ReportUtils.map(monthlyReportIndicator.kpCurr("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KP_CURR_MSW", "Cuurent on KP",
		    ReportUtils.map(monthlyReportIndicator.kpCurr("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KP_CURR_PWUD", "Cuurent on KP",
		    ReportUtils.map(monthlyReportIndicator.kpCurr("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KP_CURR_PWID", "Cuurent on KP",
		    ReportUtils.map(monthlyReportIndicator.kpCurr("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KP_CURR_Transman", "Cuurent on KP",
		    ReportUtils.map(monthlyReportIndicator.kpCurr("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KP_CURR_Transwoman", "Cuurent on KP",
		    ReportUtils.map(monthlyReportIndicator.kpCurr("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//47. KPLHIV_CURR - U8ah8a3Up1f
	protected DataSetDefinition kpLHIVCurrDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("47");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "KPLHIV_CURR_FSW", "KP - Current living with HIV",
		    ReportUtils.map(monthlyReportIndicator.kpLHIVCurr("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KPLHIV_CURR_MSM", "KP - Current living with HIV",
		    ReportUtils.map(monthlyReportIndicator.kpLHIVCurr("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KPLHIV_CURR_MSW", "KP - Current living with HIV",
		    ReportUtils.map(monthlyReportIndicator.kpLHIVCurr("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KPLHIV_CURR_PWUD", "KP - Current living with HIV",
		    ReportUtils.map(monthlyReportIndicator.kpLHIVCurr("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KPLHIV_CURR_PWID", "KP - Current living with HIV",
		    ReportUtils.map(monthlyReportIndicator.kpLHIVCurr("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KPLHIV_CURR_Transman", "KP - Current living with HIV",
		    ReportUtils.map(monthlyReportIndicator.kpLHIVCurr("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KPLHIV_CURR_Transwoman", "KP - Current living with HIV",
		    ReportUtils.map(monthlyReportIndicator.kpLHIVCurr("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//48. TX_LTFU_RECENT - ayMFkwavWB7
	protected DataSetDefinition kplhivLTFURecentlyDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("48");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "TX_LTFU_RECENT_FSW", "Recently lost to followup",
		    ReportUtils.map(monthlyReportIndicator.kplhivLTFURecently("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_LTFU_RECENT_MSM", "Recently lost to followup",
		    ReportUtils.map(monthlyReportIndicator.kplhivLTFURecently("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_LTFU_RECENT_MSW", "Recently lost to followup",
		    ReportUtils.map(monthlyReportIndicator.kplhivLTFURecently("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_LTFU_RECENT_PWUD", "Recently lost to followup",
		    ReportUtils.map(monthlyReportIndicator.kplhivLTFURecently("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_LTFU_RECENT_PWID", "Recently lost to followup",
		    ReportUtils.map(monthlyReportIndicator.kplhivLTFURecently("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_LTFU_RECENT_Transman", "Recently lost to followup",
		    ReportUtils.map(monthlyReportIndicator.kplhivLTFURecently("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_LTFU_RECENT_Transwoman", "Recently lost to followup",
		    ReportUtils.map(monthlyReportIndicator.kplhivLTFURecently("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//49. KP_EVER_POS - vKtYvZGWdQ3
	
	protected DataSetDefinition kpEverPosDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("49");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "KP_EVER_POS_FSW", " KP Ever Positive",
		    ReportUtils.map(monthlyReportIndicator.kpEverPos("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KP_EVER_POS_MSM", " KP Ever Positive",
		    ReportUtils.map(monthlyReportIndicator.kpEverPos("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KP_EVER_POS_MSW", " KP Ever Positive",
		    ReportUtils.map(monthlyReportIndicator.kpEverPos("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KP_EVER_POS_PWUD", " KP Ever Positive",
		    ReportUtils.map(monthlyReportIndicator.kpEverPos("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KP_EVER_POS_PWID", " KP Ever Positive",
		    ReportUtils.map(monthlyReportIndicator.kpEverPos("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KP_EVER_POS_Transman", " KP Ever Positive",
		    ReportUtils.map(monthlyReportIndicator.kpEverPos("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "KP_EVER_POS_Transwoman", " KP Ever Positive",
		    ReportUtils.map(monthlyReportIndicator.kpEverPos("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//50. TX_EVER_DICE - PhOOi3jpyU5
	
	protected DataSetDefinition txEverDiceDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("50");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "TX_EVER_DICE_FSW", "Ever on ART in this DICE",
		    ReportUtils.map(monthlyReportIndicator.txEverDice("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_EVER_DICE_MSM", "Ever on ART in this DICE",
		    ReportUtils.map(monthlyReportIndicator.txEverDice("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_EVER_DICE_MSW", "Ever on ART in this DICE",
		    ReportUtils.map(monthlyReportIndicator.txEverDice("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_EVER_DICE_PWUD", "Ever on ART in this DICE",
		    ReportUtils.map(monthlyReportIndicator.txEverDice("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_EVER_DICE_PWID", "Ever on ART in this DICE",
		    ReportUtils.map(monthlyReportIndicator.txEverDice("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_EVER_DICE_Transman", "Ever on ART in this DICE",
		    ReportUtils.map(monthlyReportIndicator.txEverDice("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_EVER_DICE_Transwoman", "Ever on ART in this DICE",
		    ReportUtils.map(monthlyReportIndicator.txEverDice("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//51. TX_EVER_VERIFY_PEPFAR_SITE - E26PZb2eocw
	protected DataSetDefinition kpTxEverVerifyPEPFARDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("51");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "TX_EVER_VERIFY_PEPFAR_SITE_FSW", "Ever on ART in other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.txEverVerifyPEPFAR("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_EVER_VERIFY_PEPFAR_SITE_MSM", "Ever on ART in other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.txEverVerifyPEPFAR("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_EVER_VERIFY_PEPFAR_SITE_MSW", "Ever on ART in other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.txEverVerifyPEPFAR("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_EVER_VERIFY_PEPFAR_SITE_PWUD", "Ever on ART in other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.txEverVerifyPEPFAR("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_EVER_VERIFY_PEPFAR_SITE_PWID", "Ever on ART in other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.txEverVerifyPEPFAR("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_EVER_VERIFY_PEPFAR_SITE_Transman", "Ever on ART in other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.txEverVerifyPEPFAR("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_EVER_VERIFY_PEPFAR_SITE_Transwoman", "Ever on ART in other PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.txEverVerifyPEPFAR("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//52. TX_EVER_VERIFY_NON_PEPFAR_SITE - hotT1X2G7Ss
	
	protected DataSetDefinition kpTxEverVerifyNonPEPFARDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("52");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "TX_EVER_VERIFY_NON_PEPFAR_SITE_FSW", "Ever on ART in other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.txEverVerifyNonPEPFAR("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_EVER_VERIFY_NON_PEPFAR_SITE_MSM", "Ever on ART in other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.txEverVerifyNonPEPFAR("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_EVER_VERIFY_NON_PEPFAR_SITE_MSW", "Ever on ART in other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.txEverVerifyNonPEPFAR("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_EVER_VERIFY_NON_PEPFAR_SITE_PWUD", "Ever on ART in other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.txEverVerifyNonPEPFAR("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_EVER_VERIFY_NON_PEPFAR_SITE_PWID", "Ever on ART in other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.txEverVerifyNonPEPFAR("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_EVER_VERIFY_NON_PEPFAR_SITE_Transman",
		    "Ever on ART in other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.txEverVerifyNonPEPFAR("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_EVER_VERIFY_NON_PEPFAR_SITE_Transwoman",
		    "Ever on ART in other non PEPFAR site",
		    ReportUtils.map(monthlyReportIndicator.txEverVerifyNonPEPFAR("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//53. TX_PVLS_ELIGIBLE_DICE - wEATMdiockB
	protected DataSetDefinition kpTxPvlsEligibleDiceDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("53");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_DICE_FSW",
		    "On ART in this DICE and Eligible for VL within last 12 months",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleDice("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_DICE_MSM",
		    "On ART in this DICE and Eligible for VL within last 12 months",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleDice("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_DICE_MSW",
		    "On ART in this DICE and Eligible for VL within last 12 months",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleDice("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_DICE_PWUD",
		    "On ART in this DICE and Eligible for VL within last 12 months",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleDice("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_DICE_PWID",
		    "On ART in this DICE and Eligible for VL within last 12 months",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleDice("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_DICE_Transman",
		    "On ART in this DICE and Eligible for VL within last 12 months",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleDice("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_DICE_Transwoman",
		    "On ART in this DICE and Eligible for VL within last 12 months",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleDice("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//54. TX_PVLS_ELIGIBLE_DONE_DICE- mhkO6IPf1nE
	protected DataSetDefinition kpTxPvlsEligibleDoneDiceDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("54");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_DONE_DICE_FSW",
		    "On ART in this DICE and Eligible for VL within last 12 months whose samples were taken",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleDoneDice("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_DONE_DICE_MSM",
		    "On ART in this DICE and Eligible for VL within last 12 months whose samples were taken",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleDoneDice("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_DONE_DICE_MSW",
		    "On ART in this DICE and Eligible for VL within last 12 months whose samples were taken",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleDoneDice("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_DONE_DICE_PWUD",
		    "On ART in this DICE and Eligible for VL within last 12 months whose samples were taken",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleDoneDice("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_DONE_DICE_PWID",
		    "On ART in this DICE and Eligible for VL within last 12 months whose samples were taken",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleDoneDice("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_DONE_DICE_Transman",
		    "On ART in this DICE and Eligible for VL within last 12 months whose samples were taken",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleDoneDice("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_DONE_DICE_Transwoman",
		    "On ART in this DICE and Eligible for VL within last 12 months whose samples were taken",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleDoneDice("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//55.TX_PVLS_ELIGIBLE_VERIFY_PEPFAR_SITE - tcKlzWxQG6w
	protected DataSetDefinition kpTxPvlsEligibleVerifyPEPFARDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("55");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_VERIFY_PEPFAR_SITE_FSW",
		    "On ART in other PEPFAR sites Eligible for VL within last 12 months",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleVerifyPEPFAR("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_VERIFY_PEPFAR_SITE_MSM",
		    "On ART in other PEPFAR sites Eligible for VL within last 12 months",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleVerifyPEPFAR("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_VERIFY_PEPFAR_SITE_MSW",
		    "On ART in other PEPFAR sites Eligible for VL within last 12 months",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleVerifyPEPFAR("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_VERIFY_PEPFAR_SITE_PWUD",
		    "On ART in other PEPFAR sites Eligible for VL within last 12 months",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleVerifyPEPFAR("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_VERIFY_PEPFAR_SITE_PWID",
		    "On ART in other PEPFAR sites Eligible for VL within last 12 months",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleVerifyPEPFAR("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_VERIFY_PEPFAR_SITE_Transman",
		    "On ART in other PEPFAR sites Eligible for VL within last 12 months",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleVerifyPEPFAR("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_VERIFY_PEPFAR_SITE_Transwoman",
		    "On ART in other PEPFAR sites Eligible for VL within last 12 months",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleVerifyPEPFAR("Transwoman"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//56. TX_PVLS_ELIGIBLE_DONE_VERIFY_PEPFAR_SITE - cn1u70K6fMZ
	
	protected DataSetDefinition kpTxPvlsEligibleDoneVerifyPEPFARDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("56");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_DONE_VERIFY_PEPFAR_SITE_FSW",
		    "On ART in other PEPFAR sites Eligible for VL within last 12 months whose samples were taken",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleDoneVerifyPEPFAR("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_DONE_VERIFY_PEPFAR_SITE_MSM",
		    "On ART in other PEPFAR sites Eligible for VL within last 12 months whose samples were taken",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleDoneVerifyPEPFAR("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_DONE_VERIFY_PEPFAR_SITE_MSW",
		    "On ART in other PEPFAR sites Eligible for VL within last 12 months whose samples were taken",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleDoneVerifyPEPFAR("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_DONE_VERIFY_PEPFAR_SITE_PWUD",
		    "On ART in other PEPFAR sites Eligible for VL within last 12 months whose samples were taken",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleDoneVerifyPEPFAR("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_DONE_VERIFY_PEPFAR_SITE_PWID",
		    "On ART in other PEPFAR sites Eligible for VL within last 12 months whose samples were taken",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleDoneVerifyPEPFAR("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_DONE_VERIFY_PEPFAR_SITE_Transman",
		    "On ART in other PEPFAR sites Eligible for VL within last 12 months whose samples were taken",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleDoneVerifyPEPFAR("Transman"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_DONE_VERIFY_PEPFAR_SITE_Transwoman",
		    "On ART in other PEPFAR sites Eligible for VL within last 12 months whose samples were taken",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleDoneVerifyPEPFAR("Transwoman"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//57. TX_PVLS_ELIGIBLE_VERIFY_NON_PEPFAR_SITE - b5pkOaXA4d7
	protected DataSetDefinition kpTxPvlsEligibleVerifyNonPEPFARDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("57");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_VERIFY_NON_PEPFAR_SITE_FSW",
		    "On ART in other non PEPFAR sites Eligible for VL within last 12 months",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleVerifyNonPEPFAR("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_VERIFY_NON_PEPFAR_SITE_MSM",
		    "On ART in other non PEPFAR sites Eligible for VL within last 12 months",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleVerifyNonPEPFAR("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_VERIFY_NON_PEPFAR_SITE_MSW",
		    "On ART in other non PEPFAR sites Eligible for VL within last 12 months",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleVerifyNonPEPFAR("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_VERIFY_NON_PEPFAR_SITE_PWUD",
		    "On ART in other non PEPFAR sites Eligible for VL within last 12 months",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleVerifyNonPEPFAR("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_VERIFY_NON_PEPFAR_SITE_PWID",
		    "On ART in other non PEPFAR sites Eligible for VL within last 12 months",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleVerifyNonPEPFAR("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_VERIFY_NON_PEPFAR_SITE_Transman",
		    "On ART in other non PEPFAR sites Eligible for VL within last 12 months",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleVerifyNonPEPFAR("Transman"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_VERIFY_NON_PEPFAR_SITE_Transwoman",
		    "On ART in other non PEPFAR sites Eligible for VL within last 12 months",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleVerifyNonPEPFAR("Transwoman"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//58. TX_PVLS_ELIGIBLE_DONE_VERIFY_NON_PEPFAR_SITE - O4M0FcApmzi
	protected DataSetDefinition kpTxPvlsEligibleDoneVerifyNonPEPFARDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("58");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_DONE_VERIFY_NON_PEPFAR_SITE_FSW",
		    "On ART in other non PEPFAR sites Eligible for VL within last 12 months whose samples were taken",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleDoneVerifyNonPEPFAR("FSW"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_DONE_VERIFY_NON_PEPFAR_SITE_MSM",
		    "On ART in other non PEPFAR sites Eligible for VL within last 12 months whose samples were taken",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleDoneVerifyNonPEPFAR("MSM"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_DONE_VERIFY_NON_PEPFAR_SITE_MSW",
		    "On ART in other non PEPFAR sites Eligible for VL within last 12 months whose samples were taken",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleDoneVerifyNonPEPFAR("MSW"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_DONE_VERIFY_NON_PEPFAR_SITE_PWUD",
		    "On ART in other non PEPFAR sites Eligible for VL within last 12 months whose samples were taken",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleDoneVerifyNonPEPFAR("PWUD"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_DONE_VERIFY_NON_PEPFAR_SITE_PWID",
		    "On ART in other non PEPFAR sites Eligible for VL within last 12 months whose samples were taken",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleDoneVerifyNonPEPFAR("PWID"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_DONE_VERIFY_NON_PEPFAR_SITE_Transman",
		    "On ART in other non PEPFAR sites Eligible for VL within last 12 months whose samples were taken",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleDoneVerifyNonPEPFAR("Transman"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "TX_PVLS_ELIGIBLE_DONE_VERIFY_NON_PEPFAR_SITE_Transwoman",
		    "On ART in other non PEPFAR sites Eligible for VL within last 12 months whose samples were taken",
		    ReportUtils.map(monthlyReportIndicator.txPvlsEligibleDoneVerifyNonPEPFAR("Transwoman"), indParams),
		    kpAgeDisaggregation, Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
	//59. MMD - xCjs0ES6xwx
	protected DataSetDefinition kpOnMultiMonthARTDataset() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("59");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cohortDsd.addDimension("age", ReportUtils.map(commonDimensions.monthlyReportAgeGroups(), "onDate=${endDate}"));
		cohortDsd.addDimension("KPType", ReportUtils.map(commonDimensions.kpType()));
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		EmrReportingUtils.addRow(cohortDsd, "MMD_FSW", "Multi month appointments",
		    ReportUtils.map(monthlyReportIndicator.kpOnMultiMonthART("FSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "MMD_MSM", "Multi month appointments",
		    ReportUtils.map(monthlyReportIndicator.kpOnMultiMonthART("MSM"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "MMD_MSW", "Multi month appointments",
		    ReportUtils.map(monthlyReportIndicator.kpOnMultiMonthART("MSW"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "MMD_PWUD", "Multi month appointments",
		    ReportUtils.map(monthlyReportIndicator.kpOnMultiMonthART("PWUD"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "MMD_PWID", "Multi month appointments",
		    ReportUtils.map(monthlyReportIndicator.kpOnMultiMonthART("PWID"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "MMD_Transman", "Multi month appointments",
		    ReportUtils.map(monthlyReportIndicator.kpOnMultiMonthART("Transman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		EmrReportingUtils.addRow(cohortDsd, "MMD_Transwoman", "Multi month appointments",
		    ReportUtils.map(monthlyReportIndicator.kpOnMultiMonthART("Transwoman"), indParams), kpAgeDisaggregation,
		    Arrays.asList("01", "02", "03", "04", "05"));
		
		return cohortDsd;
		
	}
	
}
