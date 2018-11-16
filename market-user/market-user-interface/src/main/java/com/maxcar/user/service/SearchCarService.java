package com.maxcar.user.service;

import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.user.entity.SearchCar;

public interface SearchCarService {

    /**
     * 新增或者修改消费者信息
     * @param searchCar
     * @return
     * @throws Exception
     */
    InterfaceResult saveOrUpdateSearchCar(SearchCar searchCar)throws Exception;
}
