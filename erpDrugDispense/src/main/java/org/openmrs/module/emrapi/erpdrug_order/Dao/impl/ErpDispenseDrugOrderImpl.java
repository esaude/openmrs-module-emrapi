package org.openmrs.module.emrapi.erpdrug_order.Dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.emrapi.erpdrug_order.Erpdrug_Order;
import org.openmrs.module.emrapi.erpdrug_order.Dao.ErpDispenseDrugOrderDao;
import org.springframework.transaction.annotation.Transactional;

public class ErpDispenseDrugOrderImpl implements ErpDispenseDrugOrderDao {

	protected static final Log log = LogFactory.getLog(ErpDispenseDrugOrderImpl.class);
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
	@Transactional(readOnly = true)
	public List<Erpdrug_Order> getDispensedDrugOrder(int locId, String patientUuid) {

		int patient_id = getPatientId(patientUuid);

		/*
		 * return (List<Erpdrug_Order>) sessionFactory.getCurrentSession().
		 * createQuery("from org.openmrs.module.emrapi.erpdrug_order.Erpdrug_Order")
		 * 
		 * .list();
		 */

		Query query = sessionFactory.getCurrentSession().createQuery(
				"from org.openmrs.module.emrapi.erpdrug_order.Erpdrug_Order a where a.patient_id=:patient_id");
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
