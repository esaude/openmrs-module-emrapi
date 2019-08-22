package org.openmrs.module.emrapi.patientStatusState.model;

import java.text.ParseException;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.patientStatusState.JsonDateSerializer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class PatientStatusState {
	
	int id;
	int patient_id;
	String patient_state;
	String patient_status;
	int creator;
	Date date_created;
	String uuid;
	String patientUuid;
	String creatorUuid;
	

	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	public String getPatient_state() {
		return patient_state;
	}
	public void setPatient_state(String patient_state) {
		this.patient_state = patient_state;
	}
	public String getPatient_status() {
		return patient_status;
	}
	public void setPatient_status(String patient_status) {
		this.patient_status = patient_status;
	}
	public int getCreator() {
		return creator;
	}
	public void setCreator(int creator) {
		this.creator = creator;
	}
	
	public Date getDate_created() throws ParseException {
		return date_created;
	}

	public void setDate_created(Date date_created) throws ParseException {
		this.date_created = date_created;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public String getPatientUuid() {
		return patientUuid;
	}
	public void setPatientUuid(String patientUuid) {
		this.patientUuid = patientUuid;
	}
	
	public String getCreatorUuid() {
		return creatorUuid;
	}
	public void setCreatorUuid(String creatorUuid) {
		this.creatorUuid = creatorUuid;
	}
	
	@Override
	public String toString() {
		return "PatientStatusState [id=" + id + ", patient_id=" + patient_id + ", patient_state=" + patient_state
				+ ", patient_status=" + patient_status + ", creator=" + creator + ", date_created=" + date_created
				+ ", uuid=" + uuid + "]";
	}
	
}
