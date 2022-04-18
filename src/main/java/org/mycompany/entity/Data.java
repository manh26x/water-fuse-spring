package org.mycompany.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.mycompany.config.DateJsonDeserialize;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public abstract class Data {
	
	@JsonAlias({ "mathongso"})
	@Column(name="parametername")
	protected String parameterName;
	
	@JsonAlias({ "maTram"})
	@Column(name="stationcode")
	protected String stationCode;
	
	@JsonAlias({ "trangThaiThietBi"})
	@Column(name="devicestatus")
	protected String deviceStatus;
	
	
	@Column(name="value")
	protected Float value;
	
	@Column(name="unit")
	protected String unit;
	
	@JsonAlias({ "thoigian" })
	@JsonDeserialize(using=DateJsonDeserialize.class)
	@Column(name="time")
	protected Date time;
	
	@Column(name="status")
	protected Boolean status;

	public Data() {
		super();
		this.status = true;
	}




	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

}
