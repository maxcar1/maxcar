package com.maxcar.core.entity.mqtt;

public class Car {
	
	private String vin;
	private Float amount;
	private Integer status;
	
	public Car() {
	}
	
	public Car(String vin, Float amount, Integer status) {
		super();
		this.vin = vin;
		this.amount = amount;
		this.status = status;
	}





	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}
	

}
