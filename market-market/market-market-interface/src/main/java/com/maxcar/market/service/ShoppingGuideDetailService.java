package com.maxcar.market.service;

import com.maxcar.market.pojo.ShoppingGuideDetail;

import java.util.List;

public interface ShoppingGuideDetailService {

    /**
     * 新增或修改导购信息
     * @param shoppingGuideDetail
     * @return
     */
    boolean addOrUpdateShoppingGuideDetail(ShoppingGuideDetail shoppingGuideDetail);

    /**
     * 获取市场导购信息
     * @param shoppingGuideDetail
     * @return
     */
    List<ShoppingGuideDetail> getShoppingGuideDetail(ShoppingGuideDetail shoppingGuideDetail);
}
