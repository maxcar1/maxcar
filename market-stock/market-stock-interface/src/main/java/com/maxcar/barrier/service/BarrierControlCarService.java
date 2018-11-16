package com.maxcar.barrier.service;

import com.github.pagehelper.PageInfo;
import com.maxcar.barrier.pojo.BarrierControlCar;
import com.maxcar.base.service.BaseService;

/**
 * @Author sunyazhou
 * @Date 2018/10/24 13:54
 * @desc
 */
public interface BarrierControlCarService extends BaseService<BarrierControlCar,String> {

    @Override
    int insertSelective(BarrierControlCar record);

    /**
     * 查询黑白名单配置信息
     * @param controlCar
     * @return
     */
    PageInfo selectAllByListType(BarrierControlCar controlCar);
}
