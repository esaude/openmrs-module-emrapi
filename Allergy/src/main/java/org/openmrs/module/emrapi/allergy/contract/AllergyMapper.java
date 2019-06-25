package org.openmrs.module.emrapi.allergy.contract;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.openmrs.util.LocaleUtility.getDefaultLocale;

import java.util.Locale;

import org.openmrs.ConceptName;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.emrapi.allergy.AllergyListConstant;
import org.openmrs.module.emrapi.allergy.contract.Concept;
public class AllergyMapper {
	public Allergy_New map(org.openmrs.module.emrapi.allergy.Allergy_New openmrsAllergy) {
		Concept concept = mapConcept(openmrsAllergy.getConcept());
		Allergy_New allergy_New = new Allergy_New();
		allergy_New.setUuid(openmrsAllergy.getUuid());
		allergy_New.setAdditionalDetail(openmrsAllergy.getAdditionalDetail());
		allergy_New.setStatus(openmrsAllergy.getStatus());
		allergy_New.setConcept(concept);
		allergy_New.setPatientUuid(openmrsAllergy.getPatient().getUuid());
		allergy_New.setAllergyNonCoded(openmrsAllergy.getAllergyNonCoded());
		allergy_New.setOnSetDate(openmrsAllergy.getOnsetDate());
		allergy_New.setVoided(openmrsAllergy.getVoided());
		allergy_New.setVoidReason(openmrsAllergy.getVoidReason());
		allergy_New.setEndDate(openmrsAllergy.getEndDate());
		allergy_New.setCreator(openmrsAllergy.getCreator().getDisplayString());
		allergy_New.setDateCreated(openmrsAllergy.getDateCreated());
		if (openmrsAllergy.getPreviousAllergy() != null) {
			allergy_New.setPreviousAllergyUuid(openmrsAllergy.getPreviousAllergy().getUuid());
		}
		if (openmrsAllergy.getEndReason() != null) {
			allergy_New.setEndReason(mapConcept(openmrsAllergy.getEndReason()));
		}
		return allergy_New;
	}
	
	public org.openmrs.module.emrapi.allergy.Allergy_New map(Allergy_New allergy_New) {
		org.openmrs.Concept concept = Context.getConceptService().getConceptByUuid(allergy_New.getConcept().getUuid());
		Patient patient = Context.getPatientService().getPatientByUuid(allergy_New.getPatientUuid());
		String nonCodedAllergyConcept = Context.getAdministrationService().getGlobalProperty(
				AllergyListConstant.GLOBAL_PROPERTY_NON_CODED_UUID);
		
		org.openmrs.module.emrapi.allergy.Allergy_New openmrsAllergy = new org.openmrs.module.emrapi.allergy.Allergy_New();
		
		if (!isEmpty(allergy_New.getAllergyNonCoded())) {
			concept = Context.getConceptService().getConceptByUuid(nonCodedAllergyConcept);
		}
		if (allergy_New.getEndReason() != null) {
			org.openmrs.Concept endReason = Context.getConceptService().getConceptByUuid(
					allergy_New.getEndReason().getUuid());
			openmrsAllergy.setEndReason(endReason);
		}
		if (allergy_New.getUuid() != null) {
			openmrsAllergy.setUuid(allergy_New.getUuid());
		}
		openmrsAllergy.setAdditionalDetail(allergy_New.getAdditionalDetail());
		openmrsAllergy.setStatus(allergy_New.getStatus());
		openmrsAllergy.setConcept(concept);
		openmrsAllergy.setPatient(patient);
		openmrsAllergy.setAllergyNonCoded(allergy_New.getAllergyNonCoded());
		openmrsAllergy.setOnsetDate(allergy_New.getOnSetDate());
		openmrsAllergy.setEndDate(allergy_New.getEndDate());
		openmrsAllergy.setVoided(allergy_New.getVoided());
		openmrsAllergy.setVoidReason(allergy_New.getVoidReason());
		
		return openmrsAllergy;
	}
	
	private Concept mapConcept(org.openmrs.Concept openmrsConcept) {
		ConceptName fullySpecifiedName = openmrsConcept.getFullySpecifiedName(Context.getLocale());
		if(fullySpecifiedName == null){
			fullySpecifiedName = openmrsConcept.getFullySpecifiedName(getDefaultLocale());
		}
		if(fullySpecifiedName == null){
			fullySpecifiedName = openmrsConcept.getFullySpecifiedName(new Locale("en"));
		}
		Concept concept = new Concept(openmrsConcept.getUuid(), fullySpecifiedName.getName());
		ConceptName shortName = openmrsConcept.getShortNameInLocale(Context.getLocale());
		
		if (shortName != null) {
			concept.setShortName(shortName.getName());
		}
		return concept;
	}
}
