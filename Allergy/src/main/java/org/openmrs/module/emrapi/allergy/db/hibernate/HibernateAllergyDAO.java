package org.openmrs.module.emrapi.allergy.db.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.openmrs.Patient;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.emrapi.allergy.Allergy_New;
import org.openmrs.module.emrapi.allergy.db.AllergyDAO;

import org.springframework.transaction.annotation.Transactional;

public class HibernateAllergyDAO implements AllergyDAO {

	protected static final Log log = LogFactory.getLog(HibernateAllergyDAO.class);
	
	/**
	 * Hibernate session factory
	 */
	
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
	public Allergy_New saveOrUpdate(Allergy_New allergy_New) {
		sessionFactory.getCurrentSession().saveOrUpdate(allergy_New);
		return allergy_New;
	}
	@Override
	@Transactional(readOnly = true)
	public Allergy_New getAllergyByUuid(String uuid) {
		return (Allergy_New) sessionFactory.getCurrentSession().createQuery("from org.openmrs.module.emrapi.allergy.Allergy_New a where a.uuid = :uuid")
				.setString("uuid", uuid).uniqueResult();
	}
	@Override
	@Transactional(readOnly = true)
	public List<Allergy_New> getActiveAllergies(Patient patient) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from org.openmrs.module.emrapi.allergy.Allergy_New a where a.patient.patientId = :patientId and a.voided = false and a.endDate is null order "
						+ "by a.dateCreated desc");
		query.setInteger("patientId", patient.getId());
		return query.list();
	}
	@Override
	@Transactional(readOnly = true)
	public List<Allergy_New> getAllergyHistory(Patient patient) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"select ala from org.openmrs.module.emrapi.allergy.Allergy_New as ala where ala.patient.patientId = :patientId and ala.voided = false " +
						"order by ala.dateCreated desc");
		query.setInteger("patientId", patient.getId());
		return query.list();
	}
}

