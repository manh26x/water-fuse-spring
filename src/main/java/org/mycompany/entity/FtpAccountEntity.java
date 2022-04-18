package org.mycompany.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "datatransmission")
public class FtpAccountEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1822034336084219122L;

	@javax.persistence.Id
	private Long Id;
	
	@Column(name="FTPAddress")
	private String ftpAddress;
	
	@Column(name="Account")
	private String account;
	
	@Column(name="Password")
	private String password;
	
	@Column(name="Protocol")
	private String protocol;
	
	@Column(name="Port")
	private Integer port;
	
	@Column(name="Status")
	private Boolean status;

	@Column(name="workingdirectory")
	private String workingDirectory;
	
	@Column(name="datatype")
	private String dataType;
	
	public FtpAccountEntity() {
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getFtpAddress() {
		return ftpAddress;
	}

	public void setFtpAddress(String ftpAddress) {
		this.ftpAddress = ftpAddress;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getWorkingDirectory() {
		return workingDirectory;
	}

	public void setWorkingDirectory(String workingDirectory) {
		this.workingDirectory = workingDirectory;
	}
	
	

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	@Override
	public String toString() {
		return "FtpAccountEntity [Id=" + Id + ", ftpAddress=" + ftpAddress + ", account=" + account + ", password="
				+ password + ", protocol=" + protocol + ", port=" + port + ", status=" + status + ", workingDirectory="
				+ workingDirectory + "]";
	}
	
	
	
}
