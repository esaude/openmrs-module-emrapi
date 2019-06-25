package org.openmrs.module.emrapi.web.controller;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.openmrs.util.LocaleUtility.getDefaultLocale;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openmrs.CodedOrFreeText;
import org.openmrs.Concept;
import org.openmrs.ConceptName;
import org.openmrs.ConditionClinicalStatus;
import org.openmrs.Patient;
import org.openmrs.api.ConceptService;
import org.openmrs.api.ConditionService;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;

import org.openmrs.module.webservices.rest.web.v1_0.controller.BaseRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * This class specifies data manipulation methods on a Condition.
 */
@Controller
public class AllergyController extends BaseRestController{
	
}

