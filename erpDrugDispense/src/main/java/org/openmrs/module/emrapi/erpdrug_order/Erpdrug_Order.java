package org.openmrs.module.emrapi.erpdrug_order;


import java.util.Date;


public class Erpdrug_Order {
	

	private int id;
	private int order_id;
	private int patient_id;
	private String status;
	private Boolean dispensed;
	private Boolean arv_dispensed;
	private Boolean first_arv_dispensed;
	private Date dispensed_date;
	private int encounter_id;
	private int location_id;
	private int creator;
	private Date date_created;
	private String uuid;

	
	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getDispensed() {
		return dispensed;
	}

	public void setDispensed(Boolean dispensed) {
		this.dispensed = dispensed;
	}

	public int getEncounter_id() {
		return encounter_id;
	}

	public void setEncounter_id(int encounter_id) {
		this.encounter_id = encounter_id;
	}

	public int getLocation_id() {
		return location_id;
	}

	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}

	public int getCreator() {
		return creator;
	}

	public void setCreator(int creator) {
		this.creator = creator;
	}

	public Date getDate_created() {

		return date_created;
	}

	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean getArv_dispensed() {
		return arv_dispensed;
	}

	public void setArv_dispensed(Boolean arv_dispensed) {
		this.arv_dispensed = arv_dispensed;
	}

	public Boolean getFirst_arv_dispensed() {
		return first_arv_dispensed;
	}

	public void setFirst_arv_dispensed(Boolean first_arv_dispensed) {
		this.first_arv_dispensed = first_arv_dispensed;
	}

	public Date getDispensed_date() {
		return dispensed_date;
	}

	public void setDispensed_date(Date dispensed_date) {
		this.dispensed_date = dispensed_date;
	}

	@Override
	public String toString() {
		return "Erpdrug_Order [id=" + id + ", order_id=" + order_id + ", patient_id=" + patient_id + ", status="
				+ status + ", dispensed=" + dispensed + ", arv_dispensed=" + arv_dispensed + ", first_arv_dispensed="
				+ first_arv_dispensed + ", dispensed_date=" + dispensed_date + ", encounter_id=" + encounter_id
				+ ", location_id=" + location_id + ", creator=" + creator + ", date_created=" + date_created + ", uuid="
				+ uuid + "]";
	}


}
