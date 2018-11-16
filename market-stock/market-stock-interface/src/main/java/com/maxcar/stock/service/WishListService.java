package com.maxcar.stock.service;


import com.maxcar.base.service.BaseService;
import com.maxcar.stock.pojo.WishList;
import com.maxcar.stock.pojo.WxWishListCar;

import java.util.List;

/**
 * 心愿单
 */
public interface WishListService extends BaseService<WishList,String> {

    void insertWishList(WishList wishList);
    List<WxWishListCar> getWishListCar(WishList wishList);
}
