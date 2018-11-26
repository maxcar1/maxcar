package com.maxcar.stock.service;

import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.stock.pojo.CarWarningExcel;
import com.maxcar.stock.pojo.HisWarning;

import java.util.List;

public interface ReviewListService {

    InterfaceResult getHisWarningList(HisWarning hisWarning)throws Exception;

    InterfaceResult carWarningExcel(CarWarningExcel carWarningExcel)throws Exception;
}
