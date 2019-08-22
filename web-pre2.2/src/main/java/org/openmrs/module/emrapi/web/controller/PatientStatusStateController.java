package org.openmrs.module.emrapi.web.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openmrs.module.emrapi.conditionslist.contract.Condition;
import org.openmrs.module.emrapi.conditionslist.contract.ConditionHistory;
import org.openmrs.module.emrapi.patientStatusState.model.PatientStatusState;
import org.patientStatusState.service.PatientStatusStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/rest/emrapi")
public class PatientStatusStateController {
	
	PatientStatusStateService patientStatusStateService;

	@Autowired
	public PatientStatusStateController(PatientStatusStateService patientStatusStateService) {
		
		this.patientStatusStateService = patientStatusStateService;
	}
	
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/setPatientStatusState")
	@ResponseBody
	public PatientStatusState save(@RequestBody PatientStatusState patientStatusState) throws ParseException {
		patientStatusState.setDate_created(new Date());
		return patientStatusStateService.savePatientStatusState(patientStatusState);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/patientStatusState")
	@ResponseBody
	public List<PatientStatusState> getPatientStatusState(@RequestParam("patientUuid") String patientUuid) {
		List<PatientStatusState> patientStatusStatelist = patientStatusStateService.getPatientStatusState(patientUuid);

		return patientStatusStatelist;
	}
	

}
