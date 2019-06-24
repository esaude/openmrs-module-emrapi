package org.openmrs.module.emrapi.allergy.db;

import java.util.List;

import org.openmrs.Patient;
import org.openmrs.module.emrapi.allergy.Allergy_New;

public interface AllergyDAO {
	
    Allergy_New saveOrUpdate(Allergy_New allergy_New);
	
	Allergy_New getAllergyByUuid(String uuid);
	
	List<Allergy_New> getAllergyHistory(Patient patient);
	
	List<Allergy_New> getActiveAllergies(Patient patient);
	
}
