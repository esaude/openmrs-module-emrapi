package org.openmrs.module.emrapi.allergy.impl;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openmrs.Concept;
import org.openmrs.Patient;
import org.openmrs.api.AdministrationService;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;

import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.emrapi.allergy.Allergy_New;
import org.openmrs.module.emrapi.allergy.AllergyHistory;
import org.openmrs.module.emrapi.allergy.AllergyListConstant;
import org.openmrs.module.emrapi.allergy.AllergyService;
import org.openmrs.module.emrapi.allergy.db.AllergyDAO;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

public class AllergyServiceImpl extends BaseOpenmrsService implements AllergyService {
	
	private AllergyDAO allergyDAO;
	
	private ConceptService conceptService;
	
	private AdministrationService administrationService;
	
	public AllergyServiceImpl(AllergyDAO allergyDAO, ConceptService conceptService,
	                            AdministrationService administrationService) {
		this.allergyDAO = allergyDAO;
		this.conceptService = conceptService;
		this.administrationService = administrationService;
	}
	
	@Override
	public Allergy_New save(Allergy_New allergy_New) {
		Date endDate = allergy_New.getEndDate() != null ? allergy_New.getEndDate() : new Date();
		if (allergy_New.getEndReason() != null) {
			allergy_New.setEndDate(endDate);
		}
		Allergy_New existingAllergy = getAllergyByUuid(allergy_New.getUuid());
		if (allergy_New.equals(existingAllergy)) {
			return existingAllergy;
		}
		if (existingAllergy == null) {
			return allergyDAO.saveOrUpdate(allergy_New);
		}
		allergy_New = Allergy_New.newInstance(allergy_New);
		allergy_New.setPreviousAllergy(existingAllergy);
		if (existingAllergy.getStatus().equals(allergy_New.getStatus())) {
			existingAllergy.setVoided(true);
			allergyDAO.saveOrUpdate(existingAllergy);
			return allergyDAO.saveOrUpdate(allergy_New);
		}
		Date onSetDate = allergy_New.getOnsetDate() != null ? allergy_New.getOnsetDate() : new Date();
		existingAllergy.setEndDate(onSetDate);
		allergyDAO.saveOrUpdate(existingAllergy);
		allergy_New.setOnsetDate(onSetDate);
		return allergyDAO.saveOrUpdate(allergy_New);
	}
	
	@Override
	public Allergy_New voidAllergy(Allergy_New allergy_New, String voidReason) {
		if (!StringUtils.hasLength(voidReason)) {
			throw new IllegalArgumentException("voidReason cannot be empty or null");
		}
		return allergyDAO.saveOrUpdate(allergy_New);
	}
	
	@Override
	public Allergy_New getAllergyByUuid(String uuid) {
		return allergyDAO.getAllergyByUuid(uuid);
	}
	
	public List<AllergyHistory> getAllergyHistory(Patient patient) {
		List<Allergy_New> allergyList = allergyDAO.getAllergyHistory(patient);
		Map<String, AllergyHistory> allAllergies = new LinkedHashMap<String, AllergyHistory>();
		for (Allergy_New allergy_New : allergyList) {
			Concept concept = allergy_New.getConcept();
			
			String nonCodedConceptUuid = administrationService.getGlobalProperty(
					AllergyListConstant.GLOBAL_PROPERTY_NON_CODED_UUID);
			
			String key = concept.getUuid().equals(nonCodedConceptUuid) ?
					allergy_New.getAllergyNonCoded() :
					concept.getUuid();
					AllergyHistory allergyHistory = allAllergies.get(key);
			if (allergyHistory != null) {
				allergyHistory.getAllergies().add(allergy_New);
			} else {
				allergyHistory = new AllergyHistory();
				List<Allergy_New> allergy_News = new ArrayList<Allergy_New>();
				allergy_News.add(allergy_New);
				allergyHistory.setAllergies(allergy_News);
				allergyHistory.setAllergy(allergy_New.getConcept());
				if (concept.getUuid().equals(nonCodedConceptUuid)) {
					allergyHistory.setNonCodedAllergy(allergy_New.getAllergyNonCoded());
				}
			}
			allAllergies.put(key, allergyHistory);
		}
		return new ArrayList<AllergyHistory>(allAllergies.values());
	}
	
	@Override
	public List<Allergy_New> getActiveAllergy(Patient patient) {
		return allergyDAO.getActiveAllergies(patient);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Concept> getEndReasonConcepts() {
		return getSetMembersOfConceptSetFromGP(AllergyListConstant.GP_END_REASON_CONCEPT_SET_UUID);
	}
	
	private List<Concept> getSetMembersOfConceptSetFromGP(String globalProperty) {
		String conceptUuid = administrationService.getGlobalProperty(globalProperty);
		Concept concept = conceptService.getConceptByUuid(conceptUuid);
		if (concept != null && concept.isSet()) {
			return concept.getSetMembers();
		}
		return Collections.emptyList();
	}

}
