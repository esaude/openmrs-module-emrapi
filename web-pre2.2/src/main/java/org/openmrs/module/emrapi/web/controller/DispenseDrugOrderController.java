package org.openmrs.module.emrapi.web.controller;

import java.util.List;

import org.openmrs.module.emrapi.erpdrug_order.Erpdrug_Order;
import org.openmrs.module.emrapi.erpdrug_order.service.ErpdrugOrderSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/rest/emrapi")
public class DispenseDrugOrderController {
	 ErpdrugOrderSevice erpdrugOrderSevice;

	@Autowired
	public DispenseDrugOrderController(ErpdrugOrderSevice erpdrugOrderSevice) {
		this.erpdrugOrderSevice = erpdrugOrderSevice;

	}

	@RequestMapping(method = RequestMethod.GET, value = "/dispensedrugorder")
	@ResponseBody
	public List<Erpdrug_Order> getDispenseDrugOrders(@RequestParam("locId") int locId,
			@RequestParam("patientUuid") String patientUuid) {
		List<Erpdrug_Order> erpdrug_Order = erpdrugOrderSevice.getDispensedDrugOrder(locId, patientUuid);

		return erpdrug_Order;
	}
}