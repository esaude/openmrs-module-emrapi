package org.openmrs.module.emrapi.allergy;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.BaseOpenmrsData;
import org.openmrs.Concept;
import org.openmrs.Patient;


public class Allergy_New extends BaseOpenmrsData implements java.io.Serializable{

	public static final long serialVersionUID = 2L;
	
	protected final Log log = LogFactory.getLog(getClass());
	
	/**
	 * default empty constructor
	 */
	public Allergy_New() {
	}
	
	/**
	 * @param allergyId Integer to create this allergy object from
	 */
	public Allergy_New(Integer allergyId) {
		this.allergyId = allergyId;
	}
	
	public enum Status {
		ACTIVE, INACTIVE, HISTORY_OF
	}
	
	private Integer allergyId;
	
	private Allergy_New previousAllergy;
	
	private Patient patient;
	
	private Status status = Status.ACTIVE;
	
	private Concept concept;
	
	private String allergyNonCoded;
	
	private Date onsetDate;
	
	private String additionalDetail;
	
	private Date endDate;
	
	private Concept endReason;
	
	public static Allergy_New newInstance(Allergy_New allergy_New) {
		return copy(allergy_New, new Allergy_New());
	}
	
	public static Allergy_New copy(Allergy_New fromAllergy, Allergy_New toAllergy) {
		toAllergy.setPreviousAllergy(fromAllergy.getPreviousAllergy());
		toAllergy.setPatient(fromAllergy.getPatient());
		toAllergy.setStatus(fromAllergy.getStatus());
		toAllergy.setConcept(fromAllergy.getConcept());
		toAllergy.setAllergyNonCoded(fromAllergy.getAllergyNonCoded());
		toAllergy.setOnsetDate(fromAllergy.getOnsetDate());
		toAllergy.setAdditionalDetail(fromAllergy.getAdditionalDetail());
		toAllergy.setEndDate(fromAllergy.getEndDate());
		toAllergy.setEndReason(fromAllergy.getEndReason());
		toAllergy.setVoided(fromAllergy.getVoided());
		toAllergy.setVoidedBy(fromAllergy.getVoidedBy());
		toAllergy.setVoidReason(fromAllergy.getVoidReason());
		toAllergy.setDateVoided(fromAllergy.getDateVoided());
		return toAllergy;
	}
	
	/**
	 * @return Returns the allergyId.
	 */
	public Integer getAllergyId() {
		return allergyId;
	}
	
	/**
	 * @param allergyId The allergyId to set.
	 */
	public void setAllergyId(Integer allergyId) {
		this.allergyId = allergyId;
	}
	
	/**
	 * @return Returns the previousallergy.
	 */
	public Allergy_New getPreviousAllergy() {
		return previousAllergy;
	}
	
	/**
	 * @param previousallergy The previousallergy to set.
	 *                          When a allergy is altered (e.g., a symptom explicitly converted into a diagnosis), this
	 *                          field
	 *                          is used to link the new allergy to the allergy(s) it has replaced.
	 */
	public void setPreviousAllergy(Allergy_New previousAllergy) {
		this.previousAllergy = previousAllergy;
	}
	
	/**
	 * @return Returns the patient.
	 */
	public Patient getPatient() {
		return patient;
	}
	
	/**
	 * @param patient The patient to set.
	 */
	public void setPatient(Patient patient) {
		if (getAllergyId() != null && getPatient() != null && !getPatient().equals(patient)) {
			throw new IllegalArgumentException("Patient cannot be changed");
		}
		this.patient = patient;
	}
	
	/**
	 * @return Returns the status.
	 */
	public Status getStatus() {
		return status;
	}
	
	/**
	 * @param status The status to set.
	 *               The clinical status of the allergy.  Default is ACTIVE.
	 *               <ul>
	 *               <li><b>ACTIVE</b> when the allergy is suspected, but not yet confirmed
	 *               (HL7 uses the term "working")</li>
	 *               <li><b>INACTIVE</b> when the allergy has been confirmed (typically for
	 *               diagnoses)</li>
	 *               <li><b>HISTORY_OF</b> when the history of a allergy is relevant to the
	 *               patient's ongoing medical care (e.g., history of stroke)</li>
	 *               </ul>
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	
	/**
	 * @return Returns the concept.
	 */
	public Concept getConcept() {
		return concept;
	}
	
