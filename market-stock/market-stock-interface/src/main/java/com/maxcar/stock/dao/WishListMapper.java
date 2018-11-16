package com.maxcar.stock.dao;

import com.maxcar.base.dao.BaseDao;

import com.maxcar.stock.pojo.*;

import java.util.List;


public interface WishListMapper extends BaseDao<WishList,String>{

    void insertWishList(WishList wishList);

    List<WxWishListCar> getWishListCar(WishList wishList);
}