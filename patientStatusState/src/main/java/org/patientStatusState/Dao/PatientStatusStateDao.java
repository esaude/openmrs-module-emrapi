package org.patientStatusState.Dao;

import java.util.List;

import org.openmrs.module.emrapi.patientStatusState.model.PatientStatusState;

public interface PatientStatusStateDao {
	
	public PatientStatusState savePatientStatusState(PatientStatusState patientStatusState);
	
	public List<PatientStatusState> getPatientStatusState(String patientUUID);
	
	

}
