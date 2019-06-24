package org.openmrs.module.emrapi.allergy.contract;

import java.util.List;

import org.openmrs.module.emrapi.allergy.contract.Allergy_New;

public class AllergyHistory {
private String allergyNonCoded;
	
	private String conceptUuid;
	
	private List<Allergy_New> allergy_News;
	
	public String getAllergyNonCoded() {
		return allergyNonCoded;
	}
	
	public void setAllergyNonCoded(String allergyNonCoded) {
		this.allergyNonCoded = allergyNonCoded;
	}
	
	public String getConceptUuid() {
		return conceptUuid;
	}
	
	public void setConceptUuid(String conceptUuid) {
		this.conceptUuid = conceptUuid;
	}
	
	public List<Allergy_New> getAllergies() {
		return allergy_News;
	}
	
	public void setAllergies(List<Allergy_New> allergy_News) {
		this.allergy_News = allergy_News;
	}
}
