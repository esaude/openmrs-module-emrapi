package org.openmrs.module.emrapi.allergy.contract;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.openmrs.module.emrapi.allergy.contract.Concept;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Allergy_New {
	
	public String uuid;
	
	private String patientUuid;
	
	private Concept concept;
	
	private String allergyNonCoded;
	
	private org.openmrs.module.emrapi.allergy.Allergy_New.Status status;
	
	private Date onSetDate;
	
	private Date endDate;
	
	private Concept endReason;
	
	private String additionalDetail;
	
	private Boolean voided;
	
	private String voidReason;
	
	private String creator;
	
	private Date dateCreated;
	
	private String previousAllergyUuid;
	
	
	
	public Date getDateCreated() {
		return dateCreated;
	}
	
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public String getCreator() {
		return creator;
	}
	
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	

	
	public String getAllergyNonCoded() {
		return allergyNonCoded;
	}

	public void setAllergyNonCoded(String allergyNonCoded) {
		this.allergyNonCoded = allergyNonCoded;
	}

	public String getPreviousAllergyUuid() {
		return previousAllergyUuid;
	}

	public void setPreviousAllergyUuid(String previousAllergyUuid) {
		this.previousAllergyUuid = previousAllergyUuid;
	}

	public Date getOnSetDate() {
		return onSetDate;
	}
	
	public void setOnSetDate(Date onSetDate) {
		this.onSetDate = onSetDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Concept getEndReason() {
		return endReason;
	}
	
	public void setEndReason(Concept endReason) {
		this.endReason = endReason;
	}
	
	public String getAdditionalDetail() {
		return additionalDetail;
	}
	
	public void setAdditionalDetail(String additionalDetail) {
		this.additionalDetail = additionalDetail;
	}
	
	public Boolean getVoided() {
		return voided;
	}
	
	public void setVoided(Boolean voided) {
		this.voided = voided;
	}
	
	public String getVoidReason() {
		return voidReason;
	}
	
	public void setVoidReason(String voidReason) {
		this.voidReason = voidReason;
	}
	
	public org.openmrs.module.emrapi.allergy.Allergy_New.Status getStatus() {
		return status;
	}
	
	public void setStatus(org.openmrs.module.emrapi.allergy.Allergy_New.Status status) {
		this.status = status;
	}
	
	public String getPatientUuid() {
		return patientUuid;
	}
	
	public void setPatientUuid(String patientUuid) {
		this.patientUuid = patientUuid;
	}
	
	public Concept getConcept() {
		return concept;
	}
	
	public void setConcept(Concept concept) {
		this.concept = concept;
	}
	
}
