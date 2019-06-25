package org.openmrs.module.emrapi.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.ConceptService;
import org.openmrs.api.PatientService;
import org.openmrs.module.emrapi.allergy.AllergyService;
import org.openmrs.module.emrapi.allergy.AllergyListConstant;
import org.openmrs.module.emrapi.allergy.contract.Allergy_New;
import org.openmrs.module.emrapi.allergy.contract.AllergyHistory;
import org.openmrs.module.emrapi.allergy.contract.AllergyHistoryMapper;
import org.openmrs.module.emrapi.allergy.contract.AllergyMapper;
import org.openmrs.module.webservices.rest.web.v1_0.controller.BaseRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/rest/emrapi")
public class AllergyController extends BaseRestController {
	private Log log = LogFactory.getLog(this.getClass());
	AllergyMapper allergyMapper = new AllergyMapper();
	
	AllergyHistoryMapper allergyHistoryMapper = new AllergyHistoryMapper(allergyMapper);
	
	AllergyService allergyService;
	
	PatientService patientService;
	
	ConceptService conceptService;
	
	@Autowired
	public AllergyController(AllergyService allergyService, PatientService patientService,
	                           ConceptService conceptService) {
		this.allergyService = allergyService;
		this.patientService = patientService;
		this.conceptService = conceptService;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/allergyhistory")
	@ResponseBody
	public List<AllergyHistory> getAllergyHistory(@RequestParam("patientUuid") String patientUuid) {
		
		List<org.openmrs.module.emrapi.allergy.AllergyHistory> allergyHistory = allergyService.getAllergyHistory(
				patientService.getPatientByUuid(patientUuid));
		
		return allergyHistoryMapper.map(allergyHistory);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/allergy")
	@ResponseBody
	public List<Allergy_New> save(@RequestBody Allergy_New[] Allergies) {
		
		List<Allergy_New> savedAllergies = new ArrayList<Allergy_New>();
		for (Allergy_New allergy_New : Allergies) {
			org.openmrs.module.emrapi.allergy.Allergy_New savedAllergy = allergyService.save(allergyMapper.map(allergy_New));
			savedAllergies.add(allergyMapper.map(savedAllergy));
		}
		return savedAllergies;
	}
}
