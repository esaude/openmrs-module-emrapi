package org.openmrs.module.emrapi.erpdrug_order.service;

import org.openmrs.api.OpenmrsService;
import org.openmrs.module.emrapi.erpdrug_order.Erpdrug_Order;

import java.util.List;

import org.openmrs.annotation.Authorized;


public interface ErpdrugOrderSevice extends OpenmrsService{
	
	public List<Erpdrug_Order> getDispensedDrugOrder(int locId, String patientUuid);
}
