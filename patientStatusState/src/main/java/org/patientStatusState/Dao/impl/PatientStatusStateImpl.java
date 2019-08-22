package org.patientStatusState.Dao.impl;

import java.util.List;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.emrapi.patientStatusState.model.PatientStatusState;
import org.patientStatusState.Dao.PatientStatusStateDao;
import org.springframework.transaction.annotation.Transactional;

public class PatientStatusStateImpl implements PatientStatusStateDao {

	protected static final Log log = LogFactory.getLog(PatientStatusStateImpl.class);
	private DbSessionFactory sessionFactory;

	/**
	 * Set session factory
	 *
	 * @param sessionFactory
	 */
	public void setSessionFactory(DbSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override

	@Transactional
	public PatientStatusState savePatientStatusState(PatientStatusState patientStatusState) {
		User creator = Context.getUserService().getUserByUuid(patientStatusState.getCreatorUuid());
		int patient_id = getPatientId(patientStatusState.getPatientUuid());
		UUID uuid = UUID.randomUUID();
		patientStatusState.setUuid(uuid.toString());
		patientStatusState.setPatient_id(patient_id);
		patientStatusState.setCreator(creator.getId());
		sessionFactory.getCurrentSession().save(patientStatusState);
		return patientStatusState;
	}

	@Override

	@Transactional(readOnly = true)
	public List<PatientStatusState> getPatientStatusState(String patientUUID) {
		int patient_id = getPatientId(patientUUID);

		Query query = sessionFactory.getCurrentSession().createQuery(
				"from org.openmrs.module.emrapi.patientStatusState.model.PatientStatusState a where a.patient_id=:patient_id order by date_created desc");
		query.setInteger("patient_id", patient_id);

		return query.list();
	}
	
	private int getPatientId(String patientUuid) {
		int patient_id = 0;
		Query query = sessionFactory.getCurrentSession()
				.createQuery("select personId from org.openmrs.Person a where a.uuid=:uuid ");
		query.setString("uuid", patientUuid);
		List<Integer> id = query.list();

		for (Integer item : id) {

			patient_id = item;

		}
		return patient_id;
	}

}
