package org.mycompany.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.mycompany.config.DateJsonDeserialize;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Data {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;



	@JsonAlias({ "mathongso"})
	@Column(name="parametername")
	private String parameterName;
	
	@JsonAlias({ "maTram"})
	@Column(name="stationcode")
	private String stationCode;
	
	@JsonAlias({ "trangThaiThietBi"})
	@Column(name="devicestatus")
	private String deviceStatus;
	
	
	@Column(name="value")
	private Float value;
	
	@Column(name="unit")
	private String unit;
	
	@JsonAlias({ "thoigian" })
	@JsonDeserialize(using=DateJsonDeserialize.class)
	@Column(name="time")
	private Date time;
	
	@Column(name="status")
	private Boolean status;
	public Data() {
		super();
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




	public void setConstructionName(String string) {
	}




	public Object getConstructionName() {
		return null;
	}

}
