package org.openmrs.module.emrapi.allergy;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.api.AdministrationService;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
public class EmrAllergyValidator implements Validator {

	/**
	 * Log for this class and subclasses
	 */
	protected final Log log = LogFactory.getLog(getClass());
	
	private AdministrationService administrationService;
	
	private AllergyService allergyService;
	
	public EmrAllergyValidator(AllergyService allergyService, AdministrationService administrationService) {
		this.allergyService = allergyService;
		this.administrationService = administrationService;
	}
	
	/**
	 * Determines if the command object being submitted is a valid type
	 *
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public boolean supports(Class c) {
		return Allergy_New.class.isAssignableFrom(c);
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		
		Allergy_New allergy_New = (Allergy_New) obj;
		if (allergy_New == null) {
			errors.reject("error.general");
		} else {
			ValidationUtils.rejectIfEmpty(errors, "patient", "error.null");
			ValidationUtils.rejectIfEmpty(errors, "status", "error.null");
			ValidationUtils.rejectIfEmpty(errors, "creator", "error.null");
			ValidationUtils.rejectIfEmpty(errors, "concept", "error.null");
			ValidationUtils.rejectIfEmpty(errors, "voided", "error.null");
			ValidationUtils.rejectIfEmpty(errors, "dateCreated", "error.null");
			ValidationUtils.rejectIfEmpty(errors, "uuid", "error.null");
			
			validateNonCodedAllergy(allergy_New, errors);
			validateDuplicateAllergies(allergy_New, errors);
			validateEndReasonConcept(allergy_New, errors);
		}
		
	}
	
	private void validateEndReasonConcept(Allergy_New allergy_New, Errors errors) {
		if (allergy_New.getEndReason() == null) {
			if (allergy_New.getEndDate() != null) {
				errors.rejectValue("endReason", "Condition.error.endReasonIsMandatory");
			}
		} else {
			List<Concept> endReasonConcepts = allergyService.getEndReasonConcepts();
			if (!endReasonConcepts.contains(allergy_New.getEndReason())) {
				errors.rejectValue("endReason", "Condition.error.notAmongAllowedConcepts");
			}
		}
	}
	
	private void validateDuplicateAllergies(Allergy_New allergy_New, Errors errors) {
		List<Allergy_New> conditionsForPatient = allergyService.getActiveAllergy(allergy_New.getPatient());
		if (allergy_New.getAllergyNonCoded() != null) {
			for (Allergy_New eachCondition : conditionsForPatient) {
				if (eachCondition.getConcept().equals(allergy_New.getConcept())
						&& eachCondition.getAllergyNonCoded().equalsIgnoreCase(
						allergy_New.getAllergyNonCoded().replaceAll("\\s", "")) && !eachCondition.getUuid().equals(
						allergy_New.getUuid())) {
					errors.rejectValue("concept", "Condition.error.duplicatesNotAllowed");
				}
			}
		}
	}
	
	private void validateNonCodedAllergy(Allergy_New allergy_New, Errors errors) {
		String nonCodedAllergyUuid = administrationService.getGlobalProperty(
				AllergyListConstant.GLOBAL_PROPERTY_NON_CODED_UUID);
		if (allergy_New.getAllergyNonCoded() != null) {
			if (!allergy_New.getConcept().getUuid().equals(nonCodedAllergyUuid)) {
				errors.rejectValue("conditionNonCoded",
						"Condition.error.conditionNonCodedValueNotSupportedForCodedCondition");
			}
		} else {
			if (allergy_New.getConcept().getUuid().equals(nonCodedAllergyUuid)) {
				errors.rejectValue("allergyNonCoded", "Condition.error.conditionNonCodedValueNeededForNonCodedCondition");
			}
		}
	}
}
