package org.openmrs.module.emrapi.erpdrug_order.service.impl;

import java.util.List;

import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.emrapi.erpdrug_order.Erpdrug_Order;
import org.openmrs.module.emrapi.erpdrug_order.Dao.ErpDispenseDrugOrderDao;
import org.openmrs.module.emrapi.erpdrug_order.service.ErpdrugOrderSevice;

public class ErpdrugOrderSeviceImpl extends BaseOpenmrsService implements ErpdrugOrderSevice {

	private ErpDispenseDrugOrderDao erpdispenseDrugOrderDao;

	public ErpdrugOrderSeviceImpl(ErpDispenseDrugOrderDao erpdispenseDrugOrderDao) {
		this.erpdispenseDrugOrderDao = erpdispenseDrugOrderDao;

	}
	
	@Override
	public List<Erpdrug_Order> getDispensedDrugOrder(int locId, String patientUuid) {
		return erpdispenseDrugOrderDao.getDispensedDrugOrder(locId, patientUuid);
	}

	
	
	
	
}
