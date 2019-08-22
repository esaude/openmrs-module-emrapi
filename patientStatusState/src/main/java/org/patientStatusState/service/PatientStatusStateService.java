package org.patientStatusState.service;

import java.util.List;

import org.openmrs.api.OpenmrsService;
import org.openmrs.module.emrapi.patientStatusState.model.PatientStatusState;
@Deprecated
public interface PatientStatusStateService extends OpenmrsService{

	
	public PatientStatusState savePatientStatusState(PatientStatusState patientStatusState);
	
	public List<PatientStatusState> getPatientStatusState(String patientUUID);
	
	
}