	/**
	 * @param concept The concept to set.
	 */
	public void setConcept(Concept concept) {
		if (getAllergyId() != null && getConcept() != null && !getConcept().equals(concept)) {
			throw new IllegalArgumentException("Concept cannot be changed");
		}
		this.concept = concept;
	}
	
	/**
	 * @return Returns the allergyNonCoded.
	 */
	public String getAllergyNonCoded() {
		return allergyNonCoded;
	}
	
	/**
	 * @param allergyNonCoded The allergyNonCoded to set.
	 *                          When a allergy is not codified, the concept for the allergy is set to a concept for
	 *                          NON-CODED and the free text representation of the allergy is stored here.
	 */
	public void setAllergyNonCoded(String allergyNonCoded) {
		if (getAllergyId() != null && getAllergyNonCoded() != null && !getAllergyNonCoded().equals(
				allergyNonCoded)) {
			throw new IllegalArgumentException("allergy non coded cannot be changed");
		}
		this.allergyNonCoded = allergyNonCoded;
	}
	
	/**
	 * @return Returns the onsetDate.
	 */
	public Date getOnsetDate() {
		return onsetDate;
	}
	
	/**
	 * @param onsetDate The onsetDate to set.
	 */
	public void setOnsetDate(Date onsetDate) {
		this.onsetDate = onsetDate;
	}
	
	/**
	 * @return Returns the additionalDetail.
	 */
	public String getAdditionalDetail() {
		return additionalDetail;
	}
	
	/**
	 * @param additionalDetail The additionalDetail to set.
	 *                         Additional detail about the allergy.  This is used to further refine the concept and
	 *                         <em>not</em> meant for encounter-specific detail or notes.  For example, detail
	 *                         such as "left more than right" or "diagnosed by chest x-ray 5-June-2010" would be
	 *                         appropriate additional detail; however, "hurts worse today" would not, since the
	 *                         additional detail is assumed to be refining the allergy and not providing encounter-
	 *                         specific information.
	 */
	public void setAdditionalDetail(String additionalDetail) {
		this.additionalDetail = additionalDetail;
	}
	
	/**
	 * @return Returns the endDate.
	 */
	
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * @param endDate The endDate to set.
	 */
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * @return Returns the endReason.
	 */
	
	public Concept getEndReason() {
		return endReason;
	}
	
	/**
	 * @param endReason The endReason to set.
	 */
	
	public void setEndReason(Concept endReason) {
		this.endReason = endReason;
	}
	
	/**
	 * @see org.openmrs.OpenmrsObject#getId()
	 */
	@Override
	public Integer getId() {
		return getAllergyId();
	}
	
	/**
	 * @see org.openmrs.OpenmrsObject#setId(java.lang.Integer)
	 */
	@Override
	public void setId(Integer allergyId) {
		setAllergyId(allergyId);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		
		Allergy_New allergy_New = (Allergy_New) o;
		
		if (!patient.equals(allergy_New.patient)) {
			return false;
		}
		if (status != allergy_New.status) {
			return false;
		}
		if (!concept.equals(allergy_New.concept)) {
			return false;
		}
		if (allergyNonCoded != null ?
				!allergyNonCoded.equals(allergy_New.allergyNonCoded) :
					allergy_New.allergyNonCoded != null) {
			return false;
		}
		if (onsetDate != null ? !onsetDate.equals(allergy_New.onsetDate) : allergy_New.onsetDate != null) {
			return false;
		}
		if (additionalDetail != null ?
				!additionalDetail.equals(allergy_New.additionalDetail) :
					allergy_New.additionalDetail != null) {
			return false;
		}
		if (endDate != null ? !endDate.equals(allergy_New.endDate) : allergy_New.endDate != null) {
			return false;
		}
		return endReason != null ? endReason.equals(allergy_New.endReason) : allergy_New.endReason == null;
	}
}
