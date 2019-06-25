package org.openmrs.module.emrapi.allergy.contract;

import java.util.ArrayList;
import java.util.List;




public class AllergyHistoryMapper {

	private AllergyMapper allergyMapper;
	
	public AllergyHistoryMapper(AllergyMapper allergyMapper) {
		this.allergyMapper = allergyMapper;
	}
	
	public AllergyHistory map(org.openmrs.module.emrapi.allergy.AllergyHistory allergyHistory) {
		AllergyHistory allergyHistoryContract = new AllergyHistory();
		allergyHistoryContract.setConceptUuid(allergyHistory.getAllergy().getUuid());
		
		ArrayList< Allergy_New> allergy_News = new ArrayList< Allergy_New>();
		for (org.openmrs.module.emrapi.allergy. Allergy_New allergy_New : allergyHistory.getAllergies()) {
			allergy_News.add(allergyMapper.map(allergy_New));
		}
		allergyHistoryContract.setAllergies(allergy_News);;
		allergyHistoryContract.setAllergyNonCoded(allergyHistory.getNonCodedAllergy());
		return allergyHistoryContract;
	}
	
	public List<AllergyHistory> map(List<org.openmrs.module.emrapi.allergy.AllergyHistory> allergyHistories100) {
		List<AllergyHistory> allergyHistories101 = new ArrayList<AllergyHistory>();
		for (org.openmrs.module.emrapi.allergy.AllergyHistory allergyHistory : allergyHistories100) {
			allergyHistories101.add(map(allergyHistory));
		}
		return allergyHistories101;
	}
}
