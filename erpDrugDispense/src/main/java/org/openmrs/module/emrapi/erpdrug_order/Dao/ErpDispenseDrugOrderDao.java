package org.openmrs.module.emrapi.erpdrug_order.Dao;

import java.util.List;

import org.openmrs.module.emrapi.erpdrug_order.Erpdrug_Order;

public interface ErpDispenseDrugOrderDao {

	public List<Erpdrug_Order> getDispensedDrugOrder(int locId, String patientUuid);

}
