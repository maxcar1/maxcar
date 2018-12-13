package com.maxcar.stock.service;

import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.stock.pojo.CarWarningExcel;
import com.maxcar.stock.pojo.HisWarning;

import java.util.List;
import java.util.Map;

public interface ReviewListService {

    InterfaceResult getHisWarningList(HisWarning hisWarning)throws Exception;

    public InterfaceResult carWarningExcel(HisWarning hisWarning)throws Exception;
}
