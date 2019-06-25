package org.openmrs.module.emrapi.allergy;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.openmrs.Concept;
import org.openmrs.module.emrapi.allergy.Allergy_New;

public class AllergyHistory {
private String nonCodedAllergy;
	
	private Concept allergy;
	
	private List<Allergy_New> allergy_News;
	
	public String getNonCodedAllergy() {
		return nonCodedAllergy;
	}
	
	public void setNonCodedAllergy(String nonCodedAllergy) {
		this.nonCodedAllergy = nonCodedAllergy;
	}
	
	public Concept getAllergy() {
		return allergy;
	}
	
	public void setAllergy(Concept allergy) {
		this.allergy = allergy;
	}
	
	public List<Allergy_New> getAllergies() {
		return allergy_News;
	}
	
	public void setAllergies(List<Allergy_New> allergy_News) {
		this.allergy_News = allergy_News;
	}
	
	@Override
	public String toString() {
		String name = nonCodedAllergy;
		if (name != null && allergy != null && allergy.getName() != null) {
			name = allergy.getName().getName();
		}
		
		return new ToStringBuilder(this).append("allergy", name).append("count", allergy_News.size()).build();
	}
}
