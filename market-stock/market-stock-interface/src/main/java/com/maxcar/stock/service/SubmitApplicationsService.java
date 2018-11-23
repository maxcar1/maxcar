package com.maxcar.stock.service;

import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.stock.pojo.CarReview;

public interface SubmitApplicationsService {
    public InterfaceResult insertRecord(CarReview carReview);
}
