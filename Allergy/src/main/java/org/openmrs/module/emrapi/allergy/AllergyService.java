package org.openmrs.module.emrapi.allergy;

import java.util.List;

import org.openmrs.Concept;
import org.openmrs.Patient;
import org.openmrs.annotation.Authorized;
import org.openmrs.api.OpenmrsService;


@Deprecated
public interface AllergyService extends OpenmrsService {
	
	@Authorized({ PrivilegeAllergyConstant.EDIT_ALLERGIES })
	Allergy_New save(Allergy_New allergy_New);
	
	@Authorized({ PrivilegeAllergyConstant.EDIT_ALLERGIES })
	Allergy_New voidAllergy(Allergy_New allergy_New, String voidReason);
	
	Allergy_New getAllergyByUuid(String uuid);
	
	List<AllergyHistory> getAllergyHistory(Patient patient);
	
	@Authorized({ PrivilegeAllergyConstant.GET_ALLERGIES })
	List<Allergy_New> getActiveAllergy(Patient patient);
	
	List<Concept> getEndReasonConcepts();
}
