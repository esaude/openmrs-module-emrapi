package org.patientStatusState.service.impl;

import java.util.List;

import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.emrapi.patientStatusState.model.PatientStatusState;
import org.patientStatusState.Dao.PatientStatusStateDao;
import org.patientStatusState.service.PatientStatusStateService;

public class PatientStatusStateServiceImpl extends BaseOpenmrsService implements PatientStatusStateService {

	private PatientStatusStateDao patientStatusStateDao;
	
	
	public PatientStatusStateServiceImpl(PatientStatusStateDao patientStatusStateDao) {
	
		this.patientStatusStateDao = patientStatusStateDao;
	}

	@Override
	public PatientStatusState savePatientStatusState(PatientStatusState patientStatusState) {

		return patientStatusStateDao.savePatientStatusState(patientStatusState);
		
	}

	@Override
	public List<PatientStatusState> getPatientStatusState(String patientUUID) {
		
		return patientStatusStateDao.getPatientStatusState(patientUUID);
	}

}
