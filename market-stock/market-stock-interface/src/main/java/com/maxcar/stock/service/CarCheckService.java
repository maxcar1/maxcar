package com.maxcar.stock.service;

import com.maxcar.stock.pojo.CarCheck;

public interface CarCheckService {
	CarCheck getCarCheck(String carId);

	String getCarCheckByVin(CarCheck carCheck);

	void insertCarCheck(CarCheck check);
}
