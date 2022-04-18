package org.mycompany.request;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

public class DataRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonAlias({ "username"})
	private String account;
	
	@JsonAlias({ "pass"})
	private String password;
	@JsonAlias({ "datas"})
	private List<org.mycompany.entity.StorageDataParam> data;
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
	public List<org.mycompany.entity.StorageDataParam> getData() {
		return data;
	}
	public void setData(List<org.mycompany.entity.StorageDataParam> data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "DataRequest [account=" + account + ", password=" + password + ", data=" + data.stream().map(e -> e.toString()).reduce("", String::concat) + "]";
	}
	
	
	

}
